package com.shtz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Organization;
import com.shtz.service.OrgService;

/**
 * @author sjm
 *  
 */
public class AddAction extends ActionSupport {
	
	private int parentId;
	
	private String name;
	
	private String description;

	private OrgService service;
	
	
	public void setService(OrgService service) {
		this.service = service;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String execute() throws Exception {
		System.out.println("AddAction [ parentId ]= " + parentId);
		Organization o = new Organization();
		o.setName(name);
		o.setDescription(description);
		
		service.addOrg(o, parentId);
		
		return "add_success";
	}
	
	
	
}
