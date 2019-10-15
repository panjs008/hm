package com.hm.rsgl.order.service;
import com.hm.rsgl.order.entity.HmOrderEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmOrderServiceI extends CommonService{
	
 	public void delete(HmOrderEntity entity) throws Exception;
 	
 	public Serializable save(HmOrderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmOrderEntity entity) throws Exception;
 	
}
