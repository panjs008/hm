package com.hm.rsgl.work.controller;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.rsgl.staff.entity.HmStaffEntity;
import com.hm.rsgl.staff.entity.HmStaffEntityB;
import com.hm.rsgl.work.entity.HmWorkEntity;
import com.hm.rsgl.work.service.HmWorkServiceI;

import java.text.DecimalFormat;
import java.util.*;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hm.rsgl.workprice.entity.HmWorkPriceEntity;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
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
 * @Description: 工作表
 * @author onlineGenerator
 * @date 2019-06-23 23:24:09
 * @version V1.0   
 *
 */
@Api(value="HmWork",description="工作表",tags="hmWorkController")
@Controller
@RequestMapping("/hmWorkController")
public class HmWorkController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmWorkController.class);

	@Autowired
	private HmWorkServiceI hmWorkService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;


	/**
	 * 工作表列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "forOrder")
	public ModelAndView forOrder(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/work/forOrder");
	}

	/**
	 * 产能列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "forProduct")
	public ModelAndView forProduct(HttpServletRequest request) {
		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/rsgl/work/forProduct");
	}

	/**
	 * 每日产能列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "forProductDay")
	public ModelAndView forProductDay(HttpServletRequest request) {
		/*List<Map> cols = new ArrayList<>();
		Map data = null;
		for(int i = 1 ; i <=31 ; i++){
			data = new HashMap();
			data.put("name",i);
			data.put("code","d"+i);
			cols.add(data);
		}
		request.setAttribute("cols",cols);*/
		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		List<Map<String, Object>>  unitList = systemService.findForJdbc("select DISTINCT h.a01a01a04 unit from hm_project h where h.a01a01a04 in ('吨','千克','克','公斤','斤')",null);
		request.setAttribute("unitList",unitList);
		return new ModelAndView("com/hm/rsgl/work/forProductDay");
	}

	/**
	 * 产能列表查询
	 *
	 * @return
	 */
	@RequestMapping(params = "listProductByJdbc")
	public void listProductByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("workDate"))){
			month = param.get("workDate");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		String calUnit = "";
		if(Utils.notEmpty(param.get("calUnit"))){
			calUnit = param.get("calUnit");
		}else{
			calUnit = "吨";
		}
		/*sql +=" select left(t.work_date,7) workDate,t.main_work_name mainWorkName,t.main_work_code mainWorkCode,t.work_group_code workGroupCode,t.work_group_name workGroupName,TRUNCATE(SUM(case unit\n" +
				" when '吨' then weight*total \n" +
				" when '斤' then (0.0005*weight*total) \n" +
				" when '公斤' then (0.001*weight*total) \n" +
				" when '千克' then (0.001*weight*total) \n" +
				" when '克' then (0.000001*weight*total) \n" +
				" else 0 end),3) total,t.unit \n" +
				" from (select h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,h.total,h.weight,h.st_nit unit,h.work_date from hm_work h where h.order_id is null \n" +
				" UNION \n" +
				" select h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,h.total,h.weight,h.st_nit unit,h2.kd_date work_date from hm_work h \n" +
				" left join hm_order h2 on h2.id=h.order_id \n" +
				" where h.order_id is not null and h2.order_no is not null) t \n" +
				" where 1=1 \n";*/

		sql += " select * from (select left(t.work_date,7) workDate,t.main_work_name mainWorkName,t.main_work_code mainWorkCode,t.work_group_code workGroupCode,t.work_group_name workGroupName,TRUNCATE(SUM(case unit\n" +
				" when '吨' then weight*total \n" +
				" when '斤' then (0.0005*weight*total) \n" +
				" when '公斤' then (0.001*weight*total) \n" +
				" when '千克' then (0.001*weight*total) \n" +
				" when '克' then (0.000001*weight*total) \n" +
				" else 0 end),3) total,'"+calUnit+"' unit \n" +
				" from (select h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,h.total,h.weight,h.st_nit unit,h.work_date from hm_work h where h.order_id is null and h.st_nit is not null\n" +
				" UNION \n" +
				" select h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,h.total,h.weight,h.st_nit unit,h2.kd_date work_date from hm_work h \n" +
				" left join hm_order h2 on h2.id=h.order_id \n" +
				" where h.order_id is not null and h2.order_no is not null and h.st_nit is not null) t \n" +
				" where 1=1 and left(t.work_date,7) = '"+month+"' \n";
		if(Utils.notEmpty(param.get("mainWorkName"))){
			sql += " and t.main_work_name like '%"+param.get("mainWorkName")+"%'";
		}
		if(Utils.notEmpty(param.get("workGroupName"))){
			sql += " and t.work_group_name like '%"+param.get("workGroupName")+"%'";
		}
		if(Utils.notEmpty(param.get("workDate_begin"))){
			sql += " and t.work_date >= '"+param.get("workDate_begin")+"'";
		}
		if(Utils.notEmpty(param.get("workDate_end"))){
			sql += " and t.work_date <= '"+param.get("workDate_end")+"'";
		}
		sql +=" group by t.main_work_name,t.work_group_name,left(t.work_date,7) \n" +
				" UNION \n" +
				" select left(IFNULL(h2.kd_date,t.work_date),7) workDate,t.main_work_name mainWorkName,t.main_work_code mainWorkCode,t.work_group_code workGroupCode,t.work_group_name workGroupName,\n" +
				" TRUNCATE(SUM(t.weight),3) total,t.unit from hm_work t\n" +
				" left join hm_order h2 on h2.id=t.order_id \n" +
				" where t.unit !=''and left(t.work_date,7) = '"+month+"' and t.unit not in ('吨','千克','克','公斤','斤') " ;
		if(Utils.notEmpty(param.get("mainWorkName"))){
			sql += " and t.main_work_name like '%"+param.get("mainWorkName")+"%'";
		}
		if(Utils.notEmpty(param.get("workGroupName"))){
			sql += " and t.work_group_name like '%"+param.get("workGroupName")+"%'";
		}
		if(Utils.notEmpty(param.get("workDate_begin"))){
			sql += " and t.work_date >= '"+param.get("workDate_begin")+"'";
		}
		if(Utils.notEmpty(param.get("workDate_end"))){
			sql += " and t.work_date <= '"+param.get("workDate_end")+"'";
		}
		sql +=	" group by t.main_work_name,t.work_group_name,left(IFNULL(h2.kd_date,t.work_date),7),t.unit) aa where 1=1\n" +
				" order by aa.mainWorkName asc,left(aa.workDate,7) desc";

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
	 * 产能列表规格分类查询
	 *
	 * @return
	 */
	@RequestMapping(params = "listProductByGgflJdbc")
	public void listProductByGgflJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("workDate"))){
			month = param.get("workDate");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		String calUnit = "";
		if(Utils.notEmpty(param.get("calUnit"))){
			calUnit = param.get("calUnit");
		}else{
			calUnit = "吨";
		}

		sql += " select * from (select left(t.work_date,7) workDate,t.main_work_name mainWorkName,t.main_work_code mainWorkCode,t.work_group_code workGroupCode,t.work_group_name workGroupName,TRUNCATE(SUM(case unit\n" +
				" when '吨' then weight*total \n" +
				" when '斤' then (0.0005*weight*total) \n" +
				" when '公斤' then (0.001*weight*total) \n" +
				" when '千克' then (0.001*weight*total) \n" +
				" when '克' then (0.000001*weight*total) \n" +
				" else 0 end),3) total,'"+calUnit+"' unit,ggfl \n" +
				" from (select h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,h.total,h.weight,h.st_nit unit,h.work_date,h.ggfl  from hm_work h where h.order_id is null and h.st_nit is not null\n" +
				" UNION \n" +
				" select h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,h.total,h.weight,h.st_nit unit,h2.kd_date work_date,h.ggfl from hm_work h \n" +
				" left join hm_order h2 on h2.id=h.order_id \n" +
				" where h.order_id is not null and h2.order_no is not null and h.st_nit is not null) t \n" +
				" where 1=1 and left(t.work_date,7) = '"+month+"' \n";
		if(Utils.notEmpty(param.get("mainWorkName"))){
			sql += " and t.main_work_name like '%"+param.get("mainWorkName")+"%'";
		}
		if(Utils.notEmpty(param.get("workGroupName"))){
			sql += " and t.work_group_name like '%"+param.get("workGroupName")+"%'";
		}
		if(Utils.notEmpty(param.get("workDate_begin"))){
			sql += " and t.work_date >= '"+param.get("workDate_begin")+"'";
		}
		if(Utils.notEmpty(param.get("workDate_end"))){
			sql += " and t.work_date <= '"+param.get("workDate_end")+"'";
		}
		sql +=" group by t.main_work_name,t.work_group_name,left(t.work_date,7),t.ggfl \n" +
				" UNION \n" +
				" select left(IFNULL(h2.kd_date,t.work_date),7) workDate,t.main_work_name mainWorkName,t.main_work_code mainWorkCode,t.work_group_code workGroupCode,t.work_group_name workGroupName,\n" +
				" TRUNCATE(SUM(t.weight),3) total,t.unit,t.ggfl from hm_work t\n" +
				" left join hm_order h2 on h2.id=t.order_id \n" +
				" where t.unit !=''and left(t.work_date,7) = '"+month+"' and t.unit not in ('吨','千克','克','公斤','斤') " ;
		if(Utils.notEmpty(param.get("mainWorkName"))){
			sql += " and t.main_work_name like '%"+param.get("mainWorkName")+"%'";
		}
		if(Utils.notEmpty(param.get("workGroupName"))){
			sql += " and t.work_group_name like '%"+param.get("workGroupName")+"%'";
		}
		if(Utils.notEmpty(param.get("workDate_begin"))){
			sql += " and t.work_date >= '"+param.get("workDate_begin")+"'";
		}
		if(Utils.notEmpty(param.get("workDate_end"))){
			sql += " and t.work_date <= '"+param.get("workDate_end")+"'";
		}
		sql +=	" group by t.main_work_name,t.work_group_name,left(IFNULL(h2.kd_date,t.work_date),7),t.unit,t.ggfl) aa where 1=1\n" +
				" order by aa.mainWorkName asc,left(aa.workDate,7) desc";

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
	 * 产能列表查询
	 *
	 * @return
	 */
	@RequestMapping(params = "listProductDayByJdbc")
	public void listProductDayByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		if(!"吨,千克,克,公斤,斤".contains(param.get("unit"))){
			sql +=" select h.work_date workDate,h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,TRUNCATE(SUM(h.weight),3) hjtotal,h.unit,h.work_date,h.ggfl from hm_work h\n" +
					" where 1=1  ";
			if(Utils.notEmpty(param.get("unit"))){
				sql += " and h.unit = '"+param.get("unit")+"'";
			}
			if(Utils.notEmpty(param.get("ggfl"))){
				sql += " and h.ggfl = '"+param.get("ggfl")+"'";
			}
		}else{
			/*sql +=" select h.work_date workDate,h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code,TRUNCATE(SUM(case st_nit \n" +
					" when '吨' then h.total*h.weight \n" +
					" when '斤' then (0.0005*h.total*h.weight) \n" +
					" when '公斤' then (0.001*h.total*h.weight) \n" +
					" when '千克' then (0.001*h.total*h.weight) \n" +
					" when '克' then (0.000001*h.total*h.weight) \n" +
					" else 0 end),3) hjtotal,'吨' unit,h.total,h.weight,h.work_date,h.ggfl from hm_work h\n" +
					" where 1=1  \n";*/
			List<Map<String, Object>>  unitList = systemService.findForJdbc("select DISTINCT h.a01a01a04 unit from hm_project h where h.a01a01a04 in ('吨','千克','克','公斤','斤')",null);
			sql +=" select h.work_date workDate,h.main_work_name,h.main_work_code,h.work_group_name,h.work_group_code \n";
			for(Map unit : unitList){
				sql += ",TRUNCATE(sum(CASE h.st_nit WHEN '"+unit.get("unit")+"'  THEN h.weight*h.total ELSE 0 END ),1) "+unit.get("unit")+" \n";
			}
			sql+=" ,h.work_date,h.ggfl from hm_work h where 1=1";

		}

		if(Utils.notEmpty(param.get("mainWorkCode"))){
			sql += " and h.main_work_code = '"+param.get("mainWorkCode")+"'";
		}
		if(Utils.notEmpty(param.get("workGroupCode"))){
			sql += " and h.work_group_code = '"+param.get("workGroupCode")+"'";
		}
		if(Utils.notEmpty(param.get("workDate"))){
			sql += " and left(h.work_date,7) = '"+param.get("workDate")+"'";
		}
		if(Utils.notEmpty(param.get("ggfl"))){
			sql += " and h.ggfl = '"+param.get("ggfl")+"'";
		}
		sql += " group by h.main_work_code,h.work_group_code,h.unit,h.work_date,h.ggfl asc ";

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
	 * 工作表列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "forWorkPrice")
	public ModelAndView forWorkPrice(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/work/forWorkPrice");
	}
	/**
	 * 工作表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/work/hmWorkList");
	}
	/**
	 * 形式二 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list1")
	public ModelAndView list1(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/work/hmWorkList1");
	}
	/**
	 * 形式三 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list2")
	public ModelAndView list2(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/work/hmWorkList2");
	}
	/**
	 * 形式四 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list3")
	public ModelAndView list3(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/work/hmWorkList3");
	}

	@RequestMapping(params = "detailList")
	public ModelAndView detailList(HttpServletRequest request) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		request.setAttribute("categoryEntities",categoryEntities);
		String columns = "";
		for(Map m : categoryEntities){
			if(Utils.notEmpty(m)){
				columns += m.get("code")+",";
			}
		}
		if(columns.indexOf(",")>0){
			columns = columns.substring(0,columns.length()-1);
		}
		request.setAttribute("columns",columns);
		return new ModelAndView("com/hm/rsgl/work/detailList");
	}

	@RequestMapping(params = "detailList2")
	public ModelAndView detailList2(HttpServletRequest request) {
		Map p = ParameterUtil.getParamMaps(request.getParameterMap());
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		request.setAttribute("categoryEntities",categoryEntities);
		String columns = "";
		for(Map m : categoryEntities){
			if(Utils.notEmpty(m)){
				columns += m.get("code")+",";
			}
		}
		if(columns.indexOf(",")>0){
			columns = columns.substring(0,columns.length()-1);
		}
		request.setAttribute("columns",columns);

		List<HmWorkEntity> workEntityList = systemService.findHql("from HmWorkEntity where orderId=?",p.get("orderId"));
		request.setAttribute("workEntityList",workEntityList);

		return new ModelAndView("com/hm/rsgl/work/detailList2");
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
	public void datagrid(HmWorkEntity hmWork,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		Map p = ParameterUtil.getParamMaps(request.getParameterMap());
		CriteriaQuery cq = new CriteriaQuery(HmWorkEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmWork, request.getParameterMap());
		try{
		//自定义追加查询条件
			if(Utils.notEmpty(p.get("forWp"))){
				cq.add(Restrictions.sqlRestriction("(FIND_IN_SET('"+p.get("workNo")+"',{alias}.gd_names_code) or FIND_IN_SET('"+p.get("workNo")+"',{alias}.other_jd_names_code) " +
						"	or FIND_IN_SET('"+p.get("workNo")+"',{alias}.gd_jd_names_code) or FIND_IN_SET('"+p.get("workNo")+"',{alias}.ls_jd_names_code))"));
			}
			if(Utils.notEmpty(p.get("realName"))){
				cq.add(Restrictions.sqlRestriction("({alias}.gd_names like '%"+p.get("realName")+"%' or {alias}.other_jd_names like '%"+p.get("realName")+"%' " +
						"or {alias}.gd_jd_names like '%"+p.get("realName")+"%'  or {alias}.ls_jd_names like '%"+p.get("realName")+"%')"));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmWorkService.getDataGridReturn(cq, true);
		if(Utils.notEmpty(hmWork.getOrderId())){
			String sql = "SELECT ifnull(ROUND(SUM(hj_money),2),0) zongji FROM hm_work WHERE order_id=?";
			String zongji = String.valueOf(systemService.findOneForJdbc(sql, hmWork.getOrderId()).get("zongji"));
			dataGrid.setFooter("hjMoney:" + zongji);
		}
		if(Utils.notEmpty(p.get("forWp"))){
			String sql = "SELECT ifnull(ROUND(SUM(hj_money),2),0) zongji FROM hm_work WHERE work_date=? and (FIND_IN_SET(?,gd_names_code) or FIND_IN_SET(?,other_jd_names_code) or FIND_IN_SET(?,gd_jd_names_code) or FIND_IN_SET(?,ls_jd_names_code))";
			String zongji = String.valueOf(systemService.findOneForJdbc(sql, hmWork.getWorkDate(),p.get("workNo"),p.get("workNo"),p.get("workNo"),p.get("workNo")).get("zongji"));
			dataGrid.setFooter("hjMoney:" + zongji);
		}
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "findDept")
	@ResponseBody
	public AjaxJson findDept(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		Map map = ParameterUtil.getParamMaps(request.getParameterMap());
		if(Utils.notEmpty(map.get("pCode"))){
			List<Map<String, Object>> departList = systemService.findForJdbc("select departname,org_code orgCode from t_s_depart where length(org_code)=? and parent_org_code=?",Integer.valueOf(map.get("len").toString()),map.get("pCode"));
			j.setObj(departList);
		}else{
			List<Map<String, Object>> departList = systemService.findForJdbc("select departname,org_code orgCode from t_s_depart where length(org_code)=?",Integer.valueOf(map.get("len").toString()));
			j.setObj(departList);
		}

		return j;
	}
	
	/**
	 * 删除工作表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(HmWorkEntity hmWork, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		hmWork = systemService.getEntity(HmWorkEntity.class, hmWork.getId());
		message = "工作表删除成功";
		try{
			hmWorkService.delete(hmWork);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工作表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除工作表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工作表删除成功";
		try{
			for(String id:ids.split(",")){
				HmWorkEntity hmWork = systemService.getEntity(HmWorkEntity.class, 
				id
				);
				systemService.executeSql("delete from hm_work_price where order_id=?",id);
				hmWorkService.delete(hmWork);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "工作表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加工作表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmWorkEntity hmWork, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工作表添加成功";
		try{
			Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());

			String dataRows = (String) map.get("dataRowsVal");
			if (Utils.notEmpty(dataRows)) {
				int rows = Integer.parseInt(dataRows);
				DecimalFormat df = new DecimalFormat("#.00");
				Map<String, Object> xs = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='xs' and t2.typename='形式一系数'");
				HmWorkEntity hmWorkEntity = null;

				for (int i = 0; i < rows; i++) {
					//if(Utils.notEmpty(map.get("orderMxList["+i+"].total"))){
						if(Utils.notEmpty(map.get("orderMxList["+i+"].a01a01a01"))){
							String workNoStr = "";
							hmWork.setXmmc(map.get("orderMxList["+i+"].a01a01a01"));
							hmWork.setGylc(map.get("orderMxList["+i+"].a01a01a02"));
							hmWork.setGgfl(map.get("orderMxList["+i+"].a01a01a03"));
							hmWork.setUnit(map.get("orderMxList["+i+"].a01a01a04"));
							hmWork.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].a01a01a05")));

							hmWork.setTotal(Double.parseDouble(Utils.notEmpty(map.get("orderMxList["+i+"].total")) ? map.get("orderMxList["+i+"].total"):"0"));
							hmWork.setStNit(map.get("orderMxList["+i+"].unit"));

							hmWork.setWeight(Double.parseDouble(Utils.notEmpty(map.get("orderMxList["+i+"].weight")) ? map.get("orderMxList["+i+"].weight"):"0"));
							hmWork.setHjWeight(map.get("orderMxList["+i+"].hjWeight"));
							hmWork.setHjMoney(map.get("orderMxList["+i+"].hjMoney"));

							hmWork.setId(null);
//						List<HmStaffEntityB> staffEntityList = systemService.findHql("from HmStaffEntityB where groupCode=? and yglb='本地' and job like '%组长%' and job not like '%副组长%' ",hmWork.getWorkGroupCode());
							List<HmStaffEntityB> staffEntityList = systemService.findHql("from HmStaffEntityB where groupCode=?  and job like '%组长%' and job not like '%副组长%' ",hmWork.getWorkGroupCode());
							List<HmStaffEntityB> staffEntityList2 = systemService.findHql("from HmStaffEntityB where groupCode=? and yglb='本地' and (job not like '%组长%' or job like '%副组长%')  ",hmWork.getWorkGroupCode());

							if(Utils.notEmpty(hmWork.getGdNamesCode())){
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
									}
									if(Utils.notEmpty(hmWork.getOtherJdNamesCode())){
										String[] rs = hmWork.getOtherJdNamesCode().replaceAll(",,",",").split(",");
										hmWork.setPeoples(hmWork.getPeoples()+rs.length);
									}
									if(Utils.notEmpty(hmWork.getLsJdNamesCode())){
										String[] rs = hmWork.getLsJdNamesCode().replaceAll(",,",",").split(",");
										hmWork.setPeoples(hmWork.getPeoples()+rs.length);
									}
									hmWork.setHjMoney(Utils.notEmpty(hmWork.getHjMoney()) ? hmWork.getHjMoney():"0");
									hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
								}

								if("0".equals(hmWork.getType())){
									hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));

									for(HmStaffEntityB staffEntityB : staffEntityList){
										if(!hmWork.getGdNamesCode().contains(staffEntityB.getWorkNo())){
											hmWork.setLocalPeoples(hmWork.getLocalPeoples()+1);
										}
									}
									for(HmStaffEntityB staffEntityB : staffEntityList2){
										if(!hmWork.getGdNamesCode().contains(staffEntityB.getWorkNo())){
											if(!staffEntityB.getJob().contains("组长")){
												hmWork.setLocalPeoples(hmWork.getLocalPeoples()+1);
											}else{
												if(staffEntityB.getJob().contains("副组长")){
													hmWork.setLocalPeoples(hmWork.getLocalPeoples()+1);
												}
											}
										}
									}
									if(Utils.notEmpty(hmWork.getGdJdNamesCode())){
										if(Utils.notEmpty(xs)){
											hmWork.setKfWages(hmWork.getGdPeoples()*Double.parseDouble(hmWork.getCapitaWages())*Double.parseDouble(xs.get("remark").toString()));
											if(hmWork.getKfWages()/hmWork.getLocalPeoples()>=1){
												String c = df.format(hmWork.getKfWages()/hmWork.getLocalPeoples());
												c = c.substring(0,c.indexOf("."));
												hmWork.setChoucheng(Double.parseDouble(c));
											}else{
												hmWork.setChoucheng(0.0);
											}
										}
									}
								}
								if("2".equals(hmWork.getType())){
									if(Utils.notEmpty(hmWork.getPeoples()) && Utils.notEmpty(hmWork.getHjMoney())){
										hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
										if(Utils.notEmpty(hmWork.getGdJdNamesCode())){
											xs = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='xs' and t2.typename='形式三系数'");
											if(Utils.notEmpty(xs)){
												hmWork.setKfWages(hmWork.getGdPeoples()*Double.parseDouble(hmWork.getCapitaWages())*Double.parseDouble(xs.get("remark").toString()));
											}
										}
									}

								}
								if("3".equals(hmWork.getType())){
									hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
								}
							}

							hmWorkEntity = new HmWorkEntity();
							MyBeanUtils.copyBeanNotNull2Bean(hmWork,hmWorkEntity);
							hmWorkService.save(hmWorkEntity);
							systemService.executeSql("delete from hm_work_price where order_id=? and type!=5",hmWorkEntity.getId());
							hmWork.setId(hmWorkEntity.getId());
							if(Utils.notEmpty(hmWork.getHjMoney())){
								//本组人员工资
								workNoStr = hmWork.getGdNamesCode().replaceAll(",,",",");
								saveWorkPrice(workNoStr,hmWork,"0");
								//其他人员工资
								workNoStr = hmWork.getOtherJdNamesCode().replaceAll(",,",",");
								saveWorkPrice(workNoStr,hmWork,"1");
								//固借人员工资
								workNoStr = hmWork.getGdJdNamesCode().replaceAll(",,",",");
								saveWorkPrice(workNoStr,hmWork,"2");
								//临时人员工资
								workNoStr = hmWork.getLsJdNamesCode().replaceAll(",,",",");
								saveWorkPrice(workNoStr,hmWork,"3");
							}
						}
					}
				}
			//}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "工作表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 更新工作表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmWorkEntity hmWork, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工作表更新成功";
		Map p = ParameterUtil.getParamMaps(request.getParameterMap());
		HmWorkEntity t = hmWorkService.get(HmWorkEntity.class, p.get("wid").toString());
		hmWork.setType(t.getType());
		try {
			List<HmStaffEntityB> staffEntityList = systemService.findHql("from HmStaffEntityB where groupCode=?  and job like '%组长%' and job not like '%副组长%' ",hmWork.getWorkGroupCode());
			List<HmStaffEntityB> staffEntityList2 = systemService.findHql("from HmStaffEntityB where groupCode=? and yglb='本地' and (job not like '%组长%' or job like '%副组长%')  ",hmWork.getWorkGroupCode());
			String workNoStr = "";
			Map<String, Object> xs = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='xs' and t2.typename='形式一系数'");
			DecimalFormat df = new DecimalFormat("#.00");
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
				}
				if(Utils.notEmpty(hmWork.getOtherJdNamesCode())){
					String[] rs = hmWork.getOtherJdNamesCode().replaceAll(",,",",").split(",");
					hmWork.setPeoples(hmWork.getPeoples()+rs.length);
				}
				if(Utils.notEmpty(hmWork.getLsJdNamesCode())){
					String[] rs = hmWork.getLsJdNamesCode().replaceAll(",,",",").split(",");
					hmWork.setPeoples(hmWork.getPeoples()+rs.length);
				}
				hmWork.setHjMoney(Utils.notEmpty(hmWork.getHjMoney()) ? hmWork.getHjMoney():"0");
				hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
			}
			if("0".equals(t.getType())){
				hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
				for(HmStaffEntityB staffEntityB : staffEntityList){
					if(!hmWork.getGdNamesCode().contains(staffEntityB.getWorkNo())){
						hmWork.setLocalPeoples(hmWork.getLocalPeoples()+1);
					}
				}
				for(HmStaffEntityB staffEntityB : staffEntityList2){
					if(!hmWork.getGdNamesCode().contains(staffEntityB.getWorkNo())){
						if(!staffEntityB.getJob().contains("组长")){
							hmWork.setLocalPeoples(hmWork.getLocalPeoples()+1);
						}else{
							if(staffEntityB.getJob().contains("副组长")){
								hmWork.setLocalPeoples(hmWork.getLocalPeoples()+1);
							}
						}
					}
				}
				if(Utils.notEmpty(hmWork.getGdJdNamesCode())){
					if(Utils.notEmpty(xs)){
						hmWork.setKfWages(hmWork.getGdPeoples()*Double.parseDouble(hmWork.getCapitaWages())*Double.parseDouble(xs.get("remark").toString()));
						if(hmWork.getKfWages()/hmWork.getLocalPeoples()>=1){
							String c = df.format(hmWork.getKfWages()/hmWork.getLocalPeoples());
							c = c.substring(0,c.indexOf("."));
							hmWork.setChoucheng(Double.parseDouble(c));
						}else{
							hmWork.setChoucheng(0.0);
						}
					}
				}
			}
			if("2".equals(t.getType())){
				if(Utils.notEmpty(hmWork.getPeoples()) && Utils.notEmpty(hmWork.getHjMoney())){
					hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
					if(Utils.notEmpty(hmWork.getGdJdNamesCode())){
						xs = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='xs' and t2.typename='形式三系数'");
						if(Utils.notEmpty(xs)){
							hmWork.setKfWages(hmWork.getGdPeoples()*Double.parseDouble(hmWork.getCapitaWages())*Double.parseDouble(xs.get("remark").toString()));
						}
					}
				}

			}
			if("3".equals(t.getType())){
				hmWork.setCapitaWages(df.format(Double.parseDouble(hmWork.getHjMoney())/hmWork.getPeoples()));
			}
			hmWork.setId(null);
			TSDepart tsDepart = systemService.findUniqueByProperty(TSDepart.class,"orgCode",hmWork.getWorkGroupCode());
			hmWork.setWorkGroupName(tsDepart.getDepartname());
			tsDepart = systemService.findUniqueByProperty(TSDepart.class,"orgCode",hmWork.getMainWorkCode());
			hmWork.setMainWorkName(tsDepart.getDepartname());
			MyBeanUtils.copyBeanNotNull2Bean(hmWork, t);
			hmWorkService.saveOrUpdate(t);
			systemService.executeSql("delete from hm_work_price where order_id=? and type!=5",t.getId());
			hmWork.setId(t.getId());
			if(Utils.notEmpty(hmWork.getHjMoney())){
				//本组人员工资
				workNoStr = hmWork.getGdNamesCode().replaceAll(",,",",");
				saveWorkPrice(workNoStr,hmWork,"0");
				//其他人员工资
				workNoStr = hmWork.getOtherJdNamesCode().replaceAll(",,",",");
				saveWorkPrice(workNoStr,hmWork,"1");
				//固借人员工资
				workNoStr = hmWork.getGdJdNamesCode().replaceAll(",,",",");
				saveWorkPrice(workNoStr,hmWork,"2");
				//临时人员工资
				workNoStr = hmWork.getLsJdNamesCode().replaceAll(",,",",");
				saveWorkPrice(workNoStr,hmWork,"3");
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工作表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}



	/**
	 * 重新生成日工资数据
	 *
	 * @return
	 */
	@RequestMapping(params = "doRe")
	@ResponseBody
	public AjaxJson doRe(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "重新生成日工资数据成功";
		try{
			for(String id:ids.split(",")){
				HmWorkEntity hmWork = systemService.getEntity(HmWorkEntity.class,id);
				try {
					systemService.executeSql("delete from hm_work_price where order_id=? and type!=5",hmWork.getId());
					//本组人员工资
					String workNoStr = hmWork.getGdNamesCode().replaceAll(",,",",");
					saveWorkPrice(workNoStr,hmWork,"0");
					//其他人员工资
					workNoStr = hmWork.getOtherJdNamesCode().replaceAll(",,",",");
					saveWorkPrice(workNoStr,hmWork,"1");
					//固借人员工资
					workNoStr = hmWork.getGdJdNamesCode().replaceAll(",,",",");
					saveWorkPrice(workNoStr,hmWork,"2");
					//临时人员工资
					workNoStr = hmWork.getLsJdNamesCode().replaceAll(",,",",");
					saveWorkPrice(workNoStr,hmWork,"3");
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "重新生成日工资数据失败";
					throw new BusinessException(e.getMessage());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "重新生成日工资数据失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工作表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(HmWorkEntity hmWork, HttpServletRequest req) {
		req.getSession().setAttribute("gdNamesCodeS","");
		req.getSession().setAttribute("otherJdNamesCodeS","");
		req.getSession().setAttribute("gdJdNamesCodeS","");
		req.getSession().setAttribute("lsJdNamesCodeS","");
		req.setAttribute("kdDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		if (StringUtil.isNotEmpty(hmWork.getId())) {
			hmWork = hmWorkService.getEntity(HmWorkEntity.class, hmWork.getId());
			req.setAttribute("hmWorkPage", hmWork);
		}
		return new ModelAndView("com/hm/rsgl/work/hmWork-add");
	}
	/**
	 * 工作表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmWorkEntity hmWork, HttpServletRequest req) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A01' order by order_num asc");
		req.setAttribute("categoryEntities",categoryEntities);
		String columns = "";
		for(Map m : categoryEntities){
			if(Utils.notEmpty(m)){
				columns += m.get("code")+",";
			}
		}
		if(columns.indexOf(",")>0){
			columns = columns.substring(0,columns.length()-1);
		}
		req.setAttribute("columns",columns);
		if (StringUtil.isNotEmpty(hmWork.getId())) {
			hmWork = hmWorkService.getEntity(HmWorkEntity.class, hmWork.getId());
			req.setAttribute("hmWorkPage", hmWork);
			req.getSession().setAttribute("gdNamesCodeS",hmWork.getGdNamesCode());
			req.getSession().setAttribute("otherJdNamesCodeS",hmWork.getOtherJdNamesCode());
			req.getSession().setAttribute("gdJdNamesCodeS",hmWork.getGdJdNamesCode());
			req.getSession().setAttribute("lsJdNamesCodeS",hmWork.getLsJdNamesCode());
		}
		return new ModelAndView("com/hm/rsgl/work/hmWork-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","hmWorkController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(HmWorkEntity hmWork,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(HmWorkEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmWork, request.getParameterMap());
		List<HmWorkEntity> hmWorks = this.hmWorkService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"工作表");
		modelMap.put(NormalExcelConstants.CLASS,HmWorkEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工作表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,hmWorks);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(HmWorkEntity hmWork,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"工作表");
    	modelMap.put(NormalExcelConstants.CLASS,HmWorkEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("工作表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<HmWorkEntity> listHmWorkEntitys = ExcelImportUtil.importExcel(file.getInputStream(),HmWorkEntity.class,params);
				for (HmWorkEntity hmWork : listHmWorkEntitys) {
					hmWorkService.save(hmWork);
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
	@ApiOperation(value="工作表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<HmWorkEntity>> list() {
		List<HmWorkEntity> listHmWorks=hmWorkService.getList(HmWorkEntity.class);
		return Result.success(listHmWorks);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取工作表信息",notes="根据ID获取工作表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		HmWorkEntity task = hmWorkService.get(HmWorkEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取工作表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建工作表")
	public ResponseMessage<?> create(@ApiParam(name="工作表对象")@RequestBody HmWorkEntity hmWork, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmWorkEntity>> failures = validator.validate(hmWork);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmWorkService.save(hmWork);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工作表信息保存失败");
		}
		return Result.success(hmWork);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新工作表",notes="更新工作表")
	public ResponseMessage<?> update(@ApiParam(name="工作表对象")@RequestBody HmWorkEntity hmWork) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<HmWorkEntity>> failures = validator.validate(hmWork);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			hmWorkService.saveOrUpdate(hmWork);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新工作表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新工作表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除工作表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			hmWorkService.deleteEntityById(HmWorkEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("工作表删除失败");
		}

		return Result.success();
	}
}
