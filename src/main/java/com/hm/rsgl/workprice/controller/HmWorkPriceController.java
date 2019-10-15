package com.hm.rsgl.workprice.controller;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.rsgl.workprice.entity.HmWorkPriceEntity;
import com.hm.rsgl.workprice.service.HmWorkPriceServiceI;

import java.util.*;
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
 * @Description: 工价表
 * @author onlineGenerator
 * @date 2019-06-28 23:12:16
 * @version V1.0   
 *
 */
@Api(value="HmWorkPrice",description="工价表",tags="hmWorkPriceController")
@Controller
@RequestMapping("/hmWorkPriceController")
public class HmWorkPriceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmWorkPriceController.class);

	@Autowired
	private HmWorkPriceServiceI hmWorkPriceService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 工价表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/workprice/hmWorkPriceList");
	}
	/**
	 * 工资明细表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "detail")
	public ModelAndView detail(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/workprice/detail");
	}
	@RequestMapping(params = "detailList")
	public ModelAndView detailList(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/workprice/detailList");
	}
	@RequestMapping(params = "workTimeList")
	public ModelAndView workTimeList(HttpServletRequest request) {
		Map<String,String> p = ParameterUtil.getParamMaps(request.getParameterMap());
		List<HmWorkPriceEntity> workPriceEntityList = systemService.findHql("from HmWorkPriceEntity where type=5");
		request.setAttribute("workPriceEntityList",workPriceEntityList);
		return new ModelAndView("com/hm/rsgl/workprice/workTimeList");
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
	public void datagrid(HmWorkPriceEntity hmWorkPrice,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		CriteriaQuery cq = new CriteriaQuery(HmWorkPriceEntity.class, dataGrid);
		if(Utils.notEmpty(map.get("kdDate_begin"))){
			hmWorkPrice.setKdDate(null);
			cq.ge("kdDate",map.get("kdDate_begin"));
		}
		if(Utils.notEmpty(map.get("kdDate_end"))){
			hmWorkPrice.setKdDate(null);
			cq.le("kdDate",map.get("kdDate_end"));
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmWorkPrice, request.getParameterMap());
		try{
		//自定义追加查询条件

		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		Map p = ParameterUtil.getParamMaps(request.getParameterMap());
		this.hmWorkPriceService.getDataGridReturn(cq, true);
		if(!"5".equals(hmWorkPrice.getType()) && Utils.isEmpty(p.get("showEnd"))){
			String sql = "SELECT ifnull(SUM(zcb),0) zcb,ifnull(SUM(jiab),0) jiab ,ifnull(SUM(zjhj),0) zjhj, " +
					" 	ifnull(SUM(zs_zcb),0) zsZcb,ifnull(SUM(zs_jiab),0) zsJiab,ifnull(SUM(zshj),0) zshj," +
					"	ifnull(SUM(wages),0) wages, ifnull(SUM(kf_wages),0) kfWages, " +
					"	 ifnull(SUM(choucheng),0) choucheng,ifnull(SUM(gz_hj),0) gzHj FROM hm_work_price WHERE order_id=?";
			Map tj = systemService.findOneForJdbc(sql, hmWorkPrice.getOrderId());
			dataGrid.setFooter("zcb:"+tj.get("zcb")+",jiab:"+tj.get("jiab")+",zjhj:"+tj.get("zjhj")+",zsZcb:"+tj.get("zsZcb")
					+",zsJiab:"+tj.get("zsJiab")+",zshj:" +tj.get("zshj")+",wages:" +tj.get("wages")+",kfWages:" +tj.get("kfWages")
					+",choucheng:" +tj.get("choucheng")+",gzHj:" +tj.get("gzHj"));
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除工价表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmWorkPriceEntity hmWorkPrice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmWorkPrice = systemService.getEntity(HmWorkPriceEntity.class, hmWorkPrice.getId());
		message = "工价表删除成功";
		try{
			hmWorkPriceService.delete(hmWorkPrice);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工价表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工价表删除成功";
		try{
			for(String id:ids.split(",")){
				HmWorkPriceEntity hmWorkPrice = systemService.getEntity(HmWorkPriceEntity.class, 
				id
				);
				hmWorkPriceService.delete(hmWorkPrice);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工价表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmWorkPriceEntity hmWorkPriceEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工价表添加成功";
		try{
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			TSDepart tsDepart = systemService.findUniqueByProperty(TSDepart.class,"orgCode",hmWorkPriceEntity.getWorkGroupCode());
			if(Utils.notEmpty(tsDepart)){
				hmWorkPriceEntity.setWorkGroupName(tsDepart.getDepartname());
			}
			tsDepart = systemService.findUniqueByProperty(TSDepart.class,"orgCode",hmWorkPriceEntity.getMainWorkCode());
			if(Utils.notEmpty(tsDepart)){
				hmWorkPriceEntity.setMainWorkName(tsDepart.getDepartname());
			}
			String dataRows = map.get("dataRowsValWork");
			if (Utils.notEmpty(dataRows)) {
				HmWorkPriceEntity hmWorkPrice = null;

				int rows = Integer.parseInt(dataRows);

				for (int i = 0; i < rows; i++) {
					hmWorkPrice = new HmWorkPriceEntity();
					MyBeanUtils.copyBeanNotNull2Bean(hmWorkPriceEntity,hmWorkPrice);

					if (Utils.notEmpty(map.get("orderMxList["+i+"].workNo"))) {
						hmWorkPrice.setWorkNo(map.get("orderMxList["+i+"].workNo"));
						hmWorkPrice.setRealName(map.get("orderMxList["+i+"].realName"));
						hmWorkPrice.setType(map.get("orderMxList["+i+"].type"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].zcb"))){
							hmWorkPrice.setZcb(Double.parseDouble(map.get("orderMxList["+i+"].zcb")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].jiab"))) {
							hmWorkPrice.setJiab(Double.parseDouble(map.get("orderMxList["+i+"].jiab")));
						}

						if(Utils.notEmpty(map.get("orderMxList["+i+"].ssPrice"))) {
							if(Double.parseDouble(map.get("orderMxList["+i+"].ssPrice"))>0){
								hmWorkPrice.setSsPrice(Double.parseDouble(map.get("orderMxList["+i+"].ssPrice")));
							}
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].ssPriceT"))) {
							if(Double.parseDouble(map.get("orderMxList["+i+"].ssPriceT"))>0){
								hmWorkPrice.setSsPriceT(Double.parseDouble(map.get("orderMxList["+i+"].ssPriceT")));
							}
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].tsPrice"))) {
							if(Double.parseDouble(map.get("orderMxList["+i+"].tsPrice"))>0){
								hmWorkPrice.setTsPrice(Double.parseDouble(map.get("orderMxList["+i+"].tsPrice")));
							}
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].tsPriceT"))) {
							if(Double.parseDouble(map.get("orderMxList["+i+"].tsPriceT"))>0){
								hmWorkPrice.setTsPriceT(Double.parseDouble(map.get("orderMxList["+i+"].tsPriceT")));
							}
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].tyPrice"))) {
							if(Double.parseDouble(map.get("orderMxList["+i+"].tyPrice"))>0){
								hmWorkPrice.setTyPrice(Double.parseDouble(map.get("orderMxList["+i+"].tyPrice")));
							}
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].tyPriceT"))) {
							if(Double.parseDouble(map.get("orderMxList["+i+"].tyPriceT"))>0){
								hmWorkPrice.setTyPriceT(Double.parseDouble(map.get("orderMxList["+i+"].tyPriceT")));
							}
						}

						if(Utils.notEmpty(map.get("orderMxList["+i+"].zcb"))){
							if(Utils.notEmpty(hmWorkPrice.getSsPrice())) {
								hmWorkPrice.setGzHj(hmWorkPrice.getZcb()*hmWorkPrice.getSsPrice());

							}else{
								if(Utils.notEmpty(hmWorkPrice.getTsPrice())){
									hmWorkPrice.setGzHj(hmWorkPrice.getZcb()*hmWorkPrice.getTsPrice());
								}else{
									hmWorkPrice.setGzHj(hmWorkPrice.getZcb()*hmWorkPrice.getTyPrice());
								}
							}
						}


						if(Utils.notEmpty(map.get("orderMxList["+i+"].jiab"))) {
							if(Utils.notEmpty(hmWorkPrice.getSsPriceT())) {
								hmWorkPrice.setGzHj((Utils.notEmpty(hmWorkPrice.getGzHj()) ? hmWorkPrice.getGzHj():0)+hmWorkPrice.getJiab()/60*hmWorkPrice.getSsPriceT());
							}else{
								if(Utils.notEmpty(hmWorkPrice.getTsPriceT())){
									hmWorkPrice.setGzHj((Utils.notEmpty(hmWorkPrice.getGzHj()) ? hmWorkPrice.getGzHj():0)+hmWorkPrice.getJiab()/60*hmWorkPrice.getTsPriceT());
								}else{
									hmWorkPrice.setGzHj((Utils.notEmpty(hmWorkPrice.getGzHj()) ? hmWorkPrice.getGzHj():0)+hmWorkPrice.getJiab()/60*hmWorkPrice.getTyPriceT());
								}
							}
						}
						systemService.save(hmWorkPrice);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工价表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工价表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmWorkPriceEntity hmWorkPrice, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工价表更新成功";
		HmWorkPriceEntity t = hmWorkPriceService.get(HmWorkPriceEntity.class, hmWorkPrice.getId());
		try {
			if(Utils.notEmpty(hmWorkPrice.getZcb())){
				if(Utils.notEmpty(hmWorkPrice.getSsPrice()) && hmWorkPrice.getSsPrice()>0) {
					hmWorkPrice.setGzHj(hmWorkPrice.getZcb()*hmWorkPrice.getSsPrice());
				}else{
					if(Utils.notEmpty(hmWorkPrice.getTsPrice()) && hmWorkPrice.getTsPrice()>0){
						hmWorkPrice.setGzHj(hmWorkPrice.getZcb()*hmWorkPrice.getTsPrice());
					}else{
						hmWorkPrice.setGzHj(hmWorkPrice.getZcb()*hmWorkPrice.getTyPrice());
					}
				}
			}

			if(Utils.notEmpty(hmWorkPrice.getJiab())){
				if(Utils.notEmpty(hmWorkPrice.getSsPriceT()) && hmWorkPrice.getSsPriceT()>0) {
					hmWorkPrice.setGzHj((Utils.notEmpty(hmWorkPrice.getGzHj()) ? hmWorkPrice.getGzHj():0)+hmWorkPrice.getJiab()/60*hmWorkPrice.getSsPriceT());
				}else{
					if(Utils.notEmpty(hmWorkPrice.getTsPriceT()) && hmWorkPrice.getTsPriceT()>0){
						hmWorkPrice.setGzHj((Utils.notEmpty(hmWorkPrice.getGzHj()) ? hmWorkPrice.getGzHj():0)+hmWorkPrice.getJiab()/60*hmWorkPrice.getTsPriceT());
					}else{
						hmWorkPrice.setGzHj((Utils.notEmpty(hmWorkPrice.getGzHj()) ? hmWorkPrice.getGzHj():0)+hmWorkPrice.getJiab()/60*hmWorkPrice.getTyPriceT());
					}
				}
			}

			MyBeanUtils.copyBeanNotNull2Bean(hmWorkPrice, t);
			hmWorkPriceService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工价表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工价表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmWorkPriceEntity hmWorkPrice, HttpServletRequest req) {
		req.getSession().setAttribute("gdNamesCodeS","");
		req.setAttribute("kdDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		if (StringUtil.isNotEmpty(hmWorkPrice.getId())) {
			hmWorkPrice = hmWorkPriceService.getEntity(HmWorkPriceEntity.class, hmWorkPrice.getId());
			req.setAttribute("hmWorkPricePage", hmWorkPrice);
		}
		return new ModelAndView("com/hm/rsgl/workprice/hmWorkPrice-add");
	}
	/**
	 * 工价表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmWorkPriceEntity hmWorkPrice, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmWorkPrice.getId())) {
			hmWorkPrice = hmWorkPriceService.getEntity(HmWorkPriceEntity.class, hmWorkPrice.getId());
			req.setAttribute("hmWorkPricePage", hmWorkPrice);
		}
		return new ModelAndView("com/hm/rsgl/workprice/hmWorkPrice-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmWorkPriceController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmWorkPriceEntity hmWorkPrice,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmWorkPriceEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmWorkPrice, request.getParameterMap());
		List<HmWorkPriceEntity> hmWorkPrices = this.hmWorkPriceService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"工价表");
		modelMap.put(NormalExcelConstants.CLASS,HmWorkPriceEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmWorkPrices);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmWorkPriceEntity hmWorkPrice,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"工价表");
    	modelMap.put(NormalExcelConstants.CLASS,HmWorkPriceEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工价表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmWorkPriceEntity> listHmWorkPriceEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmWorkPriceEntity.class,params);
				for (HmWorkPriceEntity hmWorkPrice : listHmWorkPriceEntitys) {
					hmWorkPriceService.save(hmWorkPrice);
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
	@ApiOperation(value="工价表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmWorkPriceEntity>> list() {
		List<HmWorkPriceEntity> listHmWorkPrices=hmWorkPriceService.getList(HmWorkPriceEntity.class);
		return Result.success(listHmWorkPrices);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取工价表信息",notes="根据ID获取工价表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmWorkPriceEntity task = hmWorkPriceService.get(HmWorkPriceEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取工价表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建工价表")
	public ResponseMessage<?> create(@ApiParam(name="工价表对象")@RequestBody HmWorkPriceEntity hmWorkPrice, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmWorkPriceEntity>> failures = validator.validate(hmWorkPrice);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmWorkPriceService.save(hmWorkPrice);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工价表信息保存失败");
		}
		return Result.success(hmWorkPrice);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新工价表",notes="更新工价表")
	public ResponseMessage<?> update(@ApiParam(name="工价表对象")@RequestBody HmWorkPriceEntity hmWorkPrice) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmWorkPriceEntity>> failures = validator.validate(hmWorkPrice);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmWorkPriceService.saveOrUpdate(hmWorkPrice);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新工价表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新工价表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除工价表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmWorkPriceService.deleteEntityById(HmWorkPriceEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工价表删除失败");
		}

		return Result.success();
	}
}
