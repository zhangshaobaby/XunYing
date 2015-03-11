package com.zc.base.util;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.search.exception.impl.LogErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zc.base.action.Action;
import com.zc.base.po.Dict;
import com.zc.bsm.pojo.Message;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.bizDataService;

//发送消息
/**
 * senddirs:发送目的地 发送到手机或者发送到站内信 集合中存放  phone webmeg;
 * phone 手机号码
 * user：发送到的用户对象 如果是站内信该 参数不能为空
 * actionName
 */
@Component(value = "msgUtil")
public  class msgUtil {
	protected static final Log log = LogFactory.getLog(msgUtil.class);
	@Autowired
	private  bizDataService bizdataservice;
  public   boolean    sendmessage(String type,String[] senddirs,User user,Map<String,String> prams){
	  if(type==null||senddirs==null||senddirs.length==0){
		  log.error("参数错误");
		  return false;
	  }
	  String serverPhone="4000573090";
	  String message="";
	  if(type.equals("1")){
		 message= "尊敬的投资者，您购买的产品"+prams.get("proname")+"，第"+prams.get("count")+"期还款,金额"+prams.get("money")+"元，客服电话："+serverPhone; 
	  }else if(type.equals("2")){
		   message= "您推荐的用户"+prams.get("touser")+"发生一笔投资，如产品成立，您将获得一笔推荐奖励。客服电话："+serverPhone;
	       
	  }
	  else if(type.equals("13")){
		   message= "您服务的客户"+prams.get("touser")+"发生一笔投资，如产品成立，您将获得一笔推荐奖励。客服电话："+serverPhone; 
		  }
	  else if(type.equals("3")){
		   message= "感谢您参与了我们的"+prams.get("activtityName")+"活动，我们会及时推送我们的活动内容，期待您的再次参与！客服电话："+serverPhone;
	  
	  }
	  else if(type.equals("4")){
		   message= "您已注册成为我们的理财顾问，并获得1717元投资大礼包，客服电话："+serverPhone;
	  }
	  else if(type.equals("5")){
		   message= "您一笔推荐奖励"+prams.get("money")+"元已发放到账户，请登录www.1717tou.com查看详情。客服电话："+serverPhone;
	  }
	  else if(type.equals("19")){
		   message= "您一笔服务奖励"+prams.get("money")+"元已发放到账户，请登录www.1717tou.com查看详情。客服电话："+serverPhone;
	  }
	  else if(type.equals("6")){
		   message= "您已成功绑定银行卡"+prams.get("bankcard")+"，感谢您对一起一起投的支持！客服电话："+serverPhone;
	  }else if(type.equals("7")){
		   message= "恭喜注册成功！您已获得1717元投资大礼包，客服电话"+serverPhone;
	  }
	  else if(type.equals("8")){
		   message= "恭喜注册成功！您已获得1717元投资大礼包，客服电话"+serverPhone;
	  }
	  else if(type.equals("9")){
		   message= "尊敬的投资者，您已成功申请提现银行卡号（"+prams.get("bankcard").substring(0,4)+"*********"+prams.get("bankcard").substring(15)+"）"+prams.get("money")+"元，预计T加3个工作日内到账。";
	  }
	  else if(type.equals("10")){
		   message= "尊敬的投资者，您已成功申请购买"+prams.get("proname")+"产品，投资金额"+prams.get("money")+"元已在账户中冻结，客服电话"+serverPhone;
	  }
	  else if(type.equals("11")){
			 message= "尊敬的投资者，您购买的产品"+prams.get("proname")+"，归还本金金额"+prams.get("money")+"元已入账，客服电话："+serverPhone; 
		  }
	  else if(type.equals("12")){
		  
			 message= "您购买的产品"+prams.get("proname")+"正式成立，恭喜您投资成功。客服电话："+serverPhone; 
		  }
	 
	   else if(type.equals("14")){
			  message= "好消息，您推荐的用户（ 手机号："+prams.get("phone")+"）刚刚注册了一起一起投，客服电话："+serverPhone; 
		  }
	   else if(type.equals("18")){
			  message= "好消息，您服务的客户（ 手机号："+prams.get("phone")+"）刚刚注册了一起一起投，客服电话："+serverPhone; 
		  }
	   else if(type.equals("15")){
			  message= "尊敬的投资者，您已在平台成功充值"+prams.get("money")+"元，可以开始购买产品。客服电话："+serverPhone; 
		  }  
	   else if(type.equals("16")){
			  message= "您购买的产品"+prams.get("proname")+"募集成功，等待产品成立,客服电话："+serverPhone; 
		}else if(type.equals("17")){
			  message= "您购买的产品"+prams.get("proname")+"募集失败，您的账户已返还冻结款项"+prams.get("money")+"元，客服电话："+serverPhone;  
		}
		else if(type.equals("22")){
			  message= "尊敬的投资者，您购买的产品"+prams.get("proname")+"不成立，投资金额"+prams.get("money")+"元返还账户，注意查收";  
		}
		else if(type.equals("20")){
			  message= "尊敬的投资者，您正在进行"+prams.get("action")+",您的验证码是："+prams.get("code")+"，请勿泄漏。客服电话："+serverPhone;  
		}else if(type.equals("21")){
			//	message="恭喜您注册成功，您的初始密码为"+prams.get("pwd")+"，现在登录还送1717红包哦，年化收益8-11%屌丝宝理财产品，全场100元起投，还能赢取更多屌丝币。一起玩，一起赚，就来一起一起投";
			  message= "恭喜注册成功！您已获得1717元投资大礼包,您的初始化密码是："+prams.get("pwd")+"，客服电话："+serverPhone;  
		}
	  
	  else{
		  return false;
	  }
	  message="【一起一起投】"+message;
	for(String senddir:senddirs ){
		//发送到手机
		if(senddir.equals("phone")){
			if(user==null||user.getPhone()==""){
				log.error("发送手机信息失败！手机号码为空");
				continue;
			}
			SmsHttp sms = new SmsHttp();
			boolean ispush=false;
			try {
				ispush = sms.SendMsg(user.getPhone(), StringUtil.getDecode2(message));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				log.error("发送到手机出错:"+message);
			}
			if(ispush==false){
				log.error("发送到手机失败:"+message);
			}
		}else if(senddir.equals("webmeg")){
			if(user==null){
				log.error("发送站内信息失败！接收用户为空");
				continue;
			}
			//发送站内信
			Message msg=new Message();
			msg.setType(new Dict(59));
			msg.setContent(message);
			msg.setDestUser(user);
			msg.setHaveLook(0);
			try {
				this.bizdataservice.bizSave(Message.class, msg);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("发送站内信出错");
			}
		}
		
		
	}
	  
	  return true;
  }
  public static void main(String[] args) {
	msgUtil m=new msgUtil();
	User user=new User();
	user.setPhone("18659630571");
	Map<String,String> prams=new HashMap<String, String>();
	prams.put("bankcard", "123454556666666");
	prams.put("money", "1000");
	m.sendmessage("9", new String[]{"phone"}, user, prams);
}
}
