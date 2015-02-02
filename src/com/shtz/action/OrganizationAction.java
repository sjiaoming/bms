package com.shtz.action;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shtz.model.Organization;
import com.shtz.service.OrganizationService;
import com.shtz.util.ExcelCreate;
import com.shtz.util.PageModel;

/**
 * @author sjm
 *  
 */
public class OrganizationAction extends ActionSupport {
	
	private OrganizationService oservice;
	
	private PageModel pageModel;
	
	//private PersonService pservice;
	
	//private RoleService rservice;
	
	private int id;


	private String password;

	private Date createTime;

	private Date expireTime;
	
	private int personId;
	
	private int roleId;
	
	private int orderNum;
	
	
	private int parentId;
	
	private String name;
	
	private String description;
	
	private int organizationId;
	
	private String sn;
	
	private String modify_parentId;
	
	private Organization org;
	
	private List<Organization> allParentOrg;
	
	private String newname;
	private String newsn;
	private String newdescription;
	private int snid;
	public int getSnid() {
		return snid;
	}
	public void setSnid(int snid) {
		this.snid = snid;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getNewsn() {
		return newsn;
	}
	public void setNewsn(String newsn) {
		this.newsn = newsn;
	}
	public String getNewdescription() {
		return newdescription;
	}
	public void setNewdescription(String newdescription) {
		this.newdescription = newdescription;
	}
	public Organization getOrg() {
		return org;
	}
	public void setOrg(Organization org) {
		this.org = org;
	}
	public List<Organization> getAllParentOrg() {
		return allParentOrg;
	}
	public void setAllParentOrg(List<Organization> allParentOrg) {
		this.allParentOrg = allParentOrg;
	}
	public String getModify_parentId() {
		return modify_parentId;
	}
	public void setModify_parentId(String modifyParentId) {
		modify_parentId = modifyParentId;
	}
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}
	
	public void setOservice(OrganizationService oservice) {
		this.oservice = oservice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public String execute() throws Exception {
		System.out.println(snid);
		//ServletActionContext.getRequest().getSession().setAttribute("snid", snid);
		//List<Organization>  orgns= oservice.findLevel1Organization();
		//ServletActionContext.getRequest().setAttribute("orgns", orgns);
		return SUCCESS;
	}
	public String findOrganizationAndChild() throws Exception {

		
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			System.out.println("分页异常");
		}
		int pageSize = 15;
		Map params = new HashMap();
		//if(parentId!=0)
			params.put("id_parent.id", parentId);
		if(name!=null&&!name.trim().equals(""))
			params.put("name", name);
		
		pageModel = oservice.findOrganizationByParams(params, offset, pageSize);

		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		ServletActionContext.getRequest().setAttribute("params", params);
		ServletActionContext.getRequest().setAttribute("parentId", parentId);
		
		return "success";
	}
	
		public String add_organization() throws Exception {
			Organization org=new Organization();
			org.setName(newname);
			org.setDescription(description);		
			oservice.addOrg(org, parentId);
			this.findOrganizationAndChild();
			return "add_success";
		}
		public String new_organization() throws Exception {
			Organization org1=new Organization();
			org1.setName(newname);
			org1.setSn(newsn);
			org1.setDescription(newdescription);
			oservice.addNewOrg(org1);
			
			//this.findOrganizationAndChild();
			ServletActionContext.getRequest().setAttribute("cflag", "success");
			
			return "addnew_success";
		}
		
		
		public String modify_org_input()throws Exception{
			org=oservice.findOrganization(organizationId);
			System.out.println(org.getId());
			System.out.println(org.getName());
			
			allParentOrg=oservice.findLevel1Organization();
			ServletActionContext.getRequest().setAttribute("organization", org);
			ServletActionContext.getRequest().setAttribute("allParentOrg", allParentOrg);
			return "modify_input_success";
		}
		
		public String update_organization()throws Exception{
			Organization organization=new Organization();
			organization.setId(organizationId);
			organization.setName(name);
			organization.setDescription(description);
			organization.setSn(sn);
			if(modify_parentId!=null){
				Organization newOrganization=oservice.findOrganization(Integer.parseInt(modify_parentId));
				organization.setParent(newOrganization);
				organization.setSn(newOrganization==null?""+organizationId:newOrganization.getSn()+"_"+organizationId);	
			}
			oservice.modifyOrg(organization);
			//this.findOrganizationAndChild();
			ServletActionContext.getRequest().setAttribute("cflag", "success");
			return "success";
		}
		public String del_organization()throws Exception{
			oservice.deleteOrg(organizationId);
			this.findOrganizationAndChild();
			return "del_success";
		}
		public String toNewOrg()throws Exception{
			return "toNewOrg";
		}
/*	public String executebak() throws Exception {
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("��ҳ offset ����ת���쳣");
		}
		int pageSize = 5;
		pageModel = oservice.searchPersons(offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		

		return SUCCESS;
	}*/
	/*public String add_user() throws Exception {
		
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		u.setExpireTime(expireTime);
		
		uservice.addUser(u, personId);
		this.execute();
		return "add_success";
	}
	public String del_user() throws Exception {
		
		uservice.deleteUser(id);
		this.execute();
		return "del_success";
		
	}
	public String modify_user() throws Exception {
		User u = new User();
		u.setId(id);
		u.setUsername(username);
		u.setPassword(password);
		u.setExpireTime(expireTime);
		uservice.modifyUser(u, personId);
		
		return "modify_success";
	}
	public String userRole() throws Exception {
		
		User user = uservice.findUser(id);
		
		List<UsersRoles> urs = uservice.findUserRoles(id);
		
		ServletActionContext.getRequest().setAttribute("user", user);
		
		ServletActionContext.getRequest().setAttribute("urs", urs);
		
		return SUCCESS;
		
	}
	public String toUserRole() throws Exception {
		
		int offset = 0;
		try {
			offset = Integer.parseInt(ServletActionContext.getRequest().getParameter("pager.offset"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("��ҳ offset ����ת���쳣");
		}
		int pageSize = 5;
		pageModel = rservice.findRoles(offset, pageSize);
		
		ServletActionContext.getRequest().setAttribute("pm", pageModel);
		
		ServletActionContext.getRequest().setAttribute("id", id);
		return SUCCESS;
	}
	public String add_userRole() throws Exception {
		
		uservice.addOrModityUserRole(id, roleId, orderNum);
		
		return "add_success";
	}
	public String del_userRole() throws Exception {
		System.out.println("--------------------");
		uservice.deleteUserRole(id, roleId);
		
		return "del_success";
	}
	*/
		
		public String exportEx() throws Exception{
			
			

			
			List<String> list = new ArrayList<String>();
			list.add("员工号");
			list.add("姓名");
			list.add("出生日前");
			list.add("工作地点");
			list.add("职务");
			list.add("性别");
			list.add("那裡");

			ExcelCreate s = new ExcelCreate();
			s.createSheet("系统报名表");
			s.addHeader(list, true);
			//s.addHeader(list, false);

			List lists = null;
			for (int i = 0; i < 6; i++) {

				lists = new ArrayList();
				lists.add("A000" + i);
				if(i==1){
				   lists.add("赵云");
				}
				else if(i==2){
					 lists.add("关羽");
				}
				else if(i==3){
					 lists.add("张飞");
				}
				else if(i==4){
					 lists.add("什么");
				}
				else if(i==5){
					 lists.add("那个");
				}
				lists.add("6500444444444444444444444444444444 ");
//				new SimpleDateFormat("yyyy-M-d").format(new Date())
				lists.add(new Date());
				lists.add("生死战" + i);
				lists.add("男" + i);
				lists.add("常山的" + i);
				s.addRow(lists);
			}
			List lists1 = new ArrayList();
			lists1.add("五虎上将之一");
			lists1.add("赵云");
			lists1.add("6500");
			lists1.add("2010-9-1");
			lists1.add("生死战");
			lists1.add("男sdfdsf");
			lists1.add("weher");
			

			s.insertRow(lists1, 3);
			//s.insertRow(5, 6);
			//s.delRow(5);
			s.setSelect(3, 3, list);
			s.createSheet("第二张系统报名表");// 第二张工作表

			//File file = new File("F:\\ss.xls");
			//s.exportExcel(file);
			String msg = OrganizationAction.exportExcel("xx.xls", s);
			ServletActionContext.getRequest().setAttribute("msg", msg);
			return SUCCESS;
		}

		 public  final static String exportExcel(String fileName,ExcelCreate s) {
		  String result="系统提示：Excel文件导出成功！";  
		  // 以下开始输出到EXCEL
		  try {    
		   //定义输出流，以便打开保存对话框______________________begin
		   HttpServletResponse response=ServletActionContext.getResponse();
		   OutputStream os = response.getOutputStream();// 取得输出流      
		   response.reset();// 清空输出流      
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1"));
		// 设定输出文件头      
		   response.setContentType("application/msexcel");// 定义输出类型    
		   //定义输出流，以便打开保存对话框_______________________end
		   
		   s.exportExcel(os);

		  } catch (Exception e) {
		   result="系统提示：Excel文件导出失败，原因："+ e.toString();
		   System.out.println(result); 
		   e.printStackTrace();
		  }
		  return result;
		 }
		
}
