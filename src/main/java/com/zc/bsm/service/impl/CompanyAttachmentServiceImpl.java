package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.bsm.dao.CompanyAttachmentDao;
import com.zc.bsm.pojo.CompanyAttachment;
import com.zc.bsm.service.CompanyAttachmentService;


@Service("companyAttachmentService")
public class CompanyAttachmentServiceImpl extends BaseServiceImpl<CompanyAttachment, Long> implements CompanyAttachmentService{
	
	@Autowired
	public void setBaseDao(CompanyAttachmentDao companyAttachmentDao) {
		this.baseDao = companyAttachmentDao;
	}
	public List<CompanyAttachment> findAllCid(Long cid){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from CompanyAttachment where flag = 0 and cid = ?");
		param.add(cid);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public void deleteAllCid(Long cid){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("update CompanyAttachment set flag = 1 where cid = ?");
		param.add(cid);
		//缺省是否删除图片源文件
		this.baseDao.update(hql.toString(), param.toArray());
	}
}
