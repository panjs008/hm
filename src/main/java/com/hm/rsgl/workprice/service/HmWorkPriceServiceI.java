package com.hm.rsgl.workprice.service;
import com.hm.rsgl.workprice.entity.HmWorkPriceEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmWorkPriceServiceI extends CommonService{
	
 	public void delete(HmWorkPriceEntity entity) throws Exception;
 	
 	public Serializable save(HmWorkPriceEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmWorkPriceEntity entity) throws Exception;
 	
}
