package com.zc.bsm.action;

import java.beans.DesignMode;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.base.action.Action;
import com.zc.base.po.Dict;
import com.zc.base.util.Page;
import com.zc.base.util.SignTools;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.BidDetail;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.BorrowerDetailCreditAssign;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.CreditAssignPoApply;
import com.zc.bsm.pojo.CreditAssignTender;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.CreditAssignService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.message;

@Controller
@Scope("prototype")
/**
 * 债权转让类
 */
public class CreditAssignAction  extends Action{
	@Autowired
	private CreditAssignService creditAssignService;
	@Autowired
	private bizDataService bizdataService;
	@Autowired
	private TenderService tenderService;
	   @Autowired
	    private RepaymentPlanService repaymentPlanService;
		// 格式
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //保存债权转让申请
	@RequestMapping(value="creditAssign/add")
	@ResponseBody
	public String addCreditAssign(CreditAssignPoApply creditAssign){
		try {	
			
			        Date nowdate=new Date();
					creditAssign.setState(new Dict(51));
					creditAssign.setApplydate(StringUtil.DateToString(nowdate, "yyyy-MM-dd"));
					//设置开始时间
					creditAssign.setStartTime(StringUtil.DateToString(nowdate, "yyyy-MM-dd")+" 00:00:00");
					//设置结束时间
					Date lastDate=StringUtil.getIntervalDate(nowdate, 1) ;
					creditAssign.setEndTime(StringUtil.DateToString(lastDate, "yyyy-MM-dd")+" 00:00:00");
					//设置产品对象
					Project project=(Project) this.bizdataService.bizfindbyid(Project.class,creditAssign.getProject().getId() );
					creditAssign.setProject(project);
					//设置债权转让信息
					creditAssign.setBidDetails(getbidDetails(creditAssign));
					   
					//查询是否有相同记录
					List<Object> oldpo=this.bizdataService.find("from CreditAssignPoApply where sellCustId=? and project.id=? and applydate=?",creditAssign.getSellCustId(),creditAssign.getProject().getId(),creditAssign.getApplydate());
					if(oldpo!=null&&oldpo.size()>0){
						CreditAssignPoApply  assign=(CreditAssignPoApply) oldpo.get(0);
						creditAssign.setId(assign.getId());
					}
					bizdataService.bizSave(CreditAssignPoApply.class, creditAssign);
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return ERROR;
				}
	
	 return "success";	
	}
	  //计算债权人明细 前期需求 转让所有的标
	 public  List<BidDetail> getbidDetails(CreditAssignPoApply creditAssign){
		 //查询当前用户 投资该项目的标 
		   String hql="from Tender where project.id=? and usrCustId=? and flag=0";
		   List<Tender> tenderlist= this.tenderService.find(hql,creditAssign.getProject().getId(),creditAssign.getSellCustId());
		   
		 
		   List<BidDetail> bidDetails=new ArrayList<BidDetail>();
		   
		   for(Tender tender :tenderlist){
			   
			 //原标信息
				 BidDetail bidDetail=new BidDetail();
				 bidDetail.setBidOrdId(tender.getId().toString());
				 bidDetail.setBidOrdDate(tender.getOrdDate());
				 bidDetail.setBidCreditAmt(tender.getTransAmt());
				 //借款人明细
				 List<BorrowerDetailCreditAssign> bdclist=new ArrayList<BorrowerDetailCreditAssign>();
				 BorrowerDetailCreditAssign  bdc=new BorrowerDetailCreditAssign();
				 bdc.setBorrowerCreditAmt(tender.getTransAmt());
				 bdc.setPrinAmt(new BigDecimal(0));
				 Borrower b=(Borrower) this.bizdataService.bizfindbyid(Borrower.class, creditAssign.getProject().getBorrowId());
				 bdc.setBorrowerCustId(b.getUsrCustId());
				 bdclist.add(bdc);
				 //设置标的借款人信息
				 bidDetail.setBorrowerDetails(bdclist);
				 this.bizdataService.save(bdc);
				 bidDetails.add(bidDetail);	
				 try {
					bidDetail=(BidDetail) this.bizdataService.bizSave(BidDetail.class, bidDetail);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }	
		return bidDetails;
	}
	
	 //跳转到转让申请界面
	@RequestMapping(value="creditAssign/toCreditAssign")
	public String toCreditAssign(Tender tender,ModelMap map){
		tender=(Tender) this.bizdataService.bizfindbyid(Tender.class,tender.getId());
	    map.put("tender", tender);
	    try {
	    	  //计算剩余天数
			int diffDays=SignTools.dateDiffMonth(new Date(),StringUtil.StringToDate(tender.getProject().getPendTime(), "yyyy-MM-dd") );
			map.put("diffDays", diffDays);  
			
			String replayplanhql="select  repayTime from RepaymentPlan where tenderId=? and state=1 and flag=0 order by repayTime desc  LIMIT 1";
			String repayTime=(String) this.bizdataService.getRowCount(replayplanhql, tender.getId());
			// 如果从来没派息过
			if(repayTime==null||repayTime.equals("")){
				repayTime=tender.getProject().getRateTime();
			}
			//上个派息日到今天的日差 用于计算债权价值
			long lastRapayDaysFromNow=SignTools.getdiffDays(StringUtil.StringToDate(repayTime, "yyyy-MM-dd"),new Date() );
		 	map.put("rapaydiffDays", lastRapayDaysFromNow);
		 	//计算债权价值 通过日利率来计算 
		    BigDecimal defealtPrice=tender.getTransAmt().multiply(tender.getProject().getDay_rate()).multiply(new BigDecimal(lastRapayDaysFromNow)).add(tender.getTransAmt());
			map.put("defealtPrice", StringUtil.BigDecimal2String(defealtPrice));
	    } catch (Exception e) {
			e.printStackTrace();
		}
	    return "userCenter/CreditAssign";	
	}
	//跳转到购买债权页面
	@RequestMapping(value="creditAssign/toBuyCreditAssign")
	public String toBuyCreditAssign(CreditAssignPoApply credit,ModelMap map){
		credit=(CreditAssignPoApply) this.bizdataService.bizfindbyid(CreditAssignPoApply.class,credit.getId());
	    map.put("credit", credit);
	    return "project/CreditAssignTender";	
	}
	 //添加购买债权申请
	@RequestMapping(value="/creditAssign/Initial")
	public String addCreditAssign(CreditAssignTender tender,Long creditAssignid,HttpSession  session){
	 try {
		 User user=(User) session.getAttribute("user");
		 tender= creditAssignService.addCreditAssign(tender, creditAssignid, user);
		 //重定向到汇付接口
		 return "redirect:/huifu/creditAssign?id="+tender.getId();
	 } catch (Exception e) {
		e.printStackTrace();
	   message me=new message();
	   me.setMsgString("生成标订单失败");
		return ERROR;
	}
	}
	//跳转到债权转让列表页
	@RequestMapping(value="creditAssign/list")
	public String list(ModelMap map,Page page){
		if(page==null){
			page=new Page<CreditAssignPoApply>();
		}
		String hql="from CreditAssignPoApply where state.id=51";
		page.setHql(hql);
     	page=this.creditAssignService.findByPage(page);
		return "project/CreditAssignList";
	}
	//弹出债券转让信息
	@RequestMapping(value="creditAssign/creditinfor")
	@ResponseBody
	public Map<String,String> creditinfor(String projectid,HttpSession  session){
		 User user=(User) session.getAttribute("user");
		Map<String,String> map=new HashMap<String, String>();
		Project project=(Project) this.bizdataService.bizfindbyid(Project.class, projectid);
		//产品编号
		map.put("id", project.getId().toString());
		//产品名称
		map.put("proname", project.getName());
		//查询投资总额
		//查询当前用户 投资该项目的标
		String hql="select sum(transAmt) from Tender where project.id=? and usrCustId=? and flag=0";
		Object sumtransAmt=this.bizdataService.getRowCount(hql,Long.parseLong(projectid),user.getHuifuID());
		map.put("transAmt", sumtransAmt.toString());
		//持有期限/总期限
		try {
		  Long ownDays=SignTools.getdiffDays(StringUtil.StringToDate(project.getPstartTime(),"yyyy-MM-dd") , new Date())+1;
		  map.put("ownDays", ownDays.toString());
		  Long totalDays=SignTools.getdiffDays(StringUtil.StringToDate(project.getPstartTime(),"yyyy-MM-dd") , StringUtil.StringToDate(project.getPendTime(),"yyyy-MM-dd"));
		  map.put("totalDays", totalDays.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 map.put("rate",StringUtil.BigDecimal2String(project.getRate()));
		 //查找该产品总收益
		 String incomeMoneyhql="select SUM(r.repayMoney) from RepaymentPlan as r   where r.flag=0  and r.state=1 and r.usrCustId=?   and r.projectId=? ";
		 Object replanMoney=this.bizdataService.getRowCount(incomeMoneyhql,user.getHuifuID(),Long.parseLong(projectid));
		//加上债权转让收益
		  String rateMoney=creditAssignService.getCreditMoney(user.getHuifuID(), Long.parseLong(projectid));
		  String  incomeMoney= StringUtil.BigDecimal2String(new BigDecimal(replanMoney.toString()).add(new BigDecimal(rateMoney)));
		 map.put("incomeMoney",incomeMoney);
		 //剩余预计收益
		 String surplusMoneyhql="select SUM(r.repayMoney) from RepaymentPlan as r   where r.flag=0  and r.state=-1 and r.usrCustId=?   and r.projectId=? and repayCount>0 ";
		 Object surplusMoney=this.bizdataService.getRowCount(surplusMoneyhql,user.getHuifuID(),Long.parseLong(projectid));
		 map.put("surplusMoney",surplusMoney.toString());
		 
		 //剩余期限
		 
			try {
				 Long diffDays = SignTools.getdiffDays(new Date(),StringUtil.StringToDate(project.getPendTime(), "yyyy-MM-dd") );
				 map.put("surplusDays",String.valueOf(diffDays));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//当前剩余价值
			BigDecimal totalPrice=new BigDecimal(0);
			
			//查询当前用户 投资该项目的标
			   String tenderhql="from Tender where project.id=? and usrCustId=? and flag=0";
			   List<Object> tenderlist= this.bizdataService.find(tenderhql,Long.parseLong(projectid),user.getHuifuID());
			   //第一个 标
			   for( Object tenderobj:tenderlist){
				   
				   Tender tender=(Tender)tenderobj;
				   String replayplanhql="select  repayTime from RepaymentPlan where tenderId=? and state=1 and flag=0 order by repayTime desc ";
				   List<Object>   repayTimes= this.tenderService.getTopRows(replayplanhql,1,  tender.getId());
				   String repayTime="";	
				 // 如果从来没派息过
					if(repayTimes==null||repayTimes.size()==0){
						repayTime=tender.getProject().getStart_time();
					}else{
						repayTime=(String) repayTimes.get(0);
					}
					//上个派息日到今天的日差 用于计算债权价值 今日利息也是债权人利息 因此加1；
					long lastRapayDaysFromNow=0;
					try {
						lastRapayDaysFromNow = SignTools.getdiffDays(StringUtil.StringToDate(repayTime, "yyyy-MM-dd"),new Date() )+1;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
				 	//项目的日率
				     BigDecimal dayRate = tender.getProject().getDay_rate();
						//计算债权价值 通过日利率来计算 
					    BigDecimal defealtPrice=tender.getTransAmt().multiply(dayRate).multiply(new BigDecimal(lastRapayDaysFromNow));
					    defealtPrice=defealtPrice.add(tender.getTransAmt());
					    totalPrice=totalPrice.add(defealtPrice);
			       }
			      map.put("totalPrice",StringUtil.BigDecimal2String(totalPrice) );
			      return map;
			   }
			   
	}
