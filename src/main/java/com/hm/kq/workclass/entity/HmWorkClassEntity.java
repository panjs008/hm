package com.hm.kq.workclass.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;
import java.util.List;

import com.hm.kq.checkstaff.entity.HmCheckStaffEntity;
import com.hm.kq.workclassdetail.entity.HmWorkClassDetailEntity;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 班次表
 * @author onlineGenerator
 * @date 2019-08-04 16:48:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_work_class", schema = "")
@SuppressWarnings("serial")
public class HmWorkClassEntity implements java.io.Serializable {
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
	/**班次名称*/
	@Excel(name="班次名称",width=15)
	private String bcName;
	/**打卡次数*/
	@Excel(name="打卡次数",width=15)
	private String times;
	/**时长*/
	@Excel(name="时长",width=15)
	private String workTime;
	/**加班时长*/
	@Excel(name="加班时长",width=15)
	private String jiaTime;
	/**休息时长*/
	@Excel(name="休息时长",width=15)
	private String xiuTime;

	private List<HmCheckStaffEntity> checkStaffEntityList;

	@OneToMany(cascade = {CascadeType.MERGE },fetch = FetchType.LAZY, mappedBy = "hmWorkClassEntity")
	public List<HmCheckStaffEntity> getCheckStaffEntityList() {
		return checkStaffEntityList;
	}

	public void setCheckStaffEntityList(List<HmCheckStaffEntity> checkStaffEntityList) {
		this.checkStaffEntityList = checkStaffEntityList;
	}

	private List<HmWorkClassDetailEntity> workClassDetailEntityList;
	@OneToMany(cascade = {CascadeType.MERGE },fetch = FetchType.LAZY, mappedBy = "workClassEntity")
	public List<HmWorkClassDetailEntity> getWorkClassDetailEntityList() {
		return workClassDetailEntityList;
	}

	public void setWorkClassDetailEntityList(List<HmWorkClassDetailEntity> workClassDetailEntityList) {
		this.workClassDetailEntityList = workClassDetailEntityList;
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
	 *@return: java.lang.String  班次名称
	 */

	@Column(name ="BC_NAME",nullable=true,length=32)
	public String getBcName(){
		return this.bcName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班次名称
	 */
	public void setBcName(String bcName){
		this.bcName = bcName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  打卡次数
	 */

	@Column(name ="TIMES",nullable=true,length=32)
	public String getTimes(){
		return this.times;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  打卡次数
	 */
	public void setTimes(String times){
		this.times = times;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时长
	 */

	@Column(name ="WORK_TIME",nullable=true,length=32)
	public String getWorkTime(){
		return this.workTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时长
	 */
	public void setWorkTime(String workTime){
		this.workTime = workTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  加班时长
	 */

	@Column(name ="JIA_TIME",nullable=true,length=32)
	public String getJiaTime(){
		return this.jiaTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  加班时长
	 */
	public void setJiaTime(String jiaTime){
		this.jiaTime = jiaTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  休息时长
	 */

	@Column(name ="XIU_TIME",nullable=true,length=32)
	public String getXiuTime(){
		return this.xiuTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  休息时长
	 */
	public void setXiuTime(String xiuTime){
		this.xiuTime = xiuTime;
	}
}
