package com.zc.bsm.action;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zc.base.action.Action;
import com.zc.base.util.Page;
import com.zc.base.util.StringUtil;
import com.zc.bsm.pojo.DynamicRate;
import com.zc.bsm.service.DynamicRateService;

@Controller
@RequestMapping(value="/auth/dynamicRate")
public class DynamicRateAction extends Action{
	@Autowired
	private DynamicRateService dynamicRateService;
	private DynamicRate dynamicRate;
	@RequestMapping(value="toAdd")
	public ModelAndView toAdd(Long id){
		ModelAndView model = new ModelAndView("dynamicRate/add");
		if(id!=null&&id.intValue()!=0)
			model.addObject("model",dynamicRateService.get(id));
		return model;
	}
	@RequestMapping(value="add")
	public String add(DynamicRate dynamicRate){
		if(dynamicRate.getId()==null){
			dynamicRate.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		}
		dynamicRate.setDay_rate(dynamicRate.getDay_rate().divide(new BigDecimal(100)));
		dynamicRate.setFlag(0);
		dynamicRate.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		dynamicRateService.saveOrUpdate(dynamicRate);
		return "redirect:/auth/dynamicRate/list";
	}
	@RequestMapping(value="list")
	public ModelAndView list(Page page,DynamicRate dynamicRate){
		ModelAndView model = new ModelAndView("dynamicRate/list");
		page = dynamicRateService.findByPage(page,null,null);
		for(int i = 0; i < page.getResult().size() ; i++){
			((List)page.getResult().get(i)).set(1, StringUtil.BigDecimal2StringSmall(new BigDecimal(((List)page.getResult().get(i)).get(1).toString()).multiply(new BigDecimal(100)),8)); 
		}
		model.addObject("page",page);
		return model;	
	}
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(Long id){
		dynamicRate = dynamicRateService.get(id);
		dynamicRate.setFlag(1);
		dynamicRateService.saveOrUpdate(dynamicRate);
		return null;
	}
}
