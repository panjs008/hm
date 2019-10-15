package com.hm.rsgl.project.controller;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.emk.util.WebFileUtils;
import com.emk.util.excel.ExcelUtil;
import com.hm.rsgl.project.entity.HmProjectEntity;
import com.hm.rsgl.project.service.HmProjectServiceI;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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
 * @Description: 项目表
 * @author onlineGenerator
 * @date 2019-06-23 08:28:51
 * @version V1.0   
 *
 */
@Api(value="HmProject",description="项目表",tags="hmProjectController")
@Controller
@RequestMapping("/hmProjectController")
public class HmProjectController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmProjectController.class);

	@Autowired
	private HmProjectServiceI hmProjectService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 项目表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		request.setAttribute("categoryEntities",categoryEntities);
		return new ModelAndView("com/hm/rsgl/project/hmProjectList");
	}
	@RequestMapping(params = "select")
	public ModelAndView setlect(HttpServletRequest request) {
		List<Map<String, Object>> gglb = systemService.findForJdbc("SELECT t.a01a01a03 name FROM hm_project t GROUP BY t.a01a01a03");
		request.setAttribute("gglb",gglb);
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		request.setAttribute("categoryEntities",categoryEntities);
		return new ModelAndView("com/hm/rsgl/project/hmProjectList-select");
	}
	@RequestMapping(params = "select1")
	public ModelAndView setlect1(HttpServletRequest request) {
		List<Map<String, Object>> gglb = systemService.findForJdbc("SELECT t.a01a01a03 name FROM hm_project t GROUP BY t.a01a01a03");
		request.setAttribute("gglb",gglb);
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		request.setAttribute("categoryEntities",categoryEntities);
		return new ModelAndView("com/hm/rsgl/project/hmProjectList-select1");
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
	public void datagrid(HmProjectEntity hmProject,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if("-1".equals(hmProject.getA01a01a03())){
			hmProject.setA01a01a03(null);
		}
		CriteriaQuery cq = new CriteriaQuery(HmProjectEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmProject, request.getParameterMap());
		try{
		//自定义追加查询条件

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmProjectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除项目表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmProjectEntity hmProject, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmProject = systemService.getEntity(HmProjectEntity.class, hmProject.getId());
		message = "项目表删除成功";
		try{
			hmProjectService.delete(hmProject);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除项目表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目表删除成功";
		try{
			for(String id:ids.split(",")){
				HmProjectEntity hmProject = systemService.getEntity(HmProjectEntity.class, 
				id
				);
				hmProjectService.delete(hmProject);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "项目表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加项目表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmProjectEntity hmProject, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目表添加成功";
		try{
			hmProjectService.save(hmProject);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新项目表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmProjectEntity hmProject, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目表更新成功";
		HmProjectEntity t = hmProjectService.get(HmProjectEntity.class, hmProject.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmProject, t);
			hmProjectService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "项目表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 项目表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmProjectEntity hmProject, HttpServletRequest req) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		req.setAttribute("categoryEntities",categoryEntities);
		if (StringUtil.isNotEmpty(hmProject.getId())) {
			hmProject = hmProjectService.getEntity(HmProjectEntity.class, hmProject.getId());
			req.setAttribute("hmProjectPage", hmProject);
		}
		return new ModelAndView("com/hm/rsgl/project/hmProject-add");
	}
	/**
	 * 项目表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmProjectEntity hmProject, HttpServletRequest req) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		req.setAttribute("categoryEntities",categoryEntities);
		if (StringUtil.isNotEmpty(hmProject.getId())) {
			hmProject = hmProjectService.getEntity(HmProjectEntity.class, hmProject.getId());
			req.setAttribute("hmProjectPage", hmProject);
		}
		return new ModelAndView("com/hm/rsgl/project/hmProject-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmProjectController");
		return new ModelAndView("common/upload/pub_excel_upload2");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmProjectEntity hmProject,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		StringBuffer sql = new StringBuffer();
		Map pamp = ParameterUtil.getParamMaps(request.getParameterMap());
		sql.append(" select t1.* from hm_project t1 ");
		String savepath = request.getRealPath("/")+"export/model/";
		File file = new File(savepath);
		if(!file.exists()) {
			file.mkdirs();
		}
		savepath = request.getRealPath("/")+"export/model/生产内容表.xls";
		Map titleMap = new HashMap();
		List<String> headList = new ArrayList<String>();
		List<String> fieldList = new ArrayList<String>();
		List<Map<String,Object>> dataList = this.systemService.findForJdbc(sql.toString());
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		for(Map codeMap : categoryEntities){
			headList.add(codeMap.get("name").toString());
			fieldList.add(codeMap.get("code").toString());
		}

		try {
			ExcelUtil.createExcel(savepath,headList, fieldList, dataList);
			WebFileUtils.downLoad(savepath, response, false);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			file.delete();
		}
		return null;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmProjectEntity hmProject,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		String savepath = request.getRealPath("/")+"export/model/";
		File file = new File(savepath);
		if(!file.exists())
		{
			file.mkdirs();
		}
		savepath = request.getRealPath("/")+"export/model/生产内容表.xls";
		Map titleMap = new HashMap();
		List<String> headList = new ArrayList<String>();
		List<String> fieldList = new ArrayList<String>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();

		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		for(Map codeMap : categoryEntities){
			headList.add(codeMap.get("name").toString());
		}
		try {
			ExcelUtil.createExcel(savepath,headList, fieldList, dataList);
			WebFileUtils.downLoad(savepath, response, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public Object importExcel(HttpServletRequest request, HttpServletResponse response) {
		String sussess = null;
		Map retmap = new HashMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			File newfile = null;
			HSSFWorkbook wb = null;
			String cellValue = "";
			HmProjectEntity hmProjectEntity = null;
			try {
				String savepath = request.getRealPath("/")+"imp/project/";
				String savename = WebFileUtils.saveFile(file, savepath);
				newfile =  new File(savepath+savename);
				wb = WebFileUtils.createHSSFWorkBook(newfile);
				if (wb==null) {
					logger.error("传入文件无法识别，请检查文件类型！！");
					sussess = "false";
				}
				HSSFSheet sheet = wb.getSheetAt(0);
				DecimalFormat df = new DecimalFormat("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				HSSFCell cell = null;
				int counter = 0;
				HSSFRow row = null;
				logger.info("执行导入："+newfile.getName());
				int colorNum1 = 1;
				List<String> itemValue =null;
				List<String> itemValue0 = new ArrayList<String>();
				List<String> codeList = new ArrayList<String>();

				row = sheet.getRow(0);
				for(int z = 0; z <= 19 ; z++){
					cell = row.getCell(z);
					if(cell == null){
						itemValue0.add(cellValue);
						continue;
					}
					switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							cellValue =cell.getRichStringCellValue().getString().trim();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							cellValue = df.format(cell.getNumericCellValue()).toString();
							break;
						default:
							cellValue = "";
					}
					itemValue0.add(cellValue);
					cellValue = "";
				}
				for(int z = 0; z < itemValue0.size() ; z++){
					Map code = systemService.findOneForJdbc("select lower(code) code from hm_category where PARENT_CODE='A01A01' and  name=?",itemValue0.get(z));
					if(code != null){
						codeList.add(code.get("code").toString());
					}
				}
				for (int i = 1; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					if(row == null){
						continue;
					}else{
						counter++;
					}
					itemValue = new ArrayList<String>();
					for(int z = 0; z <= 25 ; z++){
						cell = row.getCell(z);
						if(cell == null){
							itemValue.add(cellValue);
							continue;
						}
						switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								cellValue =cell.getRichStringCellValue().getString().trim();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								cellValue = df.format(cell.getNumericCellValue()).toString();
								break;
							default:
								cellValue = "";
						}
						itemValue.add(cellValue);
						cellValue = "";
					}
					if(Utils.notEmpty(itemValue.get(0))){
						this.systemService.executeSql("delete from hm_project where a01a01a01=?",itemValue.get(0));
						hmProjectEntity = new HmProjectEntity();
						this.systemService.save(hmProjectEntity);
						String sql = "update hm_project set  ";
						int  codei = 0;

						for(int z = 0 ; z < codeList.size() ; z++){
							sql += codeList.get(z)+"='"+itemValue.get(z)+"',";
						}
						if(itemValue.size()>1){
							sql = sql.substring(0,sql.length()-1);
							sql += " where id='"+hmProjectEntity.getId()+"'";
							this.systemService.executeSql(sql);
						}
					}

				}
				sussess = "true";
			} catch (Exception e) {
				sussess = "false";
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
					newfile.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		retmap.put("sussess",sussess);
		return retmap;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="项目表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmProjectEntity>> list() {
		List<HmProjectEntity> listHmProjects=hmProjectService.getList(HmProjectEntity.class);
		return Result.success(listHmProjects);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取项目表信息",notes="根据ID获取项目表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmProjectEntity task = hmProjectService.get(HmProjectEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取项目表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建项目表")
	public ResponseMessage<?> create(@ApiParam(name="项目表对象")@RequestBody HmProjectEntity hmProject, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmProjectEntity>> failures = validator.validate(hmProject);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmProjectService.save(hmProject);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目表信息保存失败");
		}
		return Result.success(hmProject);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新项目表",notes="更新项目表")
	public ResponseMessage<?> update(@ApiParam(name="项目表对象")@RequestBody HmProjectEntity hmProject) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmProjectEntity>> failures = validator.validate(hmProject);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmProjectService.saveOrUpdate(hmProject);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新项目表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新项目表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除项目表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmProjectService.deleteEntityById(HmProjectEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目表删除失败");
		}

		return Result.success();
	}
}
