package com.shtz.test;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.Session;

import com.shtz.model.Organization;

/**
 * @author sjm
 *  2012-1-5
 */
public class OrgTest extends TestCase {
	
	public void test1_save(){
		
		Session session = null;
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Set<Organization> set = new HashSet<Organization>();
			
			Organization org2 = new Organization();
			org2.setName("org2");
			set.add(org2);
			
			Organization org3 = new Organization();
			org3.setName("org3");
			set.add(org3);
			
			Organization org4 = new Organization();
			org4.setName("org4");
			set.add(org4);
			
			Organization org1 = new Organization();
			org1.setName("org1");
			org1.setChildren(set);
			
			session.save(org1);
			session.save(org2);
			session.save(org3);
			session.save(org4);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void test2_load(){
		
		Session session = null;
		
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			Set<Organization> set = new HashSet();
			
			Organization org = (Organization)session.load(Organization.class, 1);
			
			System.out.println(org.getName());
			
			set = org.getChildren();
			
			for(Organization o : set){
				
				System.out.println(o.getName());
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
