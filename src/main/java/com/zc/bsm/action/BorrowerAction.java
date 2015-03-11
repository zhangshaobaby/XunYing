package com.zc.bsm.action;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zc.base.action.Action;
import com.zc.base.util.Page;
import com.zc.base.util.TransSubmit;
import com.zc.bsm.pojo.Borrower;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BorrowerService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.vo.CorpRegisterQuery;
import com.zc.bsm.vo.returnVo.CorpRegisterQueryReturn;

@Controller
@Scope("prototype")
public class BorrowerAction extends Action{
   //著录
	@Autowired
	private BorrowerService borrowerService;
	@Autowired
	private UserService userService;
	
	private Borrower borrower;
	
	//转到添加页面
	@RequestMapping(value="/auth/borrower/toAddPerson")
	public String toAddPerson(){
		
		return "borrower/addPerson";
	}
	//删除
	@RequestMapping(value="/auth/borrower/deletePerson")
	@ResponseBody
	public void deletePerson(String uid){
		borrowerService.deleteByUid(uid);
	}
	//转到添加页面
	@RequestMapping(value="/auth/borrower/search")
	@ResponseBody
	public List search(Integer type){
		List list = borrowerService.findByType(type);
		return list;
	}
	//转到添加页面
	@RequestMapping(value="/auth/borrower/addPerson")
	public ModelAndView addPerson(String name){
		ModelAndView model = new ModelAndView("borrower/list");
		User user = userService.findByName(name);
		if(user!=null){
			borrower = new Borrower();
			borrower.setFlag("0");
			borrower.setType(1);
			borrower.setUid(user.getId());
			borrower.setName(name);
			borrower.setUsrCustId(user.getHuifuID());
			borrowerService.saveOrUpdate(borrower);
		}
		return model;
	}
	//转到添加页面
	@RequestMapping(value="/auth/borrower/toAdd")
	public ModelAndView toAdd(Long id){
		borrower = null;
		if(id!=null&&id!=0)
			borrower = borrowerService.get(id);
		ModelAndView model = new ModelAndView("borrower/add");
		model.addObject("borrower", borrower);
		return model;
	}
	/**
	 * 添加
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/borrower/add")
	public String add(Borrower borrower){
		if(borrower.getId()==null||borrower.getId()==0){
			borrower.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		borrower.setType(0);
		borrower.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		borrower.setFlag("0");
		borrowerService.saveOrUpdate(borrower);
		return "redirect:/auth/borrower/list";
	}
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value="/auth/borrower/list")
	public ModelAndView list(Page page,Borrower borrower){
		borrower.setType(0);
		page = borrowerService.findByPage(page, borrower);
		ModelAndView model = new ModelAndView("borrower/list");
		model.addObject("page", page);
		return model;
	}
	@RequestMapping(value="/auth/borrower/stateChange")
	@ResponseBody
	public String preview(Long id,Integer state){
		if(id!=null&&id!=0&&state!=null){
			borrowerService.changeState(id, state);
			return "1";
		}
		return "-1";
	}
	@RequestMapping(value="/auth/borrower/deleteborrower")
	@ResponseBody
	public String preview(Long id){
		if(id!=null&&id!=0){
			Borrower b=new Borrower();
			b.setId(id);
			borrowerService.delete(b);
			return "1";
		}
		return "-1";
	}
	@RequestMapping(value="/auth/borrower/waitHuankuanApplaylist")
	public String huankuanExamine(){
	return "manager/huankuanExamine";
	}
	@RequestMapping(value="/auth/borrower/updateFromHuifu")
	@ResponseBody
	public String updateFromHuifu(Long id){
		try{
			if(id!=null&&id!=0)
				borrower = borrowerService.get(id);
			CorpRegisterQuery crq = new CorpRegisterQuery();
			crq.setBusiCode(borrower.getBusiCode());
			Map<String, String> params = crq.getParam();
	    	String jsonString = TransSubmit.doPost(params);
			Gson gson = new Gson();
			CorpRegisterQueryReturn _return = gson.fromJson(jsonString,CorpRegisterQueryReturn.class );
			if(_return.getRespCode().equals("000")){
				//更新borrower状态
			}
			return URLDecoder.decode(_return.getRespDesc(),"utf-8");
		}catch(Exception e){e.printStackTrace();}
		return "ok";
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
}
