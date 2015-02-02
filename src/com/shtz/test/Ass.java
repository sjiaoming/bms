package com.shtz.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * @author Brown.Yao/姚昌:
 * @version 创建时间：May 7, 2013 2:50:10 PM
 * 
 */

public class Ass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			String s ="20130536";
			try{
				System.out.println(sdf.parse(s));
				Calendar c = Calendar.getInstance();
				c.setLenient(false);
				c.setTime(sdf.parse(s));
//				c.
				c.getTime();
//			sdf.parse("2013-05-26");
			}catch(Exception e) {
				e.printStackTrace();
			}/**/
	}

}
