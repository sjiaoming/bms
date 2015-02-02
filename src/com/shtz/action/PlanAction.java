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
import com.shtz.model.Change;
import com.shtz.model.Check;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.ProcurementContract;
import com.shtz.model.SalesContract;
import com.shtz.model.Suppliers;
import com.shtz.model.UseComp;
import com.shtz.model.User;
import com.shtz.service.CheckService;
import com.shtz.service.PersonService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.SuppliersService;
import com.shtz.service.UserCompService;
import com.shtz.util.KeyGenerator;
import com.shtz.util.PageModel;

/**
 * @author sjm
 * 
 */
public class PlanAction extends ActionSupport {

	private PlanService service;
	private PersonService personService;
	private SuppliersService suservice;
	private CheckService checkService;
	private ProcurementCheckService pcService;
	private UserCompService ucservice;
	private int id;
	private String pids;
	private String supplierIds;
	private Integer[] pid;
	private String itemsName;
	private String model;
//	private String planNum;
	private String reportComp;
	private String planNum;
	private String supplierName;
	private Date planReceiptDate;
	private String company;
	private Double num;
	private Date arrivalDate;
	private String arrivalSite;
	private Double budget;
	private String procurementFlag;
	private String remark;
	private String procurementWay;
	private String memo;
	private String packageNum;
	private String caseNum;
	private String procurementName;
	private Date procurementDate;
	private String procurementRemark;
	private Date searchAnnouncedDate;
	private Date searchDate;
	private String contractExecutionWay;
	private Double contractMoney;
	private Double[] procurementPrice;
	private Double[] procurementMoney;
	private Double salesMoney;
	private Double salesRatio;
	private Change change;
	private ProcurementCheck pc ;
	private String tags;

	private Map form;

	private Map map;

	private String flag;

	private String all_selected;

	private String now_selected;

	private String no_selected;

	private String planId;

	private Date changeTime;

	private Double changenum;

	private double changebudget;

	private String changereason;

	private double changecontractMoney;

	private Double contractMoney1;

	private Plan plan;

	private String sDate;

	private String eDate;

	private int ybPlanCount;

	private Double ybPlanMoney;

	private int yqProcurementContract;

	private int xYCount;

	private Double xYMoney;

	private int dXyCount;

	private Double dXyMoney;

	private Double arrivalMoney;

	private int salesContractCount;

	private Double SalesContractMoney;

	private Double yqProcurementMoney;

	private Double yfMoney;

	private Double creditMoney;

	private Double billingMoney;

	private Double ysMoney;

	private Double jz;

	private Double[] contractNum;

	private Double planPrice;

	private String contractMsg;

	private String b;

	private String cflag;

	private Integer useCompId;

	private Integer suppliersId;

	private String procurementStatus;

	private String modifyFlag;

	private String spid;
	private String sopn;

	private String method;

	private Integer myOffset;

	private String add;

	private String resetflag;

	private String personName;
	private Integer personId;
	private String initFlag;

	public String getMemo() {
		return memo;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}

	public String getInitFlag() {
		return initFlag;
	}

	public void setUcservice(UserCompService ucservice) {
		this.ucservice = ucservice;
	}

	public ProcurementCheck getPc() {
		return pc;
	}

	public String getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(String packageNum) {
		this.packageNum = packageNum;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public void setPc(ProcurementCheck pc) {
		this.pc = pc;
	}

	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}

	public String getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(String supplierIds) {
		this.supplierIds = supplierIds;
	}

	public String getProcurementName() {
		return procurementName;
	}

	public void setProcurementName(String procurementName) {
		this.procurementName = procurementName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getResetflag() {
		return resetflag;
	}

	public void setResetflag(String resetflag) {
		this.resetflag = resetflag;
	}

	public String getAdd() {
		return add;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public Integer getMyOffset() {
		return myOffset;
	}

	public void setMyOffset(Integer myOffset) {
		this.myOffset = myOffset;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
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

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public void setSpid(String spid) {
		this.spid = spid;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getProcurementStatus() {
		return procurementStatus;
	}

	public void setProcurementStatus(String procurementStatus) {
		this.procurementStatus = procurementStatus;
	}

	public Integer getUseCompId() {
		return useCompId;
	}

	public void setUseCompId(Integer useCompId) {
		this.useCompId = useCompId;
	}

	public Integer getSuppliersId() {
		return suppliersId;
	}

	public void setSuppliersId(Integer suppliersId) {
		this.suppliersId = suppliersId;
	}

	public String getCflag() {
		return cflag;
	}

	public void setCflag(String cflag) {
		this.cflag = cflag;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public Double getBillingMoney() {
		return billingMoney;
	}

	public void setBillingMoney(Double billingMoney) {
		this.billingMoney = billingMoney;
	}

	public Double getYsMoney() {
		return ysMoney;
	}

	public void setYsMoney(Double ysMoney) {
		this.ysMoney = ysMoney;
	}

	public Double getYfMoney() {
		return yfMoney;
	}

	public void setYfMoney(Double yfMoney) {
		this.yfMoney = yfMoney;
	}

	public Double getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(Double creditMoney) {
		this.creditMoney = creditMoney;
	}

	public int getSalesContractCount() {
		return salesContractCount;
	}

	public void setSalesContractCount(int salesContractCount) {
		this.salesContractCount = salesContractCount;
	}

	public Double getSalesContractMoney() {
		return SalesContractMoney;
	}

	public void setSalesContractMoney(Double salesContractMoney) {
		SalesContractMoney = salesContractMoney;
	}

	public Double getArrivalMoney() {
		return arrivalMoney;
	}

	public void setArrivalMoney(Double arrivalMoney) {
		this.arrivalMoney = arrivalMoney;
	}

	public int getdXyCount() {
		return dXyCount;
	}

	public void setdXyCount(int dXyCount) {
		this.dXyCount = dXyCount;
	}

	public Double getdXyMoney() {
		return dXyMoney;
	}

	public void setdXyMoney(Double dXyMoney) {
		this.dXyMoney = dXyMoney;
	}

	public String getContractMsg() {
		return contractMsg;
	}

	public void setContractMsg(String contractMsg) {
		this.contractMsg = contractMsg;
	}

	public Double getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(Double planPrice) {
		this.planPrice = planPrice;
	}

	public Integer[] getPid() {
		return pid;
	}

	public void setPid(Integer[] pid) {
		this.pid = pid;
	}

	public Double[] getContractNum() {
		return contractNum;
	}

	public void setContractNum(Double[] contractNum) {
		this.contractNum = contractNum;
	}

	public Double getJz() {
		return jz;
	}

	public void setJz(Double jz) {
		this.jz = jz;
	}

	public Double getYqProcurementMoney() {
		return yqProcurementMoney;
	}

	public void setYqProcurementMoney(Double yqProcurementMoney) {
		this.yqProcurementMoney = yqProcurementMoney;
	}

	public Double getxYMoney() {
		return xYMoney;
	}

	public void setxYMoney(Double xYMoney) {
		this.xYMoney = xYMoney;
	}

	public Double getYbPlanMoney() {
		return ybPlanMoney;
	}

	public void setYbPlanMoney(Double ybPlanMoney) {
		this.ybPlanMoney = ybPlanMoney;
	}

	public int getxYCount() {
		return xYCount;
	}

	public void setxYCount(int xYCount) {
		this.xYCount = xYCount;
	}

	public int getYqProcurementContract() {
		return yqProcurementContract;
	}

	public void setYqProcurementContract(int yqProcurementContract) {
		this.yqProcurementContract = yqProcurementContract;
	}

	public int getYbPlanCount() {
		return ybPlanCount;
	}

	public void setYbPlanCount(int ybPlanCount) {
		this.ybPlanCount = ybPlanCount;
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

	public double getChangecontractMoney() {
		return changecontractMoney;
	}

	public void setChangecontractMoney(double changecontractMoney) {
		this.changecontractMoney = changecontractMoney;
	}

	public Double getContractMoney1() {
		return contractMoney1;
	}

	public void setContractMoney1(Double contractMoney1) {
		this.contractMoney1 = contractMoney1;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}

	public Double getChangenum() {
		return changenum;
	}

	public void setChangenum(Double changenum) {
		this.changenum = changenum;
	}

	public double getChangebudget() {
		return changebudget;
	}

	public void setChangebudget(double changebudget) {
		this.changebudget = changebudget;
	}

	public String getChangereason() {
		return changereason;
	}

	public void setChangereason(String changereason) {
		this.changereason = changereason;
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

	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}

	public void setForm(Map form) {
		this.form = form;
	}

	public void setCheckService(CheckService checkService) {
		this.checkService = checkService;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	private int orgId;

	private List l;

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

	public void setService(PlanService service) {
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

	public String getplanNum() {
		return planNum;
	}

	public void setplanNum(String planNum) {
		this.planNum = planNum;
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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
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

	public String getProcurementWay() {
		return procurementWay;
	}

	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}

	public Date getProcurementDate() {
		return procurementDate;
	}

	public void setProcurementDate(Date procurementDate) {
		this.procurementDate = procurementDate;
	}

	public String getProcurementRemark() {
		return procurementRemark;
	}

	public void setProcurementRemark(String procurementRemark) {
		this.procurementRemark = procurementRemark;
	}

	public Date getSearchAnnouncedDate() {
		return searchAnnouncedDate;
	}

	public void setSearchAnnouncedDate(Date searchAnnouncedDate) {
		this.searchAnnouncedDate = searchAnnouncedDate;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
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

	public Double getSalesMoney() {
		return salesMoney;
	}

	public void setSalesMoney(Double salesMoney) {
		this.salesMoney = salesMoney;
	}

	public Double getSalesRatio() {
		return salesRatio;
	}

	public void setSalesRatio(Double salesRatio) {
		this.salesRatio = salesRatio;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}

	public PlanService getService() {
		return service;
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
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_org", orgId);
		if (planNum != null && !"".equals(planNum))
			params.put("planNum", planNum);
		params.put("eq_planStatus", "01");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.arrivalDate", " between '" + sDate + "' and '"+ eDate + "'");
//			params.put("dy_p.planReceiptDate", " between '" + sDate + "' and '"+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		// params.put("obj_user",
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		// PageModel pm = service.searchPlans(offset, pageSize);
		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// String add = ServletActionContext.getRequest().getParameter("add");
		// ServletActionContext.getRequest().setAttribute("add", add);
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		this.setMyOffset(offset);
		if (b != null && !"".equals(b))
			ServletActionContext.getRequest().setAttribute("b", b);

		return SUCCESS;
	}

	// add by yao 20130415
	public String searchPlans() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_org", orgId);
		if (planNum != null && !"".equals(planNum))
			params.put("planNum", planNum);
		params.put("eq_planStatus", "01");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.arrivalDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		// params.put("obj_user",
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		// PageModel pm = service.searchPlans(offset, pageSize);
		
		/*Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// String add = ServletActionContext.getRequest().getParameter("add");
		// ServletActionContext.getRequest().setAttribute("add", add);
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		this.setMyOffset(offset);
		if (b != null && !"".equals(b))
			ServletActionContext.getRequest().setAttribute("b", b);

		return SUCCESS;
	}
	
	public String searchPlanStatus() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_org", orgId);
		if (planNum != null && !"".equals(planNum))
			params.put("planNum", planNum);
		//params.put("eq_planStatus", "01");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.arrivalDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		 params.put("obj_user",(User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,pageSize);
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		this.setMyOffset(offset);
		if (b != null && !"".equals(b))
			ServletActionContext.getRequest().setAttribute("b", b);

		return SUCCESS;
	}
	public String searchPlansForApproval() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int managerId = (Integer) ServletActionContext.getRequest()
				.getSession().getAttribute("pid");
		int pageSize = 50;
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_org", orgId);
		if (planNum != null && !"".equals(planNum))
			params.put("planNum", planNum);
		System.out.println("searchPlansForApproval  personId   " + personId);
		if (personId != 0 && !"".equals(personId)) {
			params.put("id_person.id", personId);
			ServletActionContext.getRequest()
					.setAttribute("personId", personId);
		}
		params.put("eq_planStatus", "01");
		// only search checkstatus is 01 status
		//经理末审核
		params.put("eq_checkStatus", "03");
		params.put("id_manager.id", managerId);
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.arrivalDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		// params.put("obj_user",
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		// PageModel pm = service.searchPlans(offset, pageSize);
		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		/*try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		// String add = ServletActionContext.getRequest().getParameter("add");
		// ServletActionContext.getRequest().setAttribute("add", add);
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		this.setMyOffset(offset);
		// 页面设置persons
		List<Person> ps = personService.loadPersons();
		ServletActionContext.getRequest().setAttribute("persons", ps);
		if (b != null && !"".equals(b))
			ServletActionContext.getRequest().setAttribute("b", b);

		return SUCCESS;
	}

	public String planTrackSearch() throws Exception {
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
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		/*
		 * if(method!=null && !"".equals(method) && method.equals("planAdd")){
		 * params.put("in_planStatus", "01"); }else{ params.put("in_planStatus",
		 * "01,02"); }
		 */
		//plan.PlanStatus 未处理
		params.put("eq_planStatus", "01");
		//06  checkStatus  经理己审核 when set operator 2 checkstatus 05
		params.put("eq_checkStatus", "05");
		if (orgId != 0)
			params.put("id_org", orgId);
		if (planNum != null)
			params.put("planNum", planNum);
		if (procurementStatus != null && !"".equals(procurementStatus)) {
			if (procurementStatus.equals("01")) {
				params.put("dy_p.procurementDate", " is not null");
			} else if (procurementStatus.equals("02")) {
				params.put("dy_p.procurementWay", " is not null");
				params.put("dy_p.procurementDate", " is null");
			} else if (procurementStatus.equals("03")) {
				params.put("dy_p.procurementWay", " is null");
				params.put("dy_p.procurementDate", " is null");
			}
		}
		if (remark != null && !"".equals(remark.trim()))
			params.put("remark", remark.trim().trim());
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.arrivalDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		//delete 20140702
//		if (personName != null && !personName.equals(""))
//			params.put("person.name", personName.trim());
		//search person which is settled by groupLeader
		params.put("id_person.id", personId);
		params.put("in_groupStatus", "'04'");
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		//params.put("personName", personName.trim());
//		HttpServletRequest r = ServletActionContext.getRequest();
//		ProcurementCheck pc = new ProcurementCheck();
//		pc.setId(Integer.parseInt((String)r.getAttribute("pcid")));
//		r.setAttribute("pc", pc);
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
		if (method != null && !"".equals(method) && method.equals("planAdd")) {
			return "planAdd_success";
		}
		return SUCCESS;
	}

	
	public String initPlanPage() throws Exception {
		System.out.println("-----Plan Action add()----");
		List<Person> ps = new ArrayList<Person>();
		if ("initSetPlanner".equals(initFlag)) {
			//List<Person> clerks = personService.loadClerksPersons();
			List<Person> clerks = personService.loadPersons();
			ps = personService.loadManagerPersons();
			ServletActionContext.getRequest().setAttribute("mpersons", ps);
			ServletActionContext.getRequest().setAttribute("cpersons", clerks);
			return "initSetPlanner";

		} else if ("initPlanApproval".equals(initFlag)) {
			if (plan.getId() != 0) {
				plan = service.findPlan(plan.getId());
			}
			ps = personService.loadPersons();
			ServletActionContext.getRequest().setAttribute("persons", ps);
			return "initPlanApproval";

		} else if ("initPlanApprovalList".equals(initFlag)) {
			ps = personService.loadPersons();
			ServletActionContext.getRequest().setAttribute("persons", ps);
			return "initPlanApprovalList";

		} else {
			// service.addPlan(plan, orgId);
			// this.execute();
			return SUCCESS;
		}
	}

	public String modifyPlansPerson() throws Exception {
		// tags.split(",");
//		System.out.println("personId  --  " + personId + "personId  --  "
//				+ plan.getManager().getId());
//		System.out.println("tags  --  " + tags);
		int directorId = (Integer) ServletActionContext.getRequest()
				.getSession().getAttribute("pid");
		service.modifyPlanPersonByIds(tags, personId,
				plan.getManager().getId(), directorId);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		// if(modifyFlag !=null && modifyFlag.equals("seekSource"))
		// return "seekSource_success";
		return "success";
	}
//计划  更新
	public String modifyPlansOpinion() throws Exception {
		// String opinion = plan.getOpinion();
		// plan.setOpinion(null);
		System.out.println("plan.getOpinion()   "+plan.getOpinion());
		service.modifyPlanOpinionByIds(tags, plan);
		int personId = (Integer) ServletActionContext.getRequest().getSession()
				.getAttribute("pid");
		String[] idss = tags.split(",");
		Integer[] ids = new Integer[idss.length];
		for (int i = 0; i < idss.length; i++) {
			ids[i] = Integer.parseInt(idss[i]);
		}
		Person pe = personService.findPerson(personId);
		System.out.println("ids------------      " + ids);
		List<Plan> ls = service.getPlans(ids);
		List<Check> cs = new ArrayList<Check>();
		for (Plan p : ls) {
			Check check = new Check();
			check.setPlan(p);
			check.setPerson(pe);
			check.setCheckDate(new Date());
			check.setCheckStatus(p.getCheckStatus());
			check.setOpinion(plan.getOpinion());
			check.setCheckType(p.getPlanStatus());
			cs.add(check);
		}
		// System.out.println("plan.getPlanStatus()------------      "+plan.getPlanStatus());
		checkService.addChecks(cs);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}

	public String initProcurementWay() throws Exception {
//		List slist = suservice.getSupplierslist(); 
//		ServletActionContext.getRequest().setAttribute("suppliers", slist);
		// System.out.println("personId  --  "+ plan.getId() +
//		String role = (String)ServletActionContext.getRequest().getAttribute("role");
//		pc = pcService.getProcurementCheck(pc.getId());
		if("manager".equals(initFlag)){
			List<Person> managers = personService.loadManagerPersons();
			ServletActionContext.getRequest().setAttribute("mpersons", managers);
			return "innerProcurementWay";
		}else if("director".equals(initFlag)){
			List<Person> directors = personService.loadDirectorPersons();
			ServletActionContext.getRequest().setAttribute("dpersons", directors);
			
//			String pids = (String)ServletActionContext.getRequest().getAttribute("pids");
			System.out.println("pids   "+pids);
			String[] planIds = pids.split(",");
			int pId = Integer.parseInt(planIds[0]);
			Plan p =  service.findPlan(pId);
			if(p != null){
//				System.out.println(p.getReportCompId());
				UseComp uc = ucservice.findUseComp(p.getReportCompId().intValue());
				//System.out.println("contractNum    ....  "+contractNum);
				String code = uc.getCode()==null?"":uc.getCode();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				Date d = new Date();
				String srt = sdf.format(d);
				String head = code+srt;//定义开头字符串
				String maxCode = pcService.getMaxCaseNum(head);//从数据库获取最大值
				/*if(maxCode!=null && !"".equals(maxCode) && maxCode.length() == 14){
						maxCode = zContractService.getMaxContractNum(head).substring(0, 14);
				}*/
				String maxCaseNum = KeyGenerator.computeNewCode(maxCode,head,4);
				ServletActionContext.getRequest().setAttribute("maxCaseNum",maxCaseNum);
				System.out.println("maxCaseNum    "+maxCaseNum);
			}
			
			return "initProcurementWay";
		}else if("innerContral".equals(initFlag)){
			List<Person> directors = personService.loadInnerContralPersons();
			ServletActionContext.getRequest().setAttribute("ipersons", directors);
			return "directorProcurementWay";
		}else if("chairMan".equals(initFlag)){
			List<Person> directors = personService.loadChairManPersons();
			ServletActionContext.getRequest().setAttribute("cpersons", directors);
			return "managerProcurementWay";
		}else{

			return "initProcurementWay";
		}
	}

	public String doProcurementWay() throws Exception {
//		String[] str = tags.split(",");
//		String ps = "04";
		Date d = new Date();
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
		ProcurementCheck pck = new ProcurementCheck();
		int maxPCnum = pcService.getNextProcurementCheckNum();
		pck.setCheckNum(maxPCnum);
		pck.setPerson(pe);
		pck.setSubmitDate(d);
		pck.setProcurementWay(procurementWay);
		pck.setProcurementName(procurementName);
		pck.setSupplierIds(supplierIds);
		pck.setMemo(memo);
		pck.setCaseNum(caseNum);
		pck.setPackageNum(packageNum);
		//01 主管末审核
		pck.setCheckStatus("01");
		System.out.println("pc.getDirector()    "+pc.getDirector().getId());
		pck.setDirector(pc.getDirector());
		pcService.addProcurementCheck(pck);
		service.modifyPlansPc(tags,pck.getId());

		List<Check> cs = new ArrayList<Check>();
		
		Check check = new Check();
		check.setPc(pck);
		check.setPerson(pe);
		check.setCheckDate(d);
		//主管末审核
		check.setCheckStatus("01");
		//02:已采购方案
		check.setCheckType("02");
		cs.add(check);
		
		checkService.addChecks(cs);
		// System.out.println("plan.getPlanStatus()------------      "+plan.getPlanStatus());
		// if(modifyFlag !=null && modifyFlag.equals("seekSource"))
		// ps = "03";
		// service.addProcurementWay(str, procurementWay, procurementDate,
		// procurementRemark,ps);
		// this.planTrackSearch();
		 ServletActionContext.getRequest().setAttribute("cflag", "success");
		// if(modifyFlag !=null && modifyFlag.equals("seekSource"))
		// return "seekSource_success";
		return "success";

	}

	// end add by yao 20130415
	public String add() throws Exception {
		System.out.println("-----Plan Action add()----");
		Plan plan = new Plan();
		plan.setItemsName(itemsName);
		plan.setModel(model);
//		plan.setplanNum(planNum);
		plan.setReportComp(reportComp);
		plan.setPlanNum(planNum);
//		plan.setNum(num);

		service.addPlan(plan, orgId);
		this.execute();
		return "add_success";
	}

	public String delete() throws Exception {
		service.deletePlan(id);
		this.execute();
		return "del_success";
	}

	public String add_input() throws Exception {

		return SUCCESS;
	}

	public String addOrModify() throws Exception {

		Plan p = service.findPlan(id);
		if (p != null) {
			ServletActionContext.getRequest().setAttribute("plan", p);
			return "modify_input";
		} else {
			return "add_input";
		}

	}

	public String procurementWay() throws Exception {

		Plan p = service.findPlan(id);

		ServletActionContext.getRequest().setAttribute("plan", p);
		return "procurementWay_input";

	}

	public String SeekSourceSearch() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		params.put("in_planStatus", "02,03");
		if (orgId != 0)
			params.put("id_org", orgId);
		if (planNum != null)
			params.put("planNum", planNum);
		params.put("dy_p.procurementDate", "is not null");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.planReceiptDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (remark != null && !"".equals(remark.trim()))
			params.put("remark", remark.trim());
		if (personName != null && !personName.equals(""))
			params.put("person.name", personName.trim());
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		params.put("personName", personName.trim());
		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
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
			// System.out.println("======now_select======"+now_select);
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
			// System.out.println("======all======"+all_select);
			request.setAttribute("all", all_select);

		}
	}

	public String doSeekSource() throws Exception {
		if (pid.length > 0) {
			for (int i = 0; i < pid.length; i++) {
				if (pid[i] != null) {
					Plan pn = new Plan();
					pn.setId(pid[i]);
					if (procurementPrice[i] != null)
						pn.setProcurementPrice(procurementPrice[i]);
					if (procurementMoney[i] != null)
						pn.setProcurementMoney(procurementMoney[i]);
					if (searchAnnouncedDate != null)
						pn.setSearchAnnouncedDate(searchAnnouncedDate);
					if (contractExecutionWay != null
							&& !contractExecutionWay.equals("0"))
						pn.setContractExecutionWay(contractExecutionWay);
					if (searchDate != null && !"".equals(searchDate)) {
						pn.setSearchDate(searchDate);
						pn.setPlanStatus("03");
					}
					service.modifyPlanPM(pn);
				}
			}
		}
		this.setCflag("success");
		// ServletActionContext.getRequest().setAttribute("cflag", "success");
		return "success";
	}

	public String modifyPlanClearSeekDate() throws Exception {
		String[] str = tags.split(",");
		if (str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				if (str[i] != null) {
					Plan pn = new Plan();
					pn.setId(Integer.parseInt(str[i]));
					service.modifyPlanClearSeekDate(pn);
				}
			}
		}

		return "success";
	}

	public String doSeekSourcePmodify() throws Exception {
		String[] str = tags.split(",");
		if (str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				if (str[i] != null) {
					Plan pn = new Plan();
					pn.setId(Integer.parseInt(str[i]));
					if (procurementPrice != null)
						pn.setProcurementPrice(procurementPrice[i]);
					if (procurementMoney != null)
						pn.setProcurementMoney(procurementMoney[i]);
					if (searchAnnouncedDate != null)
						pn.setSearchAnnouncedDate(searchAnnouncedDate);
					if (contractExecutionWay != null)
						pn.setContractExecutionWay(contractExecutionWay);
					if (searchDate != null && !"".equals(searchDate)) {
						pn.setSearchDate(searchDate);
						pn.setPlanStatus("03");
					}
					service.modifyPlanPM(pn);
				}
			}
		}
		this.setCflag("success");
		return "success";
	}

	public String modifySeekSource() throws Exception {
		if (pid.length > 0) {
			for (int i = 0; i < pid.length; i++) {
				if (pid[i] != null) {
					Plan pn = new Plan();
					pn.setId(pid[i]);
					pn.setProcurementPrice(procurementPrice[i]);
					pn.setProcurementMoney(procurementMoney[i]);
					pn.setSearchAnnouncedDate(searchAnnouncedDate);
					pn.setSearchDate(searchDate);
					pn.setContractExecutionWay(contractExecutionWay);
					service.modifyPlanPM(pn);
				}
			}
		}
		this.setCflag("success");
		return SUCCESS;
	}

	public String doChange() throws Exception {
		Date changetime = new Date();
		Person ps = (Person) ServletActionContext.getRequest().getSession()
				.getAttribute("person");
		Plan p = service.findPlan(Integer.parseInt(planId));
		System.out.println("doChange is executed--------------------------------------------------------");
//		service.addOrUpdageChange(p, changetime, p.getNum(), p.getBudget(),changereason, p.getPlanPrice(), ps.getName());
//		p.setNum(changenum);
//		p.setBudget(changecontractMoney);
//		p.setPlanPrice(planPrice);
		service.modifyPlanChange(planId, changenum, changecontractMoney,
				planPrice);
		// service.addOrUpdageChange(planId, changetime, changenum,
		// changecontractMoney, changereason,planPrice,p.getName());
		this.setCflag("success");
		return SUCCESS;
	}

	public String modifyPlan() throws Exception {
		int id = 0;
		if (planId != null) {
			id = Integer.parseInt(planId);
		}
		Plan p = service.findPlan(id);
		if (p != null) {
			ServletActionContext.getRequest().setAttribute("plan", p);
		}
		return "modifyPlan_success";
	}

	public String doModifyPlan() throws Exception {
		// System.out.println(plan.getItemsName());
		service.modifyPlan(plan);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		// this.planTrackSearch();
		return SUCCESS;
	}

	public String doModifyPlanMg() throws Exception {
		System.out.println("arrivalDate   " + arrivalDate);
		plan.setPlanReceiptDate(new Date());
		// plan.setArrivalDate(arrivalDate);
		service.modifyPlanMg(plan);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		// return "doModifyPlanMg_success";
		// return searchPlans();
		return SUCCESS;
	}

	public String changeSearch() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fenye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		//未处理    已采购方案    已寻源
		params.put("in_planStatus", "'01','02','03'");
		if (orgId != 0)
			params.put("id_org", orgId);
		if (supplierName != null && !"".equals(supplierName)){
				params.put("mh_p.supplier.name", supplierName);
		}
		if (planNum != null)
			params.put("planNum", planNum);
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		//params.put("dy_p.searchDate", "is null");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null && !"".equals(eDate.trim())) {
			params.put("dy_p.planReceiptDate", " between '" + sDate + "' and '"
					+ eDate + "'");
		}
		//delete by brown 20140729
//		 params.put("dy_p.salesContract", " is null ");
		 params.put("dy_p.procurementContract", " is null ");
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		 params.put("supplierName",supplierName);
		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);

		if (contractMsg != null && !"".equals(contractMsg))
			ServletActionContext.getRequest().setAttribute("contractMsg",
					contractMsg);
		return SUCCESS;
	}

	public String getSave() throws Exception {
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_org.id", orgId);

		Map params1 = new HashMap();
		if (orgId != 0)
			params1.put("id_orgId", orgId);
		Map params2 = new HashMap();
		if (orgId != 0)
			params2.put("id_plan.org.id", orgId);
		Map params3 = new HashMap();
		if (orgId != 0)
			params3.put("id_procurementContract.orgId", orgId);
		Map params4 = new HashMap();
		if (orgId != 0)
			params4.put("id_salesContract.orgId", orgId);
		ybPlanCount = service.getPlanCountByDate(sDate, eDate, params);

		ybPlanMoney = service.getPlanMoneyByDate(sDate, eDate, params);

		xYCount = service.getPlanXYCountByDate(sDate, eDate, params);

		xYMoney = service.getPlanXYMoneyByDate(sDate, eDate, params);

		dXyCount = service.getPlanDXYCountByDate(sDate, eDate, params);

		dXyMoney = service.getPlanDXYMoneyByDate(sDate, eDate, params);

		yqProcurementContract = service.getProcurementContractCountByDate(
				sDate, eDate, params1);

		yqProcurementMoney = service.getProcurementMoneyCountByDate(sDate,
				eDate, params1);

		arrivalMoney = service.getPlanArrivalMoneyByDate(sDate, eDate, params2);

		salesContractCount = service.getSalesContractCountByDate(sDate, eDate,
				params1);

		SalesContractMoney = service.getSalesContractMoneyByDate(sDate, eDate,
				params1);

		creditMoney = service.getContractCreditMoneyByDate(sDate, eDate,
				params3);

		List<ProcurementContract> pList = service.getProcurementsByDate(sDate,
				eDate, params1);
		double temp = 0;
		for (ProcurementContract p : pList) {
			if (p.getPayed() != null && !"".equals(p.getPayed())) {
				String a = "&";
				String[] str = p.getPayed().split(",");
				if (str.length >= 1) {
					for (int j = 0; j < str.length; j++) {
						if (str[j] != null && !str[j].equals("")
								&& str[j].indexOf(a) > 0) {
							String[] temps = str[j].split(a);
							if (temps.length == 2) {
								temp += Double.parseDouble(temps[0]);
							}
						}
					}
				}
			}
		}
		yfMoney = temp;

		List<SalesContract> sList = service.getSalesContractsByDate(sDate,
				eDate, params1);
		double temp1 = 0;
		for (SalesContract p : sList) {
			if (p.getContractReceivedMoney() != null) {
				String a = "&";
				String[] str = p.getContractReceivedMoney().split(",");
				if (str.length >= 1) {
					for (int j = 0; j < str.length; j++) {
						if (str[j] != null && !str[j].equals("")
								&& str[j].indexOf(a) > 0) {
							String[] temps = str[j].split(a);
							if (temps.length == 2) {
								temp1 += Double.parseDouble(temps[0]);
							}
						}
					}
				}
			}
		}
		ysMoney = temp1;
		billingMoney = service.getbillingMoneyByDate(sDate, eDate, params4);
		jz = service.getJzByDate(sDate, eDate, params);
		if (flag != null && !"".equals(flag) && flag.equals("y"))
			return "dySuccess";
		else
			return SUCCESS;
	}

	public String cfPlan() throws Exception {

		service.addcfPlan(id, contractNum, cflag);
		ServletActionContext.getRequest().setAttribute("ccflag", "success");
		if (cflag != null && !"".equals(cflag) && cflag.equals("pcontract"))
			return "pcontract_success";
		else
			return SUCCESS;
	}

	public String toSeekSource() throws Exception {

		String[] str = tags.split(",");
		Integer[] ia = new Integer[str.length];
		for (int i = 0; i < str.length; i++) {
			ia[i] = Integer.parseInt(str[i]);
		}

		List plist = service.getPlans(ia);
		ServletActionContext.getRequest().setAttribute("plist", plist);
		if (flag != null && flag.equals("success_price"))
			return "success_price";
		return SUCCESS;
	}

	public String toModifySeekSource() throws Exception {

		Plan p = service.findPlan(id);

		ServletActionContext.getRequest().setAttribute("plan", p);
		if (modifyFlag != null) {
			if (id == 0)
				ServletActionContext.getRequest().setAttribute("pids", tags);
			else {
				ServletActionContext.getRequest().setAttribute("pids", id);
				ServletActionContext.getRequest().setAttribute(
						"procurementRemark", p.getProcurementRemark());
			}
			if (modifyFlag.equals("procurementRemark"))
				return "seekSource_success";
			if (modifyFlag.equals("seekSource"))
				return "seekSource_success";
		}
		return "modifySuccess";
	}

	public String getPlanStatusList() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			System.out.println("fen ye error");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_oid", orgId);
		if (planNum != null && !"".equals(planNum.trim()))
			params.put("planNum", planNum);
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		if (procurementWay != null && !"".equals(procurementWay.trim())
				&& !("0".equals(procurementWay.trim())))
			params.put("eq_pc.procurementWay", procurementWay);
		if(procurementName!=null && !"".equals(procurementName.trim()))
			params.put("pc.procurementName", procurementName.trim());
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.planReceiptDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (suppliersId != null)
			params.put("dy_p.procurementContract.suppliersId", "=" + suppliersId + "  ");
		//params.put("dy_p.model", "<> '02'");
		if (personName != null && !personName.equals("")) {
			params.put("dy_p.person.name", " like '%" + personName.trim() + "%'");
		}
		PageModel pm = service.getPlanStatusList(offset, pageSize, params, true);
		if (personName != null && !personName.equals("")) {
			params.put("personName", personName.trim());
		}
		if (procurementName != null && !procurementName.equals("")) {
			params.put("procurementName", procurementName.trim());
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}

	public String getReportCompList() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			System.out.println("fen ye error");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (orgId != 0)
			params.put("id_oid", orgId);
		if (useCompId != null)
			params.put("id_reportCompId", useCompId);
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.planReceiptDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
//		PageModel pm = service.getReportCompList(offset, pageSize, params, true);
		PageModel pm = service.exportReportComp(offset, pageSize, params, true);
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}

	public String getPlanss() throws Exception {
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
		params.put("in_planStatus", "01");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.planReceiptDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if (remark != null && !"".equals(remark.trim()))
			params.put("remark", remark.trim());
		Person p = (Person) ServletActionContext.getRequest().getSession()
				.getAttribute("person");
		if (p != null)
			params.put("id_org", p.getOrg().getId());
		else
			return LOGIN;
		if (planNum != null && !"".equals(planNum.trim()))
			params.put("planNum", planNum);
		params.put("dy_p.person ", " is null");
		params.put("dy_p.user ", " is null");
		// Person p =
		// (Person)ServletActionContext.getRequest().getSession().getAttribute("person");
		// if(p!=null)
		// params.put("obj_org",
		// ((Person)ServletActionContext.getRequest().getSession().getAttribute("person")).getOrg().getId());
		// else
		// return LOGIN;
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);

		Map form = new HashMap();
		form.put("all_selected", all_selected);
		form.put("no_selected", no_selected);
		form.put("now_selected", now_selected);

		try {
			this.operationCheckInfo(map, form,
					ServletActionContext.getRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);

		return SUCCESS;
	}

	public String getPlanssPerson() throws Exception {
		Plan plan = new Plan();
		Person p = (Person) ServletActionContext.getRequest().getSession()
				.getAttribute("person");
		User u = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("login");
		String[] str = tags.split(",");
		Integer[] ia = new Integer[str.length];
		for (int i = 0; i < str.length; i++) {
			ia[i] = Integer.parseInt(str[i]);
			plan.setId(ia[i]);
			plan.setPerson(p);
			plan.setUser(u);
			service.modifyPlansByIds(plan);
		}

		// service.modifyPlansByIds(p.getId(), u.getId(), tags);
		// Plan pl = new Plan();
		// pl.setPerson(p);
		// pl.setUser(u);
		return SUCCESS;
	}

	public String modifyPlansByIdsBack() throws Exception {
		service.modifyPlansByIdsBack(tags);
		String from = ServletActionContext.getRequest().getParameter("from");
		if (from != null && !"".equals(from) && from.equals("seek"))
			return "seek_success";
		return SUCCESS;
	}

	public String cancelPlans() throws Exception {
		service.modifycancelPlans(tags);
		return SUCCESS;
	}

	public String redoPlans() throws Exception {
		service.modifyredoPlans(tags);
		return SUCCESS;
	}

	public String deletePlansByIds() throws Exception {
		service.deletePlansByIds(tags);
		return SUCCESS;
	}

	public String modifyPlansOrg() throws Exception {
		service.modifyPlansOrg(tags, id);
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		return SUCCESS;
	}

	public String initChangeSuppliers() throws Exception {
		List slist = suservice.getSupplierslist();
		ServletActionContext.getRequest().setAttribute("suppliers", slist);
		System.out.println(tags);
		return SUCCESS;
	}
	public String initChangeReportComp() throws Exception {
		List slist = ucservice.getUseCompList();
		ServletActionContext.getRequest().setAttribute("useComps", slist);
		System.out.println(tags);
		return SUCCESS;
	}
	public String doChangePlan() throws Exception {
		List slist = ucservice.getUseCompList();
		ServletActionContext.getRequest().setAttribute("useComps", slist);
		String[] idss = tags.split(",");
		Integer[] ids = new Integer[idss.length];
		for (int i = 0; i < idss.length; i++) {
			ids[i] = Integer.parseInt(idss[i]);
		}
		if(flag.equals("changeSupplier")){
			Suppliers s = suservice.findSuppliers(plan.getSupplier().getId());
			List<Plan> ps = service.getPlans(ids);
			for(Plan p : ps){
				p.setSupplier(s);
				service.modifyPlan(p);
			}
		}
		if(flag.equals("changeUseComp")){
			UseComp uc = ucservice.findUseComp(plan.getReportCompId());
			List<Plan> ps = service.getPlans(ids);
			for(Plan p : ps){
				p.setReportComp(uc.getName());
				p.setReportCompId(uc.getId());
				service.modifyPlan(p);
			}
		}
		System.out.println(tags);
		return SUCCESS;
	}
}
