package com.zc.bsm.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
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
import com.zc.base.po.Dict;
import com.zc.base.util.ContractProperties;
import com.zc.base.util.Page;
import com.zc.base.util.StringUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.Brokerage;
import com.zc.bsm.pojo.DynamicRateByProject;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Message;
import com.zc.bsm.pojo.PasswordSafeQuestion;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.DictService;
import com.zc.bsm.service.DynamicRateByProjectService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LoansSendService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.RepaymentSendService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.vo.returnVo.UserRegisterReturn;
import com.zc.bsm.webVo.Recordformobile;

@Controller
@RequestMapping("/user")
@Scope("prototype")
public class UserAction extends Action {
	// 前台提示信息
	private String msg;
	@Autowired
	public UserService userService;
	@Autowired
	public TenderService tenderService;
	@Autowired
	private  bizDataService bizdataservice;
    @Autowired
    private DictService dictService;
    @Autowired
	public EnchashmentService enchashmentService;
    @Autowired
    public ProjectService projectService;
    @Autowired
    public RepaymentSendService repaymentSendService;
    @Autowired
    public RepaymentPlanService repaymentPlanService;
    @Autowired
    public LoansSendService loansSendService;
    @Autowired
    public LuckyMoneyService luckyMoneyService;
    @Autowired
    public DynamicRateByProjectService dynamicRateByProjectService;
    @Autowired
    public  msgUtil msgutil;
    
    private User user;
    private Project project;
    private RepaymentSend repaymentSend = new RepaymentSend();
 	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@RequestMapping(value = "/contract")
 	@NeedInterceptor
	public String contract() {
		return "register/contract";
	}
	@RequestMapping(value = "/contract_mobile")
 	@NeedInterceptor
	public String contract_mobile() {
		return "register/contract_mobile";
	}
	@RequestMapping(value = "/saveUser")
 	@NeedInterceptor
	public String saveUser(User user,HttpSession  session, HttpServletRequest request,
			String code, ModelMap modelMap) {
		message mes = new message();
		try {
			//防止get提交
			if(request.getMethod().equalsIgnoreCase("get")){
				return "register/register";
			}
			// 验证码验证
			String sVCode = (String) session.getAttribute("identify");
			if (!sVCode.equals(code)) {
				mes.setMsgCode("errorCode");
				mes.setMsgString("验证码输入错误,请重新输入");
				modelMap.put("message", mes);
				return "register/register";
			}
			//查询是否数据库中已存在  防止非法提交
			String 	getUserHql = "from User where username=?";
		    List<User>	loginOperatorList = this.userService.find(getUserHql,
					user.getUsername());
			if(loginOperatorList!=null&&loginOperatorList.size()>0){
				mes.setMsgCode("exsit");
				mes.setMsgString("用户已存在,请重新输入用户名");
				modelMap.put("message", mes);
				return "register/register";
			}
			
		     String   pwd=userService.revotePwd(user.getPwd(),user.getUsername());
		     String   md5Pwd=userService.md5(pwd);
		     //登陆密码
		     user.setPwd(md5Pwd);
		     //支付密码默认与登陆密码一样
		     user.setPaypwd(md5Pwd);
		     //推荐人信息转换
		     if(user.getAgent()!=null&&!user.getAgent().equals("")){
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
		    		userService.addScore("6", agent,session, null, "推荐用户注册");
		    	}else{
		    		user.setAgent("");
		    	}
		     }
	
		     bizdataservice.bizSave(User.class, user);
		     if(user.getType()==2){
		    	 user.setAdvisor(user.getId());
		     }
		     bizdataservice.saveOrUpdate(user);
		     session.setAttribute("user", user);
		     userService.addScore("10", user, session, null, "注册赠送100屌丝币");
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		mes.setMsgCode(SUCCESS);
		modelMap.put("message", mes);
		 //赠送红包
		 LuckyMoney lucky = new LuckyMoney();
		 lucky.setComeFrom(0l);
		 //
		 luckyMoneyService.saveLuckyMoney(lucky, user);
	
		return "register/validationPhone";
	}
	//身份认证
	@RequestMapping(value = "/identifAuthent")
	public String identifAuthent(User user,HttpSession  session,ModelMap modelMap){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date nowdate = new Date();
		String realNameAuthentDate=format
		.format(nowdate);
		PropertyUtils.setProperty(user, "realNameAuthentDate",realNameAuthentDate);
	     user= (User) bizdataservice.bizSave(User.class, user);
		 session.setAttribute("user", user);
		} catch (Exception e) {
		e.printStackTrace();
		}
		modelMap.put("callbackurl", "user/authentList");
		return "userCenterok";
	}
	//手机认证
	@RequestMapping(value = "/phoneAuthent")
	public String phoneAuthent(User user, HttpSession  session,ModelMap map,String  phoneCode){
		try {
			message mes = new message();
			 String bindPhoneCode=(String) session.getAttribute("bindPhoneCode");
			 if(bindPhoneCode==null){
				    mes.setMsgCode("errorCode");
					mes.setMsgString("非法操作");
					map.put("message", mes);
				    return ERROR;
			 }
			 if(!phoneCode.equals(bindPhoneCode)){
				    mes.setMsgCode("errorCode");
					mes.setMsgString("验证码输入错误");
					map.put("message", mes);
				    return ERROR;
			 }
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 Date nowdate = new Date();
		String realNameAuthentDate=format
		.format(nowdate);
		PropertyUtils.setProperty(user, "phoneAuthentDate",realNameAuthentDate);
		user=(User) bizdataservice.bizSave(User.class, user);
		 session.setAttribute("user", user);
		} catch (Exception e) {
		e.printStackTrace();
		}
		map.put("callbackurl", "user/authentList");
		return "userCenterok";
	}
	//更改登录密码
	@RequestMapping(value = "/changePwd")
	public String changePwd(User user,String newpwd, HttpSession  session,ModelMap map){
		try {
			String oldpwdmd5=this.userService.md5(user.getPwd());
			User peruser=(User) session.getAttribute("user");
			if(!peruser.getPwd().equals(oldpwdmd5)){
				message me=new message();
				me.setMsgCode("errorOld");
				me.setMsgString("原密码错误");
				session.setAttribute("message", me);
				return "error";
			}
			 String   pwd=userService.revotePwd(newpwd,user.getUsername());
			String   newmd5Pwd=userService.md5(pwd);
	        user.setPwd(newmd5Pwd);
	    	user=(User) bizdataservice.bizSave(User.class, user);
			 session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			message me=new message();
			me.setMsgCode("errorOld");
			me.setMsgString("修改密码出错,请稍后重试,或者联系客服");
			session.setAttribute("message", me);
			return "error";
		}
		map.put("callbackurl", "user/authentList");
		return "userCenterok";
	}
	//修改支付密码
	@RequestMapping(value = "/setPayPassword")
	public String setPayPassword(User user,String newpwd, HttpSession  session,ModelMap map){
	   try {
		   String oldpwdmd5=this.userService.md5(user.getPaypwd());
			User peruser=(User) session.getAttribute("user");
			if(!peruser.getPaypwd().equals(oldpwdmd5)){
				message me=new message();
				me.setMsgCode("errorOld");
				me.setMsgString("原密码错误");
				session.setAttribute("message", me);
				return "error";
			}
	   String   pwd=userService.revotePwd(newpwd,user.getUsername());
	   String   md5Pwd=userService.md5(pwd);
		        user.setPaypwd(md5Pwd);
		    	user=(User) bizdataservice.bizSave(User.class, user);
				 session.setAttribute("user", user);
		} catch (Exception e) {
		e.printStackTrace();
		}
		map.put("callbackurl", "user/authentList");
		return "userCenterok";
	}
	
	//重设支付密码 遗忘密码时
	 @RequestMapping(value = "/resetPayPassword")
	 public String resetPayPassword(User user, HttpSession  session,ModelMap map){
	   try {
		        String   pwd=userService.revotePwd(user.getPaypwd(),user.getUsername());
	            String   md5Pwd=userService.md5(pwd);
		        user.setPaypwd(md5Pwd);
		    	user=(User) bizdataservice.bizSave(User.class, user);
				 session.setAttribute("user", user);
		} catch (Exception e) {
		e.printStackTrace();
		}
		map.put("callbackurl", "user/authentList");
		return "userCenterok";
	}
	
	@RequestMapping(value = "/checkUser")
	@NeedInterceptor
	@ResponseBody
	/**
	 * 检查是否存在相同登录名
	 */
	public String checkUser(String username) {
		String getUserHql = "from User where username=? or phone=?";
		List<User> loginOperatorList = this.userService.find(getUserHql,
				username,username);
		if (loginOperatorList.size() > 0) {
			return "exsit";
		} else {
			return "noexsit";
		}
	}
	@RequestMapping(value = "/checkagent")
	@NeedInterceptor
	@ResponseBody
	/**
	 * 检查是否存在相同手机
	 */
	public String checkagent(String agent) {
		String getUserHql = "from User where phone=?";
		List<User> loginOperatorList = this.userService.find(getUserHql,
				agent);
		if (loginOperatorList.size() > 0) {
			return "exsit";
		} else {
			return "noexsit";
		}
	}
	@RequestMapping(value = "/checkphone")
	@NeedInterceptor
	@ResponseBody
	/**
	 * 检查是否存在相同手机
	 */
	public String checkphone(String phone) {
		String getUserHql = "from User where phone=?  or username=?";
		List<User> loginOperatorList = this.userService.find(getUserHql,
				phone,phone);
		if (loginOperatorList.size() > 0) {
			return "exsit";
		} else {
			return "noexsit";
		}
	}
	@RequestMapping(value = "/checkpwd")
	@ResponseBody
	/**
	 * 检查登陆密码是否正确
	 */
	public String checkpwd(String pwd,HttpSession  session) {
		   String pwdmd5=this.userService.md5(pwd);
		User peruser=(User) session.getAttribute("user");
		if(peruser.getPwd().equals(pwdmd5)){
			return "0";
		}else{
			return "1";
		}
	}
	@RequestMapping(value = "/checkpaypwd")
	@ResponseBody
	/**
	 * 检查支付密码是否正确
	 */
	public String checkpaypwd(String  paypwd,HttpSession  session) {
		   String pwdmd5=this.userService.md5(paypwd);
		User peruser=(User) session.getAttribute("user");
		if(peruser.getPaypwd().equals(pwdmd5)){
			return "0";
		}else{
			return "1";
		}
	}
	@RequestMapping(value = "/register")
	@NeedInterceptor
	/**
	 * 跳转到注册页
	 */
	public String register(ModelMap map,String uid) {
		map.put("type", 1);
		if(uid!=null&&!uid.equals("")){
			User user = (User)this.bizdataservice.bizfindbyid(User.class, uid);
			map.put("_user", user);
		}
		map.put("_time", Math.random());
		return "register/register";
	}
	@RequestMapping(value = "/paypwdphoneCode")
	@ResponseBody
	/**
	 * 获取支付验证码
	 */
	public String paypwdphoneCode(String phone, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user=(User) session.getAttribute("user");
		String code = StringUtil.getRandom(6);
		String result = ERROR;
		try {
			String action="设置支付密码";
			Map<String,String> params=new HashMap<String, String>();
			  params.put("action", action);
			  params.put("code",code);
			boolean ispush=	msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
			if (ispush) {
				result = SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("phonecode", code);
		return result;
	}

	@RequestMapping(value = "/getPhoneCode")
	@NeedInterceptor
	@ResponseBody
	/**
	 * 获取注册验证吗
	 */
	public String phoneCode(String phone, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String code = StringUtil.getRandom(6);	
		String result = ERROR;
		try {
			String getUserHql = "from User where phone=?";
			List<User> loginOperatorList = this.userService.find(getUserHql,
					phone);
			if (loginOperatorList.size() > 0) {
				
			} else {
				String action="用户注册";
				User tmpuser=new User();
				tmpuser.setPhone(phone);
				Map<String,String> params=new HashMap<String, String>();
				  params.put("action", action);
				  params.put("code",code);
				boolean ispush=	msgutil.sendmessage("20",new String[]{"phone"}, tmpuser,  params);
				if (ispush) {
					result = SUCCESS;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("phonecode", code);
		return result;
	}
	/**
	 * 获取绑定手机的验证码
	 */
	@RequestMapping(value = "/bindPhoneCode")
	@ResponseBody
	public String bindPhoneCode(HttpSession session, HttpServletRequest request,String phone) {
		String code = StringUtil.getRandom(6);
	
		String result = ERROR;
		try {
			User user = this.userService.findByName_Phone(phone);
			if(user==null||user.equals("null")||user.equals("")){
				User tmpuser= new User();
				tmpuser.setPhone(phone);
				String action="绑定手机";
				Map<String,String> params=new HashMap<String, String>();
				  params.put("action", action);
				  params.put("code",code);
				  boolean ispush=	msgutil.sendmessage("20", new String[]{"phone"}, tmpuser, params);
					if (ispush) {
						result = SUCCESS;
					}
			}else{
				result = "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("bindPhoneCode", code);
		return result;
	}
	/**
	 * 获取债权转让的验证码
	 */
	@RequestMapping(value = "/creditAssignPhoneCode")
	@ResponseBody
	public String creditAssignPhoneCode(HttpSession session, HttpServletRequest request) {
		User user=(User) session.getAttribute("user");
		String code = StringUtil.getRandom(6);

		String result = ERROR;
		try {
			String action="债权转让";
			Map<String,String> params=new HashMap<String, String>();
			  params.put("action", action);
			  params.put("code",code);
			boolean ispush=	msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
			if (ispush) {
				result = SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("creditAssignPhoneCode", code);
		return result;
	}
	/**
	 * 获取取现的验证码
	 */
	@RequestMapping(value = "/cashPhoneCode")
	@ResponseBody
	public String cashPhoneCode(HttpSession session, HttpServletRequest request) {
		User user=(User) session.getAttribute("user");
		String code = StringUtil.getRandom(6);

		String result = ERROR;
		try {
			
			String action="取现";
			Map<String,String> params=new HashMap<String, String>();
			  params.put("action", action);
			  params.put("code",code);
			boolean ispush=	msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
			if (ispush) {
				result = SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("cashPhoneCode", code);
		return result;
	}
	@RequestMapping(value = "/checkPhoneCode")
	@NeedInterceptor
	public String checkPhoneCode(User user,String code, HttpServletRequest request,HttpSession session) {
		message mes = new message();
		
		try {
			String sessionPhoneCode = (String) session
					.getAttribute("phonecode");
			session.setAttribute("mes", mes);
			if (sessionPhoneCode == null) {
				mes.setMsgCode("CodeTimeOut");
				mes.setMsgString("验证码已失效,请重新获取");
				return "/register/validationPhone";
			}
			if (!code.equals(sessionPhoneCode)) {
				mes.setMsgCode("errorCode");
				mes.setMsgString("验证码输入错误,请重新输入");
				return "/register/validationPhone";
			}
			if(user==null||user.getId()==null||user.getId().equals("")||user.getPhone().equals("")){
				mes.setMsgCode("error");
				mes.setMsgString("验证出错,请稍后重试");
				return "/register/validationPhone";
			}
		 user=	(User) this.bizdataservice.bizSave(User.class,user);
		} catch (Exception e) {
			e.printStackTrace();
			mes.setMsgCode("error");
			mes.setMsgString("验证出错,请稍后重试");
			return "login";
		}
		mes.setMsgCode("success");
		//发送手机短信

	    try {
		   //注册后发消息
		  if(user.getType()!=null&&user.getType()==2){
			  //发送经纪人消息
			  msgutil.sendmessage("4",new String[]{"phone","webmeg"}, user,  null);
		  }else{
			  //发送普通消息
			  msgutil.sendmessage("7",new String[]{"phone","webmeg"}, user,  null);
		  }
		  if(user.getAgent()!=null&&!user.getAgent().equals("")){
			  User agent=this.userService.get(user.getAgent());
			  Map<String,String> parms=new HashMap<String, String>();
			  parms.put("phone", user.getPhone());
			  msgutil.sendmessage("14",new String[]{"phone","webmeg"}, agent,  parms);
		  }
		  if(user.getAdvisor()!=null&&!user.getAdvisor().equals("")){
			  User advisor=this.userService.get(user.getAdvisor());
			  Map<String,String> parms=new HashMap<String, String>();
			  parms.put("phone", user.getPhone());
			  msgutil.sendmessage("18",new String[]{"phone","webmeg"}, advisor,  parms);
		  }
		  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/register/registerFinish";
	}
	@RequestMapping(value = "/pwdsafe")
	public String pwdsafe(ModelMap modelMap,HttpSession  session) {
		try {
	    User peruser=(User) session.getAttribute("user");
		List<Dict> dictList=dictService.findByDictype(1);
		modelMap.put("questions", dictList);
		//如果已经有密保 回填
		List<Object> ques= this.bizdataservice.find("from PasswordSafeQuestion where user.id=?",peruser.getId());
		if(ques!=null&&ques.size()>0){
			modelMap.put("question", ques.get(0));
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saveCenter/pwdsafeQuestion";
	}
	//保存密保
	@RequestMapping(value = "/savePwdQuest")
	public String savePwdQuest(PasswordSafeQuestion passwordSafeQuestion,HttpSession session,ModelMap map) {
		try {
		this.bizdataservice.bizSave(PasswordSafeQuestion.class, passwordSafeQuestion);
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("callbackurl", "user/authentList");
		return "userCenterok";
	}
    @RequestMapping(value = "/userCenter")
    @NeedOpenHFPayInterceptor
	public String userCenter(HttpSession session,ModelMap map){
    	//判断汇付账号是否开通
    	  user=(User) session.getAttribute("user");
    	  if(user.getHuifuID()==null||user.getHuifuID().equals("")){
    		  return "saveCenter/slmpleuserCenter";
    	  }
    	return "saveCenter/userCenter";
	}
    @RequestMapping(value = "/slmpleuserEstate")
    @NeedOpenHFPayInterceptor
    public  String slmpleuserEstate(HttpSession session,ModelMap map){
    	try{
    		user=  (User) session.getAttribute("user");
    	 //红包数量
        String hbnumHql="select count(id) from LuckyMoney where uid=? and flag=0 and state=0";
        Object hbnum =  this.tenderService.getRowCount(hbnumHql,user.getId());
        map.put("hbnum",hbnum==null?"0":hbnum.toString());
        //积分
        map.put("score",user.getScore()==null?"0":user.getScore().toString());
        int level=0;
        //实名认证
       	 User peruser=(User) session.getAttribute("user");
       	 if(peruser.getRealNameAuthentDate()!=null){
       		 map.put("realNameAuthent", true);
       		level++;
       	 }
       	//手机号
       	  if(peruser.getPhone()!=null){
       		 map.put("phone", true);
       		level++;
       	 }
       	  //是否设置密保
       	//如果已经有密保 回填
     		List<Object> ques= this.bizdataservice.find("from PasswordSafeQuestion where user.id=?",peruser.getId());
     		if(ques!=null&&ques.size()>0){
     			map.put("question", true);
     			level++;
     		}
     		map.put("ulevel", level);
     	//是否签到
     		if(user.getLastsignDate()==null||user.getLastsignDate().equals("")){
     			map.put("signed", false);
     		}else{
     			 try {
					Date lastsigndate=StringUtil.StringToDate(user.getLastsignDate(), "yyyy-MM-dd");
					if(lastsigndate.before(StringUtil.StringToDate(StringUtil.DateToString(new Date(),"yyyy-MM-dd"), "yyyy-MM-dd"))){
					 map.put("signed", false);
					}else{
				     map.put("signed", true);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
        //时段
        Date date=new Date();
        int hour=date.getHours();
        //早上
        if(6<=hour&&hour<12)
        	map.put("period", "早上好");
        //中午
        if(12<=hour&&hour<13)
        	map.put("period", "中午好");
        //下午
        if(13<=hour&&hour<19)
        	map.put("period", "下午好");
        //晚上
        if(19<=hour&&hour<24)
        	map.put("period", "晚上好");
        if(0<=hour&&hour<6)
        	map.put("period", "晚上好");
        return "saveCenter/slmpleuserEstate";
    	
    }
    @RequestMapping(value = "/userEstate")
    public  String userEstate(HttpSession session,ModelMap map){
//    	try {
    	//余额
//    	user=  (User) session.getAttribute("user");
//    	QueryBalanceBgReturn	qbr=	enchashmentService.getBalanceObj(user); 
//    	map.put("AvlBal", qbr.getAvlBal());
//    	map.put("AvlBalNumber", qbr.getAvlBal().replace(",", ""));
//    	map.put("FreBal", qbr.getFrzBal());
//    	//总资产 账户余额+投标被扣款的金额+放贷的钱
//    	Double totalAmt=0.00;
//    	
//    	/*String hql="select sum(transAmt)  from Tender as e where (e.project.state=0 or e.project.state=1 or e.project.state=3)  and flag=? and usrCustId=?";
//    	//计算标的投资总额
//        Object transAmtCount =this.tenderService.getRowCount(hql,0,user.getHuifuID());
//        Double bd=Double.parseDouble(transAmtCount==null?"0":transAmtCount.toString()) ;*/
//        
//       //获取放款后未还款
//        String  protransAmtHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0 and (p.state=0  or p.state=3) and usrCustId=?";
//        Object protransAmt =  this.tenderService.getRowCount(protransAmtHql,user.getHuifuID());
//        Double probd=Double.parseDouble(protransAmt==null?"0":protransAmt.toString()) ;
//        //未还完的还款
//        String  dsbtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0";
//        Object dsbprotransAmt =  this.bizdataservice.getRowCount(dsbtransAmtHql);
//        Double dsbbd=Double.parseDouble(dsbprotransAmt==null?"0":dsbprotransAmt.toString()) ;
//        Double bd=probd+dsbbd;
//  
//    	/*//待回款的收益 查找还款计划 逻辑为 查找还款计划表中  产品状态为还款中 并且 还款计划为等待还款的金额，并且汇付号为当前用户的资金总和
//        String planhql="select sum(e.repayMoney) from RepaymentPlan as e,Project as p  where   e.projectId=p.id and e.usrCustId=? and p.state=?   and    e.state=? ";
//        Object planAmtCount=this.tenderService.getRowCount(planhql,user.getHuifuID(),1,-1);
//        Double bdplan=Double.parseDouble(planAmtCount==null?"0":planAmtCount.toString());*/
//        Double acctBalDb=Double.parseDouble(qbr.getAvlBal().replace(",", ""));
//        totalAmt=acctBalDb+bd;
//        map.put("totalAmt",StringUtil.DoubletoDecimal(totalAmt));
//        String dbstring=StringUtil.DoubletoDecimal(bd);
//        
//        map.put("pbd",dbstring);
//        map.put("pbdNumber",dbstring.replace(",", ""));
//    	 //累计净收益 逻辑为 查找还款计划表中   还款计划为还款成功的利息，并且汇付号为当前用户的资金 +已使用的红包金额
//        //利息部分
//        String purehql="select sum(repayMoney)  from RepaymentPlan as e  where e.flag=0 and e.usrCustId=? and  e.state=? and e.repayCount !=0 ";
//        Object pureAmtCount=this.tenderService.getRowCount(purehql,user.getHuifuID(),1);
//        Double pureAmt=Double.parseDouble(pureAmtCount==null?"0":pureAmtCount.toString());
//        //红包部分
//    	String hongBaohql="select sum(money)  from LuckyMoney where state=3  and flag=0 and uid=?";    	
//        Object hongBaoAmtCount =this.tenderService.getRowCount(hongBaohql,user.getId());
//        Double hb=Double.parseDouble(hongBaoAmtCount==null?"0":hongBaoAmtCount.toString()) ;
//        
//        map.put("pureplan",StringUtil.DoubletoDecimal(pureAmt+hb));
//        
//        //查询投资项目数
//        //获取非屌丝宝持有项目数目和屌丝宝投标成功后未还款数目
//        String  procountHql="select count(DISTINCT t.project) from Tender as t join  t.project as p  where t.flag=0 and p.flag = 0 and (p.type!=3 and ( (p.state=0 or p.state=1 or p.state=3)) or (p.type=3 and (p.state=0 or p.state=3)) ) and usrCustId=?";
//        Object procount =  this.tenderService.getRowCount(procountHql,user.getHuifuID());
//        //未还完的屌丝宝
//        String  dsbcountHql="select  p.id from RepaymentPlan as r ,Project as p where r.state=-1 and r.flag=0 and p.flag=0 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and p.type=3 group by p.id";
//        Object dsbprocount =  this.bizdataservice.findByHql(dsbcountHql).size();
//        map.put("procount",Integer.parseInt(procount.toString())+Integer.parseInt(dsbprocount.toString()));
//        
//        //各产品投资总额
//         //屌丝宝       
//        //获取屌丝宝投标成功后未还款
//        String  diaositransAmtHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.type=3 and (p.state=0 or p.state=3)) and usrCustId=? and p.type=3";
//        Object diaositransAmt =  this.tenderService.getRowCount(diaositransAmtHql,user.getHuifuID());
//        Double diaosi=Double.parseDouble(diaositransAmt==null?"0":diaositransAmt.toString()) ;
//         //加上未还完的屌丝宝
//        String  diaosi2transAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=3";
//        Object diaosi2transAmt =  this.bizdataservice.getRowCount(diaosi2transAmtHql);
//        Double diaosi2=Double.parseDouble(diaosi2transAmt==null?"0":diaosi2transAmt.toString()) ;
//        map.put("dsprocount",diaosi+diaosi2);
//        
//        //资涨通投资未生成还款
//        String  zizhangprocountHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.state=0  or p.state=3)  and usrCustId=? and p.type=1";
//        Object zizhangprocount =  this.tenderService.getRowCount(zizhangprocountHql,user.getHuifuID());
//        Double zizhang=Double.parseDouble(zizhangprocount==null?"0":zizhangprocount.toString()) ;
//        //未还完的还款资涨通
//        String  zizhangtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=1";
//        Object zizhangtransAmt =  this.bizdataservice.getRowCount(zizhangtransAmtHql);
//        Double zizhang2=Double.parseDouble(zizhangtransAmt==null?"0":zizhangtransAmt.toString()) ;
//        map.put("zizhangprocount",zizhang+zizhang2);
//
//        //信满盈投资未生成还款
//        String  xmprocountHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.state=0  or p.state=3)  and usrCustId=? and p.type=0";
//        Object xmprocount =  this.tenderService.getRowCount(xmprocountHql,user.getHuifuID());
//        Double xm=Double.parseDouble(xmprocount==null?"0":xmprocount.toString()) ;
//        //未还完的还款
//        String  xmtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=0";
//        Object xmtransAmt =  this.bizdataservice.getRowCount(xmtransAmtHql);
//        Double xm2=Double.parseDouble(xmtransAmt==null?"0":xmtransAmt.toString()) ;
//        map.put("xmprocount",xm+xm2);
//
//        //金多宝投资未生成还款
//        String  jdbprocountHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.state=0  or p.state=3)  and usrCustId=? and p.type=2";
//        Object jdbprocount =  this.tenderService.getRowCount(jdbprocountHql,user.getHuifuID());
//        Double jdb=Double.parseDouble(jdbprocount==null?"0":jdbprocount.toString()) ;
//        //未还完的还款
//        String  jdbtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=2";
//        Object jdbtransAmt =  this.bizdataservice.getRowCount(jdbtransAmtHql);
//        Double jdb2=Double.parseDouble(jdbtransAmt==null?"0":jdbtransAmt.toString()) ;
//        map.put("jdprocount",jdb+jdb2);
//
//        //红包数量
//        String hbnumHql="select count(id) from LuckyMoney where uid=? and flag=0 and state=0";
//        Object hbnum =  this.tenderService.getRowCount(hbnumHql,user.getId());
//        map.put("hbnum",hbnum==null?"0":hbnum.toString());
//        //积分
//        map.put("score",user.getScore()==null?"0":user.getScore().toString());
//        int level=0;
//        //实名认证
//       	 User peruser=(User) session.getAttribute("user");
//       	 if(peruser.getRealNameAuthentDate()!=null){
//       		 map.put("realNameAuthent", true);
//       		level++;
//       	 }
//       	//手机号
//       	  if(peruser.getPhone()!=null){
//       		 map.put("phone", true);
//       		level++;
//       	 }
//       	  //是否设置密保
//       	//如果已经有密保 回填
//     		List<Object> ques= this.bizdataservice.find("from PasswordSafeQuestion where user.id=?",peruser.getId());
//     		if(ques!=null&&ques.size()>0){
//     			map.put("question", true);
//     			level++;
//     		}
//     		map.put("ulevel", level);
//     	//是否签到
//     		if(user.getLastsignDate()==null||user.getLastsignDate().equals("")){
//     			map.put("signed", false);
//     		}else{
//     			 try {
//					Date lastsigndate=StringUtil.StringToDate(user.getLastsignDate(), "yyyy-MM-dd");
//					if(lastsigndate.before(StringUtil.StringToDate(StringUtil.DateToString(new Date(),"yyyy-MM-dd"), "yyyy-MM-dd"))){
//					 map.put("signed", false);
//					}else{
//				     map.put("signed", true);
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//     		}
//    	} catch (Exception e) {
//			e.printStackTrace();
//		}
//        //时段
//        Date date=new Date();
//        int hour=date.getHours();
//        //早上
//        if(6<=hour&&hour<12)
//        	map.put("period", "早上好");
//        //中午
//        if(12<=hour&&hour<13)
//        	map.put("period", "中午好");
//        //下午
//        if(13<=hour&&hour<19)
//        	map.put("period", "下午好");
//        //晚上
//        if(19<=hour&&hour<24)
//        	map.put("period", "晚上好");
//        if(0<=hour&&hour<6)
//        	map.put("period", "晚上好");
    	
    	user=  (User) session.getAttribute("user");
    	this.userService.userEstate(map, user);
        return "saveCenter/userEstate";
    }
    
    @RequestMapping(value = "/authentList")
    public  String authentList(HttpSession  session,ModelMap map){
    	//实名认证
    	 User peruser=(User) session.getAttribute("user");
    	 if(peruser.getRealNameAuthentDate()!=null&&!peruser.getRealNameAuthentDate().equals("")){
    		 map.put("realNameAuthent", true);
    	 }
    	//汇付账号
   	  if(peruser.getHuifuAccount()!=null&&!peruser.getHuifuAccount().equals("")){
 		 map.put("huifuAccount", true);
 	   }
 	  //手机认证
 	   if(peruser.getPhone()!=null&&!peruser.getPhone().equals("")){
 		  map.put("phoneAuthent", true);
 	    }
    	  //是否设置密保
    	//如果已经有密保 回填
  		List<Object> ques= this.bizdataservice.find("from PasswordSafeQuestion where user.id=?",peruser.getId());
  		if(ques!=null&&ques.size()>0){
  			map.put("question", true);
  		}
    	return "saveCenter/authentList";
    }
	
	@RequestMapping(value = "/gotophoneCheck")
	public String gotophoneCheck(){
		return "register/validationPhone";
	}

	/**
	 * 找回密码
	 * @return
	 */
	@RequestMapping(value = "/toResetPwd")
	@NeedInterceptor
	public String toResetPwd(){
		return "register/askResetPwd";
	}
	/**
	 * 申请找回密码
	 * @return
	 */
	@RequestMapping(value = "/askResetPwd")
	@ResponseBody
	@NeedInterceptor
	public String askResetPwd(String phone,HttpSession session){
		User user = this.userService.findByName_Phone( phone);
		String msgs = "-1";
		if(user!=null){

			try {
				String code = StringUtil.getRandom(6);
				String action="找回密码";
				Map<String,String> params=new HashMap<String, String>();
				  params.put("action", action);
				  params.put("code",code);
				boolean ispush=	msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
				if (ispush) {
					msgs = "1";
					session.setAttribute("pwdcode", code);
					//System.out.println(code);
				}else{
					msgs = "-2";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return msgs;
	}
	@RequestMapping(value = "/doAskResetPwd")
	@NeedInterceptor
	public ModelAndView doAskResetPwd(String username,String phone,String code,HttpSession session){
		ModelAndView model = null;
		User user = this.userService.findByName_Phone(phone);
		Object _code = session.getAttribute("pwdcode");
		if(code.equals(_code)&&user!=null){
			session.setAttribute(session.getAttribute("pwdcode").toString(),user.getId());
			model = new ModelAndView("redirect:/user/reset");
			return model;
		}else{
			model = new ModelAndView("redirect:/user/toResetPwd");
			model.addObject("msgss",user==null?"-1":"-2");
			return model;
		}
	}
	@RequestMapping(value = "/reset")
	@NeedInterceptor
	public ModelAndView reset(String uid){
		ModelAndView model = new ModelAndView("register/reset");
		return model;
	}
	@RequestMapping(value = "/doReset")
	@NeedInterceptor
	public String doReset(String uid,String password1,HttpSession session){
		uid = session.getAttribute(session.getAttribute("pwdcode").toString()).toString();

		User user = userService.get(uid);
		user.setPwd(userService.md5(password1));
		userService.saveOrUpdate(user);
		session.removeAttribute(session.getAttribute("pwdcode").toString());
		session.removeAttribute("pwdcode");
		return "register/resetOk";
	}
	/**
	 * 申请找回支付密码
	 * @return
	 */
	@RequestMapping(value = "/getlostPayCode")
	@ResponseBody
	public String getlostPayCode(String phone,HttpSession session){
		String msgs = "-1";

			try {
				User user = this.userService.findByName_Phone(phone);
			 if(user!=null){
				String code = StringUtil.getRandom(6);
				String action="找回支付密码";
				Map<String,String> params=new HashMap<String, String>();
				  params.put("action", action);
				  params.put("code",code);
				boolean ispush=	msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
				if (ispush) {
					msgs = "1";
					session.setAttribute("getlostPayCode", code);
					System.out.println(code);
				}else{
					msgs = "-2";
				}
			 }else{
				 msgs="-3";
			 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		return msgs;
	}
	/**
	 * 申请找回支付密码
	 * @return
	 */
	@RequestMapping(value = "/dolostPayCode")
	public String dolostPayCode(User u,String phone,HttpSession session,String code,ModelMap map){
			try {
				message me=new message();
				map.put("message", me);
				User user = this.userService.findByPwd_Phone(userService.md5(u.getPwd()), phone);
				if(user==null){
					me.setMsgString("登陆密码错误");
					return ERROR;
				}
				String getlostPayCode=	(String) session.getAttribute("getlostPayCode");
				if(getlostPayCode==null){
					me.setMsgString("请先获取验证码");
					return ERROR;
				}
				if(getlostPayCode.equals(code)){
				    return	"saveCenter/reset";
				}else{
					me.setMsgString("验证码输入错误");
					return ERROR;
				 }
			} catch (Exception e) {
				e.printStackTrace();
		 }
	    return ERROR;	
	}
	
	@RequestMapping(value = "/recharge")
	//充值页面
	public String recharge(HttpServletRequest request,ModelMap map,HttpSession session){
	    User user=(User) session.getAttribute("user");
		map.put("userid", user.getId());
		map.put("huifuID", user.getHuifuID());
		map.put("gateBusiId","B2C");
        return "userCenter/recharge";
	}
	// 查看充值记录
	@RequestMapping(value = "/rechargelist")
	public String rechargelist(HttpSession session, ModelMap map, Page page,
			String start_time, String end_time) {
		// 当前用户
		User peruser = (User) session.getAttribute("user");
		String usrcusid = peruser.getHuifuID();
		if (page == null) {
			page = new Page<Enchashment>();
		}
		String hql = "from RechargeOrder where huifuID='" + usrcusid + "'";
		if (start_time != null && !start_time.equals("")) {
			hql += " and createTime>'" + start_time+"'";
		}
		if (end_time != null && !end_time.equals("")) {
			hql += " and createTime<'" + end_time + " 23:59:59'";
		}
		hql+="order by createTime desc";
		page.setHql(hql);
		page = this.bizdataservice.findByPage(page);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("page", page);
		QueryBalanceBgReturn qbr=enchashmentService.getBalanceObj(peruser);
		map.put("AvlBal", qbr.getAvlBal());
		map.put("AcctBal", qbr.getAcctBal());
		return "userCenter/rechargelist";
	}
	// 查看绑卡记录
	@RequestMapping(value = "/buildBanklist")
	public String buildBanklist(HttpSession session, ModelMap map,Page page) {
		// 当前用户
		User peruser = (User) session.getAttribute("user");
		if(page==null){
			page=new Page<Object>();
		}
		page.setRows(3);
		page.setHql("from Bankcard where user.id='"+peruser.getId()+"'");
		page=this.bizdataservice.findByPage(page);
	  return "userCenter/buildBanklist";
	}
	// 查看交易记录
	/**start_time :开始时间
	 * end_time 结束时间
	 * transtype： 交易类型
	 * dateInterval :间隔周期，最近一周，最近一个月，最近3个月
	 */
	@RequestMapping(value = "/translist")
	public String translist(HttpSession session, ModelMap map,Page page,
			String start_time, String end_time,String transtype,String dateInterval) {
		// 当前用户
		User peruser = (User) session.getAttribute("user");
//		String usrcusid = peruser.getHuifuID();
//		if (page == null) {
//			page = new Page<Enchashment>();
//		}
//		String hql = "from CashStream where (inCustId='" + usrcusid + "'" +" or outCustId='" + usrcusid + "')";
//	    String starttime=start_time;
//	    String endtime=end_time;
//		
//		//间隔周期
//		if(dateInterval!=null&&!dateInterval.equals("")){
//			Date dNow = new Date();   //当前时间
//			Date dBefore = new Date();
//			Calendar calendar = Calendar.getInstance();  //得到日历
//			calendar.setTime(dNow);//把当前时间赋给日历
//			calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(dateInterval));  //设置为前多少天
//			dBefore = calendar.getTime();   //得到前一天的时间
//			 try {
//				 starttime=StringUtil.DateToString(dBefore, "yyyy-MM-dd");
//				 endtime=StringUtil.DateToString(dNow, "yyyy-MM-dd");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		if (starttime != null && !starttime.equals("")) {
//			hql += " and createTime>'" + starttime+" 00:00:00'";
//		}
//		if (endtime != null && !endtime.equals("")) {
//			hql += " and createTime<'" + endtime + " 23:59:59'";
//		}
//		if(transtype!=null&&!transtype.equals("")){
//			hql += " and type.id="+transtype;
//		}
//		hql+=" order by createTime desc";
//		page.setHql(hql);
//		page = this.bizdataservice.findByPage(page);
		this.userService.translist(page, start_time, end_time, transtype, dateInterval, peruser);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("dateInterval", dateInterval);
		map.put("transtype", transtype);
		map.put("page", page);
	  return "userCenter/translist";
	}
	@RequestMapping(value = "/autoTenderPlan")
	public String autoTenderPlan(){
		return "userCenter/autoTenderPlan";
	}
	
	/**
	 * 投资记录
	 * @param session
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/recordList")
	public ModelAndView recordList(HttpSession session,Page page,Integer type){
		user = (User)session.getAttribute("user");
		if(user != null){
			ModelAndView model = null;
			if(type!=null&&type.intValue()==3){
				model = new ModelAndView("userCenter/recordList");
				page = tenderService.record(page,user.getHuifuID(),0,type,null);
			}else{
				model = new ModelAndView("userCenter/_recordList");
				page = tenderService.record(page,user.getHuifuID(),0,type,null);
			}
			Map map = tenderService.recordNum(user);
			model.addObject("num",map);
			model.addObject("recordList", page);
			model.addObject("type", type);
			return model;
		}
		return null;
	}
	
	
	/**
	 * 投资记录
	 * @param session
	 * @param page
	 * @return
	 */
	//@RequestMapping(value = "/recordList")
	@RequestMapping(value = "/mobilerecordList")
	public List recordListformobile(HttpSession session,Page page,Integer type){
		user = (User)session.getAttribute("user");
		if(user != null){
			if(type!=null&&type.intValue()==3){
				page = tenderService.record(page,user.getHuifuID(),0,type,null);
				List pagelist=page.getResult();
				for(int i=0;i<pagelist.size();i++){		
					Recordformobile touzi=new Recordformobile();
					List tmp=(List)pagelist.get(i);
					touzi.setId(tmp.get(0)+"");
					touzi.setName(tmp.get(1)+"");
					touzi.setRateTime(tmp.get(2)==null?"":tmp.get(2)+"");
					touzi.setPendTime(tmp.get(3)==null?"":tmp.get(2)+"");
					touzi.setTransAmt(tmp.get(4)+"");
					touzi.setCounttid(tmp.get(5)+"");
					touzi.setState(tmp.get(6)+"");
					touzi.setStartdays(tmp.get(7)+"");
					touzi.setPstarttime(tmp.get(8)+"");
					
					List pageother=this.repaymentPlanService.repayEarlyRewardformobile(Long.parseLong(touzi.getId()),user.getHuifuID());
					// 
					touzi.setCurrentmoney(pageother.get(0).toString());
					if(pageother.get(1).equals("true")){
						touzi.setCanrepay("1");
					}else if (pageother.get(1).equals("false")){
						touzi.setState("2");//设置成已还款
						touzi.setCanrepay("1");
					}else{
						touzi.setCanrepay("0");
					}
					pagelist.set(i, touzi);
				}
				page.setResult(pagelist);
			}else{
				page = tenderService.record(page,user.getHuifuID(),0,type,null);
				
				List pagelist=page.getResult();
				for(int i=0;i<pagelist.size();i++){		
					Recordformobile touzi=new Recordformobile();
					List tmp=(List)pagelist.get(i);
					touzi.setId(tmp.get(0)+"");
					touzi.setName(tmp.get(1)+"");
					touzi.setRateTime(tmp.get(2)==null?"":tmp.get(2)+"");
					touzi.setPendTime(tmp.get(3)==null?"":tmp.get(3)+"");
					touzi.setTransAmt(tmp.get(4)+"");
					touzi.setCounttid(tmp.get(5)+"");
					touzi.setState(tmp.get(6)+"");
					touzi.setStartdays(tmp.get(7)+"");
					touzi.setPstarttime(tmp.get(8)+"");
					// 
					touzi.setCurrentmoney("尚未开始还款");
					if(touzi.getState().equals("1")||touzi.getState().equals("2")){
						String currentdate="0";
	                     try {
							 currentdate=this.repaymentPlanService.projectRepay(Long.parseLong(touzi.getId()),user.getHuifuID());
							 touzi.setCurrentmoney(currentdate);
	                     } catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else if(touzi.getState().equals("4")){
						 touzi.setCurrentmoney(touzi.getTransAmt());
					}
					pagelist.set(i, touzi);
				}
				page.setResult(pagelist);
			}
			
			return page.getResult();
		}
		return null;
	}
	/**
	 * 投资记录
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/recordDetail")
	public ModelAndView recordDetail(Long id,HttpSession session){
		user = (User)session.getAttribute("user");
		if(user != null&&id!=null&&id!=0){
//			page = new Page();
//			page.setRows(99999);
//			repaymentSend.setProjectId(id);
//			repaymentSend.setInCustId(user.getHuifuID());
//			ModelAndView model = new ModelAndView("userCenter/recordDetail");
//			//产品相关信息
//			project = projectService.get(id);
//			//还款记录
//			page = repaymentSendService.findByPage(page,repaymentSend);
//			//总投资金额
//			List sum = tenderService.loansSum(user.getHuifuID(), id);
//			//已还款金额
//			List repay = repaymentSendService.repaySum(user.getHuifuID(), id);
//			model.addObject("model", project);
//			model.addObject("page", page);
//			model.addObject("sum", sum==null||sum.size()==0?0:sum.get(0));
//			model.addObject("repaySum", repay==null||repay.size()==0?0:repay.get(0));
//			
//			//还款记录  假分页
//			model.addObject("recordList",repaymentPlanService.record(id, user.getHuifuID()));
			ModelAndView model = new ModelAndView("userCenter/recordDetail");
			this.userService.recordDetail(model, user, id);
			return model;
		}
		return null;
	}
	/**
	 * 已还款
	 * @param id pid
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/projectRepay")
	@ResponseBody
	public String projectRepay(Long id,HttpSession session){
		user = (User)session.getAttribute("user");
		if(user != null&&id!=null&&id!=0){
			try{
				return repaymentPlanService.projectRepay(id,user.getHuifuID());
			}catch(Exception e){
			}
		}
		return null;
	}
	/**
	 * 提前还款前  计算收益
	 * @param id pid
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/repayEarlyReward")
	@ResponseBody
	public List repayEarlyReward(Long id,HttpSession session){
		user = (User)session.getAttribute("user");
		if(user != null&&id!=null&&id!=0){
			try{
				List list = this.dynamicRateByProjectService.repayEarlyReward(user, id);
				//List pageother=this.repaymentPlanService.repayEarlyRewardformobile(id,user.getHuifuID());
				return list;
			}catch(Exception e){
				
			}
		}
		return null;
	}
	/**
	 * 提前还款  仅针对屌丝宝
	 * @param id pid
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/repayEarly")
	@ResponseBody
	public String repayEarly(Long id,HttpSession session){
		user = (User)session.getAttribute("user");
		if(user != null&&id!=null&&id!=0){
			try{
				return dynamicRateByProjectService.repayEarly(user,id);
			}catch(Exception e){
				
			}
		}
		return null;
	}
	/**
	 * 提前还款  仅针对屌丝宝
	 * @param id pid
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/cancelRepayEarly")
	@ResponseBody
	public String cancelRepayEarly(Long id,HttpSession session){
		user = (User)session.getAttribute("user");
		if(user != null&&id!=null&&id!=0){
			try{
				return dynamicRateByProjectService.cancelRepayEarly(user,id);
			}catch(Exception e){
				
			}
		}
		return null;
	}
	/**
	 * 红包列表
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/luckyMoney")
	@NeedOpenHFPayInterceptor
	public ModelAndView luckyMoney(Page page,HttpSession session,LuckyMoney luckyMoney,String _state){
		user = (User)session.getAttribute("user");
		if(user!=null){
			if(luckyMoney==null)
				luckyMoney = new LuckyMoney();
			luckyMoney.setUid(user.getId());
			if(_state!=null&&!_state.equals(""))luckyMoney.setState(Integer.parseInt(_state));
			if(page==null){
				page=new Page<Object>();
			}
			page.setRows(7);
			page = luckyMoneyService.findPage(page,luckyMoney);
			ModelAndView model = new ModelAndView("userCenter/luckyList");
			model.addObject("page",page);
			model.addObject("luckyMoney",luckyMoney);
			model.addObject("state", _state);
			return model;
		}
		return null;
	}
	//跳转到开通第三方支付页面
	@RequestMapping(value = "/hfpay")
	@NeedOpenHFPayInterceptor
	public String hfpay(){
		return "userCenter/hfpay";
	}
	//跳转到开通第三方支付页面
	@RequestMapping(value = "/hfregisterfinish")
	@NeedOpenHFPayInterceptor
	@NeedInterceptor
	public String hfregisterfinish(UserRegisterReturn registerReturn,HttpSession session){
		boolean flag = registerReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			message me=new message();
			me.setMsgString("验证签名失败");
			return HRERROR;
		}
		try {
			// 将汇付的账号写到user表中
			// 截取用户名
			String huifuUsrId = registerReturn.getUsrId();
			String username = huifuUsrId
					.substring(huifuUsrId.indexOf("lyjx_") + 5);

			User user = userService.findByUsername(username);
			String orderflag = user.getHuifuID();
			if (orderflag != null && !orderflag.equals("")) {
				session.setAttribute("user", user);
				return "redirect:userCenter";
			}
			// 汇付用户名
			user.setHuifuAccount(huifuUsrId);
			// 汇付账号
			user.setHuifuID(registerReturn.getUsrCustId());
			// 真实姓名
			user.setRealName(URLDecoder.decode(registerReturn.getUsrName(),
					"UTF-8"));
			// 身份证
			user.setIdentification(registerReturn.getIdNo());
			// 身份认证时间
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Date nowdate = new Date();
			String realNameAuthentDate = format.format(nowdate);
			user.setRealNameAuthentDate(realNameAuthentDate);
			user=(User) bizdataservice.bizSave(User.class, user);
			session.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
			message me=new message();
			me.setMsgString("出错了。。。");
			return ERROR;
		}
		return "redirect:userCenter";
	}
	@RequestMapping(value = "/getMessages")
	@ResponseBody
	@NeedOpenHFPayInterceptor
	public Object getMessages(HttpSession session){
		user = (User)session.getAttribute("user");
		if(user==null){
			return "0";
		}
		String hql="select count(*) from Message where destUser.id='"+user.getId()+"' and haveLook = 0";
		List list = bizdataservice.findByHql(hql);
		return list!=null&&list.size()>0?list.get(0):0;
	}
	 @RequestMapping(value = "/mymessages")
	 @NeedOpenHFPayInterceptor
	 public String mymessages(String type,HttpSession session,Page page,ModelMap map,String haveLook){
		 user = (User)session.getAttribute("user");
//		 String  hql="from Message where destUser.id='"+user.getId()+"'";
//		 if(type!=null&&!type.equals("")){
//			 hql+=" and  type.id="+type;
//		 }
//		 if(haveLook!=null&&!haveLook.equals("")){
//			 hql+=" and  haveLook="+haveLook;
//		 }
//		 if(page==null){
//			 page=new Page<Object>();
//		 }
//	     page.setEntityName("Message"); 
//	     page.setHql(hql);
//	     page=this.bizdataservice.findByPage(page);	
		 this.userService.mymessages(type, haveLook, user, page);
//		 String  hql="from Message where destUser.id='"+user.getId()+"'";
//		 if(type!=null&&!type.equals("")){
//			 hql+=" and  type.id="+type;
//		 }
//		 if(haveLook!=null&&!haveLook.equals("")){
//			 hql+=" and  haveLook="+haveLook;
//		 }
//		 hql+=" order by createTime desc";
//		 if(page==null){
//			 page=new Page<Object>();
//		 }
//	     page.setEntityName("Message"); 
//	     page.setHql(hql);
//	     page=this.bizdataservice.findByPage(page);	
	   map.put("type", type);
	   map.put("haveLook", haveLook);
	 return "userCenter/myMessage";
	}
	 //删除消息
	 @RequestMapping(value = "/delmes")
	 @ResponseBody
	 @NeedOpenHFPayInterceptor
	 public boolean delmes(String ids){
		return  this.bizdataservice.bizDel("com.zc.bsm.pojo.Message", ids);
	 }
	 //设置全部消息为已读
	 @RequestMapping(value = "/setMesHaveLook")
	 @ResponseBody
	 @NeedOpenHFPayInterceptor
	 public boolean setMesHaveLook(HttpSession session,ModelMap map){
//		 try {
//			 user = (User)session.getAttribute("user");
//			 String  hql="select id from Message where destUser.id='"+user.getId()+"'";
//			 hql+=" and  haveLook=0";
//		  List<Object> list=this.bizdataservice.find(hql);
//			 for(Object obj:list )  {
//			  String id=(String) obj;
//			  Message me=new Message();
//			  me.setId(id);
//			  me.setHaveLook(1);
//			  this.bizdataservice.bizSave(Message.class, me);
//			 }
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		 user = (User)session.getAttribute("user");
		 boolean tem=this.userService.setMesHaveLook(user);
		return  tem;
	 }
	//设置已读消息为已读
	 @RequestMapping(value = "/setMesHaveLookbyId")
	 @ResponseBody
	 @NeedOpenHFPayInterceptor
	 public boolean setMesHaveLookbyId(HttpSession session,ModelMap map,String id){
//		 try {
//			  Message me=(Message) this.bizdataservice.bizfindbyid(Message.class, id);
//			  me.setHaveLook(1);
//			  this.bizdataservice.bizSave(Message.class, me);
//			 }
//		 catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
		 boolean tem=this.userService.setMesHaveLookbyId(id);
		return  tem;
	 }
	 //信息详情
	 @RequestMapping(value = "/mesDetail")
	 @NeedOpenHFPayInterceptor
	 public String mesDetail (String id,ModelMap map,String currPage){
		 try {
          Message me= (Message) this.bizdataservice.bizfindbyid(Message.class, id); 
          map.put("mes", me);
          map.put("currPage", currPage);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return  "userCenter/MessageDetal";
	 }
	 //签到
	 @RequestMapping(value = "/usersigned")
	 @NeedOpenHFPayInterceptor
	 @ResponseBody
	public boolean usersigned(HttpSession session,ModelMap map){		
		try {
			user = (User)session.getAttribute("user");
			if(user.getScore()==null)user.setScore(0);
			if(user.getLastsignDate()==null||user.getLastsignDate().equals("")){
//				 user.setScore(user.getScore()+5);
				user= userService.addScore("8", user,session, null, "每日签到");
				 user.setLastsignDate(StringUtil.DateToString(new Date(), "yyyy-MM-dd"));
			     this.bizdataservice.bizSave(User.class, user);
     		}else{
     			 try {
					Date lastsigndate=StringUtil.StringToDate(user.getLastsignDate(), "yyyy-MM-dd");
					if(lastsigndate.before(StringUtil.StringToDate(StringUtil.DateToString(new Date(),"yyyy-MM-dd"), "yyyy-MM-dd"))){
//						 user.setScore(user.getScore()+5);
						 user= userService.addScore("8", user,session, null, "每日签到");
						 user.setLastsignDate(StringUtil.DateToString(new Date(), "yyyy-MM-dd"));
					     this.bizdataservice.bizSave(User.class, user);
					}else{
						return false;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     		}
			 map.put("user", user);
			 session.setAttribute("user",user);
		     return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 建立合同pdf
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/createContract")
	public String createContract(Long id,HttpSession session,HttpServletRequest request){
		user=(User) session.getAttribute("user");
//		project = projectService.get(id);
//		if(user!=null&&project!=null){
//			String rootPath = request.getSession().getServletContext().getRealPath("/");
//			String filePath = rootPath+"WEB-INF"+File.separator+"contract"+File.separator+id+File.separator+user.getId();
//			File file = new File(filePath+".pdf");
//			System.out.println("file exists'"+file.exists()+"':--------->"+filePath+".pdf");
//			if(!file.exists()){
//				try{
//					//生成静态页面
//					String ftlName = "contract"+(project.getType()==3?3:1)+".ftl";
//					FreeMarker fm = new FreeMarker();
//					Map<String,Object> root = new HashMap<String,Object>();
//					//放入对应数据key value
//					//甲方基本信息
//					root.put("name", ContractProperties.SELF_NAME);
//					root.put("address", ContractProperties.SELF_ADDRESS);
//					root.put("owner", ContractProperties.SELF_OWNER);
//					root.put("host", ContractProperties.SELF_HOST);
//					root.put("companyName", ContractProperties.SELF_COMPANYNAME);
//					//乙方基本信息
//					root.put("user", user);
//					//产品基本信息
//					root.put("project", project);
//					//产品类型
//					root.put("type", project.getType()==0?"信满盈":project.getType()==1?"金多宝":project.getType()==2?"资涨通":"屌丝宝");
//					//产品还款方式
//					String repay_type = "";
//					if(project.getRepay_type().equals("1"))
//						repay_type = "按月付息，到期还本";
//					else if(project.getRepay_type().equals("3"))
//						repay_type = "按季付息，到期还本";
//					else if(project.getRepay_type().equals("6"))
//						repay_type = "按半年付息，到期还本";
//					else if(project.getRepay_type().equals("12"))
//						repay_type = "按年付息，到期还本";
//					else if(project.getRepay_type().equals("-1"))
//						repay_type = "到期本息全还";
//					root.put("repay_type", repay_type);
//					
//					//投标份数/总金额
//					List list = tenderService.selfCondition(user.getHuifuID(), id);
//					root.put("count", ((Object[])list.get(0))[0]);
//					root.put("sum", ((Object[])list.get(0))[1]);
//					
//					//访问路径
//					String url = request.getRequestURL().toString();
//					url = url.substring(0,url.length()-14)+"downloadPdf?id="+id;
//					root.put("url", url.indexOf("http://")!=-1?url:("http://"+url));
//					System.out.println(filePath+".html");
//					file = new File(filePath+".html");
//					if(!file.getParentFile().exists())
//						file.getParentFile().mkdirs();
//					fm.implement(root,rootPath+"WEB-INF"+File.separator+"classes"+File.separator+"com"+File.separator+"zc"+File.separator+"ftl"+File.separator+"",ftlName,filePath+".html");
//					System.out.println("freeOver");
//					System.out.println("pdfStart");
//					//生成pdf
//					new Html2Pdf().convertHtmlToPdf(filePath+".html", filePath+".pdf");
//					System.out.println("pdfOver");
//				}catch(Exception e ){
//					e.printStackTrace();
//					return "false";
//				}
//			 }
//		 }
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String url = request.getRequestURL().toString();
		String result=this.userService.createContract(user, id, rootPath, url);
		if(result.equals("true"))
		{
			return id+"/"+user.getId()+".html";
		}
		else
		{
			return "false";
		}
	 }
	@RequestMapping("/downloadPdf")   
    public ModelAndView download(Long id,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {   
		user=(User) session.getAttribute("user");
		project = projectService.get(id);
		if(user!=null&&project!=null){
	        response.setContentType("text/html;charset=utf-8");   
	        request.setCharacterEncoding("UTF-8");   
	        java.io.BufferedInputStream bis = null;   
	        java.io.BufferedOutputStream bos = null;   
	        String rootPath = request.getSession().getServletContext().getRealPath("/");
			String ctxPath = rootPath+"WEB-INF"+File.separator+"contract"+File.separator+id+File.separator+user.getId();
	        try {   
	        	File file = new File(ctxPath+".pdf");
	            long fileLength = file.length();   
	            response.setContentType("application/x-msdownload;");   
	            response.setHeader("Content-disposition", "attachment; filename="  
	                    + new String((project.getId()+user.getId()+"contract.pdf").getBytes("utf-8"), "ISO8859-1"));   
	            response.setHeader("Content-Length", String.valueOf(fileLength));   
	            bis = new BufferedInputStream(new FileInputStream(ctxPath+".pdf"));   
	            bos = new BufferedOutputStream(response.getOutputStream());   
	            byte[] buff = new byte[2048];   
	            int bytesRead;   
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
	                bos.write(buff, 0, bytesRead);   
	            }   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            if (bis != null)   
	                bis.close();   
	            if (bos != null)   
	                bos.close();   
	        }   
		}
        return null;   
    } 
	@RequestMapping(value = "/danger")
	@NeedInterceptor
	public String danger(){
		return "contracts/danger";
	}
	@RequestMapping(value = "/contracts")
	@NeedInterceptor
	public ModelAndView contracts(Long id,HttpSession session){
		project = projectService.get(id);
		ModelAndView model = new ModelAndView("contracts/contract"+(project.getType()==3?"3":"1"));
		user = (User)session.getAttribute("user");
		model.addObject("project",project);
		model.addObject("name",ContractProperties.SELF_NAME);
		model.addObject("address",ContractProperties.SELF_ADDRESS);
		model.addObject("owner",ContractProperties.SELF_OWNER);
		model.addObject("companyName",ContractProperties.SELF_COMPANYNAME);
		model.addObject("host",ContractProperties.SELF_HOST);
		model.addObject("sum",0);
		//产品还款方式
		String repay_type = "";
		if(project.getRepay_type().equals("1"))
			repay_type = "按月付息，到期还本";
		else if(project.getRepay_type().equals("3"))
			repay_type = "按季付息，到期还本";
		else if(project.getRepay_type().equals("6"))
			repay_type = "按半年付息，到期还本";
		else if(project.getRepay_type().equals("12"))
			repay_type = "按年付息，到期还本";
		else if(project.getRepay_type().equals("-1"))
			repay_type = "到期本息全还";
		model.addObject("repay_type", repay_type);
		return model;
	}
	//经纪人注册
	@RequestMapping(value = "/agentRegister")
	@NeedInterceptor
	/**
	 * 跳转到注册页
	 */
	public String agentRegister(ModelMap map) {
		map.put("type", 2);
		return "register/register";
	}
	/**
	 * 我的客户  tab1 客户信息
	 */
	@RequestMapping(value = "/kehu")
	public String kehu(HttpSession session,ModelMap map,Page page) {
		User user=(User) session.getAttribute("user");
		String hql="from User where advisor='"+user.getId()+"' order by createTime desc";
		if (page == null) {
			page = new Page();
		}
		page.setRows(8);
		page.setHql(hql);
		page=this.bizdataservice.findByPage(page);
		//查询总推广奖励
		String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 2";
		Object sumbro=this.bizdataservice.getRowCount(sumhql);
		if(sumbro==null){
			sumbro=0;
		}
		map.put("sumbro", sumbro.toString());
		//可提前奖励
		String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62)  and agentType = 2" ;
		Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
		if(sumablehqlbro==null){
			sumablehqlbro=0;
		}
		//可提取项目树
		String countablehql="select count(id)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 2" ;
		Object countablehqlbro=this.bizdataservice.getRowCount(countablehql);
		if(countablehqlbro==null){
			countablehqlbro=0;
		}
		map.put("sumablehqlbro", sumablehqlbro.toString());
		map.put("countablehqlbro", countablehqlbro.toString());
		//累计推荐人数
		String agentCountHql="select count(id) from User where advisor='"+user.getId()+"'" ;
		Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
		if(agentCount==null){
			agentCount=0;
		}
		map.put("agentCount", agentCount.toString());
		
		return "/saveCenter/commder";
	}
	/**
	 * 我的客户  tab2我的奖励
	 */
	@RequestMapping(value = "/kehu2")
	public String kehu2(HttpSession session,ModelMap map,Page page) {
		User user=(User) session.getAttribute("user");
		String hql="from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 2";
		if (page == null) {
			page = new Page();
		}
		page.setRows(8);
		page.setHql(hql);
	    page=this.bizdataservice.findByPage(page);
	    //查询总推广奖励
	    String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 2";
	    Object sumbro=this.bizdataservice.getRowCount(sumhql);
	    if(sumbro==null){
	    	sumbro=0;
	    }
	    map.put("sumbro", sumbro.toString());
	    //可提前奖励
	    String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 2" ;
	    Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
	    if(sumablehqlbro==null){
	    	sumablehqlbro=0;
	    }
	    //可提取项目树
	    String countablehql="select count(id)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 2" ;
	    Object countablehqlbro=this.bizdataservice.getRowCount(countablehql);
	    if(countablehqlbro==null){
	    	countablehqlbro=0;
	    }
	    //第一个项目名称
	    if(page.getResult()!=null&&page.getResult().size()>0){
	    	Brokerage brokerage=(Brokerage)page.getResult().get(0);
	    	String fpname=  brokerage.getProject().getName();
	    	  map.put("fpname", fpname);
	    }
	    map.put("sumablehqlbro", sumablehqlbro.toString());
	    map.put("allablehqlbro", countablehqlbro.toString());
	    
	    //累计推荐人数
	    String agentCountHql="select count(id) from User where advisor='"+user.getId()+"'" ;
	    Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
	    if(agentCount==null){
	    	agentCount=0;
	    }
	    map.put("agentCount", agentCount.toString());
	    
		return "/saveCenter/_commder";
	}
	/**
	 * 我的推荐  tab1 客户信息
	 */
	@RequestMapping(value = "/tuijian")
	public String tuijian(HttpSession session,ModelMap map,Page page) {
		User user=(User) session.getAttribute("user");
		String hql="from User where agent='"+user.getId()+"' order by createTime desc";
		if (page == null) {
			page = new Page();
		}
		page.setRows(8);
		page.setHql(hql);
		page=this.bizdataservice.findByPage(page);
		//查询总推广奖励
		String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 1";
		Object sumbro=this.bizdataservice.getRowCount(sumhql);
		if(sumbro==null){
			sumbro=0;
		}
		map.put("sumbro", sumbro.toString());
		//可提前奖励
		String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
		Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
		if(sumablehqlbro==null){
			sumablehqlbro=0;
		}
		//可提取项目树
		String countablehql="select count(id)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
		Object countablehqlbro=this.bizdataservice.getRowCount(countablehql);
		if(countablehqlbro==null){
			countablehqlbro=0;
		}
		map.put("sumablehqlbro", sumablehqlbro.toString());
		
		//累计推荐人数
		String agentCountHql="select count(id) from User where agent='"+user.getId()+"'" ;
		Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
		if(agentCount==null){
			agentCount=0;
		}
		map.put("agentCount", agentCount.toString());
		
		return "/saveCenter/commder";
	}
	/**
	 * 我的推荐   tab2 我的奖励
	 */
	@RequestMapping(value = "/tuijian2")
	public String tuijian2(HttpSession session,ModelMap map,Page page) {
		User user=(User) session.getAttribute("user");
		String hql="from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 1";
		if (page == null) {
			page = new Page();
		}
		page.setRows(8);
		page.setHql(hql);
	    page=this.bizdataservice.findByPage(page);
	    //查询总推广奖励
	    String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 1";
	    Object sumbro=this.bizdataservice.getRowCount(sumhql);
	    if(sumbro==null){
	    	sumbro=0;
	    }
	    map.put("sumbro", sumbro.toString());
	    //可提现奖励
	    String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
	    Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
	    if(sumablehqlbro==null){
	    	sumablehqlbro=0;
	    }
	    //可提现奖励个数
	    String allablehql="select count(*)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
	    Object allablehqlbro=this.bizdataservice.getRowCount(allablehql);
	    if(allablehqlbro==null){
	    	allablehqlbro=0;
	    }
	    //可提取项目树
	    String countablehql="select count(id)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
	    Object countablehqlbro=this.bizdataservice.getRowCount(countablehql);
	    if(countablehqlbro==null){
	    	countablehqlbro=0;
	    }
	    //第一个项目名称
	    if(page.getResult()!=null&&page.getResult().size()>0){
	    	Brokerage brokerage=(Brokerage)page.getResult().get(0);
	    	String fpname=  brokerage.getProject().getName();
	    	  map.put("fpname", fpname);
	    }
	    map.put("sumablehqlbro", sumablehqlbro.toString());
	    map.put("allablehqlbro", allablehqlbro.toString());
	    
	    //累计推荐人数
	    String agentCountHql="select count(id) from User where agent='"+user.getId()+"'" ;
	    Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
	    if(agentCount==null){
	    	agentCount=0;
	    }
	    map.put("agentCount", agentCount.toString());
	    
		return "/saveCenter/_commder";
	}
	/**
	 * 我的客户  tab3奖励明细
	 */
	@RequestMapping(value = "/kehu3")
	public String kehu3(HttpSession session,ModelMap map,Page page) {
		project = new Project();
		project.setState(-101);
		project.setFlag("publictime");
		page = projectService.findByPage(page, project);
		User user=(User) session.getAttribute("user");
	    //查询总推广奖励
	    String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 2";
	    Object sumbro=this.bizdataservice.getRowCount(sumhql);
	    if(sumbro==null){
	    	sumbro=0;
	    }
	    map.put("sumbro", sumbro.toString());
	    //累计推荐人数
	    String agentCountHql="select count(id) from User where advisor='"+user.getId()+"'" ;
	    Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
	    if(agentCount==null){
	    	agentCount=0;
	    }
	    map.put("agentCount", agentCount.toString());
	     //可提前奖励
	    String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 2" ;
	    Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
	    if(sumablehqlbro==null){
	    	sumablehqlbro=0;
	    }
	    map.put("sumablehqlbro", sumablehqlbro.toString());
		return "/saveCenter/_commder2";
	}
	/**
	 * 我的客户  tab4我的帮助
	 */
	@RequestMapping(value = "/kehu4")
	public String kehu4(HttpSession session,ModelMap map,Page page) {
		
		return "/saveCenter/_commder3";
	}
	/**
	 * 我的推荐  tab3奖励明细
	 */
	@RequestMapping(value = "/tuijian3")
	public String tuijian3(HttpSession session,ModelMap map,Page page) {
		project = new Project();
		project.setState(-101);
		project.setFlag("publictime");
		page = projectService.findByPage(page, project);
		User user=(User) session.getAttribute("user");
		//查询总推广奖励
	    String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 1";
	    Object sumbro=this.bizdataservice.getRowCount(sumhql);
	    if(sumbro==null){
	    	sumbro=0;
	    }
	    map.put("sumbro", sumbro.toString());
	    //累计推荐人数
	    String agentCountHql="select count(id) from User where agent='"+user.getId()+"'" ;
	    Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
	    if(agentCount==null){
	    	agentCount=0;
	    }
	    map.put("agentCount", agentCount.toString());
	    //可提现奖励
	    String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
	    Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
	    if(sumablehqlbro==null){
	    	sumablehqlbro=0;
	    }
	    map.put("sumablehqlbro", sumablehqlbro.toString());
	    
	    
		return "/saveCenter/_commder2";
	}
	/**
	 * 我的推荐  tab4我的帮助
	 */
	@RequestMapping(value = "/tuijian4")
	public String tuijian4(HttpSession session,ModelMap map,Page page) {
		User user=(User) session.getAttribute("user");
		//查询总推广奖励
	    String sumhql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and agentType = 1";
	    Object sumbro=this.bizdataservice.getRowCount(sumhql);
	    if(sumbro==null){
	    	sumbro=0;
	    }
	    map.put("sumbro", sumbro.toString());
	    //累计推荐人数
	    String agentCountHql="select count(id) from User where agent='"+user.getId()+"'" ;
	    Object agentCount=this.bizdataservice.getRowCount(agentCountHql);
	    if(agentCount==null){
	    	agentCount=0;
	    }
	    map.put("agentCount", agentCount.toString());
	    //可提现奖励
	    String sumablehql="select sum(brokerage)  from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62) and agentType = 1" ;
	    Object sumablehqlbro=this.bizdataservice.getRowCount(sumablehql);
	    if(sumablehqlbro==null){
	    	sumablehqlbro=0;
	    }
	    map.put("sumablehqlbro", sumablehqlbro.toString());
	    
		return "/saveCenter/_commder3";
	}
	//赎回详情
	@RequestMapping(value = "/repayEarlyDetail")
	@ResponseBody
	public List repayEarlyDetail(Long id) {
		List list = dynamicRateByProjectService.getListById(id);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		try{
		for(;i <list.size();i++){
			DynamicRateByProject dynamicRateByProject = (DynamicRateByProject)list.get(i);
			if(sdf.parse(dynamicRateByProject.getStartTime()).compareTo(new Date())==1){
				break;
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		list.add(i);
		return list;
	}
	
	//推荐人申请提现
	@RequestMapping(value = "/commercash")
	@ResponseBody
	public boolean commercash(Brokerage apply){
    if(apply==null||apply.getId()==0){
    	return false;
    }
     apply.setState(new Dict(61));
     try {
		this.bizdataservice.bizSave(Brokerage.class, apply);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	return true;
	}
	//推荐人申请提现
	@RequestMapping(value = "/commercashall")
	@ResponseBody
	public boolean commercashall(HttpSession session){
		User user=(User) session.getAttribute("user");
		   String sumablehql="from Brokerage where revenueUser.id='"+user.getId()+"' and (state.id=60 or state.id=62)" ;
          List<Object> list=this.bizdataservice.find(sumablehql);
          if(list==null||list.size()==0){
        	  return true;
          }
		   for(Object obj:list){
			   Brokerage apply=(Brokerage) obj;
			   apply.setState(new Dict(61));
			   try {
					this.bizdataservice.bizSave(Brokerage.class, apply);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
          	}  
	return true;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
