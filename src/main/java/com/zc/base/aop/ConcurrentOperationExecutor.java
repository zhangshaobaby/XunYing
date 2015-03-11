/**  
 * <b>项目名：</b>表单自定义系统<br/>  
 * <b>包名：</b>com.winston.base.aop<br/>  
 * <b>文件名：</b>ConcurrentOperationExecutor.java<br/>  
 * <b>版本信息：</b><br/>  
 * <b>日期：</b>2013-10-16-下午11:46:46<br/>  
 * <b>Copyright (c)</b> 2013太极开放公司-版权所有<br/>   
 */
package com.zc.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.Ordered;
import org.springframework.dao.PessimisticLockingFailureException;

/**
 * <b>类名称：</b>ConcurrentOperationExecutor<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Winston<br/>
 * <b>修改人：</b>Winston<br/>
 * <b>修改时间：</b>2013-10-16 下午11:46:46<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class ConcurrentOperationExecutor implements Ordered {

    private static final int DEFAULT_MAX_RETRIES = 2;

    private int maxRetries = DEFAULT_MAX_RETRIES;
    private int order = 1;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        int numAttempts = 0;
        PessimisticLockingFailureException lockFailureException;
        do {
            numAttempts++;
            try {
                return pjp.proceed();
            } catch (PessimisticLockingFailureException ex) {
                lockFailureException = ex;
            }
        } while (numAttempts <= this.maxRetries);
        throw lockFailureException;
    }

}
