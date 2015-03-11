package com.zc.bsm.service.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.bsm.dao.RechargeOrderDao;
import com.zc.bsm.pojo.RechargeOrder;
import com.zc.bsm.service.RechargeOrderService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.NetSave;
@Service("rechargeOrderService")
public class RechargeOrderServiceImpl extends BaseServiceImpl<RechargeOrder, String> implements RechargeOrderService {
	protected Log logger = LogFactory.getLog(RechargeOrderServiceImpl.class);
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	public void setBaseDao(RechargeOrderDao rechargeOrderDao) {
		this.baseDao = rechargeOrderDao;
	}
	public String recharge(RechargeOrder rechargeOrder,String huifuID, String gateBusiId) {
		String url="";
		try {
			rechargeOrder = (RechargeOrder) bizdataservice.bizSave(RechargeOrder.class, rechargeOrder);
			NetSave netsave = new NetSave();
			netsave.setBgRetUrl(SignUtils.PUBLIC_HOST+ "huifucallback/printRechargeResult");
			netsave.setRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/success");
			netsave.setUsrCustId(huifuID);
			netsave.setOrdId(rechargeOrder.getId().toString());
			netsave.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(rechargeOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),"yyyyMMdd"));
			netsave.setGateBusiId(gateBusiId);
			if(null!=rechargeOrder.getBankId())
			{
				// 查找银行数据字典
				Dict bankdict = (Dict) this.bizdataservice.bizfindbyid(Dict.class,rechargeOrder.getBankId().getId());
				netsave.setOpenBankId(bankdict.getDictid());
			}
			
			netsave.setDcFlag("D");
			netsave.setTransAmt(StringUtil.BigDecimal2String(rechargeOrder.getAmount()));
			url = netsave.getParam();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return url;
	}
}
