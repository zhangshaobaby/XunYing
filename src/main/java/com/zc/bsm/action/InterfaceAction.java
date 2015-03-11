package com.zc.bsm.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.VKDES_PLUS;
import com.zc.base.util.XmlUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.Bankcard;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Message;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RechargeOrder;
import com.zc.bsm.pojo.SoftUpdate;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.RechargeOrderService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.UserBindCard;
import com.zc.bsm.vo.UserRegister;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.webVo.Recordformobile;
@Controller
@RequestMapping("/interface")
@Scope("prototype")
public class InterfaceAction extends Action {
	@Autowired
	public UserService userService;
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	private ProjectService projectService;
	@Autowired
    public LuckyMoneyService luckyMoneyService;
	@Autowired
	public TenderService tenderService;
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
	private RechargeOrderService rechargeOrderService;
	@Autowired
    public RepaymentPlanService repaymentPlanService;
	private JSONObject jsonResult = new JSONObject();
	private String errcode = "99";
	private String errmsg = "";
	private int pagenum = 1;
	private String count = "0";
	private String decodetojson(String para)
	{
		//接受参数解密
		String parameters=VKDES_PLUS.Decrypt(para);
		if(null==parameters)
		{
			return null;
		}
		return parameters;
	}
	
	private String returnresult()
	{
		//处理结果加密
		jsonResult.put("errcode", errcode);
        jsonResult.put("errmsg", errmsg);
        jsonResult.put("page", String.valueOf(pagenum));
        jsonResult.put("count", count);
        String r=jsonResult.toString();
        //加密
        String result=VKDES_PLUS.Encrypt(r);
        return result;
	}
	private String paraencode(String flag)
	{
		//模拟请求参数加密
		String v=jsonResult.toString();
		String result;
		if(null==flag)
		{
			result="para="+VKDES_PLUS.Encrypt(v);
		}
		else
		{
			result=VKDES_PLUS.Encrypt(v);
		}
        return result;
	}
	//测试页面进入总的方法进行加密和解密根据页面url进行分发
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/test",method=RequestMethod.POST)
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public void test(HttpServletRequest request, HttpServletResponse response){
		Enumeration enu=request.getParameterNames(); 
		String thisurl=request.getParameter("url");
		String path = request.getContextPath(); 
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		URL url;
		BufferedReader br=null;
		InputStream is=null;
		OutputStream outStrm=null;
		HttpURLConnection hc=null;
		try {
			String teshu="";
			jsonResult = new JSONObject();
			response.setHeader("content-type","text/html;charset=UTF-8");
	    	response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>test</TITLE></HEAD>");
			out.println("  <BODY>");
	    	out.println("Respose:"+"<br/>");
	    	while(enu.hasMoreElements()) 
			{
				String name=(String)enu.nextElement();
				if(!name.startsWith("**"))
				{
					out.println(name+"="+request.getParameter(name)+"<br/>");
					jsonResult.put(name, request.getParameter(name));
					if(name.equals("teshu"))
					{
						teshu="1";
					}
				}
			}
			out.println("<br/>");
			out.println("<br/>");
			url = new URL(basePath+thisurl);
			hc = (HttpURLConnection) url.openConnection();
			hc.setDoOutput(true);
			hc.setUseCaches(false);
			hc.setRequestMethod("POST");
			hc.connect();
			outStrm = hc.getOutputStream();
			String r=paraencode(null);
			outStrm.write(r.getBytes());
			
			outStrm.flush();
			outStrm.close(); 
			is = hc.getInputStream();
			
			
			out.println("Result:"+"<br/>");
			out.println("<br/>");
			
			br = new BufferedReader(new InputStreamReader(is,"utf-8"));
			String Line=br.readLine();
			String v="";
	    	while(Line!=null)
	    	{
	    		if(v.equals(""))
	    		{
	    			v=Line;
	    		}
	    		else
	    		{
	    			v=v+Line;
	    		}
	    		Line=br.readLine();
	    	}
	    	if(teshu.equals(""))
			{
	    		out.println(decodetojson(v)+"<br/>");
			}
	    	else
	    	{
	    		out.println("true <br/>");
	    	}
	    	out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				if(null!=br)br.close();
				if(null!=is)is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            hc.disconnect();
		}
		
	}
	//测试页面进入总的方法进行加密和解密根据页面url进行分发
	@SuppressWarnings("unchecked")
	@RequestMapping(value ="/testwebview")
	@NeedInterceptor//过滤器
	public ModelAndView testwebview(HttpServletRequest request, HttpServletResponse response){
		String thisurl=request.getParameter("url");
		jsonResult = new JSONObject();
		Enumeration enu=request.getParameterNames();
		while(enu.hasMoreElements()) 
		{
			String name=(String)enu.nextElement();
			if(!name.startsWith("**"))
			{
				jsonResult.put(name, request.getParameter(name));
			}
		}
		String r=paraencode("1");
		
		if(thisurl.equals("interface/productdetail"))
		{
			return this.productdetail(r);
		}
		else if(thisurl.equals("interface/recordcontact"))
		{
			return this.recordcontact(r, request);
		}
		else if(thisurl.equals("interface/recorddetail"))
		{
			return this.recorddetail(r);
		}
		else
		{
			return null;
		}
		
		
	}
	/**
	 * 手机号码验证接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/getcode")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String getcode(String para){
		para=para.replaceAll(" ", "+");
		String pa=decodetojson(para);
		if(null==pa)
		{
			errcode="1";
			errmsg="加密解密出现错误";
		}
		else
		{
			User user=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
			if(null==user.getPhone()||user.getPhone().equals(""))
			{
				errcode="50";
				errmsg="手机号码";
			}
			if(null==user.getVercode()||user.getVercode().equals(""))
			{
				errcode="51";
				errmsg="验证码为空";
			}
			if(null==user.getFlag()||user.getFlag().equals(""))
			{
				errcode="52";
				errmsg="标志位为空";
			}
			if(errcode.equals("99"))
			{
				msgUtil msgutil=new msgUtil();
				Map<String,String> params=new HashMap<String, String>();
				params.put("code", user.getVercode());
				if(user.getFlag().equals("1"))
				{
					//为注册时发送验证码
					String getUserHql = "from User where phone=? or username=?";
					List<User> list = this.userService.find(getUserHql,user.getPhone(),user.getPhone());
					if(list!=null&&list.size()>0){
						errcode="3";
						errmsg="该手机号码已经使用";
					}
					else
					{
						params.put("action", "注册");
					}
				}
				else if(user.getFlag().equals("2")||user.getFlag().equals("3"))
				{
					//忘记密码时发送验证码
					String getUserHql = "from User where phone=?";
					List<User> list = this.userService.find(getUserHql,user.getPhone());
					if(list!=null&&list.size()>0){
						if(user.getFlag().equals("2"))
						{
							params.put("action", "修改密码操作");
						}
						if(user.getFlag().equals("3"))
						{
							params.put("action", "取现操作");
						}
					}
					else
					{
						errcode="3";
						errmsg="该手机号码不存在";
					}
				}
				if(errcode.equals("99"))
				{
					boolean ispush=msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
					if(ispush)
					{
						errcode="0";
						jsonResult.put("data", new JSONObject());
					}
					else
					{
						errcode="2";
						errmsg="短消息发送失败";
					}	
				}
				
			}
			
		}
		return returnresult();	
	}
	/**
	 * 注册基本信息接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/regist")//
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String regist(String para){
		para=para.replaceAll(" ", "+");
		String pa=decodetojson(para);
		if(null==pa)
		{
			errcode="1";
			errmsg="加密解密出现错误";
		}
		else
		{
			User user=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
			if(null==user.getUsername()||user.getUsername().equals(""))
			{
				errcode="50";
				errmsg="用户名为空";
			}
			if(null==user.getPhone()||user.getPhone().equals(""))
			{
				errcode="51";
				errmsg="电话号码为空";
			}
			if(null==user.getPwd()||user.getPwd().equals(""))
			{
				errcode="52";
				errmsg="密码为空";
			}
			if(null!=user.getAgent()&&!user.getAgent().equals(""))
			{

		    	 //确定推荐人
		    	User agent= userService.findByName_Phone(user.getAgent());
		    	if(agent!=null){
		    		user.setAgent(agent.getId());
		    		//确定理财顾问
		    		if(agent.getType()!=null&&agent.getType()==2){//如果推荐人是理财顾问
		    			user.setAdvisor(agent.getId());
		    		}else{//如果推荐人是一般人员
		    			if(agent.getAdvisor()!=null&&!agent.getAdvisor().equals("")){//继承人推荐人的理财顾问
			    			user.setAdvisor(agent.getAdvisor());
			    		}
		    		}
		    		//积分
		    		userService.addScore("6", agent,null, null, "推荐用户注册");
		    	}else{
		    		errcode="6";
					errmsg="推荐人不存在";
		    	}
			}
			if(errcode.equals("99"))
			{
				if(user.getPhone().length()>11)
				{
					errcode="5";
					errmsg="电话长度有误";
				}
				else
				{
					if(user.getUsername().length()<6||user.getUsername().length()>16)
					{
						errcode="4";
						errmsg="用户名长度有误";
					}
					else
					{
						String getUserHql = "from User where username=?";
						List<User> loginOperatorList = this.userService.find(getUserHql,user.getUsername());
						if(loginOperatorList!=null&&loginOperatorList.size()>0){
							errcode="2";
							errmsg="该用户名已经存在";
						} 
						else
						{
							getUserHql = "from User where phone=? or username=?";
							List<User> list = this.userService.find(getUserHql,user.getPhone(),user.getUsername());
							if(list!=null&&list.size()>0){
								errcode="3";
								errmsg="该手机号码已经使用";
							}
							else
							{
								String   md5Pwd=userService.md5(user.getPwd());
								user.setPwd(md5Pwd);
								user.setPaypwd(md5Pwd);
								user.setSource("1");
								user.setType(1);
//								user.setScore(100);
								try {
									user= (User) bizdataservice.bizSave(User.class, user);
									//赠送红包
									LuckyMoney lucky = new LuckyMoney();
									lucky.setComeFrom(0l);
									luckyMoneyService.saveLuckyMoney(lucky, user);
									userService.addScore("10", user,null, null, "手机用户注册");
									//发送系统消息
//									Message message=new Message();
//									message.setType(new Dict(59));
//									message.setContent("恭喜注册一起一起投完成，你不理财，财不理你。赶紧去投资专区找找合适你的投资项目吧。");
//									message.setDestUser(user);
//									message.setHaveLook(0);
//									this.bizdataservice.bizSave(Message.class, message);
									
//									Map<String,String> params=new HashMap<String, String>();
//									params.put("action", "进行注册");
//									params.put("code", vercode);
//									msgUtil msgutil=new msgUtil();
//									msgutil.sendmessage(new String[]{"webmeg"}, user, "20", params);
									
									JSONObject data = new JSONObject();
									data.put("id", user.getId());
									jsonResult.put("data", data);
									errcode="0";
								} catch (Exception e) {
									e.printStackTrace();
									errmsg="系统异常";
								}
							}
						}
					}
				}
			}
			
		}
		return returnresult();	
	}
	/**
	 * 登录接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/login")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String login(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				User user=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
				String username=user.getUsername();
				String pwd=user.getPwd();
				if(null==username||username.equals(""))
				{
					errcode="50";
					errmsg="用户名为空";
				}
				else if(null==pwd||pwd.equals(""))
				{
					errcode="51";
					errmsg="密码为空";
				}
				else
				{
					String md5Password = this.userService.md5(pwd);
					
					String getUserHql = "from User where (username=? or phone=?)";
					List<User> loginOperatorList = this.userService.find(getUserHql,username,username);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						getUserHql=getUserHql+" and pwd=?";
						List<User> list = this.userService.find(getUserHql,username,username,md5Password);
						if(list!=null&&list.size()>0)
						{
							JSONObject data = new JSONObject();
							data.put("id", list.get(0).getId());
							data.put("huifuID", list.get(0).getHuifuID()==null?"":list.get(0).getHuifuID());
							jsonResult.put("data", data);
							errcode="0";
							errmsg="";
						}
						else
						{
							errcode="3";
							errmsg="密码不正确";
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户或手机号码不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 忘记密码
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/findpassword")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String findpassword(String para){
		para=para.replaceAll(" ", "+");
		String pa=decodetojson(para);
		if(null==pa)
		{
			errcode="1";
			errmsg="加密解密出现错误";
		}
		else
		{	
			//按照接口参数填写对应的逻辑
			User user=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
			if(null==user.getPhone() || "".equals(user.getPhone())){
				errcode="51";
				errmsg="电话号码为空";
			}
			if(null==user.getPwd() || "".equals(user.getPwd())){
				errcode="52";
				errmsg="密码为空";
			}
			if(errcode.equals("99")){
				User temp = this.userService.findByName_Phone( user.getPhone());
				if(temp==null){
					errcode="2";
					errmsg="该手机号不存在";
				}else{
					User user2 = userService.get(temp.getId());
					user2.setPwd(userService.md5(user.getPwd()));
					userService.saveOrUpdate(user2);
					errcode="0";
					jsonResult.put("data", new JSONObject());
					errmsg="";
				}
			}
		}
		return returnresult();	
	}
	/**
	 * 修改登录密码接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/updatepassword")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String updatepassword(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				User user=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
				String id=user.getId();
				String old=user.getOldpwd().trim();
				String newpass=user.getPwd().trim();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else if(null==old||old.equals(""))
				{
					errcode="51";
					errmsg="原密码为空";
				}
				else if(null==newpass||newpass.equals(""))
				{
					errcode="52";
					errmsg="新密码为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						String oldpwdmd5=this.userService.md5(user.getOldpwd());
						if(loginOperatorList.get(0).getPwd().equals(oldpwdmd5))
						{
							if(user.getPwd().equals(user.getOldpwd()))
							{
								errcode="4";
								errmsg="原密码和新密码相同";
							}
							else
							{
								String   newmd5Pwd=userService.md5(user.getPwd());
						        user.setPwd(newmd5Pwd);
						    	user=(User) bizdataservice.bizSave(User.class, user);
						    	errcode="0";
								errmsg="";
								jsonResult.put("data", new JSONObject());
							}
						}
						else
						{
							errcode="3";
							errmsg="原密码不正确";
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 开通第三方支付接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/hfpay")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String hfpay(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				User mess=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
				String id=mess.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						UserRegister hufuuse = new UserRegister();
						hufuuse.setBgRetUrl(SignUtils.PUBLIC_HOST+ "huifucallback/printRegistResult");
						hufuuse.setRetUrl(SignUtils.PUBLIC_HOST + "user/hfregisterfinish");
						hufuuse.setUsrId(loginOperatorList.get(0).getUsername());
						String url = hufuuse.getParam();
						if(null==url||url.equals(""))
						{
							errcode="3";
							errmsg="开通失败";
						}
						else
						{
							JSONObject datajson = new JSONObject();
							datajson.put("url", url);
							jsonResult.put("data", datajson);
							errcode="0";
							errmsg="";
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 用户名或者手机号码验证
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/uservalidate")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String uservalidate(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				User user=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
				String name=user.getUsername();
				if(null==name||name.equals(""))
				{
					errcode="50";
					errmsg="用户或手机号码为空";
				}
				else
				{
					String getUserHql = "from User where phone=? or username=?";
					List<User> list = this.userService.find(getUserHql,user.getUsername(),user.getUsername());
					if(list!=null&&list.size()>0){
						errcode="2";
						errmsg="该用户或手机号码存在";
					}else{
						errcode="3";
						errmsg="该用户或手机号码不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 我的基本信息接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/mybaseinfor")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String mybaseinfor(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Message mess=(Message)JSONObject.toBean(JSONObject.fromObject(pa), Message.class);
				String id=mess.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						User user=loginOperatorList.get(0);
						if(null==user.getHuifuID()||user.getHuifuID().equals(""))
						{
							errcode="3";
							errmsg="没有开通第三方支付";
						}
						else
						{
							ModelMap map=new ModelMap();
							this.userService.userEstate(map, user);
							JSONObject datajson = new JSONObject();
							datajson.put("id", id);
							datajson.put("username", user.getUsername()==null?"":user.getUsername());
							datajson.put("realNameAuthent", user.getRealNameAuthentDate()==null?"0":"1");
							datajson.put("phone",user.getPhone()==null?"0":"1");
							datajson.put("question", map.get("question")==null?"0":"1");
							datajson.put("ulevel", map.get("ulevel")==null?"0":map.get("ulevel").toString());
							datajson.put("huifuAccount", user.getHuifuAccount()==null?"":user.getHuifuAccount().toString());
							datajson.put("hbnum", map.get("hbnum").toString());
							datajson.put("score", map.get("score").toString());
							datajson.put("totalAmt", map.get("totalAmt").toString());
							datajson.put("AvlBal", map.get("AvlBal").toString());
							datajson.put("pbd", map.get("pbd").toString());
							datajson.put("procount", map.get("procount").toString());
							datajson.put("zizhangprocount", map.get("zizhangprocount").toString());
							datajson.put("xmprocount", map.get("xmprocount").toString());
							datajson.put("jdprocount", map.get("jdprocount").toString());
							datajson.put("dsprocount", map.get("dsprocount").toString());
							datajson.put("pureplan", map.get("pureplan").toString());
							datajson.put("realname", user.getRealName()==null?"":user.getRealName());
							jsonResult.put("data", datajson);
							errcode="0";
							errmsg="";
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 充值接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/recharge")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String recharge(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				RechargeOrder rechargeOrder=(RechargeOrder)JSONObject.toBean(JSONObject.fromObject(pa), RechargeOrder.class);
				String id=rechargeOrder.getUid();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
//						List<Object> buildbankList = this.comomBizService.find("from Bankcard where user.id=?", loginOperatorList.get(0).getId());
						if(null==loginOperatorList.get(0).getHuifuID()||loginOperatorList.get(0).getHuifuID().equals(""))
						{
							errcode="3";
							errmsg="没有开通第三方支付";
						}
						else
						{
							User user=this.userService.findByHuifuId(loginOperatorList.get(0).getHuifuID());
							if(null!=user)
							{
								rechargeOrder.setHuifuID(user.getHuifuID());
								rechargeOrder.setUser(user);
								String url=this.rechargeOrderService.recharge(rechargeOrder, user.getHuifuID(), "B2C");
								if(url.equals(""))
								{
									errcode="4";
									errmsg="生成订单失败，请重新尝试";
								}
								else
								{
									errcode="0";
									errmsg="";
									JSONObject datajson = new JSONObject();
									datajson.put("url", url);
									jsonResult.put("data", datajson);
								}
							}
							else
							{
								errcode="3";
								errmsg="没有开通第三方支付";
							}
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 提现接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/enterprisecash")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String enterprisecash(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Message mess=(Message)JSONObject.toBean(JSONObject.fromObject(pa), Message.class);
				String id=mess.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
//						List<Object> buildbankList = this.comomBizService.find("from Bankcard where user.id=?", loginOperatorList.get(0).getId());
						if(null==loginOperatorList.get(0).getHuifuID()||loginOperatorList.get(0).getHuifuID().equals(""))
						{
							errcode="3";
							errmsg="没有开通第三方支付";
						}
						else
						{
							User user=this.userService.findByHuifuId(loginOperatorList.get(0).getHuifuID());
							if(null!=user)
							{
								QueryBalanceBgReturn qbr = enchashmentService.getBalanceObj(user);
								JSONObject datajson = new JSONObject();
								datajson.put("AvlBal", qbr.getAvlBal()==null?"":qbr.getAvlBal());
								datajson.put("txfy", "1");//提现费用 汇付天下
								datajson.put("txfffy", "0");//提现服务费
								datajson.put("yjdzrq","T+3个工作日(T日21:00之前申请)");//预计到账日期
								datajson.put("AlltransAmt",user.getAlltransAmt()==null?"0.00":StringUtil.BigDecimal2StringSmall(user.getAlltransAmt()));
								jsonResult.put("data", datajson);
								errcode="0";
								errmsg="";
							}
							else
							{
								errcode="3";
								errmsg="没有开通第三方支付";
							}
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 申请提现接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/applycash")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String applycash(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				CashStream cs=(CashStream)JSONObject.toBean(JSONObject.fromObject(pa), CashStream.class);
				String id=cs.getUid();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
//						List<Object> buildbankList = this.comomBizService.find("from Bankcard where user.id=?", loginOperatorList.get(0).getId());
						if(null==loginOperatorList.get(0).getHuifuID()||loginOperatorList.get(0).getHuifuID().equals(""))
						{
							errcode="3";
							errmsg="没有开通第三方支付";
						}
						else
						{
							List<Object> banklist=this.bizdataservice.find("from Bankcard where user.id=?",loginOperatorList.get(0).getId());
							if(null==banklist||banklist.size()<=0)
							{
								errcode="5";
								errmsg="该用户没有绑定银行卡";
							}
							else
							{
								User user=this.userService.findByHuifuId(loginOperatorList.get(0).getHuifuID());
								if(null!=user)
								{
									String url=this.enchashmentService.cash(cs.getTransAmt().toString(), user.getHuifuID(), "",StringUtil.BigDecimal2String(new BigDecimal(cs.getServFee())) );
									if(url.equals(""))
									{
										errcode="4";
										errmsg="生成订单失败，请重新尝试";
									}
									else
									{
										errcode="0";
										errmsg="";
										JSONObject datajson = new JSONObject();
										datajson.put("url", url);
										jsonResult.put("data", datajson);
									}
								}
								else
								{
									errcode="3";
									errmsg="没有开通第三方支付";
								}
							}
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 银行卡列表接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/banklist")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String banklist(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Bankcard bc=(Bankcard)JSONObject.toBean(JSONObject.fromObject(pa), Bankcard.class);
				String id=bc.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						List<Object> banklist=this.bizdataservice.find("from Bankcard where user.id=?",loginOperatorList.get(0).getId());
						JSONArray dataarray = new JSONArray();
						if(null!=banklist&&banklist.size()>0)
						{
							for(Object cash:banklist)
							{
								Bankcard cashs=(Bankcard)cash;
								JSONObject datajson = new JSONObject();
								datajson.put("bankName", cashs.getBankName()==null?"":cashs.getBankName());
								datajson.put("cardNumber", cashs.getCardNumber()==null?"":cashs.getCardNumber());
								datajson.put("realName", cashs.getUser().getRealName()==null?"":cashs.getUser().getRealName());
								dataarray.add(datajson);
							}
							errcode="0";
							errmsg="";
						}
						else
						{
							errcode="98";
							errmsg="查询结果为空";
						}
						jsonResult.put("data", dataarray);
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 绑定银行卡
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/userbindcard")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String userbindcard(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				User mess=(User)JSONObject.toBean(JSONObject.fromObject(pa), User.class);
				String id=mess.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						UserBindCard bindCard=new UserBindCard();
						bindCard.setUsrCustId(loginOperatorList.get(0).getHuifuID());
						bindCard.setBgRetUrl(SignUtils.PUBLIC_HOST+"huifucallback/printUserBindCard");
						String url=bindCard.getParam();
						if(null==url||url.equals(""))
						{
							errcode="3";
							errmsg="绑定失败";
						}
						else
						{
							JSONObject datajson = new JSONObject();
							datajson.put("url", url);
							jsonResult.put("data", datajson);
							errcode="0";
							errmsg="";
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 投资列表接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/recordlist")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String recordlist(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Project p=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
				String id=p.getUid();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						if(null==loginOperatorList.get(0).getHuifuID()||loginOperatorList.get(0).getHuifuID().equals(""))
						{
							errcode="3";
							errmsg="没有开通第三方支付";
						}
						else
						{
							Page page=new Page();
							page.setCurrPage(p.getPage());
							pagenum=p.getPage();
							String state=null;
							if(null!=p.getState())
							{
								state=p.getState().toString();
							}
							List<Recordformobile> list=this.userService.recordListformobile(loginOperatorList.get(0), page,p.getType(),state);
							JSONArray dataarray = new JSONArray();
							if(null!=list&&list.size()>0)
							{
								SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
								Date date=new Date(); 
								String d=sdf.format(date); 
								count=String.valueOf(page.getTotalPage());
								for(int i=0;i<list.size();i++)
								{
									Recordformobile content=(Recordformobile)list.get(i);
									JSONObject datajson = new JSONObject();
									datajson.put("pid", content.getId()==null?"":content.getId().toString());
									datajson.put("pname", content.getName()==null?"":content.getName().toString());
									datajson.put("rateTime", content.getRateTime()==null?"":content.getRateTime().toString());
									datajson.put("pendTime", content.getPendTime()==null?"":content.getPendTime().toString());
									datajson.put("transAmt", content.getTransAmt()==null?"":content.getTransAmt().toString());
//									String value=this.repaymentPlanService.projectRepay(Long.parseLong(content.get(0).toString()),loginOperatorList.get(0).getHuifuID());
									String value=content.getCurrentmoney()==null?"":content.getCurrentmoney().toString();
									if(StringUtil.getIsNum(value))
									{
										datajson.put("repayMoney", value);
									}
									else
									{
										datajson.put("repayMoney", "0");
									}
									if(content.getState().toString().equals("1")&&Integer.parseInt(content.getStartdays())>60)
									{
										datajson.put("istender", "1");
									}
									else
									{
										datajson.put("istender", "0");
									}
									datajson.put("state", content.getState()==null?"":content.getState().toString());
									datajson.put("type", content.getType()==null?"":content.getType().toString());
									String pid=datajson.getString("pid");
									Project bean=(Project)projectService.findById("com.zc.bsm.pojo.Project", Long.parseLong(pid));
									datajson.put("total_money", bean.getTotal_money()==null?"":bean.getTotal_money().toString());
									datajson.put("now_money", bean.get_now_money()==null?"":bean.get_now_money().toString());
									datajson.put("rate", bean.getRate()==null?"":bean.getRate().toString());
									datajson.put("time_limit", bean.getTime_limit()==null?"":bean.getTime_limit().toString());
									datajson.put("lowest_money",bean.getLowest_money()==null?"":String.valueOf(bean.getLowest_money()));
									datajson.put("highest_money",bean.getHighest_money()==null?"":String.valueOf(bean.getHighest_money()));
									datajson.put("self_highest_money",bean.getSelf_highest_money()==null?"":String.valueOf(bean.getSelf_highest_money()));
									datajson.put("hongbaorate",bean.getHongbaorate()==null?"":String.valueOf(bean.getHongbaorate()));
									datajson.put("end_time",bean.getEnd_time()==null?"":bean.getEnd_time());
									datajson.put("server_time",d);
									dataarray.add(datajson);
								}
								errcode="0";
								errmsg="";
							}
							else
							{
								errcode="98";
								errmsg="查询结果为空";
							}
							jsonResult.put("data", dataarray);
						}
						
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 赎回接口(适用3屌丝宝)
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/repayearly")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String repayearly(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Project pro=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
				String id=pro.getUid();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
//						String result=repaymentPlanService.repayEarlyReward(pro.getId(),loginOperatorList.get(0).getHuifuID());
						List pageother=this.repaymentPlanService.repayEarlyRewardformobile(pro.getId(),loginOperatorList.get(0).getHuifuID());
						String result=pageother.get(0).toString()+pageother.get(1).toString();
						if(result.equals("false")||result.indexOf("true")!=-1)
						{
							errcode="3";
							errmsg="该状态不能赎回";
						}
						else
						{
							String r=repaymentPlanService.repayEarly(pro.getId(),loginOperatorList.get(0).getHuifuID());
							if(null==r)
							{
								errcode="0";
								errmsg="";
								jsonResult.put("data", new JSONObject());
							}
							else
							{
								errcode="4";
								errmsg="申请已经提交,正在处理中";
							}
						}
						
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 资金明细列表接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/translist")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String translist(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				CashStream cs=(CashStream)JSONObject.toBean(JSONObject.fromObject(pa), CashStream.class);
				String id=cs.getUid();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						Page page=new Page();
						page.setCurrPage(cs.getPage());
						pagenum=cs.getPage();
						this.userService.translist(page, cs.getStart_time(), cs.getEnd_time(), cs.getTranstype(), null, loginOperatorList.get(0));
						JSONArray dataarray = new JSONArray();
						List<CashStream> list=page.getResult();
						if(null!=list&&list.size()>0)
						{
							count=String.valueOf(page.getTotalPage());
							for(CashStream cashs:list)
							{
								JSONObject datajson = new JSONObject();
								datajson.put("createTime", cashs.getCreateTime()==null?"":cashs.getCreateTime());
								datajson.put("transtype", cashs.getType().getId()==null?"":cashs.getType().getId().toString());
								datajson.put("ordId", cashs.getOrdId()==null?"":cashs.getOrdId());
								datajson.put("transAmt", cashs.getTransAmt()==null?"":String.valueOf(cashs.getTransAmt()));
								datajson.put("avlBal", cashs.getAvlBal()==null?"":cashs.getAvlBal());
								datajson.put("summary", cashs.getSummary()==null?"":cashs.getSummary());
								dataarray.add(datajson);
							}
							errcode="0";
							errmsg="";
						}
						else
						{
							errcode="98";
							errmsg="查询结果为空";
						}
						jsonResult.put("data", dataarray);
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 红包列表接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/luckmoney")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String luckmoney(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				LuckyMoney lm=(LuckyMoney)JSONObject.toBean(JSONObject.fromObject(pa), LuckyMoney.class);
				String id=lm.getUid();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						Page page=new Page();
						page.setCurrPage(lm.getPage());
						pagenum=lm.getPage();
						page = luckyMoneyService.findPage(page,lm);
						JSONArray dataarray = new JSONArray();
						List<LuckyMoney> list=page.getResult();
						if(null!=list&&list.size()>0)
						{
							count=String.valueOf(page.getTotalPage());
							for(LuckyMoney luckmoney:list)
							{
								JSONObject datajson = new JSONObject();
								datajson.put("money", luckmoney.getMoney()==null?"":String.valueOf(luckmoney.getMoney()));
								datajson.put("investLimit", luckmoney.getInvestLimit()==null?"":String.valueOf(luckmoney.getInvestLimit()));
								datajson.put("type", luckmoney.getType()==null?"":luckmoney.getType());
								datajson.put("start_time",luckmoney.getStart_time()==null?"":luckmoney.getStart_time());
								datajson.put("end_time", luckmoney.getEnd_time()==null?"":luckmoney.getEnd_time());
								datajson.put("state", luckmoney.getState()==null?"":String.valueOf(luckmoney.getState()));
								datajson.put("id", luckmoney.getId()==null?"":String.valueOf(luckmoney.getId()));
								dataarray.add(datajson);
							}
							errcode="0";
							errmsg="";
						}
						else
						{
							errcode="98";
							errmsg="查询结果为空";
						}
						jsonResult.put("data", dataarray);
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 项目列表接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/productlist")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String productlist(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Project pro=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
				Page page=new Page();
				page.setCurrPage(pro.getPage());
				pagenum=pro.getPage();
				if(null==pro.getState())
				{
					pro.setState(-101);
				}
				page = projectService.findByPage(page, pro);
				JSONArray dataarray = new JSONArray();
				List<Project> list=page.getResult();
				if(null!=list&&list.size()>0)
				{
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
					Date date=new Date(); 
					String d=sdf.format(date); 
					count=String.valueOf(page.getTotalPage());
					for(Project p:list)
					{
						JSONObject datajson = new JSONObject();
						datajson.put("pid", p.getId()==null?"":String.valueOf(p.getId()));
						datajson.put("name", p.getName()==null?"":p.getName());
						datajson.put("comname", p.getDb_name()==null?"":p.getDb_name());
						datajson.put("repay_type",p.getRepay_type()==null?"":p.getRepay_type());
						datajson.put("summary",p.getSummary()==null?"":p.getSummary());
						
						datajson.put("now_money",p.get_now_money()==null?"":String.valueOf(p.get_now_money()));
						datajson.put("total_money",p.getTotal_money()==null?"":String.valueOf(p.getTotal_money()));
						datajson.put("rate",p.getRate()==null?"":String.valueOf(p.getRate()));
						datajson.put("time_limit",p.getTime_limit()==null?"":String.valueOf(p.getTime_limit()));
						datajson.put("state",p.getState()==null?"":String.valueOf(p.getState()));
						datajson.put("type",p.getType()==null?"":String.valueOf(p.getType()));
						
						datajson.put("lowest_money",p.getLowest_money()==null?"":String.valueOf(p.getLowest_money()));
						datajson.put("highest_money",p.getHighest_money()==null?"":String.valueOf(p.getHighest_money()));
						datajson.put("self_highest_money",p.getSelf_highest_money()==null?"":String.valueOf(p.getSelf_highest_money()));
						datajson.put("hongbaorate",p.getHongbaorate()==null?"":String.valueOf(p.getHongbaorate()));
						datajson.put("end_time",p.getEnd_time()==null?"":p.getEnd_time());
						datajson.put("server_time",d);
						dataarray.add(datajson);
					}
					errcode="0";
					errmsg="";
				}
				else
				{
					errcode="98";
					errmsg="查询结果为空";
				}
				jsonResult.put("data", dataarray);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 我的消息列表
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/mymessage")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String mymessage(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Message mess=(Message)JSONObject.toBean(JSONObject.fromObject(pa), Message.class);
				String id=mess.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						Page page=new Page();
						page.setCurrPage(mess.getPage());
						pagenum=mess.getPage();
						this.userService.mymessages("", String.valueOf(mess.getHaveLook()), loginOperatorList.get(0), page);
						JSONArray dataarray = new JSONArray();
						List<Message> list=page.getResult();
						if(null!=list&&list.size()>0)
						{
							count=String.valueOf(page.getTotalPage());
							for(Message m:list)
							{
								JSONObject datajson = new JSONObject();
								datajson.put("type", m.getType().getDictName()==null?"":m.getType().getDictName());
								datajson.put("content", m.getContent()==null?"":m.getContent());
								datajson.put("createTime", m.getCreateTime()==null?"":m.getCreateTime());
								datajson.put("haveLook",String.valueOf(m.getHaveLook())==null?"":String.valueOf(m.getHaveLook()));
								datajson.put("mid", m.getId()==null?"":m.getId());
								dataarray.add(datajson);
							}
							errcode="0";
							errmsg="";
						}
						else
						{
							errcode="98";
							errmsg="查询结果为空";
						}
						jsonResult.put("data", dataarray);
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 消息全部设置为已读接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/readallmessage")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String readallmessage(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Message mess=(Message)JSONObject.toBean(JSONObject.fromObject(pa), Message.class);
				String id=mess.getId();
				if(null==id||id.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					User user=new User();
					user.setId(id);
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						boolean tem=this.userService.setMesHaveLook(loginOperatorList.get(0));
						if(tem)
						{
							errcode="0";
							errmsg="";
							jsonResult.put("data", new JSONObject());
						}
						else
						{
							errcode="3";
							errmsg="已经全部读取";
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 消息的删除接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/delmessage")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String delmessage(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Message mess=(Message)JSONObject.toBean(JSONObject.fromObject(pa), Message.class);
				String id=mess.getId();
				String mid=mess.getMid();
				if(null==mid||mid.equals(""))
				{
					errcode="51";
					errmsg="消息ID为空";
				}
				else
				{
					if(null==id||id.equals(""))
					{
						errcode="50";
						errmsg="用户ID为空";
					}
					else
					{
						User user=new User();
						user.setId(id);
						String getUserHql = "from User where id=? ";
						List<User> loginOperatorList = this.userService.find(getUserHql,id);
						if(loginOperatorList!=null&&loginOperatorList.size()>0)
						{
							boolean tem=this.bizdataservice.bizDel("com.zc.bsm.pojo.Message", mid);
							if(tem)
							{
								errcode="0";
								errmsg="";
								jsonResult.put("data", new JSONObject());
							}
							else
							{
								errcode="3";
								errmsg="删除失败";
							}
						}
						else
						{
							errcode="2";
							errmsg="该用户不存在";
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 消息读取接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/readonemessage")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String readonemessage(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Message mess=(Message)JSONObject.toBean(JSONObject.fromObject(pa), Message.class);
				String id=mess.getId();
				String mid=mess.getMid();
				if(null==mid||mid.equals(""))
				{
					errcode="51";
					errmsg="消息ID为空";
				}
				else
				{
					if(null==id||id.equals(""))
					{
						errcode="50";
						errmsg="用户ID为空";
					}
					else
					{
						User user=new User();
						user.setId(id);
						String getUserHql = "from User where id=? ";
						List<User> loginOperatorList = this.userService.find(getUserHql,id);
						if(loginOperatorList!=null&&loginOperatorList.size()>0)
						{
							boolean tem=this.userService.setMesHaveLookbyId(mid);
							if(tem)
							{
								errcode="0";
								errmsg="";
								jsonResult.put("data", new JSONObject());
							}
							else
							{
								errcode="3";
								errmsg="读取失败";
							}
						}
						else
						{
							errcode="2";
							errmsg="该用户不存在";
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 投资详细接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/recorddetail")
	@NeedInterceptor//过滤器
	public ModelAndView recorddetail(String para){
		para=para.replaceAll(" ", "+");
		String pa=decodetojson(para);
		if(null!=pa)
		{
			Project pro=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
			String id=pro.getUid();
			if(null!=id&&!id.equals(""))
			{
				String getUserHql = "from User where id=? ";
				List<User> loginOperatorList = this.userService.find(getUserHql,id);
				if(loginOperatorList!=null&&loginOperatorList.size()>0)
				{
					ModelAndView model = new ModelAndView("userCenter/recordDetail");
					this.userService.recordDetail(model, loginOperatorList.get(0), pro.getId());
					return model;
				}
			}
		}
		return null;	
	}
	/**
	 * 合同查看接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/recordcontact")
	@NeedInterceptor//过滤器
	public ModelAndView recordcontact(String para,HttpServletRequest request){
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null!=pa)
			{
				Project pro=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
				String id=pro.getUid();
				
				if(null!=id&&!id.equals(""))
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,id);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						Project product = projectService.get(pro.getId());
						if((product.getState()==1||product.getState()==2)&&null!=product.getPstartTime()&&null!=product.getPendTime())
						{
							String tourl = request.getRequestURL().toString();
							String rootPath = request.getSession().getServletContext().getRealPath("/");
							String url=this.userService.createContract(loginOperatorList.get(0), pro.getId(), rootPath, tourl);
							if(url.equals("true"))
							{
								ModelAndView modelAndView = new ModelAndView("redirect:/"+pro.getId()+"/"+id+".html");
								return modelAndView;	
							}
						}
					}
				}
			}
		return null;	
	}
	/**
	 * 项目详情接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/productdetail")
	@NeedInterceptor//过滤器
	public ModelAndView productdetail(String para){
		para=para.replaceAll(" ", "+");
		String pa=decodetojson(para);
		if(null!=pa)
		{
			Project pro=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
			String uid=pro.getUid();
			pro = projectService.get(pro.getId());
			ModelAndView modelAndView = new ModelAndView("webapp/product_view");
			if(pro.getState()>=0)
			{
				if(null==uid||uid.equals(""))
				{
					this.projectService.view(modelAndView, null, pro);
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,uid);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						this.projectService.view(modelAndView, loginOperatorList.get(0), pro);
					}
					else
					{
						this.projectService.view(modelAndView, null, pro);
					}
				}
			}
			else
			{
				modelAndView = new ModelAndView("redirect:/index");
				return modelAndView;
			}
			return modelAndView;
		}
		return null;	
	}
	/**
	 * 投资接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/addtouzi")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String addtouzi(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Project p=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
				String uid=p.getUid();
				if(null==uid||uid.equals(""))
				{
					errcode="50";
					errmsg="用户ID为空";
				}
				else
				{
					String getUserHql = "from User where id=? ";
					List<User> loginOperatorList = this.userService.find(getUserHql,uid);
					if(loginOperatorList!=null&&loginOperatorList.size()>0)
					{
						if(null==loginOperatorList.get(0).getHuifuID()||loginOperatorList.get(0).getHuifuID().equals(""))
						{
							errcode="3";
							errmsg="请开通第三方支付";
						}
						else
						{
							String luckyid=null;
							if(null!=p.getLuckyId())
							{
								luckyid=p.getLuckyId().toString();
							}
							String[] arr=this.userService.initiativetender(luckyid, p.getId().toString(), loginOperatorList.get(0), p.getTransAmt().intValue());
							errcode=arr[2];
							errmsg=arr[0];
							JSONObject datajson = new JSONObject();
							datajson.put("url", arr[1]);
							jsonResult.put("data", datajson);
						}
					}
					else
					{
						errcode="2";
						errmsg="该用户不存在";
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 协议接口
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/xieyi")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String xieyi(String para,HttpServletRequest request){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				Project mess=(Project)JSONObject.toBean(JSONObject.fromObject(pa), Project.class);
				String uid=mess.getUid();
				long id=mess.getId();
				String flag=mess.getFlag();
				if(null==flag||flag.equals(""))
				{
					errcode="51";
					errmsg="标志位为空";
				}
				else
				{
					if(flag.equals("3"))
					{
						if(null==uid||uid.equals(""))
						{
							errcode="50";
							errmsg="用户ID为空";
						}
						else
						{
							String getUserHql = "from User where id=? ";
							List<User> loginOperatorList = this.userService.find(getUserHql,id);
							if(null==loginOperatorList||loginOperatorList.size()<=0)
							{
								errcode="2";
								errmsg="该用户不存在";
							}
							else
							{
								if(id<0)
								{
									errcode="52";
									errmsg="项目ID为空";
								}
								else
								{
									request.getSession().setAttribute("user",loginOperatorList.get(0));
								}
							}
						}
					}
					if(errcode.equals("99"))
					{
						//1注册协议 2红包介绍3购买协议4关于我们5屌丝币介绍
						String url="";
						if(flag.equals("1"))
						{//
							url="http://www.1717tou.com/huodong/register/index.html";
						}
						else if(flag.equals("2"))
						{//
							url="http://www.1717tou.com/huodong/register/hongbao.html";
						}
						else if(flag.equals("3"))
						{
							url="http://www.1717tou.com/user/contracts?id="+id;
						}
						else if(flag.equals("4"))
						{
							url="http://www.1717tou.com/huodong/register/about.html";
						}
						else if(flag.equals("5"))
						{
							url="http://www.1717tou.com/huodong/register/diaosibi.html";
						}
						JSONObject datajson = new JSONObject();
						datajson.put("url", url);
						jsonResult.put("data", datajson);
						errcode="0";
						errmsg="";
					}
					
				}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	/**
	 * 软件更新
	 * @param para
	 * @return
	 */
	@RequestMapping(value ="/softupdate")
	@ResponseBody//输出到页面
	@NeedInterceptor//过滤器
	public String softupdate(String para){
		try{
			para=para.replaceAll(" ", "+");
			String pa=decodetojson(para);
			if(null==pa)
			{
				errcode="1";
				errmsg="加密解密出现错误";
			}
			else
			{
				SoftUpdate mess=(SoftUpdate)JSONObject.toBean(JSONObject.fromObject(pa), SoftUpdate.class);
				JSONObject datajson = new JSONObject();
				 try{
				        if (StringUtils.isNotEmpty(mess.getType())&& StringUtils.isNotEmpty(mess.getVersion())) {
				            List<SoftUpdate> list = XmlUtil.readXMLFile(SignUtils.APK_PATH);
				            if (list != null && list.size() == 2) {
				                errcode = "0";
				                errmsg="";
				                if ("1".equals(mess.getType())) {
				                    SoftUpdate soft = list.get(0);
				                    if (soft.getVersion().compareTo(mess.getVersion())>0) {
				                        datajson.put("filepath", soft.getFilepath());
				                        datajson.put("isneed", "1");
				                        datajson.put("msg", soft.getMsg());
				                    }
				                    else {
				                    	datajson.put("filepath", "");
				                    	datajson.put("isneed", "0");
				                    }
				                }
				                else if ("2".equals(mess.getType())) {
				                    SoftUpdate soft = list.get(1);
				                    if (soft.getVersion().compareTo(mess.getVersion())>0) {
				                    	datajson.put("filepath", soft.getFilepath());
				                    	datajson.put("isneed", "1");
				                    	datajson.put("msg", soft.getMsg());
				                    }
				                    else {
				                    	datajson.put("filepath", "");
				                    	datajson.put("isneed", "0");
				                    }
				                }
				            }
				            jsonResult.put("data", datajson);
				        }
				        else
				        {
				        	errcode="2";
							errmsg="参数错误";
				        }
						} catch (Exception e) {
							errmsg="系统异常";
						    
						}
				
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			errmsg="系统异常";
		}
		return returnresult();	
	}
	
}
