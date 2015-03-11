package com.zc.bsm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.po.Dict;
import com.zc.base.util.SignUtils;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.Bankcard;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.CashStream;
import com.zc.bsm.pojo.LoansSend;
import com.zc.bsm.pojo.LuckyMoney;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.RepaymentPlan;
import com.zc.bsm.pojo.RepaymentSend;
import com.zc.bsm.pojo.Tender;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BankCardService;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.CallBackService;
import com.zc.bsm.service.CashStreamService;
import com.zc.bsm.service.DictService;
import com.zc.bsm.service.EnchashmentService;
import com.zc.bsm.service.LoansSendService;
import com.zc.bsm.service.RepaymentPlanService;
import com.zc.bsm.service.RepaymentSendService;
import com.zc.bsm.service.TenderService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;
import com.zc.bsm.vo.returnVo.CorpRegisterReturn;
import com.zc.bsm.vo.returnVo.LoansReturn;
import com.zc.bsm.vo.returnVo.RepaymentReturn;
import com.zc.bsm.vo.returnVo.UserBindCardReturn;

/**
 * 专用于汇付回调接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/huifucallback")
public class huifucallbackZLAction {
	@Autowired
	public CallBackService callBackService;
	@Autowired
	public UserService userService;
	@Autowired
	public BankCardService bankCardService;
	@Autowired
	public BorrowerService borrowerService;
	@Autowired
	public LoansSendService loansSendService;
	@Autowired
	public TenderService tenderService;
	@Autowired
	public RepaymentSendService repaymentSendService;
	@Autowired
	public RepaymentPlanService repaymentPlanService;
	@Autowired
	public CashStreamService cashStreamService;
	@Autowired
	private  bizDataService bizdataservice;
	@Autowired
	private  DictService dictservice;
	 @Autowired
   public EnchashmentService enchashmentService;
	   @Autowired
	    public  msgUtil msgutil;
		/**
		 * 企业用户开户
		 * @param _return
		 * @return
		 */
	@RequestMapping(value="/printCorpRegisterReturn")
	@ResponseBody
	@NeedInterceptor
	public String registerBg(CorpRegisterReturn cr){
		if(cr.validate()){
			callBackService.registerBg(cr);
		}
		return "RECV_ORD_ID_"+cr.getTrxId();
	}
	/**
	 * 绑卡响应
	 * @param _return
	 * @return
	 */
	@RequestMapping(value="/printUserBindCard")
	@NeedInterceptor
	public void UserBindCard(UserBindCardReturn _return,HttpServletResponse response){

		try{
			if(_return.getRespCode().equals("000")&&_return.validate()){
				User user= userService.findByHuifuId(_return.getUsrCustId());
				
				List<Object> buildBanklist =this.bizdataservice.find("from Bankcard where user.id=? and cardNumber=?",user.getId(),_return.getOpenAcctId());
				if(buildBanklist==null||buildBanklist.size()==0){
					Bankcard bc = new Bankcard();
					bc.setUser(user);
					bc.setUsrCustId(_return.getUsrCustId());
					bc.setCardNumber(_return.getOpenAcctId());
					List<Dict> banklist=this.dictservice.find("from Dict where dictid=?",_return.getOpenBankId());
					Dict bank=null;
					if(banklist.size()>0){
						bank=banklist.get(0);
					}
					bc.setBankId(_return.getOpenBankId());
					bc.setBankName(bank.getDictName());
					bc.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					bankCardService.saveOrUpdate(bc);
					//发送消息
					Map params=new HashMap<String, String>();
					params.put("bankcard", _return.getOpenAcctId());
					msgutil.sendmessage("6",new String[]{"phone","webmeg"}, user,  params);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		String trxId = _return.getTrxId();
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("RECV_ORD_ID_".concat(trxId));
		
	}
	/**
	 * 放款响应
	 * @param _return
	 */
	@RequestMapping(value="/printLoans")
	@ResponseBody
	@NeedInterceptor
	public String Loans(LoansReturn _return){
		//System.out.println(_return.getSubOrdId());
		callBackService.Loans(_return);
		return "RECV_ORD_ID_"+_return.getOrdId();
	}
	/**
	 * 还款响应
	 * @param _return
	 */
	@RequestMapping(value="/printRepayment")
	@ResponseBody
	@NeedInterceptor
	public String printRepayment(RepaymentReturn _return){
		callBackService.printRepayment(_return);
		return "RECV_ORD_ID_"+_return.getOrdId();
	}
}
