/**
 * 
 */
package com.shtz.service;

import java.util.List;
import java.util.Map;

import com.shtz.model.SeekSource;
import com.shtz.util.PageModel;

/**
 * Filename : SeekSourceService.java
 *
 * @author yao chang
 *
 * Creation time : 下午09:56:23 - 2013-4-26
 *
 */
public interface SeekSourceService {
	public void addSeekSource(SeekSource seekSource);
	public void modifySeekSource(SeekSource seekSource);
	public void modifySeekSourceCheck(SeekSource seekSource,String ids);
	public void modifySeekSourceCheckForLeader(SeekSource seekSource, String ids);
	public SeekSource getSeekSource(int id);
	public PageModel serchSeekSourceByParams(Map params,int offset, int pageSize);
	public void deleteSeekSourceDetail(String sdId);
	public int getNextSeekSourceNum() ;
	public List<SeekSource> getSeekSources(String ids) ;
	public void modifyPlansSS(String ids);
	public void modifyPlansCheckStatus(String status,String ids);
	public void modifyPlansSSLeaderStatus(String status,String ids);
	public String checkPCStatus(String ids,String needStatus);
//	public Integer getSSCountByPersonId(int personId) ;
	public Integer getSSCountByRoleAndPersonId(int personId,String role) ;
	public void deleteSeekSource(String ids);
}
