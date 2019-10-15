package com.hm.kq.workclassdetail.service;
import com.hm.kq.workclassdetail.entity.HmWorkClassDetailEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface HmWorkClassDetailServiceI extends CommonService{
	
 	public void delete(HmWorkClassDetailEntity entity) throws Exception;
 	
 	public Serializable save(HmWorkClassDetailEntity entity) throws Exception;
 	
 	public void saveOrUpdate(HmWorkClassDetailEntity entity) throws Exception;
 	
}
