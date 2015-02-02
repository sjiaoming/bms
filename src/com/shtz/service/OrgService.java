package com.shtz.service;

import com.shtz.model.Organization;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  2012-1-5
 */
public interface OrgService {
	
	public void addOrg(Organization org,int parentId);
	
	public void deleteOrg(int orgId) throws Exception;
	
	public void modifyOrg(Organization org,int parentId);
	
	public Organization findOrgById(int orgId);
	
	public PageModel findOrgs(int parentId,int offset,int pagesize);
	
}
