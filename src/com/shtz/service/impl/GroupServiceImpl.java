/**
 * 
 */
package com.shtz.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.BusinessGroup;
import com.shtz.model.DistributeGroupRecord;
import com.shtz.model.Group;
import com.shtz.model.User;
import com.shtz.service.GroupService;
import com.shtz.service.PageService;
import com.shtz.util.PageModel;

/**
 * Filename : BusinessGroupServiceImpl.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:33:47 - 2014-6-26
 *
 */
public class GroupServiceImpl extends HibernateDaoSupport implements
		GroupService {

	private PageService service;
	private PageModel pageModel;
	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	public void setService(PageService service) {
		this.service = service;
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.BusinessGroupService#loadBusinessGroup()
	 */
	public List<BusinessGroup> loadBusinessGroup() {
		// TODO Auto-generated method stub
		String hql = "from BusinessGroup";
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).list();
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.BusinessGroupService#getGroupById(java.lang.Integer)
	 */
	@Override
	public BusinessGroup getBusinessGroupById(Integer id) {
		// TODO Auto-generated method stub
		return (BusinessGroup)this.getHibernateTemplate().getSessionFactory().getCurrentSession().load(BusinessGroup.class, id);
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.BusinessGroupService#addBusinessGroup(com.shtz.model.BusinessGroup)
	 */
	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub
		this.getSession().save(group);
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.GroupService#getDayCount(java.lang.String)
	 */
	@Override
	public Long getDayCount(String day) {
		// TODO Auto-generated method stub
		String hqi = "select count(*) from Group g where g.addDate='"+day+"'";
		return (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hqi).uniqueResult();
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.GroupService#serchGroupsByParams()
	 */
	@Override
	public PageModel serchGroupsByParams(Map params,int offset, int pageSize) {
		// TODO Auto-generated method stub
		String hql = "from Group p ";
		hql = this.getParamsSql(hql, params, " order by p.addDate desc");
		
		return service.getPageModel(hql, offset, pageSize);
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
	/* (non-Javadoc)
	 * @see com.shtz.service.GroupService#getGroupById(java.lang.Integer)
	 */
	@Override
	public Group getGroupById(Integer id) {
		// TODO Auto-generated method stub
		return (Group)this.getSession().load(Group.class, id);
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.GroupService#modifyGroup(com.shtz.model.Group)
	 */
	@Override
	public void modifyGroup(Group group) {
		// TODO Auto-generated method stub
		this.getSession().update(group);
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.GroupService#getBusinessGroupByLeaderId(java.lang.Integer)
	 */
	@Override
	public BusinessGroup getBusinessGroupByLeaderId(Integer leaderId) {
		// TODO Auto-generated method stub
		String hql = "from BusinessGroup b where b.groupLeader.id = "+leaderId;
		return (BusinessGroup)this.getSession().createQuery(hql).uniqueResult();
	}
	/* (non-Javadoc)
	 * @see com.shtz.service.GroupService#getBusinessGroupPersonIdsByLeaderId(java.lang.Integer)
	 */
	@Override
	public List<Integer> getBusinessGroupPersonIdsByLeaderId(Integer leaderId) {
		// TODO Auto-generated method stub
//		String hql = "select p.id from t_person p " +
//				"left join t_businessGroup b on p.sgid = b.id " +
//				"where b.glid = "+leaderId;
		String hql = "select p.id from Person p where p.seekSourceGroup.groupLeader.id="+leaderId;
		return (List<Integer>)this.getSession().createQuery(hql).list();
	}
	@Override
	public void addDistributeGroupRecord(DistributeGroupRecord dr) {
		// TODO Auto-generated method stub
		this.getSession().save(dr);
	}
	@Override
	public Integer getGroupCount() {
		// TODO Auto-generated method stub

		String hql = "select count(*) From Group p where p.status='01' " ;
		Long count = (Long)this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count == null){
			return 0;
		}else{
			return count.intValue();
		}
	}
}
