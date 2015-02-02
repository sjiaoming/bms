package com.shtz.service;

import java.util.Map;

import com.shtz.model.Role;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public interface RoleService {

	public void addRole(Role role);
	
	public void deleteRole(int roleId);
	
	public void modityRole(Role role);
	
	public Role findRole(int roleId);
	
	public PageModel findRoles(int offset,int pagesize);
	
	public PageModel findRolesByParams(Map params,int offset, int pageSize);
}
