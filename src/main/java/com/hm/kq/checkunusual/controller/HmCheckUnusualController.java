package com.hm.kq.checkunusual.controller;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.kq.checkunusual.entity.HmCheckUnusualEntity;
import com.hm.kq.checkunusual.service.HmCheckUnusualServiceI;

import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.web.system.pojo.base.TSUser;
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
 * @Description: 考勤异常数据
 * @author onlineGenerator
 * @date 2019-08-12 14:51:53
 * @version V1.0   
 *
 */
@Api(value="HmCheckUnusual",description="考勤异常数据",tags="hmCheckUnusualController")
@Controller
@RequestMapping("/hmCheckUnusualController")
public class HmCheckUnusualController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmCheckUnusualController.class);

	@Autowired
	private HmCheckUnusualServiceI hmCheckUnusualService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 考勤异常数据列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		List<Map> cols = new ArrayList<>();
		Map data = null;
		for(int i = 1 ; i <=31 ; i++){
			data = new HashMap();
			data.put("name",i);
			data.put("code","d"+i);
			cols.add(data);
		}
		request.setAttribute("month", DateUtil.format(new Date(),"yyyy-MM"));
		request.setAttribute("cols",cols);
		List<Map<String, Object>> departList = systemService.findForJdbc("select departname,org_code orgCode from t_s_depart where length(org_code)=6",null);
		request.setAttribute("departList",departList);
		return new ModelAndView("com/hm/kq/checkunusual/hmCheckUnusualList");
	}

	/**
	 * 考勤异常数据列表
	 *
	 * @return
	 */
	@RequestMapping(params = "listUnusualByJdbc")
	public void listUnusualByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		sql +=" select hs.real_name realName,hs.work_no workNo,hs.dept_name deptName \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select GROUP_CONCAT(hcu.is_clock) from hm_check_unusual hcu  where hcu.real_name=hs.real_name and left(hcu.check_time,10)='"+month+"-"+String.format("%02d",i)+"') d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_name like '%"+param.get("deptName")+"%'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_name like '%"+param.get("workName")+"%'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		sql += " order by hs.id asc ";

		countSql = "SELECT count(1) FROM ("+sql+") t9 ";

		sql += " limit ";
		if(dataGrid.getPage()==1){
			sql += " 0, "+dataGrid.getRows();
		}else{
			sql += (dataGrid.getPage()-1)*dataGrid.getRows()+","+dataGrid.getRows();
		}

		this.systemService.listAllByJdbc(dataGrid, sql, countSql);
		TagUtil.datagrid(response, dataGrid);
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
	public void datagrid(HmCheckUnusualEntity hmCheckUnusual,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckUnusualEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckUnusual, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmCheckUnusualService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除考勤异常数据
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmCheckUnusualEntity hmCheckUnusual, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmCheckUnusual = systemService.getEntity(HmCheckUnusualEntity.class, hmCheckUnusual.getId());
		message = "考勤异常数据删除成功";
		try{
			hmCheckUnusualService.delete(hmCheckUnusual);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤异常数据删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除考勤异常数据
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤异常数据删除成功";
		try{
			for(String id:ids.split(",")){
				HmCheckUnusualEntity hmCheckUnusual = systemService.getEntity(HmCheckUnusualEntity.class, 
				id
				);
				hmCheckUnusualService.delete(hmCheckUnusual);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤异常数据删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加考勤异常数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmCheckUnusualEntity hmCheckUnusual, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤异常数据添加成功";
		try{
			hmCheckUnusualService.save(hmCheckUnusual);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤异常数据添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新考勤异常数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmCheckUnusualEntity hmCheckUnusual, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤异常数据更新成功";
		HmCheckUnusualEntity t = hmCheckUnusualService.get(HmCheckUnusualEntity.class, hmCheckUnusual.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmCheckUnusual, t);
			hmCheckUnusualService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "考勤异常数据更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 考勤异常数据新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmCheckUnusualEntity hmCheckUnusual, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmCheckUnusual.getId())) {
			hmCheckUnusual = hmCheckUnusualService.getEntity(HmCheckUnusualEntity.class, hmCheckUnusual.getId());
			req.setAttribute("hmCheckUnusualPage", hmCheckUnusual);
		}
		return new ModelAndView("com/hm/kq/checkunusual/hmCheckUnusual-add");
	}
	/**
	 * 考勤异常数据编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmCheckUnusualEntity hmCheckUnusual, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmCheckUnusual.getId())) {
			hmCheckUnusual = hmCheckUnusualService.getEntity(HmCheckUnusualEntity.class, hmCheckUnusual.getId());
			req.setAttribute("hmCheckUnusualPage", hmCheckUnusual);
		}
		return new ModelAndView("com/hm/kq/checkunusual/hmCheckUnusual-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmCheckUnusualController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmCheckUnusualEntity hmCheckUnusual,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckUnusualEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckUnusual, request.getParameterMap());
		List<HmCheckUnusualEntity> hmCheckUnusuals = this.hmCheckUnusualService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"考勤异常数据");
		modelMap.put(NormalExcelConstants.CLASS,HmCheckUnusualEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤异常数据列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmCheckUnusuals);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmCheckUnusualEntity hmCheckUnusual,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"考勤异常数据");
    	modelMap.put(NormalExcelConstants.CLASS,HmCheckUnusualEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤异常数据列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmCheckUnusualEntity> listHmCheckUnusualEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmCheckUnusualEntity.class,params);
				for (HmCheckUnusualEntity hmCheckUnusual : listHmCheckUnusualEntitys) {
					hmCheckUnusualService.save(hmCheckUnusual);
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
	@ApiOperation(value="考勤异常数据列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmCheckUnusualEntity>> list() {
		List<HmCheckUnusualEntity> listHmCheckUnusuals=hmCheckUnusualService.getList(HmCheckUnusualEntity.class);
		return Result.success(listHmCheckUnusuals);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取考勤异常数据信息",notes="根据ID获取考勤异常数据信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmCheckUnusualEntity task = hmCheckUnusualService.get(HmCheckUnusualEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取考勤异常数据信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建考勤异常数据")
	public ResponseMessage<?> create(@ApiParam(name="考勤异常数据对象")@RequestBody HmCheckUnusualEntity hmCheckUnusual, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckUnusualEntity>> failures = validator.validate(hmCheckUnusual);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckUnusualService.save(hmCheckUnusual);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤异常数据信息保存失败");
		}
		return Result.success(hmCheckUnusual);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新考勤异常数据",notes="更新考勤异常数据")
	public ResponseMessage<?> update(@ApiParam(name="考勤异常数据对象")@RequestBody HmCheckUnusualEntity hmCheckUnusual) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckUnusualEntity>> failures = validator.validate(hmCheckUnusual);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckUnusualService.saveOrUpdate(hmCheckUnusual);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新考勤异常数据信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新考勤异常数据信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除考勤异常数据")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmCheckUnusualService.deleteEntityById(HmCheckUnusualEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤异常数据删除失败");
		}

		return Result.success();
	}
}
