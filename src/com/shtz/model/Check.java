package com.shtz.model;

import java.util.Date;

public class Check {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 审核人
	 * @hibernate.many-to-one
	 * column = "psid"
	 */
	private Person person;
	/*
	 * old status 20130823 
	 * 01 主管未审核
	 * 02 主管未通过审核
	 * 08 内控未审核
	 * 09 内控未通过审核
	 * 03 经理未审核
	 * 04 经理未通过审核
	 * 05 董事长未审核
	 * 06 董事长未通过审核
	 * 07 董事长己审核
	 */
	/**pppp
	 *  new 审核状态 20130823
	 * 01 主管未审核
	 * 02 主管未通过审核
	 * 08 主管己审核
	 * 09 内控未通过审核
	 * 03 内控己审核
	 * 04 经理未通过审核
	 * 05 经理己审核
	 * 06 董事长未通过审核
	 * 07 董事长己审核
	 * @hibernate.property
	 */
	private String checkStatus;
	/**
	 * 审核意见
	 * @hibernate.property
	 */
	private String opinion;
	/**
	 * 计划
	 * @hibernate.many-to-one
	 * column = "pid"
	 */
	private Plan plan;
	/**
	 * 采购方式审批表
	 * @hibernate.many-to-one
	 * column = "pcid"
	 */
	private ProcurementCheck pc;
	/**
	 * 寻源表
	 * @hibernate.many-to-one
	 * column = "ssid"
	 */
	private SeekSource seekSource;
	/**
	 * 审核日期
	 * @hibernate.property
	 */
	private Date checkDate;
	/**
	 * 审核类型
	 * @hibernate.property
	 */
	private String checkType;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SeekSource getSeekSource() {
		return seekSource;
	}
	public void setSeekSource(SeekSource seekSource) {
		this.seekSource = seekSource;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public ProcurementCheck getPc() {
		return pc;
	}
	public void setPc(ProcurementCheck pc) {
		this.pc = pc;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
}
