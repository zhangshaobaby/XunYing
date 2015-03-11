package com.zc.bsm.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.User;
import com.zc.bsm.webVo.Recordformobile;

public interface UserService extends BaseService<User,String> {
	// md5加密
	public String md5(String str);
	
	public User login(String username,String password);
	public User login(String username,String phone,String password);
	public String revotePwd(String secretPwd,String uname);
	public String test();
	public User findByName_Phone(String phone);
	public User findByName(String username);
	public User findByPwd_Phone(String loginPwd,String phone);
	public User findByUsername(String username);
	public User findByHuifuId(String huifuId);
	//同接口公用方法开始
	public void mymessages(String type,String haveLook,User user,Page page);
	public boolean setMesHaveLook(User user);
	public boolean setMesHaveLookbyId(String id);
	public void translist(Page page,String start_time, String end_time,String transtype,String dateInterval,User user);
	public void userEstate(ModelMap map,User user);
	public String createContract(User user,long proid,String rootPath,String url);
	public void recordDetail(ModelAndView model,User user,long pid);
	public List<Recordformobile> recordListformobile(User user,Page page,Integer type,String state);
	public String[] initiativetender(String luckyId,String projectId,User user,Integer transAmt);
	//添加积分
	public User addScore(String comeFrom,User user,HttpSession session,BigDecimal money,String summary);
	public Page scoreDetail(User user,Page page);
	
	public void updateBankCard(User user);
}
