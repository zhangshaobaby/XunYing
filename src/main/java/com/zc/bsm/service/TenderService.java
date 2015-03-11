package com.zc.bsm.service;
import java.util.List;
import java.util.Map;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.vo.returnVo.InitiativeTenderReturn;
public interface TenderService extends BaseService<Tender, Long>{	
	public List findByPid(Long pid);
	public List findByPid(Long pid,Integer state);
	public Page findPageByPid(Long pid,Page page);
	public List checkLoans(Long pid);
	public List checkLoans(Long pid,Integer state);
	public List checkLoansCount(Long pid,Integer state);
	public Page record(Page page,String usrCustId,Integer flag,Integer type,String status);
	public Page recorddiaosi(Page page,String usrCustId,Integer flag,Integer type);
	public List loansSum(String usrCustId,Long projectId);
	public List selfCondition(String usrCustId,Long projectId);
	public void doTenderState(Freeze  en) throws Exception;
	//处理投标之后的后续操作 因为涉及到session关闭问题 所以必须写在service
	public boolean InitiativeTenderResult(InitiativeTenderReturn InitiativeTenderReturn ) throws Exception;
	public List findSumTransmat(Long pid,String usrCustId);
	public Page tenderDetail(Long id,Page page);
	public Map recordNum(User user);
	//终端方法
//	public Page recordInterface(Page page,String usrCustId,Integer flag,Integer type);
	public void tmpTenderResult(String orderid);
}
