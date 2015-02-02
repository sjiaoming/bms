package com.shtz.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Suppliers;
import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.service.RoleService;
import com.shtz.service.SuppliersService;
import com.shtz.service.UserService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class SuppliersAction extends ActionSupport {
	
	private UserService uservice;
	
	
	private RoleService rservice;
	
	private SuppliersService suservice;
	
	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}
	private int id;

	private String username;

	private String password;

	private Date createTime;

	private Date expireTime;
	
	private int personId;
	
	private int roleId;
	
	private int orderNum;
	
	
	
	
	private PageModel pageModel;
	
	private String suppliersName;
	
	private String suppliersRemark;
	private int suppliersId;
	private int snid;
	public int getSnid() {
		return snid;
	}

	public void setSnid(int snid) {
		this.snid = snid;
	}

	public String getSuppliersName() {
		return suppliersName;
	}

	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public String getSuppliersRemark() {
		return suppliersRemark;
	}

	public void setSuppliersRemark(String suppliersRemark) {
		this.suppliersRemark = suppliersRemark;
	}

	public int getSuppliersId() {
		return suppliersId;
	}

	public void setSuppliersId(int suppliersId) {
		this.suppliersId = suppliersId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}

	public void setRservice(RoleService rservice) {
		this.rservice = rservice;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String execute() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 15;
		Map params = new HashMap();
		if(suppliersName!=null&&suppliersName.trim()!="")
			params.put("name", suppliersName);
		
		pageModel = suservice.serchSuppliersByParams(params, offset, pageSize);
		ServletActionContext.getRequest().setAttribute("params", params);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);

		return SUCCESS;
	}
	
	public String initsuppliers() throws Exception {
		System.out.println(snid);
		ServletActionContext.getRequest().getSession().setAttribute("snid", snid);
		return "initsuppliers_success";
	}
	public String add_suppliers() throws Exception {
		Suppliers su  = new Suppliers();
		su.setName(suppliersName);
		su.setRemark(suppliersRemark);	
		suservice.addSuppliers(su);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}
	public String del_suppliers() throws Exception {
		suservice.deleteSuppliers(suppliersId);
		this.execute();
		return "del_success";
	}
	public String modify_suppliers_input() throws Exception {	
		Suppliers su=suservice.findSuppliers(suppliersId);
		ServletActionContext.getRequest().setAttribute("su", su);
		return "modify_input_success";
	}
	public String update_suppliers() throws Exception {
		Suppliers su=new Suppliers();
		su.setId(suppliersId);
		su.setName(suppliersName);
		su.setRemark(suppliersRemark);
		suservice.modifySuppliers(su,suppliersId);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}
		
	
	
	
	
	public String userRole() throws Exception {
		
		User user = uservice.findUser(id);
		
		List<UsersRoles> urs = uservice.findUserRoles(id);
		
		ServletActionContext.getRequest().setAttribute("user", user);
		
		ServletActionContext.getRequest().setAttribute("urs", urs);
		
		return SUCCESS;
		
	}
	public String toUserRole() throws Exception {
		
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("��ҳ offset ����ת���쳣");
		}
		int pageSize = 5;
		pageModel = rservice.findRoles(offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		
		ServletActionContext.getRequest().setAttribute("id", id);
		return SUCCESS;
	}
	public String add_userRole() throws Exception {
		
		uservice.addOrModityUserRole(id, roleId, orderNum);
		
		return "add_success";
	}
	public String del_userRole() throws Exception {
		System.out.println("--------------------");
		uservice.deleteUserRole(id, roleId);
		
		return "del_success";
	}
	
}
