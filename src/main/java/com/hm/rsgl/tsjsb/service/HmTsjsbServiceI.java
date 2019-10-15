package com.hm.rsgl.tsjsb.service;
import com.hm.rsgl.tsjsb.entity.HmTsjsbEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmTsjsbServiceI extends CommonService{
	
 	public void delete(HmTsjsbEntity entity) throws Exception;
 	
 	public Serializable save(HmTsjsbEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmTsjsbEntity entity) throws Exception;
 	
}
