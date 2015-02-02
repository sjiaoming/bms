/**
 * 
 */
package com.shtz.model;

import java.util.Date;
import java.util.Set;

/**
 * Filename : SeekSource.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:36:56 - 2013-4-26
 *
 */
/**
 * 寻源
 * @author yao chang
 * @hibernate.class table="t_seekSource"   
 */
public class SeekSource {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 审批编号
	 * @hibernate.property
	 * not-null="true"
	 */
	private int checkNum;
	/**
	 * 审核状态
	 * 01 主管末审核
	 * 02 主管末通过审核
	 * 08 内控末审核
	 * 09 内控末通过审核
	 * 03 经理末审核
	 * 04 经理末通过审核
	 * 05 董事长末审核
	 * 06 董事长末通过审核
	 * 07 董事长己审核
	 * @hibernate.property
	 * not-null="true"
	 */
	private String checkStatus;
	/**
	 * 审核状态
	 * 01 未审核
	 * 02 已退回
	 * 03 已审核
	 * @hibernate.property
	 * not-null="true"
	 */
	private String leaderStatus;
	/**
	 * 提交日期
	 * @hibernate.property
	 * not-null="true"
	 * 
	 */
	private Date  submitDate;
	private Date  beginDate;
	private Date  endDate;

	/**
	 * 审核主管
	 * @hibernate.many-to-one
	 * column = "pdid"
	 */
	private Person director;
	/**
	 * 审核内控
	 * @hibernate.many-to-one
	 * column = "piid"
	 */
	private Person innerContral;
	
	/**
	 * 审核经理
	 * @hibernate.many-to-one
	 * column = "pmid"
	 */
	private Person manager;
	
	/**
	 * 提交人
	 * @hibernate.many-to-one
	 * column = "psid"
	 */
	private Person person;
	/**
	 * 审核董事长
	 * @hibernate.many-to-one
	 * column = "pcid"
	 */
	private Person chairMan;
	/**
	 * 采购方案
	 * @hibernate.many-to-one
	 * column="pchId"
	 * unique="true"
	 * cascade="none"
	 */
	private ProcurementCheck pc;
	/**
	 * @hibernate.set inverse = "true" cascade="all" order-by="planId desc"
	 * @hibernate.key column = "sid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.SeekSourceDetail"
	 */
	private Set<SeekSourceDetail> details;
	/**
	 * 提交日期
	 * @hibernate.property
	 */
	private String opinion;
	/**
	 * @hibernate.set inverse = "true" cascade="all" order-by="checkDate desc"
	 * @hibernate.key column = "ssid"
	 * @hibernate.one-to-many
	 *  class = "com.shtz.model.Check"
	 */
	private Set<Check> checks;
	
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Set<SeekSourceDetail> getDetails() {
		return details;
	}
	public Person getChairMan() {
		return chairMan;
	}
	public void setChairMan(Person chairMan) {
		this.chairMan = chairMan;
	}
	public void setDetails(Set<SeekSourceDetail> details) {
		this.details = details;
	}
	public ProcurementCheck getPc() {
		return pc;
	}
	public void setPc(ProcurementCheck pc) {
		this.pc = pc;
	}
	public int getId() {
		return id;
	}
	public String getLeaderStatus() {
		return leaderStatus;
	}
	public void setLeaderStatus(String leaderStatus) {
		this.leaderStatus = leaderStatus;
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
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Person getInnerContral() {
		return innerContral;
	}
	public void setInnerContral(Person innerContral) {
		this.innerContral = innerContral;
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
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
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
	public Set<Check> getChecks() {
		return checks;
	}
	public void setChecks(Set<Check> checks) {
		this.checks = checks;
	}
	
}
