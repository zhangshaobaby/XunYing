package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.DynamicRateDao;
import com.zc.bsm.pojo.DynamicRate;
import com.zc.bsm.service.DynamicRateService;


@Service("dynamicRateService")
public class DynamicRateServiceImpl extends BaseServiceImpl<DynamicRate, Long> implements DynamicRateService{
	
	@Autowired
	public void setBaseDao(DynamicRateDao advertDao) {
		this.baseDao = advertDao;
	}
	
	public Page findByPage(Page page,String time1,String time2){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select new List (id,day_rate,startTime) from DynamicRate where flag = 0 ");
		if(time1!=null&&!time1.equals("")){
			hql.append(" and startTime > ? ");
			param.add(time1);
		}
		if(time2!=null&&!time2.equals("")){
			hql.append(" and startTime < ? ");
			param.add(time2);
		}
		hql.append(" order by startTime desc");
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page, param.toArray());
	}
	
}
