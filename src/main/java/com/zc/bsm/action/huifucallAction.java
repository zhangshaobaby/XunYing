package com.zc.bsm.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.zc.abf.pojo.Operator;
import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedOpenHFPayInterceptor;
import com.zc.base.po.Dict;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.BorrowerDetailPo;
import com.zc.bsm.pojo.CreditAssignTender;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.EnchashmentApply;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RechargeOrder;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.CreditAssignService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.OperatorLogService;
import com.zc.bsm.service.RechargeOrderService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.AutoTenderPlan;
import com.zc.bsm.vo.BorrowerDetail;
import com.zc.bsm.vo.Cash;
import com.zc.bsm.vo.CreditAssign;
import com.zc.bsm.vo.InitiativeTender;
import com.zc.bsm.vo.MerCash;
import com.zc.bsm.vo.UserRegister;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.MerCashReturn;

/**
 * 汇付的接口
 * 
 * @author Administrator
 * 
 */
@Controller
public class huifucallAction extends Action {
	protected static final Log logger = LogFactory.getLog(huifucallAction.class);
	@Autowired
	public UserService userService;
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	public UserAction useraction;
	@Autowired
	public CreditAssignService creditAssignService;
	@Autowired
	private TenderService tenderService;
	@Autowired
	private EnchashmentService enchashmentService;
	@Autowired
	private RechargeOrderService rechargeOrderService;
	@Autowired
	private OperatorLogService operatorLogService;
	// 充值
	@RequestMapping(value = "/huifu/recharge")
	public void recharge(HttpSession session,ModelMap map, String huifuID,
			RechargeOrder rechargeOrder, String userid, String gateBusiId,HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			if (userid != null && !userid.equals("")) {
				User user = new User();
				user.setId(userid);
				rechargeOrder.setUser(user);
			}
//			rechargeOrder = (RechargeOrder) bizdataservice.bizSave(
//					RechargeOrder.class, rechargeOrder);
//			NetSave netsave = new NetSave();
//			netsave.setBgRetUrl(SignUtils.PUBLIC_HOST
//					+ "huifucallback/printRechargeResult");
//			netsave.setRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/success");
//			netsave.setUsrCustId(huifuID);
//			netsave.setOrdId(rechargeOrder.getId().toString());
//			netsave.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(
//					rechargeOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
//					"yyyyMMdd"));
//			netsave.setGateBusiId(gateBusiId);
//			// 查找银行数据字典
//			Dict bankdict = (Dict) this.bizdataservice.bizfindbyid(Dict.class,
//					rechargeOrder.getBankId().getId());
//			netsave.setOpenBankId(bankdict.getDictid());
//			netsave.setDcFlag("D");
//			netsave.setTransAmt(StringUtil.BigDecimal2String(rechargeOrder.getAmount()));
//			String url = netsave.getParam();
			String url=this.rechargeOrderService.recharge(rechargeOrder, huifuID, gateBusiId);
			map.put("forwardurl", url);
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, huifuID, "8");
			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("生成充值订单出错,请重试");
			try {
				response.getWriter().print("生成充值订单出错,请重试");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// 打开汇付注册页面
	@RequestMapping(value = "/huifu/regist")
	@NeedOpenHFPayInterceptor
	public void regist(ModelMap map, String username,HttpServletResponse response) {
		UserRegister hufuuse = new UserRegister();
		hufuuse.setBgRetUrl(SignUtils.PUBLIC_HOST
				+ "huifucallback/printRegistResult");
		hufuuse.setRetUrl(SignUtils.PUBLIC_HOST + "user/hfregisterfinish");
		hufuuse.setUsrId(username);
		String url = hufuuse.getParam();
		map.put("forwardurl", url);
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 主动投标
	@RequestMapping(value = "/huifu/initiativetender")
	public void initiativetender(ModelMap map,
			int  timeLimit,Integer transAmt, String projectId,HttpSession session,HttpServletResponse response,String luckyId) {
		User user=(User) session.getAttribute("user");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
//		Project project;
//		Borrower borrower=null;
		String[] arr=this.userService.initiativetender(luckyId, projectId, user, transAmt);
		if(!arr[1].equals(""))
		{
			try {
				response.sendRedirect(arr[1]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			response.getWriter().print(arr[0]);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*try {
			Long luckyid=null;
			if(luckyId!=null&&!luckyId.equals("")){
				luckyid=Long.parseLong(luckyId.split(",")[0]);
			}
			 
			boolean tag = true;
			String mes = "";
			//前期验证
			project = (Project)this.bizdataservice.bizfindbyid(Project.class, Long.parseLong(projectId));
			if(user==null){
				tag = false;
				mes = "请先登陆后再发起投标。";
			}
			if(project!=null){				
				borrower=this.borrowerService.get(project.getBorrowId());
				if(project.getLowest_money().compareTo(transAmt)==1){
					//增加判断  判断产品余额  个人投资总额 如果余额小于单笔投资下限
					if(project.get_now_money().add(new BigDecimal(transAmt)).compareTo(project.getTotal_money())!=0){
						tag = false;
						mes = "您的投资金额小于最小投资金额。";
					}
				}
				if(project.getHighest_money().compareTo(transAmt)==-1){
					tag = false;
					mes = "您的投资金额小于最小投资金额。";
				}
				if(project.getTotal_money().compareTo(project.get_now_money().add(new BigDecimal(transAmt)))==-1){
					tag = false;
					mes = "您的投资金额超出资金缺口。";
				}
				if(project.getTotal_money().compareTo(project.get_now_money())==0){
					tag = false;
					mes = "产品资金已募集完成~您来晚啦~";
				}
			}else{
				tag = false;
				mes = "产品已取消。";
			}
			//error
			if(project.getType().intValue()==3){
				BigDecimal release = null;
				//剩余可投资金额   如果是屌丝宝  同时计算个人最大可投资金额  和项目资金缺口
				if(user!=null&&project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1){
					List releaseList = tenderService.findSumTransmat(Long.parseLong(projectId),user.getHuifuID());
					if(releaseList!=null&&releaseList.get(0)!=null&&!releaseList.get(0).toString().equals("")){
						release = new BigDecimal(releaseList.get(0).toString());
						release = project.getSelf_highest_money().subtract(release);
					}else{
						release = project.getSelf_highest_money();
					}
				}
				if(release != null && release.compareTo(new BigDecimal(transAmt))==-1){
					tag = false;
					mes = "此产品下您的投资总额已经超出最大限额。";
				}
			}
			//红包验证
			LuckyMoney luckyMoney = null;
//			if(luckyId!=null&&luckyId!=0){
//				luckyMoney = (LuckyMoney)this.bizdataservice.bizfindbyid(LuckyMoney.class, luckyId);
//				if(luckyMoney!=null){
//					//判断是否非法
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = new Date();
//					//状态已经使用或使用中，起始时间结束时间不符合，投资额度小于红包限制单笔投资金额,使用产品类别不适
//					if(luckyMoney.getState()!=0||sdf.parse(luckyMoney.getStart_time()).compareTo(date)>0||sdf.parse(luckyMoney.getEnd_time()).compareTo(date)<0||luckyMoney.getInvestLimit().compareTo(new BigDecimal(transAmt))>0||!luckyMoney.getType().contains(project.getType().toString())){
//						tag = false;
//						mes = "您选择的红包不符合使用条件";
//					}
//				}
//			}
			if(luckyid!=null&&luckyid!=0){
				luckyMoney = (LuckyMoney)this.bizdataservice.bizfindbyid(LuckyMoney.class, luckyid);
				if(luckyMoney!=null){
					//判断是否非法
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					//状态已经使用或使用中，起始时间结束时间不符合，投资额度小于红包限制单笔投资金额,使用产品类别不适
					
					if(luckyMoney.getState()!=0||sdf.parse(luckyMoney.getStart_time()).compareTo(date)>0||sdf.parse(luckyMoney.getEnd_time()).compareTo(date)<0||!luckyMoney.getType().contains(project.getType().toString())){
						tag = false;
						mes = "您选择的红包不符合使用条件";
					}
					//非正常红包通用
					if(luckyMoney.getType().indexOf("-1")==-1&&luckyMoney.getMoney().compareTo((new BigDecimal(transAmt+"")).multiply(project.getHongbaorate()).multiply(new BigDecimal(0.01)))>0){
						tag = false;
						mes = "您选择的红包不符合使用条件";
					}
					
				}
			}
			if(!tag){
				try {
					response.getWriter().print(mes);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return ;
			}
			String tmpDetail = "";
			List<BorrowerDetailPo> bdpolist = new ArrayList<BorrowerDetailPo>();
			List<BorrowerDetail> bdlist=new ArrayList<BorrowerDetail>();
			if (borrower != null) {
				String transAmtString=transAmt.toString();
				if(transAmtString.indexOf(".")==-1){
					transAmtString=transAmtString+".00";
				}
					
					Double dou = Double.parseDouble(project.getRate().toString());
					Double monrate = dou / 12;
					Double totalrate = monrate * timeLimit;
					BigDecimal d=new BigDecimal(totalrate/100*SignUtils.TENDER_FLOATI_RATE);
					BorrowerDetail bd=new BorrowerDetail();
					bd.setBorrowerAmt(transAmtString);
					bd.setBorrowerRate(StringUtil.BigDecimal2StringBig(d));
					bd.setBorrowerCustId(borrower.getUsrCustId().toString());
					bdlist.add(bd);
					// 实例化 数据库借款人详情
					BorrowerDetailPo dbpo = new BorrowerDetailPo();
					dbpo.setBorrowerCustId(project.getBorrowId().toString());
					BigDecimal bde = new BigDecimal(transAmtString);
					dbpo.setBorrowerAmt(bde);
					dbpo.setBorrowerRate(StringUtil.BigDecimal2StringBig(d));
					dbpo = (BorrowerDetailPo) this.bizdataservice.bizSave(
							BorrowerDetailPo.class, dbpo);
					bdpolist.add(dbpo);
				}
				Gson gson = new Gson();
				tmpDetail = gson.toJson(bdlist);
			
			// 生成标订单
			Tender ten = new Tender();
			ten.setType(0);
			ten.setBorrowerDetails(bdpolist);
			BigDecimal bde = new BigDecimal(transAmt);
			ten.setTransAmt(bde);
			Project pro = new Project();
			pro.setId(Long.parseLong(projectId));
			ten.setProject(pro);
			ten.setMaxTenderRate("0.01");
			ten.setUsrCustId(user.getHuifuID());
			ten.setIsFreeze("Y");
			ten.setStart_time(pro.getStart_time());
			ten.setUsername(user.getUsername());
			ten.setEnd_time(pro.getEnd_time());
			ten.setRepayAmt(new BigDecimal(0));
			if(luckyMoney!=null){
				ten.setLuckyId(luckyid);
			}
			ten=(Tender) this.bizdataservice.bizSave(Tender.class, ten);
			//回填srcTenderid
			ten.setSrcTenderId(ten.getId());
			//重新保存
			ten=(Tender) this.bizdataservice.bizSave(Tender.class, ten);
//			if(luckyMoney!=null){
//				luckyMoney.setState(1);
//				luckyMoney.setTenderId(ten.getId());
//				luckyMoney.setProjectId(ten.getProject().getId());
//				this.bizdataservice.saveOrUpdate(luckyMoney);
//			}
			// 设置汇付参数
			InitiativeTender tender = new InitiativeTender();
			tender.setBgRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/printInitiativeTenderResult");
			tender.setRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/printInitiativeTenderResult_");
			tender.setBorrowerDetails(tmpDetail);
			tender.setTransAmt(StringUtil.BigDecimal2String(ten.getTransAmt()));
			tender.setUsrCustId(user.getHuifuID());
			tender.setMaxTenderRate("0.01");
			tender.setIsFreeze("Y");
			/////////////////////////////////////////////
			//插入一条冻结记录到Freeze
			Freeze freeze = new Freeze();
			freeze.setUsrCustId(user.getHuifuID());
			freeze.setSubAcctId(ten.getId().toString());
			freeze.setType(new Dict(40));
			freeze.setTransAmt(ten.getTransAmt());
			this.bizdataservice.save(freeze);
			//主键作为freezeOrdId
			tender.setFreezeOrdId(freeze.getId().toString());
			/////////////////////////////////////////////
			tender.setOrdId(ten.getId().toString());
			tender.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(
					ten.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
			"yyyyMMdd"));
			//
			if(luckyMoney!=null){
				String json = "{\"Vocher\":{\"VocherAmt\":\""+luckyMoney.getMoney()+"\",\"AcctId\":\""+SignUtils.MER_CUST_ACCT_ID+"\"}}";
				tender.setReqExt(json);
			}
//			//更新project nowmoney
//			project.setNow_money(project.getNow_money().add(ten.getTransAmt()));
//			project.setPay_number(project.getPay_number()==null?1:project.getPay_number()+1);
//			bizdataservice.saveOrUpdate(project);
			String url = tender.getParam();
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("生成投标订单出错,请重试");
			try {
				response.getWriter().print("生成投标订单出错,请重试");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/

	}
	//取现
	@RequestMapping(value = "/huifu/cash")
	public void cash(String transAmt,String bankcardid,HttpServletResponse response,HttpSession  session,String paypwd,String servFee,String code){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
		/*	//检查支付密码
		   String result=useraction.checkpaypwd(paypwd, session);
		   //支付密码错误
		   if(result.equals(1)){
			   response.getWriter().print("支付密码错误,请返回重新输入");
			   return;
		   }*/
			
			//检查验证码
		   String cashPhoneCode=	(String) session.getAttribute("cashPhoneCode");
			if(!cashPhoneCode.equals(code)){
				response.getWriter().print("验证码错误,请返回重新输入");
				return;
			}
			User user=(User) session.getAttribute("user");
			String usrCustId=user.getHuifuID();
//			//生成取现订单
//			Enchashment ment=new Enchashment();
//			ment.setCreateTime(StringUtil.DateToString(
//						new Date(), "yyyy-MM-dd HH:mm:ss"));
//			ment.setTransAmt(new BigDecimal(transAmt));
//			ment.setUsrCustId(usrCustId);
//			ment.setOpenAcctId(bankcardid);
//			ment=(Enchashment) this.bizdataservice.bizSave(Enchashment.class, ment);
//			Cash hfcash=new Cash();
//			hfcash.setBgRetUrl(SignUtils.PUBLIC_HOST
//					+ "huifucallback/printcashResult");
//			hfcash.setTransAmt(StringUtil.BigDecimal2String(ment.getTransAmt()));
//			hfcash.setOrdId(ment.getId().toString());
//			hfcash.setUsrCustId(usrCustId);
//			hfcash.setOpenAcctId(bankcardid);
//			hfcash.setServFee(servFee);
//			hfcash.setServFeeAcctId(SignUtils.MER_CUST_ACCT_ID);
//			String url = hfcash.getParam();
			String url=this.enchashmentService.cash(transAmt, usrCustId, bankcardid, servFee);
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("生成取现订单出错,请重试");
			try {
				response.getWriter().print("生成取现订单出错,请重试");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//代取现
	@RequestMapping(value = "auth/huifu/mercash")
	@ResponseBody
	public boolean mercash(EnchashmentApply ea,HttpServletResponse response,HttpSession  session){
		try {
			 if(ea.getId()==null||ea.getId().equals("")){
				 response.getWriter().print("参数错误");
				 return false;
		      }
			 ea=(EnchashmentApply) this.bizdataservice.bizfindbyid(EnchashmentApply.class,ea.getId());
			//生成取现订单
			Enchashment ment=new Enchashment();
			ment.setCreateTime(StringUtil.DateToString(
						new Date(), "yyyy-MM-dd HH:mm:ss"));
			ment.setTransAmt(ea.getTransAmt());
			ment.setUsrCustId(ea.getUsrCustId());
			ment.setEnchashmentApply(ea);
			ment.setOpenAcctId(ea.getOpenAcctId());
			ment=(Enchashment) this.bizdataservice.bizSave(Enchashment.class, ment);
			MerCash hfcash=new MerCash();
			hfcash.setBgRetUrl(SignUtils.PUBLIC_HOST
					+ "huifucallback/printMercashResult");
			hfcash.setTransAmt(StringUtil.BigDecimal2String(ment.getTransAmt()));
			hfcash.setOrdId(ment.getId().toString());
			hfcash.setUsrCustId(ea.getUsrCustId());
			hfcash.setMerCustId(SignUtils.MER_CUST_ID);
			//设置取现申请状态为取现中
			ea.setState(new Dict(42));
			this.bizdataservice.bizSave(EnchashmentApply.class, ea);
			TransSubmit st=new TransSubmit();
			MerCashReturn mcr=st.getMerCash(hfcash);
			//处理返回结果
            boolean result=enchashmentService.printMercashResult(mcr);
		    return result;
		} catch (Exception e) {
			e.printStackTrace();
     		return false;
		}
	}
	//企业取现
	@RequestMapping(value = "/auth/huifu/enterprisecash")
	public void enterprisecash(EnchashmentApply ea,HttpServletResponse response,HttpSession  session){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			 if(ea.getId()==null||ea.getId().equals("")){
				 response.getWriter().print("参数错误");
				 return;
		      }
			 ea=(EnchashmentApply) this.bizdataservice.bizfindbyid(EnchashmentApply.class,ea.getId());
			//生成取现订单
			Enchashment ment=new Enchashment();
			ment.setCreateTime(StringUtil.DateToString(
						new Date(), "yyyy-MM-dd HH:mm:ss"));
			ment.setTransAmt(ea.getTransAmt());
			ment.setUsrCustId(ea.getUsrCustId());
			ment.setEnchashmentApply(ea);
			ment.setOpenAcctId(ea.getOpenAcctId());
			ment=(Enchashment) this.bizdataservice.bizSave(Enchashment.class, ment);
			Cash hfcash=new Cash();
			hfcash.setBgRetUrl(SignUtils.PUBLIC_HOST
					+ "huifucallback/printcashResult");
			hfcash.setTransAmt(StringUtil.BigDecimal2String(ment.getTransAmt()));
			hfcash.setOrdId(ment.getId().toString());
			hfcash.setUsrCustId(ea.getUsrCustId());
			hfcash.setOpenAcctId(ea.getOpenAcctId());
			String url = hfcash.getParam();
			
			//设置取现申请状态为取现中
			ea.setState(new Dict(42));
			this.bizdataservice.bizSave(EnchashmentApply.class, ea);
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("生成取现订单出错,请重试");
			try {
				response.getWriter().print("生成取现订单出错,请重试");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//自动投标计划
	@RequestMapping(value = "/huifu/autoTenderPlan")
	public void autoTenderPlan(AutoTenderPlan tenderplan,HttpServletResponse response,HttpSession  session){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			if(tenderplan.getTransAmt().indexOf(".")==-1){
				tenderplan.setTransAmt(tenderplan.getTransAmt().concat(".00"));
			}
			User user=(User) session.getAttribute("user");
			String usrCustId=user.getHuifuID();
			tenderplan.setUsrCustId(usrCustId);
			String url = tenderplan.getParam();
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("生成自动投标计划订单出错,请重试");
			try {
				response.getWriter().print("生成自动投标计划订单出错,请重试");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//债权转让 
	//param:tender 数据库对象：债权转让订单
	@RequestMapping(value = "/huifu/creditAssign")
	public void creditAssign(HttpServletResponse response,CreditAssignTender tender){
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
		CreditAssign	credit=this.creditAssignService.creditAssign(tender);
		String url=	credit.getParam();
		//设置购买状态为待确认
		response.sendRedirect(url);     
		} catch (Exception e) {
		e.printStackTrace();
		try {
			response.getWriter().print("生成投标订单出错,请重试");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
	}

}
