package com.hm.kq.checkrecord.service;
import com.hm.kq.checkrecord.entity.HmCheckRecordEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmCheckRecordServiceI extends CommonService{
	
 	public void delete(HmCheckRecordEntity entity) throws Exception;
 	
 	public Serializable save(HmCheckRecordEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmCheckRecordEntity entity) throws Exception;
 	
}
