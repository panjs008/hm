package com.hm.rsgl.leavestaff.service.impl;
import com.hm.rsgl.leavestaff.service.HmLeaveStaffServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.hm.rsgl.leavestaff.entity.HmLeaveStaffEntity;
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

@Service("hmLeaveStaffService")
@Transactional
public class HmLeaveStaffServiceImpl extends CommonServiceImpl implements HmLeaveStaffServiceI {

	
 	public void delete(HmLeaveStaffEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(HmLeaveStaffEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(HmLeaveStaffEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(HmLeaveStaffEntity t) throws Exception{
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
	private void doUpdateBus(HmLeaveStaffEntity t) throws Exception{
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
	private void doDelBus(HmLeaveStaffEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(HmLeaveStaffEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("real_name", t.getRealName());
		map.put("dept_name", t.getDeptName());
		map.put("dept_code", t.getDeptCode());
		map.put("work_name", t.getWorkName());
		map.put("work_code", t.getWorkCode());
		map.put("group_name", t.getGroupName());
		map.put("group_code", t.getGroupCode());
		map.put("job", t.getJob());
		map.put("jb", t.getJb());
		map.put("xclb", t.getXclb());
		map.put("sex", t.getSex());
		map.put("yglb", t.getYglb());
		map.put("taker", t.getTaker());
		map.put("work_type", t.getWorkType());
		map.put("rz_date", t.getRzDate());
		map.put("zz_date", t.getZzDate());
		map.put("telphone", t.getTelphone());
		map.put("id_card", t.getIdCard());
		map.put("birth_day", t.getBirthDay());
		map.put("work_year", t.getWorkYear());
		map.put("sleep_days", t.getSleepDays());
		map.put("mz", t.getMz());
		map.put("xueli", t.getXueli());
		map.put("house_phone", t.getHousePhone());
		map.put("jjlxr", t.getJjlxr());
		map.put("zz_days", t.getZzDays());
		map.put("work_no", t.getWorkNo());
		map.put("zzmm", t.getZzmm());
		map.put("apply_date", t.getApplyDate());
		map.put("leave_date", t.getLeaveDate());
		map.put("leave_type", t.getLeaveType());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,HmLeaveStaffEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{real_name}",String.valueOf(t.getRealName()));
 		sql  = sql.replace("#{dept_name}",String.valueOf(t.getDeptName()));
 		sql  = sql.replace("#{dept_code}",String.valueOf(t.getDeptCode()));
 		sql  = sql.replace("#{work_name}",String.valueOf(t.getWorkName()));
 		sql  = sql.replace("#{work_code}",String.valueOf(t.getWorkCode()));
 		sql  = sql.replace("#{group_name}",String.valueOf(t.getGroupName()));
 		sql  = sql.replace("#{group_code}",String.valueOf(t.getGroupCode()));
 		sql  = sql.replace("#{job}",String.valueOf(t.getJob()));
 		sql  = sql.replace("#{jb}",String.valueOf(t.getJb()));
 		sql  = sql.replace("#{xclb}",String.valueOf(t.getXclb()));
 		sql  = sql.replace("#{sex}",String.valueOf(t.getSex()));
 		sql  = sql.replace("#{yglb}",String.valueOf(t.getYglb()));
 		sql  = sql.replace("#{taker}",String.valueOf(t.getTaker()));
 		sql  = sql.replace("#{work_type}",String.valueOf(t.getWorkType()));
 		sql  = sql.replace("#{rz_date}",String.valueOf(t.getRzDate()));
 		sql  = sql.replace("#{zz_date}",String.valueOf(t.getZzDate()));
 		sql  = sql.replace("#{telphone}",String.valueOf(t.getTelphone()));
 		sql  = sql.replace("#{id_card}",String.valueOf(t.getIdCard()));
 		sql  = sql.replace("#{birth_day}",String.valueOf(t.getBirthDay()));
 		sql  = sql.replace("#{work_year}",String.valueOf(t.getWorkYear()));
 		sql  = sql.replace("#{sleep_days}",String.valueOf(t.getSleepDays()));
 		sql  = sql.replace("#{mz}",String.valueOf(t.getMz()));
 		sql  = sql.replace("#{xueli}",String.valueOf(t.getXueli()));
 		sql  = sql.replace("#{house_phone}",String.valueOf(t.getHousePhone()));
 		sql  = sql.replace("#{jjlxr}",String.valueOf(t.getJjlxr()));
 		sql  = sql.replace("#{zz_days}",String.valueOf(t.getZzDays()));
 		sql  = sql.replace("#{work_no}",String.valueOf(t.getWorkNo()));
 		sql  = sql.replace("#{zzmm}",String.valueOf(t.getZzmm()));
 		sql  = sql.replace("#{apply_date}",String.valueOf(t.getApplyDate()));
 		sql  = sql.replace("#{leave_date}",String.valueOf(t.getLeaveDate()));
 		sql  = sql.replace("#{leave_type}",String.valueOf(t.getLeaveType()));
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
					javaInter.execute("hm_leave_staff",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}