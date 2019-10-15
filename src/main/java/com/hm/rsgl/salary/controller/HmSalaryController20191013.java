package com.hm.rsgl.salary.controller;

import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.kq.checkpay.entity.HmCheckPayEntity;
import com.hm.rsgl.basesalary.entity.HmBaseSalaryEntity;
import com.hm.rsgl.basesalary.service.HmBaseSalaryServiceI;
import com.hm.rsgl.leavestaff.entity.HmLeaveStaffEntity;
import com.hm.rsgl.salary.entity.HmSalaryEntity;
import com.hm.rsgl.staff.entity.HmStaffEntity;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.util.*;

/**   
 * @Title: Controller  
 * @Description: 薪酬管理
 * @author onlineGenerator
 * @date 2019-07-08 22:19:21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/HmSalaryController20191013")
public class HmSalaryController20191013 extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HmSalaryController20191013.class);

	@Autowired
	private HmBaseSalaryServiceI hmBaseSalaryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@RequestMapping(params = "forGzReport")
	public ModelAndView forGzReport(HttpServletRequest request) {
		String month = DateUtil.format(new Date(),"yyyy-MM");
		Map tj = systemService.findOneForJdbc("select TRUNCATE(SUM(a.money),1) zgz from hm_salary a where a.month=?",month);
		List<Map<String, Object>>  orgList = systemService.findForJdbc("select org_code orgCode,CONCAT(substr(departname,1,2),'<br/>',substr(departname,3,2),'<br/>',substr(departname,5,2)) departname from t_s_depart where LENGTH(org_code)=9 and departname !=''",null);
		request.setAttribute("orgList",orgList);
		request.setAttribute("zgz",tj.get("zgz"));
		request.setAttribute("month",month);
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-forGzReport");
	}
	@RequestMapping(params = "forGroupReport")
	public ModelAndView forGroupReport(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-forGroupReport");
	}

	/**
	 * 每日工资明细列表 页面跳转
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
		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		request.setAttribute("cols",cols);
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList");
	}
	/**
	 * 工资总表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "gzzb")
	public ModelAndView gzzb(HttpServletRequest request) {
		/*List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
		request.setAttribute("categoryEntities",categoryEntities);*/

		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		request.setAttribute("zcategoryEntities",categoryEntities);
		request.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		request.setAttribute("fcategoryEntities",categoryEntities);
		request.setAttribute("fSize",categoryEntities.size());

		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-zb");
	}
	/**
	 * 离职工资总表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "lzzb")
	public ModelAndView lzzb(HttpServletRequest request) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		request.setAttribute("zcategoryEntities",categoryEntities);
		request.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		request.setAttribute("fcategoryEntities",categoryEntities);
		request.setAttribute("fSize",categoryEntities.size());

		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-lzzb");
	}
	/**
	 * 自离工资总表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "zlzb")
	public ModelAndView zlzb(HttpServletRequest request) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		request.setAttribute("zcategoryEntities",categoryEntities);
		request.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		request.setAttribute("fcategoryEntities",categoryEntities);
		request.setAttribute("fSize",categoryEntities.size());

		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-zlzb");
	}
	/**
	 * 工资发放明细列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "provide")
	public ModelAndView provide(HttpServletRequest request) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		request.setAttribute("zcategoryEntities",categoryEntities);
		request.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		request.setAttribute("fcategoryEntities",categoryEntities);
		request.setAttribute("fSize",categoryEntities.size());

		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-provide");
	}
	/**
	 * 考勤工时核对条列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "worktime")
	public ModelAndView worktime(HttpServletRequest request) {
		List<Map> cols = new ArrayList<>();
		Map data = null;
		for(int i = 1 ; i <=31 ; i++){
			data = new HashMap();
			data.put("name",i);
			data.put("code","d"+i);
			cols.add(data);
		}
		request.setAttribute("month",DateUtil.format(new Date(),"yyyy-MM"));
		request.setAttribute("cols",cols);
		List<Map<String, Object>> departList = systemService.findForJdbc("select departname,org_code orgCode from t_s_depart where length(org_code)=6",null);
		request.setAttribute("departList",departList);

		return new ModelAndView("com/hm/rsgl/salary/hmSalaryList-worktime");
	}
	/**
	 * 薪酬管理列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "detail")
	public ModelAndView detail(HttpServletRequest request) {
		return new ModelAndView("com/hm/rsgl/salary/detail");
	}

	/**
	 * 打印 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "jump")
	public Object jump(HttpServletRequest req) {
		Map<String,String> map= ParameterUtil.getParamMaps(req.getParameterMap());
		String month = map.get("month")+"-01";
		month = DateUtil.getDate2SimpleStr(DateUtil.getDate(month),"yyyy年MM月");
		String date = DateUtil.getDate2SimpleStr(new Date(),"yyyy年MM月dd日");
		req.setAttribute("url", map.get("url")+"&month="+map.get("month")+"&monthS="+month+"&date="+date+"&realName="+map.get("realName")
				+"&workNo="+map.get("workNo")+"&deptName="+map.get("deptName")+"&workName="+map.get("workName")+"&groupName="+map.get("groupName")
				+"&taker="+map.get("taker")+"&leaveStaff="+map.get("leaveStaff")+"&leaveT="+map.get("leaveT"));
		return "forward:/context/"+map.get("r")+".jsp";
	}

	/**
	 * 薪酬总表打印预览 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "printPreview")
	public ModelAndView printPreview(HttpServletRequest req) {
		Map<String,String> map= ParameterUtil.getParamMaps(req.getParameterMap());
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		req.setAttribute("zcategoryEntities",categoryEntities);
		req.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		req.setAttribute("fcategoryEntities",categoryEntities);
		req.setAttribute("fSize",categoryEntities.size());
		//List<HmSalaryEntity> salaryEntityList = systemService.findHql("from HmSalaryEntity where month=? order by deptName,workName,groupName",map.get("month"));
		StringBuilder hql = new StringBuilder("from HmSalaryEntity h where 1=1 ");
		Map<String, Object> param = new HashMap<String, Object>();
		if(Utils.notEmpty(map.get("month"))){
			hql.append(" and h.month = '"+map.get("month")+"'");
		}
		if(Utils.notEmpty(map.get("deptName"))){
			hql.append(" and h.deptName like '%"+map.get("deptName")+"%'");
		}
		if(Utils.notEmpty(map.get("workName"))){
			hql.append(" and h.workName like '%"+map.get("workName")+"%'");
		}
		if(Utils.notEmpty(map.get("groupName"))){
			hql.append(" and h.groupName like '%"+map.get("groupName")+"%'");
		}
		if(Utils.notEmpty(map.get("workNo"))){
			hql.append(" and h.workNo = '"+map.get("workNo")+"'");
		}
		if(Utils.notEmpty(map.get("realName"))){
			hql.append(" and h.realName like '%"+map.get("realName")+"%'");
		}
		if(Utils.notEmpty(map.get("taker"))){
			hql.append(" and h.taker = '"+map.get("taker")+"'");
		}

		if(Utils.isEmpty(map.get("leaveT"))){
			hql.append(" and (h.leaveType is null or h.leaveType='') ");
		}else{
			hql.append(" and h.leaveType ='"+map.get("leaveT")+"' ");
		}

		if(Utils.notEmpty(map.get("leaveStaff"))){
			List<String> workList = new ArrayList<String>();
			List<HmLeaveStaffEntity> leaveStaffEntityList = systemService.findHql("from HmLeaveStaffEntity where state=1");
			hql.append(" and h.realName in ( ");
			for(HmLeaveStaffEntity hmLeaveStaffEntity:leaveStaffEntityList){
				hql.append("'"+hmLeaveStaffEntity.getRealName()+"',");
			}
			hql.append(" '0') ");
		}

		hql.append(" order by deptName,workName,groupName ");
		List<HmSalaryEntity> salaryEntityList = systemService.findHql(hql.toString(),null);
		req.setAttribute("salaryEntityList",salaryEntityList);

		List<Map> dataList = new ArrayList<>();
		List<HmSalaryEntity> subdataList = new ArrayList<>();
		int i= 0;
		Map b = null;

		for(HmSalaryEntity hmSalaryEntity : salaryEntityList){
			if(i == 24){
				b = new HashMap();
				subdataList.add(hmSalaryEntity);
				b.put("subdataList",subdataList);
				dataList.add(b);
				i = 0;
				subdataList = new ArrayList<>();
			}else{
				subdataList.add(hmSalaryEntity);
				i++;
			}
		}
		if(subdataList.size()>0){
			b = new HashMap();
			b.put("subdataList",subdataList);
			dataList.add(b);
		}
		req.setAttribute("dataList",dataList);
		return new ModelAndView("com/hm/rsgl/salary/printPreview");

	}
	/**
	 * 按部门车间打印预览 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "printPreviewDeptWork")
	public ModelAndView printPreviewDeptWork(HttpServletRequest req) {
		Map<String,String> map= ParameterUtil.getParamMaps(req.getParameterMap());
		StringBuilder sql = new StringBuilder("select (select departname from t_s_depart where org_code=t.parent_org_code) pdeptName,t.departname,t.org_code orgCode from t_s_depart t where length(org_code)=9 ");
		//List<HmSalaryEntity> salaryEntityList = systemService.findHql("from HmSalaryEntity where month=? order by deptName,workName,groupName",map.get("month"));
		StringBuilder hql = new StringBuilder("from HmSalaryEntity where 1=1 ");
		Map<String, Object> param = new HashMap<String, Object>();
		if(Utils.notEmpty(map.get("month"))){
			hql.append(" and month = '"+map.get("month")+"'");
		}
		if(Utils.notEmpty(map.get("deptName"))){
			hql.append(" and deptName like '%"+map.get("deptName")+"%'");
			sql.append(" and departname like '%"+map.get("deptName")+"%'");
		}
		if(Utils.notEmpty(map.get("workName"))){
			hql.append(" and workName like '%"+map.get("workName")+"%'");
			sql.append(" and departname like '%"+map.get("workName")+"%'");

		}
		if(Utils.notEmpty(map.get("groupName"))){
			hql.append(" and groupName like '%"+map.get("groupName")+"%'");
			sql.append(" and org_code=(select parent_org_code from t_s_depart where departname = '"+map.get("groupName")+"' limit 0,1)");
		}
		if(Utils.notEmpty(map.get("realName"))){
			hql.append(" and realName like '%"+map.get("realName")+"%'");
		}
		if(Utils.notEmpty(map.get("workNo"))){
			hql.append(" and workNo = '"+map.get("workNo")+"'");
		}
		if(Utils.notEmpty(map.get("taker"))){
			hql.append(" and taker = '"+map.get("taker")+"'");
		}
		if(Utils.notEmpty(map.get("leaveT"))){
			hql.append(" and (leaveType is null or leaveType='') ");
		}else{
			hql.append(" and leaveType ='"+map.get("leaveT")+"' ");
		}
		hql.append(" order by deptName,workName,groupName ");
		List<HmSalaryEntity> salaryEntityList = systemService.findHql(hql.toString(),null);
		List<Map<String, Object>> departList = systemService.findForJdbc(sql.toString());

		List<Map> dataList = new ArrayList<>();
		List<HmSalaryEntity> subdataList = null;
		for(Map p : departList){
			subdataList = new ArrayList<>();
			for(HmSalaryEntity hmSalaryEntity : salaryEntityList){
				if(hmSalaryEntity.getWorkCode().equals(p.get("orgCode"))){
					subdataList.add(hmSalaryEntity);
				}
			}
			if(subdataList.size()>0){
				p.put("subdataList",subdataList);
				dataList.add(p);
			}
		}
		req.setAttribute("dataList",dataList);

		dataList = new ArrayList<>();
		Map b = null;
		for(Map p : departList){

			subdataList = new ArrayList<>();
			int i= 0;
			for(HmSalaryEntity hmSalaryEntity : salaryEntityList){
				if(hmSalaryEntity.getWorkCode().equals(p.get("orgCode"))){
					if(i == 24){
						b = new HashMap();
						b.put("pdeptName",p.get("pdeptName"));
						b.put("departname",p.get("departname"));

						subdataList.add(hmSalaryEntity);
						b.put("subdataList",subdataList);
						dataList.add(b);
						i = 0;
						subdataList = new ArrayList<>();
					}else{
						subdataList.add(hmSalaryEntity);
						i++;
					}
				}
			}
			if(subdataList.size()>0){
				b = new HashMap();
				b.put("pdeptName",p.get("pdeptName"));
				b.put("departname",p.get("departname"));
				b.put("subdataList",subdataList);
				dataList.add(b);
			}

		}
		req.setAttribute("dataList2",dataList);

		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		req.setAttribute("zcategoryEntities",categoryEntities);
		req.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		req.setAttribute("fcategoryEntities",categoryEntities);
		req.setAttribute("fSize",categoryEntities.size());

		return new ModelAndView("com/hm/rsgl/salary/printPreviewDeptWork");

	}

	/**
	 * 按部门车间工资条打印预览 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "printPreviewGzt")
	public ModelAndView printPreviewGzt(HttpServletRequest req) {
		Map<String,String> map= ParameterUtil.getParamMaps(req.getParameterMap());
		List<Map<String, Object>> departList = systemService.findForJdbc("select (select departname from t_s_depart where org_code=t.parent_org_code) pdeptName,t.departname,t.org_code orgCode from t_s_depart t where length(org_code)=9");
		StringBuilder hql = new StringBuilder("from HmSalaryEntity where 1=1 ");
		Map<String, Object> param = new HashMap<String, Object>();

		if(Utils.notEmpty(map.get("month"))){
			hql.append(" and month = '"+map.get("month")+"'");
		}
		if(Utils.notEmpty(map.get("deptName"))){
			hql.append(" and deptName like '%"+map.get("deptName")+"%'");
		}
		if(Utils.notEmpty(map.get("workName"))){
			hql.append(" and workName like '%"+map.get("workName")+"%'");
		}
		if(Utils.notEmpty(map.get("groupName"))){
			hql.append(" and groupName like '%"+map.get("groupName")+"%'");
		}
		if(Utils.notEmpty(map.get("realName"))){
			hql.append(" and realName like '%"+map.get("realName")+"%'");
		}
		if(Utils.notEmpty(map.get("workNo"))){
			hql.append(" and workNo = '"+map.get("workNo")+"'");
		}
		if(Utils.notEmpty(map.get("taker"))){
			hql.append(" and taker = '"+map.get("taker")+"'");
		}
		if(Utils.notEmpty(map.get("leaveT"))){
			hql.append(" and (leaveType is null or leaveType='') ");
		}else{
			hql.append(" and leaveType ='"+map.get("leaveT")+"' ");
		}
		List<HmSalaryEntity> salaryEntityList = systemService.findHql(hql.toString(),null);
		List<Map> dataList = new ArrayList<>();
		List<HmSalaryEntity> subdataList = null;
		for(Map p : departList){
			subdataList = new ArrayList<>();
			for(HmSalaryEntity hmSalaryEntity : salaryEntityList){
				if(hmSalaryEntity.getWorkCode().equals(p.get("orgCode"))){
					subdataList.add(hmSalaryEntity);
				}
			}
			if(subdataList.size()>0){
				p.put("subdataList",subdataList);
				dataList.add(p);
			}

		}
		req.setAttribute("dataList",dataList);

		dataList = new ArrayList<>();
		Map b = null;
		for(Map p : departList){

			subdataList = new ArrayList<>();
			int i= 0;
			for(HmSalaryEntity hmSalaryEntity : salaryEntityList){
				if(hmSalaryEntity.getWorkCode().equals(p.get("orgCode"))){
					if(i == 14){
						b = new HashMap();
						b.put("pdeptName",p.get("pdeptName"));
						b.put("departname",p.get("departname"));

						subdataList.add(hmSalaryEntity);
						b.put("subdataList",subdataList);
						dataList.add(b);
						i = 0;
						subdataList = new ArrayList<>();
					}else{
						subdataList.add(hmSalaryEntity);
					}
					i++;
				}
			}
			if(subdataList.size()>0){
				b = new HashMap();
				b.put("pdeptName",p.get("pdeptName"));
				b.put("departname",p.get("departname"));
				b.put("subdataList",subdataList);
				dataList.add(b);
			}

		}
		req.setAttribute("dataList2",dataList);

		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=6 order by order_num asc");
		req.setAttribute("zcategoryEntities",categoryEntities);
		req.setAttribute("zSize",categoryEntities.size());

		categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' and column_type=7 order by order_num asc");
		req.setAttribute("fcategoryEntities",categoryEntities);
		req.setAttribute("fSize",categoryEntities.size());
		return new ModelAndView("com/hm/rsgl/salary/printPreviewGzt");

	}

	/**
	 * 考勤工时核对条打印预览 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "printPreviewGshdt")
	public ModelAndView printPreviewGshdt(HttpServletRequest req) {
		String sql = "",countSql = "";
		Map data = null;
		List<Map> cols = new ArrayList<>();

		for(int i = 1 ; i <=31 ; i++){
			data = new HashMap();
			data.put("name",i);
			data.put("code","d"+i);
			cols.add(data);
		}
		req.setAttribute("cols",cols);
		TSUser user = ResourceUtil.getSessionUser();
		Map<String,String> param = ParameterUtil.getParamMaps(req.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		sql +="select * from ((select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'公班' cqsjType,'0' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5)+(select ifnull(sum(hm.zs_zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'公班' cqsjType,'1' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(TRUNCATE(sum(hm.jiab)/60,1),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5)+(select ifnull(sum(hm.zs_jiab),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'金额' cqsjType,'2' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) +(select ifnull(sum(hm.zshj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}
		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'计件' cqsjType,'3' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'计件' cqsjType,'4' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(TRUNCATE(sum(hm.jiab)/60,1),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'金额' cqsjType,'5' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and ((hm.order_id is not null and hm.order_id!='' and (hm.zcb !='' or hm.jiab !='') and hm.work_type=1) or hm.work_type=0 or hm.work_type=2 or hm.work_type=3)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql += " )) h group by h.deptName,h.workName,h.workNo,h.cqsj asc ";

		List<Map<String, Object>> dataList0 = systemService.findForJdbc(sql,null);
		req.setAttribute("dataList",dataList0);


		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Map> subdataList = new ArrayList<>();

		int i = 0;
		Map b = null;
		for(Map<String, Object>  p : dataList0){
			if("2".equals(p.get("cqsj")) || "5".equals(p.get("cqsj"))) {
				for (int z = 1; z <= 31; z++) {
					if (p.get("d" + z).toString().indexOf(".") > 0) {
						p.put("d" + z, p.get("d" + z).toString().substring(0, p.get("d" + z).toString().indexOf(".")));
					}
				}
			}else{
				for (int z = 1; z <= 31; z++) {
					if (p.get("d" + z).toString().indexOf(".0") > 0) {
						p.put("d" + z, p.get("d" + z).toString().substring(0, p.get("d" + z).toString().indexOf(".")));
					}
				}
			}
			if(i == 5){
				b = new HashMap();
				subdataList.add(p);
				b.put("subdataList",subdataList);
				dataList.add(b);
				subdataList = new ArrayList<>();
				i = 0;
			}else{
				subdataList.add(p);
				i++;
			}
		}
		if(subdataList.size()>0){
			b = new HashMap();
			b.put("subdataList",subdataList);
			dataList.add(b);
		}
		req.setAttribute("dataList2",dataList);
		return new ModelAndView("com/hm/rsgl/salary/printPreviewGshdt");
	}

	/**
	 * 考勤工时核对总表打印预览 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "printPreviewGshdtZb")
	public ModelAndView printPreviewGshdtZb(HttpServletRequest req) {
		String sql = "",countSql = "";
		Map data = null;
		List<Map> cols = new ArrayList<>();

		for(int i = 1 ; i <=31 ; i++){
			data = new HashMap();
			data.put("name",i);
			data.put("code","d"+i);
			cols.add(data);
		}
		req.setAttribute("cols",cols);
		TSUser user = ResourceUtil.getSessionUser();
		Map<String,String> param = ParameterUtil.getParamMaps(req.getParameterMap());
		String month = "";

		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		sql +="select * from ((select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'公班' cqsjType,'0' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			//sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) d"+i+" \n";
			sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5)+(select ifnull(sum(hm.zs_zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'公班' cqsjType,'1' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			//sql += ",(select ifnull(sum(hm.jiab),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) d"+i+" \n";
			sql += ",(select ifnull(TRUNCATE(sum(hm.jiab)/60,1),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5)+(select ifnull(sum(hm.zs_jiab),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'金额' cqsjType,'2' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			//sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) d"+i+" \n";
			sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) +(select ifnull(sum(hm.zshj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'计件' cqsjType,'3' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'计件' cqsjType,'4' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(TRUNCATE(sum(hm.jiab)/60,1),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'金额' cqsjType,'5' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and ((hm.order_id is not null and hm.order_id!='' and (hm.zcb !='' or hm.jiab !='') and hm.work_type=1) or hm.work_type=0 or hm.work_type=2 or hm.work_type=3)) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("workNo"))){
			sql += " and hs.work_no = '"+param.get("workNo")+"'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql += " )) h group by h.deptName,h.workName,h.workNo,h.cqsj asc ";

		List<Map<String, Object>> dataList0 = systemService.findForJdbc(sql,null);
		req.setAttribute("dataList",dataList0);


		List<Map<String, Object>> dataList = new ArrayList<>();
		List<Map> subdataList = new ArrayList<>();

		int i = 0;
		Map b = null;
		for(Map<String, Object>  p : dataList0){
			if("2".equals(p.get("cqsj")) || "5".equals(p.get("cqsj"))) {
				for (int z = 1; z <= 31; z++) {
					if (p.get("d" + z).toString().indexOf(".") > 0) {
						p.put("d" + z, p.get("d" + z).toString().substring(0, p.get("d" + z).toString().indexOf(".")));
					}
				}
			}else{
				for (int z = 1; z <= 31; z++) {
					if (p.get("d" + z).toString().indexOf(".0") > 0) {
						p.put("d" + z, p.get("d" + z).toString().substring(0, p.get("d" + z).toString().indexOf(".")));
					}
				}
			}
			if(i == 23){
				b = new HashMap();
				subdataList.add(p);
				b.put("subdataList",subdataList);
				dataList.add(b);
				subdataList = new ArrayList<>();
				i = 0;
			}else{
				subdataList.add(p);
				i++;
			}
		}
		if(subdataList.size()>0){
			b = new HashMap();
			b.put("subdataList",subdataList);
			dataList.add(b);
		}
		req.setAttribute("dataList2",dataList);
		return new ModelAndView("com/hm/rsgl/salary/printPreviewGshdtZb");
	}

	/**
	 * 历史查询
	 *
	 * @return
	 */
	@RequestMapping(params = "listAllByJdbc")
	public void listAllByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		sql +=" select hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select sum(hm.gz_hj + ifnull(hm.zshj,0)) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no) d"+i+" \n";
		}
		sql+="from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_name like '%"+param.get("deptName")+"%'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_name like '%"+param.get("workName")+"%'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_name like '%"+param.get("groupName")+"%'";
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
	 * 考勤工时核对条查询
	 *
	 * @return
	 */
	@RequestMapping(params = "listWorkTimeByJdbc")
	public void listWorkTimeByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		TSUser user = ResourceUtil.getSessionUser();
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		sql +="select * from ((select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'公班' cqsjType,'0' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5)+(select ifnull(sum(hm.zs_zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+=" from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'公班' cqsjType,'1' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(TRUNCATE(sum(hm.jiab)/60,1),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) +(select ifnull(sum(hm.zs_jiab),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+=" from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'金额' cqsjType,'2' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and hm.type=5) +(select ifnull(sum(hm.zshj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+=" from hm_staff hs where 1=1 ";
		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'计件' cqsjType,'3' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+=" from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'计件' cqsjType,'4' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(TRUNCATE(sum(hm.jiab)/60,1),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) d"+i+" \n";
		}
		sql+=" from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql +=" ) union (select hs.xclb,hs.real_name realName,hs.work_no workNo,hs.dept_name deptName,hs.work_name workName,hs.group_name groupName,'金额' cqsjType,'5' cqsj \n";
		for(int i = 1 ; i <=31 ; i++){
			sql += ",(select ifnull(sum(hm.gz_hj),0) from hm_work_price hm where hm.kd_date='"+month+"-"+String.format("%02d",i)+"' and hm.work_no=hs.work_no and ((hm.order_id is not null and hm.order_id!='' and (hm.zcb !='' or hm.jiab !='') and hm.work_type=1) or hm.work_type=0 or hm.work_type=2 or hm.work_type=3)) d"+i+" \n";
		}
		sql+=" from hm_staff hs where 1=1 ";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and hs.dept_code = '"+param.get("deptName")+"'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and hs.work_code = '"+param.get("workName")+"'";
		}
		if(Utils.notEmpty(param.get("groupName"))){
			sql += " and hs.group_code = '"+param.get("groupName")+"'";
		}
		if(Utils.notEmpty(param.get("realName"))){
			sql += " and hs.real_name like '%"+param.get("realName")+"%'";
		}
		if(Utils.notEmpty(param.get("taker"))){
			sql += " and hs.taker = '"+param.get("taker")+"'";
		}

		sql += " )) h group by h.deptName,h.workName,h.workNo,h.cqsj asc ";

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
	 * 工资明细查询
	 *
	 * @return
	 */
	@RequestMapping(params = "detailList")
	public void detailList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		TSUser user = ResourceUtil.getSessionUser();
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		String day = String.format("%02d",Integer.parseInt(param.get("day")));
		sql +=" (select ifnull(hw.main_work_name,hw2.main_work_name) mainWorkName,(select sum(hm.gz_hj) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.MAIN_WORK_CODE=h.MAIN_WORK_CODE and (hm.order_id is not null and hm.order_id!='' and hm.work_type=0)) hj1,\n" +
				"	(select sum(hm.gz_hj) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.MAIN_WORK_CODE=h.MAIN_WORK_CODE and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) hj2,\n" +
				"	(select sum(hm.gz_hj) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.MAIN_WORK_CODE=h.MAIN_WORK_CODE and (hm.order_id is not null and hm.order_id!='' and hm.work_type=2)) hj3,\n" +
				"	(select sum(hm.gz_hj) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.MAIN_WORK_CODE=h.MAIN_WORK_CODE and (hm.order_id is not null and hm.order_id!='' and hm.work_type=3)) hj4,\n" +
				" 	'' hj5,'' hj5zcb,'' hj5jiab \n" +
				"	from hm_work_price h LEFT JOIN hm_work hw on hw.id=h.order_id LEFT JOIN hm_work hw2 on hw2.order_id=h.order_id " +
				"	where  h.kd_date='"+month+'-'+day+"' and  h.work_no='"+param.get("workNo")+"' " +
				"	and (hw.main_work_code is not null or hw2.main_work_code is not null) group by hw.main_work_code,hw2.main_work_code) ";


		//sql += " order by h.id asc ";
		countSql = "SELECT count(1) FROM ("+sql+") t9 ";

		sql += " limit ";
		if(dataGrid.getPage()==1){
			sql += " 0, "+dataGrid.getRows();
		}else{
			sql += (dataGrid.getPage()-1)*dataGrid.getRows()+","+dataGrid.getRows();
		}
		this.systemService.listAllByJdbc(dataGrid, sql, countSql);

		String sql1 =" select h.main_work_name mainWorkName,'' hj1,'' hj2,'' hj3,'' hj4,\n" +
				"	(select sum(hm.gz_hj) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.type=5) hj5,\n" +
				"	(select ifnull(sum(hm.zcb),0) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.type=5) hj5zcb,\n" +
				"	(select sum(hm.gz_hj) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no and hm.type=5) hj5jiab\n" +
				"	from hm_work_price h where  h.kd_date='"+month+'-'+day+"' and  h.work_no='"+param.get("workNo")+"' group by h.work_no";
		List<Map<String, Object>>  list1 = systemService.findForJdbc(sql1);
		String sql2 =" select h.main_work_name mainWorkName,'' hj1,'' hj2,'' hj3,'' hj4,\n" +
				"	(select ifnull(sum(hm.zs_zcb),0) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no  and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) hj5zcb,\n" +
				"	(select ifnull(sum(hm.zs_jiab),0) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no  and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) hj5jiab,\n" +
				"	(select ifnull(sum(hm.zshj),0) from hm_work_price hm where hm.kd_date='"+month+'-'+day+"' and hm.work_no=h.work_no   and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1)) hj5\n" +
				"	from hm_work_price h LEFT JOIN hm_work hw on hw.id=h.order_id LEFT JOIN hm_work hw2 on hw2.order_id=h.order_id " +
				"	where  h.kd_date='"+month+'-'+day+"' and  h.work_no='"+param.get("workNo")+"' " +
				"	and (hw.main_work_code is not null or hw2.main_work_code is not null) group by hw.main_work_code,hw2.main_work_code ";
		List<Map<String, Object>>  list2 = systemService.findForJdbc(sql2);
		List<Map<String, Object>> newlist = dataGrid.getResults();
		newlist.addAll(list1);
		newlist.addAll(list2);
		dataGrid.setResults(newlist);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 工资总表报表历史查询
	 *
	 * @return
	 */
	@RequestMapping(params = "listGzReportByJdbc")
	public void listGzReportByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		List<Map<String, Object>>  orgList = systemService.findForJdbc("select org_code from t_s_depart where LENGTH(org_code)=9 and departname !=''",null);
		sql +=" select h.month,h.dept_name deptName,h.dept_code deptCode,h.work_name workName,h.work_code workCode,(select TRUNCATE(SUM(a.money),1) from hm_salary a where a.dept_code=h.dept_code and a.month='"+month+"') bmgz \n";
		for(Map orgCode : orgList){
			sql += ",TRUNCATE(sum(CASE h.work_code WHEN '"+orgCode.get("org_code")+"'  THEN h.money ELSE 0 END ),1) "+orgCode.get("org_code")+" \n";
		}
		sql+=" from hm_salary h where 1=1 and h.month='"+month+"'";

		if(Utils.notEmpty(param.get("deptName"))){
			sql += " and h.dept_name like '%"+param.get("deptName")+"%'";
		}
		if(Utils.notEmpty(param.get("workName"))){
			sql += " and h.work_name like '%"+param.get("workName")+"%'";
		}

		sql += "  GROUP BY h.dept_code ";

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
	 * 按组分类统计工资
	 *
	 * @return
	 */
	@RequestMapping(params = "listAllGroupByJdbc")
	public void listAllGroupByJdbc(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String sql = "",countSql = "";
		Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
		String month = "";
		if(Utils.notEmpty(param.get("month"))){
			month = param.get("month");
		}else{
			month = DateUtil.format(new Date(),"yyyy-MM");
		}
		sql +=" select h.DEPT_NAME deptName,h.WORK_NAME workName,h.GROUP_NAME groupName,h.month,TRUNCATE(SUM(h.money),1) gz from hm_salary h where h.month='"+month+"' \n";
		if(Utils.notEmpty(param.get("workCode"))){
			sql += " and h.WORK_CODE = '"+param.get("workCode")+"'";
		}
		sql += "   group by h.GROUP_CODE  ";
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
	public void datagrid(HmSalaryEntity hmSalaryEntity, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HmSalaryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, hmSalaryEntity, request.getParameterMap());
		Map p = ParameterUtil.getParamMaps(request.getParameterMap());

		try{
			//自定义追加查询条件
			if(Utils.notEmpty(p.get("xclbV"))){
				if("01".equals(p.get("xclbV"))){
					cq.eq("xclb",p.get("xclbV"));
				}else{
					cq.notEq("xclb","01");
				}
			}
			if(Utils.notEmpty(p.get("month"))){
				cq.eq("month",p.get("month"));
			}else{
				cq.eq("month","-1");
			}
			if(Utils.isEmpty((p.get("leaveT")))){
				cq.add(Restrictions.sqlRestriction("(({alias}.leave_type is null or {alias}.leave_type =''))"));
			}else{
				cq.eq("leaveType",String.valueOf(p.get("leaveT")));
			}
			if(Utils.notEmpty(p.get("levalStaff"))){
				cq.add(Restrictions.sqlRestriction("(FIND_IN_SET({alias}.real_name,(select GROUP_CONCAT(hl.real_name) from hm_leave_staff hl where hl.state=1)))"));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.hmBaseSalaryService.getDataGridReturn(cq, true);
		List<HmSalaryEntity> results = dataGrid.getResults();
		double otherBt=0,lb=0,jf=0,qtkk=0,baseHj = 0,yfHj = 0,ykHj = 0,money = 0;
		double d1 = 0,d2 = 0,d3 = 0,d4 = 0,d5 = 0,d6 = 0,d7 = 0,d8 = 0,d9 = 0,d10 = 0;
		double d11 = 0,d12 = 0,d13 = 0,d14 = 0,d15 = 0,d16 = 0,d17 = 0,d18 = 0,d19 = 0,d20 = 0;

		for(HmSalaryEntity he : results){
			baseHj += Double.parseDouble(he.getBaseHj());
			yfHj += Double.parseDouble(he.getYfHj());
			ykHj += Double.parseDouble(he.getYkHj());
			money += Double.parseDouble(he.getMoney());
			otherBt += Double.parseDouble(Utils.notEmpty(he.getOtherBt()) ? he.getOtherBt():"0");
			lb += Double.parseDouble(Utils.notEmpty(he.getLb()) ? he.getLb():"0");
			jf += Double.parseDouble(Utils.notEmpty(he.getJf()) ? he.getJf():"0");
			qtkk += Double.parseDouble(Utils.notEmpty(he.getQtkh()) ? he.getQtkh():"0");

			d1 += Double.parseDouble(Utils.notEmpty(he.getA01a02a01()) ? he.getA01a02a01():"0");
			d2 += Double.parseDouble(Utils.notEmpty(he.getA01a02a02()) ? he.getA01a02a02():"0");
			d3 += Double.parseDouble(Utils.notEmpty(he.getA01a02a03()) ? he.getA01a02a03():"0");
			d4 += Double.parseDouble(Utils.notEmpty(he.getA01a02a04()) ? he.getA01a02a04():"0");
			d5 += Double.parseDouble(Utils.notEmpty(he.getA01a02a05()) ? he.getA01a02a05():"0");
			d6 += Double.parseDouble(Utils.notEmpty(he.getA01a02a06()) ? he.getA01a02a06():"0");
			d7 += Double.parseDouble(Utils.notEmpty(he.getA01a02a07()) ? he.getA01a02a07():"0");
			d8 += Double.parseDouble(Utils.notEmpty(he.getA01a02a08()) ? he.getA01a02a08():"0");
			d9 += Double.parseDouble(Utils.notEmpty(he.getA01a02a09()) ? he.getA01a02a09():"0");
			d10 += Double.parseDouble(Utils.notEmpty(he.getA01a02a10()) ? he.getA01a02a10():"0");
			d11 += Double.parseDouble(Utils.notEmpty(he.getA01a02a11()) ? he.getA01a02a11():"0");
			d12 += Double.parseDouble(Utils.notEmpty(he.getA01a02a12()) ? he.getA01a02a12():"0");
			d13 += Double.parseDouble(Utils.notEmpty(he.getA01a02a13()) ? he.getA01a02a13():"0");
			d14 += Double.parseDouble(Utils.notEmpty(he.getA01a02a14()) ? he.getA01a02a14():"0");
			d15 += Double.parseDouble(Utils.notEmpty(he.getA01a02a15()) ? he.getA01a02a15():"0");
			d16 += Double.parseDouble(Utils.notEmpty(he.getA01a02a16()) ? he.getA01a02a16():"0");
			d17 += Double.parseDouble(Utils.notEmpty(he.getA01a02a17()) ? he.getA01a02a17():"0");
			d18 += Double.parseDouble(Utils.notEmpty(he.getA01a02a18()) ? he.getA01a02a18():"0");
			d19 += Double.parseDouble(Utils.notEmpty(he.getA01a02a19()) ? he.getA01a02a19():"0");
			d20 += Double.parseDouble(Utils.notEmpty(he.getA01a02a20()) ? he.getA01a02a20():"0");

		}
		dataGrid.setFooter("baseHj:" + baseHj+",yfHj:"+yfHj+",otherBt:" + otherBt+",lb:"+lb+",jf:" + jf+",qtkh:"+qtkk
							+",ykHj:"+ykHj+",money:"+money +",a01a02a01:"+d1+",a01a02a02:"+d2
				+",a01a02a03:"+d3+",a01a02a04:"+d4 +",a01a02a05:"+d5+",a01a02a06:"+d6
				+",a01a02a07:"+d7+",a01a02a08:"+d8 +",a01a02a09:"+d9+",a01a02a10:"+d10
				+",a01a02a11:"+d11+",a01a02a12:"+d12 +",a01a02a13:"+d13+",a01a02a14:"+d14
				+",a01a02a15:"+d15+",a01a02a16:"+d16 +",a01a02a17:"+d17+",a01a02a18:"+d18
				+",a01a02a19:"+d19+",a01a02a20:"+d20);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 生成工资表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HmSalaryEntity hmSalaryEntity, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "生成工资表成功";
		try{
			Map<String,String> param = ParameterUtil.getParamMaps(request.getParameterMap());
			String month = "";
			if(Utils.notEmpty(param.get("month"))){
				month = param.get("month");
			}else{
				month = DateUtil.format(new Date(),"yyyy-MM");
			}

			//查询已生成的月结工资数据
			List<HmSalaryEntity> salaryEntityList = systemService.findHql("from HmSalaryEntity where month=? and (leave_type is null or leave_type='') ",month);
			Map<String, HmSalaryEntity> salaryEntityMap = new HashMap<>();
			for(HmSalaryEntity salaryEntity : salaryEntityList){
				salaryEntityMap.put(salaryEntity.getWorkNo(),salaryEntity);
			}
			systemService.executeSql("delete from hm_salary where month=? and (leave_type is null or leave_type='') ",month);
			systemService.executeSql("delete from hm_check_t_pay where month=?",month);
			List<HmBaseSalaryEntity> baseSalaryEntityList = systemService.findHql("from HmBaseSalaryEntity ");
			List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
			/*Map<String, Object> mqj = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='jiangj' and t2.typecode='mq'");
			Map<String, Object> qqj = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='jiangj' and t2.typecode='qq'");*/

			Class c = Class.forName(HmSalaryEntity.class.getName());
			List<HmStaffEntity> staffEntityList = systemService.findHql("from HmStaffEntity where state=0 ");
			HmBaseSalaryEntity hmBaseSalaryEntity = null;
			//HmCheckTPayEntity hmCheckTPayEntity = null;
			HmCheckPayEntity hmCheckPayEntity = null;
			double yfMoney = 0,ykMoney =0,gz = 0,cdzt=0,zt=0,kg=0,qk=0,qjzt = 0;
			int day = 0,monthDays=0;

			//按工号保存满勤奖数据
			List<Map<String, Object>>  mqjList = systemService.findForJdbc("select m.work_no,ifnull(m.mqj,0) mqj,ifnull(m.qqj,0) qqj from hm_check_staff_mqj m");
			Map<String,Map<String, Object>> mqjMap = new HashMap<>();
			for(Map p : mqjList){
				mqjMap.put(p.get("work_no").toString(),p);
			}

			for(HmStaffEntity staffEntity : staffEntityList){
				hmSalaryEntity = new HmSalaryEntity();
				//hmCheckTPayEntity = new HmCheckTPayEntity();
				HmSalaryEntity salaryEntity = salaryEntityMap.get(staffEntity.getWorkNo());
				if(Utils.notEmpty(salaryEntity)){
					hmSalaryEntity.setOtherBt(salaryEntity.getOtherBt());
					hmSalaryEntity.setLb(salaryEntity.getLb());
					hmSalaryEntity.setJf(salaryEntity.getJf());
					hmSalaryEntity.setQtkh(salaryEntity.getQtkh());
				}
				hmSalaryEntity.setMonth(month);
				//hmCheckTPayEntity.setMonth(month);
				MyBeanUtils.copyBeanNotNull2Bean(staffEntity,hmSalaryEntity);
				//MyBeanUtils.copyBeanNotNull2Bean(staffEntity,hmCheckTPayEntity);

				String sql  = "select ifnull((select hw.work_time from hm_check_staff hc left join hm_work_class hw on hc.bc_name=hw.bc_name" +
						" 	where hc.work_no = h.WORK_NO limit 0,1),8) workTime,ifnull((select hc.holiday from hm_check_staff hc \n" +
						"	left join hm_work_class hw on hc.bc_name=hw.bc_name where hc.work_no = '"+staffEntity.getWorkNo()+"' limit 0,1),0) holiday," +
						"	sum(CASE h.TIME_TYPE WHEN 0 THEN ifnull((select hw.work_time from hm_check_staff hc \n" +
						"	left join hm_work_class hw on hc.bc_name=hw.bc_name where hc.work_no = h.WORK_NO limit 0,1),8)" +
						"	 WHEN 1 THEN h.long_time else 0 END) hours from hm_leval h where h.WORK_NO=? and h.leval_type='sj' and left(h.BEGIN_TIME,7)=?";
				Map level = systemService.findOneForJdbc(sql,hmSalaryEntity.getWorkNo(),month);

				sql  = "select count(0) yx from hm_leval h where h.WORK_NO=? and h.leval_type='tx' and left(h.BEGIN_TIME,7)=?";
				Map yxlevel = systemService.findOneForJdbc(sql,hmSalaryEntity.getWorkNo(),month);

				sql = "select \n" +
						"(select ifnull(sum(pay_money),0) from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.`month`=hcp.`month` and h1.pay_type=0) cd,\n" +
						"(select ifnull(sum(pay_money),0) from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.`month`=hcp.`month` and h1.pay_type=1) zt,\n" +
						"(select ifnull(count(0),0) from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.month=hcp.month and h1.pay_type=2 ";
				if(Utils.notEmpty(staffEntity.getRzDate())){
					if(staffEntity.getRzDate().substring(0,7).equals(month)){
						sql +="	and h1.check_time >= '"+staffEntity.getRzDate()+"'";
					}
				}
				sql +="	and h1.check_time not in (select left(h.BEGIN_TIME,10) from hm_leval h where h.work_no=hcp.work_no and h.TIME_TYPE=0)) kg,\n" +
						"(select ifnull(sum(pay_money),0)  from hm_check_pay h1 where h1.work_no=hcp.work_no and h1.`month`=hcp.`month` and h1.pay_type=3) qk\n" +
						" from hm_check_pay hcp where hcp.work_no=? and hcp.month=? limit 0,1";
				Map<String, Object> koufei = systemService.findOneForJdbc(sql,staffEntity.getWorkNo(),month);
				if(Utils.notEmpty(koufei)){
					//hmCheckTPayEntity.setCdMoney(koufei.get("cd").toString());
					zt = Double.parseDouble(koufei.get("zt").toString());
					cdzt = Double.parseDouble(koufei.get("cd").toString()) + Double.parseDouble(koufei.get("zt").toString());
					kg = Double.parseDouble(koufei.get("kg").toString());
					qk = Double.parseDouble(koufei.get("qk").toString());
				}

				/*sql = "select ifnull(sum(hp.pay_money),0) qjzt from hm_leval hl \n" +
						" left join hm_check_pay hp on hl.WORK_NO=hp.work_no where  hl.work_no=? and left(hl.BEGIN_TIME,7)=? and hl.TIME_TYPE=1\n" +
						" and hp.pay_type=1 and left(hp.clock_time,13)>= hl.BEGIN_TIME and left(hp.clock_time,13)<=hl.END_TIME";
				Map<String, Object> qjztmap = systemService.findOneForJdbc(sql,staffEntity.getWorkNo(),month);
				if(Utils.notEmpty(qjztmap)){
					qjzt = Double.parseDouble(qjztmap.get("qjzt").toString());
				}*/


				hmBaseSalaryEntity = systemService.findUniqueByProperty(HmBaseSalaryEntity.class,"realName",hmSalaryEntity.getRealName());
				if(Utils.notEmpty(hmBaseSalaryEntity)){
					MyBeanUtils.copyBeanNotNull2Bean(hmBaseSalaryEntity,hmSalaryEntity);
				}
				hmSalaryEntity.setId(null);

				yfMoney = 0;
				ykMoney =0;
				gz = 0;
				day = 0;
				for(Map category : categoryEntities){
					String m0 = "setA"+category.get("code").toString().substring(1);
					String m = "getA"+category.get("code").toString().substring(1);

					//if(!"01".equals(hmSalaryEntity.getXclb())){
						if("基本工资".equals(category.get("name"))){
							Method show = c.getMethod(m);
							Object object = show.invoke(hmSalaryEntity);
							double ys = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));

							//Map workPrice = systemService.findOneForJdbc("select ifnull(sum(hm.gz_hj),0) hj,ifnull(sum(hm.zcb),0) zcb,ifnull(sum(hm.gz_hj),0) jiab from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and hm.type=5",month,hmSalaryEntity.getWorkNo());
							Map workPrice = systemService.findOneForJdbc("select ifnull(sum(t.hj),0) hj from (select ifnull(sum(floor(hm.gz_hj)),0) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and hm.type=5 group by hm.kd_date) t",month,hmSalaryEntity.getRealName());
							Map zb = systemService.findOneForJdbc("select ifnull(sum(hm.zcb),0) zcb,ifnull(sum(hm.jiab),0) jiab from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and hm.type=5",month,hmSalaryEntity.getRealName());
							Map workPrice2 = systemService.findOneForJdbc("select ifnull(sum(t.hj),0) hj from (select floor(sum(hm.zshj)) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1) group by hm.kd_date) t ",month,hmSalaryEntity.getRealName());

							double money = Double.parseDouble(workPrice.get("hj").toString());
							if(Utils.notEmpty(workPrice2)){
								money += Double.parseDouble(workPrice2.get("hj").toString());
							}
							hmSalaryEntity.setZcb(zb.get("zcb").toString());
							hmSalaryEntity.setJiab(zb.get("jiab").toString());
							show = c.getMethod(m0,String.class);

							gz += money+ys;
							object = show.invoke(hmSalaryEntity,String.valueOf(money+ys));
						}
						if("岗位补贴".equals(category.get("name"))){
							Method show = c.getMethod(m);
							Object object = show.invoke(hmSalaryEntity);
							double ys = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));

							Map workPrice = systemService.findOneForJdbc("SELECT ifnull(sum(t.hj),0) hj from (select floor(sum(hm.gz_hj)) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.real_name=? and hm.type!=5 and hm.work_type in (0,1,2,3) group by hm.kd_date)  t",month,hmSalaryEntity.getRealName());
							show = c.getMethod(m0,String.class);

							gz += Double.parseDouble(Utils.notEmpty(workPrice.get("hj")) ? workPrice.get("hj").toString():"0")+ys;
							double gwgz = Double.parseDouble(Utils.notEmpty(workPrice.get("hj")) ? workPrice.get("hj").toString():"0")+ys;
							object = show.invoke(hmSalaryEntity,String.valueOf(gwgz));
						}

					if(category.get("column_type").equals("6")){
						if(Utils.notEmpty(category.get("code"))){
							Method show = c.getMethod(m);
							Object object = show.invoke(hmSalaryEntity);
							yfMoney += Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
						}
					}
					if(category.get("column_type").equals("7")){
						if(Utils.notEmpty(category.get("code"))){
							Method show = c.getMethod(m);
							Object object = show.invoke(hmSalaryEntity);
							double val = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
							if(val > 0){
								ykMoney += val;
							}else{
								ykMoney += -val;
							}
						}
					}
				}
				monthDays = DateUtil.getDays(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)));
				if(level.get("holiday").equals("zm")){
					day = DateUtil.getWeeks(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),0);
				}else if(level.get("holiday").equals("zr")){
					day = DateUtil.getWeeks(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),2);
				}else if(level.get("holiday").equals("zl")){
					day = DateUtil.getWeeks(Integer.parseInt(month.substring(0,4)),Integer.parseInt(month.substring(5,7)),1);
				}else{
					day = Integer.parseInt(Utils.notEmpty(staffEntity.getSleepDays()) ? staffEntity.getSleepDays():"0");
				}
				double hourSalary = gz/(Double.parseDouble(level.get("workTime").toString())*(monthDays-day));
				if(Utils.notEmpty(level.get("hours"))){
					hmSalaryEntity.setBsj(String.valueOf(hourSalary*Double.parseDouble(level.get("hours").toString())));
					//hmCheckTPayEntity.setQjTime(level.get("hours").toString());
					//hmCheckTPayEntity.setQjMoney(hmSalaryEntity.getBsj());
				}

				if(kg > 0 || qk >0){
					if(kg > 0 ){
						if(monthDays>0 && day >0 && kg>day){
							double daySalary = gz/(monthDays-day);
							//hmCheckTPayEntity.setKgDay(String.valueOf(kg));
							kg = kg*daySalary*3;
							//hmCheckTPayEntity.setKgMoney(String.valueOf(kg));
							systemService.executeSql("update hm_check_pay set pay_money=? where  work_no=?  and month=? and pay_type=2",daySalary,staffEntity.getWorkNo(),month);
						}
					}
				}else{
					Map mqj = mqjMap.get(hmSalaryEntity.getWorkNo());

					if(!"01".equals(hmSalaryEntity.getXclb())){
						if(Utils.notEmpty(mqj)){
							if("0".equals(yxlevel.get("yx").toString())){
								hmSalaryEntity.setFullHourMoney(mqj.get("mqj").toString());
							}else{
								hmSalaryEntity.setFullHourMoney(mqj.get("qqj").toString());
							}
						}else{
							Map cq = systemService.findOneForJdbc("select ifnull(count(1),0) days from hm_work_price p where  work_no=? and left(p.kd_date,7)=? ",hmSalaryEntity.getWorkName(),month);
							if(cq.get("days").equals(monthDays-day)){
								hmSalaryEntity.setFullHourMoney(mqj.get("mqj").toString());
							}else if(Integer.valueOf(cq.get("days").toString()) > 0 && cq.get("days").equals(monthDays-day)){
								hmSalaryEntity.setFullHourMoney(mqj.get("qqj").toString());
							}
						}
					}else{
						if(Utils.notEmpty(mqj)){
							hmSalaryEntity.setFullHourMoney(mqj.get("qqj").toString());
						}
					}


				}

				if(Utils.notEmpty(hmSalaryEntity.getOtherBt())){
					yfMoney += Double.parseDouble(hmSalaryEntity.getOtherBt());
				}
				if(Utils.notEmpty(hmSalaryEntity.getLb())){
					double val = Double.parseDouble(hmSalaryEntity.getLb());
					if(val > 0){
						ykMoney += val;
					}else{
						ykMoney += -val;
					}
				}
				if(Utils.notEmpty(hmSalaryEntity.getJf())){
					double val = Double.parseDouble(hmSalaryEntity.getJf());
					if(val > 0){
						ykMoney += val;
					}else{
						ykMoney += -val;
					}
				}
				if(Utils.notEmpty(hmSalaryEntity.getQtkh())){
					double val = Double.parseDouble(hmSalaryEntity.getQtkh());
					if(val > 0){
						ykMoney += val;
					}else{
						ykMoney += -val;
					}
				}
				/*hmCheckTPayEntity.setQkMoney(String.valueOf(qk));
				hmCheckTPayEntity.setZdMoney(String.valueOf(zt-qjzt));

				hmSalaryEntity.setCdzt(String.valueOf(cdzt-qjzt));
				hmSalaryEntity.setKgwdk(String.valueOf(kg+qk));
				hmSalaryEntity.setBaseHj(String.valueOf(yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)));
				hmSalaryEntity.setYfHj(String.valueOf(yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)
						-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0)-cdzt+qjzt-kg-qk));
				hmSalaryEntity.setYkHj(String.valueOf(ykMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)));
				hmSalaryEntity.setMoney(String.valueOf((yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)
						-ykMoney-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0))-cdzt+qjzt-kg-qk));*/

				hmSalaryEntity.setBaseHj(String.valueOf(yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)));
				hmSalaryEntity.setYfHj(String.valueOf(yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)
						-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0)));
				hmSalaryEntity.setYkHj(String.valueOf(ykMoney));
				hmSalaryEntity.setMoney(String.valueOf((yfMoney+(Utils.notEmpty(hmSalaryEntity.getFullHourMoney()) ? Double.parseDouble(hmSalaryEntity.getFullHourMoney()):0)
						-ykMoney-(Utils.notEmpty(hmSalaryEntity.getBsj()) ? Double.parseDouble(hmSalaryEntity.getBsj()):0))));
				systemService.save(hmSalaryEntity);
				//systemService.save(hmCheckTPayEntity);

			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "生成工资表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 工资表编辑页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(HmSalaryEntity hmSalary, HttpServletRequest req) {
		List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
		req.setAttribute("categoryEntities",categoryEntities);
		if (StringUtil.isNotEmpty(hmSalary.getId())) {
			hmSalary = hmBaseSalaryService.getEntity(HmSalaryEntity.class, hmSalary.getId());
			req.setAttribute("hmSalaryPage", hmSalary);
		}
		return new ModelAndView("com/hm/rsgl/salary/hmSalary-update");
	}

	/**
	 * 更新工资表
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(HmSalaryEntity hmSalary, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "工资表更新成功";
		HmSalaryEntity t = hmBaseSalaryService.get(HmSalaryEntity.class, hmSalary.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(hmSalary, t);
			hmBaseSalaryService.saveOrUpdate(t);

			double yfMoney = 0,ykMoney =0;
			Class c = Class.forName("com.hm.rsgl.salary.entity.HmSalaryEntity");
			List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
			for(Map category : categoryEntities){
				String m = "getA"+category.get("code").toString().substring(1);
				Method show = c.getMethod(m);

				if(category.get("column_type").equals("6")){
					if(Utils.notEmpty(category.get("code"))){
						Object object = show.invoke(t);
						yfMoney += Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
					}
				}
				if(category.get("column_type").equals("7")){
					if(Utils.notEmpty(category.get("code"))){
						Object object = show.invoke(t);
						double val = Double.parseDouble(String.valueOf(Utils.notEmpty(object) ? object:"0"));
						if(val > 0){
							ykMoney += val;
						}else{
							ykMoney += -val;
						}
					}
				}
			}
			if(Utils.notEmpty(t.getOtherBt())){
				yfMoney += Double.parseDouble(t.getOtherBt());
			}
			if(Utils.notEmpty(t.getLb())){
				double val = Double.parseDouble(t.getLb());
				if(val > 0){
					ykMoney += val;
				}else{
					ykMoney += -val;
				}
			}
			if(Utils.notEmpty(t.getJf())){
				double val = Double.parseDouble(t.getJf());
				if(val > 0){
					ykMoney += val;
				}else{
					ykMoney += -val;
				}
			}
			if(Utils.notEmpty(t.getQtkh())){
				double val = Double.parseDouble(t.getQtkh());
				if(val > 0){
					ykMoney += val;
				}else{
					ykMoney += -val;
				}
			}
			systemService.executeSql("update hm_salary set base_hj=?,yf_hj=?,yk_hj=?,money=? where id=?",yfMoney,yfMoney,ykMoney,(yfMoney-ykMoney),t.getId());

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "工资表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
