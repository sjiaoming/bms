package com.shtz.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Person;
import com.shtz.model.Role;
import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.service.PageService;
import com.shtz.service.UserCompService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class UserCompServiceImpl extends HibernateDaoSupport implements UserCompService {
	private PageService pservice;

	public void setPservice(PageService pservice) {
		this.pservice = pservice;
	}
	public PageModel searchUserComps(int offset, int pagesize) {
		return pservice.getPageModel("from UseComp", offset, pagesize);
	}
	public PageModel searchUserCompByName(String name,int offset, int pagesize) {
		return pservice.getPageModel("from UseComp uc where uc.name like '%"+name+"%'", offset, pagesize);
	}
	public void addUserComp(UseComp uc) {
		if(this.findUseCompByName(uc.getName())==null)
			this.getHibernateTemplate().save(uc);
	}
	public void deleteUseComp(int useCompId) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(UseComp.class, useCompId));
	}
	public UseComp findUseComp(int useCompId) {
		return (UseComp)this.getHibernateTemplate().load(UseComp.class, useCompId);
	}
	public UseComp findUseCompByName(String useCompName) {
		return (UseComp)this.getSession().createQuery(" from UseComp uc where uc.name = ?").setParameter(0, useCompName).uniqueResult();
	}
	public void modifyUseComp(UseComp uc, int UseCompId) {
		if(UseCompId == 0 ){
			throw new RuntimeException("不存在该公司");
		}
		this.getHibernateTemplate().update(uc);
	}
	public List getUseCompList(){
		return this.getHibernateTemplate().loadAll(UseComp.class);
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

	public User findUser(int userId) {
		return (User)this.getHibernateTemplate().load(User.class, userId);
	}

	public List<UsersRoles> findUserRoles(int userId) {
		
		return  this.getHibernateTemplate().find("select ur from UsersRoles ur where ur.user.id = ?",userId);
	}

	public User login(String username, String password) {
		User u = (User)this.getSession().createQuery("select u from User u where u.username = ?")
					.setParameter(0, username).uniqueResult();
		if(u == null)
			throw new RuntimeException("user is null");
		
		if(!password.equals(u.getPassword()))
			throw new RuntimeException("password is error");
		/*if(u.getExpireTime() != null) {
			Calendar now = Calendar.getInstance();
			Calendar expireTime = Calendar.getInstance();
			expireTime.setTime(u.getExpireTime());
			if(now.after(expireTime))
				throw new RuntimeException("���û���ʧЧ");
		}*/
		return u;
	}

	public void modifyUser(User user, int personId) {
		if(personId == 0 ){
			throw new RuntimeException("modify user error");
		}
		user.setPerson((Person)this.getHibernateTemplate().load(Person.class, personId));
		this.getHibernateTemplate().update(user);
	}
	private UsersRoles getUserRole(int userId, int roleId) {
		return (UsersRoles)this.getSession().createQuery("select ur from UsersRoles ur where ur.user.id = ? and ur.role.id = ?")
			.setParameter(0, userId)
			.setParameter(1, roleId)
			.uniqueResult();
		
	}
	
	public PageModel serchUserCompByParams(Map params,int offset, int pageSize){
		String hql = "from UseComp p where 1=1 ";
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
		
		
		
		return pservice.getPageModel(hql, offset, pageSize);
	}
	
}
