/**
 * 
 */
package com.shtz.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import antlr.StringUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.BusinessGroup;
import com.shtz.model.DistributeGroupRecord;
import com.shtz.model.Group;
import com.shtz.model.GroupPlan;
import com.shtz.model.Person;
import com.shtz.model.Plan;
import com.shtz.model.ProcurementCheck;
import com.shtz.model.Suppliers;
import com.shtz.model.User;
import com.shtz.service.GroupService;
import com.shtz.service.PersonService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.SuppliersService;
import com.shtz.util.PageModel;

/**
 * Filename : PlanGroupAction.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:24:24 - 2014-6-26
 *
 */
public class PlanGroupAction extends ActionSupport {
	private PlanService service;
	private PersonService personService;
	private ProcurementCheckService pcService;
	private GroupService groupService;
	private SuppliersService suservice;
	private Group group;
	private Plan plan;
	private String sDate;
	private String eDate;
	private List<Plan> plans;
	private String all_selected;
	private String now_selected;
	private String no_selected;
	private String tags;
	private String flag;
	private Integer[] pids;
	private Integer businessGroupId;
	private Integer personId;
	private ProcurementCheck pc;
	
	public void setSuservice(SuppliersService suservice) {
		this.suservice = suservice;
	}
	public ProcurementCheck getPc() {
		return pc;
	}
	public void setPc(ProcurementCheck pc) {
		this.pc = pc;
	}
	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}
	public Integer getBusinessGroupId() {
		return businessGroupId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public void setBusinessGroupId(Integer businessGroupId) {
		this.businessGroupId = businessGroupId;
	}
	public String getsDate() {
		return sDate;
	}
	public Integer[] getPids() {
		return pids;
	}
	public void setPids(Integer[] pids) {
		this.pids = pids;
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
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Plan getPlan() {
		return plan;
	}
	public List<Plan> getPlans() {
		return plans;
	}
	public String getAll_selected() {
		return all_selected;
	}
	public String getNow_selected() {
		return now_selected;
	}
	public String getNo_selected() {
		return no_selected;
	}
	public String getTags() {
		return tags;
	}
	public String getFlag() {
		return flag;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setService(PlanService service) {
		this.service = service;
	}
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	public void setAll_selected(String all_selected) {
		this.all_selected = all_selected;
	}
	public void setNow_selected(String now_selected) {
		this.now_selected = now_selected;
	}
	public void setNo_selected(String no_selected) {
		this.no_selected = no_selected;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String searchGroupPlans(){
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (plan !=null && plan.getReportComp() != null && !"".equals(plan.getReportComp()))
			params.put("reportComp", plan.getReportComp());
		if (plan !=null && plan.getGsperson() != null && !"".equals(plan.getGsperson()))
			params.put("gsperson", plan.getGsperson());
		if (plan !=null && plan.getPlanNum() != null && !"".equals(plan.getPlanNum()))
			params.put("planNum", plan.getPlanNum());
		params.put("in_groupStatus", "'01','02'");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) ) {
			params.put("dy_p.arrivalDate >=", " '" + sDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(eDate != null && !"".equals(eDate.trim())){
			params.put("dy_p.arrivalDate <= ", "'" + eDate + "'");
		}
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.serchsearchPlansByParams(params, offset,
				pageSize);
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		ServletActionContext.getRequest().setAttribute("groups", bgs);
//		this.setMyOffset(offset);

		return SUCCESS;
	}
	public String savePlanGroup(){
//		System.out.println(tags);
		String[] pids = tags.split(",");
		Integer[] ids = new Integer[pids.length];
		for(int i=0;i<pids.length;i++){
			ids[i] = Integer.parseInt(pids[i]);
		}
		List<Plan> ps = service.getPlans(ids);
		Group g = new Group();
		int personID = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personID);
		BusinessGroup bg = groupService.getBusinessGroupById(plan.getGroup().getId());

		DistributeGroupRecord dr = new DistributeGroupRecord();
		dr.setType("01");
		String pIds = "";
		
		Set<GroupPlan> gps = new HashSet<GroupPlan>();
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String day = sdf.format(d);
		Long dayCount = groupService.getDayCount(day);
		String groupNum = day+"-"+(dayCount+1);
		String groupName = bg.getGroupNum()+"-"+pids.length+"-"+groupNum;
//		System.out.println("groupNum   "+groupNum);
//		System.out.println("groupName   "+groupName);
		g.setGroupName(groupName);
		g.setGroupNum(groupNum);
		g.setAddPerson(pe);
		g.setGroup(bg);
		g.setStatus("01");
		try {
			g.setAddDate(sdf.parse(day));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Plan p : ps){
			GroupPlan gp = new GroupPlan();
			
			gp.setGroup(g);
			p.setGroupStatus("03");
			p.setGroup(g);
			gp.setPlan(p);
			gps.add(gp);
			pIds = pIds+","+p.getId();
//			System.out.println("p.getId()   "+p.getId());
		}
		dr.setPlanIds(pIds.substring(1));
		dr.setAddDate(new Date());
		dr.setDistributor(pe);
		g.setPlans(gps);
//		System.out.println("gps()   "+gps.size());
		groupService.addGroup(g);
		groupService.addDistributeGroupRecord(dr);
		return SUCCESS;
	}
	public String searchGroups(){
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (group !=null && group.getGroupNum() != null && !"".equals(group.getGroupNum()))
			params.put("groupNum", group.getGroupNum());
		if (group !=null && group.getGroupName() != null && !"".equals(group.getGroupName()))
			params.put("groupName", group.getGroupName());
		if (group !=null && group.getGroup() !=null && group.getGroup().getId() != 0)
			params.put("id_group.id", group.getGroup().getId());
		//01 待审批   04主管领导未通过
		params.put("in_status", "'01','04'");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null && !"".equals(eDate.trim())) {
			params.put("dy_addDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = groupService.serchGroupsByParams(params, offset,
				pageSize);
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		ServletActionContext.getRequest().setAttribute("groups", bgs);
//		this.setMyOffset(offset);

		return SUCCESS;
	}
	public String searchDirectGroups(){
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (group !=null && group.getGroupNum() != null && !"".equals(group.getGroupNum()))
			params.put("groupNum", group.getGroupNum());
		if (group !=null && group.getGroupName() != null && !"".equals(group.getGroupName()))
			params.put("groupName", group.getGroupName());
		if (group !=null && group.getGroup() !=null && group.getGroup().getId() != 0)
			params.put("id_group.id", group.getGroup().getId());
		// 03  主管领导未审批    06 总经理未通过
		params.put("in_status", "'03','06'");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null && !"".equals(eDate.trim())) {
			params.put("dy_addDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = groupService.serchGroupsByParams(params, offset,
				pageSize);
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		ServletActionContext.getRequest().setAttribute("groups", bgs);
//		this.setMyOffset(offset);
		
		return SUCCESS;
	}
	public String searchManagerGroups(){
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		int pageSize = 50;
		Map params = new HashMap();
		if (group !=null && group.getGroupNum() != null && !"".equals(group.getGroupNum()))
			params.put("groupNum", group.getGroupNum());
		if (group !=null && group.getGroupName() != null && !"".equals(group.getGroupName()))
			params.put("groupName", group.getGroupName());
		if (group !=null && group.getGroup() !=null && group.getGroup().getId() != 0)
			params.put("id_group.id", group.getGroup().getId());
		
		// 05  总经理未审批
		params.put("in_status", "'05'");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null && !"".equals(eDate.trim())) {
			params.put("dy_addDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = groupService.serchGroupsByParams(params, offset,
				pageSize);
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		ServletActionContext.getRequest().setAttribute("groups", bgs);
//		this.setMyOffset(offset);
		
		return SUCCESS;
	}
	public String loadGroup(){
		
		if(group !=  null && group.getId() != 0){
			group = groupService.getGroupById(group.getId());
		}
		if("d".equals(flag)){
			return "direct_success";
		}else if("m".equals(flag)){
			return "manager_success";
		}else{
			return SUCCESS;
		}
	}
	public String initSetPlanOperator(){
		System.out.println("businessGroupId     "+businessGroupId);
		List<Person> ps = personService.loadBusinessGroupPersons(businessGroupId,"plan");
		ServletActionContext.getRequest().setAttribute("persons", ps);
//		if(group !=  null && group.getId() != 0){
//			group = groupService.getGroupById(group.getId());
//		}
		
		return SUCCESS;
	}
	public String initSetProcurementOperator(){
		System.out.println("businessGroupId     "+businessGroupId);
		List<Person> ps = personService.loadBusinessGroupPersons(businessGroupId,"seekSource");
		ServletActionContext.getRequest().setAttribute("persons", ps);
		return SUCCESS;
	}
	public String initProcurementOperatorPage(){
		int leaderId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		if(leaderId != 0){
			BusinessGroup bg = groupService.getBusinessGroupByLeaderId(leaderId);
			if(bg != null){
				businessGroupId = bg.getId();
			}
		}
		return SUCCESS;
	}
	public String initSetProcurementPage(){

		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("groups", bgs);
		
		return SUCCESS;
	}
	public String saveProcurementGroup(){
//		System.out.println(tags);
		String[] pids = tags.split(",");
		Integer[] ids = new Integer[pids.length];
		for(int i=0;i<pids.length;i++){
			ids[i] = Integer.parseInt(pids[i]);
		}
		List<ProcurementCheck> ps = pcService.getPCs(tags);
		Group g = new Group();
		int personID = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person pe = personService.findPerson(personID);
		BusinessGroup bg = groupService.getBusinessGroupById(pc.getGroup().getId());
		DistributeGroupRecord dr = new DistributeGroupRecord();
		dr.setType("02");
		String pcIds = "";
		for(ProcurementCheck p : ps){
			 // 03:已分组
			p.setGroupStatus("03");
			p.setGroup(bg);
			pcIds = pcIds+","+p.getId();
//			System.out.println("p.getId()   "+p.getId());
			pcService.modifyProcurementCheckGroup(p);
		}
		dr.setPcIds(pcIds.substring(1));
		dr.setAddDate(new Date());
		dr.setDistributor(pe);
		groupService.addDistributeGroupRecord(dr);
		return SUCCESS;
	}
	public String modifyGroupProcurementsPerson() throws Exception {
		// tags.split(",");
		System.out.println("personId  --  " + personId );
		System.out.println("tags  --  " + tags);
		
		pcService.modifyProcurementOperatorByIds(tags, personId);
		
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		// if(modifyFlag !=null && modifyFlag.equals("seekSource"))
		// return "seekSource_success";
		return "success";
	}
	public String searchOperatorProcurements(){

		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
//		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		//07 董事长己审核
		params.put("eq_checkStatus","07");
		//groupStatus 未分组  已退回
		params.put("eq_groupStatus","03");
		params.put("id_group.id", businessGroupId);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc != null && pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc != null && pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		if(pc != null && pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		if(pc != null && pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc != null && pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		if(pc != null && pc.getProcurementName() != null){
			params.put("pc.procurementName", pc.getProcurementName());
		}
//		params.put("obj_user", (User) ServletActionContext.getRequest()
//				.getSession().getAttribute("login"));
//		params.put("role", "d");
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			String idnames = "";
			if(p.getSupplierIds()!=null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				idnames = sb.toString();
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}else{
				p.setSupplierNames(idnames);
			}
			
			
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		
		return SUCCESS;
	}
	public String searchGroupProcurements(){

		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("fen ye error");
		}
//		this.setMyOffset(offset);
		int pageSize = 50;
		Map params = new HashMap();
		//07 董事长己审核
		params.put("eq_checkStatus","07");
		//groupStatus 未分组  已退回
		params.put("in_groupStatus","'01','02'");
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		if(pc != null && pc.getBeginDate() != null) {
			params.put("dy_p.submitDate", " > '" + sd.format(pc.getBeginDate())+"'" );
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		if(pc != null && pc.getEndDate() != null){
			params.put("dy_p.submitDate", " < '" + sd.format(pc.getEndDate())+"'");
		}
		if(pc != null && pc.getProcurementName() != null){
			params.put("mh_procurementName", pc.getProcurementName());
		}
		if(pc != null && pc.getCaseNum() != null){
			params.put("mh_caseNum", pc.getCaseNum());
		}
		if(pc != null && pc.getPackageNum() != null){
			params.put("mh_packageNum", pc.getPackageNum());
		}
		if(pc != null && pc.getProcurementName() != null){
			params.put("pc.procurementName", pc.getProcurementName());
		}
//		params.put("obj_user", (User) ServletActionContext.getRequest()
//				.getSession().getAttribute("login"));
//		params.put("role", "d");
		PageModel pm = pcService.serchProcurementCheckByParams(params, offset,pageSize);
		List<ProcurementCheck> ls = pm.getList();
		
		List slist = suservice.getSupplierslist();
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		for(ProcurementCheck p : ls){
			String idnames = "";
			if(p.getSupplierIds()!=null && !"".equals(p.getSupplierIds())){
				String[] supplierIds = p.getSupplierIds().split(",");
				StringBuffer sb = new StringBuffer();
				for(int i=0;i < supplierIds.length ; i++){
					sb.append(idAndName.get(Integer.parseInt(supplierIds[i].trim()))+",");
				}
				idnames = sb.toString();
				p.setSupplierNames(idnames.substring(0,idnames.length()-1));
			}else{
				p.setSupplierNames(idnames);
			}
			
		}
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
		
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("groups", bgs);
		
		return SUCCESS;
	}
	public String initSetPlanPage(){
		int leaderId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		if(leaderId != 0){
			BusinessGroup bg = groupService.getBusinessGroupByLeaderId(leaderId);
			if(bg != null){
//				List<Person> ps = personService.loadBusinessGroupPersons(businessGroupId);
//				ServletActionContext.getRequest().setAttribute("persons", ps);
				businessGroupId = bg.getId();
			}
		}
		
		return SUCCESS;
	}
	public String doCheckGroupPlans(){
		System.out.println(group.getStatus());
		String remark  = group.getRemark();
		String status  = group.getStatus();
		group = groupService.getGroupById(group.getId());
		if(group !=  null && "02".equals(status)){
			System.out.println(group.getPlans().size());
			for(GroupPlan gp : group.getPlans()){
				Plan p = gp.getPlan();
				p.setGroupStatus("02");
				//manger have checked
				p.setCheckStatus("05");
				p.setGroup(null);
				service.modifyPlan(p);
			}
			group.setStatus("02");
			groupService.modifyGroup(group);
		}else if(group !=  null && "03".equals(status)){
			/*for(GroupPlan gp : group.getPlans()){
				Plan p = gp.getPlan();
				// when general manager pass then it should be set 04  _20140821
//				p.setGroupStatus("04");
//				p.setGroup(null);
//				service.modifyPlan(p);
			}*/
			group.setStatus("03");
			group.setRemark(remark);
			groupService.modifyGroup(group);
		}
		
		return SUCCESS;
	}
	public String doDirectCheckGroupPlans(){
		System.out.println(group.getStatus());
		String remark  = group.getRemark();
		String status  = group.getStatus();
		group = groupService.getGroupById(group.getId());
		if(group !=  null && "04".equals(status)){
			System.out.println(group.getPlans().size());
			for(GroupPlan gp : group.getPlans()){
				Plan p = gp.getPlan();
				p.setGroupStatus("02");
				//manger have checked
//				p.setCheckStatus("05");
//				p.setGroup(null);
				service.modifyPlan(p);
			}
			//04主管领导未通过
			group.setStatus("04");
			groupService.modifyGroup(group);
		}else if(group !=  null && "05".equals(status)){
			/*for(GroupPlan gp : group.getPlans()){
				Plan p = gp.getPlan();
				// when general manager pass then it should be set 04  _20140821
//				p.setGroupStatus("04");
//				p.setGroup(null);
//				service.modifyPlan(p);
			}*/
			//05 总经理未审批
			group.setStatus("05");
			group.setRemark(remark);
			groupService.modifyGroup(group);
		}
		
		return SUCCESS;
	}
	public String doGeneralManagerCheckGroupPlans(){
		System.out.println(group.getStatus());
		String remark  = group.getRemark();
		String status  = group.getStatus();
		group = groupService.getGroupById(group.getId());
		if(group !=  null && "06".equals(status)){
			System.out.println(group.getPlans().size());
			for(GroupPlan gp : group.getPlans()){
				Plan p = gp.getPlan();
				p.setGroupStatus("02");
				//manger have checked
//				p.setCheckStatus("05");
//				p.setGroup(null);
				service.modifyPlan(p);
			}
			//06 总经理未通过
			group.setStatus("06");
			groupService.modifyGroup(group);
		}else if(group !=  null && "07".equals(status)){
			for(GroupPlan gp : group.getPlans()){
				Plan p = gp.getPlan();
				// when general manager pass then it should be set 04  _20140821
				p.setGroupStatus("04");
//				p.setGroup(null);
//				service.modifyPlan(p);
			}
			//07  总经理已通过
			group.setStatus("07");
			group.setRemark(remark);
			groupService.modifyGroup(group);
		}
		
		return SUCCESS;
	}
	
	public String searchOperatorPlans() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest()
					.getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页异常");
		}
		System.out.println("businessGroupId    "+businessGroupId);
		int pageSize = 50;
		Map params = new HashMap();
		if (plan != null && plan.getPlanNum() != null && !"".equals(plan.getPlanNum()))
			params.put("planNum", plan.getPlanNum());
//		params.put("eq_planStatus", "01");
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		if (sDate != null && !"".equals(sDate.trim()) && eDate != null
				&& !"".equals(eDate.trim())) {
			params.put("dy_p.arrivalDate", " between '" + sDate + "' and '"
					+ eDate + "'");
			// params.put("dy_p.planReceiptDate", " <='"+eDate+"' ");
		}
		params.put("in_group.status", "'07'");
		params.put("null_person.id", "");
		params.put("id_group.group.id", businessGroupId);
		//导入后主管设置业务员状态为04,05
//		params.put("in_checkStatus", "'04','05'");
		// (User)ServletActionContext.getRequest().getSession().getAttribute("login"));
		PageModel pm = service.searchOperatorPlans(params, offset,
				pageSize);
//		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);
//		ServletActionContext.getRequest().setAttribute("groups", bgs);
		return SUCCESS;
	}
	public String modifyGroupPlansPerson() throws Exception {
		// tags.split(",");
		System.out.println("personId  --  " + personId );
		System.out.println("tags  --  " + tags);
		
		service.modifyPlanOperatorByIds(tags, personId);
		
		ServletActionContext.getRequest().setAttribute("cflag", "success");
		// if(modifyFlag !=null && modifyFlag.equals("seekSource"))
		// return "seekSource_success";
		return "success";
	}
	public String LoadProcurementCheck() throws Exception {
		pc = pcService.getProcurementCheck(pc.getId());
		List slist = suservice.getSupplierslist();
		String[] supplierIds = pc.getSupplierIds().split(",");
		Map<Integer ,String> idAndName = new HashMap<Integer, String>();
		for(Iterator it = slist.iterator();it.hasNext();){
			Suppliers s = (Suppliers)it.next();
			idAndName.put(s.getId(), s.getName());
		}
		Map<Integer,String> supplierMp = new HashMap<Integer, String>();
		for(int i=0;i < supplierIds.length ; i++){
			supplierMp.put(Integer.parseInt(supplierIds[i].trim()), idAndName.get(Integer.parseInt(supplierIds[i].trim())));
		}
		ServletActionContext.getRequest().setAttribute("supplierMp", supplierMp);
		ServletActionContext.getRequest().setAttribute("suppliers", slist);
		return SUCCESS;
	}
}
