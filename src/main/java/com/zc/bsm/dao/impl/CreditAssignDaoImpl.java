package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.CreditAssignDao;
import com.zc.bsm.pojo.CreditAssignTender;

/**
 * 债权转让
 * @author Administrator
 *
 */
@Repository("creditAssignDao")
public class CreditAssignDaoImpl  extends BaseDaoImpl<CreditAssignTender, Long> implements CreditAssignDao{

}
