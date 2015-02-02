/**
 * 
 */
package com.shtz.service.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Person;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.User;
import com.shtz.service.PageService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.util.PageModel;

/**
 * Filename : ProcurementCheckImpl.java
 *
 * @author yao chang
 *
 * Creation time : 下午10:30:01 - 2013-4-21
 *
 */
public class ProcurementCheckServiceImpl extends HibernateDaoSupport implements
		ProcurementCheckService {
	private PageService service;

	private PageModel pageModel;
	
	public void setService(PageService service) {
		this.service = service;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#addProcurementCheck(com.shtz.model.ProcurementCheck)
	 */
	@Override
	public void addProcurementCheck(ProcurementCheck pc) {
		// TODO Auto-generated method stub
		this.getSession().save(pc);
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#getNextProcurementCheckNum()
	 */
	@Override
	public int getNextProcurementCheckNum() {
		// TODO Auto-generated method stub
		String sql = "select if(max(p.checkNum) IS NULL,0,max(p.checkNum))+1 from t_procurementCheck p";
		BigInteger bi = (BigInteger)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
		return bi.intValue();
		
	}

	@Override
	public PageModel serchProcurementCheckByParams(Map params, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from ProcurementCheck p ";
		hql = this.getParamsSql(hql, params, " order by p.procurementName asc,p.submitDate desc");
		
		return service.getPageModel(hql, offset, pageSize);
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
				}else if(temp.startsWith("eq_")){
					sb.append(" and  p."+temp.substring(3)+" = '"+pa.getValue()+"' ");
				}else if(temp.startsWith("in_")){
					sb.append(" and  p."+temp.substring(3)+" in ("+pa.getValue()+") ");
				}else if(temp.startsWith("dy_")){
					sb.append(" and  "+temp.substring(3)+" "+pa.getValue()+" ");
				}else if(temp.startsWith("null_")){
					sb.append(" and  "+temp.substring(5)+" is null ");
				}else if(temp.startsWith("bdy_")){
					sb.append(" and  "+temp.substring(4)+" <> "+pa.getValue());
				}else if(temp.startsWith("obj_")){
					User u = (User)params.get("obj_user");
					if(u!=null){
						if("d".equals(params.get("role"))){
							sb.append(" and p.director.id in ("+u.getPerson().getId()+") ");
						}else if("m".equals(params.get("role"))){
							sb.append(" and p.manager.id in ("+u.getPerson().getId()+") ");
						}else if("c".equals(params.get("role"))){
							sb.append(" and p.chairMan.id in ("+u.getPerson().getId()+") ");
						}else if("i".equals(params.get("role"))){
							sb.append(" and p.innerContral.id in ("+u.getPerson().getId()+") ");
						}else{
//							sb.append(" and p.person.id in ("+u.getAuth()+") ");
							sb.append(" and p.person.id in ("+u.getPerson().getId()+") ");
						}
					}
				}else if(temp.startsWith("mh_")){
					if(pa.getValue()!=null && !pa.getValue().equals(""))
						sb.append(" and  p."+temp.substring(3)+" like '%"+pa.getValue()+"%' ");
				}
			}
			
		}
		if(lastSql!=null && !"".equals(lastSql)){
			sb.append(lastSql);
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#ModifyProcurementCheck(com.shtz.model.ProcurementCheck)
	 */
	@Override
	public void modifyProcurementCheckD(ProcurementCheck pc,String ids) {
		// TODO Auto-generated method stub
		String hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"',p.director.id="+pc.getDirector().getId()+" where p.id in (" + ids + ")";
		System.out.println("ModifyProcurementCheck director >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	@Override
	public void modifyProcurementCheckI(ProcurementCheck pc,String ids) {
		// TODO Auto-generated method stub
		String hql = null;
		if("08".equals(pc.getCheckStatus())){
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"',p.innerContral.id="+pc.getInnerContral().getId()+" where p.id in (" + ids + ")";
		}else{
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"' where p.id in (" + ids + ")";
		}
		System.out.println("ModifyProcurementCheck innerContral >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	@Override
	public void modifyProcurementCheckM(ProcurementCheck pc,String ids) {
		// TODO Auto-generated method stub
		String hql = null;
		if("03".equals(pc.getCheckStatus())){
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"',p.manager.id="+pc.getManager().getId()+" where p.id in (" + ids + ")";
		}else{
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"' where p.id in (" + ids + ")";
		}
		System.out.println("ModifyProcurementCheck manager >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	@Override
	public void modifyProcurementCheck(ProcurementCheck pc,String ids) {
		// TODO Auto-generated method stub
		String hql = null;
		if(pc.getCheckStatus().equals("07")){
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"',p.groupStatus='01' where p.id in (" + ids + ")";
		}else{
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"' where p.id in (" + ids + ")";
		}
		System.out.println("ModifyProcurementCheck chairMan setting >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	public void modifyProcurementCheckGroup(ProcurementCheck p){
		this.getSession().update(p);
	}
	@Override
	public void modifyProcurementCheck(ProcurementCheck p){
		//modify 20131014
		String hql = "update ProcurementCheck p set p.supplierIds = '"+p.getSupplierIds()+"',p.director.id = "+p.getDirector().getId()+"," +
					" p.caseNum='"+p.getCaseNum()+"',p.packageNum='"+p.getPackageNum()+"',p.checkStatus='01',p.procurementName='"+p.getProcurementName()+"'  " +
					",p.procurementWay='"+p.getProcurementWay()+"' where p.id =" + p.getId();
//					",pc.procurementWay='"+p.getProcurementWay()+"' where p.id =" + p.getId();
		System.out.println("modifyProcurementCheck  >>>><<<< "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#getPCs(java.lang.String)
	 */
	@Override
	public List<ProcurementCheck> getPCs(String ids) {
		// TODO Auto-generated method stub
		String hql = "From ProcurementCheck p where p.id in (" + ids + ")";
		
		return (List<ProcurementCheck>)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#getProcurementCheck(int)
	 */
	@Override
	public ProcurementCheck getProcurementCheck(int id) {
		// TODO Auto-generated method stub
		return (ProcurementCheck)this.getHibernateTemplate().getSessionFactory().getCurrentSession().load(ProcurementCheck.class, id);
	}

	@Override
	public void addPlan(String ids, int pcId) {
		String hql = "update Plan p set p.pc.id="+pcId+",p.planStatus='02' where p.id in (" + ids + ")";
		System.out.println("addPlan  ProcurementCheck  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#modifyAddSeekSource(int)
	 */
	@Override
	public void modifyAddSeekSource(int id) {
		// TODO Auto-generated method stub

		String hql = "update ProcurementCheck c set c.finishSeekSource='true' where c.id="+id;	
		System.out.println(" ProcurementCheck  modifyAddSeekSource   >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#modifyProcurementCheckC(com.shtz.model.ProcurementCheck, java.lang.String)
	 */
	@Override
	public void modifyProcurementCheckC(ProcurementCheck pc, String ids) {
		// TODO Auto-generated method stub
		String hql = null;
		//05经理通过
		if("05".equals(pc.getCheckStatus())){
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"',p.chairMan.id="+pc.getChairMan().getId()+" where p.id in (" + ids + ")";
		}else{
			hql = "update ProcurementCheck p set p.opinion = '"+pc.getOpinion()+"',p.checkStatus='"+pc.getCheckStatus()+"' where p.id in (" + ids + ")";
		}
		System.out.println("ModifyProcurementCheck manager >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#checkPCStatus(java.lang.String)
	 */
	@Override
	public String checkPCStatus(String ids,String needStatus) {
		// TODO Auto-generated method stub
		String[] status = needStatus.split(",");
		Map<String,String> mp = new HashMap<String, String>();
		for(int i=0;i<status.length;i++){
			mp.put(status[i], status[i]);
		}
		System.out.println("ids      "+ids);
		String hql = "From ProcurementCheck p where p.id in (" + ids + ")";
		String s = "yes";
		List<ProcurementCheck> ls = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
		for(ProcurementCheck p : ls){
			if(!mp.keySet().contains(p.getCheckStatus())){
				s = "no";
			}
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.ProcurementCheckService#getPCCountByPersonId(int)
	 */
	@Override
	public Integer getPCCountByPersonId(int personId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) From Plan p where p.groupStatus='04' and p.checkStatus='05' and " +
					" p.planStatus='01' and p.person.id = " + personId ;
		Long count = (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count == null){
			return 0;
		}else{
			return count.intValue();
		}
	}

	@Override
	public String getMaxCaseNum(String head) {
		// TODO Auto-generated method stub
		return (String)this.getSession().createQuery("select max(t.caseNum) from ProcurementCheck t where t.caseNum like '"+head.trim()+"%'").uniqueResult() ;
	}

	@Override
	public void modifyProcurementOperatorByIds(String tags, int operatorId) {
		// TODO Auto-generated method stub
		String hql = "update ProcurementCheck p set p.seekSourceOperator.id = "+operatorId+",p.groupStatus='04' where p.id in ("+tags+")";
		this.getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void modifyPlansByIdsBack(String ids) {
		// TODO Auto-generated method stub
		//02 己退回，03己分组
		String hql = "update ProcurementCheck p set p.groupStatus='03' where p.id in ("+ids+")";
		this.getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public void modifyPcFinishSeekSource(String ids) {
		// TODO Auto-generated method stub
		String hql = "update ProcurementCheck c set c.finishSeekSource = null where c.id in ("+ids+")";	
			System.out.println(" ProcurementCheck  modifyAddSeekSource   >>>> "+hql);
			this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		}

	@Override
	public Integer getPCCountByRole(int personId, String role) {
		// TODO Auto-generated method stub
		
		String hql = "select count(*) from ProcurementCheck p where " ;
		if("d".equals(role)){
			// 主管末审核    内控末通过审核
			hql = hql + "p.checkStatus in ('01','09') and p.director.id = "+personId; 
		}else if("i".equals(role)){
			//内控末审核  经理末通过审核
			hql = hql + "p.checkStatus in ('08','04') and p.innerContral.id = "+personId; 
		}else if("m".equals(role)){
			//经理末审核  董事长末通过审核
			hql = hql + "p.checkStatus in ('03','06') and p.manager.id = "+personId; 
		}else if("c".equals(role)){
			//董事长末审核
			hql = hql + "p.checkStatus in ('05') and p.chairMan.id = "+personId; 
		}else{
			hql = hql + "p.checkStatus in ('00') and p.chairMan.id = "+personId; 
		}
		hql = hql + " order by p.procurementName asc,p.submitDate desc ";
		
		Long count = (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count == null){
			return 0;
		}else{
			return count.intValue();
		}
	}
}
