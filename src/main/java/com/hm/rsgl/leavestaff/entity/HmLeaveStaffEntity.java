package com.hm.rsgl.leavestaff.entity;

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
 * @Description: 离职人员信息表
 * @author onlineGenerator
 * @date 2019-07-21 22:00:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_leave_staff", schema = "")
@SuppressWarnings("serial")
public class HmLeaveStaffEntity implements java.io.Serializable {
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
	/**职务*/
	@Excel(name="职务",width=15)
	private String job;
	/**级别*/
	@Excel(name="级别",width=15)
	private String jb;
	/**薪酬类别*/
	@Excel(name="薪酬类别",width=15)
	private String xclb;
	/**性别*/
	@Excel(name="性别",width=15)
	private String sex;
	/**员工类别*/
	@Excel(name="员工类别",width=15)
	private String yglb;
	/**带工*/
	@Excel(name="带工",width=15)
	private String taker;
	/**固定临时*/
	@Excel(name="固定临时",width=15)
	private String workType;
	/**入职日期*/
	@Excel(name="入职日期",width=15)
	private String rzDate;
	/**转正日期*/
	@Excel(name="转正日期",width=15)
	private String zzDate;
	/**手机*/
	@Excel(name="手机",width=15)
	private String telphone;
	/**身份证号码*/
	@Excel(name="身份证号码",width=15)
	private String idCard;
	/**出生日期*/
	@Excel(name="出生日期",width=15)
	private String birthDay;
	/**工龄*/
	@Excel(name="工龄",width=15)
	private String workYear;
	/**月休天数*/
	@Excel(name="月休天数",width=15)
	private String sleepDays;
	/**民族*/
	@Excel(name="民族",width=15)
	private String mz;
	/**学历*/
	@Excel(name="学历",width=15)
	private String xueli;
	/**家庭电话*/
	@Excel(name="家庭电话",width=15)
	private String housePhone;
	/**紧急联系人电话*/
	@Excel(name="紧急联系人电话",width=15)
	private String jjlxr;
	/**转正提醒*/
	@Excel(name="转正提醒",width=15)
	private String zzDays;
	/**工号*/
	@Excel(name="工号",width=15)
	private String workNo;
	/**政治面貌*/
	@Excel(name="政治面貌",width=15)
	private String zzmm;

	@Excel(name="是否内宿",width=15)
	private String isNs;
	@Excel(name="户籍所在地",width=15)
	private String hjszd;
	@Excel(name="电子邮件",width=15)
	private String email;
	/**申请时间*/
	@Excel(name="申请时间",width=15)
	private String applyDate;
	/**离职时间*/
	@Excel(name="离职时间",width=15)
	private String leaveDate;
	/**离职类别*/
	@Excel(name="离职类别",width=15)
	private String leaveType;
	/**结算日期*/
	@Excel(name="结算日期",width=15)
	private String annualDate;
	private String state;
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
	 *@return: java.lang.String  性别
	 */

	@Column(name ="SEX",nullable=true,length=32)
	public String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(String sex){
		this.sex = sex;
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
	 *@return: java.lang.String  带工
	 */

	@Column(name ="TAKER",nullable=true,length=32)
	public String getTaker(){
		return this.taker;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  带工
	 */
	public void setTaker(String taker){
		this.taker = taker;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固定临时
	 */

	@Column(name ="WORK_TYPE",nullable=true,length=32)
	public String getWorkType(){
		return this.workType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定临时
	 */
	public void setWorkType(String workType){
		this.workType = workType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入职日期
	 */

	@Column(name ="RZ_DATE",nullable=true,length=32)
	public String getRzDate(){
		return this.rzDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入职日期
	 */
	public void setRzDate(String rzDate){
		this.rzDate = rzDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  转正日期
	 */

	@Column(name ="ZZ_DATE",nullable=true,length=32)
	public String getZzDate(){
		return this.zzDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  转正日期
	 */
	public void setZzDate(String zzDate){
		this.zzDate = zzDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机
	 */

	@Column(name ="TELPHONE",nullable=true,length=32)
	public String getTelphone(){
		return this.telphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机
	 */
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号码
	 */

	@Column(name ="ID_CARD",nullable=true,length=32)
	public String getIdCard(){
		return this.idCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号码
	 */
	public void setIdCard(String idCard){
		this.idCard = idCard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出生日期
	 */

	@Column(name ="BIRTH_DAY",nullable=true,length=32)
	public String getBirthDay(){
		return this.birthDay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出生日期
	 */
	public void setBirthDay(String birthDay){
		this.birthDay = birthDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工龄
	 */

	@Column(name ="WORK_YEAR",nullable=true,length=32)
	public String getWorkYear(){
		return this.workYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工龄
	 */
	public void setWorkYear(String workYear){
		this.workYear = workYear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月休天数
	 */

	@Column(name ="SLEEP_DAYS",nullable=true,length=32)
	public String getSleepDays(){
		return this.sleepDays;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月休天数
	 */
	public void setSleepDays(String sleepDays){
		this.sleepDays = sleepDays;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  民族
	 */

	@Column(name ="MZ",nullable=true,length=32)
	public String getMz(){
		return this.mz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  民族
	 */
	public void setMz(String mz){
		this.mz = mz;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学历
	 */

	@Column(name ="XUELI",nullable=true,length=32)
	public String getXueli(){
		return this.xueli;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学历
	 */
	public void setXueli(String xueli){
		this.xueli = xueli;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家庭电话
	 */

	@Column(name ="HOUSE_PHONE",nullable=true,length=32)
	public String getHousePhone(){
		return this.housePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家庭电话
	 */
	public void setHousePhone(String housePhone){
		this.housePhone = housePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  紧急联系人电话
	 */

	@Column(name ="JJLXR",nullable=true,length=32)
	public String getJjlxr(){
		return this.jjlxr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  紧急联系人电话
	 */
	public void setJjlxr(String jjlxr){
		this.jjlxr = jjlxr;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  转正提醒
	 */

	@Column(name ="ZZ_DAYS",nullable=true,length=32)
	public String getZzDays(){
		return this.zzDays;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  转正提醒
	 */
	public void setZzDays(String zzDays){
		this.zzDays = zzDays;
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
	 *@return: java.lang.String  政治面貌
	 */

	@Column(name ="ZZMM",nullable=true,length=32)
	public String getZzmm(){
		return this.zzmm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  政治面貌
	 */
	public void setZzmm(String zzmm){
		this.zzmm = zzmm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请时间
	 */

	@Column(name ="APPLY_DATE",nullable=true,length=32)
	public String getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请时间
	 */
	public void setApplyDate(String applyDate){
		this.applyDate = applyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职时间
	 */

	@Column(name ="LEAVE_DATE",nullable=true,length=32)
	public String getLeaveDate(){
		return this.leaveDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职时间
	 */
	public void setLeaveDate(String leaveDate){
		this.leaveDate = leaveDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职类别
	 */

	@Column(name ="LEAVE_TYPE",nullable=true,length=32)
	public String getLeaveType(){
		return this.leaveType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职类别
	 */
	public void setLeaveType(String leaveType){
		this.leaveType = leaveType;
	}

	@Column(name ="is_ns",nullable=true,length=50)
	public String getIsNs() {
		return isNs;
	}

	public void setIsNs(String isNs) {
		this.isNs = isNs;
	}

	@Column(name ="hjszd",nullable=true,length=50)
	public String getHjszd() {
		return hjszd;
	}

	public void setHjszd(String hjszd) {
		this.hjszd = hjszd;
	}

	@Column(name ="email",nullable=true,length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name ="annual_date",nullable=true,length=50)
	public String getAnnualDate() {
		return annualDate;
	}

	public void setAnnualDate(String annualDate) {
		this.annualDate = annualDate;
	}

	@Column(name ="state",nullable=true,length=50)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
