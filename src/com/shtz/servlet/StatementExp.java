package com.shtz.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

/**
 * @author sjm
 *  
 */
public class StatementExp extends HttpServlet {
	private ServletConfig config;
	 final public void init(ServletConfig config) throws ServletException {
	  this.config = config;
	 }
	/**
	 * Constructor of the object.
	 */
	public StatementExp() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		String msg = request.getParameter("msg");
		String[] msga = msg.split(",");
		//String msg = (String)request.getAttribute("msg");
		System.out.println("msg:"+msg);
		File file = new File("D:\\360CloudCache\\8168928\\08 Workspaces\\shtz\\WebRoot\\upload"); 
	    String s  = getLastFile(file);
	    String str = getExtName(s, '.');
	    
	    if(str!=null&&!"".equals(str)){
	    	if(str.equals("xlsx")){
	    		ExcelRead2010 parser = new ExcelRead2010();
	    		parser.importExcel("D:\\360CloudCache\\8168928\\08 Workspaces\\shtz\\WebRoot\\upload\\"+s);
	    		System.out.println("--行数--"+parser.getRowIndex(0));
	        	List<Object> listparam=null;
	        	String temp_user_id="";
	        	int rowcount=parser.getRowIndex(0);
	        	
	        	List<String> list = new ArrayList<String>();
	        	ExcelCreate e = new ExcelCreate();
	        	List lists = new ArrayList();
	        	e.createSheet("系统报名表");
	        	  for (int i = 0; i <= rowcount; i++) {
	    				List listuser = parser.readRow(0, i);
	    				
	    				if(i==0){
	    					for(int m=0;m<msga.length;m++){
		    					if(listuser.get(Integer.parseInt(msga[m]))==null){
		    						temp_user_id = " ";
		    					
		    					}else{
		    						temp_user_id = listuser.get(Integer.parseInt(msga[m])).toString();
		    					}
		    					
		    					list.add(temp_user_id);
		    				}
	    					
	    		    		
	    		    		try {
								e.addHeader(list, true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	    				}else{
		    				for(int m=0;m<msga.length;m++){
		    					
		    					if(listuser.get(Integer.parseInt(msga[m]))==null){
		    						temp_user_id = " ";
		    					
		    					}else{
		    						temp_user_id = listuser.get(Integer.parseInt(msga[m])).toString();
		    					}
		    					lists.add(temp_user_id);
		    					System.out.print(temp_user_id+"--");
		    				}
		    				e.addRow(lists); 
	    				}
	    				
	    				lists.clear();
	                  System.out.println("--第"+i+"行列数"+listuser.size());
	        	  }
	    		
	        	File file1 = new File("D:\\360CloudCache\\8168928\\08 Workspaces\\shtz\\WebRoot\\upload\\shtz.xls");
	      		try {
					e.exportExcel(file1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	    	}else if(str.equals("xls")){
	    		ExcelRead parser = new ExcelRead();
	    		parser.importExcel("D:\\360CloudCache\\8168928\\08 Workspaces\\myTest\\WebRoot\\upload\\"+s);
	    		System.out.println("--行数--"+parser.getRowIndex(0));
	        	List<Object> listparam=null;
	        	String temp_user_id="";
	        	int rowcount=parser.getRowIndex(0);
	        	
	        	List<String> list = new ArrayList<String>();
	        	ExcelCreate e = new ExcelCreate();
	        	List lists = new ArrayList();
	        	e.createSheet("系统报名表");
	    		
	        	  for (int i = 0; i <= rowcount; i++) {
	    				List listuser = parser.readRow(0, i);
	    				
	    				if(i==0){
	    					for(int m=0;m<msga.length;m++){
		    					if(listuser.get(Integer.parseInt(msga[m]))==null){
		    						temp_user_id = " ";
		    					
		    					}else{
		    						temp_user_id = listuser.get(Integer.parseInt(msga[m])).toString();
		    					}
		    					
		    					list.add(temp_user_id);
		    				}
	    					
	    		    		
	    		    		try {
								e.addHeader(list, true);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	    				}else{
		    				for(int m=0;m<msga.length;m++){
		    					
		    					if(listuser.get(Integer.parseInt(msga[m]))==null){
		    						temp_user_id = " ";
		    					
		    					}else{
		    						temp_user_id = listuser.get(Integer.parseInt(msga[m])).toString();
		    					}
		    					lists.add(temp_user_id);
		    					System.out.print(temp_user_id+"--");
		    				}
		    				e.addRow(lists); 
	    				}
	    				
	    				lists.clear();
	                  System.out.println("--第"+i+"行列数"+listuser.size());
	        	  }
	    		
	        	File file1 = new File("D:\\360CloudCache\\8168928\\08 Workspaces\\shtz\\WebRoot\\upload\\shtz.xls");
	      		try {
					e.exportExcel(file1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	    	}
	    }
	    
	    SmartUpload mySmartUpload = new SmartUpload();
		  try {
		   mySmartUpload.initialize(config, request, response);
		   mySmartUpload.setContentDisposition(null);
		   mySmartUpload.downloadFile("/upload/shtz.xls");
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
	    
	    
	    
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	private String getLastFile(File file) { 
		 List<String> l = new ArrayList();
	     File[] files = file.listFiles(); // 获取文件夹下面的所有文件 
	     for (File f : files) { 
	      if (f.isDirectory()) { 
	    	  getLastFile(f);      
	         } else { 
	        l.add(f.getName());	 
	      } 
	     }
	     Collections.sort(l);
	     return l.get(l.size()-1);
	  }
	//获取后缀
	private static String getExtName(String s, char split) {
        int i = s.indexOf(split);
        int leg = s.length();
        return (i > 0 ? (i + 1) == leg ? " " : s.substring(i+1, s.length()) : " ");
    }

}
