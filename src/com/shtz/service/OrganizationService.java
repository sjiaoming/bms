package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.Organization;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public interface OrganizationService {
	
	public void deleteOrganization(int organizationId);
	
	public void modifyOrganization(Organization organization);
	
	public Organization findOrganization(int organizationId);
	
	public List<Organization> findAllOrganization();
	
	public List<Organization> findLevel1Organization();
	
	//public List<Organization> findLevel2Organization(int organizationId);
	
	public Map<String,String> findLevel2Organization(int organizationId);
	
	public List<?> findLevel4Organization(int organizationId);
	
	public PageModel findOrganizationByParams(Map params,int offset,int pagesize);
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<UsersRoles> findUserRoles(int userId);
	
	public void addOrModityUserRole(int userId,int roleId,int orderNum);
	
	public void deleteUserRole(int userId,int roleId);
	
	//public User login(String username,String password);
	
	//public PageModel findUsers(int offset,int pagesize);
	
	public void addOrg(Organization org,int parentId);
	
	public void deleteOrg(int orgId) throws Exception;
	
	public void modifyOrg(Organization org);
	
	public Organization findOrgById(int orgId);
	
	public PageModel findOrgs(int parentId,int offset,int pagesize);
	
	public PageModel findOrganizationByName(String name,int offset,int pagesize);
	
	public void addNewOrg(Organization org);
}
