package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.LoansSendDao;
import com.zc.bsm.pojo.LoansSend;

@Repository("loansSendDao")
public class LoansSendDaoImpl extends BaseDaoImpl<LoansSend, Long> implements LoansSendDao{
	
}
