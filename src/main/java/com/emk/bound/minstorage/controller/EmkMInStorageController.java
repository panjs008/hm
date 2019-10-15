package com.emk.bound.minstorage.controller;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity2;
import com.emk.bound.minstorage.service.EmkMInStorageServiceI;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.bound.moutstorage.entity.EmkMOutStorageEntity;
import com.emk.product.product.entity.EmkProductEntity;
import com.emk.storage.accessories.entity.EmkAccessoriesEntity;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.storage.entity.EmkStorageEntity;
import com.emk.storage.storagelog.entity.EmkStorageLogEntity;
import com.emk.util.*;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;

import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

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
 * @Description: 入库申请表
 * @author onlineGenerator
 * @date 2018-09-15 11:33:47
 * @version V1.0   
 *
 */
@Api(value="EmkMInStorage",description="入库申请表",tags="emkMInStorageController")
@Controller
@RequestMapping("/emkMInStorageController")
public class EmkMInStorageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EmkMInStorageController.class);

	@Autowired
	private EmkMInStorageServiceI emkMInStorageService;

	@Autowired
	private Validator validator;


	/**
	 * 入库申请表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorageList");
	}

	@RequestMapping(params = "list2")
	public ModelAndView list2(HttpServletRequest request) {
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorageList2");
	}

	@RequestMapping(params = "list3")
	public ModelAndView list3(HttpServletRequest request) {
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorageList3");
	}

	@RequestMapping(params="emkMInStorageDetailList")
	public ModelAndView rkglMxList(HttpServletRequest request) {
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());

		if (Utils.notEmpty(map.get("inStorageId"))){
			List<EmkMInStorageDetailEntity> rkglMxEntities = systemService.findHql("from EmkMInStorageDetailEntity where inStorageId=?",  map.get("inStorageId"));
			request.setAttribute("rkglMxList", rkglMxEntities);
		}
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorageDetailList");
	}

	@RequestMapping(params = "orderMxList")
	public ModelAndView orderMxList(HttpServletRequest request) {
		request.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if (Utils.notEmpty(map.get("inStorageId"))) {
			List<EmkMInStorageDetailEntity> rkglMxEntities = systemService.findHql("from EmkMInStorageDetailEntity where inStorageId=? and type=?",  map.get("inStorageId"),map.get("type"));
			request.setAttribute("rkglMxList", rkglMxEntities);
		}
		if(map.get("type").equals("0")){
			return new ModelAndView("com/emk/bound/minstorage/orderMxList");
		}else if(map.get("type").equals("1")){
			return new ModelAndView("com/emk/bound/minstorage/orderMxList2");
		}else if(map.get("type").equals("2")){
			return new ModelAndView("com/emk/bound/minstorage/orderMxList3");
		}
		return new ModelAndView("com/emk/bound/minstorage/orderMxList");
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
	public void datagrid(EmkMInStorageEntity emkMInStorage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EmkMInStorageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkMInStorage, request.getParameterMap());
		try{
		//自定义追加查询条件
			TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
			Map roleMap = (Map) request.getSession().getAttribute("ROLE");
			if(roleMap != null){
				if(roleMap.get("rolecode").toString().contains("cgy") || roleMap.get("rolecode").toString().contains("scgdy")){
					cq.eq("createBy",user.getUserName());
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		emkMInStorageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除入库申请表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EmkMInStorageEntity emkMInStorage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		emkMInStorage = systemService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
		message = "入库申请表删除成功";
		try{
			emkMInStorageService.delete(emkMInStorage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "入库申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除入库申请表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请表删除成功";
		try{
			for(String id:ids.split(",")){
				EmkMInStorageEntity emkMInStorage = systemService.getEntity(EmkMInStorageEntity.class, id);
				systemService.executeSql("delete from emk_m_in_storage_detail where in_storage_id=?", id);
				emkMInStorageService.delete(emkMInStorage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "入库申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加入库申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EmkMInStorageEntity emkMInStorage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请表添加成功";
		try{
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			emkMInStorage.setState("0");
			emkMInStorage.setAppler(map.get("realName"));
			TSUser tsUser = systemService.findUniqueByProperty(TSUser.class,"userName",map.get("userName"));
			emkMInStorage.setApplerId(tsUser.getId());
			emkMInStorage.setRker(map.get("sender"));
			emkMInStorage.setRkerId(map.get("senderUserNames"));
/*
			emkMInStorageEntity.setRker(user.getRealName());
			emkMInStorageEntity.setRkerId(user.getUserName());
			emkMInStorageEntity.setCaigouer(bpmUser.getRealName());
			emkMInStorageEntity.setCaigouerName(bpmUser.getUserName());*/
			emkMInStorage.setRkNo("RK"+DateUtil.format(new Date(),"yyyyMMddHHmmss"));

			emkMInStorageService.save(emkMInStorage);
			String dataRows = map.get("orderMxListID");
			dataRows = map.get("orderMxListID");
			//保存原料面料数据
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
						emkMInStorageDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
						emkMInStorageDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
						emkMInStorageDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
						emkMInStorageDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
						emkMInStorageDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
						emkMInStorageDetailEntity.setTotal(map.get("orderMxList["+i+"].total"));
						emkMInStorageDetailEntity.setInTotal(map.get("orderMxList["+i+"].inTotal"));
						emkMInStorageDetailEntity.setKdDate(map.get("orderMxList["+i+"].kdDate"));
						emkMInStorageDetailEntity.setActualTotal(map.get("orderMxList["+i+"].actualTotal"));
						emkMInStorageDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate"));
						emkMInStorageDetailEntity.setInStorageId(emkMInStorage.getId());
						emkMInStorageDetailEntity.setType("0");
						systemService.save(emkMInStorageDetailEntity);
					}
				}
			}
			dataRows = map.get("orderMxListID2");
			//保存缝制辅料数据
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
						emkMInStorageDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
						emkMInStorageDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
						emkMInStorageDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
						emkMInStorageDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
						emkMInStorageDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
						emkMInStorageDetailEntity.setTotal(map.get("orderMxList["+i+"].total"));
						emkMInStorageDetailEntity.setInTotal(map.get("orderMxList["+i+"].inTotal"));
						emkMInStorageDetailEntity.setKdDate(map.get("orderMxList["+i+"].kdDate"));
						emkMInStorageDetailEntity.setActualTotal(map.get("orderMxList["+i+"].actualTotal"));
						emkMInStorageDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate"));
						emkMInStorageDetailEntity.setInStorageId(emkMInStorage.getId());
						emkMInStorageDetailEntity.setType("1");
						systemService.save(emkMInStorageDetailEntity);
					}
				}
			}
			dataRows = map.get("orderMxListID3");
			//保存包装辅料数据
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
						emkMInStorageDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
						emkMInStorageDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
						emkMInStorageDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
						emkMInStorageDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
						emkMInStorageDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
						emkMInStorageDetailEntity.setTotal(map.get("orderMxList["+i+"].total"));
						emkMInStorageDetailEntity.setInTotal(map.get("orderMxList["+i+"].inTotal"));
						emkMInStorageDetailEntity.setKdDate(map.get("orderMxList["+i+"].kdDate"));
						emkMInStorageDetailEntity.setActualTotal(map.get("orderMxList["+i+"].actualTotal"));
						emkMInStorageDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate"));
						emkMInStorageDetailEntity.setInStorageId(emkMInStorage.getId());
						emkMInStorageDetailEntity.setType("2");
						systemService.save(emkMInStorageDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "入库申请表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新入库申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EmkMInStorageEntity emkMInStorage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请表更新成功";
		Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

		EmkMInStorageEntity t = emkMInStorageService.get(EmkMInStorageEntity.class, map.get("emkMInStorageId"));
		try {
			if(!t.getState().equals("0")){
				j.setMsg("入库单在处理中无法进行修改");
				j.setSuccess(false);
				return j;
			}
			emkMInStorage.setState("0");
			emkMInStorage.setId(null);
			emkMInStorage.setAppler(map.get("realName"));
			emkMInStorage.setApplerId(map.get("userName"));
			emkMInStorage.setRker(map.get("sender"));
			emkMInStorage.setRkerId(map.get("senderUserNames"));
			MyBeanUtils.copyBeanNotNull2Bean(emkMInStorage, t);
			emkMInStorageService.saveOrUpdate(t);
			String dataRows = map.get("orderMxListID");
			dataRows = map.get("orderMxListID");
			//保存原料面料数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_m_in_storage_detail where in_storage_id = ? and type=0",t.getId());
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
						emkMInStorageDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
						emkMInStorageDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
						emkMInStorageDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
						emkMInStorageDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
						emkMInStorageDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
						emkMInStorageDetailEntity.setTotal(map.get("orderMxList["+i+"].total"));
						emkMInStorageDetailEntity.setInTotal(map.get("orderMxList["+i+"].inTotal"));
						emkMInStorageDetailEntity.setKdDate(map.get("orderMxList["+i+"].kdDate"));
						emkMInStorageDetailEntity.setActualTotal(map.get("orderMxList["+i+"].actualTotal"));
						emkMInStorageDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate"));
						emkMInStorageDetailEntity.setInStorageId(t.getId());
						emkMInStorageDetailEntity.setType("0");
						systemService.save(emkMInStorageDetailEntity);
					}
				}
			}
			dataRows = map.get("orderMxListID2");
			//保存缝制辅料数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_m_in_storage_detail where in_storage_id = ? and type=1",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
						emkMInStorageDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
						emkMInStorageDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
						emkMInStorageDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
						emkMInStorageDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
						emkMInStorageDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
						emkMInStorageDetailEntity.setTotal(map.get("orderMxList["+i+"].total"));
						emkMInStorageDetailEntity.setInTotal(map.get("orderMxList["+i+"].inTotal"));
						emkMInStorageDetailEntity.setKdDate(map.get("orderMxList["+i+"].kdDate"));
						emkMInStorageDetailEntity.setActualTotal(map.get("orderMxList["+i+"].actualTotal"));
						emkMInStorageDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate"));
						emkMInStorageDetailEntity.setInStorageId(t.getId());
						emkMInStorageDetailEntity.setType("1");
						systemService.save(emkMInStorageDetailEntity);
					}
				}
			}
			dataRows = map.get("orderMxListID3");
			//保存包装辅料数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_m_in_storage_detail where in_storage_id = ? and type=2",t.getId());

				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkMInStorageDetailEntity emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
						emkMInStorageDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
						emkMInStorageDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
						emkMInStorageDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
						emkMInStorageDetailEntity.setHtNum(map.get("orderMxList["+i+"].htNum"));
						emkMInStorageDetailEntity.setBrand(map.get("orderMxList["+i+"].brand"));
						emkMInStorageDetailEntity.setTotal(map.get("orderMxList["+i+"].total"));
						emkMInStorageDetailEntity.setInTotal(map.get("orderMxList["+i+"].inTotal"));
						emkMInStorageDetailEntity.setKdDate(map.get("orderMxList["+i+"].kdDate"));
						emkMInStorageDetailEntity.setActualTotal(map.get("orderMxList["+i+"].actualTotal"));
						emkMInStorageDetailEntity.setOutDate(map.get("orderMxList["+i+"].outDate"));
						emkMInStorageDetailEntity.setInStorageId(t.getId());
						emkMInStorageDetailEntity.setType("2");
						systemService.save(emkMInStorageDetailEntity);
					}
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "入库申请表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "doPrintPDF")
	public String doPrintPDF(String ids,EmkMInStorageEntity emkMInStorage, HttpServletRequest request,HttpServletResponse response) {
		String message = null;
		try {
			for (String id : ids.split(",")) {
				String fileName = "c:\\PDF\\"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
				File file = new File(fileName);
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				emkMInStorage=  systemService.get(EmkMInStorageEntity.class, id);
				List<EmkMInStorageDetailEntity> rkglMxEntities = systemService.findHql("from EmkMInStorageDetailEntity where inStorageId=?",id);
				new createPdf(file).generateRkdPDF(emkMInStorage,rkglMxEntities,findProcessList(id));
				String fFileName = "c:\\PDF\\F"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
				if("0".equals(emkMInStorage.getType())){
					WaterMark.waterMark(fileName,fFileName, "原料面料需求开发单");
				}else if("1".equals(emkMInStorage.getType())){
					WaterMark.waterMark(fileName,fFileName, "缝制辅料需求开发单");
				}else if("2".equals(emkMInStorage.getType())){
					WaterMark.waterMark(fileName,fFileName, "包装辅料需求开发单");
				}
				file.delete();
				WebFileUtils.downLoad(fFileName,response,false);
				file = new File(fFileName);
				file.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = "导出PDF失败";
			throw new BusinessException(e.getMessage());
		}
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 入库申请表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
		if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
			emkMInStorage = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
			req.setAttribute("emkMInStoragePage", emkMInStorage);
		}
		if(emkMInStorage.getType().equals("0")){
			req.setAttribute("pactTypeName", "原料面料");
		}else if(emkMInStorage.getType().equals("1")){
			req.setAttribute("pactTypeName", "缝制辅料");
		}else if(emkMInStorage.getType().equals("2")){
			req.setAttribute("pactTypeName", "包装辅料");
		}
		req.getSession().setAttribute("orderFinish", "");

		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-add");
	}
	/**
	 * 入库申请表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));

		if(Utils.notEmpty(emkMInStorage.getMaterialNo())){
			String materialNo = "";
			Map param = ParameterUtil.getParamMaps(req.getParameterMap());
			if(emkMInStorage.getType().equals("0")){
				EmkMaterialEntity emkMaterialEntity = systemService.getEntity(EmkMaterialEntity.class,emkMInStorage.getMaterialNo());
				materialNo = emkMaterialEntity.getMaterialNo();
			}else if(emkMInStorage.getType().equals("1")){
				EmkAccessoriesEntity emkAccessoriesEntity = systemService.getEntity(EmkAccessoriesEntity.class,emkMInStorage.getMaterialNo());
				materialNo = emkAccessoriesEntity.getMaterialNo();
			}
			emkMInStorage = systemService.findUniqueByProperty(EmkMInStorageEntity.class,"materialNo",materialNo);

		}else{
			if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
				emkMInStorage = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
			}
		}
		try {
			Map countMap = MyBeanUtils.culBeanCounts(emkMInStorage);
			int a=0,b=0;
			a = Integer.parseInt(countMap.get("finishColums").toString());
			b = Integer.parseInt(countMap.get("Colums").toString())-4;

			countMap.put("finishColums",a);
			countMap.put("Colums",b);
			req.setAttribute("countMap", countMap);
			DecimalFormat df = new DecimalFormat("#.00");
			req.setAttribute("recent", df.format(a*100/b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("emkMInStoragePage", emkMInStorage);
		/*if(emkMInStorage.getType().equals("0")){
			req.setAttribute("pactTypeName", "原料面料");
		}else if(emkMInStorage.getType().equals("1")){
			req.setAttribute("pactTypeName", "缝制辅料");
		}else if(emkMInStorage.getType().equals("2")){
			req.setAttribute("pactTypeName", "包装辅料");
		}*/
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-update");
	}
	@RequestMapping(params = "goUpdate1")
	public ModelAndView goUpdate1(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		if(Utils.notEmpty(emkMInStorage.getMaterialNo())){
			EmkAccessoriesEntity emkAccessoriesEntity = systemService.getEntity(EmkAccessoriesEntity.class,emkMInStorage.getMaterialNo());
			if(Utils.notEmpty(emkAccessoriesEntity)){
				emkMInStorage = systemService.findUniqueByProperty(EmkMInStorageEntity.class,"materialNo",emkAccessoriesEntity.getMaterialNo());

				try {
					Map countMap = MyBeanUtils.culBeanCounts(emkMInStorage);
					int a=0,b=0;
					a = Integer.parseInt(countMap.get("finishColums").toString());
					b = Integer.parseInt(countMap.get("Colums").toString())-4;

					countMap.put("finishColums",a);
					countMap.put("Colums",b);
					req.setAttribute("countMap", countMap);
					DecimalFormat df = new DecimalFormat("#.00");
					req.setAttribute("recent", df.format(a*100/b));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		req.setAttribute("emkMInStoragePage", emkMInStorage);
		req.setAttribute("pactTypeName", "缝制辅料");

		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-update");
	}

	@RequestMapping(params="goUpdate2")
	public ModelAndView goUpdate2(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
			emkMInStorage = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorage.getId());
			req.setAttribute("emkMInStoragePage", emkMInStorage);
			try {
				Map countMap = MyBeanUtils.culBeanCounts(emkMInStorage);
				int a=0,b=0;
				a = Integer.parseInt(countMap.get("finishColums").toString());
				b = Integer.parseInt(countMap.get("Colums").toString())-4;

				countMap.put("finishColums",a);
				countMap.put("Colums",b);
				req.setAttribute("countMap", countMap);
				DecimalFormat df = new DecimalFormat("#.00");
				req.setAttribute("recent", df.format(a*100/b));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*if(emkMInStorage.getType().equals("0")){
			req.setAttribute("pactTypeName", "原料面料");
		}else if(emkMInStorage.getType().equals("1")){
			req.setAttribute("pactTypeName", "缝制辅料");
		}else if(emkMInStorage.getType().equals("2")){
			req.setAttribute("pactTypeName", "包装辅料");
		}*/
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-update2");
	}
	@RequestMapping(params="goUpdate3")
	public ModelAndView goUpdate3(EmkMInStorageEntity emkMInStorage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkMInStorage.getId())) {
			EmkMaterialRequiredEntity emkMaterialRequiredEntity = systemService.get(EmkMaterialRequiredEntity.class,emkMInStorage.getId());
			List<EmkMInStorageEntity> emkMInStorageEntityList = systemService.findHql("from EmkMInStorageEntity where materialNo=? and type=?",emkMaterialRequiredEntity.getMaterialNo(),emkMaterialRequiredEntity.getType());
			if(Utils.notEmpty(emkMInStorageEntityList)){
				emkMInStorage = emkMInStorageEntityList.get(0);
				req.setAttribute("emkMInStoragePage",emkMInStorage);

				try {
					Map countMap = MyBeanUtils.culBeanCounts(emkMInStorage);
					int a=0,b=0;
					a = Integer.parseInt(countMap.get("finishColums").toString());
					b = Integer.parseInt(countMap.get("Colums").toString())-4;

					countMap.put("finishColums",a);
					countMap.put("Colums",b);
					req.setAttribute("countMap", countMap);
					DecimalFormat df = new DecimalFormat("#.00");
					req.setAttribute("recent", df.format(a*100/b));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(emkMInStorage.getType().equals("0")){
					req.setAttribute("pactTypeName", "原料面料");
				}else if(emkMInStorage.getType().equals("1")){
					req.setAttribute("pactTypeName", "缝制辅料");
				}else if(emkMInStorage.getType().equals("2")){
					req.setAttribute("pactTypeName", "包装辅料");
				}
			}


		}
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-update3");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","emkMInStorageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EmkMInStorageEntity emkMInStorage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EmkMInStorageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, emkMInStorage, request.getParameterMap());
		List<EmkMInStorageEntity> emkMInStorages = emkMInStorageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"入库申请表");
		modelMap.put(NormalExcelConstants.CLASS,EmkMInStorageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("入库申请表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,emkMInStorages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EmkMInStorageEntity emkMInStorage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"入库申请表");
    	modelMap.put(NormalExcelConstants.CLASS,EmkMInStorageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("入库申请表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EmkMInStorageEntity> listEmkMInStorageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EmkMInStorageEntity.class,params);
				for (EmkMInStorageEntity emkMInStorage : listEmkMInStorageEntitys) {
					emkMInStorageService.save(emkMInStorage);
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
	@ApiOperation(value="入库申请表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EmkMInStorageEntity>> list() {
		List<EmkMInStorageEntity> listEmkMInStorages=emkMInStorageService.getList(EmkMInStorageEntity.class);
		return Result.success(listEmkMInStorages);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取入库申请表信息",notes="根据ID获取入库申请表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EmkMInStorageEntity task = emkMInStorageService.get(EmkMInStorageEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取入库申请表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建入库申请表")
	public ResponseMessage<?> create(@ApiParam(name="入库申请表对象")@RequestBody EmkMInStorageEntity emkMInStorage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkMInStorageEntity>> failures = validator.validate(emkMInStorage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkMInStorageService.save(emkMInStorage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("入库申请表信息保存失败");
		}
		return Result.success(emkMInStorage);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新入库申请表",notes="更新入库申请表")
	public ResponseMessage<?> update(@ApiParam(name="入库申请表对象")@RequestBody EmkMInStorageEntity emkMInStorage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EmkMInStorageEntity>> failures = validator.validate(emkMInStorage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			emkMInStorageService.saveOrUpdate(emkMInStorage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新入库申请表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新入库申请表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除入库申请表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			emkMInStorageService.deleteEntityById(EmkMInStorageEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("入库申请表删除失败");
		}

		return Result.success();
	}

	@RequestMapping(params="doSubmit")
	@ResponseBody
	public AjaxJson doSubmit(EmkMInStorageEntity2 emkMInStorageEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "入库申请单提交成功";
		try {
			int flag = 0;
			TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

			if(Utils.isEmpty(emkMInStorageEntity.getId())){
				for (String id : map.get("ids").toString().split(",")) {
					EmkMInStorageEntity inStorageEntity = systemService.getEntity(EmkMInStorageEntity.class, id);
					if (!inStorageEntity.getState().equals("0")) {
						message = "存在已提交的入库单，请重新选择在提交入库单！";
						j.setSuccess(false);
						flag = 1;
						break;
					}
				}
			}else{
				map.put("ids", emkMInStorageEntity.getId());
			}
			Map<String, Object> variables = new HashMap();
			if (flag == 0) {
				for (String id : map.get("ids").toString().split(",")) {
					EmkMInStorageEntity t = emkMInStorageService.get(EmkMInStorageEntity.class, id);
					/*if(t.getType().equals("0")){
						EmkMaterialEntity e = systemService.findUniqueByProperty(EmkMaterialEntity.class,"materialNo",t.getMaterialNo());
						if(Utils.notEmpty(e)){
							if(!e.getState().equals("41")){
								message = "提交入库单采购经理还未审核通过，无法提交！";
								j.setMsg(message);
								j.setSuccess(false);
								return j;
							}
						}
					}else if(t.getType().equals("1")){
						EmkAccessoriesEntity e = systemService.findUniqueByProperty(EmkAccessoriesEntity.class,"materialNo",t.getMaterialNo());
						if(Utils.notEmpty(e)){
							if(!e.getState().equals("41")){
								message = "提交入库单采购经理还未审核通过，无法提交！";
								j.setSuccess(false);
								return j;
							}
						}
					}*/

					t.setState("1");
					variables.put("optUser", t.getId());
					message = "操作成功";

					EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
					if(Utils.isEmpty(b)){
						//type 工单类型，workNum 单号，formId 对应表单ID，bpmName 节点名称，bpmNode 节点代码，advice 处理意见，user 用户对象
						b = new EmkApprovalEntity();
						EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
						ApprovalUtil.saveApproval(b,5,t.getRkNo(),t.getId(),user);
						systemService.save(b);
						ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"入库申请单","instorageTask","提交",user);
						systemService.save(approvalDetailEntity);
					}

					List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
					TSUser makerUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getCreateBy());
					TSUser bpmUser = null;
					if (task.size() > 0) {
						bpmUser = systemService.get(TSUser.class,b.getBpmSherId());
					}else{
						bpmUser = systemService.get(TSUser.class,b.getCommitId());
					}
					//保存审批意见
					EmkApprovalDetailEntity approvalDetail = ApprovalUtil.saveApprovalDetail(b.getId(),user,b,map.get("advice"));

					if (task.size() > 0) {
						Task task1 = (Task)task.get(task.size() - 1);
						if (task1.getTaskDefinitionKey().equals("instorageTask")) {
							taskService.complete(task1.getId(), variables);
							t.setState("1");
							b.setStatus(1);

							b.setNextBpmSher(t.getCaigouerName());
							b.setNextBpmSherId(t.getCaigouer());
							b.setProcessName("入库申请单【仓管员】");

							saveApprvoalDetail(approvalDetail,"重新提交的入库申请单","instorageTask",0,"重新提交入库申请单");
							bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getCaigouer());
							saveSmsAndEmailForOne("入库申请单【仓管员】","您有【"+user.getRealName()+"】重新提交的入库申请单，单号："+b.getWorkNum()+"，请及时审核。",bpmUser,user.getUserName());

						}
						if (task1.getTaskDefinitionKey().equals("checkTask")) {
							if (map.get("isPass").equals("0")) {
								variables.put("isPass","0");
								taskService.complete(task1.getId(), variables);
								b.setStatus(24);
								approvalDetail.setBpmName("采购员审核");
								t.setState("24");
								approvalDetail.setApproveStatus(0);
								if(Utils.notEmpty(t.getOrderNo())){
									List<EmkMaterialRequiredEntity> emkMaterialRequiredEntityList = systemService.findHql("from EmkMaterialRequiredEntity where materialNo=? and type=?",t.getMaterialNo(),t.getType());
									if(Utils.notEmpty(emkMaterialRequiredEntityList)){
										EmkMaterialRequiredEntity c = emkMaterialRequiredEntityList.get(0);
										EmkApprovalEntity d = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",c.getId());

										List<Task>  task0 = taskService.createTaskQuery().taskAssignee(c.getId()).list();
										if(Utils.notEmpty(task0)){
											Task task00 = task.get(task0.size() - 1);
											if (task00.getTaskDefinitionKey().equals("ckyrkTask")) {
												c.setState("57");
												d.setStatus(57);
												variables.put("inputUser",c.getId());
												taskService.complete(task00.getId(), variables);
												approvalDetail = ApprovalUtil.saveApprovalDetail(d.getId(),user,d,map.get("advice"));

												systemService.saveOrUpdate(c);
												systemService.saveOrUpdate(d);
											}
										}
									}
								}

							}else{
								saveApprvoalDetail(approvalDetail,"采购员审核","checkTask",1,map.get("advice"));
								backProcess(task1.getProcessInstanceId(),"checkTask","instorageTask","入库申请单");
								systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"isPass");

								t.setState("0");
								b.setStatus(0);
								b.setBpmStatus("1");
								saveSmsAndEmailForOne("采购员审核","您有【"+user.getRealName()+"】回退的入库申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						if (task1.getTaskDefinitionKey().equals("cgyzjTask")) {
							if (map.get("isPass").equals("0")) {
								variables.put("isPass","0");
								taskService.complete(task1.getId(), variables);
								b.setStatus(40);
								approvalDetail.setBpmName("采购员质检");
								t.setState("40");
								approvalDetail.setApproveStatus(0);

								bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getRkerId());
								saveSmsAndEmailForOne("采购员质检","您有【"+user.getRealName()+"】质检通过的入库申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						}
						if (task1.getTaskDefinitionKey().equals("rkTask")) {
							//if (map.get("isPass").equals("0")) {
								//生成出库单
								EmkMaterialEntity emkMaterialEntity = systemService.findUniqueByProperty(EmkMaterialEntity.class,"materialNo",t.getMaterialNo());
								List<EmkMaterialRequiredEntity> emkMaterialRequiredEntityList = systemService.findHql("from EmkMaterialRequiredEntity where materialNo=? and type=?",t.getMaterialNo(),t.getType());
								EmkMOutStorageEntity emkMOutStorageEntity = new EmkMOutStorageEntity();
								MyBeanUtils.copyBeanNotNull2Bean(t,emkMOutStorageEntity);
								emkMOutStorageEntity.setId(null);
								emkMOutStorageEntity.setCkNo("CK"+ DateUtil.format(new Date(),"yyyyMMddHHmmss"));
								emkMOutStorageEntity.setType("0");
								emkMOutStorageEntity.setState("0");
								emkMOutStorageEntity.setFormType("1");
								emkMOutStorageEntity.setMaterialNo(t.getMaterialNo());

								if(Utils.notEmpty(emkMaterialEntity)){
									emkMOutStorageEntity.setCreateBy(emkMaterialEntity.getCreateBy());
									emkMOutStorageEntity.setCreateName(emkMaterialEntity.getCreateName());
									emkMOutStorageEntity.setLler(emkMaterialEntity.getCreateName());
									emkMOutStorageEntity.setLlerId(emkMaterialEntity.getCreateBy());
									emkMOutStorageEntity.setAppler(emkMaterialEntity.getCreateName());
									emkMOutStorageEntity.setApplerId(emkMaterialEntity.getCreateBy());
								}
								if(Utils.notEmpty(emkMaterialRequiredEntityList)){
									EmkMaterialRequiredEntity emkMaterialRequiredEntity = emkMaterialRequiredEntityList.get(0);
									emkMOutStorageEntity.setCreateBy(emkMaterialRequiredEntity.getCreateBy());
									emkMOutStorageEntity.setCreateName(emkMaterialRequiredEntity.getCreateName());
									emkMOutStorageEntity.setLler(emkMaterialRequiredEntity.getCreateName());
									emkMOutStorageEntity.setLlerId(emkMaterialRequiredEntity.getCreateBy());
									emkMOutStorageEntity.setAppler(emkMaterialRequiredEntity.getCreateName());
									emkMOutStorageEntity.setApplerId(emkMaterialRequiredEntity.getCreateBy());
								}
								systemService.save(emkMOutStorageEntity);

								//入库操作
								EmkStorageLogEntity storageLogEntity = new EmkStorageLogEntity();
								EmkProductEntity productEntity = null;
								List<EmkMInStorageDetailEntity> inStorageDetailEntityList = systemService.findHql("from EmkMInStorageDetailEntity where inStorageId=?",t.getId());
								EmkMInStorageDetailEntity emkMInStorageDetailEntity = null;
								for(EmkMInStorageDetailEntity inStorageDetailEntity : inStorageDetailEntityList){
									//保存数据到出库单
									emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
									MyBeanUtils.copyBeanNotNull2Bean(inStorageDetailEntity,emkMInStorageDetailEntity);
									emkMInStorageDetailEntity.setId(null);
									emkMInStorageDetailEntity.setInStorageId(emkMOutStorageEntity.getId());
									systemService.save(emkMInStorageDetailEntity);

									//保存库存数据和日记
									EmkStorageEntity storageEntity = systemService.findUniqueByProperty(EmkStorageEntity.class,"proNum",inStorageDetailEntity.getProNum());
									storageLogEntity = new EmkStorageLogEntity();
									storageLogEntity.setTotal(inStorageDetailEntity.getActualTotal());
									storageLogEntity.setProZnName(inStorageDetailEntity.getProZnName());
									storageLogEntity.setRkNo(t.getRkNo());
									if(storageEntity == null){
										storageEntity = new EmkStorageEntity();
										productEntity = systemService.findUniqueByProperty(EmkProductEntity.class,"proNum",inStorageDetailEntity.getProNum());
										if(productEntity != null){
											storageEntity.setProType(productEntity.getProType());
											storageEntity.setProTypeName(productEntity.getProTypeName());
											storageEntity.setRemark(productEntity.getType());
										}
										storageEntity.setProZnName(inStorageDetailEntity.getProZnName());
										storageEntity.setProNum(inStorageDetailEntity.getProNum());
										storageEntity.setTotal(inStorageDetailEntity.getActualTotal());
										storageEntity.setUnit(inStorageDetailEntity.getUnit());
										storageEntity.setBrand(inStorageDetailEntity.getBrand());
										storageEntity.setProType(inStorageDetailEntity.getType());
										systemService.save(storageEntity);
										storageLogEntity.setPreTotal("0");
										storageLogEntity.setNextTotal(inStorageDetailEntity.getActualTotal().toString());
									}else{
										storageLogEntity.setPreTotal(storageEntity.getTotal());
										double total = Double.parseDouble(storageEntity.getTotal());
										total = total + Double.parseDouble(inStorageDetailEntity.getActualTotal().toString());
										storageLogEntity.setNextTotal(String.valueOf(total));
										systemService.executeSql("update emk_storage set total = (total+?) where pro_num=?",inStorageDetailEntity.getActualTotal(),inStorageDetailEntity.getProNum());
									}
									systemService.save(storageLogEntity);
								}

								taskService.complete(task1.getId(), variables);
								b.setStatus(2);
								approvalDetail.setBpmName("操作员入库");
								t.setState("2");
								approvalDetail.setApproveStatus(0);

								/*if(Utils.notEmpty(emkMaterialEntity)) {
									task = taskService.createTaskQuery().taskAssignee(emkMaterialEntity.getId()).list();
									variables.put("optUser", emkMaterialEntity.getId());
									task1 = task.get(task.size() - 1);
									taskService.complete(task1.getId(), variables);

									EmkApprovalEntity d = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkMaterialEntity.getId());
									emkMaterialEntity.setState("58");
									d.setStatus(58);

								}*/
								if(Utils.notEmpty(emkMaterialRequiredEntityList)) {
									EmkMaterialRequiredEntity emkMaterialRequiredEntity = emkMaterialRequiredEntityList.get(0);
									task = taskService.createTaskQuery().taskAssignee(emkMaterialRequiredEntity.getId()).list();
									variables.put("inputUser", emkMaterialRequiredEntity.getId());
									task1 = task.get(task.size() - 1);
									if (task1.getTaskDefinitionKey().equals("cgyshTask")) {
										taskService.complete(task1.getId(), variables);
										EmkApprovalEntity d = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkMaterialRequiredEntity.getId());
										emkMaterialRequiredEntity.setState("58");
										d.setStatus(58);
									}
								}
								bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getRkerId());
								saveSmsAndEmailForOne("操作员入库","您有【"+user.getRealName()+"】操作员入库通过的入库申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
							}
						//}
						systemService.save(approvalDetail);

					}else {
						ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("instorage", "emkMInStorage", variables);
						task = taskService.createTaskQuery().taskAssignee(id).list();
						Task task1 = task.get(task.size() - 1);
						taskService.complete(task1.getId(), variables);

						t.setState("1");
						b.setStatus(1);
						b.setBpmStatus("0");
						b.setNextBpmSher(t.getCaigouerName());
						b.setNextBpmSherId(t.getCaigouer());
						b.setProcessName("入库申请单【仓管员】");

						bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getCaigouer());
						saveSmsAndEmailForOne("入库申请单","您有【"+user.getRealName()+"】提交的入库申请单，单号："+b.getWorkNum()+"，请及时审核。",bpmUser,user.getUserName());
					}
					systemService.saveOrUpdate(t);
					systemService.saveOrUpdate(b);
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}
		catch (Exception e) {
			e.printStackTrace();
			message = "入库申请单提交失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params="goWork")
	public ModelAndView goWork(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(emkMInStorageEntity.getId())) {
			emkMInStorageEntity = emkMInStorageService.getEntity(EmkMInStorageEntity.class, emkMInStorageEntity.getId());
			req.setAttribute("emkInStoragePage", emkMInStorageEntity);
		}
		return new ModelAndView("com/emk/bound/minstorage/emkMInStorage-work");
	}

	@RequestMapping(params="goTime")
	public ModelAndView goTime(EmkMInStorageEntity emkMInStorageEntity, HttpServletRequest req, DataGrid dataGrid) {
		EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkMInStorageEntity.getId());
		if(Utils.notEmpty(approvalEntity)){
			List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
			req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
			req.setAttribute("approvalEntity", approvalEntity);
			req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
		}

		return new ModelAndView("com/emk/bound/minstorage/time");
	}
}
