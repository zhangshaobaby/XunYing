package com.zc.bsm.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.EnchashmentApply;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.Cash;
import com.zc.bsm.vo.QueryBalanceBg;
import com.zc.bsm.vo.returnVo.MerCashReturn;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;

@Service("enchashmentService")
public class EnchashmentServiceImpl extends BaseServiceImpl<Enchashment, Long> implements EnchashmentService {
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	public UserService userService;
	/**
	 * 获取余额
	 */
	public QueryBalanceBgReturn getBalanceObj(User   user){
		QueryBalanceBg balance=new QueryBalanceBg();
		balance.setUsrCustId(user.getHuifuID());
		Map<String, String>  parammap= balance.getParam();
		try {
			String qbjson=TransSubmit.doPost(parammap);
			Gson gson = new Gson();
			QueryBalanceBgReturn  balanceReturn=	gson.fromJson(qbjson, QueryBalanceBgReturn.class);
			return balanceReturn;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public QueryBalanceBgReturn getBalanceObj(String usrCustId) {
		if(usrCustId==null||usrCustId.equals("")){
			return null;
		}
		QueryBalanceBg balance=new QueryBalanceBg();
		balance.setUsrCustId(usrCustId);
		Map<String, String>  parammap= balance.getParam();
		try {
			String qbjson=TransSubmit.doPost(parammap);
			Gson gson = new Gson();
			QueryBalanceBgReturn  balanceReturn=	gson.fromJson(qbjson, QueryBalanceBgReturn.class);
			return balanceReturn;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 代取现
	 */

	public boolean printMercashResult(MerCashReturn cashReturn
			) {
		boolean flag = cashReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			return false;
		}
		String orderid = "";
		try {
			orderid = cashReturn.getOrdId();
			Enchashment en = (Enchashment) this.bizdataservice.bizfindbyid(
					Enchashment.class, orderid);
			Integer orderflag = en.getFlag();
			if (orderflag != null && orderflag == 0) {
				
			}else{
				EnchashmentApply apply = en.getEnchashmentApply();
				if (cashReturn.getRespCode().equals("000")) {
					en.setFlag(0);
					// 修改取现申请的状态
					if (apply != null) {
						apply.setState(new Dict(44));
					}
				} else {
					en.setFlag(1);
					// 修改取现申请的状态
					if (apply != null) {
						apply.setState(new Dict(43));
					}
				}
				en.setOpenAcctId(cashReturn.getOpenAcctId());
				en.setOpenBankId(cashReturn.getOpenBankId());
				en
						.setCashDesc(URLDecoder.decode(cashReturn.getRespDesc(),
								"UTF-8"));
				en.setFeeAmt(new BigDecimal(cashReturn.getFeeAmt()));
				en.setFeeAcctId(cashReturn.getFeeAcctId());
				en.setFeeCustId(cashReturn.getFeeCustId());
				this.bizdataservice.bizSave(Enchashment.class, en);
				// 往流水表中新增一条取现记录
				CashStream cashstream = new CashStream();
				cashstream.setOutCustId(cashReturn.getUsrCustId());
				cashstream.setOrdId(en.getId().toString());
				cashstream.setOrdDate("");
				cashstream.setFlag(0);
				cashstream.setTransAmt(en.getTransAmt());
				//查找银行卡名称
				List<Object> banklist=this.bizdataservice.find("from  Dict where dictid=?",en.getOpenBankId());
				String bankname="";
				if(banklist!=null&&banklist.size()>0){
					Dict bank=(Dict)banklist.get(0);
					bankname=bank.getDictName();
				}
				//可用余额
				try {
					cashstream.setAvlBal(this.getBalanceObj(cashReturn.getUsrCustId()).getAvlBal());
				} catch (Exception e) {
					cashstream.setAvlBal("未知..可登陆汇付网站查询");
				}
				cashstream.setSummary("提现|"+bankname);
				cashstream.setType(new Dict(34));
				bizdataservice.bizSave(CashStream.class, cashstream);
				//如果是用户取款 剩余投资回款余额
			    User user=	this.userService.findByHuifuId(cashReturn.getUsrCustId());
				if(user!=null){
				 
				  if(user.getAlltransAmt()!=null){
				   BigDecimal	multAmt= user.getAlltransAmt().subtract(new BigDecimal( cashReturn.getTransAmt().replace(",", "")));
					 if(multAmt.compareTo(new BigDecimal(0))==1 ){
						 user.setAlltransAmt(multAmt);
					 }else{
						 user.setAlltransAmt(new BigDecimal(0) );
					 }
					 this.userService.update(user);
				  }
				}
				
				
				// 保存取现申请状态
				if(apply!=null){
					this.bizdataservice.bizSave(EnchashmentApply.class, apply);
				}
				
			}
         return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public String cash(String transAmt,String usrCustId,String bankcardid,String servFee) {
		//生成取现订单
		Enchashment ment=new Enchashment();
		String url="";
		try {
			ment.setCreateTime(StringUtil.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			ment.setTransAmt(new BigDecimal(transAmt));
			ment.setUsrCustId(usrCustId);
			ment.setOpenAcctId(bankcardid);
			ment=(Enchashment) this.bizdataservice.bizSave(Enchashment.class, ment);
			Cash hfcash=new Cash();
			hfcash.setBgRetUrl(SignUtils.PUBLIC_HOST+ "huifucallback/printcashResult");
			hfcash.setTransAmt(StringUtil.BigDecimal2String(ment.getTransAmt()));
			hfcash.setOrdId(ment.getId().toString());
			hfcash.setUsrCustId(usrCustId);
			hfcash.setOpenAcctId(bankcardid);
			hfcash.setServFee(servFee);
			hfcash.setServFeeAcctId(SignUtils.MER_CUST_ACCT_ID);
			url= hfcash.getParam();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
	
}
