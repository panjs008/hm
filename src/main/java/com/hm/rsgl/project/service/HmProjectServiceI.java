package com.hm.rsgl.project.service;
import com.hm.rsgl.project.entity.HmProjectEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmProjectServiceI extends CommonService{
	
 	public void delete(HmProjectEntity entity) throws Exception;
 	
 	public Serializable save(HmProjectEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmProjectEntity entity) throws Exception;
 	
}
