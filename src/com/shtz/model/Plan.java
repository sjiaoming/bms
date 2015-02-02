package com.shtz.model;

import java.util.Date;

import java.util.Set;

/**
 * 计划管理
 * @author sjm
 * @hibernate.class table="t_plan" 
 */
public class Plan {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 计划编号
	 * @hibernate.property
	 */
	private String planNum;
	/**
	 * @see 物资类别
	 * @hibernate.property
	 */
	private String itemType;
	/**
	 * @see 物资代码
	 * @hibernate.property
	 */
	private String itemsNum;
	/**
	 * 设备名称及位号
	 * @hibernate.property
	 */
	private String facilityNameAndNum;
	/**
	 * 物资名称
	 * @hibernate.property
	 * not-null="true"
	 */
	private String itemsName;
	/**
	 * 规格型号
	 * @hibernate.property
	 */
	private String modelType;
	/**
	 * 材质 
	 * @hibernate.property
	 */
	private String material;
	/**
	 * 标准号
	 * @hibernate.property
	 */
	private String standardNum;
	/**
	 * 单位
	 * @hibernate.property
	 */
	private String unit;
	/**
	 * 数量
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Double quantity;
	/**
	 * 预算单价（元）
	 * @hibernate.property
	 */
	private Double budgetPrice;
	/**
	 * 预算金额（元）
	 * @hibernate.property
	 */
	private Double budgetMoney;

	/**
	 * 物资需求部门
	 * @hibernate.property
	 */
	private String demandDept;
	/**
	 * 要求到货时间
	 * @hibernate.property
	 */
	private Date arrivalDate;
	/**
	 * 检修项目编号
	 * @hibernate.property
	 */
	private String checkItemNum;
	/**
	 * 用途
	 * @hibernate.property
	 */
	private String useFor;
	/**
	 * 原厂商
	 * @hibernate.property
	 * 
	 * 
	 */
	private String originComp;
	/**
	 * 备注
	 * @hibernate.property
	 * 
	 * 
	 */
	private String remark;
	/**
	 * 供应商
	 * @hibernate.many-to-one
	 * column = "psuid"
	 * cascade="none"
	 */
	private Suppliers supplier;
	/**
	 * 是否拆分
	 * @hibernate.property
	 * 
	 * 
	 */
	private String model;
	/**
	 * 使用单位
	 * @hibernate.property
	 * 
	 * 
	 */
	private String reportComp;
	/**
	 * 使用单位ID
	 * @hibernate.property
	 * 
	 * 
	 */
	private Integer reportCompId;
	
	/**
	 * 计划接收日期
	 * @hibernate.property
	 */
	private Date planReceiptDate;
	/**
	 * 合同签订数量
	 * @hibernate.property
	 */
	private Double contractNum;
	/**
	 * 到货地点
	 * @hibernate.property
	 * 
	 * 
	 */
	private String arrivalSite;
	/**
	 * 变更状态
	 * @hibernate.property
	 * 
	 * 
	 */
	private String procurementFlag;
	
	/**
	 * 采购方案
	 * @hibernate.property
	 * 
	 * 
	 */
	private String procurementWay;
	/**
	 * 采购方式审批
	 * @hibernate.many-to-one
	 * column = "pwcid"
	 */
	private ProcurementCheck pc;
	/**
	 * 采购签报日期
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date procurementDate;
	/**
	 * 采购方案备注
	 * @hibernate.property
	 * 
	 * 
	 */
	private String procurementRemark;

	/**
	 * 寻源公布日期
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date searchAnnouncedDate;
	/**
	 * 寻源签报日期
	 * @hibernate.property
	 * 
	 */
	private Date searchDate;
	/**
	 * 合同执行方式
	 * @hibernate.property
	 * 
	 */
	private String contractExecutionWay;
	/**
	 * 合同金额
	 * @hibernate.property
	 *  
	 */
	private Double contractMoney;

	/**
	 * 采购单价
	 * @hibernate.property
	 *
	 */
	private Double procurementPrice;
	/**
	 * 采购金额
	 * @hibernate.property
	 * 
	 */
	private Double procurementMoney;
	/**
	 * 销售金额
	 * @hibernate.property
	 *  
	 */
	private Double salesMoney;
	
	/**
	 * 销售比率
	 * @hibernate.property
	 *  
	 */
	private Double salesRatio;
	/**
	 * @see 销售单价
	 * @hibernate.property
	 *  
	 */
	private Double salesPrice;
	/**
	 * 变更管理
	 * @hibernate.one-to-one
	 * property-ref="plan"
	 * cascade="all"
	 */
	private Change change;
	
	/**
	 * 采购合同
	 * @hibernate.many-to-one
	 * column = "pid"
	 * cascade="save-update"
	 */
	private ProcurementContract procurementContract;
	
	/**
	 * 销售合同
	 * @hibernate.many-to-one
	 * column = "sid"
	 */
	private SalesContract salesContract;
	/**
	 * 到货管理
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "aid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.ArrivalItems"
	 */
	private Set<ArrivalItems> arrivalItems;
	
	/**
	 * 计划所属部门
	 * @hibernate.many-to-one
	 * column = "oid"
	 */
	private Organization org;
	
	
	/**
	 * 用户
	 * @hibernate.many-to-one
	 * column = "uid"
	 */
	private User user;
	
	/**
	 * 主管 设置业务员
	 * @hibernate.many-to-one
	 * column = "psid"
	 */
	private Person person;
	/**
	 * 主管 记录主管信息
	 * @hibernate.many-to-one
	 * column = "pdid"
	 */
	private Person director;
	/**
	 * 经理 审核业务员
	 * @hibernate.many-to-one
	 * column = "pmid"
	 */
	private Person manager;
	/**
	 * 计划单状态
	 * @hibernate.property
	 * 01:未处理
	 * 02:已采购方案
	 * 03:已寻源
	 * 04:已销售
	 * 05:已采购
	 * 
	 */
	private String planStatus;
	/**
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
	 * 审批意见
	 * @hibernate.property
	 */
	private String opinion;
	/**
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "pcid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Plan"
	 */
	private Set<Plan> children;
	/**
	 * @hibernate.set inverse = "true" cascade="all-delete-orphan"
	 * @hibernate.key column = "pid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Check"
	 */
	private Set<Check> check;
	/**
	 * @hibernate.many-to-one
	 * column = "pcid"  not-found="ignore"
	 */
	private Plan parent;
	
	/**
	 * 采购制定日期
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date procurementSigningleDate;
	/**
	 * 
	 * @hibernate.property
	 * @see 到货总数量
	 *  
	 */
	private Double arrivalNumTotal;
	/**
	 * 
	 * @hibernate.property
	 * @see 验收总数量
	 *  
	 */
	private Double acceptanceNumTotal;
	/**
	 * 
	 * @hibernate.property
	 * @see 到货总金额
	 *  
	 */
	private Double arrivalMoneyTotal;
	/**
	 * 
	 * @hibernate.property
	 * @see 验收总金额
	 *  
	 */
	private Double acceptanceMoneyTotal;
	/**
	 * 开票明细
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "plan_id"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.BillingDetail"
	 */
	private Set<BillingDetail> billingDetail;
	/**
	 * @hibernate.property
	 * @see 到货地址
	 */
	private String arrivalAddress;
	/**
	 * @see 大类
	 * @hibernate.property
	 */
	private String dType;
	/**
	 * @see 中类
	 * @hibernate.property
	 */
	private String zType;
	/**
	 * @see 小类
	 * @hibernate.property
	 */
	private String xType;

//---------------------------add 20140624 --------------------
	/**
	 * @see 联系方式
	 * @hibernate.property
	 */
	private String contact;
	/**
	 * @see 公司负责人
	 * @hibernate.property
	 */
	private String gsperson;
	/**
	 * @see 项目负责人
	 * @hibernate.property
	 */
	private String xmperson;
	/**
	 * @see A物资类别
	 * @hibernate.property
	 */
	private String Atype;
	/**
	 * @see 计划行号
	 * @hibernate.property
	 */
	private String planRowNum;
	/**
	 * @see 计划内容
	 * @hibernate.property
	 */
	private String planContent;
	/**
	 * @see 项目名称
	 * @hibernate.property
	 */
	private String projectName;
	/**
	 * 计划下达时间
	 * @hibernate.property
	 * 
	 */
	private Date planIssuedDate;
	/**
	 * 组
	 * @hibernate.many-to-one
	 * column = "gid"
	 * cascade="save-update"
	 */
	private Group group;
	/**
	 * 计划分组状态
	 * @hibernate.property
	 * 01:未分组
	 * 02:已退回
	 * 03:已分组
	 * 04:分组通过
	 * 
	 */
	private String groupStatus;
	/**
	 * 寻源审核状态
	 * @hibernate.property
	 * 01:未审核
	 * 02:已退回
	 * 03:已审核
	 * 
	 */
	private String seekSourceStatus;
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getdType() {
		return dType;
	}

	public void setdType(String dType) {
		this.dType = dType;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Set<Check> getCheck() {
		return check;
	}

	public void setCheck(Set<Check> check) {
		this.check = check;
	}

	public String getzType() {
		return zType;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}

	public void setzType(String zType) {
		this.zType = zType;
	}

	public String getxType() {
		return xType;
	}

	public void setxType(String xType) {
		this.xType = xType;
	}

	public String getArrivalAddress() {
		return arrivalAddress;
	}

	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}

	public Set<BillingDetail> getBillingDetail() {
		return billingDetail;
	}

	public void setBillingDetail(Set<BillingDetail> billingDetail) {
		this.billingDetail = billingDetail;
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}

	public Double getAcceptanceNumTotal() {
		return acceptanceNumTotal;
	}

	public void setAcceptanceNumTotal(Double acceptanceNumTotal) {
		this.acceptanceNumTotal = acceptanceNumTotal;
	}

	public Double getArrivalMoneyTotal() {
		return arrivalMoneyTotal;
	}

	public void setArrivalMoneyTotal(Double arrivalMoneyTotal) {
		this.arrivalMoneyTotal = arrivalMoneyTotal;
	}

	public Double getAcceptanceMoneyTotal() {
		return acceptanceMoneyTotal;
	}

	public void setAcceptanceMoneyTotal(Double acceptanceMoneyTotal) {
		this.acceptanceMoneyTotal = acceptanceMoneyTotal;
	}

	public Double getArrivalNumTotal() {
		return arrivalNumTotal;
	}

	public void setArrivalNumTotal(Double arrivalNumTotal) {
		this.arrivalNumTotal = arrivalNumTotal;
	}

	public Date getProcurementSigningleDate() {
		return procurementSigningleDate;
	}

	public void setProcurementSigningleDate(Date procurementSigningleDate) {
		this.procurementSigningleDate = procurementSigningleDate;
	}

	public Integer getReportCompId() {
		return reportCompId;
	}

	public void setReportCompId(Integer reportCompId) {
		this.reportCompId = reportCompId;
	}

	public Set<Plan> getChildren() {
		return children;
	}

	public void setChildren(Set<Plan> children) {
		this.children = children;
	}

	public Plan getParent() {
		return parent;
	}

	public void setParent(Plan parent) {
		this.parent = parent;
	}


	public Double getContractNum() {
		return contractNum;
	}

	public void setContractNum(Double contractNum) {
		this.contractNum = contractNum;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getItemsNum() {
		return itemsNum;
	}

	public void setItemsNum(String itemsNum) {
		this.itemsNum = itemsNum;
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

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getReportComp() {
		return reportComp;
	}

	public void setReportComp(String reportComp) {
		this.reportComp = reportComp;
	}

	public String getPlanNum() {
		return planNum;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	public Date getPlanReceiptDate() {
		return planReceiptDate;
	}

	public void setPlanReceiptDate(Date planReceiptDate) {
		this.planReceiptDate = planReceiptDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalSite() {
		return arrivalSite;
	}

	public void setArrivalSite(String arrivalSite) {
		this.arrivalSite = arrivalSite;
	}

	public String getProcurementFlag() {
		return procurementFlag;
	}

	public void setProcurementFlag(String procurementFlag) {
		this.procurementFlag = procurementFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getProcurementRemark() {
		return procurementRemark;
	}

	public void setProcurementRemark(String procurementRemark) {
		this.procurementRemark = procurementRemark;
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

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public Double getProcurementPrice() {
		return procurementPrice;
	}

	public void setProcurementPrice(Double procurementPrice) {
		this.procurementPrice = procurementPrice;
	}

	public Double getProcurementMoney() {
		return procurementMoney;
	}

	public void setProcurementMoney(Double procurementMoney) {
		this.procurementMoney = procurementMoney;
	}

	public Double getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Double salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Double getSalesRatio() {
		return salesRatio;
	}

	public void setSalesRatio(Double salesRatio) {
		this.salesRatio = salesRatio;
	}

	public Change getChange() {
		return change;
	}

	public Suppliers getSupplier() {
		return supplier;
	}

	public void setSupplier(Suppliers supplier) {
		this.supplier = supplier;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public ProcurementContract getProcurementContract() {
		return procurementContract;
	}

	public void setProcurementContract(ProcurementContract procurementContract) {
		this.procurementContract = procurementContract;
	}

	public ProcurementCheck getPc() {
		return pc;
	}

	public void setPc(ProcurementCheck pc) {
		this.pc = pc;
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

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getFacilityNameAndNum() {
		return facilityNameAndNum;
	}

	public void setFacilityNameAndNum(String facilityNameAndNum) {
		this.facilityNameAndNum = facilityNameAndNum;
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGsperson() {
		return gsperson;
	}

	public void setGsperson(String gsperson) {
		this.gsperson = gsperson;
	}

	public String getXmperson() {
		return xmperson;
	}

	public void setXmperson(String xmperson) {
		this.xmperson = xmperson;
	}

	public String getAtype() {
		return Atype;
	}

	public void setAtype(String atype) {
		Atype = atype;
	}

	public String getPlanRowNum() {
		return planRowNum;
	}

	public void setPlanRowNum(String planRowNum) {
		this.planRowNum = planRowNum;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getPlanIssuedDate() {
		return planIssuedDate;
	}

	public void setPlanIssuedDate(Date planIssuedDate) {
		this.planIssuedDate = planIssuedDate;
	}

	public String getSeekSourceStatus() {
		return seekSourceStatus;
	}

	public void setSeekSourceStatus(String seekSourceStatus) {
		this.seekSourceStatus = seekSourceStatus;
	}
	
}
