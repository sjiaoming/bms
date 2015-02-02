package com.shtz.service.impl;

import com.shtz.service.LoginService;

public class LoginServiceImpl implements LoginService {

	public boolean isLogin(String name, String password) {
		// TODO Auto-generated method stub
		
		if("peter".equals(name)&&"323".equals(password)){
			return true;
		}
		
		return false;
	}

}
