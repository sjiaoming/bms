package com.shtz.model;

import java.util.Date;
import java.util.Set;
public class ProcurementArrivalBean {

	private int id;
	private String contractNum;
	private Integer suppliersId;
	private String suppliersName;
	private String contractExecutionWay;
	private Double advance;
	private Integer orgId;
	private String orgName;
	private Date signingDate;
	private Date arrivalDate;
	private Double totalMoney;
	private String procurementWay;
	private Double qualityMoney;
	private Date qualityDate;
	private Double noPayment;
	private Double shouldPayment;
	private String remark;
	private String payed;
	private Set<Plan> plan;
	private Set<ContractCredit> contractCredit;
	private User user;
	private Person person;
	private String contractName;
	
	
	private Double creditMoney;
	private Date creditDate;
	private Double arrivalMoney;
	private Double acceptanceMoney;
	
	private String reportComp;
	private String reportCompName;
	

	public String getReportComp() {
		return reportComp;
	}

	public Double getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(Double creditMoney) {
		this.creditMoney = creditMoney;
	}

	public Date getCreditDate() {
		return creditDate;
	}

	public void setCreditDate(Date creditDate) {
		this.creditDate = creditDate;
	}

	public void setReportComp(String reportComp) {
		this.reportComp = reportComp;
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
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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


	public Integer getSuppliersId() {
		return suppliersId;
	}
	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
	}
	public String getSuppliersName() {
		return suppliersName;
	}
	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public Double getAdvance() {
		return advance;
	}
	public void setAdvance(Double advance) {
		this.advance = advance;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getProcurementWay() {
		return procurementWay;
	}
	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}

	public Double getNoPayment() {
		return noPayment;
	}
	public void setNoPayment(Double noPayment) {
		this.noPayment = noPayment;
	}
	public Double getShouldPayment() {
		return shouldPayment;
	}
	public void setShouldPayment(Double shouldPayment) {
		this.shouldPayment = shouldPayment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Set<Plan> getPlan() {
		return plan;
	}
	public void setPlan(Set<Plan> plan) {
		this.plan = plan;
	}
	public Set<ContractCredit> getContractCredit() {
		return contractCredit;
	}
	public void setContractCredit(Set<ContractCredit> contractCredit) {
		this.contractCredit = contractCredit;
	}
	public String getContractExecutionWay() {
		return contractExecutionWay;
	}
	public void setContractExecutionWay(String contractExecutionWay) {
		this.contractExecutionWay = contractExecutionWay;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
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
	public void setPayed(String payed) {
		this.payed = payed;
	}
	public String getPayed() {
		return payed;
	}

	
	
}
