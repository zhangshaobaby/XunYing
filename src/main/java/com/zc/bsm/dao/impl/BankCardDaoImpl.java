package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.BankCardDao;
import com.zc.bsm.pojo.Bankcard;

@Repository("bankcardDao")
public class BankCardDaoImpl extends BaseDaoImpl<Bankcard, String> implements BankCardDao{
	
}
