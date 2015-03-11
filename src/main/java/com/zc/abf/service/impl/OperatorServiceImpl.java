package com.zc.abf.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.abf.dao.impl.OperatorDaoImpl;
import com.zc.abf.pojo.Operator;
import com.zc.abf.pojo.Role;
import com.zc.abf.pojo.RoleMenu;
import com.zc.abf.service.OperatorService;
import com.zc.base.service.BaseServiceImpl;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.impl.UserServiceImpl;

@Service("OperatorService")
public class OperatorServiceImpl extends BaseServiceImpl<Operator, String> implements OperatorService{
	protected Log logger = LogFactory.getLog(OperatorServiceImpl.class);
	@Autowired
	public void setBaseDao(OperatorDaoImpl OperatorDao) {
		this.baseDao = OperatorDao;
	}
	// md5加密
	public String md5(String str) {
		String s = str;
		if (s == null) {
			return "";
		} else {
			String value = null;
			MessageDigest md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException ex) {
				logger.debug(ex);
			}
			sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
			try {
				value = baseEncoder.encode(md5.digest(s.getBytes("utf-8")));
			} catch (Exception ex) {
			}
			return value;
		}
	}
	//获取人员角色的权限
	public String getMenusFromUser(Operator oper){
		oper=this.get(oper.getId());
		String menus="";
		if(oper!=null&&oper.getRoles()!=null&&oper.getRoles().size()>0){
			List<Role> roles=oper.getRoles();
			for(Role role:roles){
		    	Set<RoleMenu> roleMenus=role.getRoleMenus();
		    	for(RoleMenu rolemenu: roleMenus){
		    		menus+=rolemenu.getMenuid()+",";
		    	}
			}
			
		}
		if(!menus.equals("")){
			menus=menus.substring(0,menus.length()-1);
		}
		return menus;
	}
	
	public Operator login(String username,String password){
		String hql="from Operator where userid=? and pwd=?";
		List list = this.find(hql, username,password);		
		return list.size()>0?(Operator)list.get(0):null;
	}
}
