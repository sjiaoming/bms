package com.shtz.util;

public class SystemContext {
	
	private static ThreadLocal<Integer> offset = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pagesize = new ThreadLocal<Integer>();
	
	public static int getOffset() {
		Integer os = offset.get();
		System.out.println("os:" + os);
		
		if(os == null) {
			return 0;
		}
		
		return os;
	}
	
	public static void setOffset(int offsetValue) {
		offset.set(offsetValue);
	}
	
	public static void removeOffset() {
		offset.remove();
	}
	
	public static int getPagesize() {
		Integer ps = pagesize.get();
		System.out.println("ps:" + ps);
		if(ps == null) {
			return Integer.MAX_VALUE;
		}
		
		return ps;
	}
	
	public static void setPagesize(int pagesizeValue) {
		pagesize.set(pagesizeValue);
	}
	
	public static void removePagesize() {
		pagesize.remove();
	}
	
}
