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

import com.shtz.model.ProcurementCheck;
import com.shtz.model.SeekSource;
import com.shtz.model.User;
import com.shtz.service.PageService;
import com.shtz.service.SeekSourceService;
import com.shtz.util.PageModel;

/**
 * Filename : SeekSourceServiceImpl.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:59:10 - 2013-4-26
 *
 */
public class SeekSourceServiceImpl extends HibernateDaoSupport implements SeekSourceService {
	private PageService service;

	private PageModel pageModel;

	public void setService(PageService service) {
		this.service = service;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#addSeekSource(com.shtz.model.SeekSource)
	 */
	@Override
	public void addSeekSource(SeekSource seekSource) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().save(seekSource);
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#modifySeekSource(com.shtz.model.SeekSource)
	 */
	@Override
	public void modifySeekSource(SeekSource seekSource) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().update(seekSource);
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#getSeekSource(int)
	 */
	@Override
	public SeekSource getSeekSource(int id) {
		// TODO Auto-generated method stub
		return (SeekSource)this.getHibernateTemplate().getSessionFactory().getCurrentSession().load(SeekSource.class, id);
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#serchSeekSourceByParams(java.util.Map, int, int)
	 */
	@Override
	public PageModel serchSeekSourceByParams(Map params, int offset,
			int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from SeekSource p ";
		hql = this.getParamsSql(hql, params, " order by p.pc.procurementName asc,p.submitDate asc");
		
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
//						sb.append(" and p.person.id in ("+u.getAuth()+") ");
					}
				}else if(temp.startsWith("mh_")){
					if(pa.getValue()!=null && !pa.getValue().equals(""))
						sb.append(" and  p."+temp.substring(3)+" like '%"+pa.getValue()+"%' ");
				}else if(temp.startsWith("persons")){
					if(pa.getValue()!=null && !pa.getValue().equals(""))
						sb.append(" and  p.person.id in ("+pa.getValue()+")");
					}
				}
			
		}
		if(lastSql!=null && !"".equals(lastSql)){
			sb.append(lastSql);
		}
		return sb.toString();
	}


	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#delSeekSourceDetail(int)
	 */
	@Override
	public void deleteSeekSourceDetail(String sdId) {
		// TODO Auto-generated method stub
		String hql = "delete from SeekSourceDetail s where s.id in ( "+sdId+" )";
		System.out.println("delSeekSourceDetail  >>>><<<< "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public int getNextSeekSourceNum() {
		// TODO Auto-generated method stub
		String sql = "select if(max(p.checkNum) IS NULL,0,max(p.checkNum))+1 from t_SeekSource p";
		BigInteger bi = (BigInteger)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql).uniqueResult();
		return bi.intValue();
		
	}
	@Override
	public void modifyPlansSS(String ids){
		// TODO Auto-generated method stub

//		String hql = "update Plan p set p.planStatus='03',p.checkStatus='01' where p.id in (" + ids + ")" ;
		//modify for seeksource don't need check by d,i,m,c
		String hql = "update Plan p set p.planStatus='03',p.checkStatus='07' where p.id in (" + ids + ")" ;
		System.out.println("modifyPlans for add seekSource  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	@Override
	public void modifyPlansCheckStatus(String status,String ids){
		// TODO Auto-generated method stub

		String hql = "update Plan p set p.checkStatus='"+status+"' where p.id in (" + ids + ")";
		System.out.println("modifyPlansPc  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	@Override
	public void modifyPlansSSLeaderStatus(String status,String ids){
		// TODO Auto-generated method stub
		
		String hql = "update Plan p set p.seekSourceStatus='"+status+"' where p.id in (" + ids + ")";
		System.out.println("modifyPlansPc  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#modifySeekSourceCheck(com.shtz.model.SeekSource, java.lang.String)
	 */
	@Override
	public void modifySeekSourceCheck(SeekSource seekSource, String ids) {
		// TODO Auto-generated method stub
		String hql="";
		if(seekSource.getManager() != null && seekSource.getManager().getId() != 0){
			hql = "update SeekSource p set p.checkStatus='"+seekSource.getCheckStatus()+"' ,p.opinion = '"+seekSource.getOpinion()+"'" +
				" ,p.manager.id=" + seekSource.getManager().getId() + " where p.id in (" + ids + ")";
		}else if(seekSource.getChairMan() != null && seekSource.getChairMan().getId() != 0){
			hql = "update SeekSource p set p.checkStatus='"+seekSource.getCheckStatus()+"' ,p.opinion = '"+seekSource.getOpinion()+"'" +
			" ,p.chairMan.id=" + seekSource.getChairMan().getId() + " where p.id in (" + ids + ")";
		}else if(seekSource.getInnerContral() != null && seekSource.getInnerContral().getId() != 0){
			hql = "update SeekSource p set p.checkStatus='"+seekSource.getCheckStatus()+"' ,p.opinion = '"+seekSource.getOpinion()+"'" +
			" ,p.innerContral.id=" + seekSource.getInnerContral().getId() + " where p.id in (" + ids + ")";
		}else{
			hql = "update SeekSource p set p.checkStatus='"+seekSource.getCheckStatus()+"' ,p.opinion = '"+seekSource.getOpinion()+"' where p.id in (" + ids + ")";
		}
//		String hql = "update SeekSource p set p.opinion = '"+seekSource.getOpinion()+"',p.checkStatus='"+seekSource.getCheckStatus()+"' where p.id in (" + ids + ")";
		System.out.println("modifySeekSourceCheck add manager  >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}

	public void modifySeekSourceCheckForLeader(SeekSource seekSource, String ids) {
		// TODO Auto-generated method stub
		String hql = "update SeekSource p set p.leaderStatus='"+seekSource.getLeaderStatus()+"' ,p.opinion = '"+seekSource.getOpinion()+"' where p.id in (" + ids + ")";
		System.out.println("modifySeekSourceCheckForLeader   >>>> "+hql);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#getSeekSources(java.lang.String)
	 */
	@Override
	public List<SeekSource> getSeekSources(String ids) {
		// TODO Auto-generated method stub
		String hql = "From SeekSource p where p.id in (" + ids + ")";
		
		return (List<SeekSource>)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#checkPCStatus(java.lang.String)
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
		String hql = "From SeekSource p where p.id in (" + ids + ")";
		String s = "yes";
		List<SeekSource> ls = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
		for(SeekSource p : ls){
			if(!mp.keySet().contains(p.getCheckStatus())){
				s = "no";
			}
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see com.shtz.service.SeekSourceService#getSSCountByPersonId(int)
	 */
	@Override
	public Integer getSSCountByRoleAndPersonId(int personId,String role) {
		// TODO Auto-generated method stub
		String hql = "";
		if(role == null){
			hql = "select count(*) From ProcurementCheck p where p.seekSourceOperator.id = " + personId +" " +
			" and p.checkStatus='07' and p.groupStatus='04' and p.finishSeekSource is null ";
		}else if(role.equals("dunGroup")){
			hql = "select count(*) From ProcurementCheck p where " +
			" p.checkStatus='07' and p.groupStatus='01' and p.finishSeekSource is null ";
		}else if(role.equals("dsetSeekSourceOperator")){
			hql = "select count(*) From ProcurementCheck p where p.group.groupLeader.id=" + personId +
				  " and p.checkStatus='07' and p.groupStatus='03' and p.finishSeekSource is null ";
		}else if(role.equals("dSeekSourceCheck")){
			String operatorsHql = "select p.id from Person p where p.seekSourceGroup.groupLeader.id="+personId;
			List<Integer> oids = (List<Integer>)this.getSession().createQuery(operatorsHql).list();
			String operatorIds = (oids.toString().replace("[", "").replace("]", ""));
			hql = "from SeekSource p where p.leaderStatus='01' and p.person.id in ("+operatorIds+")";
		}
		Long count = (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count == null){
			return 0;
		}else{
			return count.intValue();
		}
	}

	@Override
	public void deleteSeekSource(String ids){
		// TODO Auto-generated method stub
//		SeekSource s = ;
		String sql2 = " delete from t_seeksourceDetail where sid in ("+ids+")";
		String sql = "delete from t_seeksource where id in ("+ids+")" ;
		this.getSession().createSQLQuery(sql2).executeUpdate();
		this.getSession().createSQLQuery(sql).executeUpdate();
	}
}
