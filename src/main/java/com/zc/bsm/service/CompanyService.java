package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.pojo.CompanyAttachment;
import com.zc.bsm.pojo.Project;
public interface CompanyService extends BaseService<Company, Long>{	
	public Page findByPage(Page page,Company company);
	public void deleteAll(String ids);
    public List<Company> findXt();
    public List<Company> findDb();
}
