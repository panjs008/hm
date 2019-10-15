package com.hm.kq.attender.service;
import com.hm.kq.attender.entity.HmAttenderEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmAttenderServiceI extends CommonService{
	
 	public void delete(HmAttenderEntity entity) throws Exception;
 	
 	public Serializable save(HmAttenderEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmAttenderEntity entity) throws Exception;
 	
}
