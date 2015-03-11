package com.zc.abf.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zc.abf.dao.MenuDao;
import com.zc.abf.pojo.Menu;
import com.zc.base.dao.BaseDaoImpl;
@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<Menu, String> implements MenuDao{
}
