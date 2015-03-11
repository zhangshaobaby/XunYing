package com.zc.bsm.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zc.abf.pojo.Operator;
import com.zc.base.action.Action;
import com.zc.base.po.Dict;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.LoansSend;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.CashStreamService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LoansSendService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.OperatorLogService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.RepaymentSendService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.CorpRegister;
import com.zc.bsm.vo.Loans;
import com.zc.bsm.vo.Repayment;
import com.zc.bsm.vo.TenderCancle;
import com.zc.bsm.vo.UserBindCard;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;

/**
 * 汇付的接口
 * @author Administrator
 *
 */
@Controller
public class huifucallZLAction extends Action {
	@Autowired
	public UserService userService;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	public CashStreamService cashStreamService;
	@Autowired
	public LoansSendService loansSendService;
	@Autowired
	public RepaymentSendService repaymentSendService;
	@Autowired
	public ProjectService projectService;
	@Autowired
	public RepaymentPlanService repaymentPlanService;
	@Autowired
	public TenderService tenderService;
	@Autowired
    private  EnchashmentService enchashmentService;
	@Autowired
	private  LuckyMoneyService luckyMoneyService;
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	private OperatorLogService operatorLogService;
	 /**
	 * 企业注册
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/huifu/CorpRegister")
	public ModelAndView CorpRegister(Long id){
		ModelAndView model = new ModelAndView("jump");
		Borrower borrower = null;
		if(id != null && id != 0){
			borrower = borrowerService.get(id);
		}
		CorpRegister cr = new CorpRegister();
		if(borrower!=null){
			cr.setInstuCode(borrower.getInstuCode());
			cr.setBusiCode(borrower.getBusiCode());
			cr.setTaxCode(borrower.getTaxCode());
			cr.setUsrId(borrower.getAccount().toString());
			cr.setUsrName(borrower.getName());
			cr.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printCorpRegisterReturn");
			model.addObject("href", cr.getParam());
		} else {
			model.addObject("href", "");
		}
		return model;
	}
	/**
	 * 用户绑卡
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/huifu/UserBindCard")
	public void UserBindCard(ModelMap map,HttpSession session,HttpServletResponse response){
		try {
			User user = (User)session.getAttribute("user");
//			User user = userService.get("402881e4479a705c01479a7110a30000");
			
			UserBindCard bindCard=new UserBindCard();
			bindCard.setUsrCustId(user.getHuifuID());
			bindCard.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printUserBindCard");
			String url=bindCard.getParam();
			map.put("forwardurl", url);
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().print("生成绑卡订单出错,请重试");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 用户绑卡
	 * @param map
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/auth/huifu/enterpriseUserBindCard")
	public void enterpriseUserBindCard(ModelMap map,HttpSession session,HttpServletResponse response,String usrCustId){
		try {
			UserBindCard bindCard=new UserBindCard();
			bindCard.setUsrCustId(usrCustId);
			bindCard.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printUserBindCard");
			String url=bindCard.getParam();
			map.put("forwardurl", url);
			response.sendRedirect(url);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().print("生成绑卡订单出错,请重试");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	/**
	 * 扣款   放款
	 */
	//@RequestMapping(value = "/huifu/Loans")
	@RequestMapping(value = "/auth/huifu/Loans")
	@ResponseBody
	public String Loans(Long id,HttpSession session){
		//id 合法性  是否已经成功  上线  通过审核  等待放款
		if(session.getAttribute("loansp"+id)==null){
		  try{
			session.setAttribute("loansp"+id,"flag");
			Project project = projectService.get(id);
			if(project!=null&&(project.getState()==0||project.getState()==3)&&project.getTotal_money().compareTo(project.get_now_money())==0&&project.getOnline()==0){
				if(project.getState()==0){
					project.setState(3);
					projectService.saveOrUpdate(project);
				}
				//循环此项目下所有投标
				List<Tender> list = tenderService.checkLoans(id);
				Loans loans ;
				LoansSend loansSend ;
				String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				Borrower borrower = borrowerService.get(project.getBorrowId());
				for(Tender tender : list){
					
					if(tender.getFreezeOrdId()==null||tender.getFreezeTrxId()==null){
						continue;
					}
					//重设为等待响应
					tender.setState(0);
					
					loans = new Loans();
					loans.setOrdDate(tender.getOrdDate());
					loans.setOutCustId(tender.getUsrCustId());
					loans.setTransAmt(StringUtil.BigDecimal2String(tender.getTransAmt()));//金额
					loans.setFee("0.00");
					loans.setSubOrdId((tender.getId())+"");
					loans.setSubOrdDate(tender.getOrdDate());
					loans.setInCustId(borrower.getUsrCustId());
					loans.setFeeObjFlag("I");
					loans.setIsDefault("N");
					if(tender.getLuckyId()!=null){
						LuckyMoney luckyMoney = luckyMoneyService.get(tender.getLuckyId());
						String json = "{\"LoansVocherAmt\":\""+luckyMoney.getMoney()+"\"}";
						loans.setReqExt(json);
					}
					loans.setIsUnFreeze("Y");
					///插入一条解冻记录到数据库
					Freeze freeze = new Freeze();
					freeze.setSubAcctId(tender.getId().toString());
					freeze.setType(new Dict(41));
					freeze.setTransAmt(tender.getTransAmt());
					this.bizdataservice.save(freeze);
					
					//主键作为UnfreezeOrdId
					//loans.setUnFreezeOrdId(tender.getFreezeOrdId());//解冻id
					loans.setUnFreezeOrdId(freeze.getId().toString());//解冻id
					
					loans.setFreezeTrxId(tender.getFreezeTrxId());//冻结标识	定长 18 位	组成规则为：8 位商户专属平台日期+10 位系统流水号
					loans.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printLoans");
					//生成  资金流水
					loansSend = new LoansSend();
					loansSend.setOrdId(tender.getId().toString());
					loansSend.setOrdDate(loans.getOrdDate());
					loansSend.setInCustId(loans.getInCustId());
					loansSend.setOutCustId(loans.getOutCustId());
					loansSend.setTransAmt(tender.getTransAmt());
					//等待响应
					loansSend.setState(0);
					
					//	tender.getId();//记录标id/卡号
					
					loansSend.setCreateTime(cTime);
					loansSend.setFlag("0");
					
					loansSendService.saveOrUpdate(loansSend);
					
					loans.setOrdId((loansSend.getId())+"");
					tender.setUnFreezeOrdId(freeze.getId().toString());
					tender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					tenderService.saveOrUpdate(tender);
					try{
						//发出放款请求
						String result = TransSubmit.doPost(loans.getParam());
						if(result!=null&&JSON.parseObject(result).getString("RespCode").equals("000")){
							
						}else{
							//接受发送响应信息
							loansSend.setErrorCode(JSON.parseObject(result).getString("RespCode").toString());
						}
					}catch(ClientProtocolException e){
						e.printStackTrace();
						loansSend.setErrorCode("-1");//网络不通
					}catch(Exception e){
						e.printStackTrace();
						loansSend.setErrorCode("-2");//json解析失败
					}finally{
						loansSendService.saveOrUpdate(loansSend);
					}
					
				}
				
			 }
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, project, "4");
			}catch(Exception e){
				e.printStackTrace();				
			}finally{		
				session.removeAttribute("loansp"+id);
			}

		}
		return "sended";
	}
	/**
	 * 扣款   还款
	 * @param id    projectId
	 * @param type  全款/利息
	 */
	//@RequestMapping(value = "/huifu/Repayment")
	@RequestMapping(value = "/auth/huifu/Repayment")
	@ResponseBody
	public String Repayment(Long id,Integer repayCount,HttpSession session,String repayTime){
		if(session.getAttribute("repaymentp"+id)==null){
		 try{
			session.setAttribute("repaymentp"+id,"flag");
			
			//id 合法性  是否已经成功  上线  通过审核  等待放款
			Project project = projectService.get(id);
			//List<CashStream> cashList = new ArrayList<CashStream>();
			if(project!=null&&project.getState()==1&&project.getOnline()==0){
				Repayment repayment ;
				RepaymentSend repaymentSend ;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				Borrower borrower = borrowerService.get(project.getBorrowId());
				//判断账户金额是否足够
				User user = new User();
				user.setHuifuID(borrowerService.get(project.getBorrowId()).getUsrCustId());
				QueryBalanceBgReturn _return = enchashmentService.getBalanceObj(user);
				
				BigDecimal avl = new BigDecimal(Double.parseDouble(_return.getAvlBal().replace(",", "")));
				List _list = repaymentPlanService.findByPid(id,-1,repayCount,repayTime);
				if(_list!=null&&_list.get(0)!=null){
					BigDecimal need = new BigDecimal(Double.parseDouble(_list.get(0).toString()));
					if(avl.compareTo(need)==-1){
						return "notEnough";
					}
				}else{
					return "sended";
				}
				
				//年利率/12*收益周期  收益周期利率
	//			BigDecimal rate = project.getRate();
	//			rate = rate.divide(new BigDecimal(1200)).multiply(new BigDecimal(project.getRate_type()));
				
				//循环此项目下所有投标
				List<RepaymentPlan> list = repaymentPlanService.checkRepayment(id, repayCount,repayTime);
				
				
				for(RepaymentPlan repay : list){
					//重设为等待响应
					repay.setState(0);
					repay.setUpdateTime(cTime);
					repaymentPlanService.saveOrUpdate(repay);
					
					repayment = new Repayment();
					repayment.setSubOrdId(repay.getTenderId().toString());
					repayment.setSubOrdDate(repay.getOrdDate());
					repayment.setOutCustId(borrower.getUsrCustId());
					
					repayment.setOrdDate(sdf.format(new Date()));
					repayment.setFee("0.00");
					repayment.setInCustId(repay.getUsrCustId());
					repayment.setTransAmt(StringUtil.BigDecimal2StringSmall(repay.getRepayMoney()));
					repayment.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printRepayment");
					
					//增加日志流水表
					repaymentSend = new RepaymentSend();
					repaymentSend.setOrdId(repayment.getSubOrdId());
					repaymentSend.setOrdDate(repayment.getSubOrdDate());
					repaymentSend.setOutCustId(repayment.getOutCustId());
					repaymentSend.setTransAmt(repay.getRepayMoney());
					repaymentSend.setPlanId(repay.getId());
					repaymentSend.setInCustId(repayment.getInCustId());
					repaymentSend.setCreateTime(cTime);
					repaymentSend.setFlag("0");
					repaymentSend.setState(0);//等待响应
					repaymentSend.setProjectId(repay.getProjectId());
					repaymentSendService.save(repaymentSend);
					repayment.setOrdId(repaymentSend.getId().toString());
					try{
						//发出放款请求
						String result = TransSubmit.doPost(repayment.getParam());
						if(result!=null&&JSON.parseObject(result).getString("RespCode").equals("000")){
							
						}else{
							//接受发送响应信息
							repaymentSend.setErrorCode(JSON.parseObject(result).getString("RespCode").toString());
						}
					}catch(ClientProtocolException e){
						e.printStackTrace();
						repaymentSend.setErrorCode("-1");//网络不通
					}catch(Exception e){
						e.printStackTrace();
						repaymentSend.setErrorCode("-2");//网络不通
					}finally{
						repaymentSendService.saveOrUpdate(repaymentSend);
						
					}
				}
			}
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, project, "16");
			}catch(Exception e){
				e.printStackTrace();				
			}finally{		
				session.removeAttribute("repaymentp"+id);
			}
			
		}
		return "sended";
	}
	/**
	 * 撤销投标
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/auth/huifu/TenderCancle")
	@ResponseBody
	public String TenderCancle(Long id,HttpServletResponse response){
		Project project = projectService.get(id);
		if(project!=null&&project.getState()==0){
			TenderCancle tc = new TenderCancle();
			List<Tender> tenderList = (List<Tender>)tenderService.findByPid(id,-1);
			for(Tender t : tenderList){
				tc.setOrdId(t.getId().toString());
				tc.setOrdDate(t.getOrdDate());
				tc.setTransAmt(t.getTransAmt().toString());
				tc.setUsrCustId(t.getUsrCustId());
				///插入一条解冻记录到数据库
				Freeze freeze = new Freeze();
				freeze.setSubAcctId(t.getId().toString());
				freeze.setType(new Dict(41));
				freeze.setTransAmt(t.getTransAmt());
				this.bizdataservice.save(freeze);
				
				//主键作为UnfreezeOrdId
				//loans.setUnFreezeOrdId(tender.getFreezeOrdId());//解冻id
				tc.setIsUnFreeze("Y");
				tc.setUnFreezeOrdId(freeze.getId().toString());//解冻id
				tc.setFreezeTrxId(t.getFreezeTrxId());//冻结标识	定长 18 位	组成规则为：8 位商户专属平台日期+10 位系统流水号
				tc.setRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/tendersuccess");
				tc.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printTenderCancle");
				String url=tc.getParam();
				try{
					response.sendRedirect(url);
					break;
				}catch(Exception e){e.printStackTrace();}
			}
		}
		return null;
	}
	public static void main(String[]args){
		BigDecimal rate = new BigDecimal(0.66666666);
		System.out.println(StringUtil.BigDecimal2StringSmall(rate));
	}
}
