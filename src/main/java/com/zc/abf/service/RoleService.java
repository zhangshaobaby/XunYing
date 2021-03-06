package com.zc.abf.service;
import java.util.List;

import com.zc.abf.pojo.Operator;
import com.zc.abf.pojo.Role;
import com.zc.base.service.BaseService;
import com.zc.base.util.JsonTreeCheckedNode;


public interface RoleService extends BaseService<Role, String>{
  public List<JsonTreeCheckedNode> getRoleJsonTreeNode(Operator oper);
	
}
