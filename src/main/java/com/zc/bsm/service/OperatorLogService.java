package com.zc.bsm.service;
import com.zc.abf.pojo.Operator;
import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.OperatorLog;
public interface OperatorLogService extends BaseService<OperatorLog, Long>{	
	public void saveLog(Operator operator,Object obj,String behavior);
}
