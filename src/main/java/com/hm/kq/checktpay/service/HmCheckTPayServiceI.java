package com.hm.kq.checktpay.service;
import com.hm.kq.checktpay.entity.HmCheckTPayEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmCheckTPayServiceI extends CommonService{
	
 	public void delete(HmCheckTPayEntity entity) throws Exception;
 	
 	public Serializable save(HmCheckTPayEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmCheckTPayEntity entity) throws Exception;
 	
}
