package com.zc.bsm.service;

import com.zc.base.service.BaseService;

import com.zc.bsm.pojo.CreditAssignTender;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.vo.CreditAssign;
import com.zc.bsm.vo.returnVo.AutoCreditAssignReturn;

public interface CreditAssignService extends BaseService<CreditAssignTender,Long>{
	public CreditAssign creditAssign(CreditAssignTender tender) throws Exception;
	public void creditAssignReturn(AutoCreditAssignReturn _return) throws Exception;
    public  CreditAssignTender	addCreditAssign(CreditAssignTender tender,Long creditAssignid,User user);
    public String  getCreditMoney(String sellCustId, Long projectid);
}
