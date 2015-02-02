package com.shtz.model;

import java.util.Set;

/**
 * @author sjm
 * @hibernate.class table = "t_module"
 */
public class Module {
	/**
	 * @hibernate.id
	 * generator-class = "native"
	 */
	private int id;
	/**
	 * 模块名称
	 * @hibernate.property
	 * not-null="true"
	 * unique="true"
	 */
	private String name;
	/**
	 * 模块编号
	 * @hibernate.property
	 * not-null="true"
	 * unique="true"
	 */
	private String sn;
	/**
	 * 模块地址
	 * @hibernate.property
	 */
	private String url;
	/**
	 * 模块序号
	 * @hibernate.property
	 */
	private int orderNum;
	/**
	 * 父模块
	 * @hibernate.many-to-one 
	 * not-found="ignore" column="parentId"
	 */
	private Module parent;
	/**
	 * 子模块
	 * @hibernate.set inverse="true" lazy="extra"
	 * @hibernate.key column="parentId"
	 * @hibernate.one-to-many class="com.shtz.model.Module"
	 */
	private Set<Module> children;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public Module getParent() {
		return parent;
	}
	public void setParent(Module parent) {
		this.parent = parent;
	}
	public Set<Module> getChildren() {
		return children;
	}
	public void setChildren(Set<Module> children) {
		this.children = children;
	}	
}
