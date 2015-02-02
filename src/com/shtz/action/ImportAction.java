package com.shtz.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.SeekSource;
import com.shtz.model.SeekSourceDetail;
import com.shtz.model.Suppliers;
import com.shtz.service.PersonService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.SeekSourceService;
import com.shtz.service.SuppliersService;
import com.shtz.service.UserCompService;
import com.shtz.util.ExcelCreate;
import com.shtz.util.ExcelRead;
import com.shtz.util.ExcelRead2010;

/**
 * @author sjm
 *  
 */
public class ImportAction extends ActionSupport {

	private ExcelCreate excelCreateService;
	
	private ExcelRead ExcelReadService;
	
	private ExcelRead2010 ExcelReadxService;
	
	private String importfilename;
	
	private File some;
	
	private PlanService pservice;
	
	private PersonService personService;
	private SuppliersService supplierService;

	private ProcurementCheckService pcService;
	
	public void setSeekSourceService(SeekSourceService seekSourceService) {
		this.seekSourceService = seekSourceService;
	}
	private SeekSourceService seekSourceService;
	
	private Map<Integer, Map<Integer,String>> Feedback =new TreeMap<Integer, Map<Integer,String>>();
	
	private int numb;
	
	private String ex;
	
	private UserCompService ucservice;
	
	public void setSupplierService(SuppliersService supplierService) {
		this.supplierService = supplierService;
	}
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	public void setUcservice(UserCompService ucservice) {
		this.ucservice = ucservice;
	}
	public String getEx() {
		return ex;
	}
	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}
	public void setEx(String ex) {
		this.ex = ex;
	}
	public void setExcelReadxService(ExcelRead2010 excelReadxService) {
		ExcelReadxService = excelReadxService;
	}
	public File getSome() {
		return some;
	}
	public void setSome(File some) {
		this.some = some;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}
	public Map<Integer, Map<Integer, String>> getFeedback() {
		return Feedback;
	}
	public void setFeedback(Map<Integer, Map<Integer, String>> feedback) {
		Feedback = feedback;
	}
	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}
	public String getImportfilename() {
		return importfilename;
	}
	public void setImportfilename(String importfilename) {
		this.importfilename = importfilename;
	}
	public void setExcelReadService(ExcelRead excelReadService) {
		ExcelReadService = excelReadService;
	}
	public void setExcelCreateService(ExcelCreate excelCreateService) {
		this.excelCreateService = excelCreateService;
	}
	
	public String importSeekSource() throws Exception {
		
		String someFileName="seekSource.xls";
		String path = "/upload";
		try {
			//路径upload创建,不存在就创建，存在就不创建
			String filePath = "/bms/upload";
			File file = new File(filePath);
			 //判断文件夹是否存在,如果不存在则创建文件夹
			 if (!file.exists()) {
				 file.mkdirs();
				 /*if(file.mkdir()){ //创建文件夹，如果要创建文件： 
				   file.createNewFile();
			   	}*/
			 }
		} catch (Exception e) {
		}
		
		path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(path);
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(some));
		File f=new File(path, someFileName);
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		IOUtils.copy(is, os);
		is.close();
		os.close();
	
		//excel读取文件
		if(ex!=null&&ex.equals("xls")){
			ExcelReadService.importExcel(path + "/" + someFileName);
		}else if(ex!=null&&ex.equals("xlsx")){
			ExcelReadxService.importExcel(path + "/" + someFileName);
		}
        //List<List<String>> allList=new ArrayList<List<String>>();
		int rowcount=0;
		if(ex!=null&&ex.equals("xls")){
			 rowcount=ExcelReadService.getRowIndex(0);
		}else if(ex!=null&&ex.equals("xlsx")){
			rowcount=ExcelReadxService.getRowIndex(0);
		}
		rowcount = rowcount - 5;
		List<Plan> ps = new ArrayList<Plan>();
		List listuser = new ArrayList();
		List listsupplier = new ArrayList();
		List<List> lists = new ArrayList();
//    	  numb=rowcount;
		List<Suppliers> suppliers = new ArrayList<Suppliers> ();
		listsupplier = ExcelReadService.readRow(0, 2);
		for(int i=0;i<listsupplier.size();i++){
			if(i == 0){
				String supplierName = (String)listsupplier.get(i);
				if(!supplierName.contains(",")){
					supplierName = supplierName.substring(supplierName.indexOf("：")+1);
					if(supplierName == null || supplierName.equals("")){
						 Map<Integer,String> tempmap= new HashMap<Integer, String>();
						 tempmap.put(1, "供应商不能为空");
		    			 Feedback.put(0, tempmap);
	//	       		  if(f.exists()){
	//	    			  f.delete();
	//	    		  }
	//	    		  return "feedback";
					}
					System.out.println(listsupplier.get(i));
					suppliers = supplierService.findSuppliersByNames(supplierName.trim());
					if(suppliers.size()==0){
						 Map<Integer,String> tempmap= new HashMap<Integer, String>();
						 tempmap.put(1, "无此供应商   "+supplierName);
		    			 Feedback.put(0, tempmap);
	//		       		 if(f.exists()){
	//		    			  f.delete();
	//		       		 }
	//		    		 return "feedback";
					}else{
					System.out.println(suppliers.get(0).getName()+"  id   "+suppliers.get(0).getId());
					}
				}else{
					 Map<Integer,String> tempmap= new HashMap<Integer, String>();
					 tempmap.put(1, "请输入一个供应商   ");
	    			 Feedback.put(0, tempmap);
				}
			}
		}

  	  if(Feedback.size()!=0){
  		  if(f.exists()){
  			  f.delete();
  		  }
  		  return "feedback";
  	  }
    	  for (int i = 4; i <= rowcount; i++) {
    		  if(ex!=null&&ex.equals("xls")){
    			  listuser =  ExcelReadService.readRow(0, i);
    			}else if(ex!=null&&ex.equals("xlsx")){
    				listuser = ExcelReadxService.readRow(0, i);
    			}
    		  lists.add(listuser);
    		  
    		  Map<Integer,String> tempmap= this.isNumericSeekSource(listuser,ps);
    		  
    		  Map<Integer,String> map= new HashMap<Integer, String>();
    		  boolean flag=false;
    		  for (int k=0;k<=listuser.size();k++) {
//    			  System.out.println(d+"   value    "+map.get(d));
    			  if(tempmap.get(k) != null){
	    			  map.put(k, tempmap.get(k));
	    			  System.out.println(k+"  value   "+tempmap.get(k));
    			  }else{
	    			  map.put(k, "");
    			  }
    		  }
    		  for (String str : map.values()) {
				if(str != null && !str.equals("")){
					flag=true;
				}
    		  }
    		  if(flag){
    			  
    			  if(Feedback.size()>20){
    				  
        			  ServletActionContext.getRequest().getSession().setAttribute("msg","友情提示：您的错误数超过20个，请仔细核对后再尝试。");
        			  break;
        		  }else{
        			  ServletActionContext.getRequest().getSession().setAttribute("msg","错误信息");
        		  }
    			  Feedback.put(i, map);
    		  }
    		  numb++ ;
    	  }
    	  
    	  if(Feedback.size()!=0){
    		  if(f.exists()){
    			  f.delete();
    		  }
    		  return "feedback";
    	  }else{
    		  SeekSource seek =  new SeekSource();

    			int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
    			Person pe = personService.findPerson(personId);
    			seek.setPerson(pe);
//    			seek.setPc(null)
    			seek.setSubmitDate(new Date());
    			seek.setCheckStatus("07");
    			seek.setLeaderStatus("01");
    			int checkNum = seekSourceService.getNextSeekSourceNum();
    			seek.setCheckNum(checkNum);

    			Set<SeekSourceDetail> sets = new HashSet<SeekSourceDetail>();
    		  ProcurementCheck pc = new ProcurementCheck();
			  for (int i = 1; i <= numb; i++) {
    			  SeekSourceDetail sd = new SeekSourceDetail();
    			  String numRow = "";
    			  sd.setSeekSource(seek);
    			  sd.setSupplier(suppliers.get(0));
    			  String num = lists.get(i-1).get(0).toString();
		  		  String row = lists.get(i-1).get(0+1).toString();
		  		  numRow = num+","+row ;
		  		  Plan p = getPlan(ps,numRow);
		  		  if(i == 1)
		  			  pc = p.getPc();
    			  for (int j = 0; j < lists.get(i-1).size(); j++) {
	    			  if(lists.get(i-1).get(j)!=null){
	    				  switch (j) {
	    				  case 0: //String num = lists.get(i-1).get(j).toString();
	    				  		  //String row = lists.get(i-1).get(j+1).toString();
	    				  		 // numRow = num+","+row ;
	    				  		  //Plan p = getPlan(ps,numRow);
	    					  	  p.setSupplier(suppliers.get(0));	
	    					  	  p.setSeekSourceStatus("01");
	    				  		  sd.setPlan(p);
	    				  		  break;
	    				  case 2: sd.setMaterialName(lists.get(i-1).get(j).toString());break;
	    				  case 4: sd.setSeekSourceQuantity(Double.parseDouble(lists.get(i-1).get(j).toString()));
	    				  		  p.setContractNum(sd.getSeekSourceQuantity());
//	    				  System.out.println("q   "+sd.getSeekSourceQuantity());
	    				  			break;
	    				  case 5: String price = lists.get(i-1).get(j).toString();
	    				  		  sd.setSeekSourcePrice(Double.parseDouble(price));
	    	    				  p.setProcurementPrice(sd.getSeekSourcePrice());
	    	    				  break;
	    				  case 6: sd.setSeekSourceM(Double.parseDouble(lists.get(i-1).get(j).toString()));
//			    				  System.out.println("m   "+sd.getSeekSourceM());
//						  		  Plan p1 = getPlan(ps,numRow);
						  		  p.setProcurementMoney(sd.getSeekSourceM());
			    				  break;
	    				  }
		    		  }
		    	  }
    			  sets.add(sd);
    		  }
			  seek.setPc(pc);
    		 String pIds = "";
    		 for(Plan p : ps){
    			 pIds = pIds+","+p.getId();
       		  System.out.println("PCID   "+p.getPc().getId());
    		 }
    		 pIds = pIds.substring(1);
    		  seek.setDetails(sets);
    		  pcService.modifyAddSeekSource(ps.get(0).getPc().getId());
    		  System.out.println("pland Ids   "+pIds);
    			//更新plan表为03已寻源状态
    		  seekSourceService.modifyPlansSS(pIds);
    		  seekSourceService.addSeekSource(seek);
    	  }
    	  
		return "importSeekSource_success";
	}

    private Plan getPlan(List<Plan> ps,String numAndRow){
    	for(Plan p : ps){
    		String planNUMAndROW = p.getPlanNum()+","+p.getPlanRowNum();
    		if(planNUMAndROW.equals(numAndRow)){
    			return p;
    		}
    	}
    	return null;
    }

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		//上传到服务器端upload固定文件中,文件 名改为test.xls
		String someFileName="test.xls";
		String path = "/upload";
		try {
			//路径upload创建,不存在就创建，存在就不创建
			String filePath = "/bms/upload";
			File file = new File(filePath);
			 //判断文件夹是否存在,如果不存在则创建文件夹
			 if (!file.exists()) {
				 file.mkdirs();
				 /*if(file.mkdir()){ //创建文件夹，如果要创建文件： 
				   file.createNewFile();
			   	}*/
			 }
		} catch (Exception e) {
		}
		
		path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath(path);
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(some));
		File f=new File(path, someFileName);
		BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(f));
		IOUtils.copy(is, os);
		is.close();
		os.close();
	
		//excel读取文件
		if(ex!=null&&ex.equals("xls")){
			ExcelReadService.importExcel(path + "/" + someFileName);
		}else if(ex!=null&&ex.equals("xlsx")){
			ExcelReadxService.importExcel(path + "/" + someFileName);
		}
        //List<List<String>> allList=new ArrayList<List<String>>();
		int rowcount=0;
		if(ex!=null&&ex.equals("xls")){
			 rowcount=ExcelReadService.getRowIndex(0);
		}else if(ex!=null&&ex.equals("xlsx")){
			rowcount=ExcelReadxService.getRowIndex(0);
		}
		
		List listuser = new ArrayList();
		List<List> lists = new ArrayList();
    	  numb=rowcount;
    	  
    	  for (int i = 1; i <= rowcount; i++) {
    		  if(ex!=null&&ex.equals("xls")){
    			  listuser =  ExcelReadService.readRow(0, i);
    			}else if(ex!=null&&ex.equals("xlsx")){
    				listuser = ExcelReadxService.readRow(0, i);
    			}
    		  lists.add(listuser);
    		 
    		  Map<Integer,String> tempmap= this.isNumeric(listuser);
    		  Map<Integer,String> map= new HashMap<Integer, String>();
    		  boolean flag=false;
    		  for (int k=1;k<=tempmap.keySet().size();k++) {
//    			  System.out.println(d+"   value    "+map.get(d));
    			  map.put(k, tempmap.get(k));
    			  System.out.println(k+"  value   "+tempmap.get(k));
    		  }
    		  for (String str : map.values()) {
				if(str != null && !str.equals("")){
					flag=true;
				}
    		  }
    		  if(flag){
    			  
    			  if(Feedback.size()>20){
    				  
        			  ServletActionContext.getRequest().getSession().setAttribute("msg","友情提示：您的错误数超过20个，请仔细核对后再尝试。");
        			  break;
        		  }else{
        			  ServletActionContext.getRequest().getSession().setAttribute("msg","错误信息");
        		  }
    			  Feedback.put(i+1, map);
    		  }
    		  
    		  
    	  }
    	  
    	  if(Feedback.size()!=0){
    		  if(f.exists()){
    			  f.delete();
    		  }
    		  return "feedback";
    	  }else{
    		  for (int i = 1; i <= rowcount; i++) {
        		  
//        		  if(ex!=null&&ex.equals("xls")){
//        			  listuser =  ExcelReadService.readRow(0, i);
//        			}else if(ex!=null&&ex.equals("xlsx")){
//        				listuser = ExcelReadxService.readRow(0, i);
//        			}
        		  Plan p=new Plan();
//        		  Object result = null;
        		  SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
//        		  SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
        		  
//        		  DecimalFormat df = new DecimalFormat("0"); 
//        		  DecimalFormat df1 = new DecimalFormat("0.00000"); 
        		  
        		 
        		  for (int j = 0; j < lists.get(i-1).size(); j++) {
    	    			  if(lists.get(i-1).get(j)!=null){
    	    				  
    	    				  switch (j) {
    	    				  case 1: p.setReportComp(lists.get(i-1).get(j).toString());
			   				  		   p.setReportCompId(ucservice.findUseCompByName(lists.get(i-1).get(j).toString()).getId());
			   				  		   break;
    	    				  case 2: p.setPlanIssuedDate(sdf.parse(lists.get(i-1).get(j).toString()));break;
    	    				  case 3: p.setProjectName(lists.get(i-1).get(j).toString());break;
    	    				  case 4: p.setPlanContent(lists.get(i-1).get(j).toString());break;
    	    				  case 5: p.setPlanNum(lists.get(i-1).get(j).toString());break;
    	    				  case 6: p.setPlanRowNum(lists.get(i-1).get(j).toString());break;
    	    				  case 7: p.setArrivalDate(sdf.parse(lists.get(i-1).get(j).toString()));break;
    	    				  case 8: p.setDemandDept(lists.get(i-1).get(j).toString());break;
    	    				  case 9: p.setItemType(lists.get(i-1).get(j).toString());break;
    	    				  case 10: p.setAtype(lists.get(i-1).get(j).toString());break;
    	    				  case 11: p.setItemsNum(lists.get(i-1).get(j).toString());break;
    	    				  case 12: p.setItemsName(lists.get(i-1).get(j).toString());break;
    	    				  case 13: p.setModelType(lists.get(i-1).get(j).toString());break;
    	    				  case 14: p.setUnit(lists.get(i-1).get(j).toString());break;
    	    				  case 15: p.setQuantity(Double.parseDouble((String)lists.get(i-1).get(j)));break;
    	    				  case 16: p.setBudgetMoney(Double.parseDouble(lists.get(i-1).get(j).toString()));break;
    	    				  case 17: p.setXmperson(lists.get(i-1).get(j).toString());break;
    	    				  case 18: p.setContact(lists.get(i-1).get(j).toString());break;
    	    				  case 19: p.setRemark((lists.get(i-1).get(j).toString()));break;
    	    				  case 20: p.setGsperson(lists.get(i-1).get(j).toString());break;
    	    				  }
    	    			  }else{
    	    				  continue;
    	    			  }
        		    }
        		  p.setPlanStatus("01");
        		  p.setGroupStatus("01");
        		  pservice.addPlans(p);
        	  }
    		  if(f.exists()){
    			  f.delete();
    		  }
    	  }
		return "importfile_success";
	}
	/**
	 * 
	 * @param list
	 * @return 错误所在行号
	 */

	public  Map<Integer,String> isNumeric(List<String> list){ 
		Map<Integer,String> l=new HashMap<Integer, String>();
//		Pattern pattern = Pattern.compile("^\\d+$|\\d+\\.\\d+$"); 
		
		for(int i=1;i<list.size();i++){
			if(i!=1 & i!=3 & i!=4 & i!=5  & i!=6 & i!=8 & i!=9 & i!=10 & i!=11 & i!=12 & i!=13 & i!=14 & i!=17 & i!=18 & i!=19 & i!=20){
				if( i==2){
					if(list.get(i) != null ){
						if(!isDate(list.get(i))){
							l.put(i, "日期格式不正确");
						}else{
							l.put(i, "");
						}
					}
					continue;
				}else if( i==7){
					if(list.get(i) != null ){
						if(!isDate(list.get(i))){
							l.put(i, "日期格式不正确");
						}else{
							l.put(i, "");
						}
					}
					continue;
				}else{
					if(list.get(i)==null){
//						System.out.println("fffffff   "+i);
						l.put(i, "为空值");
						continue;
					}
					if(!isNumeric1(list.get(i))){
						l.put(i, "不是数字");
						continue;
					}
				}
				l.put(i, "");
			}else{
				if(i==1 || i==5 || i==6 || i==14 ){
					if(list.get(i)==null){
							l.put(i, "为空");
						continue;
					}
					Long count = null;
					if(i == 5){
						count = pservice.checkPlanNumAndPlanRowNum(list.get(5), list.get(6));
						System.out.println(list.get(5)+","+list.get(6));
					}
					if( count != null && count.intValue() > 0){
						l.put(i, "计划号与计划行号重复");
						continue;
					}
				}
				if(i==1){
					if(ucservice.findUseCompByName(list.get(i))==null){
						l.put(i, "没有匹配的计划提报单位");
					}else{
						l.put(i, "");
					}
					continue;
				}
				l.put(i, "");
				/*if(i==1 || i==12){
					if(!isDate(list.get(i))){
						l.put(i, "日期格式不正确");
					}else{
						l.put(i, "");
					}
					continue;
				}
				if(i==3){
						if(pservice.getPlanListByOldPlanNum(list.get(i-1).trim(), list.get(i).trim()).size() > 0){
							l.put(i, "购物车号和行号已存在");
						}else{
							l.put(i, "");
						}
						continue;
				}
				if(i==10){
					if(pservice.findOrgByNames(list.get(i))==null){
						l.put(i, "没有匹配的组织");
					}else{
						l.put(i, "");
					}
					continue;
				}
				
				if(i==11){
					if(pservice.findOrgByNames(list.get(i-1))!=null){
						Person p = pservice.findPersonByName(list.get(i),pservice.findOrgByNames(list.get(i-1)));
						if(p==null){
							l.put(i, "没有匹配的负责人");
						}else if(p.getUser()==null){
							l.put(i, "负责人没有分配帐号");
						}else{
							l.put(i, "");
						}
					}else{
						l.put(i, "无组织，无法判断负责人");
					}
					continue;
				}
				*/
			}
		}
		
		return l; 
	}

	public  Map<Integer,String> isNumericSeekSource(List<String> list,List<Plan> ps){ 
		Map<Integer,String> l=new HashMap<Integer, String>();
//		Pattern pattern = Pattern.compile("^\\d+$|\\d+\\.\\d+$"); 
		
		for(int i=0;i<list.size();i++){
//				if(i!=3 & i!=4 & i!=5 & i!=6 & i!=7 & i!=11 & i!=13 & i!=14 & i!=15){
				if(i==0){
//					if( i==1){
						Plan p = pservice.getPlanByPlanNumAndRowNum(list.get(i), list.get(i+1));
						if(p == null ){
							l.put(i, "无此计划");
						}else{
							ps.add(p);
							l.put(i, "");
						}
//					}
					continue;
				}else{
					if (i == 4 || i == 5 || i == 6) {
						if (list.get(i).equals("")) {
							// System.out.println("fffffff   "+i);
							l.put(i, "为空值");
							continue;
						}
						if (!isNumeric1(list.get(i))) {
							l.put(i, "不是数字");
							continue;
						}
					}
					if (i == 7) {
						if (!isDateSeekSource(list.get(i))) {
							l.put(i, "日期格式不正确");
						} else {
							l.put(i, "");
						}
					}
					continue;
				}
			}
		return l; 
	}
	public  boolean isNumeric1(String str){
		try {
			DecimalFormat df = new DecimalFormat("0"); 
			double strd=Double.parseDouble(str);
			Pattern pattern = Pattern.compile("^\\d+$|\\d+\\.\\d+$"); 
			if(pattern.matcher(df.format(strd).toString()).matches()){
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	public  boolean isDateSeekSource(String str){
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			System.out.println(str);
			sdf.setLenient(false);
			Date d = sdf.parse(str);
			//System.out.println(sdf1.format(d));
			return true;
//			DecimalFormat df = new DecimalFormat("0"); 
//			double strd=Double.parseDouble(str);
//			Pattern pattern = Pattern.compile("^\\d+$|\\d+\\.\\d+$"); 
//			if(df.format(strd).length()==8){
//				return true;
//			}
		} catch (Exception e) {
			System.out.println("import plans date format error");
			return false;
		}
		//return false;
	}
	public  boolean isDate(String str){
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//			System.out.println(str);
			sdf.setLenient(false);
			Date d = sdf.parse(str);
			//System.out.println(sdf1.format(d));
			return true;
//			DecimalFormat df = new DecimalFormat("0"); 
//			double strd=Double.parseDouble(str);
//			Pattern pattern = Pattern.compile("^\\d+$|\\d+\\.\\d+$"); 
//			if(df.format(strd).length()==8){
//				return true;
//			}
		} catch (Exception e) {
			System.out.println("import plans date format error");
			return false;
		}
		//return false;
	}
	
	public String uploadfile() throws Exception {

		return null;
	}
	
}
