package com.shtz.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Calendar;

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
public class FileUpLoad extends HttpServlet {
	private ServletConfig config;

	final public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.setCharacterEncoding("text/html;utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		// out.println("<BODY BGCOLOR='white'>");
		// out.println("<H1>jspSmartUpload : Servlet Sample</H1>");
		out.println("<HR>");
		// 变量定义
		int count = 0;
		SmartUpload mySmartUpload = new SmartUpload();
		String saveurl = "";
		String url = "upload/";
		String ext = "";
		try {
			mySmartUpload.initialize(config, request, response);
			mySmartUpload.upload();
			System.out.println("=====1==="+mySmartUpload.getFiles().getSize());
			Calendar calendar = Calendar.getInstance();

			String filename = String.valueOf(calendar.getTimeInMillis());

			saveurl = config.getServletContext().getRealPath("/") + url;

			for (int i = 0; i < mySmartUpload.getFiles().getCount(); i++) {
				com.jspsmart.upload.File myfile = mySmartUpload.getFiles()
						.getFile(i);
				System.out.println("====2===="+myfile.getSize());
				ext = myfile.getFileExt();
				saveurl += filename + "." + ext; // 保存路径
				// String fileName = myfile.getFileName();
				myfile.saveAs(saveurl, SmartUpload.SAVE_PHYSICAL);
				// count = mySmartUpload.save("/upload");
				// count = mySmartUpload.save(null);
			}
			// out.println(count + " file uploaded.");
		} catch (Exception e) {
			System.out.println("======"+e.toString());
			 out.println("Unable to upload the file.<br>");
			 out.println("Error : " + e.toString());

		}
		out.write("<script language='javascript'>alert('导入成功');" +
				"window.location='businessManagement/planMg/import_success.jsp';</script>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		doGet(request, response);
	}

}
