package com.shtz.service.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.ArrivalItems;
import com.shtz.model.BillingDetail;
import com.shtz.model.Plan;
import com.shtz.service.ArrivalItemsService;
import com.shtz.service.PlanService;
import com.shtz.util.Arith;

public class ArrivalItemsServiceImpl extends HibernateDaoSupport implements ArrivalItemsService {
	private PlanService pservice;
	private Arith arithService;
	
	
	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}
	public void setArithService(Arith arithService) {
		this.arithService = arithService;
	}
	public void addArrival(ArrivalItems arrivalItems){
		this.getHibernateTemplate().save(arrivalItems);
	}
	public ArrivalItems getArrivalItemById(int id){
		return (ArrivalItems)this.getHibernateTemplate().load(ArrivalItems.class, id);
	}
	public void modifyArrivalItem(ArrivalItems arrivalItems){
		//this.getSession().merge(arrivalItems);
		//this.getHibernateTemplate().update(arrivalItems);
		//this.getSession().createSQLQuery("update t_arrivalitems t set t.baid = "+arrivalItems.getBilling().getId()+" ,t.billingstatus = "+arrivalItems.getBillingStatus()+" where t.id = "+arrivalItems.getId()).executeUpdate();
		this.getSession().createSQLQuery("update t_arrivalitems t set t.billingstatus = "+arrivalItems.getBillingStatus()+",t.billingNum="+arrivalItems.getBillingNum()+" where t.id = "+arrivalItems.getId()).executeUpdate();
	}
	
	public void modifyArrivalItemDelBill(int id){
		this.getSession().createSQLQuery("update t_arrivalitems t set t.baid = null,t.billingstatus = null where t.id = "+id).executeUpdate();
	}
	public void deleteArrivalItems(int id){
		ArrivalItems a = (ArrivalItems)this.getHibernateTemplate().load(ArrivalItems.class, id);
		Plan p = a.getPlan();
		double arrivalNumTotal = p.getArrivalNumTotal()==null?0.0:p.getArrivalNumTotal();
		double acceptanceNumTotal = p.getAcceptanceNumTotal()==null?0.0:p.getAcceptanceNumTotal();
		double arrivalMoneyTotal = p.getArrivalMoneyTotal()==null?0.0:p.getArrivalMoneyTotal();
		double acceptanceMoneyTotal = p.getAcceptanceMoneyTotal()==null?0.0:p.getAcceptanceMoneyTotal();
		double arrivalNum = a.getArrivalNum()==null?0.0:a.getArrivalNum();
		double acceptanceNum = a.getAcceptanceNum()==null?0.0:a.getAcceptanceNum();
		p.setArrivalNumTotal(arithService.sub(arrivalNumTotal,arrivalNum));
		p.setArrivalMoneyTotal(arithService.sub(arrivalMoneyTotal,arithService.mul(arrivalNum, p.getProcurementPrice())));
		p.setAcceptanceNumTotal(arithService.sub(acceptanceNumTotal,acceptanceNum));
		p.setAcceptanceMoneyTotal(arithService.sub(acceptanceMoneyTotal,arithService.mul(acceptanceNum, p.getProcurementPrice())));
		pservice.modifyPlanByObj(p);
		this.getHibernateTemplate().delete(a);
	}
	public void addcfArrivalItems(int id,Double[] count){
		ArrivalItems a = this.getArrivalItemById(id);
		double tempAcceptanceNum = a.getAcceptanceNum();
		if(a.getBilling() == null){
			for(int i=0;i<count.length;i++){
				ArrivalItems ai = new ArrivalItems();
				ai.setAcceptanceDate(a.getAcceptanceDate());
				if(count[i] >= tempAcceptanceNum){
					ai.setAcceptanceNum(tempAcceptanceNum);
					tempAcceptanceNum = 0.0;
				}else{
					ai.setAcceptanceNum(count[i]);
					double tempac = arithService.sub(tempAcceptanceNum, count[i]);
					tempAcceptanceNum = tempac > 0 ? tempac : 0;
				}
				
				ai.setArrivalDate(a.getArrivalDate());
				ai.setArrivalNum(count[i]);
				ai.setPlan(a.getPlan());
//				this.addArrival(ai);
				this.getHibernateTemplate().save(ai);
			}
		
		}
		this.deleteArrivalItems(a.getId());
	}
}
