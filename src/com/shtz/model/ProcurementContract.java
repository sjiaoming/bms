package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * 采购合同
 * @author sjm
 * @hibernate.class table="t_procurementcontract"  
 */
public class ProcurementContract {

	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 合同编号
	 * @hibernate.property
	 * not-null="true"
	 * unique="true"
	 */
	private String contractNum;
	/**
	 * 供应商id
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Integer suppliersId;
	/**
	 * 供应商name
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private String suppliersName;
	/**
	 * 合同执行方式
	 * @hibernate.property
	 * 
	 */
	private String contractExecutionWay;
	/**
	 * 预付款
	 * @hibernate.property
	 * 
	 */
	private Double advance;
	/**
	 * 业务id
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Integer orgId;
	/**
	 * 业务部门名称
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private String orgName;
	
	/**
	 * 合同签订日期
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Date signingDate;
	/**
	 * 合同到货日期
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Date arrivalDate;
	/**
	 * 合同总额
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Double totalMoney;
	/**
	 * 采购方案
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private String procurementWay;
	/**
	 * 合同质保金额
	 * @hibernate.property
	 * 
	 * 
	 */
	private Double qualityMoney;
	/**
	 * 合同质保日期
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date qualityDate;
	/**
	 * 合同未付金额
	 * @hibernate.property
	 * 
	 * 
	 */
	private Double noPayment;
	/**
	 * 合同应付金额
	 * @hibernate.property
	 * 
	 * 
	 */
	private Double shouldPayment;
	/**
	 * 合同备注
	 * @hibernate.property
	 * 
	 * 
	 */
	private String remark;
	
	/**
	 * 合同已付金额
	 * @hibernate.property
	 * 
	 * 
	 */
	private String payed;
	
	/**
	 * 计划
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "pid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Plan"
	 */
	private Set<Plan> plan;
	/**
	 * 挂账
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "cid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.ContractCredit"
	 */
	private Set<ContractCredit> contractCredit;

	/**
	 * 用户
	 * @hibernate.many-to-one
	 * column = "uid"
	 */
	private User user;
	
	/**
	 * 人员
	 * @hibernate.many-to-one
	 * column = "psid"
	 */
	private Person person;
	
	/**
	 * 合同名称
	 * @hibernate.property
	 * 
	 */
	private String contractName;
	/**
	 * 使用单位
	 * @hibernate.property
	 * 
	 */
	private String reportCompId_pc;
	/**
	 * 使用单位	
	 * @hibernate.property
	 * 
	 */
	private String reportCompName_pc;
	

	public String getReportCompId_pc() {
		return reportCompId_pc;
	}
	public void setReportCompId_pc(String reportCompId_pc) {
		this.reportCompId_pc = reportCompId_pc;
	}
	public String getReportCompName_pc() {
		return reportCompName_pc;
	}
	public void setReportCompName_pc(String reportCompName_pc) {
		this.reportCompName_pc = reportCompName_pc;
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
