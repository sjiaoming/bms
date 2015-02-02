package com.shtz.model;

import java.util.Date;

public class ProcurementContractBean {
	
	/*
	 * ---plan---
	 */
	private int number;
	private Integer reportComp;
	private String reportCompName;
	private String oldPlanNum;
	private Date planReceiptDate;
	private String itemsName;
	private String itemsNum;
	private String unit;
	private String planNum;
	private String facilityNameAndNum;
	private Double num;
	private Double planPrice;
	private Double budget;
	private String procurementWay;
	private Date procurementDate;
	private Date searchAnnouncedDate;
	private Date searchDate;
	private String contractExecutionWay;
	private Double procurementPrice;
	private Double plancontractNum;
	private Double procurementMoney;
	private String personName;
	private String planStatus;
	private String checkStatus;
	private String remark;
	private String orgId;	
	private Double quantity;
	private Double budgetPrice;
	private Double budgetMoney;
	private String itemType;
	private String modelType;
	private String material;
	private String standardNum;
	private String demandDept;
	private String checkItemNum;
	private String useFor;
	private String originComp;
	private String groupName;
	/*
	 * ---procurementChect---
	 */
	private String procurementName;
	/*
	 * ---salecontract---
	 */
	private String saleContractNum;
	private String saleContractName;
	private Double saleContractMoney;
	/*
	 * ---procurementContract---
	 */
	private String contractNum;
	private String contractName;
	private Date signingDate;
	private Date arrivalDate;
	private Double totalMoney;
	private String suppliersName;
	/*
	 * ---arrival---
	 */
	private Double arrivalNum;
	private Double arrivalMoney;
	private Double acceptanceNum;
	private Double acceptanceMoney;
	
	
	
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Double getBudgetPrice() {
		return budgetPrice;
	}
	public void setBudgetPrice(Double budgetPrice) {
		this.budgetPrice = budgetPrice;
	}
	public Double getBudgetMoney() {
		return budgetMoney;
	}
	public void setBudgetMoney(Double budgetMoney) {
		this.budgetMoney = budgetMoney;
	}
	public Double getSaleContractMoney() {
		return saleContractMoney;
	}
	public void setSaleContractMoney(Double saleContractMoney) {
		this.saleContractMoney = saleContractMoney;
	}
	public String getProcurementName() {
		return procurementName;
	}
	public String getFacilityNameAndNum() {
		return facilityNameAndNum;
	}
	public void setFacilityNameAndNum(String facilityNameAndNum) {
		this.facilityNameAndNum = facilityNameAndNum;
	}
	public void setProcurementName(String procurementName) {
		this.procurementName = procurementName;
	}
	public String getSaleContractNum() {
		return saleContractNum;
	}
	public void setSaleContractNum(String saleContractNum) {
		this.saleContractNum = saleContractNum;
	}
	public String getSaleContractName() {
		return saleContractName;
	}
	public void setSaleContractName(String saleContractName) {
		this.saleContractName = saleContractName;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Date getPlanReceiptDate() {
		return planReceiptDate;
	}
	public void setPlanReceiptDate(Date planReceiptDate) {
		this.planReceiptDate = planReceiptDate;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getItemsNum() {
		return itemsNum;
	}
	public void setItemsNum(String itemsNum) {
		this.itemsNum = itemsNum;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public Integer getReportComp() {
		return reportComp;
	}
	public void setReportComp(Integer reportComp) {
		this.reportComp = reportComp;
	}
	public String getReportCompName() {
		return reportCompName;
	}
	public void setReportCompName(String reportCompName) {
		this.reportCompName = reportCompName;
	}
	public String getOldPlanNum() {
		return oldPlanNum;
	}
	public void setOldPlanNum(String oldPlanNum) {
		this.oldPlanNum = oldPlanNum;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPlanNum() {
		return planNum;
	}
	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}
	public Double getPlanPrice() {
		return planPrice;
	}
	public void setPlanPrice(Double planPrice) {
		this.planPrice = planPrice;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public String getProcurementWay() {
		return procurementWay;
	}
	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}
	public Date getProcurementDate() {
		return procurementDate;
	}
	public void setProcurementDate(Date procurementDate) {
		this.procurementDate = procurementDate;
	}
	public Date getSearchAnnouncedDate() {
		return searchAnnouncedDate;
	}
	public void setSearchAnnouncedDate(Date searchAnnouncedDate) {
		this.searchAnnouncedDate = searchAnnouncedDate;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public String getContractExecutionWay() {
		return contractExecutionWay;
	}
	public void setContractExecutionWay(String contractExecutionWay) {
		this.contractExecutionWay = contractExecutionWay;
	}
	public Double getProcurementPrice() {
		return procurementPrice;
	}
	public void setProcurementPrice(Double procurementPrice) {
		this.procurementPrice = procurementPrice;
	}
	public Double getPlancontractNum() {
		return plancontractNum;
	}
	public void setPlancontractNum(Double plancontractNum) {
		this.plancontractNum = plancontractNum;
	}
	public Double getProcurementMoney() {
		return procurementMoney;
	}
	public void setProcurementMoney(Double procurementMoney) {
		this.procurementMoney = procurementMoney;
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
	public String getSuppliersName() {
		return suppliersName;
	}
	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}
	public Double getArrivalNum() {
		return arrivalNum;
	}
	public void setArrivalNum(Double arrivalNum) {
		this.arrivalNum = arrivalNum;
	}
	public Double getArrivalMoney() {
		return arrivalMoney;
	}
	public void setArrivalMoney(Double arrivalMoney) {
		this.arrivalMoney = arrivalMoney;
	}
	public Double getAcceptanceNum() {
		return acceptanceNum;
	}
	public void setAcceptanceNum(Double acceptanceNum) {
		this.acceptanceNum = acceptanceNum;
	}
	public Double getAcceptanceMoney() {
		return acceptanceMoney;
	}
	public void setAcceptanceMoney(Double acceptanceMoney) {
		this.acceptanceMoney = acceptanceMoney;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getStandardNum() {
		return standardNum;
	}
	public void setStandardNum(String standardNum) {
		this.standardNum = standardNum;
	}
	public String getDemandDept() {
		return demandDept;
	}
	public void setDemandDept(String demandDept) {
		this.demandDept = demandDept;
	}
	public String getCheckItemNum() {
		return checkItemNum;
	}
	public void setCheckItemNum(String checkItemNum) {
		this.checkItemNum = checkItemNum;
	}
	public String getUseFor() {
		return useFor;
	}
	public void setUseFor(String useFor) {
		this.useFor = useFor;
	}
	public String getOriginComp() {
		return originComp;
	}
	public void setOriginComp(String originComp) {
		this.originComp = originComp;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}
