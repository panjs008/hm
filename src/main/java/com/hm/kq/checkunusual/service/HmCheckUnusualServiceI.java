package com.hm.kq.checkunusual.service;
import com.hm.kq.checkunusual.entity.HmCheckUnusualEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmCheckUnusualServiceI extends CommonService{
	
 	public void delete(HmCheckUnusualEntity entity) throws Exception;
 	
 	public Serializable save(HmCheckUnusualEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmCheckUnusualEntity entity) throws Exception;
 	
}
