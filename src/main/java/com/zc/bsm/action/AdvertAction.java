package com.zc.bsm.action;

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
import com.zc.base.util.SignUtils;
import com.zc.bsm.pojo.Advert;
import com.zc.bsm.service.AdvertService;

@Controller
public class AdvertAction extends Action{
   //著录
	@Autowired
	private AdvertService advertService;
	
	private Advert advert;
	
	//转到添加页面
	@RequestMapping(value="/auth/advert/toAdd")
	public ModelAndView toAdd(Long id){
		advert = null;
		if(id!=null&&id!=0)
			advert = advertService.get(id);
		ModelAndView model = new ModelAndView("advert/add");
		model.addObject("advert", advert);
		model.addObject("picpath",SignUtils.PIC_HOST);
		return model;
	}
	/**
	 * 添加
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/auth/advert/add")
	public String add(Advert advert){
		if(advert.getId()==null||advert.getId()==0)
			advert.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		advert.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		advert.setFlag("0");
		advert.setState(1);
		advertService.saveOrUpdate(advert);
		return "redirect:/auth/advert/list";
	}
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping(value="/auth/advert/list")
	public ModelAndView list(Page page,Advert advert){
		page = advertService.findByPage(page, advert);
		ModelAndView model = new ModelAndView("advert/list");
		model.addObject("page", page);
		model.addObject("picpath",SignUtils.PIC_HOST);
		return model;
	}
	@RequestMapping(value="/auth/advert/stateChange")
	@ResponseBody
	public String preview(Long id,Integer state){
		if(id!=null&&id!=0&&state!=null){
			advertService.changeState(id, state);
			return "1";
		}
		return "-1";
	}
	
	
	public Advert getAdvert() {
		return advert;
	}
	public void setAdvert(Advert advert) {
		this.advert = advert;
	}
}
