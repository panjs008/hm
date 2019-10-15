package com.emk.storage.pack.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_pack", schema = "")
public class EmkPackEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "包装辅料需求单号")
    private String parkNo;
    @Excel(name = "样品通知单编号")
    private String sampleNum;
    @Excel(name = "打样通知单日期")
    private String sampleDate;
    @Excel(name = "打样需求单编号")
    private String xqdh;
    @Excel(name = "打样需求单日期")
    private String dyxqdDate;
    @Excel(name = "提交日期")
    private String kdDate;
    @Excel(name = "业务跟单员")
    private String businesser;
    private String businesserName;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "工艺种类")
    private String gyzl;
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "是否打过初样")
    private String isPrintSample;
    @Excel(name = "是否有原样")
    private String isHaveOld;
    private String oldImageUrl;
    @Excel(name = "原样")
    private String oldImage;
    @Excel(name = "是否有设计稿")
    private String isHaveDgr;
    private String dgrImageUrl;
    @Excel(name = "设计稿")
    private String dgrImage;
    @Excel(name = "意向货交期")
    private String ysDate;
    @Excel(name = "距交期余天数")
    private Integer levelDays;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "业务跟单员")
    private String tracer;
    private String tracerName;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "样品交期")
    private String ypjqDate;
    @Excel(name = "距样品交期剩余天数")
    private Integer leaveYpjqDays;
    @Excel(name = "距大货交期剩余天数")
    private Integer leaveDhjqDays;
    @Excel(name = "是否有订单")
    private String isOrder;
    @Excel(name = "大货交期")
    private String dhjqDate;
    @Excel(name = "是否现有原料面料")
    private String isHave;
    @Excel(name = "订单号")
    private String orderNo;
    @Excel(name = "生产合同号")
    private String productHtNo;


    @Excel(name = "是否参考图片")
    private String isCkImage;
    private String ckImageUrl;
    @Excel(name = "参考图片")
    private String ckImage;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;

    private String state;
    private String processName;
    private String formType;

    private String endDate;
    private String cusAdvice;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name = "ID", nullable = false, length = 36)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "CREATE_NAME", nullable = true, length = 50)
    public String getCreateName() {
        return this.createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "CREATE_DATE", nullable = true, length = 20)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    @Column(name = "PARK_NO", nullable = true, length = 32)
    public String getParkNo() {
        return this.parkNo;
    }

    public void setParkNo(String parkNo) {
        this.parkNo = parkNo;
    }

    @Column(name = "sample_num", nullable = true, length = 32)
    public String getSampleNum() {
        return sampleNum;
    }

    public void setSampleNum(String sampleNum) {
        this.sampleNum = sampleNum;
    }

    @Column(name = "sample_date", nullable = true, length = 32)
    public String getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(String sampleDate) {
        this.sampleDate = sampleDate;
    }

    @Column(name = "xqdh", nullable = true, length = 32)
    public String getXqdh() {
        return xqdh;
    }

    public void setXqdh(String xqdh) {
        this.xqdh = xqdh;
    }

    @Column(name = "dyxqd_date", nullable = true, length = 32)
    public String getDyxqdDate() {
        return dyxqdDate;
    }

    public void setDyxqdDate(String dyxqdDate) {
        this.dyxqdDate = dyxqdDate;
    }

    @Column(name = "KD_DATE", nullable = true, length = 32)
    public String getKdDate() {
        return this.kdDate;
    }

    public void setKdDate(String kdDate) {
        this.kdDate = kdDate;
    }

    @Column(name = "BUSINESSER", nullable = true, length = 32)
    public String getBusinesser() {
        return this.businesser;
    }

    public void setBusinesser(String businesser) {
        this.businesser = businesser;
    }

    @Column(name = "BUSINESSER_NAME", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
    }

    @Column(name = "CUS_NUM", nullable = true, length = 32)
    public String getCusNum() {
        return this.cusNum;
    }

    public void setCusNum(String cusNum) {
        this.cusNum = cusNum;
    }

    @Column(name = "CUS_NAME", nullable = true, length = 32)
    public String getCusName() {
        return this.cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "PRO_TYPE", nullable = true, length = 32)
    public String getProType() {
        return this.proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    @Column(name = "PRO_TYPE_NAME", nullable = true, length = 32)
    public String getProTypeName() {
        return this.proTypeName;
    }

    public void setProTypeName(String proTypeName) {
        this.proTypeName = proTypeName;
    }

    @Column(name = "CUSTOM_SAMPLE_URL", nullable = true, length = 256)
    public String getCustomSampleUrl() {
        return this.customSampleUrl;
    }

    public void setCustomSampleUrl(String customSampleUrl) {
        this.customSampleUrl = customSampleUrl;
    }

    @Column(name = "CUSTOM_SAMPLE", nullable = true, length = 32)
    public String getCustomSample() {
        return this.customSample;
    }

    public void setCustomSample(String customSample) {
        this.customSample = customSample;
    }

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "IS_PRINT_SAMPLE", nullable = true, length = 6)
    public String getIsPrintSample() {
        return this.isPrintSample;
    }

    public void setIsPrintSample(String isPrintSample) {
        this.isPrintSample = isPrintSample;
    }

    @Column(name = "IS_HAVE_OLD", nullable = true, length = 6)
    public String getIsHaveOld() {
        return this.isHaveOld;
    }

    public void setIsHaveOld(String isHaveOld) {
        this.isHaveOld = isHaveOld;
    }

    @Column(name = "OLD_IMAGE_URL", nullable = true, length = 256)
    public String getOldImageUrl() {
        return this.oldImageUrl;
    }

    public void setOldImageUrl(String oldImageUrl) {
        this.oldImageUrl = oldImageUrl;
    }

    @Column(name = "OLD_IMAGE", nullable = true, length = 32)
    public String getOldImage() {
        return this.oldImage;
    }

    public void setOldImage(String oldImage) {
        this.oldImage = oldImage;
    }

    @Column(name = "IS_HAVE_DGR", nullable = true, length = 6)
    public String getIsHaveDgr() {
        return this.isHaveDgr;
    }

    public void setIsHaveDgr(String isHaveDgr) {
        this.isHaveDgr = isHaveDgr;
    }

    @Column(name = "DGR_IMAGE_URL", nullable = true, length = 256)
    public String getDgrImageUrl() {
        return this.dgrImageUrl;
    }

    public void setDgrImageUrl(String dgrImageUrl) {
        this.dgrImageUrl = dgrImageUrl;
    }

    @Column(name = "DGR_IMAGE", nullable = true, length = 32)
    public String getDgrImage() {
        return this.dgrImage;
    }

    public void setDgrImage(String dgrImage) {
        this.dgrImage = dgrImage;
    }

    @Column(name = "YS_DATE", nullable = true, length = 32)
    public String getYsDate() {
        return this.ysDate;
    }

    public void setYsDate(String ysDate) {
        this.ysDate = ysDate;
    }

    @Column(name = "LEVEL_DAYS", nullable = true, length = 32)
    public Integer getLevelDays() {
        return this.levelDays;
    }

    public void setLevelDays(Integer levelDays) {
        this.levelDays = levelDays;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "TRACER", nullable = true, length = 32)
    public String getTracer() {
        return this.tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    @Column(name = "TRACER_NAME", nullable = true, length = 32)
    public String getTracerName() {
        return this.tracerName;
    }

    public void setTracerName(String tracerName) {
        this.tracerName = tracerName;
    }

    @Column(name = "ORDER_NO", nullable = true, length = 32)
    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Column(name = "PRODUCT_HT_NO", nullable = true, length = 32)
    public String getProductHtNo() {
        return this.productHtNo;
    }

    public void setProductHtNo(String productHtNo) {
        this.productHtNo = productHtNo;
    }

    @Column(name = "IS_CK_IMAGE", nullable = true, length = 32)
    public String getIsCkImage() {
        return this.isCkImage;
    }

    public void setIsCkImage(String isCkImage) {
        this.isCkImage = isCkImage;
    }

    @Column(name = "CK_IMAGE_URL", nullable = true, length = 256)
    public String getCkImageUrl() {
        return this.ckImageUrl;
    }

    public void setCkImageUrl(String ckImageUrl) {
        this.ckImageUrl = ckImageUrl;
    }

    @Column(name = "CK_IMAGE_", nullable = true, length = 32)
    public String getCkImage() {
        return this.ckImage;
    }

    public void setCkImage(String ckImage) {
        this.ckImage = ckImage;
    }

    @Column(name = "BUSINESSE_DEPT_NAME", nullable = true, length = 32)
    public String getBusinesseDeptName() {
        return this.businesseDeptName;
    }

    public void setBusinesseDeptName(String businesseDeptName) {
        this.businesseDeptName = businesseDeptName;
    }

    @Column(name = "BUSINESSE_DEPT_ID", nullable = true, length = 32)
    public String getBusinesseDeptId() {
        return this.businesseDeptId;
    }

    public void setBusinesseDeptId(String businesseDeptId) {
        this.businesseDeptId = businesseDeptId;
    }

    @Column(name ="STATE",nullable=true,length=32)
    public String getState(){
        return this.state;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  工单状态
     */
    public void setState(String state){
        this.state = state;
    }

    @Column(name ="version",nullable=true,length=32)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    @Column(name = "DEVELOPER", nullable = true, length = 32)
    public String getDeveloper() {
        return this.developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    @Column(name = "DEVELOPER_NAME", nullable = true, length = 32)
    public String getDeveloperName() {
        return this.developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    @Formula("(select CONCAT(p.NAME_,'-',p.TASK_DEF_KEY_) from act_ru_task p where p.ASSIGNEE_ = id limit 0,1)")
    @Column(name = "process_name", nullable = true, length = 32)
    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Column(name = "form_type", nullable = true, length = 32)
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Column(name = "YPJQ_DATE", nullable = true, length = 32)
    public String getYpjqDate() {
        return this.ypjqDate;
    }

    public void setYpjqDate(String ypjqDate) {
        this.ypjqDate = ypjqDate;
    }

    @Column(name = "LEAVE_YPJQ_DAYS", nullable = true, length = 32)
    public Integer getLeaveYpjqDays() {
        return this.leaveYpjqDays;
    }

    public void setLeaveYpjqDays(Integer leaveYpjqDays) {
        this.leaveYpjqDays = leaveYpjqDays;
    }

    @Column(name = "LEAVE_DHJQ_DAYS", nullable = true, length = 32)
    public Integer getLeaveDhjqDays() {
        return this.leaveDhjqDays;
    }

    public void setLeaveDhjqDays(Integer leaveDhjqDays) {
        this.leaveDhjqDays = leaveDhjqDays;
    }

    @Column(name = "IS_ORDER", nullable = true, length = 32)
    public String getIsOrder() {
        return this.isOrder;
    }

    public void setIsOrder(String isOrder) {
        this.isOrder = isOrder;
    }

    @Column(name = "DHJQ_DATE", nullable = true, length = 32)
    public String getDhjqDate() {
        return this.dhjqDate;
    }

    public void setDhjqDate(String dhjqDate) {
        this.dhjqDate = dhjqDate;
    }

    @Column(name = "IS_HAVE", nullable = true, length = 32)
    public String getIsHave() {
        return this.isHave;
    }

    public void setIsHave(String isHave) {
        this.isHave = isHave;
    }


    @Column(name = "end_date", nullable = true, length = 32)
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    @Column(name = "cus_advice", nullable = true, length = 32)
    public String getCusAdvice() {
        return cusAdvice;
    }

    public void setCusAdvice(String cusAdvice) {
        this.cusAdvice = cusAdvice;
    }
}
