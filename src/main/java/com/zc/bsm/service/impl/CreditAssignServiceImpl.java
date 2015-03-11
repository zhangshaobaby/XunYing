package com.zc.bsm.service.impl;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignTools;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.bsm.dao.CreditAssignDao;
import com.zc.bsm.pojo.BidDetail;
import com.zc.bsm.pojo.BorrowerDetailCreditAssign;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.CreditAssignPoApply;
import com.zc.bsm.pojo.CreditAssignTender;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.CreditAssignService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.BidDetailVo;
import com.zc.bsm.vo.BorrowerDetailCreditAssignVo;
import com.zc.bsm.vo.CreditAssign;
import com.zc.bsm.vo.returnVo.AutoCreditAssignReturn;

@Service("creditAssignService")
public class CreditAssignServiceImpl extends
		BaseServiceImpl<CreditAssignTender, Long> implements
		CreditAssignService {
	protected static final Log loger = LogFactory
			.getLog(CreditAssignService.class);
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
	private RepaymentPlanService repaymentPlanService;
	// 格式
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	public void setBaseDao(CreditAssignDao creditAssignDao) {
		this.baseDao = creditAssignDao;
	}

	@Autowired
	private bizDataService bizdataservice;

	public CreditAssign creditAssign(CreditAssignTender tender)
			throws Exception {
		// 查找原债权转让订单
		tender = (CreditAssignTender) this.bizdataservice.bizfindbyid(
				CreditAssignTender.class, tender.getId());
		// 新建债权转让vo对象
		CreditAssign credit = new CreditAssign();
		credit.setOrdId(tender.getOrderid().toString());
		credit.setSellCustId(tender.getSellCustId());
		credit.setBuyCustId(tender.getBuyCustId());
		credit
				.setCreditAmt(StringUtil.BigDecimal2String(tender
						.getCreditAmt()));
		credit.setOrdDate(StringUtil.TimetoTime(tender.getCreateTime(),
				"yyyy-MM-dd HH:mm:ss", "yyyyMMdd"));
		credit.setCreditDealAmt(StringUtil.BigDecimal2String(tender
				.getCreditDealAmt()));
		credit.setBgRetUrl(SignUtils.PUBLIC_HOST
				+ "huifucallback/printCreditAssignResult");
		// 手续费率为0
		credit.setFee(StringUtil.BigDecimal2String(tender.getFee()));
		List<BidDetailVo> listb = new ArrayList<BidDetailVo>();
		for (BidDetail bidDetail : tender.getBidDetails()) {
			BidDetailVo bidDetailvo = new BidDetailVo();
			bidDetailvo.setBidOrdId(bidDetail.getBidOrdId());
			bidDetailvo.setBidCreditAmt(StringUtil.BigDecimal2String(bidDetail
					.getBidCreditAmt()));
			bidDetailvo.setBidOrdDate(bidDetail.getBidOrdDate());
			List<BorrowerDetailCreditAssignVo> bl = new ArrayList<BorrowerDetailCreditAssignVo>();
			for (BorrowerDetailCreditAssign borrower : bidDetail
					.getBorrowerDetails()) {
				BorrowerDetailCreditAssignVo bv = new BorrowerDetailCreditAssignVo();
				bv.setBorrowerCreditAmt(StringUtil.BigDecimal2String(borrower
						.getBorrowerCreditAmt()));
				bv.setBorrowerCustId(borrower.getBorrowerCustId());
				bv.setPrinAmt(StringUtil.BigDecimal2String(borrower
						.getPrinAmt()));
				bl.add(bv);
			}
			bidDetailvo.setBorrowerDetails(bl);
			listb.add(bidDetailvo);
		}
		Gson gson = new Gson();
		String bidDetailString = gson.toJson(listb);
		bidDetailString = "{" + "\"BidDetails\":" + bidDetailString + "}";
		credit.setBidDetails(bidDetailString);
		return credit;
	}

	public void creditAssignReturn(AutoCreditAssignReturn _return)
			throws Exception {
		String orderid = _return.getOrdId();
		// 债权转让对象
		CreditAssignTender tender = null;
		List<Object> creditAssignTenderList = this.bizdataservice.find(
				"from CreditAssignTender where orderid=?", Long
						.parseLong(orderid));
		tender = (CreditAssignTender) creditAssignTenderList.get(0);
		if (tender.getFlag() != null) {
			return;
		}
		// 返回描述
		tender.setRespDesc(URLDecoder.decode(_return.getRespDesc(), "UTF-8"));
		String code = _return.getRespCode();
		if (code.equals("000")) {
			// 成功
			tender.setFlag(0);
			tender.setState(1);
			try {
				dosuccesscreditAssign(tender);
			} catch (Exception e) {
				e.printStackTrace();
				// 失败
				tender.setFlag(1);
				tender.setRespDesc("生成还款计划等后续处理出错，请联系管理员");
			}
			// 设置转让申请为成功
			CreditAssignPoApply ca = tender.getCreditAssignApply();
			ca.setState(new Dict(52));
			this.bizdataservice.bizSave(CreditAssignPoApply.class, ca);
		} else {
			// 失败
			tender.setFlag(1);
		}
		tender.setOrdDate(_return.getOrdDate());
		this.bizdataservice.bizSave(CreditAssignTender.class, tender);

	}

	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void dosuccesscreditAssign(CreditAssignTender tender)
			throws Exception {
		// 通过债全转让订单的 标转让明细
		for (Tender creditTender : tender.getTenderList()) {
			// 设置转让标为有效
			creditTender.setFlag(0);
			creditTender.setTenderDesc("债权转让成功");
			creditTender.setState(1);
			this.bizdataservice.bizSave(Tender.class, creditTender);
			// 转让的原标
			Tender srcTender = (Tender) this.bizdataservice.bizfindbyid(
					Tender.class, creditTender.getSrcTenderId());
			// 查找未还清的还款计划
			String replayplanhql = " from RepaymentPlan where tenderId=? and state=-1 and flag=0 ";
			List<Object> unreplays = this.bizdataservice.find(replayplanhql,
					srcTender.getId());
			// 添加债权转让的还款计划
			genaraterPayPlan(creditTender, unreplays);
			// 计算还剩下多少钱
			BigDecimal bde = srcTender.getTransAmt().subtract(
					creditTender.getTransAmt());
			if (!bde.toString().equals("0.00")) {
				// 如果还有剩余价值，则创建新的标//并生成新的还款计划
				genaraterSurplusTender(tender, srcTender, bde, unreplays);
			}
			String repayTime = getrepayTime(srcTender);
			// 设置原标的未还款计划为无效
			srcTender.setTenderDesc("已债权转让——" + tender.getCreditAmt());
			srcTender.setEnd_time(repayTime);
			srcTender.setFlag(0);
			this.bizdataservice.bizSave(Tender.class, srcTender);
		}
		// 往流水表中添加数据
		addcashStream(tender);

	}

	// 添加债权转让tender
	public Tender addCreditAssignTender(Tender srcTender,
			CreditAssignTender creditAssign, BidDetail bidDetail)
			throws Exception {
		Tender tender = new Tender();
		tender.setType(1);
		tender.setTransAmt(bidDetail.getBidCreditAmt());
		tender.setProject(srcTender.getProject());
		tender.setMaxTenderRate("0.01");
		tender.setUsrCustId(creditAssign.getBuyCustId());
		tender.setSrcTenderId(srcTender.getId());
		tender.setIsFreeze("Y");
		tender.setCreditAssignTenderId(creditAssign.getId());
		tender.setSrcTransAmt(bidDetail.getBidCreditAmt());
		tender.setParentTender(srcTender.getId());
		// 开始时间为上一个还息日
		String replayplanhql = "select  repayTime from RepaymentPlan where tenderId=? and state=1 and flag=0 order by repayTime desc";
		List<Object> repayTimes = this.bizdataservice.getTopRows(replayplanhql,
				1, srcTender.getId());
		String repayTime = "";
		// 如果从来没派息过
		if (repayTimes == null || repayTimes.size() == 0) {
			repayTime = srcTender.getProject().getStart_time();
		} else {
			repayTime = (String) repayTimes.get(0);
		}
		tender.setStart_time(repayTime);
		// 结束时间为产品的结束日
		tender.setEnd_time(srcTender.getEnd_time());
		this.bizdataservice.bizSave(Tender.class, tender);
		return tender;
	}

	/**
	 * 如果还有剩余价值，则创建新的标//并生成新的还款计划 tender ：债权转让标 srcTender ：原标 bde 剩余本金 unreplays
	 * :原标未还款的还款计划
	 */
	public void genaraterSurplusTender(CreditAssignTender tender,
			Tender srcTender, BigDecimal bde, List<Object> unreplays)
			throws Exception {
		// 新建标
		Tender ten = new Tender();
		ten.setType(0);
		ten.setTransAmt(bde);
		ten.setProject(srcTender.getProject());
		ten.setMaxTenderRate("0.01");
		ten.setUsrCustId(srcTender.getUsrCustId());
		// 原标
		Long srcTenderid = srcTender.getSrcTenderId();
		if (srcTenderid == null) {
			srcTenderid = srcTender.getId();
		}
		ten.setSrcTenderId(srcTenderid);
		ten.setFlag(0);
		ten.setIsFreeze("Y");

		BigDecimal srctransamt = srcTender.getSrcTransAmt();
		if (srctransamt == null) {
			srctransamt = srcTender.getTransAmt();
		}
		ten.setSrcTransAmt(srctransamt);
		ten.setParentTender(srcTender.getId());
		String repayTime = getrepayTime(srcTender);
		ten.setStart_time(repayTime);
		// 结束时间为产品的结束日
		ten.setEnd_time(srcTender.getEnd_time());
		this.bizdataservice.bizSave(Tender.class, ten);
		// 添加债权转让之后剩余本金的还款计划
		genaraterPayPlan(ten, unreplays);

	}

	// 获取一个标的上一次还款时间
	public String getrepayTime(Tender srcTender) {
		// 开始时间为上一个还息日
		String replayplanhql = "select  repayTime from RepaymentPlan where tenderId=? and state=1 and flag=0 order by repayTime desc";
		List<Object> repayTimes = this.bizdataservice.getTopRows(replayplanhql,
				1, srcTender.getId());
		String repayTime = "";
		// 如果从来没派息过
		if (repayTimes == null || repayTimes.size() == 0) {
			repayTime = srcTender.getProject().getStart_time();
		} else {
			repayTime = (String) repayTimes.get(0);
		}
		return repayTime;
	}

	// 将原标的还款计划设置为无效 并生成新的还款计划
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void genaraterPayPlan(Tender tender, List<Object> unreplays)
			throws Exception {
		// 设置状态不可用
		for (Object object : unreplays) {
			RepaymentPlan replayplan = (RepaymentPlan) object;
			RepaymentPlan newreplayplan = (RepaymentPlan) BeanUtils
					.cloneBean(replayplan);
			// 清空主键
			newreplayplan.setId(null);
			// 如果为本金
			if (replayplan.getRepayCount() == 0) {
				newreplayplan.setRepayMoney(tender.getTransAmt());
			} else {
				// 计算收益
				BigDecimal repayMoney = SignTools.getRate(
						StringUtil.StringToDate(replayplan.getStart_time(),
								"yyyy-MM-dd"), StringUtil.StringToDate(
								replayplan.getEnd_time(), "yyyy-MM-dd"), tender
								.getProject().getDay_rate(), tender
								.getTransAmt());
				newreplayplan.setRepayMoney(repayMoney);
			}
			// 设置收益人
			newreplayplan.setUsrCustId(tender.getUsrCustId());
			newreplayplan.setTenderId(tender.getId());
			Date nowdate = new Date();
			newreplayplan.setCreateTime(format.format(nowdate));
			newreplayplan.setUpdateTime(format.format(nowdate));
			this.repaymentPlanService.save(newreplayplan);
		}
	}

	// 设置原标的未还款计划为无效
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void setUnableForSrcPlan(List<Object> unreplays) throws Exception {
		// 设置状态不可用
		for (Object object : unreplays) {
			RepaymentPlan replayplan = (RepaymentPlan) object;
			replayplan.setFlag(1);
			Date nowdate = new Date();
			replayplan.setUpdateTime(format.format(nowdate));
			this.repaymentPlanService.saveOrUpdate(replayplan);
		}
	}

	// 往流水表中添加数据
	public void addcashStream(CreditAssignTender tender) {
		try {
			// 买方
			CashStream cs = new CashStream();
			cs.setFlag(0);
			cs.setOrdDate(tender.getOrdDate());
			cs.setOrdId(tender.getId().toString());
			cs.setOutCustId(tender.getBuyCustId());
			cs.setTransAmt(tender.getCreditDealAmt());
			cs.setType(new Dict(56));
			// 可用余额
			try {
				cs.setAvlBal(enchashmentService.getBalanceObj(
						tender.getBuyCustId()).getAvlBal());
			} catch (Exception e) {
				cs.setAvlBal("未知..可登陆汇付网站查询");
			}
			// 设置摘要
			cs.setSummary(SignTools.getProjectTypeName(tender
					.getCreditAssignApply().getProject().getType())
					+ "_"
					+ tender.getCreditAssignApply().getProject().getName());
			this.bizdataservice.bizSave(CashStream.class, cs);
			// 卖方
			CashStream sellcs = new CashStream();
			sellcs.setFlag(0);
			sellcs.setOrdDate(tender.getOrdDate());
			sellcs.setOrdId(tender.getId().toString());
			sellcs.setInCustId(tender.getSellCustId());
			sellcs.setTransAmt(tender.getCreditDealAmt());
			sellcs.setType(new Dict(57));
			// 可用余额
			try {
				sellcs.setAvlBal(enchashmentService.getBalanceObj(
						tender.getSellCustId()).getAvlBal());
			} catch (Exception e) {
				sellcs.setAvlBal("未知..可登陆汇付网站查询");
			}
			// 设置摘要
			sellcs.setSummary(SignTools.getProjectTypeName(tender
					.getCreditAssignApply().getProject().getType())
					+ "_"
					+ tender.getCreditAssignApply().getProject().getName());
			this.bizdataservice.bizSave(CashStream.class, sellcs);
		} catch (Exception e) {
			e.printStackTrace();
			loger.error("债权转让往流水表中添加数据失败 。。。tenderid-" + tender.getId());
		}
	}

	public CreditAssignTender addCreditAssign(CreditAssignTender tender,
			Long creditAssignid, User user) {
		CreditAssignPoApply creditAssign = (CreditAssignPoApply) this.bizdataservice
				.bizfindbyid(CreditAssignPoApply.class, creditAssignid);
		// 设置债权转让信息
		tender.setBuyCustId(user.getHuifuID());
		tender.setCreditAmt(creditAssign.getCreditAmt());
		tender.setCreditDealAmt(creditAssign.getCreditDealAmt());
		tender.setFee(new BigDecimal("0"));
		tender.setSellCustId(creditAssign.getSellCustId());
		tender.setCreditAssignApply(new CreditAssignPoApply(creditAssignid));
		// 原标信息
		// 设置转让订单的原投标标信息
		tender.setBidDetails(creditAssign.getBidDetails());
		// 保存转让订单
		try {
			tender = (CreditAssignTender) this.bizdataservice.bizSave(
					CreditAssignTender.class, tender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		// 设置债权对象所生成的转让标
		List<Tender> tenders = genaraterCreditTenders(tender);
		tender.setOrderid(tenders.get(0).getId());
		try {
			tender = (CreditAssignTender) this.bizdataservice.bizSave(
					CreditAssignTender.class, tender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tender;
	}

	// 生成债权转让标集合
	public List<Tender> genaraterCreditTenders(CreditAssignTender creditTender) {
		List<Tender> tenders = new ArrayList<Tender>();
		// 通过债全转让订单的 标转让明细
		for (BidDetail bidDetail : creditTender.getBidDetails()) {
			// 转让的原标
			Tender srcTender = (Tender) this.bizdataservice.bizfindbyid(
					Tender.class, bidDetail.getBidOrdId());
			// 添加债权转让tender
			Tender tender = null;
			try {
				tender = addCreditAssignTender(srcTender, creditTender,
						bidDetail);
				tenders.add(tender);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return tenders;
	}

	// 获取债权转让收益
	public String  getCreditMoney(String sellCustId, Long projectid) {
		String rateAmthql = "select SUM(creditDealAmt)-SUM(creditAmt) from CreditAssignTender where  sellCustId=? and creditAssignApply.project.id=?";
        Object  rateobj=  this.getRowCount(rateAmthql,sellCustId,projectid);
        return rateobj!=null?rateobj.toString():"0";
	}

}
