package com.zc.bsm.service;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.User;
public interface LuckyMoneyService extends BaseService<LuckyMoney, Long>{	
	public List findListBySelf(LuckyMoney luckyMoney);
	public List findListBySelf2(LuckyMoney luckyMoney);
	public List findListBySelf3(LuckyMoney luckyMoney);
	public Page findPage(Page page,LuckyMoney luckyMoney);
	public Page findVerifyByPage(Page page,LuckyMoney luckyMoney);
	public List findVerifyListByPid(Long pid);
	public void saveLuckyMoney(LuckyMoney luckyMoney,User user);
	public User changeToScore(String ids,User user,HttpSession session);
}
