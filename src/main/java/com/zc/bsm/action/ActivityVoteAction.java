package com.zc.bsm.action;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.util.FileUtils;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.Activity_vote;
import com.zc.bsm.pojo.Activity_vote_persion_infor;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.ActivityVotePersionService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.service.impl.UserServiceImpl;
@Controller
@RequestMapping("/activityvote")
@Scope("prototype")
public class ActivityVoteAction extends Action {
	@Autowired
	public UserService userService;
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
    public LuckyMoneyService luckyMoneyService;
	@Autowired
	public ActivityVotePersionService activityVotePersionService;
	@Autowired
	public TenderService tenderService;
	@Autowired
    public UserAction userAction;
	//女神list
	@RequestMapping(value = "/list")
 	@NeedInterceptor
	public String list(ModelMap map,Page page) {
		if(null==page)
		{
			page=new Page();
		}
		page = activityVotePersionService.findByPage(page);
		map.put("picpath",SignUtils.PIC_HOST+"/activity/vote");
		return "activity_vote/list";
	}
	//点击进入手机端票选页面
	/**
	 * type phone/pc 如果为null默认手机页
	 */
	@RequestMapping(value = "/index")
 	@NeedInterceptor
	public String phoneindex(ModelMap map,HttpServletRequest request,String type) {
		List<Activity_vote_persion_infor> idlist = activityVotePersionService.find("");
		List<Activity_vote_persion_infor> votelist=activityVotePersionService.find("1");
		long max=((Activity_vote_persion_infor)votelist.get(0)).getTotalvote();
		for(Activity_vote_persion_infor vote:votelist)
		{
			for(Activity_vote_persion_infor idb:idlist)
			{
				if(idb.getId()==vote.getId())
				{
					if(max==idb.getTotalvote())
					{
						idb.setPersent("0");
					}
					else
					{
						
						String v=String.valueOf((1-((float)idb.getTotalvote()/(float)max))*100);
						String persent=v;
						if(v.length()>5)
						{
							persent=v.substring(0, 5);
						}
						idb.setPersent(persent);
					}
				}
				
			}
		}
		
		map.put("list", idlist);
		map.put("votelist", votelist);
		map.put("picpath",SignUtils.PIC_HOST+"/activity/vote");
		User u=(User)request.getSession().getAttribute("user");
		if(null!=u)
		{
			map.put("uid", u.getId());
		}
		else
		{
			map.put("uid", "");
		}
		if(type!=null&&type.equals("pc")){
	     	//是否签到
     		if(u==null||u.getLastsignDate()==null||u.getLastsignDate().equals("")){
     			map.put("signed", false);  
     		}else{
     			 try {
					Date lastsigndate=StringUtil.StringToDate(u.getLastsignDate(), "yyyy-MM-dd");
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
     		if(u==null){
    			map.put("hongbao", 0);
     			map.put("diaosibi", 0);
     		}else{
     			 //红包数量
     	        String hbnumHql="select count(id) from LuckyMoney where uid=? and flag=0 and state=0";
     	        Object hbnum =  this.tenderService.getRowCount(hbnumHql,u.getId());
     	        map.put("hongbao",hbnum==null?"0":hbnum.toString());
     	        //积分
     	        this.userService.findById("com.zc.bsm.pojo.User", u.getId());
     	        map.put("diaosibi",u.getScore()==null?"0":u.getScore().toString());
     		}

			return "activity_vote/index";	
		}else{
			return "activity_vote/webview/index";	
		}
		
	}
	//删除女神
	 @RequestMapping(value = "/delmes")
	 @ResponseBody
	 @NeedInterceptor
	 public boolean delmes(String ids,ModelMap map,Page page){
		return  this.activityVotePersionService.updateflag(ids);
	 }
	//女神信息
	@RequestMapping(value = "/view")
 	@NeedInterceptor
	public String view(Long id,ModelMap map,Page page) {
		if(null!=id)
		{
			Activity_vote_persion_infor persion=activityVotePersionService.get(id);
			map.put("persion", persion);
		}
		map.put("picpath",SignUtils.PIC_HOST+"/activity/vote");
		return "activity_vote/add";
	}
	//女神信息增加或者修改
	@RequestMapping(value = "/addorupdate")
 	@NeedInterceptor
	public String addorupdate(ModelMap map,Page page,Activity_vote_persion_infor persion) {
		if(null==persion.getId())
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			persion.setInserttime(sdf.format(date));
			persion.setTotalvote((long)100);
			persion.setFlag("1");
			
		}
		if(persion.getHeadpic().indexOf(",")>-1)
		{
			persion.setHeadpic(persion.getHeadpic().split(",")[1]);
		}
//		this.activityVotePersionService.saveOrUpdate(persion);
		
		 try {
			bizdataservice.bizSave(Activity_vote_persion_infor.class, persion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		page = activityVotePersionService.findByPage(page);
		map.put("picpath",SignUtils.PIC_HOST+"/activity/vote");
		return "activity_vote/list";
	}
	/**
	 * 
	 * @param session
	 * @param phone
	 * @param vercode
	 * @param hiddencode
	 * @param persionid
	 * @param request
	 * @param type sign/vote 签到或者投票
	 * @return
	 */
	//一键注册
	@RequestMapping(value = "/registquick")
	@ResponseBody//输出到页面
 	@NeedInterceptor
	public String registquick(HttpSession session,String phone,String vercode,String hiddencode,String persionid,HttpServletRequest request,String type,ModelMap map) {
		String errcode="99";
		String getUserHql = "from User where phone=? or username=?";
		List<User> list = this.userService.find(getUserHql,phone,phone);
		if(list!=null&&list.size()>0){
			errcode="1";//已经存在
			return errcode;
		}
		else if(null==phone||phone.equals(""))
		{
			errcode="2";//手机号码为空
			return errcode;
		}
		else if(null==vercode||vercode.equals(""))
		{
			errcode="3";//验证码为空
			return errcode;
		}
		else if(null==hiddencode||hiddencode.equals(""))
		{
			errcode="4";//验证码为空
			return errcode;
		}else if(hiddencode.equals("pccode")){
			  hiddencode=(String) session.getAttribute("votephonecode");
			if(hiddencode==null||!hiddencode.equals(vercode)){
				errcode="3";//验证码输入错误
				return errcode;
			}
			
		}
		    //设置随机密码
		    String randompwd= StringUtil.getRandom(6);
			String   md5Pwd=userService.md5(randompwd);
			User user=new User();
			user.setPwd(md5Pwd);
			user.setPhone(phone);
			user.setUsername(phone);
			user.setSource("2");
			user.setPaypwd(md5Pwd);
			user.setType(1);
			try {
				user= (User) bizdataservice.bizSave(User.class, user);
				//赠送红包
				LuckyMoney lucky = new LuckyMoney();
				lucky.setComeFrom(0l);
				luckyMoneyService.saveLuckyMoney(lucky, user);
				msgUtil msgutil=new msgUtil();
				Map<String,String> params=new HashMap<String, String>();
				params.put("pwd", randompwd);
				boolean tem=msgutil.sendmessage("21",new String[]{"phone"}, user,  params);
  				session.setAttribute("user",user);
  				map.put("uid", user.getId());
				//修改积分
  				userService.addScore("10", user, session, null, "手机用户注册");
				if(type!=null&&type.equals("sign")){
                    //签到
                    boolean flag=userAction.usersigned(session, map);
                    if(flag){
                 	   return "success";
                    }else{
                 	   return "";
                    }
              }else{
            	//增加投票流水
  				Activity_vote vote=new Activity_vote();
  				vote.setFlag("1");
  				vote.setPersionid(Long.parseLong(persionid));
  				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  				Date date = new Date();
  				vote.setInserttime(sdf.format(date));
  				vote.setFlag("1");
  				vote.setUid(user.getId());
  				vote= (Activity_vote) bizdataservice.bizSave(Activity_vote.class, vote);
  				//女神票数增加
  				Activity_vote_persion_infor persion=activityVotePersionService.get(vote.getPersionid());
  				persion.setTotalvote(persion.getTotalvote()+1);
  				activityVotePersionService.saveOrUpdate(persion);
  				userService.addScore("9", user, session, null, "选女神投票");
               }				
 			errcode="0,"+user.getId();
			} catch (Exception e) {
				e.printStackTrace();
				errcode="5";
			}
	
		return errcode;
	}
	//手机号码验证
	@RequestMapping(value = "/phonecheck")
	@ResponseBody//输出到页面
 	@NeedInterceptor
	public String phonecheck(String phone) {
		String errcode="";
		String getUserHql = "from User where phone=? or username=?";
		List<User> list = this.userService.find(getUserHql,phone,phone);
		if(list!=null&&list.size()>0){
		}
		else
		{
			String code = StringUtil.getRandom(6);
			User user=new User();
			user.setPhone(phone);
			msgUtil msgutil=new msgUtil();
			Map<String,String> params=new HashMap<String, String>();
			params.put("action", "用户注册");
			params.put("code", code);
			boolean tem=msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
			if(tem)
			{
				errcode=code;
			}
		}
		return errcode;
	}
	/**
	 * 
	 * @param phone
	 * @param pwd
	 * @param persionid
	 * @param request
	 * @param type sign/vote 签到或者投票
	 * @return
	 */
	//快速登录投票
	@RequestMapping(value = "/loginquick")
	@ResponseBody//输出到页面
 	@NeedInterceptor
	public String loginquick(String phone,String pwd,String persionid,HttpServletRequest request,String type,ModelMap map) {
		if(null==phone||phone.equals("")||null==pwd||pwd.equals("")){
			return "";
		}
		// 验证码验证
		HttpSession session = request.getSession();
		String md5Password = this.userService.md5(pwd);
		// 数据库已存数据 
		User loginOperator =this.userService.login(phone,phone,md5Password);
		if(loginOperator==null){
			return "loginfalse";
		}
		session.setAttribute("user",loginOperator);
		
		  if(type!=null&&type.equals("sign")){
    	      //签到
               boolean flag=userAction.usersigned(session, map);
               if(flag){
            	   return "success";
               }else{
            	   return "";
               }
              }else{
            	
          		try {
          			//增加投票流水
              		Activity_vote vote=new Activity_vote();
              		vote.setFlag("1");
              		vote.setUid(loginOperator.getId());
              		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              		Date date = new Date();
              		vote.setInserttime(sdf.format(date));
              		vote.setPersionid(Long.parseLong(persionid));
          			vote= (Activity_vote) bizdataservice.bizSave(Activity_vote.class, vote);
          			//女神票数增加
          			Activity_vote_persion_infor persion=activityVotePersionService.get(vote.getPersionid());
          			persion.setTotalvote(persion.getTotalvote()+1);
          			activityVotePersionService.saveOrUpdate(persion);
          			int score=loginOperator.getScore();
          			if(score<UserServiceImpl.SCORE_PROPERTIES.get("9"))
          			{
          				return "buzu";//屌丝币不足
          			}
          			else
          			{
          				userService.addScore("9", loginOperator, request.getSession(), null, "选女神投票");
          			}
          			String s=String.valueOf(score-100);
          			map.put("scorenum", s);
          		} catch (Exception e) {
          			e.printStackTrace();
          			return "";
          		}
          		 return this.getvote(persionid);
              }
	}
	//投票(没有登录直接投票或者已经登录的投票)
	@RequestMapping(value = "/notloginvote")
	@ResponseBody//输出到页面
 	@NeedInterceptor
	public String notloginvote(String persionid,HttpServletRequest request) {
		if(null==persionid||persionid.equals("")){
			return "";
		}
		//增加投票流水
		Activity_vote vote=new Activity_vote();
		vote.setFlag("1");
		vote.setPersionid(Long.parseLong(persionid));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		vote.setInserttime(sdf.format(date));
		vote.setFlag("1");
		User u=(User)request.getSession().getAttribute("user");
		if(null==u)
		{
			vote.setUid("");
		}
		else
		{
			vote.setUid(u.getId());
		}
		try {
			vote= (Activity_vote) bizdataservice.bizSave(Activity_vote.class, vote);
			//女神票数增加
			Activity_vote_persion_infor persion=activityVotePersionService.get(vote.getPersionid());
			persion.setTotalvote(persion.getTotalvote()+1);
			activityVotePersionService.saveOrUpdate(persion);
			if(!vote.getUid().equals(""))
			{
				User baseuser=(User)userService.findById("com.zc.bsm.pojo.User", vote.getUid());
				if(null!=baseuser)
				{
					int score=baseuser.getScore();
					if(score<UserServiceImpl.SCORE_PROPERTIES.get("9"))
					{
						return "buzu";//屌丝币不足
					}
					else
					{
						userService.addScore("9", baseuser, request.getSession(), null, "选女神投票");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	    return this.getvote(persionid);
	}
	//注册发送验证码
	@RequestMapping(value = "/sendcode")
	@ResponseBody//输出到页面
 	@NeedInterceptor
	public String sendcode(String phone,String code,HttpSession session) {
		msgUtil msgutil=new msgUtil();
		Map<String,String> params=new HashMap<String, String>();
		params.put("action", "注册");
		if(code==null||code.equals("")){
			 code = StringUtil.getRandom(6);
		}
		params.put("code", code);
		User user=new User();
		user.setPhone(phone);
		boolean tem=msgutil.sendmessage("20",new String[]{"phone"}, user,  params);
		session.setAttribute("votephonecode", code);
		if(tem)
		{
			return "1";
		}
		else
		{
			return "";
		}
	}
	
	public String getvote(String persionid)
	{
		List<Activity_vote_persion_infor> votelist=activityVotePersionService.find("1");
		long max=votelist.get(0).getTotalvote();
		Activity_vote_persion_infor bean=(Activity_vote_persion_infor)activityVotePersionService.findById("com.zc.bsm.pojo.Activity_vote_persion_infor", Long.parseLong(persionid));
		long my=bean.getTotalvote();
		return String.valueOf(max-my);
	}
	
	
	/**
	 *	上传
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/upload")
	@ResponseBody
	@NeedInterceptor
	public String upload(@RequestParam MultipartFile[] files,HttpServletRequest request){
		String path = "";
		for(MultipartFile f:files){
			try{
				//path += FileUtils.saveFileFromInputStream(f.getInputStream(), request.getSession().getServletContext().getRealPath("/") , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += FileUtils.saveFileFromInputStreamBypath(f.getInputStream(),SignUtils.PIC_REALPAHT+"activity/vote/" , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += ",";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return path.length()>0?path.substring(0,path.length()-1):"-1";
	}
	//快速登录投票
	@RequestMapping(value = "/loginquickpc")
	@ResponseBody//输出到页面
 	@NeedInterceptor
	public String loginquickpc(String phone,String pwd,String persionid,HttpServletRequest request,String type,ModelMap map) {
		if(null==phone||phone.equals("")||null==pwd||pwd.equals("")){
			return "";
		}
		// 验证码验证
		HttpSession session = request.getSession();
		String md5Password = this.userService.md5(pwd);
		// 数据库已存数据 
		User loginOperator =this.userService.login(phone,phone,md5Password);
		if(loginOperator==null){
			return "loginfalse";
		}
		session.setAttribute("user",loginOperator);
		
		  if(type!=null&&type.equals("sign")){
    	      //签到
               boolean flag=userAction.usersigned(session, map);
               if(flag){
            	   return "success";
               }else{
            	   return "";
               }
              }else{
            	
          		try {
          			//增加投票流水
              		Activity_vote vote=new Activity_vote();
              		vote.setFlag("1");
              		vote.setUid(loginOperator.getId());
              		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              		Date date = new Date();
              		vote.setInserttime(sdf.format(date));
              		vote.setPersionid(Long.parseLong(persionid));
          			vote= (Activity_vote) bizdataservice.bizSave(Activity_vote.class, vote);
          			//女神票数增加
          			Activity_vote_persion_infor persion=activityVotePersionService.get(vote.getPersionid());
          			persion.setTotalvote(persion.getTotalvote()+1);
          			activityVotePersionService.saveOrUpdate(persion);
          			int score=loginOperator.getScore();
          			if(score<100)
          			{
          				return "buzu";//屌丝币不足
          			}
          			else
          			{
          				userService.addScore("9", loginOperator, request.getSession(), null, "选女神投票");
          			}
          			return String.valueOf(score-100);
          		} catch (Exception e) {
          			e.printStackTrace();
          			return "";
          		}
              }
	}
	
	/**
	 * 快速注册访问地址
	 */
	@RequestMapping(value ="/registfast")
	@NeedInterceptor//过滤器
	public String registfast(){
		return "registfast/addfast";
	}
	/**
	 * 快速注册
	 */
	@RequestMapping(value ="/registfastadd")
	@NeedInterceptor//过滤器
	public String registfastadd(String cmobile,String vercode,String hiddencode,ModelMap map){
		String getUserHql = "from User where phone=? or username=?";
		List<User> list = this.userService.find(getUserHql,cmobile,cmobile);
		String result="99";
		if(list!=null&&list.size()>0){
			result="1";//已经存在
		}
		else if(null==cmobile||cmobile.equals(""))
		{
			result="2";//手机号码为空
		}
		else if(null==vercode||vercode.equals("")||null==hiddencode||hiddencode.equals(""))
		{
			result= "3";//验证码为空
		}else if(!hiddencode.equals(vercode)){
			result= "4";//验证码输入错误
		}
	    //设置随机密码
	    String randompwd= StringUtil.getRandom(6);
		String   md5Pwd=userService.md5(randompwd);
		User user=new User();
		user.setPwd(md5Pwd);
		user.setPhone(cmobile);
		user.setUsername(cmobile);
		user.setSource("3");
		user.setPaypwd(md5Pwd);
		user.setType(1);
		try {
			user= (User) bizdataservice.bizSave(User.class, user);
			//赠送红包
			LuckyMoney lucky = new LuckyMoney();
			lucky.setComeFrom(0l);
			luckyMoneyService.saveLuckyMoney(lucky, user);
			msgUtil msgutil=new msgUtil();
			Map<String,String> params=new HashMap<String, String>();
			params.put("pwd", randompwd);
			boolean tem=msgutil.sendmessage("21",new String[]{"phone"}, user,  params);
			//修改积分
			userService.addScore("10", user, null, null, "手机用户注册");
			result= "0";
		} catch (Exception e) {
			e.printStackTrace();
			result= "5";
		}
		map.put("result", result);
		return "registfast/success";
	}
	
}
