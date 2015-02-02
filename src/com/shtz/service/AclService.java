package com.shtz.service;

import java.util.List;

import com.shtz.model.Module;

/**
 * @author sjm
 *  
 */
public interface AclService {
	/**
	 * 授权
	 * @param principalType 主体类型
	 * @param principalSn 主体编号
	 * @param resourceSn 资源编号
	 * @param permission CRUD
	 * @param yes 是否允许
	 */
	public void addOrModifyPermission(String principalType,int principalSn,
			int resourceSn,int permission,boolean yes);
	
	public void deletePermission(String principalType,int principalSn,
			int resourceSn);
	/**
	 * add or modify 用户继承特性
	 * @param userId
	 * @param resourceSn
	 * @param yes 是否允许继承
	 */
	public void addOrModifyUserExtends(int userId,int resourceSn,boolean yes);
	/**
	 * 认证，判断用户对某资源的某操作是否有效
	 * @param userId
	 * @param resourceSn
	 * @param permission
	 * @return true or false
	 */
	public boolean  hasPermission(int userId,int resourceSn,int permission);
	/**
	 * 查询用户所拥有读取权限的模块列表
	 * @param userId
	 * @return 模块列表
	 */
	public List<Module> searchModules(int userId);
	//初始化表格
	public List<?> searchAclRecord(String principalType, int principalSn);
	//即时认证
	public boolean hasPermissionByResourceSn(int userId, String sn, int permission);
	//dwr调用方法
	public boolean hasPermission1(int permission);
	
	public void addOrModifyDataPermission(int personId,int resourceSn,boolean yes);
	
	//初始化表格
	public String[] searchDataAclRecord(int personId,int orgId,String name);
	
	
}
