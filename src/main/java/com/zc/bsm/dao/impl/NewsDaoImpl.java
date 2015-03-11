package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.NewsDao;
import com.zc.bsm.pojo.News;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDaoImpl<News, Long> implements NewsDao{
	
}
