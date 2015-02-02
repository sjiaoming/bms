package com.shtz.model;

import java.util.Date;

/**
 * @author bry
 * @hibernate.class table="t_groupPlan" 
 */
public class GroupPlan {
	
	/**
	 * @hibernate.id
	 * generator-class="native"
	 */
	private int id;
	/**
	 * 组
	 * @hibernate.many-to-one
	 * column = "gId"
	 * 
	 */
	private Group group;
	/**
	 * 计划
	 * @hibernate.many-to-one
	 * column = "pId"
	 */
	private Plan plan;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	
	
}
