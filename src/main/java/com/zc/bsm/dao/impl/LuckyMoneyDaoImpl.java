package com.zc.bsm.dao.impl;

import org.springframework.stereotype.Repository;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.bsm.dao.LuckyMoneyDao;
import com.zc.bsm.pojo.LuckyMoney;

@Repository("luckyMoneyDao")
public class LuckyMoneyDaoImpl extends BaseDaoImpl<LuckyMoney, Long> implements LuckyMoneyDao{
	
}
