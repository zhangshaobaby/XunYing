package com.zc.bsm.service;

import java.util.List;

import com.zc.base.po.Dict;
import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.User;

public interface DictService extends BaseService<Dict,Integer> {
	public  List<Dict> findByDictype(int dicttype);
	public Dict findByDictId(String dictid);
}
