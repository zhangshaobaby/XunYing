package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.RepaymentSendDao;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.service.RepaymentSendService;


@Service("repaymentSendService")
public class RepaymentSendServiceImpl extends BaseServiceImpl<RepaymentSend, Long> implements RepaymentSendService{
	
	@Autowired
	public void setBaseDao(RepaymentSendDao repaymentSendDao) {
		this.baseDao = repaymentSendDao;
	}
	public Page findByPage(Page page,RepaymentSend repaymentSend){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from RepaymentSend where flag = 0 and state = 1");
		if(repaymentSend!=null){
			if(repaymentSend.getProjectId()!=null&&repaymentSend.getProjectId()!=0){
				hql.append(" and projectId = ? ");
				param.add(repaymentSend.getProjectId());
			}
			if(repaymentSend.getInCustId()!=null&&!repaymentSend.getInCustId().trim().equals("")){
				hql.append(" and inCustId = ? ");
				param.add(repaymentSend.getInCustId());
			}
			hql.append(" order by ordDate desc");
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public RepaymentSend findBySelf(RepaymentSend repaymentSend){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from RepaymentSend where flag = 0");
		if(repaymentSend!=null){
			if(repaymentSend.getId()!=null&&repaymentSend.getId()!=0){
				hql.append(" and id = ?");
				param.add(repaymentSend.getId());
			}
			if(repaymentSend.getOrdId()!=null&&!repaymentSend.getOrdId().equals("")){
				hql.append(" and ordId = ?");
				param.add(repaymentSend.getOrdId());
			}
		}
		List<RepaymentSend> list = this.baseDao.find(hql.toString(),param.toArray());
		return list.size()==1?list.get(0):null;
	}
	public List<RepaymentSend> checkLoans(Long pid){
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
	public List repaySum(String userCustId,Long pid){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select sum(transAmt) from RepaymentSend where projectId  = ? and inCustId = ? and flag = 0 and state = 1 GROUP BY projectId");
		param.add(pid);
		param.add(userCustId);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
}
