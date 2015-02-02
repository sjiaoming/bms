package com.shtz.model;
/**
 * @author sjm
 *  @hibernate.class table="t_usersroles"
 */
public class UsersRoles {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * @hibernate.many-to-one
	 * 
	 */
	private Role role;
	/**
	 * @hibernate.many-to-one
	 * 
	 */
	private User user;
	/**
	 * @hibernate.property
	 * 
	 */
	private int orderNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}
