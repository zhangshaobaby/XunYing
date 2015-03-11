package com.zc.bsm.service;
import com.zc.base.service.BaseService;
import com.zc.bsm.pojo.PlatformTransfer;


public interface TransferService extends BaseService<PlatformTransfer,Long> {
	public boolean trytransfer(PlatformTransfer   transfer);
}
