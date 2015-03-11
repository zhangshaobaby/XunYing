package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.DynamicRateDao;
import com.zc.bsm.pojo.DynamicRate;

@Repository("dynamicRateDao")
public class DynamicRateDaoImpl extends BaseDaoImpl<DynamicRate, Long> implements DynamicRateDao{
	
}
