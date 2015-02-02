/**
 * 
 */
package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.ProcurementCheck;
import com.shtz.util.PageModel;

/**
 * Filename : ProcurementCheckService.java
 *
 * @author yao chang
 *
 * Creation time : 下午10:28:34 - 2013-4-21
 *
 */
public interface ProcurementCheckService {
	public void addProcurementCheck(ProcurementCheck pc);
	public void addPlan(String ids,int pcId);
	public void modifyProcurementCheckD(ProcurementCheck pc,String ids);
	public void modifyProcurementCheckI(ProcurementCheck pc,String ids);
	public void modifyProcurementCheckM(ProcurementCheck pc,String ids);
	public void modifyProcurementCheckC(ProcurementCheck pc,String ids);
	public void modifyProcurementCheck(ProcurementCheck pc,String ids);
	public int getNextProcurementCheckNum();
	public ProcurementCheck getProcurementCheck(int id);
	public List<ProcurementCheck> getPCs(String ids);
	public PageModel serchProcurementCheckByParams(Map params,int offset, int pageSize);
	public void modifyProcurementCheck(ProcurementCheck p);
	public void modifyAddSeekSource(int id);
	public String checkPCStatus(String ids,String needStatus);
	public Integer getPCCountByPersonId(int personId);
	public String getMaxCaseNum(String head);
	public void modifyProcurementCheckGroup(ProcurementCheck p);
	public void modifyProcurementOperatorByIds(String tags,int operatorId);
	public void modifyPlansByIdsBack(String ids);
	public void modifyPcFinishSeekSource(String ids);

	public Integer getPCCountByRole(int personId,String role);
}
