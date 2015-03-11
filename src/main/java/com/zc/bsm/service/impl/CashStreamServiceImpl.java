package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.CashStreamDao;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.service.CashStreamService;


@Service("cashStreamService")
public class CashStreamServiceImpl extends BaseServiceImpl<CashStream, Long> implements CashStreamService{
	
	@Autowired
	public void setBaseDao(CashStreamDao advertDao) {
		this.baseDao = advertDao;
	}
	public Page findByPage(Page page,CashStream cashStream){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Advert where flag = 0");
		if(cashStream!=null){
			//
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public CashStream findBySelf(CashStream cashStream){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from CashStream where flag = 0");
		if(cashStream!=null){
			if(cashStream.getId()!=null&&cashStream.getId()!=0){
				hql.append(" and id = ?");
				param.add(cashStream.getId());
			}
			if(cashStream.getOrdId()!=null&&!cashStream.getOrdId().equals("")){
				hql.append(" and ordId = ?");
				param.add(cashStream.getOrdId());
			}
			if(cashStream.getType()!=null){
				hql.append(" and type = ?");
				param.add(cashStream.getType());
			}
		}
		List<CashStream> list = this.baseDao.find(hql.toString(),param.toArray());
		return list.size()==1?list.get(0):null;
	}
	public List<CashStream> checkLoans(Long pid){
		List result = new ArrayList();
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select count(a.*) from CashStream as a , Tender as b where a.ordId  = b.id and b.projectId = ? ");
		param.add(pid);
		List list = this.baseDao.findByHql(hql.toString(), param.toArray());
		//总数
		//result.add();
		
		//未完成数
		hql.append(" and a.state is NULL");
		result.add(this.baseDao.findByHql(hql.toString(), param.toArray()).get(0));
		
		return result;
	}
}
