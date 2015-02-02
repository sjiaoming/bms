package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * 到货管理
 * @author sjm
 *  @hibernate.class table="t_arrivalitems"   
 */
public class ArrivalItems {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 到货数量
	 * @hibernate.property
	 */
	private Double arrivalNum;
	/**
	 * 到货时间
	 * @hibernate.property
	 */
	private Date arrivalDate;
	
	/**
	 * 验收数量
	 * @hibernate.property
	 */
	private Double acceptanceNum;
	/**
	 * 验收时间
	 * @hibernate.property
	 */
	private Date acceptanceDate;
	/**
	 * @see 开票状态 01未开 02已开
	 * @hibernate.property
	 */
	private String billingStatus;
	/**
	 * @see 开票数量
	 * @hibernate.property
	 */
	private Double billingNum;
	/**
	 * @hibernate.many-to-one
	 *  column = "aid"
	 *  cascade="save-update"
	 */
	private Plan plan;
	/**
	 * 
	 * @hibernate.many-to-one
	 * column = "baid"
	 * cascade="save-update"
	 */
	private Billing billing;
	/**
	 * 到货管理
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "arrival_id"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.BillingDetail"
	 */
	private Set<BillingDetail> billingDetail;
	
	
	public Set<BillingDetail> getBillingDetail() {
		return billingDetail;
	}

	public void setBillingDetail(Set<BillingDetail> billingDetail) {
		this.billingDetail = billingDetail;
	}

	public Double getBillingNum() {
		return billingNum;
	}

	public void setBillingNum(Double billingNum) {
		this.billingNum = billingNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Double getArrivalNum() {
		return arrivalNum;
	}

	public void setArrivalNum(Double arrivalNum) {
		this.arrivalNum = arrivalNum;
	}

	public Double getAcceptanceNum() {
		return acceptanceNum;
	}

	public void setAcceptanceNum(Double acceptanceNum) {
		this.acceptanceNum = acceptanceNum;
	}

	public String getBillingStatus() {
		return billingStatus;
	}

	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}
	
	
}
