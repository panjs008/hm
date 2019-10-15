package com.hm.rsgl.workprice.entity;

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

import com.emk.util.Utils;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 工价表
 * @author onlineGenerator
 * @date 2019-07-01 20:22:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hm_work_price", schema = "")
@SuppressWarnings("serial")
public class HmWorkPriceEntity implements java.io.Serializable {
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
	/**工单ID*/
	@Excel(name="工单ID",width=15)
	private String orderId;
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
	/**工号*/
	@Excel(name="工号",width=15)
	private String workNo;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private String realName;
	/**性别*/
	@Excel(name="性别",width=15)
	private String sex;
	/**上班时间*/
	@Excel(name="上班时间",width=15)
	private String startTime;
	/**下班时间*/
	@Excel(name="下班时间",width=15)
	private String endTime;
	/**正常班*/
	@Excel(name="正常班",width=15)
	private Double zcb;
	private Double zcb0;
	private Double zcb1;

	/**加班*/
	@Excel(name="加班",width=15)
	private Double jiab;
	private Double jiab0;
	private Double jiab1;

	/**合计*/
	@Excel(name="合计",width=15)
	private Double zjhj;
	/**连班时间*/
	@Excel(name="连班时间",width=15)
	private String lbTime;
	/**时长*/
	@Excel(name="时长",width=15)
	private String times;
	/**日期*/
	@Excel(name="日期",width=15)
	private String kdDate;
	/**正常班*/
	@Excel(name="正常班",width=15)
	private Double zsZcb;
	/**单价*/
	@Excel(name="单价",width=15)
	private Double zsZcbPrice;
	/**加班*/
	@Excel(name="加班",width=15)
	private Double zsJiab;
	/**单价*/
	@Excel(name="单价",width=15)
	private String zsJiabPrice;
	/**合计*/
	@Excel(name="合计",width=15)
	private Double zshj;
	/**工资*/
	@Excel(name="工资",width=15)
	private Double wages;
	/**可分配工资*/
	@Excel(name="可分配工资",width=15)
	private Double kfWages;
	/**抽成*/
	@Excel(name="抽成",width=15)
	private Double choucheng;
	/**计件工资合计*/
	@Excel(name="计件工资合计",width=15)
	private Double zjWagesHj;
	/**工资合计*/
	@Excel(name="工资合计",width=15)
	private Double gzHj;
	private Double gzHj4;
	private Double gzHj0;
	private Double gzHj1;
	private Double gzHj2;
	private Double gzHj3;
	/**类型*/
	@Excel(name="类型",width=15)
	private String type;
	/**是否组长*/
	@Excel(name="是否组长",width=15)
	private String isZz;
	/**员工类型*/
	@Excel(name="员工类型",width=15)
	private String yglx;

	@Excel(name="实时单价",width=15)
	private Double ssPrice;
	@Excel(name="特殊单价",width=15)
	private Double tsPrice;
	@Excel(name="统一单价",width=15)
	private Double tyPrice;

	@Excel(name="实时单价",width=15)
	private Double ssPriceT;
	@Excel(name="特殊单价",width=15)
	private Double tsPriceT;
	@Excel(name="统一单价",width=15)
	private Double tyPriceT;

	private String workType;
	private String isJiab;

	@Column(name ="ss_price",nullable=true,length=50)
	public Double getSsPrice() {
		return ssPrice;
	}

	public void setSsPrice(Double ssPrice) {
		this.ssPrice = ssPrice;
	}

	@Column(name ="ts_price",nullable=true,length=50)
	public Double getTsPrice() {
		return tsPrice;
	}

	public void setTsPrice(Double tsPrice) {
		this.tsPrice = tsPrice;
	}

	@Column(name ="ty_price",nullable=true,length=50)
	public Double getTyPrice() {
		return tyPrice;
	}

	public void setTyPrice(Double tyPrice) {
		this.tyPrice = tyPrice;
	}

	@Column(name ="ss_price_t",nullable=true,length=50)
	public Double getSsPriceT() {
		return ssPriceT;
	}

	public void setSsPriceT(Double ssPriceT) {
		this.ssPriceT = ssPriceT;
	}

	@Column(name ="ts_price_t",nullable=true,length=50)
	public Double getTsPriceT() {
		return tsPriceT;
	}

	public void setTsPriceT(Double tsPriceT) {
		this.tsPriceT = tsPriceT;
	}

	@Column(name ="ty_price_t",nullable=true,length=50)
	public Double getTyPriceT() {
		return tyPriceT;
	}

	public void setTyPriceT(Double tyPriceT) {
		this.tyPriceT = tyPriceT;
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
	 *@return: java.lang.String  上班时间
	 */

	@Column(name ="START_TIME",nullable=true,length=32)
	public String getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上班时间
	 */
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  下班时间
	 */

	@Column(name ="END_TIME",nullable=true,length=32)
	public String getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  下班时间
	 */
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  正常班
	 */

	@Column(name ="ZCB",nullable=true,length=32)
	public Double getZcb(){
		return this.zcb;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  正常班
	 */
	public void setZcb(Double zcb){
		this.zcb = zcb;
	}

	public Double getZcb0() {
		if(Utils.isEmpty(workType)){
			return this.zcb;
		}else{
			return null;
		}
	}

	public void setZcb0(Double zcb0) {
		this.zcb0 = zcb0;
	}

	public Double getZcb1() {
		if("1".equals(workType)){
			this.zcb1 = zcb;
		}
		return zcb1;
	}

	public void setZcb1(Double zcb1) {
		this.zcb1 = zcb1;
	}



	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  加班
	 */

	@Column(name ="JIAB",nullable=true,length=32)
	public Double getJiab(){
		return this.jiab;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  加班
	 */
	public void setJiab(Double jiab){
		this.jiab = jiab;
	}

	public Double getJiab0() {
		if(Utils.isEmpty(workType)){
			return this.jiab;
		}else{
			return null;
		}
	}

	public void setJiab0(Double jiab0) {
		this.jiab0 = jiab0;
	}

	public Double getJiab1() {
		if("1".equals(workType)){
			this.jiab1 = jiab;
		}
		return jiab1;
	}

	public void setJiab1(Double jiab1) {
		this.jiab1 = jiab1;
	}

	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  合计
	 */

	@Column(name ="ZJHJ",nullable=true,length=32)
	public Double getZjhj(){
		return this.zjhj;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  合计
	 */
	public void setZjhj(Double zjhj){
		this.zjhj = zjhj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  连班时间
	 */

	@Column(name ="LB_TIME",nullable=true,length=32)
	public String getLbTime(){
		return this.lbTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  连班时间
	 */
	public void setLbTime(String lbTime){
		this.lbTime = lbTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时长
	 */

	@Column(name ="TIMES",nullable=true,length=32)
	public String getTimes(){
		return this.times;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时长
	 */
	public void setTimes(String times){
		this.times = times;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  正常班
	 */

	@Column(name ="ZS_ZCB",nullable=true,length=32)
	public Double getZsZcb(){
		return this.zsZcb;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  正常班
	 */
	public void setZsZcb(Double zsZcb){
		this.zsZcb = zsZcb;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单价
	 */

	@Column(name ="ZS_ZCB_PRICE",nullable=true,length=32)
	public Double getZsZcbPrice(){
		return this.zsZcbPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单价
	 */
	public void setZsZcbPrice(Double zsZcbPrice){
		this.zsZcbPrice = zsZcbPrice;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  加班
	 */

	@Column(name ="ZS_JIAB",nullable=true,length=32)
	public Double getZsJiab(){
		return this.zsJiab;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  加班
	 */
	public void setZsJiab(Double zsJiab){
		this.zsJiab = zsJiab;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */

	@Column(name ="ZS_JIAB_PRICE",nullable=true,length=32)
	public String getZsJiabPrice(){
		return this.zsJiabPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setZsJiabPrice(String zsJiabPrice){
		this.zsJiabPrice = zsJiabPrice;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  合计
	 */

	@Column(name ="ZSHJ",nullable=true,length=32)
	public Double getZshj(){
		return this.zshj;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  合计
	 */
	public void setZshj(Double zshj){
		this.zshj = zshj;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  工资
	 */

	@Column(name ="WAGES",nullable=true,length=32)
	public Double getWages(){
		return this.wages;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  工资
	 */
	public void setWages(Double wages){
		this.wages = wages;
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  抽成
	 */

	@Column(name ="CHOUCHENG",nullable=true,length=32)
	public Double getChoucheng(){
		return this.choucheng;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  抽成
	 */
	public void setChoucheng(Double choucheng){
		this.choucheng = choucheng;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  计件工资合计
	 */

	@Column(name ="ZJ_WAGES_HJ",nullable=true,length=32)
	public Double getZjWagesHj(){
		return this.zjWagesHj;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  计件工资合计
	 */
	public void setZjWagesHj(Double zjWagesHj){
		this.zjWagesHj = zjWagesHj;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  工资合计
	 */

	@Column(name ="GZ_HJ",nullable=true,length=32)
	public Double getGzHj(){
		return this.gzHj;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  工资合计
	 */
	public void setGzHj(Double gzHj){
		this.gzHj = gzHj;
	}

	public Double getGzHj0() {
		if("0".equals(workType)){
			this.gzHj0 = gzHj;
		}
		return gzHj0;
	}

	public void setGzHj0(Double gzHj0) {
		this.gzHj0 = gzHj0;
	}

	public Double getGzHj1() {
		if("1".equals(workType)){
			this.gzHj1 = gzHj;
		}
		return gzHj1;
	}

	public void setGzHj1(Double gzHj1) {
		this.gzHj1 = gzHj1;
	}

	public Double getGzHj2() {
		if("2".equals(workType)){
			this.gzHj2 = gzHj;
		}
		return gzHj2;
	}

	public void setGzHj2(Double gzHj2) {
		this.gzHj2 = gzHj2;
	}

	public Double getGzHj3() {
		if("3".equals(workType)){
			this.gzHj3 = gzHj;
		}
		return gzHj3;
	}

	public void setGzHj3(Double gzHj3) {
		this.gzHj3 = gzHj3;
	}

	public Double getGzHj4() {
		if(Utils.isEmpty(workType)){
			this.gzHj4 = gzHj;
		}
		return gzHj4;
	}

	public void setGzHj4(Double gzHj4) {
		this.gzHj4 = gzHj4;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型
	 */

	@Column(name ="TYPE",nullable=true,length=32)
	public String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否组长
	 */

	@Column(name ="IS_ZZ",nullable=true,length=32)
	public String getIsZz(){
		return this.isZz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否组长
	 */
	public void setIsZz(String isZz){
		this.isZz = isZz;
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

	@Column(name ="work_type",nullable=true,length=32)
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	@Column(name ="is_jiab",nullable=true,length=32)
	public String getIsJiab() {
		return isJiab;
	}

	public void setIsJiab(String isJiab) {
		this.isJiab = isJiab;
	}
}
