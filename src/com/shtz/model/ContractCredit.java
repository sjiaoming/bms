package com.shtz.model;

import java.util.Date;

/**
 * 挂帐
 * @author sjm
 *  @hibernate.class table="t_contractcredit"   
 */
public class ContractCredit {

	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	
	/**
	 * 
	 * @hibernate.property
	 * 
	 * 
	 */
	private Double contractCreditMoney;
	/**
	 * 
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date contractCreditDate;
	/**
	 * 
	 * @hibernate.many-to-one
	 * column = "cid"
	 * cascade="save-update"
	 */
	private ProcurementContract procurementContract;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getContractCreditMoney() {
		return contractCreditMoney;
	}
	public void setContractCreditMoney(Double contractCreditMoney) {
		this.contractCreditMoney = contractCreditMoney;
	}
	public Date getContractCreditDate() {
		return contractCreditDate;
	}
	public void setContractCreditDate(Date contractCreditDate) {
		this.contractCreditDate = contractCreditDate;
	}
	public ProcurementContract getProcurementContract() {
		return procurementContract;
	}
	public void setProcurementContract(ProcurementContract procurementContract) {
		this.procurementContract = procurementContract;
	}
	
	
}
