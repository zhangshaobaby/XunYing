/**   
 * @Title: BaseDao.java
 * 
 * @Package com.zc.authority.dao
 * 
 * @Description: 
 * 
 * @Copyright: Copyright 1998 - 2010 zc.com, All Rights Reserved
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 *    
 * @date: Jan 13, 2010 2:52:02 PM
 * 
 * @version: V1.0   
 */
package com.zc.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import com.zc.base.util.Page;
import com.zc.base.util.QueryParameter;

/**
 * @pdOid 28284d08-6455-4ff4-b083-0a5a574a4268
 * @ClassName: BaseDao
 * 
 * @Description: DAO操作基类接口,实现了通用的数据操作
 * 
 * @author: <a href="mailto:ws.t@msn.com">Winston</a>
 * 
 * @date: Jan 11, 2010 2:52:02 PM
 */
public interface BaseDao<E, PK extends Serializable> {

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @return 一个实体对象
	 */
	E get(PK id) throws HibernateException;

	/**
	 * 
	 * @param id
	 *            根据主键查询一个实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	E get(PK id, LockMode lock) throws HibernateException;

	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param criteria
	 *            一个DetachedCriteria对象
	 * @return 记录数
	 */
	Integer getRowCount(DetachedCriteria criteria) throws HibernateException;
	/**
	 * 查询数据库对应的记录数
	 * 
	 * @param String  hql
	 * @return 记录数
	 */
	Object getRowCount(String  hql,Object... values) throws HibernateException;
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
	E load(PK id) throws HibernateException;

	/**
	 * 
	 * @param id
	 *            根据主键加裁实体
	 * @param lock
	 *            加锁实体
	 * @return 一个实体对象
	 */
	E load(PK id, LockMode lock) throws HibernateException;

	/**
	 * 
	 * @return 加裁所有对象
	 */
	List<E> loadAll() throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            保存一个实体
	 * @throws HibernateException
	 *             抛出Exception异常
	 */
	void save(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void delete(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            删除一个实体
	 * @param lock
	 *            加锁实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void delete(E entity, LockMode lock) throws HibernateException;

	/**
	 * 
	 * @param entitys
	 *            删除多个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void delete(Collection<E> entitys) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void update(E entity) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            修改一个实体
	 * @param lock
	 *            加锁实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void update(E entity, LockMode lock) throws HibernateException;

	/**
	 * 
	 * @param entity
	 *            当实体在数据库不在在与之对应记录时,则保存实体,在在实体,则更新实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void saveOrUpdate(E entity) throws HibernateException;

	/**
	 * 
	 * @param entitys
	 *            保存多个实体
	 * @throws HibernateException
	 *             抛出异常
	 */
	void saveOrUpdateBatch(Collection<E> entitys) throws HibernateException;

	/*---------------------------利用hql,sql对数据库进行操作--------------------------------*/

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql) throws HibernateException;

	/**
	 * 
	 * @param hql
	 *            使用hql语句进行数据库增删改操作
	 * @param params
	 *            hql语句参数
	 * @return 受影响行的记录数
	 */
	Integer bulkUpdate(String hql, Object... values) throws HibernateException;

	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list集合
	 */
	List<E> find(String hql) throws HibernateException;
	
	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @return 一个list<Object>集合 可以是任意类型
	 */
	List<Object> findByHql(String hql) throws HibernateException;
	List<Object> findByHql(String hql,Object[]param) throws HibernateException;
  
	/**
	 * 
	 * @param hql
	 *            使用hql语句,检索数据
	 * @param params
	 *            hql语句参数
	 * @return 一个list集合
	 */
	List<E> find(String hql, Object... values) throws HibernateException;

	/**
	 * 
	 * @param queryName
	 *            使用命名的hql语句进行查询
	 * @return 一个list集合
	 */
	List<E> findByNamedQuery(String queryName) throws HibernateException;

	/**
	 * 
	 * @param queryName
	 *            使用带参数的命名hql语句进行查询
	 * @param values
	 *            参数集合
	 * @return 一个list集合
	 */
	List<E> findByNamedQuery(String queryName, Object... values)
			throws HibernateException;

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
	List<E> findByNamedParam(String queryName, Map<String, Object> params)
			throws HibernateException;

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
	List<E> findByNamedQueryAndNamedParam(String queryName,
			Map<String, Object> params) throws HibernateException;

	/**
	 * 
	 * @param criteria
	 *            使用指定的检索标准来检索数
	 * @return 一个list集合
	 */
	List<E> findByCriteria(DetachedCriteria criteria) throws HibernateException;

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
	List<E> findByCriteria(DetachedCriteria criteria, Integer firstResult,
			Integer maxResults) throws HibernateException;
   //返还page分页对象
    Page findByPage(Page page) throws HibernateException;
    
	/**
	 * 加锁指定的实体
	 * 
	 * @param entity
	 *            实体对象
	 * 
	 * @param lock
	 *            加锁
	 */
	void lock(E entity, LockMode lock) throws HibernateException;

	/**
	 * 强制立即更新到数据库,否则需要事务提交后,才会提交到数据库
	 */
	void flush() throws HibernateException;

	void clear() throws HibernateException;

	/**
	 * 
	 * @return 根据泛型类型,创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria() throws HibernateException;

	/**
	 * 
	 * @param c
	 *            为一个实体类型
	 * @return 根据指定的类型创建一个与会话无关的检索对象
	 */
	DetachedCriteria createDetachedCriteria(Class<? extends Serializable> c)
			throws HibernateException;

	/**
	 * 
	 * @return 创建与会话绑定的检索标准对象
	 */
	Criteria createCriteria() throws HibernateException;

	/**
	 * 补充方法(未测) 据说可以无视session的状态持久化对象
	 * 
	 * 
	 * @param entity
	 *            实体类
	 * @return 持久后的实体类
	 * 
	 * @pdOid 87586f3c-c141-44ed-a2ad-5625f720079e
	 */
	public E merge(E entity)  throws HibernateException;
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
	public List<E> findByProperty(QueryParameter qp)  throws HibernateException;
	/**
	 * 重载分页
	 * @param page
	 * @param param
	 * @return
	 * @throws HibernateException
	 */
	Page findByPage(Page page,Object[] param) throws HibernateException;
	/**
	 * 更新
	 * @param hql
	 * @param param
	 * @throws HibernateException
	 */
    public void update(final String hql,final Object[]param) throws HibernateException ;
    public int updateWithCount(final String hql,final Object[]param);
    //实体查找
    public Object findById(String entityName,String id);
    //查找主键类型为long类型的对象
    public Object findById(String entityName,Long id);
  //查找主键类型为long类型的对象
    public Object findById(String entityName,Integer id);
    //将一个object 从session中逐出
    public  void evict(Object obj);
}
