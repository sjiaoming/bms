package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * @author bry
 * @hibernate.class table="t_businessGroup" 
 */
public class BusinessGroup {
	
	/**
	 * @hibernate.id
	 * generator-class="native"
	 */
	private int id;
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
	 * 组类型
	 * @hibernate.property
	 * 
	 */
	private String groupType;
	/**
	 * 组长
	 * @hibernate.many-to-one
	 * column = "glid"
	 */
	private Person groupLeader;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Person getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(Person groupLeader) {
		this.groupLeader = groupLeader;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	
}
