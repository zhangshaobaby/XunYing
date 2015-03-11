package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Advert;
public interface AdvertService extends BaseService<Advert, Long>{	
	public Page findByPage(Page page,Advert advert);
	public List<Advert> mainPageAdvert();
	public void changeState(Long id,Integer state);
}
