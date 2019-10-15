package com.hm.rsgl.tsjsb.controller;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.rsgl.staff.entity.HmStaffEntity;
import com.hm.rsgl.tsjsb.entity.HmTsjsbEntity;
import com.hm.rsgl.tsjsb.service.HmTsjsbServiceI;
import java.util.ArrayList;
import java.util.List;
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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 特殊计时员工表
 * @author onlineGenerator
 * @date 2019-06-28 22:34:19
 * @version V1.0   
 *
 */
@Api(value="HmTsjsb",description="特殊计时员工表",tags="hmTsjsbController")
@Controller
@RequestMapping("/hmTsjsbController")
public class HmTsjsbController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmTsjsbController.class);

	@Autowired
	private HmTsjsbServiceI hmTsjsbService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 特殊计时员工表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/tsjsb/hmTsjsbList");
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
	public void datagrid(HmTsjsbEntity hmTsjsb,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmTsjsbEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmTsjsb, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmTsjsbService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除特殊计时员工表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmTsjsbEntity hmTsjsb, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmTsjsb = systemService.getEntity(HmTsjsbEntity.class, hmTsjsb.getId());
		message = "特殊计时员工表删除成功";
		try{
			hmTsjsbService.delete(hmTsjsb);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "特殊计时员工表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除特殊计时员工表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "特殊计时员工表删除成功";
		try{
			for(String id:ids.split(",")){
				HmTsjsbEntity hmTsjsb = systemService.getEntity(HmTsjsbEntity.class, 
				id
				);
				hmTsjsbService.delete(hmTsjsb);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "特殊计时员工表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加特殊计时员工表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmTsjsbEntity hmTsjsb, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "特殊计时员工表添加成功";
		try{
			hmTsjsb.setId(null);
			hmTsjsbService.save(hmTsjsb);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "特殊计时员工表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新特殊计时员工表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmTsjsbEntity hmTsjsb, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "特殊计时员工表更新成功";
		Map p = ParameterUtil.getParamMaps(request.getParameterMap());
		HmTsjsbEntity t = hmTsjsbService.get(HmTsjsbEntity.class, p.get("tid").toString());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmTsjsb, t);
			hmTsjsbService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "特殊计时员工表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 特殊计时员工表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmTsjsbEntity hmTsjsb, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmTsjsb.getId())) {
			hmTsjsb = hmTsjsbService.getEntity(HmTsjsbEntity.class, hmTsjsb.getId());
			req.setAttribute("hmTsjsbPage", hmTsjsb);
		}
		return new ModelAndView("com/hm/rsgl/tsjsb/hmTsjsb-add");
	}
	/**
	 * 特殊计时员工表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmTsjsbEntity hmTsjsb, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmTsjsb.getId())) {
			hmTsjsb = hmTsjsbService.getEntity(HmTsjsbEntity.class, hmTsjsb.getId());
			req.setAttribute("hmTsjsbPage", hmTsjsb);

		}
		return new ModelAndView("com/hm/rsgl/tsjsb/hmTsjsb-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmTsjsbController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmTsjsbEntity hmTsjsb,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmTsjsbEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmTsjsb, request.getParameterMap());
		List<HmTsjsbEntity> hmTsjsbs = this.hmTsjsbService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"特殊计时员工表");
		modelMap.put(NormalExcelConstants.CLASS,HmTsjsbEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("特殊计时员工表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmTsjsbs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmTsjsbEntity hmTsjsb,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"特殊计时员工表");
    	modelMap.put(NormalExcelConstants.CLASS,HmTsjsbEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("特殊计时员工表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		systemService.executeSql("delete from hm_tsjsb");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<HmTsjsbEntity> listHmTsjsbEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmTsjsbEntity.class,params);
				for (HmTsjsbEntity hmTsjsb : listHmTsjsbEntitys) {
					Map dict = systemService.findOneForJdbc("select t2.typecode from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where t1.typegroupcode='yglb' and t2.typename=?  limit 0,1",hmTsjsb.getYglb());
					if(Utils.notEmpty(dict)){
						hmTsjsb.setYglb(dict.get("typecode").toString());
					}
					dict = systemService.findOneForJdbc("select t2.typecode from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where t1.typegroupcode='job' and t2.typename=?  limit 0,1",hmTsjsb.getJob());
					if(Utils.notEmpty(dict)){
						hmTsjsb.setJob(dict.get("typecode").toString());
					}
					dict = systemService.findOneForJdbc("select t2.typecode from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where t1.typegroupcode='xclb' and t2.typename=?  limit 0,1",hmTsjsb.getXclb());
					if(Utils.notEmpty(dict)){
						hmTsjsb.setXclb(dict.get("typecode").toString());
					}
					hmTsjsbService.save(hmTsjsb);
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
	@ApiOperation(value="特殊计时员工表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmTsjsbEntity>> list() {
		List<HmTsjsbEntity> listHmTsjsbs=hmTsjsbService.getList(HmTsjsbEntity.class);
		return Result.success(listHmTsjsbs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取特殊计时员工表信息",notes="根据ID获取特殊计时员工表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmTsjsbEntity task = hmTsjsbService.get(HmTsjsbEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取特殊计时员工表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建特殊计时员工表")
	public ResponseMessage<?> create(@ApiParam(name="特殊计时员工表对象")@RequestBody HmTsjsbEntity hmTsjsb, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmTsjsbEntity>> failures = validator.validate(hmTsjsb);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmTsjsbService.save(hmTsjsb);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("特殊计时员工表信息保存失败");
		}
		return Result.success(hmTsjsb);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新特殊计时员工表",notes="更新特殊计时员工表")
	public ResponseMessage<?> update(@ApiParam(name="特殊计时员工表对象")@RequestBody HmTsjsbEntity hmTsjsb) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmTsjsbEntity>> failures = validator.validate(hmTsjsb);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmTsjsbService.saveOrUpdate(hmTsjsb);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新特殊计时员工表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新特殊计时员工表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除特殊计时员工表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmTsjsbService.deleteEntityById(HmTsjsbEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("特殊计时员工表删除失败");
		}

		return Result.success();
	}
}
