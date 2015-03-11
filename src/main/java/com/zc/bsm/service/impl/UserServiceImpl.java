package com.zc.bsm.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.ContractProperties;
import com.zc.base.util.FreeMarker;
import com.zc.base.util.Html2Pdf;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.dao.UserDao;
import com.zc.bsm.pojo.Bankcard;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.BorrowerDetailPo;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Message;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.pojo.ScoreLog;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;
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
import com.zc.bsm.vo.BorrowerDetail;
import com.zc.bsm.vo.InitiativeTender;
import com.zc.bsm.vo.QueryCardInfo;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.vo.returnVo.QueryCardInfoResult;
import com.zc.bsm.vo.returnVo.QueryCardInfoReturn;
import com.zc.bsm.webVo.Recordformobile;
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {
	protected Log logger = LogFactory.getLog(UserServiceImpl.class);
	public static Map<String,Integer> SCORE_PROPERTIES = new HashMap<String,Integer>();
	public UserServiceImpl(){
		//("来源","金钱换取积分比例"）
		UserServiceImpl.SCORE_PROPERTIES.put("1",1);//每一元红包兑换
		UserServiceImpl.SCORE_PROPERTIES.put("2",33);//100元信满盈 	x3	0
		UserServiceImpl.SCORE_PROPERTIES.put("3",33);//100元资涨通	x3	1
		UserServiceImpl.SCORE_PROPERTIES.put("4",33);//100元金多宝 	x3	2
		UserServiceImpl.SCORE_PROPERTIES.put("5",50);//100元屌丝宝 	x2	3
		
		UserServiceImpl.SCORE_PROPERTIES.put("6",500);//推荐给好友 	x500
		UserServiceImpl.SCORE_PROPERTIES.put("7",300);//下载并登陆App 	x300
		UserServiceImpl.SCORE_PROPERTIES.put("8",10);//每日签到 	x10
		UserServiceImpl.SCORE_PROPERTIES.put("9",-100);//屌丝节投票使用
		UserServiceImpl.SCORE_PROPERTIES.put("10",100);//个人注册初始化100
		//System.out.println("score properties ready");
	}
	@Autowired
	public void setBaseDao(UserDao userDao) {
		this.baseDao = userDao;
	}
	@Autowired
	private  DictService dictservice;
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
	public TenderService tenderService;
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
	public BorrowerService borrowerService;
    @Autowired
    public DynamicRateByProjectService dynamicRateByProjectService;
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
	
	public User login(String username,String password){
		String hql="from User where username=? and pwd=?";
		String[] parms={username,password};
		List list = this.find(hql, username,password);		
		return list.size()>0?(User)list.get(0):null;
	}
	public User login(String username,String phone,String password){
		String hql="from User where (username=? or phone=?) and pwd=?";
	
		List list = this.find(hql, username,phone,password);		
		return list.size()>0?(User)list.get(0):null;
	}
	
	
	public String revotePwd(String secretPwd,String uname){
		try{
          String pwd=secretPwd.substring(0,3)+secretPwd.substring(uname.length()+3);
        //change
      	String firstVar=pwd.substring(0, 1);
      	String lastVar=pwd.substring(pwd.length() - 1, pwd.length());
      	pwd = changeStr(pwd, pwd.length() - 1, pwd.length(),firstVar);
      	pwd = changeStr(pwd, 0, 1,lastVar);
      	String secendVar=pwd.substring(1, 2);
      	String lastsecendVar=pwd.substring(pwd.length() - 2, pwd.length()-1);
      	pwd = changeStr(pwd, pwd.length() - 2, pwd.length() - 1,secendVar);
      	pwd = changeStr(pwd, 1, 2,lastsecendVar);
          return pwd;
		 }catch (Exception e) {
			e.printStackTrace();
		}
     return null;
}
	public String changeStr(String allstr,int start,int end,String changeStr){
		String  temp= allstr.substring(0, start) + changeStr + allstr.substring(end, allstr.length());
		return temp;
	}
	
	 @Transactional(readOnly = false, rollbackFor = DataAccessException.class) 
	public String test(){
		User user=new User();
		user.setId("1");
		String t="dfafaf";
		this.save(user);
		if(Integer.parseInt(t)>0){
			
		}

		return null;
	}
	public static void main(String[] args) {
		UserServiceImpl u=new UserServiceImpl();
		u.revotePwd("e8c80174e81f","zhangshaob");
	}
	public User findByName(String username){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from User where 1 = 1 ");
		if(username!=null&&!username.equals("")){
			hql.append("and username = ?  ");
			param.add(username);
			List<User> list =  this.baseDao.find(hql.toString(), param.toArray());
			return list.size()==1?list.get(0):null;
		}
		return null;
	}
	public User findByName_Phone(String phone){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from User where 1 = 1 ");
		if(phone!=null&&!phone.equals("")){
			hql.append("and phone = ?  ");
			param.add(phone);
			List<User> list =  this.baseDao.find(hql.toString(), param.toArray());
			return list.size()==1?list.get(0):null;
		}
		return null;
	}
	public User findByPwd_Phone(String loginPwd,String phone){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from User where 1 = 1 ");
		if(loginPwd!=null&&!loginPwd.equals("")&&phone!=null&&!phone.equals("")){
			hql.append("and pwd = ?  ");
			param.add(loginPwd);
			hql.append("and phone = ?  ");
			param.add(phone);
			List<User> list =  this.baseDao.find(hql.toString(), param.toArray());
			return list.size()==1?list.get(0):null;
		}
		return null;
	}

	public User findByUsername(String username) {
		try {
			List<User> list = this.find("from User where username=?", username);
			return list.size()==1?list.get(0):null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User findByHuifuId(String huifuId){
		try {
			List<User> list = this.find("from User where huifuID = ?", huifuId);
			return list.size()==1?list.get(0):null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//同接口公用方法开始
	public void mymessages(String type,String haveLook,User user,Page page) {
		 String  hql="from Message where destUser.id='"+user.getId()+"'";
		 if(type!=null&&!type.equals("")){
			 hql+=" and  type.id="+type;
		 }
		 if(haveLook!=null&&!haveLook.equals("")){
			 hql+=" and  haveLook="+haveLook;
		 }
		 hql+=" order by createTime desc";
		 if(page==null){
			 page=new Page<Object>();
		 }
	     page.setEntityName("Message"); 
	     page.setHql(hql);
	     page=this.bizdataservice.findByPage(page);
	}

	public boolean setMesHaveLook(User user) {
		boolean tem=false;
		try {
			 String  hql="select id from Message where destUser.id='"+user.getId()+"'";
			 hql+=" and  haveLook=0";
			 List<Object> list=this.bizdataservice.find(hql);
			 for(Object obj:list )  
			 {
				  String id=(String) obj;
				  Message me=new Message();
				  me.setId(id);
				  me.setHaveLook(1);
				  this.bizdataservice.bizSave(Message.class, me);
			 } 
			 tem=true;
		 }catch (Exception e) {
				e.printStackTrace();
		 }
		 return tem;
	}

	public boolean setMesHaveLookbyId(String id) {
		boolean tem=false;
		 Message me=(Message) this.bizdataservice.bizfindbyid(Message.class, id);
		  me.setHaveLook(1);
		  try {
			this.bizdataservice.bizSave(Message.class, me);
			tem=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tem;
	}

	public void translist(Page page, String start_time, String end_time,
			String transtype, String dateInterval,User user) {
		String usrcusid = user.getHuifuID();
		if (page == null) {
			page = new Page<Enchashment>();
		}
		String hql = "from CashStream where (inCustId='" + usrcusid + "'" +" or outCustId='" + usrcusid + "')";
	    String starttime=start_time;
	    String endtime=end_time;
		
		//间隔周期
		if(dateInterval!=null&&!dateInterval.equals("")){
			Date dNow = new Date();   //当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance();  //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -Integer.parseInt(dateInterval));  //设置为前多少天
			dBefore = calendar.getTime();   //得到前一天的时间
			 try {
				 starttime=StringUtil.DateToString(dBefore, "yyyy-MM-dd");
				 endtime=StringUtil.DateToString(dNow, "yyyy-MM-dd");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (starttime != null && !starttime.equals("")) {
			hql += " and createTime>'" + starttime+" 00:00:00'";
		}
		if (endtime != null && !endtime.equals("")) {
			hql += " and createTime<'" + endtime + " 23:59:59'";
		}
		if(transtype!=null&&!transtype.equals("")){
			hql += " and type.id="+transtype;
		}
		hql+=" order by createTime desc";
		page.setHql(hql);
		page = this.bizdataservice.findByPage(page);
	}

	public void userEstate(ModelMap map,User user) {
		QueryBalanceBgReturn	qbr=	enchashmentService.getBalanceObj(user); 
    	map.put("AvlBal", qbr.getAvlBal());
    	map.put("AvlBalNumber", qbr.getAvlBal().replace(",", ""));
    	map.put("FreBal", qbr.getFrzBal());
    	//总资产 账户余额+投标被扣款的金额+放贷的钱
    	Double totalAmt=0.00;
    	
    	/*String hql="select sum(transAmt)  from Tender as e where (e.project.state=0 or e.project.state=1 or e.project.state=3)  and flag=? and usrCustId=?";
    	//计算标的投资总额
        Object transAmtCount =this.tenderService.getRowCount(hql,0,user.getHuifuID());
        Double bd=Double.parseDouble(transAmtCount==null?"0":transAmtCount.toString()) ;*/
        
       //获取放款后未还款
        String  protransAmtHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0 and (p.state=0  or p.state=3) and usrCustId=?";
        Object protransAmt =  this.tenderService.getRowCount(protransAmtHql,user.getHuifuID());
        Double probd=Double.parseDouble(protransAmt==null?"0":protransAmt.toString()) ;
        //未还完的还款
        String  dsbtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0";
        Object dsbprotransAmt =  this.bizdataservice.getRowCount(dsbtransAmtHql);
        Double dsbbd=Double.parseDouble(dsbprotransAmt==null?"0":dsbprotransAmt.toString()) ;
        Double bd=probd+dsbbd;
  
    	/*//待回款的收益 查找还款计划 逻辑为 查找还款计划表中  产品状态为还款中 并且 还款计划为等待还款的金额，并且汇付号为当前用户的资金总和
        String planhql="select sum(e.repayMoney) from RepaymentPlan as e,Project as p  where   e.projectId=p.id and e.usrCustId=? and p.state=?   and    e.state=? ";
        Object planAmtCount=this.tenderService.getRowCount(planhql,user.getHuifuID(),1,-1);
        Double bdplan=Double.parseDouble(planAmtCount==null?"0":planAmtCount.toString());*/
        Double acctBalDb=Double.parseDouble(qbr.getAvlBal().replace(",", ""));
        totalAmt=acctBalDb+bd;
        map.put("totalAmt",StringUtil.DoubletoDecimal(totalAmt));
        String dbstring=StringUtil.DoubletoDecimal(bd);
        
        map.put("pbd",dbstring);
        map.put("pbdNumber",dbstring.replace(",", ""));
    	 //累计净收益 逻辑为 查找还款计划表中   还款计划为还款成功的利息，并且汇付号为当前用户的资金 +已使用的红包金额
        //利息部分
        String purehql="select sum(repayMoney)  from RepaymentPlan as e  where e.flag=0 and e.usrCustId=? and  e.state=? and e.repayCount !=0 ";
        Object pureAmtCount=this.tenderService.getRowCount(purehql,user.getHuifuID(),1);
        Double pureAmt=Double.parseDouble(pureAmtCount==null?"0":pureAmtCount.toString());
        //红包部分
    	String hongBaohql="select sum(money)  from LuckyMoney where state=3  and flag=0 and uid=?";    	
        Object hongBaoAmtCount =this.tenderService.getRowCount(hongBaohql,user.getId());
        Double hb=Double.parseDouble(hongBaoAmtCount==null?"0":hongBaoAmtCount.toString()) ;
        
        map.put("pureplan",StringUtil.DoubletoDecimal(pureAmt+hb));
        
        //查询投资项目数
        //获取非屌丝宝持有项目数目和屌丝宝投标成功后未还款数目
        String  procountHql="select count(DISTINCT t.project) from Tender as t join  t.project as p  where t.flag=0 and p.flag = 0 and (p.type!=3 and ( (p.state=0 or p.state=1 or p.state=3)) or (p.type=3 and (p.state=0 or p.state=3)) ) and usrCustId=?";
        Object procount =  this.tenderService.getRowCount(procountHql,user.getHuifuID());
        //未还完的屌丝宝
        String  dsbcountHql="select  p.id from RepaymentPlan as r ,Project as p where r.state=-1 and r.flag=0 and p.flag=0 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and p.type=3 group by p.id";
        Object dsbprocount =  this.bizdataservice.findByHql(dsbcountHql).size();
        map.put("procount",Integer.parseInt(procount.toString())+Integer.parseInt(dsbprocount.toString()));
        
        //各产品投资总额
         //屌丝宝       
        //获取屌丝宝投标成功后未还款
        String  diaositransAmtHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.type=3 and (p.state=0 or p.state=3)) and usrCustId=? and p.type=3";
        Object diaositransAmt =  this.tenderService.getRowCount(diaositransAmtHql,user.getHuifuID());
        Double diaosi=Double.parseDouble(diaositransAmt==null?"0":diaositransAmt.toString()) ;
         //加上未还完的屌丝宝
        String  diaosi2transAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=3";
        Object diaosi2transAmt =  this.bizdataservice.getRowCount(diaosi2transAmtHql);
        Double diaosi2=Double.parseDouble(diaosi2transAmt==null?"0":diaosi2transAmt.toString()) ;
        map.put("dsprocount",diaosi+diaosi2);
        
        //资涨通投资未生成还款
        String  zizhangprocountHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.state=0  or p.state=3)  and usrCustId=? and p.type=1";
        Object zizhangprocount =  this.tenderService.getRowCount(zizhangprocountHql,user.getHuifuID());
        Double zizhang=Double.parseDouble(zizhangprocount==null?"0":zizhangprocount.toString()) ;
        //未还完的还款资涨通
        String  zizhangtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=1";
        Object zizhangtransAmt =  this.bizdataservice.getRowCount(zizhangtransAmtHql);
        Double zizhang2=Double.parseDouble(zizhangtransAmt==null?"0":zizhangtransAmt.toString()) ;
        map.put("zizhangprocount",zizhang+zizhang2);

        //信满盈投资未生成还款
        String  xmprocountHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.state=0  or p.state=3)  and usrCustId=? and p.type=0";
        Object xmprocount =  this.tenderService.getRowCount(xmprocountHql,user.getHuifuID());
        Double xm=Double.parseDouble(xmprocount==null?"0":xmprocount.toString()) ;
        //未还完的还款
        String  xmtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=0";
        Object xmtransAmt =  this.bizdataservice.getRowCount(xmtransAmtHql);
        Double xm2=Double.parseDouble(xmtransAmt==null?"0":xmtransAmt.toString()) ;
        map.put("xmprocount",xm+xm2);

        //金多宝投资未生成还款
        String  jdbprocountHql="select sum(t.transAmt) from Tender as t join  t.project as p  where t.flag=0 and p.flag=0  and (p.state=0  or p.state=3)  and usrCustId=? and p.type=2";
        Object jdbprocount =  this.tenderService.getRowCount(jdbprocountHql,user.getHuifuID());
        Double jdb=Double.parseDouble(jdbprocount==null?"0":jdbprocount.toString()) ;
        //未还完的还款
        String  jdbtransAmtHql="select SUM(r.repayMoney) from RepaymentPlan as r ,Project as p  where r.flag=0 and p.flag=0 and r.state=-1 and r.usrCustId="+user.getHuifuID()+" and p.id= r.projectId and r.repayCount=0 and p.type=2";
        Object jdbtransAmt =  this.bizdataservice.getRowCount(jdbtransAmtHql);
        Double jdb2=Double.parseDouble(jdbtransAmt==null?"0":jdbtransAmt.toString()) ;
        map.put("jdprocount",jdb+jdb2);

        //红包数量
        String hbnumHql="select count(id) from LuckyMoney where uid=? and flag=0 and state=0";
        Object hbnum =  this.tenderService.getRowCount(hbnumHql,user.getId());
        map.put("hbnum",hbnum==null?"0":hbnum.toString());
        //积分
        map.put("score",user.getScore()==null?"0":user.getScore().toString());
        int level=0;
        //实名认证
       	 if(user.getRealNameAuthentDate()!=null){
       		 map.put("realNameAuthent", true);
       		level++;
       	 }
       	//手机号
       	  if(user.getPhone()!=null){
       		 map.put("phone", true);
       		level++;
       	 }
       	  //是否设置密保
       	//如果已经有密保 回填
     		List<Object> ques= this.bizdataservice.find("from PasswordSafeQuestion where user.id=?",user.getId());
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
	}

	public String createContract(User user, long proid,String rootPath,String url) {
		String result="true";
		Project project = projectService.get(proid);
		if(user!=null&&project!=null){
//			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String filePath = rootPath+"WEB-INF"+File.separator+"contract"+File.separator+proid+File.separator+user.getId();
			File file = new File(filePath+".pdf");
			System.out.println("file exists'"+file.exists()+"':--------->"+filePath+".pdf");
			if(!file.exists()){
				try{
					//生成静态页面
					String ftlName = "contract"+(project.getType()==3?3:1)+".ftl";
					FreeMarker fm = new FreeMarker();
					Map<String,Object> root = new HashMap<String,Object>();
					//放入对应数据key value
					//甲方基本信息
					root.put("name", ContractProperties.SELF_NAME);
					root.put("address", ContractProperties.SELF_ADDRESS);
					root.put("owner", ContractProperties.SELF_OWNER);
					root.put("host", ContractProperties.SELF_HOST);
					root.put("companyName", ContractProperties.SELF_COMPANYNAME);
					//乙方基本信息
					root.put("user", user);
					//产品基本信息
					root.put("project", project);
					//产品类型
					root.put("type", project.getType()==0?"信满盈":project.getType()==1?"金多宝":project.getType()==2?"资涨通":"屌丝宝");
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
					root.put("repay_type", repay_type);
					
					//投标份数/总金额
					List list = tenderService.selfCondition(user.getHuifuID(), proid);
					root.put("count", ((Object[])list.get(0))[0]);
					root.put("sum", ((Object[])list.get(0))[1]);
					
					//访问路径
//					String url = request.getRequestURL().toString();
					url = url.substring(0,url.length()-14)+"downloadPdf?id="+proid;
					root.put("url", url.indexOf("http://")!=-1?url:("http://"+url));
					System.out.println(filePath+".html");
					file = new File(filePath+".html");
					if(!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					fm.implement(root,rootPath+"WEB-INF"+File.separator+"classes"+File.separator+"com"+File.separator+"zc"+File.separator+"ftl"+File.separator+"",ftlName,filePath+".html");
					System.out.println("freeOver");
					System.out.println("pdfStart");
					//生成pdf
					new Html2Pdf().convertHtmlToPdf(filePath+".html", filePath+".pdf");
					System.out.println("pdfOver");
				}catch(Exception e ){
					e.printStackTrace();
					result="false";
				}
			 }
		 }
		return result;
	}

	public void recordDetail(ModelAndView model, User user, long pid) {
		Page page = new Page();
		page.setRows(99999);
		RepaymentSend repaymentSend = new RepaymentSend();
		repaymentSend.setProjectId(pid);
		repaymentSend.setInCustId(user.getHuifuID());
		//产品相关信息
		Project project = projectService.get(pid);
		//还款记录
		page = repaymentSendService.findByPage(page,repaymentSend);
		//总投资金额
		List sum = tenderService.loansSum(user.getHuifuID(), pid);
		//已还款金额
		List repay = repaymentSendService.repaySum(user.getHuifuID(), pid);
		model.addObject("model", project);
		model.addObject("page", page);
		model.addObject("sum", sum==null||sum.size()==0?0:sum.get(0));
		model.addObject("repaySum", repay==null||repay.size()==0?0:repay.get(0));
		
		//还款记录  假分页
		model.addObject("recordList",repaymentPlanService.record(pid, user.getHuifuID()));
	}
	public User addScore(String comeFrom,User user,HttpSession session,BigDecimal money,String summary){
		 user=this.get(user.getId());
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		//获取积分兑换比例
		Integer proportion = UserServiceImpl.SCORE_PROPERTIES.get(comeFrom);
		int score = 0;
		if(money==null||money.intValue()==0){
			score = proportion;
		} else { 
			score = money.intValue()/proportion ;
		}
		int oldscore=user.getScore()==null?0:user.getScore();
		//修改用户积分
		int finalScore=oldscore+score;
		user.setScore(finalScore);
		try {
			this.bizdataservice.bizSave(User.class, user);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//添加积分流水
		try{
			ScoreLog sl = new ScoreLog();
			sl.setUid(user.getId());
			sl.setComeFrom(comeFrom);
			sl.setMoney(money);
			sl.setScore(new BigDecimal(score));
			sl.setSummary(summary);
			sl.setFlag("0");
			this.bizdataservice.bizSave(ScoreLog.class, sl);
		}catch(Exception e){
			
		}
		if(session!=null)
			session.setAttribute("user", user);
		    return user;
	}
	public Page scoreDetail(User user,Page page){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from ScoreLog where uid = ? and flag = 0 order by id desc");
		param.add(user.getId());
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page, param.toArray());
		
	}
	public List<Recordformobile> recordListformobile(User user,Page page,Integer type,String state){
		if(user != null){
			page = tenderService.record(page,user.getHuifuID(),0,null,state);
			List pagelist=page.getResult();
			for(int i=0;i<pagelist.size();i++)
			{
				List tmp=(List)pagelist.get(i);
				Recordformobile touzi=new Recordformobile();
				touzi.setType(tmp.get(9)+"");
				touzi.setId(tmp.get(0)+"");
				touzi.setName(tmp.get(1)+"");
				touzi.setRateTime(tmp.get(2)==null?"":tmp.get(2)+"");
				touzi.setPendTime(tmp.get(3)==null?"":tmp.get(2)+"");
				touzi.setTransAmt(tmp.get(4)+"");
				touzi.setCounttid(tmp.get(5)+"");
				touzi.setState(tmp.get(6)+"");
				touzi.setStartdays(tmp.get(7)+"");
				touzi.setPstarttime(tmp.get(8)+"");
				//屌丝宝
				if(tmp.get(9)!=null&&tmp.get(9).toString().equals("3")){
					//List pageother=this.repaymentPlanService.repayEarlyRewardformobile(Long.parseLong(touzi.getId()),user.getHuifuID());
					List pageother=dynamicRateByProjectService.repayEarlyReward(user, Long.parseLong(touzi.getId()));
					touzi.setCurrentmoney(pageother.get(2).toString());
					//已经申请过提前还款
					
					if("3,4,7,8,9".contains(pageother.get(0).toString())){
						touzi.setCanrepay("1");
					}
					//已经还款
					else if (pageother.get(0).equals("6")){
						touzi.setState("2");//设置成已还款
						touzi.setCanrepay("1");
					}
					//可以申请
					else if (pageother.get(0).equals("5")){
						touzi.setCanrepay("0");
					}else{
						//错误信息
					}
				}else{
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
				}
				pagelist.set(i, touzi);
			}
			return page.getResult();
		}
		return null;
	}
	public void updateBankCard(User user){
		List<Object> buildbankList = null;
		if(user.getId()!=null)
			buildbankList = this.bizdataservice.find(
				"from Bankcard where user.id=?", user.getId());
		else 
			buildbankList = this.bizdataservice.find(
					"from Bankcard where usrCustId=?", user.getHuifuID());
		////////////调用汇付接口查询当前绑定银行卡
		QueryCardInfo queryCardInfo = new QueryCardInfo();
		queryCardInfo.setUsrCustId(user.getHuifuID());
		QueryCardInfoReturn _return = new TransSubmit().QueryCardInfo(queryCardInfo);
		if(_return.getRespCode().equals("000")){
			List<Bankcard> newList = new ArrayList<Bankcard>();
			List<QueryCardInfoResult> list = _return.getUsrCardInfolist();
			//删除过往记录
			StringBuilder hql = new StringBuilder();
			List param = new ArrayList();
			if(user.getId()!=null){
				hql.append("delete from Bankcard where user.id = ?");
				param.add(user.getId());
			}else{
				hql.append("delete from Bankcard where usrCustId = ?");
				param.add(user.getHuifuID());
			}
			this.baseDao.bulkUpdate(hql.toString(), param.toArray());
			for(int i = 0 ;i < list.size() ; i++){
				
				QueryCardInfoResult _result = list.get(i);
				try{
					List<Dict> banklist=this.dictservice.find("from Dict where dictid=?",_result.getBankId());
					Dict bank=null;
					if(banklist.size()>0){
						bank=banklist.get(0);
					}
					Bankcard bc = new Bankcard();
					bc.setBankId(_result.getBankId());
					bc.setBankName(bank.getDictName());
					if(user.getId()!=null)
						bc.setUser(user);
					else
						bc.setUsrCustId(user.getHuifuID());
					bc.setCardNumber(_result.getCardId());
					this.bizdataservice.bizSave(Bankcard.class, bc);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * luckyId 红包id
	 * projectId 产品id
	 * transAmt 申请投资金额
	 * timeLimit 投资期限
	 */
	public String[] initiativetender(String luckyId,String projectId,User user,Integer transAmt) {
		String[] mes = new String[3];
		mes[0] = "";
		try {
			Long luckyid=null;
			if(luckyId!=null&&!luckyId.equals("")){
				luckyid=Long.parseLong(luckyId.split(",")[0]);
			}
			Borrower	borrower=null;
			boolean tag = true;
			
			//前期验证
			Project project = (Project)this.bizdataservice.bizfindbyid(Project.class, Long.parseLong(projectId));
			if(user==null){
				tag = false;
				mes[0] = "请先登陆后再发起投标。";
			}
			if(project!=null&&project.getState()==0){				
				borrower=this.borrowerService.get(project.getBorrowId());
				if(project.getLowest_money().compareTo(transAmt)==1){
					//增加判断  判断产品余额  个人投资总额 如果余额小于单笔投资下限
//					if(project.get_now_money().add(new BigDecimal(transAmt)).compareTo(project.getTotal_money())!=0){
//						
//					}
					tag = false;
					mes[0] = "您的投资金额小于最小投资金额。";
					mes[2]="4";
				}
				if(project.getHighest_money().compareTo(transAmt)==-1){
					tag = false;
					mes[0] = "您的投资金额大于最大投资金额。";
					mes[2]="5";
				}
				if(project.getTotal_money().compareTo(project.get_now_money().add(new BigDecimal(transAmt)))==-1){
					tag = false;
					mes[0] = "您的投资金额超出资金缺口。";
					mes[2]="6";
				}
				if(project.getTotal_money().compareTo(project.get_now_money())==0){
					tag = false;
					mes[0] = "产品资金已募集完成~您来晚啦~";
					mes[2]="7";
				}
			}else{
				tag = false;
				mes[0] = "产品已取消。";
				mes[2]="3";
			}
			//error
			if(project.getType().intValue()==3){
				BigDecimal release = null;
				//剩余可投资金额   如果是屌丝宝  同时计算个人最大可投资金额  和项目资金缺口
				if(user!=null&&project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1){
					List releaseList = tenderService.findSumTransmat(Long.parseLong(projectId),user.getHuifuID());
					if(releaseList!=null&&releaseList.get(0)!=null&&!releaseList.get(0).toString().equals("")){
						release = new BigDecimal(releaseList.get(0).toString());
						release = project.getSelf_highest_money().subtract(release);
					}else{
						release = project.getSelf_highest_money();
					}
				}
				if(release != null && release.compareTo(new BigDecimal(transAmt))==-1){
					tag = false;
					mes[0] = "此产品下您的投资总额已经超出最大限额。";
					mes[2]="8";
				}
			}
			//红包验证
			LuckyMoney luckyMoney = null;
//			if(luckyId!=null&&luckyId!=0){
//				luckyMoney = (LuckyMoney)this.bizdataservice.bizfindbyid(LuckyMoney.class, luckyId);
//				if(luckyMoney!=null){
//					//判断是否非法
//					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//					Date date = new Date();
//					//状态已经使用或使用中，起始时间结束时间不符合，投资额度小于红包限制单笔投资金额,使用产品类别不适
//					if(luckyMoney.getState()!=0||sdf.parse(luckyMoney.getStart_time()).compareTo(date)>0||sdf.parse(luckyMoney.getEnd_time()).compareTo(date)<0||luckyMoney.getInvestLimit().compareTo(new BigDecimal(transAmt))>0||!luckyMoney.getType().contains(project.getType().toString())){
//						tag = false;
//						mes = "您选择的红包不符合使用条件";
//					}
//				}
//			}
			if(luckyid!=null&&luckyid!=0){
				luckyMoney = (LuckyMoney)this.bizdataservice.bizfindbyid(LuckyMoney.class, luckyid);
				if(luckyMoney!=null){
					//判断是否非法
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					//状态已经使用或使用中，起始时间结束时间不符合，投资额度小于红包限制单笔投资金额,使用产品类别不适
					
					if(luckyMoney.getState()!=0||sdf.parse(luckyMoney.getStart_time()).compareTo(date)>0||sdf.parse(luckyMoney.getEnd_time()).compareTo(date)<0||!luckyMoney.getType().contains(project.getType().toString())){
						tag = false;
						mes[0] = "您选择的红包不符合使用条件";
						mes[2]="9";
					}
					//非正常红包通用
					if(luckyMoney.getType().indexOf("x")==-1&&luckyMoney.getMoney().compareTo((new BigDecimal(transAmt+"")).multiply(project.getHongbaorate()).multiply(new BigDecimal(0.01)))>0){
						tag = false;
						mes[0] = "您选择的红包不符合使用条件";
						mes[2]="9";
					}
					
				}
			}
			if(!tag){
				return mes;
			}
			String tmpDetail = "";
			List<BorrowerDetailPo> bdpolist = new ArrayList<BorrowerDetailPo>();
			List<BorrowerDetail> bdlist=new ArrayList<BorrowerDetail>();
			if (borrower != null) {
				String transAmtString=transAmt.toString();
				if(transAmtString.indexOf(".")==-1){
					transAmtString=transAmtString+".00";
				}
					
					Double dou = Double.parseDouble(project.getRate().toString());
					Double monrate = dou / 12;
					Double totalrate = monrate *(project.getTime_limit()+project.getDelay_time_limit());
					BigDecimal d=new BigDecimal(totalrate/100*SignUtils.TENDER_FLOATI_RATE);
					BorrowerDetail bd=new BorrowerDetail();
					bd.setBorrowerAmt(transAmtString);
					bd.setBorrowerRate(StringUtil.BigDecimal2StringBig(d));
					bd.setBorrowerCustId(borrower.getUsrCustId().toString());
					bdlist.add(bd);
					// 实例化 数据库借款人详情
					BorrowerDetailPo dbpo = new BorrowerDetailPo();
					dbpo.setBorrowerCustId(project.getBorrowId().toString());
					BigDecimal bde = new BigDecimal(transAmtString);
					dbpo.setBorrowerAmt(bde);
					dbpo.setBorrowerRate(StringUtil.BigDecimal2StringBig(d));
					dbpo = (BorrowerDetailPo) this.bizdataservice.bizSave(
							BorrowerDetailPo.class, dbpo);
					bdpolist.add(dbpo);
				}
				Gson gson = new Gson();
				tmpDetail = gson.toJson(bdlist);
			
			// 生成标订单
			Tender ten = new Tender();
			ten.setType(0);
			ten.setBorrowerDetails(bdpolist);
			BigDecimal bde = new BigDecimal(transAmt);
			ten.setTransAmt(bde);
			Project pro = new Project();
			pro.setId(Long.parseLong(projectId));
			ten.setProject(pro);
			ten.setMaxTenderRate("0.01");
			ten.setUsrCustId(user.getHuifuID());
			ten.setIsFreeze("Y");
			ten.setSrcTransAmt(bde);
			ten.setStart_time(pro.getStart_time());
			ten.setUsername(user.getUsername());
			ten.setEnd_time(pro.getEnd_time());
			ten.setRepayAmt(new BigDecimal(0));
			if(luckyMoney!=null){
				ten.setLuckyId(luckyid);
			}
			ten=(Tender) this.bizdataservice.bizSave(Tender.class, ten);
			//回填srcTenderid
			ten.setSrcTenderId(ten.getId());
			//重新保存
			ten=(Tender) this.bizdataservice.bizSave(Tender.class, ten);
//			if(luckyMoney!=null){
//				luckyMoney.setState(1);
//				luckyMoney.setTenderId(ten.getId());
//				luckyMoney.setProjectId(ten.getProject().getId());
//				this.bizdataservice.saveOrUpdate(luckyMoney);
//			}
			// 设置汇付参数
			InitiativeTender tender = new InitiativeTender();
			tender.setBgRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/printInitiativeTenderResult");
			tender.setRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/printInitiativeTenderResult_");
			tender.setBorrowerDetails(tmpDetail);
			tender.setTransAmt(StringUtil.BigDecimal2String(ten.getTransAmt()));
			tender.setUsrCustId(user.getHuifuID());
			tender.setMaxTenderRate("0.01");
			tender.setIsFreeze("Y");
			/////////////////////////////////////////////
			//插入一条冻结记录到Freeze
			Freeze freeze = new Freeze();
			freeze.setUsrCustId(user.getHuifuID());
			freeze.setSubAcctId(ten.getId().toString());
			freeze.setType(new Dict(40));
			freeze.setTransAmt(ten.getTransAmt());
			this.bizdataservice.save(freeze);
			//主键作为freezeOrdId
			tender.setFreezeOrdId(freeze.getId().toString());
			/////////////////////////////////////////////
			tender.setOrdId(ten.getId().toString());
			tender.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(
					ten.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
			"yyyyMMdd"));
			//
			if(luckyMoney!=null){
				String json = "{\"Vocher\":{\"VocherAmt\":\""+luckyMoney.getMoney()+"\",\"AcctId\":\""+SignUtils.MER_CUST_ACCT_ID+"\"}}";
				tender.setReqExt(json);
			}
//			//更新project nowmoney
//			project.setNow_money(project.getNow_money().add(ten.getTransAmt()));
//			project.setPay_number(project.getPay_number()==null?1:project.getPay_number()+1);
//			bizdataservice.saveOrUpdate(project);
			String url = tender.getParam();
//			response.sendRedirect(url);
			mes[1]=url;
			mes[2]="0";
		} catch (Exception e) {
			e.printStackTrace();
//			message me = new message();
//			me.setMsgString("生成投标订单出错,请重试");
//			try {
//				response.getWriter().print("生成投标订单出错,请重试");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			mes[0]="生成投标订单出错,请重试";
			mes[2]="99";
		}
		return mes;
	}
}
