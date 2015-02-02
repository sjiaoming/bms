package com.shtz.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.service.RoleService;
import com.shtz.service.UserCompService;
import com.shtz.service.UserService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class UserCompAction extends ActionSupport {
	
	private UserService uservice;
	
	
	private RoleService rservice;
	
	private UserCompService ucservice;
	
	private int id;

	private String username;

	private String password;

	private Date createTime;

	private Date expireTime;
	
	private int personId;
	
	private int roleId;
	
	private int orderNum;
	
	private PageModel pageModel;
	
	private String userCompName;
	
	private String userCompRemark;
	private int useCompId;
	private int snid;
	public int getSnid() {
		return snid;
	}

	public void setSnid(int snid) {
		this.snid = snid;
	}

	public int getUseCompId() {
		return useCompId;
	}

	public void setUseCompId(int useCompId) {
		this.useCompId = useCompId;
	}

	public String getUserCompName() {
		return userCompName;
	}

	public void setUserCompName(String userCompName) {
		this.userCompName = userCompName;
	}

	public String getUserCompRemark() {
		return userCompRemark;
	}

	public void setUserCompRemark(String userCompRemark) {
		this.userCompRemark = userCompRemark;
	}

	public void setUcservice(UserCompService ucservice) {
		this.ucservice = ucservice;
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
		if(userCompName!=null&&userCompName.trim()!="")
			params.put("name", userCompName);
		
		pageModel = ucservice.serchUserCompByParams(params, offset, pageSize);
		
		//pageModel = ucservice.searchUserComps(offset, pageSize);
		ServletActionContext.getRequest().setAttribute("params", params);
		ServletActionContext.getRequest().setAttribute("pm", pageModel);

		return SUCCESS;
	}
	public String initusecomp() throws Exception {
		System.out.println(snid);
		ServletActionContext.getRequest().getSession().setAttribute("snid", snid);
		return "initusecomp_success";
		
	}
	public String add_usercomp() throws Exception {
		UseComp uc  = new UseComp();
		uc.setName(userCompName);
		uc.setRemark(userCompRemark);	
		ucservice.addUserComp(uc);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}
	public String del_useComp() throws Exception {
		
		ucservice.deleteUseComp(useCompId);
		this.execute();
		return "del_success";
		
	}
	public String modify_userComp_input() throws Exception {
		
		UseComp uc=ucservice.findUseComp(useCompId);
		ServletActionContext.getRequest().setAttribute("uc", uc);
		return "modify_input_success";
	}
	public String update_usecomp() throws Exception {
		UseComp uc=new UseComp();
		uc.setId(useCompId);
		uc.setName(userCompName);
		uc.setRemark(userCompRemark);
		ucservice.modifyUseComp(uc, useCompId);
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
