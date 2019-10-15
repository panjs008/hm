package com.hm.rsgl.project.service.impl;
import com.hm.rsgl.project.service.HmProjectServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.hm.rsgl.project.entity.HmProjectEntity;
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

@Service("hmProjectService")
@Transactional
public class HmProjectServiceImpl extends CommonServiceImpl implements HmProjectServiceI {

	
 	public void delete(HmProjectEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(HmProjectEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(HmProjectEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(HmProjectEntity t) throws Exception{
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
	private void doUpdateBus(HmProjectEntity t) throws Exception{
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
	private void doDelBus(HmProjectEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(HmProjectEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("a01a01a01", t.getA01a01a01());
		map.put("a01a01a02", t.getA01a01a02());
		map.put("a01a01a03", t.getA01a01a03());
		map.put("a01a01a04", t.getA01a01a04());
		map.put("a01a01a05", t.getA01a01a05());
		map.put("a01a01a06", t.getA01a01a06());
		map.put("a01a01a07", t.getA01a01a07());
		map.put("a01a01a08", t.getA01a01a08());
		map.put("a01a01a09", t.getA01a01a09());
		map.put("a01a01a10", t.getA01a01a10());
		map.put("a01a01a11", t.getA01a01a11());
		map.put("a01a01a12", t.getA01a01a12());
		map.put("a01a01a13", t.getA01a01a13());
		map.put("a01a01a14", t.getA01a01a14());
		map.put("a01a01a15", t.getA01a01a15());
		map.put("a01a01a16", t.getA01a01a16());
		map.put("a01a01a17", t.getA01a01a17());
		map.put("a01a01a18", t.getA01a01a18());
		map.put("a01a01a19", t.getA01a01a19());
		map.put("a01a01a20", t.getA01a01a20());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,HmProjectEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{a01a01a01}",String.valueOf(t.getA01a01a01()));
 		sql  = sql.replace("#{a01a01a02}",String.valueOf(t.getA01a01a02()));
 		sql  = sql.replace("#{a01a01a03}",String.valueOf(t.getA01a01a03()));
 		sql  = sql.replace("#{a01a01a04}",String.valueOf(t.getA01a01a04()));
 		sql  = sql.replace("#{a01a01a05}",String.valueOf(t.getA01a01a05()));
 		sql  = sql.replace("#{a01a01a06}",String.valueOf(t.getA01a01a06()));
 		sql  = sql.replace("#{a01a01a07}",String.valueOf(t.getA01a01a07()));
 		sql  = sql.replace("#{a01a01a08}",String.valueOf(t.getA01a01a08()));
 		sql  = sql.replace("#{a01a01a09}",String.valueOf(t.getA01a01a09()));
 		sql  = sql.replace("#{a01a01a10}",String.valueOf(t.getA01a01a10()));
 		sql  = sql.replace("#{a01a01a11}",String.valueOf(t.getA01a01a11()));
 		sql  = sql.replace("#{a01a01a12}",String.valueOf(t.getA01a01a12()));
 		sql  = sql.replace("#{a01a01a13}",String.valueOf(t.getA01a01a13()));
 		sql  = sql.replace("#{a01a01a14}",String.valueOf(t.getA01a01a14()));
 		sql  = sql.replace("#{a01a01a15}",String.valueOf(t.getA01a01a15()));
 		sql  = sql.replace("#{a01a01a16}",String.valueOf(t.getA01a01a16()));
 		sql  = sql.replace("#{a01a01a17}",String.valueOf(t.getA01a01a17()));
 		sql  = sql.replace("#{a01a01a18}",String.valueOf(t.getA01a01a18()));
 		sql  = sql.replace("#{a01a01a19}",String.valueOf(t.getA01a01a19()));
 		sql  = sql.replace("#{a01a01a20}",String.valueOf(t.getA01a01a20()));
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
					javaInter.execute("hm_project",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}