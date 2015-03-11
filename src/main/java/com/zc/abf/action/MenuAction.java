package com.zc.abf.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.abf.pojo.Menu;
import com.zc.abf.pojo.Role;
import com.zc.abf.service.MenuService;
import com.zc.base.action.Action;
import com.zc.base.util.JsonTreeCheckedNode;
import com.zc.base.util.JsonTreeNode;
import com.zc.base.util.Page;
import com.zc.bsm.service.bizDataService;
@Controller
@RequestMapping("/auth/menu")
@Scope("prototype")
public class MenuAction extends Action{
@Autowired
private  bizDataService bizdataservice;
	
 @Autowired
 private MenuService menuService;
 //不带复选框的树
 @RequestMapping(value="/getMenuJsonTreeNode")
 @ResponseBody
 public List<JsonTreeNode>  getMenuJsonTreeNode( Menu parentMenu){
	 try {
		return menuService.getMenuTreeNode(parentMenu);
	} catch (Exception e) {
		e.printStackTrace();
		}
    return null;
 }
 
 //带复选框的树
 @RequestMapping(value="/getMenuJsonTreeCheckedNode")
 @ResponseBody
 public List<JsonTreeCheckedNode>  getMenuJsonTreeCheckedNode( Menu parentMenu,String roleid){
	 try {
		return menuService.getMenuTreeCheckedNode(parentMenu,roleid);
	} catch (Exception e) {
		e.printStackTrace();
		}
    return null;
 }
 //获取首页菜单
 @RequestMapping(value="/getIndexMenu")
  public void getIndexMenu(Menu parentMenu,HttpSession session,HttpServletResponse response){
	 response.setCharacterEncoding("utf-8");
	 response.setContentType("text/html;charset=utf-8");
	 String menus=(String) session.getAttribute("menus");
	 String menusjson="";
	 try {
	  menusjson=this.menuService.getIndexMenu(parentMenu, menus);
	} catch (Exception e) {
		e.printStackTrace();
	}
	try {
		response.getWriter().print(menusjson);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	
 }
 
 @RequestMapping(value="/listMenu")
 public String listMenu(Menu parentMenu,ModelMap map,Page page){
	page=this.menuService.getPageMenu(page,parentMenu);
	 map.put("page", page);
	 map.put("parentMenu", parentMenu);
	 return "menu/list";
 }
 @RequestMapping(value="/saveMenu")
 @ResponseBody
 public  String saveMenu(Menu menu){
	 try { 
		 this.bizdataservice.bizSave(Menu.class, menu);
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}

		return "suceess";
 }
 @RequestMapping(value="/editMenu")
 public String editMenu(Menu menu,ModelMap map){
	 try {
		 Object persistencebject=null;
		 if(menu!=null&&menu.getId()!=null&&!menu.getId().equals("")){
			 persistencebject=menuService.get(menu.getId());
	 	 }
	    Object newMenu= bizdataservice.getPersistenceObj(Menu.class, persistencebject, menu);
	    map.put("menu", newMenu);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "menu/edit";
 }
 @RequestMapping(value="/menuFrame")
 public String menuFrame(){
	 return "menu/listFrame";
 }
 @RequestMapping(value="/menuLeftTree")
 public String menuLeftTree(){
	 return "menu/menuLeftTree";
 }
 @RequestMapping(value="/selectMenu")
  public String selectMenu(Role role,ModelMap map){
	 map.put("role", role);
	 return "menu/menuCheckedTree";
 }
 @RequestMapping(value="/delMenu")
 @ResponseBody
 public boolean delMenu(String ids) {
	 return  this.bizdataservice.bizDel("com.zc.abf.pojo.Menu", ids);
 };
public MenuService getMenuService() {
	return menuService;
}

public void setMenuService(MenuService menuService) {
	this.menuService = menuService;
}

}
