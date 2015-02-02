package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.Suppliers;
import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public interface SuppliersService {
	public void setPservice(PageService pservice);
	public PageModel searchSuppliers(int offset, int pagesize);
	public PageModel searchSuppliersByName(String name,int offset, int pagesize);
	public void addSuppliers(Suppliers su);
	public void deleteSuppliers(int suppliersId);
	public Suppliers findSuppliers(int suppliersId);
	public void modifySuppliers(Suppliers su, int suppliersId);
	public List getSupplierslist();
	public PageModel serchSuppliersByParams(Map params,int offset, int pageSize);
	
	public List<Suppliers> findSuppliersByIds(String suppliersIds);
	
	public List<Suppliers> findSuppliersByNames(String supplierName);
	
	public void modifyUser(User user,int personId);
	
	public User findUser(int userId);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<UsersRoles> findUserRoles(int userId);
	
	public void addOrModityUserRole(int userId,int roleId,int orderNum);
	
	public void deleteUserRole(int userId,int roleId);
	
	public User login(String username,String password);
	
	//public PageModel findUsers(int offset,int pagesize);
}
