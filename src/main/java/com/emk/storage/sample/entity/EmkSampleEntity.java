package com.emk.storage.sample.entity;

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
@Table(name = "emk_sample", schema = "")
public class EmkSampleEntity implements Serializable {
    private String id;
    private String createName;
    private String createBy;
    private Date createDate;
    private String sysOrgCode;
    @Excel(name = "客户编号")
    private String cusNum;
    @Excel(name = "客户名称")
    private String cusName;
    @Excel(name = "款号")
    private String sampleNo;

    @Excel(name = "产品类别")
    private String proType;
    @Excel(name = "产品类别名称")
    private String proTypeName;
    @Excel(name = "大货交期")
    private String dhjq;

    @Excel(name = "状态")
    private String state;
    @Excel(name = "打样通知单号")
    private String sampleNum;
    @Excel(name = "样品类型")
    private String type;
    @Excel(name = "开单日期")
    private String kdTime;
    @Excel(name = "打样需求单日期")
    private String dyxqdTime;
    @Excel(name = "工艺种类")
    private String gyzl;
    @Excel(name = "版次")
    private String version;
    @Excel(name = "打样需求单号")
    private String xqdh;
    @Excel(name = "描述")
    private String sampleNoDesc;
    @Excel(name = "备注")
    private String remark;

    @Excel(name = "所用机台尺寸")
    private String syjtcc;
    @Excel(name = "下机重量")
    private String xjzl;
    @Excel(name = "下机尺寸")
    private String xjcc;
    @Excel(name = "密度")
    private String md;
    @Excel(name = "用料")
    private String yl;
    @Excel(name = "单件织造时间")
    private String djzjsj;

    @Excel(name = "是否有标准色样")
    private String isColorStrand;
    @Excel(name = "是否有潘通色号")
    private String isPan;
    @Excel(name = "是否有参考样")
    private String isCan;
  /*  @Excel(name = "是否有设计稿")
    private String isDrg;
    @Excel(name = "是否有原样")
    private String isSample;*/
    @Excel(name = "是否有物料明细")
    private String isDetail;
  /*  @Excel(name = "是否有尺寸表")
    private String isSize;*/

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
    @Excel(name = "是否有尺寸表")
    private String isHaveSize;
    private String sizeImageUrl;
    @Excel(name = "尺寸表")
    private String sizeImage;

    private String formType;
    private String factoryCode;
    private String ylgg;
    private String pfkz;
    private String sampleState;

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

    @Column(name = "SAMPLE_NO", nullable = true, length = 32)
    public String getSampleNo() {
        return this.sampleNo;
    }

    public void setSampleNo(String sampleNo) {
        this.sampleNo = sampleNo;
    }


    @Column(name = "REMARK", nullable = true, length = 32)
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    @Column(name = "dhjq", nullable = true, length = 32)
    public String getDhjq() {
        return dhjq;
    }

    public void setDhjq(String dhjq) {
        this.dhjq = dhjq;
    }

    @Column(name = "STATE", nullable = true, length = 6)
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name = "SAMPLE_NUM", nullable = true, length = 32)
    public String getSampleNum() {
        return this.sampleNum;
    }

    public void setSampleNum(String sampleNum) {
        this.sampleNum = sampleNum;
    }

    @Column(name = "TYPE", nullable = true, length = 32)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "KD_TIME", nullable = true, length = 32)
    public String getKdTime() {
        return this.kdTime;
    }

    public void setKdTime(String kdTime) {
        this.kdTime = kdTime;
    }

    @Column(name = "GYZL", nullable = true, length = 32)
    public String getGyzl() {
        return this.gyzl;
    }

    public void setGyzl(String gyzl) {
        this.gyzl = gyzl;
    }

    @Column(name = "VERSION", nullable = true, length = 32)
    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "XQDH", nullable = true, length = 32)
    public String getXqdh() {
        return this.xqdh;
    }

    public void setXqdh(String xqdh) {
        this.xqdh = xqdh;
    }

    @Column(name = "dyxqd_time", nullable = true, length = 32)
    public String getDyxqdTime() {
        return dyxqdTime;
    }

    public void setDyxqdTime(String dyxqdTime) {
        this.dyxqdTime = dyxqdTime;
    }

    @Column(name = "SAMPLE_NO_DESC", nullable = true, length = 256)
    public String getSampleNoDesc() {
        return this.sampleNoDesc;
    }

    public void setSampleNoDesc(String sampleNoDesc) {
        this.sampleNoDesc = sampleNoDesc;
    }

    @Column(name = "is_color_strand", nullable = true, length = 256)
    public String getIsColorStrand() {
        return isColorStrand;
    }

    public void setIsColorStrand(String isColorStrand) {
        this.isColorStrand = isColorStrand;
    }

    @Column(name = "is_pan", nullable = true, length = 256)
    public String getIsPan() {
        return isPan;
    }

    public void setIsPan(String isPan) {
        this.isPan = isPan;
    }

    @Column(name = "is_can", nullable = true, length = 256)
    public String getIsCan() {
        return isCan;
    }

    public void setIsCan(String isCan) {
        this.isCan = isCan;
    }


    @Column(name = "syjtcc", nullable = true, length = 32)
    public String getSyjtcc() {
        return syjtcc;
    }

    public void setSyjtcc(String syjtcc) {
        this.syjtcc = syjtcc;
    }

    @Column(name = "xjzl", nullable = true, length = 32)
    public String getXjzl() {
        return xjzl;
    }

    public void setXjzl(String xjzl) {
        this.xjzl = xjzl;
    }

    @Column(name = "xjcc", nullable = true, length = 32)
    public String getXjcc() {
        return xjcc;
    }

    public void setXjcc(String xjcc) {
        this.xjcc = xjcc;
    }

    @Column(name = "md", nullable = true, length = 32)
    public String getMd() {
        return md;
    }

    public void setMd(String md) {
        this.md = md;
    }

    @Column(name = "yl", nullable = true, length = 32)
    public String getYl() {
        return yl;
    }

    public void setYl(String yl) {
        this.yl = yl;
    }

    @Column(name = "djzjsj", nullable = true, length = 32)
    public String getDjzjsj() {
        return djzjsj;
    }

    public void setDjzjsj(String djzjsj) {
        this.djzjsj = djzjsj;
    }

    @Column(name = "form_type", nullable = true, length = 32)
    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    @Column(name = "factory_code", nullable = true, length = 32)
    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    @Column(name = "ylgg", nullable = true, length = 32)
    public String getYlgg() {
        return ylgg;
    }

    public void setYlgg(String ylgg) {
        this.ylgg = ylgg;
    }

    @Column(name = "pfkz", nullable = true, length = 32)
    public String getPfkz() {
        return pfkz;
    }

    public void setPfkz(String pfkz) {
        this.pfkz = pfkz;
    }

    @Column(name = "sample_state", nullable = true, length = 32)
    public String getSampleState() {
        return sampleState;
    }

    public void setSampleState(String sampleState) {
        this.sampleState = sampleState;
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

    @Column(name = "IS_HAVE_SIZE", nullable = true, length = 6)
    public String getIsHaveSize() {
        return this.isHaveSize;
    }

    public void setIsHaveSize(String isHaveSize) {
        this.isHaveSize = isHaveSize;
    }

    @Column(name = "SIZE_IMAGE_URL", nullable = true, length = 256)
    public String getSizeImageUrl() {
        return this.sizeImageUrl;
    }

    public void setSizeImageUrl(String sizeImageUrl) {
        this.sizeImageUrl = sizeImageUrl;
    }

    @Column(name = "SIZE_IMAGE", nullable = true, length = 32)
    public String getSizeImage() {
        return this.sizeImage;
    }

    public void setSizeImage(String sizeImage) {
        this.sizeImage = sizeImage;
    }

    @Column(name = "is_detail", nullable = true, length = 32)
    public String getIsDetail() {
        return isDetail;
    }

    public void setIsDetail(String isDetail) {
        this.isDetail = isDetail;
    }
}
