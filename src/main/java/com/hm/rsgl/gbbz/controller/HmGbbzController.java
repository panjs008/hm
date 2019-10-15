package com.hm.rsgl.gbbz.controller;
import com.emk.util.Utils;
import com.hm.rsgl.gbbz.entity.HmGbbzEntity;
import com.hm.rsgl.gbbz.service.HmGbbzServiceI;
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
 * @Description: 公班标准
 * @author onlineGenerator
 * @date 2019-06-28 22:34:14
 * @version V1.0   
 *
 */
@Api(value="HmGbbz",description="公班标准",tags="hmGbbzController")
@Controller
@RequestMapping("/hmGbbzController")
public class HmGbbzController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmGbbzController.class);

	@Autowired
	private HmGbbzServiceI hmGbbzService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 公班标准列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/gbbz/hmGbbzList");
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
	public void datagrid(HmGbbzEntity hmGbbz,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmGbbzEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmGbbz, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmGbbzService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除公班标准
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmGbbzEntity hmGbbz, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmGbbz = systemService.getEntity(HmGbbzEntity.class, hmGbbz.getId());
		message = "公班标准删除成功";
		try{
			hmGbbzService.delete(hmGbbz);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公班标准删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除公班标准
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公班标准删除成功";
		try{
			for(String id:ids.split(",")){
				HmGbbzEntity hmGbbz = systemService.getEntity(HmGbbzEntity.class, 
				id
				);
				hmGbbzService.delete(hmGbbz);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "公班标准删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加公班标准
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmGbbzEntity hmGbbz, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公班标准添加成功";
		try{
			hmGbbzService.save(hmGbbz);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "公班标准添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新公班标准
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmGbbzEntity hmGbbz, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "公班标准更新成功";
		HmGbbzEntity t = hmGbbzService.get(HmGbbzEntity.class, hmGbbz.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmGbbz, t);
			hmGbbzService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "公班标准更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 公班标准新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmGbbzEntity hmGbbz, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmGbbz.getId())) {
			hmGbbz = hmGbbzService.getEntity(HmGbbzEntity.class, hmGbbz.getId());
			req.setAttribute("hmGbbzPage", hmGbbz);
		}
		return new ModelAndView("com/hm/rsgl/gbbz/hmGbbz-add");
	}
	/**
	 * 公班标准编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmGbbzEntity hmGbbz, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmGbbz.getId())) {
			hmGbbz = hmGbbzService.getEntity(HmGbbzEntity.class, hmGbbz.getId());
			req.setAttribute("hmGbbzPage", hmGbbz);
		}
		return new ModelAndView("com/hm/rsgl/gbbz/hmGbbz-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmGbbzController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmGbbzEntity hmGbbz,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmGbbzEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmGbbz, request.getParameterMap());
		List<HmGbbzEntity> hmGbbzs = this.hmGbbzService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"公班标准");
		modelMap.put(NormalExcelConstants.CLASS,HmGbbzEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公班标准列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmGbbzs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmGbbzEntity hmGbbz,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"公班标准");
    	modelMap.put(NormalExcelConstants.CLASS,HmGbbzEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("公班标准列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		systemService.executeSql("delete from hm_gbbz");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<HmGbbzEntity> listHmGbbzEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmGbbzEntity.class,params);
				for (HmGbbzEntity hmGbbz : listHmGbbzEntitys) {
					if("男".equals(hmGbbz.getSex())){
						hmGbbz.setSex("01");
					}
					if("女".equals(hmGbbz.getSex())){
						hmGbbz.setSex("02");
					}
					Map dict = systemService.findOneForJdbc("select t2.typecode from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where t1.typegroupcode='yglb' and t2.typename=?  limit 0,1",hmGbbz.getYglx());
					if(Utils.notEmpty(dict)){
						hmGbbz.setYglx(dict.get("typecode").toString());
					}
					hmGbbzService.save(hmGbbz);
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
	@ApiOperation(value="公班标准列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmGbbzEntity>> list() {
		List<HmGbbzEntity> listHmGbbzs=hmGbbzService.getList(HmGbbzEntity.class);
		return Result.success(listHmGbbzs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取公班标准信息",notes="根据ID获取公班标准信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmGbbzEntity task = hmGbbzService.get(HmGbbzEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取公班标准信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建公班标准")
	public ResponseMessage<?> create(@ApiParam(name="公班标准对象")@RequestBody HmGbbzEntity hmGbbz, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmGbbzEntity>> failures = validator.validate(hmGbbz);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmGbbzService.save(hmGbbz);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("公班标准信息保存失败");
		}
		return Result.success(hmGbbz);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新公班标准",notes="更新公班标准")
	public ResponseMessage<?> update(@ApiParam(name="公班标准对象")@RequestBody HmGbbzEntity hmGbbz) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmGbbzEntity>> failures = validator.validate(hmGbbz);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmGbbzService.saveOrUpdate(hmGbbz);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新公班标准信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新公班标准信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除公班标准")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmGbbzService.deleteEntityById(HmGbbzEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("公班标准删除失败");
		}

		return Result.success();
	}
}
