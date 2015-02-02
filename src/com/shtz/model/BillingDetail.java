package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * 
 * @author sjm
 *  @hibernate.class table="t_billingdetail"
 */
public class BillingDetail {

	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private Double arrivalNum;
	/**
	 * @hibernate.property
	 */
	private Date arrivalDate;
	
	/**
	 * @hibernate.property
	 */
	private Double acceptanceNum;
	/**
	 * @hibernate.property
	 */
	private Date acceptanceDate;
	/**
	 * @see 开票数量
	 * @hibernate.property
	 */
	private Double billingNum;
	/**
	 * @see 开票金额
	 * @hibernate.property
	 */
	private Double billingMoney;
	/**
	 * @hibernate.property
	 */
	private Date billingDate;
	/**
	 * @hibernate.many-to-one
	 * column = "billing_id"
	 * cascade="save-update"
	 */
	private Billing billing;
	/**
	 * @hibernate.many-to-one
	 *  column = "plan_id"
	 *  cascade="save-update"
	 */
	private Plan plan;
	/**
	 * @hibernate.many-to-one
	 *  column = "arrival_id"
	 *  cascade="save-update"
	 */
	private ArrivalItems arrivalItems;
	
	
	public ArrivalItems getArrivalItems() {
		return arrivalItems;
	}
	public void setArrivalItems(ArrivalItems arrivalItems) {
		this.arrivalItems = arrivalItems;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public Double getBillingMoney() {
		return billingMoney;
	}
	public void setBillingMoney(Double billingMoney) {
		this.billingMoney = billingMoney;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getArrivalNum() {
		return arrivalNum;
	}
	public void setArrivalNum(Double arrivalNum) {
		this.arrivalNum = arrivalNum;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Double getAcceptanceNum() {
		return acceptanceNum;
	}
	public void setAcceptanceNum(Double acceptanceNum) {
		this.acceptanceNum = acceptanceNum;
	}
	public Date getAcceptanceDate() {
		return acceptanceDate;
	}
	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}
	public Double getBillingNum() {
		return billingNum;
	}
	public void setBillingNum(Double billingNum) {
		this.billingNum = billingNum;
	}
	public Billing getBilling() {
		return billing;
	}
	public void setBilling(Billing billing) {
		this.billing = billing;
	}
	
	
}
