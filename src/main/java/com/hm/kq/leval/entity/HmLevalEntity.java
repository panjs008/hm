package com.hm.kq.leval.entity;

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
 * @Description: 请假单
 * @author onlineGenerator
 * @date 2019-08-01 23:07:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_leval", schema = "")
@SuppressWarnings("serial")
public class HmLevalEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String realName;
	/**工号*/
	@Excel(name="工号",width=15)
	private java.lang.String workNo;
	/**部门*/
	@Excel(name="部门",width=15)
	private java.lang.String deptName;
	/**申请日期*/
	@Excel(name="申请日期",width=15)
	private java.lang.String applyDate;
	private java.lang.String deptCode;
	/**请假类型*/
	@Excel(name="请假类型",width=15)
	private java.lang.String levalType;
	/**时间类别*/
	@Excel(name="时间类别",width=15)
	private java.lang.String timeType;
	@Excel(name="时长",width=15)
	private String longTime;
	/**开始时间*/
	@Excel(name="开始时间",width=15)
	private java.lang.String beginTime;
	/**结束时间*/
	@Excel(name="结束时间",width=15)
	private java.lang.String endTime;
	/**事由*/
	@Excel(name="事由",width=15)
	private java.lang.String reason;

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="REAL_NAME",nullable=true,length=32)
	public java.lang.String getRealName(){
		return this.realName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setRealName(java.lang.String realName){
		this.realName = realName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工号
	 */

	@Column(name ="WORK_NO",nullable=true,length=32)
	public java.lang.String getWorkNo(){
		return this.workNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工号
	 */
	public void setWorkNo(java.lang.String workNo){
		this.workNo = workNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */

	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public java.lang.String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门代码
	 */

	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门代码
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假类型
	 */

	@Column(name ="LEVAL_TYPE",nullable=true,length=32)
	public java.lang.String getLevalType(){
		return this.levalType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假类型
	 */
	public void setLevalType(java.lang.String levalType){
		this.levalType = levalType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时间类别
	 */

	@Column(name ="TIME_TYPE",nullable=true,length=32)
	public java.lang.String getTimeType(){
		return this.timeType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时间类别
	 */
	public void setTimeType(java.lang.String timeType){
		this.timeType = timeType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开始时间
	 */

	@Column(name ="BEGIN_TIME",nullable=true,length=32)
	public java.lang.String getBeginTime(){
		return this.beginTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开始时间
	 */
	public void setBeginTime(java.lang.String beginTime){
		this.beginTime = beginTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结束时间
	 */

	@Column(name ="END_TIME",nullable=true,length=32)
	public java.lang.String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结束时间
	 */
	public void setEndTime(java.lang.String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  事由
	 */

	@Column(name ="REASON",nullable=true,length=256)
	public java.lang.String getReason(){
		return this.reason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  事由
	 */
	public void setReason(java.lang.String reason){
		this.reason = reason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请日期
	 */

	@Column(name ="APPLY_DATE",nullable=true,length=32)
	public java.lang.String getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请日期
	 */
	public void setApplyDate(java.lang.String applyDate){
		this.applyDate = applyDate;
	}

	@Column(name ="long_time",nullable=true,length=32)
	public String getLongTime() {
		return longTime;
	}

	public void setLongTime(String longTime) {
		this.longTime = longTime;
	}
}
