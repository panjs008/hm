package com.hm.kq.checkstaff.service;
import com.hm.kq.checkstaff.entity.HmCheckStaffEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmCheckStaffServiceI extends CommonService{
	
 	public void delete(HmCheckStaffEntity entity) throws Exception;
 	
 	public Serializable save(HmCheckStaffEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmCheckStaffEntity entity) throws Exception;
 	
}
