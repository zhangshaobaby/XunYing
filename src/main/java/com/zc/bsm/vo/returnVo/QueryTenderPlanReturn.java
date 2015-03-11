package com.zc.bsm.vo.returnVo;

import com.zc.base.util.TransSubmit;

/**
 * 自动投标计划状态查询
 * @author Administrator
 *
 */
public class QueryTenderPlanReturn {
	private String CmdId ;
	private String RespCode  ;
	private String RespDesc  ;
	private String MerCustId  ;
	private String UsrCustId   ;
	private String TransStat   ;
	private String checkCode = "CmdId  +  RespCode  +   MerCustId+  UsrCustId+  TransStat";

	public boolean validate() {
		return TransSubmit.validate(this, this.getClass());
	}
}
