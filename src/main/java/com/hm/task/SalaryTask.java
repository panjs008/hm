package com.hm.task;

import com.emk.util.DateUtil;
import com.emk.util.ParameterUtil;
import com.emk.util.Utils;
import com.hm.kq.checkpay.entity.HmCheckPayEntity;
import com.hm.rsgl.basesalary.entity.HmBaseSalaryEntity;
import com.hm.rsgl.salary.entity.HmSalaryEntity;
import com.hm.rsgl.staff.entity.HmStaffEntity;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.service.TSSmsServiceI;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @ClassName:salaryTask 月结工资表生成任务类
 * @Description: TODO
 * @author
 * @date
 *
 */

@Service("salaryTask")
public class SalaryTask implements Job{
	@Autowired
	private SystemService systemService;

	public void run() {
		long start = System.currentTimeMillis();
		org.jeecgframework.core.util.LogUtil.info("===================月结工资生成任务开始===================");
		try{
			String month = DateUtil.format(new Date(),"yyyy-MM");
			systemService.executeSql("delete from hm_salary where month=? and (leave_type is null or leave_type='') ",month);
			systemService.executeSql("delete from hm_check_t_pay where month=?",month);
			List<HmBaseSalaryEntity> baseSalaryEntityList = systemService.findHql("from HmBaseSalaryEntity ");
			List<Map<String, Object>> categoryEntities = this.systemService.findForJdbc("select lower(code) code,queryed,name,lower(PARENT_CODE) parentCode,required,column_type from hm_category where PARENT_CODE='A01A02' order by order_num asc");
			/*Map<String, Object> mqj = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='jiangj' and t2.typecode='mq'");
			Map<String, Object> qqj = systemService.findOneForJdbc("select remark,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='jiangj' and t2.typecode='qq'");*/

			Class c = Class.forName(HmSalaryEntity.class.getName());
			List<HmStaffEntity> staffEntityList = systemService.findHql("from HmStaffEntity where state=0");
			HmBaseSalaryEntity hmBaseSalaryEntity = null;
			HmSalaryEntity hmSalaryEntity = null;
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
						Map workPrice = systemService.findOneForJdbc("select ifnull(sum(t.hj),0) hj from (select ifnull(sum(floor(hm.gz_hj)),0) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and hm.type=5 group by hm.kd_date) t",month,hmSalaryEntity.getWorkNo());
						Map zb = systemService.findOneForJdbc("select ifnull(sum(hm.zcb),0) zcb,ifnull(sum(hm.jiab),0) jiab from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and hm.type=5",month,hmSalaryEntity.getWorkNo());
						Map workPrice2 = systemService.findOneForJdbc("select ifnull(sum(t.hj),0) hj from (select floor(sum(hm.zshj)) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and (hm.order_id is not null and hm.order_id!='' and hm.work_type=1) group by hm.kd_date) t ",month,hmSalaryEntity.getWorkNo());

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

						Map workPrice = systemService.findOneForJdbc("SELECT ifnull(sum(t.hj),0) hj from (select floor(sum(hm.gz_hj)) hj from hm_work_price hm where left(hm.kd_date,7)=? and hm.work_no=? and hm.type!=5 and hm.work_type in (0,1,2,3) group by hm.kd_date)  t",month,hmSalaryEntity.getWorkNo());
						show = c.getMethod(m0,String.class);

						gz += Double.parseDouble(Utils.notEmpty(workPrice.get("hj")) ? workPrice.get("hj").toString():"0")+ys;
						double gwgz = Double.parseDouble(Utils.notEmpty(workPrice.get("hj")) ? workPrice.get("hj").toString():"0")+ys;
						object = show.invoke(hmSalaryEntity,String.valueOf(gwgz));
					}
					//}else{
						/*if("基本工资".equals(category.get("name"))){

						}
						if("岗位补贴".equals(category.get("name"))){

						}*/
					//}
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

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		org.jeecgframework.core.util.LogUtil.info("===================月结工资生成任务结束===================");
		long end = System.currentTimeMillis();
		long times = end - start;
		org.jeecgframework.core.util.LogUtil.info("总耗时"+times+"毫秒");
	}

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		run();
	}
}
