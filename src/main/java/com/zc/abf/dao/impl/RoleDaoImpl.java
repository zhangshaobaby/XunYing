package com.zc.abf.dao.impl;
import org.springframework.stereotype.Repository;

import com.zc.abf.dao.RoleDao;
import com.zc.abf.pojo.Role;
import com.zc.base.dao.BaseDaoImpl;
@Repository("RoleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role, String> implements RoleDao{
}
