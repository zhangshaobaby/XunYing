package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.RechargeOrderDao;
import com.zc.bsm.pojo.RechargeOrder;
@Repository("rechargeOrderDao")
public class RechargeOrderDaoImpl extends BaseDaoImpl<RechargeOrder,String> implements RechargeOrderDao{

}
