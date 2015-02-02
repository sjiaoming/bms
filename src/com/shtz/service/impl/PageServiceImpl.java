package com.shtz.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.service.PageService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class PageServiceImpl extends HibernateDaoSupport implements PageService {
	private PageModel pageModel;

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	@SuppressWarnings("unchecked")
	public PageModel getPageModel(String hql, Object[] params, int offset,
			int pageSize) {
		String countHql = this.getCountHql(hql);
		Query query = this.getSession().createQuery(countHql);
		if(query != null && params !=null && params.length >0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		int total = ((Long)query.uniqueResult()).intValue();
		
		query = this.getSession().createQuery(hql);
		if(query != null && params !=null && params.length >0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List list = query.list();
		pageModel.setTotal(total);
		pageModel.setList(list);
		
		return pageModel;
	}
	
	public PageModel getPageModelPlan(String hql, Object[] params, int offset,
			int pageSize) {
		String countHql = this.getCountHql(hql);
		Query query = this.getSession().createQuery(countHql);
		if(query != null && params !=null && params.length >0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		int total = ((Long)query.uniqueResult()).intValue();
		
		query = this.getSession().createQuery(hql);
		if(query != null && params !=null && params.length >0){
			for(int i=0;i<params.length;i++){
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		List list = query.list();
		pageModel.setTotal(total);
		pageModel.setList(list);
		
		return pageModel;
	}
	

	public PageModel getPageModel(String hql, Object param, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		
		return this.getPageModel(hql, new Object[]{param}, offset, pageSize);
	}

	public PageModel getPageModel(String hql, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return this.getPageModel(hql, null, offset, pageSize);
	}
	private String getCountHql(String hql){
		int index = hql.indexOf("from");
		if(index!=-1){
			return "select count(*) "+hql.substring(index);
		}
		throw new RuntimeException("error HQL");
	}
}
