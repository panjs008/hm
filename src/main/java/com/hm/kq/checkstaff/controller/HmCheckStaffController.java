package com.hm.kq.checkstaff.controller;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.kq.checkstaff.entity.HmCheckStaffEntity;
import com.hm.kq.checkstaff.entity.HmCheckStaffEntityA;
import com.hm.kq.checkstaff.service.HmCheckStaffServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.kq.workclass.entity.HmWorkClassEntity;
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
import java.util.Map;
import java.util.HashMap;
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
import java.util.Set;
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
 * @Description: 考勤配置表
 * @author onlineGenerator
 * @date 2019-08-05 22:22:13
 * @version V1.0   
 *
 */
@Api(value="HmCheckStaff",description="考勤配置表",tags="hmCheckStaffController")
@Controller
@RequestMapping("/hmCheckStaffController")
public class HmCheckStaffController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmCheckStaffController.class);

	@Autowired
	private HmCheckStaffServiceI hmCheckStaffService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 考勤配置表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/kq/checkstaff/hmCheckStaffList");
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
	public void datagrid(HmCheckStaffEntityA hmCheckStaff,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckStaffEntityA.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckStaff, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmCheckStaffService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除考勤配置表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmCheckStaffEntity hmCheckStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmCheckStaff = systemService.getEntity(HmCheckStaffEntity.class, hmCheckStaff.getId());
		message = "考勤配置表删除成功";
		try{
			hmCheckStaffService.delete(hmCheckStaff);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除考勤配置表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤配置表删除成功";
		try{
			for(String id:ids.split(",")){
				HmCheckStaffEntityA hmCheckStaff = systemService.getEntity(HmCheckStaffEntityA.class,
				id
				);
				hmCheckStaffService.delete(hmCheckStaff);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤配置表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加考勤配置表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmCheckStaffEntityA hmCheckStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤配置表添加成功";
		try{
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("dataRowsVal");
			HmCheckStaffEntityA checkStaffEntity = null;
			//HmWorkClassEntity workClassEntity = systemService.findUniqueByProperty(HmWorkClassEntity.class,"bcName",hmCheckStaff.getBcName());
			String[] workNoArr = map.get("gdNamesCode").split(",");
			for(String workNo : workNoArr){
				systemService.executeSql("delete from  hm_check_staff where work_no=?",workNo);
				checkStaffEntity = new HmCheckStaffEntityA();
				MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaff,checkStaffEntity);
				//checkStaffEntity.setWorkClassId(workClassEntity.getId());
				HmStaffEntity hmStaffEntity = systemService.findUniqueByProperty(HmStaffEntity.class,"workNo",workNo);
				MyBeanUtils.copyBeanNotNull2Bean(hmStaffEntity,checkStaffEntity);
				checkStaffEntity.setId(null);
				systemService.save(checkStaffEntity);
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤配置表添加失败";
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新考勤配置表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmCheckStaffEntityA hmCheckStaff, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤配置表更新成功";
		HmCheckStaffEntityA t = hmCheckStaffService.get(HmCheckStaffEntityA.class, hmCheckStaff.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaff, t);
			hmCheckStaffService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "考勤配置表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 考勤配置表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmCheckStaffEntity hmCheckStaff, HttpServletRequest req) {
		req.getSession().setAttribute("gdNamesCodeS","");
		List<HmWorkClassEntity> workClassEntityList = systemService.findHql("from HmWorkClassEntity");
		req.setAttribute("workClassEntityList",workClassEntityList);
		if (StringUtil.isNotEmpty(hmCheckStaff.getId())) {
			hmCheckStaff = hmCheckStaffService.getEntity(HmCheckStaffEntity.class, hmCheckStaff.getId());
			req.setAttribute("hmCheckStaffPage", hmCheckStaff);
		}
		return new ModelAndView("com/hm/kq/checkstaff/hmCheckStaff-add");
	}
	/**
	 * 考勤配置表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmCheckStaffEntity hmCheckStaff, HttpServletRequest req) {
		List<HmWorkClassEntity> workClassEntityList = systemService.findHql("from HmWorkClassEntity");
		req.setAttribute("workClassEntityList",workClassEntityList);
		HmCheckStaffEntityA t = systemService.getEntity(HmCheckStaffEntityA.class, hmCheckStaff.getId());
		req.setAttribute("hmCheckStaffPage", t);
		return new ModelAndView("com/hm/kq/checkstaff/hmCheckStaff-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmCheckStaffController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmCheckStaffEntity hmCheckStaff,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckStaffEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckStaff, request.getParameterMap());
		List<HmCheckStaffEntity> hmCheckStaffs = this.hmCheckStaffService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"考勤配置表");
		modelMap.put(NormalExcelConstants.CLASS,HmCheckStaffEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤配置表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmCheckStaffs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmCheckStaffEntity hmCheckStaff,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"考勤配置表");
    	modelMap.put(NormalExcelConstants.CLASS,HmCheckStaffEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤配置表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmCheckStaffEntity> listHmCheckStaffEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmCheckStaffEntity.class,params);
				for (HmCheckStaffEntity hmCheckStaff : listHmCheckStaffEntitys) {
					hmCheckStaffService.save(hmCheckStaff);
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
	@ApiOperation(value="考勤配置表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmCheckStaffEntity>> list() {
		List<HmCheckStaffEntity> listHmCheckStaffs=hmCheckStaffService.getList(HmCheckStaffEntity.class);
		return Result.success(listHmCheckStaffs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取考勤配置表信息",notes="根据ID获取考勤配置表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmCheckStaffEntity task = hmCheckStaffService.get(HmCheckStaffEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取考勤配置表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建考勤配置表")
	public ResponseMessage<?> create(@ApiParam(name="考勤配置表对象")@RequestBody HmCheckStaffEntity hmCheckStaff, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckStaffEntity>> failures = validator.validate(hmCheckStaff);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckStaffService.save(hmCheckStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤配置表信息保存失败");
		}
		return Result.success(hmCheckStaff);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新考勤配置表",notes="更新考勤配置表")
	public ResponseMessage<?> update(@ApiParam(name="考勤配置表对象")@RequestBody HmCheckStaffEntity hmCheckStaff) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckStaffEntity>> failures = validator.validate(hmCheckStaff);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckStaffService.saveOrUpdate(hmCheckStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新考勤配置表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新考勤配置表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除考勤配置表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmCheckStaffService.deleteEntityById(HmCheckStaffEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤配置表删除失败");
		}

		return Result.success();
	}
}
