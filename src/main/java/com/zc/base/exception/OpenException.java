/**  
 * <b>项目名：</b>表单自定义系统<br/>  
 * <b>包名：</b>com.winston.exception<br/>  
 * <b>文件名：</b>OpenException.java<br/>  
 * <b>版本信息：</b><br/>  
 * <b>日期：</b>2013-10-5-下午10:21:12<br/>  
 * <b>Copyright (c)</b> 2013太极开放公司-版权所有<br/>   
 */
package com.zc.base.exception;

/**
 * <b>类名称：</b>OpenException<br/>
 * <b>类描述：</b><br/>
 * <b>创建人：</b>Winston<br/>
 * <b>修改人：</b>Winston<br/>
 * <b>修改时间：</b>2013-10-5 下午10:21:12<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class OpenException extends RuntimeException {

    static final long serialVersionUID = -7034897190745766939L;

    public OpenException(Throwable root) {
        super(root);
    }

    public OpenException(String string, Throwable root) {
        super(string, root);
    }

    public OpenException(String s) {
        super(s);
    }
}
