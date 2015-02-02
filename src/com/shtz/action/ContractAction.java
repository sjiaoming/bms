package com.shtz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.ArrivalItems;
import com.shtz.model.ContractCredit;
import com.shtz.model.Organization;
import com.shtz.model.Payed;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementArrivalBean;
import com.shtz.model.ProcurementContract;
import com.shtz.model.Suppliers;
import com.shtz.model.User;
import com.shtz.service.ContractService;
import com.shtz.service.ProcurementContractService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class ContractAction extends ActionSupport {
	
	private ContractService service;
	
	private ProcurementContractService pservice;
	
	private Map<Integer,Double> getcreditmoney=new HashMap<Integer, Double>();
	
	private int ccid;
	
	private int procurementContractId;
	
	private double payNum;
	
	private int planId;
	
	private ProcurementContract procurementContract;
	
	private int orgId;
	
	private String contractNum;
	
	private String contractMsg;
	
	private Double shouldPayment;
	
	private Double qualityMoney;
	private Double advance;
	
	private String b;
	
//	private Double contractCreditMoney;
	private Double[] contractCreditMoney;
	private String[] contractCreditDate;
	
	private Double[] payed;
	
	private String[] payedStyle;
	private String[] payedDate;
	private Double am;

	private String gn;
	
	private Integer useCompId;
	
	 private String  sDate;
	 
	 private String eDate;
	
	 private Integer myOffset; 
	 
	 private String resetflag;
	 
	 private String personName;
		
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
	public String[] getPayedDate() {
		return payedDate;
	}
	public void setPayedDate(String[] payedDate) {
		this.payedDate = payedDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	public void setPservice(ProcurementContractService pservice) {
		this.pservice = pservice;
	}
	public String getGn() {
		return gn;
	}
	public void setGn(String gn) {
		this.gn = gn;
	}
	public Double getAm() {
		return am;
	}
	public void setAm(Double am) {
		this.am = am;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public Double getQualityMoney() {
		return qualityMoney;
	}
	public void setQualityMoney(Double qualityMoney) {
		this.qualityMoney = qualityMoney;
	}
	public Double getAdvance() {
		return advance;
	}
	public void setAdvance(Double advance) {
		this.advance = advance;
	}
	public Double getShouldPayment() {
		return shouldPayment;
	}
	public void setShouldPayment(Double shouldPayment) {
		this.shouldPayment = shouldPayment;
	}
	public String getContractMsg() {
		return contractMsg;
	}
	public void setContractMsg(String contractMsg) {
		this.contractMsg = contractMsg;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	
	public String[] getPayedStyle() {
		return payedStyle;
	}
	public void setPayedStyle(String[] payedStyle) {
		this.payedStyle = payedStyle;
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
	public String[] getContractCreditDate() {
		return contractCreditDate;
	}
	public void setContractCreditDate(String[] contractCreditDate) {
		this.contractCreditDate = contractCreditDate;
	}
	
	public ProcurementContract getProcurementContract() {
		return procurementContract;
	}
//	public Double getContractCreditMoney() {
//		return contractCreditMoney;
//	}
//	public void setContractCreditMoney(Double contractCreditMoney) {
//		this.contractCreditMoney = contractCreditMoney;
//	}
	public void setProcurementContract(ProcurementContract procurementContract) {
		this.procurementContract = procurementContract;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getProcurementContractId() {
		return procurementContractId;
	}

	public void setProcurementContractId(int procurementContractId) {
		this.procurementContractId = procurementContractId;
	}

	public double getPayNum() {
		return payNum;
	}

	public void setPayNum(double payNum) {
		this.payNum = payNum;
	}

	public int getCcid() {
		return ccid;
	}

	public void setCcid(int ccid) {
		this.ccid = ccid;
	}

	public void setService(ContractService service) {
		this.service = service;
	}
	
	public Map<Integer, Double> getGetcreditmoney() {
		return getcreditmoney;
	}

	public void setGetcreditmoney(Map<Integer, Double> getcreditmoney) {
		this.getcreditmoney = getcreditmoney;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页 offset 类型转换异常");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_orgId", orgId);
		if(contractNum!=null&&!"".equals(contractNum.trim()))
			params.put("contractNum", contractNum);
		if(useCompId!=null)
			params.put("dy_reportCompId_pc", " like '%,"+useCompId.toString()+",%'");
		if(sDate!=null && !"".equals(sDate.trim())  && eDate!=null && !"".equals(eDate.trim()) ){
			params.put("dy_p.signingDate", " between '"+sDate+"' and '"+eDate+"'");
		}
		if(b!=null && !b.equals("y"))
			params.put("obj_user", (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		if(personName!=null && !personName.equals("")){
			params.put("dy_ps.name", " like '%"+personName.trim()+"%'");
		}
		PageModel pm = service.getProcurementList(offset, pageSize, params, true);
		if(personName!=null && !personName.equals("")){
			params.put("personName", personName.trim());
		}
		List<ProcurementArrivalBean> pList=new ArrayList<ProcurementArrivalBean>();
		for (int i = 0; i < pm.getList().size(); i++) {
			ProcurementArrivalBean p=(ProcurementArrivalBean) pm.getList().get(i);
			double temp=0;
			if(p.getPayed()!=null){
				String a = "&";
				String[] str = p.getPayed().split(",");
					if(str.length>=1){
						for (int j = 0; j < str.length; j++) {
							if(str[j]!=null && !str[j].equals("") && str[j].indexOf(a)>0){
								String[] temps = str[j].split(a);
								if(temps.length == 2){
									temp += Double.parseDouble(temps[0]);
								}
							}
						}
					}
				}
			p.setPayed(String.valueOf(temp));
			pList.add(p);
				
			
			
			double t=service.searchContractcreditMoney(p.getId());
			getcreditmoney.put(p.getId(), t);
		}
		pm.setList(pList);
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		if(!"".equals(contractMsg)&&contractMsg!=null)
			ServletActionContext.getRequest().setAttribute("contractMsg", contractMsg);
		if(b!=null&&!"".equals(b))
			ServletActionContext.getRequest().setAttribute("b", b);
		return SUCCESS;
	}
	
	public String getProcurementPlans() throws Exception {
		String scd=(String) ServletActionContext.getRequest().getParameter("procurementContractId");
		int pcd=Integer.parseInt(scd);
		List<Plan> planList=service.getPlans(pcd);
		ProcurementContract pc=service.findProcurementContractById(pcd);
		//List<Organization> orgList =service.findAllOrganization();
		//List<Suppliers>  suppList=service.findAllSuppliers();
		if(gn==null || "".equals(gn) || !gn.equals("modify")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			List<ContractCredit> ccList=service.findContractCreditByPid(pcd);
			List<Payed> payedList=new ArrayList<Payed>();
			double temp=0;
			if(pc.getPayed()!=null){
			String a = "&";
			String[] str = pc.getPayed().split(",");
				if(str.length>=1){
					for (int j = 0; j < str.length; j++) {
						if(str[j]!=null && !str[j].equals("") && str[j].indexOf(a)>0){
							String[] temps = str[j].split(a);
							if(temps.length == 2){
								Payed p = new Payed();
								temp=Double.parseDouble(temps[0]);
								p.setPayed(temp);
								p.setPayedStyle(temps[1]);
								payedList.add(p);
							}else if(temps.length == 3){
								Payed p = new Payed();
								temp=Double.parseDouble(temps[0]);
								p.setPayed(temp);
								p.setPayedStyle(temps[1]);
								p.setPayedDate(sdf.parse(temps[2]));
								payedList.add(p);
							}
						}
					}
				}
			}
			List<Map> ls = new ArrayList();
			Map m = new HashMap();
			for(Plan p : planList){
				
//				if(!p.getArrivalItems().isEmpty()){
//					for(ArrivalItems a : p.getArrivalItems()){
//						
//					l.add(a);
//					}
//				}
				ls = pservice.getPlanArrival(p.getId());
				if(ls.size()>0){
					if(!ls.get(0).isEmpty()){
						m.put(p.getId(), ls.get(0));
					}
				}
			}
			ServletActionContext.getRequest().setAttribute("m", m);
			ServletActionContext.getRequest().setAttribute("payedList", payedList);
			ServletActionContext.getRequest().setAttribute("ccList", ccList);
		}
		//ServletActionContext.getRequest().setAttribute("orgList", orgList);
		//ServletActionContext.getRequest().setAttribute("suppList", suppList);
		ServletActionContext.getRequest().setAttribute("procurementContract", pc);
		ServletActionContext.getRequest().setAttribute("planList", planList);
		if(gn!=null && !"".equals(gn) && gn.equals("modify"))
			return "modifyProcurementPlans_success";
		return "getProcurementPlans_success";
	}
	
	public String del_contranctCredit() throws Exception {
		service.deleteContractCredit(ccid);
		return "del_contranctCredit_success";
	}

	
	public String del_payed() throws Exception {
		ProcurementContract pc=service.findProcurementContractById(procurementContractId);
		String[] str = pc.getPayed().split(",");
		StringBuffer sb = new StringBuffer();
		boolean flag=false;
		for (int j = 0; j < str.length; j++) {
			double temp=Double.parseDouble(str[j]); 
			if(!flag){
				if(temp==payNum){
					flag=true;
					continue;
				}
			}
			if(j==0){
				 sb.append(str[j]);
			 }else{
				 sb.append(','+str[j]);
			 }
		}
		pc.setPayed(sb.toString());
		service.modifyProcurementContract(pc);
		return "del_payed_success";
	}
	
	public String del_plan_pid() throws Exception {
		
		service.modifyplanpid(planId);
		return "del_plan_pid_success";
	}
	public String del_procurementContract() throws Exception {
		try {
			service.deleteProcurementContract(procurementContractId);
		} catch (Exception e) {
			if(e.getMessage().equals("haveContractCredit"))
				this.setContractMsg("该合同已执行，无法删除");
		}
		return "del_procurementContract_success";
	}
	
	
	public String doModifyProcurementContract() throws Exception {
		//double totalPayed = 0;
		//double totalCredit = 0;
//		System.out.println("............................");
		//String[] d = ServletActionContext.getRequest().getParameterValues("contractCreditMoney");
//		System.out.println("contractCreditMoney 1   "+contractCreditMoney[0]);
//		System.out.println("contractCreditDate 1  "+contractCreditDate[0]);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<payed.length;i++){
			if(payed[i]!=null && payed[i]>0){
//				System.out.println("payedDate  "+sdf.format(sdf.parse(payedDate[i])));
			//totalPayed +=payed[i];
			 if(i==0){
//				 sb.append(payed[i].toString()+"&"+payedStyle[i]);
				 sb.append(payed[i].toString()+"&"+payedStyle[i]+"&"+sdf.format(sdf.parse(payedDate[i])));
			 }else{
				 sb.append(','+payed[i].toString()+"&"+payedStyle[i]+"&"+sdf.format(sdf.parse(payedDate[i])));
			 }
			}
		}
		
		ProcurementContract p = service.findProcurementContractById(procurementContractId);
		List<ContractCredit> cList=service.findContractCreditById(procurementContractId);
		for (int i = 0; i < cList.size(); i++) {
			ContractCredit cc=service.findContractCreditByIds(cList.get(i).getId());
			String  contractCreditMoney=ServletActionContext.getRequest().getParameter(cList.get(i).getId()+"_contractCreditMoney");
			String  contractCreditDate=ServletActionContext.getRequest().getParameter(cList.get(i).getId()+"_contractCreditDate");
			if(contractCreditMoney!=null && !"".equals(contractCreditMoney)){
				cc.setContractCreditMoney(Double.parseDouble(contractCreditMoney));
				cc.setContractCreditDate(sdf.parse(contractCreditDate));
				//cc.setProcurementContract(p);
				service.addOrUpdateContractCredit(cc,cList.get(i).getId());
				//totalCredit += Double.parseDouble(contractCreditMoney);
			}else{
				//totalCredit +=cList.get(i).getContractCreditMoney();
			}
			
		}
		if(contractCreditMoney.length>0){
			for(int i=0;i<contractCreditMoney.length;i++){
				if(contractCreditMoney[i]!=null && contractCreditMoney[i]>0){
					ContractCredit cc = new ContractCredit();
					cc.setContractCreditDate(sdf.parse(contractCreditDate[i]));
					cc.setContractCreditMoney(contractCreditMoney[i]);
					cc.setProcurementContract(p);
					service.addOrUpdateContractCredit(cc,0);
					//totalCredit += contractCreditMoney[i];
				}
			}
		}
		//double temp = totalCredit + advance - qualityMoney -totalPayed;
		//shouldPayment = temp>0 ? temp : 0;
		p.setPayed(sb.toString());
		p.setShouldPayment(shouldPayment);
		p.setRemark(procurementContract.getRemark());
		service.addProcurementContract(p);
		ServletActionContext.getRequest().setAttribute("ccflag", "success");
		return SUCCESS;
	}
}
