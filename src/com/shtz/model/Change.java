package com.shtz.model;

import java.util.Date;

/**
 * @author sjm
 * @hibernate.class table="t_change" 
 */
public class Change {
	
	/**
	 * @hibernate.id
	 * generator-class="native"
	 */
	private int id;
	/**
	 * 计划数量
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Double num;
	/**
	 * 计划单价
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Double planPrice;
	/**
	 * 预算金额
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Double contractMoney;
	/**
	 * 变更原因
	 * @hibernate.property
	 * 
	 */
	private String reason;
	/**
	 * 变更person
	 * @hibernate.property
	 * 
	 */
	private String personName;
	/**
	 * 变更时间
	 * @hibernate.property
	 * 
	 */
	private Date changeTime;
	/**
	 * @hibernate.many-to-one
	 * unique="true"
	 */
	private Plan plan;
	
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Double getPlanPrice() {
		return planPrice;
	}
	public void setPlanPrice(Double planPrice) {
		this.planPrice = planPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Double getContractMoney() {
		return contractMoney;
	}
	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	
}
