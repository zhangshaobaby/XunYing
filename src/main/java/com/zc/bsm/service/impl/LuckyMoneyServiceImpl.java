package com.zc.bsm.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.bsm.dao.LuckyMoneyDao;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;


@Service("luckyMoneyService")
public class LuckyMoneyServiceImpl extends BaseServiceImpl<LuckyMoney, Long> implements LuckyMoneyService{
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	private  UserService userService;
	private static Map LUCKYMONEY_PROPERTIES = new HashMap();
	public LuckyMoneyServiceImpl(){
		//("来源","使用红包投资下限，红包金额，红包有效期（月），红包使用范围"）
		List<String> list = new ArrayList<String>();
		//list.add("100,7,6,0|1|2|3|x");
		list.add("100,7,6,0|1|2");
		list.add("100,10,6,0|1|2");
		list.add("50000,100,6,0|1|2");
		list.add("10000,100,6,2");
		list.add("50000,500,6,2");
		list.add("100000,1000,6,2");
		this.LUCKYMONEY_PROPERTIES.put("0", list);
		//System.out.println("lucky properties ready");
	}
	
	@Autowired
	public void setBaseDao(LuckyMoneyDao advertDao) {
		this.baseDao = advertDao;
	}
	public Page findVerifyByPage(Page page,LuckyMoney luckyMoney){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select new List(p.name,sum(l.money),l.updateTime,p.id) from LuckyMoney as l , Project as p where l.projectId=p.id and l.state = 2 and l.flag = 0 and p.flag = 0 group by l.projectId");
		
		page.setHql(hql.toString());
		return this.findByPage(page);
	}
	public Page findPage(Page page,LuckyMoney luckyMoney){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from LuckyMoney where flag = 0");
		if(luckyMoney.getUid()!=null&&!luckyMoney.getUid().equals("")){
			hql.append(" and uid = ? ");
			param.add(luckyMoney.getUid());
		}
		if(luckyMoney.getState()!=null){
			hql.append(" and state = ? ");
			param.add(luckyMoney.getState());
		}
		if(luckyMoney.getStart_time()!=null&&!luckyMoney.getStart_time().equals("")){
			hql.append(" and start_time <= ? ");
			param.add(luckyMoney.getStart_time());
		}
		if(luckyMoney.getEnd_time()!=null&&!luckyMoney.getEnd_time().equals("")){
			hql.append(" and end_time >= ? ");
			param.add(luckyMoney.getEnd_time());
		}
		if(luckyMoney.getInvestLimit()!=null){
			hql.append(" and investLimit <= ? ");
			param.add(luckyMoney.getInvestLimit());
		}
		if(luckyMoney.getType()!=null){
			hql.append(" and type = ? ");
			param.add(luckyMoney.getType());
		}
		//0    	3		 1     null
		//未		已经		 冻     全部
		if(luckyMoney.getState()!=null){
			if(luckyMoney.getState()==0){
				hql.append(" order by money desc,end_time");
			}else if(luckyMoney.getState()==3){
				hql.append("order by updateTime desc");
			}else if(luckyMoney.getState()==1){
				hql.append("order by updateTime desc");
			}
		}else{
			hql.append(" order by money desc,end_time");
		}
		//hql.append(" order by money desc,end_time");
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public List findVerifyListByPid(Long pid){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from LuckyMoney where state = 2 and flag = 0 and uid is not NULL and projectId = ?");
		param.add(pid);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public List findListBySelf(LuckyMoney luckyMoney){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select new List(id,money,start_time,end_time,investLimit) from LuckyMoney where flag = 0 ");
		if(luckyMoney.getUid()!=null&&!luckyMoney.getUid().equals("")){
			hql.append(" and uid = ? ");
			param.add(luckyMoney.getUid());
		}
		if(luckyMoney.getState()!=null){
			hql.append(" and state = ? ");
			param.add(luckyMoney.getState());
		}
		if(luckyMoney.getStart_time()!=null&&!luckyMoney.getStart_time().equals("")){
			hql.append(" and start_time <= ? ");
			param.add(luckyMoney.getStart_time());
		}
		if(luckyMoney.getEnd_time()!=null&&!luckyMoney.getEnd_time().equals("")){
			hql.append(" and end_time >= ? ");
			param.add(luckyMoney.getEnd_time());
		}
		if(luckyMoney.getInvestLimit()!=null){
			hql.append(" and investLimit <= ? ");
			param.add(luckyMoney.getInvestLimit());
		}
		if(luckyMoney.getType()!=null){
			hql.append(" and LOCATE(?,type)!=0 ");
			param.add(luckyMoney.getType());
		}
		hql.append(" order by money desc,end_time");
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List findListBySelf2(LuckyMoney luckyMoney){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select new List(id,money,start_time,end_time,investLimit) from LuckyMoney where flag = 0 ");
		if(luckyMoney.getUid()!=null&&!luckyMoney.getUid().equals("")){
			hql.append(" and uid = ? ");
			param.add(luckyMoney.getUid());
		}
		if(luckyMoney.getState()!=null){
			hql.append(" and state = ? ");
			param.add(luckyMoney.getState());
		}
		if(luckyMoney.getStart_time()!=null&&!luckyMoney.getStart_time().equals("")){
			hql.append(" and start_time <= ? ");
			param.add(luckyMoney.getStart_time());
		}
		if(luckyMoney.getEnd_time()!=null&&!luckyMoney.getEnd_time().equals("")){
			hql.append(" and end_time >= ? ");
			param.add(luckyMoney.getEnd_time());
		}
		if(luckyMoney.getInvestLimit()!=null){
//			hql.append(" and money <= ? ");
//			param.add(luckyMoney.getInvestLimit());
			//适应非正常红包可用
			hql.append(" and (money <= ? or  LOCATE('x',type)!=0)");
			param.add(luckyMoney.getInvestLimit());
		}
		if(luckyMoney.getType()!=null){
			hql.append(" and LOCATE(?,type)!=0 ");
			param.add(luckyMoney.getType());
		}
		hql.append(" order by money desc,end_time");
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List findListBySelf3(LuckyMoney luckyMoney){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("select new List(id,money,start_time,end_time,investLimit) from LuckyMoney where flag = 0 ");
		if(luckyMoney.getUid()!=null&&!luckyMoney.getUid().equals("")){
			hql.append(" and uid = ? ");
			param.add(luckyMoney.getUid());
		}
		if(luckyMoney.getState()!=null){
			hql.append(" and state = ? ");
			param.add(luckyMoney.getState());
		}
		if(luckyMoney.getStart_time()!=null&&!luckyMoney.getStart_time().equals("")){
			hql.append(" and start_time <= ? ");
			param.add(luckyMoney.getStart_time());
		}
		if(luckyMoney.getEnd_time()!=null&&!luckyMoney.getEnd_time().equals("")){
			hql.append(" and end_time >= ? ");
			param.add(luckyMoney.getEnd_time());
		}
		if(luckyMoney.getType()!=null){
			hql.append(" and LOCATE(?,type)!=0 ");
			param.add(luckyMoney.getType());
		}
		hql.append(" order by money asc,end_time");
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public void saveLuckyMoney(LuckyMoney luckyMoney,User user){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		List<String> list = (List<String>)this.LUCKYMONEY_PROPERTIES.get(luckyMoney.getComeFrom().toString());

		String[]prop=null;
		LuckyMoney lucky = null;
		for(String str:list){
			date = new Date();
			prop = str.split(",");
			lucky = new LuckyMoney();
			lucky.setComeFrom(luckyMoney.getComeFrom());
			lucky.setInvestLimit(new BigDecimal(prop[0]));
			lucky.setMoney(new BigDecimal(prop[1]));
			lucky.setUid(user.getId());
			lucky.setPhone(user.getPhone());
			lucky.setCreateTime(sdf.format(new Date()));
			lucky.setUpdateTime(sdf.format(new Date()));
			lucky.setStart_time(sdf.format(date).substring(0,10)+" 00:00:00");
			date.setMonth(date.getMonth()+Integer.parseInt(prop[2]));
			lucky.setEnd_time(sdf.format(date).substring(0,10)+" 23:59:59");
			lucky.setType(prop[3].replaceAll("\\|", ","));
			lucky.setFlag(0);
			lucky.setState(0);
			this.baseDao.save(lucky);
		}
	}
	@Transactional
	public User changeToScore(String ids,User user,HttpSession session){
		String [] _ids = ids.split(",");
		Long []__ids = new Long[_ids.length];
		for( int i = 0 ; i < _ids.length ; i++ ){
			__ids[i] = Long.parseLong(_ids[i]);
		}
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from LuckyMoney where id in ("+ids+") and uid = ? and end_time > ? and state = 0 ");
		param.add(user.getId());
		param.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		List list = this.baseDao.findByHql(hql.toString(), param.toArray());
		LuckyMoney lucky = null;
		ids = "";
		BigDecimal score = new BigDecimal(0l);
		String summary = "";
		for(int i = 0;i<list.size();i++){
			lucky = (LuckyMoney)list.get(i);
			ids+=lucky.getId()+",";
			//积分流水表
			summary = "兑换金额为"+lucky.getMoney()+"的红包";
			userService.addScore("1", user,session, lucky.getMoney(), summary);
		}
		if(ids!="")
			ids = ids.substring(0,ids.length()-1);
		//更新用户积分
		
		//更改红包状态
		hql = new StringBuilder();
		hql.append("update LuckyMoney set state = 4 where id in ("+ids+")");
		this.baseDao.update(hql.toString(),null);
		try{
			return (User)this.bizdataservice.bizfindbyid(User.class, user.getId());
		}catch(Exception e){
			return null;
		}
	}
	public static void main(String[]args){
		
	}
}
