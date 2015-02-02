package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public interface UserCompService {
	public PageModel searchUserComps(int offset, int pagesize);
	public PageModel searchUserCompByName(String name,int offset, int pagesize);
	public void addUserComp(UseComp uc);
	public void deleteUseComp(int useCompId);
	public UseComp findUseComp(int useCompId);
	public void modifyUseComp(UseComp uc, int UseCompId);
	public List getUseCompList();
	public PageModel serchUserCompByParams(Map params,int offset, int pageSize);
	public UseComp findUseCompByName(String useCompName);
	public void modifyUser(User user,int personId);
	public User findUser(int userId);
	public List<UsersRoles> findUserRoles(int userId);
	
	public void addOrModityUserRole(int userId,int roleId,int orderNum);
	
	public void deleteUserRole(int userId,int roleId);
	
	public User login(String username,String password);
	
	//public PageModel findUsers(int offset,int pagesize);
}
