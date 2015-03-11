package com.zc.abf.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.abf.dao.impl.RoleMenuDaoImpl;
import com.zc.abf.pojo.RoleMenu;
import com.zc.abf.service.OperatorService;
import com.zc.abf.service.RoleMenuService;
import com.zc.base.service.BaseServiceImpl;


@Service("RoleMenuService")
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu, String> implements RoleMenuService{
	@Autowired
	public void setBaseDao(RoleMenuDaoImpl RoleMenuDao) {
		this.baseDao = RoleMenuDao;
	}
}
