package com.hm.rsgl.order.entity;

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
 * @Description: 工单表
 * @author onlineGenerator
 * @date 2019-06-29 22:44:32
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_order", schema = "")
@SuppressWarnings("serial")
public class HmOrderEntity implements java.io.Serializable {
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
	/**工单编号*/
	@Excel(name="工单编号",width=15)
	private String orderNo;
	/**日期*/
	@Excel(name="日期",width=15)
	private String kdDate;
	/**主班组*/
	@Excel(name="主班组",width=15)
	private String workGroupName;
	/**主班组代码*/
	@Excel(name="主班组代码",width=15)
	private String workGroupCode;
	/**主车间*/
	@Excel(name="主车间",width=15)
	private String mainWorkName;
	/**主车间代码*/
	@Excel(name="主车间代码",width=15)
	private String mainWorkCode;
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
	/**固定人员*/
	@Excel(name="固定人员",width=15)
	private String gdNames;
	/**固定人员代码*/
	@Excel(name="固定人员代码",width=15)
	private String gdNamesCode;
	/**其他借调人员*/
	@Excel(name="其他借调人员",width=15)
	private String otherJdNames;
	/**其他借调人员代码*/
	@Excel(name="其他借调人员代码",width=15)
	private String otherJdNamesCode;
	/**固定借调人员*/
	@Excel(name="固定借调人员",width=15)
	private String gdJdNames;
	/**固定借调人员代码*/
	@Excel(name="固定借调人员代码",width=15)
	private String gdJdNamesCode;
	/**临时借调人员*/
	@Excel(name="临时借调人员",width=15)
	private String lsJdNames;
	/**临时借调人员代码*/
	@Excel(name="临时借调人员代码",width=15)
	private String lsJdNamesCode;
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
	 *@return: java.lang.String  工单编号
	 */

	@Column(name ="ORDER_NO",nullable=true,length=32)
	public String getOrderNo(){
		return this.orderNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单编号
	 */
	public void setOrderNo(String orderNo){
		this.orderNo = orderNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  日期
	 */

	@Column(name ="KD_DATE",nullable=true,length=32)
	public String getKdDate(){
		return this.kdDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  日期
	 */
	public void setKdDate(String kdDate){
		this.kdDate = kdDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主班组
	 */

	@Column(name ="WORK_GROUP_NAME",nullable=true,length=32)
	public String getWorkGroupName(){
		return this.workGroupName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主班组
	 */
	public void setWorkGroupName(String workGroupName){
		this.workGroupName = workGroupName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主班组代码
	 */

	@Column(name ="WORK_GROUP_CODE",nullable=true,length=32)
	public String getWorkGroupCode(){
		return this.workGroupCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主班组代码
	 */
	public void setWorkGroupCode(String workGroupCode){
		this.workGroupCode = workGroupCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主车间
	 */

	@Column(name ="MAIN_WORK_NAME",nullable=true,length=32)
	public String getMainWorkName(){
		return this.mainWorkName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主车间
	 */
	public void setMainWorkName(String mainWorkName){
		this.mainWorkName = mainWorkName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主车间代码
	 */

	@Column(name ="MAIN_WORK_CODE",nullable=true,length=32)
	public String getMainWorkCode(){
		return this.mainWorkCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主车间代码
	 */
	public void setMainWorkCode(String mainWorkCode){
		this.mainWorkCode = mainWorkCode;
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
	 *@return: java.lang.String  固定人员
	 */

	@Column(name ="GD_NAMES",nullable=true,length=512)
	public String getGdNames(){
		return this.gdNames;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定人员
	 */
	public void setGdNames(String gdNames){
		this.gdNames = gdNames;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固定人员代码
	 */

	@Column(name ="GD_NAMES_CODE",nullable=true,length=512)
	public String getGdNamesCode(){
		return this.gdNamesCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定人员代码
	 */
	public void setGdNamesCode(String gdNamesCode){
		this.gdNamesCode = gdNamesCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他借调人员
	 */

	@Column(name ="OTHER_JD_NAMES",nullable=true,length=512)
	public String getOtherJdNames(){
		return this.otherJdNames;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他借调人员
	 */
	public void setOtherJdNames(String otherJdNames){
		this.otherJdNames = otherJdNames;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他借调人员代码
	 */

	@Column(name ="OTHER_JD_NAMES_CODE",nullable=true,length=512)
	public String getOtherJdNamesCode(){
		return this.otherJdNamesCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他借调人员代码
	 */
	public void setOtherJdNamesCode(String otherJdNamesCode){
		this.otherJdNamesCode = otherJdNamesCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固定借调人员
	 */

	@Column(name ="GD_JD_NAMES",nullable=true,length=512)
	public String getGdJdNames(){
		return this.gdJdNames;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定借调人员
	 */
	public void setGdJdNames(String gdJdNames){
		this.gdJdNames = gdJdNames;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固定借调人员代码
	 */

	@Column(name ="GD_JD_NAMES_CODE",nullable=true,length=512)
	public String getGdJdNamesCode(){
		return this.gdJdNamesCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定借调人员代码
	 */
	public void setGdJdNamesCode(String gdJdNamesCode){
		this.gdJdNamesCode = gdJdNamesCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  临时借调人员
	 */

	@Column(name ="LS_JD_NAMES",nullable=true,length=512)
	public String getLsJdNames(){
		return this.lsJdNames;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  临时借调人员
	 */
	public void setLsJdNames(String lsJdNames){
		this.lsJdNames = lsJdNames;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  临时借调人员代码
	 */

	@Column(name ="LS_JD_NAMES_CODE",nullable=true,length=512)
	public String getLsJdNamesCode(){
		return this.lsJdNamesCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  临时借调人员代码
	 */
	public void setLsJdNamesCode(String lsJdNamesCode){
		this.lsJdNamesCode = lsJdNamesCode;
	}
}
