package com.hm.kq.checkrecord.controller;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.emk.util.WebFileUtils;
import com.hm.kq.checkpay.entity.HmCheckPayEntity;
import com.hm.kq.checkrecord.entity.HmCheckRecordEntity;
import com.hm.kq.checkrecord.service.HmCheckRecordServiceI;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.kq.checkstaff.entity.HmCheckStaffEntity;
import com.hm.kq.checkunusual.entity.HmCheckUnusualEntity;
import com.hm.kq.workclassdetail.entity.HmWorkClassDetailEntity;
import com.hm.rsgl.basesalary.entity.HmBaseSalaryEntity;
import com.hm.rsgl.staff.entity.HmStaffEntity;
import com.hm.rsgl.workprice.entity.HmWorkPriceEntity;
import com.jeecg.demo.entity.TSDocument;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jeecgframework.core.util.*;
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
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

import java.io.OutputStream;

import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
 * @Description: 考勤记录表
 * @author onlineGenerator
 * @date 2019-08-07 23:16:32
 * @version V1.0   
 *
 */
@Api(value="HmCheckRecord",description="考勤记录表",tags="hmCheckRecordController")
@Controller
@RequestMapping("/hmCheckRecordController")
public class HmCheckRecordController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmCheckRecordController.class);

	@Autowired
	private HmCheckRecordServiceI hmCheckRecordService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 考勤记录表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/kq/checkrecord/hmCheckRecordList");
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
	public void datagrid(HmCheckRecordEntity hmCheckRecord,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckRecordEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckRecord, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmCheckRecordService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除考勤记录表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmCheckRecordEntity hmCheckRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmCheckRecord = systemService.getEntity(HmCheckRecordEntity.class, hmCheckRecord.getId());
		message = "考勤记录表删除成功";
		try{
			hmCheckRecordService.delete(hmCheckRecord);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤记录表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除考勤记录表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤记录表删除成功";
		try{
			for(String id:ids.split(",")){
				HmCheckRecordEntity hmCheckRecord = systemService.getEntity(HmCheckRecordEntity.class, 
				id
				);
				hmCheckRecordService.delete(hmCheckRecord);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤记录表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加考勤记录表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmCheckRecordEntity hmCheckRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤扣费表添加成功";
		try{
			Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
			String month = "";
			if(Utils.notEmpty(param.get("month"))){
				month = param.get("month");
			}else{
				month = DateUtil.format(new Date(),"yyyy-MM");
			}
			List<Map<String, Object>>  workClass = null;
			List<Map>  qkworkClass = null;//缺卡数据
			List<HmCheckRecordEntity>  checkRecordIdArr = new ArrayList<>();//异常打卡数据

			Calendar cal1 = null, cal2 = null;
			int diffM = 0,workClassSize = 0 ,flag = 0,day = 0,monthDays=0;

			String stTime = "",edTime = "",sql = "",weekEndDays="";
			HmCheckPayEntity hmCheckPayEntity = null;//扣费类
			HmCheckUnusualEntity hmCheckUnusualEntity = null;//异常数据类
			systemService.executeSql("delete from hm_check_pay where month=?",month);
			systemService.executeSql("delete from hm_check_unusual where month=?",month);

			//systemService.executeSql("update hm_check_record set state =0 ");

			DecimalFormat df = new DecimalFormat("#.00");
			List<HmCheckRecordEntity> checkRecordEntityList = null;
			List<HmCheckStaffEntity> staffEntityList = systemService.findHql(" from HmCheckStaffEntity ");

			Map<String, Object> kqsz = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typecode='fz'");
			Map<String, Object> kqsz0 = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typecode='分钟1'");
			Map<String, Object> kqsz1 = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typecode='分钟2'");
			Map<String, Object> kqsz2 = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typename='扣费一'");
			Map<String, Object> kqsz3 = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typename='扣费二'");
			Map<String, Object> kqsz4 = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typename='上午缺卡扣费'");
			Map<String, Object> kqsz5 = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='kf' and t2.typename='其他缺卡扣费'");
			monthDays = DateUtil.getDays(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)));

			//按姓名+日期分组保存考勤记录
			checkRecordEntityList = systemService.findHql("from HmCheckRecordEntity where month=? group by realName,clockTime",month);
			Map<String,List<HmCheckRecordEntity>> checkRecordMap = new HashMap<>();
			for(HmCheckRecordEntity hmCheckRecordEntity : checkRecordEntityList){
				if(checkRecordMap.containsKey(hmCheckRecordEntity.getRealName()+hmCheckRecordEntity.getClockTime().substring(0,10))){
					checkRecordMap.get(hmCheckRecordEntity.getRealName()+hmCheckRecordEntity.getClockTime().substring(0,10)).add(hmCheckRecordEntity);
				}else{
					List<HmCheckRecordEntity> tmpList = new ArrayList<HmCheckRecordEntity>();
					tmpList.add(hmCheckRecordEntity);
					checkRecordMap.put((hmCheckRecordEntity.getRealName()+hmCheckRecordEntity.getClockTime().substring(0,10)),tmpList);
				}
			}

			//按工号分组保存事假记录
			List<Map<String, Object>>  sjList = systemService.findForJdbc("select ifnull(GROUP_CONCAT(left(h.BEGIN_TIME,10)),0) leDate,h.work_no from hm_leval h where h.TIME_TYPE=0 group by h.work_no");
			Map<String,Map<String, Object>> sjMap = new HashMap<>();
			for(Map p : sjList){
				sjMap.put(p.get("work_no").toString(),p);
			}

			for(HmCheckStaffEntity hmCheckStaffEntity : staffEntityList){
				workClassSize = hmCheckStaffEntity.getHmWorkClassEntity().getWorkClassDetailEntityList().size();
				for(int i = 1 ; i <=monthDays ; i++){
					if(Utils.notEmpty(hmCheckStaffEntity.getHoliday())){
						if(hmCheckStaffEntity.getHoliday().equals("zm")){
							weekEndDays = DateUtil.getWeekEndDays(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),0);
						}else if(hmCheckStaffEntity.getHoliday().equals("zr")){
							weekEndDays = DateUtil.getWeekEndDays(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),2);
						}else if(hmCheckStaffEntity.getHoliday().equals("zl")){
							weekEndDays = DateUtil.getWeekEndDays(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),1);
						}else{
							weekEndDays = hmCheckStaffEntity.getSleepDays();
						}
						if(!weekEndDays.contains(month+"-"+String.format("%02d", i))){
							checkRecordEntityList = checkRecordMap.get(hmCheckStaffEntity.getRealName()+month+"-"+String.format("%02d", i));
							flag = 0;
							List<HmWorkClassDetailEntity> workClassDetailEntities = hmCheckStaffEntity.getHmWorkClassEntity().getWorkClassDetailEntityList();
							workClass = new ArrayList<>();
							qkworkClass = new ArrayList<>();

							for(HmWorkClassDetailEntity workClassDetailEntity : workClassDetailEntities){
								Map m = new HashMap();
								MyBeanUtils.copyBean2Map(m,workClassDetailEntity);
								m.put("state",0);
								workClass.add(m);
							}
							/*for(Map m : workClass){
								m.put("state",0);
								workClass.set(flag,m);
								flag++;
							}*/
							if(Utils.notEmpty(checkRecordEntityList)){
								for(HmCheckRecordEntity checkRecordEntity : checkRecordEntityList){
									flag = 0;
									for(Map m : workClass){
										if("0".equals(m.get("state").toString())){
											hmCheckPayEntity = new HmCheckPayEntity();
											stTime = checkRecordEntity.getClockTime().substring(0,10)+" "+m.get("stTime").toString();
											edTime = checkRecordEntity.getClockTime().substring(0,10)+" "+m.get("edTime").toString();

											Map<String, Object>  pdDate = systemService.findOneForJdbc("select (? > ? and ? < ?) result",checkRecordEntity.getClockTime(),stTime,checkRecordEntity.getClockTime(),edTime);
											String result = "";
											for (Map.Entry<String, Object> entry : pdDate.entrySet()) {
												result = entry.getValue().toString();
											}
											if(Utils.notEmpty(pdDate)){
												if("1".equals(result)){
													//计算迟到和早退时间和金额
													if("0".equals(m.get("workType"))){
														cal1 = DateUtils.parseCalendar(checkRecordEntity.getClockTime().substring(0,10)+" "+m.get("workSTime").toString(),"yyyy-MM-dd HH:mm");
														cal2 = DateUtils.parseCalendar(checkRecordEntity.getClockTime(),"yyyy-MM-dd HH:mm");
														hmCheckPayEntity.setPayType("0");

														diffM  = DateUtils.dateDiff('m',cal2,cal1);
													}else if("1".equals(m.get("workType"))){
														cal2 = DateUtils.parseCalendar(checkRecordEntity.getClockTime().substring(0,10)+" "+m.get("workSTime").toString(),"yyyy-MM-dd HH:mm");
														cal1 = DateUtils.parseCalendar(checkRecordEntity.getClockTime(),"yyyy-MM-dd HH:mm");
														hmCheckPayEntity.setPayType("1");
														//判断计件人员加班早退不计算
														/*Map levalMap = systemService.findOneForJdbc("select * from hm_leval hl where " +
																			"	hl.work_no=? and hl.BEGIN_TIME<=left(?,13) and hl.END_TIME>=left(?,13)  and hl.TIME_TYPE=1",hmCheckStaffEntity.getWorkNo(),checkRecordEntity.getClockTime(),checkRecordEntity.getClockTime());
														if(Utils.notEmpty(levalMap)){
															diffM = 0;
														}*/
														diffM  = DateUtils.dateDiff('m',cal2,cal1);
													}else if("2".equals(m.get("workType"))){
														diffM = 0;
													}
													if(diffM > 0 ){
														MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffEntity,hmCheckPayEntity);
														hmCheckPayEntity.setClockTime(checkRecordEntity.getClockTime());
														hmCheckPayEntity.setLongTime(String.valueOf(diffM));
														if(Double.parseDouble(kqsz0.get("remark").toString())<diffM){
															hmCheckPayEntity.setPayMoney(Double.parseDouble(kqsz0.get("remark").toString())*(Utils.notEmpty(kqsz2) ? Double.parseDouble(kqsz2.get("remark").toString()):0));
															hmCheckPayEntity.setPayMoney(hmCheckPayEntity.getPayMoney()+(diffM-Double.parseDouble(kqsz0.get("remark").toString()))*(Utils.notEmpty(kqsz3) ? Double.parseDouble(kqsz3.get("remark").toString()):0));
															hmCheckPayEntity.setPrecent(kqsz0.get("remark").toString()+"*"+kqsz2.get("remark").toString()+"+"+(diffM-Double.parseDouble(kqsz0.get("remark").toString()))+"*"+kqsz3.get("remark").toString());
														}else{
															hmCheckPayEntity.setPrecent(diffM+"*"+kqsz2.get("remark").toString());
															hmCheckPayEntity.setPayMoney(diffM*(Utils.notEmpty(kqsz2) ? Double.parseDouble(kqsz2.get("remark").toString()):0));
														}
														hmCheckPayEntity.setCheckTime(checkRecordEntity.getClockTime().substring(0,10)+" "+m.get("workSTime")+"到"+m.get("workETime"));
														hmCheckPayEntity.setMonth(checkRecordEntity.getMonth());
														systemService.save(hmCheckPayEntity);
													}
													m.put("state",1);
													workClass.set(flag,m);
													checkRecordIdArr.add(checkRecordEntity);
												}
											}
										}
										flag++;
									}
								}
							}

							for(Map m : workClass){
								if("0".equals(m.get("state").toString())){
									qkworkClass.add(m);
								}
							}
							//旷工班次缺卡数据
							if(Utils.notEmpty(qkworkClass)){
								if(workClassSize == qkworkClass.size()){
									hmCheckPayEntity = new HmCheckPayEntity();
									MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffEntity,hmCheckPayEntity);
									//h1.check_time not in (select left(h.BEGIN_TIME,10) from hm_leval h where h.work_no=hcp.work_no and h.TIME_TYPE=0)
									Map levalMap = sjMap.get(hmCheckPayEntity.getWorkNo());
									//MyBeanUtils.copyMap2Bean(hmCheckPayEntity,staff);
									if(Utils.isEmpty(levalMap) || !levalMap.get("leDate").toString().contains(month+"-"+String.format("%02d", i))){
										hmCheckPayEntity.setId(null);
										hmCheckPayEntity.setClockTime("");
										hmCheckPayEntity.setCheckTime(month+"-"+String.format("%02d", i));
										hmCheckPayEntity.setMonth(month);
										hmCheckPayEntity.setPayType("2");
										systemService.save(hmCheckPayEntity);

										hmCheckUnusualEntity = new HmCheckUnusualEntity();
										MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffEntity,hmCheckUnusualEntity);
										hmCheckUnusualEntity.setId(null);
										hmCheckUnusualEntity.setCheckTime(hmCheckPayEntity.getCheckTime());
										hmCheckUnusualEntity.setMonth(month);
										hmCheckUnusualEntity.setIsClock("2");
										systemService.save(hmCheckUnusualEntity);
									}
								}else{
									for(Map m : qkworkClass){
										hmCheckUnusualEntity = new HmCheckUnusualEntity();
										MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffEntity,hmCheckUnusualEntity);
										hmCheckUnusualEntity.setId(null);
										hmCheckUnusualEntity.setCheckTime(month+"-"+String.format("%02d", i)+" "+m.get("stTime").toString()+"到"+m.get("edTime"));
										hmCheckUnusualEntity.setWorkTime(month+"-"+String.format("%02d", i)+" "+m.get("workSTime").toString()+"到"+m.get("workETime"));
										hmCheckUnusualEntity.setMonth(month);
										hmCheckUnusualEntity.setIsClock("1");
										systemService.save(hmCheckUnusualEntity);

										hmCheckPayEntity = new HmCheckPayEntity();
										MyBeanUtils.copyBeanNotNull2Bean(hmCheckStaffEntity,hmCheckPayEntity);
										hmCheckPayEntity.setId(null);
										hmCheckPayEntity.setClockTime("");
										cal1 = DateUtils.parseCalendar(month+"-01 "+m.get("workSTime").toString(),"yyyy-MM-dd HH:mm");
										cal2 = DateUtils.parseCalendar(month+"-01 10:10","yyyy-MM-dd HH:mm");

										diffM  = DateUtils.dateDiff('m',cal2,cal1);
										if(diffM>0){
											hmCheckPayEntity.setPayMoney(Double.parseDouble(kqsz4.get("remark").toString()));
										}else{
											hmCheckPayEntity.setPayMoney(Double.parseDouble(kqsz5.get("remark").toString()));
										}
										hmCheckPayEntity.setCheckTime(month+"-"+String.format("%02d", i)+" "+m.get("workSTime").toString()+"到"+m.get("workETime"));
										hmCheckPayEntity.setMonth(month);
										hmCheckPayEntity.setPayType("3");
										systemService.save(hmCheckPayEntity);
									}
								}
							}
						}
					}

				}
			}

			//异常打卡数据
			if(Utils.notEmpty(checkRecordIdArr)){
				for(HmCheckRecordEntity checkRecordEntity : checkRecordIdArr){
					hmCheckUnusualEntity = new HmCheckUnusualEntity();
					hmCheckUnusualEntity.setRealName(checkRecordEntity.getRealName());
					hmCheckUnusualEntity.setDeptName(checkRecordEntity.getDeptName());
					hmCheckUnusualEntity.setCheckTime(checkRecordEntity.getCheckTime());
					hmCheckUnusualEntity.setMonth(month);
					hmCheckUnusualEntity.setClockTime(checkRecordEntity.getClockTime());
					hmCheckUnusualEntity.setIsClock("0");
					systemService.save(hmCheckUnusualEntity);
				}
			}

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "考勤扣费表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新考勤记录表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmCheckRecordEntity hmCheckRecord, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "考勤记录表更新成功";
		HmCheckRecordEntity t = hmCheckRecordService.get(HmCheckRecordEntity.class, hmCheckRecord.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmCheckRecord, t);
			hmCheckRecordService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "考勤记录表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 考勤记录表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmCheckRecordEntity hmCheckRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmCheckRecord.getId())) {
			hmCheckRecord = hmCheckRecordService.getEntity(HmCheckRecordEntity.class, hmCheckRecord.getId());
			req.setAttribute("hmCheckRecordPage", hmCheckRecord);
		}
		return new ModelAndView("com/hm/kq/checkrecord/hmCheckRecord-add");
	}
	/**
	 * 考勤记录表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmCheckRecordEntity hmCheckRecord, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(hmCheckRecord.getId())) {
			hmCheckRecord = hmCheckRecordService.getEntity(HmCheckRecordEntity.class, hmCheckRecord.getId());
			req.setAttribute("hmCheckRecordPage", hmCheckRecord);
		}
		return new ModelAndView("com/hm/kq/checkrecord/hmCheckRecord-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmCheckRecordController");
		return new ModelAndView("common/upload/pub_excel_upload2");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmCheckRecordEntity hmCheckRecord,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmCheckRecordEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmCheckRecord, request.getParameterMap());
		List<HmCheckRecordEntity> hmCheckRecords = this.hmCheckRecordService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"考勤记录表");
		modelMap.put(NormalExcelConstants.CLASS,HmCheckRecordEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤记录表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmCheckRecords);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmCheckRecordEntity hmCheckRecord,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"考勤记录表");
    	modelMap.put(NormalExcelConstants.CLASS,HmCheckRecordEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("考勤记录表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public Object importExcel(HttpServletRequest request, HttpServletResponse response,String filesavename) {
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
			try {
				String savepath = request.getRealPath("/")+"imp/kq/";
				String savename = WebFileUtils.saveFile(file, savepath);
				newfile =  new File(savepath+savename);
				wb = WebFileUtils.createHSSFWorkBook(newfile);
				if (wb==null) {
					logger.error("传入文件无法识别，请检查文件类型！！");
					sussess = "false";
				}
				HSSFSheet sheet = wb.getSheetAt(0);
				DecimalFormat df = new DecimalFormat("0");
				HSSFCell cell = null;
				int counter = 0;
				HSSFRow row = null;
				logger.info("执行导入："+newfile.getName());
				List<String> itemValue = null;
				HmCheckRecordEntity hmCheckRecordEntity = null;
				Map orderNum = null;
				TSDepart tsDepart = null;
				for (int i = 3; i <= sheet.getLastRowNum(); i++) {
					row = sheet.getRow(i);
					itemValue = new ArrayList<String>();
					for(int z = 0; z <= 7 ; z++){
						cell = row.getCell(z);
						if(null == cell){
							itemValue.add(cellValue);
							continue;
						}
						switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								cellValue =cell.getRichStringCellValue().getString().trim();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
									SimpleDateFormat sdf = null;
									if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
											.getBuiltinFormat("h:mm")) {
										sdf = new SimpleDateFormat("HH:mm");
									} else {// 日期
										sdf = new SimpleDateFormat("yyyy-MM-dd");
									}
									Date date = cell.getDateCellValue();
									cellValue = sdf.format(date);
								} else if (cell.getCellStyle().getDataFormat() == 58) {
									// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									double value = cell.getNumericCellValue();
									Date date = org.apache.poi.ss.usermodel.DateUtil
											.getJavaDate(value);
									cellValue = sdf.format(date);
								} else {
									double value = cell.getNumericCellValue();
									CellStyle style = cell.getCellStyle();
									DecimalFormat format = new DecimalFormat();
									String temp = style.getDataFormatString();
									// 单元格设置成常规
									if (temp.equals("General")) {
										format.applyPattern("#");
									}
									cellValue = format.format(value);
								}
								//cellValue = df.format(cell.getNumericCellValue()).toString();
								break;

							default:
								cellValue = "";
						}
						itemValue.add(cellValue);
						cellValue = "";
					}
					hmCheckRecordEntity = new HmCheckRecordEntity();
					if(Utils.notEmpty(itemValue.get(0)) && Utils.notEmpty(itemValue.get(6)) && itemValue.get(6).length()>10){
						systemService.executeSql("delete from hm_check_record where REAL_NAME=? and left(CHECK_TIME,13)=?",itemValue.get(0),itemValue.get(6).substring(0,13));
						hmCheckRecordEntity.setRealName(itemValue.get(0));
						hmCheckRecordEntity.setDeptName(itemValue.get(1));
						hmCheckRecordEntity.setKdTime(itemValue.get(4));
						hmCheckRecordEntity.setCheckTime(itemValue.get(5));
						hmCheckRecordEntity.setClockTime(itemValue.get(6));
						hmCheckRecordEntity.setMonth(itemValue.get(6).substring(0,7));
						hmCheckRecordEntity.setClockResult(itemValue.get(7));
						systemService.saveOrUpdate(hmCheckRecordEntity);
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
	@ApiOperation(value="考勤记录表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmCheckRecordEntity>> list() {
		List<HmCheckRecordEntity> listHmCheckRecords=hmCheckRecordService.getList(HmCheckRecordEntity.class);
		return Result.success(listHmCheckRecords);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取考勤记录表信息",notes="根据ID获取考勤记录表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmCheckRecordEntity task = hmCheckRecordService.get(HmCheckRecordEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取考勤记录表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建考勤记录表")
	public ResponseMessage<?> create(@ApiParam(name="考勤记录表对象")@RequestBody HmCheckRecordEntity hmCheckRecord, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckRecordEntity>> failures = validator.validate(hmCheckRecord);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckRecordService.save(hmCheckRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤记录表信息保存失败");
		}
		return Result.success(hmCheckRecord);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新考勤记录表",notes="更新考勤记录表")
	public ResponseMessage<?> update(@ApiParam(name="考勤记录表对象")@RequestBody HmCheckRecordEntity hmCheckRecord) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmCheckRecordEntity>> failures = validator.validate(hmCheckRecord);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmCheckRecordService.saveOrUpdate(hmCheckRecord);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新考勤记录表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新考勤记录表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除考勤记录表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmCheckRecordService.deleteEntityById(HmCheckRecordEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("考勤记录表删除失败");
		}

		return Result.success();
	}
}
