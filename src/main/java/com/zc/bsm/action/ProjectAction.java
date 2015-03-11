package com.zc.bsm.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import com.zc.abf.pojo.Operator;
import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.util.FileUtils;
import com.zc.base.util.Page;
import com.zc.base.util.PdfTools;
import com.zc.base.util.SignTools;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.pojo.CompanyAttachment;
import com.zc.bsm.pojo.DynamicRate;
import com.zc.bsm.pojo.DynamicRateByProject;
import com.zc.bsm.pojo.LoansSend;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.RepaymentPlanVerify;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.CallBackService;
import com.zc.bsm.service.CompanyAttachmentService;
import com.zc.bsm.service.CompanyService;
import com.zc.bsm.service.DynamicRateByProjectService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LoansSendService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.OperatorLogService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.RepaymentPlanVerifyService;
import com.zc.bsm.service.RepaymentSendService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.QueryTransStat;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.vo.returnVo.QueryTransStatReturn;
import com.zc.bsm.webVo.RepayProject;

@Controller
public class ProjectAction extends Action{
   //著录
	@Autowired
	private ProjectService projectService;
	@Autowired
	private bizDataService bizdataservice;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private TenderService tenderService;
    @Autowired
    private LoansSendService loansSendService;
    @Autowired
    private RepaymentPlanService repaymentPlanService;
    @Autowired
    private CompanyAttachmentService companyAttachmentService;
    @Autowired
	private  BorrowerService borrowerService;
    @Autowired
    private  EnchashmentService enchashmentService;
    @Autowired
    private  RepaymentPlanVerifyService repaymentPlanVerifyService;
    @Autowired
    private  LuckyMoneyService luckyMoneyService;
    @Autowired
    private  RepaymentSendService repaymentSendService;
    @Autowired
    private  CallBackService callBackService;
    @Autowired
    private  OperatorLogService operatorLogService;
    @Autowired
    private  DynamicRateByProjectService dynamicRateByProjectService;
    //动态利率集合
    private List<Object>  dynamicRateList;
	private Project project;
	private List<Project> projectList;
    List<Company> comList;
    List<Company> comList2;
    private List percentList;
    private List<CompanyAttachment> caList = new ArrayList<CompanyAttachment>();
	/**
	 * 产品详情展示  前台
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/project/view")
	@NeedInterceptor
	public ModelAndView view(Long id,HttpSession session,HttpServletRequest request){
		project = projectService.get(id);
		ModelAndView modelAndView = new ModelAndView("project/product_view");
		if(project.getState()<0&&(request.getHeader("referer")==null||request.getHeader("referer").indexOf("/auth/project/productListModify")==-1)){
			modelAndView = new ModelAndView("redirect:/index");
			return modelAndView;
		}
//		Company company =null;
//		Company company2 =null;
//		if(project.getDbid()!=null){
//			company= projectService.findByCId(project.getDbid());
//		}
//		if(project.getXtid()!=null){
//			company2= projectService.findByCId(project.getXtid());
//		}
//       
//		
//		modelAndView.addObject("model", project);
//		modelAndView.addObject("company", company);
//		modelAndView.addObject("company2", company2);
//		BigDecimal release = null;
//		//剩余可投资金额   如果是屌丝宝  同时计算个人最大可投资金额  和项目资金缺口
//		User user=(User) session.getAttribute("user");
//		if(user!=null&&project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1){
//			List releaseList = tenderService.findSumTransmat(id,user.getHuifuID());
//			if(releaseList!=null&&releaseList.get(0)!=null&&!releaseList.get(0).toString().equals("")){
//				release = new BigDecimal(releaseList.get(0).toString());
//				release = project.getSelf_highest_money().subtract(release);
//			}
//		}
//		if(user!=null){
//			//先查询账户余额
//			QueryBalanceBgReturn _return = enchashmentService.getBalanceObj(user);
//			modelAndView.addObject("self_money", _return.getAvlBal());
//			//可用红包数量
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			LuckyMoney luckyMoney = new LuckyMoney();
//			luckyMoney.setUid(user.getId());
//			luckyMoney.setStart_time(sdf.format(new Date()));
//			luckyMoney.setEnd_time(sdf.format(new Date()));
//			luckyMoney.setType(project.getType().toString());
//			luckyMoney.setState(0);
//			modelAndView.addObject("luckyCount", luckyMoneyService.findListBySelf(luckyMoney).size());
//		}
//		//最低单笔投资金额  最高单笔投资金额  如果最大可投资金额小于最低单笔  则最低单笔和最高单笔皆为最大可投资金额
//		BigDecimal low = null;
//		BigDecimal high = null;
//		if(project.getTotal_money().subtract(project.get_now_money()).compareTo(new BigDecimal(project.getLowest_money()))!=-1){
//			low = new BigDecimal(project.getLowest_money());
//			high = new BigDecimal(project.getHighest_money());
//		}else{
//			low = project.getTotal_money().subtract(project.get_now_money());
//			high =project.getTotal_money().subtract(project.get_now_money());
//		}
//		//如果剩余可投资金额为空  则剩余可投资金额等于总投资金额-已投资金额  否则比对
//		if(release==null){
//			release = project.getTotal_money().subtract(project.get_now_money());
//		}else{
//			release = project.getTotal_money().subtract(project.get_now_money()).compareTo(release)!=-1?release:project.getTotal_money().subtract(project.get_now_money());
//		}
//			
//		modelAndView.addObject("low", low);
//		modelAndView.addObject("high", high);
//		modelAndView.addObject("release", release);
//		int percent = project.get_now_money().multiply(new BigDecimal(100)).divide(project.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
//		//投资比率
//		modelAndView.addObject("percent", percent);
//		//附件
//        modelAndView.addObject("companyAttachment", projectService.findAttByCId(project.getId()));
//        modelAndView.addObject("borrower",borrowerService.get(project.getBorrowId()));
//        modelAndView.addObject("picpath",SignUtils.PIC_HOST);
//        //服务器当前时间 
//        modelAndView.addObject("date",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//        //是否逾期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try{
//        	modelAndView.addObject("dateTag",sdf.parse(project.getEnd_time().length()==10?(project.getEnd_time()+" 23:59:59"):project.getEnd_time()).compareTo(new Date()));
//        	modelAndView.addObject("startTag",sdf.parse(project.getStart_time()).compareTo(new Date()));
//        }catch(Exception e){e.printStackTrace();}
		User user=(User) session.getAttribute("user");
		this.projectService.view(modelAndView, user, project);
//		if(user!=null&&project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1){
//			List releaseList = tenderService.findSumTransmat(id,user.getHuifuID());
//			if(releaseList!=null&&releaseList.get(0)!=null&&!releaseList.get(0).toString().equals("")){
//				release = new BigDecimal(releaseList.get(0).toString());
//				release = project.getSelf_highest_money().subtract(release);
//			}else{
//				release = project.getSelf_highest_money();
//			}
//		}
//		//如果剩余可投资金额为空  则剩余可投资金额等于总投资金额-已投资金额  否则比对
//		if(release==null){
//			if(project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1)
//				release = project.getSelf_highest_money();
//			else
//				release = project.getTotal_money().subtract(project.get_now_money());
//		}else{
//			release = project.getTotal_money().subtract(project.get_now_money()).compareTo(release)!=-1?release:project.getTotal_money().subtract(project.get_now_money());
//		}
//		if(user!=null){
//			//先查询账户余额
//			QueryBalanceBgReturn _return = enchashmentService.getBalanceObj(user);
//			modelAndView.addObject("self_money", _return.getAvlBal());
//			//可用红包数量
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			LuckyMoney luckyMoney = new LuckyMoney();
//			luckyMoney.setUid(user.getId());
//			luckyMoney.setStart_time(sdf.format(new Date()));
//			luckyMoney.setEnd_time(sdf.format(new Date()));
//			luckyMoney.setType(project.getType().toString());
//			luckyMoney.setState(0);
//			modelAndView.addObject("luckyCount", luckyMoneyService.findListBySelf(luckyMoney).size());
//		}
//		//最低单笔投资金额  最高单笔投资金额  如果最大可投资金额小于最低单笔  则最低单笔和最高单笔皆为最大可投资金额
//		BigDecimal low = null;
//		BigDecimal high = null;
//		if(project.getTotal_money().subtract(project.get_now_money()).compareTo(new BigDecimal(project.getLowest_money()))!=-1){
//			low = new BigDecimal(project.getLowest_money());
//			high = new BigDecimal(project.getHighest_money());
//		}else{
//			low = project.getTotal_money().subtract(project.get_now_money());
//			high =project.getTotal_money().subtract(project.get_now_money());
//		}
//			
//		modelAndView.addObject("low", low);
//		modelAndView.addObject("high", high);
//		modelAndView.addObject("release", release);
//		int percent = project.get_now_money().multiply(new BigDecimal(100)).divide(project.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
//		//投资比率
//		modelAndView.addObject("percent", percent);
//		//附件
//        modelAndView.addObject("companyAttachment", projectService.findAttByCId(project.getId()));
//        modelAndView.addObject("borrower",borrowerService.get(project.getBorrowId()));
//        modelAndView.addObject("picpath",SignUtils.PIC_HOST);
//        //服务器当前时间 
//        modelAndView.addObject("date",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
//        //是否逾期
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try{
//        	modelAndView.addObject("dateTag",sdf.parse(project.getEnd_time().length()==10?(project.getEnd_time()+" 23:59:59"):project.getEnd_time()).compareTo(new Date()));
//        	modelAndView.addObject("startTag",sdf.parse(project.getStart_time()).compareTo(new Date()));
//        }catch(Exception e){e.printStackTrace();}
//>>>>>>> .r4087
        return modelAndView;
	}
	
	/**
	 * 产品详情展示  后台
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/view")
	@NeedInterceptor
	public ModelAndView authview(Long id,HttpSession session){
		project = projectService.get(id);
		Company company = projectService.findByCId(project.getDbid());
        Company company2 = projectService.findByCId(project.getXtid());
		ModelAndView modelAndView = new ModelAndView("project/auth_product_view");
		modelAndView.addObject("model", project);
		modelAndView.addObject("company", company);
		modelAndView.addObject("company2", company2);
		BigDecimal release = null;
		//剩余可投资金额   如果是屌丝宝  同时计算个人最大可投资金额  和项目资金缺口
		User user=(User) session.getAttribute("user");
		if(user!=null&&project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1){
			List releaseList = tenderService.findSumTransmat(id,user.getHuifuID());
			if(releaseList!=null&&releaseList.get(0)!=null&&!releaseList.get(0).toString().equals("")){
				release = new BigDecimal(releaseList.get(0).toString());
				release = project.getSelf_highest_money().subtract(release);
			}
		}
		//最低单笔投资金额  最高单笔投资金额  如果最大可投资金额小于最低单笔  则最低单笔和最高单笔皆为最大可投资金额
		BigDecimal low = null;
		BigDecimal high = null;
		if(project.getTotal_money().subtract(project.get_now_money()).compareTo(new BigDecimal(project.getLowest_money()))!=-1){
			low = new BigDecimal(project.getLowest_money());
			high = new BigDecimal(project.getHighest_money());
		}else{
			low = project.getTotal_money().subtract(project.get_now_money());
			high =project.getTotal_money().subtract(project.get_now_money());
		}
		//如果剩余可投资金额为空  则剩余可投资金额等于总投资金额-已投资金额  否则比对
		if(release==null){
			release = project.getTotal_money().subtract(project.get_now_money());
		}else{
			release = project.getTotal_money().subtract(project.get_now_money()).compareTo(release)!=-1?release:project.getTotal_money().subtract(project.get_now_money());
		}
			
		modelAndView.addObject("low", low);
		modelAndView.addObject("high", high);
		modelAndView.addObject("release", release);
		int percent = project.get_now_money().multiply(new BigDecimal(100)).divide(project.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
		//投资比率
		modelAndView.addObject("percent", percent);
		//附件
        modelAndView.addObject("companyAttachment", projectService.findAttByCId(project.getXtid()));
        modelAndView.addObject("borrower",borrowerService.get(project.getBorrowId()));
        modelAndView.addObject("picpath",SignUtils.PIC_HOST);
        return modelAndView;
	}
	/**
	 * 产品详细页 展示可用红包列表
	 * @param session
	 * @param transAmt
	 * @return
	 */
	@RequestMapping(value="/project/luckyMoneyList")
	@ResponseBody
	public Object luckyMoneyList(HttpSession session,BigDecimal transAmt,Integer type,BigDecimal hongbaorate){
		User user = (User) session.getAttribute("user");
		if(user != null){
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			LuckyMoney luckyMoney = new LuckyMoney();
//			luckyMoney.setUid(user.getId());
//			luckyMoney.setStart_time(sdf.format(new Date()));
//			luckyMoney.setEnd_time(sdf.format(new Date()));
//			luckyMoney.setInvestLimit(transAmt);
//			luckyMoney.setType(type.toString());
//			luckyMoney.setState(0);
//			Gson gson = new Gson();
//			return gson.toJson(luckyMoneyService.findListBySelf(luckyMoney));			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			LuckyMoney luckyMoney = new LuckyMoney();
			luckyMoney.setUid(user.getId());
			luckyMoney.setStart_time(sdf.format(new Date()));
			luckyMoney.setEnd_time(sdf.format(new Date()));
			luckyMoney.setInvestLimit(transAmt.multiply(hongbaorate).multiply(new BigDecimal(0.01)));
			luckyMoney.setType(type.toString());
			luckyMoney.setState(0);
			Gson gson = new Gson();
			List lList = luckyMoneyService.findListBySelf2(luckyMoney);
			if(lList!=null&&lList.size()>0)
				return gson.toJson(lList);
			else{
				//如果没有红包展示使用金额最小的红包所需要的金额
				lList = luckyMoneyService.findListBySelf3(luckyMoney);
				if(lList!=null&&lList.size()>0){
					return ((List)lList.get(0)).get(1).toString();
				}else{
					return "noLucky";
				}
			}
		}
		return null;
	}
	
	@RequestMapping(value="/project/tenderList")
	@ResponseBody
	@NeedInterceptor
	public String tenderList(Long id,Page page){
		page = tenderService.findPageByPid(id, page);
		List list = new ArrayList();
		list.add(page.getResult());
		list.add(page.getTotalPage());
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	//转到添加页面
	@RequestMapping(value="/auth/project/toAdd")
	public ModelAndView toAdd(Long id){
		ModelAndView modelAndView = new ModelAndView("project/add");
		project=null;
		caList=null;
		if(id!=null){
			project = projectService.get(id);
			caList = companyAttachmentService.findAllCid(project.getId());
		}
		modelAndView.addObject("caList", caList);
		modelAndView.addObject("model", project);
		return modelAndView;
	}
	/**
	 * 	转到列表
	 */
	@RequestMapping(value="/auth/project/productListModify")
	public String productListModify(Page ppage,Project project){
		ppage.setRows(5);
		page = projectService.findByPage(ppage, project);
		int percent = 0;
		for(Project p : (List<Project>)page.getResult()){
			percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			p.setFlag(percent+"");
		}
		return "project/productListModify";
	}
	/**
	 * 	转到添加页面
	 */
	@RequestMapping(value="/auth/project/toAddPre")
	public ModelAndView toAddPre(Long id,Long tag){
		project = null;
		caList = null;
		if(id!=null){
		    project = projectService.get(id);
			caList = companyAttachmentService.findAllCid(project.getId());
			project.setDay_rate(project.getDay_rate().multiply(new BigDecimal(100)));
		}else{
			project = new Project();
			try{
				this.bizdataservice.bizSave(Project.class, project);
			}catch(Exception e ){
				
			}
		}
        comList = companyService.findXt();
        comList2 = companyService.findDb();
		ModelAndView modelAndView = new ModelAndView("project/product_add");
		modelAndView.addObject("model", project);
		modelAndView.addObject("comList",comList);
        modelAndView.addObject("comList2",comList2);
        modelAndView.addObject("caList",caList);
        modelAndView.addObject("picpath",SignUtils.PIC_HOST);
        if(tag!=null){	
        	modelAndView.addObject("tag",tag);
        }
		return modelAndView;
	}
	/**
	 * 添加
	 * @return
	 */
	@RequestMapping(value="/auth/project/add")
	public String add(HttpSession session,Project model,@RequestParam("tag") int tag,@RequestParam("_tag") Integer _tag,HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		model.setState(-1);
		//日利率/100
		model.setDay_rate(model.getDay_rate().divide(new BigDecimal(100)));
		boolean saveOrNot = true;
		if(model.getId()!=null&&model.getId()!=0){
			saveOrNot = false;
		}
		if(model.getCommission_agent()==null)
			model.setCommission_agent(new BigDecimal(0));
		if(model.getCommission()==null)
			model.setCommission(new BigDecimal(0));
		if(model.getHongbaorate()==null)
			model.setHongbaorate(new BigDecimal(0));
		if(model.getOnline()==null)
			model.setOnline(0);
		if(model.getPay_number()==null)
			model.setPay_number(0);
		if(model.getCreateTime()==null||model.getCreateTime().equals(""))
			model.setCreateTime(sdf.format(date));
		if(model.getNow_money()==null){
			model.setNow_money(new BigDecimal(0));
		}
		if(model.get_now_money()==null){
			model.set_now_money(new BigDecimal(0));
		}
		if(model.getRepay_money()==null)
			model.setRepay_money(new BigDecimal(0));
		if(model.getXtid()==0)
			model.setXtid(null);
		if(model.getDbid()==0){
			model.setDbid(null);
		}
		model.setUpdateTime(sdf.format(date));
		projectService.saveOrUpdate(model);
		//附件存储
		//leixing0  name  
		//imgUrl0   url  split(,)
		//tag 
		String name="";
		String imgUrl="";
		String imgName = "";
		String [] imgS;
		String [] imgN;
		CompanyAttachment ca = null; 
		this.companyAttachmentService.deleteAllCid(model.getId());
		for(int i=0;i<tag;i++){
			imgUrl = "";
			name = request.getParameter("leixing"+i);
			imgS = request.getParameterValues("imgUrl"+i);
			imgN = request.getParameterValues("imgName"+i);
			if(name!=null&&!name.equals("")&&imgS!=null&&imgS.length>0){
				for(int j = 0 ; j < imgS.length ; j++){
					imgUrl += imgS[j]+",";
					imgName += imgN[j]+",";
				}
				ca = new CompanyAttachment();
				ca.setFlag("0");
				ca.setName(name);
				ca.setUrl(imgUrl);
				ca.setNames(imgName);
				ca.setCreateTime(sdf.format(date));
				ca.setCid(model.getId());
				this.companyAttachmentService.save(ca);
			}
		}
		Operator operator = (Operator)session.getAttribute("operator");
		if(_tag==null){
			//添加/修改产品
			if(saveOrNot)
				operatorLogService.saveLog(operator, model, "1");
			else
				operatorLogService.saveLog(operator, model, "2");
			return "redirect:/auth/project/productListModify";
		}else if (_tag==1){
			//查看产品
			return "redirect:/auth/project/productListModify";
		}else if (_tag==11){
			//审核产品
			operatorLogService.saveLog(operator, model, "3");
			return "redirect:/auth/project/preVerify";
		}else{
			return "redirect:/auth/project/productListModify";
		}
	}
	
	/**
	 *
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/auth/project/list")
	public String list(Page ppage,Project project){
		//state >= 0
		project.setState(-101);
		page = projectService.findByPage(ppage, project);
		return "project/list";
	}
	/**
	 *
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/auth/project/preVerify")
	public String preVerify(Page ppage,Project project){
		page = projectService.findByPage(ppage, project);
		int percent = 0;
		for(Project p : (List<Project>)page.getResult()){
			percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			p.setFlag(percent+"");
		}
		return "project/product_verify";
	}
	@RequestMapping(value="/auth/project/_verify")
	@ResponseBody
	public String _verify(Long id ,Integer state){
		projectService._verify(id, state);
		return "ok";
	}
	/**
	 * 后台列表
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/auth/project/preList")
	public String preList(Page ppage,Project project,ModelMap map){
		ppage.setRows(5);
		if(project==null)project=new Project();
		project.setState(-101);
		project.setFlag("publictime");//表示按此条件order by desc
		page = projectService.findByPage(ppage, project);
		for(Project p : (List<Project>)page.getResult()){
			int percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			p.setFlag(percent+"");
		}
		//当前时间
		try {
			map.put("nowDate", StringUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "project/product_list";
	}
	/**
	 * 订单总数 成功数  失败数
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/auth/project/checkLoans")
	@ResponseBody
	public String checkLoans(Long id){
		if(id!=null&&id!=0){
			return tenderService.checkLoansCount(id,null).get(0).toString()+","+tenderService.checkLoansCount(id,1).get(0).toString()+","+tenderService.checkLoansCount(id,-1).get(0).toString();
		}
		return null;
	}
	/**
	 * 查询未知条目的交易状态（深度查询放款
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/transState")
	@ResponseBody
	public String transState(Long id){
		TransSubmit ts = new TransSubmit();
		QueryTransStat tr=new QueryTransStat();
		QueryTransStatReturn _return ;
		List<LoansSend> loansSendList = loansSendService.checkLoans(id);
		Tender t;
		a:
		for(int i=0;i<loansSendList.size();i++){
			LoansSend l=loansSendList.get(i);
			tr.setOrdId(l.getId().toString());
			tr.setQueryTransType("LOANS");
			tr.setOrdDate(l.getOrdDate());
			_return = ts.getQueryTransStat(tr);
			if(_return.validate()&&_return.getRespCode().equals("000")){
				callBackService.LoansTransState(_return);
				break a;
			}else if(_return.validate()&&(i+1)==list.size()){
				callBackService.LoansTransState(_return);
				break a;
			}
		}
		return "sended";
	}
	/**
	 * 查询未知条目的交易状态（深度查询还款
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/transRepayState")
	@ResponseBody
	public String transRepayState(Long id){
		TransSubmit ts = new TransSubmit();
		QueryTransStat tr=new QueryTransStat();
		QueryTransStatReturn _return ;
		RepaymentSend repaymentSend = null;
		List<RepaymentPlan> loansSendList = repaymentPlanService.checkRepaymentPlan(id);
		for(RepaymentPlan l : loansSendList){
			List list = repaymentSendService.findByHql("from RepaymentSend where planId = "+l.getId()+" order by id desc");
			a:
			for(int i=0;i<list.size();i++){
				Object obj=list.get(i);
				repaymentSend = (RepaymentSend)obj;
				tr.setOrdId(repaymentSend.getId().toString());
				tr.setQueryTransType("REPAYMENT");
				tr.setOrdDate(l.getOrdDate());
				_return = ts.getQueryTransStat(tr);
				if(_return.validate()&&_return.getRespCode().equals("000")){
					callBackService.printRepaymentTransState(_return);
					break a;
				}else if(_return.validate()&&(i+1)==list.size()){
					callBackService.printRepaymentTransState(_return);
					break a;
				}
				
			}
		}
		return "sended";
	}
	/**
	 * 产品开始时间  产品结束时间
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/resetStartEndTime")
	@ResponseBody
	public String resetStartEndTime(HttpSession session,Long id,String time1,String time2,String time3){
		if(id!=null&&id!=0){
			project = projectService.get(id);
			project.setPstartTime(time1);
			project.setPendTime(time2);
			project.setRateTime(time3);
			project.setState(1);
			projectService.saveOrUpdate(project);
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, project, "9");
			//给所有Brokerage添加奖励时间
			String hql = "update Brokerage set rewardTime = '"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"' where project.id = "+id;
			this.bizdataservice.bulkUpdate(hql);
		}
		return "sended";
	}
	
	/**
	 * 生成还款计划
	 * @param ppage
	 * @param project
	 * @return
	 */
	@RequestMapping(value="/auth/project/createRepaymentPlan")
	@ResponseBody
	public String createRepaymentPlan(HttpSession session,Project project){
		if(project!=null&&project.getId()!=null&&project.getId()!=0l){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			project = projectService.get(project.getId());
			if(project.getRepay_type().equals("-2")){
				return "auto";
			}
			///判断是否已经还款 
			try{
				if(!repaymentPlanService.checkCount(project.getId()).get(0).toString().equals("0")){
					return "outOfDate";
				}
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			//生成前删除所有有关还款计划
//			int applyCount = repaymentPlanService.deleteByPId(project.getId());
//			//生成前还款计划审核情况的删除
//			repaymentPlanVerifyService.deletebyprojectid(project.getId());
			
			
			//是否之前生成过还款计划
			if(repaymentPlanService.selectCountByPId(project.getId())!=0){
				return null;
			}
			
			List<Tender> list = tenderService.findByPid(project.getId());
			List<RepaymentPlan> _result = new ArrayList<RepaymentPlan>();
			List luckyList = new ArrayList();
			RepaymentPlan rp ;
			LuckyMoney luckyMoney = null;
			String cTime = sdf1.format(new Date());
			int repayAllCount = 1;
			int repaySpace = Integer.parseInt(project.getRepay_type());
			BigDecimal totalRepay = new BigDecimal(0);
			//计算还款计划期数
			if(!project.getRepay_type().equals("-1")){
				repayAllCount = project.getTime_limit()+project.getDelay_time_limit();
				repayAllCount = repayAllCount%repaySpace==0?repayAllCount/repaySpace:((repayAllCount/repaySpace)+1);
			}
			for(Tender t : list){
				//if(applyCount==0){
					//之前没有任何记录  即第一次生成还款计划
					//循环所有标  判断是否有红包id
					if(t.getLuckyId()!=null){
						luckyMoney = luckyMoneyService.get(t.getLuckyId());
						if(luckyMoney.getState()==1){
							//如果未兑现  状态更改为待审核
							luckyMoney.setState(2);
							luckyList.add(luckyMoney);
						}
					}
				//}
				try{
					Date repayStartDate = sdf.parse(project.getRateTime());
					Date repayEndDate = sdf.parse(project.getPstartTime());
					if(!project.getRepay_type().equals("-1")){
						repayEndDate.setMonth(repayEndDate.getMonth()+Integer.parseInt(project.getRepay_type()));
					}else{
						repayEndDate = sdf.parse(project.getPendTime());
					}
					for(int i = 0 ; i < repayAllCount ; i++){
						rp = new RepaymentPlan();
						//rp.setUserId();
						rp.setUsrCustId(t.getUsrCustId());
						rp.setProjectId(project.getId());
						rp.setTenderId(t.getSrcTenderId());
						rp.setOrdDate(t.getOrdDate());
						rp.setRepayTime(sdf.format(repayEndDate));
						rp.setRepayCount((i+1));
						
						totalRepay = totalRepay.add(SignTools.getRate(repayStartDate,repayEndDate,project.getDay_rate(),t.getTransAmt()));
						rp.setRepayMoney(SignTools.getRate(repayStartDate,repayEndDate,project.getDay_rate(),t.getTransAmt()));
						
						rp.setState(-1);
						rp.setFlag(0);
						rp.setCreateTime(cTime);
						rp.setUpdateTime(cTime);
						rp.setStart_time(sdf.format(repayStartDate));
						rp.setEnd_time(sdf.format(repayEndDate));
						_result.add(rp);
						//更改起始/终结日期
						repayStartDate.setTime(repayEndDate.getTime());
						repayEndDate.setMonth(repayEndDate.getMonth()+Integer.parseInt(project.getRepay_type()));
						if(repayEndDate.compareTo(sdf.parse(project.getPendTime()))==1){
							repayEndDate = sdf.parse(project.getPendTime());
						}
					}
					rp = new RepaymentPlan();
					//rp.setUserId();
					rp.setUsrCustId(t.getUsrCustId());
					rp.setProjectId(project.getId());
					rp.setTenderId(t.getSrcTenderId());
					rp.setOrdDate(t.getOrdDate());
					rp.setRepayTime(project.getPendTime());
					rp.setRepayCount(0);
					rp.setRepayMoney(t.getTransAmt());
					
					rp.setState(-1);
					rp.setFlag(0);
					rp.setCreateTime(cTime);
					rp.setUpdateTime(cTime);
					totalRepay = totalRepay.add(t.getTransAmt());
					rp.setStart_time(sdf.format(repayStartDate));
					rp.setEnd_time(project.getPendTime());
					_result.add(rp);
				}catch(Exception e ){
					e.printStackTrace();
				}
			}
			if(luckyList.size()>0){
				luckyMoneyService.saveOrUpdateBatch(luckyList);
			}
			if(_result.size()>0){
				repaymentPlanService.saveOrUpdateBatch(_result);
				//计算总还款金额存入project
				project.setTotalRepay(totalRepay);
				project.setState(1);
				projectService.saveOrUpdate(project);
			}
			
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, project, "10");
			//更新开放日
			List drbp = this.dynamicRateByProjectService.getListById(project.getId());
			try{
				for(Object obj:drbp){
					Date date = sdf.parse(project.getPstartTime());
					DynamicRateByProject drb = (DynamicRateByProject)obj;
					date.setMonth(date.getMonth()+drb.getMonth());
					drb.setStartTime(sdf.format(date));
					this.dynamicRateByProjectService.saveOrUpdate(drb);
				}
			}catch(Exception e ){
				e.printStackTrace();
			}
			return "sended";
		}
		return null;
	}
	/**
	 * 还款管理列表
	 * @param project
	 * @return
	 */
	@RequestMapping(value="/auth/project/repayManage")
	public ModelAndView repayManage(Page page,Project project,String startTime,String endTime){
		project.setPstartTime(startTime);
		project.setPendTime(endTime);
		ModelAndView model = new ModelAndView("project/repayManage");
		//还款中 以及  还款完成
		project.setState(-100);
		page = projectService.findByRepayPage(page, project);
		if(project.getType()==null||project.getType()==0){
			//
		}
		//循环获取审批通过待还款项目
		for(Object obj:page.getResult()){
			RepayProject rp=(RepayProject) obj;
			StringBuilder hql = new StringBuilder();
			hql.append("select count(DISTINCT projectId) from RepaymentPlanVerify where projectId = ? and flag = 0  and state=1");
		   Object accRepayCountObj=	this.bizdataservice.getRowCount(hql.toString(), rp.getId());
			rp.setAccRepayCount(Integer.parseInt(accRepayCountObj.toString()));
		}
		model.addObject("page",page);
		model.addObject("project",project);
		model.addObject("startTime",startTime);
		model.addObject("endTime",endTime);
		return model;
	}
	/**
	 * 还款管理列表 
	 * @param project
	 * @return
	 */
	@RequestMapping(value="/auth/project/repayDetail")
	public ModelAndView repayDetail(Long id){
		ModelAndView model = new ModelAndView("project/repayDetail");
		if(id!=null&&id!=0){
			project = projectService.get(id);
			//先查询账户余额
			User user = new User();
			user.setHuifuID(borrowerService.get(project.getBorrowId()).getUsrCustId());
			QueryBalanceBgReturn _return = enchashmentService.getBalanceObj(user);
			//还款计划
			List<RepayProject> planList = repaymentPlanService.findByPid(id,null);
			List<RepayProject> _planList = repaymentPlanService.findByPid(id,-1);
			_planList.addAll(repaymentPlanService.findByPid(id,0));
			//已经还款
			List<RepayProject> planList2 = new ArrayList<RepayProject>();
			for(int i=0;i<planList.size();i++){
				int j = 0 ;
				for(; j < _planList.size() ; j++){
					if(planList.get(i).getRepayCount().equals(_planList.get(j).getRepayCount())&&planList.get(i).getRepayTime().equals(_planList.get(j).getRepayTime())){
						planList.get(i).setRepay_money(_planList.get(j).getRepayMoney());
						break;
					}
				}
				if(j==_planList.size()){
					planList2.add(planList.get(i));
					planList.remove(i);
					i--;
				}
			} 
			List<RepaymentPlanVerify> verifyList = repaymentPlanVerifyService.findAllByPid(id);
			for(RepayProject rp : planList){
				for(RepaymentPlanVerify rv : verifyList){
					if(rp.getRepayCount().intValue()==rv.getRepayCount().intValue()&&rp.getRepayTime().equals(rv.getRepayTime())){
						rp.setVerifyState(rv.getState());
					}
				}
			}
			
			model.addObject("avlBal",_return.getAvlBal());
			model.addObject("model",project);
			model.addObject("planList",planList);
			model.addObject("planList2",planList2);
			
		}
		return model;
	}
	/**
	 * 还款计划总数 成功数  失败数
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/auth/project/checkRepaymentPlans")
	@ResponseBody
	public String checkRepaymentPlans(Long id,Integer repayCount,String repayTime){
		if(id!=null&&id!=0){
			return repaymentPlanService.checkCount(id, repayCount, null,repayTime).get(0).toString()+","+repaymentPlanService.checkCount(id, repayCount, 1,repayTime).get(0).toString()+","+repaymentPlanService.checkCount(id, repayCount, -1,repayTime).get(0).toString();
		}
		return null;
	}
	/**
	 * 发起还款资金审核
	 * @param id
	 * @param repayCount
	 * @return
	 */
	@RequestMapping(value="/auth/project/RepaymentPlanVerify")
	@ResponseBody
	public String RepaymentPlanVerify(HttpSession session ,Long id,Integer repayCount,String repayTime){
		RepaymentPlanVerify verify = new RepaymentPlanVerify();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		List<Object[]> sum = repaymentPlanService.findSumByPid(id, -1, repayCount,repayTime);
		verify.setProjectId(id);
		verify.setRepayCount(repayCount);
		verify.setRepayMoney(new BigDecimal(sum.get(0)[0]+""));
		verify.setRepayTime(sum.get(0)[1]+"");
		verify.setState(0);
		verify.setCreateTime(sdf.format(date));
		verify.setFlag("0");
		verify.setUpdateTime(sdf.format(date));
		//here to update
		if(repaymentPlanVerifyService.checkIdentity(verify))
			repaymentPlanVerifyService.saveOrUpdate(verify);
		Operator operator = (Operator)session.getAttribute("operator");
		operatorLogService.saveLog(operator, verify, "15");
		return "OK";
	}
	/**
	 * 还款资金审核列表
	 * @return
	 */
	@RequestMapping(value="/auth/repaymentPlan/verifyList")
	public ModelAndView RepaymentPlanVerifyList(Page page){
		ModelAndView model = new ModelAndView("project/repaymentPlanVerifyList");
		
		page = repaymentPlanVerifyService.findByPage(page, null);
		for(Object obj:page.getResult()){
			List list = (List)obj;
			Project project = (Project)this.bizdataservice.bizfindbyid(Project.class, list.get(8));
			//查询账户余额
			QueryBalanceBgReturn _return = enchashmentService.getBalanceObj(borrowerService.get(project.getBorrowId()).getUsrCustId());
			list.set(9, _return.getAvlBal());
		}
		model.addObject("page",page);
		return model;
	}
	/**
	 * 审核还款计划
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/auth/repaymentPlan/verify")
	@ResponseBody
	public String Verify(HttpSession session,Long id,Integer state){
		String flag="";
		if(state==1)flag="0";//审核通过
		if(state==-1)flag="1";//审核不通过
		repaymentPlanVerifyService.changeState(id, state,flag);
		Operator operator = (Operator)session.getAttribute("operator");
		operatorLogService.saveLog(operator, this.bizdataservice.bizfindbyid(RepaymentPlanVerify.class, id), "17");
		return "ok";
	}
	/**
	 * 忽略还款计划
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/auth/repaymentPlan/ignore")
	@ResponseBody
	public String ignore(HttpSession session,Long id,Integer repayCount,String repayTime){
		Operator operator = (Operator)session.getAttribute("operator");
		project = projectService.get(id);
		operatorLogService.saveLog(operator, project, "18");
		return repaymentPlanService.ignore(id, repayCount,repayTime)+"";
	}
	/**
	 * 还款计划完结
	 * @param id
	 * @param state
	 * @return
	 */
	@RequestMapping(value="/auth/repaymentPlan/overPlan")
	@ResponseBody
	public String overPlan(Long id){
		return repaymentPlanService.overPlan(id)+"";
	}
//	/**
//	 * 红包资金审核列表
//	 * @return
//	 */
//	@RequestMapping(value="/auth/luckyMoney/verifyList")
//	public ModelAndView LuckyMoneyVerifyList(Page page){
//		ModelAndView model = new ModelAndView("project/luckyMoneyVerifyList");
//		
//		page = luckyMoneyService.findVerifyByPage(page, null);
//		
//		model.addObject("page",page);
//		return model;
//	}
//	@RequestMapping(value="/auth/luckyMoney/verify")
//	@ResponseBody
//	public String LuckyMoneyVerify(Long id,Integer state){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
//		//根据传入pid搜索  红包表内所有有效数据（uid不为空 state==2 projectId==id等）
//		List<LuckyMoney> luckyList = luckyMoneyService.findVerifyListByPid(id);
//		boolean tag = false;
//		PlatformTransfer platformtransfer = new PlatformTransfer();
//		User user = null;
//		if(state==3){
//			//循环红包列表  启用平台转账接口 
//			for(LuckyMoney lucky : luckyList){
//				user = (User)bizdataservice.bizfindbyid(User.class, lucky.getUid());
//				platformtransfer.setInCustId(user.getHuifuID());
//				platformtransfer.setInAcctId(user.getHuifuID());
//				platformtransfer.setTransAmt(lucky.getMoney());
//				platformtransfer.setOutCustId(SignUtils.MER_CUST_ID);
//				platformtransfer.setOutAcctId(SignUtils.MER_CUST_ACCT_ID);
//				bizdataservice.saveOrUpdate(platformtransfer);
//				boolean qbr=transferService.trytransfer(platformtransfer);
//				System.out.println(qbr);
//			}
//			//
//			tag=true;
//		}else if (state == 0){
//			tag=true;
//		}
//		if(tag){
//			//更改所有红包状态
//			for(LuckyMoney lucky : luckyList){
//				lucky.setState(state);
//				lucky.setUpdateTime(sdf.format(date));
//			}
//			luckyMoneyService.saveOrUpdateBatch(luckyList);
//			return "ok";
//		}
//		return null;
//	}
	/**
	 *	上传
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/auth/project/upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile[] files,HttpServletRequest request){
		String path = "";
		for(MultipartFile f:files){
			try{
				//path += FileUtils.saveFileFromInputStream(f.getInputStream(), request.getSession().getServletContext().getRealPath("/") , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += FileUtils.saveFileFromInputStream(f.getInputStream(),SignUtils.PIC_REALPAHT , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += ",";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return path.length()>0?path.substring(0,path.length()-1):"-1";
	}
	/**
	 *	上传
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/auth/project/uploadForName")
	@ResponseBody
	public String uploadForName(@RequestParam MultipartFile[] files,HttpServletRequest request){
		String path = "";
		for(MultipartFile f:files){
			try{
				//path += FileUtils.saveFileFromInputStream(f.getInputStream(), request.getSession().getServletContext().getRealPath("/") , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += FileUtils.saveFileFromInputStream(f.getInputStream(),SignUtils.PIC_REALPAHT , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += "|"+f.getOriginalFilename();
				path += ",";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return path.length()>0?path.substring(0,path.length()-1):"-1";
	}
	/**
	 * 批量删除
	 * @param ids 跳转
	 */
	@RequestMapping(value="/auth/project/delete")
	@ResponseBody
	public String delete(String ids,HttpServletResponse response){
		projectService.deleteAll(ids);
		return "ok";
	}
	/**
	 * 批量删除
	 * @param ids 跳转
	 */
	@RequestMapping(value="/auth/project/verify")
	@ResponseBody
	public String verify(Long id,Integer online){
		projectService.verify(id, online);
		return "ok";
	}
	/**
	 * 前台列表页
	 * @return
	 */
	@RequestMapping(value="/project/list")
	@NeedInterceptor
	public ModelAndView list(Project project,Page page,String vsi){
		ModelAndView model = new ModelAndView("project/list");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		page.setRows(8);
		if(project.getType()==null){			
			project.setType(0);
		}
//		if(project!=null&&project.getType()!=null&&project.getType()==3){
//			page.setRows(6);
//		}
		if(project.getState()==null){
			project.setState(-101);
		}
		project.setFlag("publictime");
		page = projectService.findByPage(page, project);
		List<Map<String,Object>> maplist=TransSubmit.getmapobj(page.getResult());
		for(Map<String,Object> p : maplist){
			//查找信托公司或者担保公司
			String comname="";
			String comicon="";
			if(p.get("xtid")!=null&&!p.get("xtid").toString().equals("")){
			   Company c=this.companyService.get(Long.parseLong(p.get("xtid").toString()));	
			   if(c!=null){
				   comicon=c.getLogo();
				   comname=c.getCompany_name(); 
			   }
			  
			 }else if(p.get("dbid")!=null&&!p.get("dbid").toString().equals("")) {
				 Company c=this.companyService.get(Long.parseLong(p.get("dbid").toString()));	
				 if(c!=null){
					   comicon=c.getLogo();
					   comname=c.getCompany_name(); 
				   }
			}
			//
		    p.put("comname", comname);
		    p.put("comicon", comicon);
		    //
			int percent = new BigDecimal( p.get("_now_money").toString()).multiply(new BigDecimal(100)).divide(new BigDecimal( p.get("total_money").toString()),1,BigDecimal.ROUND_HALF_UP).intValue();
		     p.put("percent", percent);
		     try{
		    	 p.put("rate_type", sdf.parse(p.get("end_time").toString()).compareTo(date));
				}catch(Exception e){}
			//消除html标签
			if(p.get("content")!=null&&!p.get("content").toString().equals("")){
				p.put("content", (p.get("content").toString().replaceAll("<.*?>", "").replace("\t", "").replace("\r\n", "")));
			}
			int d=0;
			try {
				d = sdf.parse(p.get("start_time").toString()).compareTo(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.put("diffnow",+d);
		}
		page.setResult(maplist);
		model.addObject("vsi",vsi);
		model.addObject("project",project);
		Gson gson=new Gson();
		List<Map<String,Object>> newmaplist=new ArrayList<Map<String,Object>>();
		for(Object obj:page.getResult()){
			Map<String,Object>  map=(Map<String, Object>) obj;
			Map<String,Object> newmap=new HashMap<String, Object>();
			newmap.put("id", map.get("id"));
			newmap.put("percent", map.get("percent"));
			newmap.put("rate_type", map.get("rate_type"));
			newmap.put("state", map.get("state"));
			newmap.put("end_time", map.get("end_time"));
			newmap.put("diffnow", map.get("diffnow"));
			newmaplist.add(newmap);
		}	
		model.addObject("listjson",gson.toJson(newmaplist));
		try {
			//服务器时间
			model.addObject("nowDate",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 更新已还款期数
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/updateRepayCount")
	@ResponseBody
	public String updateRepayCount(Long id){
		//List<Tender>
		projectService.updateRepayCount(id);
		return null;
	}
	/**
	 * 查看仍能投资金额  （计算伪金额
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/checkMoneyAllow")
	@ResponseBody
	public Object checkMoneyAllow(Long id){
		//List<Tender>
		project = projectService.get(id);
		return project.getTotal_money().subtract(project.get_now_money());
	}
	/**
	 * 增加伪标
	 * @param id
	 * @param username
	 * @param money
	 * @return
	 */
	@RequestMapping(value="/auth/project/addTender")
	@ResponseBody
	public String addTender(Long id,String _username,String _money){
		//List<Tender>
		Tender t = null;
		String []username = _username.split(",");
		String []money = _money.split(",");
		List list = new ArrayList();
		Project project = projectService.get(id);
		for(int i=0;i<username.length;i++){
			t = new Tender();
			//伪标标示
			t.setFlag(2);
			t.setTransAmt(new BigDecimal(money[i]));
			t.setUsername(username[i]);
			t.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			t.setProject(project);
			list.add(t);
			project.setPay_number(project.getPay_number()+1);
			project.set_now_money(project.get_now_money().add(new BigDecimal(money[i])));
		}
		projectService.saveOrUpdate(project);
		tenderService.saveOrUpdateBatch(list);
		return null;
	}
	@RequestMapping(value="/auth/project/addRepaymentPlan")
	@ResponseBody
	public String addRepaymentPlan(Long id,Integer repayCount,String time1,String time2,BigDecimal rate){
		if(projectService.checkAddRepaymentPlan(id, time1, time2,repayCount)){
			try{
				rate = rate.divide(new BigDecimal(100));
			}catch(Exception e){
				e.printStackTrace();
				return "rateError";
			}
			//查询所有标
			RepaymentPlan plan = null;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			List<Tender> list = tenderService.findByPid(id);
			List<RepaymentPlan> _result = new ArrayList<RepaymentPlan>();
			//新增还款计划
			if(repayCount==0){
				//本金
				for(Tender t : list){
					plan = new RepaymentPlan();
					plan.setCreateTime(sdf1.format(date));
					plan.setUpdateTime(sdf1.format(date));
					plan.setFlag(0);
					plan.setProjectId(id);
					plan.setRepayCount(0);
					plan.setRepayMoney(t.getTransAmt());
					plan.setRepayTime(time2);
					plan.setState(-1);
					plan.setTenderId(t.getId());
					plan.setUsrCustId(t.getUsrCustId());
					plan.setOrdDate(t.getOrdDate());
					plan.setStart_time(time2);
					plan.setEnd_time(time2);
					_result.add(plan);
				}
			}else{
				//计算利息
				repayCount = repaymentPlanService.newRepayCount(id, time2);
				for(Tender t : list){
					plan = new RepaymentPlan();
					plan.setCreateTime(sdf1.format(date));
					plan.setUpdateTime(sdf1.format(date));
					plan.setFlag(0);
					plan.setProjectId(id);
					plan.setRepayCount(repayCount);
					plan.setRepayTime(time2);
					plan.setState(-1);
					plan.setTenderId(t.getId());
					plan.setUsrCustId(t.getUsrCustId());
					plan.setOrdDate(t.getOrdDate());
					plan.setStart_time(time1);
					plan.setEnd_time(time2);
					
					//利息
					try{
						plan.setRepayMoney(SignTools.getRate(sdf.parse(time1),sdf.parse(time2),rate,t.getTransAmt()));
					}catch(Exception e){
						continue;
					}
					_result.add(plan);
				}
			}
			repaymentPlanService.saveOrUpdateBatch(_result);
			return "success";
		}
		return "error";
	}
	/**
	 * 产品下架
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/takeOff")
	@ResponseBody
	public String takeOff(HttpSession session,Long id){
		Project project = projectService.get(id);
		Operator operator = (Operator)session.getAttribute("operator");
		if(project.getFlag().equals("0")&&project.getState()>=0){
			if(project.getState()==4){
				//已撤销
				project.setState(-99);
			}else if(project.getState()==0){
				//投标中
				//判断是否已经有人投标  如果有投标则无法下架  需要先进行撤销动作
				List tenderList = tenderService.findByPid(id,null);
				if(tenderList!=null&&tenderList.size()>0){
					operatorLogService.saveLog(operator, project, "14");
					return "error";
				}else
					project.setState(-99);
			}
		}else{
			project.setState(-99);
		}
		projectService.saveOrUpdate(project);
		operatorLogService.saveLog(operator, project, "14");
		return "success";
	}
	/**
	 * 产品退还本金
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/project/returnMoney")
	@ResponseBody
	public String returnMoney(HttpSession session,Long id){
		Project project = projectService.get(id);
		if(project.getState()==0||project.getState()==3){
			//查询所有还款计划  如果有删除  重新生成当天归还本金的还款计划
			//生成前删除所有有关还款计划
			int applyCount = repaymentPlanService.deleteByPId(project.getId());
			//生成前还款计划审核情况的删除
			repaymentPlanVerifyService.deletebyprojectid(project.getId());
			List<Tender> list = tenderService.findByPid(project.getId());//state=1 已成功放款
			List<RepaymentPlan> _result = new ArrayList<RepaymentPlan>();
			RepaymentPlan rp = null;
			LuckyMoney lucky = null;
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			for(Tender t : list ){
				rp = new RepaymentPlan();
				//rp.setUserId();
				rp.setUsrCustId(t.getUsrCustId());
				rp.setProjectId(project.getId());
				rp.setTenderId(t.getSrcTenderId());
				rp.setOrdDate(t.getOrdDate());
				rp.setRepayTime(sdf2.format(date));
				rp.setRepayCount(0);
				rp.setRepayMoney(t.getTransAmt());
				
				rp.setState(-1);
				rp.setFlag(0);
				rp.setCreateTime(sdf1.format(date));
				rp.setUpdateTime(sdf1.format(date));				
				rp.setStart_time(sdf2.format(date));
				rp.setEnd_time(sdf2.format(date));
				_result.add(rp);
				
				
			}
			
			if(_result.size()>0)
				repaymentPlanService.saveOrUpdateBatch(_result);
			project.setState(1);
			project.setPstartTime(sdf2.format(date));
			project.setPendTime(sdf2.format(date));
			project.setTotalRepay(project.getTotal_money());
			projectService.saveOrUpdate(project);
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, project, "13");
			return "ok";
		}
		return null;
	}
	@RequestMapping(value="/auth/project/tenderDetail")
	public String tenderDetail(Long id,ModelMap map,Page page){
		page = tenderService.tenderDetail(id, page);
		map.put("page", page);
		map.put("id", id);
		map.put("iscancel", SignUtils.ISCANCEL);
		return "project/tenderDetail";
	}
	
	@RequestMapping(value="/auth/project/tenderDetailPdf")
	public String tenderDetailforPDF(Long id,ModelMap map,Page page,HttpServletRequest request, HttpServletResponse response){
		PdfTools tools=new PdfTools();

        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;  
        
        
		page.setRows(999999);
		page = tenderService.tenderDetail(id, page);
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String ctxdir = rootPath+"WEB-INF"+File.separator+"tenderpdf";
		File dirfile = new File(ctxdir);if(!dirfile.exists())  dirfile.mkdirs();
		
		String ctxPath = ctxdir+File.separator+id+".pdf";
		try {

			tools.createPDF(ctxPath, page);

		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		


        try {   
            response.setContentType("text/html;charset=utf-8");   
            request.setCharacterEncoding("UTF-8");   
        	File file = new File(ctxPath);
            long fileLength = file.length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(("protender"+id+".pdf").getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(ctxPath));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
            if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
        } 
		return null;
	}
	/**
	 * 屌丝宝的开放日列表
	 * @return
	 */
	@RequestMapping(value="/auth/drbp/list")
	public String dynamicRateByProjectList(Long id,ModelMap map,Long tag){
		map.put("list", this.dynamicRateByProjectService.getListById(id));
		map.put("id", id);
		map.put("tag", tag);
		return "drbp/list";
	}
	@RequestMapping(value="/auth/drbp/toAdd")
	public String toAddDynamicRate(Long id,Long pid,ModelMap map){
		if(id!=null&&id!=0){
			map.put("model",this.dynamicRateByProjectService.get(id) );
		}else{
			DynamicRateByProject drbp = new DynamicRateByProject();
			drbp.setPid(pid);
			map.put("model", drbp);
		}
		return "drbp/add";
	}
	@RequestMapping(value="/auth/drbp/doAdd")
	public String  doAddDynamicRate(DynamicRateByProject model,ModelMap map){
		map.put("id", model.getPid());
		try{
			this.bizdataservice.bizSave(DynamicRateByProject.class, model);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/auth/drbp/list";
	}
	
	/********************************************************************************************************/
	
	
	//撤销产品
	@RequestMapping(value="/auth/project/cancelProduct")
	public String  cancelProduct(HttpSession session,Long projectid,ModelMap map){
		try {
			  //查找产品
			Project  project = (Project) this.bizdataservice.bizfindbyid(Project.class, projectid);
		    if(project==null){
		    	map.put("error", "产品已不存在");
		    	return "project/cancelProduct";
		    }		     
			
		    this.projectService.cancelProduct(projectid);
			//总解冻标数
			int totalTender=0;
			int waitTender=0;
			int errorTender=0;
			int successTender=0;
			
	    //总标数量
	    totalTender=Integer.parseInt(this.bizdataservice.getRowCount("select count(id) from Tender as tender where project.id=? and flag=0", projectid).toString());
	    waitTender=Integer.parseInt(this.bizdataservice.getRowCount("select count(id) from Tender as tender where project.id=? and flag=0 and unFreezeState=?", projectid,0).toString());
	    errorTender=Integer.parseInt(this.bizdataservice.getRowCount("select count(id) from Tender as tender where project.id=? and flag=0 and unFreezeState=?", projectid,1).toString());
	    successTender=Integer.parseInt(this.bizdataservice.getRowCount("select count(id) from Tender as tender where project.id=? and flag=0 and unFreezeState=?", projectid,2).toString());
		map.put("totalTender", totalTender);
		map.put("waitTender", waitTender);
		map.put("errorTender", errorTender);
		map.put("successTender", successTender);
		map.put("projectid", projectid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Operator operator = (Operator)session.getAttribute("operator");
		operatorLogService.saveLog(operator, project, "12");
		return "project/cancelProduct";
	}
	@RequestMapping(value="/auth/project/updatePro")
	@ResponseBody
  public  boolean updatePro(Project project){
		try {
			this.bizdataservice.bizSave(Project.class, project);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	return true;
  }
	
	//查看投资项目的所有标记录
	@RequestMapping(value="/project/projectTenderlist")
	public String projectTenderlist(Long projectid,HttpSession session,ModelMap map){
		User user=(User) session.getAttribute("user");
		//查询当前用户 投资该项目的标
	   String hql="from Tender where project.id=? and usrCustId=?";
	   List<Object> tenderlist= this.bizdataservice.find(hql,projectid,user.getHuifuID());
	   map.put("list", tenderlist);
	   List<Map<String,Object>> tenderlistmap=TransSubmit.getmapobj(tenderlist);
	   //第一个 标
	   Tender tender=(Tender) tenderlist.get(0);
	   
	   String replayplanhql="select  repayTime from RepaymentPlan where tenderId=? and state=1 and flag=0 order by repayTime desc ";
	 List<Object>   repayTimes= this.tenderService.getTopRows(replayplanhql,1,  tender.getId());
	String repayTime="";	
	 // 如果从来没派息过
		if(repayTimes==null||repayTimes.size()==0){
			repayTime=tender.getProject().getStart_time();
		}else{
			repayTime=(String) repayTimes.get(0);
		}
		//上个派息日到今天的日差 用于计算债权价值
		long lastRapayDaysFromNow=0;
		try {
			lastRapayDaysFromNow = SignTools.getdiffDays(StringUtil.StringToDate(repayTime, "yyyy-MM-dd"),new Date() );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	map.put("rapaydiffDays", lastRapayDaysFromNow);
	 	 //计算剩余天数
		int diffDays=0;
		try {
			diffDays = SignTools.dateDiffMonth(new Date(),StringUtil.StringToDate(tender.getProject().getEnd_time(), "yyyy-MM-dd") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("diffDays", diffDays);
	 	//项目的日率
	     BigDecimal dayRate = tender.getProject().getDay_rate();
		for( Map<String,Object> tendermap:tenderlistmap){
			//计算债权价值 通过日利率来计算 
		    BigDecimal defealtPrice=new BigDecimal(tendermap.get("transAmt").toString()).multiply(dayRate).multiply(new BigDecimal(lastRapayDaysFromNow)).add(new BigDecimal(tendermap.get("transAmt").toString()));
		    tendermap.put("price", StringUtil.BigDecimal2String(defealtPrice));
       }
		map.put("listmap", tenderlistmap);
	   return "userCenter/tenderlist";
	}
	//获取屌丝宝每日收益详情
	/**
	 * rateDate:计息日
	 * page：当前页
	 */ 
	@RequestMapping(value="/project/getdslucre")
	@ResponseBody
	public Page getdslucre(String rateDate,Page page,String transAmt,Long pid,HttpSession session){
		init();
		User user=(User) session.getAttribute("user");
		try {
			if(page==null){
		 		page=new Page<Object>();		 	
		 	}
			page.setRows(5);
			List<Map<String,String>> listmap=new ArrayList<Map<String,String>>();
			String stopDate="";
			String pendDate="";
			String hql="select pendTime from Project where id=?";
		    List<Object> pendTimes=this.bizdataservice.find(hql,pid);
		    if(pendTimes!=null&&pendTimes.get(0)!=null){
					pendDate = pendTimes.get(0).toString();
					Date pendtime=StringUtil.StringToDate(pendDate, "yyyy-MM-dd");
					//查询最后一次未执行还款计划
					stopDate = repaymentPlanService.findLastRepayTime(user.getHuifuID(), pid);
					//获取 昨天的日期
				 	Date  yesterday= StringUtil.getIntervalDate(new Date(), -1);
				 	if(stopDate!=null&&!stopDate.equals("")){//未还完
				 		Date lastrepaytime=StringUtil.StringToDate(stopDate, "yyyy-MM-dd");		 		
				 		if(lastrepaytime.before(pendtime)){//证明是提前赎回 前推三天	 			
				 			Date tmpday=StringUtil.getIntervalDate(lastrepaytime, SignTools.TN_TIME*-1);
				 			if(tmpday.before(yesterday)){
				 				yesterday=tmpday;
				 			}
				 		}else{//未提前还款
				 			if(yesterday.before(lastrepaytime)){//还未到期
				 				
				 			}else{//到期未还
				 				//产品到期未还
								Map map=new HashMap<String, String>();
							   	map.put("date", "-");
					    	 	map.put("lucre", "-");
								listmap.add(map);
								page.setResult(listmap);
								return page;
					 		}
				 			
				 		
				 		}
				 	}else{//还完了
				 		 String	stopedDate = repaymentPlanService.findLastedRepayTime(user.getHuifuID(), pid);
				 		Date lastrepayedtime=StringUtil.StringToDate(stopedDate, "yyyy-MM-dd");	
				 		 if(lastrepayedtime.before(pendtime)){//提前还款还完
				 			  yesterday=StringUtil.getIntervalDate(lastrepayedtime, SignTools.TN_TIME*-1);
				 		}else{//到期还完				 
							Map map=new HashMap<String, String>();
						   	map.put("date", "-");
				    	 	map.put("lucre", "-");
							listmap.add(map);
							page.setResult(listmap);
							return page;
				 		}
		
				 	}
				 	
				 		
					if(transAmt==null||transAmt.equals("")||transAmt.equals("0")){
						Map map=new HashMap<String, String>();
					   	map.put("date", "-");
			    	 	map.put("lucre", "-");
						listmap.add(map);
						page.setResult(listmap);
						return page;
					}
			
				 	//计算投资计息日距离昨天多少天
				 	if(page.getTotalRecord()==0 ){
				 		int diffDays = SignTools.dateDiffMonth(yesterday,StringUtil.StringToDate(rateDate, "yyyy-MM-dd") );
					 	page.setTotalRecord(diffDays+1);	
				 	}
					page.processPage(page.getCurrPage(), page.getRows(), page
							.getTotalRecord());
				
					//计算从开始计息日起到当前页的分页数的每日利率
					Map<String,BigDecimal> everyrate=getrateEveryDay(StringUtil.StringToDate(rateDate, "yyyy-MM-dd"),page.getTotalRecord()-page.getRows()*(page.getCurrPage()-1));
				
				    //循环每一天
					//算出开始天的日期  9,17
				    Date   currentPageStartDate= StringUtil.getIntervalDate(yesterday, -page.getStartRecord());
				    BigDecimal  nexttotallucre=new BigDecimal(0);
				 
				    for(int i=0;i<=page.getRows()-1;i++ ){
				    	  //如果循环到最后一条记录 停止循环
				          if(page.getStartRecord()+i+1>page.getTotalRecord()){
					        break;
				           }
				           //当前记录的日期
				            Date curedate= StringUtil.getIntervalDate(currentPageStartDate, -i);
				            //计算截止当期日期的总收益
				            if(nexttotallucre.toString().equals("0")){
				            	
				            	nexttotallucre=getTotallucrecurentDay(StringUtil.StringToDate(rateDate, "yyyy-MM-dd"), curedate, new BigDecimal(transAmt), everyrate);
				            	nexttotallucre=nexttotallucre.setScale(2, BigDecimal.ROUND_DOWN); 
				            }
				            
				            //计算上一天的总收益
				            Date lastdate= StringUtil.getIntervalDate(curedate, -1);
				        	
				            BigDecimal lasttotalluncre =getTotallucrecurentDay(StringUtil.StringToDate(rateDate, "yyyy-MM-dd"), lastdate, new BigDecimal(transAmt), everyrate);
				            lasttotalluncre=lasttotalluncre.setScale(2, BigDecimal.ROUND_DOWN); 
				            //当天利率等于截止当天的总收益减去截止上一天的总收益		
				         
			    	    	  String    daylucre=StringUtil.BigDecimal2StringSmall(nexttotallucre.subtract(lasttotalluncre));
			    	         	Map map=new HashMap<String, String>();
			    	         	map.put("date", StringUtil.DateToString(curedate, "yyyy-MM-dd"));
			    	    	 	map.put("lucre", daylucre);
			    	    	 	listmap.add(map);
			    	    	  nexttotallucre=lasttotalluncre;
				    	}
				    page.setResult(listmap);
		    }else{				
			
					Map map=new HashMap<String, String>();
				   	map.put("date", "-");
		    	 	map.put("lucre", "-");
					listmap.add(map);
					page.setResult(listmap);
			
			
		    }
		    return page;
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;	
	}
	/**
	 * 计算截止昨天总收益
	 * @param rateDate
	 * @param transAmt
	 * @param pid
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/project/gettototallunre")
	@ResponseBody
	public Map<String, String> gettototallunre(String rateDate,String transAmt,Long pid,HttpSession session){
		init();
		Map<String, String> map=new HashMap<String, String>();
		User user=(User) session.getAttribute("user");
		try {
			String stopDate="";
			String pendDate="";
			//获取 昨天的日期
		 	Date  yesterday= StringUtil.getIntervalDate(new Date(), -1);	
			boolean isover=false;
			String hql="select pendTime from Project where id=?";
		    List<Object> pendTimes=this.bizdataservice.find(hql,pid);
		    if(pendTimes!=null&&pendTimes.get(0)!=null){
				pendDate = pendTimes.get(0).toString();
				//查询最后一次还款计划
				stopDate = repaymentPlanService.findLastRepayTime(user.getHuifuID(), pid);
						
		 		Date pendtime=StringUtil.StringToDate(pendDate, "yyyy-MM-dd");
			 	if(stopDate!=null&&!stopDate.equals("")){//还有未还计划
			 		Date lastrepaytime=StringUtil.StringToDate(stopDate, "yyyy-MM-dd");
			 		if(lastrepaytime.before(pendtime)){//证明是提前赎回 前推三天	 			
			 			Date tmpday=StringUtil.getIntervalDate(lastrepaytime, SignTools.TN_TIME*-1);
			 			if(tmpday.before(yesterday)){
			 				yesterday=tmpday;
			 			}
			 		}else{//产品原计划
			 			if(yesterday.before(lastrepaytime)){//还未到期
			 				
			 			}else{//到期未还
			 				StringBuilder hql0 = new StringBuilder();
				 			hql0.append("select sum(repayMoney) from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = -1 and repayCount=0");
				 			List<Object> param0 = new ArrayList<Object>();
				 			param0.add(pid);
				 			param0.add(user.getHuifuID());
				 			List listbenjin = this.bizdataservice.find(hql0.toString(),param0.toArray());
				 			BigDecimal benjin =new BigDecimal(0);
				 			if(listbenjin!=null&&listbenjin.size()>0&&listbenjin.get(0)!=null){
				 			 benjin =new BigDecimal(listbenjin.get(0).toString());
				 			}		
				 			project=projectService.get(pid);
							int diffDays = SignTools.dateDiffMonth(StringUtil.StringToDate(project.getPstartTime(), "yyyy-MM-dd"),StringUtil.StringToDate(project.getPendTime(), "yyyy-MM-dd") );
							benjin.add(benjin.multiply(project.getDay_rate()).multiply( new BigDecimal(diffDays)));
				 			map.put("totallunre", benjin+"");
				 			isover=true;
				 		}
			 			
			 		}
			 	}else{//都还完了
			 		StringBuilder hql0 = new StringBuilder();
		 			hql0.append("select sum(repayMoney) from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = 1 ");
		 			List<Object> param0 = new ArrayList<Object>();
		 			param0.add(pid);
		 			param0.add(user.getHuifuID());
		 			List list0 = this.bizdataservice.find(hql0.toString(),param0.toArray());
		 			
			 		map.put("totallunre", "0(已还"+list0.get(0).toString()+")");
					isover=true;
				}
			 	
				if(transAmt==null||transAmt.equals("")||transAmt.equals("0")){
					map.put("totallunre", "0");
				}
				int diffDays = SignTools.dateDiffMonth(yesterday,StringUtil.StringToDate(rateDate, "yyyy-MM-dd") )+1;
				//计算从开始计息日起到当前页的分页数的每日利率
				Map<String,BigDecimal> everyrate=getrateEveryDay(StringUtil.StringToDate(rateDate, "yyyy-MM-dd"),diffDays);
				
				 BigDecimal totalluncre =getTotallucrecurentDay(StringUtil.StringToDate(rateDate, "yyyy-MM-dd"), yesterday, new BigDecimal(transAmt), everyrate);
				 
			 if(!isover)map.put("totallunre",  StringUtil.BigDecimal2StringSmall(totalluncre));
		    }
			 //计算7天总收益率
			 Date last7day=StringUtil.getIntervalDate(yesterday, -6);
			 //前7天的每日利率
			 Map<String,BigDecimal> lastSenveneveryrate= getrateEveryDay(last7day, 6);
			 BigDecimal totalrate =getTotalratecurentDay(last7day, yesterday, lastSenveneveryrate);
			 BigDecimal 	sdayyearrate = StringUtil.div(totalrate,new BigDecimal(7)).multiply(new BigDecimal(36500));
	
			 map.put("sdayyearrate", StringUtil.BigDecimal2String(sdayyearrate));
			 //计算万分收益
			 Map<String,BigDecimal> everyrate0 =getrateEveryDay(yesterday,0);
			 BigDecimal tenthondslunre=  getTotallucrecurentDay(yesterday, yesterday, new BigDecimal(10000), everyrate0);
			 map.put("tenthondslunre", StringUtil.BigDecimal2String(tenthondslunre));
			 
		}catch (Exception e) {
	      e.printStackTrace();
		}
		return map;
	}
	//获取一个时间段每日利息map表
	public Map<String,BigDecimal> getrateEveryDay(Date startDay,int diffdate){
		try {
			Map<String, BigDecimal> map=new HashMap<String, BigDecimal>();
			for(int i=0;i<=diffdate;i++ ){
		    Date curedate= StringUtil.getIntervalDate(startDay, i);
		 	for(Object obj:dynamicRateList){
	    		//由于查出来的集合是按照开始时间顺序排的 所以找到最近的一个日期 就可返回
	    		DynamicRate dynamic=(DynamicRate) obj;
	    	    String startTime=dynamic.getStartTime();
	    	    Date startDate=StringUtil.StringToDate(startTime, "yyyy-MM-dd");
	    	    if(curedate.after(startDate)||curedate.equals(startDate)){
	    	    	BigDecimal dayrate= dynamic.getDay_rate(); 
	    	         	map.put(StringUtil.DateToString(curedate, "yyyy-MM-dd"), dayrate);
	    	    		break;
	    	    	}
	    	    }
			}
			   return map;
		} catch (Exception e) {
		  e.printStackTrace();
		  return null;
		}	
	
	}
	//计算截止当前日期的总收益
/**
 * ratedate 计息日
 * curedate 当期日期
 * lastTotallucre 前一天的总收益
 * transAmt 总投资金额
 * everyrate：每日利率
 */
	public BigDecimal getTotallucrecurentDay(Date ratedate,Date curedate,  BigDecimal transAmt, Map<String,BigDecimal> everyrate){
		try {
	        BigDecimal totalRate=new BigDecimal(0);	      
	        Date startDate= ratedate;
	        while(startDate.before(curedate)||startDate.equals(curedate)){
	        	String startdateString=StringUtil.DateToString(startDate, "yyyy-MM-dd");
		        BigDecimal curentdaterate=everyrate.get(startdateString);	
		        totalRate=totalRate.add(curentdaterate);
		        startDate= StringUtil.getIntervalDate(startDate, 1);
	        }	
	       return totalRate.multiply(transAmt);
		} catch (Exception e) {
		e.printStackTrace();
		return new BigDecimal(0);
		}
	}
	//计算截止当前日期的总收益率
	/**
	 * ratedate 计息日
	 * curedate 当期日期
	 * lastTotallucre 前一天的总收益
	 * transAmt 总投资金额
	 * everyrate：每日利率
	 */
		public BigDecimal getTotalratecurentDay(Date ratedate,Date curedate, Map<String,BigDecimal> everyrate){
			try {
		        BigDecimal totalRate=new BigDecimal(0);	      
		        Date startDate= ratedate;
		        while(startDate.before(curedate)||startDate.equals(curedate)){
		        	String startdateString=StringUtil.DateToString(startDate, "yyyy-MM-dd");
			        BigDecimal curentdaterate=everyrate.get(startdateString);	
			        totalRate=totalRate.add(curentdaterate);
			        startDate= StringUtil.getIntervalDate(startDate, 1);
		        }	
		       return totalRate;
			} catch (Exception e) {
			e.printStackTrace();
			return new BigDecimal(0);
			}
		}
	
	//初始化动态利率 倒叙排列 
	public void init(){
	 	dynamicRateList=this.bizdataservice.find("from DynamicRate where flag = 0 order by startTime desc");
	}
	
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public List<Company> getComList() {
		return comList;
	}
	public void setComList(List<Company> comList) {
		this.comList = comList;
	}
	public List<Company> getComList2() {
		return comList2;
	}
	public void setComList2(List<Company> comList2) {
		this.comList2 = comList2;
	}
	public List getPercentList() {
		return percentList;
	}
	public void setPercentList(List percentList) {
		this.percentList = percentList;
	}
	public List<CompanyAttachment> getCaList() {
		return caList;
	}
	public void setCaList(List<CompanyAttachment> caList) {
		this.caList = caList;
	}
	public List<Project> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}
}
