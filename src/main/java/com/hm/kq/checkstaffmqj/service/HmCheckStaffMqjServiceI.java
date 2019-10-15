package com.hm.kq.checkstaffmqj.service;
import com.hm.kq.checkstaffmqj.entity.HmCheckStaffMqjEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmCheckStaffMqjServiceI extends CommonService{
	
 	public void delete(HmCheckStaffMqjEntity entity) throws Exception;
 	
 	public Serializable save(HmCheckStaffMqjEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmCheckStaffMqjEntity entity) throws Exception;
 	
}
