package com.hm.kq.leval.service;
import com.hm.kq.leval.entity.HmLevalEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmLevalServiceI extends CommonService{
	
 	public void delete(HmLevalEntity entity) throws Exception;
 	
 	public Serializable save(HmLevalEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmLevalEntity entity) throws Exception;
 	
}
