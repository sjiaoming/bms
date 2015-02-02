package com.shtz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Check;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.Suppliers;
import com.shtz.model.User;
import com.shtz.service.CheckService;
import com.shtz.service.PersonService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.SuppliersService;
import com.shtz.util.PageModel;

/**
 * @author Brown.Yao/姚昌:
 * @version 创建时间：Apr 22, 2013 2:15:26 PM
 * 
 */

public class ProcurementCheckAction extends ActionSupport {

	private ProcurementCheckService pcService;
	private ProcurementCheck pc;
	private Integer myOffset;
	private SuppliersService suservice;
	private PersonService personService;
	private CheckService checkService;
	private String tags;
	private String method;
	private String pageType;

	private Map<Integer,String> supplierMp = new HashMap<Integer, String>();
//


	public Integer getMyOffset() {
		return myOffset;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public void setCheckService(CheckService checkService) {
		this.checkService = checkService;
	}

	public String getTags() {
		return tags;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}

	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}

	public ProcurementCheck getPc() {
		return pc;
	}

	public void setPc(ProcurementCheck pc) {
		this.pc = pc;
	}

	public Map<Integer, String> getSupplierMp() {
		return supplierMp;
	}

	public void setSupplierMp(Map<Integer, String> supplierMp) {
		this.supplierMp = supplierMp;
	}

	public void setMyOffset(Integer myOffset) {
		this.myOffset = myOffset;
	}
	@SuppressWarnings("unchecked")
	public String procurementCheckDSearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		/*
		 * if(method!=null && !"".equals(method) && method.equals("planAdd")){
		 * params.put("in_planStatus", "01"); }else{ params.put("in_planStatus",
		 * "01,02"); }
		 */

		if(!"0".equals(pc.getCheckStatus())){
//		params.put("in_checkStatus", "'01','02'");
			params.put("in_checkStatus","'"+pc.getCheckStatus()+"'");
		}
		else{//modify by brown 20131014
			params.put("bdy_p.checkStatus","'02'");
		}
		//包括 主管末审核，内控末审核，内控末通过审核 delete by brown 20130724
//		params.put("in_checkStatus", "'01','08','09'");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		if(pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		if(pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		params.put("pc.procurementName", pc.getProcurementName());
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		params.put("role", "d");
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			if(p.getSupplierIds() != null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				String idnames = sb.toString();
	//			System.out.println(idnames);
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
//		if (method != null && !"".equals(method) && method.equals("planAdd")) {
//			return "planAdd_success";
//		}
			return SUCCESS;
	}
	public String procurementCheckISearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();

		if(!"0".equals(pc.getCheckStatus())){
//		params.put("in_checkStatus", "'01','02'");
			params.put("in_checkStatus","'"+pc.getCheckStatus()+"'");
		}
		else{//modify by brown 20131014
			params.put("bdy_p.checkStatus","'09'");
		}
		//包括 内控末审核，经理末审核，经理末通过审核
//		params.put("in_checkStatus", "'08','03','04'");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		params.put("role", "i");
		if(pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		if(pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		params.put("pc.procurementName", pc.getProcurementName());
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
//			System.out.println(p.getSupplierIds()==null);
			if(p.getSupplierIds()!=null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				String idnames = sb.toString();
	//			System.out.println(idnames);
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
			return SUCCESS;
	}
	public String procurementCheckMSearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		if(!"0".equals(pc.getCheckStatus())){
//			params.put("in_checkStatus", "'01','02'");
				params.put("in_checkStatus","'"+pc.getCheckStatus()+"'");
		}
		else{//modify by brown 20131014
			params.put("bdy_p.checkStatus","'04'");
		}
		//包括 主管己审核,董事长末审核  董事长末通过
//		params.put("in_checkStatus", "'03','05','06'");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		params.put("role", "m");
		if(pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		if(pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		params.put("pc.procurementName", pc.getProcurementName());
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			if(p.getSupplierIds()!=null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				String idnames = sb.toString();
				System.out.println(idnames);
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
			return SUCCESS;
	}
	public String procurementCheckCSearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		//包括 董事长末审核
		params.put("in_checkStatus", "'05'");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		params.put("role", "c");
		if(pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		params.put("pc.procurementName", pc.getProcurementName());
		if(pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			if(p.getSupplierIds()!=null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				String idnames = sb.toString();
	//			System.out.println(idnames);
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	public String procurementCheckSSearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
//		System.out.println("pc.getCheckStatus() =====>  "+pc.getCheckStatus());
		//包括 经理末审核
		if(!"0".equals(pc.getCheckStatus())){
//		params.put("in_checkStatus", "'01','02'");
			params.put("in_checkStatus","'"+pc.getCheckStatus()+"'");
		}
		/*else{
			params.put("in_checkStatus", "'01','02','03','04','05','06','07','08','09'");
		}*/
//		System.out.println(pc.getCheckStatus());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		params.put("role", "s");
		if(pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		params.put("pc.procurementName", pc.getProcurementName());
		if(pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			if(p.getSupplierIds()!=null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				String idnames = sb.toString();
	//			System.out.println(idnames);
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
			return SUCCESS;
	}
	public String getProcurementCheck() throws Exception {
		pc = pcService.getProcurementCheck(Integer.parseInt(tags));
		List slist = suservice.getSupplierslist();
		if(pc.getSupplierIds() != null && !"".equals(pc.getSupplierIds())){
			String[] supplierIds = pc.getSupplierIds().split(",");
	//		StringBuffer sb = new StringBuffer();
			Map<Integer ,String> idAndName = new HashMap<Integer, String>();
			for(Iterator it = slist.iterator();it.hasNext();){
				Suppliers s = (Suppliers)it.next();
				idAndName.put(s.getId(), s.getName());
			}
			for(int i=0;i < supplierIds.length ; i++){
	//			sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				supplierMp.put(Integer.parseInt(supplierIds[i].trim()), idAndName.get(Integer.parseInt(supplierIds[i].trim())));
			}
		}
		List<Person> directors = personService.loadDirectorPersons();
		ServletActionContext.getRequest().setAttribute("dpersons", directors);
		System.out.println("pageType -------------  "+pageType);
//		pc.setSupplierNames(idnames.substring(0,idnames.length()-1));
        ServletActionContext.getRequest().setAttribute("pageType", pageType);  
		ServletActionContext.getRequest().setAttribute("suppliers", slist);
		return SUCCESS;
	}
	
	public String paAddPlan() throws Exception {
		pcService.addPlan(tags, pc.getId());
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		//添加后回传pageType
//		ServletActionContext.getRequest().setAttribute("pageType", "s_index");
		return SUCCESS;
	}
	public String initPlanAdd() throws Exception {
//		pc.set
		return SUCCESS;
	}
	public String doProcurementCheck() throws Exception {
//		String[] str = tags.split(",");
//		String ps = "04";
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		System.out.println(pc.getCheckStatus()+"   pc checkstatus and opinion    "+pc.getOpinion());
		//director在业务员页面保存     inner在主管页面保存   manager在内控页面保存  chairMan在经理页面保存 最后为董事长页面保存
		if("director".equals(pageType)){//不用保存check
			pcService.modifyProcurementCheckD(pc,tags);
		}else if("inner".equals(pageType)){
			pcService.modifyProcurementCheckI(pc,tags);
			List<ProcurementCheck> ls = pcService.getPCs(tags);
			saveCheck(ls,personId);
		}else if("manager".equals(pageType)){
			pcService.modifyProcurementCheckM(pc,tags);
			List<ProcurementCheck> ls = pcService.getPCs(tags);
			saveCheck(ls,personId);
		}else if("charMan".equals(pageType)){
			pcService.modifyProcurementCheckC(pc,tags);
			List<ProcurementCheck> ls = pcService.getPCs(tags);
			saveCheck(ls,personId);
		}else{
			pcService.modifyProcurementCheck(pc,tags);
			List<ProcurementCheck> ls = pcService.getPCs(tags);
			saveCheck(ls,personId);
		}

		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";

	}
	private void saveCheck(List<ProcurementCheck> ls,int personId){
		Person pe = personService.findPerson(personId);
		Date d = new Date();
		List<Check> cs = new ArrayList<Check>();
		for(ProcurementCheck p : ls){
			Check check = new Check();
			check.setPc(p);
			check.setPerson(pe);
			check.setCheckDate(d);
			check.setOpinion(pc.getOpinion());
			check.setCheckStatus(pc.getCheckStatus());
			check.setPlan(null);
			//02:已采购方案
			check.setCheckType("02");
			cs.add(check);
		}
		checkService.addChecks(cs);
	}
	
	public String modifyProcurementsBackByIds()  {

		pcService.modifyPlansByIdsBack(tags);
//		String from = ServletActionContext.getRequest().getParameter("from");
//		if (from != null && !"".equals(from) && from.equals("seek"))
//			return "seek_success";
		return SUCCESS;
	}
	public String modifyPc() throws Exception {
		
//		System.out.println(pc.getSupplierIds());
		if(pc.getSupplierIds()==null){
			pc.setSupplierIds("");
		}
		pcService.modifyProcurementCheck(pc);
		
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";

	}
	public String seekSourceSearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		//包括 经理末审核
		params.put("in_checkStatus", "'07'");
		params.put("in_groupStatus", "'04'");
		params.put("null_finishSeekSource", "null");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc != null && pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc != null && pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		// delete by brown 2014/7/24
//		params.put("obj_user", (User) ServletActionContext.getRequest()
//				.getSession().getAttribute("login"));
		//add by brown 2014/7/24
		int personId = 0;
		if(ServletActionContext.getRequest().getSession().getAttribute("pid") != null){
			personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		}
		params.put("id_seekSourceOperator.id",personId);
		if(pc != null && pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
//		params.put("pc.procurementName", pc.getProcurementName());
		if(pc != null && pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc != null && pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		if(pc != null && pc.getCheckNum() != 0){
			params.put("mh_checkNum", pc.getCheckNum());
		}
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			String[] supplierIds = p.getSupplierIds().split(",");
			StringBuffer sb = new StringBuffer();
			for(int i=0;i < supplierIds.length ; i++){
				sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
			}
			String idnames = sb.toString();
			System.out.println(idnames);
			p.setSupplierNames(idnames.substring(0,idnames.length()-1));
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
			return SUCCESS;
	}
	public String initSeekSource() throws Exception {
		pc = pcService.getProcurementCheck(pc.getId());
		List<Suppliers> slist = suservice.findSuppliersByIds(pc.getSupplierIds());
		List<String> materialNames = new ArrayList<String>();
		String[] supplierIds = pc.getSupplierIds().split(",");
//		StringBuffer sb = new StringBuffer();
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(int i=0;i < supplierIds.length ; i++){
//			sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
			supplierMp.put(Integer.parseInt(supplierIds[i].trim()), idAndName.get(Integer.parseInt(supplierIds[i].trim())));
		}
		List<Person> ps = personService.loadDirectorPersons();
		
		for(Plan p : pc.getPlans()){
			String idName = p.getId()+"_"+p.getItemsName();
			materialNames.add(idName);
		}
		ServletActionContext.getRequest().setAttribute("materialNames", materialNames);
		
		ServletActionContext.getRequest().setAttribute("dpersons", ps);

		ServletActionContext.getRequest().setAttribute("suppliers", slist);
		
		return SUCCESS;
	}
}
