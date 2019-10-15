package com.hm.rsgl.gbbz.entity;

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
 * @Description: 公班标准
 * @author onlineGenerator
 * @date 2019-06-28 22:34:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_gbbz", schema = "")
@SuppressWarnings("serial")
public class HmGbbzEntity implements java.io.Serializable {
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
	/**员工类型*/
	@Excel(name="员工类型",width=15)
	private String yglx;
	/**性别*/
	@Excel(name="性别",width=15)
	private String sex;
	/**正常班*/
	@Excel(name="正常班",width=15)
	private String zcb;
	/**加班*/
	@Excel(name="加班",width=15)
	private String jiab;
	
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
	 *@return: java.lang.String  员工类型
	 */

	@Column(name ="YGLX",nullable=true,length=32)
	public String getYglx(){
		return this.yglx;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  员工类型
	 */
	public void setYglx(String yglx){
		this.yglx = yglx;
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
}
