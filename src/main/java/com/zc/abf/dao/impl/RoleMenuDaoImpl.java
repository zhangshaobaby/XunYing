package com.zc.abf.dao.impl;
import org.springframework.stereotype.Repository;

import com.zc.abf.dao.RoleMenuDao;
import com.zc.abf.pojo.RoleMenu;
import com.zc.base.dao.BaseDaoImpl;
@Repository("RoleMenuDao")
public class RoleMenuDaoImpl extends BaseDaoImpl<RoleMenu, String> implements RoleMenuDao{
}
