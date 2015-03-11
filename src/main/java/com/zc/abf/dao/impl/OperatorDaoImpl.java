package com.zc.abf.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zc.abf.dao.OperatorDao;
import com.zc.abf.pojo.Operator;
import com.zc.base.dao.BaseDaoImpl;
@Repository("OperatorDao")
public class OperatorDaoImpl extends BaseDaoImpl<Operator, String> implements OperatorDao{
}
