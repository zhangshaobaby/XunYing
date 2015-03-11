package com.zc.bsm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
@Controller
@RequestMapping("/forwordAction")
@Scope("prototype")
/**
 * 跳转Action 专用于直接跳转 跳转页面为/forwordAction 后面的路径 如 请求路径为 
 *  /forwordAction/forwordToView?path=menu/menuLeftTree
 */
public class forwordAction extends Action {
    //跳转到页面
	@NeedInterceptor
	 @RequestMapping(value="/forwordToView")
	public String forwordToView(String path){
		return path;
	}
}
