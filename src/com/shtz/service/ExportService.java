package com.shtz.service;

import java.util.List;

public interface ExportService {
	public List<List<String>> findSelectField(int[] msg,int orgId,String name);
	public List<List<String>> findSalesContract(int orgId,String contractNum,String auth);
	public List<List<String>> findPlanSheet(int orgId,String oldPlanNum,String auth);
	public List<List<String>> findProcurementContract(int orgId,String name);
}
