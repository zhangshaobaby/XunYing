package com.zc.base.util.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.po.Dict;
import com.zc.bsm.service.bizDataService;
@Component(value = "countDictJob")
public class CountDictJob  implements ICountDictJob{
	@Autowired
	private bizDataService bizdataservice;
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public boolean dojob() throws Exception{
			 String totalusersql="select count(id) from User";
		     Object totalusercount=bizdataservice.getRowCount(totalusersql);
			 int  totalusercountint= 10000+  Integer.parseInt(totalusercount.toString());
			 Dict totaluserdict=new Dict(45);
			 totaluserdict.setRemark(String.valueOf(totalusercountint));
			 this.bizdataservice.bizSave(Dict.class, totaluserdict);
			 String totalinvestsql="select count(transAmt)  from Tender as e where flag=?";
		     Object totalinvestcount=bizdataservice.getRowCount(totalinvestsql,0);
		     Double    totalinvestcountint= 10000.00+  Double.parseDouble(totalinvestcount.toString());
			 
		     Dict totalinvestdict=new Dict(46);
		     totalinvestdict.setRemark(String.valueOf(totalinvestcountint));
			 this.bizdataservice.bizSave(Dict.class, totalinvestdict);
			 
		     String totalbenefitsql="select count(repayMoney)  from RepaymentPlan as e  where   e.state=? or e.state=?";
		     Object totalbenefitcount=bizdataservice.getRowCount(totalbenefitsql,0,1);
		     Double    totalbenefitcountint= 10000.00+  Double.parseDouble(totalbenefitcount.toString());
		     
		     Dict  totalbenefitdict=new Dict(47);
		     totalbenefitdict.setRemark(String.valueOf(totalbenefitcountint));
			 this.bizdataservice.bizSave(Dict.class, totalbenefitdict);
		     return true;
	}

}
