package com.zc.bsm.action;


import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.util.task.ICheckLogJob;
import com.zc.bsm.pojo.PlatformTransfer;
import com.zc.bsm.pojo.UserPay;
import com.zc.bsm.service.BankCardService;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.returnVo.TransferReturn;
import com.zc.bsm.vo.returnVo.UsrAcctPayReturn;

/**
 * 专用于汇付回调接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/huifucallback")
public class huifucallbackLuYangAction {
	@Autowired
	public UserService userService;
	@Autowired
	public BankCardService bankCardService;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	public ICheckLogJob checklogjob ;
	
	// 获取最新的汇付平台流水记录
	@RequestMapping(value = "/gethuifu")
	@NeedInterceptor
	public void gethuifu(HttpServletResponse response, HttpServletRequest request) {
		  this.checklogjob.loggerToArchives();
		
	}
	// 用户支付callback
	@RequestMapping(value = "/printUsracctpayResult")
	@NeedInterceptor
	public void printUsracctpayResult(UsrAcctPayReturn usrAcctPayReturn,
			HttpServletResponse response, HttpServletRequest request) {
		boolean flag = usrAcctPayReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			return;
		}
		try {
			UserPay userPay = (UserPay) this.bizdataservice.bizfindbyid(
					UserPay.class, usrAcctPayReturn.getOrdId());
			
			// 获取结果
			String code = usrAcctPayReturn.getRespCode();
			if (code.equals("000")) {
				// 成功
				userPay.setFlag(0);
			} else {
				userPay.setFlag(1);
			}
			userPay.setOrderDesc((URLDecoder.decode(usrAcctPayReturn.getRespDesc(), "UTF-8")));
			bizdataservice.bizSave(UserPay.class, userPay);		
			String orderId = usrAcctPayReturn.getOrdId();
			PrintWriter out = response.getWriter();
			out.print("RECV_ORD_ID_".concat(orderId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// 自动扣款转账（平台专用）callback
	@RequestMapping(value = "/printTransferResult")
	@NeedInterceptor
	public void transferReturnResult(TransferReturn transferReturn,
			HttpServletResponse response, HttpServletRequest request) {
		boolean flag = transferReturn.validate();
		if (!flag) {
			System.out.println("验证签名失败...");
			return;
		}
		try {
			PlatformTransfer platformtransfer = (PlatformTransfer) this.bizdataservice.bizfindbyid(
					PlatformTransfer.class, transferReturn.getOrdId());
			// 获取结果
			String code = transferReturn.getRespCode();
			if (code.equals("000")) {
				// 成功
				platformtransfer.setFlag(0);
			} else {
				platformtransfer.setFlag(1);
			}
			bizdataservice.bizSave(PlatformTransfer.class,
					platformtransfer);
			String orderId = transferReturn.getOrdId();
			PrintWriter out = response.getWriter();
			out.print("RECV_ORD_ID_".concat(orderId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
