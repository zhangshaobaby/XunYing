package com.zc.bsm.service;

import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.Freeze;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.UsrFreezeBgReturn;
import com.zc.bsm.vo.returnVo.UsrUnFreezeReturn;

public interface ComomBizService extends BaseService<Object,String>{
	public UsrFreezeBgReturn getUsrFreezeBg(Freeze freeze)throws Exception;
	public message dobgFreeState(UsrFreezeBgReturn freezeBgReturn );
	public UsrUnFreezeReturn UsrUnFreeze(Freeze unfreeze) throws Exception;
	public message dobgUnFreeState(UsrUnFreezeReturn freezeBgReturn );
	public message dobgTenderUnFreeState(UsrUnFreezeReturn freezeBgReturn );
	public UsrUnFreezeReturn UsrTenderUnFreeze(Freeze unfreeze)throws Exception;
	public message unFreezeman(Freeze freeze);
	public message cancelTender(Tender tender);
	
}
