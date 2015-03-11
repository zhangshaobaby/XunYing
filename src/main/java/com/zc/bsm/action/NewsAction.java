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
import com.zc.base.defineAnnotation.NeedInterceptor;
import com.zc.base.po.Dict;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.base.util.StringUtil;
import com.zc.bsm.pojo.News;
import com.zc.bsm.pojo.Project;
import com.zc.bsm.service.NewsService;
import com.zc.bsm.service.ProjectService;
import com.zc.bsm.service.bizDataService;

@Controller
public class NewsAction extends Action{
   //著录
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private bizDataService bizdataservice;
	//转到添加新闻页面
	@RequestMapping(value="/auth/news/toAdd")
	public ModelAndView toAddNews(Long id) throws Exception{
		ModelAndView model = new ModelAndView("news/add");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(id!=null){
			News news = newsService.get(id);
			Date end = sdf.parse(news.getEnd_time());
			end.setDate(end.getDate()-1);
			news.setEnd_time(sdf.format(end));
			model.addObject("news",news);
		}
		model.addObject("picpath",SignUtils.PIC_HOST);
		List newsType = this.bizdataservice.find("from Dict where dictType.id = 11");
		model.addObject("newsType",newsType);
		
		return model;
	}
	@RequestMapping(value="/auth/news/doAdd")
	public String doAddNews(News news){
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date end = sdf.parse(news.getEnd_time());
			end.setDate(end.getDate()+1);
			news.setEnd_time(sdf.format(end));
			this.bizdataservice.bizSave(News.class, news);
			news.setFlag(0);
			news.setState(0);
			if(news.getHref().equals(""))
				news.setHref("news/view?id="+news.getId());
			this.bizdataservice.bizSave(News.class, news);
		}catch(Exception e)
		{
			
		}
		return "redirect:/auth/news/list";
	}
	@RequestMapping(value="/news/view")
	@NeedInterceptor
	public ModelAndView views(Long id){
		News news = newsService.get(id);
		ModelAndView model = new ModelAndView("news/view");
		news.setContent(StringUtil.replaceNR(news.getContent()));
		Dict dict = (Dict)this.bizdataservice.bizfindbyid(Dict.class, news.getType());
		model.addObject("news",news);
		model.addObject("dict",dict);
		return model;
	}
	@RequestMapping(value="/auth/news/view")
	public ModelAndView view(Long id){
		News news = newsService.get(id);
		ModelAndView model = new ModelAndView("news/view");
		model.addObject("news",news);
		return model;
	}
	
	@RequestMapping(value="/auth/news/list")
	public ModelAndView list (Page page,News news){
		ModelAndView model = new ModelAndView("news/list");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(page==null)page=new Page();
		page = newsService.findPageBySelf(page,news);
		try{
			for(News newss : (List<News>)page.getResult()){
				Date end = sdf.parse(newss.getEnd_time());
				end.setDate(end.getDate()-1);
				newss.setEnd_time(sdf.format(end));
			}
		}catch(Exception e ){
			
		}
		model.addObject("page", page);
		model.addObject("picpath",SignUtils.PIC_HOST);
		return model;
	}
	@RequestMapping(value="/auth/news/stateChange")
	@ResponseBody
	public String stateChange(Long id,Integer state){
		newsService.stateChange(id,state);
		return "";
	}
	@RequestMapping(value="/news/list")
	@NeedInterceptor
	public ModelAndView preList(Page page,News news){
		ModelAndView model = new ModelAndView("news/preList");
		if(news.getType()==null)
			news.setType(65);
		news.setState(1);
		if(page==null)page=new Page();
		page = newsService.findPageBySelf(page,news);
		model.addObject("model", news);
		model.addObject("page", page);
		model.addObject("picpath",SignUtils.PIC_HOST);
		return model;
	}
	@RequestMapping(value="/auth/news/getProject")
	@ResponseBody
	public Page getProject(Project project,Page page){
		if(page==null)
			page = new Page();
		page.setRows(8);
		if(project.getType()!=null){
			return projectService.findByPage(page, project);
			
		}
		return null;
	}
}
