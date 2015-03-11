package com.zc.bsm.service.impl;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.base.util.SignTools;
import com.zc.base.util.SignUtils;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.dao.TenderDao;
import com.zc.bsm.pojo.Brokerage;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BrokerageService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.UsrUnFreeze;
import com.zc.bsm.vo.returnVo.InitiativeTenderReturn;
import com.zc.bsm.vo.returnVo.UsrUnFreezeReturn;


@Service("tenderService")
public class TenderServiceImpl extends BaseServiceImpl<Tender, Long> implements TenderService{
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
	public LuckyMoneyService luckyMoneyService;
	@Autowired
	private BrokerageService brokerageService;
	@Autowired
	public void setBaseDao(TenderDao advertDao) {
		this.baseDao = advertDao;
	}
	public List findSumTransmat(Long pid,String usrCustId){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select sum(transAmt) from Tender where projectId = ? and UsrCustId = ? and flag = 0");
		param.add(pid);
		param.add(usrCustId);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	
	public List findByPid(Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Tender where projectId = ? and flag = 0 and state = 1");
		param.add(pid);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List findByPid(Long pid,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Tender where projectId = ? and flag = 0 ");
		param.add(pid);
		if(state!=null){
			hql.append(" and state = ? ");
			param.add(state);
		}
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public Page findPageByPid(Long pid,Page page){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select new List(SUBSTRING(username,1,2),SUBSTRING(username,6),transAmt,createTime) from Tender as t where project.id = ? and flag in (0,2) order by createTime desc");
		param.add(pid);
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page, param.toArray());
	}
	public List checkLoansCount(Long pid,Integer state ){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*) from Tender where project.id = ? and flag = 0");
		param.add(pid);
		if(state!=null){
			hql.append("and state = ?");
			param.add(state);
		}
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List checkLoans(Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Tender where project.id = ? and state = -1 and flag = 0 ");
		param.add(pid);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List checkLoans(Long pid,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Tender where project.id = ? and flag = 0 ");
		param.add(pid);
		hql.append(" and state = ? ");
		param.add(state);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	
	public Page record(Page page,String usrCustId,Integer flag,Integer type,String status){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select new List(p.id,p.name,p.rateTime,p.pendTime,sum(t.transAmt),count(t.id),p.state,DATEDIFF(now(),p.pstartTime),p.pstartTime,p.type,sum(t.srcTransAmt)) from Tender as t,Project as p  where t.project.id = p.id and t.flag = 0 and p.flag = 0 and p.state >= 0");
		if(usrCustId!=null&&!usrCustId.trim().equals("")){
			hql.append(" and t.usrCustId = ? ");
			param.add(usrCustId);
		}
		if(flag!=null){
			hql.append(" and t.flag = ? ");
			param.add(flag);
		}
		if(type!=null){
			hql.append(" and p.type = ? ");
			param.add(type);
		}
		if(status!=null){
			hql.append(" and p.state = ? ");
			param.add(Integer.parseInt(status));
		}
		hql.append(" group by p.id ");
		hql.append(" order by t.createTime desc ");
		page.setHql(hql.toString());
		page = this.baseDao.findByPage(page, param.toArray());
		//if(type!=null&&type.intValue()!=3){
			return page;
//		}else{
//			String pid = "";
//			List<String[]> list = new ArrayList();
//			for(Object[] obj : (List<Object[]>)page.getResult()){
//				pid+=","+obj[0].toString();
//				String[]object = new String[3];
//				object[0]=pid;
//				object[1]=obj[4].toString();
//				object[2]=obj[6].toString();
//			}
//			pid = pid.length()>0?pid.substring(1):pid;
//			
//			return null;
//		}
	}
	
	public Page recorddiaosi(Page page,String usrCustId,Integer flag,Integer type){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select new List(p.id,p.name,p.rateTime,p.pendTime,sum(t.transAmt),count(t.id),p.state,DATEDIFF(now(),p.pstartTime), MAX(r.repayTime)) from Tender as t,Project as p,RepaymentPlan as r  where t.project.id = p.id and t.flag = 0 and p.state >= 0" +
				" and r.tenderId=t.id and r.flag=0 ");
		if(usrCustId!=null&&!usrCustId.trim().equals("")){
			hql.append(" and t.usrCustId = ? ");
			param.add(usrCustId);
		}
		if(usrCustId!=null&&!usrCustId.trim().equals("")){
			hql.append(" and t.flag = ? ");
			param.add(flag);
		}
		if(type!=null){
			hql.append(" and p.type = ? ");
			param.add(type);
		}
		hql.append(" group by p.id ");
		hql.append(" order by t.createTime desc ");
		page.setHql(hql.toString());
		page = this.baseDao.findByPage(page, param.toArray());

			return page;

	}
	public List selfCondition(String usrCustId,Long projectId){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*),sum(transAmt) from Tender where usrCustId = ? and flag = 0 and project.id = ?");
		param.add(usrCustId);
		param.add(projectId);
		return this.baseDao.findByHql(hql.toString(),param.toArray());
	}
	public List loansSum(String usrCustId,Long projectId){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select sum(transAmt) from Tender where usrCustId = ? and flag = 0 and state = 1 and project.id = ?");
		param.add(usrCustId);
		param.add(projectId);
		return this.baseDao.findByHql(hql.toString(),param.toArray());
	}
	//处理解冻之后的标状态
	public void doTenderState(Freeze  en) throws Exception{
	    //通过解冻对象的trxid  查找标 理论上是唯一的。
		List<Object> tenders=  	this.bizdataservice.find("from  Tender  where freezeTrxId=?",en.getTrxId());
		if(tenders==null||tenders.size()==0){
		 return ;
		}
		Tender  tender=	(Tender) tenders.get(0);
		//标解冻资金申请状态 如果成功
		if(tender!=null){
			if(en.getFlag()==0){
				tender.setUnFreezeOrdId(en.getId().toString());
				 ;
			}else{
				   //设置状态为解冻失败
				tender.setUnFreezeState(1);
			}
		}
		 this.bizdataservice.bizSave(Tender.class, tender);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public void tmpTenderResult(String orderid) {
		Tender tender=(Tender) this.bizdataservice.bizfindbyid(Tender.class, orderid);
		//增加推荐人和经纪人推荐奖励		
		String hql = "select user.id,user.agent,tender.transAmt,user.advisor from User as user ,Tender as tender  where tender.usrCustId=user.huifuID and tender.id="
				+ tender.getId() ;
        List<Object> list=this.brokerageService.findByHql(hql);
        for(Object obj:list){
        	//转化为集合  取出user 和 投资总额
        	Object[] sublist= (Object[]) obj;
            String  userid=sublist[0].toString();
            String  agent=sublist[1]==null?null:sublist[1].toString();
            String  transAmt=sublist[2].toString();
            String advisor=sublist[3]==null?null:sublist[3].toString();
            //添加经纪人佣金所得
            if(agent!=null&&!agent.equals("")){  
            	//计算推荐人佣金
        		Brokerage bk=new Brokerage();
        		Brokerage bk2=new Brokerage();

            		User investUser=new User();
            		investUser.setId(userid);
            		User revenueUser=new User();            		
            		revenueUser.setId(agent);	 
            		bk.setTender(tender);	            		
            		bk.setInvestUser(investUser);
            		bk.setRevenueUser(revenueUser);	                      
            		bk.setProject(tender.getProject());	            
            		bk.setTransAmt(new BigDecimal(transAmt));	 
            	    //计算佣金比例
	           		 BigDecimal brokeramt=new BigDecimal(0);
	           		 BigDecimal   commission=  tender.getProject().getCommission()==null?new BigDecimal(0): tender.getProject().getCommission();
	       			 brokeramt=commission.multiply(new BigDecimal(transAmt)).multiply(new BigDecimal(0.01));
	       			 bk.setBrokerage(brokeramt);		            		
       			     bk.setAgentType(1);		       		
            		 bk.setState(new Dict(68));	
          			try {
						bizdataservice.bizSave(Brokerage.class, bk);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            			//计算理财顾问佣金 
        			 if(advisor!=null&&!advisor.equals("")){
		            		bk2.setTender(tender);	            		
	            		bk2.setInvestUser(investUser);
	            		bk2.setRevenueUser(revenueUser);	                      
	            		bk2.setProject(tender.getProject());	            
	            		bk2.setTransAmt(new BigDecimal(transAmt));		
 	            		 revenueUser.setId(advisor);	 
        				 bk2.setRevenueUser(revenueUser);
        				 bk2.setAgentType(2);
        				 commission= tender.getProject().getCommission_agent()==null?new BigDecimal(0): tender.getProject().getCommission_agent();
	            			 brokeramt=commission.multiply(new BigDecimal(transAmt)).multiply(new BigDecimal(0.01));
	            			 bk2.setBrokerage(brokeramt);
        				 bk2.setState(new Dict(68));
        				 
        				 try {
							bizdataservice.bizSave(Brokerage.class, bk2);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			 }
            }
        }
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public boolean InitiativeTenderResult(InitiativeTenderReturn initiativeTenderReturn) throws Exception {
		boolean istrue=false;
		boolean tendersuccess=false;
		String 	orderid = initiativeTenderReturn.getOrdId();
		Tender tender=(Tender) this.bizdataservice.bizfindbyid(Tender.class, orderid);
		if(tender.getFlag()!=null&&tender.getFlag()==0){
			return true;
		}else if(tender.getFlag()!=null&&tender.getFlag()==1){
			return false;
		}	
		CashStream cashStream = new CashStream();
		tender.setUsrCustId(initiativeTenderReturn.getUsrCustId());
        try{	
			if(initiativeTenderReturn.getRespCode().equals("000")){		
				//首次处理
				if(tender.getFlag()==null){
					//List sum = this.baseDao.findByHql("select SUM(transAmt) from Tender where projectId = ? and flag = 0",new Object[]{tender.getProject().getId()});
					//投标总额是否溢出
					if(tender.getProject().getTotal_money().compareTo(tender.getProject().get_now_money().add(tender.getTransAmt()))!=-1){
						//ok
						tender.getProject().setNow_money(tender.getProject().getNow_money().add(tender.getTransAmt()));
						tender.getProject().set_now_money(tender.getProject().get_now_money().add(tender.getTransAmt()));
						tender.getProject().setPay_number(tender.getProject().getPay_number()+1);
						tender.setFlag(0);	
						bizdataservice.bizSave(Project.class, tender.getProject());
						tendersuccess=true;
					}else{
						//解冻标金额并且标失败
						UsrUnFreeze userunfree=new UsrUnFreeze();
						Freeze freeze = new Freeze();
						freeze.setTransAmt(tender.getTransAmt());
						freeze.setTrxId(tender.getFreezeTrxId());
						freeze.setType(new Dict(41));
						freeze.setUsrCustId(tender.getUsrCustId());
						this.bizdataservice.save(freeze);
						userunfree.setOrdId(freeze.getId().toString());
						userunfree.setOrdDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
						userunfree.setTrxId(initiativeTenderReturn.getFreezeTrxId());
						userunfree.setBgRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/printUsrUnFreezeResult");
						Map<String,String>	params= userunfree.getParam();
						String jsonString = TransSubmit.doPost(params);
						Gson gson = new Gson();
						UsrUnFreezeReturn freezeBgReturn=gson.fromJson(jsonString, UsrUnFreezeReturn.class);
						if(freezeBgReturn.getRespCode().equals("000")){
							
						}else{
							
						}
						tender.setFlag(1);
					}

				}else{
					return tender.getFlag()==0?true:false;
				}
			}else{
				tender.setFlag(1);
			}
			tender.setTenderDesc(URLDecoder.decode(initiativeTenderReturn.getRespDesc(),"UTF-8"));
			tender.setOrdDate(initiativeTenderReturn.getOrdDate());
			tender.setFreezeOrdId(initiativeTenderReturn.getFreezeOrdId());
			tender.setFreezeTrxId(initiativeTenderReturn.getFreezeTrxId());
			if(initiativeTenderReturn.getOrdId()!=null&&tender.getSrcTenderId()==null)
				tender.setSrcTenderId(Long.parseLong(initiativeTenderReturn.getOrdId()));
			//修改资金冻结记录
			Freeze free=(Freeze) this.bizdataservice.bizfindbyid(Freeze.class, initiativeTenderReturn.getFreezeOrdId());
			free.setFlag(0);
			free.setTrxId(initiativeTenderReturn.getFreezeTrxId());
			this.bizdataservice.bizSave(Freeze.class, free);
		} catch (Exception e) {
		 System.out.println(e.toString());
		}
		tender.setState(-1);
		bizdataservice.bizSave(Tender.class, tender);
		
		try{
			//投标成功后
			if(tendersuccess){
				/////////////处理红包
				LuckyMoney luckyMoney = null;
				if(tender.getLuckyId()!=null&&tender.getLuckyId()!=0){
					luckyMoney = luckyMoneyService.get(tender.getLuckyId());
					if(luckyMoney!=null){
						luckyMoney.setState(1);
						luckyMoney.setTenderId(tender.getId());
						luckyMoney.setProjectId(tender.getProject().getId());
						this.bizdataservice.saveOrUpdate(luckyMoney);
					}
				}
				Project pro=tender.getProject();
				//增加推荐人和经纪人推荐奖励		
				String hql = "select user.id,user.agent,tender.transAmt,user.advisor from User as user ,Tender as tender  where tender.usrCustId=user.huifuID and tender.id="
						+ tender.getId() ;
		        List<Object> list=this.brokerageService.findByHql(hql);
		        for(Object obj:list){
		        	//转化为集合  取出user 和 投资总额
		        	Object[] sublist= (Object[]) obj;
		            String  userid=sublist[0].toString();
		            String  agent=sublist[1]==null?null:sublist[1].toString();
		            String  transAmt=sublist[2].toString();
		            String advisor=sublist[3]==null?null:sublist[3].toString();
		           	//计算推荐人佣金
	        		Brokerage bk=new Brokerage();
	        		Brokerage bk2=new Brokerage();
            		User investUser=new User();
            		investUser.setId(userid);
            		User revenueUser=new User();            		
            		revenueUser.setId(agent);	 
            		BigDecimal   commission= new BigDecimal(0);
            		BigDecimal brokeramt=new BigDecimal(0);
		            //计算推荐人佣金所得
            		if(pro.getCommission().compareTo(new BigDecimal(0))==1&&agent!=null&&!agent.equals("")){		          
		            		bk.setTender(tender);	            		
		            		bk.setInvestUser(investUser);
		            		bk.setRevenueUser(revenueUser);	                      
		            		bk.setProject(tender.getProject());	            
		            		bk.setTransAmt(new BigDecimal(transAmt));	 
		            	    //计算佣金比例			           		
			           		 commission=  tender.getProject().getCommission()==null?new BigDecimal(0): tender.getProject().getCommission();
			       			 brokeramt=commission.multiply(new BigDecimal(transAmt)).multiply(new BigDecimal(0.01));
			       			 bk.setBrokerage(brokeramt);		            		
		       			     bk.setAgentType(1);		       		
		            		 bk.setState(new Dict(68));	
		          			bizdataservice.bizSave(Brokerage.class, bk);
		            }
        			//计算理财顾问佣金 
            		if(pro.getCommission_agent().compareTo(new BigDecimal(0))==1&&advisor!=null&&!advisor.equals("")){
			            	bk2.setTender(tender);	            		
		            		bk2.setInvestUser(investUser);
		            		bk2.setRevenueUser(revenueUser);	                      
		            		bk2.setProject(tender.getProject());	            
		            		bk2.setTransAmt(new BigDecimal(transAmt));		
		            		revenueUser.setId(advisor);	 
	       				    bk2.setRevenueUser(revenueUser);
	       				    bk2.setAgentType(2);
	       				    commission= tender.getProject().getCommission_agent()==null?new BigDecimal(0): tender.getProject().getCommission_agent();
		            		brokeramt=commission.multiply(new BigDecimal(transAmt)).multiply(new BigDecimal(0.01));
		            		bk2.setBrokerage(brokeramt);
	       				    bk2.setState(new Dict(68));	       				 
	       				    bizdataservice.bizSave(Brokerage.class, bk2);
	       			 }
		        }
			}
		} catch (Exception e) {
			 System.out.println(e.toString());
			}
		
		try{			
			if(tender.getFlag()!=null&&tender.getFlag()==0){
				istrue=true;
				//
				String cTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");		
				cashStream.setOrdId(initiativeTenderReturn.getOrdId());
				try{
					cashStream.setOrdDate(sdf1.format(sdf.parse(initiativeTenderReturn.getOrdDate())));
				}catch(Exception e){
					
				}			
				cashStream.setOutCustId(initiativeTenderReturn.getUsrCustId());
				cashStream.setTransAmt(new BigDecimal(Double.parseDouble(initiativeTenderReturn.getTransAmt())));
				cashStream.setCreateTime(cTime);
				cashStream.setType(new Dict(55));
				cashStream.setFlag(0);
				//可用余额
				try {
					cashStream.setAvlBal(enchashmentService.getBalanceObj(initiativeTenderReturn.getUsrCustId()).getAvlBal());
				} catch (Exception e) {
					cashStream.setAvlBal("未知..可登陆汇付网站查询");
				}
				//设置摘要
				cashStream.setSummary(SignTools.getProjectTypeName(tender.getProject().getType())+"_"+tender.getProject().getName());
				bizdataservice.bizSave(CashStream.class, cashStream);
			
			}
		} catch (Exception e) {
			
		}finally{
			return istrue;
		}
	
	}
//	public Page recordInterface(Page page,String usrCustId,Integer flag,Integer type){
//		StringBuilder hql = new StringBuilder();
//		List<Object> param = new ArrayList<Object>();
//		hql.append("select new List(p.id,p.name,p.rateTime,p.pendTime,sum(t.transAmt),count(t.id),p.state,DATEDIFF(now(),p.pstartTime),p.type) from Tender as t,Project as p  where t.project.id = p.id and t.flag = 0 and p.flag = 0 and p.state >= 0");
//		if(usrCustId!=null&&!usrCustId.trim().equals("")){
//			hql.append(" and t.usrCustId = ? ");
//			param.add(usrCustId);
//		}
//		if(flag!=null){
//			hql.append(" and t.flag = ? ");
//			param.add(flag);
//		}
//		if(type!=null){
//			hql.append(" and p.type = ? ");
//			param.add(type);
//		}
//		hql.append(" group by p.id ");
//		hql.append(" order by t.createTime desc ");
//		page.setHql(hql.toString());
//		page = this.baseDao.findByPage(page, param.toArray());
//		//if(type!=null&&type.intValue()!=3){
//			return page;
////		}else{
////			String pid = "";
////			List<String[]> list = new ArrayList();
////			for(Object[] obj : (List<Object[]>)page.getResult()){
////				pid+=","+obj[0].toString();
////				String[]object = new String[3];
////				object[0]=pid;
////				object[1]=obj[4].toString();
////				object[2]=obj[6].toString();
////			}
////			pid = pid.length()>0?pid.substring(1):pid;
////			
////			return null;
////		}
//	}
	/**
	 * 投标详情
	 */
	public Page tenderDetail(Long id,Page page){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select new Map(t.project.state as pstate,t.id as id,u.realName as realName,u.phone as phone,t.createTime as createTime,t.transAmt as transAmt,t.repayAmt as repayAmt,t.luckyId as luckyId,u.identification as identification) from Tender as t , User as u where projectId = ? and t.flag = 0 and t.usrCustId = u.huifuID");
		param.add(id);
		page.setHql(hql.toString());
		page = this.baseDao.findByPage(page, param.toArray());
		for(Map tender : (List<Map>)page.getResult()){
			if(tender.get("repayAmt")==null)tender.put("repayAmt", "0.00");
			if(tender.get("luckyId")!=null&&!tender.get("luckyId").toString().equals("0")){
				tender.put("luckyMoney", ((LuckyMoney)this.bizdataservice.bizfindbyid(LuckyMoney.class, tender.get("luckyId"))).getMoney());
			}else{
				tender.put("luckyMoney",new BigDecimal(0));
			}
		}
			
		return page;
	}
	public Map recordNum(User user){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select p.type,count(*) from Tender as t , Project as p where t.project.id = p.id and t.usrCustId = ?  and t.flag = 0 and p.flag = 0 group by p.type,p.id");
		param.add(user.getHuifuID());
		List list = this.baseDao.find(hql.toString(), param.toArray());
		Object[]o = null;
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("0", 0);
		map.put("1", 0);
		map.put("2", 0);
		map.put("3", 0);
		for(Object obj:list){
			o = (Object[])obj;
			map.put(o[0].toString(), map.get(o[0].toString())+1);
		}
		return map;
	}
}
