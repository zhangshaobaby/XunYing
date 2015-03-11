package com.zc.bsm.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.ActivityVotePersionDao;
import com.zc.bsm.pojo.Activity_vote_persion_infor;
import com.zc.bsm.service.ActivityVotePersionService;
@Service("activityVotePersionService")
public class ActivityVotePersionServiceImpl extends BaseServiceImpl<Activity_vote_persion_infor, Long> implements ActivityVotePersionService {
	protected Log logger = LogFactory.getLog(ActivityVotePersionServiceImpl.class);
	@Autowired
	public void setBaseDao(ActivityVotePersionDao activityVotePersionDao) {
		this.baseDao = activityVotePersionDao;
	}
	public Page findByPage(Page page){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Activity_vote_persion_infor where flag = 1");
		hql.append(" order by id desc ");
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public boolean updateflag(String ids) {
		if(ids==null||ids.equals("")){
    		return false;
    	}
		String [] idstring=ids.split(",");
    	for(String id:idstring){
    		Activity_vote_persion_infor obj=(Activity_vote_persion_infor)this.findById("com.zc.bsm.pojo.Activity_vote_persion_infor", Long.parseLong(id));
    	   if(obj!=null){
    		   obj.setFlag("0");
    		   this.update(obj);
    	   }
    	}
    	return true;
	}
	
	public List<Activity_vote_persion_infor> find(String orderby){
		StringBuilder hql = new StringBuilder();
		hql.append("from Activity_vote_persion_infor where flag = 1");
		if(!orderby.equals(""))
		{
			hql.append(" order by totalvote desc ");
		}
		else 
		{
			hql.append(" order by id desc ");
		}
		List<Activity_vote_persion_infor> l=this.baseDao.find(hql.toString());
		return l;
	}
	
	
}
