package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.RepaymentPlanDao;
import com.zc.bsm.pojo.RepaymentPlan;

@Repository("repaymentPlanDao")
public class RepaymentPlanDaoImpl extends BaseDaoImpl<RepaymentPlan,Long> implements RepaymentPlanDao{
	
}
