/**   
 * @Title: AbstractBaseService.java
 * 
 * @Package com.zc.authority.service
 * 
 * @Description: 
 * 
 * @Copyright: Copyright 1998 - 2009 zc.com, All Rights Reserved
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 *    
 * @date: Dec 29, 2009 2:07:24 PM
 * 
 * @version: V1.0   
 */

package com.zc.base.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zc.base.dao.BaseDao;
import com.zc.base.exception.OpenException;
import com.zc.base.util.Page;
import com.zc.base.util.QueryParameter;

/**
 * @pdOid d0256d94-2404-4c68-8bc8-f57eedd420b0
 * @ClassName: AbstractBaseService
 * 
 * @Description: 基础服务接口实现抽象类
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 * 
 * @date: Dec 29, 2009 2:07:24 PM
 */
@Service("baseService")
@Transactional
public class BaseServiceImpl<E, PK extends Serializable> implements
		BaseService<E, PK> {

	private Class<?> entityClass;

	protected static final Log log = LogFactory.getLog(BaseServiceImpl.class);

	public BaseServiceImpl() {
		Class<?> c = this.getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			this.entityClass = (Class<?>) ((ParameterizedType) t)
					.getActualTypeArguments()[0];
		}
	}

	@Autowired(required = false)
	@Qualifier("baseDao")
	public BaseDao<E, PK> baseDao;

	private BaseDao<E, PK> getBaseDao() {
		return baseDao;
	}

	public E get(PK id) {
		return this.getBaseDao().get(id);
	}

	public E get(PK id, LockMode lock) {
		return this.getBaseDao().get(id, lock);
	}

	public Integer getRowCount(DetachedCriteria criteria) {
		return this.getBaseDao().getRowCount(criteria);
	}
	public Object getRowCount(String hql,Object... values) {
		return this.getBaseDao().getRowCount(hql, values);
	}
	public List<Object> getTopRows(String hql,int rows,Object... values) {
		return this.getBaseDao().getTopRows(hql, rows, values);
	}
	public E load(PK id) {
		return this.getBaseDao().load(id);
	}

	public E load(PK id, LockMode lock) {
		return this.getBaseDao().load(id, lock);
	}

	public List<E> loadAll() {
		return this.getBaseDao().loadAll();
	}

	public List<E> find(String hql) {
		return this.baseDao.find(hql);
	}

	public List<E> find(String hql, Object... values) {
		return this.getBaseDao().find(hql, values);
	}

	public List<E> findByNamedQuery(String queryName, Object... values) {
		return this.getBaseDao().findByNamedQuery(queryName, values);
	}

	public List<E> findByNamedQuery(String queryName) {
		return this.getBaseDao().findByNamedQuery(queryName);
	}

	public List<E> findByNamedQueryAndNamedParam(String queryName,
			Map<String, Object> params) {
		return this.getBaseDao().findByNamedQueryAndNamedParam(queryName,
				params);
	}

	public List<E> findByNamedParam(String queryName, Map<String, Object> params) {
		return this.getBaseDao().findByNamedParam(queryName, params);
	}

	public List<E> findByCriteria(DetachedCriteria criteria) {
		return this.getBaseDao().findByCriteria(criteria);
	}

	public List<E> findByCriteria(DetachedCriteria criteria,
			Integer firstResult, Integer maxResults) {
		return this.getBaseDao().findByCriteria(criteria, firstResult,
				maxResults);
	}

	public void save(E entity) throws OpenException {
		this.getBaseDao().save(entity);
	}

	public void saveOrUpdate(E entity) throws OpenException {
		this.getBaseDao().saveOrUpdate(entity);
	}

	public void saveOrUpdateBatch(Collection<E> entitys) throws OpenException {
		this.getBaseDao().saveOrUpdateBatch(entitys);
	}

	public void delete(E entity) throws OpenException {
		this.getBaseDao().delete(entity);
	}

	public void delete(E entity, LockMode lock) throws OpenException {
		this.getBaseDao().delete(entity, lock);
	}

	public void delete(Collection<E> entitys) throws OpenException {
		this.getBaseDao().delete(entitys);
	}

	public void update(E entity) throws OpenException {
		this.getBaseDao().update(entity);
	}

	public void update(E entity, LockMode lock) throws OpenException {
		this.getBaseDao().update(entity, lock);
	}

	public Integer bulkUpdate(String hql) {
		return this.getBaseDao().bulkUpdate(hql);
	}

	public Integer bulkUpdate(String hql, Object... values) {
		return this.getBaseDao().bulkUpdate(hql, values);
	}

	public void lock(E entity, LockMode lock) throws OpenException {
		this.getBaseDao().lock(entity, lock);
	}

	public DetachedCriteria createDetachedCriteria() {
		return this.getBaseDao().createDetachedCriteria();
	}

	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c) {
		return this.getBaseDao().createDetachedCriteria(c);
	}

	public Criteria createCriteria() {
		return this.getBaseDao().createCriteria();
	}

	

	public Page findByPage(Page page) {
		return this.getBaseDao().findByPage(page);
	}

	public List<E> findByProperty(QueryParameter qp) {
		return this.baseDao.findByProperty(qp);
	}

	public Page limitToChangePage(Page pagen, List list) throws Exception {
		List newlist = new ArrayList();
		for (int i = pagen.getStartRecord(); i < pagen.getStartRecord()
				+ pagen.getRows(); i++) {
			if (i < list.size() - 1) {
				newlist.add(list.get(i));
			} else if (i == list.size() - 1 && list.size() > 0) {
				newlist.add(list.get(i));
				break;
			} else {
				break;
			}
		}
		pagen.setResult(newlist);
		return pagen;
	}

	public List<Object> findByHql(String hql) throws HibernateException {
		return this.baseDao.findByHql(hql);
	}
	public Object findById(String entityName, String id) {
		return  this.baseDao.findById(entityName, id);
	}
	public Object findById(String entityName, Long id) {
		return  this.baseDao.findById(entityName, id);
	}

	public void evict(Object obj) {
		this.baseDao.evict( obj);
	}

	public Object findById(String entityName, Integer id) {
		return  this.baseDao.findById(entityName, id);
	}
}