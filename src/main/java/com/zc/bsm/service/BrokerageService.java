package com.zc.bsm.service;


import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.Brokerage;



public interface BrokerageService extends BaseService<Brokerage,Long> {
	public Brokerage findBytenderUser(String tender,String userid,String usertype);
	public void updateByproduct(Long pid,Integer state);
}
