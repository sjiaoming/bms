package com.shtz.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Role;
import com.shtz.service.RoleService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
@SuppressWarnings("serial")
public class RoleAction extends ActionSupport {
	
	private RoleService service;
	
	public void setService(RoleService service) {
		this.service = service;
	}

	private int id;

	private String name;

	
	private PageModel pageModel;

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



	@Override
	public String execute() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页 转换异常");
		}
		int pagesize = 15;
		Map params = new HashMap();
		if(name!=null)
			params.put("name", name);
		
		//pageModel = service.findRoles(offset, pagesize);
		pageModel = service.findRolesByParams(params, offset, pagesize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		ServletActionContext.getRequest().setAttribute("params", params);
		

		return SUCCESS;
	}
	public String add_role() throws Exception {
		//System.out.println("module_add_module");
		Role role = new Role();
		role.setName(name);
		service.addRole(role);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		//this.execute();
		return "add_role_success";
	}
	public String del_role()throws Exception{
		service.deleteRole(id);
		//this.execute();
		return "del_success";
	}
	
	
	
	
	
}
