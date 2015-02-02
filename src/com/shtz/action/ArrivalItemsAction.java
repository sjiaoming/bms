package com.shtz.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.ArrivalItems;
import com.shtz.model.Billing;
import com.shtz.model.BillingDetail;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementContract;
import com.shtz.model.SalesContract;
import com.shtz.service.ArrivalItemsService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementContractService;
import com.shtz.service.SalesContractService;
import com.shtz.util.Arith;

/**
 * @author sjm
 *  
 */
public class ArrivalItemsAction extends ActionSupport {
	
	private ProcurementContractService service;
	
	private PlanService pservice;
	
	private ArrivalItemsService  aservice;
	
	private Arith arithService;
	
	private SalesContractService scservice;
	
	private String sbillingDate;


	public String getSbillingDate() {
		return sbillingDate;
	}

	public void setSbillingDate(String sbillingDate) {
		this.sbillingDate = sbillingDate;
	}
	public void setScservice(SalesContractService scservice) {
		this.scservice = scservice;
	}

	public void setArithService(Arith arithService) {
		this.arithService = arithService;
	}

	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}

	public void setService(ProcurementContractService service) {
		this.service = service;
	}


	public void setAservice(ArrivalItemsService aservice) {
		this.aservice = aservice;
	}
	private int id;
	private int scid;
	private int aid;
	private Double[] arrivalNum;
	private String[] arrivalDate;
	private Double[] acceptanceNum;
	private String[] acceptanceDate;
	private Plan plan;
	
	private int[] pid;

	private Double billingMoney;
	private Date billingDate;
	
	
	public int getScid() {
		return scid;
	}

	public void setScid(int scid) {
		this.scid = scid;
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

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int[] getPid() {
		return pid;
	}

	public void setPid(int[] pid) {
		this.pid = pid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
	public String[] getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String[] arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String[] getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(String[] acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	public Double[] getArrivalNum() {
		return arrivalNum;
	}

	public void setArrivalNum(Double[] arrivalNum) {
		this.arrivalNum = arrivalNum;
	}

	public Double[] getAcceptanceNum() {
		return acceptanceNum;
	}

	public void setAcceptanceNum(Double[] acceptanceNum) {
		this.acceptanceNum = acceptanceNum;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		return SUCCESS;
	}
	public String toAddArrival() throws Exception {
		
		ProcurementContract pc = service.findProcurementContractById(id);
		List l = new ArrayList();
		
		List<Map> ls = new ArrayList();
		Map m = new HashMap();
		//Map m = new HashMap();
		if(!pc.getPlan().isEmpty()){
			//Iterator<Plan> plan = pc.getPlan().iterator();
			for(Plan p : pc.getPlan()){
				
				if(!p.getArrivalItems().isEmpty()){
					for(ArrivalItems a : p.getArrivalItems()){
					
					l.add(a);
					}
				}
				ls = service.getPlanArrival(p.getId());
				if(ls.size()>0){
					if(!ls.get(0).isEmpty()){
						m.put(p.getId(), ls.get(0));
					}
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("al", l);
		
		ServletActionContext.getRequest().setAttribute("pc", pc);
		ServletActionContext.getRequest().setAttribute("m",m);
		return SUCCESS;
	}
	public String toAddBillingByArrival() throws Exception {
		List l = new ArrayList();//未绑定
		List na = new ArrayList();//已绑定的本票信息
		SalesContract sc=scservice.findSalesContractById(scid);
		Billing billing = scservice.getBillingById(id);
		if(billing !=null){
			if(!billing.getBillingDetail().isEmpty()){
				for(BillingDetail bd :billing.getBillingDetail()){
					na.add(bd);
				}
			}
		}
		if(!sc.getPlan().isEmpty()){
			for(Plan p : sc.getPlan()){
				if(!p.getArrivalItems().isEmpty()){
					for(ArrivalItems a : p.getArrivalItems()){
						if(a.getBillingNum()!=null && a.getBillingNum() < a.getArrivalNum())
							l.add(a);
						if(a.getBillingNum() ==null)
							l.add(a);
						
					}
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("al", l);
		ServletActionContext.getRequest().setAttribute("na", na);
		ServletActionContext.getRequest().setAttribute("salesContract", sc);
		ServletActionContext.getRequest().setAttribute("id", id);
		ServletActionContext.getRequest().setAttribute("sbillingDate", billingDate);
		return SUCCESS;
	}
	public String addArrival() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(pid.length>0){
			for(int i=0;i<pid.length;i++){
				System.out.println(acceptanceDate[i]);
				System.out.println(sdf.parse(acceptanceDate[i]).toString());
				System.out.println("arrivalNum[i]   "+arrivalNum[i]);
				if(arrivalNum[i] != 0.0){
					Plan p = pservice.findPlan(pid[i]);
					this.setPlan(p);
					ArrivalItems a = new ArrivalItems();
					a.setAcceptanceDate(sdf.parse(acceptanceDate[i]));
					a.setAcceptanceNum(acceptanceNum[i]);
					a.setArrivalDate(sdf.parse(arrivalDate[i]));
					a.setArrivalNum(arrivalNum[i]);
					a.setPlan(plan);
					double temp = p.getArrivalNumTotal()==null?0.0:p.getArrivalNumTotal();
					double tempac = p.getAcceptanceNumTotal()==null?0.0:p.getAcceptanceNumTotal();
					double arrivalNumTotal = arithService.add(temp, arrivalNum[i]==null?0.0:arrivalNum[i]);
					double acceptanceNumTotal = arithService.add(tempac, acceptanceNum[i]==null?0.0:acceptanceNum[i]);
					p.setArrivalNumTotal(arrivalNumTotal);
					p.setArrivalMoneyTotal(arithService.mul(arrivalNumTotal,p.getProcurementPrice()));
					p.setAcceptanceNumTotal(acceptanceNumTotal);
					p.setAcceptanceMoneyTotal(arithService.mul(acceptanceNumTotal,p.getProcurementPrice()));
					aservice.addArrival(a);
				}
			}
		}
		ServletActionContext.getRequest().setAttribute("ccflag", "success");
		return SUCCESS;
	}
	public String deleteArrivalItems() throws Exception {
		aservice.deleteArrivalItems(aid);
		this.toAddArrival();
		return SUCCESS;
	}
	public String cfArrivalItems() throws Exception {
		aservice.addcfArrivalItems(aid, arrivalNum);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
}
