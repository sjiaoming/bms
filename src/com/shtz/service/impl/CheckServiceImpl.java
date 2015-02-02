/**
 * 
 */
package com.shtz.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Check;
import com.shtz.service.CheckService;

/**
 * Filename : CheckServiceImpl.java
 *
 * @author yao chang
 *
 * Creation time : 下午10:18:11 - 2013-4-18
 *
 */
public class CheckServiceImpl extends HibernateDaoSupport implements
		CheckService {

	/* (non-Javadoc)
	 * @see com.shtz.service.CheckService#addChecks(java.util.List)
	 */
	@Override
	public void addChecks(List<Check> checks) {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for(Check c : checks){
				this.getSession().save(c);
			}
			session.getTransaction().commit();
			session.flush();
			session.clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		finally{
			session.close();
		}
	}

}
