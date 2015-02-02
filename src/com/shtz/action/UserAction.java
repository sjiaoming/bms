package com.shtz.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Person;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.service.PersonService;
import com.shtz.service.RoleService;
import com.shtz.service.UserService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class UserAction extends ActionSupport {
	
	private UserService uservice;
	
	private PersonService pservice;
	
	private RoleService rservice;
	
	private int id;

	private String username;

	private String password;

	private Date createTime;

	private Date expireTime;
	
	private int personId;
	
	private int roleId;
	
	private int orderNum;
	
	private PageModel pageModel;
	
	private int orgId;
	
	private String name;

	private int userId;
	
	private String flag;
	
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	
	public PersonService getPservice() {
		return pservice;
	}

	public void setPservice(PersonService pservice) {
		this.pservice = pservice;
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
		if(orgId!=0)
			params.put("id_org", orgId);
		if(name!=null)
			params.put("name", name);
		
		pageModel = pservice.serchPersionByParams(params, offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		ServletActionContext.getRequest().setAttribute("params", params);
		if(!"".equals(flag)&&flag!=null&&flag.equals("auth_search")){
			return "auth_search_success";
		}else if(!"".equals(flag)&&flag!=null&&flag.equals("data_search")){
			return "data_search_success";
		}else {
		return SUCCESS;
		}
	}
	public String add_user() throws Exception {
		
		User u  =   uservice.getUserById(userId);
		boolean b = false;
		if(u==null){
			u = new User();
			b = true;
		}
		u.setUsername(username);
		u.setPassword(password);
		u.setExpireTime(expireTime);
		u.setAuth(String.valueOf(personId));
		if(!b){
			uservice.modifyUser(u, personId);
		}else{
			uservice.addUser(u, personId);
		}
		//this.execute();
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return "add_success";
	}
	public String del_user() throws Exception {
		
		uservice.deleteUser(id);
		//this.execute();
		return "del_success";
		
	}
	public String modify_myPwd() throws Exception {
		User u  = (User)ServletActionContext.getRequest().getSession().getAttribute("login");
		Person p  =   (Person)ServletActionContext.getRequest().getSession().getAttribute("person");
		User u1 = uservice.findUser(u.getId());
		u1.setPassword(password);
		uservice.modifyUser(u1, p.getId());
		ServletActionContext.getRequest().setAttribute("msg", "密码修改成功，请重新登陆");
		return LOGIN;
		
	}
	public String modify_user() throws Exception {
		User u = new User();
		u.setId(id);
		u.setUsername(username);
		u.setPassword(password);
		u.setExpireTime(expireTime);
		uservice.modifyUser(u, personId);
		
		return "modify_success";
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
			System.out.println("分页 异常");
		}
		int pageSize = 20;
		pageModel = rservice.findRoles(offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		
		ServletActionContext.getRequest().setAttribute("id", id);
		return SUCCESS;
	}
	public String add_userRole() throws Exception {
		
		uservice.addOrModityUserRole(id, roleId, orderNum);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		this.userRole();
		
		return "add_success";
	}
	public String del_userRole() throws Exception {
		uservice.deleteUserRole(id, roleId);
		ServletActionContext.getRequest().setAttribute("cflag", "delSuccess");
		this.userRole();
		
		return "del_success";
	}
	
}
