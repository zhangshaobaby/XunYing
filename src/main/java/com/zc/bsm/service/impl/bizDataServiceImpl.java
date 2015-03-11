package com.zc.bsm.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zc.base.dao.BaseDaoImpl;
import com.zc.base.service.BaseServiceImpl;
import com.zc.bsm.dao.bizDataDao;
import com.zc.bsm.service.bizDataService;
@Service("bizDataService")
public class bizDataServiceImpl  extends BaseServiceImpl<Object,String> implements bizDataService{
	protected static final Log logger = LogFactory.getLog(bizDataServiceImpl.class);
	
	// 当前操作时间
	protected Date nowdate = new Date();
	// 格式
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	public void setBaseDao(bizDataDao bizDataDao) {
		this.baseDao = bizDataDao;
	}
	// 保存
	public Object bizSave(Class entity, Object obj) throws Exception {
		// 数据库obj
		Object persistenceObj = null;
		try {
			Object id =  PropertyUtils.getProperty(obj, "id");
			if (id != null && !id.toString().equals("")) {
				persistenceObj = this.bizfindbyid(entity, id);
			}else{
				PropertyUtils.setProperty(obj, "id", null);
			}
			// 二次封装
			persistenceObj = getPersistenceObj(entity, persistenceObj, obj);
			doEmptityRelation(entity, persistenceObj);
			// 设置系统属性值
			persistenceObj = setSystemPropertyValue(persistenceObj);
			this.saveOrUpdate(persistenceObj);
		} catch (Exception ex) {
			logger.error("entity.getClassPath():" + entity.getName());
			logger.error(this, ex);
		}
		return persistenceObj;
	}
	 public  Object bizfindbyid(Class entity, Object id){
		String pktype=  getPKType(entity);
		if(pktype.equals("java.lang.Long")){
		 return this.findById(entity.getName(), Long.parseLong(id.toString())); 
		}else if(pktype.equals("java.lang.Integer")){
			 return this.findById(entity.getName(), Integer.parseInt(id.toString())); 
		}
		else{
			 return this.findById(entity.getName(), id.toString()); 
		}
	}
	//获取主键类型
	public String getPKType(Class entity){
      try {
		Field  idfield=entity.getDeclaredField("id");
		return getRefClass(idfield);
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return null;	
	}
	
	/**
	 * 修改保存方法 满足业务需求 将从数据库查询处理的对象从新赋值 entity ：当前实体 persistencebject：入库对象
	 * strutsObject struts从前台封装的对象
	 * 
	 */
	public Object getPersistenceObj(Class claszz, Object persistencebject,
			Object strutsObject) throws Exception {
		if (persistencebject == null) {
			persistencebject = strutsObject;
		}
		for (Field property : claszz.getDeclaredFields()) {
			try {
				Object propertyValue =  PropertyUtils.getProperty(
						strutsObject, property.getName());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("实体"+claszz.getName()+"的属性"+property.getName()+"不存在");
				continue;
			}
				Object propertyValue =  PropertyUtils.getProperty(
						strutsObject, property.getName());
				if (propertyValue != null) {
					PropertyUtils.setProperty(persistencebject, property
							.getName(), propertyValue);
				}
		}
		return persistencebject;
	}
	public void doEmptityRelation(Class entity, Object obj) throws Exception {
		// 获取该对象的所有字段
		Field[] fileds = entity.getDeclaredFields();
		// 循环所有的属性
		for (Field filed : fileds) {
			// 如果属性值为空 则跳过该属性
			Object propertyvalue = PropertyUtils.getProperty(obj, filed
					.getName());
			if (propertyvalue == null) {
				continue;
			}
			String refClassPath = getRefClass(filed);
			String refType = getRefType(filed);
			// 如果有引用对象
			if (refType != null && !refType.equals("singleField")) {
				try {
					// 如果是多对一 或者一对一 取出引用对象的主键
					if (refType.equals("refObject")) {
					 String refid=String.valueOf(PropertyUtils.getProperty(propertyvalue, "id")) ;
					  if(refid.equals("")){
						  PropertyUtils.setProperty(obj, filed.getName(), null);
					  }
						
					} else if (refType.equals("collectionType")) {
						List newRefpropertyvalue = new ArrayList<Object>();
						List<Object> refpropertyvalue = (List<Object>) propertyvalue;
						newRefpropertyvalue.addAll(refpropertyvalue);
						for (Object refObj : refpropertyvalue) {
							if(refObj==null){
								newRefpropertyvalue.remove(refObj);
								continue;
							}
							Object refpropertyid = PropertyUtils
									.getProperty(refObj,"id");
							if (refpropertyid == null) {
								newRefpropertyvalue.remove(refObj);
							}
						}
						PropertyUtils.setProperty(obj, filed.getName(),
								newRefpropertyvalue);
					} else {
						continue;
					}
				} catch (Exception e1) {
					logger.error("实体" + entity.getName()
							+ "在属性表中的属性" + filed.getName() + "在JEAN中不存在");
					e1.printStackTrace();
					continue;
				}
			}
		}
	}
	
	// 获取引用实体类 //集合无泛型 返还null, 否则返还类
	public String getRefClass(Field field) {
		if (field == null) {
			return null;
		}
		Type type = field.getGenericType();
		String typeString = type.toString();
		if (typeString.contains("java.util.Set")
				|| typeString.contains("java.util.List")
				|| typeString.contains("java.util.Collection")) {
			int genericIndex = typeString.indexOf("<");
			if (genericIndex == -1) {
				return null;
			}
			return typeString.substring(typeString.indexOf("<") + 1, typeString
					.indexOf(">"));
		} else {
			return typeString.substring(typeString.indexOf("class") + 5).trim();
		}
	}

	// 获取引用类型 collectionType 集合类型 or singleType 基础数据类型或者多对一
	public String getRefType(Field field) {
		if (field == null) {
			return null;
		}
		Type type = field.getGenericType();
		String typeString = type.toString();
		if (typeString.contains("java.util.Set")
				|| typeString.contains("java.util.List")
				|| typeString.contains("java.util.Collection")) {
			// 集合字段
			return "collectionType";
		} else {
			if (typeString.contains("java.lang")
					|| typeString.contains("java.util")||typeString.contains("java.math")) {
				// 普通字段
				return "singleField";
			} else {
				// 引用对象
				return "refObject";
			}
		}
	}
	// 设置系统属性值
	public Object setSystemPropertyValue(Object persistencebject)
			throws Exception {
		nowdate = new Date();
	 String	username = "";
		String operator = "-- ";
		try {
			Object createTimeObject=PropertyUtils.getProperty(persistencebject, "createTime");
			if(createTimeObject==null){
				// 创建时间
				PropertyUtils.setProperty(persistencebject, "createTime", format
						.format(nowdate));
			}
			/*//Object creatorObject=PropertyUtils.getProperty(persistencebject, "creator");
			if(creatorObject==null){
				// 创建人
				PropertyUtils.setProperty(persistencebject, "creator", username);
			}*/
			// 最后修改时间
			PropertyUtils.setProperty(persistencebject, "updateTime", format
					.format(nowdate));
			// 最后修改人
			//PropertyUtils.setProperty(persistencebject, "updator", username);

		} catch (Exception e) {
			logger.warn("无系统属性");
		}
		return persistencebject;
	}
	 //异步 业务数据删除
    public  boolean bizDel(String entityName,String ids){
    	if(ids==null||ids.equals("")){
    		return false;
    	}
    	String [] idstring=ids.split(",");
    	for(String id:idstring){
    	   Object obj=this.findById(entityName, id);
    	   if(obj!=null){
    		   this.delete(obj);
    	   }
    	}
    	return true;	
    }
	
}
