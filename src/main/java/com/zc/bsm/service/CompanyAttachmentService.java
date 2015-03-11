package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.CompanyAttachment;
public interface CompanyAttachmentService extends BaseService<CompanyAttachment, Long>{	
	public void deleteAllCid(Long cid);
	public List<CompanyAttachment> findAllCid(Long cid);
}
