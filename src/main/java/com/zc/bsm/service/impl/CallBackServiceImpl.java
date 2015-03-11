package com.zc.bsm.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignTools;
import com.zc.base.util.SignUtils;
import com.zc.base.util.SmsHttp;
import com.zc.base.util.StringUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.dao.AdvertDao;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.pojo.Bankcard;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.LoansSend;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BankCardService;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.CallBackService;
import com.zc.bsm.service.CashStreamService;
import com.zc.bsm.service.DictService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LoansSendService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.RepaymentSendService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.returnVo.CorpRegisterReturn;
import com.zc.bsm.vo.returnVo.LoansReturn;
import com.zc.bsm.vo.returnVo.QueryTransStatReturn;
import com.zc.bsm.vo.returnVo.RepaymentReturn;


@Service("callBackService")
public class CallBackServiceImpl extends BaseServiceImpl<Advert, Long> implements CallBackService{
	 @Autowired
	 public  msgUtil msgutil;
	@Autowired
	public UserService userService;
	@Autowired
	public BankCardService bankCardService;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	public LoansSendService loansSendService;
	@Autowired
	public TenderService tenderService;
	@Autowired
	public RepaymentSendService repaymentSendService;
	@Autowired
	public RepaymentPlanService repaymentPlanService;
	@Autowired
	public CashStreamService cashStreamService;
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	private  DictService dictservice;
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
	public void setBaseDao(AdvertDao advertDao) {
		this.baseDao = advertDao;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void Loans(LoansReturn _return){
		LoansSend loansSend = new LoansSend();
		User user = null ;
		loansSend.setId(Long.parseLong(_return.getOrdId()));
		loansSend.setOrdId(_return.getSubOrdId());
		loansSend = loansSendService.findBySelf(loansSend);
		Tender tender = tenderService.get(Long.parseLong(_return.getSubOrdId()));
		String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		loansSend.setUpdateTime(cTime);
		if(_return.getRespExt()!=null&&!_return.getRespExt().equals("")){
			try{
				_return.setRespExt(URLDecoder.decode(_return.getRespExt(),"UTF-8"));
			}catch(Exception e){
			}
		}
		if(_return.getRespCode().equals("000")&&_return.validate()){
			if(loansSend.getState()==0){
				//更改红包状态为已用
				if(tender.getLuckyId()!=null&&tender.getLuckyId()!=0){
					LuckyMoney lucky = (LuckyMoney)bizdataservice.bizfindbyid(LuckyMoney.class, tender.getLuckyId());
					lucky.setState(3);
					bizdataservice.saveOrUpdate(lucky);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				CashStream cashStream = new CashStream();
				cashStream.setOrdId(_return.getOrdId());
				try{
					cashStream.setOrdDate(sdf1.format(sdf.parse(_return.getOrdDate())));
				}catch(Exception e){
					
				}
				cashStream.setInCustId(_return.getInCustId());
				cashStream.setOutCustId(_return.getOutCustId());
				cashStream.setTransAmt(new BigDecimal(Double.parseDouble(_return.getTransAmt())));
				cashStream.setCreateTime(cTime);
				cashStream.setType(new Dict(31));
				cashStream.setFlag(0);
				//可用余额
				try {
					cashStream.setAvlBal(enchashmentService.getBalanceObj(_return.getOutCustId()).getAvlBal());
				} catch (Exception e) {
					cashStream.setAvlBal("未知..可登陆汇付网站查询");
				}
				//设置摘要
				cashStream.setSummary(SignTools.getProjectTypeName(tender.getProject().getType())+"_"+tender.getProject().getName());
				cashStreamService.saveOrUpdate(cashStream);
				tender.setState(1);
				loansSend.setState(1);
				loansSend.setErrorCode(_return.getRespCode());
				loansSendService.saveOrUpdate(loansSend);
				tender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				tenderService.saveOrUpdate(tender);

			}
		}else{
			if(tender!=null&&loansSend!=null&&tender.getState()==0&&loansSend.getState()!=-1){
				tender.setState(-1);
				loansSend.setState(-1);
				loansSend.setErrorCode(_return.getRespCode());
				loansSendService.saveOrUpdate(loansSend);
				tender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				tenderService.saveOrUpdate(tender);
			}
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void LoansTransState(QueryTransStatReturn _return){
		LoansSend loansSend = new LoansSend();
		User user = null ;
		loansSend.setId(Long.parseLong(_return.getOrdId()));
		loansSend = loansSendService.findBySelf(loansSend);
		Tender tender = tenderService.get(Long.parseLong(loansSend.getOrdId()));
		String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		loansSend.setUpdateTime(cTime);
		if(_return.getRespCode().equals("000")&&_return.validate()){
			if(loansSend.getState()==0){
				//更改红包状态为已用
				if(tender.getLuckyId()!=null&&tender.getLuckyId()!=0){
					LuckyMoney lucky = (LuckyMoney)bizdataservice.bizfindbyid(LuckyMoney.class, tender.getLuckyId());
					lucky.setState(3);
					bizdataservice.saveOrUpdate(lucky);
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				CashStream cashStream = new CashStream();
				cashStream.setOrdId(_return.getOrdId());
				try{
					cashStream.setOrdDate(sdf1.format(sdf.parse(_return.getOrdDate())));
				}catch(Exception e){
					
				}
				cashStream.setInCustId(loansSend.getInCustId());
				cashStream.setOutCustId(loansSend.getOutCustId());
				cashStream.setTransAmt(loansSend.getTransAmt());
				cashStream.setCreateTime(cTime);
				cashStream.setType(new Dict(31));
				cashStream.setFlag(0);
				//可用余额
				try {
					cashStream.setAvlBal(enchashmentService.getBalanceObj(loansSend.getOutCustId()).getAvlBal());
				} catch (Exception e) {
					cashStream.setAvlBal("未知..可登陆汇付网站查询");
				}
				//设置摘要
				cashStream.setSummary(SignTools.getProjectTypeName(tender.getProject().getType())+"_"+tender.getProject().getName());
				cashStreamService.saveOrUpdate(cashStream);
				tender.setState(1);
				loansSend.setState(1);
				loansSend.setErrorCode(_return.getRespCode());
				loansSendService.saveOrUpdate(loansSend);
				tender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				tenderService.saveOrUpdate(tender);

			}
		}else{
			if(tender!=null&&loansSend!=null&&tender.getState()==0&&loansSend.getState()!=-1){
				tender.setState(-1);
				loansSend.setState(-1);
				loansSend.setErrorCode(_return.getRespCode());
				loansSendService.saveOrUpdate(loansSend);
				tender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				tenderService.saveOrUpdate(tender);
			}
		}
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void printRepayment(RepaymentReturn _return){
		RepaymentPlan repaymentPlan = new RepaymentPlan();
		RepaymentSend repaymentSend = new RepaymentSend();
		User user;
		repaymentSend.setOrdId(_return.getSubOrdId());
		repaymentSend.setId(Long.parseLong(_return.getOrdId()));
		repaymentSend = repaymentSendService.findBySelf(repaymentSend);
		String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		repaymentSend.setUpdateTime(cTime);
		//对应计划
		repaymentPlan = repaymentPlanService.get(repaymentSend.getPlanId());
		if(_return.getRespCode().equals("000")&&_return.validate()){
			if(repaymentSend.getState()==0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				CashStream cashStream = new CashStream();
				cashStream.setOrdId(_return.getOrdId());
				try{
					cashStream.setOrdDate(sdf1.format(sdf.parse(_return.getOrdDate())));
				}catch(Exception e){
					
				}
				cashStream.setInCustId(_return.getInCustId());
				cashStream.setOutCustId(_return.getOutCustId());
				cashStream.setTransAmt(new BigDecimal(Double.parseDouble(_return.getTransAmt())));
				cashStream.setCreateTime(cTime);
				//如果是本金 设置类型为还本数据字典
				if(repaymentPlan.getRepayCount()==0){
					cashStream.setType(new Dict(32));
				}else{
					//如果是其他，未付息
					cashStream.setType(new Dict(58));
				}
				cashStream.setFlag(0);
				//可用余额
				try {
					cashStream.setAvlBal(enchashmentService.getBalanceObj(_return.getInCustId()).getAvlBal());
				} catch (Exception e) {
					cashStream.setAvlBal("未知..可登陆汇付网站查询");
				}
				//设置摘要
				Project project = (Project)bizdataservice.bizfindbyid(Project.class, repaymentPlan.getProjectId());
				
				cashStream.setSummary(SignTools.getProjectTypeName(project.getType())+"_"+project.getName());
				cashStreamService.saveOrUpdate(cashStream);
				repaymentSend.setState(1);
				repaymentSend.setErrorCode("000");
				repaymentPlan.setState(1);
				repaymentPlan.setUpdateTime(cTime);
				repaymentPlanService.saveOrUpdate(repaymentPlan);
				repaymentSendService.saveOrUpdate(repaymentSend);
			
				project.setRepay_money(project.getRepay_money().add(repaymentPlan.getRepayMoney()));
				bizdataservice.saveOrUpdate(project);

				try{
						//发送成功短信
						user=userService.findByHuifuId(_return.getInCustId());
						String retimes = repaymentPlanService.findLastedRepayTimes(repaymentSend.getInCustId(), project.getId());
						int thisretimes=retimes==null?1:Integer.parseInt(retimes);
						Map<String, String> params=new HashMap<String, String>();
						params.put("proname", project.getName());
						params.put("money", _return.getTransAmt());
						params.put("count", thisretimes+"");
						params.put("totalcount", repaymentPlan.getRepayCount().toString());
						if(repaymentPlan.getRepayCount()==0){
							//本金还款
							msgutil.sendmessage("11",new String[]{"phone","webmeg"}, user,  params);
						}else{
							msgutil.sendmessage("1",new String[]{"phone","webmeg"}, user,  params);
						} 
					
					//增加投资人投资总额	
						if(user!=null){
						 BigDecimal alltransAmt=user.getAlltransAmt()==null?new BigDecimal(0):user.getAlltransAmt();
						   BigDecimal	multAmt= alltransAmt.add(new BigDecimal( _return.getTransAmt().replace(",", "")));
							 if(multAmt.compareTo(new BigDecimal(0))==1 ){
								 user.setAlltransAmt(multAmt);
							 }else{
								 user.setAlltransAmt(new BigDecimal(0) );
							 }
							 this.userService.update(user);
						}
						//更新标表还款记录
						Tender thistender=this.tenderService.get(repaymentPlan.getTenderId());
						if(thistender.getRepayAmt()==null)thistender.setRepayAmt(new BigDecimal(0));
						thistender.setRepayAmt(thistender.getRepayAmt().add(cashStream.getTransAmt()));
						thistender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						this.tenderService.saveOrUpdate(thistender);
                } catch (Exception e) {
					
				}
			}
		}else{
			repaymentSend.setErrorCode(_return.getRespCode());
			repaymentSend.setState(-1);
			repaymentPlan.setState(-1);
			repaymentPlan.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			repaymentSend.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			repaymentPlanService.saveOrUpdate(repaymentPlan);
			repaymentSendService.saveOrUpdate(repaymentSend);
		}
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void printRepaymentTransState(QueryTransStatReturn _return){
		RepaymentPlan repaymentPlan = new RepaymentPlan();
		RepaymentSend repaymentSend = new RepaymentSend();
		User user;
		repaymentSend.setId(Long.parseLong(_return.getOrdId()));
		repaymentSend = repaymentSendService.findBySelf(repaymentSend);
		String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		repaymentSend.setUpdateTime(cTime);
		//对应计划
		repaymentPlan = repaymentPlanService.get(repaymentSend.getPlanId());
		if(_return.getRespCode().equals("000")&&_return.validate()){
			if(repaymentSend.getState()==0){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				CashStream cashStream = new CashStream();
				cashStream.setOrdId(_return.getOrdId());
				try{
					cashStream.setOrdDate(sdf1.format(sdf.parse(_return.getOrdDate())));
				}catch(Exception e){
					
				}
				cashStream.setInCustId(repaymentSend.getInCustId());
				cashStream.setOutCustId(repaymentSend.getOutCustId());
				cashStream.setTransAmt(repaymentSend.getTransAmt());
				cashStream.setCreateTime(cTime);
				//如果是本金 设置类型为还本数据字典
				if(repaymentPlan.getRepayCount()==0){
					cashStream.setType(new Dict(32));
				}else{
					//如果是其他，未付息
					cashStream.setType(new Dict(58));
				}
				cashStream.setFlag(0);
				//可用余额
				try {
					cashStream.setAvlBal(enchashmentService.getBalanceObj(repaymentSend.getInCustId()).getAvlBal());
				} catch (Exception e) {
					cashStream.setAvlBal("未知..可登陆汇付网站查询");
				}
				//设置摘要
				Project project = (Project)bizdataservice.bizfindbyid(Project.class, repaymentPlan.getProjectId());
				
				cashStream.setSummary(SignTools.getProjectTypeName(project.getType())+"_"+project.getName());
				cashStreamService.saveOrUpdate(cashStream);
				repaymentSend.setState(1);
				repaymentSend.setErrorCode("000");
				repaymentSend.setUpdateTime(cTime);
				repaymentPlan.setState(1);
				repaymentPlan.setUpdateTime(cTime);
				repaymentPlanService.saveOrUpdate(repaymentPlan);
				repaymentSendService.saveOrUpdate(repaymentSend);
				
				project.setRepay_money(project.getRepay_money().add(repaymentPlan.getRepayMoney()));
				bizdataservice.saveOrUpdate(project);

				try {
						user=userService.findByHuifuId(repaymentSend.getInCustId());
						//短信
						String retimes = repaymentPlanService.findLastedRepayTimes(repaymentSend.getInCustId(), project.getId());
						int thisretimes=retimes==null?1:Integer.parseInt(retimes);
		                Map<String, String> params=new HashMap<String, String>();
						params.put("proname", project.getName());
						params.put("money", repaymentSend.getTransAmt()+"");
						params.put("count", thisretimes+"");
						params.put("totalcount", repaymentPlan.getRepayCount().toString());
						if(repaymentPlan.getRepayCount()==0){
							//本金还款
							msgutil.sendmessage("11",new String[]{"phone","webmeg"}, user,  params);
						}else{
							msgutil.sendmessage("1",new String[]{"phone","webmeg"}, user,  params);
						} 
				
						//增加投资人投资总额					
						if(user!=null){
							BigDecimal alltransAmt=user.getAlltransAmt()==null?new BigDecimal(0):user.getAlltransAmt();
							BigDecimal	multAmt= alltransAmt.add(repaymentSend.getTransAmt());
							if(multAmt.compareTo(new BigDecimal(0))==1 ){
								user.setAlltransAmt(multAmt);
							}else{
								user.setAlltransAmt(new BigDecimal(0) );
							}
							this.userService.update(user);
						}
							
						//更新标表还款记录
						Tender thistender=this.tenderService.get(repaymentPlan.getTenderId());
						if(thistender.getRepayAmt()==null)thistender.setRepayAmt(new BigDecimal(0));
						thistender.setRepayAmt(thistender.getRepayAmt().add(cashStream.getTransAmt()));
						thistender.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						this.tenderService.saveOrUpdate(thistender);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			repaymentSend.setErrorCode(_return.getRespCode());
			repaymentSend.setState(-1);
			repaymentPlan.setState(-1);
			repaymentPlan.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			repaymentSend.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			repaymentPlanService.saveOrUpdate(repaymentPlan);
			repaymentSendService.saveOrUpdate(repaymentSend);
		}
	}
	public void registerBg(CorpRegisterReturn cr){
		Borrower borrower = new Borrower();
		borrower.setName(cr.getUsrName());
		List<Borrower> list = borrowerService.findBy(borrower);
		if(list!=null&&list.size()==1){
			borrower = list.get(0);
			borrower.setUsrCustId(cr.getUsrCustId());
			borrower.setBankId(cr.getOpenBankId());
			borrower.setCardId(cr.getCardId());
			borrower.setUsrCustAccount(cr.getUsrId());
			borrowerService.saveOrUpdate(borrower);
			
			Bankcard bc = new Bankcard();
			bc.setUsrCustId(cr.getUsrCustId());
			bc.setCardNumber(cr.getCardId());
			List<Dict> banklist=this.dictservice.find("from Dict where dictid=?",cr.getOpenBankId());
			Dict bank=null;
			if(banklist.size()>0){
				bank=banklist.get(0);
			}
			bc.setBankId(cr.getOpenBankId());
			bc.setBankName(bank.getDictName());
			bc.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			bankCardService.saveOrUpdate(bc);
		}
	}
}
