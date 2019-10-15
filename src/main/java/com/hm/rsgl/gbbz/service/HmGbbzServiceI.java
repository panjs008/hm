package com.hm.rsgl.gbbz.service;
import com.hm.rsgl.gbbz.entity.HmGbbzEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmGbbzServiceI extends CommonService{
	
 	public void delete(HmGbbzEntity entity) throws Exception;
 	
 	public Serializable save(HmGbbzEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmGbbzEntity entity) throws Exception;
 	
}
