package com.shtz.action;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementArrivalBean;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.ProcurementContractBean;
import com.shtz.model.ReportCompStatisticsBean;
import com.shtz.model.SalesContractArrivalBean;
import com.shtz.model.Suppliers;
import com.shtz.service.ContractService;
import com.shtz.service.ExportService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.SalesContractService;
import com.shtz.service.SuppliersService;
import com.shtz.util.ExcelCreate;
import com.shtz.util.ExcelRead;
import com.shtz.util.PageModel;
import com.shtz.util.StatusName;

/**
 * @author sjm
 * 
 */
public class ExportAction extends ActionSupport {

	private ExcelCreate excelCreateService;

	private ExcelRead ExcelReadService;

	private ExportService eservice;

	private String importfilename;

	private SuppliersService supplierService;
	private PlanService pservice;
	
	private SalesContractService service;
	
	private ContractService cservice;
	
	private ProcurementCheckService pcService;
	
	private StatusName statusName;
	
	private String planNum;
	
	private Integer pcId;
	
	
	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}

	public void setSupplierService(SuppliersService supplierService) {
		this.supplierService = supplierService;
	}

	public SalesContractService getService() {
		return service;
	}

	public ContractService getCservice() {
		return cservice;
	}

	public StatusName getStatusName() {
		return statusName;
	}

	public String getPlanNum() {
		return planNum;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	public void setStatusName(StatusName statusName) {
		this.statusName = statusName;
	}

	public void setService(SalesContractService service) {
		this.service = service;
	}

	public void setCservice(ContractService cservice) {
		this.cservice = cservice;
	}
	private int[] msg;
	
	private String result;
	//查询部门号
	private int orgId;
	//查询名称
	private String name;
	
	//查询名称
	private String contractNum;
	
	private String oldPlanNum;
	
	private Integer useCompId;
	 
	 private Integer suppliersId;
	 
	 private String procurementWay;
	 
	 private String contractExecutionWay;
	 
	 private String  sDate;
	 
	 private String eDate;
	
	 
	 
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

	public String getProcurementWay() {
		return procurementWay;
	}

	public void setProcurementWay(String procurementWay) {
		this.procurementWay = procurementWay;
	}

	public String getContractExecutionWay() {
		return contractExecutionWay;
	}

	public void setContractExecutionWay(String contractExecutionWay) {
		this.contractExecutionWay = contractExecutionWay;
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

	public ExcelCreate getExcelCreateService() {
		return excelCreateService;
	}

	public ExcelRead getExcelReadService() {
		return ExcelReadService;
	}

	public ExportService getEservice() {
		return eservice;
	}

	public PlanService getPservice() {
		return pservice;
	}

	public String getOldPlanNum() {
		return oldPlanNum;
	}

	public void setOldPlanNum(String oldPlanNum) {
		this.oldPlanNum = oldPlanNum;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setEservice(ExportService eservice) {
		this.eservice = eservice;
	}

	public void setPservice(PlanService pservice) {
		this.pservice = pservice;
	}

	public int[] getMsg() {
		return msg;
	}

	public void setMsg(int[] msg) {
		this.msg = msg;
	}

	public String getImportfilename() {
		return importfilename;
	}

	public void setImportfilename(String importfilename) {
		this.importfilename = importfilename;
	}

	public void setExcelReadService(ExcelRead excelReadService) {
		ExcelReadService = excelReadService;
	}

	public void setExcelCreateService(ExcelCreate excelCreateService) {
		this.excelCreateService = excelCreateService;
	}

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			String fname = "test";// Excel文件名
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="
					+ fname + ".xls");// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// ------
			// 读取excel并计算
			System.out.println(msg.length);
			System.out.println(msg.toString());
			// ExcelReadService.importExcel(importfilename);
			// List<List<String>> allList=new ArrayList<List<String>>();
			// int rowcount=ExcelReadService.getRowIndex(0);
			List<String> headList = new ArrayList<String>();
			for (int j = 1; j <= msg.length; j++) {
				switch (msg[j]) {
				case 1:
					headList.add("物资名称");
					break;
				case 2:
					headList.add("规格型号");
					break;
				case 3:
					headList.add("原计划号");
					break;
				case 4:
					headList.add("使用单位");
					break;
				case 5:
					headList.add("计划编号");
					break;
				case 6:
					headList.add("计划接收日期");
					break;
				case 7:
					headList.add("单位");
					break;
				case 8:
					headList.add("数量");
					break;
				case 9:
					headList.add("到货时间");
					break;
				case 10:
					headList.add("到货地点");
					break;
				case 11:
					headList.add("预算金额");
					break;
				case 12:
					headList.add("采购状态");
					break;
				case 13:
					headList.add("备注");
					break;
				case 14:
					headList.add("采购方案");
					break;
				case 15:
					headList.add("采购签报日期");
					break;
				case 16:
					headList.add("采购方案备注");
					break;
				case 17:
					headList.add("寻源公布日期");
					break;
				case 18:
					headList.add("寻源签报日期");
					break;
				case 19:
					headList.add("合同执行方式");
					break;
				case 20:
					headList.add("合同金额");
					break;

				case 22:
					headList.add("采购单价");
					break;
				case 23:
					headList.add("采购金额");
					break;
				case 24:
					headList.add("销售金额");
					break;
				case 25:
					headList.add("销售比率");
					break;
				// pid
				case 26:
					headList.add("采购合同序号");
					break;
				case 27:
					headList.add("合同编号");
					break;
				case 28:
					headList.add("供应商id");
					break;
				case 29:
					headList.add("供应商name");
					break;
				case 30:
					headList.add("合同执行方式");
					break;
				case 31:
					headList.add("预付款");
					break;
				case 32:
					headList.add("业务id");
					break;
				case 33:
					headList.add("业务部门名称");
					break;
				case 34:
					headList.add("合同签订日期");
					break;
				case 35:
					headList.add("合同到货日期");
					break;
				case 36:
					headList.add("合同总额");
					break;
				case 37:
					headList.add("采购方案");
					break;
				case 38:
					headList.add("合同质保金额");
					break;
				case 39:
					headList.add("合同质保日期");
					break;
				case 40:
					headList.add("合同未付金额");
					break;
				case 41:
					headList.add("合同应付金额");
					break;
				case 42:
					headList.add("合同备注");
					break;
				case 43:
					headList.add("合同已付金额");
					break;
				// 挂账
				case 44:
					headList.add("合同挂账金额");
					break;
				case 45:
					headList.add("合同挂账日期");
					break;
				}
			}
			// 写入excel
			excelCreateService.createSheet("报表");
			excelCreateService.addHeader(headList, true);
			List<List<String>> rowList = eservice.findSelectField(msg,orgId,name);
			for (int i = 1; i < rowList.size(); i++) {
				excelCreateService.addRow(rowList.get(i));
			}
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(now);
			File file = new File("D:\\报表" + date + ".xls");
			excelCreateService.exportExcel(file);

			// --------
		} catch (Exception e) {
			System.out.println(e);
		}
		return "export_success";
	}

	// 需求提报单位统计表
	@SuppressWarnings("unchecked")
	public String exportReportComp() throws Exception {
		
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("部门");
		headList.add("提报单位");
		headList.add("采购计划接收金额");
		headList.add("公开招标");
		headList.add("邀请招标");
		headList.add("竞争性谈判");
		headList.add("单一来源");
		headList.add("询比价");
		headList.add("合计");
		headList.add("节支");
		headList.add("采购合同份数");
		headList.add("采购合同金额");
		headList.add("销售合同份数");
		headList.add("销售合同金额");
		headList.add("到货金额");
		headList.add("回款金额");
		
		ExcelCreate s = new ExcelCreate();
		s.createSheet("需求提报单位统计表");
		s.addHeader(headList, true);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_oid", orgId);
		if(planNum!=null && !"".equals(planNum.trim()))
			params.put("planNum", planNum);
		if(useCompId!=null)
			params.put("id_reportCompId", useCompId);
		PageModel  pm = pservice.exportReportComp(0, 0, params, false);
		List<ReportCompStatisticsBean> l = pm.getList();
		HSSFCellStyle dtStyle  = s.setdtStyle();
		HSSFCellStyle dateStyle = s.setdateStyle();
		for (int i = 0; i < pm.getList().size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add("" );
			list.add(l.get(i).getReportCompName());
			list.add("");
			list.add(l.get(i).getGongkai() );
			list.add(l.get(i).getYaoqing() );
			list.add(l.get(i).getJingzheng() );
			list.add(l.get(i).getDanyi() );
			list.add(l.get(i).getXunbijia() );
			list.add(l.get(i).getSum());
			list.add(l.get(i).getSavedMoney() );
			list.add(l.get(i).getProcumentCount() );
			list.add(l.get(i).getProcurementMoney() );
			list.add(l.get(i).getSalesCount() );
			list.add(l.get(i).getSaleMoney());
			list.add(l.get(i).getArrivalMoney() );
			list.add(l.get(i).getReceviedMoney() );
//			list.add(l.get(i).getPersonName() );
			s.addRow(list,dtStyle,dateStyle);
		}
		Date now = new Date();
		
		String date = sdf.format(now);
		String fileName ="需求提报单位统计表" + date + ".xls";
		result=ExportAction.exportExcel(fileName, s);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String planSheet() throws Exception {
		/*
		 * HttpServletResponse response=ServletActionContext.getResponse(); try{
		 * 
		 * Date now=new Date(); SimpleDateFormat sdf=new
		 * SimpleDateFormat("yyyy-MM-dd"); String date=sdf.format(now); //File
		 * file = new File("D:\\采购合同报表"+date+".xls"); //String fname =
		 * "test";//Excel文件名 OutputStream os =
		 * response.getOutputStream();//取得输出流 response.reset();//清空输出流
		 * response.setHeader("Content-disposition", "attachment; filename="
		 * +"test"+date+".xls");//设定输出文件头
		 * //response.setContentType("application/octet-stream");//定义输出类型
		 * response.setContentType("application/msexcel");//定义输出类型
		 * response.setCharacterEncoding("utf-8"); //设置响应头为二进制流
		 * //response.setContentType( "application/x-msdownload;charset=utf-8");
		 * response.setCharacterEncoding("utf-8"); BufferedOutputStream buffout
		 * = new BufferedOutputStream(os); //workbook.write(buffout);
		 * buffout.flush(); buffout.close();
		 * 
		 * 
		 * }catch(Exception e){ System.out.println(e); }
		 */

		// 写入excel
		// 表头
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("计划编号");
		headList.add("提报单位");
		headList.add("物资类别");
		headList.add("物资代码");
		headList.add("设备名称及位号");
		headList.add("物资名称");
		headList.add("规格型号");
		headList.add("业务组");
		headList.add("材质");
		headList.add("标准号");
		headList.add("单位");
		headList.add("数量");
		headList.add("预算单价（元）");
		headList.add("预算金额（元）");
		headList.add("物资需求部门");
		headList.add("要求到货日期");
		headList.add("检修项目编号");
		headList.add("用途");
		headList.add("原厂商");
		headList.add("备注");
		headList.add("采购方案名称");
		headList.add("采购方式");
		headList.add("供应商");
		headList.add("寻源单价");
		headList.add("寻源金额");
		headList.add("销售合同编号");
		headList.add("销售合同名称");
		headList.add("销售合同金额");
		headList.add("采购合同编号");
		headList.add("采购合同名称");
		headList.add("采购合同金额");
		headList.add("到货数量");
		headList.add("到货金额");
		headList.add("验收数量");
		headList.add("验收金额");
		headList.add("操作人");
		ExcelCreate s = new ExcelCreate();
		s.createSheet("计划执行明细表");
		s.addHeader(headList, true);
		//User u=(User) ServletActionContext.getRequest().getSession().getAttribute("login");
		//List<List<String>> rowList = eservice.findPlanSheet(orgId,oldPlanNum,u.getAuth());
		// ExcelCreate workbook=new ExcelCreate();
		
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_oid", orgId);
		if(planNum!=null && !"".equals(planNum.trim()))
			params.put("planNum", planNum);
		if(useCompId!=null)
			params.put("id_reportCompId", useCompId);
		if(procurementWay!=null && !"".equals(procurementWay.trim()) && !("0".equals(procurementWay.trim())))
			params.put("id_pc.procurementWay", procurementWay);
//		if(contractExecutionWay!=null && !"".equals(contractExecutionWay.trim()) && !("0".equals(contractExecutionWay.trim())))
//			params.put("id_contractExecutionWay", contractExecutionWay);
		if(sDate!=null && !"".equals(sDate.trim())  && eDate!=null && !"".equals(eDate.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+sDate+"' and '"+eDate+"'");
			//params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(suppliersId!=null)
			params.put("dy_p.procurementContract.suppliersId", "="+suppliersId+"  ");
		//params.put("dy_p.model", "<> '02'");
//		if (personName != null && !personName.equals("")) {
//			params.put("dy_p.person.name", " like '%" + personName.trim() + "%'");
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		PageModel  pm = pservice.getPlanStatusList(0, 0, params, false);
		// ExcelCreate workbook=new ExcelCreate();
		List<ProcurementContractBean> l = pm.getList();
		HSSFCellStyle dtStyle  = s.setdtStyle();
		HSSFCellStyle dateStyle = s.setdateStyle();
		for (int i = 0; i < pm.getList().size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add(l.get(i).getPlanNum() );
			list.add(l.get(i).getReportCompName());
			list.add(l.get(i).getItemType());
			list.add(l.get(i).getItemsNum() );
			list.add(l.get(i).getFacilityNameAndNum() );
			list.add(l.get(i).getItemsName() );
			list.add(l.get(i).getModelType() );
			list.add(l.get(i).getGroupName() );
			list.add(l.get(i).getMaterial() );
			list.add(l.get(i).getStandardNum() );
			list.add(l.get(i).getUnit() );
			list.add(l.get(i).getQuantity() );
			list.add(l.get(i).getBudgetPrice() );
			list.add(l.get(i).getBudgetMoney() );
			list.add(l.get(i).getDemandDept());
			if(l.get(i).getArrivalDate() !=null)
				list.add(sdf.format(l.get(i).getArrivalDate()));
			else
				list.add("");
			list.add(l.get(i).getCheckItemNum() );
			list.add(l.get(i).getUseFor() );
			list.add(l.get(i).getOriginComp());
			list.add(l.get(i).getRemark());
			list.add(l.get(i).getProcurementName());
			list.add(l.get(i).getProcurementWay() );
			list.add(l.get(i).getSuppliersName() );
			list.add(l.get(i).getProcurementPrice() );
			list.add(l.get(i).getProcurementMoney() );
			list.add(l.get(i).getSaleContractNum());
			list.add(l.get(i).getSaleContractName());
			list.add(l.get(i).getSaleContractMoney());
			list.add(l.get(i).getContractNum() );
			list.add(l.get(i).getContractName());
			list.add(l.get(i).getTotalMoney() );
			list.add(l.get(i).getArrivalNum() );
			list.add(l.get(i).getArrivalMoney() );
			list.add(l.get(i).getAcceptanceNum() );
			list.add(l.get(i).getAcceptanceMoney() );
			list.add(l.get(i).getPersonName() );
			s.addRow(list,dtStyle,dateStyle);
		}
		Date now = new Date();
		
		String date = sdf.format(now);
		String fileName ="计划执行明细表" + date + ".xls";
		result=ExportAction.exportExcel(fileName, s);

		return null;
	}

	// 销售合同报表生成
	@SuppressWarnings("unchecked")
	public String exportSalesContract() throws Exception {	
		// 写入excel
		List<String> headList = new ArrayList<String>();
		headList.add("系统ID");
		headList.add("销售合同编号");
		headList.add("销售合同名称");
		headList.add("合同金额");
		headList.add("合同签订时间");
		headList.add("合同签订单位");
		headList.add("供应商");
		headList.add("合同质保金额");
		headList.add("合同质保日期");
		headList.add("合同开票金额");
		headList.add("合同已收金额");
		headList.add("合同应收金额");
		headList.add("备注");
		ExcelCreate s = new ExcelCreate();
		s.createSheet("销售合同报表");
		s.addHeader(headList, true);
		
		Map params = new HashMap();
		//params.put("in_planStatus", "04");
		if(orgId!=0){
			params.put("id_orgId", orgId);
		}
		if( contractNum!=null && !contractNum.equals("") ){
			params.put("contractNum", contractNum);
		}
		if(useCompId!=null)
			params.put("id_reportCompId_sc", useCompId);
		if(sDate!=null && !"".equals(sDate.trim())  && eDate!=null && !"".equals(eDate.trim()) ){
			params.put("dy_p.signingDate", " between '"+sDate+"' and '"+eDate+"'");
		}
		PageModel pm = service.getSalesContractList(0,0,params,false);
		List<SalesContractArrivalBean> l = pm.getList();
//		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("login");
//		List<List<String>> rowList = eservice.findSalesContract(orgId,contractNum,u.getAuth());
//		for (int i = 0; i < rowList.size(); i++) {
//			s.addRow(rowList.get(i));
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFCellStyle dtStyle  = s.setdtStyle();
		HSSFCellStyle dateStyle = s.setdateStyle();
		for (int i = 0; i < pm.getList().size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add(l.get(i).getContractNum() );
			list.add(l.get(i).getSalesContractName());
			list.add(l.get(i).getContractMoney() );
			if(l.get(i).getSigningDate() !=null)
				list.add(sdf.format(l.get(i).getSigningDate()));
			else
				list.add("");
			list.add(l.get(i).getSignComp() );
			list.add("");
			list.add(l.get(i).getQualityMoney());
			if(l.get(i).getQualityDate() !=null)
				list.add(sdf.format(l.get(i).getQualityDate()));
			else
				list.add("");
			list.add(l.get(i).getTotalContractInvoiceMoney() );
			list.add(l.get(i).getTotalcontractReceivedMoney());
			list.add(l.get(i).getContractReceivableMoney());
//			list.add(l.get(i).getArrivalMoney() );
//			list.add(l.get(i).getAcceptanceMoney() );
//			list.add(l.get(i).getPerson().getName() );
			s.addRow(list,dtStyle,dateStyle);
		}
		Date now = new Date();
		String date = sdf.format(now);
		String fileName ="销售合同报表" + date + ".xls";
		//excelCreateService.exportExcel(file);
		result=ExportAction.exportExcel(fileName, s);
		return null;
	}

	// 采购合同报表生成
	@SuppressWarnings("unchecked")
	public String exportProcurementContract() throws Exception {
		// 写入excel
		List<String> headList = new ArrayList<String>();
		headList.add("系统ID");
		headList.add("采购合同编号");
		headList.add("采购合同名称");
		headList.add("合同金额");
		headList.add("合同签订日期");
		headList.add("采购方案");
		headList.add("供应商");
		headList.add("合同质保金额");
		headList.add("合同质保日期");
		headList.add("预付金额");
		headList.add("合同到货日期");
		headList.add("挂帐总金额");
		headList.add("挂帐日期");
		headList.add("已付总金额");
		headList.add("应付金额");
		headList.add("备注");
//		headList.add("计划提报单位");
//		headList.add("合同执行方式");
//		headList.add("到货金额");
//		headList.add("验收金额");
//		headList.add("部门");
//		headList.add("操作人");
		ExcelCreate s = new ExcelCreate();
		s.createSheet("采购合同报表");
		s.addHeader(headList, true);
//		List<List<String>> rowList = eservice.findProcurementContract(orgId,contractNum);
//		for (int i = 0; i < rowList.size(); i++) {
//			s.addRow(rowList.get(i));
//		}
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_orgId", orgId);
		if(contractNum!=null&&!"".equals(contractNum.trim()))
			params.put("contractNum", contractNum);
		if(useCompId!=null)
			params.put("id_reportCompId_pc", useCompId);
		if(sDate!=null && !"".equals(sDate.trim())  && eDate!=null && !"".equals(eDate.trim()) ){
			params.put("dy_p.signingDate", " between '"+sDate+"' and '"+eDate+"'");
		}
		//PageModel pm = service.searchContracts(params,offset, pageSize);
		PageModel pm = cservice.getProcurementList(0,0, params, false);
		List<ProcurementArrivalBean> l=pm.getList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HSSFCellStyle dtStyle  = s.setdtStyle();
		HSSFCellStyle dateStyle = s.setdateStyle();
		for (int i = 0; i < l.size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add(l.get(i).getContractNum() );
			list.add(l.get(i).getContractName());
			list.add(l.get(i).getTotalMoney());
			if(l.get(i).getSigningDate() !=null)
				list.add(sdf.format(l.get(i).getSigningDate()));
			else
				list.add("");
			if(l.get(i).getProcurementWay() !=null)
				list.add(statusName.getProcurementWay(l.get(i).getProcurementWay()) );
			else
				list.add("");
			list.add(l.get(i).getSuppliersName() );
			list.add(l.get(i).getQualityMoney());
			if(l.get(i).getQualityDate() !=null)
				list.add(sdf.format(l.get(i).getQualityDate()));
			else
				list.add("");
			list.add(l.get(i).getAdvance());
			if(l.get(i).getArrivalDate() !=null)
				list.add(sdf.format(l.get(i).getArrivalDate()));
			else
				list.add("");
			list.add(l.get(i).getCreditMoney() );
			if(l.get(i).getCreditDate() !=null)
				list.add(sdf.format(l.get(i).getCreditDate()));
			else
				list.add("");
//			list.add(l.get(i).getPayed() );
			double temp=0;
			if(l.get(i).getPayed()!=null){
				String a = "&";
				String[] str = l.get(i).getPayed().split(",");
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
			list.add(temp);
			list.add(l.get(i).getShouldPayment());
			list.add(l.get(i).getRemark());
			/*double t=cservice.searchContractcreditMoney(l.get(i).getId());
			list.add(t);
			list.add(l.get(i).getArrivalMoney() );
			list.add(l.get(i).getAcceptanceMoney() );
			list.add(l.get(i).getOrgName());
			list.add(l.get(i).getPerson().getName() );*/
			s.addRow(list,dtStyle,dateStyle);
		}
		Date now = new Date();
		String date = sdf.format(now);
		String fileName ="采购合同报表" + date + ".xls";
		//excelCreateService.exportExcel(file);
		result=ExportAction.exportExcel(fileName, s);
		return null;
	}
	public String exportSeekSourcePlans(){
		
	    ProcurementCheck pc = pcService.getProcurementCheck(pcId);
	    
		List<String> headList = new ArrayList<String>();
//		headList.add("序号");
		headList.add("计划编号");
		headList.add("计划行号");
//		headList.add("物资类别");
//		headList.add("物资代码");
		headList.add("物资名称");
		headList.add("规格型号");
//		headList.add("单位");
		headList.add("数量");
		headList.add("单价含17%增值税(元)");
		headList.add("总价含17%增值税(元)");
//		headList.add("物资需求部门");
		headList.add("要求到货日期");
//		headList.add("用途");
//		headList.add("原厂商");
//		headList.add("备注");
		String memo = "备注：报价说明:  \n" +
						"1、报价含17%增值税；含包装费用；含送达使用地点的运输费用；含技术服务费用 。报价时间：2014年6月7日15:00点前  \n   " +
						"2、供应商在报价过程中如出现有关询价物资规格参数不全等问题，需在报价单缺项处给予说明，同时另发书面文字加盖公章加以确认。        ";
		ExcelCreate s = new ExcelCreate();
		HSSFSheet sheet = s.createSheet1("询价单");
		HSSFFont headfont = s.wb.createFont();   
		HSSFCellStyle headstyle = s.wb.createCellStyle();   
		HSSFCellStyle dateStyle = s.wb.createCellStyle();   
	    headstyle.setFont(headfont);   
	    headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
	    headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
	    headstyle.setLocked(true);   
	    headstyle.setWrapText(true);// 自动换行   
		HSSFCellStyle dtStyle  = s.setdtStyle();
		dateStyle.setFont(headfont);   
		dateStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左右居中   
		dateStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
		dateStyle.setLocked(true);   
		dateStyle.setWrapText(true);// 自动换行   
      // 创建第一行   
      HSSFRow row0 = sheet.createRow(0);   
      // 设置行高   
      row0.setHeight((short) 700);   
      // 创建第一列   
      HSSFCell cell0 = row0.createCell(0);   
      cell0.setCellValue(new HSSFRichTextString("港华泓通贸易（深圳）有限公司2014年物资采购询价单"));  
      CellRangeAddress range0 = new CellRangeAddress(0, 0, 0, 7); 
      sheet.addMergedRegion(range0);  
      cell0.setCellStyle(headstyle); 
      
      HSSFRow row1 = sheet.createRow(1);   
      row1.setHeight((short) 500);   
      HSSFCell cell1 = row1.createCell(0);   
//      Calendar c = Calendar.getInstance();
//      int year = c.get(c.YEAR);
//      c = null; 
      cell1.setCellValue(new HSSFRichTextString("编制单位：　                                               计划类别：                                                  计划编号：                                     "));  
      CellRangeAddress range1 = new CellRangeAddress(1, 1, 0, 7);   
      sheet.addMergedRegion(range1);    
      cell1.setCellStyle(dateStyle);   
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      
      HSSFRow row2 = sheet.createRow(2);   
      row2.setHeight((short) 500);   
      HSSFCell cell2 = row2.createCell(0);   
      Calendar c = Calendar.getInstance();
      int year = c.get(c.YEAR);
      c = null; 
      List<Suppliers> ss = supplierService.findSuppliersByIds(pc.getSupplierIds());
      String supplierNames = "";
      for(Suppliers su : ss){
    	  supplierNames = supplierNames +","+ su.getName();
      }
      supplierNames = supplierNames.substring(1);
      cell2.setCellValue(new HSSFRichTextString("供应商： "+supplierNames));  
      CellRangeAddress range2 = new CellRangeAddress(2, 2, 0, 3);   
      sheet.addMergedRegion(range2);    
      cell2.setCellStyle(dateStyle);   
//	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      
      HSSFCell cell2_2 = row2.createCell(4);  
      cell2_2.setCellValue(new HSSFRichTextString(year+"年       月           日"));  
      CellRangeAddress range2_2 = new CellRangeAddress(2, 2, 4, 7);   
      sheet.addMergedRegion(range2_2);    
      cell2_2.setCellStyle(dateStyle);   

	  try {
		s.addHeaderByPosition(headList, false,3);
	  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	  }

//      int row = 1;
	  int count = 4;
	  for(Plan p : pc.getPlans()){
		List list = new ArrayList();
//		list.add(p.getId());
		list.add(p.getPlanNum());
		list.add(p.getPlanRowNum());
//		list.add(p.getItemType());
//		list.add(p.getItemsNum());
		list.add(p.getItemsName());
		list.add(p.getModelType());
//		list.add(p.getUnit());
		list.add(p.getQuantity());
		list.add("");
		list.add("");
//		list.add(p.getDemandDept());
		if(p.getArrivalDate() != null){
			list.add(sdf.format(p.getArrivalDate()));
		}else{
			list.add("");
		}
//		list.add(p.getUseFor());
//		list.add(p.getOriginComp());
//		list.add(p.getRemark());
		//add row value start
		HSSFRow dtRow = sheet.createRow(count);
//		DataFormat format = s.wb.createDataFormat();
		
		for (int j = 0; j < list.size(); j++) {
			String flag = "";
			Object cell_data = list.get(j);
			HSSFCell cell = dtRow.createCell(j);
			// 正文格式
			if (cell_data instanceof String) {
				flag = "string";
				cell.setCellValue((String)cell_data);
			}
			else if (cell_data instanceof Double) {
				cell.setCellValue((Double) cell_data);
			} 
			else if (cell_data instanceof Integer) {
				cell.setCellValue(Double.valueOf(String.valueOf(cell_data)));
			} 	
			else if (cell_data instanceof Date) {
				flag = "date";
				cell.setCellValue((Date) cell_data);
			} 
			else if (cell_data instanceof Boolean) {
				cell.setCellValue((Boolean) cell_data);
			}else if (cell_data instanceof Float) {
				cell.setCellValue((Float) cell_data);
			}
			// 背景颜色
//			if(s%2!=0)
//			dtStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
//			dtStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			if(flag==""||flag.equals("string")){
				cell.setCellStyle(dtStyle);
			}else if(flag.equals("date")){
				cell.setCellStyle(dateStyle);
			}
			
		}
		//add row end
		
//		s.addRow(list,dtStyle,dateStyle);
		count++;
	  }
      HSSFRow rowX = sheet.createRow(count);   
      rowX.setHeight((short) 500);   
      HSSFCell cellX = rowX.createCell(0);  
      cellX.setCellValue(new HSSFRichTextString("               合计          "));  
      CellRangeAddress rangeX = new CellRangeAddress(count, count, 0, 3);   
      sheet.addMergedRegion(rangeX);    
      cellX.setCellStyle(dateStyle);   
      count++;
      HSSFRow rowMemo = sheet.createRow(count);   
      rowMemo.setHeight((short) 1000);   
      HSSFCell cellMemo = rowMemo.createCell(0);  
      cellMemo.setCellValue(new HSSFRichTextString(memo));  
      CellRangeAddress rangeMemo = new CellRangeAddress(count, count, 0, 7);   
      sheet.addMergedRegion(rangeMemo);    
      cellMemo.setCellStyle(dateStyle);   
      count++;
      
      HSSFRow rowGSMC = sheet.createRow(count);   
      rowGSMC.setHeight((short) 400);   
      HSSFCell cellGSMC = rowGSMC.createCell(0);  
      cellGSMC.setCellValue(new HSSFRichTextString("港华泓通贸易（深圳）有限公司"));  
      CellRangeAddress rangeGSMC = new CellRangeAddress(count, count, 0, 3);   
      sheet.addMergedRegion(rangeGSMC);    
      cellGSMC.setCellStyle(dateStyle);   
      
      HSSFCell cellTBDW = rowGSMC.createCell(4);  
      cellTBDW.setCellValue(new HSSFRichTextString("  报价单位："));  
      CellRangeAddress rangeTBDW = new CellRangeAddress(count, count, 4, 7);   
      sheet.addMergedRegion(rangeTBDW);    
      cellTBDW.setCellStyle(dateStyle);   
      count++;
      
      
      HSSFRow rowLXR = sheet.createRow(count);   
      rowLXR.setHeight((short) 400);   
      HSSFCell cellLXR = rowLXR.createCell(0);  
      Person p = (Person)ServletActionContext.getRequest().getSession().getAttribute("person");
      cellLXR.setCellValue(new HSSFRichTextString(" 联系人："+p.getName()+"    电话："+p.getPhone()));  
      CellRangeAddress rangeLXR = new CellRangeAddress(count, count, 0, 3);   
      sheet.addMergedRegion(rangeLXR);    
      cellLXR.setCellStyle(dateStyle);   
      
      HSSFCell cellLXR2 = rowLXR.createCell(4);  
      cellLXR2.setCellValue(new HSSFRichTextString("  联系人：                            电话：                           "));  
      CellRangeAddress rangeLXR2 = new CellRangeAddress(count, count, 4, 7);   
      sheet.addMergedRegion(rangeLXR2);    
      cellLXR2.setCellStyle(dateStyle);   
      count++;

      HSSFRow rowYX = sheet.createRow(count);   
      rowYX.setHeight((short) 400);   
      HSSFCell cellYX = rowYX.createCell(0);  
      cellYX.setCellValue(new HSSFRichTextString(" 邮箱："+p.getEmail()));  
      CellRangeAddress rangeYX = new CellRangeAddress(count, count, 0, 3);   
      sheet.addMergedRegion(rangeYX);    
      cellYX.setCellStyle(dateStyle);   
      
      HSSFCell cellYX2 = rowYX.createCell(4);  
      cellYX2.setCellValue(new HSSFRichTextString("  邮箱："));  
      CellRangeAddress rangeYX2 = new CellRangeAddress(count, count, 4, 7);   
      sheet.addMergedRegion(rangeYX2);    
      cellYX2.setCellStyle(dateStyle);   

	  Date now = new Date();
	  String date = sdf.format(now);
	  String fileName ="询价单" + date + ".xls";
	  result=ExportAction.exportExcel(fileName, s);
	  
	  return SUCCESS;
	}
	public final static String exportExcel(String fileName, ExcelCreate s) {
		String result = "系统提示：Excel文件导出成功！";
		// 以下开始输出到EXCEL
		try {
			// 定义输出流，以便打开保存对话框______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// 取得输出流
			response.reset();// 清空输出流
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// 设定输出文件头
			response.setContentType("application/msexcel");// 定义输出类型
			// 定义输出流，以便打开保存对话框_______________________end

			s.exportExcel(os);

		} catch (Exception e) {
			result = "系统提示：Excel文件导出失败，原因：" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

}
