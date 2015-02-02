package com.shtz.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Change;
import com.shtz.model.Organization;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementContract;
import com.shtz.model.ProcurementContractBean;
import com.shtz.model.ReportCompStatisticsBean;
import com.shtz.model.SalesContract;
import com.shtz.model.User;
import com.shtz.service.PageService;
import com.shtz.service.PlanService;
import com.shtz.util.Arith;
import com.shtz.util.PageModel;
import com.shtz.util.StatusName;

public class PlanServiceImpl extends HibernateDaoSupport implements PlanService {
	private PageService service;
	
	private Arith arithService;
	
	private PageModel pageModel;
	
	private StatusName statusName;
	
	

	public void setStatusName(StatusName statusName) {
		this.statusName = statusName;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	
	public void setArithService(Arith arithService){
		this.arithService = arithService;
	}
	
	public void setService(PageService service) {
		this.service = service;
	}

	public void addPlan(Plan plan, int orgId) {

		if(orgId == 0){
			throw new RuntimeException("部门不允许为空！");
		}
		
		plan.setOrg(
				(Organization)getHibernateTemplate().load(Organization.class, orgId)
			);
		getHibernateTemplate().save(plan);
	}
	public void addPlans(Plan plan) {
		getHibernateTemplate().save(plan);
	}
	public void deletePlan(int planId) {
		getHibernateTemplate().delete(
				getHibernateTemplate().load(Plan.class, planId)
			);
	}

	public Plan findPlan(int planId) {
		return (Plan)getHibernateTemplate().load(Plan.class, planId);
	}

	public PageModel searchPlans(int offset, int pageSize) {
		return service.getPageModel("from Plan  where procurementContract is null order by planNum , planRowNum  asc", offset, pageSize);
	}
	public PageModel serchsearchPlansByParams(Map params,int offset, int pageSize){
		String hql = "from Plan p ";
		hql = this.getParamsSql(hql, params, " order by p.planNum,p.planRowNum asc,p.planReceiptDate asc,p.reportCompId");
		
		return service.getPageModel(hql, offset, pageSize);
	}
	public PageModel searchPlansByOrg(int orgId,int offset, int pageSize) {
		return service.getPageModel("select p from Plan p where p.org.id = "+orgId+" order by p.planNum,p.planRowNum asc", offset, pageSize);
	}
	
	public void addProcurementWay(String[] tags,String procurementWay,Date procurementDate,String procurementRemark,String planStatus){
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		int id = 0;
		Plan p = new Plan();
		for(String temp : tags){
			id = Integer.parseInt(temp);
			p =(Plan)this.getHibernateTemplate().load(Plan.class, id);
			if(procurementWay != null)
				p.setProcurementWay(procurementWay);
			if(procurementDate != null)
				p.setProcurementDate(procurementDate);
			if(procurementRemark != null)
				p.setProcurementRemark(procurementRemark);
			p.setPlanStatus(planStatus);
			//System.out.println(new Date());
			if(p.getProcurementSigningleDate() == null)
				p.setProcurementSigningleDate(new Date());
			this.getHibernateTemplate().update(p);
		}
	}
	
	public void modifyPlanChange(String planId,Double changenum,double changecontractMoney,double planPrice){
		int id =Integer.parseInt(planId);
			Plan p  =(Plan)this.getHibernateTemplate().load(Plan.class, id);
			p.setQuantity(changenum);
			p.setBudgetMoney(changecontractMoney);
			p.setBudgetPrice(planPrice);
			p.setProcurementFlag("02");
			//p.setContractMoney(changecontractMoney);
			this.getHibernateTemplate().update(p);
	}
	
	public void addSeekSource(String[] tags,Map m){
		int id = 0;
		for(String temp : tags){
			Plan p = new Plan();
			id = Integer.parseInt(temp);
			p =(Plan)this.getHibernateTemplate().load(Plan.class, id);
			if(m.get("searchAnnouncedDate")!=null)
				p.setSearchAnnouncedDate((Date)m.get("searchAnnouncedDate"));
			if(m.get("searchDate")!=null)
				p.setSearchDate((Date)m.get("searchDate"));
			if(m.get("contractExecutionWay")!=null)
				p.setContractExecutionWay((String)m.get("contractExecutionWay"));
			p.setContractMoney((Double)m.get("contractMoney"));
			p.setPlanStatus("03");
			this.getHibernateTemplate().update(p);
		}
			
	}
	public void modifySeekSource(int id,Map m){
			Plan p =(Plan)this.getHibernateTemplate().load(Plan.class, id);
		if(m.get("searchAnnouncedDate")!=null)
			p.setSearchAnnouncedDate((Date)m.get("searchAnnouncedDate"));
		if(m.get("searchDate")!=null)
			p.setSearchDate((Date)m.get("searchDate"));
		if(m.get("contractExecutionWay")!=null)
			p.setContractExecutionWay((String)m.get("contractExecutionWay"));
		p.setContractMoney((Double)m.get("contractMoney"));
			this.getHibernateTemplate().update(p);
			
	}

	public void addOrUpdageChange(Plan p ,Date changeTime,Double changenum,double changebudget,String changereason,double planPrice,String name){
		Change ch=(Change) this.getSession().createQuery("from Change c where c.plan.id="+p.getId()).uniqueResult();
		if(ch == null){
			ch = new Change();
			ch.setChangeTime(changeTime);
			ch.setNum(changenum);
			ch.setContractMoney(changebudget);
			ch.setReason(changereason);
			ch.setPlanPrice(planPrice);
			ch.setPlan(p);
			ch.setPersonName(name);
			this.getHibernateTemplate().save(ch);
		}else{
			ch.setChangeTime(changeTime);
			ch.setNum(changenum);
			ch.setContractMoney(changebudget);
			ch.setReason(changereason);
			ch.setPlanPrice(planPrice);
			ch.setPlan(p);
			ch.setPersonName(name);
			this.getHibernateTemplate().update(ch);
		}
	}

	public void modifyPlan(Plan plan){
		Plan p=new Plan();
		p=this.findPlan(plan.getId());
		if(p!=null){
			if(plan.getReportComp()!=null && !"".equals(plan.getReportComp()))
				p.setReportComp(plan.getReportComp());
//			if(plan.getOldPlanNum()!=null && !"".equals(plan.getOldPlanNum()))
//				p.setOldPlanNum(plan.getOldPlanNum());
			if(plan.getItemsName()!=null && !"".equals(plan.getItemsName()))
				p.setItemsName(plan.getItemsName());
			if(plan.getModel()!=null && !"".equals(plan.getModel()))
				p.setModel(plan.getModel());
			if(plan.getPlanNum()!=null && !"".equals(plan.getPlanNum()))
				p.setPlanNum(plan.getPlanNum());
			if(plan.getProcurementWay()!=null && !"".equals(plan.getProcurementWay()))
				p.setProcurementWay(plan.getProcurementWay());
			if(plan.getProcurementDate()!=null && !"".equals(plan.getProcurementDate()))
				p.setProcurementDate(plan.getProcurementDate());
			if(plan.getProcurementRemark()!=null && !"".equals(plan.getProcurementRemark()))
				p.setProcurementRemark(plan.getProcurementRemark());
			//p=(Plan) this.getHibernateTemplate().merge(p);
			this.getHibernateTemplate().update(p);
		}
	}
	public void modifyPlanMg(Plan plan){
		Plan p=new Plan();
		p=this.findPlan(plan.getId());
		if(p!=null){
			p.setPlanReceiptDate(plan.getPlanReceiptDate());
//			p.setOldPlanNum(plan.getOldPlanNum());
			p.setItemsNum(plan.getItemsNum());
			p.setItemsName(plan.getItemsName());
			p.setProcurementPrice(plan.getProcurementPrice());
//			p.setNum(plan.getNum());
			p.setUnit(plan.getUnit());
			p.setProcurementMoney(plan.getProcurementMoney());
			p.setArrivalDate(plan.getArrivalDate());
			p.setReportComp(plan.getReportComp());
			p.setRemark(plan.getRemark());
			//p=(Plan) this.getHibernateTemplate().merge(p);
			this.getHibernateTemplate().update(p);
		}
	}
	public List getPlans(Integer[] ids){
		//Query  q =this.getSession().createQuery("select p from Plan p where p.id in (:ids)").setParameterList("ids",ids);
		//System.out.println(q.list().size());
		
		return this.getSession().createQuery("select p from Plan p where p.id in (:ids)").setParameterList("ids",ids).list();
		
	}
	public void modifyPlanPM(Plan plan){
		Plan p=this.findPlan(plan.getId());
		if(p!=null){
				if(plan.getProcurementContract()!=null)
					p.setProcurementContract(plan.getProcurementContract());
				if(plan.getPlanStatus()!=null&&!"".equals(plan.getPlanStatus()))
					p.setPlanStatus(plan.getPlanStatus());
				if(plan.getContractNum()!=null)
					p.setContractNum(plan.getContractNum());
				if(plan.getSearchDate()!=null)
					p.setSearchDate(plan.getSearchDate());
				if(plan.getProcurementPrice()!=null)
					p.setProcurementPrice(plan.getProcurementPrice());
				if(plan.getProcurementMoney()!=null)
					p.setProcurementMoney(plan.getProcurementMoney());
				if(plan.getSearchAnnouncedDate()!=null)
					p.setSearchAnnouncedDate(plan.getSearchAnnouncedDate());
				if(plan.getContractExecutionWay()!=null)
					p.setContractExecutionWay(plan.getContractExecutionWay());
			}
			this.getHibernateTemplate().update(p);
		
	}
	public void modifyPlanClearSeekDate(Plan plan){
		Plan p=this.findPlan(plan.getId());
		if(p!=null){
				p.setSearchDate(null);
				p.setProcurementPrice(null);
				p.setProcurementMoney(null);
				p.setSearchAnnouncedDate(null);
				p.setContractExecutionWay(null);
				p.setPlanStatus("02");
			this.getHibernateTemplate().update(p);
		}
	}
	public Organization findOrgByNames(String orgName) {
		String hql="from Organization o where o.name=?";
		Organization o=(Organization) this.getSession().createQuery(hql).setParameter(0, orgName).uniqueResult();
		if(o == null)
			return null;
		return  o;
	
	}
	public List getPlanListByOldPlanNum(String oldPlanNum,String planNum){
		return this.getSession().createQuery("from Plan p where p.oldPlanNum = ? and p.planNum = ?").setParameter(0, oldPlanNum).setParameter(1, planNum).list();
	}
	public User findUserByName(String userName){
		User u = (User)this.getSession().createQuery("select u from User u where u.username = ?")
				.setParameter(0, userName).uniqueResult();
		if(u == null)
			return null;
		return u;
	}
	
	public Person findPersonByName(String userName,Organization org){
		Person u = (Person)this.getSession().createQuery("select p from Person p where p.name = ? and p.org = ?")
				.setParameter(0, userName).setParameter(1, org).uniqueResult();
		if(u == null)
			return null;
		return u;
	}
	//修改
	public void modifyPlan(int pid, double salesRatio, double procurementMoney) {
		Plan p=(Plan) this.getHibernateTemplate().load(Plan.class, pid);
		p.setSalesRatio(salesRatio);
		p.setContractMoney(salesRatio*procurementMoney);
		p.setSalesMoney(salesRatio*procurementMoney);
		this.getHibernateTemplate().update(p);
		
	}

	public void modifyPlanSalesMoney(int pid, double salesMoney) {
		Plan p=(Plan) this.getHibernateTemplate().load(Plan.class, pid);
		p.setContractMoney(salesMoney);
		this.getHibernateTemplate().update(p);
	}
	
	public int getPlanCountByDate(String s,String e,Map params){
		//String sql = "select count(p.id) from Plan p where p.planReceiptDate >= '"+s+"'  and p.planReceiptDate <= '"+e+"' and p.parent is null";
		String sql = "select count(p.id) from Plan p ";
		params.put("dy_p.parent ", " is null ");
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		Long a = (Long)this.getSession().createQuery(sql).uniqueResult();
		return a.intValue();
	}
	public Double getPlanMoneyByDate(String s,String e,Map params){
		String sql = "select sum(p.budget) from Plan p ";
		params.put("dy_p.parent ", " is null ");
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	public int getProcurementContractCountByDate(String s,String e,Map params){
		String sql = "select count(p.id) from ProcurementContract p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		Long a = (Long)this.getSession().createQuery(sql).uniqueResult();
		return a.intValue();
	}
	public int getPlanXYCountByDate(String s,String e,Map params){
		String sql = "select count(p.id) from Plan p ";
		params.put("dy_p.parent ", " is null ");
		params.put("dy_p.searchDate ", " is not null ");
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.planReceiptDate", " <=  '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		Long a = (Long)this.getSession().createQuery(sql).uniqueResult();
		return a.intValue();
	}
	public Double getPlanXYMoneyByDate(String s,String e,Map params){
		String sql = "select sum(p.procurementPrice * p.num) from Plan p  ";
		params.put("dy_p.parent ", " is null ");
		params.put("dy_p.searchDate ", " is not null ");
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.planReceiptDate",  " <=  '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	public int getPlanDXYCountByDate(String s,String e,Map params){
		String sql = "select count(p.id) from Plan p  ";
		params.put("dy_p.parent ", " is null ");
		params.put("dy_p.searchDate ", " is null ");
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		Long a = (Long)this.getSession().createQuery(sql).uniqueResult();
		return a.intValue();
	}
	public Double getPlanDXYMoneyByDate(String s,String e,Map params){
		String sql = "select sum(p.budget) from Plan p  ";
		params.put("dy_p.parent ", " is null ");
		params.put("dy_p.searchDate ", " is null ");
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	
	public Double getProcurementMoneyCountByDate(String s,String e,Map params){
		String sql = "select sum(p.totalMoney) from ProcurementContract p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	
	public Double getPlanArrivalMoneyByDate(String s,String e,Map params){
		//String sql = "select sum(a.plan.procurementPrice * a.arrivalNum) from ArrivalItems p where p.arrivalDate >= '"+s+"'  and p.arrivalDate <= '"+e+"' ";
		String sql = "select sum(p.plan.procurementPrice * p.arrivalNum) from ArrivalItems p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.plan.planReceiptDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	public int getSalesContractCountByDate(String s,String e,Map params){
		String sql = "select count(p.id) from SalesContract p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		Long a = (Long)this.getSession().createQuery(sql).uniqueResult();
		return a.intValue();
	}
	public Double getSalesContractMoneyByDate(String s,String e,Map params){
		String sql = "select sum(p.contractMoney) from SalesContract p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	public Double getContractCreditMoneyByDate(String s,String e,Map params){
		String sql = "select sum(p.contractCreditMoney) from ContractCredit p  ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.procurementContract.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	public List<ProcurementContract> getProcurementsByDate(String s,String e,Map params){
		String sql = "from ProcurementContract p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return this.getSession().createQuery(sql).list();
	}
	public List<SalesContract> getSalesContractsByDate(String s,String e,Map params){
		String sql = "from SalesContract p ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return this.getSession().createQuery(sql).list();
	}
	public Double getbillingMoneyByDate(String s,String e,Map params){
		String sql = "select sum(p.billingMoney) from Billing p  ";
		if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
			params.put("dy_p.salesContract.signingDate", " between '"+s+"' and '"+e+"'");
		}
		sql = this.getParamsSql(sql, params, "");
		return (Double)this.getSession().createQuery(sql).uniqueResult();
	}
	public Double getJzByDate(String s,String e,Map params){
		 double temp1;
		 double temp2;
		 Double q;
		 String sql = "select sum(p.budget) from Plan p ";
			params.put("dy_p.parent ", " is null ");
			params.put("dy_p.searchDate ", " is not null ");
			if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
				params.put("dy_p.planReceiptDate", " between '"+s+"' and '"+e+"'");
			}
			sql = this.getParamsSql(sql, params, "");
		q = (Double)this.getSession().createQuery(sql).uniqueResult();
		
		if(q == null)
			temp1 = 0;
		else
			temp1 = (double)q;
		
		 String sql1 = "select sum(p.procurementPrice * p.num) from Plan p ";
			params.put("dy_p.parent ", " is null ");
			params.put("dy_p.searchDate ", " is not null ");
			if(s!=null && !"".equals(s.trim())  && e!=null && !"".equals(e.trim()) ){
				params.put("dy_p.planReceiptDate", " between '"+s+"' and '"+e+"'");
			}
			sql1 = this.getParamsSql(sql1, params, "");
		q = (Double)this.getSession().createQuery(sql1).uniqueResult();
		if(q == null)
			temp2 = 0;
		else
			temp2 = (double)q;
		
		return arithService.sub(temp1,temp2);
	}
	public void addcfPlan(int id,Double[] s,String cflag){
		//List<Plan> l = new ArrayList<Plan>();
		
		int tempId = id;
		Plan p = this.findPlan(tempId);

		if(s.length>0){
			for(int i=0;i<s.length;i++){
				Plan p1 = new Plan();
				p1.setItemsName(p.getItemsName());
//				p1.setOldPlanNum(p.getOldPlanNum());
				p1.setReportComp(p.getReportComp());
				p1.setReportCompId(p.getReportCompId());
				p1.setPlanNum(p.getPlanNum());
				p1.setPlanReceiptDate(p.getPlanReceiptDate());
				p1.setUnit(p.getUnit());
				p1.setArrivalDate(p.getArrivalDate());
//				p1.setBudget(p.getBudget());
				p1.setProcurementWay(p.getProcurementWay());
				p1.setProcurementDate(p.getProcurementDate());
				p1.setProcurementRemark(p.getProcurementRemark());
				p1.setSearchAnnouncedDate(p.getSearchAnnouncedDate());
				p1.setSearchDate(p.getSearchDate());
				p1.setContractExecutionWay(p.getContractExecutionWay());
				//p1.setContractMoney(p.getContractMoney());
//				p1.setPlanPrice(p.getPlanPrice());
				p1.setPlanStatus(p.getPlanStatus());
				p1.setOrg(p.getOrg());
				p1.setItemsNum(p.getItemsNum());
				p1.setPerson(p.getPerson());
				p1.setUser(p.getUser());
				p1.setModel("01");
				p1.setProcurementPrice(p.getProcurementPrice());
				p1.setProcurementMoney(p.getProcurementPrice()*s[i]);
				p1.setProcurementSigningleDate(p.getProcurementSigningleDate());
				
				/**
				 * if 采购合同拆分，拆分的是计划的数量
				 * else 销售合同拆分，拆分的是计划的合同数量
				 */
				if(cflag!=null && !"".equals(cflag) && cflag.equals("pcontract")){
//					p1.setNum(s[i]);
				}
				else{
//					p1.setNum(p.getNum());
					p1.setContractNum(s[i]);
				}
					
				
				if(p.getParent() ==null){
					p1.setParent(p);
				}else{
					p1.setParent(p.getParent());
				}
//				p1.setOldPlanNum(p1.getOldPlanNum());
				this.addPlans(p1);
				
//				if(p.getParent() ==null){
//					p1.setOldPlanNum(p1.getOldPlanNum()+"_"+p1.getId());
//				}else{
//					p1.setOldPlanNum(p1.getParent().getOldPlanNum()+"_"+p1.getId());
//				}
//				this.getHibernateTemplate().update(p1);
			}
			//如果拆分的是已经拆分过的计划，就删除。
			if(p.getParent()!=null){
				this.getHibernateTemplate().delete(p);
			}else{
				p.setModel("02");
				this.getHibernateTemplate().update(p);
			}
		}
	}
	public PageModel getPlanStatusList(int offset,int pageSize,Map params,boolean isLimit){
//		String hql = "from Plan p left join p.pc pch left join p.salesContract s left join p.procurementContract pct";
//		String countHql = "select count(*) from Plan p left join p.pc left join p.salesContract left join p.procurementContract";
		String hql = "select p from Plan p ";
		String countHql = "select count(*) from Plan p ";
		String totalSql = "select sum(p.budgetMoney) as totalBudgetMoney,sum(p.procurementMoney) as totalProcurementMoney," +
						" sum(p.arrivalNumTotal) as totalArrivalNum,sum(p.arrivalMoneyTotal) as totalArrivalMoney, sum(p.acceptanceNumTotal) as totalAcceptanceNum," +
						" sum(p.acceptanceMoneyTotal) as totalAcceptanceMoney from t_plan p " ;
				
		String lastSql = "  group by p.id order by p.planNum asc,p.planReceiptDate asc";
//		String lastSql = "  group by p.id order by p.planRowNum asc,p.planReceiptDate asc";
		hql = this.getParamsSql(hql, params, lastSql);
//		System.out.println(hql);
//		String clastSql = " group by p.id";
		countHql = this.getParamsSql(countHql, params, null);
//		System.out.println("countHql   "+countHql);
		hql = hql + " order by p.planNum,p.planRowNum asc";
		Query q = this.getSessionFactory().getCurrentSession().createQuery(hql);
		String key = "dy_p.procurementContract.suppliersId";
		if(params.keySet().contains(key)){
			totalSql = totalSql + " left join t_procurementContract pct on p.pid = pct.id";
			String suId = (String)params.get(key);
			params.put("dy_pct.suppliersId", params.get(key));
			params.remove(key);
		}
		if(params.keySet().contains("pc.procurementName")){
			String value = (String)params.get("pc.procurementName");
			params.put("mh_pc.procurementName",value);
			params.remove("pc.procurementName");
			totalSql = totalSql + " left join t_procurementcheck pc on p.pwcid = pc.id";
		}
		//add by brown 20140729
		if(params.keySet().contains("eq_pc.procurementWay")){
			String value = (String)params.get("eq_pc.procurementWay");
			params.put("eqSql_pc.procurementWay",value);
			params.remove("eq_pc.procurementWay");
			if(!totalSql.contains("t_procurementcheck"))
				totalSql = totalSql + " left join t_procurementcheck pc on p.pwcid = pc.id";
		}
		totalSql = this.getParamsSql(totalSql, params, null);
		Query totalQuery = this.getSession().createSQLQuery(totalSql)
				.addScalar("totalBudgetMoney", Hibernate.DOUBLE)
				.addScalar("totalProcurementMoney", Hibernate.DOUBLE)
				.addScalar("totalArrivalNum", Hibernate.DOUBLE)
				.addScalar("totalArrivalMoney", Hibernate.DOUBLE)
				.addScalar("totalAcceptanceNum", Hibernate.DOUBLE)
				.addScalar("totalAcceptanceMoney", Hibernate.DOUBLE);
		if(isLimit){
			q.setFirstResult(offset);
			q.setMaxResults(pageSize);
		}
		Long o = (Long)this.getSession().createQuery(countHql).uniqueResult();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		@SuppressWarnings("unchecked")
		List<Plan> l = q.list();
		List<ProcurementContractBean> list = new ArrayList<ProcurementContractBean>();
		for(int i=0;i<l.size();i++){
			
			ProcurementContractBean pcb = new ProcurementContractBean();
			pcb.setNumber(offset+1+i);
			pcb.setReportComp(l.get(i).getReportCompId());
			String groupName = null;
			if( l.get(i).getPerson() != null && l.get(i).getPerson().getPlanGroup() != null){
				groupName = l.get(i).getPerson().getPlanGroup().getGroupName();
			}else{
				groupName = "";
			}
			pcb.setGroupName(groupName);
			pcb.setReportCompName(l.get(i).getReportComp());
			pcb.setFacilityNameAndNum(l.get(i).getFacilityNameAndNum());
			pcb.setItemsName(l.get(i).getItemsName());
			pcb.setItemsNum(l.get(i).getItemsNum());
			pcb.setUnit(l.get(i).getUnit());
			pcb.setPlanNum(l.get(i).getPlanNum());
			pcb.setQuantity(l.get(i).getQuantity());
			pcb.setBudgetPrice(l.get(i).getBudgetPrice());
			pcb.setBudgetMoney(l.get(i).getBudgetMoney());
			pcb.setItemType(l.get(i).getItemType());
			pcb.setModelType(l.get(i).getModelType());
			pcb.setMaterial(l.get(i).getMaterial());
			pcb.setStandardNum(l.get(i).getStandardNum());
			pcb.setDemandDept(l.get(i).getDemandDept());
			pcb.setCheckItemNum(l.get(i).getCheckItemNum());
			pcb.setUseFor(l.get(i).getUseFor());
			pcb.setOriginComp(l.get(i).getOriginComp());
			pcb.setProcurementWay(statusName.getProcurementWay(l.get(i).getProcurementWay()));
			pcb.setProcurementDate(l.get(i).getProcurementDate());
			pcb.setCheckStatus(l.get(i).getCheckStatus());
			pcb.setRemark(l.get(i).getRemark());
//			pcb.setContractExecutionWay(statusName.getContractExecutionWay(l.get(i).getContractExecutionWay()));
			//寻源单价
			pcb.setProcurementPrice(l.get(i).getProcurementPrice());
			pcb.setPlancontractNum(l.get(i).getContractNum());
			//寻源金额
			pcb.setProcurementMoney(l.get(i).getProcurementMoney());
//			pcb.setPlanReceiptDate(l.get(i).getPlanReceiptDate());
			pcb.setPlanStatus(l.get(i).getPlanStatus());
//			pcb.setOrgName(l.get(i).getOrg().getName());
			if(l.get(i).getPerson()!=null)
				pcb.setPersonName(l.get(i).getPerson().getName());
			//salecontract
			if(l.get(i).getSalesContract()!= null){
				pcb.setSaleContractName(l.get(i).getSalesContract().getSalesContractName());
				pcb.setSaleContractNum(l.get(i).getSalesContract().getContractNum());
				pcb.setSaleContractMoney(l.get(i).getSalesContract().getContractMoney());
			}else{
				pcb.setSaleContractName("");
				pcb.setSaleContractNum("");
				pcb.setSaleContractMoney(0.0);
			}
			//procurementContract
			if(l.get(i).getProcurementContract()!= null){
				pcb.setContractNum(l.get(i).getProcurementContract().getContractNum());
				pcb.setContractName(l.get(i).getProcurementContract().getContractName());
				pcb.setSuppliersName(l.get(i).getProcurementContract().getSuppliersName());
				pcb.setTotalMoney(l.get(i).getProcurementContract().getTotalMoney());
			}else{
				pcb.setContractNum("");
				pcb.setContractName("");
				pcb.setSuppliersName("");
				pcb.setTotalMoney(0.0);
			}
			//procurementCheck
			if(l.get(i).getPc() != null){
				pcb.setProcurementName(l.get(i).getPc().getProcurementName());
			}else{
				pcb.setProcurementName("");
				
			}
			
			pcb.setArrivalNum(l.get(i).getArrivalNumTotal());
			pcb.setArrivalMoney(l.get(i).getArrivalMoneyTotal());
			pcb.setAcceptanceNum(l.get(i).getAcceptanceNumTotal());
			pcb.setAcceptanceMoney(l.get(i).getAcceptanceMoneyTotal());
			list.add(pcb);
		}
		List<Object[]> totalList = totalQuery.list();
		for(int i=0;i<totalList.size();i++){
				pageModel.setTotal1(o==null?0:o.intValue());
			if(totalList.get(i)[0]!=null){
				pageModel.setDouble1(Double.parseDouble(totalList.get(i)[0].toString()));
			}else{
				pageModel.setDouble1(0.0);
			}
			if(totalList.get(i)[1]!=null){
				pageModel.setDouble2(Double.parseDouble(totalList.get(i)[1].toString()));
			}else{
				pageModel.setDouble2(0.0);
			}
			if(totalList.get(i)[2]!=null){
				pageModel.setDouble3(Double.parseDouble(totalList.get(i)[2].toString()));
			}else{
				pageModel.setDouble3(0.0);
			}
			if(totalList.get(i)[3]!=null){
				pageModel.setDouble4(Double.parseDouble(totalList.get(i)[3].toString()));
			}else{
				pageModel.setDouble4(0.0);
			}
			if(totalList.get(i)[4]!=null){
				pageModel.setDouble5(Double.parseDouble(totalList.get(i)[4].toString()));
			}else{
				pageModel.setDouble5(0.0);
			}
			if(totalList.get(i)[5]!=null){
				pageModel.setDouble6(Double.parseDouble(totalList.get(i)[5].toString()));
			}else{
				pageModel.setDouble6(0.0);
			}
			
		}
		pageModel.setList(list);
		pageModel.setTotal(o==null?0:o.intValue());
		return pageModel;
	}
	public PageModel exportReportComp(int offset,int pageSize,Map params,boolean isLimit){
//		String hql = "from Plan p left join p.pc pch left join p.salesContract s left join p.procurementContract pct";
//		String countHql = "select count(*) from Plan p left join p.pc left join p.salesContract left join p.procurementContract";
		String sql = "select p.reportCompId,p.reportComp,count(distinct p.sid) salesCount,sum(p.procurementMoney) procurementMoney,sum(p.salesMoney) saleMoney ," +
					 "count(distinct p.pid) procumentCount,s.totalcontractReceivedMoney receviedMoney,sum(p.arrivalMoneyTotal) arrivalMoney " +
					 "from t_plan p " +
					 "left join t_salescontract s ON p.sid = s.id ";
		String countSql = "select count(distinct t.reportCompId) from t_plan t ";
		String procurementWaySql = "select p.*,pc.procurementWay procurementWay1 from t_plan p left join t_procurementcheck pc ON  p.pwcid = pc.id" ;
		if(params.isEmpty()){
			sql = sql+" where 1=1 ";
			countSql = countSql+" where 1=1 ";
			procurementWaySql = procurementWaySql+" where 1=1 ";
		}
//		String last =  "where p.reportCompId is not null group by p.reportCompId";
		String last =  " and p.reportCompId is not null and p.pwcid is not null group by p.reportCompId";
		String pwaylast =  " and p.reportCompId is not null and p.pwcid is not null";
		
		sql = this.getParamsSql(sql, params, last);
		Query q = this.getSession().createSQLQuery(sql)
		.addScalar("reportCompId", Hibernate.INTEGER)
		.addScalar("reportComp", Hibernate.STRING)
		.addScalar("salesCount", Hibernate.INTEGER)
		.addScalar("procurementMoney", Hibernate.DOUBLE)
		.addScalar("saleMoney", Hibernate.DOUBLE)
		.addScalar("procumentCount", Hibernate.INTEGER)
		.addScalar("receviedMoney", Hibernate.DOUBLE)	
		.addScalar("arrivalMoney", Hibernate.DOUBLE);	
		if(isLimit){
			q.setFirstResult(offset);
			q.setMaxResults(pageSize);
		}
		procurementWaySql = this.getParamsSql(procurementWaySql, params, pwaylast);
		Query pwq = this.getSession().createSQLQuery(procurementWaySql)
					.addEntity(Plan.class)
					.addScalar("procurementWay1", Hibernate.STRING);
		BigInteger o = (BigInteger)this.getSession().createSQLQuery(countSql).uniqueResult();
		List<Object[]> l = q.list();
		List<ReportCompStatisticsBean> list = new ArrayList<ReportCompStatisticsBean>();
		for(int i=0;i<l.size();i++){
			ReportCompStatisticsBean b = new ReportCompStatisticsBean();
			b.setReportCompId(Integer.parseInt(l.get(i)[0]==null?"0":l.get(i)[0].toString()));
			b.setReportCompName(l.get(i)[1].toString());
			b.setSalesCount(Integer.parseInt(l.get(i)[2]==null?"0":l.get(i)[2].toString()));
			b.setProcurementMoney(Double.parseDouble(l.get(i)[3]==null?"0.0":l.get(i)[3].toString()));
			b.setSaleMoney(Double.parseDouble(l.get(i)[4]==null?"0.0":l.get(i)[4].toString()));
			b.setProcumentCount(Integer.parseInt(l.get(i)[5]==null?"0":l.get(i)[5].toString()));
			if(l.get(i)[6]!=null){
				b.setReceviedMoney(Double.parseDouble(l.get(i)[6].toString()));
			}else{
				b.setReceviedMoney(0.0);
			}
			if(l.get(i)[7]!=null){
				b.setArrivalMoney(Double.parseDouble(l.get(i)[7].toString()));
			}else{
				b.setArrivalMoney(0.0);
			}
			list.add(b);
		}
		List<Object[]> ps = pwq.list();
		List<ReportCompStatisticsBean> pways = new ArrayList<ReportCompStatisticsBean>();
		for(int i=0 ;i<ps.size();i++){
			ReportCompStatisticsBean b = new ReportCompStatisticsBean();
			b.setReportCompId(((Plan)ps.get(i)[0]).getReportCompId());
			b.setProcurementPrice(((Plan)ps.get(i)[0]).getProcurementPrice());
			b.setBudgetPrice(((Plan)ps.get(i)[0]).getBudgetPrice());
			b.setContractNum(((Plan)ps.get(i)[0]).getContractNum());
			b.setProcurementWay(ps.get(i)[1].toString());
			pways.add(b);
		}
		Map<Integer,Map<String,Integer>> finalmp = new HashMap<Integer, Map<String,Integer>>();
		Map<Integer,List<ReportCompStatisticsBean>> reIdMap = new HashMap<Integer, List<ReportCompStatisticsBean>>();
		for(ReportCompStatisticsBean r : pways){
			Integer rId = r.getReportCompId();
			if(reIdMap.keySet().contains(rId)){
				reIdMap.get(rId).add(r);
			}else{
				List<ReportCompStatisticsBean> temp = new ArrayList<ReportCompStatisticsBean>();
				temp.add(r);
				reIdMap.put(rId, temp);
			}
		}
		Map<Integer,Double> reIdSaveMoneyMap = new HashMap<Integer, Double>();
		for(List<ReportCompStatisticsBean> rs : reIdMap.values()){
			Map<String,List<ReportCompStatisticsBean>> tempMap = new HashMap<String, List<ReportCompStatisticsBean>>();
			double savedPrice = 0.0;
			for(ReportCompStatisticsBean r : rs){
				String way = r.getProcurementWay();
				double procurementPrice = r.getProcurementPrice() == null ? 0.0 : r.getProcurementPrice();
				double budgetPrice = r.getBudgetPrice() == null ? 0.0 : r.getBudgetPrice();
				double contractNum = r.getContractNum() == null ? 0.0 : r.getContractNum();
				//预算单价*数量－寻源数量*数量
				savedPrice = savedPrice + (budgetPrice*contractNum-procurementPrice*contractNum);
				if(tempMap.keySet().contains(way)){
					tempMap.get(way).add(r);
				}else{
					List<ReportCompStatisticsBean> temp1 = new ArrayList<ReportCompStatisticsBean>();
					temp1.add(r);
					tempMap.put(way, temp1);
				}
			}
			System.out.println(rs.get(0).getReportCompName()+"    fffffffff      "+savedPrice);
			reIdSaveMoneyMap.put(rs.get(0).getReportCompId(), savedPrice);
			Map<String,Integer> way_Count = new HashMap<String,Integer>();
			for(String way : tempMap.keySet()){
				way_Count.put(way, tempMap.get(way).size());
			}
			finalmp.put(rs.get(0).getReportCompId(), way_Count);
		}
		/*for(Integer k: finalmp.keySet()){
			System.out.println("finalmp.key    "+k);
			Map<String,Integer> kp = finalmp.get(k);
			for(String s : kp.keySet()){
				System.out.println(s+"   value   "+kp.get(s));
			}
		}*/
		//填充采购方式
		for (ReportCompStatisticsBean r : list) {
			Integer reportId = r.getReportCompId();
			//取得节支
			r.setSavedMoney(reIdSaveMoneyMap.get(reportId)==null?0.0:reIdSaveMoneyMap.get(reportId));
			if (finalmp.keySet().contains(reportId)) {
				Map<String,Integer> tm = finalmp.get(reportId);
				Integer total = 0;
				Iterator it = tm.entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<String, Integer> e = (Entry<String, Integer>) it.next();
					String wayCode = e.getKey();
					total = total + e.getValue();
					int p = Integer.parseInt(wayCode);
					switch (p) {
					case 1:
						r.setGongkai(e.getValue());
						break;
					case 2:
						r.setYaoqing(e.getValue());
						break;
					case 3:
						r.setJingzheng(e.getValue());
						break;
					case 4:
						r.setDanyi(e.getValue());
						break;
					case 5:
						r.setXunbijia(e.getValue());
						break;
					}
				}
				 r.setSum(total);
			}
		}
		pageModel.setList(list);
		pageModel.setTotal(o==null?0:o.intValue());
		return pageModel;
	}
	private String getParamsSql(String sql,Map params,String lastSql){
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		if(!params.isEmpty()){
			sb.append(" where 1=1");
			Iterator it = params.entrySet().iterator();
			Map.Entry pa = null;
			while(it.hasNext()){
				pa = (Entry) it.next();
				String temp = pa.getKey().toString();
				if(temp.startsWith("id_")){
					sb.append(" and  p."+temp.substring(3)+" = "+pa.getValue());
					//sql += " and  p."+temp.substring(3)+" = "+pa.getValue();
				}else if(temp.startsWith("eq_")){
					sb.append(" and  p."+temp.substring(3)+" = '"+pa.getValue()+"' ");
					//sql += " and  p."+temp.substring(3)+" = '"+pa.getValue()+"'";
				}else if(temp.startsWith("in_")){
					sb.append(" and  p."+temp.substring(3)+" in ("+pa.getValue()+") ");
					//sql += " and  p."+temp.substring(3)+" in ("+pa.getValue()+")";
				}else if(temp.startsWith("dy_")){
					sb.append(" and  "+temp.substring(3)+" "+pa.getValue()+" ");
					//sql += " and  p."+temp.substring(3)+" "+pa.getValue()+" ";
				}else if(temp.startsWith("mh_")){
					sb.append(" and  "+temp.substring(3)+" like '%"+pa.getValue()+"%' ");
					//sql += " and  p."+temp.substring(3)+" "+pa.getValue()+" ";
				}else if(temp.startsWith("null_")){
					sb.append(" and  "+temp.substring(5)+" is null ");
					//sql += " and  p."+temp.substring(3)+" "+pa.getValue()+" ";
				}else if(temp.startsWith("eqSql_")){
					sb.append(" and  "+temp.substring(6)+" = '"+pa.getValue()+"' ");
					//sql += " and  p."+temp.substring(3)+" "+pa.getValue()+" ";
				}else if(temp.startsWith("obj_")){
					User u = (User)params.get("obj_user");
					if(u!=null){
						sb.append(" and p.person.id in ("+u.getAuth()+") ");
					}
//				}else if(temp.startsWith("null_")){
//					sb.append(" and  "+temp.substring(5)+" "+pa.getValue()+" ");
				}else{
					if(pa.getValue()!=null && !pa.getValue().equals(""))
						sb.append(" and  p."+pa.getKey()+" like '%"+pa.getValue()+"%' ");
					//sql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
			
		}
		if(lastSql!=null && !"".equals(lastSql)){
			sb.append(lastSql);
		}
		return sb.toString();
	}
	public void modifyPlansByIds(Plan plan) {
		Plan p=this.findPlan(plan.getId());
		if(p!=null){
			if(plan.getPerson() !=null)
				p.setPerson(plan.getPerson());
			if(plan.getUser()!=null)
				p.setUser(plan.getUser());
			this.getHibernateTemplate().update(p);
		}
	}
	public void modifyPlanByObj(Plan p){
		this.getHibernateTemplate().update(p);
	}
//	public void modifyPlansByIdsBack(String ids) {
//		this.getSession().createSQLQuery("update t_plan p set p.uid = null,p.psid = null,p.planStatus='01',p.procurementWay=null,p.procurementDate=null,p.procurementRemark='',p.procurementSigningleDate=null,p.searchAnnouncedDate=null,p.searchDate=null,p.contractExecutionWay=null,p.procurementPrice=null,p.procurementMoney=null  where p.id in ("+ids+")").executeUpdate();
//	}
	public void modifyPlansByIdsBack(String ids) {
		this.getSession().createSQLQuery("update t_plan p set " +
				"p.psid = null,p.checkStatus=null " +
				"where p.id in ("+ids+")").executeUpdate();
	}
	public void modifycancelPlans(String ids) {
		this.getSession().createSQLQuery("update t_plan p set p.planStatus='06',p.procurementFlag='02'  where p.id in ("+ids+")").executeUpdate();
	}
	public void deletePlansByIds(String ids) {
		this.getSession().createSQLQuery("delete from t_plan where id in ("+ids+")").executeUpdate();
	}
	public void modifyredoPlans(String ids) {
		this.getSession().createSQLQuery("update t_plan p set p.planStatus='01',p.procurementFlag='01'  where p.id in ("+ids+")").executeUpdate();
	}
	public void modifyPlansOrg(String ids,int orgId) {
		this.getSession().createSQLQuery("update t_plan p set p.oid = "+orgId+"  where p.id in ("+ids+")").executeUpdate();
	}
	public String validationToAddContract(String tags){
		String msg = "";
		String[] str = tags.split(",");
		Integer[] ia=new Integer[str.length];
		for(int i=0;i<str.length;i++){
		   ia[i]=Integer.parseInt(str[i]);
		}
		List<Plan> plist = this.getPlans(ia);
		
		if(plist.size() > 1){
			for(int i=1;i<plist.size() ;i++){
//				System.out.println(plist.get(i-1).getProcurementWay());
//				System.out.println(plist.get(i).getProcurementWay());
//				System.out.println(plist.get(i-1).getContractExecutionWay());
//				System.out.println(plist.get(i).getContractExecutionWay());
				if(!plist.get(i).getPc().getProcurementWay().equals(plist.get(i-1).getPc().getProcurementWay())){
					msg = "请选择相同采购方式的计划";
					break;
				}
				if(!(plist.get(i).getSupplier().getId() == plist.get(i-1).getSupplier().getId())){
					msg = "请选择相同的供应商";
					break;
				}
//				if(!plist.get(i).getContractExecutionWay().equals(plist.get(i-1).getContractExecutionWay())){
//					msg = "请选择相同合同执行方式的计划";
//					break;
//				}
			}
		}
		return msg;
	}
	public String validationToAddSalesContract(String tags){
		String msg = "";
		String[] str = tags.split(",");
		Integer[] ia=new Integer[str.length];
		for(int i=0;i<str.length;i++){
		   ia[i]=Integer.parseInt(str[i]);
		}
		List<Plan> plist = this.getPlans(ia);
		if(plist.size() > 1){
			for(int i=1;i<plist.size() ;i++){
				if(!plist.get(i).getReportCompId().equals(plist.get(i-1).getReportCompId())){
					msg = "请选择相同计划提报单位";
					break;
				}
			}
		}
		return msg;
	}
	public String validationToAddSeekSource(String tags,String flag){
		String msg = "";
		String[] str = tags.split(",");
		Integer[] ia=new Integer[str.length];
		for(int i=0;i<str.length;i++){
		   ia[i]=Integer.parseInt(str[i]);
		}
		List<Plan> plist = this.getPlans(ia);
		if(plist.size() > 1){
			if(flag != null && flag.equals("toAddSearchDate")){
				for(int i=1;i<plist.size() ;i++){
					if(plist.get(i).getSearchAnnouncedDate()==null || plist.get(i).getSearchAnnouncedDate().equals("")){
						msg = "寻源公布日期为空，不能添加寻源结果审批通过日期";
						break;
					}
					if(plist.get(i).getContractExecutionWay()==null || plist.get(i).getContractExecutionWay().equals("") || plist.get(i).getContractExecutionWay().equals("0")){
						msg = "合同执行方式为空，不能添加寻源结果审批通过日期";
						break;
					}
					if(plist.get(i).getProcurementPrice()==null || plist.get(i).getProcurementPrice() < 0){
						msg = "寻源金额为空，不能添加寻源结果审批通过日期";
						break;
					}
				}
			}else if(flag != null && flag.equals("toAddContractExcutionWay")){
				for(int i=1;i<plist.size() ;i++){
					if(plist.get(i).getSearchAnnouncedDate()==null || plist.get(i).getSearchAnnouncedDate().equals("")){
						msg = "寻源公布日期为空，不能添加合同执行方式";
						break;
					}
				}
			}else if(flag != null && flag.equals("toAddPrice")){
				for(int i=1;i<plist.size() ;i++){
					if(plist.get(i).getSearchAnnouncedDate()==null || plist.get(i).getSearchAnnouncedDate().equals("")){
						msg = "寻源公布日期为空，不能添加寻源单价";
						break;
					}
				}
			}
		}
		return msg;
	}

	@Override
	public void modifyPlanPersonByIds(String ids,int personId,int managerId,int directorId) {
		// TODO Auto-generated method stub
		//checkStatus 04 经理末审核
		String hql = "update Plan p set p.person.id = "+personId + " ,p.checkStatus='03',p.manager.id= " + managerId + ",p.director.id= " + directorId + " where p.id in (" + ids + ")";
		System.out.println(hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	@Override
	public void modifyPlanOpinionByIds(String ids,Plan p) {
		// TODO Auto-generated method stub
		String hql ;
		//05 经理末通过审核,06 经理己审核
//		if("05".equals(p.getCheckStatus())){//,p.planStatus='01' 页面传进来末通过状态
			hql = "update Plan p set p.checkStatus='"+p.getCheckStatus()+"',p.opinion='"+p.getOpinion()+"' where p.id in (" + ids + ")";
//		}else{
//			hql = "update Plan p set p.person.id="+p.getPerson().getId()+" ,p.planStatus='02',p.checkStatus='"+p.getCheckStatus()+"' where p.id in (" + ids + ")";
//		}
		System.out.println(hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.PlanService#modifyPlansPc(java.lang.String, int)
	 */
	@Override
	public void modifyPlansPc(String ids, int proChxId) {
		// TODO Auto-generated method stub

		String hql = "update Plan p set p.pc.id = "+proChxId+",p.planStatus='02',p.checkStatus='01' where p.id in (" + ids + ")";
		System.out.println("doProcurementWay  modifyPlansPc  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void modifyRemovePlanPc(int id) {
		// TODO Auto-generated method stub
		String hql = "update Plan p set p.pc.id = null,p.planStatus='01' where p.id = " + id;
		System.out.println("removePlanPc  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	public void modifyPlanForSplit(Plan plan){
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().update(plan);
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.PlanService#checkPlanNumAndPlanRowNum(java.lang.String, java.lang.String)
	 */
	@Override
	public Long checkPlanNumAndPlanRowNum(String PlanNum, String PlanRowNum) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from  Plan p where p.planNum = '" + PlanNum+"' and p.planRowNum ='"+PlanRowNum+"'" ;
		System.out.println("checkPlanNumAndPlanRowNum  >>>> "+hql);
		return (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.PlanService#modifyPlanOperatorByIds(java.lang.String, int)
	 */
	@Override
	public void modifyPlanOperatorByIds(String ids, int personId) {
		// TODO Auto-generated method stub
		String hql = "update Plan p set p.person.id = "+personId+",p.checkStatus='05' where p.id in ("+ids+")";
		this.getSession().createQuery(hql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.PlanService#searchOperatorPlans(java.util.Map, int, int)
	 */
	@Override
	public PageModel searchOperatorPlans(Map params, int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Plan p ";
		hql = this.getParamsSql(hql, params, " order by p.planNum,p.planRowNum asc,p.planReceiptDate asc,p.reportCompId");
		
		return service.getPageModel(hql, offset, pageSize);
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.PlanService#getPlanByPlanNumAndRowNum(java.lang.String, java.lang.String)
	 */
	@Override
	public Plan getPlanByPlanNumAndRowNum(String PlanNum, String PlanRowNum) {
		// TODO Auto-generated method stub
		String hql = "from  Plan p where p.planNum = '" + PlanNum+"' and p.planRowNum ='"+PlanRowNum+"' order by p.planNum,p.planRowNum asc" ;
		System.out.println("getPlanByPlanNumAndRowNum  >>>> "+hql);
		return (Plan)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Integer getUnGroupCountByRole(int personId, String role) {
		// TODO Auto-generated method stub
		String hql = "";
		//业务员自己分组，然后主管审批，内控，经理
		if(personId == 0 && role == null){
			hql = "select count(*) from Plan p where p.groupStatus in ('01','02')";
		}else if("d".equals(role)){
			hql = "select count(*) from Group p where p.status in ('01','04')";// and p.group.groupLeader.id="+personId;
		}else if("i".equals(role)){
			hql = "select count(*) from Group p where p.status in ('03','06')";
		}else if("m".equals(role)){
			hql = "select count(*) from Group p where p.status in ('05')";
		}else if("dPcSetOperator".equals(role)){
			hql = "select count(*) from Plan p where p.group.status='07' and p.person.id is null " +
					"and p.group.group.groupLeader.id="+personId;
		}
		
		Long count = (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count == null){
			return 0;
		}else{
			return count.intValue();
		}
	}
}
