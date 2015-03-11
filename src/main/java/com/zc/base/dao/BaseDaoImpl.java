/**   
 * @Title: AbstractBaseHibernateDAO.java
 * 
 * @Package com.zc.authority.dao
 * 
 * @Description: 
 * 
 * @Copyright: Copyright 1998 - 2010 zc.com, All Rights Reserved
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 *    
 * @date: Jan 13, 2010 2:59:14 PM
 * 
 * @version: V1.0   
 */

package com.zc.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.zc.base.util.Page;
import com.zc.base.util.QueryParameter;
import com.zc.base.util.SearchCondition;
import com.zc.base.util.TransferUtil;

/**
 * @pdOid 9f1c911e-d385-4de1-83e3-a0a62bdc5aa2
 * @ClassName: AbstractBaseHibernateDAO
 * 
 * @Description:DAO操作基类实现类,实现了通用的数据操作
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 * 
 * @date: Jan 11, 2010 4:59:14 PM
 */
@SuppressWarnings("unchecked")
@Repository("baseDao")
public  class BaseDaoImpl<E, PK extends Serializable> extends
		HibernateDaoSupport implements BaseDao<E, PK> {
	// BaseDaoImpl为abstract
	protected static final Log log = LogFactory.getLog(BaseDaoImpl.class);

	private Class<?> entityClass;

	@Autowired
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	public BaseDaoImpl() {
		Class<?> c = this.getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			this.entityClass = (Class<?>) ((ParameterizedType) t)
					.getActualTypeArguments()[0];
		}
	}

	public E get(PK id) throws HibernateException {
		//System.out.println(this.getHibernateTemplate());
		return (E) this.getHibernateTemplate().get(this.entityClass, id);
	}

	public E get(PK id, LockMode lock) throws HibernateException {
		E entity = (E) this.getHibernateTemplate().get(this.entityClass, id,
				lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	public Integer getRowCount(DetachedCriteria criteria)
			throws HibernateException {
		criteria.setProjection(Projections.rowCount());
		// return (Integer) this.findByCriteria(criteria, 0, 1).get(0);
		Long r = (Long) this.findByCriteria(criteria, 0, 1).get(0);
		return r.intValue();
	}
	public Object getRowCount(String hql,Object... values)
	throws HibernateException {
	  return	findUnique(hql, values);
     }
	public E load(PK id) throws HibernateException {
		return (E) this.getHibernateTemplate().load(this.entityClass, id);
	}

	public E load(PK id, LockMode lock) throws HibernateException {
		E entity = (E) this.getHibernateTemplate().load(this.entityClass, id,
				lock);
		if (entity != null) {
			this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
		}
		return entity;
	}

	public List<E> loadAll() throws HibernateException {
		return (List<E>) this.getHibernateTemplate().loadAll(entityClass);
	}

	public List<E> find(String hql) throws HibernateException {
		return this.getHibernateTemplate().find(hql);
	}

	public List<E> find(String hql, Object... values) throws HibernateException {
		return this.getHibernateTemplate().find(hql, values);
	}

	public List<E> findByNamedQuery(String queryName, Object... values)
			throws HibernateException {
		return this.getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	public List<E> findByNamedQuery(String queryName) throws HibernateException {
		return this.getHibernateTemplate().findByNamedQuery(queryName);
	}

	public List<E> findByNamedQueryAndNamedParam(String queryName,
			Map<String, Object> params) throws HibernateException {
	

		return this.getHibernateTemplate().findByNamedQueryAndNamedParam(
				queryName,
				TransferUtil
						.objectArrayToStringArray(params.keySet().toArray()),
				params.values().toArray());

	}

	public List<E> findByNamedParam(String queryName, Map<String, Object> params)
			throws HibernateException {
		return this.getHibernateTemplate().findByNamedParam(
				queryName,
				TransferUtil
						.objectArrayToStringArray(params.keySet().toArray()),
				params.values().toArray());
	}

	public List<E> findByCriteria(DetachedCriteria criteria)
			throws HibernateException {
		return this.getHibernateTemplate().findByCriteria(criteria);
	}

	public List<E> findByCriteria(DetachedCriteria criteria,
			Integer firstResult, Integer maxResults) throws HibernateException {
		return this.getHibernateTemplate().findByCriteria(criteria,
				firstResult, maxResults);
	}
	/**
	 * 按分页对象查询列表
	 * 
	 * @param page
	 *            分页对象
	 * 
	 * @return
	 */
	public Page<E> findByPage(Page page)throws HibernateException {
		final String hql = this.getHQL(page);
			if (page.getTotalRecord() == 0) {
				page.setTotalRecord(this.findCount(hql));
			}

			
			page.processPage(page.getCurrPage(), page.getRows(), page
					.getTotalRecord());
			if(page.getCurrPages()==-1){
				page.setResult(null);
				return page;
			}
			Query query = createQuery(this.getSession(), hql);

			query.setFirstResult(page.getStartRecord());
			query.setMaxResults(page.getRows());

			page.setResult(query.list());
			if (logger.isInfoEnabled()) {
				logger.info("结束查找指定HQL分页数据," + this.getClass().getName());
			}
			return page;
	}
	/**
	 * 按分页对象查询列表
	 * 
	 * @param page
	 *            分页对象
	 * 
	 * @return
	 */
	public Page<E> findByPage(Page page,Object[] param)throws HibernateException {
		final String hql = page.getHql();
		if (page.getTotalRecord() == 0) {
			page.setTotalRecord(this.findCount(hql,param));
		}
		
		page.processPage(page.getCurrPage(), page.getRows(), page
				.getTotalRecord());
		if(page.getCurrPages()==-1){
			page.setResult(null);
			return page;
		}
		Query query = createQuery(this.getSession(), hql,param);
		
		query.setFirstResult(page.getStartRecord());
		query.setMaxResults(page.getRows());
		
		page.setResult(query.list());
		if (logger.isInfoEnabled()) {
			logger.info("结束查找指定HQL分页数据," + this.getClass().getName());
		}
		return page;
	}
	/**
	 * 根据参数对象得到HQL查询语句
	 * 
	 * @param page
	 *            参数对象
	 * 
	 * @return
	 */
	public String getHQL(QueryParameter qp) {
		if (qp.getHql() != null && !qp.getHql().trim().equals("")) {
			return qp.getHql();
		}
		// 非空判断
		if (qp == null || qp.getEntityName() == null
				|| qp.getEntityName().equals("")) {
			  qp.setEntityName(entityClass.getName());
		}
		String entityName = qp.getEntityName();
		// 生成的hql语句
		StringBuffer hql = new StringBuffer();
		hql.append("select e ");
		hql.append(" from ");
		hql.append(entityName);
		hql.append(" as e ");
		hql.append("where 1=1 ");
		Map<String, String> sortMap = qp.getSortMap();
		List<String> groupList = qp.getGroupList();
		
		List<SearchCondition> searchConditionLists = qp.getSearchCondition();
	    Map<String,String> searchMap=qp.getSearchMap();
		if(qp.getSearchMap()!=null&&qp.getSearchMap().size()>0){
			 hql=searchMapHql(searchMap, hql);
		}else{
			hql=SearchConditionHql(searchConditionLists, hql);
		}
		// 分组
		if (groupList != null && groupList.size() > 0) {
			String groupString = " GROUP by ";
			for (String groupstr : groupList) {
				groupString += groupstr.trim();
				groupString += ",";
			}
			groupString = groupString.substring(0, groupString.length() - 1);
			hql.append(groupString);
		}

		// 排序条件
		if (sortMap != null && sortMap.size() > 0) {
			hql.append(" order by ");
			//优先除创建时间的排序和序号
			Map<String,String> createTimeMap=new HashMap<String, String>();
			for (Map.Entry<String, String> entry : sortMap.entrySet()) {
				if(entry.getKey().trim().contains("createTime")||entry.getKey().trim().equals("id")){
					createTimeMap.put(entry.getKey(), entry.getValue());
					continue;
				}
				hql.append(entry.getKey().trim());
				hql.append(" ");
				hql.append(entry.getValue().trim());
				hql.append(",");
			}
			for(Map.Entry<String, String> entry:createTimeMap.entrySet()){
				hql.append(entry.getKey().trim());
				hql.append(" ");
				hql.append(entry.getValue().trim());
				hql.append(",");
			}
			return hql.substring(0, hql.length() - 1);
		}
		return hql.toString();
	}
	
	public StringBuffer searchMapHql(Map<String,String> searchMap,StringBuffer hql){
		// 查询条件
		if (searchMap != null && searchMap.size() > 0) {
			for (Map.Entry<String, String> entry : searchMap.entrySet()) {
				hql.append(" and e.");
				hql.append(entry.getKey().trim() + " ");
				// 如果是null操作
				if (entry.getValue() == null || entry.getValue().equals("")) {
					hql.append(" is null");
				} else if (entry.getValue().trim().toLowerCase().equals("null")
						|| entry.getValue().trim().toLowerCase().equals(
								"not null")) {
					hql.append(" is ");
					hql.append(entry.getValue().trim());
				} else {
					hql.append("=");
					hql.append("'");
					hql.append(entry.getValue().trim());
					hql.append("'");
				}
			}
		}
		return hql;
	}
	public StringBuffer SearchConditionHql(List<SearchCondition> searchConditionLists,StringBuffer hql){
		SearchCondition condition = new SearchCondition();
        List<SearchCondition> removesearchConditionLists = new ArrayList<SearchCondition>();
		//获取链接查询
		List<String> names=new ArrayList<String>();
		//找出多对多的查询
        List<SearchCondition> manyToManysc =new ArrayList<SearchCondition>();
    	if (searchConditionLists != null && searchConditionLists.size() > 0) {
    		//多对多的查询条件
    		 manyToManysc= getManyToManySc(searchConditionLists);
    		 searchConditionLists.removeAll(manyToManysc);
		}

		// 筛选出有相同查询条件但是值不一样的查询条件 如：pici.id.dictid=first or
		// pici.id.dictid=second
		  String orhql = "";
		   if (searchConditionLists != null && searchConditionLists.size() > 0) {
			   Map<String, List<SearchCondition>> scmap=this.getTimeSearch(searchConditionLists);
			   orhql= getOrSql(scmap, removesearchConditionLists);
		}
		// 先把已处理过的查询条件 提出
		if (searchConditionLists != null && searchConditionLists.size() > 0) {
			searchConditionLists.removeAll(removesearchConditionLists);
		}

		String joinHql="";
		String whereHql="";
		Map<String,String> otherMap=new HashMap<String, String>();
	    for(SearchCondition sc:manyToManysc){
	    	String mapkey=sc.getKey().split("\\.")[0];
	    	String otherName=otherMap.get(mapkey);
	    	if(otherName==null){
	    		otherName= getOtherNameForEntity(names);
	    		String	joinhql= getManyToManyJoinHql(sc,otherName);
	    	    joinHql+=joinhql;
	            joinHql+=",";
	        	otherMap.put(mapkey,otherName );
	    	}
	        String	wherehql= getManyToManyAndHql(sc,otherName);  
	        whereHql+=wherehql+"  ";
     	}
        if(!joinHql.equalsIgnoreCase("")){
        	hql.append(joinHql.substring(0,joinHql.length()-1));
        }
		hql.append(orhql);
        if(!whereHql.equalsIgnoreCase("")){
        	hql.append(whereHql);
        }
		 if (searchConditionLists != null
				&& searchConditionLists.size() > 0) {
			for (int i = 0; i < searchConditionLists.size(); i++) {
				if (i == 0) {
					condition = searchConditionLists.get(i);
					condition.setConditionalRaletion(" AND ");
					hql.append(condition.getConditionalRaletion());
				} else {
					condition = searchConditionLists.get(i);
					hql.append(" " + condition.getConditionalRaletion() + " ");
				}
				hql.append("e." + condition.getKey().trim());

				// 如果是null操作
				if (condition.getValue().trim().toLowerCase().equals("null")
						|| condition.getValue().trim().toLowerCase().equals(
								"not null")) {
					hql.append(" is ");
					hql.append(condition.getValue().trim());
				} else {
					hql.append(" " + condition.getOperator().trim() + " ");
					if (condition.getOperator().trim().equals("in")
							|| condition.getOperator().trim().equalsIgnoreCase(
									"BETWEEN")) {
						hql.append(condition.getValue().trim());

					} else {
						hql.append("'" + condition.getValue().trim() + "'");
					}

				}
			}

		}
		if (searchConditionLists != null) {
			searchConditionLists.addAll(removesearchConditionLists);
		}
		return hql;
	}
	
	/**
	    * 返回每个查询条件key出现次数
	    * @param searchConditionList page.searchConditionList
	    * @return Map<String, List<SearchCondition>>
	    */
	   public Map<String, List<SearchCondition>> getTimeSearch(List<SearchCondition> searchConditionList){
			Map<String, Integer> time = new HashMap<String, Integer>();
			for (SearchCondition sc : searchConditionList) {
				Integer i = time.get(sc.getKey()) != null ? time.get(sc
						.getKey()) : 0;
				time.put(sc.getKey(), i + 1);
			}
			Map<String, List<SearchCondition>> scmap = new HashMap<String, List<SearchCondition>>();

			for (Map.Entry<String, Integer> sctime : time.entrySet()) {
				if (sctime.getValue() > 1) {
					List<SearchCondition> listsc = new ArrayList<SearchCondition>();
					for (SearchCondition sc : searchConditionList) {
						if (sc.getKey().equals(sctime.getKey())) {
							listsc.add(sc);
						}
					}
					scmap.put(sctime.getKey(), listsc);
				}
			}
		   
		   return scmap;
	   }
	  /**
	    * 
	    * 获取OR sql语句
	    * @param scmap
	    * @param removesearchConditionLists
	    * @return
	    */
	   public String getOrSql(Map<String, List<SearchCondition>> scmap,List<SearchCondition>  removesearchConditionLists){
		   String orhql="";
			for (Map.Entry<String, List<SearchCondition>> entry : scmap
					.entrySet()) {
				removesearchConditionLists.addAll(entry.getValue());
				orhql += " and( ";
				// 记录操作为AND的对象
				SearchCondition andSearchCondition = new SearchCondition();
				for (SearchCondition sc : entry.getValue()) {
					if (sc.getConditionalRaletion().trim().equalsIgnoreCase("AND")) {
						orhql += sc.getKey();
						if (sc.getValue().trim().toLowerCase().equals("null")
								|| sc.getValue().trim().toLowerCase().equals(
										"not null")) {
							orhql += " is ";
							orhql += sc.getValue().trim();
						} else {
							orhql += " " + sc.getOperator().trim() + " ";
							orhql += "'" + sc.getValue().trim() + "'";
						}
						andSearchCondition = sc;
						break;
					}
				}
				entry.getValue().remove(andSearchCondition);
				for (SearchCondition sc : entry.getValue()) {
					orhql += " or ";
					orhql += sc.getKey();
					if (sc.getValue().trim().toLowerCase().equals("null")
							|| sc.getValue().trim().toLowerCase().equals(
									"not null")) {
						orhql += " is ";
						orhql += sc.getValue().trim();
					} else {
						orhql += " " + sc.getOperator().trim() + " ";
						orhql += "'" + sc.getValue().trim() + "'";
					}
				}
				orhql += ")";
			}
		   return orhql;
	   }
	
	public List<SearchCondition> getManyToManySc(List<SearchCondition> sclist){
		   List<SearchCondition>  manytomanySc=new ArrayList<SearchCondition>();
			for(SearchCondition sc:sclist){
				if(sc.getRefType()!=null&&(sc.getRefType().equals("many-to-many")||sc.getRefType().equals("one-to-many"))){
					manytomanySc.add(sc);
				}
			}
			return manytomanySc;
		}
		
		public String getManyToManyAndHql(SearchCondition sc,String otherName){
			String hql="";
				String key=sc.getKey();
				 hql=sc.getConditionalRaletion()+" "+otherName+".";
					hql+=key.substring(key.indexOf(".")+1)+sc.getOperator()+"'"+sc.getValue()+"'";			
			
			return hql;
		}
		public String getManyToManyJoinHql(SearchCondition sc,String otherName){
			String hql="";
				String key=sc.getKey();
				 hql="LEFT JOIN e."+key.substring(0,key.indexOf("."))+"  as "+otherName;
			return hql;
		}
		
		//获取别名 多对多查询
		public String getOtherNameForEntity(List<String> names ){
			if(names==null){
				names=new ArrayList<String>();
			}
		List<String> allnames=new ArrayList<String>();
		allnames.add("m");
		allnames.add("n");
		allnames.add("k");
		allnames.add("s");
		if(names==null){
			return allnames.get(0);
		}
		    for(String name: allnames ){
				if(names.contains(name)){
					continue;
				}
				names.add(name);
				return name;
			}
		 return null;
		}
	/**
	 * 根据查询条件与参数列表创建Query对象
	 * 
	 * 
	 * @param session
	 *            Hibernate会话
	 * @param hql
	 *            HQL语句
	 * @param objects
	 *            参数列表
	 * @return Query对象
	 * 
	 * @pdOid b020b5dd-f6e5-4590-bf58-c9e0144ed874
	 */
	public Query createQuery(Session session, String hql, Object... objects) {
		Query query = session.createQuery(hql);
		if (objects != null) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query;
	}
	public int findCount(String hql) {
		int index = 0;
		if (hql.indexOf("from") > 0) {
			index = hql.indexOf("from");
		} else if (hql.indexOf("FROM") > 0) {
			index = hql.indexOf("FROM");
		}
		hql = "select count(*) " + hql.substring(index);
		return findInt(hql);
	}
	public int findCount(String hql,Object[]param) {
		int index = 0;
		if(hql.toLowerCase().indexOf("group by")!=-1){
			return this.find(hql, param).size();
		}else{
			if (hql.indexOf("from") > 0) {
				index = hql.indexOf("from");
			} else if (hql.indexOf("FROM") > 0) {
				index = hql.indexOf("FROM");
			}
			hql = "select count(*) " + hql.substring(index);
			return findInt(hql,param);
		}
	}
	/**
	 * 查找指定HQL并返回INT型
	 * 
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数列表
	 * @return INT
	 * 
	 * @pdOid 97927a63-9d48-4e43-b1b9-796ff5103f31
	 */
	public int findInt(final String hql, final Object... values) {
		Object obj = findUnique(hql, values);
		return null == obj ? 0 : Integer.parseInt(obj.toString());
	}
	/**
	 * 按照HQL语句查询唯一对象.
	 * 
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            可变参数集合
	 * @return OBJECT对象
	 * 
	 * @pdOid 81f544e4-7f7c-4b93-91f0-5b104d941b4e
	 */
	public Object findUnique(final String hql, final Object... values) {
		Object obj = new Object();
		try {
			if (logger.isInfoEnabled()) {
				logger.info("开始查询返回唯一结果的HQL语句," + hql);
			}
			obj = getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session s)
						throws HibernateException, SQLException {
					Query query = createQuery(s, hql, values);
					return query.uniqueResult();
				}
			});
			if (logger.isInfoEnabled()) {
				logger.info("结束查询返回唯一结果的HQL语句," + hql);
			}
			return obj;
		} catch (RuntimeException e) {
			logger.error("查询指定HQL异常，HQL：" + hql, e);
		}
		return null;
	}
	public void save(E entity) throws HibernateException {
		this.getHibernateTemplate().save(entity);
	}

	public void saveOrUpdate(E entity) throws HibernateException {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	public void saveOrUpdateBatch(Collection<E> entitys)
			throws HibernateException {
		int i = 0;
		for (E entity : entitys) {
			this.getHibernateTemplate().saveOrUpdate(entity);
			if (i % 200 == 0) {
				this.flush();
				this.clear();
			}
			i++;
		}
	}

	public void delete(E entity) throws HibernateException {
		this.getHibernateTemplate().delete(entity);
	}
	public void update(final String hql,final Object[]param) throws HibernateException {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {  
				Query q = createQuery(session,hql,param);
				q.executeUpdate();
				return null;
			}
		});
	}
	public int updateWithCount(final String hql,final Object[]param) {
		return this.getHibernateTemplate().bulkUpdate(hql, param);
	}

	public void delete(E entity, LockMode lock) throws HibernateException {
		this.getHibernateTemplate().delete(entity, lock);
		this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
	}

	public void delete(Collection<E> entitys) throws HibernateException {
		this.getHibernateTemplate().deleteAll(entitys);
	}

	public void update(E entity) throws HibernateException {
		this.getHibernateTemplate().update(entity);
	}

	public void update(E entity, LockMode lock) throws HibernateException {
		this.getHibernateTemplate().update(entity, lock);
		this.flush();// 如果实体不为null,立即刷新,否则锁不会生效
	}

	public Integer bulkUpdate(String hql) throws HibernateException {
		return this.getHibernateTemplate().bulkUpdate(hql);
	}

	public Integer bulkUpdate(String hql, Object... values)
			throws HibernateException {
		return this.getHibernateTemplate().bulkUpdate(hql, values);
	}

	public void flush() throws HibernateException {
		this.getHibernateTemplate().flush();
	}

	public void clear() throws HibernateException {
		this.getHibernateTemplate().clear();
	}

	public void lock(E entity, LockMode lock) throws HibernateException {
		this.getHibernateTemplate().lock(entity, lock);
	}

	public DetachedCriteria createDetachedCriteria() {
		return DetachedCriteria.forClass(this.entityClass);
	}

	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c) {
		return DetachedCriteria.forClass(c);
	}

	public Criteria createCriteria() {
		return this.createDetachedCriteria().getExecutableCriteria(
				this.getSession());
	}

	public E merge(E entity) throws HibernateException  {
			E result = (E) getHibernateTemplate().merge(entity);
			return result;
	}
	/**
	 * 查找指定属性集合的实体集合
	 * 
	 * 
	 * @param entityClass
	 *            实体
	 * @param propertyName
	 *            属性名
	 * @param value
	 *            条件
	 * @return 实体集合
	 * 
	 * @pdOid 59eaff80-8379-48fa-b606-5f3fa345a9e0
	 */
	public List<E> findByProperty(QueryParameter qp) throws HibernateException{
		String hql = this.getHQL(qp);
			return getHibernateTemplate().find(hql, null);
	}

	public List<Object> findByHql(String hql) throws HibernateException {
		List list = new ArrayList();
		
			if (logger.isInfoEnabled()) {
				logger.info("开始查询指定HQL语句," + hql);
			}
			list = getHibernateTemplate().find(hql);
			if (logger.isInfoEnabled()) {
				logger.info("结束查询指定HQL语句," + hql);
			}
			return list;
	}
	public List<Object> findByHql(String hql,Object[]param) throws HibernateException {
		List list = new ArrayList();
		
		if (logger.isInfoEnabled()) {
			logger.info("开始查询指定HQL语句," + hql);
		}
		list = getHibernateTemplate().find(hql, param);
		if (logger.isInfoEnabled()) {
			logger.info("结束查询指定HQL语句," + hql);
		}
		return list;
	}

	public Object findById(String entityName, String id) {
		return  this.getHibernateTemplate().get(entityName, id);
	}

	public Object findById(String entityName, Long id) {
		return  this.getHibernateTemplate().get(entityName, id);
	}

	public void evict(Object obj) {
		this.getHibernateTemplate().evict(obj);
		
	}
	public Object findById(String entityName, Integer id) {
		return  this.getHibernateTemplate().get(entityName, id);
	}

	public List<Object> getTopRows(String hql,int rows, Object... values)
			throws HibernateException {
		Object obj = new Object();
		try {
			if (logger.isInfoEnabled()) {
				logger.info("开始查询返回top结果的HQL语句," + hql);
			}
			Query query = createQuery(this.getSession(), hql,values);

			query.setFirstResult(0);
			query.setMaxResults(rows);
			if (logger.isInfoEnabled()) {
				logger.info("结束查询返回top结果的HQL语句," + hql);
			}
			return  query.list();
		
		} catch (RuntimeException e) {
			logger.error("查询指定HQL异常，HQL：" + hql, e);
		}
		return null;
	}
}
