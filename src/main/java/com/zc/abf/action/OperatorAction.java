package com.zc.abf.action;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zc.abf.pojo.Operator;
import com.zc.abf.service.OperatorService;
import com.zc.base.action.Action;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.QueryAccts;
import com.zc.bsm.vo.UsrFreezeBg;
import com.zc.bsm.vo.UsrUnFreeze;
import com.zc.bsm.vo.returnVo.AcctDetailResult;
import com.zc.bsm.vo.returnVo.CashReconciliatioResult;
import com.zc.bsm.vo.returnVo.QueryAcctsReturn;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.vo.returnVo.UsrFreezeBgReturn;
@Controller
@RequestMapping("/auth/operator")
@Scope("prototype")
public class OperatorAction extends Action{
@Autowired
private  bizDataService bizdataservice;
@Autowired
public EnchashmentService enchashmentService;
 @Autowired
 private OperatorService OperatorService;

 @RequestMapping(value="/listOperator")
 public String listOperator(ModelMap map,Page page){
	 if(page==null){
		 page=new Page<Operator>();
	 }
	 page=this.OperatorService.findByPage(page);
	 map.put("page", page);
	 return "operator/list";
 }
 @RequestMapping(value="/saveOperator")
 @ResponseBody
 public  String saveOperator(Operator Operator){
	 try {
		 String md5Password = this.OperatorService.md5(Operator.getPwd());
		 Operator.setPwd(md5Password);
		 this.bizdataservice.bizSave(Operator.class, Operator);
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
		return "suceess";
 }
 @RequestMapping(value="/udpateOperatorRoll")
 @ResponseBody
 public  String udpateOperatorRoll(Operator Operator){
	 try {
		 Operator old = this.OperatorService.get(Operator.getId());
		 Operator.setPwd(old.getPwd());
		 this.bizdataservice.bizSave(Operator.class, Operator);
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
		return "suceess";
 }
 @RequestMapping(value="/changePwd")
 public String changePwd(ModelMap map,HttpSession session){
	 Operator old = (Operator)session.getAttribute("operator");
	 map.put("operator", old);
	 return "operator/changePwd";
 }
 @RequestMapping(value="/doChangePwd")
 public String doChangePwd(ModelMap map,HttpSession session,String pwd){
	 try {
		 Operator old = (Operator)session.getAttribute("operator");
		 old.setPwd(this.OperatorService.md5(pwd));
		 this.bizdataservice.bizSave(Operator.class, old);
	 } catch (Exception e) {
		 e.printStackTrace();
	 }
	 return "operator/edit";
 }
 @RequestMapping(value="/editOperator")
 public String editOperator(Operator Operator,ModelMap map){
	 try {
		 Object persistencebject=null;
		 if(Operator!=null&&Operator.getId()!=null&&!Operator.getId().equals("")){
			 persistencebject=OperatorService.get(Operator.getId());
	 	 }
	    Object newOperator= bizdataservice.getPersistenceObj(Operator.class, persistencebject, Operator);
	    map.put("operator", newOperator);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return "operator/edit";
 }
 @RequestMapping(value="/OperatorFrame")
 public String OperatorFrame(){
	 return "operator/listFrame";
 }
 @RequestMapping(value="/delOperator")
 @ResponseBody
 public boolean delOperator(String ids) {
	 return  this.bizdataservice.bizDel("com.zc.abf.pojo.Operator", ids);
 };
 
	@RequestMapping(value = "/recharge")
	//平台充值页面
	public String recharge(String gateBusiId,HttpServletRequest request,ModelMap map,HttpSession session){
		map.put("huifuID",SignUtils.MER_CUST_ID);
		map.put("gateBusiId",gateBusiId.trim());
        return "enterprise/enterpriceRecharge";
	}
	@RequestMapping(value = "/enterpriserecharge")
	//企业充值页面
	public String enterpriserecharge(HttpServletRequest request,ModelMap map,HttpSession session,String  usrCustId){
		map.put("huifuID",usrCustId);
		map.put("gateBusiId","B2B");
        return "enterprise/enterpriceRecharge";
	}
	//平台账号
	@RequestMapping(value = "/raceterracemanager")
	public String raceterracemanager(HttpServletRequest request,ModelMap map,HttpSession session,String  custID){
     //查询子账户
		try {
			//查询当前平台余额
			User user=new User();
			user.setHuifuID(SignUtils.MER_CUST_ID);
			QueryBalanceBgReturn qbr=enchashmentService.getBalanceObj(user);
			map.put("AvlBal", qbr.getAvlBal());
			map.put("AcctBal", qbr.getAcctBal());
			list=new ArrayList<Object>();
			QueryAccts qac=new QueryAccts();
			Map<String, String> params = qac.getParam();
			String jsonString = TransSubmit.doPost(params);
			JSONObject js = JSONObject.parseObject(jsonString);
			JSONArray jr = js.getJSONArray("AcctDetails");
			for (int i = 0; i < jr.size(); i++) {
				JSONObject subjo = jr.getJSONObject(i);
				Gson gson = new Gson();
				AcctDetailResult rdr = gson.fromJson(subjo
						.toJSONString(), AcctDetailResult.class);
				list.add(rdr);
			}
			List<Map<String,Object>> listmap=TransSubmit.getmapobj(list);
			map.put("list", listmap);			
			


	
		} catch (Exception e) {
		e.printStackTrace();
		} 
		
		try {
		String	date = StringUtil.DateToString(new Date(), "yyyy-MM-dd");
		Date  yesterday= StringUtil.getIntervalDate(new Date(), -1);
		String	ydate = StringUtil.DateToString(yesterday, "yyyy-MM-dd");
		
		String yhql="select count(*) from User where substring(createTime,1,10)=? and type<>2";
	    List<Object> y1Times=this.bizdataservice.find(yhql,ydate);		   
		map.put("y1Times",  y1Times.get(0).toString());			
		yhql="select count(*) from User where substring(createTime,1,10)=? and huifuID!=null and type<>2";
	    List<Object> y1hfTimes=this.bizdataservice.find(yhql,ydate);		   
		map.put("y1hfTimes",  y1hfTimes.get(0).toString());
		yhql="select count(*) from User where substring(createTime,1,10)=?  and type=2";
	    List<Object> y2Times=this.bizdataservice.find(yhql,ydate);		   
		map.put("y2Times",  y2Times.get(0).toString());
		yhql="select count(*) from User where substring(createTime,1,10)=? and huifuID!=null and type=2";
	    List<Object> y2hfTimes=this.bizdataservice.find(yhql,ydate);		   
		map.put("y2hfTimes",  y2hfTimes.get(0).toString());
		
		
		String hql="select count(*) from User where substring(createTime,1,10)=? and type<>2";
	    List<Object> today1Times=this.bizdataservice.find(hql,date);		   
		map.put("today1Times",  today1Times.get(0).toString());			
		hql="select count(*) from User where substring(createTime,1,10)=? and huifuID!=null and type<>2";
	    List<Object> today1hfTimes=this.bizdataservice.find(hql,date);		   
		map.put("today1hfTimes",  today1hfTimes.get(0).toString());
		hql="select count(*) from User where substring(createTime,1,10)=?  and type=2";
	    List<Object> today2Times=this.bizdataservice.find(hql,date);		   
		map.put("today2Times",  today2Times.get(0).toString());
		hql="select count(*) from User where substring(createTime,1,10)=? and huifuID!=null and type=2";
	    List<Object> today2hfTimes=this.bizdataservice.find(hql,date);		   
		map.put("today2hfTimes",  today2hfTimes.get(0).toString());
		
		hql="select count(*) from User where  type<>2";
		List<Object> all1Times=this.bizdataservice.find(hql);		   
	    map.put("all1Times",  all1Times.get(0).toString());			
		hql="select count(*) from User where huifuID!=null and type<>2";
		List<Object> all1hfTimes=this.bizdataservice.find(hql);		   
		map.put("all1hfTimes",  all1hfTimes.get(0).toString());
		hql="select count(*) from User where  type=2";
		List<Object> all2Times=this.bizdataservice.find(hql);		   
		map.put("all2Times",  all2Times.get(0).toString());
		hql="select count(*) from User where huifuID!=null and type=2";
		List<Object> all2hfTimes=this.bizdataservice.find(hql);		   
		map.put("all2hfTimes",  all2hfTimes.get(0).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "enterprise/raceterracemanager";
	}
	
public OperatorService getOperatorService() {
	return OperatorService;
}

public void setOperatorService(OperatorService OperatorService) {
	this.OperatorService = OperatorService;
}

}
