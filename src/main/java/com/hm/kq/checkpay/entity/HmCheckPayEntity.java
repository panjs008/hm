package com.hm.kq.checkpay.entity;

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
 * @Description: 考勤扣款表
 * @author onlineGenerator
 * @date 2019-08-12 10:54:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_check_pay", schema = "")
@SuppressWarnings("serial")
public class HmCheckPayEntity implements java.io.Serializable {
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
	/**考勤时间*/
	@Excel(name="考勤时间",width=15)
	private String checkTime;
	/**打卡时间*/
	@Excel(name="打卡时间",width=15)
	private String clockTime;
	/**迟到时间*/
	@Excel(name="迟到时间",width=15)
	private String longTime;
	/**扣款比例*/
	@Excel(name="扣款比例",width=15)
	private String precent;
	/**扣款金额*/
	@Excel(name="扣款金额",width=15)
	private Double payMoney;
	/**扣款类型*/
	@Excel(name="扣款类型",width=15)
	private String payType;
	/**月份*/
	@Excel(name="月份",width=15)
	private String month;
	
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
	 *@return: java.lang.String  考勤时间
	 */

	@Column(name ="CHECK_TIME",nullable=true,length=56)
	public String getCheckTime(){
		return this.checkTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  考勤时间
	 */
	public void setCheckTime(String checkTime){
		this.checkTime = checkTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打卡时间
	 */

	@Column(name ="CLOCK_TIME",nullable=true,length=32)
	public String getClockTime(){
		return this.clockTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打卡时间
	 */
	public void setClockTime(String clockTime){
		this.clockTime = clockTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  迟到时间
	 */

	@Column(name ="LONG_TIME",nullable=true,length=32)
	public String getLongTime(){
		return this.longTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  迟到时间
	 */
	public void setLongTime(String longTime){
		this.longTime = longTime;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  扣款金额
	 */

	@Column(name ="PAY_MONEY",nullable=true,scale=2,length=32)
	public Double getPayMoney(){
		return this.payMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  扣款金额
	 */
	public void setPayMoney(Double payMoney){
		this.payMoney = payMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扣款类型
	 */

	@Column(name ="PAY_TYPE",nullable=true,length=6)
	public String getPayType(){
		return this.payType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扣款类型
	 */
	public void setPayType(String payType){
		this.payType = payType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月份
	 */

	@Column(name ="MONTH",nullable=true,length=12)
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

	public String getPrecent() {
		return precent;
	}

	public void setPrecent(String precent) {
		this.precent = precent;
	}
}
