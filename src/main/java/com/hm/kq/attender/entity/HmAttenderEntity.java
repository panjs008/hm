package com.hm.kq.attender.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 出勤表
 * @author onlineGenerator
 * @date 2019-10-13 14:17:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_attender", schema = "")
@SuppressWarnings("serial")
public class HmAttenderEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**所属部门*/
	private String sysOrgCode;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private String realName;
	/**工号*/
	@Excel(name="工号",width=15)
	private String workNo;
	/**部门*/
	@Excel(name="部门",width=15)
	private String deptName;
	/**部门代码*/
	@Excel(name="部门代码",width=15)
	private String deptCode;
	/**车间*/
	@Excel(name="车间",width=15)
	private String workName;
	/**车间代码*/
	@Excel(name="车间代码",width=15)
	private String workCode;
	/**班组*/
	@Excel(name="班组",width=15)
	private String groupName;
	/**班组代码*/
	@Excel(name="班组代码",width=15)
	private String groupCode;
	/**薪酬类别*/
	@Excel(name="薪酬类别",width=15)
	private String xclb;
	/**职务*/
	@Excel(name="职务",width=15)
	private String job;
	/**级别*/
	@Excel(name="级别",width=15)
	private String jb;
	/**员工类别*/
	@Excel(name="员工类别",width=15)
	private String yglb;
	/**出勤天数*/
	@Excel(name="出勤天数",width=15)
	private String cqDay;
	/**请假*/
	@Excel(name="请假",width=15)
	private String qjMoney;
	private String hourSalary;
	private String hours;
	/**迟到*/
	@Excel(name="迟到",width=15)
	private String cdMoney;
	/**旷工*/
	@Excel(name="旷工",width=15)
	private String kgMoney;
	/**出勤奖*/
	@Excel(name="出勤奖",width=15)
	private String mqj;
	/**月份*/
	@Excel(name="月份",width=15)
	private String month;
	private String sex;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="REAL_NAME",nullable=true,length=32)
	public String getRealName(){
		return this.realName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setRealName(String realName){
		this.realName = realName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工号
	 */

	@Column(name ="WORK_NO",nullable=true,length=32)
	public String getWorkNo(){
		return this.workNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工号
	 */
	public void setWorkNo(String workNo){
		this.workNo = workNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */

	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门代码
	 */

	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门代码
	 */
	public void setDeptCode(String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车间
	 */

	@Column(name ="WORK_NAME",nullable=true,length=32)
	public String getWorkName(){
		return this.workName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车间
	 */
	public void setWorkName(String workName){
		this.workName = workName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  车间代码
	 */

	@Column(name ="WORK_CODE",nullable=true,length=32)
	public String getWorkCode(){
		return this.workCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  车间代码
	 */
	public void setWorkCode(String workCode){
		this.workCode = workCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  班组
	 */

	@Column(name ="GROUP_NAME",nullable=true,length=32)
	public String getGroupName(){
		return this.groupName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班组
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  班组代码
	 */

	@Column(name ="GROUP_CODE",nullable=true,length=32)
	public String getGroupCode(){
		return this.groupCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班组代码
	 */
	public void setGroupCode(String groupCode){
		this.groupCode = groupCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  薪酬类别
	 */

	@Column(name ="XCLB",nullable=true,length=32)
	public String getXclb(){
		return this.xclb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  薪酬类别
	 */
	public void setXclb(String xclb){
		this.xclb = xclb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务
	 */

	@Column(name ="JOB",nullable=true,length=32)
	public String getJob(){
		return this.job;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务
	 */
	public void setJob(String job){
		this.job = job;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  级别
	 */

	@Column(name ="JB",nullable=true,length=32)
	public String getJb(){
		return this.jb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  级别
	 */
	public void setJb(String jb){
		this.jb = jb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  员工类别
	 */

	@Column(name ="YGLB",nullable=true,length=32)
	public String getYglb(){
		return this.yglb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工类别
	 */
	public void setYglb(String yglb){
		this.yglb = yglb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出勤天数
	 */

	@Column(name ="CQ_DAY",nullable=true,length=32)
	public String getCqDay(){
		return this.cqDay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出勤天数
	 */
	public void setCqDay(String cqDay){
		this.cqDay = cqDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假
	 */

	@Column(name ="QJ_MONEY",nullable=true,length=32)
	public String getQjMoney(){
		return this.qjMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假
	 */
	public void setQjMoney(String qjMoney){
		this.qjMoney = qjMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  迟到
	 */

	@Column(name ="CD_MONEY",nullable=true,length=32)
	public String getCdMoney(){
		return this.cdMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  迟到
	 */
	public void setCdMoney(String cdMoney){
		this.cdMoney = cdMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  旷工
	 */

	@Column(name ="KG_MONEY",nullable=true,length=32)
	public String getKgMoney(){
		return this.kgMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  旷工
	 */
	public void setKgMoney(String kgMoney){
		this.kgMoney = kgMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出勤奖
	 */

	@Column(name ="MQJ",nullable=true,length=32)
	public String getMqj(){
		return this.mqj;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出勤奖
	 */
	public void setMqj(String mqj){
		this.mqj = mqj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月份
	 */

	@Column(name ="MONTH",nullable=true,length=32)
	public String getMonth(){
		return this.month;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月份
	 */
	public void setMonth(String month){
		this.month = month;
	}

	@Column(name ="hour_salary",nullable=true,length=32)
	public String getHourSalary() {
		return hourSalary;
	}

	public void setHourSalary(String hourSalary) {
		this.hourSalary = hourSalary;
	}

	@Column(name ="hours",nullable=true,length=32)
	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	@Column(name ="sex",nullable=true,length=32)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
}
