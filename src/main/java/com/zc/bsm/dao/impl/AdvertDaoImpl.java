package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.AdvertDao;
import com.zc.bsm.pojo.Advert;

@Repository("advertDao")
public class AdvertDaoImpl extends BaseDaoImpl<Advert, Long> implements AdvertDao{
	
}
