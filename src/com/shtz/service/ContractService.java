package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.ContractCredit;
import com.shtz.model.Organization;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementContract;
import com.shtz.model.SalesContract;
import com.shtz.model.Suppliers;
import com.shtz.model.UseComp;
import com.shtz.util.PageModel;


/**
 * @author sjm
 *  
 */
public interface ContractService {
	public PageModel searchContracts(int offset, int pageSize);
	public double searchContractcreditMoney(int id);
	public List<Plan> getPlans(int procurementContractId);
	public ProcurementContract findProcurementContractById(int procurementContractId);
	public List<Organization> findAllOrganization();
	public List<Suppliers> findAllSuppliers();
	public List<ContractCredit> findContractCreditByPid(int pid);
	public void deleteContractCredit(int id);
	public void modifyProcurementContract(ProcurementContract pc);
	public void modifyplanpid(int planId);
	public void deleteProcurementContract(int pcontractId);
	public void addProcurementContract(ProcurementContract pc);
	public void addOrUpdateContractCredit(ContractCredit cc,int ccid);
	
	public List<ContractCredit> findContractCreditById(int pid);
	public ContractCredit findContractCreditByIds(int id);
	public PageModel searchContracts(Map params,int offset, int pageSize);
	
	public PageModel searchContracts1(Map params,int offset, int pageSize);
	
	public PageModel getProcurementList(int offset,int pageSize,Map params,boolean isLimit);

	
}
