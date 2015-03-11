package com.zc.abf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.abf.dao.impl.RoleDaoImpl;
import com.zc.abf.pojo.Menu;
import com.zc.abf.pojo.Operator;
import com.zc.abf.pojo.Role;
import com.zc.abf.service.OperatorService;
import com.zc.abf.service.RoleService;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.JsonTreeCheckedNode;
import com.zc.base.util.JsonTreeNode;

@Service("RoleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService{
	@Autowired
	private OperatorService operatorService;
	@Autowired
	public void setBaseDao(RoleDaoImpl RoleDao) {
		this.baseDao = RoleDao;
	}
//将集合转化成JsonTreeCheckedNode 只赋值id ,text leaf属性
	public List<JsonTreeCheckedNode> getJsonTreeCheckNode(List<Role> roleList) {
		List<JsonTreeCheckedNode> listNode=new ArrayList<JsonTreeCheckedNode>();
		for(Role role:roleList){
			JsonTreeCheckedNode node=new JsonTreeCheckedNode();
			node.setId(role.getId());
			node.setText(role.getRoleName());
			node.setLeaf(true);
			listNode.add(node);	
		}
		return listNode;
	}
	//根据业务需要 需要根据操作者是否拥有该角色 决定是否需要回填
	 public List<JsonTreeCheckedNode> getJsonTreeCheckNode(List<JsonTreeCheckedNode> checkedNoeList,Operator oper){
		if(oper!=null&&oper.getId()!=null&&!oper.getId().equals("")){
		oper=this.operatorService.get(oper.getId());
		}
		 if(oper.getRoles()!=null&&oper.getRoles().size()>0){
			List<Role> rolelist=oper.getRoles();
			//外层循环操作者角色 ，可以提升效率
				for(Role operrole: rolelist){		
					for(JsonTreeCheckedNode node:checkedNoeList){
					if(node.getId().equals(operrole.getId())){
						node.setChecked(true);
						break;
					}
				}
			}
			
		}
		return checkedNoeList;
	}

	public List<JsonTreeCheckedNode> getRoleJsonTreeNode(Operator oper) {
		 List<Role> roleList=this.loadAll(); 
		 List<JsonTreeCheckedNode>  checkedNoeList=this.getJsonTreeCheckNode(roleList);
		 return getJsonTreeCheckNode(checkedNoeList, oper);
	}
	
}
