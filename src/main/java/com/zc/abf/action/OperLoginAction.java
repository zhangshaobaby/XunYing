package com.zc.abf.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zc.abf.pojo.Operator;
import com.zc.abf.pojo.Role;
import com.zc.abf.pojo.RoleMenu;
import com.zc.abf.service.OperatorService;
import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.util.IdentifyUtil;
import com.zc.bsm.pojo.User;

/**
 * @author 张少
 *  后台登陆用的
 */
@Controller
@RequestMapping("/auth/login")
@Scope("prototype")
public class OperLoginAction extends Action{
 @Autowired
 private OperatorService operatorService;
 /**
	 * 后台管理人员登陆。
	 * 
	 * @return
	 */
	@RequestMapping(value ="/dologin")
	@NeedInterceptor
	public String login(HttpServletRequest request, ModelMap modelMap,HttpSession session) {
		try {
			String userid=(String) request.getParameter("username");
			String	password=(String) request.getParameter("password");
		    String	identify=(String) request.getParameter("identify");
		    modelMap.put("username", userid);
		    modelMap.put("password", password);
		    if(userid==null&&password==null&&identify==null){
		    	return "managerLogin/login";
		    }
		    
			//非空验证
			String valValue=validation( userid, password, identify);
			if(valValue.equals("login")){
				modelMap.put("msg", "请填写必填项");
				return valValue;
			}
			// 验证码验证
			String sVCode = (String) session.getAttribute("identify");
			// 验证码输入错误
			if (identify != null && !identify.equals("")
					&& !identify.equals(sVCode)) {
				this.msg="验证码输入错误";
				modelMap.put("msg", msg);
				return "managerLogin/login";
			}
			String md5Password = this.operatorService.md5(password);
			// 数据库已存数据 
			Operator loginOperator =this.operatorService.login(userid,md5Password);
			if(loginOperator==null){
				this.msg="账号或密码错误";
				modelMap.put("msg", msg);
				return "managerLogin/login";
			}
			//跳转到页面
			//将管理员的角色 权限存放在session 中
			session.setAttribute("operator",loginOperator);
			session.setAttribute("menus", this.operatorService.getMenusFromUser(loginOperator));
		    return "index";
		} catch (Exception e) {
			e.printStackTrace();
			this.msg="登录出错";
			modelMap.put("msg", msg);
			return "managerLogin/login";
		}
	}
	
	
	//后台非空验证
	public String validation(String userid,String password,String identify){
		// 用户名不能为空
		if (userid==null || userid.equals("")) {
			this.msg="用户名不能为空";
			return "managerLogin/login";
		}
		// 密码不能为空
		if (password == null || password.equals("")) {
			this.msg="密码不能为空";
			return "managerLogin/login";
		}
		// 验证码不能为空
		if (identify == null || identify.equals("")) {
			this.msg="验证码不能为空";
			return "managerLogin/login";
		}
		return "success";
	}
	
	/**
	 * 获得验证码
	 * 
	 * @return
	 */
	@RequestMapping(value ="/identify")
	@NeedInterceptor
	public void identify(HttpServletRequest request,HttpServletResponse  response, ModelMap modelMap) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/jpeg");	
		HttpSession session = request.getSession();
		IdentifyUtil indentify = new IdentifyUtil(100, 31);
		String identifyStr = indentify.getRandomString();
		BufferedImage img = indentify.createImage(identifyStr);
		session.setAttribute("identify", identifyStr);
		try {
			try {
				ServletOutputStream sos = response.getOutputStream();
				ImageIO.write(img, "jpeg", sos);
			} catch (IOException e) {
				this.log.debug("系统信息：[at:IdentifyUtil],写入文件错误！");
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/outLogin")
	public String outLogin(HttpServletRequest request,HttpServletResponse  response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute(
					"operator");
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "managerLogin/login";
	}
 
 
	
}
