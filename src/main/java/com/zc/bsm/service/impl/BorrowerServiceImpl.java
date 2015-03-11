package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.BorrowerDao;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;


@Service("borrowerService")
public class BorrowerServiceImpl extends BaseServiceImpl<Borrower, Long> implements BorrowerService{
	
	@Autowired
	public void setBaseDao(BorrowerDao borrowerDao) {
		this.baseDao = borrowerDao;
	}
	public Page findByPage(Page page,Borrower borrower){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Borrower where flag = 0");
		if(borrower!=null){
			//
			if(borrower.getType()!=null){
				hql.append(" and type = ?");
				param.add(borrower.getType());
			}
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public void changeState(Long id,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Borrower set state = ? where id = ?");
		param.add(state);
		param.add(id);
		this.baseDao.update(hql.toString(), param.toArray());
	}
	public List<Borrower> findBy(Borrower borrower){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Borrower where flag = 0");
		if(borrower.getName()!=null&&!borrower.getName().equals("")){
			hql.append(" and name = ?");
			param.add(borrower.getName());
		}
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public void deleteByUid(String uid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Borrower set flag = 1 where uid = ?");
		param.add(uid);
		this.baseDao.update(hql.toString(), param.toArray());
	}
	public List<Borrower> findByType(Integer type){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Borrower where flag = 0 and type = ? and usrCustId<>null");
		param.add(type);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public Borrower findByUsrCustId(String usrCustId) {
		try {
			List<Borrower> list = this.find("from Borrower where usrCustId=?", usrCustId);
			return list.size()==1?list.get(0):null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
