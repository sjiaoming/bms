package com.shtz.test;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shtz.service.InitSystemDatas;

public class InitSystemDatasTest extends TestCase {
	private static BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
	public void testAddOrUpdateInitDatas() {
		InitSystemDatas init = (InitSystemDatas)factory.getBean("initSystemDatas");
		init.addOrUpdateInitDatas("init_data.xml");
	}

}
