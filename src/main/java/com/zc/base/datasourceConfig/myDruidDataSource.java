package com.zc.base.datasourceConfig;

import java.sql.SQLException;

public class myDruidDataSource extends com.alibaba.druid.pool.DruidDataSource {
	//String	url="jdbc:mysql://192.168.1.111:3306/cxzc?useUnicode=true&characterEncoding=utf-8";
	String	url="jdbc:mysql://218.244.157.101:3306/cxzc?useUnicode=true&characterEncoding=utf-8";
	String	driverClassName="com.mysql.jdbc.Driver";
	String	username="agent";
	String	password="123456";
		     
	String	filters="stat";
		 
	int	maxActive=1000;
	int	initialSize=1;
	long	maxWait=60000;
	int	minIdle=10;
		 
	long	timeBetweenEvictionRunsMillis=60000;
	long	minEvictableIdleTimeMillis=300000;

	boolean	testWhileIdle=true;
	boolean	testOnBorrow=false;
	boolean	testOnReturn=false;
	boolean	poolPreparedStatements=false;
	int	maxOpenPreparedStatements=-1;

	boolean	removeAbandoned=true;
	int	removeAbandonedTimeout=1800;
	boolean	logAbandoned=true;
        public void init(){
	   try {
		super.init();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   public void close(){
	   super.close();
   }
   
   public myDruidDataSource(){
	   super.setUrl(url);
	   super.setDriverClassName(driverClassName);
	   super.setUsername(username);
	   super.setPassword(password);
	   try {
		super.setFilters(filters);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	super.setMaxActive(maxActive);
	super.setInitialSize(initialSize);
	super.setMaxWait(maxWait);
	super.setMinIdle(minIdle);
	super.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
	super.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
	super.setTestWhileIdle(testWhileIdle);
	super.setTestOnBorrow(testOnBorrow);
	super.setTestOnReturn(testOnReturn);
	super.setPoolPreparedStatements(poolPreparedStatements);
	super.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
	super.setRemoveAbandoned(removeAbandoned);
	super.setRemoveAbandonedTimeout(removeAbandonedTimeout);
	super.setLogAbandoned(logAbandoned);
   }
	
}
