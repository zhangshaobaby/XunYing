package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.DynamicRateByProject;
import com.zc.bsm.pojo.User;
public interface DynamicRateByProjectService extends BaseService<DynamicRateByProject, Long>{	
	public String repayEarly(User user ,Long projectId);
	public List getListById(Long pid);
	public List<String> repayEarlyReward(User user ,Long projectId);
	public String cancelRepayEarly(User user,Long projectId);
}
