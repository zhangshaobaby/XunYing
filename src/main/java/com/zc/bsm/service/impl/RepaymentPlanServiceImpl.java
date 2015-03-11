package com.zc.bsm.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.base.util.SignTools;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.bsm.dao.RepaymentPlanDao;
import com.zc.bsm.pojo.DynamicRate;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.service.CreditAssignService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.RepaymentSendService;


@Service("repaymentPlanService")
public class RepaymentPlanServiceImpl extends BaseServiceImpl<RepaymentPlan, Long> implements RepaymentPlanService{
	@Autowired
	private ProjectService projectService;
   @Autowired
   private CreditAssignService creditAssignServiceImpl;
	@Autowired
	public void setBaseDao(RepaymentPlanDao repaymentPlanDao) {
		this.baseDao = repaymentPlanDao;
	}
	public List<RepaymentPlan> checkRepaymentPlan(Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from RepaymentPlan where projectId = ? and flag = 0 and state = 0");
		param.add(pid);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public List<RepaymentPlan> findByPidRepayCount(Long pid,Integer repayCount){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from RepaymentPlan where projectId = ?  and repayCount = ? and flag = 0");
		param.add(pid);
		param.add(repayCount);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public List findByPid(Long pid,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select new com.zc.bsm.webVo.RepayProject(repayTime,sum(repayMoney),repayCount,state,start_time,end_time,cType) from RepaymentPlan ");
		hql.append("where projectId = ? and flag = 0 ");
		param.add(pid);
		if(state!=null){
			hql.append(" and state = ?");
			param.add(state);
		}
		hql.append("group by repayTime,repayCount order by repayTime,id");
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List findByPid(Long pid,Integer state,Integer repayCount,String repayTime){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select sum(repayMoney) from RepaymentPlan ");
		hql.append("where projectId = ? and flag = 0 and repayTime = ?");
		param.add(pid);
		param.add(repayTime);
		if(state!=null){
			hql.append(" and state = ?");
			param.add(state);
		}
		if(repayCount!=null){
			hql.append(" and repayCount = ?");
			param.add(repayCount);
		}
		hql.append("group by repayCount order by repayTime,id");
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List findSumByPid(Long pid,Integer state,Integer repayCount,String repayTime){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select sum(repayMoney),repayTime from RepaymentPlan ");
		hql.append("where projectId = ? and flag = 0 ");
		param.add(pid);
		if(state!=null){
			hql.append(" and state = ?");
			param.add(state);
		}
		if(repayCount!=null){
			hql.append(" and repayCount = ?");
			param.add(repayCount);
		}
		if(repayTime!=null){
			hql.append(" and repayTime = ?");
			param.add(repayTime);
		}
		hql.append("group by repayCount order by repayTime,id");
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List<RepaymentPlan> checkRepayment(Long id,Integer repayCount,String repayTime){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from RepaymentPlan where projectId = ?  and repayCount = ? and repayTime =? and state = -1 and (interType = 0 or interType is null) and flag = 0");
		param.add(id);
		param.add(repayCount);
		param.add(repayTime);
		return this.baseDao.find(hql.toString(), param.toArray());
	}
	public List checkCount(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*) from RepaymentPlan where projectId  = ? and state = 1 and flag = 0");
		param.add(id);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public List checkCount(Long id,Integer repayCount,Integer state,String repayTime){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*) from RepaymentPlan where projectId  = ? and repayCount = ?  and flag = 0 and repayTime = ?");
		param.add(id);
		param.add(repayCount);
		param.add(repayTime);
		if(state!=null){
			hql.append(" and state = ?");
			param.add(state);
		}
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public int deleteByPId(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update RepaymentPlan set flag = 1 where projectId = ? and flag = 0");
		param.add(id);
		return this.baseDao.updateWithCount(hql.toString(), param.toArray());
	}
	public int selectCountByPId(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*) from RepaymentPlan where projectId = ? and flag = 0");
		param.add(id);
		List result = this.baseDao.findByHql(hql.toString(), param.toArray());
		return Integer.parseInt(result.get(0).toString());
	}
	public List record(Long pid,String userCustId){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select new List(ordDate,sum(repayMoney),repayCount) from RepaymentPlan where projectId  = ? and usrCustId = ? and flag = 0 and state = 1 group by repayCount");
		param.add(pid);
		param.add(userCustId);
		return this.baseDao.findByHql(hql.toString(), param.toArray());
	}
	public String repayEarly(Long pid,String userCustId) throws Exception{
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Tender t = new Tender();
		Project project = projectService.get(pid);
		//取现时间3个正常日
		date.setDate(date.getDate()+3);
		//屌丝宝  用户申请取现   
		hql.append("from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = -1");
		param.add(pid);
		param.add(userCustId);
		//原计划
		List<RepaymentPlan> oldPlan = this.baseDao.find(hql.toString(), param.toArray());
		if(oldPlan!=null&&oldPlan.size()>0&&sdf.parse(oldPlan.get(0).getRepayTime()).compareTo(date)==1){
			//生成两条还款计划     根据【时间查询】是否有相同repayCount  如果有  repayCount相同 否则  repayCount+1
			hql = new StringBuilder();
			hql.append("select repayCount from RepaymentPlan  where repayCount!=0 and projectId = ? and flag = 0  and state = -1 and repayTime = ? group by repayCount");
			param.remove(1);
			param.add(sdf.format(date));
			List _repayCount = this.baseDao.findByHql(hql.toString(), param.toArray());
			int repayCount = 2;
			if(_repayCount!=null&&_repayCount.size()>0){
				repayCount = Integer.parseInt(_repayCount.get(0).toString());
			}else{
				//select出最新的repayCount
				hql = new StringBuilder();
				hql.append("select max(repayCount) from RepaymentPlan  where projectId = ? and flag = 0  and state = -1 ");
				param.remove(1);
				_repayCount = this.baseDao.findByHql(hql.toString(), param.toArray());
				repayCount = Integer.parseInt(_repayCount.get(0).toString()) + 1;
			}
			//批量更新repayDate 和repayCount  repayMoney
			param = new ArrayList();
			Date rateDate = new Date();
			//测试用
//			rateDate = sdf.parse("2014-09-25");
			rateDate.setDate(rateDate.getDate());
			try{
				for(RepaymentPlan plan : oldPlan){
					param.add(0, plan.getTenderId());
					if(plan.getRepayCount()!=0){
						t = (Tender)(this.baseDao.findByHql(" from Tender where id = ? " ,param.toArray()).get(0));
						//重置金额   重做
						//plan.setRepayMoney(SignUtils.getRate(sdf.parse(t.getProject().getPstartTime()),rateDate,t.getProject().getDay_rate().multiply(t.getProject().getHalf_rate().divide(t.getProject().getRate())),t.getTransAmt()));
						
						//此处利息重置为根据屌丝宝动态非到期日利率表计算
						//重置还款时间
						plan.setRepayTime(sdf.format(date));
						//获取非到期动态还款日利率
						hql = new StringBuilder();
						hql.append("from DynamicRate where startTime > ? and startTime < ? and flag = 0 order by startTime desc");
						param = new ArrayList();
						//利息只计算到前一天
						param.add(project.getRateTime());
						param.add(sdf.format(rateDate));
						List rateList = this.baseDao.findByHql(hql.toString(), param.toArray());
						hql = new StringBuilder();
						hql.append("from DynamicRate where startTime <= ? and flag = 0 order by startTime desc");
						Page page = new Page();
						page.setRows(1);
						page.setHql(hql.toString());
						param.remove(1);
						rateList.add(this.baseDao.findByPage(page, param.toArray()).getResult().get(0));
						DynamicRate dynamicRate = null;
						Date startTime = sdf.parse(project.getRateTime());
						Date endTime = null;
						//2014-09-10  ---   2014-09-25
						//初始化利息
						BigDecimal rate = new BigDecimal(0l);
						BigDecimal totalRate = new BigDecimal(0l);
						//循环非到期动态日利率 计算利率
						for(int i = 0 ; i< rateList.size() ; i++){
							dynamicRate = (DynamicRate)rateList.get(rateList.size()-i-1);
							//
							if(i!=rateList.size()-1){
								endTime = sdf.parse(((DynamicRate)rateList.get(rateList.size()-i-2)).getStartTime());
							}else{
								endTime = rateDate;
							}
							//计算利息
							if(startTime.compareTo(endTime)==-1)
								totalRate = totalRate.add(SignTools.getTotalRate(startTime,endTime, dynamicRate.getDay_rate()));
							//重置startTime
							startTime = endTime;
						}
						rate = t.getTransAmt().multiply(totalRate);
						//更改利息金额和批次
						if(rate.compareTo(new BigDecimal(0))<=0){
							plan.setFlag(1);
						}
						plan.setRepayMoney(new BigDecimal(StringUtil.BigDecimal2StringSmall(rate)));
						plan.setRepayCount(repayCount);
					}
					//更改还款日期
					plan.setRepayTime(sdf.format(date));
					plan.setUpdateTime(sdf.format(new Date()));
					plan.setCType("1");
					//参数置空
					param = new ArrayList();
				}
				this.baseDao.saveOrUpdateBatch(oldPlan);
			}catch(Exception e ){
				e.printStackTrace();
			}
			//屌丝宝提前还款流程。   未到期日利率 =（日利率*（未到期年利率/年利率））
			return null;
		}else{
			return "false";
		}
	}
	public String projectRepay(Long pid,String userCustId) throws Exception{
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select sum(repayMoney) from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = 1");
		param.add(pid);
		param.add(userCustId);
		List list = this.baseDao.findByHql(hql.toString(), param.toArray());
	
		String replanMoney= list!=null&&list.size()>0?list.get(0)==null?"0.00":list.get(0).toString():"0.00";
		//加上债权转让收益
	    String rateMoney=creditAssignServiceImpl.getCreditMoney(userCustId, pid);
	    return  StringUtil.BigDecimal2String(new BigDecimal(replanMoney).add(new BigDecimal(rateMoney)));
	}

	
	public List<Object> repayEarlyRewardformobile(Long pid,String userCustId){
		StringBuilder hql = new StringBuilder(); 
		List<Object> result = new ArrayList<Object>();
		
		List<Object> param = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try{	
			Project project = projectService.get(pid);
			//取现时间3个正常日
			date.setDate(date.getDate()+SignTools.TN_TIME);
			//屌丝宝  用户申请取现   
			hql.append("from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = -1");
			param.add(pid);
			param.add(userCustId);
			//原计划		
			List<RepaymentPlan> oldPlan = this.baseDao.find(hql.toString(), param.toArray());
			
			//还款计划里所有未还本金
			hql = new StringBuilder();
			hql.append("select sum(repayMoney) from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = -1 and repayCount=0");
			param.clear();
			param.add(pid);
			param.add(userCustId);
			List listbenjin = this.baseDao.findByHql(hql.toString(),param.toArray());
			BigDecimal benjin =new BigDecimal(0);
			if(listbenjin!=null&&listbenjin.size()>0&&listbenjin.get(0)!=null){
			 benjin =new BigDecimal(listbenjin.get(0).toString());
			}
			
			if((oldPlan==null||oldPlan.size()==0)){
				if(project.getState()==0||project.getState()==3){
					//筹款中
					hql = new StringBuilder();
					hql.append("select sum(transAmt) from Tender where UsrCustId = ? and projectId = ? and flag = 0");
					param.clear();
					param.add(userCustId);
					param.add(pid);
					List list = this.baseDao.findByHql(hql.toString(),param.toArray());
					if(list!=null&&list.size()>0&&list.get(0)!=null){
						result.add(list.get(0).toString());
					}else{
						result.add("0");
					}
					result.add("true");
					return result;
				}else{
					StringBuilder hql0 = new StringBuilder();
		 			hql0.append("select sum(repayMoney) from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state = 1");
		 			List<Object> param0 = new ArrayList<Object>();
		 			param0.add(pid);
		 			param0.add(userCustId);
		 			List list0 = this.baseDao.findByHql(hql0.toString(),param0.toArray());
		 			
					if(list0!=null&&list0.size()>0&&list0.get(0)!=null){
						result.add("0(已还"+list0.get(0).toString()+")");
					}else{
						result.add("0");
					}
					result.add("false");
					
					return result;
				}
			}
			//查询最后一次还款计划
			String stopDate = this.findLastRepayTime(userCustId, pid);		
			String pendDate = project.getPendTime();
	 		Date lastrepaytime=StringUtil.StringToDate(stopDate, "yyyy-MM-dd");
	 		Date pendtime=StringUtil.StringToDate(pendDate, "yyyy-MM-dd");
			//获取 昨天的日期
			boolean isdaoqi=false;
	
		 	Date  rateDate= StringUtil.getIntervalDate(new Date(), -1);
		 	if(stopDate!=null&&!stopDate.equals("")){
	
				if(new Date().after(pendtime)&&new Date().after(lastrepaytime)){//产品到期未还
					isdaoqi=true;
				}
		 		if(lastrepaytime.before(pendtime)){//证明是提前赎回 前推三天	 			
		 			Date tmpday=StringUtil.getIntervalDate(lastrepaytime, SignTools.TN_TIME*-1);
		 			if(tmpday.before(rateDate)){
		 				rateDate=tmpday;
		 			}
		 			
		 		}
		 	}
	
			//初始化利息  总利率
			BigDecimal rate = new BigDecimal(0l);
			BigDecimal totalRate = new BigDecimal(0);
				
				if(isdaoqi){//到期未还显示到期利率
					rate=benjin;
					int diffDays = SignTools.dateDiffMonth(StringUtil.StringToDate(project.getPstartTime(), "yyyy-MM-dd"),StringUtil.StringToDate(project.getPendTime(), "yyyy-MM-dd") );
					 rate.add(rate.multiply(project.getDay_rate()).multiply( new BigDecimal(diffDays)));
				}else{//未到期 显示动态利率
					Date startTime = sdf.parse(project.getRateTime());
					//获取非到期动态还款日利率
					hql = new StringBuilder();
					hql.append("from DynamicRate where startTime >= ? and startTime <= ? and flag = 0 order by startTime desc");
					param = new ArrayList();
					//利息只计算到前一天
					param.add(project.getRateTime());
					param.add(sdf.format(rateDate));
					List rateList = this.baseDao.findByHql(hql.toString(), param.toArray());
					hql = new StringBuilder();
					hql.append("from DynamicRate where startTime <= ? and flag = 0 order by startTime desc");
					Page page = new Page();
					page.setRows(1);
					page.setHql(hql.toString());
					param.remove(1);
					rateList.add(this.baseDao.findByPage(page, param.toArray()).getResult().get(0));
					
					//利息  根据屌丝宝动态非到期日利率表计算		
					DynamicRate dynamicRate = null;	
					Date endTime = null;
					//循环非到期动态日利率 计算利率
					for(int i = 0 ; i< rateList.size() ; i++){
						dynamicRate = (DynamicRate)rateList.get(rateList.size()-i-1);
						//
						if(i!=rateList.size()-1){
							endTime = sdf.parse(((DynamicRate)rateList.get(rateList.size()-i-2)).getStartTime());
						}else{
							endTime = rateDate;
							endTime.setDate(endTime.getDate()+1);
						}
						//计算利息
						//System.out.println(startTime.compareTo(endTime));
						if(startTime.compareTo(endTime)!=1){						
							totalRate = totalRate.add(SignTools.getTotalRate(startTime,endTime, dynamicRate.getDay_rate()));
						}
						//重置startTime
						startTime = endTime;
					}				
					rate = benjin.add(benjin.multiply(totalRate));
				}
			if (lastrepaytime.compareTo(date)!=1){
				result.add( StringUtil.BigDecimal2StringSmall(rate));
				result.add("true");			
			}else{
				result.add(StringUtil.BigDecimal2StringSmall(rate));
				result.add("");			
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		return result;
	}
	public int ignore(Long pid,Integer repayCount,String repayTime){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update RepaymentPlan set flag = 1,updateTime = ? where projectId = ? and repayCount = ? and flag = 0 and state = -1 and repayTime = ?");
		param.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		param.add(pid);
		param.add(repayCount);
		param.add(repayTime);
		return this.baseDao.updateWithCount(hql.toString(), param.toArray());
	}
	public boolean overPlan(Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*) from RepaymentPlan where projectId = ?  and flag = 0 and state = -1");
		param.add(pid);
		if(this.baseDao.findByHql(hql.toString(), param.toArray()).get(0).toString().equals("0")){
			hql = new StringBuilder();
			hql.append("update Project set state = 2 where id = ?");
			return this.baseDao.updateWithCount(hql.toString(), param.toArray())>0?true:false;
		}
		return false;
	}
	public Integer newRepayCount(Long id,String repayTime){
		// 根据【时间查询】是否有相同repayCount  如果有  repayCount相同 否则  repayCount+1
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		hql.append("select repayCount from RepaymentPlan  where projectId = ? and repayCount!=0 and flag = 0  and state = -1 and repayTime = ? group by repayCount");
		param.add(id);
		param.add(repayTime);
		List _repayCount = this.baseDao.findByHql(hql.toString(), param.toArray());
		int repayCount = 2;
		if(_repayCount!=null&&_repayCount.size()>0){
			repayCount = Integer.parseInt(_repayCount.get(0).toString());
		}else{
			//select出最新的repayCount
			hql = new StringBuilder();
			hql.append("select max(repayCount) from RepaymentPlan  where projectId = ? and flag = 0  and state = -1 ");
			param.remove(1);
			_repayCount = this.baseDao.findByHql(hql.toString(), param.toArray());
			if(_repayCount!=null&&_repayCount.size()>0&&_repayCount.get(0)!=null)
				repayCount = Integer.parseInt(_repayCount.get(0).toString()) + 1;
		}
		return repayCount;
	}
	public String findLastRepayTime(String usrCustId,Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select repayTime from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state=-1 order by repayTime desc");
		param.add(pid);
		param.add(usrCustId);
		List list = this.baseDao.getTopRows(hql.toString(), 1, param.toArray());
		return list!=null&&list.size()>0?list.get(0).toString():"";
	}
	public String findLastedRepayTime(String usrCustId,Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select repayTime from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state=1 order by repayTime desc");
		param.add(pid);
		param.add(usrCustId);
		List list = this.baseDao.getTopRows(hql.toString(), 1, param.toArray());
		return list!=null&&list.size()>0?list.get(0).toString():"";
	}
	public String findLastedRepayTimes(String usrCustId,Long pid){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("select count(*) from RepaymentPlan where projectId = ? and usrCustId = ? and flag = 0 and state=1 order by repayTime desc");
		param.add(pid);
		param.add(usrCustId);
		List list = this.baseDao.getTopRows(hql.toString(), 1, param.toArray());
		return list!=null&&list.size()>0?list.get(0).toString():"";
	}
	
}
