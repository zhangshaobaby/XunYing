package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.BorrowerDao;
import com.zc.bsm.pojo.Borrower;

@Repository("borrowerDao")
public class BorrowerDaoImpl extends BaseDaoImpl<Borrower, Long> implements BorrowerDao{
	
}
