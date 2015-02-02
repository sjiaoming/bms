package com.shtz.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Organization;
import com.shtz.service.OrgService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class OrgAction extends ActionSupport {
	
	private OrgService service;
	
	public void setService(OrgService service) {
		this.service = service;
	}
	
	private boolean select;
	
	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	private int parentId;
	
	private PageModel pageModel;

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
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
			System.out.println("分页异常");
		}
		int pagesize = 15;
		pageModel = service.findOrgs(parentId, offset, pagesize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		ServletActionContext.getRequest().setAttribute("parentId", parentId);
		
		int ppid = 0;
		if(parentId != 0){
			Organization og = service.findOrgById(parentId);
			if(og.getParent() != null){
				
				ppid = og.getParent().getId();
			}
		}
		ServletActionContext.getRequest().setAttribute("ppid", ppid);
		if(select){
			System.out.println("select = true");
			return "select";
		}
		return SUCCESS;
	}
	public String add() throws Exception {
		
		
		
		System.out.println("OrgAction.add method [ parentId ]= " + parentId);
		return SUCCESS;
	}
	
	
}
