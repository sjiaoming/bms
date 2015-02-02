package com.shtz.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContextFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Organization;
import com.shtz.model.Person;
import com.shtz.model.User;
import com.shtz.service.OrganizationService;
import com.shtz.service.SuppliersService;
import com.shtz.service.UserCompService;
import com.shtz.service.UserService;

public class LoginAction extends ActionSupport {
	
	private static final String String = null;

	private int id;
	
	private String username;
	
	private String password;
	
	public UserService service;
	
	private OrganizationService oservice;
	
	private SuppliersService suservice;
	
	private UserCompService ucservice;

	public void setOservice(OrganizationService oservice) {
		this.oservice = oservice;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SuppliersService getSuservice() {
		return suservice;
	}

	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}

	public UserCompService getUcservice() {
		return ucservice;
	}

	public void setUcservice(UserCompService ucservice) {
		this.ucservice = ucservice;
	}

	public UserService getService() {
		return service;
	}

	public OrganizationService getOservice() {
		return oservice;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		User u =  service.login(username, password);
		if(u==null){
			ServletActionContext.getRequest().getSession().setAttribute("msg","用户名或密码错误，请重试");
			return LOGIN;
		}
		Person p = u.getPerson();
		p.getOrg().getName();
		List<Organization>  orgns= oservice.findLevel1Organization();
		List slist = suservice.getSupplierslist();
		List ul = ucservice.getUseCompList();
		ServletActionContext.getRequest().getSession().setAttribute("pname",p.getName());
		ServletActionContext.getRequest().getSession().setAttribute("pid",p.getId());
		ServletActionContext.getRequest().getSession().setAttribute("uid",u.getId());
		ServletActionContext.getRequest().getSession().setAttribute("login",u);
		ServletActionContext.getRequest().getSession().setAttribute("person",p);
		ServletActionContext.getRequest().getSession().setAttribute("sessionOrgs", orgns);
		ServletActionContext.getRequest().getSession().setAttribute("sessionUseComps",ul);
		ServletActionContext.getRequest().getSession().setAttribute("sessionSuppliers",slist);
		System.out.println("UserID="+u.getId()+"/PersonID="+p.getId()+"/OrgSize="+orgns.size()+"/SupplierSize="+slist.size()+"/UseCompSize="+ul.size());
		return SUCCESS;
	}
	public String getLoginName(){
		org.directwebremoting.WebContext web= WebContextFactory.get();
		javax.servlet.http.HttpServletRequest request=web.getHttpServletRequest();
		String uname = (String)request.getSession().getAttribute("uname");
		return uname;
	}
	public List<?> getLoginPid(){
		org.directwebremoting.WebContext web= WebContextFactory.get();
		javax.servlet.http.HttpServletRequest request=web.getHttpServletRequest();
		Integer pid = (Integer)request.getSession().getAttribute("pid");
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		String pname = (String)request.getSession().getAttribute("pname");
		List l = new ArrayList();
		l.add(pid);
		l.add(pname);
		l.add(uid);
		System.out.println("====PID:==="+pid+"===PNAME:=="+pname+"===UID:=="+uid);
		return l;
	}
	public List<?> getLoginParam(){
		org.directwebremoting.WebContext web= WebContextFactory.get();
		javax.servlet.http.HttpServletRequest request=web.getHttpServletRequest();
		Integer pid = (Integer)request.getSession().getAttribute("pid");
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		String pname = (String)request.getSession().getAttribute("pname");
		List l = new ArrayList();
		l.add(pid);
		l.add(pname);
		l.add(uid);
		System.out.println("====PID:==="+pid+"===PNAME:=="+pname+"===UID:=="+uid);
		return l;
	}
}
