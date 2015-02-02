package com.shtz.action;

import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.ProcurementArrivalBean;
import com.shtz.model.ProcurementContractBean;
import com.shtz.model.SalesContractArrivalBean;
import com.shtz.model.User;
import com.shtz.service.ContractService;
import com.shtz.service.ExportService;
import com.shtz.service.PlanService;
import com.shtz.service.SalesContractService;
import com.shtz.util.ExcelCreate;
import com.shtz.util.ExcelRead;
import com.shtz.util.PageModel;
import com.shtz.util.StatusName;

/**
 * @author sjm
 * 
 */
public class ExportAction1 extends ActionSupport {

	private ExcelCreate excelCreateService;

	private ExcelRead ExcelReadService;

	private ExportService eservice;

	private String importfilename;

	private PlanService pservice;
	
	private SalesContractService service;
	
	private ContractService cservice;
	
	private StatusName statusName;
	
	
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
	//��ѯ���ź�
	private int orgId;
	//��ѯ���
	private String name;
	
	//��ѯ���
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
			String fname = "test";// Excel�ļ���
			OutputStream os = response.getOutputStream();// ȡ�������
			response.reset();// ��������
			response.setHeader("Content-disposition", "attachment; filename="
					+ fname + ".xls");// �趨����ļ�ͷ
			response.setContentType("application/msexcel");// �����������
			// ------
			// ��ȡexcel������
			System.out.println(msg.length);
			System.out.println(msg.toString());
			// ExcelReadService.importExcel(importfilename);
			// List<List<String>> allList=new ArrayList<List<String>>();
			// int rowcount=ExcelReadService.getRowIndex(0);
			List<String> headList = new ArrayList<String>();
			for (int j = 1; j <= msg.length; j++) {
				switch (msg[j]) {
				case 1:
					headList.add("�������");
					break;
				case 2:
					headList.add("����ͺ�");
					break;
				case 3:
					headList.add("ԭ�ƻ���");
					break;
				case 4:
					headList.add("ʹ�õ�λ");
					break;
				case 5:
					headList.add("�ƻ����");
					break;
				case 6:
					headList.add("�ƻ���������");
					break;
				case 7:
					headList.add("��λ");
					break;
				case 8:
					headList.add("����");
					break;
				case 9:
					headList.add("����ʱ��");
					break;
				case 10:
					headList.add("�����ص�");
					break;
				case 11:
					headList.add("Ԥ����");
					break;
				case 12:
					headList.add("�ɹ�״̬");
					break;
				case 13:
					headList.add("��ע");
					break;
				case 14:
					headList.add("�ɹ�����");
					break;
				case 15:
					headList.add("�ɹ�ǩ������");
					break;
				case 16:
					headList.add("�ɹ�������ע");
					break;
				case 17:
					headList.add("ѰԴ��������");
					break;
				case 18:
					headList.add("ѰԴǩ������");
					break;
				case 19:
					headList.add("��ִͬ�з�ʽ");
					break;
				case 20:
					headList.add("��ͬ���");
					break;

				case 22:
					headList.add("�ɹ�����");
					break;
				case 23:
					headList.add("�ɹ����");
					break;
				case 24:
					headList.add("���۽��");
					break;
				case 25:
					headList.add("���۱���");
					break;
				// pid
				case 26:
					headList.add("�ɹ���ͬ���");
					break;
				case 27:
					headList.add("��ͬ���");
					break;
				case 28:
					headList.add("��Ӧ��id");
					break;
				case 29:
					headList.add("��Ӧ��name");
					break;
				case 30:
					headList.add("��ִͬ�з�ʽ");
					break;
				case 31:
					headList.add("Ԥ����");
					break;
				case 32:
					headList.add("ҵ��id");
					break;
				case 33:
					headList.add("ҵ�������");
					break;
				case 34:
					headList.add("��ͬǩ������");
					break;
				case 35:
					headList.add("��ͬ��������");
					break;
				case 36:
					headList.add("��ͬ�ܶ�");
					break;
				case 37:
					headList.add("�ɹ�����");
					break;
				case 38:
					headList.add("��ͬ�ʱ����");
					break;
				case 39:
					headList.add("��ͬ�ʱ�����");
					break;
				case 40:
					headList.add("��ͬδ�����");
					break;
				case 41:
					headList.add("��ͬӦ�����");
					break;
				case 42:
					headList.add("��ͬ��ע");
					break;
				case 43:
					headList.add("��ͬ�Ѹ����");
					break;
				// ����
				case 44:
					headList.add("��ͬ���˽��");
					break;
				case 45:
					headList.add("��ͬ��������");
					break;
				}
			}
			// д��excel
			excelCreateService.createSheet("����");
			excelCreateService.addHeader(headList, true);
			List<List<String>> rowList = eservice.findSelectField(msg,orgId,name);
			for (int i = 1; i < rowList.size(); i++) {
				excelCreateService.addRow(rowList.get(i));
			}
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(now);
			File file = new File("D:\\����" + date + ".xls");
			excelCreateService.exportExcel(file);

			// --------
		} catch (Exception e) {
			System.out.println(e);
		}
		return "export_success";
	}

	// �ƻ�ִ����ϸ��
	public String planSheet() throws Exception {
		/*
		 * HttpServletResponse response=ServletActionContext.getResponse(); try{
		 * 
		 * Date now=new Date(); SimpleDateFormat sdf=new
		 * SimpleDateFormat("yyyy-MM-dd"); String date=sdf.format(now); //File
		 * file = new File("D:\\�ɹ���ͬ����"+date+".xls"); //String fname =
		 * "test";//Excel�ļ��� OutputStream os =
		 * response.getOutputStream();//ȡ������� response.reset();//��������
		 * response.setHeader("Content-disposition", "attachment; filename="
		 * +"test"+date+".xls");//�趨����ļ�ͷ
		 * //response.setContentType("application/octet-stream");//�����������
		 * response.setContentType("application/msexcel");//�����������
		 * response.setCharacterEncoding("utf-8"); //������ӦͷΪ��������
		 * //response.setContentType( "application/x-msdownload;charset=utf-8");
		 * response.setCharacterEncoding("utf-8"); BufferedOutputStream buffout
		 * = new BufferedOutputStream(os); //workbook.write(buffout);
		 * buffout.flush(); buffout.close();
		 * 
		 * 
		 * }catch(Exception e){ System.out.println(e); }
		 */

		// д��excel
		// ��ͷ
		List<String> headList = new ArrayList<String>();
		headList.add("���");
		headList.add("�ƻ��ᱨ��λ");
		headList.add("���ﳵ����");
		headList.add("����Ŀ��");
		headList.add("���ϱ���");
		headList.add("��������");
		headList.add("�ƻ�����");
		headList.add("������λ");
		headList.add("Ԥ����");
		headList.add("�ɹ���ʽ");
		headList.add("�ɹ�ǩ������");
		headList.add("ѰԴ��������");
		headList.add("ѰԴǩ������");
		headList.add("��ִͬ�з�ʽ");
		headList.add("��ͬǩ������");
		headList.add("�ɹ�����");
		headList.add("�ɹ����");
		headList.add("��ͬ���");
		headList.add("ǩ������");
		headList.add("��ͬҪ�󵽻�ʱ��");
		headList.add("��ͬ���");
		headList.add("��Ӧ��");
		headList.add("��������");
		headList.add("�������");
		headList.add("�������");
		headList.add("���ս��");
		headList.add("������");
		ExcelCreate s = new ExcelCreate();
		s.createSheet("�ƻ�ִ����ϸ��");
		s.addHeader(headList, true);
		//User u=(User) ServletActionContext.getRequest().getSession().getAttribute("login");
		//List<List<String>> rowList = eservice.findPlanSheet(orgId,oldPlanNum,u.getAuth());
		// ExcelCreate workbook=new ExcelCreate();
		
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_oid", orgId);
		if(oldPlanNum!=null && !"".equals(oldPlanNum.trim()))
			params.put("oldPlanNum", oldPlanNum);
		if(useCompId!=null)
			params.put("id_reportCompId", useCompId);
		if(procurementWay!=null && !"".equals(procurementWay.trim()) && !("0".equals(procurementWay.trim())))
			params.put("id_procurementWay", procurementWay);
		if(contractExecutionWay!=null && !"".equals(contractExecutionWay.trim()) && !("0".equals(contractExecutionWay.trim())))
			params.put("id_contractExecutionWay", contractExecutionWay);
		if(sDate!=null && !"".equals(sDate.trim())  && eDate!=null && !"".equals(eDate.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+sDate+"' and '"+eDate+"'");
			//params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(suppliersId!=null)
			params.put("dy_pc.suppliersid", "="+suppliersId+"  ");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		PageModel  pm = pservice.getPlanStatusList(0, 0, params, false);
		// ExcelCreate workbook=new ExcelCreate();
		List<ProcurementContractBean> l = pm.getList();
		for (int i = 0; i < pm.getList().size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add(l.get(i).getReportComp());
			list.add(l.get(i).getOldPlanNum() );
			list.add(l.get(i).getPlanNum() );
			list.add(l.get(i).getItemsNum() );
			list.add(l.get(i).getItemsName() );
			list.add(l.get(i).getNum() );
			list.add(l.get(i).getUnit() );
			list.add(l.get(i).getBudget() );
			list.add(l.get(i).getProcurementWay() );
			if(l.get(i).getProcurementDate() !=null)
			list.add(sdf.format(l.get(i).getProcurementDate()));
			else
				list.add("");
			if(l.get(i).getSearchAnnouncedDate() !=null)
			list.add(sdf.format(l.get(i).getSearchAnnouncedDate()));
			else
				list.add("");
			if(l.get(i).getSearchDate() !=null)
			list.add(sdf.format(l.get(i).getSearchDate()));
			else
				list.add("");
			list.add(l.get(i).getContractExecutionWay() );
			list.add(l.get(i).getPlancontractNum());
			list.add(l.get(i).getProcurementPrice() );
			list.add(l.get(i).getProcurementMoney() );
			list.add(l.get(i).getContractNum() );
			if(l.get(i).getSigningDate() !=null)
				list.add(sdf.format(l.get(i).getSigningDate()));
			else
				list.add("");
			if(l.get(i).getArrivalDate() !=null)
				list.add(sdf.format(l.get(i).getArrivalDate()) );
			else
				list.add("");
			list.add(l.get(i).getTotalMoney() );
			list.add(l.get(i).getSuppliersName() );
			list.add(l.get(i).getArrivalNum() );
			list.add(l.get(i).getArrivalMoney() );
			list.add(l.get(i).getAcceptanceNum() );
			list.add(l.get(i).getAcceptanceMoney() );
			list.add(l.get(i).getPersonName() );
			s.addRow(list);
		}
		Date now = new Date();
		
		String date = sdf.format(now);
		String fileName ="�ƻ�ִ����ϸ��" + date + ".xls";
		result=ExportAction.exportExcel(fileName, s);

		return null;
	}

	// ���ۺ�ͬ�������
	public String exportSalesContract() throws Exception {	
		// д��excel
		List<String> headList = new ArrayList<String>();
		headList.add("ϵͳID");
		headList.add("���ۺ�ͬ���");
		headList.add("���ۺ�ͬ���");
		headList.add("��ͬǩ��ʱ��");
		headList.add("�ƻ��ᱨ��λ");
		headList.add("��ͬǩ����λ");
		headList.add("��ͬ���");
		headList.add("��ͬ�ʱ����");
		headList.add("��ͬ�ʱ�����");
		headList.add("��ͬ��Ʊ���");
		headList.add("��ͬ���ս��");
		headList.add("��ͬӦ�ս��");
		headList.add("�����ܽ��");
		headList.add("�����ܽ��");
		headList.add("������");
		ExcelCreate s = new ExcelCreate();
		s.createSheet("���ۺ�ͬ����");
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
		for (int i = 0; i < pm.getList().size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add(l.get(i).getSalesContractName());
			list.add(l.get(i).getContractNum() );
			if(l.get(i).getSigningDate() !=null)
				list.add(sdf.format(l.get(i).getSigningDate()));
			else
				list.add("");
			list.add(l.get(i).getReportCompName() );
			list.add(l.get(i).getSignComp() );
			list.add(l.get(i).getQualityMoney());
			if(l.get(i).getQualityDate() !=null)
				list.add(sdf.format(l.get(i).getQualityDate()));
			else
				list.add("");
			list.add(l.get(i).getTotalContractInvoiceMoney() );
			list.add(l.get(i).getTotalcontractReceivedMoney());
			list.add(l.get(i).getContractReceivableMoney());
			list.add(l.get(i).getArrivalMoney() );
			list.add(l.get(i).getAcceptanceMoney() );
			list.add(l.get(i).getPerson().getName() );
			s.addRow(list);
		}
		Date now = new Date();
		String date = sdf.format(now);
		String fileName ="���ۺ�ͬ����" + date + ".xls";
		//excelCreateService.exportExcel(file);
		result=ExportAction.exportExcel(fileName, s);
		return null;
	}

	// �ɹ���ͬ�������
	public String exportProcurementContract() throws Exception {
		// д��excel
		List<String> headList = new ArrayList<String>();
		headList.add("ϵͳID");
		headList.add("�ɹ���ͬ���");
		headList.add("�ɹ���ͬ���");
		headList.add("��ͬǩ��ʱ��");
		headList.add("�ƻ��ᱨ��λ");
		headList.add("��ִͬ�з�ʽ");
		headList.add("��ͬ���");
		headList.add("Ԥ�����");
		headList.add("��ͬ�ʱ����");
		headList.add("��ͬ�ʱ�����");
		headList.add("��Ӧ��");
		headList.add("��ͬ��������");
		headList.add("�ɹ�����");
		headList.add("�Ѹ��ܽ��");
		headList.add("�����ܽ��");
		headList.add("Ӧ�����");
		headList.add("�������");
		headList.add("���ս��");
		headList.add("����");
		headList.add("������");
		ExcelCreate s = new ExcelCreate();
		s.createSheet("�ɹ���ͬ����");
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
		for (int i = 0; i < l.size(); i++) {
			List list = new ArrayList();
			list.add(i+1);
			list.add(l.get(i).getContractName());
			list.add(l.get(i).getContractNum() );
			if(l.get(i).getSigningDate() !=null)
				list.add(sdf.format(l.get(i).getSigningDate()));
			else
				list.add("");
			list.add(l.get(i).getReportCompName() );
			if(l.get(i).getContractExecutionWay() !=null)
				list.add(statusName.getContractExecutionWay(l.get(i).getContractExecutionWay()) );
			else
				list.add("");
			list.add(l.get(i).getTotalMoney());
			list.add(l.get(i).getAdvance());
			list.add(l.get(i).getQualityMoney());
			if(l.get(i).getQualityDate() !=null)
				list.add(sdf.format(l.get(i).getQualityDate()));
			else
				list.add("");
			list.add(l.get(i).getSuppliersName() );
			if(l.get(i).getArrivalDate() !=null)
				list.add(sdf.format(l.get(i).getArrivalDate()));
			else
				list.add("");
			if(l.get(i).getProcurementWay() !=null)
				list.add(statusName.getProcurementWay(l.get(i).getProcurementWay()) );
			else
				list.add("");
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
			double t=cservice.searchContractcreditMoney(l.get(i).getId());
			list.add(t);
			list.add(l.get(i).getShouldPayment());
			list.add(l.get(i).getArrivalMoney() );
			list.add(l.get(i).getAcceptanceMoney() );
			list.add(l.get(i).getOrgName());
			list.add(l.get(i).getPerson().getName() );
			s.addRow(list);
		}
		Date now = new Date();
		String date = sdf.format(now);
		String fileName ="�ɹ���ͬ����" + date + ".xls";
		//excelCreateService.exportExcel(file);
		result=ExportAction.exportExcel(fileName, s);
		return null;
	}
	public String exportPlan() throws Exception {
		
		List<String> headList = new ArrayList<String>();
		headList.add("���");
		headList.add("ʹ�õ�λ");
		headList.add("���ʼ��ű��");
		headList.add("ԭʼ�ƻ���");
		headList.add("�������");
		headList.add("��λ");
		headList.add("����");
		headList.add("Ԥ����");
		headList.add("�������");
		headList.add("����");
		headList.add("���");
		headList.add("�ɹ���ʽ");
		headList.add("ǩ������");
		headList.add("ѰԴ��������");
		headList.add("ǩ������");
		headList.add("ִ�з�ʽ");
		headList.add("ǩ������");
		headList.add("��ͬ���");
		headList.add("��ͬҪ�󵽻�ʱ��");
		headList.add("��ͬ���");
		headList.add("��Ӧ��");
		headList.add("��������");
		headList.add("�������");
		headList.add("�������");
		headList.add("���ս��");
		headList.add("������");
		
		ExcelCreate s = new ExcelCreate();
		s.createSheet("�ƻ�ִ����ϸ��");
		s.addHeader(headList, true);
		User u=(User) ServletActionContext.getRequest().getSession().getAttribute("login");
		//List<List<String>> rowList = eservice.findPlanSheet(orgId,oldPlanNum,u.getAuth());
		
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_oid", orgId);
		if(oldPlanNum!=null && !"".equals(oldPlanNum.trim()))
			params.put("oldPlanNum", oldPlanNum);
		if(useCompId!=null)
			params.put("id_reportCompId", useCompId);
		if(procurementWay!=null && !"".equals(procurementWay.trim()) && !("0".equals(procurementWay.trim())))
			params.put("id_procurementWay", procurementWay);
		if(contractExecutionWay!=null && !"".equals(contractExecutionWay.trim()) && !("0".equals(contractExecutionWay.trim())))
			params.put("id_contractExecutionWay", contractExecutionWay);
		if(sDate!=null && !"".equals(sDate.trim())  && eDate!=null && !"".equals(eDate.trim()) ){
			params.put("dy_p.planReceiptDate", " between '"+sDate+"' and '"+eDate+"'");
			//params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(suppliersId!=null)
			params.put("dy_pc.suppliersid", "="+suppliersId+"  ");
		
		PageModel  pm = pservice.getPlanStatusList(0, 0, params, true);
		// ExcelCreate workbook=new ExcelCreate();
		List<ProcurementContractBean> l = pm.getList();
		for (int i = 0; i < pm.getList().size(); i++) {
			List list = new ArrayList();
			list.add(i);
			list.add(l.get(i).getReportComp());
			//s.addRow(l.get(i).get);
		}
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(now);
		String fileName ="�ƻ�ִ����ϸ��" + date + ".xls";
		result=ExportAction.exportExcel(fileName, s);

		return "planSheet_success";
		
	}
	public final static String exportExcel(String fileName, ExcelCreate s) {
		String result = "ϵͳ��ʾ��Excel�ļ������ɹ���";
		// ���¿�ʼ�����EXCEL
		try {
			// ������������Ա�򿪱���Ի���______________________begin
			HttpServletResponse response = ServletActionContext.getResponse();
			OutputStream os = response.getOutputStream();// ȡ�������
			response.reset();// ��������
			response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"), "ISO8859-1"));
			// �趨����ļ�ͷ
			response.setContentType("application/msexcel");// �����������
			// ������������Ա�򿪱���Ի���_______________________end

			s.exportExcel(os);

		} catch (Exception e) {
			result = "ϵͳ��ʾ��Excel�ļ�����ʧ�ܣ�ԭ��" + e.toString();
			System.out.println(result);
			e.printStackTrace();
		}
		return result;
	}

}
