package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.Module;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public interface ModuleService {

	public void addModule(Module module,int parentId);
	
	public void deleteModule(int moduleId);
	
	public void modifyModule(Module module,int parentId);
	
	public Module findModule(int moduleId);
	
	public PageModel findModules(int parentId,int offset,int pagesize);
	
	public List<Module> findAllModules();
	
	public PageModel findModuleById(int Id, int offset, int pagesize);
	
	public PageModel findAllModule(int offset, int pagesize);
	
	public int findMaxOrderNumByParentId(int parentId);
	
	public int findIdByOrderNum(int orderNum);
	
	public PageModel findModuleByName(String name, int offset, int pagesize);
	
	public List<Module> findAllParentModule();
	
	public void modifyModule(Module module);
	
	public PageModel findAllModulesByParams(Map params,int offset, int pagesize);
	
}
