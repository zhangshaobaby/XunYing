package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.ProjectDao;
import com.zc.bsm.pojo.Project;

@Repository("projectDao")
public class ProjectDaoImpl extends BaseDaoImpl<Project, Long> implements ProjectDao{
	
}
