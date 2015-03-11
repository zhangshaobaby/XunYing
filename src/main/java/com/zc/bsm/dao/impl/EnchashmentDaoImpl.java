package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.EnchashmentDao;
import com.zc.bsm.dao.UserDao;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.User;
@Repository("enchashment")
public class EnchashmentDaoImpl extends BaseDaoImpl<Enchashment,Long> implements EnchashmentDao{

}
