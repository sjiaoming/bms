package com.shtz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shtz.model.Plan;
import com.shtz.service.ExportService;

public class ExportServiceImpl extends HibernateDaoSupport  implements ExportService {

	public List<List<String>> findSelectField(int[] msg,int orgId,String name){
		
		//�ƻ�����
		String[] fieldstr=null;
		Map<Integer,Integer> fieldstrMap=new HashMap<Integer, Integer>();//��Ӧ������±�Ĵ洢
		//�ɹ���ͬ����
		String[] fieldstrpid=null;
		Map<Integer,Integer> fieldstrpidMap=new HashMap<Integer, Integer>();//��Ӧ������±�Ĵ洢
		//���ۺ�ͬ����
		String[] fieldstrsid=null;
		Map<Integer,Integer> fieldstrsidMap=new HashMap<Integer, Integer>();//��Ӧ������±�Ĵ洢
		//���˺�ͬ����
		String[] fieldstrcid=null;
		Map<Integer,Integer> fieldstrcidMap=new HashMap<Integer, Integer>();//��Ӧ������±�Ĵ洢
		
		for (int i = 1; i <= msg.length; i++) {
			  int j=0;
			  int k=0;
			  int l=0;
			  switch (msg[i]) {
			  case 1: fieldstr[j]="p.itemsName";fieldstrMap.put(msg[i], j);fieldstrMap.put(msg[i], j);j++;break;
			  case 2: fieldstr[j]="p.model";fieldstrMap.put(msg[i], j);j++;break;
			  case 3: fieldstr[j]="p.oldPlanNum";fieldstrMap.put(msg[i], j);j++;break;
			  case 4: fieldstr[j]="p.reportComp";fieldstrMap.put(msg[i], j);j++;break;
			  case 5: fieldstr[j]="p.planNum";fieldstrMap.put(msg[i], j);j++;break;
			  case 6: fieldstr[j]="p.planReceiptDate";fieldstrMap.put(msg[i], j);j++;break;
			  case 7: fieldstr[j]="p.company";fieldstrMap.put(msg[i], j);j++;break;
			  case 8: fieldstr[j]="p.num";fieldstrMap.put(msg[i], j);j++;break;
			  case 9: fieldstr[j]="p.arrivalDate";fieldstrMap.put(msg[i], j);j++;break;
			  case 10:fieldstr[j]="p.arrivalSite";fieldstrMap.put(msg[i], j);j++;break;
			  case 11:fieldstr[j]="p.budget";fieldstrMap.put(msg[i], j);j++;break;
			  case 12:fieldstr[j]="p.procurementFlag";fieldstrMap.put(msg[i], j);j++;break;
			  case 13:fieldstr[j]="p.remark";fieldstrMap.put(msg[i], j);j++;break;
			  case 14:fieldstr[j]="p.procurementWay";fieldstrMap.put(msg[i], j);j++;break;
			  case 15:fieldstr[j]="p.procurementDate";fieldstrMap.put(msg[i], j);j++;break;
			  case 16:fieldstr[j]="p.procurementRemark";fieldstrMap.put(msg[i], j);j++;break;
			  case 17:fieldstr[j]="p.searchAnnouncedDate";fieldstrMap.put(msg[i], j);j++;break;
			  case 18:fieldstr[j]="p.searchDate";fieldstrMap.put(msg[i], j);j++;break;
			  case 19:fieldstr[j]="p.contractExecutionWay";fieldstrMap.put(msg[i], j);j++;break;
			  case 20:fieldstr[j]="p.contractMoney";fieldstrMap.put(msg[i], j);j++;break;
			 
			  case 22:fieldstr[j]="p.procurementPrice";fieldstrMap.put(msg[i], j);j++;break;
			  case 23:fieldstr[j]="p.procurementMoney";fieldstrMap.put(msg[i], j);j++;break;
			  case 24:fieldstr[j]="p.salesMoney";fieldstrMap.put(msg[i],j);j++;break;
			  case 25:fieldstr[j]="p.salesRatio";fieldstrMap.put(msg[i],j);j++;break;
			  //pid
			  
			  case 26:fieldstrpid[k]="pc.id";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 27:fieldstrpid[k]="pc.contractNum";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 28:fieldstrpid[k]="pc.suppliersId";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 29:fieldstrpid[k]="pc.suppliersName";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 30:fieldstrpid[k]="pc.contractExecutionWay";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 31:fieldstrpid[k]="pc.advance";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 32:fieldstrpid[k]="pc.orgId";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 33:fieldstrpid[k]="pc.orgName";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 34:fieldstrpid[k]="pc.oqualityMoney";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 35:fieldstrpid[k]="pc.arrivalDate";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 36:fieldstrpid[k]="pc.totalMoney";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 37:fieldstrpid[k]="pc.procurementWay";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 38:fieldstrpid[k]="pc.qualityMoney";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 39:fieldstrpid[k]="pc.qualityDate";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 40:fieldstrpid[k]="pc.noPayment";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 41:fieldstrpid[k]="pc.shouldPayment";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 42:fieldstrpid[k]="pc.remark";fieldstrpidMap.put(msg[i],k);k++;break;
			  case 43:fieldstrpid[k]="pc.payed";fieldstrpidMap.put(msg[i],k);k++;break;
			  //����
			  case 44:fieldstrcid[l]="cc.contractCreditMoney";fieldstrcidMap.put(msg[i], l);l++;break;
			  case 45:fieldstrcid[l]="cc.contractCreditDate";fieldstrcidMap.put(msg[i], l);l++;break;
			  }
		  }
			
		//String[] fieldstr,String[] fieldstrpid,String[] fieldstrcid
		//������fieldstr���ֶ�
		String field="";
		for (int i = 0; i < fieldstr.length; i++) {
			if(i==0){
				field+=fieldstr[i];
			}else{
				field=","+fieldstr[i];
			}
		}
		String fieldpid="";
		for (int i = 0; i < fieldstrpid.length; i++) {
			if(i==0){
				fieldpid+=fieldstrpid[i];
			}else{
				fieldpid=","+fieldstrpid[i];
			}
		}
		String fieldcid="";
		for (int i = 0; i < fieldstrcid.length; i++) {
			if(i==0){
				fieldcid+=fieldstrcid[i];
			}else{
				fieldcid=","+fieldstrcid[i];
			}
		}
		String hql="select"+field+"from Plan p";
		String hql1="select"+fieldpid+"from ProcurementContract pc where pc.id";
		List<Object[]> selectObjList=this.getSession().createQuery(hql).list();
		List<List<String>> rowList=new ArrayList<List<String>>(); 
		for (int i = 0; i < selectObjList.size(); i++) {
			Object[] objstr=selectObjList.get(i);
			List<String> cellList=new ArrayList<String>();
			for (int j = 0; j < objstr.length; j++) {
				cellList.add(objstr[j].toString());
			}
			rowList.add(cellList);
		}
		return rowList;
	}

//@@@@@���˴�Сд ��sc.totalcontractReceivedMoney�ֶ�
	@SuppressWarnings("unchecked")
	public List<List<String>> findSalesContract(int orgId,String contractNum,String auth) {
		
		String hql="select sc.id,sc.reportComp,sc.contractNum,sc.signingDate,sc.contractMoney,sc.qualityMoney,sc.totalContractInvoiceMoney,sc.totalcontractReceivedMoney,sc.contractReceivableMoney,sc.user.person.name from SalesContract sc";
		String hql1="from Plan p where p.salesContract.id=?";
		if(orgId!=0){
			hql=hql+",Plan p where p.salesContract.id=sc.id and p.org.id="+orgId;
		}
		if(!contractNum.trim().equals("")){
			hql=hql+" and p.planNum="+contractNum;
		}
		List<Object[]> SalesContractList=this.getSession().createQuery(hql).list();
		List<List<String>> rowList=new ArrayList<List<String>>();
		for (int i = 0; i < SalesContractList.size(); i++) {
			Object[] str=SalesContractList.get(i);
			List<String> cellList=new ArrayList<String>();
			//��ȡȫ���������
			List<Plan> planList= (List<Plan>)this.getSession().createQuery(hql1).setParameter(0, Integer.parseInt(str[0].toString())).list();
			String itemsName="";
			for (int j = 0; j < planList.size(); j++) {
				if(j==0){
					Plan p=(Plan)planList.get(j);
					System.out.println(p.getItemsName());
					
					itemsName=itemsName+planList.get(j).getItemsName();
				}else{
					itemsName+=";"+planList.get(j).getItemsName();
				}
			}
			if(planList.size()==0){
				itemsName=null;
			}
			
			for (int j = 0; j < str.length; j++) {
				if(str[j]!=null){
					cellList.add(str[j].toString());
					if(j==3){
						cellList.add(itemsName);
					}
					
				}else{
					cellList.add(null);
				}
			}
			/*cellList.add(str[1].toString());
			cellList.add(str[2].toString());
			cellList.add(str[3].toString());
			cellList.add(itemsName);
			cellList.add(str[4].toString());
			cellList.add(str[5].toString());
			cellList.add(str[6].toString());
			cellList.add(str[7].toString());
			cellList.add(str[8].toString());*/
			
			rowList.add(cellList);
		}
		return rowList;
	}
	
	public List<List<String>> findPlanSheet(int orgId,String oldPlanNum,String auth) {
	/*	String hql="select p.id,p.reportComp,p.model,p.oldPlanNum,p.itemsName,p.company,p.num,p.budget," +
		"c.changeTime,c.num,c.ContractMoney,p.procurementWay,p.procurementDate,p.searchAnnouncedDate,p.searchDate," +
		"p.ContractExecutionWay,p.procurementDate,pc.contractNum,pc.arrivalDate,p.procurementMoney,pc.suppliersName " +
		"from Plan p,Change c,ProcurementContract pc where p.procurementContract.id=pc.id and p.change.id=c.id";*/
		String hql="select p.id,p.reportComp,p.model,p.oldPlanNum,p.itemsName,p.unit,p.num,p.budget," +
				"p.procurementWay,p.procurementDate,p.searchAnnouncedDate,p.searchDate,p.contractExecutionWay,p.procurementDate," +
				"p.procurementMoney,p.person.name from Plan p where p.person in("+auth+") and p.planStatus=01" ;
		String hql2="select c.changeTime,c.num,c.ContractMoney from Plan p,Change c where  p.change.id=c.id and p.id=?";
		String hql3="select pc.contractNum,pc.arrivalDate,pc.suppliersName from Plan p,ProcurementContract pc where p.procurementContract.id=pc.id and p.id=?";
		String hql1="select sum(a.arrivalNum),sum(a.acceptanceNum),sum(a.arrivalNum)*p.procurementPrice,sum(a.acceptanceNum)*p.procurementPrice from ArrivalItems a,Plan p where a.plan.id=p.id and  a.plan.id=?";
		if(orgId!=0){
			hql=hql+" and p.org.id="+orgId;
		}
		if(!oldPlanNum.trim().equals("")){
			hql=hql+" and p.oldPlanNum="+oldPlanNum;
		}
		
		List<Object[]> SalesContractList=this.getSession().createQuery(hql).list();
		List<List<String>> rowList=new ArrayList<List<String>>();
		for (int i = 0; i < SalesContractList.size(); i++) {
			Object[] str=SalesContractList.get(i);
			List<String> cellList=new ArrayList<String>();
			//��ȡȫ���������p.
			Object[] arrivalItemList= (Object[]) this.getSession().createQuery(hql1).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
			for (int j = 0; j < str.length; j++) {
				if(str[j]!=null){
					if(j!=15){
						cellList.add(str[j].toString());
					}
					if(j==7){
						Object[] cList= (Object[]) this.getSession().createQuery(hql2).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
						if(cList!=null){
							if(cList[0]!=null){
								cellList.add(cList[0].toString());
							}else{
								cellList.add(null);
							}
							if(cList[1]!=null){
								cellList.add(cList[1].toString());
							}else{
								cellList.add(null);
							}
							if(cList[2]!=null){
								cellList.add(cList[2].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
							cellList.add(null);
							cellList.add(null);
						}
						
					}
					if(j==13){
						Object[] pcList= (Object[]) this.getSession().createQuery(hql3).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
						if(pcList!=null){
							if(pcList[0]!=null){
								cellList.add(pcList[0].toString());
							}else{
								cellList.add(null);
							}
							if(pcList[1]!=null){
								cellList.add(pcList[1].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
							cellList.add(null);
						}
					}
					if(j==14){
						Object[] pcList= (Object[]) this.getSession().createQuery(hql3).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
						if(pcList!=null){
							if(pcList[2]!=null){
								cellList.add(pcList[2].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
						}
					}
				}else{
					if(j!=15){
						cellList.add(null);
					}
					
					if(j==7){
						Object[] cList= (Object[]) this.getSession().createQuery(hql2).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
						if(cList!=null){
							if(cList[0]!=null){
								cellList.add(cList[0].toString());
							}else{
								cellList.add(null);
							}
							if(cList[1]!=null){
								cellList.add(cList[1].toString());
							}else{
								cellList.add(null);
							}
							if(cList[2]!=null){
								cellList.add(cList[2].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
							cellList.add(null);
							cellList.add(null);
						}
						
					}
					if(j==13){
						Object[] pcList= (Object[]) this.getSession().createQuery(hql3).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
						if(pcList!=null){
							if(pcList[0]!=null){
								cellList.add(pcList[0].toString());
							}else{
								cellList.add(null);
							}
							if(pcList[1]!=null){
								cellList.add(pcList[1].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
							cellList.add(null);
						}
					}
					if(j==14){
						Object[] pcList= (Object[]) this.getSession().createQuery(hql3).setParameter(0, Integer.parseInt(str[0].toString())).uniqueResult();
						if(pcList!=null){
							if(pcList[2]!=null){
								cellList.add(pcList[2].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
						}
					}
				}
			}
			/*cellList.add(str[1].toString());
			cellList.add(str[2].toString());
			cellList.add(str[3].toString());
			cellList.add(str[4].toString());
			cellList.add(str[5].toString());
			cellList.add(str[6].toString());
			cellList.add(str[7].toString());
			cellList.add(str[8].toString());
			cellList.add(str[9].toString());
			cellList.add(str[10].toString());
			cellList.add(str[11].toString());
			cellList.add(str[12].toString());
			cellList.add(str[13].toString());
			cellList.add(str[14].toString());
			cellList.add(str[15].toString());
			cellList.add(str[16].toString());
			cellList.add(str[17].toString());
			cellList.add(str[18].toString());
			cellList.add(str[19].toString());
			cellList.add(str[20].toString());
			cellList.add(str[21].toString());*/
			
			/*cellList.add(arrivalItemList[0].toString());
			cellList.add(arrivalItemList[1].toString());
			cellList.add(arrivalItemList[2].toString());
			cellList.add(arrivalItemList[3].toString());*/
			for (int k = 0; k < arrivalItemList.length; k++) {
				if(arrivalItemList[k]!=null){
					cellList.add(arrivalItemList[k].toString());
				}else{
					cellList.add(null);
				}
			}
			if(str[15]!=null)
			cellList.add(str[15].toString());
			else
				cellList.add(null);
			//cellList.add(str[17].toString());
			//cellList.add(str[18].toString());
			rowList.add(cellList);
		}
		return rowList;
	}
	
	public List<List<String>> findProcurementContract(int orgId,String name) {
		String hql="select pc.contractNum,pc.signingDate,pc.contractExecutionWay,pc.suppliersId,pc.totalMoney,pc.qualityMoney,pc.qualityDate,p.id,pc.id," +
		"pc.shouldPayment,pc.payed,pc.noPayment from ProcurementContract pc,Plan p where p.procurementContract.id=pc.id";
		
		String hql1="select sum(a.arrivalNum)*p.procurementPrice,sum(a.acceptanceNum)*p.procurementPrice from Plan p,ArrivalItems a where a.plan.id=p.id and p.id=?";
		
		String hql2="select sum(cc.contractCreditMoney) from ProcurementContract pc,ContractCredit cc where cc.procurementContract.id=pc.id and pc.id=?";
		
		if(orgId!=0){
			hql=hql+" and p.org.id="+orgId;
		}
		if(!name.trim().equals("")){
			hql=hql+" and p.planNum="+name;
		}
		hql=hql+" group by p.id";
		List<Object[]> ProcurementContractList=this.getSession().createQuery(hql).list();
		List<List<String>> rowList=new ArrayList<List<String>>();
		for (int i = 0; i < ProcurementContractList.size(); i++) {
			Object[] str=ProcurementContractList.get(i);
			List<String> cellList=new ArrayList<String>();
			for (int j = 0; j < str.length; j++) {
				if(str[j]!=null){
					if(j==7){
						Object[] field7=(Object[]) this.getSession().createQuery(hql1).setParameter(0, Integer.parseInt(str[j].toString())).uniqueResult();
						if(field7!=null){
							if(field7[0]!=null){
								cellList.add(field7[0].toString());
							}else{
								cellList.add(null);
							}
							if(field7[1]!=null){
								cellList.add(field7[1].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
							cellList.add(null);
						}
						
					}else if(j==8){
						Object field8=this.getSession().createQuery(hql2).setParameter(0, Integer.parseInt(str[j].toString())).uniqueResult();
						if(field8!=null){
							cellList.add(field8.toString());
						}else{
							cellList.add(null);
						}
					}else{
						cellList.add(str[j].toString());
					}
					
				}else{
					cellList.add(null);
					
					if(j==7){
						Object[] field7=(Object[]) this.getSession().createQuery(hql1).setParameter(0, Integer.parseInt(str[j].toString())).uniqueResult();
						if(field7!=null){
							if(field7[0]!=null){
								cellList.add(field7[0].toString());
							}else{
								cellList.add(null);
							}
							if(field7[1]!=null){
								cellList.add(field7[1].toString());
							}else{
								cellList.add(null);
							}
						}else{
							cellList.add(null);
							cellList.add(null);
						}
						
					}else if(j==8){
						Object field8=this.getSession().createQuery(hql2).setParameter(0, Integer.parseInt(str[j].toString())).uniqueResult();
						if(field8!=null){
							cellList.add(field8.toString());
						}else{
							cellList.add(null);
						}
					}
				}
			}
			rowList.add(cellList);
		}
		return rowList;
	}

}
