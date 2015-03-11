package com.zc.bsm.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.po.Dict;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.msgUtil;
import com.zc.bsm.dao.ProjectDao;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.ComomBizService;
import com.zc.bsm.service.DynamicRateByProjectService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LuckyMoneyService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;
import com.zc.bsm.vo.returnVo.UsrUnFreezeReturn;


@Service("projectService")
public class ProjectServiceImpl extends BaseServiceImpl<Project, Long> implements ProjectService{
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	private ComomBizService comomBizService;
	@Autowired
	public EnchashmentService enchashmentService;
	@Autowired
    private TenderService tenderService;
	@Autowired
    private  LuckyMoneyService luckyMoneyService;
	@Autowired
	private  BorrowerService borrowerService;
	@Autowired
    public  msgUtil msgutil;
	@Autowired
    private  DynamicRateByProjectService dynamicRateByProjectService;
	@Autowired
	public void setBaseDao(ProjectDao projectDao) {
		this.baseDao = projectDao;
	}
	public Company findByCId(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Company where flag = 0");
		if(id!=null&&id!=0){
			hql.append(" and id = ?");
			param.add(id);
		}
		List list = this.baseDao.findByHql(hql.toString(),param.toArray());
		return list.size()>0?(Company)list.get(0):null;
	}
	public List findAttByCId(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from CompanyAttachment where flag = 0");
		if(id!=null&&id!=0){
			hql.append(" and cid = ?");
			param.add(id);
		}
		return this.baseDao.findByHql(hql.toString(),param.toArray());
	}
	public Project findById(Long id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Project where flag = 0");
		if(id!=null&&id!=0){
			hql.append(" and id = ?");
			param.add(id);
		}
		List list = this.baseDao.findByHql(hql.toString(),param.toArray());
		return list.size()>0?(Project)list.get(0):null;
	}
	public void saveOrUpdate(Project project){
		this.baseDao.saveOrUpdate(project);
	}
	public void deleteAll(String id){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Project set flag = 1 where id in ("+id+")");
		this.baseDao.update(hql.toString(),null);
	}
	public void verify(Long id,Integer online){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Project set online = ? where id = ?");
		param.add(online);
		param.add(id);
		this.baseDao.update(hql.toString(),param.toArray());
	}
	public void _verify(Long id,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Project set state = ?,updateTime = ?,publictime=? where id = ?");
		param.add(state);
		param.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		param.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		param.add(id);
		this.baseDao.update(hql.toString(),param.toArray());
	}
	public List<Project> findByType(Integer type,Integer length){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Project where type = ? and flag = 0 and state>=0 order by publictime desc ");
		param.add(type);
		Page page = new Page();
		page.setRows(length);
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray()).getResult();
	}
	public Page findByPage(Page page,Project project){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("from Project where flag = 0");
		if(project!=null){
			if(project.getCode()!=null&&!project.getCode().equals("")){
				hql.append(" and code = ?");
				param.add(project.getCode());
			}
			if(project.getType()!=null&&!project.getType().equals("")){
				hql.append(" and type = ?");
				param.add(project.getType());
			}
			if(project.getName()!=null&&!project.getName().equals("")){
				hql.append(" and name = ?");
				param.add(project.getName());
			}
//			状态
			if(project.getState()!=null){
				if(project.getState()==-100){
					hql.append(" and (state = 1 or state = 2)");
				}else if(project.getState()==-101){
					hql.append(" and state >= 0 ");					
				}else if(project.getState()==0){
					hql.append(" and state = ?");
					param.add(project.getState());
						//产品募集时间必须未到期
						hql.append(" and _now_money < total_money");
						hql.append(" and start_time < ?");
						hql.append(" and end_time > ?");
						Date date = new Date();
						try {
							param.add(StringUtil.DateToString(date, "yyyy-MM-dd HH:mm:ss"));
							param.add(StringUtil.DateToString(date, "yyyy-MM-dd HH:mm:ss"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}else if(project.getState()==-3){
					hql.append(" and state= 0");
					hql.append(" and start_time > ?");
					Date date = new Date();
					try {
						param.add(StringUtil.DateToString(date, "yyyy-MM-dd HH:mm:ss"));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					hql.append(" and state = ?");
					param.add(project.getState());
				}
				}
//			期限
			if(project.getTime_limit()!=null&&!project.getTime_limit().equals("")){
				if(project.getTime_limit()<=3){
					hql.append(" and time_limit between ? and ?");
					param.add((project.getTime_limit()-1)*3);
					param.add(project.getTime_limit()*3);
				}else if(project.getTime_limit()>=4){
					hql.append(" and time_limit >= 12");
				}
			}
//			收益
			if(project.getRate()!=null&&!project.getRate().equals("")){
				if(project.getRate().toString().equals("1")){
					hql.append(" and rate < 10");
				}else if(project.getRate().toString().equals("2")){
					hql.append(" and rate between 10 and 15");
				}else if(project.getRate().toString().equals("3")){
					hql.append(" and rate between 15 and 20");
				}else if(project.getRate().toString().equals("4")){
					hql.append(" and rate > 20");
				}
			}
//			规模（total
			if(project.getTotal_money()!=null&&!project.getTotal_money().equals("")){
				if(project.getTotal_money().intValue()==1){
					hql.append(" and total_money < 2000000");
				}else if(project.getTotal_money().intValue()==2){
					hql.append(" and total_money between 2000000 and 5000000");
				}else if(project.getTotal_money().intValue()==3){
					hql.append(" and total_money between 5000000 and 10000000");
				}else if(project.getTotal_money().intValue()==4){
					hql.append(" and total_money >10000000");
				}
			}
			
		}
		if(project.getFlag()!= null &&project.getFlag().equals("publictime")){
			hql.append(" order by "+project.getFlag()+" desc ");
		}else{
			hql.append(" order by id desc ");
		}
	
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public void changeState(Long id,Integer state){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		hql.append("update Project set state = ? where id = ?");
		param.add(state);
		param.add(id);
		this.baseDao.update(hql.toString(),param.toArray());
	}
	public Page findByRepayPage(Page page,Project project){
		StringBuilder hql = new StringBuilder();
		List<Object> param = new ArrayList<Object>();
		if(project.getType()==null||project.getType()==0){//还款产品
			hql.append("select new com.zc.bsm.webVo.RepayProject(a.id,a.name,a.repay_content,a.borrowType,a.borrowName,a.totalRepay,a.repay_money,a.pendTime,a.pstartTime,a.repayCount,0,a.state) from Project as a ");
			hql.append("where   a.flag = 0 ");
		}else if(project.getType()==1){//还款记录
			hql.append("select new com.zc.bsm.webVo.RepayProject(a.id,b.repayCount,b.updateTime,a.name,a.repay_content,a.borrowType,a.borrowName,a.totalRepay,a.repay_money,sum(b.repayMoney),b.repayTime,a.pendTime) from Project as a,RepaymentPlan as b ");
			hql.append("where a.id = b.projectId and b.flag = 0 and a.flag = 0 ");
		}else if(project.getType()==2){//还款计划
			hql.append("select new com.zc.bsm.webVo.RepayProject(a.id,b.repayCount,'',a.name,a.repay_content,a.borrowType,a.borrowName,a.totalRepay,a.repay_money,sum(b.repayMoney),b.repayTime,a.pendTime) from Project as a,RepaymentPlan as b ");
			hql.append("where a.id = b.projectId and b.flag = 0 and a.flag = 0 ");
		}
		if(project.getName()!=null&&!project.getName().equals("")){
			hql.append(" and a.name like ?");
			param.add("%"+project.getName()+"%");
		}
		if(project.getBorrowName()!=null&&!project.getBorrowName().equals("")){
			hql.append(" and a.borrowName like ?");
			param.add("%"+project.getBorrowName()+"%");
		}

		
		if(project.getState()==-100){
			hql.append(" and (a.state = 1 or a.state = 2)");
		}

		
		if(project.getType()==null||project.getType()==0){//还款产品
			hql.append(" group by a.id ");
			hql.append(" order by a.pstartTime desc ");
		}else if(project.getType()==1){//还款记录
			if(project.getPstartTime()!=null&&!project.getPstartTime().equals("")&&project.getPendTime()!=null&&!project.getPendTime().equals("")){
				hql.append(" and b.updateTime > ? and b.updateTime < ?");
				param.add(project.getPstartTime());
				param.add(project.getPendTime());
			}
			hql.append(" and b.state = 1 ");
			hql.append(" group by b.projectId,b.repayCount ");
			hql.append(" order by b.updateTime desc ");
		}else if(project.getType()==2){//还款计划
			if(project.getPstartTime()!=null&&!project.getPstartTime().equals("")&&project.getPendTime()!=null&&!project.getPendTime().equals("")){
				hql.append(" and b.repayTime >= ? and b.repayTime <= ?");
				param.add(project.getPstartTime());
				param.add(project.getPendTime());
			}
			hql.append(" and b.state = -1 ");
			hql.append(" group by b.projectId,b.repayCount  ");
			hql.append(" order by b.repayTime ");
		}
		page.setHql(hql.toString());
		return this.baseDao.findByPage(page,param.toArray());
	}
	public  void cancelProduct(Long projectid) throws Exception{
		//解冻相关标
		String hql="from Tender as tender where project.id=? and flag=0 and (unFreezeState is null or unFreezeState=?)";
		List<Object> listobj=this.comomBizService.find(hql,projectid,1);
		LuckyMoney lucky = null;
		for(Object obj: listobj){
			//标
			Tender tender=(Tender) obj;
			//冻结号
			Freeze free=(Freeze) this.bizdataservice.bizfindbyid(Freeze.class, tender.getFreezeOrdId());
			message me=new message();
			//新建一个解冻账号
			Freeze unfreeze=new Freeze();
		     unfreeze.setType(new Dict(41));
		     unfreeze.setUsrCustId(free.getUsrCustId());
		     unfreeze.setTransAmt(free.getTransAmt());
		     unfreeze.setTrxId(free.getTrxId());
		     unfreeze= (Freeze) this.bizdataservice.bizSave(Freeze.class, unfreeze);
		     UsrUnFreezeReturn bgr=this.comomBizService.UsrTenderUnFreeze(unfreeze);
			 if(bgr==null){
			   	  me.setMsgString("正在等待资金解冻结果.."); 
			   	  tender.setUnFreezeState(0);
				 }else{
				   me=comomBizService.dobgTenderUnFreeState(bgr); 
				   if(me.getMsgCode().equals("false")){
					   tender.setUnFreezeState(1);
				   }else{
					   tender.setUnFreezeState(2);
					   //发送解冻消息
					   User user=(User) this.bizdataservice.bizfindbyid(User.class, unfreeze.getUsrCustId());
					   Map<String,String> params=new HashMap<String, String>();
					   params.put("proname", tender.getProject().getName());
					   params.put("money", tender.getTransAmt().toString());
					   msgutil.sendmessage("17",new String[]{"phone","webmeg"}, user,  params);
					   
				   }
			     }
			 this.bizdataservice.bizSave(Tender.class, tender);
			  if(tender.getUnFreezeState()==2){
			  	// 往流水表中新增一条产品撤销记录
				CashStream cashstream = new CashStream();
				cashstream.setOrdId(unfreeze.getId().toString());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				cashstream.setOrdDate(sdf1.format(sdf.parse(bgr.getOrdDate())));
				cashstream.setFlag(0);
				cashstream.setTransAmt(tender.getTransAmt());
				cashstream.setType(new Dict(64));
				cashstream.setInCustId(tender.getUsrCustId());
				//可用余额
				try {
					cashstream.setAvlBal(enchashmentService.getBalanceObj(tender.getUsrCustId()).getAvlBal());
				} catch (Exception e) {
					cashstream.setAvlBal("未知..可登陆汇付网站查询");
				}
				//摘要
				cashstream.setSummary("产品撤销|"+tender.getProject().getName());
				bizdataservice.bizSave(CashStream.class, cashstream);
			  }
			  //更改红包状态
			  if(tender.getLuckyId()!=null&&tender.getLuckyId()!=0){
				  lucky = (LuckyMoney)bizdataservice.bizfindbyid(LuckyMoney.class, tender.getLuckyId());
				  lucky.setState(0);
				  bizdataservice.saveOrUpdate(lucky);
			  }
	}	
	}
	public void updateRepayCount(Long id){
		Page page = new Page();
		page.setRows(1);
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		hql.append("from RepaymentPlan where projectId = ? and flag = 0 and state = 1 order by repayCount desc");
		param.add(id);
		page.setHql(hql.toString());
		page = this.baseDao.findByPage(page, param.toArray());
		if(page.getResult()!=null&&page.getResult().size()>0){
			hql = new StringBuilder();
			param = new ArrayList();
			hql.append("update Project set repayCount = ? where id = ?");
			param.add(((RepaymentPlan)page.getResult().get(0)).getRepayCount());
			param.add(id);
			this.baseDao.update(hql.toString(), param.toArray());
		}
	}
	public boolean checkAddRepaymentPlan(Long id,String time1,String time2,Integer repayCount){
		if(id==null||time1==null||time1.equals("")||time2==null||time2.equals("")||repayCount==null){
			return  false;
		}
		StringBuilder hql = new StringBuilder();
		List param = new ArrayList();
		if(repayCount!=0){
			hql.append("from RepaymentPlan where flag = 0 and repayCount != 0 and projectId = ?  and ((? < end_time) and (? > start_time))");
			param.add(id);
			param.add(time1);
			param.add(time2);
		}else{
			hql.append("from RepaymentPlan where flag = 0 and projectId = ? and repayCount = 0");
			param.add(id);
		}
		List list = this.baseDao.findByHql(hql.toString(), param.toArray());
		return !(list!=null&&list.size()>0);
	}
	public void view(ModelAndView modelAndView, User user,Project project) {
		Company company =null;
		Company company2 =null;
		if(project.getDbid()!=null){
			company= this.findByCId(project.getDbid());
		}
		if(project.getXtid()!=null){
			company2= this.findByCId(project.getXtid());
		}
       
		
		modelAndView.addObject("model", project);
		modelAndView.addObject("company", company);
		modelAndView.addObject("company2", company2);
		BigDecimal release = null;
		//剩余可投资金额   如果是屌丝宝  同时计算个人最大可投资金额  和项目资金缺口
//		User user=(User) session.getAttribute("user");
		if(user!=null&&project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1){
			List releaseList = tenderService.findSumTransmat(project.getId(),user.getHuifuID());
			if(releaseList!=null&&releaseList.get(0)!=null&&!releaseList.get(0).toString().equals("")){
				release = new BigDecimal(releaseList.get(0).toString());
				release = project.getSelf_highest_money().subtract(release);
			}else{
				release = project.getSelf_highest_money();
			}
		}
		//如果剩余可投资金额为空  则剩余可投资金额等于总投资金额-已投资金额  否则比对
		if(release==null){
			if(project.getType()==3&&project.getSelf_highest_money()!=null&&project.getSelf_highest_money().compareTo(new BigDecimal(0))==1)
				release = project.getSelf_highest_money();
			else
				release = project.getTotal_money().subtract(project.get_now_money());
		}else{
			release = project.getTotal_money().subtract(project.get_now_money()).compareTo(release)!=-1?release:project.getTotal_money().subtract(project.get_now_money());
		}
		if(user!=null){
			//先查询账户余额
			QueryBalanceBgReturn _return = enchashmentService.getBalanceObj(user);
			modelAndView.addObject("self_money", _return.getAvlBal());
			//可用红包数量
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			LuckyMoney luckyMoney = new LuckyMoney();
			luckyMoney.setUid(user.getId());
			luckyMoney.setStart_time(sdf.format(new Date()));
			luckyMoney.setEnd_time(sdf.format(new Date()));
			luckyMoney.setType(project.getType().toString());
			luckyMoney.setState(0);
			modelAndView.addObject("luckyCount", luckyMoneyService.findListBySelf(luckyMoney).size());
		}
		//最低单笔投资金额  最高单笔投资金额  如果最大可投资金额小于最低单笔  则最低单笔和最高单笔皆为最大可投资金额
		BigDecimal low = null;
		BigDecimal high = null;
		if(project.getTotal_money().subtract(project.get_now_money()).compareTo(new BigDecimal(project.getLowest_money()))!=-1){
			low = new BigDecimal(project.getLowest_money());
			high = new BigDecimal(project.getHighest_money());
		}else{
			low = project.getTotal_money().subtract(project.get_now_money());
			high =project.getTotal_money().subtract(project.get_now_money());
		}
			
		modelAndView.addObject("low", low);
		modelAndView.addObject("high", high);
		modelAndView.addObject("release", release);
		int percent = project.get_now_money().multiply(new BigDecimal(100)).divide(project.getTotal_money(),1,BigDecimal.ROUND_HALF_UP).intValue();
		//投资比率
		modelAndView.addObject("percent", percent);
		//附件
        modelAndView.addObject("companyAttachment", this.findAttByCId(project.getId()));
        modelAndView.addObject("borrower",borrowerService.get(project.getBorrowId()));
        modelAndView.addObject("picpath",SignUtils.PIC_HOST);
        //服务器当前时间 
        modelAndView.addObject("date",new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        //是否逾期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
        	modelAndView.addObject("dateTag",sdf.parse(project.getEnd_time().length()==10?(project.getEnd_time()+" 23:59:59"):project.getEnd_time()).compareTo(new Date()));
        	modelAndView.addObject("startTag",sdf.parse(project.getStart_time()).compareTo(new Date()));
        }catch(Exception e){e.printStackTrace();}
        if(project.getType()==3){
        	//屌丝宝开放日
        	modelAndView.addObject("drbp",dynamicRateByProjectService.getListById(project.getId()));	
        }
	}
}
