package com.zc.bsm.service;
import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.vo.returnVo.CorpRegisterReturn;
import com.zc.bsm.vo.returnVo.LoansReturn;
import com.zc.bsm.vo.returnVo.QueryTransStatReturn;
import com.zc.bsm.vo.returnVo.RepaymentReturn;
public interface CallBackService extends BaseService<Advert, Long>{	
	public void Loans(LoansReturn _return);
	public void LoansTransState(QueryTransStatReturn _return);
	public void printRepayment(RepaymentReturn _return);
	public void printRepaymentTransState(QueryTransStatReturn _return);
	public void registerBg(CorpRegisterReturn cr);
}
