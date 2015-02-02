/**
 * 
 */
package com.shtz.model;

/**
 * Filename : SeekSourceDetail.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:41:58 - 2013-4-26
 *
 */
/**
 * 寻源明细
 * @author yao chang
 * @hibernate.class table="t_seekSourceDetail"   
 */
public class SeekSourceDetail {

	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 物料名称
	 * @hibernate.property
	 * not-null="true"
	 */
	private String materialName;
	/**
	 * 供应商
	 * @hibernate.many-to-one
	 * column = "suid"
	 */
	private Suppliers supplier;
	/**
	 * 寻源数量
	 * @hibernate.property
	 * not-null="true"
	 */
	private Double seekSourceQuantity;
	/**
	 * 寻源单价
	 * @hibernate.property
	 * not-null="true"
	 */
	private Double seekSourcePrice;
	/**
	 * 寻源金额
	 * @hibernate.property
	 * not-null="true"
	 */
	private Double seekSourceM;

	/**
	 * 寻源金额
	 * @hibernate.many-to-one
	 * column = "sid"
	 */
	private SeekSource seekSource;

	/**
	 * 寻源金额
	 * @hibernate.many-to-one
	 * column = "planId"
	 */
	private Plan plan;
	
	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public Double getSeekSourcePrice() {
		return seekSourcePrice;
	}

	public void setSeekSourcePrice(Double seekSourcePrice) {
		this.seekSourcePrice = seekSourcePrice;
	}

	public Suppliers getSupplier() {
		return supplier;
	}

	public void setSupplier(Suppliers supplier) {
		this.supplier = supplier;
	}

	public Double getSeekSourceM() {
		return seekSourceM;
	}



	public Double getSeekSourceQuantity() {
		return seekSourceQuantity;
	}

	public void setSeekSourceQuantity(Double seekSourceQuantity) {
		this.seekSourceQuantity = seekSourceQuantity;
	}

	public void setSeekSourceM(Double seekSourceM) {
		this.seekSourceM = seekSourceM;
	}

	public SeekSource getSeekSource() {
		return seekSource;
	}

	public void setSeekSource(SeekSource seekSource) {
		this.seekSource = seekSource;
	}
	
	
}
