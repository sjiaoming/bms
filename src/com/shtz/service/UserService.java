package com.shtz.service;

import java.util.List;

import com.shtz.model.User;
import com.shtz.model.UsersRoles;

/**
 * @author sjm
 *  
 */
public interface UserService {

	public void addUser(User user,int personId);
	
	public void deleteUser(int userId);
	
	public void modifyUser(User user,int personId);
	
	public User findUser(int userId);
	
	public User getUserById(int userId);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<UsersRoles> findUserRoles(int userId);
	
	public void addOrModityUserRole(int userId,int roleId,int orderNum);
	
	public void deleteUserRole(int userId,int roleId);
	
	public User login(String username,String password);
	
	public User findUserByName(String name);
	
	//public PageModel findUsers(int offset,int pagesize);
}
