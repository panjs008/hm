package com.hm.kq.checkpay.service;
import com.hm.kq.checkpay.entity.HmCheckPayEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmCheckPayServiceI extends CommonService{
	
 	public void delete(HmCheckPayEntity entity) throws Exception;
 	
 	public Serializable save(HmCheckPayEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmCheckPayEntity entity) throws Exception;
 	
}
