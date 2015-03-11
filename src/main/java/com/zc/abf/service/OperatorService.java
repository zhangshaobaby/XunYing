package com.zc.abf.service;

import java.util.List;

import com.zc.abf.pojo.Operator;
import com.zc.base.service.BaseService;
import com.zc.base.util.JsonTreeNode;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.User;

public interface OperatorService extends BaseService<Operator, String>{
	// md5加密
	public String md5(String str);
	public Operator login(String username,String password);
	//获取人员角色的权限
	public String getMenusFromUser(Operator oper);
}
