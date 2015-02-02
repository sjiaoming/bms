package com.shtz.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Person;
import com.shtz.model.Role;
import com.shtz.model.User;
import com.shtz.model.UsersRoles;
import com.shtz.service.PageService;
import com.shtz.service.UserService;

/**
 * @author sjm
 *  
 */
public class UserServiceImpl extends HibernateDaoSupport implements UserService {
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

	public void addUser(User user, int personId) {
		if(personId == 0 ){
			throw new RuntimeException("personID is null");
		}
		user.setPerson((Person)this.getHibernateTemplate().load(Person.class, personId));
		user.setCreateTime(new Date());
		this.getHibernateTemplate().save(user);
	}

	public void deleteUser(int userId) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().load(User.class, userId));
	}

	public void deleteUserRole(int userId, int roleId) {
		UsersRoles ur = this.getUserRole(userId, roleId);
		this.getHibernateTemplate().delete(ur);
	}

	public User findUser(int userId) {
		return (User)this.getHibernateTemplate().load(User.class, userId);
	}
	public User getUserById(int userId){
		return (User)this.getHibernateTemplate().get(User.class, userId);
	}
	public List<UsersRoles> findUserRoles(int userId) {
		
		return  this.getHibernateTemplate().find("select ur from UsersRoles ur where ur.user.id = ?",userId);
	}

	public User login(String username, String password) {
		User u = (User)this.getSession().createQuery("select u from User u where u.username = ?")
					.setParameter(0, username).uniqueResult();
		if(u == null)
			return null;
//			throw new RuntimeException("���û�������");
//		
		if(!password.equals(u.getPassword()))
			return null;
//			throw new RuntimeException("���벻��ȷ");
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
			throw new RuntimeException("personId is null");
		}
		//this.getSession().merge(user);
		user.setPerson((Person)this.getHibernateTemplate().load(Person.class, personId));
		this.getHibernateTemplate().update(user);
	}
	private UsersRoles getUserRole(int userId, int roleId) {
		return (UsersRoles)this.getSession().createQuery("select ur from UsersRoles ur where ur.user.id = ? and ur.role.id = ?")
			.setParameter(0, userId)
			.setParameter(1, roleId)
			.uniqueResult();
		
	}
	public User findUserByName(String name){
		User u = (User)this.getSession().createQuery("select u from User u where u.username = ?")
				.setParameter(0, name).uniqueResult();
		if(u == null)
			return null;
		return u;
	}

}
