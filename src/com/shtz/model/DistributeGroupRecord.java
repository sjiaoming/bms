package com.shtz.model;

import java.util.Date;

public class DistributeGroupRecord {

	/**
	 * @hibernate.id
	 * generator-class="native"
	 */
	private int id;
	/**
	 * plan ids
	 * @hibernate.property
	 * 
	 */
	private String planIds;
	/**
	 * Procurement ids
	 * @hibernate.property
	 * 
	 */
	private String pcIds;
	/**
	 * record type
	 * @hibernate.property
	 * 01  plan record
	 * 02  procumentCheck record
	 */
	private String type;
	/**
	 * 添加日期
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Date  addDate;
	/**
	 *接收者
	 * @hibernate.many-to-one
	 * column = "did"
	 */
	private Person distributor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlanIds() {
		return planIds;
	}
	public void setPlanIds(String planIds) {
		this.planIds = planIds;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Person getDistributor() {
		return distributor;
	}
	public void setDistributor(Person distributor) {
		this.distributor = distributor;
	}
	public String getPcIds() {
		return pcIds;
	}
	public void setPcIds(String pcIds) {
		this.pcIds = pcIds;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
