package com.zc.bsm.service;
import java.util.List;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.News;
import com.zc.bsm.pojo.Project;
public interface NewsService extends BaseService<News, Long>{	
	public Page findPageBySelf(Page page,News news);
	public void stateChange(Long id,Integer state);
	public List<News> findByType(Integer type,Integer length);
}
