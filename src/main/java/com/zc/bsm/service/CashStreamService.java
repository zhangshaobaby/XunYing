package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.pojo.CashStream;
public interface CashStreamService extends BaseService<CashStream, Long>{	
	public Page findByPage(Page page,CashStream cashStream);
	public CashStream findBySelf(CashStream cashStream);
	public List<CashStream> checkLoans(Long pid);
}
