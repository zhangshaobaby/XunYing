package com.zc.bsm.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.zc.base.action.Action;
import com.zc.base.po.Dict;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.PlatformTransfer;
import com.zc.bsm.pojo.RechargeOrder;
import com.zc.bsm.pojo.User;
import com.zc.bsm.pojo.UserPay;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.TransferService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.BorrowerDetail;
import com.zc.bsm.vo.CorpRegister;
import com.zc.bsm.vo.InitiativeTender;
import com.zc.bsm.vo.NetSave;
import com.zc.bsm.vo.Transfer;
import com.zc.bsm.vo.UserBindCard;
import com.zc.bsm.vo.UserRegister;
import com.zc.bsm.vo.UsrAcctPay;
import com.zc.bsm.vo.message;
import com.zc.bsm.vo.returnVo.QueryBalanceBgReturn;

/**
 * 汇付的接口
 * 
 * @author Administrator
 * 
 */
@Controller
public class huifucallLuyangAction extends Action {
	@Autowired
	public UserService userService;
	@Autowired
	private bizDataService bizdataservice;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	public TransferService transferService;
	// 用户支付
	//view/enterprise/acctpay.jsp
	@RequestMapping(value = "/huifu/usracctpay")
	public void usracctpay(ModelMap map, UserPay userPay, 
			HttpServletResponse response) {
		try {
				userPay.setInAcctId("MDT000001");// SDT000001
				userPay.setInAcctType("MERDT");		//SPEDT
			
			userPay = (UserPay) bizdataservice.bizSave(UserPay.class, userPay);
			UsrAcctPay usracctpay = new UsrAcctPay();
			usracctpay.setBgRetUrl(SignUtils.PUBLIC_HOST
					+ "huifucallback/printUsracctpayResult");
			usracctpay.setRetUrl(SignUtils.PUBLIC_HOST + "huifucallback/success");
			usracctpay.setUsrCustId(userPay.getUsrCustId());
			usracctpay.setOrdId(userPay.getId().toString());
			usracctpay.setTransAmt(StringUtil.BigDecimal2String(userPay
					.getTransAmt()));
			usracctpay.setInAcctId(userPay.getInAcctId());
			usracctpay.setInAcctType(userPay.getInAcctType());
			String url = usracctpay.getParam();
			map.put("forwardurl", url);

			try {
				response.sendRedirect(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			message me = new message();
			me.setMsgString("用户支付出错,请重试");
			try {
				response.getWriter().print("用户支付出错,请重试");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// 自动扣款转账（平台专用） 
	//测试
	//view/enterprise/testtransfer.jsp
//	@RequestMapping(value = "/huifu/transfer")
//	public void transfer(ModelMap map, PlatformTransfer platformtransfer,
//		 HttpServletResponse response) {
//		try {
//			platformtransfer.setOutCustId(SignUtils.MER_CUST_ID);
//			platformtransfer.setOutAcctId(SignUtils.MER_CUST_ACCT_ID);
//			boolean qbr=transferService.trytransfer(platformtransfer);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
