package com.shtz.service.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Organization;
import com.shtz.service.OrgService;
import com.shtz.service.PageService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  2012-1-5
 */
public class OrgServiceImpl extends HibernateDaoSupport implements OrgService {
	
	private PageService ps;
	
	public void setPs(PageService ps) {
		this.ps = ps;
	}

	public void addOrg(Organization org, int parentId) {

		if(parentId != 0){
			org.setParent((Organization)this.getHibernateTemplate().load(Organization.class, parentId));
		}
		System.out.println("orgservice");
		this.getHibernateTemplate().save(org);
		org.setSn(org.getParent()==null?""+org.getId():org.getParent().getSn()+"_"+org.getId());
		this.getHibernateTemplate().update(org);
	}

	public void deleteOrg(int orgId) throws Exception{
		
		Organization o = (Organization)this.getHibernateTemplate().load(Organization.class, orgId);
		
		if(o.getChildren().size()>0){
				throw new RuntimeException("有子机构");
		}
		
		this.getHibernateTemplate()
			.delete(this.getHibernateTemplate().load(Organization.class, orgId));

	}

	public Organization findOrgById(int orgId) {
		
		return (Organization)this.getHibernateTemplate().load(Organization.class, orgId);
	}

	public PageModel findOrgs(int parentId,int offset,int pagesize) {

		if(parentId == 0){
			return ps.getPageModel("from Organization og where og.parent.id is null ", offset, pagesize);
		}
		return ps.getPageModel("from Organization og where og.parent.id = ? ", parentId,offset, pagesize);
	}

	public void modifyOrg(Organization org, int parentId) {
		
		if(parentId != 0){
			org.setParent((Organization)this.getHibernateTemplate().load(Organization.class, parentId));
		}
		this.getHibernateTemplate().update(org);
		
	}

}
