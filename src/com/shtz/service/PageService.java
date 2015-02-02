package com.shtz.service;

import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public interface PageService {
	
	public PageModel getPageModel(String hql,Object[] params,int offset,int pageSize);
	
	public PageModel getPageModel(String hql,Object param,int offset,int pageSize);
	
	public PageModel getPageModel(String hql,int offset,int pageSize);
	
}
