package com.zc.bsm.service;


import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Activity_vote_persion_infor;

public interface ActivityVotePersionService extends BaseService<Activity_vote_persion_infor,Long> {
	public Page findByPage(Page page);
	public boolean updateflag(String ids);
	public List<Activity_vote_persion_infor> find(String orderby);
}
