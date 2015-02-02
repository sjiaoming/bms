package com.shtz.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.User;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport {
	
	@Override
	public String execute() throws Exception {
		//添加权限认证代码
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("login");
		if(user == null) {
			return "login";
		}
		
		return super.execute();
	}
	
}
