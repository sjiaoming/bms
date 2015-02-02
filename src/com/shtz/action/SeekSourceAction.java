/**
 * 
 */
package com.shtz.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Check;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.SeekSource;
import com.shtz.model.SeekSourceDetail;
import com.shtz.model.Suppliers;
import com.shtz.model.User;
import com.shtz.service.CheckService;
import com.shtz.service.GroupService;
import com.shtz.service.PersonService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.SeekSourceService;
import com.shtz.service.SuppliersService;
import com.shtz.util.PageModel;

/**
 * Filename : SeekSourceAction.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:55:53 - 2013-4-26
 *
 */
public class SeekSourceAction extends ActionSupport {
	private String[] materialName ;
//	private String supplier;
	private Double[] seekSourceQuantity;
	private Double[] seekSourcePrice;
	private Double[] seekSourceMoney;
	private Integer[] supplierIds;
	private Integer[] detailIds;
	private Integer[] planId;
	private SeekSourceService seekSourceService;
	private ProcurementCheckService pcService;
	private PersonService personService;
	private PlanService planService;
	private SeekSource seekSource;
	private CheckService checkService;
	private SuppliersService suservice;
	private GroupService groupService;
	private Integer myOffset;
	private Integer sdId;
	private String tags;
	

	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}

	public Integer getSdId() {
		return sdId;
	}

	public void setSdId(Integer sdId) {
		this.sdId = sdId;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer[] getDetailIds() {
		return detailIds;
	}
	public Integer[] getPlanId() {
		return planId;
	}

	public void setPlanId(Integer[] planId) {
		this.planId = planId;
	}

	public void setDetailIds(Integer[] detailIds) {
		this.detailIds = detailIds;
	}

	public void setCheckService(CheckService checkService) {
		this.checkService = checkService;
	}

	public Integer getMyOffset() {
		return myOffset;
	}

	public void setMyOffset(Integer myOffset) {
		this.myOffset = myOffset;
	}

	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}

	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public Double[] getSeekSourcePrice() {
		return seekSourcePrice;
	}

	public void setSeekSourcePrice(Double[] seekSourcePrice) {
		this.seekSourcePrice = seekSourcePrice;
	}

	public Double[] getSeekSourceMoney() {
		return seekSourceMoney;
	}

	public void setSeekSourceMoney(Double[] seekSourceMoney) {
		this.seekSourceMoney = seekSourceMoney;
	}

	public SeekSource getSeekSource() {
		return seekSource;
	}

	public void setSeekSource(SeekSource seekSource) {
		this.seekSource = seekSource;
	}

	public void setSeekSourceService(SeekSourceService seekSourceService) {
		this.seekSourceService = seekSourceService;
	}


	public Double[] getSeekSourceQuantity() {
		return seekSourceQuantity;
	}

	public void setSeekSourceQuantity(Double[] seekSourceQuantity) {
		this.seekSourceQuantity = seekSourceQuantity;
	}

	public Integer[] getSupplierIds() {
		return supplierIds;
	}

	public void setSupplierIds(Integer[] supplierIds) {
		this.supplierIds = supplierIds;
	}

	public String[] getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String[] materialName) {
		this.materialName = materialName;
	}
	public String addSeekSource(){
//		System.out.println(materialName.length);
		System.out.println("seekSource.getPc().getId()   >   "+seekSource.getPc().getId());
		System.out.println("seekSource.getDirector().getId()    >   "+seekSource.getDirector().getId());
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
		ProcurementCheck pc = pcService.getProcurementCheck(seekSource.getPc().getId());
		StringBuffer planIds = new StringBuffer();
		LinkedHashMap<Integer,Plan> planMp = new LinkedHashMap<Integer, Plan>();
		for(Plan p: pc.getPlans()){
			planIds.append(p.getId()+",");
			planMp.put(p.getId(), p);
		}
		//ProcurementCheck pc = pcService.getProcurementCheck(seekSource.getPc().getId());
		/*List<Plan> ps = planService.getPlans(planId);
		for(Plan p : ps){
			planMp.put(p.getId(), p);
		}*/
		String pIds = planIds.toString();
		pIds = pIds.substring(0,pIds.length()-1);
		System.out.println("pIds  "+pIds);
		seekSource.setPerson(pe);
		seekSource.setSubmitDate(new Date());
//		seekSource.setCheckStatus("01");
		seekSource.setCheckStatus("07");
		int checkNum = seekSourceService.getNextSeekSourceNum();
		seekSource.setCheckNum(checkNum);
		Set<SeekSourceDetail> sets = new HashSet<SeekSourceDetail>();
		for(int i=0;i<seekSourceQuantity.length;i++){
			SeekSourceDetail d = new SeekSourceDetail();
			if(seekSourceQuantity[i] != null && seekSourceQuantity[i] != 0){
				d.setMaterialName(materialName[i]);
				d.setSeekSourceM(seekSourceMoney[i]);
				d.setSeekSourcePrice(seekSourcePrice[i]);
				d.setSeekSourceQuantity(seekSourceQuantity[i]);
				d.setSupplier(suservice.findSuppliers(supplierIds[i]));
				d.setSeekSource(seekSource);
				d.setPlan(planMp.get(planId[i]));
//				System.out.println("planId[i] =======>   "+planId[i]+"     materialName[i]   "+materialName[i]+"  getItemsName   "+planMp.get(planId[i]).getItemsName());
				sets.add(d);
			}
		}
		seekSource.setDetails(sets);
		pcService.modifyAddSeekSource(seekSource.getPc().getId());
		//更新plan表为03已寻源状态
		seekSourceService.modifyPlansSS(pIds);
		seekSourceService.addSeekSource(seekSource);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String initSeekSourceD(){

		List<Person> ps = personService.loadInnerContralPersons();
//		List<Person> ps = personService.loadManagerPersons();
		ServletActionContext.getRequest().setAttribute("ipersons", ps);
		return SUCCESS;
	}
	public String initSeekSourceL(){

//		List<Person> ps = personService.loadInnerContralPersons();
//		List<Person> ps = personService.loadManagerPersons();
//		ServletActionContext.getRequest().setAttribute("ipersons", ps);
		return SUCCESS;
	}
	public String initSeekSourceI(){
		
		List<Person> ps = personService.loadManagerPersons();
//		List<Person> ps = personService.loadManagerPersons();
		ServletActionContext.getRequest().setAttribute("mpersons", ps);
		return SUCCESS;
	}
	public String initSeekSourceM(){
		
		List<Person> ps = personService.loadChairManPersons();
		ServletActionContext.getRequest().setAttribute("cpersons", ps);
		return SUCCESS;
	}
	public String doSeekSourceL(){
//		Date d = new Date();
//		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
//		Person pe = personService.findPerson(personId);
//		System.out.println(seekSource.getManager().getId()+"   seekSource getManager and opinion    "+seekSource.getOpinion());
		if("03".equals(seekSource.getLeaderStatus())){
			System.out.println("tags   "+tags);
			seekSourceService.modifySeekSourceCheckForLeader(seekSource, tags);
			List<SeekSource> ss = seekSourceService.getSeekSources(tags);
			StringBuffer sb = new StringBuffer();
			for(SeekSource s : ss){
				ProcurementCheck pc = s.getPc();
	//			System.out.println("pc id   "+pc.getId());
				Set<Plan> ps = pc.getPlans();
	//			System.out.println("Set<Plan>    "+ps.size());
				for(Plan p : ps){
					sb.append(p.getId()+",");
				}
			}
			String planIds = sb.toString();
			planIds = planIds.substring(0,planIds.length()-1);
			seekSourceService.modifyPlansSSLeaderStatus(seekSource.getLeaderStatus(), planIds);
			/*List<Check> cs = new ArrayList<Check>();
			for(SeekSource s : ss){
				Check check = new Check();
				check.setSeekSource(s);
				check.setPerson(pe);
				check.setCheckDate(d);
				check.setOpinion(seekSource.getOpinion());
				check.setCheckStatus(s.getCheckStatus());
				check.setPlan(null);
				//03:已 寻源方案
				check.setCheckType("03");
				cs.add(check);
			}
			
			checkService.addChecks(cs);*/
		}else{
			List<SeekSource> ss = seekSourceService.getSeekSources(tags);
			String sb = "",pcIds="";
			for(SeekSource s : ss){
				sb = sb + ","+ s.getId();
				pcIds = pcIds + "," +s.getPc().getId();
			}
			seekSourceService.deleteSeekSource(sb.substring(1));
			pcService.modifyPcFinishSeekSource(pcIds.substring(1));
		}
		 ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String doSeekSourceD(){

		Date d = new Date();
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
//		System.out.println(seekSource.getManager().getId()+"   seekSource getManager and opinion    "+seekSource.getOpinion());
		seekSourceService.modifySeekSourceCheck(seekSource, tags);
		List<SeekSource> ss = seekSourceService.getSeekSources(tags);
		StringBuffer sb = new StringBuffer();
		for(SeekSource s : ss){
			ProcurementCheck pc = s.getPc();
//			System.out.println("pc id   "+pc.getId());
			Set<Plan> ps = pc.getPlans();
//			System.out.println("Set<Plan>    "+ps.size());
			for(Plan p : ps){
				sb.append(p.getId()+",");
			}
		}
		String planIds = sb.toString();
		planIds = planIds.substring(0,planIds.length()-1);
		seekSourceService.modifyPlansCheckStatus(seekSource.getCheckStatus(), planIds);
		List<Check> cs = new ArrayList<Check>();
		for(SeekSource s : ss){
			Check check = new Check();
			check.setSeekSource(s);
			check.setPerson(pe);
			check.setCheckDate(d);
			check.setOpinion(seekSource.getOpinion());
			check.setCheckStatus(s.getCheckStatus());
			check.setPlan(null);
			//03:已 寻源方案
			check.setCheckType("03");
			cs.add(check);
		}
		
		// System.out.println("plan.getPlanStatus()------------      "+plan.getPlanStatus());
		checkService.addChecks(cs);
		 ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String doSeekSourceI(){

		Date d = new Date();
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
//		System.out.println(seekSource.getManager().getId()+"   seekSource getManager and opinion    "+seekSource.getOpinion());
		seekSourceService.modifySeekSourceCheck(seekSource, tags);
		List<SeekSource> ss = seekSourceService.getSeekSources(tags);
		StringBuffer sb = new StringBuffer();
		for(SeekSource s : ss){
			ProcurementCheck pc = s.getPc();
//			System.out.println("pc id   "+pc.getId());
			Set<Plan> ps = pc.getPlans();
//			System.out.println("Set<Plan>    "+ps.size());
			for(Plan p : ps){
				sb.append(p.getId()+",");
			}
		}
		String planIds = sb.toString();
		planIds = planIds.substring(0,planIds.length()-1);
		seekSourceService.modifyPlansCheckStatus(seekSource.getCheckStatus(), planIds);
		List<Check> cs = new ArrayList<Check>();
		for(SeekSource s : ss){
			Check check = new Check();
			check.setSeekSource(s);
			check.setPerson(pe);
			check.setCheckDate(d);
			check.setOpinion(seekSource.getOpinion());
			check.setCheckStatus(s.getCheckStatus());
			check.setPlan(null);
			//03:已 寻源方案
			check.setCheckType("03");
			cs.add(check);
		}
		
		// System.out.println("plan.getPlanStatus()------------      "+plan.getPlanStatus());
		checkService.addChecks(cs);
		 ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String doSeekSourceM(){

		Date d = new Date();
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
//		System.out.println(seekSource.getManager().getId()+"   seekSource getManager and opinion    "+seekSource.getOpinion());
		seekSourceService.modifySeekSourceCheck(seekSource, tags);
		List<SeekSource> ss = seekSourceService.getSeekSources(tags);
		StringBuffer sb = new StringBuffer();
		for(SeekSource s : ss){
			ProcurementCheck pc = s.getPc();
//			System.out.println("pc id   "+pc.getId());
			Set<Plan> ps = pc.getPlans();
//			System.out.println("Set<Plan>    "+ps.size());
			for(Plan p : ps){
				sb.append(p.getId()+",");
			}
		}
		String planIds = sb.toString();
		planIds = planIds.substring(0,planIds.length()-1);
		seekSourceService.modifyPlansCheckStatus(seekSource.getCheckStatus(), planIds);
		List<Check> cs = new ArrayList<Check>();
		for(SeekSource s : ss){
			Check check = new Check();
			check.setSeekSource(s);
			check.setPerson(pe);
			check.setCheckDate(d);
			check.setOpinion(seekSource.getOpinion());
			check.setCheckStatus(s.getCheckStatus());
			check.setPlan(null);
			//03:已 寻源方案
			check.setCheckType("03");
			cs.add(check);
		}
		
		// System.out.println("plan.getPlanStatus()------------      "+plan.getPlanStatus());
		checkService.addChecks(cs);
		 ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String doSeekSourceC(){
		
		Date d = new Date();
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
		//System.out.println(seekSource.getCheckStatus()+"   seekSource checkstatus and opinion    "+seekSource.getOpinion());
		seekSourceService.modifySeekSourceCheck(seekSource, tags);
		List<SeekSource> ss = seekSourceService.getSeekSources(tags);
		StringBuffer sb = new StringBuffer();
		if("07".equals(seekSource.getCheckStatus())){
			for(SeekSource s : ss){
				Map<Integer,List<SeekSourceDetail>> dmp = new HashMap<Integer,List<SeekSourceDetail>>();
				Set<Plan> pls = s.getPc().getPlans();
				for(SeekSourceDetail detail : s.getDetails()){
					Integer detailPlanId = detail.getPlan().getId();
					if(dmp.keySet().contains(detailPlanId)){
						dmp.get(detailPlanId).add(detail);
					}else{
						List<SeekSourceDetail> tempDetails = new ArrayList<SeekSourceDetail>();
						tempDetails.add(detail);
						dmp.put(detailPlanId, tempDetails);
					}
				}
//				System.out.println("dmp.size()  >>  "+dmp.size());
				for(Plan p : pls){
					Integer pId = p.getId();
					List<SeekSourceDetail> ds = dmp.get(pId);
//					System.out.println(pId+"   dmp contains SeekSourceDetail size >>  "+ds.size());
					for(int i=0;i<ds.size();i++){
						SeekSourceDetail tempd = ds.get(i);
						Plan addPlan = new Plan();
						if(i == 0){
							//p.setQuantity(Double.valueOf(tempd.getSeekSourceQuantity()));
//							p.setReportComp(tempd.getSupplier().getName());
//							p.setReportCompId(tempd.getSupplier().getId());
							p.setSupplier(tempd.getSupplier());
							p.setProcurementPrice(tempd.getSeekSourcePrice());
							p.setProcurementMoney(tempd.getSeekSourceM());
							p.setContractNum(new Double(tempd.getSeekSourceQuantity()));
							p.setProcurementWay(tempd.getSeekSource().getPc().getProcurementWay());
//							System.out.println(tempd.getSupplier().getId()+"  tempd.getSupplier() 000  "+tempd.getSupplier().getName()+"  detail Quantity   "+tempd.getSeekSourceQuantity());
//							System.out.println(p.getId()+"  before   planid  "+p.getSupplier().getId()+"  tempd.getSupplier() 000  "+p.getSupplier().getName()+"  detail Quantity   "+p.getQuantity());
							planService.modifyPlanForSplit(p);
							sb.append(p.getId()+",");
//							System.out.println(p.getId()+"  end   planid  "+p.getSupplier().getId()+"  tempd.getSupplier() 000  "+p.getSupplier().getName()+"  detail Quantity   "+p.getQuantity());
						}else{
							
							addPlan.setPlanNum(p.getPlanNum());
							addPlan.setItemType(p.getItemType());
							addPlan.setItemsNum(p.getItemsNum());
							addPlan.setFacilityNameAndNum(p.getFacilityNameAndNum());
							addPlan.setItemsName(p.getItemsName());
							addPlan.setModelType(p.getModelType());
							addPlan.setMaterial(p.getMaterial());
							addPlan.setStandardNum(p.getStandardNum());
							addPlan.setUnit(p.getUnit());
							//添加供应商与分得的数量
							addPlan.setQuantity(p.getQuantity());
//							addPlan.setSupplier(tempd.getSupplier());
//							System.out.println(tempd.getSupplier().getId()+"  tempd.getSupplier()  "+tempd.getSupplier().getName()+"  detail Quantity   "+tempd.getSeekSourceQuantity());
							addPlan.setReportComp(p.getReportComp());
							addPlan.setReportCompId(p.getReportCompId());
							addPlan.setSupplier(tempd.getSupplier());
							addPlan.setProcurementPrice(tempd.getSeekSourcePrice());
							addPlan.setProcurementMoney(tempd.getSeekSourceM());
							addPlan.setContractNum(new Double(tempd.getSeekSourceQuantity()));
							addPlan.setProcurementWay(tempd.getSeekSource().getPc().getProcurementWay());
							
							
							addPlan.setPlanStatus(p.getPlanStatus());
							addPlan.setPerson(p.getPerson());
							addPlan.setManager(p.getManager());
							addPlan.setDirector(p.getDirector());
							addPlan.setPc(p.getPc());
							addPlan.setOpinion(p.getOpinion());
							
							addPlan.setBudgetPrice(p.getBudgetPrice());
							addPlan.setBudgetMoney(p.getBudgetMoney());
							addPlan.setDemandDept(p.getDemandDept());
							addPlan.setArrivalDate(p.getArrivalDate());
							addPlan.setCheckItemNum(p.getCheckItemNum());
							addPlan.setUseFor(p.getUseFor());
							addPlan.setOriginComp(p.getOriginComp());
							addPlan.setRemark(p.getRemark());
//							System.out.println(addPlan.getId()+"   before  planid  "+addPlan.getSupplier().getId()+"  tempd.getSupplier()  "+addPlan.getSupplier().getName()+"  detail Quantity   "+addPlan.getQuantity());
							planService.addPlans(addPlan);
							sb.append(addPlan.getId()+",");
//							System.out.println(addPlan.getId()+"   end  planid  "+addPlan.getSupplier().getId()+"  tempd.getSupplier()  "+addPlan.getSupplier().getName()+"  detail Quantity   "+addPlan.getQuantity());
						}
					}
				}
			}
//			String planIds = sb.toString();
//			planIds = planIds.substring(0,planIds.length()-1);
//			System.out.println(planIds);
		}else{
		//StringBuffer sb = new StringBuffer();
			for(SeekSource s : ss){
				ProcurementCheck pc = s.getPc();
				Set<Plan> ps = pc.getPlans();
				for(Plan p : ps){
					sb.append(p.getId()+",");
				}
			}
		}
		String planIds = sb.toString();
		planIds = planIds.substring(0,planIds.length()-1);
		seekSourceService.modifyPlansCheckStatus(seekSource.getCheckStatus(), planIds);
		List<Check> cs = new ArrayList<Check>();
		for(SeekSource s : ss){
			Check check = new Check();
			check.setSeekSource(s);
			check.setPerson(pe);
			check.setCheckDate(d);
			check.setOpinion(seekSource.getOpinion());
			check.setCheckStatus(s.getCheckStatus());
			check.setPlan(null);
			//03:已 寻源方案
			check.setCheckType("03");
			cs.add(check);
		}
		checkService.addChecks(cs);
		
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	
	public String seekSourceDSearch(){
//		System.out.println(materialName.length);
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		if(!"0".equals(seekSource.getCheckStatus())){
			params.put("in_checkStatus","'"+seekSource.getCheckStatus()+"'");
		}else{//modify by brown 20131014
			params.put("bdy_p.checkStatus","'02'");
		}
		//包括 主管末审核，内控末审核，内控末通过审核 delete by brown 20130724
//		params.put("in_checkStatus", "'01','08','09'");
		params.put("role", "d");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(seekSource.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(seekSource.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(seekSource.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(seekSource.getEndDate())+"'");
		}
		if(seekSource.getPc().getProcurementName() != null){
			params.put("mh_pc.procurementName", seekSource.getPc().getProcurementName());
		}
		if(seekSource.getPc().getCaseNum() != null){
			params.put("mh_pc.caseNum", seekSource.getPc().getCaseNum());
		}
		if(seekSource.getPc().getPackageNum() != null){
			params.put("mh_pc.packageNum", seekSource.getPc().getPackageNum());
		}
		if(seekSource != null && seekSource.getCheckNum() != 0){
			params.put("mh_checkNum", seekSource.getCheckNum());
		}
		params.put("seekSource.pc.procurementName", seekSource.getPc().getProcurementName());
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		PageModel pm = seekSourceService.serchSeekSourceByParams(params, offset,pageSize);
		List<SeekSource> ls = pm.getList();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
			return SUCCESS;
	}
	public String seekSourceISearch(){
//		System.out.println(materialName.length);
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		if(!"0".equals(seekSource.getCheckStatus())){
			params.put("in_checkStatus","'"+seekSource.getCheckStatus()+"'");
		}else{//modify by brown 20131014
			params.put("bdy_p.checkStatus","'09'");
		}
		//包括 内控末审核，经理末审核，经理末通过审核
//		params.put("in_checkStatus", "'08','03','04'");
		params.put("role", "i");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(seekSource.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(seekSource.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(seekSource.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(seekSource.getEndDate())+"'");
		}
		if(seekSource.getPc().getProcurementName() != null){
			params.put("mh_pc.procurementName", seekSource.getPc().getProcurementName());
		}
		if(seekSource.getPc().getCaseNum() != null){
			params.put("mh_pc.caseNum", seekSource.getPc().getCaseNum());
		}
		if(seekSource.getPc().getPackageNum() != null){
			params.put("mh_pc.packageNum", seekSource.getPc().getPackageNum());
		}
		if(seekSource != null && seekSource.getCheckNum() != 0){
			params.put("mh_checkNum", seekSource.getCheckNum());
		}
		params.put("seekSource.pc.procurementName", seekSource.getPc().getProcurementName());
		params.put("obj_user", (User) ServletActionContext.getRequest()
				.getSession().getAttribute("login"));
		PageModel pm = seekSourceService.serchSeekSourceByParams(params, offset,pageSize);
		List<SeekSource> ls = pm.getList();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	public String seekSourceLSearch(){
//		System.out.println(materialName.length);
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
//		if(!"0".equals(seekSource.getCheckStatus())){
//			params.put("in_checkStatus","'"+seekSource.getCheckStatus()+"'");
//		}else{//modify by brown 20131014
			params.put("eq_leaderStatus","01");
//		}
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		List<Integer> personIds = groupService.getBusinessGroupPersonIdsByLeaderId(personId);
		//包括 内控末审核，经理末审核，经理末通过审核
//		params.put("in_checkStatus", "'08','03','04'");
//		String pids = "";
		params.put("persons", personIds.toString().replace("[", "").replace("]", ""));
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(seekSource.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(seekSource.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(seekSource.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(seekSource.getEndDate())+"'");
		}
		if(seekSource.getPc().getProcurementName() != null){
			params.put("mh_pc.procurementName", seekSource.getPc().getProcurementName());
		}
		if(seekSource.getPc().getCaseNum() != null){
			params.put("mh_pc.caseNum", seekSource.getPc().getCaseNum());
		}
		if(seekSource.getPc().getPackageNum() != null){
			params.put("mh_pc.packageNum", seekSource.getPc().getPackageNum());
		}
		if(seekSource != null && seekSource.getCheckNum() != 0){
			params.put("mh_checkNum", seekSource.getCheckNum());
		}
		params.put("seekSource.pc.procurementName", seekSource.getPc().getProcurementName());
//		params.put("obj_user", (User) ServletActionContext.getRequest()
//				.getSession().getAttribute("login"));
		PageModel pm = seekSourceService.serchSeekSourceByParams(params, offset,pageSize);
		List<SeekSource> ls = pm.getList();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	public String seekSourceMSearch(){
//		System.out.println(materialName.length);
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		if(!"0".equals(seekSource.getCheckStatus())){
			params.put("in_checkStatus","'"+seekSource.getCheckStatus()+"'");
		}else{//modify by brown 20131014
			params.put("bdy_p.checkStatus","'04'");
		}
		//包括 经理末审核，董事长末审核,董事长末通过审核
//		params.put("in_checkStatus", "'03','05','06'");
		params.put("role", "m");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(seekSource.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(seekSource.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(seekSource.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(seekSource.getEndDate())+"'");
		}
		if(seekSource.getPc().getProcurementName() != null){
			params.put("mh_pc.procurementName", seekSource.getPc().getProcurementName());
		}
		if(seekSource.getPc().getCaseNum() != null){
			params.put("mh_pc.caseNum", seekSource.getPc().getCaseNum());
		}
		if(seekSource.getPc().getPackageNum() != null){
			params.put("mh_pc.packageNum", seekSource.getPc().getPackageNum());
		}
		if(seekSource != null && seekSource.getCheckNum() != 0){
			params.put("mh_checkNum", seekSource.getCheckNum());
		}
		params.put("seekSource.pc.procurementName", seekSource.getPc().getProcurementName());
		params.put("obj_user", (User) ServletActionContext.getRequest().getSession().getAttribute("login"));
		
		PageModel pm = seekSourceService.serchSeekSourceByParams(params, offset,pageSize);
		List<SeekSource> ls = pm.getList();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	public String seekSourceCSearch(){
//		System.out.println(materialName.length);
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		//包括 董事长末审核
		params.put("in_checkStatus", "'05'");
		params.put("role", "c");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(seekSource.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(seekSource.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(seekSource.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(seekSource.getEndDate())+"'");
		}
		params.put("obj_user", (User) ServletActionContext.getRequest().getSession().getAttribute("login"));
		if(seekSource.getPc().getProcurementName() != null){
			params.put("mh_pc.procurementName", seekSource.getPc().getProcurementName());
		}
		if(seekSource.getPc().getCaseNum() != null){
			params.put("mh_pc.caseNum", seekSource.getPc().getCaseNum());
		}
		if(seekSource.getPc().getPackageNum() != null){
			params.put("mh_pc.packageNum", seekSource.getPc().getPackageNum());
		}
		if(seekSource != null && seekSource.getCheckNum() != 0){
			params.put("mh_checkNum", seekSource.getCheckNum());
		}
		params.put("seekSource.pc.procurementName", seekSource.getPc().getProcurementName());
		PageModel pm = seekSourceService.serchSeekSourceByParams(params, offset,pageSize);
		List<SeekSource> ls = pm.getList();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	public String seekSourceSSearch(){
//		System.out.println(materialName.length);
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();

		if(!"0".equals(seekSource.getCheckStatus())){
			params.put("in_checkStatus","'"+seekSource.getCheckStatus()+"'");
		}
		//包括 主管末审核，经理末审核，经理末通过审核
//		params.put("in_checkStatus", "'01','02'");
//		params.put("role", "m");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(seekSource.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(seekSource.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(seekSource.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(seekSource.getEndDate())+"'");
		}
		if(seekSource.getPc().getCaseNum() != null){
			params.put("mh_pc.caseNum", seekSource.getPc().getCaseNum());
		}
		if(seekSource.getPc().getPackageNum() != null){
			params.put("mh_pc.packageNum", seekSource.getPc().getPackageNum());
		}
		if(seekSource != null && seekSource.getCheckNum() != 0){
			params.put("mh_checkNum", seekSource.getCheckNum());
		}
		params.put("obj_user", (User) ServletActionContext.getRequest().getSession().getAttribute("login"));
		
		PageModel pm = seekSourceService.serchSeekSourceByParams(params, offset,pageSize);
		List<SeekSource> ls = pm.getList();
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		return SUCCESS;
	}
	public String showDSeekSource(){
		System.out.println("ddddddd      "+seekSource.getId());
		seekSource = seekSourceService.getSeekSource(seekSource.getId());
		double sum = CalculateTotalMoney(seekSource);
		ServletActionContext.getRequest().setAttribute("totalMoney", sum);
		return SUCCESS;
	}
	public String showISeekSource(){
		System.out.println("iiiiiii      "+seekSource.getId());
		seekSource = seekSourceService.getSeekSource(seekSource.getId());
		double sum = CalculateTotalMoney(seekSource);
		ServletActionContext.getRequest().setAttribute("totalMoney", sum);
		return SUCCESS;
	}
	public String showMSeekSource(){
		System.out.println("mmmmmmm      "+seekSource.getId());
		seekSource = seekSourceService.getSeekSource(seekSource.getId());
		double sum = CalculateTotalMoney(seekSource);
		ServletActionContext.getRequest().setAttribute("totalMoney", sum);
		return SUCCESS;
	}
	public String showCSeekSource(){
		System.out.println("ccccccc      "+seekSource.getId());
		seekSource = seekSourceService.getSeekSource(seekSource.getId());
		double sum = CalculateTotalMoney(seekSource);
		ServletActionContext.getRequest().setAttribute("totalMoney", sum);
		return SUCCESS;
	}
	public double CalculateTotalMoney(SeekSource seekSource){

		double sum = 0.0;
		for(SeekSourceDetail detail : seekSource.getDetails()){
			sum = sum + detail.getSeekSourceM();
		}
		DecimalFormat df = new DecimalFormat("#.00");
		sum = Double.parseDouble(df.format(sum));
		return sum;
	}
	public String showSSeekSource(){
		System.out.println("ddddddd      "+seekSource.getId());
		seekSource = seekSourceService.getSeekSource(seekSource.getId());
		Map<Integer,String> supplierIdMap = new HashMap<Integer, String>();
		LinkedHashMap<Integer,List<SeekSourceDetail>> sourceMap = new LinkedHashMap<Integer, List<SeekSourceDetail>>();
//		Map<Integer,List<SeekSourceDetail>> sourceMap = new HashMap<Integer, List<SeekSourceDetail>>();
		for(SeekSourceDetail detail : seekSource.getDetails()){
			supplierIdMap.put(detail.getSupplier().getId(),detail.getSupplier().getName());
		}
		ProcurementCheck p = seekSource.getPc();
		List<Suppliers> slist = suservice.findSuppliersByIds(p.getSupplierIds());
		
		for(Integer supId : supplierIdMap.keySet()){
			LinkedList<SeekSourceDetail> details = new LinkedList<SeekSourceDetail>();
			for(SeekSourceDetail detail : seekSource.getDetails()){
				if(supId == detail.getSupplier().getId()){
					details.add(detail);
				}
			}
			sourceMap.put(supId, details);
		}
		List<Person> ps = personService.loadDirectorPersons();
		double sum = CalculateTotalMoney(seekSource);
		ServletActionContext.getRequest().setAttribute("totalMoney", sum);
		ServletActionContext.getRequest().setAttribute("dpersons", ps);
		ServletActionContext.getRequest().setAttribute("suppliers", slist);
		ServletActionContext.getRequest().setAttribute("detailMap", sourceMap);
		ServletActionContext.getRequest().setAttribute("supplierMap", supplierIdMap);
		return SUCCESS;
	}
	public String doModifySeekSource(){
//		System.out.println(materialName.length);
		System.out.println("seekSource.getId()   >   "+seekSource.getId());
		System.out.println("seekSource.getPc().getId()   >   "+seekSource.getPc().getId());
		System.out.println("seekSource.getDirector().getId()    >   "+seekSource.getDirector().getId());
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personId);
		
//		int len = planId.length;
//		Integer[] pids = new Integer[len];
//		for(int i = 0;i<len;i++){
//			pids[i] = Integer.parseInt(planId[i]);
//		}
		List<Plan> ps = planService.getPlans(planId);
		//ProcurementCheck pc = pcService.getProcurementCheck(seekSource.getPc().getId());
		Map<Integer,Plan> planMp = new HashMap<Integer, Plan>();
		for(Plan p : ps){
			planMp.put(p.getId(), p);
		}
		seekSource.setPerson(pe);
		seekSource.setSubmitDate(new Date());
		seekSource.setCheckStatus("01");
		Set<SeekSourceDetail> sets = new HashSet<SeekSourceDetail>();
		for(int i=0;i<seekSourceQuantity.length;i++){
			SeekSourceDetail d = new SeekSourceDetail();
			if(seekSourceQuantity[i] != null && seekSourceQuantity[i] != 0){
				if(detailIds[i] != 0){
					d.setId(detailIds[i]);
				}
				d.setMaterialName(materialName[i]);
				d.setSeekSourceM(seekSourceMoney[i]);
				d.setSeekSourcePrice(seekSourcePrice[i]);
				d.setSeekSourceQuantity(seekSourceQuantity[i]);
				d.setSupplier(suservice.findSuppliers(supplierIds[i]));
				d.setSeekSource(seekSource);
				d.setPlan(planMp.get(planId[i]));
				sets.add(d);
				System.out.println("quantity   "+i+"  "+seekSourceQuantity[i]+"  supplier id  "+supplierIds[i]);
			}
		}
		seekSource.setDetails(sets);
		seekSourceService.modifySeekSource(seekSource);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return SUCCESS;
	}
	public String delSeekSourceDetail(){
		System.out.println("tags   "+tags);
		System.out.println("seekSource.getId()   "+seekSource.getId());
		seekSourceService.deleteSeekSourceDetail(tags);
		return SUCCESS;
	}
}
