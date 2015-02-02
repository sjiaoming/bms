/**
 * 
 */
package com.shtz.action;

import java.util.ArrayList;
import java.util.List;

/**
 * Filename : Test.java
 *
 * @author yao chang
 *
 * Creation time : 上午12:40:34 - 2014-7-31
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> ls = new ArrayList<Integer>();
		ls.add(1);
		ls.add(2);
		System.out.println(ls.toString().replace("[", "").replace("]", ""));
	}

}
