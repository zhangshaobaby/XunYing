package com.zc.abf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.zc.abf.dao.impl.MenuDaoImpl;
import com.zc.abf.pojo.Menu;
import com.zc.abf.pojo.Role;
import com.zc.abf.pojo.RoleMenu;
import com.zc.abf.service.MenuService;
import com.zc.abf.service.RoleService;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.JsonTreeCheckedNode;
import com.zc.base.util.JsonTreeNode;
import com.zc.base.util.Page;
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl<Menu, String> implements MenuService{
	@Autowired
	private RoleService roleService;
	
	@Autowired
	public void setBaseDao(MenuDaoImpl MenuDao) {
		this.baseDao = MenuDao;
	}
	
	public List<JsonTreeNode> getMenuTreeNode(Menu parentMenu){
		List<Menu> listMenu=new ArrayList<Menu>();
		List<JsonTreeNode> listNode=new ArrayList<JsonTreeNode>();
		listMenu=getListMenu(parentMenu);
		listNode= getTreeNodeList(listMenu);	
		return listNode;
	}
	
	/**
	 * //获取 子菜单 如果 父菜单为null获取为""  则查询跟节点菜单集合;
	 */
	public  List<Menu> getListMenu(Menu parentMenu){
		List<Menu> listMenu=new ArrayList<Menu>();
		if(parentMenu==null||parentMenu.getId()==null||parentMenu.getId().equals("")){
			listMenu=this.find("from Menu where parentMenu is null");
		}else{
			parentMenu=this.get(parentMenu.getId());
			listMenu=(List<Menu>) parentMenu.getSubMenuList();
		}
		return listMenu;
	}
	/**
	 * //获取 子菜单 如果 父菜单为null获取为""  则查询跟节点菜单集合;
	 */
	public  Page getPageMenu(Page page,Menu parentMenu){
		if(page==null){
			page=new Page<Menu>();
		}
		String hql="";
		if(parentMenu==null||parentMenu.getId()==null||parentMenu.getId().equals("")){
			hql="from Menu where parentMenu is null";
		}else{
		    hql="from Menu where parentMenu ='"+parentMenu.getId()+"'";
		}
		page.setHql(hql);
        page=this.findByPage(page);
		return page;
	}
	/**
	 * 将List<Menu> 转换成List<JsonTreeNode> 只封装 ID 和TEXT
	 * @param menulist
	 * @return
	 */
	public List<JsonTreeNode>  getTreeNodeList(List<Menu> menulist){
		List<JsonTreeNode> listNode=new ArrayList<JsonTreeNode>();
		for(Menu menu:menulist){
			JsonTreeNode node=new JsonTreeNode();
			node.setId(menu.getId());
			node.setText(menu.getMenuLabel());
			if(menu.getSubMenuList().size()>0){
				node.setLeaf(false);
			}else{
				node.setLeaf(true);
			}
			listNode.add(node);	
		}
		return listNode;
	}
	/**
	 * 将List<Menu> 转换成List<JsonTreeCheckedNode> 只封装 ID 和TEXT
	 * @param menulist
	 * @return
	 */
	public List<JsonTreeCheckedNode>  getTreeCheckedNodeList(List<Menu> menulist){
		List<JsonTreeCheckedNode> listNode=new ArrayList<JsonTreeCheckedNode>();
		for(Menu menu:menulist){
			JsonTreeCheckedNode node=new JsonTreeCheckedNode();
			node.setId(menu.getId());
			node.setText(menu.getMenuLabel());
			if(menu.getSubMenuList().size()>0){
				node.setLeaf(false);
			}else{
				node.setLeaf(true);
			}
			listNode.add(node);	
		}
		return listNode;
	}
	/**
	 * 
	 *  根据角色的菜单集合 判断哪些菜单需要回填
	 * @param listCheckedNode 菜单的 List<JsonTreeCheckedNode> 
	 * @param roleid 角色ID
	 * @return
	 */
	public List<JsonTreeCheckedNode>  getTreeCheckedNodeList(List<JsonTreeCheckedNode> listCheckedNode,String roleid){
		 Role role=null; 
		 if(roleid!=null&&!roleid.equals("")){
			 role=roleService.get(roleid);
		 }
		 if(role!=null&&role.getRoleMenus().size()>0){
			 //外层循环 菜单节点 效率比较高 因为 菜单树不是全部菜单 
			 for(JsonTreeCheckedNode checkedNode:listCheckedNode){
				 for(RoleMenu rolemenu: role.getRoleMenus()){
					 if(rolemenu.getMenuid().equals(checkedNode.getId())){
						 checkedNode.setChecked(true);
						 break;
					 }
				 }
			 }
		 }
		 return listCheckedNode;
	}

	public List<JsonTreeCheckedNode> getMenuTreeCheckedNode(Menu parentMenu,
			String roleid) {
		List<Menu> listMenu=new ArrayList<Menu>();
		List<JsonTreeCheckedNode> listNode=new ArrayList<JsonTreeCheckedNode>();
		listMenu=getListMenu(parentMenu);
		listNode= getTreeCheckedNodeList(listMenu);	
		getTreeCheckedNodeList(listNode, roleid);
		return listNode;
	}
	public Menu getNewMenu(Menu menu){
		Menu  newMenu=new Menu();
			newMenu.setMenuCode(menu.getMenuCode());
			newMenu.setId(menu.getId());
			newMenu.setMenuLabel(menu.getMenuLabel());
            newMenu.setSubMenuList(menu.getSubMenuList());
            newMenu.setParentMenu(menu.getParentMenu());
		    return newMenu;
	}
	public String   getIndexMenu(Menu parentMenu,String menus){
		List<Menu>  list=this.getListMenu(parentMenu);
		List<Menu> removeList=new ArrayList<Menu>();
		for(Menu menu  :list){
		 	if(!menus.contains(menu.getId())){
		 		removeList.add(menu);
		 	}
		} 
		list.removeAll(removeList);
       String menujson= JSONArray.toJSONString(list);
		return menujson;
	}
	
}
