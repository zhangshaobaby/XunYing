package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zc.base.service.BaseServiceImpl;
import com.zc.bsm.dao.BrokerageDao;
import com.zc.bsm.pojo.Brokerage;
import com.zc.bsm.service.BrokerageService;

@Service("BrokerageService")
public class BrokerageServiceImpl extends BaseServiceImpl<Brokerage, Long> implements BrokerageService {
	@Autowired
	public void setBaseDao(BrokerageDao brokerageDao) {
		this.baseDao = brokerageDao;
	}
	public  Brokerage findBytenderUser(String tender,String userid,String usertype){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		if(tender!=null&&userid!=null&&usertype!=null){
			hql.append("from Brokerage where tender.id = ? and revenueUser.id= ? and agentType= ?");
			param.add(tender);
			param.add(userid);
			param.add(usertype);
			List<Brokerage> list =  this.baseDao.find(hql.toString(), param.toArray());
			return list.size()==1?list.get(0):null;
		}
		
		return null;
	}
	public void updateByproduct(Long pid,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Brokerage set state.id=? where project.id=?");
		param.add(state);
		param.add(pid);
		this.baseDao.update(hql.toString(), param.toArray());
	}
}