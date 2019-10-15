package com.hm.rsgl.leavestaff.controller;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.rsgl.basesalary.entity.HmBaseSalaryEntity;
import com.hm.rsgl.leavestaff.entity.HmLeaveStaffEntity;
import com.hm.rsgl.leavestaff.service.HmLeaveStaffServiceI;

import java.lang.reflect.Method;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.rsgl.salary.entity.HmSalaryEntity;
import com.hm.rsgl.staff.entity.HmStaffEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 离职人员信息表
 * @author onlineGenerator
 * @date 2019-07-21 22:00:37
 * @version V1.0   
 *
 */
@Api(value="HmLeaveStaff",description="离职人员信息表",tags="hmLeaveStaffController")
@Controller
@RequestMapping("/hmLeaveStaffController")
public class HmLeaveStaffController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmLeaveStaffController.class);

	@Autowired
	private HmLeaveStaffServiceI hmLeaveStaffService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 离职人员信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/leavestaff/hmLeaveStaffList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HmLeaveStaffEntity hmLeaveStaff,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmLeaveStaffEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmLeaveStaff, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmLeaveStaffService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除离职人员信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmLeaveStaff = systemService.getEntity(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
		message = "离职人员信息表删除成功";
		try{
			hmLeaveStaffService.delete(hmLeaveStaff);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "离职人员信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除离职人员信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离职人员信息表删除成功";
		try{
			for(String id:ids.split(",")){
				HmLeaveStaffEntity hmLeaveStaff = systemService.getEntity(HmLeaveStaffEntity.class, 
				id
				);
				if("0".equals(hmLeaveStaff.getState())){
					hmLeaveStaffService.delete(hmLeaveStaff);
				}else{
					message = "离职人员已结算不能删除";
					j.setMsg(message);
					j.setSuccess(false);
					return j;
				}
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "离职人员信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加离职人员信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离职人员信息表添加成功";
		try{
			hmLeaveStaff.setState("0");
			hmLeaveStaffService.save(hmLeaveStaff);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "离职人员信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 离职人员结算
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离职人员信息表更新成功";
		HmLeaveStaffEntity t = hmLeaveStaffService.get(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
		try {
			if("1".equals(t.getState())){
				j.setSuccess(false);
				j.setMsg("已结算无法修改");
				return  j;
			}
			MyBeanUtils.copyBeanNotNull2Bean(hmLeaveStaff, t);
			hmLeaveStaffService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "离职人员信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 离职人员结算
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAnnual")
	@ResponseBody
	public AjaxJson doAnnual(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "离职人员结算成功";
		HmLeaveStaffEntity t = hmLeaveStaffService.get(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
		try {
			Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
			String month = hmLeaveStaff.getAnnualDate().substring(0,7);

			systemService.executeSql("delete from hm_salary where month=? and real_name=?",month,t.getRealName());
			List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
			Class c = Class.forName(HmSalaryEntity.class.getName());

			HmStaffEntity staffEntity = systemService.findUniqueByProperty(HmStaffEntity.class,"workNo",t.getWorkNo());
			HmBaseSalaryEntity hmBaseSalaryEntity = null;

			double yfMoney = 0,ykMoney =0,gz = 0,cdzt=0,zt=0,kg=0,qk=0,qjzt = 0;
			int day = 0,monthDays=0;
			HmSalaryEntity hmSalaryEntity = new HmSalaryEntity();
			hmSalaryEntity.setMonth(month);
			MyBeanUtils.copyBeanNotNull2Bean(staffEntity,hmSalaryEntity);

			String sql  = "select ifnull((select hw.work_time from hm_check_staff hc left join hm_work_class hw on hc.bc_name=hw.bc_name" +
					" 	where hc.work_no = h.WORK_NO limit 0,1),8) workTime,ifnull((select hc.holiday from hm_check_staff hc \n" +
					"	left join hm_work_class hw on hc.bc_name=hw.bc_name where hc.work_no = '"+staffEntity.getWorkNo()+"' limit 0,1),0) holiday," +
					"	sum(CASE h.TIME_TYPE WHEN 0 THEN ifnull((select hw.work_time from hm_check_staff hc \n" +
					"	left join hm_work_class hw on hc.bc_name=hw.bc_name where hc.work_no = h.WORK_NO limit 0,1),8)" +
					"	 WHEN 1 THEN h.long_time else 0 END) hours from hm_leval h where h.WORK_NO=? and h.leval_type='sj' and left(h.BEGIN_TIME,7)=?";
			Map level = systemService.findOneForJdbc(sql,hmSalaryEntity.getWorkNo(),month);

			sql  = "select count(0) yx from hm_leval h where h.WORK_NO=? and h.leval_type='tx' and left(h.BEGIN_TIME,7)=?";
			Map yxlevel = systemService.findOneForJdbc(sql,hmSalaryEntity.getWorkNo(),month);

			sql = "select \n" +
					"(select ifnull(sum(pay_money),0) from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.`month`=hcp.`month` and h1.pay_type=0) cd,\n" +
					"(select ifnull(sum(pay_money),0) from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.`month`=hcp.`month` and h1.pay_type=1) zt,\n" +
					"(select ifnull(count(0),0) from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.month=hcp.month and h1.pay_type=2 ";
			if(Utils.notEmpty(staffEntity.getRzDate())){
				if(staffEntity.getRzDate().substring(0,7).equals(month)){
					sql +="	and h1.check_time >= '"+staffEntity.getRzDate()+"'";
				}
			}
			hmBaseSalaryEntity = systemService.findUniqueByProperty(HmBaseSalaryEntity.class,"realName",hmSalaryEntity.getRealName());
			if(Utils.notEmpty(hmBaseSalaryEntity)){
				MyBeanUtils.copyBeanNotNull2Bean(hmBaseSalaryEntity,hmSalaryEntity);
			}
			hmSalaryEntity.setId(null);
			yfMoney = 0;
			ykMoney =0;
			gz = 0;
			day = 0;
			for(Map category : categoryEntities){
				String m0 = "setA"+category.get("code").toString().substring(1);
				String m = "getA"+category.get("code").toString().substring(1);

				if("基本工资".equals(category.get("name"))){
					Method show = c.getMethod(m);
					Object object = show.invoke(hmSalaryEntity);
					double ys = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));

					Map workPrice = systemService.findOneForJdbc("select ifnull(sum(t.hj),0) hj from (select ifnull(sum(floor(hm.gz_hj)),0) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and hm.type=5 group by hm.kd_date) t",month,hmSalaryEntity.getRealName());
					Map zb = systemService.findOneForJdbc("select ifnull(sum(hm.zcb),0) zcb,ifnull(sum(hm.jiab),0) jiab from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and hm.type=5",month,hmSalaryEntity.getRealName());
					Map workPrice2 = systemService.findOneForJdbc("select ifnull(sum(t.hj),0) hj from (select floor(sum(hm.zshj)) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1) group by hm.kd_date) t ",month,hmSalaryEntity.getRealName());

					double money = Double.parseDouble(workPrice.get("hj").toString());
					if(Utils.notEmpty(workPrice2)){
						money += Double.parseDouble(workPrice2.get("hj").toString());
					}
					hmSalaryEntity.setZcb(zb.get("zcb").toString());
					hmSalaryEntity.setJiab(zb.get("jiab").toString());
					show = c.getMethod(m0,String.class);

					gz += money+ys;
					object = show.invoke(hmSalaryEntity,String.valueOf(money+ys));
				}
				if("岗位补贴".equals(category.get("name"))){
					Method show = c.getMethod(m);
					Object object = show.invoke(hmSalaryEntity);
					double ys = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));

					Map workPrice = systemService.findOneForJdbc("SELECT ifnull(sum(t.hj),0) hj from (select floor(sum(hm.gz_hj)) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and hm.type!=5 and hm.work_type in (0,1,2,3) group by hm.kd_date)  t",month,hmSalaryEntity.getRealName());
					show = c.getMethod(m0,String.class);

					gz += Double.parseDouble(Utils.notEmpty(workPrice.get("hj")) ? workPrice.get("hj").toString():"0")+ys;
					double gwgz = Double.parseDouble(Utils.notEmpty(workPrice.get("hj")) ? workPrice.get("hj").toString():"0")+ys;
					object = show.invoke(hmSalaryEntity,String.valueOf(gwgz));
				}

				if(category.get("column_type").equals("6")){
					if(Utils.notEmpty(category.get("code"))){
						Method show = c.getMethod(m);
						Object object = show.invoke(hmSalaryEntity);
						yfMoney += Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
					}
				}
				if(category.get("column_type").equals("7")){
					if(Utils.notEmpty(category.get("code"))){
						Method show = c.getMethod(m);
						Object object = show.invoke(hmSalaryEntity);
						double val = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
						if(val > 0){
							ykMoney += val;
						}else{
							ykMoney += -val;
						}
					}
				}
			}
			monthDays = DateUtil.getDays(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)));
			if(level.get("holiday").equals("zm")){
				day = DateUtil.getWeeks(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),0);
			}else if(level.get("holiday").equals("zr")){
				day = DateUtil.getWeeks(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),2);
			}else if(level.get("holiday").equals("zl")){
				day = DateUtil.getWeeks(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),1);
			}else{
				day = Integer.parseInt(Utils.notEmpty(staffEntity.getSleepDays()) ? staffEntity.getSleepDays():"0");
			}
			double hourSalary = gz/(Double.parseDouble(level.get("workTime").toString())*(monthDays-day));
			if(Utils.notEmpty(level.get("hours"))){
				hmSalaryEntity.setBsj(String.valueOf(hourSalary*Double.parseDouble(level.get("hours").toString())));
			}

			hmSalaryEntity.setBaseHj(String.valueOf(yfMoney));
			hmSalaryEntity.setYfHj(String.valueOf(yfMoney-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0)));
			hmSalaryEntity.setYkHj(String.valueOf(ykMoney));
			hmSalaryEntity.setMoney(String.valueOf((yfMoney-ykMoney-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0))));
			if("0".equals(t.getLeaveType())){
				hmSalaryEntity.setLeaveType("0");
			}
			if("1".equals(t.getLeaveType())){
				hmSalaryEntity.setLeaveType("1");
			}
			systemService.save(hmSalaryEntity);
			t.setState("1");
			t.setAnnualDate(hmLeaveStaff.getAnnualDate());
			systemService.saveOrUpdate(t);
			staffEntity.setState("1");
			staffEntity.setLzDate(t.getLeaveDate());

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "离职人员结算失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
//	public AjaxJson doAnnual(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest request) {
//		String message = null;
//		AjaxJson j = new AjaxJson();
//		message = "离职人员结算成功";
//		HmLeaveStaffEntity t = hmLeaveStaffService.get(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
//		MyBeanUtils.copyBeanNotNull2Bean(hmLeaveStaff,t);
//		try {
//			Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
//			String month = hmLeaveStaff.getAnnualDate().substring(0,7);
//
//			systemService.executeSql("delete from hm_salary where month=? and work_no=?",month,t.getWorkNo());
//			List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
//			Class c = Class.forName(HmSalaryEntity.class.getName());
//			HmStaffEntity hmStaffEntity = systemService.findUniqueByProperty(HmStaffEntity.class,"workNo",t.getWorkNo());
//			HmBaseSalaryEntity hmBaseSalaryEntity = systemService.findUniqueByProperty(HmBaseSalaryEntity.class,"workNo",t.getWorkNo());
//
//			HmSalaryEntity hmSalaryEntity = new HmSalaryEntity();
//			hmSalaryEntity.setMonth(month);
//			MyBeanUtils.copyBeanNotNull2Bean(hmStaffEntity,hmSalaryEntity);
//
//			if(Utils.notEmpty(hmBaseSalaryEntity)){
//				MyBeanUtils.copyBeanNotNull2Bean(hmBaseSalaryEntity,hmSalaryEntity);
//			}
//			if("1".equals(t.getLeaveType())){
//				hmSalaryEntity.setLeaveType("1");
//			}
//			hmSalaryEntity.setId(null);
//			double yfMoney = 0,ykMoney =0;
//			for(Map category : categoryEntities){
//				String m0 = "setA"+category.get("code").toString().substring(1);
//				String m = "getA"+category.get("code").toString().substring(1);
//
//				if(!"01".equals(hmSalaryEntity.getXclb())){
//					if("基本工资".equals(category.get("name"))){
//						Map workPrice = systemService.findOneForJdbc("select ifnull(sum(hm.gz_hj),0) hj,ifnull(sum(hm.zcb),0) zcb,ifnull(sum(hm.jiab),0) jiab from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and hm.type=5",month,hmSalaryEntity.getWorkNo());
//						Map workPrice2 = systemService.findOneForJdbc("select ifnull(sum(floor(hm.zshj)),0) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)",month,hmSalaryEntity.getWorkNo());
//						double money = Double.parseDouble(workPrice.get("hj").toString());
//						if(Utils.notEmpty(workPrice2)){
//							money += Double.parseDouble(workPrice2.get("hj").toString());
//						}
//						hmSalaryEntity.setZcb(workPrice.get("jiab").toString());
//						hmSalaryEntity.setJiab(workPrice.get("zcb").toString());
//						Method show = c.getMethod(m0,String.class);
//						Object object = show.invoke(hmSalaryEntity,String.valueOf(money));
//					}
//					if("岗位工资".equals(category.get("name"))){
//						Map workPrice = systemService.findOneForJdbc("select ifnull(sum(hm.gz_hj),0) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and hm.type!=5 and hm.work_type in (0,1,2,3)",month,hmSalaryEntity.getWorkNo());
//						Method show = c.getMethod(m0,String.class);
//						Object object = show.invoke(hmSalaryEntity,String.valueOf(workPrice.get("hj")));
//					}
//				}else{
//					if("基本工资".equals(category.get("name"))){
//						Method show = c.getMethod(m0,String.class);
//					}
//				}
//				if(category.get("column_type").equals("6")){
//					if(Utils.notEmpty(category.get("code"))){
//						Method show = c.getMethod(m);
//						Object object = show.invoke(hmSalaryEntity);
//						yfMoney += Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
//					}
//				}
//				if(category.get("column_type").equals("7")){
//					if(Utils.notEmpty(category.get("code"))){
//						Method show = c.getMethod(m);
//						Object object = show.invoke(hmSalaryEntity);
//						double val = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
//						if(val > 0){
//							ykMoney += val;
//						}else{
//							ykMoney += -val;
//						}
//					}
//				}
//			}
//			hmSalaryEntity.setBaseHj(String.valueOf(yfMoney));
//			hmSalaryEntity.setYfHj(String.valueOf(yfMoney));
//			hmSalaryEntity.setYkHj(String.valueOf(ykMoney));
//			hmSalaryEntity.setMoney(String.valueOf((yfMoney-ykMoney)));
//			systemService.save(hmSalaryEntity);
//			t.setState("1");
//			systemService.saveOrUpdate(t);
//			hmStaffEntity.setState("1");
//			hmStaffEntity.setLzDate(t.getLeaveDate());
//
//			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
//		} catch (Exception e) {
//			e.printStackTrace();
//			message = "离职人员结算失败";
//			throw new BusinessException(e.getMessage());
//		}
//		j.setMsg(message);
//		return j;
//	}

	/**
	 * 离职人员信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest req) {
		req.setAttribute("applyDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		if (StringUtil.isNotEmpty(hmLeaveStaff.getId())) {
			hmLeaveStaff = hmLeaveStaffService.getEntity(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
			req.setAttribute("hmLeaveStaffPage", hmLeaveStaff);
		}
		return new ModelAndView("com/hm/rsgl/leavestaff/hmLeaveStaff-add");
	}
	/**
	 * 离职人员结算页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goAnnual")
	public ModelAndView goAnnual(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest req) {
		req.setAttribute("annualDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		hmLeaveStaff = hmLeaveStaffService.getEntity(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
		req.setAttribute("hmLeaveStaffPage", hmLeaveStaff);
		return new ModelAndView("com/hm/rsgl/leavestaff/hmLeaveStaff-annual");
	}
	/**
	 * 离职人员信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmLeaveStaffEntity hmLeaveStaff, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmLeaveStaff.getId())) {
			hmLeaveStaff = hmLeaveStaffService.getEntity(HmLeaveStaffEntity.class, hmLeaveStaff.getId());
			req.setAttribute("hmLeaveStaffPage", hmLeaveStaff);
		}
		return new ModelAndView("com/hm/rsgl/leavestaff/hmLeaveStaff-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmLeaveStaffController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmLeaveStaffEntity hmLeaveStaff,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmLeaveStaffEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmLeaveStaff, request.getParameterMap());
		List<HmLeaveStaffEntity> hmLeaveStaffs = this.hmLeaveStaffService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"离职人员信息表");
		modelMap.put(NormalExcelConstants.CLASS,HmLeaveStaffEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("离职人员信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmLeaveStaffs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmLeaveStaffEntity hmLeaveStaff,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"离职人员信息表");
    	modelMap.put(NormalExcelConstants.CLASS,HmLeaveStaffEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("离职人员信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<HmLeaveStaffEntity> listHmLeaveStaffEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmLeaveStaffEntity.class,params);
				for (HmLeaveStaffEntity hmLeaveStaff : listHmLeaveStaffEntitys) {
					hmLeaveStaffService.save(hmLeaveStaff);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="离职人员信息表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmLeaveStaffEntity>> list() {
		List<HmLeaveStaffEntity> listHmLeaveStaffs=hmLeaveStaffService.getList(HmLeaveStaffEntity.class);
		return Result.success(listHmLeaveStaffs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取离职人员信息表信息",notes="根据ID获取离职人员信息表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmLeaveStaffEntity task = hmLeaveStaffService.get(HmLeaveStaffEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取离职人员信息表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建离职人员信息表")
	public ResponseMessage<?> create(@ApiParam(name="离职人员信息表对象")@RequestBody HmLeaveStaffEntity hmLeaveStaff, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmLeaveStaffEntity>> failures = validator.validate(hmLeaveStaff);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmLeaveStaffService.save(hmLeaveStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("离职人员信息表信息保存失败");
		}
		return Result.success(hmLeaveStaff);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新离职人员信息表",notes="更新离职人员信息表")
	public ResponseMessage<?> update(@ApiParam(name="离职人员信息表对象")@RequestBody HmLeaveStaffEntity hmLeaveStaff) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmLeaveStaffEntity>> failures = validator.validate(hmLeaveStaff);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmLeaveStaffService.saveOrUpdate(hmLeaveStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新离职人员信息表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新离职人员信息表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除离职人员信息表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmLeaveStaffService.deleteEntityById(HmLeaveStaffEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("离职人员信息表删除失败");
		}

		return Result.success();
	}
}
