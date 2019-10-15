package com.hm.rsgl.tsjsb.entity;

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
 * @Description: 特殊计时员工表
 * @author onlineGenerator
 * @date 2019-07-04 14:43:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_tsjsb", schema = "")
@SuppressWarnings("serial")
public class HmTsjsbEntity implements java.io.Serializable {
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
	/**员工id*/
	@Excel(name="员工id",width=15)
	private String staffId;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private String realName;
	/**部门*/
	@Excel(name="部门",width=15)
	private String deptName;
	/**车间*/
	@Excel(name="车间",width=15)
	private String workName;
	/**班组*/
	@Excel(name="班组",width=15)
	private String groupName;
	/**部门代码*/
	@Excel(name="部门代码",width=15)
	private String deptCode;
	/**车间代码*/
	@Excel(name="车间代码",width=15)
	private String workCode;
	/**班组代码*/
	@Excel(name="班组代码",width=15)
	private String groupCode;
	/**正常班*/
	@Excel(name="正常班",width=15)
	private String zcb;
	/**加班*/
	@Excel(name="加班",width=15)
	private String jiab;
	/**员工类别*/
	@Excel(name="员工类别",width=15)
	private String yglb;
	/**职务*/
	@Excel(name="职务",width=15)
	private String job;
	/**工号*/
	@Excel(name="工号",width=15)
	private String workNo;
	/**薪酬类别*/
	@Excel(name="薪酬类别",width=15)
	private String xclb;
	
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
	 *@return: java.lang.String  员工id
	 */

	@Column(name ="STAFF_ID",nullable=true,length=32)
	public String getStaffId(){
		return this.staffId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工id
	 */
	public void setStaffId(String staffId){
		this.staffId = staffId;
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
	 *@return: java.lang.String  正常班
	 */

	@Column(name ="ZCB",nullable=true,length=32)
	public String getZcb(){
		return this.zcb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  正常班
	 */
	public void setZcb(String zcb){
		this.zcb = zcb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  加班
	 */

	@Column(name ="JIAB",nullable=true,length=32)
	public String getJiab(){
		return this.jiab;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  加班
	 */
	public void setJiab(String jiab){
		this.jiab = jiab;
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
}
