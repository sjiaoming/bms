package com.shtz.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.ACL;
import com.shtz.model.Module;
import com.shtz.service.AclService;
import com.shtz.service.ModuleService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
@SuppressWarnings("serial")
public class ModuleAction extends ActionSupport {
	
	private ModuleService service;
	
	private AclService aservice;
	
	public void setAservice(AclService aservice) {
		this.aservice = aservice;
	}
	public void setService(ModuleService service) {
		this.service = service;
	}

	private int id;

	private String name;

	private String sn;

	private String url;

	private int orderNum;

	private int parentId;
	
	private PageModel pageModel;
	
	private int moduleId;
	
	private String modify_parentId;
	
	private String name1;
	
	private int moduleId1;
	
	private int snid;
	public int getSnid() {
		return snid;
	}
	public void setSnid(int snid) {
		this.snid = snid;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public int getModuleId1() {
		return moduleId1;
	}
	public void setModuleId1(int moduleId1) {
		this.moduleId1 = moduleId1;
	}
	public String getModify_parentId() {
		return modify_parentId;
	}
	public void setModify_parentId(String modifyParentId) {
		modify_parentId = modifyParentId;
	}
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public String execute() throws Exception {
		
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页 异常");
		}
		int pagesize = 15;
		
		Map params = new HashMap();
		if(moduleId!=0)
			params.put("id_id", moduleId);
		if(name!=null)
			params.put("name", name);
		pageModel = service.findAllModulesByParams(params, offset, pagesize);
		
//		if(name==null){
//			name="";
//		}
//		if(moduleId!=0 & name.equals("")){
//			pageModel = service.findModuleById(moduleId,offset, pagesize);
//		}else if(moduleId==0 & !name.equals("")){
//			pageModel = service.findModuleByName(name, offset, pagesize);
//		}else if(moduleId!=0 & !name.equals("")){
//			pageModel = service.findModuleByName(name, offset, pagesize);
//		}else{
//			pageModel = service.findAllModule( offset, pagesize);
//		}
		
		
		List<Module> modules=service.findAllModules();
		
		ServletActionContext.getRequest().setAttribute("modules", modules);	
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		ServletActionContext.getRequest().setAttribute("parentId", parentId);
		ServletActionContext.getRequest().setAttribute("params", params);
		
		int ppid = 0;
		if(parentId != 0){
			Module og = service.findModule(parentId);
			if(og.getParent() != null){
				
				ppid = og.getParent().getId();
			}
		}
		ServletActionContext.getRequest().setAttribute("ppid", ppid);
		return SUCCESS;
	}

	public String getModuleName() throws Exception {
		System.out.println(snid);
		ServletActionContext.getRequest().getSession().setAttribute("snid", snid);
		List<Module> modules=service.findAllModules();
		ServletActionContext.getRequest().setAttribute("modules", modules);	
		return "getModules_success";
	}
	public String add_module() throws Exception {
		List<Module> modules=service.findAllModules();
		ServletActionContext.getRequest().setAttribute("modules", modules);
		Module m = new Module();
		if(parentId != 0 )
			orderNum=service.findMaxOrderNumByParentId(parentId)+1;
		else
			orderNum=service.findMaxOrderNumByParentId(parentId)+10;
		System.out.println(orderNum);
		m.setName(name);
		m.setOrderNum(orderNum);
		m.setSn(sn);
		m.setUrl(url);
		service.addModule(m, parentId);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}
	public String del_module()throws Exception{
		service.deleteModule(id);
		List<Module> modules=service.findAllModules();
		ServletActionContext.getRequest().setAttribute("modules", modules);
		return "del_success";
	}
	public String modify_module_input()throws Exception{
		Module module=service.findModule(moduleId);
		List<Module> allParentModule=service.findAllParentModule();
		ServletActionContext.getRequest().setAttribute("module", module);
		ServletActionContext.getRequest().setAttribute("parentModule", allParentModule);
		return "modify_success";
	}
	public String update_module()throws Exception{
		Module module=new Module();
		module.setId(moduleId1);
		module.setName(name1);
		module.setSn(sn);
		System.out.println(url==null);
		Module oldModule=service.findModule(moduleId1);
		module.setUrl(url);
		if(modify_parentId!=null){
			module.setParent(service.findModule(Integer.parseInt(modify_parentId)));
			
			if(oldModule.getParent().getId()==Integer.parseInt(modify_parentId)){
				module.setOrderNum(oldModule.getOrderNum());
			}else{
				orderNum=service.findMaxOrderNumByParentId(Integer.parseInt(modify_parentId))+1;
				module.setOrderNum(orderNum);
			}
		}else{
			module.setOrderNum(oldModule.getOrderNum());
		}
		service.modifyModule(module);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}
	
	
}
