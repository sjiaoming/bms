package com.shtz.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.BusinessGroup;
import com.shtz.model.Organization;
import com.shtz.model.Person;
import com.shtz.service.GroupService;
import com.shtz.service.OrganizationService;
import com.shtz.service.PersonService;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class PersonAction extends ActionSupport {
	
	private PersonService service;
	
	private GroupService groupService;
	
	private OrganizationService oservice;
	
	private int id;
	
	private int planGroupId;
	
	private int planManagerId;
	
	private int seekSourceGroupId;

	private String name;

	private String sex;

	private int orgId;

	private String duty;

	private String address;

	private String email;

	private String phone;

	private String description;
	
	private int snid;
	
	private int personId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPlanGroupId() {
		return planGroupId;
	}
	public void setPlanGroupId(int planGroupId) {
		this.planGroupId = planGroupId;
	}
	public int getSeekSourceGroupId() {
		return seekSourceGroupId;
	}
	public void setSeekSourceGroupId(int seekSourceGroupId) {
		this.seekSourceGroupId = seekSourceGroupId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public void setOservice(OrganizationService oservice) {
		this.oservice = oservice;
	}
	public int getSnid() {
		return snid;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setSnid(int snid) {
		this.snid = snid;
	}


	public int getPlanManagerId() {
		return planManagerId;
	}
	public void setPlanManagerId(int planManagerId) {
		this.planManagerId = planManagerId;
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setService(PersonService service) {
		this.service = service;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public int getOrgId() {
		return orgId;
	}


	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}


	public String getDuty() {
		return duty;
	}


	public void setDuty(String duty) {
		this.duty = duty;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("分页 异常 ");
		}
		int pageSize = 15;
		Map params = new HashMap();
		if(orgId!=0)
			params.put("id_org", orgId);
		if(name!=null)
			params.put("name", name);
		
		PageModel pm = service.serchPersionByParams(params, offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pm);
		ServletActionContext.getRequest().setAttribute("params", params);

		return SUCCESS;
	}
	
	public String initPerson() throws Exception{
//		System.out.println(snid);
		ServletActionContext.getRequest().getSession().setAttribute("snid", snid);
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("groups", bgs);
		//List<Organization>  orgns= oservice.findLevel1Organization();
		//ServletActionContext.getRequest().setAttribute("orgns", orgns);
		return "initperson_success";
	}
	public String initAddPerson() throws Exception {
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		ServletActionContext.getRequest().setAttribute("groups", bgs);
		return SUCCESS;
	}
	public String add() throws Exception {
		
		Person person = new Person();
		person.setName(name);
		person.setAddress(address);
		person.setDescription(description);
		person.setEmail(email);
		person.setDuty(duty);
		person.setPhone(phone);
		person.setSex(sex);

		if(planGroupId != 0){
			BusinessGroup pg = groupService.getBusinessGroupById(planGroupId);
			person.setPlanGroup(pg);
		}
		if(planManagerId != 0){
			BusinessGroup pg = groupService.getBusinessGroupById(planManagerId);
			person.setPlanManagerGroup(pg);
		}
		if(seekSourceGroupId != 0){
			BusinessGroup sg = groupService.getBusinessGroupById(seekSourceGroupId);
			person.setSeekSourceGroup(sg);
		}
		
		service.addPerson(person, orgId);
		//this.execute();
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return "add_success";
	}
	public String delete() throws Exception{
		service.deletePerson(id);
		return "del_success";
	}
	public String add_input() throws Exception {
		
		
		
		return SUCCESS;
	}
	public String modify_person_input() throws Exception {
		Person person=service.findPerson(personId);
		List<Organization> organizations=oservice.findAllOrganization();
		List<BusinessGroup> bgs = groupService.loadBusinessGroup();
		
		ServletActionContext.getRequest().setAttribute("person", person);
		ServletActionContext.getRequest().setAttribute("organizations", organizations);
		ServletActionContext.getRequest().setAttribute("groups", bgs);
		return "modify_person_input_success";
		}
	public String update_person() throws Exception {
		Person person=new Person();
		person.setId(id);
		person.setName(name);
		person.setSex(sex);
		person.setOrg(oservice.findOrganization(orgId));
		person.setDuty(duty);
		person.setEmail(email);
		person.setAddress(address);
		person.setPhone(phone);
		person.setDescription(description);

		if(planGroupId != 0){
			BusinessGroup pg = groupService.getBusinessGroupById(planGroupId);
			person.setPlanGroup(pg);
		}
		if(planManagerId != 0){
			BusinessGroup pg = groupService.getBusinessGroupById(planManagerId);
			person.setPlanManagerGroup(pg);
		}
		if(seekSourceGroupId != 0){
			BusinessGroup sg = groupService.getBusinessGroupById(seekSourceGroupId);
			person.setSeekSourceGroup(sg);
		}
		
		service.modifyPerson(person);
		ServletActionContext.getRequest().setAttribute("cflag", "addSuccess");
		return "update_person_success";
		}
		
	
}
