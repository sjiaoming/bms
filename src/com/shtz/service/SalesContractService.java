package com.shtz.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shtz.model.Billing;
import com.shtz.model.BillingDetail;
import com.shtz.model.Organization;
import com.shtz.model.Plan;
import com.shtz.model.SalesContract;
import com.shtz.model.UseComp;
import com.shtz.util.PageModel;


/**
 * @author sjm
 *  
 */
public interface SalesContractService {
	
	public PageModel searchPlans(int offset, int pageSize);
	public List<Plan> findCheckedPlan(String[] tags);
	public List<UseComp> findAllUseComp();
	public void modifyPlanSales(Plan p);
	public void addOrModifySalesContract(SalesContract salesContract,String[] addmoney,String[] addmoneyDate,String[] addmoney1,String[] payedStyle);
	public void addOrModifySalesContract(SalesContract salesContract,String[] addmoney1,String[] payedStyle);
	public SalesContract findSalesContractByContractNum(String contractNum);
	public PageModel searchsalesContracts(int offset, int pageSize);
	public List<Plan> getPlans(int salesContractId);
	public void modifyplansid(int planid);
	public SalesContract findSalesContractById(int salesContractId);
	public void deleteSalesContract(int salesContractId);
	
	public PageModel serchsearchPlansByParams(Map params,int offset, int pageSize);
	public PageModel serchsearchPlansByParams1(Map params,int offset, int pageSize);
	public PageModel serchsearchSalesByParams(Map params,int offset, int pageSize);
	
	public List<Organization> findAllOrganization();
	public List<Billing> findAllBillingBySalesContract(SalesContract sc);
	
	public void deleteBilling(int id);
	public void modifySalesContract(SalesContract salesContract);
	public SalesContract addSalesContract(SalesContract salesContract);
	
	public List<?> findSalesContractListByContractNum(String contractNum);
	public PageModel getSalesContractList(int offset,int pageSize,Map params,boolean isLimit);
	public Billing addBilling(double billingMoney,Date billingDate,SalesContract salesContract);
	public Billing getBillingById(int id);
	public void modifyBilling(Billing bill);
	
	public void addBillingDetail(BillingDetail b);
	
	public BillingDetail getBillingDetailById(int id);
	public void deleteBillingDetail(int id);
	
	public Integer getSCCountByPersonId(int personId);
}
