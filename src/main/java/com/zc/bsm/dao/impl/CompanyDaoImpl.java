package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.CompanyDao;
import com.zc.bsm.pojo.Company;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company, Long> implements CompanyDao{
	
}
