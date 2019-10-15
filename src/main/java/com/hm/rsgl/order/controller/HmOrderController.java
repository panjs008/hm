package com.hm.rsgl.order.controller;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.rsgl.order.entity.HmOrderEntity;
import com.hm.rsgl.order.service.HmOrderServiceI;

import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.rsgl.staff.entity.HmStaffEntityB;
import com.hm.rsgl.work.entity.HmWorkEntity;
import com.hm.rsgl.workprice.entity.HmWorkPriceEntity;
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
 * @Description: 工单表
 * @author onlineGenerator
 * @date 2019-06-29 22:44:31
 * @version V1.0   
 *
 */
@Api(value="HmOrder",description="工单表",tags="hmOrderController")
@Controller
@RequestMapping("/hmOrderController")
public class HmOrderController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmOrderController.class);

	@Autowired
	private HmOrderServiceI hmOrderService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 工单表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/order/hmOrderList");
	}
	@RequestMapping(params = "workTimeList")
	public ModelAndView workTimeList(HttpServletRequest request) {
		Map<String,String> p = ParameterUtil.getParamMaps(request.getParameterMap());
		List<HmWorkPriceEntity> workPriceEntityList = systemService.findHql("from HmWorkPriceEntity where orderId=?",p.get("orderId"));
		request.setAttribute("workPriceEntityList",workPriceEntityList);
		return new ModelAndView("com/hm/rsgl/order/workTimeList");
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
	public void datagrid(HmOrderEntity hmOrder,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmOrderEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmOrder, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmOrderService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除工单表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmOrderEntity hmOrder, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmOrder = systemService.getEntity(HmOrderEntity.class, hmOrder.getId());
		message = "工单表删除成功";
		try{
			hmOrderService.delete(hmOrder);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工单表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工单表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单表删除成功";
		try{
			for(String id:ids.split(",")){
				HmOrderEntity hmOrder = systemService.getEntity(HmOrderEntity.class, 
				id
				);
				systemService.executeSql("delete from hm_work_price where order_id=?",id);
				systemService.executeSql("delete from hm_work where order_id=?",id);

				hmOrderService.delete(hmOrder);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工单表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmOrderEntity hmOrder, HmWorkEntity hmWork,HmWorkPriceEntity hmWorkPriceEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单表添加成功";
		try{
			Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(order_no, 3)),0)+1 AS signed) orderNum from hm_order");
			hmOrder.setOrderNo("GD" + DateUtil.format(new Date(), "yyyyMMdd") + String.format("%03d", Integer.parseInt(orderNum.get("orderNum").toString())));
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
			Map<String, Object> xs = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='xs' and t2.typename='形式二系数'");

			hmOrderService.save(hmOrder);
			String dataRows = (String) map.get("dataRowsVal");
			DecimalFormat df = new DecimalFormat("#.00");

			int gdjdPeoples = 0 ;
			double hjMoney=0,kfpHj=0,zjhj=0,zjhj1=0,gdMoney=0;
			int gj = 0;
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				systemService.executeSql("delete from hm_work where order_id=?",hmOrder.getId());
				HmWorkEntity hmWorkEntity = null;

				for (int i = 0; i < rows; i++) {
					if(Utils.notEmpty(map.get("orderMxList["+i+"].total"))){
						hmWork.setXmmc(map.get("orderMxList["+i+"].a01a01a01"));
						hmWork.setGylc(map.get("orderMxList["+i+"].a01a01a02"));
						hmWork.setGgfl(map.get("orderMxList["+i+"].a01a01a03"));
						hmWork.setUnit(map.get("orderMxList["+i+"].a01a01a04"));
						hmWork.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].a01a01a05")));
						hmWork.setWorkDate(hmOrder.getKdDate());

						hmWork.setTotal(Double.parseDouble(map.get("orderMxList["+i+"].total")));
						hmWork.setStNit(map.get("orderMxList["+i+"].unit"));

						hmWork.setWeight(Double.parseDouble(map.get("orderMxList["+i+"].weight")));
						hmWork.setHjWeight(map.get("orderMxList["+i+"].hjWeight"));
						hmWork.setTotal(Double.parseDouble(map.get("orderMxList["+i+"].total")));
						hmWork.setHjMoney(map.get("orderMxList["+i+"].hjMoney"));

						hmWork.setId(null);
						if(Utils.notEmpty(hmWork.getGdNamesCode())){
							if(Utils.notEmpty(hmWork.getGdNamesCode())){
								String[] rs = hmWork.getGdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(rs.length);
								hmWork.setLocalPeoples(rs.length);
							}
							if(Utils.notEmpty(hmWork.getGdJdNamesCode())){
								String[] rs = hmWork.getGdJdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(hmWork.getPeoples()+rs.length);
								hmWork.setGdPeoples(rs.length);
								gdjdPeoples = hmWork.getGdPeoples();
							}
							if(Utils.notEmpty(hmWork.getOtherJdNamesCode())){
								String[] rs = hmWork.getOtherJdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(hmWork.getPeoples()+rs.length);
							}
							if(Utils.notEmpty(hmWork.getLsJdNamesCode())){
								String[] rs = hmWork.getLsJdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(hmWork.getPeoples()+rs.length);
							}
						}
						hmWorkEntity = new HmWorkEntity();
						MyBeanUtils.copyBeanNotNull2Bean(hmWork,hmWorkEntity);
						hmWorkEntity.setOrderId(hmOrder.getId());
						systemService.save(hmWorkEntity);
						if(Utils.notEmpty(hmWorkEntity.getHjMoney())){
							hjMoney += Double.parseDouble(hmWorkEntity.getHjMoney());
						}
					}
				}
			}
//			gdMoney += Double.parseDouble(df.format(Double.parseDouble(hmWorkEntity.getHjMoney())*hmWorkEntity.getGdPeoples()/hmWorkEntity.getPeoples()));

			dataRows = map.get("dataRowsValWork");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from hm_work_price where order_id=?",hmOrder.getId());
				HmWorkPriceEntity hmWorkPrice = null;
				int rows = Integer.parseInt(dataRows);

				for (int i = 0; i < rows; i++) {
					if (Utils.notEmpty(map.get("orderMxList["+i+"].workNo"))) {
						hmWorkPriceEntity = new HmWorkPriceEntity();
						hmWorkPriceEntity.setWorkNo(map.get("orderMxList["+i+"].workNo"));
						hmWorkPriceEntity.setRealName(map.get("orderMxList["+i+"].realName"));
						hmWorkPriceEntity.setSex(map.get("orderMxList["+i+"].sex"));
						hmWorkPriceEntity.setStartTime(map.get("orderMxList["+i+"].startTime"));
						hmWorkPriceEntity.setEndTime(map.get("orderMxList["+i+"].endTime"));
						hmWorkPriceEntity.setType(map.get("orderMxList["+i+"].type"));
						hmWorkPriceEntity.setYglx(map.get("orderMxList["+i+"].yglb"));
						hmWorkPriceEntity.setIsZz(map.get("orderMxList["+i+"].isZz"));
						hmWorkPriceEntity.setWorkType("1");
						hmWorkPriceEntity.setMainWorkName(hmWork.getMainWorkName());
						hmWorkPriceEntity.setMainWorkCode(hmWork.getMainWorkCode());
						hmWorkPriceEntity.setWorkGroupName(hmWork.getWorkGroupName());
						hmWorkPriceEntity.setWorkGroupCode(hmWork.getWorkGroupCode());
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zszcb"))){
							hmWorkPriceEntity.setZsZcb(Double.parseDouble(map.get("orderMxList["+i+"].zszcb")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zszcbPrice"))){
							hmWorkPriceEntity.setZsZcbPrice(Double.parseDouble(map.get("orderMxList["+i+"].zszcbPrice")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zsjiab"))) {
							hmWorkPriceEntity.setZsJiab(Double.parseDouble(map.get("orderMxList["+i+"].zsjiab")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zsjiabPirce"))) {
							hmWorkPriceEntity.setZsJiabPrice(map.get("orderMxList["+i+"].zsjiabPirce"));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zshj"))) {
							hmWorkPriceEntity.setZshj(Double.parseDouble(map.get("orderMxList["+i+"].zshj")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zcb"))){
							hmWorkPriceEntity.setZcb(Double.parseDouble(map.get("orderMxList["+i+"].zcb")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].jiab"))) {
							hmWorkPriceEntity.setJiab(Double.parseDouble(map.get("orderMxList["+i+"].jiab")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zjhj"))) {
							hmWorkPriceEntity.setZjhj(Double.parseDouble(map.get("orderMxList["+i+"].zjhj")));
						}

						hmWorkPriceEntity.setKdDate(hmOrder.getKdDate());
						hmWorkPriceEntity.setOrderId(hmOrder.getId());
						hmWorkPrice = new HmWorkPriceEntity();
						MyBeanUtils.copyBeanNotNull2Bean(hmWorkPriceEntity,hmWorkPrice);
						systemService.save(hmWorkPrice);
						if(Utils.notEmpty(hmWorkPriceEntity.getZjhj())){
							zjhj += hmWorkPriceEntity.getZjhj();
							if("0".equals(hmWorkPriceEntity.getType())){
								zjhj1 += hmWorkPriceEntity.getZjhj();
							}
						}

					}
				}
			}

			if(Utils.notEmpty(hjMoney) && Utils.notEmpty(zjhj)  && 0!=zjhj){
				double gjD = hjMoney/zjhj;
				//gj = (new Double(gjD)).intValue();
				List<HmWorkPriceEntity> workPriceEntityList = systemService.findHql("from HmWorkPriceEntity where orderId=?",hmOrder.getId());
				for(HmWorkPriceEntity t : workPriceEntityList){
					if(Utils.notEmpty(t.getZjhj())){
						if("0".equals(t.getType())){
							//t.setWages(Double.valueOf((new Double(t.getZjhj()*gjD)).intValue()));
							t.setWages(t.getZjhj()*gjD);
						}
						if("2".equals(t.getType())){
						/*t.setWages(Double.valueOf((new Double(t.getZjhj()*gjD*0.9)).intValue()));
						t.setKfWages(Double.valueOf((new Double(t.getZjhj()*gjD*0.1)).intValue()));*/
						/*t.setWages(t.getZjhj()*gjD*0.9);
						t.setKfWages(t.getZjhj()*gjD*0.1);*/
							if(Utils.notEmpty(xs)){
								t.setKfWages(Double.parseDouble(df.format(t.getZjhj()*gjD*Double.parseDouble(xs.get("remark").toString()))));
								t.setWages(Double.parseDouble(df.format(t.getZjhj()*gjD*(1-Double.parseDouble(xs.get("remark").toString())))));
								gdMoney += t.getKfWages();
							}

						}
					}

				}
			}
			if(Utils.notEmpty(zjhj1) && zjhj1 != 0){
				kfpHj = gdMoney/zjhj1;
			}
			if(Utils.notEmpty(kfpHj)){
				List<HmWorkPriceEntity> workPriceEntityList = systemService.findHql("from HmWorkPriceEntity where orderId=?",hmOrder.getId());
				for(HmWorkPriceEntity t : workPriceEntityList){
					if("0".equals(t.getType())){
						if(Utils.notEmpty(t.getZjhj())){
							t.setChoucheng(kfpHj*t.getZjhj());
						}
					}
					t.setGzHj(Double.parseDouble(df.format((Utils.notEmpty(t.getWages()) ? t.getWages():0)+(Utils.notEmpty(t.getChoucheng()) ? t.getChoucheng():0))));
				}
			}

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工单表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新工单表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmOrderEntity hmOrder, HmWorkEntity hmWork,HmWorkPriceEntity hmWorkPriceEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工单表更新成功";
		Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
		HmOrderEntity t0 = hmOrderService.get(HmOrderEntity.class, map.get("oid"));
		try {
			hmOrder.setId(null);
			MyBeanUtils.copyBeanNotNull2Bean(hmOrder, t0);
			hmOrderService.saveOrUpdate(t0);
			Map<String, Object> xs = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='xs' and t2.typename='形式二系数'");

			String dataRows = (String) map.get("dataRowsVal");
			DecimalFormat df = new DecimalFormat("#.000");

			int gdjdPeoples = 0 ;
			double hjMoney=0,kfpHj=0,zjhj=0,zjhj1=0,gdMoney=0;
			int gj = 0;
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				systemService.executeSql("delete from hm_work where order_id=?",t0.getId());
				HmWorkEntity hmWorkEntity = null;


				for (int i = 0; i < rows; i++) {
					if(Utils.notEmpty(map.get("orderMxList["+i+"].total"))){
						hmWork.setXmmc(map.get("orderMxList["+i+"].a01a01a01"));
						hmWork.setGylc(map.get("orderMxList["+i+"].a01a01a02"));
						hmWork.setGgfl(map.get("orderMxList["+i+"].a01a01a03"));
						hmWork.setUnit(map.get("orderMxList["+i+"].a01a01a04"));
						hmWork.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].a01a01a05")));
						hmWork.setWorkDate(t0.getKdDate());
						hmWork.setTotal(Double.parseDouble(map.get("orderMxList["+i+"].total")));
						hmWork.setStNit(map.get("orderMxList["+i+"].unit"));

						hmWork.setWeight(Double.parseDouble(map.get("orderMxList["+i+"].weight")));
						hmWork.setHjWeight(map.get("orderMxList["+i+"].hjWeight"));
						hmWork.setTotal(Double.parseDouble(map.get("orderMxList["+i+"].total")));
						hmWork.setHjMoney(map.get("orderMxList["+i+"].hjMoney"));

						hmWork.setId(null);
						if(Utils.notEmpty(hmWork.getGdNamesCode())){
							if(Utils.notEmpty(hmWork.getGdNamesCode())){
								String[] rs = hmWork.getGdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(rs.length);
								hmWork.setLocalPeoples(rs.length);
							}
							if(Utils.notEmpty(hmWork.getGdJdNamesCode())){
								String[] rs = hmWork.getGdJdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(hmWork.getPeoples()+rs.length);
								hmWork.setGdPeoples(rs.length);
								gdjdPeoples = hmWork.getGdPeoples();
							}if(Utils.notEmpty(hmWork.getOtherJdNamesCode())){
								String[] rs = hmWork.getOtherJdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(hmWork.getPeoples()+rs.length);
							}if(Utils.notEmpty(hmWork.getLsJdNamesCode())){
								String[] rs = hmWork.getLsJdNamesCode().replaceAll(",,",",").split(",");
								hmWork.setPeoples(hmWork.getPeoples()+rs.length);
							}
						}
						hmWorkEntity = new HmWorkEntity();
						MyBeanUtils.copyBeanNotNull2Bean(hmWork,hmWorkEntity);
						hmWorkEntity.setOrderId(t0.getId());
						systemService.save(hmWorkEntity);
						if(Utils.notEmpty(hmWorkEntity.getHjMoney())){
							hjMoney += Double.parseDouble(hmWorkEntity.getHjMoney());
						}
					}
				}
			}
//			gdMoney += Double.parseDouble(df.format(Double.parseDouble(hmWorkEntity.getHjMoney())*hmWorkEntity.getGdPeoples()/hmWorkEntity.getPeoples()));

			dataRows = map.get("dataRowsValWork");
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from hm_work_price where order_id=?",t0.getId());
				HmWorkPriceEntity hmWorkPrice = null;
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					if (Utils.notEmpty(map.get("orderMxList["+i+"].workNo"))) {
						hmWorkPriceEntity = new HmWorkPriceEntity();
						hmWorkPriceEntity.setWorkNo(map.get("orderMxList["+i+"].workNo"));
						hmWorkPriceEntity.setRealName(map.get("orderMxList["+i+"].realName"));
						hmWorkPriceEntity.setSex(map.get("orderMxList["+i+"].sex"));
						hmWorkPriceEntity.setStartTime(map.get("orderMxList["+i+"].startTime"));
						hmWorkPriceEntity.setEndTime(map.get("orderMxList["+i+"].endTime"));
						hmWorkPriceEntity.setType(map.get("orderMxList["+i+"].type"));
						hmWorkPriceEntity.setYglx(map.get("orderMxList["+i+"].yglb"));
						hmWorkPriceEntity.setIsZz(map.get("orderMxList["+i+"].isZz"));
						hmWorkPriceEntity.setWorkType("1");
						hmWorkPriceEntity.setMainWorkName(hmWork.getMainWorkName());
						hmWorkPriceEntity.setMainWorkCode(hmWork.getMainWorkCode());
						hmWorkPriceEntity.setWorkGroupName(hmWork.getWorkGroupName());
						hmWorkPriceEntity.setWorkGroupCode(hmWork.getWorkGroupCode());
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zszcb"))){
							hmWorkPriceEntity.setZsZcb(Double.parseDouble(map.get("orderMxList["+i+"].zszcb")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zszcbPrice"))){
							hmWorkPriceEntity.setZsZcbPrice(Double.parseDouble(map.get("orderMxList["+i+"].zszcbPrice")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zsjiab"))) {
							hmWorkPriceEntity.setZsJiab(Double.parseDouble(map.get("orderMxList["+i+"].zsjiab")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zsjiabPirce"))) {
							hmWorkPriceEntity.setZsJiabPrice(map.get("orderMxList["+i+"].zsjiabPirce"));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zshj"))) {
							hmWorkPriceEntity.setZshj(Double.parseDouble(map.get("orderMxList["+i+"].zshj")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zcb"))){
							hmWorkPriceEntity.setZcb(Double.parseDouble(map.get("orderMxList["+i+"].zcb")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].jiab"))) {
							hmWorkPriceEntity.setJiab(Double.parseDouble(map.get("orderMxList["+i+"].jiab")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zjhj"))) {
							hmWorkPriceEntity.setZjhj(Double.parseDouble(map.get("orderMxList["+i+"].zjhj")));
						}

						hmWorkPriceEntity.setKdDate(hmOrder.getKdDate());
						hmWorkPriceEntity.setOrderId(t0.getId());
						hmWorkPrice = new HmWorkPriceEntity();
						MyBeanUtils.copyBeanNotNull2Bean(hmWorkPriceEntity,hmWorkPrice);
						systemService.save(hmWorkPrice);
						if(Utils.notEmpty(hmWorkPriceEntity.getZjhj())){
							zjhj += hmWorkPriceEntity.getZjhj();
							if("0".equals(hmWorkPriceEntity.getType())){
								zjhj1 += hmWorkPriceEntity.getZjhj();
							}
						}

					}
				}
			}

			if(Utils.notEmpty(hjMoney) && Utils.notEmpty(zjhj)  && 0!=zjhj){
				double gjD = hjMoney/zjhj;
				//gj = (new Double(gjD)).intValue();
				List<HmWorkPriceEntity> workPriceEntityList = systemService.findHql("from HmWorkPriceEntity where orderId=?",t0.getId());
				for(HmWorkPriceEntity t : workPriceEntityList){
					if(Utils.notEmpty(t.getZjhj())){
						if("0".equals(t.getType())){
							//t.setWages(Double.valueOf((new Double(t.getZjhj()*gjD)).intValue()));
							t.setWages(Double.parseDouble(df.format(t.getZjhj()*gjD)));
						}
						if("2".equals(t.getType())){
						/*t.setWages(Double.valueOf((new Double(t.getZjhj()*gjD*0.9)).intValue()));
						t.setKfWages(Double.valueOf((new Double(t.getZjhj()*gjD*0.1)).intValue()));*/
							/*t.setWages(Double.parseDouble(df.format(t.getZjhj()*gjD*0.9)));
							t.setKfWages(Double.parseDouble(df.format(t.getZjhj()*gjD*0.1)));*/

							if(Utils.notEmpty(xs)){
								t.setKfWages(Double.parseDouble(df.format(t.getZjhj()*gjD*Double.parseDouble(xs.get("remark").toString()))));
								t.setWages(Double.parseDouble(df.format(t.getZjhj()*gjD*(1-Double.parseDouble(xs.get("remark").toString())))));
								gdMoney += t.getKfWages();
							}
						/*t.setWages(t.getZjhj()*gjD*0.9);
						t.setKfWages(t.getZjhj()*gjD*0.1);
							gdMoney += t.getKfWages();*/
						}
					}

				}
			}
			if(Utils.notEmpty(zjhj1) && zjhj1 != 0){
				kfpHj = gdMoney/zjhj1;
			}
			if(Utils.notEmpty(kfpHj)){
				List<HmWorkPriceEntity> workPriceEntityList = systemService.findHql("from HmWorkPriceEntity where orderId=?",t0.getId());
				for(HmWorkPriceEntity t : workPriceEntityList){
					if("0".equals(t.getType())){
						if(Utils.notEmpty(t.getZjhj())){
							t.setChoucheng(Double.parseDouble(df.format(kfpHj*t.getZjhj())));
						}
					}
					t.setGzHj(Double.parseDouble(df.format((Utils.notEmpty(t.getWages()) ? t.getWages():0)+(Utils.notEmpty(t.getChoucheng()) ? t.getChoucheng():0))));
				}
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工单表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 工单表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmOrderEntity hmOrder, HttpServletRequest req) {
		req.getSession().setAttribute("gdNamesCodeS","");
		req.getSession().setAttribute("otherJdNamesCodeS","");
		req.getSession().setAttribute("gdJdNamesCodeS","");
		req.getSession().setAttribute("lsJdNamesCodeS","");
		req.setAttribute("kdDate", DateUtil.format(new Date(), "yyyy-MM-dd"));

		if (StringUtil.isNotEmpty(hmOrder.getId())) {
			hmOrder = hmOrderService.getEntity(HmOrderEntity.class, hmOrder.getId());
			req.setAttribute("hmOrderPage", hmOrder);
		}
		return new ModelAndView("com/hm/rsgl/order/hmOrder-add");
	}
	/**
	 * 工单表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmOrderEntity hmOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmOrder.getId())) {
			hmOrder = hmOrderService.getEntity(HmOrderEntity.class, hmOrder.getId());
			req.setAttribute("hmOrderPage", hmOrder);

			req.getSession().setAttribute("gdNamesCodeS",hmOrder.getGdNamesCode());
			req.getSession().setAttribute("otherJdNamesCodeS",hmOrder.getOtherJdNamesCode());
			req.getSession().setAttribute("gdJdNamesCodeS",hmOrder.getGdJdNamesCode());
			req.getSession().setAttribute("lsJdNamesCodeS",hmOrder.getLsJdNamesCode());
		}
		return new ModelAndView("com/hm/rsgl/order/hmOrder-update");
	}
	/**
	 * 查看
	 *
	 * @return
	 */
	@RequestMapping(params = "goLook")
	public ModelAndView goLook(HmOrderEntity hmOrder, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmOrder.getId())) {
			hmOrder = hmOrderService.getEntity(HmOrderEntity.class, hmOrder.getId());
			req.setAttribute("hmOrderPage", hmOrder);
		}
		return new ModelAndView("com/hm/rsgl/order/hmOrder-look");
	}
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmOrderController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmOrderEntity hmOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmOrderEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmOrder, request.getParameterMap());
		List<HmOrderEntity> hmOrders = this.hmOrderService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"工单表");
		modelMap.put(NormalExcelConstants.CLASS,HmOrderEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工单表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmOrders);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmOrderEntity hmOrder,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"工单表");
    	modelMap.put(NormalExcelConstants.CLASS,HmOrderEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工单表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmOrderEntity> listHmOrderEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmOrderEntity.class,params);
				for (HmOrderEntity hmOrder : listHmOrderEntitys) {
					hmOrderService.save(hmOrder);
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
	@ApiOperation(value="工单表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmOrderEntity>> list() {
		List<HmOrderEntity> listHmOrders=hmOrderService.getList(HmOrderEntity.class);
		return Result.success(listHmOrders);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取工单表信息",notes="根据ID获取工单表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmOrderEntity task = hmOrderService.get(HmOrderEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取工单表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建工单表")
	public ResponseMessage<?> create(@ApiParam(name="工单表对象")@RequestBody HmOrderEntity hmOrder, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmOrderEntity>> failures = validator.validate(hmOrder);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmOrderService.save(hmOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工单表信息保存失败");
		}
		return Result.success(hmOrder);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新工单表",notes="更新工单表")
	public ResponseMessage<?> update(@ApiParam(name="工单表对象")@RequestBody HmOrderEntity hmOrder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmOrderEntity>> failures = validator.validate(hmOrder);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmOrderService.saveOrUpdate(hmOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新工单表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新工单表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除工单表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmOrderService.deleteEntityById(HmOrderEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工单表删除失败");
		}

		return Result.success();
	}
}
