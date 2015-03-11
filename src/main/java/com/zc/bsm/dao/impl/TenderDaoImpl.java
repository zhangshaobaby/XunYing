package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.TenderDao;
import com.zc.bsm.pojo.Tender;

@Repository("tenderDao")
public class TenderDaoImpl extends BaseDaoImpl<Tender, Long> implements TenderDao{
	
}
