package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.base.po.Dict;
import com.zc.bsm.dao.DictDao;
import com.zc.bsm.dao.UserDao;
import com.zc.bsm.pojo.User;
@Repository("dictDao")
public class DictDaoImpl extends BaseDaoImpl<Dict,Integer> implements DictDao{

}
