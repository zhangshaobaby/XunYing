package com.zc.abf.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.abf.pojo.RoleMenu;
import com.zc.abf.service.RoleMenuService;
import com.zc.base.action.Action;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.service.impl.bizDataServiceImpl;
@Controller
@RequestMapping("/auth/roleMenu")
@Scope("prototype")
public class RoleMenuAction extends Action{
	protected static final Log logger = LogFactory.getLog(RoleMenuAction.class);
	@Autowired
	private RoleMenuService roleMenuService;
  @Autowired
  private  bizDataService bizdataservice;
  @RequestMapping(value="/saveRoleMenu")
  @ResponseBody
  public String   saveRoleMenu(RoleMenu roleMenu){
	  try { 
			 this.bizdataservice.bizSave(RoleMenu.class, roleMenu);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
			return "suceess";
  }
  @RequestMapping(value="/delRoleMenu")
  @ResponseBody
  public boolean delRoleMenu(RoleMenu roleMenu) {
	  if(roleMenu.getMenuid()==null||roleMenu.getRoleid()==null){
		  logger.error("参数缺失");
		  return false;
	  }
	  List<RoleMenu>  list=this.roleMenuService.find("from RoleMenu where menuid=? and roleid=?",roleMenu.getMenuid(),roleMenu.getRoleid());
 	  if(list!=null&&list.size()>0){
 		  for(RoleMenu rolemenu: list){
 			 this.roleMenuService.delete(rolemenu);  
 		  }
 	  }
	  return true;
  };
}
