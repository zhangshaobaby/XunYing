package com.zc.abf.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.zc.abf.pojo.Operator;
import com.zc.abf.pojo.Role;
import com.zc.abf.service.RoleService;
import com.zc.base.action.Action;
import com.zc.base.util.JsonTreeCheckedNode;

import com.zc.bsm.service.bizDataService;
@Controller
@RequestMapping("/auth/role")
@Scope("prototype")
public class RoleAction extends Action{
@Autowired
private  bizDataService bizdataservice;
	
 @Autowired
 private RoleService RoleService;

 @RequestMapping(value="/getRoleJsonTreeNode")
 @ResponseBody
 public List<JsonTreeCheckedNode>  getRoleJsonTreeNode(Operator oper){
	 try {
		return RoleService.getRoleJsonTreeNode(oper);
	} catch (Exception e) {
		e.printStackTrace();
		}
    return null;
 }
 
 @RequestMapping(value="/listRole")
 public String listRole(ModelMap map){
	 list=this.RoleService.loadAll();
	 map.put("list", list);
	 return "role/list";
 }
 @RequestMapping(value="/saveRole")
 @ResponseBody
 public  String saveRole(Role Role){
	 try {
		 this.bizdataservice.bizSave(Role.class, Role);
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
		return "suceess";
 }
 @RequestMapping(value="/editRole")
 public String editRole(Role Role,ModelMap map){
	 try {
		 Object persistencebject=null;
		 if(Role!=null&&Role.getId()!=null&&!Role.getId().equals("")){
			 persistencebject=RoleService.get(Role.getId());
	 	 }
	    Object newRole= bizdataservice.getPersistenceObj(Role.class, persistencebject, Role);
	    map.put("role", newRole);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "role/edit";
 }
 @RequestMapping(value="/selectRole")
 public String roleTree(Operator oper,ModelMap map){
	 map.put("oper",oper);
	 return "role/tree";
 }
 @RequestMapping(value="/delRole")
 @ResponseBody
 public boolean delRole(String ids) {
	 return  this.bizdataservice.bizDel("com.zc.abf.pojo.Role", ids);
 };
public RoleService getRoleService() {
	return RoleService;
}

public void setRoleService(RoleService RoleService) {
	this.RoleService = RoleService;
}

}
