package com.shtz.test;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shtz.model.Organization;
import com.shtz.service.OrgService;

/**
 * @author sjm
 *  2012-1-5
 */
public class OrgServiceTest extends TestCase {
	
	public static BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
	
	public void testAddOrg() {
		OrgService os = (OrgService)factory.getBean("orgService");
		
		Organization og = new Organization();
		og.setName("≤‚ ‘5");
		og.setDescription(" ø¥Û∑Ú5");
		
		os.addOrg(og, 2);
	}

	public void testDeleteOrg() {
		fail("Not yet implemented");
	}

	public void testModifyOrg() {
		fail("Not yet implemented");
	}

	public void testFindOrgById() {
		fail("Not yet implemented");
	}

	public void testFindOrgs() {
		fail("Not yet implemented");
	}

}
