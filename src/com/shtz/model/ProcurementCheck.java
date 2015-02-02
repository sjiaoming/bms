/**
 * 
 */
package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * Filename : procurementCheck.java
 *
 * @author yao chang
 *
 * Creation time : 下午11:32:25 - 2013-4-20
 *
 */
public class ProcurementCheck {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 采购方式审核编号
	 * @hibernate.property
	 */
	private int checkNum;
	/**
	 * 供应商ids
	 * @hibernate.property
	 */
	private String supplierIds;
	private String supplierNames;
	/**
	 * 采购方案名称
	 * @hibernate.property
	 */
	private String procurementName ;
	/**
	 * 方案号
	 * @hibernate.property
	 */
	private String caseNum ;
	/**
	 * 包号
	 * @hibernate.property
	 */
	private String packageNum ;
	/**
	 * 采购方式
	 * @hibernate.property
	 * 01  公开招标
	 * 02  邀请招标
	 * 03  竞争性谈判
	 * 04  单一来源
	 * 05  询比价
	 */
	private String procurementWay;
	/**
	 * 提交日期
	 * @hibernate.property
	 */
	private Date submitDate;
	/**
	 * 查询用日期
	 * 
	 */
	private Date beginDate;
	private Date endDate;
	/**
	 * 填写人
	 * @hibernate.many-to-one
	 * column = "ssid"
	 */
	private Person seekSourceOperator;
	/**
	 * 填写人
	 * @hibernate.many-to-one
	 * column = "pid"
	 */
	private Person person;
	/**
	 * 审核内控
	 * @hibernate.many-to-one
	 * column = "piid"
	 */
	private Person innerContral;
	/**
	 * 01 主管末审核
	 * 02 主管末通过审核
	 * 
	 * 08 内控末审核
	 * 09 内控末通过审核
	 * 
	 * 03 经理末审核
	 * 04 经理末通过审核
	 * 
	 * 05 董事长末审核
	 * 06 董事长末通过审核
	 * 07 董事长己审核
	 * @hibernate.property
	 */
	private String checkStatus;
	/**
	 * 审核意见
	 * @hibernate.property
	 */
	private String opinion;
	
	/**
	 * 是否寻源
	 * @hibernate.property
	 */
	private String finishSeekSource;
	/**
	 * 备注
	 * @hibernate.property
	 */
	private String memo;

	/**
	 * 计划明细
	 * @hibernate.set inverse = "true" order-by="modelType asc,quantity asc"
	 * @hibernate.key column = "pwcid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Plan"
	 */
	private Set<Plan> plans;

	/**
	 * 审核明细
	 * @hibernate.set inverse = "true" order-by="id desc"
	 * @hibernate.key column = "pcid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Check"
	 */
	private Set<Check> checks;
	/**
	 * @hibernate.one-to-one
	 * property-ref="pc"
	 * cascade="all"
	 **/
	private SeekSource seekSource;

	/**
	 * 审核主管
	 * @hibernate.many-to-one
	 * column = "pdid"
	 */
	private Person director;
	/**
	 * 审核经理
	 * @hibernate.many-to-one
	 * column = "pmid"
	 */
	private Person manager;
	/**
	 * 审核董事长
	 * @hibernate.many-to-one
	 * column = "pcid"
	 */
	private Person chairMan;

	/**
	 * 组
	 * @hibernate.many-to-one
	 * column = "bgid"
	 * cascade="save-update"
	 */
	private BusinessGroup group;
	/**
	 * 采购方案分组状态
	 * @hibernate.property
	 * 01:未分组
	 * 02:已退回
	 * 03:已分组
	 * 04:已分配
	 */
	private String groupStatus;
	
	public Person getSeekSourceOperator() {
		return seekSourceOperator;
	}
	public void setSeekSourceOperator(Person seekSourceOperator) {
		this.seekSourceOperator = seekSourceOperator;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Person getInnerContral() {
		return innerContral;
	}
	public void setInnerContral(Person innerContral) {
		this.innerContral = innerContral;
	}
	public Person getChairMan() {
		return chairMan;
	}
	public void setChairMan(Person chairMan) {
		this.chairMan = chairMan;
	}
	public String getProcurementName() {
		return procurementName;
	}
	public void setProcurementName(String procurementName) {
		this.procurementName = procurementName;
	}
	public String getFinishSeekSource() {
		return finishSeekSource;
	}
	public void setFinishSeekSource(String finishSeekSource) {
		this.finishSeekSource = finishSeekSource;
	}
	public Person getDirector() {
		return director;
	}
	public void setDirector(Person director) {
		this.director = director;
	}
	public Person getManager() {
		return manager;
	}
	public void setManager(Person manager) {
		this.manager = manager;
	}
	public SeekSource getSeekSource() {
		return seekSource;
	}
	public void setSeekSource(SeekSource seekSource) {
		this.seekSource = seekSource;
	}
	public Set<Plan> getPlans() {
		return plans;
	}
	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}
	public Set<Check> getChecks() {
		return checks;
	}
	public void setChecks(Set<Check> checks) {
		this.checks = checks;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCheckNum() {
		return checkNum;
	}
	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	public String getSupplierIds() {
		return supplierIds;
	}
	public void setSupplierIds(String supplierIds) {
		this.supplierIds = supplierIds;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSupplierNames() {
		return supplierNames;
	}
	public void setSupplierNames(String supplierNames) {
		this.supplierNames = supplierNames;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public String getProcurementWay() {
		return procurementWay;
	}
	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getPackageNum() {
		return packageNum;
	}
	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}
	public BusinessGroup getGroup() {
		return group;
	}
	public void setGroup(BusinessGroup group) {
		this.group = group;
	}
	public String getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
}
