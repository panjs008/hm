package com.hm.kq.workclassdetail.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import com.hm.kq.workclass.entity.HmWorkClassEntity;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 班次明细表
 * @author onlineGenerator
 * @date 2019-08-12 11:43:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_work_class_detail", schema = "")
@SuppressWarnings("serial")
public class HmWorkClassDetailEntity implements java.io.Serializable {
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
	/**班次ID*/
	@Excel(name="班次ID",width=15)
	private String workClassId;
	/**时间*/
	@Excel(name="时间",width=15)
	private String workTime;
	/**开始*/
	@Excel(name="开始",width=15)
	private String stTime;
	/**结束*/
	@Excel(name="结束",width=15)
	private String edTime;
	/**迟到早退*/
	@Excel(name="迟到早退",width=15)
	private String late;
	/**上班时间*/
	@Excel(name="上班时间",width=15)
	private String workSTime;
	/**下班时间*/
	@Excel(name="下班时间",width=15)
	private String workETime;
	/**时长*/
	@Excel(name="时长",width=15)
	private String longTime;
	/**内容*/
	@Excel(name="内容",width=15)
	private String workContent;
	/**打卡类型*/
	@Excel(name="打卡类型",width=15)
	private String workType;
	/**状态*/
	@Excel(name="状态",width=15)
	private String state;

	private HmWorkClassEntity workClassEntity;

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumns({
			@JoinColumn(name = "work_class_id",nullable = false, insertable = false, updatable = false)
	})
	public HmWorkClassEntity getWorkClassEntity() {
		return workClassEntity;
	}
	public void setWorkClassEntity(HmWorkClassEntity workClassEntity) {
		this.workClassEntity = workClassEntity;
	}


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
	 *@return: java.lang.String  班次ID
	 */

	@Column(name ="WORK_CLASS_ID",nullable=true,length=32)
	public String getWorkClassId(){
		return this.workClassId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班次ID
	 */
	public void setWorkClassId(String workClassId){
		this.workClassId = workClassId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时间
	 */

	@Column(name ="WORK_TIME",nullable=true,length=32)
	public String getWorkTime(){
		return this.workTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时间
	 */
	public void setWorkTime(String workTime){
		this.workTime = workTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开始
	 */

	@Column(name ="ST_TIME",nullable=true,length=32)
	public String getStTime(){
		return this.stTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开始
	 */
	public void setStTime(String stTime){
		this.stTime = stTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结束
	 */

	@Column(name ="ED_TIME",nullable=true,length=32)
	public String getEdTime(){
		return this.edTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结束
	 */
	public void setEdTime(String edTime){
		this.edTime = edTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  迟到早退
	 */

	@Column(name ="LATE",nullable=true,length=32)
	public String getLate(){
		return this.late;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  迟到早退
	 */
	public void setLate(String late){
		this.late = late;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上班时间
	 */

	@Column(name ="WORK_S_TIME",nullable=true,length=32)
	public String getWorkSTime(){
		return this.workSTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上班时间
	 */
	public void setWorkSTime(String workSTime){
		this.workSTime = workSTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下班时间
	 */

	@Column(name ="WORK_E_TIME",nullable=true,length=32)
	public String getWorkETime(){
		return this.workETime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下班时间
	 */
	public void setWorkETime(String workETime){
		this.workETime = workETime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时长
	 */

	@Column(name ="LONG_TIME",nullable=true,length=32)
	public String getLongTime(){
		return this.longTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时长
	 */
	public void setLongTime(String longTime){
		this.longTime = longTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内容
	 */

	@Column(name ="WORK_CONTENT",nullable=true,length=32)
	public String getWorkContent(){
		return this.workContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内容
	 */
	public void setWorkContent(String workContent){
		this.workContent = workContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打卡类型
	 */

	@Column(name ="WORK_TYPE",nullable=true,length=6)
	public String getWorkType(){
		return this.workType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打卡类型
	 */
	public void setWorkType(String workType){
		this.workType = workType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */

	@Column(name ="STATE",nullable=true,length=3)
	public String getState(){
		return this.state;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setState(String state){
		this.state = state;
	}
}
