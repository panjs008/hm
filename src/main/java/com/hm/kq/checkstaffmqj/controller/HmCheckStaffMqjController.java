package com.hm.kq.checkstaffmqj.controller;
import com.emk.util.ParameterUtil;
import com.hm.kq.checkstaffmqj.entity.HmCheckStaffMqjEntity;
import com.hm.kq.checkstaffmqj.service.HmCheckStaffMqjServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @Description: 考勤满勤奖
 * @author onlineGenerator
 * @date 2019-08-28 09:57:52
 * @version V1.0   
 *
 */
@Api(value="HmCheckStaffMqj",description="考勤满勤奖",tags="hmCheckStaffMqjController")
@Controller
@RequestMapping("/hmCheckStaffMqjController")
public class HmCheckStaffMqjController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmCheckStaffMqjController.class);

	@Autowired
	private HmCheckStaffMqjServiceI hmCheckStaffMqjService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 考勤满勤奖列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/kq/checkstaffmqj/hmCheckStaffMqjList");
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
	public void datagrid(HmCheckStaffMqjEntity hmCheckStaffMqj,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckStaffMqjEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckStaffMqj, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmCheckStaffMqjService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除考勤满勤奖
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmCheckStaffMqjEntity hmCheckStaffMqj, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmCheckStaffMqj = systemService.getEntity(HmCheckStaffMqjEntity.class, hmCheckStaffMqj.getId());
		message = "考勤满勤奖删除成功";
		try{
			hmCheckStaffMqjService.delete(hmCheckStaffMqj);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤满勤奖删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除考勤满勤奖
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤满勤奖删除成功";
		try{
			for(String id:ids.split(",")){
				HmCheckStaffMqjEntity hmCheckStaffMqj = systemService.getEntity(HmCheckStaffMqjEntity.class, 
				id
				);
				hmCheckStaffMqjService.delete(hmCheckStaffMqj);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤满勤奖删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加考勤满勤奖
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmCheckStaffMqjEntity hmCheckStaffMqj, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤满勤奖添加成功";
		try{
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			String dataRows = (String) map.get("dataRowsVal");
			String[] workNoArr = map.get("gdNamesCode").split(",");
			HmCheckStaffMqjEntity checkStaffMqjEntity = new HmCheckStaffMqjEntity();
			for(String workNo : workNoArr){
				systemService.executeSql("delete from  hm_check_staff where work_no=?",workNo);
				checkStaffMqjEntity = new HmCheckStaffMqjEntity();
				MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffMqj,checkStaffMqjEntity);
				HmStaffEntity hmStaffEntity = systemService.findUniqueByProperty(HmStaffEntity.class,"workNo",workNo);
				MyBeanUtils.copyBeanNotNull2Bean(hmStaffEntity,checkStaffMqjEntity);
				checkStaffMqjEntity.setId(null);
				systemService.save(checkStaffMqjEntity);
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤满勤奖添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新考勤满勤奖
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmCheckStaffMqjEntity hmCheckStaffMqj, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤满勤奖更新成功";
		HmCheckStaffMqjEntity t = hmCheckStaffMqjService.get(HmCheckStaffMqjEntity.class, hmCheckStaffMqj.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffMqj, t);
			hmCheckStaffMqjService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "考勤满勤奖更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 考勤满勤奖新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmCheckStaffMqjEntity hmCheckStaffMqj, HttpServletRequest req) {
		req.getSession().setAttribute("gdNamesCodeS","");

		if (StringUtil.isNotEmpty(hmCheckStaffMqj.getId())) {
			hmCheckStaffMqj = hmCheckStaffMqjService.getEntity(HmCheckStaffMqjEntity.class, hmCheckStaffMqj.getId());
			req.setAttribute("hmCheckStaffMqjPage", hmCheckStaffMqj);
		}
		return new ModelAndView("com/hm/kq/checkstaffmqj/hmCheckStaffMqj-add");
	}
	/**
	 * 考勤满勤奖编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmCheckStaffMqjEntity hmCheckStaffMqj, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmCheckStaffMqj.getId())) {
			hmCheckStaffMqj = hmCheckStaffMqjService.getEntity(HmCheckStaffMqjEntity.class, hmCheckStaffMqj.getId());
			req.setAttribute("hmCheckStaffMqjPage", hmCheckStaffMqj);
		}
		return new ModelAndView("com/hm/kq/checkstaffmqj/hmCheckStaffMqj-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmCheckStaffMqjController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmCheckStaffMqjEntity hmCheckStaffMqj,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckStaffMqjEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckStaffMqj, request.getParameterMap());
		List<HmCheckStaffMqjEntity> hmCheckStaffMqjs = this.hmCheckStaffMqjService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"考勤满勤奖");
		modelMap.put(NormalExcelConstants.CLASS,HmCheckStaffMqjEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤满勤奖列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmCheckStaffMqjs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmCheckStaffMqjEntity hmCheckStaffMqj,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"考勤满勤奖");
    	modelMap.put(NormalExcelConstants.CLASS,HmCheckStaffMqjEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤满勤奖列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmCheckStaffMqjEntity> listHmCheckStaffMqjEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmCheckStaffMqjEntity.class,params);
				for (HmCheckStaffMqjEntity hmCheckStaffMqj : listHmCheckStaffMqjEntitys) {
					hmCheckStaffMqjService.save(hmCheckStaffMqj);
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
	@ApiOperation(value="考勤满勤奖列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmCheckStaffMqjEntity>> list() {
		List<HmCheckStaffMqjEntity> listHmCheckStaffMqjs=hmCheckStaffMqjService.getList(HmCheckStaffMqjEntity.class);
		return Result.success(listHmCheckStaffMqjs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取考勤满勤奖信息",notes="根据ID获取考勤满勤奖信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmCheckStaffMqjEntity task = hmCheckStaffMqjService.get(HmCheckStaffMqjEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取考勤满勤奖信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建考勤满勤奖")
	public ResponseMessage<?> create(@ApiParam(name="考勤满勤奖对象")@RequestBody HmCheckStaffMqjEntity hmCheckStaffMqj, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckStaffMqjEntity>> failures = validator.validate(hmCheckStaffMqj);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckStaffMqjService.save(hmCheckStaffMqj);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤满勤奖信息保存失败");
		}
		return Result.success(hmCheckStaffMqj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新考勤满勤奖",notes="更新考勤满勤奖")
	public ResponseMessage<?> update(@ApiParam(name="考勤满勤奖对象")@RequestBody HmCheckStaffMqjEntity hmCheckStaffMqj) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckStaffMqjEntity>> failures = validator.validate(hmCheckStaffMqj);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckStaffMqjService.saveOrUpdate(hmCheckStaffMqj);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新考勤满勤奖信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新考勤满勤奖信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除考勤满勤奖")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmCheckStaffMqjService.deleteEntityById(HmCheckStaffMqjEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤满勤奖删除失败");
		}

		return Result.success();
	}
}
