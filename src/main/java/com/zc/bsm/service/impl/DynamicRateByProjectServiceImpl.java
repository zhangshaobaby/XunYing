package com.zc.bsm.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignTools;
import com.zc.bsm.dao.DynamicRateByProjectDao;
import com.zc.bsm.pojo.DynamicRateByProject;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.DynamicRateByProjectService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.bizDataService;


@Service("dynamicRateByProjectService")
public class DynamicRateByProjectServiceImpl extends BaseServiceImpl<DynamicRateByProject, Long> implements DynamicRateByProjectService{
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	private  RepaymentPlanService repaymentPlanService;
	@Autowired
	public void setBaseDao(DynamicRateByProjectDao advertDao) {
		this.baseDao = advertDao;
	}
	
	public String tenderMoney(User user,Long projectId){return null;}
	
	/**
	 * 提前赎回
	 */
	public String repayEarly(User user ,Long projectId){
		Project project = (Project)this.bizdataservice.bizfindbyid(Project.class, projectId);
		if(project==null)
			return "产品不存在";
		if(project.getType()!=3)
			return "产品类型错误";
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		Date date  = new Date();//09.26
		hql.append("from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = -1");
		param.add(projectId);
		param.add(user.getHuifuID());
		
		List oldPlan = this.baseDao.findByHql(hql.toString(), param.toArray());
		RepaymentPlan rep = null;
		try{
			if(oldPlan!=null&&oldPlan.size()>0){
				rep = (RepaymentPlan)oldPlan.get(0);
				
				date.setDate(date.getDate()+6);//10.02
				String now = sdf.format(date);
				//09.01  10.01 11.01
				hql = new StringBuilder();
				param = new ArrayList();
				hql.append(" from DynamicRateByProject where pid = ? and flag = 0 and startTime >= ? order by startTime asc");
				param.add(projectId);
				param.add(now);
				List<DynamicRateByProject> list = this.baseDao.find(hql.toString(), param.toArray());
				if(list!=null&&list.size()>0){
					DynamicRateByProject drbp = null;
					try{
					for(int i =0;i<list.size();i++){
						drbp = list.get(0);
						if(sdf.parse(drbp.getStartTime()).compareTo(new Date())==1){
							break;
						}
					}
					}catch(Exception e){}
					
					if(sdf.parse(drbp.getStartTime()).compareTo(sdf.parse(now))==1){
						hql = new StringBuilder();
						hql.append(" from RepaymentPlan where projectId = ? and flag = 0 and usrCustId = ?");
						param = new ArrayList();
						param.add(projectId);
						param.add(user.getHuifuID());
						List repaymentPlan = this.baseDao.findByHql(hql.toString(), param.toArray());
						Tender tender = null;
						for(Object obj : repaymentPlan){
							rep = (RepaymentPlan)obj;
							rep.setRepayTime(drbp.getStartTime());
							rep.setApplyTime(sdf1.format(d));
							rep.setCType("1");
							if(rep.getRepayCount()!=0){
								tender = (Tender)this.bizdataservice.bizfindbyid(Tender.class, rep.getTenderId());
								rep.setRepayMoney(SignTools.getRateByYearRate(project.getRateTime(),drbp.getStartTime(),drbp.getRate().divide(new BigDecimal(100)),tender.getTransAmt()));
							}
							try{
								rep.setUpdateTime(sdf1.format(new Date()));
								this.repaymentPlanService.update(rep);
								//this.bizdataservice.bizSave(RepaymentPlan.class, rep);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}else{
						return "将在下个开放日放款，请耐心等待";
					}
				}else{
					return "暂时无法提取";
				}
			}else{
				return "当前状态无法赎回";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	/**
	 * 当前价值
	 * @return 
	 * List 0  代码    012错误   3可以申请撤销提前还款  4不可申请撤销提前还款  5正常还款   6已经还款	7未生成还款计划 不可申请
	 * List 1  期限以月为单位
	 * List 2  错误描述/金额
	 */
	public List<String> repayEarlyReward(User user ,Long projectId){
		Project project = (Project)this.bizdataservice.bizfindbyid(Project.class, projectId);
		List<String> result = new ArrayList<String>();
		boolean tenderTag = false;
		if(project==null){
			result.add("1");
			result.add("产品不存在");
			return result;
		}
		if(project.getType()!=3){
			result.add("2");
			result.add("产品类型错误");
			return result;
		}
		StringBuilder hql = new StringBuilder();
		Tender tender = null;
		List param = new ArrayList();
		BigDecimal money = new BigDecimal(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date  = new Date();//09.26
		hql.append("from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0");
		param.add(projectId);
		param.add(user.getHuifuID());
		
		List oldPlan = this.baseDao.findByHql(hql.toString(), param.toArray());
		RepaymentPlan rep = null;
		String now = sdf.format(date);
		try{
			
			//计算价值
			if(oldPlan!=null&&oldPlan.size()>0){
				for(Object obj : oldPlan){
					rep = (RepaymentPlan)obj;
					money = money.add(rep.getRepayMoney());
				}
				//判断还款发起者类型是否为用户行为  如果是则为提前还款  否则为正常还款
				rep = (RepaymentPlan)oldPlan.get(0);
				hql = new StringBuilder();
				param = new ArrayList();
				hql.append(" from DynamicRateByProject where pid = ? and flag = 0 and startTime >= ? order by startTime asc");
				param.add(projectId);
				param.add(rep.getRepayTime());
				//第一条为  申请还款的开放日
				List<DynamicRateByProject> list = this.baseDao.find(hql.toString(), param.toArray());
				if("1".equals(rep.getCType())){
					//提前还款
					hql = new StringBuilder();
					param = new ArrayList();
					
					if(list!=null&&list.size()>0){
						
						DynamicRateByProject drbp = list.get(0);
						date.setDate(date.getDate()+6);
						if(sdf.parse(drbp.getStartTime()).compareTo(date)!=-1){
							//可以撤销申请还款
							result.add("3");
						}else{
							//不可撤销申请还款
							result.add("4");
						}
					}
					//查找期限
					result.add(list.get(0).getMonth().toString());
				}else{
					//正常还款
					result.add("5");
					result.add(list.get(list.size()-1).getMonth().toString());
				}
				result.add(money.toString());
				if(rep.getState()!=-1){
					//已经还款
					result.set(0, "6");
				}
				return result;
			}else{
				//没有还款计划   即未生成还款计划
				//查标  累计本金
				hql = new StringBuilder();
				param = new ArrayList();
				//查询所有标 未放款/已放款
				hql.append("from Tender where project.id = ? and usrCustId = ? and flag = 0 and unFreezeState is null");
				param.add(projectId);
				param.add(user.getHuifuID());
				List tenderList = this.baseDao.findByHql(hql.toString(), param.toArray());
				if(tenderList!=null){
					for(Object obj : tenderList){
						tender = (Tender)obj;
						money = money.add(tender.getTransAmt());
					}
				}
				hql = new StringBuilder();
				param = new ArrayList();
				hql.append(" from DynamicRateByProject where pid = ? and flag = 0 order by startTime asc");
				param.add(projectId);
				//第一条为  申请还款的开放日
				List<DynamicRateByProject> list = this.baseDao.find(hql.toString(), param.toArray());
				if(project.getState()==0||project.getState()==3)
					result.add("8");
				else if (project.getState()==4)
					result.add("9");
				else
					result.add("7");
				result.add(list.get(list.size()-1).getMonth().toString());
				if(project.getState()==0||project.getState()==3)
					result.add("-");
				else
					result.add(money.toString());
				return result;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			result = new ArrayList();
			result.add("0");
			result.add("0");
			result.add("系统错误，请联系客服。");
			return result;
		}
	}
	/**
	 * 取消提前还款
	 * @param user
	 * @param projectId
	 * @return
	 */
	public String cancelRepayEarly(User user,Long projectId){
		Project project = (Project)this.bizdataservice.bizfindbyid(Project.class, projectId);
		Tender tender = null;
		boolean tenderTag = false;
		if(project==null)
			return "产品不存在";
		if(project.getType()!=3)
			return "产品类型错误";
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = -1");
		param.add(projectId);
		param.add(user.getHuifuID());
		//未还款的还款计划
		List list = this.baseDao.findByHql(hql.toString(),param.toArray());
		if(list!=null&&list.size()>0){
			RepaymentPlan rep = (RepaymentPlan)list.get(0);
			//检测是否用户发起行为  并且比对当前时间和开放日之间时间差
			if("1".equals(rep.getCType())){
				hql = new StringBuilder();
				param = new ArrayList();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d = new Date();
				hql.append(" from DynamicRateByProject where pid = ? and flag = 0 and startTime >= ? order by startTime asc");
				param.add(projectId);
				param.add(sdf.format(d));
				List<DynamicRateByProject> dynamicList = this.baseDao.find(hql.toString(), param.toArray());
				if(dynamicList!=null&&dynamicList.size()>0){
					//检测是否可以取消  时间差
					DynamicRateByProject dynamic = dynamicList.get(0);
					//if(sdf.parse(dynamic.getStartTime()).compareTo(d))
					try{
						if(SignTools.getdiffDays(d, sdf.parse(dynamic.getStartTime()))>=6){
							//还原还款计划至开放日最后一天
							dynamic = dynamicList.get(dynamicList.size()-1);
							for(Object object : list){
								rep = (RepaymentPlan)object;
								rep.setRepayTime(dynamic.getStartTime());
								rep.setApplyTime(sdf1.format(d));
								rep.setCType("0");
								if(rep.getRepayCount()!=0){
									tender = (Tender)this.bizdataservice.bizfindbyid(Tender.class, rep.getTenderId());
									rep.setRepayMoney(SignTools.getRateByYearRate(project.getRateTime(),dynamic.getStartTime(),dynamic.getRate().divide(new BigDecimal(100)),tender.getTransAmt()));
								}
								try{
									rep.setUpdateTime(sdf1.format(new Date()));
									this.repaymentPlanService.saveOrUpdate(rep);
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}else{
							return "outOfDate";
						}
					}catch(Exception e){
						e.printStackTrace();
						return "error";
					}
				}
			}
			//
			
	 		
		}else{
			return "error";
		}
		
		
		return "ok";
	}
	/**
	 * 用户屌丝宝列表
	 * @param user
	 * @return
	 */
	public List getListById(Long pid){
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append(" from DynamicRateByProject where pid = ? and flag = 0 order by startTime asc");
		param.add(pid);
		return this.find(hql.toString(), param.toArray());
	}
}
