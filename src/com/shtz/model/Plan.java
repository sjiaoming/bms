package com.shtz.model;

import java.util.Date;

import java.util.Set;

/**
 * �ƻ�����
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
	 * �ƻ����
	 * @hibernate.property
	 */
	private String planNum;
	/**
	 * @see �������
	 * @hibernate.property
	 */
	private String itemType;
	/**
	 * @see ���ʴ���
	 * @hibernate.property
	 */
	private String itemsNum;
	/**
	 * �豸���Ƽ�λ��
	 * @hibernate.property
	 */
	private String facilityNameAndNum;
	/**
	 * ��������
	 * @hibernate.property
	 * not-null="true"
	 */
	private String itemsName;
	/**
	 * ����ͺ�
	 * @hibernate.property
	 */
	private String modelType;
	/**
	 * ���� 
	 * @hibernate.property
	 */
	private String material;
	/**
	 * ��׼��
	 * @hibernate.property
	 */
	private String standardNum;
	/**
	 * ��λ
	 * @hibernate.property
	 */
	private String unit;
	/**
	 * ����
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Double quantity;
	/**
	 * Ԥ�㵥�ۣ�Ԫ��
	 * @hibernate.property
	 */
	private Double budgetPrice;
	/**
	 * Ԥ���Ԫ��
	 * @hibernate.property
	 */
	private Double budgetMoney;

	/**
	 * ����������
	 * @hibernate.property
	 */
	private String demandDept;
	/**
	 * Ҫ�󵽻�ʱ��
	 * @hibernate.property
	 */
	private Date arrivalDate;
	/**
	 * ������Ŀ���
	 * @hibernate.property
	 */
	private String checkItemNum;
	/**
	 * ��;
	 * @hibernate.property
	 */
	private String useFor;
	/**
	 * ԭ����
	 * @hibernate.property
	 * 
	 * 
	 */
	private String originComp;
	/**
	 * ��ע
	 * @hibernate.property
	 * 
	 * 
	 */
	private String remark;
	/**
	 * ��Ӧ��
	 * @hibernate.many-to-one
	 * column = "psuid"
	 * cascade="none"
	 */
	private Suppliers supplier;
	/**
	 * �Ƿ���
	 * @hibernate.property
	 * 
	 * 
	 */
	private String model;
	/**
	 * ʹ�õ�λ
	 * @hibernate.property
	 * 
	 * 
	 */
	private String reportComp;
	/**
	 * ʹ�õ�λID
	 * @hibernate.property
	 * 
	 * 
	 */
	private Integer reportCompId;
	
	/**
	 * �ƻ���������
	 * @hibernate.property
	 */
	private Date planReceiptDate;
	/**
	 * ��ͬǩ������
	 * @hibernate.property
	 */
	private Double contractNum;
	/**
	 * �����ص�
	 * @hibernate.property
	 * 
	 * 
	 */
	private String arrivalSite;
	/**
	 * ���״̬
	 * @hibernate.property
	 * 
	 * 
	 */
	private String procurementFlag;
	
	/**
	 * �ɹ�����
	 * @hibernate.property
	 * 
	 * 
	 */
	private String procurementWay;
	/**
	 * �ɹ���ʽ����
	 * @hibernate.many-to-one
	 * column = "pwcid"
	 */
	private ProcurementCheck pc;
	/**
	 * �ɹ�ǩ������
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date procurementDate;
	/**
	 * �ɹ�������ע
	 * @hibernate.property
	 * 
	 * 
	 */
	private String procurementRemark;

	/**
	 * ѰԴ��������
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date searchAnnouncedDate;
	/**
	 * ѰԴǩ������
	 * @hibernate.property
	 * 
	 */
	private Date searchDate;
	/**
	 * ��ִͬ�з�ʽ
	 * @hibernate.property
	 * 
	 */
	private String contractExecutionWay;
	/**
	 * ��ͬ���
	 * @hibernate.property
	 *  
	 */
	private Double contractMoney;

	/**
	 * �ɹ�����
	 * @hibernate.property
	 *
	 */
	private Double procurementPrice;
	/**
	 * �ɹ����
	 * @hibernate.property
	 * 
	 */
	private Double procurementMoney;
	/**
	 * ���۽��
	 * @hibernate.property
	 *  
	 */
	private Double salesMoney;
	
	/**
	 * ���۱���
	 * @hibernate.property
	 *  
	 */
	private Double salesRatio;
	/**
	 * @see ���۵���
	 * @hibernate.property
	 *  
	 */
	private Double salesPrice;
	/**
	 * �������
	 * @hibernate.one-to-one
	 * property-ref="plan"
	 * cascade="all"
	 */
	private Change change;
	
	/**
	 * �ɹ���ͬ
	 * @hibernate.many-to-one
	 * column = "pid"
	 * cascade="save-update"
	 */
	private ProcurementContract procurementContract;
	
	/**
	 * ���ۺ�ͬ
	 * @hibernate.many-to-one
	 * column = "sid"
	 */
	private SalesContract salesContract;
	/**
	 * ��������
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "aid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.ArrivalItems"
	 */
	private Set<ArrivalItems> arrivalItems;
	
	/**
	 * �ƻ���������
	 * @hibernate.many-to-one
	 * column = "oid"
	 */
	private Organization org;
	
	
	/**
	 * �û�
	 * @hibernate.many-to-one
	 * column = "uid"
	 */
	private User user;
	
	/**
	 * ���� ����ҵ��Ա
	 * @hibernate.many-to-one
	 * column = "psid"
	 */
	private Person person;
	/**
	 * ���� ��¼������Ϣ
	 * @hibernate.many-to-one
	 * column = "pdid"
	 */
	private Person director;
	/**
	 * ���� ���ҵ��Ա
	 * @hibernate.many-to-one
	 * column = "pmid"
	 */
	private Person manager;
	/**
	 * �ƻ���״̬
	 * @hibernate.property
	 * 01:δ����
	 * 02:�Ѳɹ�����
	 * 03:��ѰԴ
	 * 04:������
	 * 05:�Ѳɹ�
	 * 
	 */
	private String planStatus;
	/**
	 * 01 ����δ���
	 * 02 ����δͨ�����
	 * 08 ���ܼ����
	 * 09 �ڿ�δͨ�����
	 * 03 �ڿؼ����
	 * 04 ����δͨ�����
	 * 05 �������
	 * 06 ���³�δͨ�����
	 * 07 ���³������
	 * @hibernate.property
	 */
	private String checkStatus;
	/**
	 * �������
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
	 * �ɹ��ƶ�����
	 * @hibernate.property
	 * 
	 * 
	 */
	private Date procurementSigningleDate;
	/**
	 * 
	 * @hibernate.property
	 * @see ����������
	 *  
	 */
	private Double arrivalNumTotal;
	/**
	 * 
	 * @hibernate.property
	 * @see ����������
	 *  
	 */
	private Double acceptanceNumTotal;
	/**
	 * 
	 * @hibernate.property
	 * @see �����ܽ��
	 *  
	 */
	private Double arrivalMoneyTotal;
	/**
	 * 
	 * @hibernate.property
	 * @see �����ܽ��
	 *  
	 */
	private Double acceptanceMoneyTotal;
	/**
	 * ��Ʊ��ϸ
	 * @hibernate.set inverse = "true"
	 * @hibernate.key column = "plan_id"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.BillingDetail"
	 */
	private Set<BillingDetail> billingDetail;
	/**
	 * @hibernate.property
	 * @see ������ַ
	 */
	private String arrivalAddress;
	/**
	 * @see ����
	 * @hibernate.property
	 */
	private String dType;
	/**
	 * @see ����
	 * @hibernate.property
	 */
	private String zType;
	/**
	 * @see С��
	 * @hibernate.property
	 */
	private String xType;

//---------------------------add 20140624 --------------------
	/**
	 * @see ��ϵ��ʽ
	 * @hibernate.property
	 */
	private String contact;
	/**
	 * @see ��˾������
	 * @hibernate.property
	 */
	private String gsperson;
	/**
	 * @see ��Ŀ������
	 * @hibernate.property
	 */
	private String xmperson;
	/**
	 * @see A�������
	 * @hibernate.property
	 */
	private String Atype;
	/**
	 * @see �ƻ��к�
	 * @hibernate.property
	 */
	private String planRowNum;
	/**
	 * @see �ƻ�����
	 * @hibernate.property
	 */
	private String planContent;
	/**
	 * @see ��Ŀ����
	 * @hibernate.property
	 */
	private String projectName;
	/**
	 * �ƻ��´�ʱ��
	 * @hibernate.property
	 * 
	 */
	private Date planIssuedDate;
	/**
	 * ��
	 * @hibernate.many-to-one
	 * column = "gid"
	 * cascade="save-update"
	 */
	private Group group;
	/**
	 * �ƻ�����״̬
	 * @hibernate.property
	 * 01:δ����
	 * 02:���˻�
	 * 03:�ѷ���
	 * 04:����ͨ��
	 * 
	 */
	private String groupStatus;
	/**
	 * ѰԴ���״̬
	 * @hibernate.property
	 * 01:δ���
	 * 02:���˻�
	 * 03:�����
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
