/**
 * 
 */
package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.BusinessGroup;
import com.shtz.model.DistributeGroupRecord;
import com.shtz.model.Group;
import com.shtz.util.PageModel;

/**
 * Filename : GroupService.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:30:38 - 2014-6-26
 *
 */
public interface GroupService {
	public List<BusinessGroup> loadBusinessGroup();
	public PageModel serchGroupsByParams(Map params,int offset, int pageSize);
	public BusinessGroup getBusinessGroupById(Integer id);
	public BusinessGroup getBusinessGroupByLeaderId(Integer leaderId);
	public List<Integer> getBusinessGroupPersonIdsByLeaderId(Integer leaderId);
	public void addGroup(Group group);
	public void modifyGroup(Group group);
	public Group getGroupById(Integer id);
	public Long getDayCount(String day);
	public void addDistributeGroupRecord(DistributeGroupRecord dr);
	public Integer getGroupCount();
}
