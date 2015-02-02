package com.shtz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Organization;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementContract;
import com.shtz.model.Suppliers;
import com.shtz.model.User;
import com.shtz.service.OrganizationService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementContractService;
import com.shtz.service.SuppliersService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class ProcurementContractAction extends ActionSupport {
	
	private ProcurementContractService service;
	
	private PlanService pservice;
	
	private SuppliersService suservice;
	
	private OrganizationService oservice;
	
	private int id;
	
	private Integer[] pid;
	
	private String itemsName;
	private String model;
	private String oldPlanNum;
	private String[] reportComp;
	private String planNum;
	private String procurementName;
	private Date planReceiptDate;
	private String caseNum;
	private String packageNum;
	private String company;
	private int num;
	private Date arrivalDate;
	private String arrivalSite;
	private Double budget;
	private String procurementFlag;
	private String remark;
	private Double ContractMoney;
	private Double[] procurementPrice;
	private Double[] procurementMoney;
	
	private Double[] planContractNum;
	
	
	private String tags;
	
	private Map form;
	
	private Map map;
	
	private String flag;
	
	private String all_selected;
	
	private String now_selected;
	
	private String no_selected;
	private String contractNum;
	private int suppliersId;
	private String suppliersName;
	private Double advance;
	private int orgId;
	private String orgName;
	private Date signingDate;
	private String procurementWay;
	private Double noPayment;
	private Double shouldPayment;
	private Double[] payed;
	private Double[] contractCreditMoney;
	private Date[] contractCreditDate;
	private String contractExecutionWay;
	private Double totalMoney;
	private Double qualityMoney;
	private Date qualityDate;
	
	private String contractName;
	
	private String contractMsg;
	
	private String gn;
	
	private Integer[] reportCompId;
	
	private String spid;
	 private String  sopn;
	 
 private Integer myOffset; 
	 
	 private String resetflag;
	 
	 private String personName;
		
		public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	public String getPackageNum() {
		return packageNum;
	}
	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}
		public String getPersonName() {
			return personName;
		}
		public void setPersonName(String personName) {
			this.personName = personName;
		}
	public Integer getMyOffset() {
		return myOffset;
	}
	public String getProcurementName() {
		return procurementName;
	}
	public void setProcurementName(String procurementName) {
		this.procurementName = procurementName;
	}
	public void setMyOffset(Integer myOffset) {
		this.myOffset = myOffset;
	}
	public String getResetflag() {
		return resetflag;
	}
	public void setResetflag(String resetflag) {
		this.resetflag = resetflag;
	}
	public String getSopn() {
		return sopn;
	}
	public void setSopn(String sopn) {
		this.sopn = sopn;
	}
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String[] getReportComp() {
		return reportComp;
	}
	public void setReportComp(String[] reportComp) {
		this.reportComp = reportComp;
	}
	public Integer[] getReportCompId() {
		return reportCompId;
	}
	public void setReportCompId(Integer[] reportCompId) {
		this.reportCompId = reportCompId;
	}
	public String getGn() {
		return gn;
	}
	public void setGn(String gn) {
		this.gn = gn;
	}
	public String getContractMsg() {
		return contractMsg;
	}
	public void setContractMsg(String contractMsg) {
		this.contractMsg = contractMsg;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Double[] getPlanContractNum() {
		return planContractNum;
	}
	public void setPlanContractNum(Double[] planContractNum) {
		this.planContractNum = planContractNum;
	}
	public Double getQualityMoney() {
		return qualityMoney;
	}
	public void setQualityMoney(Double qualityMoney) {
		this.qualityMoney = qualityMoney;
	}
	public Date getQualityDate() {
		return qualityDate;
	}
	public void setQualityDate(Date qualityDate) {
		this.qualityDate = qualityDate;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer[] getPid() {
		return pid;
	}
	public void setPid(Integer[] pid) {
		this.pid = pid;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public int getSuppliersId() {
		return suppliersId;
	}
	public void setSuppliersId(int suppliersId) {
		this.suppliersId = suppliersId;
	}
	public String getSuppliersName() {
		return suppliersName;
	}
	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public Double getAdvance() {
		return advance;
	}
	public void setAdvance(Double advance) {
		this.advance = advance;
	}

	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getSigningDate() {
		return signingDate;
	}
	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}


	public String getProcurementWay() {
		return procurementWay;
	}
	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}

	public Double getNoPayment() {
		return noPayment;
	}
	public void setNoPayment(Double noPayment) {
		this.noPayment = noPayment;
	}
	public Double getShouldPayment() {
		return shouldPayment;
	}
	public void setShouldPayment(Double shouldPayment) {
		this.shouldPayment = shouldPayment;
	}

	
	
	public String getContractExecutionWay() {
		return contractExecutionWay;
	}
	public void setContractExecutionWay(String contractExecutionWay) {
		this.contractExecutionWay = contractExecutionWay;
	}
	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}
	public String getAll_selected() {
		return all_selected;
	}
	public void setAll_selected(String all_selected) {
		this.all_selected = all_selected;
	}
	public String getNow_selected() {
		return now_selected;
	}
	public void setNow_selected(String now_selected) {
		this.now_selected = now_selected;
	}
	public String getNo_selected() {
		return no_selected;
	}
	public void setNo_selected(String no_selected) {
		this.no_selected = no_selected;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Map getForm() {
		return form;
	}
	public void setForm(Map form) {
		this.form = form;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public void setService(ProcurementContractService service) {
		this.service = service;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOldPlanNum() {
		return oldPlanNum;
	}
	public void setOldPlanNum(String oldPlanNum) {
		this.oldPlanNum = oldPlanNum;
	}

	public String getPlanNum() {
		return planNum;
	}
	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}
	public Date getPlanReceiptDate() {
		return planReceiptDate;
	}
	public void setPlanReceiptDate(Date planReceiptDate) {
		this.planReceiptDate = planReceiptDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getArrivalSite() {
		return arrivalSite;
	}
	public void setArrivalSite(String arrivalSite) {
		this.arrivalSite = arrivalSite;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public String getProcurementFlag() {
		return procurementFlag;
	}
	public void setProcurementFlag(String procurementFlag) {
		this.procurementFlag = procurementFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Double getContractMoney() {
		return ContractMoney;
	}
	public void setContractMoney(Double contractMoney) {
		ContractMoney = contractMoney;
	}
	
	public Double[] getProcurementPrice() {
		return procurementPrice;
	}
	public void setProcurementPrice(Double[] procurementPrice) {
		this.procurementPrice = procurementPrice;
	}
	public Double[] getProcurementMoney() {
		return procurementMoney;
	}
	public void setProcurementMoney(Double[] procurementMoney) {
		this.procurementMoney = procurementMoney;
	}

	public void setOservice(OrganizationService oservice) {
		this.oservice = oservice;
	}
	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}
	public Double[] getPayed() {
		return payed;
	}
	public void setPayed(Double[] payed) {
		this.payed = payed;
	}
	public Double[] getContractCreditMoney() {
		return contractCreditMoney;
	}
	public void setContractCreditMoney(Double[] contractCreditMoney) {
		this.contractCreditMoney = contractCreditMoney;
	}
	public Date[] getContractCreditDate() {
		return contractCreditDate;
	}
	public void setContractCreditDate(Date[] contractCreditDate) {
		this.contractCreditDate = contractCreditDate;
	}
	@Override
	public String execute() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			System.out.println("fenye error");
		}
		int pageSize = 20;
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_org", orgId);
		if(planNum!=null)
			params.put("planNum", planNum);
		PageModel pm = service.serchsearchPlansByParams(params, offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
				
				
		return SUCCESS;
	}

	public String procurementContractSearch() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
//		params.put("in_planStatus", "03");
		//03 己寻源 07 董事长己审核 20140708   先生成采购合同，后生成销售合同
		params.put("in_planStatus", "'03'");
		params.put("in_checkStatus", "'07'");
		params.put("in_seekSourceStatus", "'03'");
		//------------ delete 20140708
		//03 己寻源，04生成销售合同
		//params.put("in_planStatus", "'04'");
		//params.put("diy_salesContract.id", "is not null");
		//------------
		//params.put("in_contractExecutionWay", "1,2");
		//params.put("diy_searchDate", "is not null");
		//params.put("diy_procurementPrice", " >0 ");
//		params.put("diy_model", "<>'02'");
		if(orgId!=0){
			params.put("id_org", orgId);
		}
		if(planNum!=null && !planNum.equals("")){
			params.put("planNum", planNum);
		}
		if(remark!=null && !"".equals(remark.trim()))
			params.put("remark", remark.trim());
		if(procurementName!=null && !"".equals(procurementName.trim()))
			params.put("mh_pc.procurementName", procurementName.trim());
		if(caseNum!=null && !"".equals(caseNum.trim())){
			params.put("pc.caseNum", caseNum.trim());
		}
		// for page display
		if(packageNum!=null && !"".equals(packageNum.trim())){
			params.put("pc.packageNum", packageNum.trim());
		}
		if(personName!=null && !personName.equals(""))
			params.put("person.name", personName.trim());
		params.put("obj_user", (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params,offset, pageSize);
		params.put("personName", personName.trim());
		params.put("procurementName", procurementName.trim());
		params.put("packageNum", packageNum);
		params.put("caseNum", caseNum);
		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);
		
		try {
			this.operationCheckInfo(map, form, ServletActionContext.getRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	
	private void operationCheckInfo(Map map, Map form, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (request.getParameter("flag") != null && request.getParameter("flag").equals("CHECKED")) {
		String all_select = (String) form.get("all_selected");
		if (all_select == null || all_select.equals("")) {
		all_select = "";
		}
		String now_select = (String) form.get("now_selected");
		String[] all_now_select = now_select.split(",");

		String no_select = (String)form.get("no_selected");
		String[] all_now_no_select = no_select.split(",");
		
		System.out.println("=====now_select=====:"+now_select+"==no_select=="+no_select);
		for (int i = 1; i < all_now_select.length; i++) {
		String temp_all_select = ","+all_select;
		String strBoxSelected = all_now_select[i];
		String strSearchWith = ","+strBoxSelected + ",";
		if (temp_all_select.indexOf(strSearchWith) == -1) {
		all_select = all_select + strSearchWith;
		} 
		}
		for (int i = 1; i < all_now_no_select.length; i++) {
			String temp_all_select = ","+all_select;
			String strBoxNoselected = all_now_no_select[i];
			String strSearchWith = ","+strBoxNoselected + ",";
			int iSearchIndex = temp_all_select.indexOf(strSearchWith);
			if (iSearchIndex != -1) {
			all_select = all_select.replaceAll(strSearchWith, "");
			} 
		}
		
		request.setAttribute("all", all_select);
		System.out.println("=====all_select=====:"+all_select);
		}

		}
	
	public String toAddContract() throws Exception {
		
		String[] str = tags.split(",");
		Integer[] ia=new Integer[str.length];
		for(int i=0;i<str.length;i++){
		   ia[i]=Integer.parseInt(str[i]);
		}
		
		List<Plan> plist = pservice.getPlans(ia);
		ServletActionContext.getRequest().setAttribute("reportCompId", plist.get(0).getReportCompId());
		ServletActionContext.getRequest().setAttribute("reportCompName", plist.get(0).getReportComp());
		ServletActionContext.getRequest().setAttribute("supplierName", plist.get(0).getSupplier().getName());
		ServletActionContext.getRequest().setAttribute("supplierId", plist.get(0).getSupplier().getId());
		ServletActionContext.getRequest().setAttribute("procurementName", plist.get(0).getPc().getProcurementName());
		ServletActionContext.getRequest().setAttribute("plist", plist);
		ServletActionContext.getRequest().setAttribute("procurementWay", plist.get(0).getProcurementWay());
		ServletActionContext.getRequest().setAttribute("contractExecutionWay", plist.get(0).getContractExecutionWay());
		return "toAddSuccess";
	}
	public String addContract()	throws Exception{
		ProcurementContract p;
		if(gn!=null && !"".equals(gn) && gn.equals("modify")){
			p = service.findProcurementContractById(id);
		}else{
			p = new ProcurementContract();
//			p.setContractExecutionWay(contractExecutionWay);
			p.setProcurementWay(procurementWay);
		}
		//List l = service.findProcurementContractByContractNum(contractNum);
		p.setContractName(contractName);
		p.setContractNum(contractNum);
		Suppliers s = suservice.findSuppliers(suppliersId);
		p.setSuppliersId(s.getId());
		p.setSuppliersName(s.getName());
		p.setAdvance(advance);
		Organization o = oservice.findOrganization(orgId);
		p.setOrgId(o.getId());
		p.setOrgName(o.getName());
		p.setSigningDate(signingDate);
		p.setArrivalDate(arrivalDate);
		p.setTotalMoney(totalMoney);
		
		p.setQualityDate(qualityDate);
		p.setQualityMoney(qualityMoney);
		p.setNoPayment(noPayment);
		p.setShouldPayment(shouldPayment);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		if(reportCompId!=null && reportCompId.length > 0 && reportCompId.length == reportComp.length){
			 List<Integer> list = new ArrayList<Integer>();
			    for (int i=0; i<reportCompId.length; i++) {
				if(!list.contains(reportCompId[i])) {//如果数组 list 不包含当前项，则增加该项到数组中
					list.add(reportCompId[i]);
				}
			    }
			List<String> list1 = new ArrayList<String>();
			    for (int i=0; i<reportComp.length; i++) {
				if(!list1.contains(reportComp[i])) {//如果数组 list 不包含当前项，则增加该项到数组中
					list1.add(reportComp[i]);
				}
			    }
			if(list.size() == list1.size()){
				for(int i=0;i<list.size();i++){
					sb.append(",");
					sb.append(list.get(i));
					if(i!=0)
						sb1.append(",");
					sb1.append(list1.get(i));
				}
				sb.append(",");
			}
//			for(int i=0;i<reportCompId.length;i++){
//				sb.append(",");
//				sb.append(reportCompId[i]);
//				if(i!=0)
//					sb1.append(",");
//				sb1.append(reportComp[i]);
//			}
			//sb.append(",");
			//sb1.append(",");
		}
		p.setReportCompId_pc(sb.toString());
		p.setReportCompName_pc(sb1.toString());
		p.setRemark(remark);
		p.setUser((User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		p.setPerson((Person)ServletActionContext.getRequest().getSession().getAttribute("person"));
		
				if(procurementPrice.length>0){
					for(int i=0;i<procurementPrice.length;i++){
						if(procurementPrice[i]!=null&&procurementMoney!=null){
							Plan pn = new Plan();
							pn.setId(pid[i]);
//							pn.setProcurementPrice(procurementPrice[i]);
//							pn.setProcurementMoney(procurementMoney[i]);
//							pn.setContractNum(planContractNum[i]);
							pn.setProcurementContract(p);
//							pn.setPlanStatus("04");
							pn.setPlanStatus("05");
							pservice.modifyPlanPM(pn);
						}
					}
				}
		
//		this.procurementContractSearch();
		ServletActionContext.getRequest().setAttribute("ccflag", "success");
//		if(gn!=null && !"".equals(gn) && gn.equals("modify"))
//			return "modify_success";
		return SUCCESS;
	}
	public String planAdd() throws Exception {
		ProcurementContract pc = service.findProcurementContractById(id);
		String[] str = tags.split(",");
		Integer[] ia=new Integer[str.length];
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		for(int i=0;i<str.length;i++){
		   ia[i]=Integer.parseInt(str[i]);
		   Plan p = pservice.findPlan(ia[i]);
		   p.setProcurementWay("6");
		   p.setPlanStatus("04");
		   p.setProcurementContract(pc);
		   p.setProcurementDate(d);
		   p.setProcurementSigningleDate(d);
		   pservice.modifyPlanByObj(p);
		}
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		
		return SUCCESS;
	}
}
