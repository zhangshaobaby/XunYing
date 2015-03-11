package com.zc.bsm.service;
import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.DynamicRate;
public interface DynamicRateService extends BaseService<DynamicRate, Long>{	
	public Page findByPage(Page page,String time1,String time2);
}
