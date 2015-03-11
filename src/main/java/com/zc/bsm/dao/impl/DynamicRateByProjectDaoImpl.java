package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.DynamicRateByProjectDao;
import com.zc.bsm.pojo.DynamicRateByProject;

@Repository("dynamicRateByProjectDao")
public class DynamicRateByProjectDaoImpl extends BaseDaoImpl<DynamicRateByProject, Long> implements DynamicRateByProjectDao{
	
}
