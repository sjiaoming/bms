package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * @author bry
 * @hibernate.class table="t_group" 
 */
public class Group {
	
	/**
	 * @hibernate.id
	 * generator-class="native"
	 */
	private int id;
	/**
	 * 组
	 * @hibernate.many-to-one
	 * column = "bgId"
	 * 
	 */
	private BusinessGroup group;
	/**
	 * @hibernate.set inverse = "true" cascade="all"
	 * @hibernate.key column = "gId"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.GroupPlan"
	 */
	private Set<GroupPlan> plans;
	/**
	 * 组名称
	 * @hibernate.property
	 * 
	 */
	private String groupName;
	/**
	 * 组编号
	 * @hibernate.property
	 * 
	 */
	private String groupNum;
	/**
	 * 备注
	 * @hibernate.property
	 * 
	 */
	private String remark;
	/**
	 * 组长
	 * @hibernate.many-to-one
	 * column = "pid"
	 */
	private Person addPerson;
	/**
	 * 日期
	 * @hibernate.property
	 * 
	 */
	private Date addDate;
	/**
	 * 状态
	 * @hibernate.property
	 * 01 计划主管未审批
	 * 02 计划主管未通过
	 * 03 主管领导未审批
	 * 04主管领导未通过
	 * 05 总经理未审批
	 * 06 总经理未通过
	 * 07总经理已通过
	 */
	private String status;

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Person getAddPerson() {
		return addPerson;
	}
	public void setAddPerson(Person addPerson) {
		this.addPerson = addPerson;
	}
	public Set<GroupPlan> getPlans() {
		return plans;
	}
	public void setPlans(Set<GroupPlan> plans) {
		this.plans = plans;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BusinessGroup getGroup() {
		return group;
	}
	public void setGroup(BusinessGroup group) {
		this.group = group;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupNum() {
		return groupNum;
	}
	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}
	
}
