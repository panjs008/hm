package com.emk.storage.samplecolor.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name = "emk_sample_color", schema = "")
public class EmkSampleColorEntity
        implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String updateName;
    private String updateBy;
    private Date updateDate;
    private String sysOrgCode;
    private String sysCompanyCode;
    private String bpmStatus;
    @Excel(name = "色样通知单号")
    private String sytzdbh;
    @Excel(name = "日期")
    private String sytzdrq;
    @Excel(name = "工厂")
    private String gc;
    @Excel(name = "业务员")
    private String businesser;
    @Excel(name = "业务跟单员")
    private String tracer;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "描述 ")
    private String sampleNoDesc;
    @Excel(name = "颜色英文名")
    private String ysywm;
    @Excel(name = "颜色中文名")
    private String yaswm;
    @Excel(name = "色号")
    private String sh;
    @Excel(name = "色样规格")
    private String sygg;
    @Excel(name = "色样数量")
    private String sysl;
    @Excel(name = "技术参数")
    private String jscs;
    @Excel(name = "配方")
    private String pf;
    @Excel(name = "温度")
    private String wd;
    @Excel(name = "时间")
    private String sj;

    private String customSampleUrl;
    @Excel(name = "图片")
    private String customSample;
    @Excel(name = "款号")
    private String sampleNo;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "成分")
    private String chengf;
    @Excel(name = "克重")
    private String weight;
    @Excel(name = "订单号")
    private String orderNum;
    @Excel(name = "备注")
    private String remark;
    @Excel(name = "用途")
    private String purpose;
    private String businesserName;
    private String tracerName;
    @Excel(name = "业务部门")
    private String businesseDeptName;
    private String businesseDeptId;
    @Excel(name = "客户代码")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "款式大类")
    private String proType;
    @Excel(name = "款式大类")
    private String proTypeName;
    @Excel(name = "打样需求单号")
    private String xqdh;
    @Excel(name = "生产跟单员")
    private String developer;
    private String developerName;
    @Excel(name = "色样需求单日期")
    private String syxqdrq;

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

    @Column(name = "UPDATE_NAME", nullable = true, length = 50)
    public String getUpdateName() {
        return this.updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Column(name = "UPDATE_BY", nullable = true, length = 50)
    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "UPDATE_DATE", nullable = true, length = 20)
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "SYS_ORG_CODE", nullable = true, length = 50)
    public String getSysOrgCode() {
        return this.sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    @Column(name = "SYS_COMPANY_CODE", nullable = true, length = 50)
    public String getSysCompanyCode() {
        return this.sysCompanyCode;
    }

    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode;
    }

    @Column(name = "BPM_STATUS", nullable = true, length = 32)
    public String getBpmStatus() {
        return this.bpmStatus;
    }

    public void setBpmStatus(String bpmStatus) {
        this.bpmStatus = bpmStatus;
    }

    @Column(name = "SYTZDBH", nullable = true, length = 32)
    public String getSytzdbh() {
        return this.sytzdbh;
    }

    public void setSytzdbh(String sytzdbh) {
        this.sytzdbh = sytzdbh;
    }

    @Column(name = "SYTZDRQ", nullable = true, length = 32)
    public String getSytzdrq() {
        return this.sytzdrq;
    }

    public void setSytzdrq(String sytzdrq) {
        this.sytzdrq = sytzdrq;
    }

    @Column(name = "GC", nullable = true, length = 32)
    public String getGc() {
        return this.gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    @Column(name = "BUSINESSER", nullable = true, length = 32)
    public String getBusinesser() {
        return this.businesser;
    }

    public void setBusinesser(String businesser) {
        this.businesser = businesser;
    }

    @Column(name = "TRACER", nullable = true, length = 32)
    public String getTracer() {
        return this.tracer;
    }

    public void setTracer(String tracer) {
        this.tracer = tracer;
    }

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 32)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "YSYWM", nullable = true, length = 32)
    public String getYsywm() {
        return this.ysywm;
    }

    public void setYsywm(String ysywm) {
        this.ysywm = ysywm;
    }

    @Column(name = "YASWM", nullable = true, length = 32)
    public String getYaswm() {
        return this.yaswm;
    }

    public void setYaswm(String yaswm) {
        this.yaswm = yaswm;
    }

    @Column(name = "SH", nullable = true, length = 32)
    public String getSh() {
        return this.sh;
    }

    public void setSh(String sh) {
        this.sh = sh;
    }

    @Column(name = "SYGG", nullable = true, length = 32)
    public String getSygg() {
        return this.sygg;
    }

    public void setSygg(String sygg) {
        this.sygg = sygg;
    }

    @Column(name = "SYSL", nullable = true, length = 32)
    public String getSysl() {
        return this.sysl;
    }

    public void setSysl(String sysl) {
        this.sysl = sysl;
    }

    @Column(name = "JSCS", nullable = true, length = 32)
    public String getJscs() {
        return this.jscs;
    }

    public void setJscs(String jscs) {
        this.jscs = jscs;
    }

    @Column(name = "PF", nullable = true, length = 32)
    public String getPf() {
        return this.pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    @Column(name = "WD", nullable = true, length = 32)
    public String getWd() {
        return this.wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    @Column(name = "SJ", nullable = true, length = 32)
    public String getSj() {
        return this.sj;
    }

    public void setSj(String sj) {
        this.sj = sj;
    }

    @Column(name = "CUSTOM_SAMPLE_URL", nullable = true, length = 256)
    public String getCustomSampleUrl() {
        return this.customSampleUrl;
    }

    public void setCustomSampleUrl(String customSampleUrl) {
        this.customSampleUrl = customSampleUrl;
    }

    @Column(name = "CUSTOM_SAMPLE", nullable = true, length = 56)
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

    @Column(name = "VERSION", nullable = true, length = 32)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "CHENGF", nullable = true, length = 32)
    public String getChengf() {
        return this.chengf;
    }

    public void setChengf(String chengf) {
        this.chengf = chengf;
    }

    @Column(name = "WEIGHT", nullable = true, length = 32)
    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Column(name = "ORDER_NUM", nullable = true, length = 32)
    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    @Column(name = "REMARK", nullable = true, length = 256)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "PURPOSE", nullable = true, length = 256)
    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Column(name = "BUSINESSER_NAME", nullable = true, length = 32)
    public String getBusinesserName() {
        return this.businesserName;
    }

    public void setBusinesserName(String businesserName) {
        this.businesserName = businesserName;
    }

    @Column(name = "TRACER_NAME", nullable = true, length = 32)
    public String getTracerName() {
        return this.tracerName;
    }

    public void setTracerName(String tracerName) {
        this.tracerName = tracerName;
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

    @Column(name = "XQDH", nullable = true, length = 32)
    public String getXqdh() {
        return this.xqdh;
    }

    public void setXqdh(String xqdh) {
        this.xqdh = xqdh;
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

    @Column(name = "SYXQDRQ", nullable = true, length = 32)
    public String getSyxqdrq() {
        return this.syxqdrq;
    }

    public void setSyxqdrq(String syxqdrq) {
        this.syxqdrq = syxqdrq;
    }
}
