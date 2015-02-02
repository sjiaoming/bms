package com.shtz.model;

import java.util.Set;

import jsx3.gui.matrix.Column;

/**
 * 
 * @author sjm
 * @hibernate.class table = "t_person"
 * @see ��Ա��Ϣ
 */
public class Person {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private String sex;
	/**
	 * @hibernate.property
	 */
	private String address;
	/**
	 * @hibernate.property
	 */
	private String duty;
	/**
	 * @hibernate.property
	 */
	private String phone;
	/**
	 * @hibernate.property
	 */
	private String email;
	/**
	 * @hibernate.property
	 */
	private String description;
	/**
	 * @hibernate.many-to-one
	 */
	private Organization org;
	/**
	 * @hibernate.one-to-one
	 * property-ref="person"
	 */
	private User user;
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "psid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Plan"
	 */
	private Set<Plan> plans;

	/**
	 * @hibernate.many-to-one
	 * column = "pgid"
	 */
	private BusinessGroup planGroup;
	/**
	 * @hibernate.many-to-one
	 * column = "pmgid"
	 */
	private BusinessGroup planManagerGroup;

	/**
	 * @hibernate.many-to-one
	 * column = "sgid"
	 */
	private BusinessGroup seekSourceGroup;

	public BusinessGroup getPlanGroup() {
		return planGroup;
	}

	public void setPlanGroup(BusinessGroup planGroup) {
		this.planGroup = planGroup;
	}

	public BusinessGroup getSeekSourceGroup() {
		return seekSourceGroup;
	}

	public void setSeekSourceGroup(BusinessGroup seekSourceGroup) {
		this.seekSourceGroup = seekSourceGroup;
	}

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BusinessGroup getPlanManagerGroup() {
		return planManagerGroup;
	}

	public void setPlanManagerGroup(BusinessGroup planManagerGroup) {
		this.planManagerGroup = planManagerGroup;
	}
	
	
}
