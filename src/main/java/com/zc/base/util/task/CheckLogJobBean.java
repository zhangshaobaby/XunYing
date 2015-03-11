package com.zc.base.util.task;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.CashReconciliation;
import com.zc.bsm.vo.Reconciliation;
import com.zc.bsm.vo.SaveReconciliation;
import com.zc.bsm.vo.TrfReconciliation;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zc.bsm.vo.returnVo.CashReconciliatioResult;
import com.zc.bsm.vo.returnVo.ReconciliationDtoResult;
import com.zc.bsm.vo.returnVo.ReconciliationResult;
import com.zc.bsm.vo.returnVo.SaveReconciliationResult;

/**
 * <b>类名称：</b>LogJobBean<br/> <b>类描述：</b>每日的晚上23:59:59,删除当一天对账所有数据，并用新查询的对账信息的数据插入到对账表<br/>
 * <b>创建人：</b>张少<br/> <b>修改人：</b><br/> <b>修改时间：</b>Dec 24, 2013 3:50:07 PM<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
@Component(value = "checkLogJobBean")
public class CheckLogJobBean implements ICheckLogJob {
	protected static final Log logger = LogFactory
			.getLog(CheckLogJobBean.class);
	@Autowired
	private bizDataService bizdataservice;

	public void loggerToArchives() {

		try {
			Date dNow = new Date();   //当前时间
			Date dBefore = new Date();
			Calendar calendar = Calendar.getInstance();  //得到日历
			calendar.setTime(dNow);//把当前时间赋给日历
			calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
			dBefore = calendar.getTime();   //得到前一天的时间
			 String date=StringUtil.DateToString(dBefore, "yyyyMMdd");
			// date="20141019";
			 int pagesize=100;
			// 投标对账(放款 Reconciliation和还款对账
			 doReconciliationLog(pagesize, date);
			 //取现
			 doCashReconciliationLog(pagesize, date);
			 //充值
			 doSaveReconciliationLog(pagesize, date);
			 //商户扣款
			 doTrfReconciliationLog(pagesize, date);
		} catch (Exception e) {
			logger.error("对账失败");
			e.printStackTrace();
		}
	}
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void doReconciliationLog(int pagesize,String date){
	List<Object> listobject =  ReconciliationLog(1,pagesize,date,new ArrayList(),"LOANS");
	listobject=ReconciliationLog(1,pagesize,date,listobject,"REPAYMENT");
	if(listobject==null){
		return;
	}
	//先查出当天的数据 并删除原来数据
	   deleteDateData(ReconciliationResult.class, date);
	 //插入新数据
	   for(Object obj:listobject){
		   this.bizdataservice.save(obj);
	   }
	}
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void doCashReconciliationLog(int pagesize,String date){
	   List<Object> listobject =  CashReconciliationLog(1,pagesize,date,new ArrayList());
	   if(listobject==null){
			return;
		}
	 //先查出当天的数据 并删除原来数据
	   deleteDateData(CashReconciliatioResult.class, date);
	 //插入新数据
	   for(Object obj:listobject){
		   this.bizdataservice.save(obj);
	   }
	}
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void doSaveReconciliationLog(int pagesize,String date){
	   List<Object> listobject =   SaveReconciliationLog(1,pagesize,date,new ArrayList());
	   if(listobject==null){
			return;
		}
	 //先查出当天的数据 并删除原来数据
	   deleteDateData(SaveReconciliationResult.class, date);
	   //插入新数据
	   for(Object obj:listobject){
		   this.bizdataservice.save(obj);
	   }
	   
	}
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public void doTrfReconciliationLog(int pagesize,String date){
	   List<Object> listobject =   TrfReconciliationLog(1,pagesize,date,new ArrayList());
	   if(listobject==null){
			return;
		}
	 //先查出当天的数据 并删除原来数据
	   deleteDateData(ReconciliationDtoResult.class, date);
	   //插入新数据
	   for(Object obj:listobject){
		   this.bizdataservice.save(obj);
	   }
	}
	
	
	// 投标对账(放款 Reconciliation和还款对账
	public List<Object> ReconciliationLog(int curentpage,int pagesize,String date,List<Object> list,String type) {
		try {
			// 放款
			Reconciliation rc = new Reconciliation();
			rc.setPageNum(String.valueOf(curentpage));
			rc.setPageSize(String.valueOf(pagesize));
			rc.setBeginDate(date);
			rc.setEndDate(date);
			rc.setQueryTransType(type);
			Map<String, String> params = rc.getParam();
			String jsonString = TransSubmit.doPost(params);
			JSONObject js = JSONObject.parseObject(jsonString);
			JSONArray jr = js.getJSONArray("ReconciliationDtoList");
			for (int i = 0; i < jr.size(); i++) {
				JSONObject subjo = jr.getJSONObject(i);
				Gson gson = new Gson();
				ReconciliationResult rdr = gson.fromJson(subjo.toJSONString(),
						ReconciliationResult.class);
				list.add(rdr);
			}
			//获取总记录数
            String totalItems= (String) js.get("TotalItems");
            //计算出已请求多少数据
             int havequestitem=curentpage*pagesize;
             //如果还有未请求到的剩余数据 继续请求
            if(havequestitem<Integer.parseInt(totalItems)){
            	ReconciliationLog(curentpage+1, pagesize,date,list,type);
           }
            return list;
		} catch (Exception e) {
			logger.error("投标对账(放款和还款对账失败");
			e.printStackTrace();
		}
		return null;	
	}
	// 取现对账
	
	public List<Object> CashReconciliationLog(int curentpage,int pagesize,String date,List<Object> list) {
		try {
		
			CashReconciliation tr = new CashReconciliation();
			tr.setPageNum(String.valueOf(curentpage));
			tr.setPageSize(String.valueOf(pagesize));
			tr.setBeginDate(date);
			tr.setEndDate(date);
			Map<String, String> params = tr.getParam();
			String jsonString = TransSubmit.doPost(params);
			JSONObject js = JSONObject.parseObject(jsonString);
			JSONArray jr = js.getJSONArray("CashReconciliationDtoList");
			for (int i = 0; i < jr.size(); i++) {
				JSONObject subjo = jr.getJSONObject(i);
				Gson gson = new Gson();
				CashReconciliatioResult rdr = gson.fromJson(subjo
						.toJSONString(), CashReconciliatioResult.class);
				list.add(rdr);
			}
			//获取总记录数
            String totalItems= (String) js.get("TotalItems");
            //计算出已请求多少数据
             int havequestitem=curentpage*pagesize;
             //如果还有未请求到的剩余数据 继续请求
            if(havequestitem<Integer.parseInt(totalItems)){
            	CashReconciliationLog(curentpage+1, pagesize,date,list);
           }
            return list;
		} catch (Exception e) {
			logger.error("取现对账失败");
			e.printStackTrace();
		}
		return null;
	}

	// 充值对账
	public List<Object> SaveReconciliationLog(int curentpage,int pagesize,String date,List<Object> list) {
		try {
			
			SaveReconciliation tr = new SaveReconciliation();
			tr.setPageNum(String.valueOf(curentpage));
			tr.setPageSize(String.valueOf(pagesize));
		   tr.setBeginDate(date);
			tr.setEndDate(date);

			Map<String, String> params = tr.getParam();
			String jsonString = TransSubmit.doPost(params);
			JSONObject js = JSONObject.parseObject(jsonString);
			JSONArray jr = js.getJSONArray("SaveReconciliationDtoList");
			for (int i = 0; i < jr.size(); i++) {
				JSONObject subjo = jr.getJSONObject(i);
				Gson gson = new Gson();
				SaveReconciliationResult rdr = gson.fromJson(subjo
						.toJSONString(), SaveReconciliationResult.class);
				list.add(rdr);
			}
			//获取总记录数
            String totalItems= (String) js.get("TotalItems");
            //计算出已请求多少数据
             int havequestitem=curentpage*pagesize;
             //如果还有未请求到的剩余数据 继续请求
            if(havequestitem<Integer.parseInt(totalItems)){
            	SaveReconciliationLog(curentpage+1, pagesize,date,list);
           }
            return list;
		} catch (Exception e) {
			logger.error("充值对账失败");
			e.printStackTrace();
		}
		return null;
	}

	// 商户扣款对账
	
	public List<Object> TrfReconciliationLog(int curentpage,int pagesize,String date,List<Object> list) {
		try {
			
			TrfReconciliation tr = new TrfReconciliation();
			tr.setPageNum(String.valueOf(curentpage));
			tr.setPageSize(String.valueOf(pagesize));
			tr.setBeginDate(date);
			tr.setEndDate(date);
			Map<String, String> params = tr.getParam();
			String jsonString = TransSubmit.doPost(params);
			JSONObject js = JSONObject.parseObject(jsonString);
			JSONArray jr = js.getJSONArray("TrfReconciliationDtoList");
			for (int i = 0; i < jr.size(); i++) {
				JSONObject subjo = jr.getJSONObject(i);
				Gson gson = new Gson();
				ReconciliationDtoResult rdr = gson.fromJson(subjo
						.toJSONString(), ReconciliationDtoResult.class);
				list.add(rdr);
			}
			//获取总记录数
            String totalItems= (String) js.get("TotalItems");
            //计算出已请求多少数据
             int havequestitem=curentpage*pagesize;
             //如果还有未请求到的剩余数据 继续请求
            if(havequestitem<Integer.parseInt(totalItems)){
            	TrfReconciliationLog(curentpage+1, pagesize,date,list);
           }
            return list;
		} catch (Exception e) {
			logger.error("商户扣款对账失败");
			e.printStackTrace();

		}
		return null;
	}
	//查询当天数据
	public List<Object> deleteDateData(Class entity,String date){
		try {
			String hql="from "+entity.getName();
			try {
				Field field=entity.getDeclaredField("PnrDate");
				hql+=" where PnrDate=?";
			} catch (Exception e) {
				hql+=" where OrdDate=?";
			}
		List<Object> listobject=this.bizdataservice.find(hql,date);		
		this.bizdataservice.delete(listobject);
	    return listobject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}

}
