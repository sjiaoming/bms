package com.shtz.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shtz.model.Organization;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementContract;
import com.shtz.model.ProcurementContractBean;
import com.shtz.model.SalesContract;
import com.shtz.model.User;
import com.shtz.util.PageModel;

/**
 * @author sjm
 * 
 */
public interface PlanService {

	public void addPlan(Plan plan, int orgId);

	public void deletePlan(int planId);

	public Plan findPlan(int planId);

	public PageModel searchPlans(int offset, int pageSize);
	
	public PageModel searchOperatorPlans(Map params, int offset,int pageSize);

	public PageModel searchPlansByOrg(int orgId, int offset, int pageSize);

	public void addProcurementWay(String[] tags, String procurementWay,
			Date procurementDate, String procurementRemark, String planStatus);

	public void addSeekSource(String[] tags, Map m);

	public void addOrUpdageChange(Plan p, Date changeTime, Double changenum,
			double changebudget, String changereason, double planPrice,
			String name);

	public void modifyPlanChange(String planId, Double changenum,
			double changecontractMoneydouble, double planPrice);

	public void modifyPlan(Plan plan);

	public void modifyPlanMg(Plan plan);
	
	public void modifyPlanClearSeekDate(Plan plan);

	public List getPlans(Integer[] ids);

	public void addPlans(Plan plan);

	public void modifyPlanPM(Plan plan);

	public Organization findOrgByNames(String orgName);

	public User findUserByName(String userName);

	public Person findPersonByName(String userName, Organization org);

	public PageModel serchsearchPlansByParams(Map params, int offset,
			int pageSize);

	public void modifyPlan(int pid, double salesRatio, double procurementMoney);

	public void modifyPlanSalesMoney(int pid, double salesMoney);

	public void modifySeekSource(int id, Map m);

	public int getPlanCountByDate(String s, String e, Map params);

	public int getProcurementContractCountByDate(String s, String e, Map params);

	public Double getPlanXYMoneyByDate(String s, String e, Map params);

	public int getPlanXYCountByDate(String s, String e, Map params);

	public Double getPlanDXYMoneyByDate(String s, String e, Map params);

	public int getPlanDXYCountByDate(String s, String e, Map params);

	public Double getPlanMoneyByDate(String s, String e, Map params);

	public Double getProcurementMoneyCountByDate(String s, String e, Map params);

	public Double getPlanArrivalMoneyByDate(String s, String e, Map params);

	public Double getJzByDate(String s, String e, Map params);

	public int getSalesContractCountByDate(String s, String e, Map params);

	public Double getSalesContractMoneyByDate(String s, String e, Map params);

	public Double getContractCreditMoneyByDate(String s, String e, Map params);

	public List<ProcurementContract> getProcurementsByDate(String s, String e,
			Map params);

	public List<SalesContract> getSalesContractsByDate(String s, String e,
			Map params);

	public Double getbillingMoneyByDate(String s, String e, Map params);
	
	public Long checkPlanNumAndPlanRowNum(String PlanNum, String PlanRowNum);
	
	public Plan getPlanByPlanNumAndRowNum(String PlanNum, String PlanRowNum);

	public void addcfPlan(int id, Double[] s, String cflag);

	public PageModel getPlanStatusList(int offset, int pageSize, Map params,
			boolean isLimit);
	public PageModel exportReportComp(int offset,int pageSize,Map params,boolean isLimit);

	public void modifyPlansByIds(Plan plan);

	public void modifyPlansByIdsBack(String ids);

	public void modifycancelPlans(String ids);

	public void modifyredoPlans(String ids);

	public void modifyPlanByObj(Plan p);

	public String validationToAddContract(String tags);

	public String validationToAddSalesContract(String tags);
	
	public String validationToAddSeekSource(String tags,String flag);

	public List getPlanListByOldPlanNum(String oldPlanNum, String planNum);

	public void deletePlansByIds(String ids);

	public void modifyPlansOrg(String ids, int orgId);
	
//	public PageModel getReportCompList(int offset,int pageSize,Map params,boolean isLimit);

	//add by yao
	public void modifyPlanPersonByIds(String ids,int personId,int managerId,int directorId);
	
	public void modifyPlanOperatorByIds(String ids,int personId);
	
	public void modifyPlanOpinionByIds(String ids,Plan p);
	
	public void modifyPlansPc(String ids, int proChxId);
	
	public void modifyRemovePlanPc(int id);
	
	public void modifyPlanForSplit(Plan plan);
	
	public Integer getUnGroupCountByRole(int personId,String role);
}
