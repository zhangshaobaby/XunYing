package com.zc.bsm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.AdvertDao;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.service.AdvertService;


@Service("advertService")
public class AdvertServiceImpl extends BaseServiceImpl<Advert, Long> implements AdvertService{
	
	@Autowired
	public void setBaseDao(AdvertDao advertDao) {
		this.baseDao = advertDao;
	}
	@Transactional(readOnly = true)
	public Page findByPage(Page page,Advert advert){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Advert where flag = 0 order by id desc");
		if(advert!=null){
			//
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public void changeState(Long id,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Advert set state = ? where id = ?");
		param.add(state);
		param.add(id);
		this.baseDao.update(hql.toString(), param.toArray());
	}
	public List<Advert> mainPageAdvert(){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		hql.append("from Advert where flag = 0 and state  =  1 and start_time <= ? and end_time > ?");
		param.add(now);param.add(now);
		return (List<Advert>)this.baseDao.find(hql.toString(),param.toArray());
	}
}
