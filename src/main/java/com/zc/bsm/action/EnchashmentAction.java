package com.zc.bsm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.abf.pojo.Operator;
import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.po.Dict;
import com.zc.base.util.Page;
import com.zc.base.util.StringUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.Brokerage;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.EnchashmentApply;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.ComomBizService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.OperatorLogService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.vo.returnVo.UsrFreezeBgReturn;
import com.zc.bsm.vo.returnVo.UsrUnFreezeReturn;

@Controller
@RequestMapping(value = "/cash")
public class EnchashmentAction extends Action {
	protected static final Log logger = LogFactory.getLog(EnchashmentAction.class);
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
	private ComomBizService comomBizService;
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	public UserService userService;
	@Autowired
	public  msgUtil msgutil;
	@Autowired
	private OperatorLogService operatorLogService;
	// 查看取现记录
	@RequestMapping(value = "/cashlist")
	public String cashlist(HttpSession session, ModelMap map, Page page,
			String start_time, String end_time) {
		// 当前用户
		User peruser = (User) session.getAttribute("user");
		String usrcusid = peruser.getHuifuID();
		if (page == null) {
			page = new Page<Enchashment>();
		}
		String hql = "from Enchashment where usrCustId='" + usrcusid + "'";
		if (start_time != null && !start_time.equals("")) {
			hql += " and createTime>'" + start_time + "'";
		}
		if (end_time != null && !end_time.equals("")) {
			hql += " and createTime<'" + end_time + " 23:59:59'";
		}
		hql += "order by createTime desc";
		page.setHql(hql);
		page = enchashmentService.findByPage(page);
		map.put("start_time", start_time);
		map.put("end_time", end_time);

		QueryBalanceBgReturn qbr = this.enchashmentService
				.getBalanceObj(peruser);
		map.put("AvlBal", qbr.getAvlBal());
		map.put("AcctBal", qbr.getAcctBal());
		return "userCenter/cashlist";
	}

	// 查看企业取现申请记录
	@RequestMapping(value = "/auth/cashApplaylist")
	public String cashApplaylist(HttpSession session, ModelMap map, Page page,
			String start_time, String end_time, String usrCustId) {
		// 当前用户
		if (page == null) {
			page = new Page<Enchashment>();
		}
		String hql = "from EnchashmentApply where usrCustId='" + usrCustId
				+ "'";
		if (start_time != null && !start_time.equals("")) {
			hql += " and createTime>'" + start_time + "'";
		}
		if (end_time != null && !end_time.equals("")) {
			hql += " and createTime<'" + end_time + " 23:59:59'";
		}
		hql += "order by createTime desc";
		page.setHql(hql);
		page = enchashmentService.findByPage(page);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("usrCustId", usrCustId);
		QueryBalanceBgReturn qbr = this.enchashmentService
				.getBalanceObj(usrCustId);
		map.put("AvlBal", qbr.getAvlBal());
		map.put("AcctBal", qbr.getAcctBal());
		return "enterprise/enchashApplyList";
	}

	// 查看待审批企业取现申请
	@RequestMapping(value = "/auth/waitEnaminecashApplaylist")
	public String waitEnaminecashApplaylist(HttpSession session, ModelMap map,
			Page page) {
		// 当前用户
		if (page == null) {
			page = new Page<Enchashment>();
		}
		String hql = "from EnchashmentApply where state.dictid='wshenhe'";
		page.setHql(hql);
		page = enchashmentService.findByPage(page);
		return "manager/enchashExamine";
	}
	// 查看待审批用户推荐奖励取现申请
	@RequestMapping(value = "/auth/waitUserCommonderApplaylist")
	public String waitUserCommonderApplaylist(HttpSession session, ModelMap map,
			Page page,String identification,String proname) {
		map.put("identification", identification);
		map.put("proname", proname);
		// 当前用户
		if (page == null) {
			page = new Page<Enchashment>();
		}
		String hql = "select bro  from Brokerage as bro  ";
		boolean search=false;
		String searchHql="";
		String joinHql="";
		if(identification!=null&&!identification.equals("")){
			search=true;
			searchHql+=" and ruser.identification='"+identification+"'";
			joinHql+=" left join bro.revenueUser as ruser";
		}
		
		if(proname!=null&&!proname.equals("")){
			search=true;
			searchHql+=" and project.name like '%"+proname+"%'";
			joinHql+=" left join bro.project as project";
		}
		hql+=joinHql+" where bro.state.id=61 "+searchHql;
		page.setHql(hql);
		page = enchashmentService.findByPage(page);
	
		return "manager/waitUserCommonderApplaylist";
	}
	@RequestMapping(value = "/auth/changeStateforUserComonder")
	@ResponseBody
	public boolean  changeStateforUserComonder(Brokerage brokerage){
		if(brokerage==null||brokerage.getId()==0){
			logger.error("审批用户奖励提现-参数传递错误");
			return false;
		}
		try {
			brokerage=(Brokerage) this.bizdataservice.bizSave(Brokerage.class, brokerage);
			//提现成功 发送成功短信
			if(brokerage.getState().getId()==63){
			   Map<String,String> params=new HashMap<String, String>();
			   params.put("money", brokerage.getBrokerage().toString());
			   if(brokerage.getAgentType()==null||brokerage.getAgentType()==1){
				   msgutil.sendmessage("5",new String[]{"phone","webmeg"}, brokerage.getRevenueUser(),  params);  
			   }else{
				   msgutil.sendmessage("19",new String[]{"phone","webmeg"}, brokerage.getRevenueUser(),  params);
			   }
				 
			}
			return true;
		} catch (Exception e) {
			logger.error("审批用户奖励提现-修改状态出错");
			e.printStackTrace();
			return false;
		}
	}
	
	// 取现
	@RequestMapping(value = "/auth/enterprisecash")
	public String enterprisecash(HttpServletRequest request, ModelMap map,
			HttpSession session, String usrCustId) {
		User user = new User();
		user.setHuifuID(usrCustId);
		userService.updateBankCard(user);
		List<Object> buildbankList = this.comomBizService.find(
				"from Bankcard where usrCustId=?", usrCustId);
		map.put("usrCustId", usrCustId);
		if (buildbankList.size() == 0) {
			return "userCenter/enterprisecasherror";
		}
		// 查询公司名称
		List<Object> blist = this.comomBizService.find(
				"from Borrower where usrCustId=?", usrCustId);
		if (blist.size() > 0) {
			Borrower bw = (Borrower) blist.get(0);
			map.put("borrowername", bw.getName());
		}
		// 查询可用余额
		
		QueryBalanceBgReturn qbr = enchashmentService.getBalanceObj(user);
		map.put("AvlBal", qbr.getAvlBal());
		map.put("buildbank", buildbankList);
		return "enterprise/cash";
	}

	// 创建新的提现申请
	@RequestMapping(value = "/auth/enchashmentApply")
	public String enchashmentApply(HttpSession session, ModelMap map,EnchashmentApply ment) {
		try {
			// 生成取现订单
			Dict state=new Dict();
			state.setId(36);
			ment.setState(state);
			//设置借款方
		  List borrower=this.bizdataservice.find("from Borrower where usrCustId=?",ment.getUsrCustId());
		  if(borrower!=null&&borrower.size()>0){
			  ment.setBorrower((Borrower)borrower.get(0));
		  }
			ment = (EnchashmentApply) this.bizdataservice.bizSave(
					EnchashmentApply.class, ment);
			Operator operator = (Operator)session.getAttribute("operator");
			operatorLogService.saveLog(operator, ment, "5");
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("申请提现出错");
			return ERROR;
		}
		map.put("callbackurl", "");
		return SUCCESS;
	}

	// 取现
	@RequestMapping(value = "/enchashment")
	public String cash(HttpServletRequest request, ModelMap map,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		
		////////////调用汇付接口查询当前绑定银行卡
		userService.updateBankCard(user);
		
		
		
		
		
		
		
		
		List<Object> buildbankList = this.comomBizService.find(
				"from Bankcard where user.id=?", user.getId());
		if (buildbankList.size() == 0) {
			return "userCenter/casherror";
		}
		
		user=this.userService.findByHuifuId(user.getHuifuID());
		map.put("AlltransAmt", user.getAlltransAmt()==null?"0.00":StringUtil.BigDecimal2StringSmall(user.getAlltransAmt()));
		QueryBalanceBgReturn qbr = enchashmentService.getBalanceObj(user);
		map.put("AvlBal", qbr.getAvlBal());
		map.put("buildbank", buildbankList);
		//默认绑卡
		map.put("defaltbank", buildbankList.get(0));
		return "userCenter/cash";
	}

	// 用户支付
	@RequestMapping(value = "/auth/acctpay")
	public String usracctpay(HttpServletRequest request, ModelMap map,
			HttpSession session, String usrCustId) {
		// 查询公司名称
		List<Object> blist = this.comomBizService.find(
				"from Borrower where usrCustId=?", usrCustId);
		if (blist.size() > 0) {
			Borrower bw = (Borrower) blist.get(0);
			map.put("borrowername", bw.getName());
		}
		// 查询可用余额
		User user = new User();
		user.setHuifuID(usrCustId);
		QueryBalanceBgReturn qbr = enchashmentService.getBalanceObj(user);
		map.put("AvlBal", qbr.getAvlBal());
		map.put("usrCustId", usrCustId);
		return "enterprise/acctpay";
	}

	// 平台转账
	@RequestMapping(value = "/auth/testtransfer")
	public String testtransfer(HttpServletRequest request, ModelMap map,
			HttpSession session, String usrCustId) {

		return "enterprise/testtransfer";
	}

	// 修改申请支付状态 优先判断是否有冻结id 如果存在 直接查看该冻结记录 是否冻结完成
	@RequestMapping(value = "/auth/changestate")
	@ResponseBody
	public message changestate(HttpSession session, HttpServletRequest request, ModelMap map,
			EnchashmentApply ea) {
		message me=new message();
		 me.setMsgCode("false"); 
      if(ea.getId()==null||ea.getId().equals("")){
    	  me.setMsgString("参数传递错误");
    	  return me;
      }
      EnchashmentApply  oldea=(EnchashmentApply) this.bizdataservice.bizfindbyid(EnchashmentApply.class, ea.getId());
	//如果是通过
     if(ea.getState().getId()==38){
     //先冻结资金 如果冻结成功 修改状态 如果由于网络原因 或者其他原因 不修改状态
    	 //获取冻结请求json数据 如果返回null 说明报错或者
    	 //新建冻结请求记录
    	 Freeze freeze=new Freeze();
    	 freeze.setType(new Dict(40));
    	 freeze.setUsrCustId(oldea.getUsrCustId());
    	 freeze.setTransAmt(oldea.getTransAmt());
    	 freeze.setApplyid(oldea.getId());
    	 try {
    		 freeze=(Freeze) this.bizdataservice.bizSave(Freeze.class, freeze);
		} catch (Exception e) {
		  e.printStackTrace();
	 	}
    	 UsrFreezeBgReturn  bgr=null;
    	 try {
    	    bgr= comomBizService.getUsrFreezeBg(freeze);
		} catch (Exception e) {
			e.printStackTrace();
			  me.setMsgString("资金冻结请求出错..");
			  return me;
		}
    	 if(bgr==null){
       	  me.setMsgString("正在等待资金冻结结果.."); 
    	  return me;
    	 }else{
    	   me=comomBizService.dobgFreeState(bgr);
           return  me;
         }
       }else{
    	 //直接修改状态
    	 try {
    		 me.setMsgCode("success");
    	 	 this.bizdataservice.bizSave(EnchashmentApply.class, ea);
		} catch (Exception e) {
		e.printStackTrace();
		logger.error("修改状态失败");
		}
     }
     Operator operator = (Operator)session.getAttribute("operator");
     operatorLogService.saveLog(operator, ea, "6");
     return  me;
	}
	//解冻
	@RequestMapping(value = "/auth/unFreeze")
	@ResponseBody
	public message unFreeze(HttpSession session,HttpServletResponse response,EnchashmentApply ea){
		message me=new message();
		 me.setMsgCode("false"); 
		 try {
			 if(ea.getId()==null||ea.getId().equals("")){
			   	  me.setMsgString("参数传递错误");
			   	  return me;
			     }
			     EnchashmentApply  oldea=(EnchashmentApply) this.bizdataservice.bizfindbyid(EnchashmentApply.class, ea.getId());
			     Operator operator = (Operator)session.getAttribute("operator");
			     operatorLogService.saveLog(operator, oldea, "7");
			     Freeze unfreeze=new Freeze();
			     unfreeze.setType(new Dict(41));
			     unfreeze.setUsrCustId(oldea.getUsrCustId());
			     unfreeze.setTransAmt(oldea.getTransAmt());
			     unfreeze.setApplyid(oldea.getId());
			     unfreeze.setTrxId(oldea.getFreeze().getTrxId());
			     unfreeze= (Freeze) this.bizdataservice.bizSave(Freeze.class, unfreeze);
				 UsrUnFreezeReturn  bgr=null;
				 try {
				    bgr= comomBizService.UsrUnFreeze(unfreeze);
				} catch (Exception e) {
					e.printStackTrace();
					  me.setMsgString("资金解冻请求出错..");
					  return me;
				}
				 if(bgr==null){
			   	  me.setMsgString("正在等待资金解冻结果.."); 
				  return me;
				 }else{
				   me=comomBizService.dobgUnFreeState(bgr);
			       return  me;
			     }
		} catch (Exception e) {
			e.printStackTrace();
			  me.setMsgString("资金解冻请求出错..");
			  return me;
		}
    
	}

	//手动解冻
	@RequestMapping(value = "/auth/unFreezeman")
	@ResponseBody
	public message unFreezeman(Freeze freeze){
		return this.comomBizService.unFreezeman(freeze);
	}
	//手动撤标 慎用
	@RequestMapping(value = "/auth/cancelTender")
	@ResponseBody
	public message cancelTender(Tender tender){
     return this.comomBizService.cancelTender(tender);
	}
}
