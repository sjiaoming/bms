package com.shtz.util;

import com.shtz.service.AclService;

//JSTL函数，完成权限的即时认证
public class SecurityFunction {
	
	private static AclService aclService;
	/**
	 * 
	 * @param userId
	 * @param sn
	 * @param permission 0--增加   1--读取   2--修改   3--删除
	 * @return
	 */
	public static boolean method(int userId,String sn,int permission) {
		
		return aclService.hasPermissionByResourceSn(userId, sn, permission);
	}

	public void setAclService(AclService aclService) {
		SecurityFunction.aclService = aclService;
	}
}
