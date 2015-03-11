package com.zc.abf.service;

import java.util.List;

import com.zc.abf.pojo.Menu;
import com.zc.base.service.BaseService;
import com.zc.base.util.JsonTreeCheckedNode;
import com.zc.base.util.JsonTreeNode;
import com.zc.base.util.Page;

public interface MenuService extends BaseService<Menu, String>{
	List<JsonTreeNode> getMenuTreeNode(Menu parentMenu);
	List<JsonTreeCheckedNode> getMenuTreeCheckedNode(Menu parentMenu,String roleid);
	public  List<Menu> getListMenu(Menu parentMenu);
	public  Page getPageMenu(Page page,Menu parentMenu);
	String  getIndexMenu(Menu parentMenu,String menus);
}
