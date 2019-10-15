package com.hm.kq.checktpay.entity;

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
 * @Description: 实际扣费表
 * @author onlineGenerator
 * @date 2019-08-19 16:39:52
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_check_t_pay", schema = "")
@SuppressWarnings("serial")
public class HmCheckTPayEntity implements java.io.Serializable {
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
	@Excel(name="月份",width=15)
	private String month;
	/**工号*/
	@Excel(name="工号",width=15)
	private String workNo;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private String realName;
	/**部门代码*/
	@Excel(name="部门代码",width=15)
	private String deptCode;
	/**部门名称*/
	@Excel(name="部门名称",width=15)
	private String deptName;
	/**旷工天数*/
	@Excel(name="旷工天数",width=15)
	private String kgDay;
	/**旷工金额*/
	@Excel(name="旷工金额",width=15)
	private String kgMoney;
	/**迟到金额*/
	@Excel(name="迟到金额",width=15)
	private String cdMoney;
	/**早退金额*/
	@Excel(name="早退金额",width=15)
	private String zdMoney;
	/**缺卡金额*/
	@Excel(name="缺卡金额",width=15)
	private String qkMoney;
	/**请假时长*/
	@Excel(name="请假时长",width=15)
	private String qjTime;
	/**请假金额*/
	@Excel(name="请假金额",width=15)
	private String qjMoney;
	
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
	@Column(name ="month",nullable=true,length=32)
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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
	 *@return: java.lang.String  部门名称
	 */

	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门名称
	 */
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  旷工天数
	 */

	@Column(name ="KG_DAY",nullable=true,length=32)
	public String getKgDay(){
		return this.kgDay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  旷工天数
	 */
	public void setKgDay(String kgDay){
		this.kgDay = kgDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  旷工金额
	 */

	@Column(name ="KG_MONEY",nullable=true,length=32)
	public String getKgMoney(){
		return this.kgMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  旷工金额
	 */
	public void setKgMoney(String kgMoney){
		this.kgMoney = kgMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  迟到金额
	 */

	@Column(name ="CD_MONEY",nullable=true,length=32)
	public String getCdMoney(){
		return this.cdMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  迟到金额
	 */
	public void setCdMoney(String cdMoney){
		this.cdMoney = cdMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  早退金额
	 */

	@Column(name ="ZD_MONEY",nullable=true,length=32)
	public String getZdMoney(){
		return this.zdMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  早退金额
	 */
	public void setZdMoney(String zdMoney){
		this.zdMoney = zdMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  缺卡金额
	 */

	@Column(name ="QK_MONEY",nullable=true,length=32)
	public String getQkMoney(){
		return this.qkMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  缺卡金额
	 */
	public void setQkMoney(String qkMoney){
		this.qkMoney = qkMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假时长
	 */

	@Column(name ="QJ_TIME",nullable=true,length=32)
	public String getQjTime(){
		return this.qjTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假时长
	 */
	public void setQjTime(String qjTime){
		this.qjTime = qjTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请假金额
	 */

	@Column(name ="QJ_MONEY",nullable=true,length=32)
	public String getQjMoney(){
		return this.qjMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请假金额
	 */
	public void setQjMoney(String qjMoney){
		this.qjMoney = qjMoney;
	}
}
