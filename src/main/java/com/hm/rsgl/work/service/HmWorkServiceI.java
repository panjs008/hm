package com.hm.rsgl.work.service;
import com.hm.rsgl.work.entity.HmWorkEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmWorkServiceI extends CommonService{
	
 	public void delete(HmWorkEntity entity) throws Exception;
 	
 	public Serializable save(HmWorkEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmWorkEntity entity) throws Exception;
 	
}
