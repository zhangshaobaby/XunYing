package com.zc.bsm.service;

import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.RechargeOrder;

public interface RechargeOrderService extends BaseService<RechargeOrder,String> {
	//同接口公用方法开始
	public String recharge(RechargeOrder rechargeOrder,String huifuID, String gateBusiId);
}
