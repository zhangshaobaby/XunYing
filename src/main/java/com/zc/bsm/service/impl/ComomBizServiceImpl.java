package com.zc.bsm.service.impl;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zc.abf.pojo.Operator;
import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.EnchashmentApply;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.ComomBizService;
import com.zc.bsm.service.OperatorLogService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.UsrFreezeBg;
import com.zc.bsm.vo.UsrUnFreeze;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.UsrFreezeBgReturn;
import com.zc.bsm.vo.returnVo.UsrUnFreezeReturn;
@Service("comomBizService")
public class ComomBizServiceImpl extends   BaseServiceImpl<Object,String> implements ComomBizService{
	@Autowired
	private bizDataService bizdataservice;
	 @Autowired
	    public  msgUtil msgutil;
	/**
	 * 冻结资金
	 * @return
	 */
	@RequestMapping(value = "/auth/huifu/UsrFreezeBg")
	public UsrFreezeBgReturn getUsrFreezeBg(Freeze freeze) throws Exception{
			 UsrFreezeBg uf=new UsrFreezeBg();
			 uf.setOrdId(freeze.getId().toString());
			 uf.setBgRetUrl(SignUtils.PUBLIC_HOST
						+ "huifucallback/printUsrFreezeBgResult");
			 uf.setUsrCustId(freeze.getUsrCustId());
			 uf.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(
						freeze.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
						"yyyyMMdd"));
			 uf.setTransAmt(StringUtil.BigDecimal2String(freeze.getTransAmt()));
			 Map<String,String>	params= uf.getParam();
			  String jsonString = TransSubmit.doPost(params);
			  Gson gson = new Gson();
				//System.out.println(jsonString);
				if(jsonString==null||jsonString.equals("")){
					return null;
				}
				UsrFreezeBgReturn freezeBgReturn=gson.fromJson(jsonString, UsrFreezeBgReturn.class);
				//设置该请求的 汇付交易流水
				freeze.setTrxId(freezeBgReturn.getTrxId());
				this.bizdataservice.bizSave(Freeze.class, freeze);
				return freezeBgReturn;
	} 
	//处理汇付发送的冻结return 
	public message dobgFreeState(UsrFreezeBgReturn freezeBgReturn ){
		message me=new message();
		 me.setMsgCode("false");
		try {
			boolean flag = freezeBgReturn.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				me.setMsgString("验证签名失败...");
				return me;
			}
		    String	 orderid = freezeBgReturn.getOrdId();
			Freeze en=(Freeze) this.bizdataservice.bizfindbyid(Freeze.class, orderid);
			Integer orderflag=en.getFlag();
			if(orderflag!=null&&orderflag==0){
				 me.setMsgCode("success");
				return me;
			}
			if(freezeBgReturn.getRespCode().equals("000")){
				en.setFlag(0);
			}else{
				en.setFlag(1);
			}
			en.setSubAcctId(freezeBgReturn.getSubAcctId());
			en.setSubAcctType(freezeBgReturn.getSubAcctType());
			en.setTrxId(freezeBgReturn.getTrxId());
			en.setRespCode(freezeBgReturn.getRespCode());
			en.setRespDesc(freezeBgReturn.getRespDesc());
			this.bizdataservice.bizSave(Freeze.class, en);
		    //查找资金申请
			EnchashmentApply ea=(EnchashmentApply) this.bizdataservice.bizfindbyid(EnchashmentApply.class,en.getApplyid());
			//修改资金申请状态 如果成功
			if(ea!=null){
				if(en.getFlag()==0){
					Dict dict=new Dict();
					dict.setId(38);
					ea.setState(dict);
					ea.setFreeze(en);
					 me.setMsgCode("success");
				}else{
					//资金冻结失败
					ea.setTipmes("资金冻结失败--"+URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
					 me.setMsgString("资金冻结失败--"+URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
				}
				
				this.bizdataservice.bizSave(EnchashmentApply.class, ea);
			}
			 return me;
		} catch (Exception e) {
			e.printStackTrace();
			me.setMsgString("处理接收出错...");
			return me;
		}
	}
	
	/**
	 * 解冻资金
	 * @return
	 */

	public UsrUnFreezeReturn UsrUnFreeze(Freeze unfreeze) throws Exception{
			UsrUnFreeze userunfree=new UsrUnFreeze();
			userunfree.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(
					unfreeze.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
			"yyyyMMdd"));
			userunfree.setOrdId(unfreeze.getId().toString());
			userunfree.setTrxId(unfreeze.getTrxId());
			userunfree.setBgRetUrl(SignUtils.PUBLIC_HOST
					+ "huifucallback/printUsrUnFreezeResult");
		  Map<String,String>	params= userunfree.getParam();
		  String jsonString = TransSubmit.doPost(params);
			if(jsonString==null||jsonString.equals("")){
				return null;
			}
			 Gson gson = new Gson();
			 UsrUnFreezeReturn freezeBgReturn=gson.fromJson(jsonString, UsrUnFreezeReturn.class);
			 return freezeBgReturn;
	}	
	/**
	 * 标解冻资金
	 * @return
	 */
	
	public UsrUnFreezeReturn UsrTenderUnFreeze(Freeze unfreeze) throws Exception{
			UsrUnFreeze userunfree=new UsrUnFreeze();
			userunfree.setOrdDate(StringUtil.DateToString(StringUtil.StringToDate(
					unfreeze.getCreateTime(), "yyyy-MM-dd HH:mm:ss"),
			"yyyyMMdd"));
			userunfree.setOrdId(unfreeze.getId().toString());
			userunfree.setTrxId(unfreeze.getTrxId());
			userunfree.setBgRetUrl(SignUtils.PUBLIC_HOST
					+ "huifucallback/printUsrTenderUnFreezeResult");
		  Map<String,String>	params= userunfree.getParam();
		  String jsonString = TransSubmit.doPost(params);
			if(jsonString==null||jsonString.equals("")){
				return null;
			}
			 Gson gson = new Gson();
			 UsrUnFreezeReturn freezeBgReturn=gson.fromJson(jsonString, UsrUnFreezeReturn.class);
			 return freezeBgReturn;
	}	
	//处理汇付发送的资金申请解冻return 
	public message dobgUnFreeState(UsrUnFreezeReturn freezeBgReturn ){
		message me=new message();
		 me.setMsgCode("false");
		try {
			boolean flag = freezeBgReturn.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				me.setMsgString("验证签名失败...");
				return me;
			}
		    String	 orderid = freezeBgReturn.getOrdId();
			Freeze en=(Freeze) this.bizdataservice.bizfindbyid(Freeze.class, orderid);
			Integer orderflag=en.getFlag();
			if(orderflag!=null&&orderflag==0){
				 me.setMsgCode("success");
				return me;
			}
			if(freezeBgReturn.getRespCode().equals("000")){
				en.setFlag(0);
				me.setMsgCode("success");
			}else{
				en.setFlag(1);
				me.setMsgString(URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
			}
			en.setRespCode(freezeBgReturn.getRespCode());
			en.setRespDesc(freezeBgReturn.getRespDesc());
			this.bizdataservice.bizSave(Freeze.class, en);
		    //查找资金申请
			EnchashmentApply ea=null;
			if(en.getApplyid()!=null){
				ea=(EnchashmentApply) this.bizdataservice.bizfindbyid(EnchashmentApply.class,en.getApplyid());
			}
			//修改资金申请状态 如果成功
			if(ea!=null){
				if(en.getFlag()==0){
					 ea.setUnfreeze(en);
					 me.setMsgCode("success");
				}else{
					//资金冻结失败
					ea.setTipmes("资金解冻失败--"+URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
					me.setMsgString("资金解冻失败--"+URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
					   //设置状态为取现失败
					ea.setState(new Dict(43));
				}
				this.bizdataservice.bizSave(EnchashmentApply.class, ea);
			}   
				
			 return me;
		} catch (Exception e) {
			e.printStackTrace();
			me.setMsgString("处理解冻请求出错...");
			return me;
		}
	}
	//处理汇付发送的标 资金  解冻return 
	public message dobgTenderUnFreeState(UsrUnFreezeReturn freezeBgReturn ){
		message me=new message();
		 me.setMsgCode("false");
		try {
			boolean flag = freezeBgReturn.validate();
			if (!flag) {
				System.out.println("验证签名失败...");
				me.setMsgString("验证签名失败...");
				return me;
			}
		    String	 orderid = freezeBgReturn.getOrdId();
			Freeze en=(Freeze) this.bizdataservice.bizfindbyid(Freeze.class, orderid);
			Integer orderflag=en.getFlag();
			if(orderflag!=null&&orderflag==0){
				 me.setMsgCode("success");
				return me;
			}
			if(freezeBgReturn.getRespCode().equals("000")){
				en.setFlag(0);
			}else{
				en.setFlag(1);
			}
			en.setRespCode(freezeBgReturn.getRespCode());
			en.setRespDesc(URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
			this.bizdataservice.bizSave(Freeze.class, en);
		    //通过解冻对象的trxid  查找标 理论上是唯一的。
			List<Object> tenders=  	this.bizdataservice.find("from  Tender  where freezeTrxId=?",en.getTrxId());
			if(tenders==null||tenders.size()==0){
			 return me;
			}
			Tender  tender=	(Tender) tenders.get(0);
			//标解冻资金申请状态 如果成功
			if(tender!=null){
				if(en.getFlag()==0){
					tender.setUnFreezeOrdId(en.getId().toString());
					//发送解冻消息
					   User user=(User) this.bizdataservice.bizfindbyid(User.class, en.getUsrCustId());
					   Map<String,String> params=new HashMap<String, String>();
					   params.put("proname", tender.getProject().getName());
					   params.put("money", tender.getTransAmt().toString());
					   msgutil.sendmessage("17",new String[]{"phone","webmeg"}, user,  params);
					 me.setMsgCode("success");
				}else{
					//资金解冻失败
					me.setMsgString("资金解冻失败--"+URLDecoder.decode(freezeBgReturn.getRespDesc(),"UTF-8"));
					   //设置状态为取现失败
					tender.setUnFreezeState(1);
				}
			}
			 this.bizdataservice.bizSave(Tender.class, tender);
			 return me;
		} catch (Exception e) {
			e.printStackTrace();
			me.setMsgString("处理解冻请求出错...");
			return me;
		}
	}
	//解冻
	public message unFreezeman(Freeze freeze){
		message me=new message();
		 me.setMsgCode("false"); 
		 try {			   
			      
			     freeze=(Freeze) this.bizdataservice.bizfindbyid(Freeze.class, freeze.getId());
			     Freeze unfreeze=new Freeze();
			     unfreeze.setType(new Dict(41));
			     unfreeze.setUsrCustId(freeze.getUsrCustId());
			     unfreeze.setTransAmt(freeze.getTransAmt());		
			     unfreeze.setTrxId(freeze.getTrxId());
			     unfreeze= (Freeze) this.bizdataservice.bizSave(Freeze.class, unfreeze);
			    
				 UsrUnFreezeReturn  bgr=null;
				 try {
				    bgr= UsrUnFreeze(unfreeze);
				} catch (Exception e) {
					e.printStackTrace();
					  me.setMsgString("资金解冻请求出错..");
					  return me;
				}
				 if(bgr==null){
			   	  me.setMsgString("正在等待资金解冻结果.."); 
				  return me;
				 }else{
				   me=dobgUnFreeState(bgr);
                   return me;
			     }
		} catch (Exception e) {
			e.printStackTrace();
			  me.setMsgString("资金解冻请求出错..");
			  return me;
		}
	}
	//撤标
	public message cancelTender(Tender tender){
		message me=new message();
		 me.setMsgCode("false"); 
    if(tender==null||tender.getId()==null||tender.getId().equals("")){
    	me.setMsgString("参数传递错误");
    }else{
    	try {
    		tender=(Tender) this.bizdataservice.bizfindbyid(Tender.class, tender.getId());
    		Freeze freeze = (Freeze) this.bizdataservice.bizfindbyid(Freeze.class,tender.getFreezeOrdId());
    		//查询该冻结ID 是否已被解冻
    		List<Object> unfreezeobj=this.bizdataservice.find("from Freeze where flag=0 and type.id=41 and trxId=? ",freeze.getTrxId());
    		if(unfreezeobj!=null&&unfreezeobj.size()>0){
    			me.setMsgCode("success");
    			me.setMsgString("撤标成功.."); 
    		}else{
    			//重新解冻
    			me=unFreezeman(freeze);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			me.setMsgString("资金解冻请求出错..");
		}
    }
	  if(me.getMsgCode().equals("success")){
		//产品
		  Project project=tender.getProject();
		  project.set_now_money(project.get_now_money().subtract(tender.getTransAmt()));
		  project.setNow_money(project.getNow_money().subtract(tender.getTransAmt()));
		  project.setPay_number(project.getPay_number()-1);
		  try {
			this.bizdataservice.bizSave(Project.class, project);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			me.setMsgCode("false");
			me.setMsgString("修改产品募集金额失败.."); 
		}
		  tender.setFlag(null);
		  try {
			this.bizdataservice.bizSave(Tender.class, tender);
		} catch (Exception e) {
			e.printStackTrace();
			me.setMsgCode("false");
			me.setMsgString("修改标状态失败.."); 
		}
		//还原红包
		if(tender.getLuckyId()!=null){
			LuckyMoney	lucky=(LuckyMoney) this.bizdataservice.bizfindbyid(LuckyMoney.class, tender.getLuckyId());
		    lucky.setState(0);
		    try {
				this.bizdataservice.bizSave(LuckyMoney.class, lucky);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				me.setMsgCode("false");
				me.setMsgString("修改红包状态失败.."); 
			}
		}
		}
		return me;
	}
	
}
