package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * 开票
 * @author sjm
 *  @hibernate.class table="t_billing"
 */
public class Billing {

	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * @see 开票名称
	 * @hibernate.property
	 */
	private String billingName;
	/**
	 * @see 合同开票金额
	 * @hibernate.property
	 */
	private Double billingMoney;
	/**
	 * @see 合同开票日期
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date billingDate;
	/**
	 * @see 销售合同
	 * @hibernate.many-to-one
	 * column = "sid"
	 */
	private SalesContract salesContract;
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "baid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.ArrivalItems"
	 */
	private Set<ArrivalItems> arrivalItems;
	
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "billing_id"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.BillingDetail"
	 */
	private Set<BillingDetail> billingDetail;
	
	
	
	public String getBillingName() {
		return billingName;
	}
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}
	public Set<BillingDetail> getBillingDetail() {
		return billingDetail;
	}
	public void setBillingDetail(Set<BillingDetail> billingDetail) {
		this.billingDetail = billingDetail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getBillingMoney() {
		return billingMoney;
	}
	public void setBillingMoney(Double billingMoney) {
		this.billingMoney = billingMoney;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public SalesContract getSalesContract() {
		return salesContract;
	}
	public void setSalesContract(SalesContract salesContract) {
		this.salesContract = salesContract;
	}
	public Set<ArrivalItems> getArrivalItems() {
		return arrivalItems;
	}
	public void setArrivalItems(Set<ArrivalItems> arrivalItems) {
		this.arrivalItems = arrivalItems;
	}
	
	
}
