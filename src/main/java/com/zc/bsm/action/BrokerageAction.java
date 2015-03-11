package com.zc.bsm.action;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zc.base.po.Dict;
import com.zc.base.util.msgUtil;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.User;
import com.zc.bsm.service.BrokerageService;
import com.zc.bsm.service.UserService;
import com.zc.bsm.service.bizDataService;

@Controller
@Scope("prototype")
public class BrokerageAction {
	@Autowired
	public UserService userService;

	@Autowired
	private BrokerageService brokerageService;
	@Autowired
	private bizDataService bizdataservice;
    @Autowired
    public  msgUtil msgutil;
	// 添加 根据成立的产品 查找推荐人 生成推荐人佣金
	@RequestMapping(value="/auth/brokerage/add")
	@ResponseBody
	public int add(String  projectid) {
		if(projectid==null){
			return 0 ;
		}
	    Project	project=(Project) bizdataservice.bizfindbyid(Project.class,projectid);
		if(project==null){
			return 0;
		}
		try {
			this.brokerageService.updateByproduct(project.getId(),new Dict(60).getId());
	        return 1;
		} catch (Exception e) {
			e.printStackTrace();
		 return 0;
		}
	}
	//发送消息
	public int sendmes(String  projectid){
		try {
			if(projectid==null){
				return 0 ;
			}
		    Project	project=(Project) bizdataservice.bizfindbyid(Project.class,projectid);
			if(project==null){
				return 0;
			}
			String hql = "select user.id,user.agent,sum(tender.transAmt),user.advisor,tender.id from User as user ,Tender as tender  where tender.usrCustId=user.huifuID and tender.project.id="
				+ project.getId()
				+ " and tender.flag = 0 and tender.state = 1 GROUP BY tender.usrCustId" ;
		    List<Object> list=this.brokerageService.findByHql(hql);
			for(Object obj:list){
		        	//转化为集合  取出user 和 投资总额
		        	Object[] sublist= (Object[]) obj;
		            String  userid=sublist[0].toString();
		            String  transAmt=sublist[2].toString();
                    Map<String,String> params=new HashMap<String, String>();
		            params.put("money", transAmt);
		            params.put("proname", project.getName());
		            params.put("pstartdate", project.getPstartTime());
		            params.put("penddate", project.getPendTime());
		            User user=userService.get(userid);
		            msgutil.sendmessage("12",new String[]{"phone","webmeg"}, user,  params);  
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	// 删除
	public void del() {

	}
	/**
	 * 撤销产品引发撤销佣金奖励
	 * @param projectid
	 */
	@RequestMapping(value="/auth/brokerage/update")
	@ResponseBody
	public String update(String id) {
		//Brokerage 更改状态为产品购买失败
		String hql = "update Brokerage set state.id = "+69+" where project.id = "+id;
		this.bizdataservice.bulkUpdate(hql);
		return "ok";
	}
}
