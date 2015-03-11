package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.LoansSendDao;
import com.zc.bsm.pojo.LoansSend;
import com.zc.bsm.service.LoansSendService;


@Service("loansSendService")
public class LoansSendServiceImpl extends BaseServiceImpl<LoansSend, Long> implements LoansSendService{
	
	@Autowired
	public void setBaseDao(LoansSendDao loansSendDao) {
		this.baseDao = loansSendDao;
	}
	public Page findByPage(Page page,LoansSend loansSend){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from LoansSend where flag = 0");
		if(loansSend!=null){
			//
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public LoansSend findBySelf(LoansSend cashStream){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from LoansSend where flag = 0");
		if(cashStream!=null){
			if(cashStream.getId()!=null&&cashStream.getId()!=0){
				hql.append(" and id = ?");
				param.add(cashStream.getId());
			}
			if(cashStream.getOrdId()!=null&&!cashStream.getOrdId().equals("")){
				hql.append(" and ordId = ?");
				param.add(cashStream.getOrdId());
			}
		}
		List<LoansSend> list = this.baseDao.find(hql.toString(),param.toArray());
		return list.size()==1?list.get(0):null;
	}
	public List<LoansSend> checkLoans(Long pid){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select l from LoansSend as l ,Tender as t where t.id = l.ordId and t.project.id = ? and l.state = 0");
		param.add(pid);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
}
