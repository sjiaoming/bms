package com.shtz.model;

import java.util.Date;

public class Payed {
	
	private int id;
	/**
	 * 付款
	 */
	private Double payed;
	/**
	 * 收款
	 */
	private Double contractReceivedMoney;
	/**
	 * 收/付款方式
	 */
	private String payedStyle;
	/**
	 * 付款日期
	 */
	private Date payedDate;
	/**
	 * 收款日期
	 */
	private Date contractReceivedDate;
	/**
	 * 收付款区分
	 * 01 付款
	 * 02 收款
	 */
	private String flag;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPayed() {
		return payed;
	}

	public void setPayed(Double payed) {
		this.payed = payed;
	}

	public String getPayedStyle() {
		return payedStyle;
	}

	public void setPayedStyle(String payedStyle) {
		this.payedStyle = payedStyle;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}

	public Double getContractReceivedMoney() {
		return contractReceivedMoney;
	}

	public void setContractReceivedMoney(Double contractReceivedMoney) {
		this.contractReceivedMoney = contractReceivedMoney;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getContractReceivedDate() {
		return contractReceivedDate;
	}

	public void setContractReceivedDate(Date contractReceivedDate) {
		this.contractReceivedDate = contractReceivedDate;
	}
	
	
}
