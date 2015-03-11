package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.CompanyAttachmentDao;
import com.zc.bsm.pojo.CompanyAttachment;

@Repository("companyAttachmentDao")
public class CompanyAttachmentDaoImpl extends BaseDaoImpl<CompanyAttachment, Long> implements CompanyAttachmentDao{
	
}
