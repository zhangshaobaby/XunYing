package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.RepaymentSend;
public interface RepaymentSendService extends BaseService<RepaymentSend, Long>{	
	public Page findByPage(Page page,RepaymentSend loansSend);
	public RepaymentSend findBySelf(RepaymentSend loansSend);
	public List<RepaymentSend> checkLoans(Long pid);
	public List repaySum(String userCustId,Long pid);
}