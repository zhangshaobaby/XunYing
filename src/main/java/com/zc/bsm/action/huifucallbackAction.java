package com.zc.bsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.po.Dict;
import com.zc.base.util.SmsHttp;
import com.zc.base.util.StringUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.EnchashmentApply;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.Message;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RechargeOrder;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BankCardService;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.CreditAssignService;
import com.zc.bsm.service.DictService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.returnVo.AutoCreditAssignReturn;
import com.zc.bsm.vo.returnVo.CashReturn;
import com.zc.bsm.vo.returnVo.InitiativeTenderReturn;
import com.zc.bsm.vo.returnVo.MerCashReturn;
import com.zc.bsm.vo.returnVo.NetSaveReturn;
import com.zc.bsm.vo.returnVo.UserRegisterReturn;
import com.zc.bsm.vo.returnVo.UsrFreezeBgReturn;
import com.zc.bsm.vo.returnVo.UsrUnFreezeReturn;

/**
 * 专用于汇付回调接口
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/huifucallback")
public class huifucallbackAction {
	@Autowired
	public DictService dictService;
	@Autowired
	public UserService userService;
	@Autowired
	public BankCardService bankCardService;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	private TenderService tenderService;
	@Autowired
	private CreditAssignService creditAssignService;
	 @Autowired
	public EnchashmentService enchashmentService;
	    @Autowired
	    public  msgUtil msgutil;
	// 打印注册结果
	@RequestMapping(value = "/printRegistResult")
	@NeedInterceptor
	public void printRegistResult(UserRegisterReturn registerReturn,
			HttpServletResponse response, HttpServletRequest request) {
		boolean flag = registerReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			return;
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
				
			}else{
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
			}
	
			String trxId = registerReturn.getTrxId();
			PrintWriter out = response.getWriter();
			out.print("RECV_ORD_ID_".concat(trxId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 打印充值结果
	@RequestMapping(value = "/printRechargeResult")
	@NeedInterceptor
	public void printRechargeResult(NetSaveReturn netSaveReturn,
			HttpServletResponse response, HttpServletRequest request) {
		boolean flag = netSaveReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			return;
		}
		try {
			
			// 充值订单对象
			RechargeOrder order = (RechargeOrder) this.bizdataservice
					.bizfindbyid(RechargeOrder.class, netSaveReturn.getOrdId());
			// 优先获取交易是否完成 防止由于网络延时故障引起的汇付重复提交
			Integer orderflag = order.getFlag();
			if (orderflag != null) {
				
			}else{			
					// 获取交易结果
					String code = netSaveReturn.getRespCode()==null?"-1": netSaveReturn.getRespCode();
					if (code.equals("000")) {
						// 成功
						order.setFlag(0);
					} else {
						order.setFlag(1);
					}
					order.setOrderDesc(URLDecoder.decode(netSaveReturn.getRespDesc(),
							"UTF-8"));
					order.setFeeAmt(netSaveReturn.getFeeAmt());
					order.setFeeCustId(netSaveReturn.getFeeCustId());
					order.setFeeAcctId(netSaveReturn.getFeeAcctId());
					Dict mydict=this.dictService.findByDictId(netSaveReturn.getGateBankId());
					order.setBankId(mydict);
					bizdataservice.bizSave(RechargeOrder.class, order);
					if (code.equals("000")) {
						// 往流水表中新增一条充值记录
						CashStream cashstream = new CashStream();
						cashstream.setOrdId(order.getId().toString());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						cashstream.setOrdDate(sdf1.format(sdf.parse(netSaveReturn.getOrdDate())));
						cashstream.setFlag(0);
						cashstream.setTransAmt(order.getAmount());
						cashstream.setType(new Dict(33));
						cashstream.setInCustId(order.getHuifuID());
						//可用余额
						try {
							cashstream.setAvlBal(enchashmentService.getBalanceObj(order.getHuifuID()).getAvlBal());
						} catch (Exception e) {
							cashstream.setAvlBal("未知..可登陆汇付网站查询");
						}
						//摘要
						cashstream.setSummary("充值|"+order.getBankId().getDictName());
						bizdataservice.bizSave(CashStream.class, cashstream);
						//如果充值成功 发送信息
						 User user=userService.findByHuifuId(order.getHuifuID());
						 Map<String,String> params=new HashMap<String, String>();
						  params.put("money", order.getAmount().toString());
						  params.put("time", StringUtil.DateToString(StringUtil.StringToDate(cashstream.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), "yyyy年MM月dd日 HH时mm分ss秒"));
							msgutil.sendmessage("15",new String[]{"phone","webmeg"}, user,  params);
					}	
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	
		String trxId = netSaveReturn.getTrxId();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("RECV_ORD_ID_".concat(trxId));
	}
//	// 手动标表填数据用
//	@RequestMapping(value = "/tempTenderResult")
//	@NeedInterceptor
//	public void tempTenderResult(HttpServletRequest request,HttpSession session) {
//		String id = request.getParameter("id");
//		if(id!=null&&!id.equals("")){
//		 tenderService.tmpTenderResult(id);
//		}
//	}
	// 跳转投标结果
	@RequestMapping(value = "/printInitiativeTenderResult_")
	@NeedInterceptor
	public ModelAndView printInitiativeTenderResult_(InitiativeTenderReturn initiativeTenderReturn,HttpServletResponse response, HttpServletRequest request,HttpSession session) {

		ModelAndView model = new ModelAndView("hfsuccess");
		String message="";
		if(initiativeTenderReturn.getRespExt()!=null&&!initiativeTenderReturn.getRespExt().equals("")){
			try{
				initiativeTenderReturn.setRespExt(URLDecoder.decode(initiativeTenderReturn.getRespExt(),"UTF-8"));
			}catch(Exception e){
			}
		}
		if(!initiativeTenderReturn.validate())
			message = "验证签名失败";
		try {
			boolean  result= tenderService.InitiativeTenderResult(initiativeTenderReturn);
			if(result){
				message = initiativeTenderReturn.getRespDesc();
				message=StringUtil.getDecode(message);
				//给本人发消息
				 String 	orderid = initiativeTenderReturn.getOrdId();
			     Tender tender=(Tender) this.bizdataservice.bizfindbyid(Tender.class, orderid);
			 	User user = this.userService.findByHuifuId(tender.getUsrCustId());
			     Map<String,String> params=new HashMap<String, String>();
		        params.put("proname",tender.getProject().getName());
		        params.put("money",tender.getTransAmt().toString());
		        msgutil.sendmessage("10",new String[]{"phone","webmeg"}, user,  params);
		        
		        
		        Project pro=tender.getProject();
				//给推荐人发送消息
				if(pro.getCommission().compareTo(new BigDecimal(0))==1&&user.getAgent()!=null&&!user.getAgent().equals("")){
					User agent=(User) this.bizdataservice.bizfindbyid(User.class, user.getAgent());
			          if(agent!=null){	  
				        Map<String,String> paramsa=new HashMap<String, String>();
				        paramsa.put("date",StringUtil.DateToString(new Date(), "yyyy年MM月dd日"));
				        paramsa.put("proname",tender.getProject().getName());
				        paramsa.put("money",tender.getTransAmt().toString());
				        paramsa.put("touser",user.getRealName());
				        msgutil.sendmessage("2",new String[]{"phone","webmeg"}, agent,  paramsa);
						}	
				}
				//给理财顾问发送消息
				if(pro.getCommission_agent().compareTo(new BigDecimal(0))==1&&user.getAdvisor()!=null&&!user.getAdvisor().equals("")){
					User aduser=(User) this.bizdataservice.bizfindbyid(User.class, user.getAdvisor());
		          if(aduser!=null){	        
			        Map<String,String> paramsad=new HashMap<String, String>();
			        paramsad.put("date",StringUtil.DateToString(new Date(), "yyyy年MM月dd日"));
			        paramsad.put("proname",tender.getProject().getName());
			        paramsad.put("money",tender.getTransAmt().toString());
			        paramsad.put("touser",user.getRealName());
			        msgutil.sendmessage("13",new String[]{"phone","webmeg"}, aduser,  paramsad);
					}	
		        }
				//增加积分
				if(tender.getProject().getType()!=3){
					//非屌丝宝产品  012 234
					userService.addScore((tender.getProject().getType()+2)+"", user,null, tender.getTransAmt(), "购买"+tender.getProject().getName()+"。");
				}
			}else{
				message="投资失败,已满标";
			}
			
		    
		} catch (Exception e) {			
			e.printStackTrace();
			//message="投资失败,请求出错";
		}
		model.addObject("message",message);
		return model;
	}
	// 打印投标结果
	@RequestMapping(value = "/printInitiativeTenderResult")
	@ResponseBody
	@NeedInterceptor
	public String printInitiativeTenderResult(
			InitiativeTenderReturn initiativeTenderReturn,
			HttpServletResponse response, HttpServletRequest request,HttpSession session) {
	
		if(initiativeTenderReturn.getRespExt()!=null&&!initiativeTenderReturn.getRespExt().equals("")){
			try{
				initiativeTenderReturn.setRespExt(URLDecoder.decode(initiativeTenderReturn.getRespExt(),"UTF-8"));
			}catch(Exception e){
			}
		}
		if(!initiativeTenderReturn.validate())
			return "验证签名失败...";
		String orderid = "";
		try {
			//orderid = initiativeTenderReturn.getOrdId();
			//tenderService.InitiativeTenderResult(initiativeTenderReturn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "RECV_ORD_ID_".concat(orderid);
	}

	/**
	 * 取现
	 */
	@RequestMapping(value = "/printcashResult")
	@NeedInterceptor
	public void printcashResult(CashReturn cashReturn,
			HttpServletResponse response) {
		boolean flag = cashReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			return;
		}
		String orderid = "";
		try {
			orderid = cashReturn.getOrdId();
			Enchashment en = (Enchashment) this.bizdataservice.bizfindbyid(
					Enchashment.class, orderid);
			Integer orderflag = en.getFlag();
			if (orderflag != null && orderflag == 0) {
				
			}else{
				EnchashmentApply apply = en.getEnchashmentApply();
				if (cashReturn.getRespCode().equals("000")) {
					en.setFlag(0);
					// 修改取现申请的状态
					if (apply != null) {
						apply.setState(new Dict(44));
					}
				} else {
					en.setFlag(1);
					// 修改取现申请的状态
					if (apply != null) {
						apply.setState(new Dict(43));
					}
				}
				en.setOpenAcctId(cashReturn.getOpenAcctId());
				en.setOpenBankId(cashReturn.getOpenBankId());
				en
						.setCashDesc(URLDecoder.decode(cashReturn.getRespDesc(),
								"UTF-8"));
				this.bizdataservice.bizSave(Enchashment.class, en);
				// 往流水表中新增一条取现记录
				
				if(cashReturn.getRespCode().equals("000")){
					CashStream cashstream = new CashStream();
					cashstream.setOutCustId(cashReturn.getUsrCustId());
					cashstream.setOrdId(en.getId().toString());
					cashstream.setOrdDate("");
					cashstream.setFlag(0);
					cashstream.setTransAmt(en.getTransAmt());
					//查找银行卡名称
					List<Object> banklist=this.bizdataservice.find("from  Dict where dictid=?",en.getOpenBankId());
					String bankname="";
					if(banklist!=null&&banklist.size()>0){
						Dict bank=(Dict)banklist.get(0);
						bankname=bank.getDictName();
					}
					//可用余额
					try {
						cashstream.setAvlBal(enchashmentService.getBalanceObj(cashReturn.getUsrCustId()).getAvlBal());
					} catch (Exception e) {
						cashstream.setAvlBal("未知..可登陆汇付网站查询");
					}
					cashstream.setSummary("提现|"+bankname);
					cashstream.setType(new Dict(34));
					bizdataservice.bizSave(CashStream.class, cashstream);
					//如果是用户取款 剩余投资回款余额
				    User user=	this.userService.findByHuifuId(cashReturn.getUsrCustId());
					if(user!=null&&cashReturn.getRespCode().equals("000")){
					  if(user.getAlltransAmt()!=null){
					   BigDecimal	multAmt= user.getAlltransAmt().subtract(new BigDecimal( cashReturn.getTransAmt().replace(",", "")));
						 if(multAmt.compareTo(new BigDecimal(0))==1 ){
							 user.setAlltransAmt(multAmt);
						 }else{
							 user.setAlltransAmt(new BigDecimal(0) );
						 }
						 this.userService.update(user);
					  }
					  
						//发送消息
						Map<String, String> params=new HashMap<String, String>();
						params.put("money", en.getTransAmt().toString());
						params.put("bankcard", en.getOpenAcctId());
						msgutil.sendmessage("9",new String[]{"phone","webmeg"}, user,  params);
					}
				}
				
				// 保存取现申请状态
				if(apply!=null){
					this.bizdataservice.bizSave(EnchashmentApply.class, apply);
				}

			}
			PrintWriter out = null;
			if (StringUtils.isNotBlank(orderid)) {
				out = response.getWriter();
				//System.out.println("RECV_ORD_ID_".concat(orderid));
				out.print("RECV_ORD_ID_".concat(orderid));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 代取现
	 */
	@RequestMapping(value = "/printMercashResult")
	@NeedInterceptor
	public void printMercashResult(MerCashReturn cashReturn,
			HttpServletResponse response) {
	       	enchashmentService.printMercashResult(cashReturn);
			PrintWriter out = null;
			String orderid=cashReturn.getOrdId();
			if (StringUtils.isNotBlank(orderid)) {
				try {
					out = response.getWriter();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("RECV_ORD_ID_".concat(orderid));
				out.print("RECV_ORD_ID_".concat(orderid));
			}
	}

	/**
	 * 冻结
	 */
	@RequestMapping(value = "/printUsrFreezeBgResult")
	@NeedInterceptor
	public void printUsrFreezeBgResult(UsrFreezeBgReturn freezeBgReturn,
			HttpServletResponse response) {
		String orderid = "";
		try {
			boolean flag = freezeBgReturn.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				return;
			}
			orderid = freezeBgReturn.getOrdId();
			Freeze en = (Freeze) this.bizdataservice.bizfindbyid(Freeze.class,
					orderid);
			
			Integer orderflag = en.getFlag();
			if (orderflag != null && orderflag == 0) {
				
			}else{
				if (freezeBgReturn.getRespCode().equals("000")) {
					en.setFlag(0);
				} else {
					en.setFlag(1);
				}
				en.setSubAcctId(freezeBgReturn.getSubAcctId());
				en.setSubAcctType(freezeBgReturn.getSubAcctType());
				en.setTrxId(freezeBgReturn.getTrxId());
				en.setRespCode(freezeBgReturn.getRespCode());
				en.setRespDesc(freezeBgReturn.getRespDesc());
				this.bizdataservice.bizSave(Freeze.class, en);
				// 查找资金申请
				EnchashmentApply ea = (EnchashmentApply) this.bizdataservice
						.bizfindbyid(EnchashmentApply.class, en.getApplyid());
				// 修改资金申请状态 如果成功
				if (ea != null) {
					if (en.getFlag() == 0) {
						Dict dict = new Dict();
						dict.setId(38);
						ea.setState(dict);
					} else {
						// 资金冻结失败
						ea.setTipmes("资金冻结失败--"
								+ URLDecoder.decode(freezeBgReturn.getRespDesc(),
										"UTF-8"));
					}
					ea.setFreeze(en);
					this.bizdataservice.bizSave(EnchashmentApply.class, ea);
				}
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		PrintWriter out = null;
		if (StringUtils.isNotBlank(orderid)) {
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("RECV_ORD_ID_".concat(orderid));
			out.print("RECV_ORD_ID_".concat(orderid));
		}
	}

	/**
	 * 解冻
	 */
	@RequestMapping(value = "/printUsrUnFreezeResult")
	@NeedInterceptor
	public void printUsrUnFreezeResult(UsrUnFreezeReturn freezeBgReturn,
			HttpServletResponse response) {
		String orderid = "";
		try {
			boolean flag = freezeBgReturn.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				return;
			}
			orderid = freezeBgReturn.getOrdId();
			Freeze en = (Freeze) this.bizdataservice.bizfindbyid(Freeze.class,
					orderid);
			Integer orderflag = en.getFlag();
			if (orderflag != null && orderflag == 0) {
				
			}else{
				if (freezeBgReturn.getRespCode().equals("000")) {
					en.setFlag(0);
				} else {
					en.setFlag(1);
				}
				en.setRespCode(freezeBgReturn.getRespCode());
				en.setRespDesc(freezeBgReturn.getRespDesc());
				this.bizdataservice.bizSave(Freeze.class, en);
				// 查找资金申请
				EnchashmentApply ea = (EnchashmentApply) this.bizdataservice
						.bizfindbyid(EnchashmentApply.class, en.getApplyid());
				// 修改资金申请状态 如果成功
				if (ea != null) {
					if (en.getFlag() == 0) {
						ea.setUnfreeze(en);
					} else {
						// 资金解冻失败
						ea.setTipmes("资金解冻失败--"
								+ URLDecoder.decode(freezeBgReturn.getRespDesc(),
										"UTF-8"));
						// 设置状态为取现失败
						ea.setState(new Dict(43));
					}
				}
				this.bizdataservice.bizSave(EnchashmentApply.class, ea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = null;
		if (StringUtils.isNotBlank(orderid)) {
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	System.out.println("RECV_ORD_ID_".concat(orderid));
			out.print("RECV_ORD_ID_".concat(orderid));
		}
	}

	/**
	 * 解冻
	 */
	@RequestMapping(value = "/printUsrTenderUnFreezeResult")
	@NeedInterceptor
	public void printUsrTenderUnFreezeResult(UsrUnFreezeReturn freezeBgReturn,
			HttpServletResponse response) {
		String orderid = "";
		try {
			boolean flag = freezeBgReturn.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				return;
			}
			orderid = freezeBgReturn.getOrdId();
			Freeze en = (Freeze) this.bizdataservice.bizfindbyid(Freeze.class,
					orderid);
			if(en!=null){
				Integer orderflag = null;
				if(en!=null)orderflag=en.getFlag();
				if (orderflag != null && orderflag == 0) {
					
				}else{
					if (freezeBgReturn.getRespCode().equals("000")) {
						en.setFlag(0);
					} else {
						en.setFlag(1);
					}
					en.setRespCode(freezeBgReturn.getRespCode());
					en.setRespDesc(URLDecoder.decode(freezeBgReturn.getRespDesc(),
							"UTF-8"));
					this.bizdataservice.bizSave(Freeze.class, en);
					// 通过解冻对象的trxid 查找标 理论上是唯一的。 修改标的状态
					tenderService.doTenderState(en);
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = null;
		if (StringUtils.isNotBlank(orderid)) {
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("RECV_ORD_ID_".concat(orderid));
			out.print("RECV_ORD_ID_".concat(orderid));
		}

	}
	
	// 跳转投标结果
	@RequestMapping(value = "/tendersuccess")
	@NeedInterceptor
	public ModelAndView tendersuccess(InitiativeTenderReturn initiativeTenderReturn,HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		User user = (User)session.getAttribute("user");
		ModelAndView model = new ModelAndView("hfsuccess");
		String message="";
		try {			
			message = initiativeTenderReturn.getRespDesc();
			message=StringUtil.getDecode(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(initiativeTenderReturn.getRespCode());
		model.addObject("message",message);
		return model;
	}
	@RequestMapping(value = "/success")
	@NeedInterceptor
	public String success(HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		return "success";
	}
	
	/**
	 * 债权转让
	 */
	@RequestMapping(value = "/printCreditAssignResult")
	@NeedInterceptor
	public void printCreditAssignResult(HttpServletResponse response,
			AutoCreditAssignReturn _return) {
		String orderid = "";
		try {
			boolean flag = _return.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				return;
			}
			orderid = _return.getOrdId();
			creditAssignService.creditAssignReturn(_return);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrintWriter out = null;
		if (StringUtils.isNotBlank(orderid)) {
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("RECV_ORD_ID_".concat(orderid));
			out.print("RECV_ORD_ID_".concat(orderid));
		}
	}

}
