package com.zc.bsm.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.base.action.Action;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.defineAnnotation.NeedOpenHFPayInterceptor;
import com.zc.base.util.Page;
import com.zc.base.util.StringUtil;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.UserService;

@Controller
public class ParadiseAction extends Action{
	@Autowired
	private LuckyMoneyService luckyMoneyService;
	@Autowired
	private  UserService userService;
	
	//乐园首页
	@RequestMapping(value="/paradise")
	@NeedInterceptor
	public String view(Long id,HttpSession session,ModelMap map,String tabid){
		User user=  (User) session.getAttribute("user");
		if(user!=null){
			//积分
	        map.put("score",user.getScore()==null?"0":user.getScore().toString());
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
					e.printStackTrace();
				}
	 		}
	 		//红包列表
	 		LuckyMoney lucky = new LuckyMoney();
	 		lucky.setState(0);
	 		lucky.setUid(user.getId());
	 		lucky.setEnd_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	 		map.put("luckyList",luckyMoneyService.findListBySelf(lucky));
		}
	
		if(tabid==null){
			 tabid="1";		
		}
		map.put("tabid", tabid);
		return "paradise/main";
	}
	@RequestMapping(value="/paradise/changeScore")
	@ResponseBody
	@NeedOpenHFPayInterceptor
	public String changeScore(String ids,HttpSession session){
		User user=  (User) session.getAttribute("user");
		user = luckyMoneyService.changeToScore(ids, user, session);
		session.setAttribute("user",user);
		return "ok";
	}
	@RequestMapping(value="/paradise/scoreDetail")
	@ResponseBody
	public Page scoreDetail(HttpSession session,Page page){
		if(page==null)page = new Page();page.setRows(8);
		User user=  (User) session.getAttribute("user");
		return this.userService.scoreDetail(user,page);
	}
}
