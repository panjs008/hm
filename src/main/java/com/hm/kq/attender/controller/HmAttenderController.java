package com.hm.kq.attender.controller;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.kq.attender.entity.HmAttenderEntity;
import com.hm.kq.attender.service.HmAttenderServiceI;

import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.kq.checkstaff.entity.HmCheckStaffEntity;
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
 * @Description: 出勤表
 * @author onlineGenerator
 * @date 2019-10-13 14:17:29
 * @version V1.0   
 *
 */
@Api(value="HmAttender",description="出勤表",tags="hmAttenderController")
@Controller
@RequestMapping("/hmAttenderController")
public class HmAttenderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmAttenderController.class);

	@Autowired
	private HmAttenderServiceI hmAttenderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 出勤表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/kq/attender/hmAttenderList");
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
	public void datagrid(HmAttenderEntity hmAttender,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmAttenderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmAttender, request.getParameterMap());
		try{
		//自定义追加查询条件
			Map p = ParameterUtil.getParamMaps(request.getParameterMap());
			if(Utils.notEmpty(p.get("month"))){
				cq.eq("month",p.get("month"));
			}else{
				cq.eq("month","-1");
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmAttenderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除出勤表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmAttenderEntity hmAttender, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmAttender = systemService.getEntity(HmAttenderEntity.class, hmAttender.getId());
		message = "出勤表删除成功";
		try{
			hmAttenderService.delete(hmAttender);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "出勤表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除出勤表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "出勤表删除成功";
		try{
			for(String id:ids.split(",")){
				HmAttenderEntity hmAttender = systemService.getEntity(HmAttenderEntity.class, 
				id
				);
				hmAttenderService.delete(hmAttender);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "出勤表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加出勤表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmAttenderEntity hmAttender, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "出勤表生成成功";
		try{
			Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
			String month = "";
			if(Utils.notEmpty(param.get("month"))){
				month = param.get("month");
			}else{
				month = DateUtil.format(new Date(),"yyyy-MM");
			}
			List<HmStaffEntity> staffEntityList = systemService.findHql("from HmStaffEntity where state=0");
			//按工号保存满勤奖数据
			List<Map<String, Object>>  mqjList = systemService.findForJdbc("select m.work_no,ifnull(m.mqj,0) mqj,ifnull(m.qqj,0) qqj from hm_check_staff_mqj m");
			Map<String,Map<String, Object>> mqjMap = new HashMap<>();
			for(Map p : mqjList){
				mqjMap.put(p.get("work_no").toString(),p);
			}
			//查询已生成的出勤表数据
			List<HmAttenderEntity> attenderEntityList = systemService.findHql("from HmAttenderEntity where month=? and (hourSalary is not null or hours is not null) ",month);
			Map<String, HmAttenderEntity> attenderEntityMap = new HashMap<>();
			for(HmAttenderEntity attenderEntity : attenderEntityList){
				attenderEntityMap.put(attenderEntity.getWorkNo(),attenderEntity);
			}
			systemService.executeSql("delete from hm_attender where month=? ",month);
			List<HmSalaryEntity> salaryEntityList = systemService.findHql("from HmSalaryEntity where month=? and (leave_type is null or leave_type='') ",month);
			Map<String, HmSalaryEntity> salaryEntityMap = new HashMap<>();
			for(HmSalaryEntity salaryEntity : salaryEntityList){
				salaryEntityMap.put(salaryEntity.getWorkNo(),salaryEntity);
			}
			HmAttenderEntity attenderEntity = null;
			int day = 0,monthDays=0;
			Map cq = null;
			DecimalFormat df = new DecimalFormat("#.00");

			for(HmStaffEntity staffEntity : staffEntityList){
				HmAttenderEntity hmAttenderEntity = attenderEntityMap.get(staffEntity.getWorkNo());
				day = 0;
				HmSalaryEntity salaryEntity = salaryEntityMap.get(staffEntity.getWorkNo());
				String sql  = "select ifnull((select hw.work_time from hm_check_staff hc left join hm_work_class hw on hc.bc_name=hw.bc_name" +
						" 	where hc.work_no = h.WORK_NO limit 0,1),8) workTime,ifnull((select hc.holiday from hm_check_staff hc \n" +
						"	left join hm_work_class hw on hc.bc_name=hw.bc_name where hc.work_no = '"+staffEntity.getWorkNo()+"' limit 0,1),0) holiday," +
						"	sum(CASE h.TIME_TYPE WHEN 0 THEN ifnull((select hw.work_time from hm_check_staff hc \n" +
						"	left join hm_work_class hw on hc.bc_name=hw.bc_name where hc.work_no = h.WORK_NO limit 0,1),8)*h.long_time" +
						"	 WHEN 1 THEN h.long_time else 0 END) hours from hm_leval h where h.WORK_NO='"+staffEntity.getWorkNo()+"' and (h.leval_type='sj' or h.leval_type='bj') and left(h.BEGIN_TIME,7)='"+month+"'";
				Map level = systemService.findOneForJdbc(sql);
				sql  = "select count(0) yx from hm_leval h where h.WORK_NO=? and h.leval_type='tx' and left(h.BEGIN_TIME,7)=?";
				Map yxlevel = systemService.findOneForJdbc(sql,staffEntity.getWorkNo(),month);
				attenderEntity = new HmAttenderEntity();
				if(Utils.notEmpty(hmAttenderEntity)){
					MyBeanUtils.copyBeanNotNull2Bean(hmAttenderEntity,attenderEntity);
				}
				MyBeanUtils.copyBeanNotNull2Bean(staffEntity,attenderEntity);
				attenderEntity.setMonth(month);
				attenderEntity.setId(null);
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
				if("01".equals(staffEntity.getXclb())){
					attenderEntity.setCqDay(String.valueOf(monthDays-day));
					if(Utils.notEmpty(level.get("workTime")) && Utils.notEmpty(salaryEntity)){
						double hourSalary = Double.parseDouble(Utils.notEmpty(salaryEntity.getYfHj()) ?  salaryEntity.getYfHj():"0")/(Double.parseDouble(level.get("workTime").toString())*(monthDays-day));
						attenderEntity.setQjMoney(df.format(hourSalary*Double.parseDouble(Utils.notEmpty(level.get("hours")) ? level.get("hours").toString():"0")));
						attenderEntity.setHours(Utils.notEmpty(level.get("hours")) ? level.get("hours").toString():"");
						attenderEntity.setHourSalary(df.format((hourSalary)));
					}
				}else{
					cq = systemService.findOneForJdbc("select ifnull(count(1),0) days from (select kd_date,sum(gz_hj) gz_hj from hm_work_price p where work_no=? and left(p.kd_date,7)=? and  p.gz_hj>=50 group by work_no,p.kd_date) a",staffEntity.getWorkNo(),month);
					attenderEntity.setCqDay(cq.get("days").toString());
				}
				Map mqj = mqjMap.get(staffEntity.getWorkNo());
				if("0".equals(level.get("hours"))||Utils.isEmpty(level.get("hours"))){
					if(Utils.notEmpty(mqj)){
						if(!"01".equals(staffEntity.getXclb())){
							if(Utils.notEmpty(attenderEntity.getCqDay())){
								if(attenderEntity.getCqDay().equals(String.valueOf(monthDays))){
									attenderEntity.setMqj(mqj.get("mqj").toString());
								}else if(Integer.valueOf(attenderEntity.getCqDay())>=Integer.valueOf(monthDays-day) && Integer.valueOf(attenderEntity.getCqDay())<Integer.valueOf(monthDays)){
									attenderEntity.setMqj(mqj.get("qqj").toString());
								}
							}
						}else{
							if("0".equals(yxlevel.get("yx").toString())){
								attenderEntity.setMqj(mqj.get("mqj").toString());
							}else{
								attenderEntity.setMqj(mqj.get("qqj").toString());
							}
						}

					}
				}
				systemService.save(attenderEntity);
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "出勤表生成失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新出勤表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmAttenderEntity hmAttender, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "出勤表更新成功";
		HmAttenderEntity t = hmAttenderService.get(HmAttenderEntity.class, hmAttender.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmAttender, t);
			hmAttenderService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "出勤表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 计入工资表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doCal")
	@ResponseBody
	public AjaxJson doCal(HmAttenderEntity hmAttender, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "计入工资表成功";
		HmAttenderEntity t = hmAttenderService.get(HmAttenderEntity.class, hmAttender.getId());
		try {
			List<HmSalaryEntity> salaryEntityList = systemService.findHql("from HmSalaryEntity where month=? and workNo=?",t.getMonth(),t.getWorkNo());
			HmSalaryEntity hmSalaryEntity = salaryEntityList.get(0);
			hmSalaryEntity.setBsj(t.getQjMoney());
			hmSalaryEntity.setCdzt(t.getCdMoney());
			hmSalaryEntity.setKgwdk(t.getKgMoney());
			hmSalaryEntity.setFullHourMoney(t.getMqj());
			double yfMoney = Double.parseDouble(hmSalaryEntity.getYfHj());
			double ykMoney = Double.parseDouble(hmSalaryEntity.getYkHj());

			hmSalaryEntity.setBaseHj(String.valueOf(yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)));
			hmSalaryEntity.setYfHj(String.valueOf(yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)
					-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0)
					-(Utils.notEmpty(hmSalaryEntity.getCdzt()) ? Double.parseDouble(hmSalaryEntity.getCdzt()):0)
					-(Utils.notEmpty(hmSalaryEntity.getKgwdk()) ? Double.parseDouble(hmSalaryEntity.getKgwdk()):0)));
			yfMoney = Double.parseDouble(hmSalaryEntity.getYfHj());
			hmSalaryEntity.setMoney(String.valueOf((yfMoney -ykMoney)));
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "计入工资表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 出勤表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmAttenderEntity hmAttender, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmAttender.getId())) {
			hmAttender = hmAttenderService.getEntity(HmAttenderEntity.class, hmAttender.getId());
			req.setAttribute("hmAttenderPage", hmAttender);
		}
		return new ModelAndView("com/hm/kq/attender/hmAttender-add");
	}
	/**
	 * 出勤表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmAttenderEntity hmAttender, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmAttender.getId())) {
			hmAttender = hmAttenderService.getEntity(HmAttenderEntity.class, hmAttender.getId());
			req.setAttribute("hmAttenderPage", hmAttender);
		}
		return new ModelAndView("com/hm/kq/attender/hmAttender-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmAttenderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmAttenderEntity hmAttender,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmAttenderEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmAttender, request.getParameterMap());
		List<HmAttenderEntity> hmAttenders = this.hmAttenderService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"出勤表");
		modelMap.put(NormalExcelConstants.CLASS,HmAttenderEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("出勤表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmAttenders);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmAttenderEntity hmAttender,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"出勤表");
    	modelMap.put(NormalExcelConstants.CLASS,HmAttenderEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("出勤表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmAttenderEntity> listHmAttenderEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmAttenderEntity.class,params);
				for (HmAttenderEntity hmAttender : listHmAttenderEntitys) {
					hmAttenderService.save(hmAttender);
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
	@ApiOperation(value="出勤表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmAttenderEntity>> list() {
		List<HmAttenderEntity> listHmAttenders=hmAttenderService.getList(HmAttenderEntity.class);
		return Result.success(listHmAttenders);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取出勤表信息",notes="根据ID获取出勤表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmAttenderEntity task = hmAttenderService.get(HmAttenderEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取出勤表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建出勤表")
	public ResponseMessage<?> create(@ApiParam(name="出勤表对象")@RequestBody HmAttenderEntity hmAttender, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmAttenderEntity>> failures = validator.validate(hmAttender);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmAttenderService.save(hmAttender);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("出勤表信息保存失败");
		}
		return Result.success(hmAttender);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新出勤表",notes="更新出勤表")
	public ResponseMessage<?> update(@ApiParam(name="出勤表对象")@RequestBody HmAttenderEntity hmAttender) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmAttenderEntity>> failures = validator.validate(hmAttender);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmAttenderService.saveOrUpdate(hmAttender);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新出勤表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新出勤表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除出勤表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmAttenderService.deleteEntityById(HmAttenderEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("出勤表删除失败");
		}

		return Result.success();
	}
}
