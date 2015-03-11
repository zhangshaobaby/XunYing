package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.pojo.Borrower;
public interface BorrowerService extends BaseService<Borrower, Long>{	
	public Page findByPage(Page page,Borrower borrower);
	public void changeState(Long id,Integer state);
	public List<Borrower> findBy(Borrower borrower);
	public void deleteByUid(String uid);
	public List<Borrower> findByType(Integer type);
	public Borrower findByUsrCustId( String usrCustId);
}
