package com.zc.bsm.service;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.zc.base.service.BaseService;
import com.zc.base.util.Page;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.pojo.CompanyAttachment;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.pojo.User;
public interface ProjectService extends BaseService<Project, Long>{	
	public Page findByPage(Page page,Project project);
	public Page findByRepayPage(Page page,Project project);
	public void deleteAll(String ids);
	public void verify(Long id,Integer online);
	public void _verify(Long id,Integer state);
	public void changeState(Long id,Integer state);
	public Company findByCId(Long id);
	public List<CompanyAttachment> findAttByCId(Long id);
	public List<Project> findByType(Integer type,Integer length);
	public void cancelProduct(Long projectid) throws Exception;
	public void updateRepayCount(Long id);
	public boolean checkAddRepaymentPlan(Long id,String time1,String time2,Integer repayCount);
	//同接口公用方法开始
	public void view(ModelAndView modelAndView,User user,Project project);
}
