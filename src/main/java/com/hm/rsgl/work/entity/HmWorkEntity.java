package com.hm.rsgl.work.entity;

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
 * @Description: 工作表
 * @author onlineGenerator
 * @date 2019-06-29 22:44:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_work", schema = "")
@SuppressWarnings("serial")
public class HmWorkEntity implements java.io.Serializable {
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
	/**日期*/
	@Excel(name="日期",width=15)
	private String workDate;
	/**主班组*/
	@Excel(name="主班组",width=15)
	private String workGroupName;
	/**主班组代码*/
	@Excel(name="主班组代码",width=15)
	private String workGroupCode;
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
	/**数量*/
	@Excel(name="数量",width=15)
	private Double total;
	/**每件重量*/
	@Excel(name="每件重量",width=15)
	private Double weight;
	/**合计重量*/
	@Excel(name="合计重量",width=15)
	private String hjWeight;
	/**单价*/
	@Excel(name="单价",width=15)
	private Double price;
	/**合计金额*/
	@Excel(name="合计金额",width=15)
	private String hjMoney;
	/**人数*/
	@Excel(name="人数",width=15)
	private Integer peoples;
	/**人均工资*/
	@Excel(name="人均工资",width=15)
	private String capitaWages;
	/**固定人数*/
	@Excel(name="固定人数",width=15)
	private Integer gdPeoples;
	/**可分配工资*/
	@Excel(name="可分配工资",width=15)
	private Double kfWages;
	/**本组人数*/
	@Excel(name="本组人数",width=15)
	private Integer localPeoples;
	/**平均提成*/
	@Excel(name="平均提成",width=15)
	private Double choucheng;
	/**主车间*/
	@Excel(name="主车间",width=15)
	private String mainWorkName;
	/**主车间代码*/
	@Excel(name="主车间代码",width=15)
	private String mainWorkCode;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private String xmmc;
	/**工艺流程*/
	@Excel(name="工艺流程",width=15)
	private String gylc;
	/**规格分类*/
	@Excel(name="规格分类",width=15)
	private String ggfl;
	/**单位*/
	@Excel(name="单位",width=15)
	private String unit;
	/**手填单位*/
	@Excel(name="手填单位",width=15)
	private String stNit;
	/**计件方式*/
	@Excel(name="计件方式",width=15)
	private String type;
	/**工单ID*/
	@Excel(name="工单ID",width=15)
	private String orderId;


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
	 *@return: java.lang.String  日期
	 */

	@Column(name ="WORK_DATE",nullable=true,length=32)
	public String getWorkDate(){
		return this.workDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  日期
	 */
	public void setWorkDate(String workDate){
		this.workDate = workDate;
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
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  数量
	 */

	@Column(name ="TOTAL",nullable=true,length=32)
	public Double getTotal(){
		return this.total;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  数量
	 */
	public void setTotal(Double total){
		this.total = total;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  每件重量
	 */

	@Column(name ="WEIGHT",nullable=true,length=32)
	public Double getWeight(){
		return this.weight;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  每件重量
	 */
	public void setWeight(Double weight){
		this.weight = weight;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合计重量
	 */

	@Column(name ="HJ_WEIGHT",nullable=true,length=32)
	public String getHjWeight(){
		return this.hjWeight;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合计重量
	 */
	public void setHjWeight(String hjWeight){
		this.hjWeight = hjWeight;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单价
	 */

	@Column(name ="PRICE",nullable=true,length=32)
	public Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单价
	 */
	public void setPrice(Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合计金额
	 */

	@Column(name ="HJ_MONEY",nullable=true,length=32)
	public String getHjMoney(){
		return this.hjMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合计金额
	 */
	public void setHjMoney(String hjMoney){
		this.hjMoney = hjMoney;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  人数
	 */

	@Column(name ="PEOPLES",nullable=true,length=32)
	public Integer getPeoples(){
		return this.peoples;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  人数
	 */
	public void setPeoples(Integer peoples){
		this.peoples = peoples;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人均工资
	 */

	@Column(name ="CAPITA_WAGES",nullable=true,length=32)
	public String getCapitaWages(){
		return this.capitaWages;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人均工资
	 */
	public void setCapitaWages(String capitaWages){
		this.capitaWages = capitaWages;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  固定人数
	 */

	@Column(name ="GD_PEOPLES",nullable=true,length=32)
	public Integer getGdPeoples(){
		return this.gdPeoples;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  固定人数
	 */
	public void setGdPeoples(Integer gdPeoples){
		this.gdPeoples = gdPeoples;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  可分配工资
	 */

	@Column(name ="KF_WAGES",nullable=true,length=32)
	public Double getKfWages(){
		return this.kfWages;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  可分配工资
	 */
	public void setKfWages(Double kfWages){
		this.kfWages = kfWages;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  本组人数
	 */

	@Column(name ="LOCAL_PEOPLES",nullable=true,length=32)
	public Integer getLocalPeoples(){
		return this.localPeoples;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  本组人数
	 */
	public void setLocalPeoples(Integer localPeoples){
		this.localPeoples = localPeoples;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  平均提成
	 */

	@Column(name ="CHOUCHENG",nullable=true,length=32)
	public Double getChoucheng(){
		return this.choucheng;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  平均提成
	 */
	public void setChoucheng(Double choucheng){
		this.choucheng = choucheng;
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
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="XMMC",nullable=true,length=256)
	public String getXmmc(){
		return this.xmmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setXmmc(String xmmc){
		this.xmmc = xmmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工艺流程
	 */

	@Column(name ="GYLC",nullable=true,length=256)
	public String getGylc(){
		return this.gylc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工艺流程
	 */
	public void setGylc(String gylc){
		this.gylc = gylc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格分类
	 */

	@Column(name ="GGFL",nullable=true,length=256)
	public String getGgfl(){
		return this.ggfl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格分类
	 */
	public void setGgfl(String ggfl){
		this.ggfl = ggfl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位
	 */

	@Column(name ="UNIT",nullable=true,length=32)
	public String getUnit(){
		return this.unit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位
	 */
	public void setUnit(String unit){
		this.unit = unit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手填单位
	 */

	@Column(name ="ST_NIT",nullable=true,length=32)
	public String getStNit(){
		return this.stNit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手填单位
	 */
	public void setStNit(String stNit){
		this.stNit = stNit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计件方式
	 */

	@Column(name ="TYPE",nullable=true,length=32)
	public String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计件方式
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工单ID
	 */

	@Column(name ="ORDER_ID",nullable=true,length=32)
	public String getOrderId(){
		return this.orderId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工单ID
	 */
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}


}
