package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * @author sjm
 *  @hibernate.class table="t_user"
 *  @see 用户信息
 */
public class User {
	/**
	 * @hibernate.id
	 * generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 * not-null="true"
	 * unique="true"
	 */
	private String username;
	/**
	 * @hibernate.property
	 * not-null="true"
	 */
	private String password;
	/**
	 * @hibernate.property
	 * update="false"
	 */
	private Date createTime;
	/**
	 * @hibernate.property
	 * 
	 */
	private Date expireTime;
	/**
	 * @hibernate.property
	 * 
	 */
	private String auth;
	
	/**
	 * @hibernate.many-to-one
	 * unique="true"
	 */
	private Person person;
	
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "uid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Plan"
	 */
	private Set<Plan> plans;
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "uid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.SalesContract"
	 */
	private Set<SalesContract> salesContracts;
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "uid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.ProcurementContract"
	 */
	private Set<ProcurementContract> procurementContracts;
	
	
	
	public Set<SalesContract> getSalesContracts() {
		return salesContracts;
	}
	public void setSalesContracts(Set<SalesContract> salesContracts) {
		this.salesContracts = salesContracts;
	}
	public Set<ProcurementContract> getProcurementContracts() {
		return procurementContracts;
	}
	public void setProcurementContracts(
			Set<ProcurementContract> procurementContracts) {
		this.procurementContracts = procurementContracts;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public Set<Plan> getPlans() {
		return plans;
	}
	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
}
