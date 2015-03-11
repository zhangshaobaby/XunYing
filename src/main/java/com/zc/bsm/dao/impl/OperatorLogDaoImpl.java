package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.OperatorLogDao;
import com.zc.bsm.pojo.OperatorLog;

@Repository("operatorLogDao")
public class OperatorLogDaoImpl extends BaseDaoImpl<OperatorLog, Long> implements OperatorLogDao{
	
}
