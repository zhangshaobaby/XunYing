package com.zc.bsm.service;
import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.User;
import com.zc.bsm.vo.returnVo.MerCashReturn;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;


public interface EnchashmentService extends BaseService<Enchashment,Long> {
	public QueryBalanceBgReturn getBalanceObj(User   user);
	public QueryBalanceBgReturn getBalanceObj(String    usrCustId);
	public boolean printMercashResult(MerCashReturn cashReturn);
	//同接口公用方法开始
	public String cash(String transAmt,String usrCustId,String bankcardid,String servFee);
}
