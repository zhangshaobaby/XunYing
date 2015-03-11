package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.RepaymentSendDao;
import com.zc.bsm.pojo.RepaymentSend;

@Repository("repaymentSendDao")
public class RepaymentSendDaoImpl extends BaseDaoImpl<RepaymentSend, Long> implements RepaymentSendDao{
	
}
