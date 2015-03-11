package com.zc.bsm.action;

import com.zc.base.action.Action;
import com.zc.base.util.FileUtils;
import com.zc.base.util.Page;
import com.zc.base.util.SignUtils;
import com.zc.bsm.pojo.Company;
import com.zc.bsm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/auth/company")
public class CompanyAction extends Action{
   //著录
	@Autowired
	private CompanyService companyService;
	
	private Company company;
	
	//转到添加页面
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(Long id,String type){
		if(id!=null){
			company = companyService.get(id);
		}			
		if(type!=null){
			company=new Company();
			company.setType(Integer.parseInt(type));
		}
		ModelAndView modelAndView = new ModelAndView("company/company_add");
		modelAndView.addObject("model", company);
		modelAndView.addObject("picpath", SignUtils.PIC_HOST);
		return modelAndView;
	}
	/**
	 * 添加
	 * @return
	 */
	@RequestMapping(value="/add")
	public String add(Company model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(model.getId()==null)
			model.setCreateTime(sdf.format(new Date()));
		model.setUpdateTime(sdf.format(new Date()));
		companyService.saveOrUpdate(model);
		return "redirect:/auth/company/xtdbList?type="+model.getType();
	}
	
	/**
	 *
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/list")
	public String list(Page ppage,Company company){
		page = companyService.findByPage(ppage, company);
		return "company/list";
	}
	@RequestMapping(value="/xtdbList")
	public String xtList(Page page,Company company,int type,ModelMap map){
		company.setType(type);
		page = companyService.findByPage(page, company);
		map.put("type", type);
		return "company/company_list";
	}
	/**
	 * 上传公司logo
	 * @param
	 * @param
	 * @return 
	 */
	@RequestMapping(value="/upload/{cid}")
	@ResponseBody
	public String upload(@PathVariable(value = "cid") Long cid,@RequestParam MultipartFile[] files,HttpServletRequest request){
		String path = "";
		for(MultipartFile f:files){
			try{
				//path += FileUtils.saveFileFromInputStream(f.getInputStream(), request.getSession().getServletContext().getRealPath("/") , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += FileUtils.saveFileFromInputStream(f.getInputStream(), SignUtils.PIC_REALPAHT , f.getOriginalFilename().substring(f.getOriginalFilename().lastIndexOf(".")));
				path += ",";
			}catch(Exception e){
				e.printStackTrace();
			}
		}
			
		return path.length()>0?path.substring(0,path.length()-1):"-1";
	}
	/**
	 * 批量删除
	 * @param ids 跳转
	 */
	@RequestMapping(value="/delete")
	public void delete(String ids,HttpServletResponse response){
		companyService.deleteAll(ids);
		try{
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print("ok");
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			
		}
	}
	public CompanyService getCompanyService() {
		return companyService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
