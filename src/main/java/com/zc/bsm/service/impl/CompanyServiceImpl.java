package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.CompanyDao;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.service.CompanyService;


@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company, Long> implements CompanyService{
	
	@Autowired
	public void setBaseDao(CompanyDao companyDao) {
		this.baseDao = companyDao;
	}
	public void deleteAll(String id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Company set flag = 1 where id in ("+id+")");
		this.baseDao.update(hql.toString(),null);
	}

    public List<Company> findXt() {
        return this.findByType(1);
    }

    public List<Company> findDb() {
        return this.findByType(0);
    }

    public List<Company> findByType(Integer type){
        StringBuilder hql = new StringBuilder();
        hql.append("from Company where flag = 0 and type = ?");
        return this.baseDao.find(hql.toString(),type);
    }


    public Page findByPage(Page page,Company company){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Company where flag = 0");
		if(company!=null){
			if(company.getType()!=null&&!company.getType().equals("")){
				hql.append(" and type = ?");
				param.add(company.getType());
			}
			if(company.getCompany_name()!=null&&!company.getCompany_name().equals("")){
				hql.append(" and company_name like ?");
				param.add("%"+company.getCompany_name()+"%");
			}
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
}
