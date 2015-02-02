package com.shtz.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Role;
import com.shtz.service.PageService;
import com.shtz.service.RoleService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class RoleServiceImpl extends HibernateDaoSupport implements RoleService{
	
	private PageService service;
	

	
	public void setService(PageService service) {
		this.service = service;
	}

	public void addRole(Role role) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(role);
	}

	public void deleteRole(int roleId) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(Role.class, roleId));
	}

	public Role findRole(int roleId) {
		// TODO Auto-generated method stub
		return (Role)this.getHibernateTemplate().load(Role.class, roleId);
	}

	public PageModel findRoles(int offset, int pagesize) {
		// TODO Auto-generated method stub
		
		return service.getPageModel("from Role", offset, pagesize);
	}
	public PageModel findRolesByParams(Map params,int offset, int pageSize){
		String hql = "from Role p where 1=1 ";
		if(!params.isEmpty()){
			Iterator it = params.entrySet().iterator();
			Map.Entry pa = null;
			while(it.hasNext()){
				pa = (Entry) it.next();
				String temp = pa.getKey().toString();
				if(temp.startsWith("id_")){
					hql += " and  p."+temp.substring(3)+" = "+pa.getValue();
				}else{
					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
		}
		return service.getPageModel(hql, offset, pageSize);
	}
	public void modityRole(Role role) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(role);
	}
	
	
	
}
