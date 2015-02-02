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
import com.shtz.model.ArrivalItems;
import com.shtz.model.Billing;
import com.shtz.model.BillingDetail;
import com.shtz.model.Organization;
import com.shtz.model.Payed;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.SalesContract;
import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.service.ArrivalItemsService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementContractService;
import com.shtz.service.SalesContractService;
import com.shtz.util.Arith;
import com.shtz.util.PageModel;

/**
 * @author sjm
 * 
 */
public class SalesContractAction extends ActionSupport {

	private SalesContractService service;

	private PlanService pservice;

	private ProcurementContractService pcservice;
	
	private ArrivalItemsService  aservice;
	
	private Arith arithService;
	
	private int id;

	private String itemsName;

	private Integer[] pid;

	private Double[] salesMoney;

	private Double[] salesRatio;
	private Double salesRatioo;
	private String model;
	private String oldPlanNum;
	private String reportComp;
	private String planNum;
	private Date planReceiptDate;
	private String company;
	private int num;
	private Date arrivalDate;
	private String arrivalSite;
	private Double budget;
	private String procurementFlag;
	private String remark;
	private String contractExecutionWay;
	private Double procurementPrice;
	private Double procurementMoney;
	private String tags;
	private Map form;
	private Map map;
	private String flag;
	private String all_selected;
	private String now_selected;
	private String no_selected;
	private Double contractMoney;
	private SalesContract salesContract;

	private int addmoney;

	private int addmoney1;

	private int planid;

	private int salesContractId;

	private String contractNum;

	private String contractMsg;

	private String b;

	private String[] payedStyle;

	private String gn;
	private Integer useCompId;

	private String sDate;

	private String eDate;

	private Double am;

	private String spid;
	private String sopn;

	private Double billingMoney;
	private Date billingDate;
	
	private String baid;
	
	private int aid;
	
	private String sbillingDate;
	
	private Integer myOffset; 
	 
	 private String resetflag;
	 
	 private String personName;

		private String procurementName;
		private String caseNum;
		private String packageNum;
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
	public void setMyOffset(Integer myOffset) {
		this.myOffset = myOffset;
	}
	public String getResetflag() {
		return resetflag;
	}
	public void setResetflag(String resetflag) {
		this.resetflag = resetflag;
	}
	
	public String getSbillingDate() {
		return sbillingDate;
	}

	public void setSbillingDate(String sbillingDate) {
		this.sbillingDate = sbillingDate;
	}

	public void setArithService(Arith arithService) {
		this.arithService = arithService;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setAservice(ArrivalItemsService aservice) {
		this.aservice = aservice;
	}

	public String getBaid() {
		return baid;
	}

	public void setBaid(String baid) {
		this.baid = baid;
	}

	public Double getBillingMoney() {
		return billingMoney;
	}

	public void setBillingMoney(Double billingMoney) {
		this.billingMoney = billingMoney;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public String getProcurementName() {
		return procurementName;
	}
	public void setProcurementName(String procurementName) {
		this.procurementName = procurementName;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
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

	public Double getSalesRatioo() {
		return salesRatioo;
	}

	public void setSalesRatioo(Double salesRatioo) {
		this.salesRatioo = salesRatioo;
	}

	public void setPcservice(ProcurementContractService pcservice) {
		this.pcservice = pcservice;
	}

	public Double getAm() {
		return am;
	}

	public void setAm(Double am) {
		this.am = am;
	}

	public Integer getUseCompId() {
		return useCompId;
	}

	public void setUseCompId(Integer useCompId) {
		this.useCompId = useCompId;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String getGn() {
		return gn;
	}

	public void setGn(String gn) {
		this.gn = gn;
	}

	public String[] getPayedStyle() {
		return payedStyle;
	}

	public void setPayedStyle(String[] payedStyle) {
		this.payedStyle = payedStyle;
	}

	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}

	public Integer[] getPid() {
		return pid;
	}

	public void setPid(Integer[] pid) {
		this.pid = pid;
	}

	public Double[] getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Double[] salesMoney) {
		this.salesMoney = salesMoney;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getContractMsg() {
		return contractMsg;
	}

	public void setContractMsg(String contractMsg) {
		this.contractMsg = contractMsg;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public int getSalesContractId() {
		return salesContractId;
	}

	public void setSalesContractId(int salesContractId) {
		this.salesContractId = salesContractId;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getContractExecutionWay() {
		return contractExecutionWay;
	}

	public void setContractExecutionWay(String contractExecutionWay) {
		this.contractExecutionWay = contractExecutionWay;
	}

	public Double getContractMoney() {
		return contractMoney;
	}

	public void setContractMoney(Double contractMoney) {
		this.contractMoney = contractMoney;
	}

	public int getAddmoney() {
		return addmoney;
	}

	public void setAddmoney(int addmoney) {
		this.addmoney = addmoney;
	}

	public int getAddmoney1() {
		return addmoney1;
	}

	public void setAddmoney1(int addmoney1) {
		this.addmoney1 = addmoney1;
	}

	public SalesContract getSalesContract() {
		return salesContract;
	}

	public void setSalesContract(SalesContract salesContract) {
		this.salesContract = salesContract;
	}

	public SalesContractService getService() {
		return service;
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

	private int orgId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public void setService(SalesContractService service) {
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

	public String getReportComp() {
		return reportComp;
	}

	public void setReportComp(String reportComp) {
		this.reportComp = reportComp;
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

	public Double getProcurementPrice() {
		return procurementPrice;
	}

	public void setProcurementPrice(Double procurementPrice) {
		this.procurementPrice = procurementPrice;
	}

	public Double getProcurementMoney() {
		return procurementMoney;
	}

	public void setProcurementMoney(Double procurementMoney) {
		this.procurementMoney = procurementMoney;
	}

	public Double[] getSalesRatio() {
		return salesRatio;
	}

	public void setSalesRatio(Double[] salesRatio) {
		this.salesRatio = salesRatio;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		int pageSize = 20;
		PageModel pm = service.searchPlans(offset, pageSize);

		ServletActionContext.getRequest().setAttribute("pm", pm);
		return SUCCESS;
	}

	public String salesContracts() throws Exception {
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
		// params.put("in_planStatus", "04");
		if (orgId != 0) {
			params.put("id_orgId", orgId);
		}
		if (contractNum != null && !contractNum.equals("")) {
			params.put("contractNum", contractNum);
		}
		if (useCompId != null)
			params.put("id_reportCompId_sc", useCompId);
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.signingDate", " between '" + sDate + "' and '"
					+ eDate + "'");
		}
		if(personName!=null && !personName.equals("")){
			params.put("dy_ps.name", " like '%"+personName.trim()+"%'");
			params.put("personName", personName.trim());
		}
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		PageModel pm = service.getSalesContractList(offset, pageSize, params,
				true);

		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		if (b != null && !"".equals(b))
			ServletActionContext.getRequest().setAttribute("b", b);
		if (contractMsg != null && !"".equals(contractMsg))
			ServletActionContext.getRequest().setAttribute("contractMsg",contractMsg);
		// PageModel pm = service.searchsalesContracts(offset, pageSize);
		// ServletActionContext.getRequest().setAttribute("pm", pm);
		return SUCCESS;
	}

	public String salesContractSearch() throws Exception {
		// TODO Auto-generated method stub
		String add = ServletActionContext.getRequest().getParameter("add");
		ServletActionContext.getRequest().setAttribute("add", add);
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
		// PageModel pm = service.searchPlans(offset, pageSize);

		Map params = new HashMap();
		//05:已采购 07 董事长己审核 20140708   先生成采购合同，后生成销售合同
		params.put("in_planStatus", "'05'");
		params.put("diy_procurementContract.id", "is not null");
		//------------ delete 20140708
		// planstatus 己寻源    checkstatus 经理己审核
		//params.put("in_planStatus", "'03'");
		//params.put("in_checkStatus", "'07'");
		//------------
		if (orgId != 0) {
			params.put("id_org", orgId);
		}
		if (planNum != null && !planNum.equals("")) {
			params.put("planNum", planNum);
		}
		if(procurementName!=null && !"".equals(procurementName.trim()))
			params.put("pc.procurementName", procurementName.trim());
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		if(remark!=null && !"".equals(remark.trim()))
			params.put("remark", remark.trim());
		if(personName!=null && !personName.equals(""))
			params.put("person.name", personName.trim());
		if(caseNum!=null && !"".equals(caseNum.trim())){
			params.put("pc.caseNum", caseNum.trim());
		}
		if(packageNum!=null && !"".equals(packageNum.trim())){
			params.put("pc.packageNum", packageNum.trim());
		}
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		params.put("personName", personName.trim());
		params.put("procurementName", procurementName.trim());
		// for page display
		params.put("caseNum", caseNum);
		params.put("packageNum", packageNum);
		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		
		ServletActionContext.getRequest().setAttribute("params", params);
		if (contractMsg != null && !"".equals(contractMsg)
				&& contractMsg.equals(SUCCESS))
			ServletActionContext.getRequest().setAttribute("contractMsg",
					SUCCESS);
		return SUCCESS;
	}

	private void operationCheckInfo(Map map, Map form,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (request.getParameter("flag") != null
				&& request.getParameter("flag").equals("CHECKED")) {
			String all_select = (String) form.get("all_selected");
			if (all_select == null || all_select.equals("")) {
				all_select = "";
			}
			String now_select = (String) form.get("now_selected");
			String[] all_now_select = now_select.split(",");
			String no_select = (String) form.get("no_selected");
			String[] all_now_no_select = no_select.split(",");

			System.out.println("=====now_select=====:" + now_select
					+ "==no_select==" + no_select);
			for (int i = 1; i < all_now_select.length; i++) {
				String temp_all_select = "," + all_select;
				String strBoxSelected = all_now_select[i];
				String strSearchWith = "," + strBoxSelected + ",";
				if (temp_all_select.indexOf(strSearchWith) == -1) {
					all_select = all_select + strSearchWith;
				}
			}
			for (int i = 1; i < all_now_no_select.length; i++) {
				String temp_all_select = "," + all_select;
				String strBoxNoselected = all_now_no_select[i];
				String strSearchWith = "," + strBoxNoselected + ",";
				int iSearchIndex = temp_all_select.indexOf(strSearchWith);
				if (iSearchIndex != -1) {
					all_select = all_select.replaceAll(strSearchWith, "");
				}
			}
			request.setAttribute("all", all_select);
			System.out.println("=====all_select=====:" + all_select);
		}
	}

	public String addSalesContract_input() throws Exception {
		System.out.println(tags);
		String[] str = tags.split(",");
		List<Plan> planList = service.findCheckedPlan(str);
		List<UseComp> useCompList = service.findAllUseComp();
		List<Organization> orgList = service.findAllOrganization();
		Map<String ,String> mp = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		for(Plan p : planList){
			if(p.getSupplier() != null){	
				mp.put(p.getSupplier().getName(), p.getSupplier().getName());
			}
		}
		for(String sname : mp.keySet()){
			sb.append(sname+",");
		}
		String supplierNames = sb.toString();
		if(supplierNames.length() > 0)
			supplierNames = supplierNames.substring(0,supplierNames.length()-1);
		System.out.println("addSalesContract_input      supplierNames     "+supplierNames);
		ServletActionContext.getRequest().setAttribute("reportCompId",
				planList.get(0).getReportCompId());
		ServletActionContext.getRequest().setAttribute("reportCompName",
				planList.get(0).getReportComp());
		ServletActionContext.getRequest().setAttribute("supplierNames",supplierNames);
		ServletActionContext.getRequest().setAttribute("orgList", orgList);
		ServletActionContext.getRequest().setAttribute("planList", planList);
		ServletActionContext.getRequest().setAttribute("useCompList",
				useCompList);
		return "addSalesContract_input_success";
	}

	public String doModifyPlansales() throws Exception {
		List<Plan> planList = pservice.getPlans(pid);
		SalesContract sct = new SalesContract();
		sct.setUser((User) ServletActionContext.getRequest().getSession()
				.getAttribute("login"));
		sct.setPerson((Person) ServletActionContext.getRequest().getSession()
				.getAttribute("person"));
		if (gn != null && !"".equals(gn) && gn.equals("modify")) {
			sct = service.findSalesContractById(salesContract.getId());
		} else {

			sct.setReportCompId_sc(salesContract.getReportCompId_sc());
			sct.setReportCompName_sc(salesContract.getReportCompName_sc());
			sct.setOrgId(salesContract.getOrgId());
			sct.setSignFlag(salesContract.getSignFlag());
		}
		sct.setContractMoney(contractMoney);
		sct.setSalesContractName(salesContract.getSalesContractName());
		sct.setContractNum(salesContract.getContractNum());
		sct.setSigningDate(salesContract.getSigningDate());
		sct.setSignComp(salesContract.getSignComp());
		sct.setQualityDate(salesContract.getQualityDate());
		sct.setQualityMoney(salesContract.getQualityMoney());
		sct.setArrivalAddress(salesContract.getArrivalAddress());
		SalesContract sc = service.addSalesContract(sct);

		if (pid.length > 0) {
			for (int i = 0; i < pid.length; i++) {
				if (pid[i] != null && pid[i] != 0) {
					Plan pn = new Plan();
					pn.setId(pid[i]);
					pn.setSalesRatio(salesRatioo);
					pn.setSalesMoney(salesMoney[i]);
					pn.setSalesContract(sc);
					service.modifyPlanSales(pn);
				}
			}
		}
		 ServletActionContext.getRequest().setAttribute("ccflag", "success");
		if (gn != null && !"".equals(gn) && gn.equals("modify")) {
			// ServletActionContext.getRequest().setAttribute("contractMsg",
			// "合同修改成功");
			this.setContractMsg("合同修改成功");
			return "modify_success";
		}
		return SUCCESS;
	}

	public String doModifysales() throws Exception {
		salesContract = service.findSalesContractById(salesContractId);
		if(!salesContract.getBilling().isEmpty()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for(Billing b : salesContract.getBilling()){
				String _billingMoney = ServletActionContext.getRequest().getParameter(b.getId()+"_billingMoney");
				String _billingDate = ServletActionContext.getRequest().getParameter(b.getId()+"_billingDate");
				b.setBillingMoney(Double.parseDouble(_billingMoney));
				b.setBillingDate(sdf.parse(_billingDate));
				service.modifyBilling(b);
			}
		}
//		String[] addmoney = ServletActionContext.getRequest()
//				.getParameterValues("billingMoney");
//		String[] addmoneyDate = ServletActionContext.getRequest()
//				.getParameterValues("billingDate");
		String[] addmoney1 = ServletActionContext.getRequest()
				.getParameterValues("contractReceivedMoney");
		String contractReceivableMoney = (String) ServletActionContext
				.getRequest().getParameter("contractReceivableMoney");
		String totalContractInvoiceMoney = (String) ServletActionContext
				.getRequest().getParameter("totalContractInvoiceMoney");
		if (contractReceivableMoney != null
				&& !"".equals(contractReceivableMoney))
			salesContract.setContractReceivableMoney(Double
					.parseDouble(contractReceivableMoney));
		else
			salesContract.setContractReceivableMoney(0.0);

		if (totalContractInvoiceMoney != null
				&& !"".equals(totalContractInvoiceMoney))
			salesContract.setTotalContractInvoiceMoney(Double
					.parseDouble(totalContractInvoiceMoney));
		else
			salesContract.setTotalContractInvoiceMoney(0.0);
//		service.addOrModifySalesContract(salesContract, addmoney, addmoneyDate,
//				addmoney1, payedStyle);
		service.addOrModifySalesContract(salesContract,addmoney1, payedStyle);
		ServletActionContext.getRequest().setAttribute("ccflag", "success");
		return SUCCESS;
	}

	public String getPlans() throws Exception {
		String scd = (String) ServletActionContext.getRequest().getParameter(
				"salesContractId");
		int salesContractId = Integer.parseInt(scd);
		List<Plan> planList = service.getPlans(salesContractId);
		SalesContract sc = service.findSalesContractById(salesContractId);
		int pids[] = new int[planList.size()];
		if (gn == null || "".equals(gn) || !gn.equals("modify")) {
			List<Payed> collectList = new ArrayList<Payed>();
			if (sc.getContractReceivedMoney() != null
					&& !"".equals(sc.getContractReceivedMoney())) {
				String a = "&";
				String[] str = sc.getContractReceivedMoney().split(",");
				for (int i = 0; i < str.length; i++) {
					if (str[i] != null && !str[i].equals("")
							&& str[i].indexOf(a) > 0) {
						String[] temps = str[i].split(a);
						if (temps.length == 2) {
							Payed p = new Payed();
							p.setContractReceivedMoney(Double
									.parseDouble(temps[0]));
							p.setPayedStyle(temps[1]);
							collectList.add(p);
						}
					}
				}
			}
			List<Map> ls = new ArrayList();
			Map m = new HashMap();
			for (Plan p : planList) {

				ls = pcservice.getPlanArrival(p.getId());
				if (ls.size() > 0) {
					if (!ls.get(0).isEmpty()) {
						m.put(p.getId(), ls.get(0));
					}
				}
			}
			ServletActionContext.getRequest().setAttribute("m", m);
			List<Billing> billingList = service
					.findAllBillingBySalesContract(sc);
			ServletActionContext.getRequest().setAttribute("billingList",
					billingList);
			ServletActionContext.getRequest().setAttribute("collectList",
					collectList);

		}
		ServletActionContext.getRequest().setAttribute("salesRatioo",
				planList.get(0).getSalesRatio());
		ServletActionContext.getRequest().setAttribute("reportCompName",planList.get(0).getReportComp());
		ServletActionContext.getRequest().setAttribute("SalesContract", sc);
		ServletActionContext.getRequest().setAttribute("planList", planList);
		if (gn != null && !"".equals(gn) && gn.equals("modify"))
			return "modifySales_success";
		return "getPlans_success";
	}

	public String update_plan_sid() throws Exception {
		service.modifyplansid(planid);
		return "update_plan_sid_success";
	}

	public String del_salesContract() throws Exception {
		try {
			service.deleteSalesContract(salesContractId);
		} catch (Exception e) {
			if (e.getMessage().equals("haveOthers"))
				this.setContractMsg("该合同已执行，无法删除");
		}
		return "del_salesContract_success";
	}

	public String del_billing() throws Exception {
		String billingId = (String) ServletActionContext.getRequest()
				.getParameter("billingId");
		service.deleteBilling(Integer.parseInt(billingId));
		return "del_billing_success";
	}

	public String saveBilling() throws Exception {
		SalesContract salesContract = service.findSalesContractById(salesContractId);
		service.addBilling(billingMoney, billingDate, salesContract);
		
		return SUCCESS;
	}

	public String addBillingDetail() throws Exception {
		Billing b ;
		double tempM = 0.0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(id!=0){
			b = service.getBillingById(id);
			tempM = b.getBillingMoney();
		}else{
			
			SalesContract salesContract = service.findSalesContractById(salesContractId);
			b = new Billing();
			b.setSalesContract(salesContract);
			b.setBillingDate(sdf.parse(sbillingDate));
		}
		
		if(baid !=null && !"".equals(baid)){
			String[] aid = baid.split(",");
			for(int i = 0;i<aid.length;i++){
				if(!aid[i].trim().equals("")){
					int id = Integer.parseInt(aid[i]);
					
					ArrivalItems al = aservice.getArrivalItemById(id);
					double billingNumf = 0.0;
					double billingMoneyf = 0.0;
					double abillingNum = 0.0;
					String billingNum = ServletActionContext.getRequest().getParameter("billingNum_"+al.getId());
					if(billingNum!=null && !billingNum.trim().equals("")){
						billingNumf = Double.parseDouble(billingNum.trim());
						if(billingNumf <= 0 )
							continue;
						billingMoneyf = arithService.mul(billingNumf, al.getPlan().getSalesPrice());
						abillingNum += arithService.add(al.getBillingNum() == null?0:al.getBillingNum(), billingNumf);
						tempM += billingMoneyf;
					}
					al.setBillingStatus("02");
					
					BillingDetail bd = new BillingDetail();
					bd.setAcceptanceDate(al.getAcceptanceDate());
					bd.setAcceptanceNum(al.getAcceptanceNum());
					bd.setArrivalDate(al.getArrivalDate());
					bd.setArrivalNum(al.getArrivalNum());
					bd.setBilling(b);
					bd.setBillingDate(sdf.parse(sbillingDate));
					
					al.setBillingNum(abillingNum);
					bd.setBillingNum(billingNumf);
					bd.setBillingMoney(billingMoneyf);
					bd.setArrivalItems(al);
					bd.setPlan(al.getPlan());
					
					aservice.modifyArrivalItem(al);
					service.addBillingDetail(bd);
				}
			}
		}
		b.setBillingMoney(tempM);
		service.modifyBilling(b);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String delBillingDetail() throws Exception {
		BillingDetail bd = service.getBillingDetailById(aid);
		ArrivalItems ai = bd.getArrivalItems();
		Billing b = service.getBillingById(bd.getBilling().getId());
		double temp = arithService.sub(b.getBillingMoney(), bd.getBillingMoney());
		b.setBillingMoney(temp > 0 ? temp : 0);
		double arrivalBillingNum =  arithService.sub(ai.getBillingNum(), bd.getBillingNum());
		ai.setBillingNum(arrivalBillingNum > 0?arrivalBillingNum:0);
		if(arrivalBillingNum <= 0){
			ai.setBillingStatus("01");
			aservice.modifyArrivalItemDelBill(aid);
		}
		aservice.modifyArrivalItem(ai);
		service.modifyBilling(b);
		service.deleteBillingDetail(aid);
		return SUCCESS;
	}
	
	public String del_contractReceivedMoney() throws Exception {
		String collect = ServletActionContext.getRequest().getParameter(
				"collect");
		String salesContractId = ServletActionContext.getRequest()
				.getParameter("salesContractId");
		SalesContract salesContract = service.findSalesContractById(Integer
				.parseInt(salesContractId));
		String newstr = "";
		boolean flag = false;
		if (salesContract.getContractReceivedMoney() != null
				&& !"".equals(salesContract.getContractReceivedMoney())) {
			String a = "&";
			String[] str = salesContract.getContractReceivedMoney().split(",");

			for (int i = 0; i < str.length; i++) {
				if (str[i] != null && !str[i].equals("")
						&& str[i].indexOf(a) > 0) {
					String[] temps = str[i].split(a);
					if (temps.length == 2) {
						double temp = Double.parseDouble(temps[0]);
						if (!flag) {
							if (temp == Double.parseDouble(collect)) {
								flag = true;
								continue;
							}
						}
						if (newstr.length() == 0) {
							newstr += str[i];
						} else {
							newstr += "," + str[i];
						}
					}
				}
			}
		}
		salesContract.setTotalcontractReceivedMoney(salesContract
				.getTotalcontractReceivedMoney() - Double.parseDouble(collect));
		salesContract.setContractReceivableMoney(salesContract
				.getContractReceivableMoney() + Double.parseDouble(collect));
		salesContract.setContractReceivedMoney(newstr);
		service.modifySalesContract(salesContract);
		return "del_contractReceivedMoney_success";
	}

}
