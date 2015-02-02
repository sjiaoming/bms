package com.shtz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.shtz.model.Organization;
import com.shtz.model.Role;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.service.OrganizationService;
import com.shtz.service.PageService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class OrganizationServiceImpl extends HibernateDaoSupport implements OrganizationService {
	private PageService ps;
	
	public void setPs(PageService ps) {
		this.ps = ps;
	}
	public void addOrModityUserRole(int userId, int roleId, int orderNum) {
		UsersRoles ur = this.getUserRole(userId, roleId);
		if(ur == null){
			ur  = new UsersRoles();
			ur.setOrderNum(orderNum);
			ur.setUser((User)this.getHibernateTemplate().load(User.class, userId));
			ur.setRole((Role)this.getHibernateTemplate().load(Role.class, roleId));
			this.getHibernateTemplate().save(ur);
			return;
		}
		ur.setOrderNum(orderNum);
		this.getHibernateTemplate().update(ur);
	}



	public void deleteUserRole(int userId, int roleId) {
		UsersRoles ur = this.getUserRole(userId, roleId);
		this.getHibernateTemplate().delete(ur);
	}

	

	public List<UsersRoles> findUserRoles(int userId) {
		
		return  this.getHibernateTemplate().find("select ur from UsersRoles ur where ur.user.id = ?",userId);
	}
//
//	public User login(String username, String password) {
//		User u = (User)this.getSession().createQuery("select u from User u where u.username = ?")
//					.setParameter(0, username).uniqueResult();
//		if(u == null)
//			throw new RuntimeException("���û�������");
//		
//		if(!password.equals(u.getPassword()))
//			throw new RuntimeException("���벻��ȷ");
//		/*if(u.getExpireTime() != null) {
//			Calendar now = Calendar.getInstance();
//			Calendar expireTime = Calendar.getInstance();
//			expireTime.setTime(u.getExpireTime());
//			if(now.after(expireTime))
//				throw new RuntimeException("���û���ʧЧ");
//		}*/
//		return u;
//	}

	
	private UsersRoles getUserRole(int userId, int roleId) {
		return (UsersRoles)this.getSession().createQuery("select ur from UsersRoles ur where ur.user.id = ? and ur.role.id = ?")
			.setParameter(0, userId)
			.setParameter(1, roleId)
			.uniqueResult();
		
	}
	
	
	public void deleteOrganization(int organizationId) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Organization.class, organizationId));
	}
	public void modifyOrganization(Organization organization) {
		this.getHibernateTemplate().update(organization);
	}
	public Organization findOrganization(int organizationId) {
		String hql="from Organization o where o.id=?";
		return (Organization) this.getSession().createQuery(hql).setParameter(0, organizationId).uniqueResult();
	}
	public List<Organization> findAllOrganization() {
		return (List<Organization>)this.getHibernateTemplate().loadAll(Organization.class);
	}
	
	public List<Organization> findLevel1Organization() {
		String hql="from Organization o where o.parent is null";
		List<Organization> organizations=this.getSession().createQuery(hql).list();
		return organizations;
	}

/*	public List<Organization> findLevel2Organization(int organizationId) {
		String hql="from Organization o where o.parent.id=?";
		List<Organization> organizations=this.getSession().createQuery(hql).setParameter(0, organizationId).list();
		return organizations;
	}*/
	public Map<String,String> findLevel2Organization(int organizationId) {
		Map<String,String> m=new HashMap<String,String>();
		m.put("0", "--二级部门--");
		if(organizationId!=0){
			Organization o = (Organization)this.getHibernateTemplate().load(Organization.class, organizationId);
//			List<Organization> o=this.getSession().createQuery("from Organization o where o.parent=?").setParameter(0, organizationId).list();
//			for (int i = 0; i < o.size(); i++) {
//				Organization organization=o.get(i);
//			}
			Set<Organization> orgnSet =  o.getChildren();
			Iterator<Organization> it = orgnSet.iterator();
			while(it.hasNext()){
				Organization organization = it.next();
				m.put(organization.getId()+"", organization.getName());
			}
		}
	return m;
	}
	public List<?> findLevel4Organization(int organizationId) {
			String sql ="select id,name from t_organization  where pid="+organizationId;
			return this.getSession().createSQLQuery(sql).list();
		}
	
	public void addOrg(Organization org, int parentId) {
		if(parentId != 0){
			org.setParent((Organization)this.getHibernateTemplate().load(Organization.class, parentId));
		}
		this.getHibernateTemplate().save(org);
		org = (Organization)this.getHibernateTemplate().load(Organization.class, org.getId());
		org.setSn(org.getParent()==null?""+org.getId():org.getParent().getSn()+"_"+org.getId());
		this.getHibernateTemplate().update(org);
	}
	public void addNewOrg(Organization org) {
		this.getHibernateTemplate().save(org);
	}

	public void deleteOrg(int orgId) throws Exception{
		
		Organization o = (Organization)this.getHibernateTemplate().load(Organization.class, orgId);
		
		if(o.getChildren().size()>0){
				throw new RuntimeException("有子部门");
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
		return ps.getPageModel("from Organization o where o.id="+parentId+" or o.parent.id="+parentId+" order by o.parent.id",offset, pagesize);
	}
	public PageModel findOrganizationByName(String name,int offset,int pagesize) {
		String hql="from Organization o where o.name like '%"+name+"%'";
		return ps.getPageModel(hql,offset, pagesize);
	}
	public void modifyOrg(Organization org) {
		this.getHibernateTemplate().update(org);
	}
	public PageModel findOrganizationByParams(Map params,int offset,int pagesize) {
		
		String hql = "from Organization p where 1=1 ";
		if(!params.isEmpty()){
			Iterator it = params.entrySet().iterator();
			Map.Entry pa = null;
			while(it.hasNext()){
				pa = (Entry) it.next();
				String temp = pa.getKey().toString();
				if(temp.startsWith("id_")){
					if(Integer.parseInt(pa.getValue().toString()) == 0)
						hql += " and  p."+temp.substring(3)+"  is null  ";
					else
						hql += " and ( p.id="+pa.getValue()+" or p."+temp.substring(3)+" = "+pa.getValue()+" )";
				}else{
					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
		}
		
		hql += "order by p.parent.id";
		
		return ps.getPageModel(hql, offset, pagesize);
		
	}
	
}
