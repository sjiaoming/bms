package com.shtz.action;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.service.OrgService;

/**
 * @author sjm
 *  
 */
public class DelAction extends ActionSupport {
	
	private int id;

	private OrgService service;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setService(OrgService service) {
		this.service = service;
	}


	@Override
	public String execute() throws Exception {
		//System.out.println("DelAction [ Id ]= " + id);
		//Organization o = new Organization();
		//o.setId(id);

		service.deleteOrg(id);

		return "del_success";
	}
	
	
	
}
