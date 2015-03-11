package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.RepaymentPlan;
public interface RepaymentPlanService extends BaseService<RepaymentPlan, Long>{	
	public List<RepaymentPlan> findByPidRepayCount(Long pid,Integer repayCount);
	public List findByPid(Long pid,Integer state);
	public List findByPid(Long pid,Integer state,Integer repayCount,String repayTime);
	public List findSumByPid(Long pid,Integer state,Integer repayCount,String repayTime);
	public List<RepaymentPlan> checkRepayment(Long id,Integer repayCount,String repayTime);
	public int deleteByPId(Long id);
	public List checkCount(Long id);
	public List checkCount(Long id,Integer repayCount,Integer state,String repayTime);
	public List record(Long pid,String userCustId);
//	public String repayEarlyReward(Long pid,String userCustId) throws Exception;
	public String projectRepay(Long pid,String userCustId) throws Exception;
	public String repayEarly(Long pid,String userCustId) throws Exception;
	public int ignore(Long pid,Integer repayCount,String repayTime);
	public boolean overPlan(Long pid);
	public List<RepaymentPlan> checkRepaymentPlan(Long pid);
	public Integer newRepayCount(Long id,String repayTime);
	public int selectCountByPId(Long id);
	public String findLastRepayTime(String usrCustId,Long pid);
	public String findLastedRepayTime(String usrCustId,Long pid);
	public String findLastedRepayTimes(String usrCustId,Long pid);
	public List<Object> repayEarlyRewardformobile(Long pid,String userCustId);
}
