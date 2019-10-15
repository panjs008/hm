package com.hm.rsgl.project.entity;

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
 * @Description: 项目表
 * @author onlineGenerator
 * @date 2019-06-23 08:28:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_project", schema = "")
@SuppressWarnings("serial")
public class HmProjectEntity implements java.io.Serializable {
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
	/**a01a01a01*/
	@Excel(name="a01a01a01",width=15)
	private String a01a01a01;
	/**a01a01a02*/
	@Excel(name="a01a01a02",width=15)
	private String a01a01a02;
	/**a01a01a03*/
	@Excel(name="a01a01a03",width=15)
	private String a01a01a03;
	/**a01a01a04*/
	@Excel(name="a01a01a04",width=15)
	private String a01a01a04;
	/**a01a01a05*/
	@Excel(name="a01a01a05",width=15)
	private String a01a01a05;
	/**a01a01a06*/
	@Excel(name="a01a01a06",width=15)
	private String a01a01a06;
	/**a01a01a07*/
	@Excel(name="a01a01a07",width=15)
	private String a01a01a07;
	/**a01a01a08*/
	@Excel(name="a01a01a08",width=15)
	private String a01a01a08;
	/**a01a01a09*/
	@Excel(name="a01a01a09",width=15)
	private String a01a01a09;
	/**a01a01a10*/
	@Excel(name="a01a01a10",width=15)
	private String a01a01a10;
	/**a01a01a11*/
	@Excel(name="a01a01a11",width=15)
	private String a01a01a11;
	/**a01a01a12*/
	@Excel(name="a01a01a12",width=15)
	private String a01a01a12;
	/**a01a01a13*/
	@Excel(name="a01a01a13",width=15)
	private String a01a01a13;
	/**a01a01a14*/
	@Excel(name="a01a01a14",width=15)
	private String a01a01a14;
	/**a01a01a15*/
	@Excel(name="a01a01a15",width=15)
	private String a01a01a15;
	/**a01a01a16*/
	@Excel(name="a01a01a16",width=15)
	private String a01a01a16;
	/**a01a01a17*/
	@Excel(name="a01a01a17",width=15)
	private String a01a01a17;
	/**a01a01a18*/
	@Excel(name="a01a01a18",width=15)
	private String a01a01a18;
	/**a01a01a19*/
	@Excel(name="a01a01a19",width=15)
	private String a01a01a19;
	/**a01a01a20*/
	@Excel(name="a01a01a20",width=15)
	private String a01a01a20;
	
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
	 *@return: java.lang.String  a01a01a01
	 */

	@Column(name ="A01A01A01",nullable=true,length=32)
	public String getA01a01a01(){
		return this.a01a01a01;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a01
	 */
	public void setA01a01a01(String a01a01a01){
		this.a01a01a01 = a01a01a01;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a02
	 */

	@Column(name ="A01A01A02",nullable=true,length=32)
	public String getA01a01a02(){
		return this.a01a01a02;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a02
	 */
	public void setA01a01a02(String a01a01a02){
		this.a01a01a02 = a01a01a02;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a03
	 */

	@Column(name ="A01A01A03",nullable=true,length=32)
	public String getA01a01a03(){
		return this.a01a01a03;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a03
	 */
	public void setA01a01a03(String a01a01a03){
		this.a01a01a03 = a01a01a03;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a04
	 */

	@Column(name ="A01A01A04",nullable=true,length=32)
	public String getA01a01a04(){
		return this.a01a01a04;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a04
	 */
	public void setA01a01a04(String a01a01a04){
		this.a01a01a04 = a01a01a04;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a05
	 */

	@Column(name ="A01A01A05",nullable=true,length=32)
	public String getA01a01a05(){
		return this.a01a01a05;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a05
	 */
	public void setA01a01a05(String a01a01a05){
		this.a01a01a05 = a01a01a05;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a06
	 */

	@Column(name ="A01A01A06",nullable=true,length=32)
	public String getA01a01a06(){
		return this.a01a01a06;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a06
	 */
	public void setA01a01a06(String a01a01a06){
		this.a01a01a06 = a01a01a06;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a07
	 */

	@Column(name ="A01A01A07",nullable=true,length=32)
	public String getA01a01a07(){
		return this.a01a01a07;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a07
	 */
	public void setA01a01a07(String a01a01a07){
		this.a01a01a07 = a01a01a07;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a08
	 */

	@Column(name ="A01A01A08",nullable=true,length=32)
	public String getA01a01a08(){
		return this.a01a01a08;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a08
	 */
	public void setA01a01a08(String a01a01a08){
		this.a01a01a08 = a01a01a08;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a09
	 */

	@Column(name ="A01A01A09",nullable=true,length=32)
	public String getA01a01a09(){
		return this.a01a01a09;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a09
	 */
	public void setA01a01a09(String a01a01a09){
		this.a01a01a09 = a01a01a09;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a10
	 */

	@Column(name ="A01A01A10",nullable=true,length=32)
	public String getA01a01a10(){
		return this.a01a01a10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a10
	 */
	public void setA01a01a10(String a01a01a10){
		this.a01a01a10 = a01a01a10;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a11
	 */

	@Column(name ="A01A01A11",nullable=true,length=32)
	public String getA01a01a11(){
		return this.a01a01a11;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a11
	 */
	public void setA01a01a11(String a01a01a11){
		this.a01a01a11 = a01a01a11;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a12
	 */

	@Column(name ="A01A01A12",nullable=true,length=32)
	public String getA01a01a12(){
		return this.a01a01a12;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a12
	 */
	public void setA01a01a12(String a01a01a12){
		this.a01a01a12 = a01a01a12;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a13
	 */

	@Column(name ="A01A01A13",nullable=true,length=32)
	public String getA01a01a13(){
		return this.a01a01a13;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a13
	 */
	public void setA01a01a13(String a01a01a13){
		this.a01a01a13 = a01a01a13;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a14
	 */

	@Column(name ="A01A01A14",nullable=true,length=32)
	public String getA01a01a14(){
		return this.a01a01a14;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a14
	 */
	public void setA01a01a14(String a01a01a14){
		this.a01a01a14 = a01a01a14;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a15
	 */

	@Column(name ="A01A01A15",nullable=true,length=32)
	public String getA01a01a15(){
		return this.a01a01a15;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a15
	 */
	public void setA01a01a15(String a01a01a15){
		this.a01a01a15 = a01a01a15;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a16
	 */

	@Column(name ="A01A01A16",nullable=true,length=32)
	public String getA01a01a16(){
		return this.a01a01a16;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a16
	 */
	public void setA01a01a16(String a01a01a16){
		this.a01a01a16 = a01a01a16;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a17
	 */

	@Column(name ="A01A01A17",nullable=true,length=32)
	public String getA01a01a17(){
		return this.a01a01a17;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a17
	 */
	public void setA01a01a17(String a01a01a17){
		this.a01a01a17 = a01a01a17;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a18
	 */

	@Column(name ="A01A01A18",nullable=true,length=32)
	public String getA01a01a18(){
		return this.a01a01a18;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a18
	 */
	public void setA01a01a18(String a01a01a18){
		this.a01a01a18 = a01a01a18;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a19
	 */

	@Column(name ="A01A01A19",nullable=true,length=32)
	public String getA01a01a19(){
		return this.a01a01a19;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a19
	 */
	public void setA01a01a19(String a01a01a19){
		this.a01a01a19 = a01a01a19;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  a01a01a20
	 */

	@Column(name ="A01A01A20",nullable=true,length=32)
	public String getA01a01a20(){
		return this.a01a01a20;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  a01a01a20
	 */
	public void setA01a01a20(String a01a01a20){
		this.a01a01a20 = a01a01a20;
	}
}
