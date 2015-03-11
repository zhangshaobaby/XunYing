package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.RepaymentPlanVerifyDao;
import com.zc.bsm.pojo.RepaymentPlanVerify;
import com.zc.bsm.service.RepaymentPlanVerifyService;


@Service("repaymentPlanVerifyService")
public class RepaymentPlanVerifyServiceImpl extends BaseServiceImpl<RepaymentPlanVerify, Long> implements RepaymentPlanVerifyService{
	
	@Autowired
	public void setBaseDao(RepaymentPlanVerifyDao advertDao) {
		this.baseDao = advertDao;
	}
	public Page findByPage(Page page,RepaymentPlanVerify verify){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		//
		hql.append("select new List(p.name,r.repayCount,r.repayMoney,r.repayTime,r.state,r.id,r.updateTime,r.repayCount,p.id,'') from RepaymentPlanVerify as r ,Project as p");
		hql.append(" where p.id = r.projectId and r.flag = 0 ");
		if(verify!=null){
			//
			if(verify.getState()!=null){
				hql.append(" and state = ?");
				param.add(verify.getState());
			}
		}
		hql.append("order by r.updateTime desc");
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public void changeState(Long id,Integer state,String flag){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update RepaymentPlanVerify set state = ?,flag=? where id = ?");
		param.add(state);
		param.add(flag);
		param.add(id);
		this.baseDao.update(hql.toString(), param.toArray());
	}
	public List<RepaymentPlanVerify> findAllByPid(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from RepaymentPlanVerify where projectId = ? and flag = 0  order by updateTime desc ");
		
		param.add(id);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public void deletebyprojectid(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("delete from  RepaymentPlanVerify where projectId = ? ");
		param.add(id);
		 this.baseDao.update(hql.toString(), param.toArray());		
	}
	public Boolean checkIdentity(RepaymentPlanVerify verify){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from  RepaymentPlanVerify where projectId = ? and repayCount = ? and repayTime = ? and repayMoney = ? and flag = 0 and state = 0");
		param.add(verify.getProjectId());
		param.add(verify.getRepayCount());
		param.add(verify.getRepayTime());
		param.add(verify.getRepayMoney());
		List list = this.baseDao.find(hql.toString(), param.toArray());
		return list!=null&&list.size()>0?false:true;
	}
}
