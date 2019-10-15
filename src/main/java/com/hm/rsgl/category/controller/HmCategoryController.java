package com.hm.rsgl.category.controller;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.rsgl.category.entity.HmCategoryEntity;
import com.hm.rsgl.category.service.HmCategoryServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * @Description: 字段表
 * @author onlineGenerator
 * @date 2019-06-22 20:08:10
 * @version V1.0   
 *
 */
@Api(value="HmCategory",description="字段表",tags="hmCategoryController")
@Controller
@RequestMapping("/hmCategoryController")
public class HmCategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmCategoryController.class);

	@Autowired
	private HmCategoryServiceI hmCategoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 字段表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/category/hmCategoryList");
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
	public void datagrid(HmCategoryEntity hmCategory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmCategoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCategory, request.getParameterMap());
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		try{
		//自定义追加查询条件
			if(Utils.isEmpty(map.get("parentCode"))){
				cq.eq("parentCode","A01A01");
				request.getSession().setAttribute("pCode","A01A01");
			}else{
				request.getSession().setAttribute("pCode",map.get("parentCode"));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmCategoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除字段表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmCategoryEntity hmCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmCategory = systemService.getEntity(HmCategoryEntity.class, hmCategory.getId());
		message = "字段表删除成功";
		try{
			hmCategoryService.delete(hmCategory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "字段表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除字段表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "字段表删除成功";
		try{
			for(String id:ids.split(",")){
				HmCategoryEntity hmCategory = systemService.getEntity(HmCategoryEntity.class, 
				id
				);
				hmCategoryService.delete(hmCategory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "字段表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加字段表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmCategoryEntity hmCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "字段表添加成功";
		try{
			hmCategoryService.save(hmCategory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "字段表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新字段表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmCategoryEntity hmCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "字段表更新成功";
		HmCategoryEntity t = hmCategoryService.get(HmCategoryEntity.class, hmCategory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmCategory, t);
			hmCategoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "字段表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 往前
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doPre")
	@ResponseBody
	public AjaxJson doPre(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			Map codeMap = this.systemService.findOneForJdbc("select t1.PARENT_CODE,t1.order_num,(select min(t0.order_num) from hm_category t0 where t0.PARENT_CODE=t1.PARENT_CODE) min_order_num from hm_category t1 where code=?",map.get("code"));
			if(!codeMap.get("order_num").equals(codeMap.get("min_order_num"))){
				this.systemService.executeSql("update hm_category  set order_num=order_num+1 where PARENT_CODE=? and order_num=?",codeMap.get("PARENT_CODE"),Integer.parseInt(codeMap.get("order_num").toString())-1);
				this.systemService.executeSql("update hm_category set order_num=order_num-1 where code=?",map.get("code"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
			j.setMsg("分类管理更新失败");
		}
		return j;
	}

	/**
	 * 往后
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doNext")
	@ResponseBody
	public AjaxJson doNext(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());
			Map codeMap = this.systemService.findOneForJdbc("select t1.PARENT_CODE,t1.order_num,(select max(t0.order_num) from hm_category t0 where t0.PARENT_CODE=t1.PARENT_CODE) max_order_num from hm_category t1 where code=?",map.get("code"));
			if(!codeMap.get("order_num").equals(codeMap.get("max_order_num"))){
				this.systemService.executeSql("update hm_category set order_num=order_num-1 where PARENT_CODE=? and order_num=?",codeMap.get("PARENT_CODE"),Integer.parseInt(codeMap.get("order_num").toString())+1);
				this.systemService.executeSql("update hm_category set order_num=order_num+1 where code=?",map.get("code"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
			j.setMsg("分类管理更新失败");
		}
		return j;
	}

	/**
	 * 置顶
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doTop")
	@ResponseBody
	public AjaxJson doTop(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());

			List<Map<String,Object>> categoryList = this.systemService.findForJdbc("select * from hm_category where PARENT_CODE=(select PARENT_CODE from hm_category where code=?) and order_num<(select order_num from hm_category where code=?)  order by order_num asc",map.get("code"),map.get("code"));
			Map codeMap = this.systemService.findOneForJdbc("select t1.PARENT_CODE,t1.order_num,(select min(t0.order_num) from hm_category t0 where t0.PARENT_CODE=t1.PARENT_CODE) min_order_num from hm_category t1 where code=?",map.get("code"));
			String order_num = codeMap.get("min_order_num").toString();
			if (order_num.indexOf(".") > 0) {//判断是否有小数点
				order_num = order_num.replaceAll("0+?$", "");//去掉多余的0
				order_num = order_num.replaceAll("[.]$", "");//如最后一位是.则去掉
			}
			this.systemService.executeSql("update hm_category set order_num=? where code=?",order_num,map.get("code"));
			for(Map categoryMap : categoryList){
				this.systemService.executeSql("update hm_category set order_num=order_num+1 where code=?",categoryMap.get("code"));
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
			j.setMsg("分类管理更新失败");
		}
		return j;
	}

	/**
	 * 置底
	 *
	 * @param
	 * @return
	 */
	@RequestMapping(params = "doBottom")
	@ResponseBody
	public AjaxJson doBottom(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		try {
			Map map = ParameterUtil.getParamMaps(request.getParameterMap());

			List<Map<String,Object>> categoryList = this.systemService.findForJdbc("select * from hm_category where PARENT_CODE=(select PARENT_CODE from hm_category where code=?) and order_num>(select order_num from hm_category where code=?)  order by order_num asc",map.get("code"),map.get("code"));
			for(Map categoryMap : categoryList){
				this.systemService.executeSql("update hm_category set order_num=order_num-1 where code=?",categoryMap.get("code"));
			}
			Map max = this.systemService.findOneForJdbc("select (max(t1.order_num)+1) orderNum from hm_category t1 where t1.PARENT_CODE=(select t2.PARENT_CODE from hm_category t2 where t2.code=?)",map.get("code"));
			String order_num = max.get("orderNum").toString();
			if (order_num.indexOf(".") > 0) {//判断是否有小数点
				order_num = order_num.replaceAll("0+?$", "");//去掉多余的0
				order_num = order_num.replaceAll("[.]$", "");//如最后一位是.则去掉
			}
			this.systemService.executeSql("update hm_category t0 set t0.order_num=? where t0.code=?",order_num,map.get("code"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e.fillInStackTrace());
			j.setMsg("分类管理更新失败");
		}
		return j;
	}

	/**
	 * 字段表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmCategoryEntity hmCategory, HttpServletRequest req) {
		String pCode = (String) req.getSession().getAttribute("pCode");
		Map<String,Object> category = this.systemService.findOneForJdbc("SELECT MAX(CODE) CODE, floor(MAX(order_num)+1) order_num FROM hm_category WHERE PARENT_CODE=?",pCode);
		String code = "",order_num="";
		if(Utils.notEmpty(category)){
			if(category.get("CODE") != null){
				code = category.get("CODE").toString();
				order_num = category.get("order_num").toString();
			}else{
				code = pCode+"A00";
				order_num="1";
			}
		}

		if(order_num.indexOf(".")>0){
			order_num = order_num.substring(0,order_num.indexOf("."));
		}
		String endCode = code.substring(8,9);
		if(!endCode.equals("9")){
			code = code.substring(0,8)+(Integer.parseInt(endCode)+1);
		}else{
			if(Integer.parseInt(order_num)>10){
				code = code.substring(0,7)+"20";
			}else{
				code = code.substring(0,7)+"10";
			}
		}
		req.setAttribute("pCode", category.get("pCode"));
		req.setAttribute("code", code);
		if (order_num.indexOf(".") > 0) {//判断是否有小数点
			order_num = order_num.replaceAll("0+?$", "");//去掉多余的0
			order_num = order_num.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		req.setAttribute("orderNum",order_num);

		if (StringUtil.isNotEmpty(hmCategory.getId())) {
			hmCategory = hmCategoryService.getEntity(HmCategoryEntity.class, hmCategory.getId());
			req.setAttribute("hmCategoryPage", hmCategory);
		}
		return new ModelAndView("com/hm/rsgl/category/hmCategory-add");
	}


	/**
	 * 字段表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmCategoryEntity hmCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmCategory.getId())) {
			hmCategory = hmCategoryService.getEntity(HmCategoryEntity.class, hmCategory.getId());
			req.setAttribute("hmCategoryPage", hmCategory);
		}
		return new ModelAndView("com/hm/rsgl/category/hmCategory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmCategoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmCategoryEntity hmCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmCategoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCategory, request.getParameterMap());
		List<HmCategoryEntity> hmCategorys = this.hmCategoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"字段表");
		modelMap.put(NormalExcelConstants.CLASS,HmCategoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("字段表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmCategorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmCategoryEntity hmCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"字段表");
    	modelMap.put(NormalExcelConstants.CLASS,HmCategoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("字段表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmCategoryEntity> listHmCategoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmCategoryEntity.class,params);
				for (HmCategoryEntity hmCategory : listHmCategoryEntitys) {
					hmCategoryService.save(hmCategory);
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
	@ApiOperation(value="字段表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmCategoryEntity>> list() {
		List<HmCategoryEntity> listHmCategorys=hmCategoryService.getList(HmCategoryEntity.class);
		return Result.success(listHmCategorys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取字段表信息",notes="根据ID获取字段表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmCategoryEntity task = hmCategoryService.get(HmCategoryEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取字段表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建字段表")
	public ResponseMessage<?> create(@ApiParam(name="字段表对象")@RequestBody HmCategoryEntity hmCategory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCategoryEntity>> failures = validator.validate(hmCategory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCategoryService.save(hmCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("字段表信息保存失败");
		}
		return Result.success(hmCategory);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新字段表",notes="更新字段表")
	public ResponseMessage<?> update(@ApiParam(name="字段表对象")@RequestBody HmCategoryEntity hmCategory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCategoryEntity>> failures = validator.validate(hmCategory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCategoryService.saveOrUpdate(hmCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新字段表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新字段表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除字段表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmCategoryService.deleteEntityById(HmCategoryEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("字段表删除失败");
		}

		return Result.success();
	}
}
