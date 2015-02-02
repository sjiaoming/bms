/**
 * 
 */
package com.shtz.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Person;
import com.shtz.service.GroupService;
import com.shtz.service.PersonService;
import com.shtz.service.PlanService;
import com.shtz.service.ProcurementCheckService;
import com.shtz.service.ProcurementContractService;
import com.shtz.service.SalesContractService;
import com.shtz.service.SeekSourceService;

/**
 * Filename : MessageAction.java
 *
 * @author yao chang
 *
 * Creation time : 下午11:20:01 - 2014-7-17
 *
 */
public class MessageAction extends ActionSupport {
	private PlanService planService;
	private ProcurementCheckService pcService;
	private SeekSourceService seekSourceService;
	private ProcurementContractService pccService;
	private SalesContractService scService;
	private GroupService groupService;
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public void setPlanService(PlanService planService) {
		this.planService = planService;
	}

	public void setPcService(ProcurementCheckService pcService) {
		this.pcService = pcService;
	}

	public void setSeekSourceService(SeekSourceService seekSourceService) {
		this.seekSourceService = seekSourceService;
	}

	public void setPccService(ProcurementContractService pccService) {
		this.pccService = pccService;
	}

	public void setScService(SalesContractService scService) {
		this.scService = scService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int personId = (Integer) ServletActionContext.getRequest().getSession().getAttribute("pid");
		Person p = personService.findPerson(personId);
		String roleName = personService.getPersonRoleName(personId);
		//计划业务组
		//计划主管==以前的部门经理
		if(roleName.contains("计划主管")){
			
			Integer uncheckPlanCount = planService.getUnGroupCountByRole(personId, "d");
			ServletActionContext.getRequest().setAttribute("PlanDirectCheckGroup", "PlanDirectCheckGroup");
			ServletActionContext.getRequest().setAttribute("uncheckPlanCount", uncheckPlanCount);
			
		}else if(roleName.contains("计划业务员") ){
			Integer unDistributionPlanCount = planService.getUnGroupCountByRole(0, null);
			ServletActionContext.getRequest().setAttribute("PlanOperatorSetGroup", "PlanOperatorSetGroup");
			ServletActionContext.getRequest().setAttribute("unDistributionPlanCount", unDistributionPlanCount);
		}else if(roleName.contains("采购业务员") ){
			Integer pccCount = pccService.getPCCountByPersonId(personId);
			Integer pcCount = pcService.getPCCountByPersonId(personId);
			Integer scCount = scService.getSCCountByPersonId(personId);
			
			ServletActionContext.getRequest().setAttribute("BusinessOperatorGroup", "BusinessOperatorGroup");
			ServletActionContext.getRequest().setAttribute("pccCount", pccCount);
			ServletActionContext.getRequest().setAttribute("pcCount", pcCount);
			ServletActionContext.getRequest().setAttribute("scCount", scCount);
		}else if(roleName.contains("采购主管") ){
			String role = "d";
			Integer rolePCCount = pcService.getPCCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("PcDCheck", "PcDCheck");
			ServletActionContext.getRequest().setAttribute("PcDCheckPcCount", rolePCCount);
			
			Integer setOperatorForPlanCount = planService.getUnGroupCountByRole(personId, "dPcSetOperator");
			ServletActionContext.getRequest().setAttribute("PcDirectorSetOperator", "PcDirectorSetOperator");
			ServletActionContext.getRequest().setAttribute("setOperatorForPlanCount", setOperatorForPlanCount);
			//内控==分管领导
		}else if(roleName.contains("内控") ){
			String role = "i";
			Integer rolePCCount = pcService.getPCCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("PcICheck", "PcICheck");
			ServletActionContext.getRequest().setAttribute("PcICheckPc", rolePCCount);
			
			Integer uncheckPlanCount = planService.getUnGroupCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("PlanInternalGroup", "PlanInternalGroup");
			ServletActionContext.getRequest().setAttribute("uncheckPlanGroupICount", uncheckPlanCount);
		}else if(roleName.contains("经理") ){
			String role = "m";
			Integer rolePCCount = pcService.getPCCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("PcMCheck", "PcMCheck");
			ServletActionContext.getRequest().setAttribute("PcMCheckPc", rolePCCount);
			
			Integer uncheckPlanCount = planService.getUnGroupCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("PlanManagerGroup", "PlanManagerGroup");
			ServletActionContext.getRequest().setAttribute("uncheckPlanGroupMCount", uncheckPlanCount);
		}else if(roleName.contains("董事长") ){
			String role = "c";
			Integer rolePCCount = pcService.getPCCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("PcCCheck", "PcCCheck");
			ServletActionContext.getRequest().setAttribute("PcCCheckPc", rolePCCount);
		}else if(roleName.contains("寻源业务员") ){
			Integer ssOperatorCount = seekSourceService.getSSCountByRoleAndPersonId(personId,null);
			ServletActionContext.getRequest().setAttribute("SeekSourceOperator", "SeekSourceOperator");
			ServletActionContext.getRequest().setAttribute("ssOperatorCount", ssOperatorCount);
		}else if(roleName.contains("寻源主管") ){
			Integer unGroupCount = seekSourceService.getSSCountByRoleAndPersonId(personId,"dunGroup");
			Integer ssOperatorCount = seekSourceService.getSSCountByRoleAndPersonId(personId,"dsetSeekSourceOperator");
			Integer dSeekSourceCheckCount = seekSourceService.getSSCountByRoleAndPersonId(personId,"dSeekSourceCheck");
			ServletActionContext.getRequest().setAttribute("SeekSourceDirector", "SeekSourceDirector");
			ServletActionContext.getRequest().setAttribute("unGroupCount", unGroupCount);
			ServletActionContext.getRequest().setAttribute("dsetSeekSourceOperator", ssOperatorCount);
			ServletActionContext.getRequest().setAttribute("dSeekSourceCheckCount", dSeekSourceCheckCount);
		}
		//采购业务组
		/*
		if(p.getPlanGroup() != null){
			Integer pccCount = pccService.getPCCountByPersonId(personId);
			Integer pcCount = pcService.getPCCountByPersonId(personId);
			Integer scCount = scService.getSCCountByPersonId(personId);
			
			ServletActionContext.getRequest().setAttribute("type", "PlanGroup");
			ServletActionContext.getRequest().setAttribute("pccCount", pccCount);
			ServletActionContext.getRequest().setAttribute("pcCount", pcCount);
			ServletActionContext.getRequest().setAttribute("scCount", scCount);
		}else if(p.getSeekSourceGroup() != null ){
			Integer sscCount = seekSourceService.getSSCountByPersonId(personId);
			ServletActionContext.getRequest().setAttribute("type", "SeekSourceGroup");
			ServletActionContext.getRequest().setAttribute("sscCount", sscCount);
		}*/
		else{
			String role = "";
			if(roleName.contains("主管")){
				role = "d";
			}else if(roleName.contains("内控")){
				role = "i";
			}else if(roleName.contains("经理")){
				int groupCount = groupService.getGroupCount();
				ServletActionContext.getRequest().setAttribute("groupCount", groupCount);
				role = "m";
			}else if(roleName.contains("董事长")){
				role = "c";
			}else{
				role = "null";
			}
			Integer rolePCCount = pcService.getPCCountByRole(personId, role);
			ServletActionContext.getRequest().setAttribute("role", role);
			ServletActionContext.getRequest().setAttribute("rolePCCount", rolePCCount);
		}
		return SUCCESS;
	}

}
