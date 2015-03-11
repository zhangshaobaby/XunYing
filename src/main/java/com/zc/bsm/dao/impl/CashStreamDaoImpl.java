package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.CashStreamDao;
import com.zc.bsm.pojo.CashStream;

@Repository("cashStreamDao")
public class CashStreamDaoImpl extends BaseDaoImpl<CashStream, Long> implements CashStreamDao{
	
}
