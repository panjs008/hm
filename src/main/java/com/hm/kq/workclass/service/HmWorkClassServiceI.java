package com.hm.kq.workclass.service;
import com.hm.kq.workclass.entity.HmWorkClassEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmWorkClassServiceI extends CommonService{
	
 	public void delete(HmWorkClassEntity entity) throws Exception;
 	
 	public Serializable save(HmWorkClassEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmWorkClassEntity entity) throws Exception;
 	
}
