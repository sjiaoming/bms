package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.ContractCredit;
import com.shtz.model.ProcurementContract;
import com.shtz.util.PageModel;


/**
 * @author sjm
 *  
 */
public interface ProcurementContractService {
	
	public PageModel searchPlans(int offset, int pageSize);

	public void addProcurementContract(ProcurementContract procurementContract);
	
	public void addContractCredit(ContractCredit contractCredit);
	
	public ProcurementContract findProcurementContractById(int id);
	
	public PageModel serchsearchPlansByParams(Map params,int offset, int pageSize);
	
	public List getPlanArrival(int planId);
	
	public List<?> findProcurementContractByContractNum(String contractNum);
	
	public List<?> findArrivalsIdByPlanids(String ids);
	
	public String testdwr();

	public Integer getPCCountByPersonId(int personId);
}
