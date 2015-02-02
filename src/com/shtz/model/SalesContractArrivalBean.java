package com.shtz.model;

import java.util.Date;
import java.util.Set;
public class SalesContractArrivalBean {

	private int id;
	private String contractNum;
	private Date  signingDate;
	private Integer reportComp;
	private String reportCompName;
	private String signComp;
	private String signFlag;
	private Double qualityMoney;
	private Date qualityDate;
	private Double contractMoney;
	private String contractReceivedMoney;
	private Double totalcontractReceivedMoney;
	private Double contractReceivableMoney;
	private Set<Plan> plan;
	private int orgId;
	private String orgName;
	private Double totalContractInvoiceMoney;
	private Set<Billing> billing;
	private User user;
	private Person person;
	private String salesContractName;
	private String arrivalAddress;
	
	private Double arrivalMoney;
	private Double acceptanceMoney;
	
	
	public String getArrivalAddress() {
		return arrivalAddress;
	}
	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}
	public String getReportCompName() {
		return reportCompName;
	}
	public void setReportCompName(String reportCompName) {
		this.reportCompName = reportCompName;
	}
	public Double getArrivalMoney() {
		return arrivalMoney;
	}
	public void setArrivalMoney(Double arrivalMoney) {
		this.arrivalMoney = arrivalMoney;
	}
	public Double getAcceptanceMoney() {
		return acceptanceMoney;
	}
	public void setAcceptanceMoney(Double acceptanceMoney) {
		this.acceptanceMoney = acceptanceMoney;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public Integer getReportComp() {
		return reportComp;
	}
	public void setReportComp(Integer reportComp) {
		this.reportComp = reportComp;
	}
	public String getSignComp() {
		return signComp;
	}
	public void setSignComp(String signComp) {
		this.signComp = signComp;
	}
	public String getSignFlag() {
		return signFlag;
	}
	public void setSignFlag(String signFlag) {
		this.signFlag = signFlag;
	}
	public Double getQualityMoney() {
		return qualityMoney;
	}
	public void setQualityMoney(Double qualityMoney) {
		this.qualityMoney = qualityMoney;
	}
	public Date getQualityDate() {
		return qualityDate;
	}
	public void setQualityDate(Date qualityDate) {
		this.qualityDate = qualityDate;
	}
	public Double getContractMoney() {
		return contractMoney;
	}
	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}
	public String getContractReceivedMoney() {
		return contractReceivedMoney;
	}
	public void setContractReceivedMoney(String contractReceivedMoney) {
		this.contractReceivedMoney = contractReceivedMoney;
	}
	public Double getTotalcontractReceivedMoney() {
		return totalcontractReceivedMoney;
	}
	public void setTotalcontractReceivedMoney(Double totalcontractReceivedMoney) {
		this.totalcontractReceivedMoney = totalcontractReceivedMoney;
	}
	public Double getContractReceivableMoney() {
		return contractReceivableMoney;
	}
	public void setContractReceivableMoney(Double contractReceivableMoney) {
		this.contractReceivableMoney = contractReceivableMoney;
	}
	public Set<Plan> getPlan() {
		return plan;
	}
	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Double getTotalContractInvoiceMoney() {
		return totalContractInvoiceMoney;
	}
	public void setTotalContractInvoiceMoney(Double totalContractInvoiceMoney) {
		this.totalContractInvoiceMoney = totalContractInvoiceMoney;
	}
	public Set<Billing> getBilling() {
		return billing;
	}
	public void setBilling(Set<Billing> billing) {
		this.billing = billing;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getSalesContractName() {
		return salesContractName;
	}
	public void setSalesContractName(String salesContractName) {
		this.salesContractName = salesContractName;
	}

	
	
}
