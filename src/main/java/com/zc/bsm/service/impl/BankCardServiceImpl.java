package com.zc.bsm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.BankCardDao;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.pojo.Bankcard;
import com.zc.bsm.service.BankCardService;


@Service("bankcardService")
public class BankCardServiceImpl extends BaseServiceImpl<Bankcard, String> implements BankCardService{
	
	@Autowired
	public void setBaseDao(BankCardDao advertDao) {
		this.baseDao = advertDao;
	}
	
}
