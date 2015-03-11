/**   
 * @Title: BaseService.java
 * 
 * @Package com.zc.authority.service
 * 
 * @Description: 
 * 
 * @Copyright: Copyright 1998 - 2009 zc.com, All Rights Reserved
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 *    
 * @date: Dec 28, 2009 2:20:24 PM
 * 
 * @version: V1.0   
 */

package com.zc.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import com.zc.base.exception.OpenException;
import com.zc.base.util.Page;
import com.zc.base.util.QueryParameter;

/**
 * @pdOid 28284d08-6455-4ff4-b083-0a5a574a4268
 * @ClassName: BaseService
 * @Description: 基础服务接口
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 * 
 * @date: Dec 28, 2009 2:20:24 PM
 */

public interface BaseService<E, PK extends Serializable> {

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	public E get(PK id);

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	public E get(PK id, LockMode lock);

	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param criteria
	 *            一个DetachedCriteria对象
	 * @return 记录数
	 */
	public Integer getRowCount(DetachedCriteria criteria);
	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param String  hql
	 * @return 记录数
	 */
	public Object getRowCount(String  hql,Object... values);
	/**
	 * 查询数据库对应的头几条记录数//用于解释hql 不支持 top 和limit的中间解决方案
	 * 
	 * @param String  hql
	 * @return 记录数
	 */
	List<Object> getTopRows(String  hql,int rows,Object... values) throws HibernateException;
	/**
	 * 
	 * @param id
	 *            根据主键加裁一个实体对象
	 * @return 一个实体对象
	 */
	public E load(PK id);

	/**
	 * 
	 * @param id
	 *            根据主键加裁实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	public E load(PK id, LockMode lock);

	/**
	 * 
	 * @return 加裁所有对象
	 */
	public List<E> loadAll();

	/**
	 * 
	 * @param entity
	 *            保存一个实体
	 * @throws OpenException
	 *             抛出Exception异常
	 */
	public void save(E entity) throws OpenException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void delete(E entity) throws OpenException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @param lock
	 *            加锁实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void delete(E entity, LockMode lock) throws OpenException;

	/**
	 * 
	 * @param entitys
	 *            删除多个实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void delete(Collection<E> entitys) throws OpenException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void update(E entity) throws OpenException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @param lock
	 *            加锁实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void update(E entity, LockMode lock) throws OpenException;

	/**
	 * 
	 * @param entity
	 *            当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void saveOrUpdate(E entity) throws OpenException;

	/**
	 * 
	 * @param entitys
	 *            保存多个实体
	 * @throws OpenException
	 *             抛出异常
	 */
	public void saveOrUpdateBatch(Collection<E> entitys) throws OpenException;

	/*---------------------------利用hql,sql对数据库进行操作--------------------------------*/

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @return 受影响行的记录数
	 */
	public Integer bulkUpdate(String hql);

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @param params
	 *            hql语句参数
	 * @return 受影响行的记录数
	 */
	public Integer bulkUpdate(String hql, Object... values);

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list集合
	 */
	public List<E> find(String hql);
	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list<Object>集合 可以是任意类型
	 */
	List<Object> findByHql(String hql) throws HibernateException;
    
	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param params
	 *            hql语句参数
	 * @return 一个list集合
	 */
	public List<E> find(String hql, Object... values);

	/**
	 * 
	 * @param queryName
	 *            使用命名的hql语句进行查询
	 * @return 一个list集合
	 */
	public List<E> findByNamedQuery(String queryName);

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param values
	 *            参数集合
	 * @return 一个list集合
	 */
	public List<E> findByNamedQuery(String queryName, Object... values);

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param params
	 *            参数集合<br>
	 *            Map的键为参数名称即paramName<br>
	 *            Map的值则为values
	 * @return 一个list集合
	 */
	public List<E> findByNamedParam(String queryName, Map<String, Object> params);

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param params
	 *            参数集合<br>
	 *            Map的键为参数名称即paramName<br>
	 *            Map的值则为values
	 * @return 一个list集合
	 */
	public List<E> findByNamedQueryAndNamedParam(String queryName,
			Map<String, Object> params);

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来检索数
	 * @return 一个list集合
	 */
	public List<E> findByCriteria(DetachedCriteria criteria);

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来分页检索数据
	 * @param firstResult
	 *            开始条数
	 * @param maxResults
	 *            返回记录数
	 * @return 一个list集合
	 */
	public List<E> findByCriteria(DetachedCriteria criteria,
			Integer firstResult, Integer maxResults);

	// 返还page分页对象
	Page findByPage(Page page);

	/**
	 * 加锁指定的实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * @param lock
	 *            加锁
	 */
	public void lock(E entity, LockMode lock) throws OpenException;

	/**
	 * 
	 * @return 根据泛型类型,创建一个与会话无关的检索对象
	 */
	public DetachedCriteria createDetachedCriteria();

	/**
	 * 
	 * @param c
	 *            为一个实体类型
	 * @return 根据指定的类型创建一个与会话无关的检索对象
	 */
	public DetachedCriteria createDetachedCriteria(
			Class<? extends Serializable> c);

	/**
	 * 
	 * @return 创建与会话绑定的检索标准对象
	 */
	public Criteria createCriteria();

	public List<E> findByProperty(QueryParameter qp);

	public Page limitToChangePage(Page pagen, List list) throws Exception;
	  //实体查找
    public Object findById(String entityName,String id);
    //查找主键类型为long类型的对象
    public Object findById(String entityName,Long id);
  //查找主键类型为int类型的对象
    public Object findById(String entityName,Integer id);
    public  void evict(Object obj);
}