package com.hm.rsgl.work.service.impl;
import com.hm.rsgl.work.service.HmWorkServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.hm.rsgl.work.entity.HmWorkEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("hmWorkService")
@Transactional
public class HmWorkServiceImpl extends CommonServiceImpl implements HmWorkServiceI {

	
 	public void delete(HmWorkEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(HmWorkEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(HmWorkEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(HmWorkEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(HmWorkEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(HmWorkEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(HmWorkEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("work_date", t.getWorkDate());
		map.put("work_group_name", t.getWorkGroupName());
		map.put("work_group_code", t.getWorkGroupCode());
		map.put("work_name", t.getWorkName());
		map.put("work_code", t.getWorkCode());
		map.put("group_name", t.getGroupName());
		map.put("group_code", t.getGroupCode());

		map.put("total", t.getTotal());
		map.put("weight", t.getWeight());
		map.put("hj_weight", t.getHjWeight());
		map.put("price", t.getPrice());
		map.put("hj_money", t.getHjMoney());
		map.put("peoples", t.getPeoples());
		map.put("capita_wages", t.getCapitaWages());
		map.put("gd_peoples", t.getGdPeoples());
		map.put("kf_wages", t.getKfWages());
		map.put("local_peoples", t.getLocalPeoples());
		map.put("choucheng", t.getChoucheng());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,HmWorkEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{work_date}",String.valueOf(t.getWorkDate()));
 		sql  = sql.replace("#{work_group_name}",String.valueOf(t.getWorkGroupName()));
 		sql  = sql.replace("#{work_group_code}",String.valueOf(t.getWorkGroupCode()));
 		sql  = sql.replace("#{work_name}",String.valueOf(t.getWorkName()));
 		sql  = sql.replace("#{work_code}",String.valueOf(t.getWorkCode()));
 		sql  = sql.replace("#{group_name}",String.valueOf(t.getGroupName()));
 		sql  = sql.replace("#{group_code}",String.valueOf(t.getGroupCode()));

 		sql  = sql.replace("#{total}",String.valueOf(t.getTotal()));
 		sql  = sql.replace("#{weight}",String.valueOf(t.getWeight()));
 		sql  = sql.replace("#{hj_weight}",String.valueOf(t.getHjWeight()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{hj_money}",String.valueOf(t.getHjMoney()));
 		sql  = sql.replace("#{peoples}",String.valueOf(t.getPeoples()));
 		sql  = sql.replace("#{capita_wages}",String.valueOf(t.getCapitaWages()));
 		sql  = sql.replace("#{gd_peoples}",String.valueOf(t.getGdPeoples()));
 		sql  = sql.replace("#{kf_wages}",String.valueOf(t.getKfWages()));
 		sql  = sql.replace("#{local_peoples}",String.valueOf(t.getLocalPeoples()));
 		sql  = sql.replace("#{choucheng}",String.valueOf(t.getChoucheng()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("hm_work",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}