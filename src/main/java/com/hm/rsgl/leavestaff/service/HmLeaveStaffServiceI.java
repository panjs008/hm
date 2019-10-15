package com.hm.rsgl.leavestaff.service;
import com.hm.rsgl.leavestaff.entity.HmLeaveStaffEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmLeaveStaffServiceI extends CommonService{
	
 	public void delete(HmLeaveStaffEntity entity) throws Exception;
 	
 	public Serializable save(HmLeaveStaffEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmLeaveStaffEntity entity) throws Exception;
 	
}
