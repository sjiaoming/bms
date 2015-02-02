package com.shtz.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.ContractCredit;
import com.shtz.model.ProcurementContract;
import com.shtz.model.User;
import com.shtz.service.PageService;
import com.shtz.service.ProcurementContractService;
import com.shtz.util.PageModel;

public class ProcurementContractServiceImpl extends HibernateDaoSupport implements ProcurementContractService {
	private PageService service;
	
	
	public void setService(PageService service) {
		this.service = service;
	}

	public PageModel searchPlans(int offset, int pageSize) {
		return service.getPageModel("from Plan where procurementContract is null order by planRowNum asc", offset, pageSize);
	}
//	public PageModel serchsearchPlansByParams(Map params,int offset, int pageSize){
//		String hql = "from Plan p where p.procurementContract is null ";
//		if(!params.isEmpty()){
//			Iterator it = params.entrySet().iterator();
//			Map.Entry pa = null;
//			while(it.hasNext()){
//				pa = (Entry) it.next();
//				String temp = pa.getKey().toString();
//				if(temp.startsWith("id_")){
//					hql += " and  p."+temp.substring(3)+" = "+pa.getValue();
//				}else{
//					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
//				}
//			}
//		}
//		
//		
//		
//		return service.getPageModel(hql, offset, pageSize);
//	}
	
	public PageModel serchsearchPlansByParams(Map params,int offset, int pageSize){
		String hql = "from Plan p where 1=1 ";
		if(!params.isEmpty()){
			Iterator it = params.entrySet().iterator();
			Map.Entry pa = null;
			while(it.hasNext()){
				pa = (Entry) it.next();
				String temp = pa.getKey().toString();
				if(temp.startsWith("id_")){
					hql += " and  p."+temp.substring(3)+" = "+pa.getValue();
				}else if(temp.startsWith("eq_")){
					hql += " and  p."+temp.substring(3)+" = '"+pa.getValue()+"'";
				}else if(temp.startsWith("in_")){
					hql += " and  p."+temp.substring(3)+" in ("+pa.getValue()+")";
				}else if(temp.startsWith("diy_")){
					hql += " and  p."+temp.substring(4)+" "+pa.getValue();
				}else if(temp.startsWith("obj_")){
					
					User u = (User)params.get("obj_user");
					if(u!=null)
						hql += " and p.person.id in ("+u.getAuth()+")";
				}else{
					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
		}
		hql = hql +" order by p.planRowNum asc";
		
		
		return service.getPageModel(hql, offset, pageSize);
	}
	
	
	public void addProcurementContract(ProcurementContract procurementContract){
		
		this.getHibernateTemplate().save(procurementContract);
		
	}
	public void addContractCredit(ContractCredit contractCredit){
		this.getHibernateTemplate().save(contractCredit);
	}
	
	public ProcurementContract findProcurementContractById(int id){
		return (ProcurementContract)this.getHibernateTemplate().get(ProcurementContract.class, id);
	}
	public List getPlanArrival(int planId){
		List l = new ArrayList();
		l = this.getSession().createSQLQuery("select sum(a.arrivalNum) as arrivalNum,sum(a.acceptanceNum) as acceptanceNum from t_arrivalitems a where a.aid = "+planId+" group by a.aid").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		return l;
	}
	public List<?> findProcurementContractByContractNum(String contractNum){
		return this.getSession().createQuery("from ProcurementContract p where p.contractNum = ?").setParameter(0, contractNum).list();
	}
	public List<?> findArrivalsIdByPlanids(String ids){
		return this.getSession().createSQLQuery("select a.id from t_arrivalitems a where a.aid in ("+ids+")").list();
	}
	public String testdwr(){
		System.out.println("sssssssss");
		return "ssssssssss";
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementContractService#getPCCountByPersonId(int)
	 */
	@Override
	public Integer getPCCountByPersonId(int personId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) From Plan p where p.planStatus='03' and p.checkStatus='07' " +
					 " and p.seekSourceStatus='03' and p.person.id = " + personId ;
		Long count = (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count == null){
			return 0;
		}else{
			return count.intValue();
		}
	}
}
