package com.shtz.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Module;
import com.shtz.service.ModuleService;
import com.shtz.service.PageService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class ModuleServiceImpl extends HibernateDaoSupport implements
		ModuleService {
	private PageService service;
	
	
	public void setService(PageService service) {
		this.service = service;
	}
	public void addModule(Module module, int parentId) {
		// TODO Auto-generated method stub
		if(parentId !=0){
			module.setParent((Module)this.getHibernateTemplate().load(Module.class, parentId));
		}
		this.getHibernateTemplate().save(module);
	}

	public void deleteModule(int moduleId) {
		// TODO Auto-generated method stub
		Module m = (Module)this.getHibernateTemplate().load(Module.class, moduleId);
		
		if(m.getChildren().size()>0){
			throw new RuntimeException("有子模块");
		}
		this.getHibernateTemplate().delete(m);
	}

	public Module findModule(int moduleId) {
		return (Module) this.getSession().createQuery("from Module m where m.id="+moduleId).uniqueResult();
	}

	public PageModel findModules(int parentId, int offset, int pagesize) {
		// TODO Auto-generated method stub
		
		return service.getPageModel("from Module m where " + (parentId == 0 ? "m.parent is null " :"m.parent.id = "+parentId ), offset, pagesize);
	}
	public PageModel findModuleById(int Id, int offset, int pagesize) {
		// TODO Auto-generated method stub
		
		return service.getPageModel("from Module m where m.id = "+Id , offset, pagesize);
	}
	public PageModel findModuleByName(String name, int offset, int pagesize) {
		// TODO Auto-generated method stub
		
		return service.getPageModel("from Module m where m.name like '%"+name+"%'" , offset, pagesize);
	}
	public PageModel findAllModule(int offset, int pagesize) {
		// TODO Auto-generated method stub
		
		return service.getPageModel("from Module m order by m.orderNum", offset, pagesize);
	}

	public void modifyModule(Module module, int parentId) {
		// TODO Auto-generated method stub
		if(parentId !=0){
			module.setParent((Module)this.getHibernateTemplate().load(Module.class, parentId));
		}
		this.getHibernateTemplate().update(module);
	}
	public List<Module> findAllModules() {
		List<Module> modules=this.getSession().createQuery("from Module").list();
		return modules;
	}
	public int findMaxOrderNumByParentId(int parentId){
		int maxOrderNum = 0;
		
			if(parentId != 0)
				try {
				maxOrderNum=(Integer) this.getSession().createQuery("select max(m.orderNum) from Module m where m.parent.id=?").setParameter(0, parentId).uniqueResult();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					int temp = (Integer) this.getSession().createQuery("select m.orderNum from Module m where m.id=?").setParameter(0, parentId).uniqueResult();
					maxOrderNum = temp * 100;
				} 
				else
				try {
				maxOrderNum=(Integer) this.getSession().createQuery("select max(m.orderNum) from Module m where m.parent.id is null").uniqueResult();
				} catch (NullPointerException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					maxOrderNum = 0;
				} 
		return maxOrderNum;
	}
	public int findIdByOrderNum(int orderNum){
		int id=(Integer) this.getSession().createQuery("select m.id from Module m where m.orderNum=?").setParameter(0, orderNum).uniqueResult();
		return id;
	}
	public List<Module> findAllParentModule(){
		List<Module> parentModule=this.getSession().createQuery("from Module m where m.parent is null").list();
		return parentModule;
	}
	public void modifyModule(Module module) {
		// TODO Auto-generated method stub
		module=(Module) this.getHibernateTemplate().merge(module);
		this.getHibernateTemplate().update(module);
	}
	
	public PageModel findAllModulesByParams(Map params,int offset, int pagesize) {
		String hql = "from Module p where 1=1 ";
		if(!params.isEmpty()){
			Iterator it = params.entrySet().iterator();
			Map.Entry pa = null;
			while(it.hasNext()){
				pa = (Entry) it.next();
				String temp = pa.getKey().toString();
				if(temp.startsWith("id_")){
					hql += " and  p."+temp.substring(3)+" = "+pa.getValue();
				}else{
					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
		}
		
		
		
		return service.getPageModel(hql, offset, pagesize);
	}
}
