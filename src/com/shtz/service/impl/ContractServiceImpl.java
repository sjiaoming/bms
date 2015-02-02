package com.shtz.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.ContractCredit;
import com.shtz.model.Organization;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementArrivalBean;
import com.shtz.model.ProcurementContract;
import com.shtz.model.Suppliers;
import com.shtz.model.User;
import com.shtz.service.ContractService;
import com.shtz.service.PageService;
import com.shtz.service.PlanService;
import com.shtz.util.Arith;
import com.shtz.util.PageModel;

public class ContractServiceImpl extends HibernateDaoSupport implements ContractService {
	private PageService service;
	private PageModel pageModel;
	private PlanService  pservice;
	private Arith arithService;
	public void setArithService(Arith arithService){
		this.arithService = arithService;
	}
	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}

	public void setService(PageService service) {
		this.service = service;
	}
	

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public PageModel searchContracts(int offset, int pageSize) {
		return service.getPageModel("from ProcurementContract", offset, pageSize);
	}
	public PageModel searchContracts(Map params,int offset, int pageSize){
		String hql = "from ProcurementContract p where 1=1 ";
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
				}else if(temp.startsWith("obj_")){
					
					User u = (User)params.get("obj_user");
					if(u!=null)
						hql += " and p.person.id in ("+u.getAuth()+")";
				}else{
					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
		}
		
		
		
		return service.getPageModel(hql, offset, pageSize);
	}
	public PageModel searchContracts1(Map params,int offset, int pageSize){
		String hql = "select new com.shtz.model.ProcurementContractBean(p.id,p.contractNum,p.contractExecutionWay,p.orgName,p.totalMoney,p.procurementWay,p.payed,sum(pl.procurementPrice * IFNULL(a.arrivalNum,0)) as arrivalTotalMoney,sum(pl.procurementPrice * IFNULL(a.acceptanceNum,0)) as acceptanceTotalMoney)" +
				"from ProcurementContract p,Plan pl " +
				"left join pl.arrivalItems a  where pl.arrivalItems.id = a.id and p.plan.id = p.id";
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
				}else if(temp.startsWith("obj_")){
					
					User u = (User)params.get("obj_user");
					if(u!=null)
						hql += " and p.user.id in ("+u.getAuth()+")";
				}else{
					hql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
		}
		hql +=" group by p.contractNum";
		
		System.out.println(hql);
		
		
		
		
		
		
		return service.getPageModel(hql, offset, pageSize);
	}
	public double searchContractcreditMoney(int id) {
		String hql="select sum(contractCreditMoney) from ContractCredit c where c.procurementContract.id=?";
		Object tcm =this.getSession().createQuery(hql).setParameter(0, id).uniqueResult();
		if(tcm==null){
			return 0;
		}
		return Double.parseDouble(tcm.toString());
	}
	public List<Plan> getPlans(int procurementContractId) {
		String hql="from Plan p where p.procurementContract.id=?";
		List<Plan> plans=this.getSession().createQuery(hql).setParameter(0,procurementContractId).list();
		return plans;
	}
	public ProcurementContract findProcurementContractById(int procurementContractId) {
		return (ProcurementContract) this.getHibernateTemplate().load(ProcurementContract.class, procurementContractId);
	}
	public List<Organization> findAllOrganization() {
		return (List<Organization>)this.getHibernateTemplate().loadAll(Organization.class);
	}
	public List<Suppliers> findAllSuppliers(){
		return (List<Suppliers>)this.getHibernateTemplate().loadAll(Suppliers.class);
	}
	public List<ContractCredit> findContractCreditByPid(int pid){
		return (List<ContractCredit>)this.getSession().createQuery("from ContractCredit cc where cc.procurementContract.id="+pid).list();
	}
	public void deleteContractCredit(int id) {
		ProcurementContract p = ((ContractCredit)this.getHibernateTemplate().load(ContractCredit.class, id)).getProcurementContract();
		ContractCredit c = (ContractCredit)getHibernateTemplate().load(ContractCredit.class, id);
		p.setShouldPayment(Arith.sub(p.getShouldPayment(),c.getContractCreditMoney()));
		this.getHibernateTemplate().delete(c);
		this.getHibernateTemplate().update(p);
		
	}
	public void modifyProcurementContract(ProcurementContract pc){
		this.getHibernateTemplate().update(pc);
	}
	public void modifyplanpid(int planId) {
		Plan p=(Plan) this.getHibernateTemplate().load(Plan.class, planId);
		//p=(Plan) this.getHibernateTemplate().merge(p);
		ProcurementContract pc = p.getProcurementContract();
		double temptm = arithService.sub(pc.getTotalMoney(), p.getProcurementMoney());
		pc.setTotalMoney(temptm >0 ?temptm:0.0);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		if(!pc.getPlan().isEmpty()){
//			int i=0;
			List<Integer> list = new ArrayList<Integer>();
			List<String> list1 = new ArrayList<String>();
			for(Plan pl : pc.getPlan()){
				if(pl.getId() != p.getId()){
					if(!list.contains(pl.getReportCompId())) {//如果数组 list 不包含当前项，则增加该项到数组中
						list.add(pl.getReportCompId());
					}
					if(!list1.contains(pl.getReportComp())) {//如果数组 list 不包含当前项，则增加该项到数组中
						list1.add(pl.getReportComp());
					}
					
//					sb.append(",");
//					sb.append(pl.getReportCompId());
//					if(i!=0)
//						sb1.append(",");
//					sb1.append(pl.getReportComp());
//					i++;
				}
			}
//			sb.append(",");
			if(list.size()>0 && list.size() == list1.size()){
				for(int i=0;i<list.size();i++){
					sb.append(",");
					sb.append(list.get(i));
					if(i!=0)
						sb1.append(",");
					sb1.append(list1.get(i));
				}
				sb.append(",");
			}
		}
		
		pc.setReportCompId_pc(sb.toString());
		pc.setReportCompName_pc(sb1.toString());
		this.getHibernateTemplate().update(pc);
		p.setProcurementContract(null);
		p.setPlanStatus("03");
		if(p.getProcurementWay()!=null && p.getProcurementWay().equals("6")){
			p.setPlanStatus("01");
			p.setProcurementDate(null);
			p.setProcurementMoney(0.0);
			p.setProcurementPrice(0.0);
			p.setProcurementSigningleDate(null);
			p.setContractNum(0.0);
		}
		this.getHibernateTemplate().update(p);
	}
	public void deleteProcurementContract(int pcontractId){
		
		 List<ContractCredit> cc=this.findContractCreditByPid(pcontractId);
		 List<Plan> pList=this.getPlans(pcontractId);
		 boolean y = true;
		 for(Plan p : pList){
			 if(p.getArrivalItems().size() > 0){
				 y = false;
			 }
		 }
		 if((cc==null || cc.size()==0) && y){
			 ProcurementContract p = (ProcurementContract)this.getHibernateTemplate().load(ProcurementContract.class, pcontractId);
			 try {
				 if(p!=null){
					 for(Plan ps : pList){
						 ps.setProcurementContract(null);
						 ps.setContractNum(0.0);
						 ps.setPlanStatus("03");
						this.getHibernateTemplate().update(p);
					 }
				 }
				this.getHibernateTemplate().delete(p);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		 }else{
			 throw new RuntimeException("haveContractCredit");
		 }
		
	}
	public void addProcurementContract(ProcurementContract pc){
		this.getHibernateTemplate().update(pc);
	}
	public void addOrUpdateContractCredit(ContractCredit cc,int ccid){
		ContractCredit CCreditthis=(ContractCredit)this.getHibernateTemplate().get(ContractCredit.class, ccid);
		if(CCreditthis==null){
			this.getHibernateTemplate().save(cc);
		}else{
			this.getHibernateTemplate().update(cc);
		}
	}
	public List<ContractCredit> findContractCreditById(int pid){
		 List<ContractCredit> contractCreditList=(List<ContractCredit>) this.getSession().createQuery("from ContractCredit cc where cc.procurementContract.id=?").setParameter(0, pid).list();
		return contractCreditList;
		
	}
	public ContractCredit findContractCreditByIds(int id){
		return (ContractCredit)this.getHibernateTemplate().load(ContractCredit.class, id);
	}
	public PageModel getProcurementList(int offset,int pageSize,Map params,boolean isLimit){
		String sql="select  p.* ,sum(c.contractCreditMoney) totalCreditMoney,max(c.contractCreditDate) contractCreditDate from t_procurementcontract p " +
				"left join t_contractcredit c on c.cid = p.id";
		String countSql = "select count(p.id) from t_procurementcontract p";
		String lastSql = "  group by p.id order by p.id desc";
		sql = this.getParamsSql(sql, params, lastSql);
//		String clastSql = " group by p.id) s";
		countSql = this.getParamsSql(countSql, params, lastSql);
		Query q = this.getSession().createSQLQuery(sql)
				.addEntity(ProcurementContract.class)
				.addScalar("totalCreditMoney", Hibernate.DOUBLE)
				.addScalar("contractCreditDate", Hibernate.DATE);
		
		if(isLimit){
			q.setFirstResult(offset);
			q.setMaxResults(pageSize);
		}
		BigInteger  o = (BigInteger )this.getSession().createSQLQuery(countSql).uniqueResult();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		@SuppressWarnings("unchecked")
		List<Object[]> l = q.list();
		List<ProcurementArrivalBean> list = new ArrayList<ProcurementArrivalBean>();
		for(int i=0;i<l.size();i++){
			ProcurementArrivalBean pcb = new ProcurementArrivalBean();
			
			pcb.setId(((ProcurementContract)l.get(i)[0]).getId());
			pcb.setContractName(((ProcurementContract)l.get(i)[0]).getContractName());
			pcb.setContractNum(((ProcurementContract)l.get(i)[0]).getContractNum());
			pcb.setTotalMoney(((ProcurementContract)l.get(i)[0]).getTotalMoney());
			pcb.setSigningDate(((ProcurementContract)l.get(i)[0]).getSigningDate());
			pcb.setProcurementWay(((ProcurementContract)l.get(i)[0]).getProcurementWay());
			pcb.setSuppliersName(((ProcurementContract)l.get(i)[0]).getSuppliersName());
			pcb.setQualityMoney(((ProcurementContract)l.get(i)[0]).getQualityMoney());
			pcb.setQualityDate(((ProcurementContract)l.get(i)[0]).getQualityDate());
			pcb.setAdvance(((ProcurementContract)l.get(i)[0]).getAdvance());
			pcb.setArrivalDate(((ProcurementContract)l.get(i)[0]).getArrivalDate());
			pcb.setPayed(((ProcurementContract)l.get(i)[0]).getPayed());
			pcb.setShouldPayment(((ProcurementContract)l.get(i)[0]).getShouldPayment());
			pcb.setRemark(((ProcurementContract)l.get(i)[0]).getRemark());
			Set<Plan> plans = ((ProcurementContract)l.get(i)[0]).getPlan();
			double arrivalMoney = 0.0,acceptanceMoney = 0.0;
			for(Plan p : plans){
				arrivalMoney = arrivalMoney + (p.getArrivalMoneyTotal()==null?0.0:p.getArrivalMoneyTotal());
				acceptanceMoney = acceptanceMoney + (p.getAcceptanceMoneyTotal()==null?0.0:p.getAcceptanceMoneyTotal());
			}
			pcb.setArrivalMoney(arrivalMoney);
			pcb.setAcceptanceMoney(acceptanceMoney);
//			pcb.setOrgName(((ProcurementContract)l.get(i)[0]).getOrgName());
//			pcb.setContractCredit(((ProcurementContract)l.get(i)[0]).getContractCredit());
//			pcb.setPerson(((ProcurementContract)l.get(i)[0]).getPerson());
//			pcb.setReportCompName(((ProcurementContract)l.get(i)[0]).getReportCompName_pc());
//			pcb.setReportComp(((ProcurementContract)l.get(i)[0]).getReportCompId_pc());
			if(l.get(i)[1]!=null){
				pcb.setCreditMoney(Double.parseDouble( (l.get(i)[1]).toString() ));
			}else{
				pcb.setCreditMoney(0.0);
			}
			if(l.get(i)[2]!=null){
				try {
					pcb.setCreditDate(sdf.parse((l.get(i)[2]).toString()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				pcb.setCreditDate(null);
			}
			
			list.add(pcb);
		}
		pageModel.setList(list);
		pageModel.setTotal(o==null?0:o.intValue());
		return pageModel;
	}
	private String getParamsSql(String sql,Map params,String lastSql){
		StringBuffer sb = new StringBuffer();
		sb.append(sql);
		if(!params.isEmpty()){
			sb.append(" where 1=1 ");
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
				}else if(temp.startsWith("obj_")){
					User u = (User)params.get("obj_user");
					if(u!=null){
						sb.append(" and p.psid in ("+u.getAuth()+") ");
						//sql += " and p.person.id in ("+u.getAuth()+")";
					}
					Person ps = (Person)params.get("obj_org");
					if(ps!=null){
						sb.append(" and p.orgid = "+ps.getOrg().getId()+" ");
						//sql += " and p.person.id in ("+u.getAuth()+")";
					}
				}else{
					sb.append(" and  p."+pa.getKey()+" like '%"+pa.getValue()+"%' ");
					//sql += " and  p."+pa.getKey()+" like '%"+pa.getValue()+"%'";
				}
			}
			
		}
		if(lastSql!=null && !"".equals(lastSql)){
			sb.append(lastSql);
			//sql += lastSql;
		}
		return sb.toString();
	}
}
