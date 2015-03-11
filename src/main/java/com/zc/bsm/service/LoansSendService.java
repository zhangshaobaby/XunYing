package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.LoansSend;
public interface LoansSendService extends BaseService<LoansSend, Long>{	
	public Page findByPage(Page page,LoansSend loansSend);
	public LoansSend findBySelf(LoansSend loansSend);
	public List<LoansSend> checkLoans(Long pid);
}