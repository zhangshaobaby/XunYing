package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.RepaymentPlanVerify;
public interface RepaymentPlanVerifyService extends BaseService<RepaymentPlanVerify, Long>{	
	public Page findByPage(Page page,RepaymentPlanVerify advert);
	public void changeState(Long id,Integer state,String flag);
	public List<RepaymentPlanVerify> findAllByPid(Long id);
	public void deletebyprojectid(Long id);
	public Boolean checkIdentity(RepaymentPlanVerify verify);
}
