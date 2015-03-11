package com.zc.bsm.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.defineAnnotation.NeedOpenHFPayInterceptor;
import com.zc.base.util.IdentifyUtil;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.pojo.News;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.AdvertService;
import com.zc.bsm.service.CompanyService;
import com.zc.bsm.service.NewsService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
@Controller
@RequestMapping("/")
@Scope("prototype")
public class LoginAction extends Action {
	@Autowired
	public UserService userService;
	@Autowired
	public ProjectService projectService;
	@Autowired
	public CompanyService companyService;
	@Autowired
	public AdvertService advertService;
	@Autowired
	public bizDataService bizdataService;
	@Autowired
	public NewsService newsService;
	//前台提示信息
	private String msg;
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value ="/index")
	@NeedInterceptor
	public ModelAndView index(){
		//
		List list = advertService.mainPageAdvert();
		ModelAndView model = new ModelAndView("home");
		List pList1 = projectService.findByType(0,4);
		List pList2 = projectService.findByType(1,4);
		List pList3 = projectService.findByType(2,4);
		List pList4 = projectService.findByType(3,4);
		int percent = 0;
		String logo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		for(Project p : (List<Project>)pList1){
			percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			if(p.getXtid()!=null||p.getDbid()!=null){
				Company company = ((Company)bizdataService.bizfindbyid(Company.class,p.getXtid()==null?p.getDbid():p.getXtid()));
				if(company!=null)
					logo = company.getLogo();
			}
			try{
				p.setRate_type(sdf.parse(p.getEnd_time()).compareTo(date));
				p.setCreate_username(sdf.parse(p.getStart_time()).compareTo(date)+"");
				if(sdf.parse(p.getEnd_time()).compareTo(date)==1&&sdf.parse(p.getStart_time()).compareTo(date)==-1&&p.getState()==0){
					p.setRecommend((sdf.parse(p.getEnd_time()).getTime()-date.getTime())+"");
				}else{
					p.setRecommend("");
				}
			}catch(Exception e){}
			//
			p.setCode(logo);
			p.setFlag(percent+"");
		}
		for(Project p : (List<Project>)pList2){
			percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			if(p.getXtid()!=null||p.getDbid()!=null){
				Company company = ((Company)bizdataService.bizfindbyid(Company.class,p.getXtid()==null?p.getDbid():p.getXtid()));
				if(company!=null)
					logo = company.getLogo();
			}
			try{
				p.setRate_type(sdf.parse(p.getEnd_time()).compareTo(date));
				p.setCreate_username(sdf.parse(p.getStart_time()).compareTo(date)+"");
				if(sdf.parse(p.getEnd_time()).compareTo(date)==1&&sdf.parse(p.getStart_time()).compareTo(date)==-1&&p.getState()==0){
					p.setRecommend((sdf.parse(p.getEnd_time()).getTime()-date.getTime())+"");
				}else{
					p.setRecommend("");
				}
			}catch(Exception e){}
			p.setCode(logo);
			p.setFlag(percent+"");
		}
		for(Project p : (List<Project>)pList3){
			percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			if(p.getXtid()!=null||p.getDbid()!=null){
				Company company = ((Company)bizdataService.bizfindbyid(Company.class,p.getXtid()==null?p.getDbid():p.getXtid()));
				if(company!=null)
					logo = company.getLogo();
			}
			try{
				p.setRate_type(sdf.parse(p.getEnd_time()).compareTo(date));
				p.setCreate_username(sdf.parse(p.getStart_time()).compareTo(date)+"");
				if(sdf.parse(p.getEnd_time()).compareTo(date)==1&&sdf.parse(p.getStart_time()).compareTo(date)==-1&&p.getState()==0){
					p.setRecommend((sdf.parse(p.getEnd_time()).getTime()-date.getTime())+"");
				}else{
					p.setRecommend("");
				}
			}catch(Exception e){}
			p.setCode(logo);
			p.setFlag(percent+"");
		}
		for(Project p : (List<Project>)pList4){
			percent = p.get_now_money().multiply(new BigDecimal(100)).divide(p.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
			try{
				p.setRate_type(sdf.parse(p.getEnd_time()).compareTo(date));
				p.setCreate_username(sdf.parse(p.getStart_time()).compareTo(date)+"");
				if(sdf.parse(p.getEnd_time()).compareTo(date)==1&&sdf.parse(p.getStart_time()).compareTo(date)==-1&&p.getState()==0){
					p.setRecommend((sdf.parse(p.getEnd_time()).getTime()-date.getTime())+"");
				}else{
					p.setRecommend("");
				}
		}catch(Exception e){}
			p.setFlag(percent+"");
		}
		model.addObject("pList1",pList1);
		model.addObject("pList2",pList2);
		model.addObject("pList3",pList3);
		model.addObject("pList4",pList4);
		//服务器时间
		model.addObject("nowDate",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
		
		//新闻
		List<News> nList1 = newsService.findByType(65,5);
		List<News> nList2 = newsService.findByType(66,5);
		List<News> nList3 = newsService.findByType(67,5);
		for(int i = 0 ; i < 5 ; i++){
			if(nList1.size()>i)
				nList1.get(i).setContent(StringUtil.replaceNR( nList1.get(i).getContent()));
			if(nList2.size()>i)
				nList2.get(i).setContent(StringUtil.replaceNR( nList2.get(i).getContent()));
			if(nList3.size()>i)
				nList3.get(i).setContent(StringUtil.replaceNR( nList3.get(i).getContent()));
		}
		model.addObject("nList1",nList1);
		model.addObject("nList2",nList2);
		model.addObject("nList3",nList3);
		
		
//		Dict totaluserDict=(Dict) bizdataService.bizfindbyid(Dict.class, 45);
//		Dict totalinvestDict=(Dict) bizdataService.bizfindbyid(Dict.class, 46);
//		Dict totalbenefitDict=(Dict) bizdataService.bizfindbyid(Dict.class, 47);
//		String totaluser=totaluserDict.getRemark();
//		String totalinvest=totalinvestDict.getRemark();
//		String totalbenefit=totalbenefitDict.getRemark();
//		model.addObject("totaluser",totaluser);
//		model.addObject("totalinvest",totalinvest);
//		model.addObject("totalbenefit",totalbenefit);
		model.addObject("advertList",list);
		model.addObject("picpath",SignUtils.PIC_HOST);
		return model;
	}
	/**
	 * 用户登录登陆。
	 * 
	 * @return
	 */
	@RequestMapping(value ="/dologin")
	@NeedInterceptor
	public String login(HttpServletRequest request,String refer, ModelMap modelMap) {
		try {
//			String test = request.getContextPath();
//			String referer = request.getHeader("referer");
//			if(referer!=null&&!referer.equals("")&&referer.indexOf("dologin")==-1&&referer.indexOf("register")==-1&&referer.indexOf("Register")==-1){
//				modelMap.addAttribute("refer", referer);
//			}
			String userid=(String) request.getParameter("username");
			String	password=(String) request.getParameter("password");
			//String	identify=(String) request.getParameter("identify");
			if(userid==null||password==null){
				return "login";
			}
			
			//非空验证
			String valValue=validation( userid, password, null);
			if(valValue.equals("login")){
				modelMap.put("msg", msg);
				return valValue;
			}
			modelMap.put("username", userid);
			// 验证码验证
			HttpSession session = request.getSession();
			/*		String sVCode = (String) session.getAttribute("identify");
		// 验证码输入错误
			if (identify != null && !identify.equals("")
					&& !identify.equals(sVCode)) {
				this.msg="验证码输入错误";
				modelMap.put("msg", msg);
				return "login";
			}*/
			String md5Password = this.userService.md5(password);
			// 数据库已存数据 
			//User loginOperator =this.userService.login(userid,md5Password);
			User loginOperator =this.userService.login(userid,userid,md5Password);
			if(loginOperator==null){
				this.msg="账号或密码错误";
				modelMap.put("msg", msg);
				return "login";
			}
			session.setAttribute("user",loginOperator);
//			//跳转到页面
//			if(referer!=null&&!referer.equals("")){
//				try{
//
//							System.out.println(test);
//					System.out.println(refer);
//					System.out.println(refer.substring(refer.indexOf(test)));
//					System.out.println( "redirect:"+refer.substring(refer.indexOf(test)+test.length()));
//					return "redirect:"+refer.substring(refer.indexOf(test)+test.length());
//				}catch(Exception e){
//					return "redirect:index";
//				}
//			}
		    return "redirect:index";
		} catch (Exception e) {
			e.printStackTrace();
			this.msg="登录出错";
			modelMap.put("msg", msg);
			return "login";
		}
	}
	
	//后台非空验证
	public String validation(String userid,String password,String identify){
		// 用户名不能为空
		if (userid==null || userid.equals("")) {
			this.msg="用户名不能为空";
			return "login";
		}
		// 密码不能为空
		if (password == null || password.equals("")) {
			this.msg="密码不能为空";
			return "login";
		}
		/*// 验证码不能为空
		if (identify == null || identify.equals("")) {
			this.msg="验证码不能为空";
			return "login";
		}*/
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
	 * 获得验证码
	 * 
	 * @return
	 */
	@RequestMapping(value ="/touziidentify")
	@NeedInterceptor
	public void touziidentify(HttpServletRequest request,HttpServletResponse  response, ModelMap modelMap) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/jpeg");	
		HttpSession session = request.getSession();
		IdentifyUtil indentify = new IdentifyUtil(100, 31);
		String identifyStr = indentify.getRandomString();
		BufferedImage img = indentify.createImage(identifyStr);
		session.setAttribute("touziidentify", identifyStr);
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
	@RequestMapping(value ="/getsessionattr")
	@ResponseBody
	@NeedInterceptor
	public String getsessionattr(HttpSession session,String attrname ){
		String result=(String) session.getAttribute(attrname);
		return result;
		
	}
	//验证验证码是否相等
	@RequestMapping(value ="/checksessionattr")
	@ResponseBody
	@NeedInterceptor
	public boolean checksessionattr(HttpSession session,String attrname,String code){
		String result=(String) session.getAttribute(attrname);
		if(result==null){
			return false;
		}
		return result.equals(code);
	}
	/**
	 * 
	 * 退出登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/outLogin")
	 @NeedOpenHFPayInterceptor
	public String outLogin(HttpServletRequest request,HttpServletResponse  response) {
		try {
			HttpSession session = request.getSession();
			session.removeAttribute(
					"user");
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}
	@RequestMapping(value ="/about_us")
	@NeedInterceptor
	public String aboutus(){
		return "about_us";
	}
	@RequestMapping(value ="/about_us_mobile")
	@NeedInterceptor
	public String about_us_mobile(){
		return "about_us_mobile";
	}
	@RequestMapping(value ="/lianxi")
	@NeedInterceptor
	public String lianxi(){
		return "lianxi";
	}
	@RequestMapping(value ="/anquan")
		@NeedInterceptor
	public String anquan(){
		return "anquan";
	}
	@RequestMapping(value ="/blank")
	@NeedInterceptor
	public String blank(){
		return "blank";
	}
	@RequestMapping(value ="/new_intro")
		@NeedInterceptor
	public String new_intro(){
		return "new_intro";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
