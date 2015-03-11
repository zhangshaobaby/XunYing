package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.UserDao;
import com.zc.bsm.pojo.User;
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User,String> implements UserDao{

}
