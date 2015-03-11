package com.zc.bsm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.NewsDao;
import com.zc.bsm.pojo.News;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.service.NewsService;


@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl<News, Long> implements NewsService{
	
	@Autowired
	public void setBaseDao(NewsDao advertDao) {
		this.baseDao = advertDao;
	}
	public Page findPageBySelf(Page page,News news){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from News where flag = 0 ");
		if(news.getType()!=null){
			hql.append(" and type = ? ");
			param.add(news.getType());
		}
		if(news.getState()!=null){
			hql.append(" and state = ? ");
			param.add(news.getState());
			hql.append("and now() > start_time ");
		}
		hql.append("order by createTime desc");
		page.setHql(hql.toString());
		page = this.baseDao.findByPage(page, param.toArray());
		return page;
	}
	public void stateChange(Long id,Integer state){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("update News set state = ? where id = ? ");
		param.add(state);
		param.add(id);
		this.baseDao.update(hql.toString(), param.toArray());
	}
	public List<News> findByType(Integer type,Integer length){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from News where type = ? and flag = 0 and state=1 and  NOW() > start_time order by orderTag,createTime desc ");
		param.add(type);
		Page page = new Page();
		page.setRows(length);
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray()).getResult();
	}
}
