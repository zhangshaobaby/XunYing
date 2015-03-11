package com.zc.bsm.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zc.base.service.BaseServiceImpl;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Enchashment;
import com.zc.bsm.pojo.PlatformTransfer;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.TransferService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.QueryBalanceBg;
import com.zc.bsm.vo.Transfer;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;

@Service("transferService")
public class TransferServiceImpl extends BaseServiceImpl<PlatformTransfer, Long> implements TransferService {
	@Autowired
	private bizDataService bizdataservice;
	/**
	 * 试图自动扣款转账（平台专用）
	 */
	public boolean trytransfer(PlatformTransfer   platformtransfer){
		boolean isok=false;
		try {
		    platformtransfer = (PlatformTransfer) bizdataservice.bizSave(
				PlatformTransfer.class, platformtransfer);	
			Transfer transfer = new Transfer();
			transfer.setBgRetUrl(SignUtils.PUBLIC_HOST
					+ "huifucallback/printTransferResult");
			transfer.setOrdId(platformtransfer.getId().toString());
			transfer.setOutCustId(platformtransfer.getOutCustId());
			transfer.setOutAcctId(platformtransfer.getOutAcctId());
			transfer.setInCustId(platformtransfer.getInCustId());
			if (platformtransfer.getInAcctId() != null)
				transfer.setInAcctId(platformtransfer.getInAcctId());
			transfer.setTransAmt(StringUtil.BigDecimal2String(platformtransfer
					.getTransAmt()));
			// 发出扣款请求
			String result = TransSubmit.doPost(transfer.getParam());
			if (result != null
					&& JSON.parseObject(result).getString("RespCode")
							.equals("000")) {
				platformtransfer.setFlag(0);
			} else {
				// 接受发送响应信息
				platformtransfer.setFlag(1);
				platformtransfer.setErrorCode(JSON.parseObject(result)
						.getString("RespCode").toString());
			}
			isok=true;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			platformtransfer.setErrorCode("-1");// 网络不通
		} catch (Exception e) {
			e.printStackTrace();
			platformtransfer.setErrorCode("-2");// 其他程序错误
		} finally {
			try {
				bizdataservice.bizSave(PlatformTransfer.class,
						platformtransfer);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return isok;
	}
	
}
