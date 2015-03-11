package com.zc.bsm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.zc.abf.pojo.Operator;
import com.zc.base.service.BaseServiceImpl;
import com.zc.bsm.dao.OperatorLogDao;
import com.zc.bsm.pojo.OperatorLog;
import com.zc.bsm.service.OperatorLogService;


@Service("operatorLogService")
public class OperatorLogServiceImpl extends BaseServiceImpl<OperatorLog, Long> implements OperatorLogService{
		private static Map<String,String> BEHAVIOR_PROPERTIES = new HashMap<String,String>();
	public OperatorLogServiceImpl(){
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("1","产品发布");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("2","产品修改");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("3","产品审核");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("4","产品放款");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("5","申请取现");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("6","取现审核");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("7","取现");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("8","充值");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("9","产品补充开始结束时间");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("10","生成还款计划");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("11","产品撤标（无标）");
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("12","产品撤标（有标）");//?
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("13","产品取消");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("14","产品下架");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("15","发起还款申请");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("16","产品还款");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("17","还款审核");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("18","忽略还款");//
		OperatorLogServiceImpl.BEHAVIOR_PROPERTIES.put("19","新增还款计划");
		//System.out.println("score properties ready");
	}
	@Autowired
	public void setBaseDao(OperatorLogDao advertDao) {
		this.baseDao = advertDao;
	}
	public void saveLog(Operator operator,Object obj,String behavior){
		try{
			OperatorLog ol = new OperatorLog();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Gson gson = new Gson();
			String cTime = sdf.format(date);
			if(operator!=null){
				ol.setOid(operator.getId());
				ol.setOperatorname(operator.getOperatorname());
			}
			ol.setBehavior(this.BEHAVIOR_PROPERTIES.get(behavior));
			ol.setCreateTime(cTime);
			ol.setUpdateTime(cTime);
			ol.setObject(gson.toJson(obj));
			this.baseDao.saveOrUpdate(ol);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
