package com.emk.util;

import com.emk.bill.materialpact.entity.EmkMaterialPactEntity;
import com.emk.bill.materialrequired.entity.EmkMaterialRequiredEntity;
import com.emk.bill.proorder.entity.EmkProOrderEntity;
import com.emk.bill.proorderbarcode.entity.EmkProOrderBarcodeEntity;
import com.emk.bill.proorderbox.entity.EmkProOrderBoxEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.bound.moutstorage.entity.EmkMOutStorageEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.storage.accessories.entity.EmkAccessoriesEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntityA;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntity;
import com.emk.storage.factoryarchives.entity.EmkFactoryArchivesEntityA;
import com.emk.storage.gl.entity.EmkGlEntity;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.pack.entity.EmkPackEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.price.entity.EmkPriceEntityA;
import com.emk.storage.sample.entity.EmkSampleContentEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.samplecolor.entity.EmkSampleColorEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity2;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.storage.supplier.entity.EmkSupplierEntity;
import com.emk.storage.supplier.entity.EmkSupplierEntity2;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.custom.entity.YmkCustomEntity;
import com.service.custom.entity.YmkCustomEntityA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


public class createPdf {

    Document document = null;// 建立一个Document对象
    private static Font headFont ;
    private static Font titleFont ;
    private static Font keyFont ;
    private static Font textfont_H ;
    private static Font textfont_B ;
    int maxWidth = 700;


    static{
        BaseFont bfChinese_H;
        try {
            /**
             * 新建一个字体,iText的方法 STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀
             * UniGB-UCS2-H 是编码，在iTextAsian.jar 中以cmap为后缀 H 代表文字版式是 横版， 相应的 V 代表竖版
             */
            //bfChinese_H = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            headFont = new Font(bfChinese_H, 10, Font.NORMAL);
            titleFont = new Font(bfChinese_H, 9, Font.NORMAL);
            keyFont = new Font(bfChinese_H, 18, Font.BOLD);
            textfont_H = new Font(bfChinese_H, 10, Font.NORMAL);
            textfont_B = new Font(bfChinese_H, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置页面属性
     * @param filed
     */
    public createPdf(File file) {

        //自定义纸张
        //Rectangle rectPageSize = new Rectangle(450, 250);
        //Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        // 定义A4页面大小
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        rectPageSize = rectPageSize.rotate();// 加上这句可以实现页面的横置
        document = new Document(rectPageSize,70, 80, 10, 40);

        try {
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建表格(以列的数量建)
     * @param colNumber
     * @return
     */
    public PdfPTable createTable(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            //table.setTotalWidth(maxWidth);
            //table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            table.setWidthPercentage(150);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 建表格(以列的宽度比建)
     * @param widths
     * @return
     */
    public PdfPTable createTable(float[] widths){
        PdfPTable table = new PdfPTable(widths);
        try{
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            //table.setWidthPercentage(150);

           /* table.setSpacingBefore(10f);
            table.setWidthPercentage(200);// 设置表格宽度为100%*/
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }


    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));
        cell.setBorder(1);
        return cell;
    }

    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @param rowspan
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(20);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCell01(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(30);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCell02(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(180);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCellForEnqiry(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(120);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCellForGysImg(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(250);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCellForCustom(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(80);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    /**
     * 建短语
     * @param value
     * @param font
     * @return
     */
    public Phrase createPhrase(String value,Font font){
        Phrase phrase = new Phrase();
        phrase.add(value);
        phrase.setFont(font);
        return phrase;
    }

    /**
     * 建段落
     * @param value
     * @param font
     * @param align
     * @return
     */
    public Paragraph createParagraph(String value,Font font,int align){
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(value,font));
        paragraph.setAlignment(align);
        return paragraph;
    }

    /**
     * 客户档案
     * @param emkEnquiryEntity  客户档案表实体
     * @return
     */
    public void generateEmkCustomPDF(YmkCustomEntityA customEntity) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("客  户  档  案  ",keyFont,Element.ALIGN_CENTER));
        document.add(createParagraph("档案编号："+customEntity.getCusNum(),headFont,Element.ALIGN_RIGHT));

        //表格信息
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {15f,40f,15f,40f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);

        table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("客户地址", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getAddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getTracerName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getDeveloperName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCellForCustom("主营产品", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForCustom(customEntity.getMainContent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("客户类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("业务类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBusinessType(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("潜在业务量/年", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getQzywl(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("建立商业关系时间", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getRelationDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("区域", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getShengFen(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("国家", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getGuoJia(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        document.add(table);
        document.add(createParagraph("【联系人】",headFont,Element.ALIGN_LEFT));
        float[] widths2 = {8f,15f,15f,15f,15f,15f,15f,15f};
        table = createTable(widths2);
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务联系人一", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("包装联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("测试联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("质量联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("验厂联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("船务联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("法律联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("姓名", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBzlxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYclxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getFllxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBzlxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYclxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getFllxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBzlxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYclxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getFllxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        document.add(table);

        document.add(createParagraph("【业务联系人】",headFont,Element.ALIGN_LEFT));
        table = createTable(widths2);
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人二", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人三", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人四", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人五", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("姓名", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr2(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr3(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr4(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr5(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr2Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr3Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr4Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr5Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr2Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr3Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr4Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr5Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        document.add(table);
        document.add(blankRow41);
        document.close();
    }

    /**
     * 供应商档案表
     * @param emkEnquiryEntity  供应商档案表单实体
     * @return
     */
    public void generateFactoryArchivesPDF(EmkFactoryArchivesEntityA supplierEntity) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
//        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("供  应  商  档  案",keyFont,Element.ALIGN_CENTER));
        document.add(createParagraph("档案编号："+supplierEntity.getArchivesNo(),headFont,Element.ALIGN_RIGHT));
        //表格信息 简易供应商货代运输企业
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {25f,40f,25f,40f};
        PdfPTable table = createTable(widths);

        table.addCell(createCell01("国家:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getGuoJia(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,4,1));

        table.addCell(createCell01("省份:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getShengFen(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("城市:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getChengShi(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("供应商产品类型:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,4,1));

        table.addCell(createCell01("供应商代码:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCompanyCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,4,1));

        table.addCell(createCell01("供应商代码:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCompanyCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,4,1));

        table.addCell(createCell01("Company Name", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("English英文:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCompanyNameZn(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        table.addCell(createCell01("(English & Local Language)公司名", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Local 中文:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCompanyNameZn(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        table.addCell(createCell01("Address", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("English英文:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getAddressEn(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        table.addCell(createCell01("Address", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Local 中文:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCompanyNameZn(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        table.addCell(createCell01("Additional sites (address)其它地址:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Provide address if the facility has additional sites remote from the premises若工厂还有其它经营地址请指明:\n"+supplierEntity.getOtherAddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("Location of the Employee Documents 审核文件所存放的地址:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Provide address if employee documents (e.g. HR / Pay Roll/ Time Attendance Records) are stored in a different location若审核文件（如人事、工资考勤记录等）存放在另一地址请指明:\n"+supplierEntity.getLocationDocuments(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("Primary Facility Contact & Title 主要联系人姓名及头衔:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getPrimaryContact(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Secondary Facility Contact & Title 第二联系人姓名及头衔:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSecondaryContact(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));


        table.addCell(createCell01("E-mail Address邮箱:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getPrimaryContactEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("E-mail Address邮箱:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSecondaryContactEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Telephone Number电话:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getPrimaryContactTel(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Telephone Number电话:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSecondaryContactTel(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Year of Facility Established 成立年份:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getYearEstablished(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Business License Number 营业执照号码:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLicenseNumber(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Facility Business License:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getFacilityBusinessLicense(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Business License/ Permit is issued by 营业执照签发机构:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getPermitSsued(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("营业执照有效期:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getPermitExpirationDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("Production Process生产工序:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getProductionProcess(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Full production on proposed audit date 审核当天是否全员生产:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getFullProduction().equals("0") ? "YES":"NO", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        String text = "";
        if (supplierEntity.getProductClassification().contains("[0]")) {
            text += "Home communications, entertainment & hobby equipment,";
        }
        if (supplierEntity.getProductClassification().contains("[1]")){
            text += "Sports and Outdoor Recreation Equipment,";
        }
        if (supplierEntity.getProductClassification().contains("[2]")){
            text += "Furniture and Furnishings,";
        }
        if (supplierEntity.getProductClassification().contains("[3]")){
            text += "Packaging and Containers,";
        }
        if (supplierEntity.getProductClassification().contains("[4]")){
            text += "House appliance,";
        }
        if (supplierEntity.getProductClassification().contains("[5]")){
            text += "Toys,";
        }
        if (supplierEntity.getProductClassification().contains("[6]")){
            text += "Housewares (non-powered),";
        }
        if (supplierEntity.getProductClassification().contains("[7]")){
            text += "Personal care items,";
        }
        if (supplierEntity.getProductClassification().contains("[8]")){
            text += "Chemicals & chemical products,";
        }
        if (supplierEntity.getProductClassification().contains("[9]")){
            text += "Auto Part & Accessory,";
        }
        if (supplierEntity.getProductClassification().contains("[10]")){
            text += "Home workshop apparatus tools,";
        }
        if (supplierEntity.getProductClassification().contains("[11]")){
            text += "Beverage & Food,";
        }
        if (supplierEntity.getProductClassification().contains("[12]")){
            text += "Yard and garden,";
        }
        if (supplierEntity.getProductClassification().contains("[13]")){
            text += "Pet related products,";
        }
        if (supplierEntity.getProductClassification().contains("[14]")){
            text += "Child nursery equipment & supplies,";
        }
        if (supplierEntity.getProductClassification().contains("[15]")){
            text += "Medicine,";
        }
        if (supplierEntity.getProductClassification().contains("[16]")){
            text += "Personal use items,";
        }
        if (supplierEntity.getProductClassification().contains("[17]")){
            text += "Raw Material,";
        }
        if (supplierEntity.getProductClassification().contains("[18]")){
            text += "Garments, Footwear & Accessories,";
        }
        if (supplierEntity.getProductClassification().contains("[19]")){
            text += "Miscellaneous Products,";
        }

        if (supplierEntity.getProductClassification().contains("[21]")){
            text += "Servicing Facilities,";
        }
        if (supplierEntity.getProductClassification().contains("[23]")){
            text += "Other,";
        }
        if (supplierEntity.getProductClassification().contains("[25]")){
            text += "Pet related products,";
        }
        table.addCell(createCell01("Product Classification of Manufacturing 产业类型:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(text, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));


        table.addCell(createCell01("Facility Size 企业面积", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell01("Facility Land Size (m2 )企业用地面积:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getFacilityLandSize(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        table.addCell(createCell01("Total Facility Floor Size (m2) 企业总楼层面积:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getFacilityFoorSize(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        document.add(table);


       /* float[] widths3 = {21f,200f};
        table = createTable(widths3);
        table.addCell(createCellForGysImg("营业执照", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForGysImg(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCellForGysImg("工厂信息表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForGysImg(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        document.add(table);
        document.add(blankRow41);*/

        float[] widths2 = {25f,25f,40f,25f,40f};
        table = createTable(widths2);
        table.addCell(createCell01("Number of Buildings建筑物数目", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell01("Production 生产:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getProduction(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Dormitory宿舍:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getDormitory(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Warehouse仓库:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getWarehouse(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Other (specify)其它（请注明）:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getOtherSpecify(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Onsite Service Providers (e.g. security, janitor) 服务供应商（如保安，门卫）", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Provide name of each service provider and roles请提供服务供应商的名称及职位:\n"+supplierEntity.getProvideNameProvider(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,4,1));


        table.addCell(createCell01("Number of Employee Breakdown 员工人数明细", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell01("Permanent Employee 直接雇佣员工人数:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getPermanentEmployee(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("emporary/Agency临时工/劳务派遣工/外包工人数:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getTemporary(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Migrant labour 移民员工:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getMigrantLabour(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Home workers 家内工作者:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getHomeWorkers(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Gender Breakdown 性别明细", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Female 女性员工人数:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getFemale(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Male男性员工人数::", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getMale(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Employee Type Breakdown 员工类型明细", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell01("#of Production Employees 生产员工人数:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getProductionEmployees(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("#of Office/Admin staff办公/行政人员人数:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getAdminStaf(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("#of Management 管理人员人数::", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getManagement(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("Language Spoken by Employee员工语种", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell01("Language1语种1:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLanguage1(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Total workforce in%占总人数百分比:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getZzsPre1(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Language2语种2:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLanguage2(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Total workforce in%占总人数百分比:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getZzsPre2(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("Language Spoken by Management管理人员语种", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("Language1语种1:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getMlanguage1(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("Language2语种2:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getMlanguage2(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        String level = "";
        if(supplierEntity.getLevel().equals("0")){
            level = "不能做单";
        }else if(supplierEntity.getLevel().equals("1")){
            level = "能做单有风险";
        }else if(supplierEntity.getLevel().equals("2")){
            level = "放心做单";
        }
        table.addCell(createCell01("评估风险等级:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell01(level, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,4,1));
        document.add(table);

        document.close();
    }

    /**
     * 简易供应商货代运输企业表
     * @param emkEnquiryEntity  简易供应商货代运输企业
     * @return
     */
    public void generateEmkSupplierPDF(EmkSupplierEntity2 supplierEntity) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
//        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("简易供应商货代运输企业",keyFont,Element.ALIGN_CENTER));

        //表格信息 简易供应商货代运输企业
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {25f,40f,15f,40f,15f,40f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);

        table.addCell(createCell01("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSupplierCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("供应商类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSupplierType(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("产品类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getProductType(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("企业全称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSupplier(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCellForCustom("地址", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForCustom(supplierEntity.getAddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("纳税人识别号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getTaxpayerNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("开户行", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getBankName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("开户账号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getBankAccount(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("开户账号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getBankAccount(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("法定代表人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLegaler(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLegalerTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLegalerEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getContacter(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getContacterTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getContacterEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("财务联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCwContacter(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCwContacterTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCwContacterEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        document.add(table);

        document.newPage();
        String imagepath = "";
        Image image = null;
        if(Utils.notEmpty(supplierEntity.getLicenceUrl())){
            imagepath = supplierEntity.getLicenceUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(230,350);
            image.scaleAbsolute(400,220);
            document.add(image);
        }

        if(Utils.notEmpty(supplierEntity.getFactoryInfoUrl())){
            imagepath = supplierEntity.getFactoryInfoUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(230,100);
            image.scaleAbsolute(400,220);
            document.add(image);
        }


        float[] widths3 = {21f,200f};
        table = createTable(widths3);
        table.addCell(createCellForGysImg("营业执照", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForGysImg(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCellForGysImg("工厂信息表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForGysImg(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        document.add(table);
        document.add(blankRow41);
        document.close();
    }

    public void setSizeHeader(PdfPTable table,EmkSizeEntity emkSizeEntity){
        table.addCell(createCell(emkSizeEntity.getSizeA(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeB(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeC(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeD(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeE(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeF(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeG(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeH(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeI(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeJ(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(emkSizeEntity.getSizeK(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
    }

    public int setSizeBody(PdfPTable table,Map map,int a,int b,int c,int d,int e,int f,int g,int h,int i,int j,int k){
        table.addCell(createCell(map.get("total_a") != null ? map.get("total_a").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_b") != null ? map.get("total_b").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_c") != null ? map.get("total_c").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_d") != null ? map.get("total_d").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_e") != null ? map.get("total_e").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_f") != null ? map.get("total_f").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_g") != null ? map.get("total_g").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_h") != null ? map.get("total_h").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_i") != null ? map.get("total_i").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_j") != null ? map.get("total_j").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(map.get("total_k") != null ? map.get("total_k").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if(Utils.notEmpty(map.get("total_a"))){
            a += Integer.parseInt(map.get("total_a").toString());
        }
        if(Utils.notEmpty(map.get("total_b"))){
            b += Integer.parseInt(map.get("total_b").toString());
        }
        if(Utils.notEmpty(map.get("total_c"))){
            c += Integer.parseInt(map.get("total_c").toString());
        }
        if(Utils.notEmpty(map.get("total_d"))){
            d += Integer.parseInt(map.get("total_d").toString());
        }
        if(Utils.notEmpty(map.get("total_e"))){
            e += Integer.parseInt(map.get("total_e").toString());
        }
        if(Utils.notEmpty(map.get("total_f"))){
            f += Integer.parseInt(map.get("total_f").toString());
        }
        if(Utils.notEmpty(map.get("total_g"))){
            g += Integer.parseInt(map.get("total_g").toString());
        }
        if(Utils.notEmpty(map.get("total_h"))){
            h += Integer.parseInt(map.get("total_h").toString());
        }
        if(Utils.notEmpty(map.get("total_i"))){
            i += Integer.parseInt(map.get("total_i").toString());
        }
        if(Utils.notEmpty(map.get("total_j"))){
            j += Integer.parseInt(map.get("total_j").toString());
        }
        if(Utils.notEmpty(map.get("total_k"))){
            k += Integer.parseInt(map.get("total_k").toString());
        }

        return a+b+c+d+e+f+g+h+i+j+k;
    }

    public void setSizeEnd(PdfPTable table,int a,int b,int c,int d,int e,int f,int g,int h,int i,int j,int k){
        table.addCell(createCell(a !=0 ? String.valueOf(a):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(b !=0 ? String.valueOf(b):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(c !=0 ? String.valueOf(c):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(d !=0 ? String.valueOf(d):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(e !=0 ? String.valueOf(e):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(f !=0 ? String.valueOf(f):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(g !=0 ? String.valueOf(g):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(h !=0 ? String.valueOf(h):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(i !=0 ? String.valueOf(i):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(j !=0 ? String.valueOf(j):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(k !=0 ? String.valueOf(k):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
    }

    public void setYlmlData(PdfPTable table,List<EmkSampleDetailEntity> emkSampleDetailEntities0,String type){
        double zyl =0;
        int zjs =0;
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("1".equals(type)){
            table.addCell(createCell("规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell("比例(%)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("价格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("损耗率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("成本(含损耗)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("1".equals(type)){
            table.addCell(createCell("位置", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("入库状态", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }

        for(int i1 = 0 ; i1 < emkSampleDetailEntities0.size() ; i1++){
            EmkSampleDetailEntity sampleDetailEntity = emkSampleDetailEntities0.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProZnName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            if("1".equals(type)){
                table.addCell(createCell(sampleDetailEntity.getBrand(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }
            table.addCell(createCell(sampleDetailEntity.getPrecent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(sampleDetailEntity.getYongliang()) ? String.valueOf(sampleDetailEntity.getYongliang()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignTotal(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            if(Utils.notEmpty(sampleDetailEntity.getYongliang())){
                zyl += sampleDetailEntity.getYongliang();
            }
            if(Utils.notEmpty(sampleDetailEntity.getSignTotal())){
                zjs += Double.parseDouble(sampleDetailEntity.getSignTotal());
            }
            table.addCell(createCell(sampleDetailEntity.getGysCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignPrice(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getUnit(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getSunhaoPrecent() != null ? sampleDetailEntity.getYongliang():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getChengben() != null ? sampleDetailEntity.getChengben():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            if("1".equals(type)){
                table.addCell(createCell(sampleDetailEntity.getPosition(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(sampleDetailEntity.getRkState(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }
        }
        if("0".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
        }
        if("1".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
        }
    }

    public void setFzflData(PdfPTable table,List<EmkSampleDetailEntity> emkSampleDetailEntities1,String type){
        double zyl =0;
        int zjs =0;
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("价格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("损耗率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("成本(含损耗)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("1".equals(type)){
            table.addCell(createCell("位置", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("入库状态", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        for(int i1 = 0 ; i1 < emkSampleDetailEntities1.size() ; i1++){
            EmkSampleDetailEntity sampleDetailEntity = emkSampleDetailEntities1.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProZnName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getBrand(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(sampleDetailEntity.getYongliang()) ? String.valueOf(sampleDetailEntity.getYongliang()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignTotal(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            if(Utils.notEmpty(sampleDetailEntity.getYongliang())){
                zyl += sampleDetailEntity.getYongliang();
            }
            if(Utils.notEmpty(sampleDetailEntity.getSignTotal())){
                zjs += Double.parseDouble(sampleDetailEntity.getSignTotal());
            }
            table.addCell(createCell(sampleDetailEntity.getGysCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignPrice(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getUnit(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getSunhaoPrecent() != null ? sampleDetailEntity.getSunhaoPrecent():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getChengben() != null ? sampleDetailEntity.getChengben():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            if("1".equals(type)){
                table.addCell(createCell(sampleDetailEntity.getPosition(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(sampleDetailEntity.getRkState(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }
        }
        if("0".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
        }
        if("1".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
        }
    }

    public void setBzflData(PdfPTable table,List<EmkSampleDetailEntity> emkSampleDetailEntities2,String type){
        double zyl =0;
        int zjs =0;
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("价格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("损耗率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("成本(含损耗)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("1".equals(type)){
            table.addCell(createCell("位置", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("入库状态", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        for(int i1 = 0 ; i1 < emkSampleDetailEntities2.size() ; i1++){
            EmkSampleDetailEntity sampleDetailEntity = emkSampleDetailEntities2.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProZnName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getBrand(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(sampleDetailEntity.getYongliang()) ? String.valueOf(sampleDetailEntity.getYongliang()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignTotal(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            if(Utils.notEmpty(sampleDetailEntity.getYongliang())){
                zyl += sampleDetailEntity.getYongliang();
            }
            if(Utils.notEmpty(sampleDetailEntity.getSignTotal())){
                zjs += Double.parseDouble(sampleDetailEntity.getSignTotal());
            }
            table.addCell(createCell(sampleDetailEntity.getGysCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignPrice(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getUnit(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getSunhaoPrecent() != null ? sampleDetailEntity.getSunhaoPrecent():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getChengben() != null ? sampleDetailEntity.getChengben():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            if("1".equals(type)){
                table.addCell(createCell(sampleDetailEntity.getPosition(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(sampleDetailEntity.getRkState(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }
        }
        if("0".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
        }
        if("1".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
        }
    }

    public void setXqkfdData(PdfPTable table,List<EmkSampleDetailEntity2> emkSampleDetailEntities,String type){
        double zyl =0;
        double zj = 0;
        int zjs =0;

        table.addCell(createCell01("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("捻向", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("批号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("幅宽", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("克重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("成分", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if(!"1".equals(type)){
            table.addCell(createCell01("价格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("损耗率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("备注", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if(!"2".equals(type)){
            table.addCell(createCell01("供应商", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(type)){
            table.addCell(createCell01("合同编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("入库状态", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if(Utils.notEmpty(emkSampleDetailEntities)) {
            for (int i1 = 0; i1 < emkSampleDetailEntities.size(); i1++) {
                EmkSampleDetailEntity2 emkSampleDetailEntity = emkSampleDetailEntities.get(i1);
                table.addCell(createCell(String.valueOf((i1 + 1)), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                if(!"1".equals(type)){
                    table.addCell(createCell(emkSampleDetailEntity.getCgxqdh(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                }
                if("3".equals(type)){
                    table.addCell(createCell(emkSampleDetailEntity.getOrderNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                    table.addCell(createCell(emkSampleDetailEntity.getSampleNo(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                }
                table.addCell(createCell(emkSampleDetailEntity.getProZnName(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getProNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getBrand(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(Utils.notEmpty(emkSampleDetailEntity.getYongliang()) ? String.valueOf(emkSampleDetailEntity.getYongliang()):"0", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getDirection(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getBetchNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getWidth(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getColor(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getWeight(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getChengf(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getSignTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                if(!"1".equals(type)){
                    table.addCell(createCell(emkSampleDetailEntity.getSignPrice(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                }
                table.addCell(createCell(emkSampleDetailEntity.getUnit(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(Utils.notEmpty(emkSampleDetailEntity.getSunhaoPrecent()) ? String.valueOf(emkSampleDetailEntity.getSunhaoPrecent()):"0", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getRemark(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                if(!"2".equals(type)){
                    table.addCell(createCell(emkSampleDetailEntity.getGysCode(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                }
                if(Utils.notEmpty(emkSampleDetailEntity.getYongliang())){
                    zyl += emkSampleDetailEntity.getYongliang();
                }
                if(Utils.notEmpty(emkSampleDetailEntity.getSignTotal())){
                    zjs += Double.parseDouble(emkSampleDetailEntity.getSignTotal());
                }
                if(Utils.notEmpty(emkSampleDetailEntity.getSignPrice())){

                }
                if("1".equals(type)){
                    table.addCell(createCell(emkSampleDetailEntity.getHtNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                    table.addCell(createCell(emkSampleDetailEntity.getRkState(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                }
            }
        }

        if("0".equals(type) || "1".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,"0".equals(type) ? 5:6,1));
        }
        if("2".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
        }
        if("3".equals(type)){
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
        }
    }

    public void setRgData(PdfPTable table,List<EmkSampleGxEntity> emkSampleGxEntities){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("工序名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件耗时", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("每天产量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("成本", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        for(int i1 = 0 ; i1 < emkSampleGxEntities.size() ; i1++){
            EmkSampleGxEntity emkSampleGxEntity = emkSampleGxEntities.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleGxEntity.getGxmc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(emkSampleGxEntity.getDjhs() != null ? emkSampleGxEntity.getDjhs():0), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleGxEntity.getUnit(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(emkSampleGxEntity.getProductTotal() != null ? emkSampleGxEntity.getProductTotal():0), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(emkSampleGxEntity.getChengben() != null ? emkSampleGxEntity.getChengben():0), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
    }

    public void setYhData(PdfPTable table,List<Map<String, Object>> emkSampleYinEntities){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("印花工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("印花大小", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("成本", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        for(int i1 = 0 ; i1 < emkSampleYinEntities.size() ; i1++){
            Map<String, Object> emkSampleYinEntity = emkSampleYinEntities.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("colorName").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("size").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("chengben").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
    }

    public void setRanData(PdfPTable table,List<Map<String, Object>> emkSampleRanEntities){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("布面种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("染色单价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("染色克重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("染色成本", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        for(int i1 = 0 ; i1 < emkSampleRanEntities.size() ; i1++){
            Map<String, Object> emkSampleRan = emkSampleRanEntities.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleRan.get("colorName").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleRan.get("price").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleRan.get("one_weight").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleRan.get("chengben").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
    }

    public void setZjData(PdfPTable table,List<Map<String, Object>> emkSampleZjEntities){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("布面种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("染色单价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("染色克重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("染色成本", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        for(int i1 = 0 ; i1 < emkSampleZjEntities.size() ; i1++){
            Map<String, Object> emkSampleYinEntity = emkSampleZjEntities.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("colorName").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("price").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("one_weight").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleYinEntity.get("chengben").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
    }

    public void setBarCodeData(PdfPTable table,List<EmkProOrderBarcodeEntity> emkProOrderBarcodeEntityList,String type){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("尺码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("条码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        int ai = 0;
        for(EmkProOrderBarcodeEntity proOrderBarcodeEntity:emkProOrderBarcodeEntityList){
            if(proOrderBarcodeEntity.getType().equals("type")){
                table.addCell(createCell(String.valueOf((ai+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(proOrderBarcodeEntity.getColor(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(proOrderBarcodeEntity.getSize(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(proOrderBarcodeEntity.getCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(proOrderBarcodeEntity.getTotal()) ? String.valueOf(proOrderBarcodeEntity.getTotal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                ai++;
            }
        }
    }

    public void setBoxData(PdfPTable table,List<EmkProOrderBoxEntity> emkProOrderBoxEntityList,String type){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("尺码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("箱内数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("箱数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        /*table.addCell(createCell("总数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("总箱数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));*/
        table.addCell(createCell("长(CM)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("宽(CM)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("高(CM)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单箱毛重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单箱净重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单箱体积", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        /*table.addCell(createCell("总体积", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("总毛重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("总净重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));*/

        for(EmkProOrderBoxEntity emkProOrderBoxEntity : emkProOrderBoxEntityList){
            if(emkProOrderBoxEntity.getBoxType().equals(type)){
                table.addCell(createCell(emkProOrderBoxEntity.getColorName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(emkProOrderBoxEntity.getSize(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getInboxTotal()) ? String.valueOf(emkProOrderBoxEntity.getInboxTotal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getTotal()) ? String.valueOf(emkProOrderBoxEntity.getTotal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getLongVal()) ? String.valueOf(emkProOrderBoxEntity.getLongVal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getWidthVal()) ? String.valueOf(emkProOrderBoxEntity.getWidthVal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getHeightVal()) ? String.valueOf(emkProOrderBoxEntity.getHeightVal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getOneWeightMao()) ? String.valueOf(emkProOrderBoxEntity.getOneWeightMao()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getOneWeightJz()) ? String.valueOf(emkProOrderBoxEntity.getOneWeightJz()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkProOrderBoxEntity.getBoxVolume()) ? String.valueOf(emkProOrderBoxEntity.getBoxVolume()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            }
        }
    }

    public void setContract(PdfPTable table,List<EmkSampleDetailEntity> emkSampleDetailEntities0,double zyl,int zjs,String type){
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if(type.equals("0")){
            table.addCell(createCell("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if(type.equals("1")){
            table.addCell(createCell("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if(type.equals("2")){
            table.addCell(createCell("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell("捻向", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("批号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("幅宽", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell("比例(%)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("价格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("损耗率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("成本(含损耗)", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        for(int i1 = 0 ; i1 < emkSampleDetailEntities0.size() ; i1++){
            EmkSampleDetailEntity sampleDetailEntity = emkSampleDetailEntities0.get(i1);
            table.addCell(createCell(String.valueOf((i1+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProZnName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getProNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getPrecent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(sampleDetailEntity.getYongliang()) ? String.valueOf(sampleDetailEntity.getYongliang()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignTotal(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            if(Utils.notEmpty(sampleDetailEntity.getYongliang())){
                zyl += sampleDetailEntity.getYongliang();
            }
            if(Utils.notEmpty(sampleDetailEntity.getSignTotal())){
                zjs += Double.parseDouble(sampleDetailEntity.getSignTotal());
            }
            table.addCell(createCell(sampleDetailEntity.getGysCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getSignPrice(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(sampleDetailEntity.getUnit(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getSunhaoPrecent() != null ? sampleDetailEntity.getYongliang():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(String.valueOf(sampleDetailEntity.getChengben() != null ? sampleDetailEntity.getChengben():""), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));
        table.addCell(createCell(String.valueOf(zyl), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
    }

    /**
     * 意向询盘单
     * @param emkEnquiryEntity  意向询盘单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generateEmkEnquiryPDF(EmkEnquiryEntityA emkEnquiryEntity, List<Map<String, Object>> detailEntityList, EmkSizeEntity emkSizeEntity,List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("意  向  询  盘  单",keyFont,Element.ALIGN_CENTER));
//        document.add(createParagraph("意向订单号："+emkEnquiryEntity.getEnquiryNo(),headFont,Element.ALIGN_RIGHT));
        /*document.add(createParagraph("日期："+emkEnquiryEntity.getKdDate(),headFont,Element.ALIGN_RIGHT));
        document.add(createParagraph("生产跟单员："+emkEnquiryEntity.getDeveloperName(),headFont,Element.ALIGN_RIGHT));
        document.add(createParagraph("业务跟单员："+emkEnquiryEntity.getBusinesserName(),headFont,Element.ALIGN_RIGHT));*/

        //表格信息
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {15f,40f,60f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);
        String imagepath = "";
        Image image = null;
        if(Utils.notEmpty(emkEnquiryEntity.getCustomSampleUrl())){
            imagepath = emkEnquiryEntity.getCustomSampleUrl();
            try {
                image = Image.getInstance(imagepath);
                image.setAlignment(image.UNDERLYING);
                image.setAbsolutePosition(450,165);
                image.scaleAbsolute(180,170);
                document.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        table.addCell(createCell01("意向订单号："+emkEnquiryEntity.getEnquiryNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,3,1));

        table.addCell(createCell01("日      期："+"___"+emkEnquiryEntity.getKdDate()+"____", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,3,1));

        table.addCell(createCell01("业  务  员："+"______"+emkEnquiryEntity.getBusinesserName()+"_____", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,3,1));

        table.addCell(createCell01("生产跟单员："+"______"+emkEnquiryEntity.getDeveloperName()+"_____", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,3,1));

        table.addCell(createCell01("业务跟单员："+"______"+emkEnquiryEntity.getBusinesserName()+"_____", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,3,1));


        table.addCell(createCell01("意向大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(emkEnquiryEntity.getYsDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("距交期剩天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(emkEnquiryEntity.getLevelDays().toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,6));

        table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getGyzl(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        document.add(table);

        float[] widths2 = {15f,15f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f};
        table = createTable(widths2);

        table.addCell(createCell("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell("色号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell("尺码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,1));
        setSizeHeader(table,emkSizeEntity);

        int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,zjs=0;
        for(Map map : detailEntityList){
            table.addCell(createCell(map.get("colorName").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("colorVal").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            zjs += setSizeBody(table,map,a,b,c,d,e,f,g,h,i,j,k);
        }
        table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        setSizeEnd(table,a,b,c,d,e,f,g,h,i,j,k);
        table.addCell(createCell("总件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,1));

        document.add(table);

        //document.newPage();
        float[] widths3 = {21f,60f,21f,60f};
        table = createTable(widths3);

        if(Utils.notEmpty(emkEnquiryEntity.getOldImageUrl())){
            try {
                imagepath = emkEnquiryEntity.getOldImageUrl();
                image = Image.getInstance(imagepath);
                image.setAlignment(image.UNDERLYING);
                image.setAbsolutePosition(230,330);
                image.scaleAbsolute(180,170);
                document.add(image);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        table.addCell(createCell01("是否有原样:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("0".equals(emkEnquiryEntity.getIsHaveOld()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        table.addCell(createCell02("客户原样", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell02(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("是否有设计稿", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("0".equals(emkEnquiryEntity.getIsHaveDgr()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("是否有尺寸表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("0".equals(emkEnquiryEntity.getIsHaveSize()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("备注", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getRemark(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        document.add(table);

        table = processTable(processList);
        document.add(table);
        document.add(blankRow41);

        document.close();
    }

    PdfPTable processTable(List<Map<String, Object>> processList){
        float[] processWidths = {60f,30f,50f,50f,100f,30f};
        PdfPTable table = createTable(processWidths);

        table.addCell(createCell("流程节点", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("操作人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("开始时间", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("结束时间", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("审核意见", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("审核状态", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        for(Map map : processList){
            table.addCell(createCell(Utils.notEmpty(map.get("bpm_name")) ? map.get("bpm_name").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(map.get("create_name")) ? map.get("create_name").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(map.get("startTime")) ? map.get("startTime").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(map.get("approve_date")) ? map.get("approve_date").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(map.get("approve_advice")) ? map.get("approve_advice").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
            if(Utils.notEmpty(map.get("approve_status"))){
            }else{
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }
        }

        return table;
    }

    /**
     * 样品通知单
     * @param emkEnquiryEntity  样品通知单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generateSamplePDF(EmkSampleEntity emkSampleEntity, EmkSampleRequiredEntity emkSampleRequiredEntity, EmkPriceEntity emkPriceEntity, String gyzl, String yplx, List<Map<String, Object>> detailEntityList, EmkSizeEntity emkSizeEntity,
                                  List<EmkSampleContentEntity> sampleContentEntities0, List<EmkSampleContentEntity> sampleContentEntities1, List<EmkSampleContentEntity> sampleContentEntities2,
                                  EmkPbEntity emkPbEntity, List<EmkSampleDetailEntity> emkSampleDetailEntities0, List<EmkSampleDetailEntity> emkSampleDetailEntities1, List<EmkSampleDetailEntity> emkSampleDetailEntities2,
                                  List<EmkSampleGxEntity> emkSampleGxEntities, List<Map<String, Object>> emkSampleRanEntities, List<Map<String, Object>> emkSampleZjEntities,
                                  EmkGlEntity emkGlEntity, List<Map<String, Object>> emkSampleYinEntities,List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        if(Utils.notEmpty(emkSampleEntity)){
            document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
            document.add(createParagraph("样  品  通  知  单",keyFont,Element.ALIGN_CENTER));
        }
        if(Utils.notEmpty(emkSampleRequiredEntity)){
            document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
            document.add(createParagraph("样  品  询  盘  单",keyFont,Element.ALIGN_CENTER));

        }
        if(Utils.notEmpty(emkPriceEntity)){
            document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
            document.add(createParagraph("报  价  单",keyFont,Element.ALIGN_CENTER));
//            document.add(createParagraph("打样需求单号："+emkSampleRequiredEntity.getRequiredNo(),headFont,Element.ALIGN_RIGHT));
//            document.add(createParagraph("打样需求单日期："+emkSampleRequiredEntity.getKdDate(),headFont,Element.ALIGN_RIGHT));
        }
        PdfPTable table = null;

        //表格信息
        if(Utils.notEmpty(emkSampleEntity)){
            float[] widths = {20f,20f,20f,30f,20f,30f,20f,30f};
            table = createTable(widths);

            table.addCell(createCell01("打样通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleEntity.getKdTime(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleEntity.getDyxqdTime(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("工厂代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleEntity.getFactoryCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("样品类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(yplx, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("大货出货日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getDhjq(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("原料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getYlgg(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            table.addCell(createCell01("平方克重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleEntity.getPfkz(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            document.add(table);
        }
        if(Utils.notEmpty(emkSampleRequiredEntity)){
            float[] widths = {20f,60f,20f,30f,20f,30f,20f,30f};
            table = createTable(widths);

            table.addCell(createCell01("打样需求单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getRequiredNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,6,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getKdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getBusinesser(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("工艺类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("布面克重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getWeight(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("布面成分", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getChengf(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getYsDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkSampleRequiredEntity.getLevelDays(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("样品类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(yplx, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            document.add(table);
        }

        if(Utils.notEmpty(emkPriceEntity)){
            float[] widths = {60f,70f,60f,70f,60f,70f,60f,70f,60f,70f,60f,120f};
            table = createTable(widths);

            table.addCell(createCell01("报价单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getPirceNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,5));

            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));
            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("报价日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getKdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("报价有效期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getLimitDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("工艺类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("目的国", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getGuoJia(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("报价类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getBjlx(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("报价币种", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getBz(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getTracerName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("报价说明", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPriceEntity.getBjsm(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,9,1));

            table.addCell(createCell02("是否有原样", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPriceEntity.getIsHaveOld()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell02(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,9,1));

            String imagepath = createPdf.class.getClassLoader().getResource("/../..").toURI().getPath();
            Image image = null;
            if(Utils.notEmpty(emkPriceEntity.getCustomSampleUrl())){
                try {
                    imagepath = imagepath+emkPriceEntity.getCustomSampleUrl();
                    image = Image.getInstance(imagepath);
                    image.setAlignment(image.UNDERLYING);
                    image.setAbsolutePosition(560,395);
                    image.scaleAbsolute(155,150);
                    document.add(image);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            imagepath = createPdf.class.getClassLoader().getResource("/../..").toURI().getPath();
            if(Utils.notEmpty(emkPriceEntity.getOldImageUrl())){
                try {
                    imagepath = imagepath+emkPriceEntity.getOldImageUrl();
                    image = Image.getInstance(imagepath);
                    image.setAlignment(image.UNDERLYING);
                    image.setAbsolutePosition(300,225);
                    image.scaleAbsolute(180,160);
                    document.add(image);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            table.addCell(createCell01("是否有设计稿", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPriceEntity.getIsHaveDgr()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,9,1));

            table.addCell(createCell01("是否有尺寸表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPriceEntity.getIsHaveSize()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,9,1));

            table.addCell(createCell01("是否需要测试", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPriceEntity.getIsTest()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("测试说明", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(emkPriceEntity.getTestDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));

            table.addCell(createCell01("是否需要验厂", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPriceEntity.getIsChfactory()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,9,1));

            table.addCell(createCell01("是否需要验货", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPriceEntity.getIsChkhuo()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,9,1));

            table.addCell(createCell01("成分要求", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(emkPriceEntity.getChengf(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));

            table.addCell(createCell01("克重要求", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(emkPriceEntity.getWeight(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));

            table.addCell(createCell01("目标价位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            if(Utils.notEmpty(emkPriceEntity.getTargetPrice())){
                table.addCell(createCell01(String.valueOf(emkPriceEntity.getTargetPrice()), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));
            }else{
                table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));
            }


            table.addCell(createCell01("预计数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            if(Utils.notEmpty(emkPriceEntity.getEvlateTotal())){
                table.addCell(createCell01(String.valueOf(emkPriceEntity.getEvlateTotal()), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));
            }else{
                table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));
            }

            table.addCell(createCell01("尺码范围:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(emkPriceEntity.getSizeFawei(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,10,1));

            document.add(table);
        }

        if(Utils.notEmpty(emkSampleEntity)){
            float[] widths2 = {15f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f};
            table = createTable(widths2);
        }
        if(Utils.notEmpty(emkSampleRequiredEntity)){
            float[] widths2 = {15f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f};
            table = createTable(widths2);
        }

        if(Utils.notEmpty(emkSampleEntity) || Utils.notEmpty(emkSampleRequiredEntity)){
            table.addCell(createCell("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
            if(Utils.notEmpty(emkSampleRequiredEntity)){
                table.addCell(createCell("色号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
            }
            table.addCell(createCell("尺码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,1));
            setSizeHeader(table,emkSizeEntity);

            int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,zjs=0;
            for(Map map : detailEntityList){
                table.addCell(createCell(map.get("colorName") != null ? map.get("colorName").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                if(Utils.notEmpty(emkSampleRequiredEntity)){
                    table.addCell(createCell(map.get("colorVal") != null ? map.get("colorVal").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                }
                zjs += setSizeBody(table,map,a,b,c,d,e,f,g,h,i,j,k);
            }
            table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            setSizeEnd(table,a,b,c,d,e,f,g,h,i,j,k);
            table.addCell(createCell("总件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,1));

            document.add(table);
        }

        if(Utils.notEmpty(emkSampleEntity)){
            document.add(createParagraph("一. 织造（无缝）：",headFont,Element.ALIGN_LEFT));
            float[] widths3 = {70f,300f};
            table = createTable(widths3);
            for(int il = 0 ; il < sampleContentEntities0.size() ; il++){
                table.addCell(createCell(String.valueOf((il+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(sampleContentEntities0.get(il).getContent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
            }
            table.addCell(createCell("所用机台尺寸：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleEntity.getSyjtcc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

            table.addCell(createCell("下机重量：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleEntity.getXjzl(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

            table.addCell(createCell("下机尺寸：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleEntity.getXjcc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

            table.addCell(createCell("密度：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleEntity.getMd(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

            table.addCell(createCell("用料：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleEntity.getYlgg(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

            table.addCell(createCell("单件织造时间：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkSampleEntity.getDjzjsj(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

            document.add(table);

            document.add(createParagraph("二.染色：",headFont,Element.ALIGN_LEFT));
            float[] widths4 = {30f,30f,30f,30f,30f,30f};
            table = createTable(widths4);

            table.addCell(createCell("是否有标准色样：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsColorStrand()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell("是否有潘通色号：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsPan()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell("是否有参考样：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsCan()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            for(int il = 0 ; il < sampleContentEntities1.size() ; il++){
                table.addCell(createCell(String.valueOf((il+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(sampleContentEntities0.get(il).getContent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));
            }
            document.add(table);

            document.add(createParagraph("三.缝制：",headFont,Element.ALIGN_LEFT));
            float[] widths5 = {60f,20f,60f,20f,60f,20f,60f,20f};
            table = createTable(widths5);
            table.addCell(createCell("是否有设计稿：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsHaveDgr()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell("是否有原样：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsHaveOld()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell("是否有物料明细：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsDetail()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell("是否有尺寸表：", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkSampleEntity.getIsHaveSize()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            for(int il = 0 ; il < sampleContentEntities0.size() ; il++){
                table.addCell(createCell(String.valueOf((il+1)), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(sampleContentEntities0.get(il).getContent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,7,1));
            }
            document.add(table);
        }


        document.add(createParagraph("原料面料：",headFont,Element.ALIGN_LEFT));
        float[] widths6 = {20f,60f,60f,40f,40f,30f,60f,40f,25f,40f,60f};
        table = createTable(widths6);


        setYlmlData(table,emkSampleDetailEntities0,"0");
        document.add(table);

        document.add(createParagraph("缝制辅料：",headFont,Element.ALIGN_LEFT));
        float[] widths7 = {20f,60f,60f,60f,50f,30f,60f,40f,25f,40f,60f};
        table = createTable(widths7);
        setFzflData(table,emkSampleDetailEntities1,"0");
        document.add(table);

        document.add(createParagraph("包装辅料：",headFont,Element.ALIGN_LEFT));
        float[] widths8 = {20f,60f,60f,60f,50f,30f,60f,40f,25f,40f,60f};
        table = createTable(widths8);
        setBzflData(table,emkSampleDetailEntities2,"0");
        document.add(table);

        if(Utils.notEmpty(emkSampleGxEntities)){
            document.add(createParagraph("人工：",headFont,Element.ALIGN_LEFT));
            float[] widths9 = {25f,80f,50f,50f,50f,50f};
            table = createTable(widths9);
            setRgData(table,emkSampleGxEntities);
            document.add(table);
        }

        if(Utils.notEmpty(emkSampleYinEntities)){
            document.add(createParagraph("印花：",headFont,Element.ALIGN_LEFT));
            float[] widths10 = {25f,80f,50f,50f};
            table = createTable(widths10);
            setYhData(table,emkSampleYinEntities);
            document.add(table);
        }
        if(Utils.notEmpty(emkSampleRanEntities)){
            document.add(createParagraph("染色：",headFont,Element.ALIGN_LEFT));
            float[] widths11 = {25f,80f,50f,50f,50f};
            table = createTable(widths11);
            setRanData(table,emkSampleRanEntities);
            document.add(table);
        }
        if(Utils.notEmpty(emkPriceEntity)){
            document.add(createParagraph("助剂：",headFont,Element.ALIGN_LEFT));
            float[] widths11 = {25f,80f,50f,50f,50f};
            table = createTable(widths11);
            setZjData(table,emkSampleZjEntities);
            document.add(table);

            document.add(createParagraph("管理：",headFont,Element.ALIGN_LEFT));
            float[] widths12 = {30f,60f,30f,60f,30f,60f,30f,60f};
            table = createTable(widths12);

            if(Utils.notEmpty(emkGlEntity)){
                table.addCell(createCell("场地", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getPlace()) ? String.valueOf(emkGlEntity.getPlace()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("运输费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getTranCost()) ? String.valueOf(emkGlEntity.getTranCost()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("设备", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getEquip()) ? String.valueOf(emkGlEntity.getEquip()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("管理费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getGlf()) ? String.valueOf(emkGlEntity.getGlf()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

                table.addCell(createCell("电费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getDianfei()) ? String.valueOf(emkGlEntity.getDianfei()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("车辆使用费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getCarCost()) ? String.valueOf(emkGlEntity.getCarCost()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("水费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getWaterCost()) ? String.valueOf(emkGlEntity.getWaterCost()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("其他", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell(Utils.notEmpty(emkGlEntity.getOther()) ? String.valueOf(emkGlEntity.getOther()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }else{
                table.addCell(createCell("场地", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("运输费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("设备", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("管理费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

                table.addCell(createCell("电费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("车辆使用费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("水费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("其他", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
                table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            }

            document.add(table);

            document.add(createParagraph("报价：",headFont,Element.ALIGN_LEFT));
            float[] widths13 = {60f,60f,60f,60f,60f,60f,60f,60f,60f,60f,60f,60f,60f};
            table = createTable(widths13);

            table.addCell(createCell("测试费", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getTestMoney()) ? String.valueOf(emkPriceEntity.getTestMoney()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("利润", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getProfit()) ? String.valueOf(emkPriceEntity.getProfit()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("不可预见成本", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getUnableMoney()) ? String.valueOf(emkPriceEntity.getUnableMoney()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("税收", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getTax()) ? String.valueOf(emkPriceEntity.getTax()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("面料材料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("缝制材料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("包装材料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("人工", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("染色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("助剂", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("印花", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("管理", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("测试", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("利润", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("不可预见成本", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("税收", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("总计", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumYl()) ? String.valueOf(emkPriceEntity.getSumYl()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumFeng()) ? String.valueOf(emkPriceEntity.getSumFeng()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumBao()) ? String.valueOf(emkPriceEntity.getSumBao()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumRg()) ? String.valueOf(emkPriceEntity.getSumRg()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumRan()) ? String.valueOf(emkPriceEntity.getSumRan()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumZj()) ? String.valueOf(emkPriceEntity.getSumZj()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumYin()) ? String.valueOf(emkPriceEntity.getSumYin()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumGl()) ? String.valueOf(emkPriceEntity.getSumGl()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getTestMoney()) ? String.valueOf(emkPriceEntity.getTestMoney()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getProfit()) ? String.valueOf(emkPriceEntity.getProfit()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getUnableMoney()) ? String.valueOf(emkPriceEntity.getUnableMoney()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getTax()) ? String.valueOf(emkPriceEntity.getTax()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumMoney()) ? String.valueOf(emkPriceEntity.getSumMoney()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell("目标价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getTargetPrice()) ? String.valueOf(emkPriceEntity.getTargetPrice()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("外币价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getSumWb()) ? String.valueOf(emkPriceEntity.getSumWb()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("币种", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkPriceEntity.getBz(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell("汇率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getHuilv()) ? String.valueOf(emkPriceEntity.getHuilv()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));
            table.addCell(createCell("汇率日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(emkPriceEntity.getHuilvDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell("利润率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getMaoRate()) ? String.valueOf(emkPriceEntity.getMaoRate()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("买家同意价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getGysArgeePrice()) ? String.valueOf(emkPriceEntity.getGysArgeePrice()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            table.addCell(createCell("卖家同意价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(Utils.notEmpty(emkPriceEntity.getCusArgeePrice()) ? String.valueOf(emkPriceEntity.getCusArgeePrice()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,12,1));

            document.add(table);
        }
        table = processTable(processList);
        document.add(table);
        document.close();
    }

    /**
     * 需求开发单
     * @param emkSampleDetailEntities  明细数据
     * @return
     */
    public void generateXqkfdPDF(String gyzl,EmkMaterialEntity emkMaterialEntity, EmkAccessoriesEntity emkAccessoriesEntity, EmkPackEntity emkPackEntity,
                                 List<EmkSampleDetailEntity2> emkSampleDetailEntities,List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //表格信息
        float[] widths = {30f,60f,30f,60f,30f,60f,100f};
        PdfPTable table = createTable(widths);
        String imagepath = createPdf.class.getClassLoader().getResource("/../..").toURI().getPath();
        Image image = null;
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        if(Utils.notEmpty(emkMaterialEntity)){
            document.add(createParagraph("原料面料需求开发单",keyFont,Element.ALIGN_CENTER));
            document.add(createParagraph("原料面料需求开发单编号："+emkMaterialEntity.getMaterialNo(),headFont,Element.ALIGN_RIGHT));

            table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("样品通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDeveloper(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDyxqdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            imagepath = imagepath + emkMaterialEntity.getCustomSampleUrl();

            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距样品交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialEntity.getLeaveYpjqDays()) ? String.valueOf(emkMaterialEntity.getLeaveYpjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("是否已有订单", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkMaterialEntity.getIsOrder()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialEntity.getLeaveDhjqDays()) ? String.valueOf(emkMaterialEntity.getLeaveDhjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("是否现有适合原料面料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkMaterialEntity.getIsHave()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

            table.addCell(createCell01("需求开发交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("完成时间剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialEntity.getLeaveFinishDays()) ? String.valueOf(emkMaterialEntity.getLeaveFinishDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样原因", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleReason(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

        }
        if(Utils.notEmpty(emkAccessoriesEntity)){
            document.add(createParagraph("缝制辅料需求开发单",keyFont,Element.ALIGN_CENTER));
            document.add(createParagraph("缝制辅料需求开发单编号："+emkAccessoriesEntity.getMaterialNo(),headFont,Element.ALIGN_RIGHT));

            table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("样品通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDeveloper(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDyxqdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            imagepath = imagepath + emkAccessoriesEntity.getCustomSampleUrl();

            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距样品交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkAccessoriesEntity.getLeaveYpjqDays()) ? String.valueOf(emkAccessoriesEntity.getLeaveYpjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("是否已有订单", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkAccessoriesEntity.getIsOrder()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkAccessoriesEntity.getLeaveDhjqDays()) ? String.valueOf(emkAccessoriesEntity.getLeaveDhjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("是否现有适合原料面料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkAccessoriesEntity.getIsHave()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

            table.addCell(createCell01("需求开发交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("完成时间剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01(Utils.notEmpty(emkAccessoriesEntity.getLeaveFinishDays()) ? String.valueOf(emkAccessoriesEntity.getLeaveFinishDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样原因", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleReason(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
        }
        if(Utils.notEmpty(emkPackEntity)){
            document.add(createParagraph("包装辅料需求开发单",keyFont,Element.ALIGN_CENTER));
            document.add(createParagraph("包装辅料需求开发单编号："+emkPackEntity.getParkNo(),headFont,Element.ALIGN_RIGHT));

            table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("样品通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDeveloper(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDyxqdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            imagepath = imagepath + emkPackEntity.getCustomSampleUrl();

            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距样品交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkPackEntity.getLeaveYpjqDays()) ? String.valueOf(emkPackEntity.getLeaveYpjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("是否已有订单", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkPackEntity.getIsOrder()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkPackEntity.getLeaveDhjqDays()) ? String.valueOf(emkPackEntity.getLeaveDhjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("是否现有适合原料面料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("0".equals(emkPackEntity.getIsHave()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

            table.addCell(createCell01("需求开发交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("完成时间剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));


        }

        if(Utils.notEmpty(imagepath)){
            try {
                image = Image.getInstance(imagepath);
                image.setAlignment(image.UNDERLYING);
                image.setAbsolutePosition(540,370);
                image.scaleAbsolute(130,155);
                document.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        document.add(table);

        float[] widths2 = {20f,50f,40f,40f,30f,30f,30f,30f,30f,30f,30f,30f,30f,20f,30f,30f,30f};
        table = createTable(widths2);
        table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        if(Utils.notEmpty(emkMaterialEntity)){
            table.addCell(createCell01("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if(Utils.notEmpty(emkAccessoriesEntity)){
            table.addCell(createCell01("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if(Utils.notEmpty(emkPackEntity)){
            table.addCell(createCell01("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        setXqkfdData(table,emkSampleDetailEntities,"0");
        document.add(table);

        table = processTable(processList);
        document.add(table);
        document.close();
    }

    /**
     * 入库单
     * @param emkSampleDetailEntities  明细数据
     * @return
     */
    public void generateRkdPDF(EmkMInStorageEntity emkMInStorageEntity,List<EmkMInStorageDetailEntity> emkMInStorageDetailEntityList, List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //表格信息
        float[] widths = {30f,80f,30f,60f,30f,60f,30f,60f};
        PdfPTable table = createTable(widths);
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        if(Utils.notEmpty(emkMInStorageEntity)){
            if("0".equals(emkMInStorageEntity.getType())){
                document.add(createParagraph("原料面料入库申请单",keyFont,Element.ALIGN_CENTER));
            }else if("1".equals(emkMInStorageEntity.getType())){
                document.add(createParagraph("缝制辅料入库申请单",keyFont,Element.ALIGN_CENTER));
            }else if("2".equals(emkMInStorageEntity.getType())){
                document.add(createParagraph("包装辅料入库申请单",keyFont,Element.ALIGN_CENTER));
            }

            table.addCell(createCell01("申请人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getAppler(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            table.addCell(createCell01("入库人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getRker(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            table.addCell(createCell01("入库日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getKdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));

            table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));

            table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getOrderNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getTotal(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        document.add(table);


        float[] widths1 = {30f,60f,30f,60f};
        table = createTable(widths1);
        table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getBusinesser(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getBusinesserDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getTracerDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("采购员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getCaigouer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getCaigouerDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        document.add(table);

        float[] widths2 = {20f,100f,40f,40f,120f,40f,40f,50f,40f,50f,40f};
        table = createTable(widths2);

        table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("0".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if("1".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if("2".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("采购合同号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("0".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("原料面料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if("1".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if("2".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01("采购数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("申请入库数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("入库日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("实际入库数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("出库日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));


        if(Utils.notEmpty(emkMInStorageDetailEntityList)) {
            for (int i1 = 0; i1 < emkMInStorageDetailEntityList.size(); i1++) {
                EmkMInStorageDetailEntity emkMInStorageDetailEntity = emkMInStorageDetailEntityList.get(i1);
                table.addCell(createCell(String.valueOf((i1 + 1)), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getProNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getProZnName(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getGysCode(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getHtNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getBrand(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getInTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getKdDate(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getActualTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getOutDate(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
            }
        }
        document.add(table);

        table = processTable(processList);
        document.add(table);
        document.close();
    }

    /**
     * 出库单
     * @param emkSampleDetailEntities  明细数据
     * @return
     */
    public void generateCkdPDF(EmkMOutStorageEntity emkMInStorageEntity, List<EmkMInStorageDetailEntity> emkMInStorageDetailEntityList, List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //表格信息
        float[] widths = {30f,80f,30f,60f,30f,60f,30f,60f};
        PdfPTable table = createTable(widths);
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        if(Utils.notEmpty(emkMInStorageEntity)){
            if("0".equals(emkMInStorageEntity.getType())){
                document.add(createParagraph("原料面料出库申请单",keyFont,Element.ALIGN_CENTER));
            }else if("1".equals(emkMInStorageEntity.getType())){
                document.add(createParagraph("缝制辅料出库申请单",keyFont,Element.ALIGN_CENTER));
            }else if("2".equals(emkMInStorageEntity.getType())){
                document.add(createParagraph("包装辅料出库申请单",keyFont,Element.ALIGN_CENTER));
            }

            table.addCell(createCell01("申请人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getAppler(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            table.addCell(createCell01("领料人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getLler(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            table.addCell(createCell01("出库日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getKdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));

            table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));

            table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getOrderNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getTotal(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMInStorageEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        document.add(table);

        float[] widths1 = {30f,60f,30f,60f};
        table = createTable(widths1);
        table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getBusinesser(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getBusinesserDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getTracerDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("采购员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getCaigouer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMInStorageEntity.getCaigouerDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));


        float[] widths2 = {20f,100f,40f,40f,120f,40f,40f,50f,40f,50f,40f};
        table = createTable(widths2);

        table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("0".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        }else if("1".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if("2".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("采购合同号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("0".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("原料面料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }else if("1".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        }else if("2".equals(emkMInStorageEntity.getType())){
            table.addCell(createCell01("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01("采购数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("申请出库数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("出库日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("实际出库数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("出库日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));


        if(Utils.notEmpty(emkMInStorageDetailEntityList)) {
            for (int i1 = 0; i1 < emkMInStorageDetailEntityList.size(); i1++) {
                EmkMInStorageDetailEntity emkMInStorageDetailEntity = emkMInStorageDetailEntityList.get(i1);
                table.addCell(createCell(String.valueOf((i1 + 1)), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getProNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getProZnName(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getGysCode(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getHtNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getBrand(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getInTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getKdDate(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getActualTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkMInStorageDetailEntity.getOutDate(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
            }
        }
        document.add(table);

        table = processTable(processList);
        document.add(table);
        document.close();
    }

    /**
     * 付费开发单
     * @param emkSampleDetailEntities  明细数据
     * @return
     */
    public void generateFfkfdPDF(String gyzl,EmkMaterialEntity emkMaterialEntity, EmkAccessoriesEntity emkAccessoriesEntity, EmkPackEntity emkPackEntity, List<EmkSampleDetailEntity> emkSampleDetailEntities) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //表格信息
        float[] widths = {30f,60f,30f,60f,30f,100f};
        PdfPTable table = createTable(widths);
        String imagepath = createPdf.class.getClassLoader().getResource("/../..").toURI().getPath();
        Image image = null;
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        if(Utils.notEmpty(emkMaterialEntity)){
            document.add(createParagraph("原料面料需求开发单",keyFont,Element.ALIGN_CENTER));
            document.add(createParagraph("原料面料需求开发单编号："+emkMaterialEntity.getMaterialNo(),headFont,Element.ALIGN_RIGHT));

            table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("样品通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDeveloper(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDyxqdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            imagepath = imagepath + emkMaterialEntity.getCustomSampleUrl();

            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距样品交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialEntity.getLeaveYpjqDays()) ? String.valueOf(emkMaterialEntity.getLeaveYpjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("是否已有订单", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkMaterialEntity.getIsOrder()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialEntity.getLeaveDhjqDays()) ? String.valueOf(emkMaterialEntity.getLeaveDhjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("是否现有适合原料面料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("0".equals(emkMaterialEntity.getIsHave()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));

            table.addCell(createCell01("需求开发交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("完成时间剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialEntity.getLeaveFinishDays()) ? String.valueOf(emkMaterialEntity.getLeaveFinishDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样原因", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialEntity.getSampleReason(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

        }
        if(Utils.notEmpty(emkAccessoriesEntity)){
            document.add(createParagraph("缝制辅料需求开发单",keyFont,Element.ALIGN_CENTER));
            document.add(createParagraph("缝制辅料需求开发单编号："+emkAccessoriesEntity.getMaterialNo(),headFont,Element.ALIGN_RIGHT));

            table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("样品通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDeveloper(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDyxqdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            imagepath = imagepath + emkAccessoriesEntity.getCustomSampleUrl();

            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距样品交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkAccessoriesEntity.getLeaveYpjqDays()) ? String.valueOf(emkAccessoriesEntity.getLeaveYpjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("是否已有订单", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkAccessoriesEntity.getIsOrder()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkAccessoriesEntity.getLeaveDhjqDays()) ? String.valueOf(emkAccessoriesEntity.getLeaveDhjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("是否现有适合原料面料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("0".equals(emkAccessoriesEntity.getIsHave()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));

            table.addCell(createCell01("需求开发交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("完成时间剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01(Utils.notEmpty(emkAccessoriesEntity.getLeaveFinishDays()) ? String.valueOf(emkAccessoriesEntity.getLeaveFinishDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));

            table.addCell(createCell01("打样原因", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkAccessoriesEntity.getSampleReason(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));
        }
        if(Utils.notEmpty(emkPackEntity)){
            document.add(createParagraph("包装辅料需求开发单",keyFont,Element.ALIGN_CENTER));
            document.add(createParagraph("包装辅料需求开发单编号："+emkPackEntity.getParkNo(),headFont,Element.ALIGN_RIGHT));

            table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("样品通知单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));

            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样通知单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(gyzl, textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getTracer(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getXqdh(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDeveloper(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("打样需求单日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDyxqdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));
            imagepath = imagepath + emkPackEntity.getCustomSampleUrl();

            table.addCell(createCell01("版次", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getVersion(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

            table.addCell(createCell01("样品交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距样品交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkPackEntity.getLeaveYpjqDays()) ? String.valueOf(emkPackEntity.getLeaveYpjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

            table.addCell(createCell01("是否已有订单", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("0".equals(emkPackEntity.getIsOrder()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01(Utils.notEmpty(emkPackEntity.getLeaveDhjqDays()) ? String.valueOf(emkPackEntity.getLeaveDhjqDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("是否现有适合原料面料", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));
            table.addCell(createCell01("0".equals(emkPackEntity.getIsHave()) ? "是":"否", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,4,1));

            table.addCell(createCell01("需求开发交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkPackEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        }


        if(Utils.notEmpty(imagepath)){
            try {
                image = Image.getInstance(imagepath);
                image.setAlignment(image.UNDERLYING);
                image.setAbsolutePosition(450,305);
                image.scaleAbsolute(180,170);
                document.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        float[] widths2 = {20f,50f,40f,40f,30f,30f,30f,30f,30f,30f,30f,30f,30f,20f,30f,30f,30f};
        PdfPTable table2 = createTable(widths);

        table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if(Utils.notEmpty(emkMaterialEntity)){
            table.addCell(createCell01("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if(Utils.notEmpty(emkMaterialEntity)){
            table.addCell(createCell01("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if(Utils.notEmpty(emkMaterialEntity)){
            table.addCell(createCell01("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01("单件用量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("捻向", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("批号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("幅宽", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("克重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("成分", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("价格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("单位", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("损耗率", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("备注", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("供应商", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        if(Utils.notEmpty(emkSampleDetailEntities)) {
            for (int i1 = 0; i1 < emkSampleDetailEntities.size(); i1++) {
                EmkSampleDetailEntity emkSampleDetailEntity = emkSampleDetailEntities.get(i1);
                table.addCell(createCell(String.valueOf((i1 + 1)), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getProZnName(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getProNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getBrand(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(Utils.notEmpty(emkSampleDetailEntity.getYongliang()) ? String.valueOf(emkSampleDetailEntity.getYongliang()):"0", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getDirection(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getBetchNum(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getWidth(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getColor(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getWeight(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getChengf(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getSignTotal(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getSignPrice(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getUnit(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(Utils.notEmpty(emkSampleDetailEntity.getSunhaoPrecent()) ? String.valueOf(emkSampleDetailEntity.getSunhaoPrecent()):"0", textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getRemark(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
                table.addCell(createCell(emkSampleDetailEntity.getGysCode(), textfont_H, Element.ALIGN_MIDDLE, Element.ALIGN_CENTER, 1, 1));
            }
        }
        document.add(table);
        document.close();
    }

    /**
     * 意向询盘单
     * @param emkEnquiryEntity  意向询盘单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generateSyxqdPDF(EmkSampleColorEntity sampleColorEntity, List<Map<String, Object>> detailEntityList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("色  样  需  求  单",keyFont,Element.ALIGN_CENTER));
        document.add(createParagraph("色样需求单编号："+sampleColorEntity.getSampleNo(),headFont,Element.ALIGN_RIGHT));

        //表格信息
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {15f,40f,15f,40f,15f,40f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);


        table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(sampleColorEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));


        document.add(table);
        document.close();
    }

    /**
     * 订单
     * @param emkProOrderEntity  订单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generateOrderPDF(EmkProOrderEntity emkProOrderEntity,List<Map<String, Object>> detailEntityList, EmkSizeEntity emkSizeEntity,
                                 List<EmkProOrderBoxEntity> emkProOrderBoxEntityList,List<EmkProOrderBarcodeEntity> emkProOrderBarcodeEntityList,
                                 List<EmkSampleDetailEntity> emkSampleDetailEntities0, List<EmkSampleDetailEntity> emkSampleDetailEntities1,
                                 List<EmkSampleDetailEntity> emkSampleDetailEntities2, List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("订  单  表",keyFont,Element.ALIGN_CENTER));
        PdfPTable table = null;


        float[] widths = {20f,30f,20f,30f,20f,30f,20f,30f,90f};
        table = createTable(widths);

        table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,1,1));
        table.addCell(createCell01(emkProOrderEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));


        table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,1,1));
        table.addCell(createCell01(emkProOrderEntity.getOrderNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("交货时间", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,1,1));
        table.addCell(createCell01(emkProOrderEntity.getRecevieDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

        table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getSumTotal()) ? String.valueOf(emkProOrderEntity.getSumTotal()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("供应商", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getGys(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("客户单价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getPrice()) ? String.valueOf(emkProOrderEntity.getPrice()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("供应商单价", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getGysPrice()) ? String.valueOf(emkProOrderEntity.getGysPrice()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getTracerName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getColor(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("客户金额", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getCusJin()) ? String.valueOf(emkProOrderEntity.getCusJin()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("供应商金额", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getGysJin()) ? String.valueOf(emkProOrderEntity.getCusJin()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getDeveloperName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("尺码范围", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getSizeFw(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,8,1));

        document.add(table);

        String imagepath = createPdf.class.getClassLoader().getResource("/../..").toURI().getPath();
        Image image = null;
        if(Utils.notEmpty(emkProOrderEntity.getCustomSampleUrl())){
            try {
                imagepath = imagepath+emkProOrderEntity.getCustomSampleUrl();
                image = Image.getInstance(imagepath);
                image.setAlignment(image.UNDERLYING);
                image.setAbsolutePosition(525,395);
                image.scaleAbsolute(145,140);
                document.add(image);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        float[] widths9 = {20f,30f,20f,30f,20f,30f,20f,30f};
        table = createTable(widths9);
        table.addCell(createCell01("包装方式", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getBzfs(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        table.addCell(createCell01("包装效果图", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,5));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,5));

        table.addCell(createCell01("单件", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getOne(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        table.addCell(createCell01("胶袋", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getPolybag(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        table.addCell(createCell01("装箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getBoxup(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getOrderNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("出货日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkProOrderEntity.getChDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("单件毛重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getOneWeightMao()) ? String.valueOf(emkProOrderEntity.getOneWeightMao()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("单件净重", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getOneWeightJz()) ? String.valueOf(emkProOrderEntity.getOneWeightJz()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("预计中期验货日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(emkProOrderEntity.getZqyhDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

        table.addCell(createCell01("预计尾期验货日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(emkProOrderEntity.getWqyhDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

        table.addCell(createCell01("船样状态", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(emkProOrderEntity.getCystate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

        table.addCell(createCell01("实际出厂日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(emkProOrderEntity.getOutDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

        table.addCell(createCell01("距交货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell01(Utils.notEmpty(emkProOrderEntity.getLevelDays()) ? String.valueOf(emkProOrderEntity.getLevelDays()):"0", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,6,1));

        document.add(table);
        imagepath = createPdf.class.getClassLoader().getResource("/../..").toURI().getPath();
        image = null;
        if(Utils.notEmpty(emkProOrderEntity.getBoxImageUrl())){
            try {
                imagepath = imagepath+emkProOrderEntity.getBoxImageUrl();
                image = Image.getInstance(imagepath);
                image.setAlignment(image.UNDERLYING);
                image.setAbsolutePosition(490,210);
                image.scaleAbsolute(170,150);
                document.add(image);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        float[] widths2 = {8f,15f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f,8f};
        table = createTable(widths2);
        table.addCell(createCell("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell("尺码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,1));
        setSizeHeader(table,emkSizeEntity);

        int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0,k=0,zjs=0;
        int ii = 0;
        for(Map map : detailEntityList){
            table.addCell(createCell(String.valueOf(ii+1) , textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("colorName") != null ? map.get("colorName").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            zjs += setSizeBody(table,map,a,b,c,d,e,f,g,h,i,j,k);
            ii++;
        }
        table.addCell(createCell("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        setSizeEnd(table,a,b,c,d,e,f,g,h,i,j,k);

        table.addCell(createCell("总件数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
        table.addCell(createCell(String.valueOf(zjs), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,11,1));

        document.add(table);

        if(Utils.notEmpty(emkSampleDetailEntities0)){
            document.add(createParagraph("原料面料：",headFont,Element.ALIGN_LEFT));
            float[] widths6 = {25f,60f,60f,40f,50f,40f,50f,60f,40f,25f,50f,60f,40f,40f};
            table = createTable(widths6);
            setYlmlData(table,emkSampleDetailEntities0,"1");
            document.add(table);
        }
        if(Utils.notEmpty(emkSampleDetailEntities1)){
            document.add(createParagraph("缝制辅料：",headFont,Element.ALIGN_LEFT));
            float[] widths7 = {25f,60f,60f,50f,50f,40f,60f,40f,25f,50f,60f,40f,40f};
            table = createTable(widths7);
            setFzflData(table,emkSampleDetailEntities1,"1");
            document.add(table);
        }
        if(Utils.notEmpty(emkSampleDetailEntities2)){
            document.add(createParagraph("包装辅料：",headFont,Element.ALIGN_LEFT));
            float[] widths8 = {25f,60f,60f,50f,50f,40f,60f,40f,25f,50f,60f,40f,40f};
            table = createTable(widths8);
            setBzflData(table,emkSampleDetailEntities2,"1");
            document.add(table);
        }

        document.add(createParagraph("洗水标条码：",headFont,Element.ALIGN_LEFT));
        float[] widths10 = {25f,60f,30f,100f,80f};
        table = createTable(widths10);
        setBarCodeData(table,emkProOrderBarcodeEntityList,"0");
        document.add(table);

        document.add(createParagraph("胶袋贴条码：",headFont,Element.ALIGN_LEFT));
        table = createTable(widths10);
        setBarCodeData(table,emkProOrderBarcodeEntityList,"1");
        document.add(table);

        document.add(createParagraph("箱贴条码：",headFont,Element.ALIGN_LEFT));
        table = createTable(widths10);
        setBarCodeData(table,emkProOrderBarcodeEntityList,"2");
        document.add(table);


        document.add(createParagraph("单色单码装：",headFont,Element.ALIGN_LEFT));
        float[] widths11 = {30f,30f,30f,30f,30f,30f,30f,30f,30f,30f,30f};
        table = createTable(widths11);
        setBoxData(table,emkProOrderBoxEntityList,"0");
        document.add(table);

        document.add(createParagraph("单色混码装：",headFont,Element.ALIGN_LEFT));
        table = createTable(widths11);
        setBoxData(table,emkProOrderBoxEntityList,"1");
        document.add(table);

        document.add(createParagraph("混色单码装：",headFont,Element.ALIGN_LEFT));
        table = createTable(widths11);
        setBoxData(table,emkProOrderBoxEntityList,"2");
        document.add(table);

        document.add(createParagraph("混色混码装：",headFont,Element.ALIGN_LEFT));
        table = createTable(widths11);
        setBoxData(table,emkProOrderBoxEntityList,"3");
        document.add(table);

        table = processTable(processList);
        document.add(table);
        document.close();
    }

    /**
     * 预采购合同单
     * @param emkProOrderEntity  订单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generatenMatiralRequirePDF(EmkMaterialRequiredEntity emkMaterialRequiredEntity,String gyzl,List<EmkSampleDetailEntity2> emkSampleDetailEntities, List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        PdfPTable table = null;
        float[] widths = {40f,60f,40f,60f,40f,60f};
        table = createTable(widths);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        if("0".equals(emkMaterialRequiredEntity.getType())){
            document.add(createParagraph("原料面料采购需求单",keyFont,Element.ALIGN_CENTER));
            table.addCell(createCell01("原料面料采购需求单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,5,1));
        }
        if("1".equals(emkMaterialRequiredEntity.getType())){
            document.add(createParagraph("缝制辅料采购需求单",keyFont,Element.ALIGN_CENTER));
            table.addCell(createCell01("缝制辅料采购需求单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,5,1));
        }
        if("2".equals(emkMaterialRequiredEntity.getType())){
            document.add(createParagraph("包装辅料采购需求单",keyFont,Element.ALIGN_CENTER));
            table.addCell(createCell01("包装辅料采购需求单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_RIGHT,5,1));
        }
        table.addCell(createCell01(emkMaterialRequiredEntity.getMaterialNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getOrderNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("工艺类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getTracerName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkMaterialRequiredEntity.getSumTotal()) ? String.valueOf(emkMaterialRequiredEntity.getSumTotal()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("生产跟单员:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getDeveloperName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("采购需求单提交日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getKdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialRequiredEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

        document.add(table);

        float[] widths2 = {20f,50f,40f,40f,30f,30f,30f,30f,30f,30f,30f,30f,30f,20f,30f,30f,30f,30f};
        table = createTable(widths2);
        table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        if("0".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("2".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        setXqkfdData(table,emkSampleDetailEntities,"1");
        document.add(table);

        float[] widths3 = {60f,150f};
        table = createTable(widths);
        if("0".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("原料面料交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("缝制辅料交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("2".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("包装辅料交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01(emkMaterialRequiredEntity.getYpjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        if("0".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("距离缝制辅料交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("距离原料面料交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("2".equals(emkMaterialRequiredEntity.getType())){
            table.addCell(createCell01("距离包装辅料交期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        table.addCell(createCell01(Utils.notEmpty(emkMaterialRequiredEntity.getLeaveYpjqDays()) ? String.valueOf(emkMaterialRequiredEntity.getLeaveYpjqDays()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("距大货期剩余天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(Utils.notEmpty(emkMaterialRequiredEntity.getLeaveDhjqDays()) ? String.valueOf(emkMaterialRequiredEntity.getLeaveDhjqDays()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        document.add(table);

        table = processTable(processList);
        document.add(table);
        document.close();
    }

    /**
     * 预采购合同单
     * @param emkProOrderEntity  订单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generatenMatiralPactPDF(EmkMaterialPactEntity emkMaterialPactEntity, String gyzl, List<EmkSampleDetailEntity2> emkSampleDetailEntities, List<Map<String, Object>> processList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        PdfPTable table = null;
        float[] widths = {40f,60f,40f,60f,40f,60f,40f,60f};
        table = createTable(widths);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        if("0".equals(emkMaterialPactEntity.getFlag())){
            if("0".equals(emkMaterialPactEntity.getType())){
                document.add(createParagraph("原料面料预采购合同单",keyFont,Element.ALIGN_CENTER));
            }
            if("1".equals(emkMaterialPactEntity.getType())){
                document.add(createParagraph("缝制辅料预采购合同单",keyFont,Element.ALIGN_CENTER));
            }
            if("2".equals(emkMaterialPactEntity.getType())){
                document.add(createParagraph("包装辅料预采购合同单",keyFont,Element.ALIGN_CENTER));
            }
            table.addCell(createCell01("甲方", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getPartyA(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getOrderNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("已方", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getPartyB(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getDhjqDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("工艺类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getTracerName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("数量", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(Utils.notEmpty(emkMaterialPactEntity.getSumTotal()) ? String.valueOf(emkMaterialPactEntity.getSumTotal()):"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,2,1));
            table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("生产跟单员:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getDeveloperName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("预采购合同单提交日期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getKdDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

            table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));

        }
        if("1".equals(emkMaterialPactEntity.getFlag())){
            if("0".equals(emkMaterialPactEntity.getType())){
                document.add(createParagraph("原料面料正式采购合同单",keyFont,Element.ALIGN_CENTER));
            }
            if("1".equals(emkMaterialPactEntity.getType())){
                document.add(createParagraph("缝制辅料正式采购合同单",keyFont,Element.ALIGN_CENTER));
            }
            if("2".equals(emkMaterialPactEntity.getType())){
                document.add(createParagraph("包装辅料正式采购合同单",keyFont,Element.ALIGN_CENTER));
            }
            table.addCell(createCell01("甲方", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getPartyA(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
            table.addCell(createCell01("已方", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01(emkMaterialPactEntity.getPartyB(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,7,1));
        }

        document.add(table);
        document.add(createParagraph("一、订单明细和条款",headFont,Element.ALIGN_LEFT));

        if("0".equals(emkMaterialPactEntity.getFlag())){
            float[] widths2 = {20f,50f,50f,40f,40f,30f,30f,30f,30f,30f,30f,30f,30f,30f,20f,30f,30f};
            table = createTable(widths2);
            table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("采购需求单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(emkMaterialPactEntity.getFlag())){
            float[] widths2 = {20f,50f,50f,50f,50f,40f,40f,30f,30f,30f,30f,30f,30f,30f,30f,30f,20f,30f,30f,30f};
            table = createTable(widths2);
            table.addCell(createCell01("序号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("采购需求单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("订单号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("0".equals(emkMaterialPactEntity.getType())){
            table.addCell(createCell01("原料面料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("原料面料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(emkMaterialPactEntity.getType())){
            table.addCell(createCell01("缝制辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("缝制辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("2".equals(emkMaterialPactEntity.getType())){
            table.addCell(createCell01("包装辅料名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell01("包装辅料规格", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        if("1".equals(emkMaterialPactEntity.getFlag())){
            setXqkfdData(table,emkSampleDetailEntities,"3");
        }
        if("0".equals(emkMaterialPactEntity.getFlag())){
            setXqkfdData(table,emkSampleDetailEntities,"2");
        }
        document.add(table);

        float[] widths3 = {60f,150f};
        table = createTable(widths3);

        document.add(createParagraph("1）以上单价为出厂价："+emkMaterialPactEntity.getOutPrice()+"，FOB："+emkMaterialPactEntity.getOutPrice()+"(备注：，尺码颜色的数量分配如上表或见附件。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("2）甲方确保按以上条款及乙方确认品质后生产大货，准时进仓，如有变化按双方修改文件为准。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("3）甲方在出货前合理时间内提供准确的预报箱单供乙方定仓，通过乙方指定的出口代理公司和货运代理报关出口。需要商检的货物乙方提前一周以上通知甲方，甲方在报关前及时提供相关单证。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("4）甲方须及时提供乙方生产进程并同意乙方的查货人员进入生产工厂验货，根据乙方的验货报告及时改进；经验货合格后方可出货。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("5）包装:根据客人指示资料提供的要求。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("6）交货地点：乙方指定："+emkMaterialPactEntity.getPlace()+"，或："+emkMaterialPactEntity.getBoundName()+"仓库。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("7）原产地及生产商："+emkMaterialPactEntity.getYcd(),headFont,Element.ALIGN_LEFT));


        document.add(createParagraph("二、付款方式：",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("1）"+emkMaterialPactEntity.getPayType(),headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("2）订单确认后，乙方应及时付订金，甲方收到订金之后安排预定原料。出货前乙方应及时安排货款。如不能安时付款的，依次造成不能及时预定原料，不能上机生产的并耽误货期的情况，甲方概不负责。"+emkMaterialPactEntity.getYcd(),headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("3）甲方仅代付衣架款，乙方负责衣架的订购及送货至甲方仓库。",headFont,Element.ALIGN_LEFT));

        document.add(createParagraph("三、其他：",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("1）本合同依法签订，一旦签订即具法律效力，双方必须全面履行。不得单方擅自变更或解除。如履行本协协议时发生争议，由甲，乙双方协商解决。协商不成的，双方一致同意交由协议签订双方的任何一方当地人民法院通过诉讼方式予以解决。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("2）本合同正本二份，双方各执一份，具有同等法律效力。",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("3）甲方仅代付衣架款，乙方负责衣架的订购及送货至甲方仓库。",headFont,Element.ALIGN_LEFT));

        float[] widths4 = {60f,150f,60f,150f};
        table = createTable(widths4);
        table.addCell(createCell01("甲方", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));
        table.addCell(createCell01("乙方", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,2,1));

        table.addCell(createCell01("法定代表授权代表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getSqdb(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("法定代表授权代表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getYsqdb(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));


        table.addCell(createCell01("地址", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getAddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("地址", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getYaddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getYtelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));


        table.addCell(createCell01("签定日期:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getSignDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("签定日期:", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkMaterialPactEntity.getYsignDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        document.add(table);

        table = processTable(processList);
        document.add(table);

        document.close();
    }

    public static void main(String[] args) throws Exception {
        File file = new File("d:\\PDF\\T3.pdf");
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
/*
        try {
            // 第一步，实例化一个document对象
            Document document = new Document();
            // 第二步，设置要到出的路径
            // FileOutputStream out = new  FileOutputStream("H:/workbook111.pdf");
            FileOutputStream out = new  FileOutputStream("d:\\PDF\\T3.pdf");
            //如果是浏览器通过request请求需要在浏览器中输出则使用下面方式
            //OutputStream out = response.getOutputStream();
            // 第三步,设置字符
            BaseFont bfChinese = BaseFont.createFont("C:/windows/fonts/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            Font fontZH = new Font(bfChinese, 12.0F, 0);
            // 第四步，将pdf文件输出到磁盘
            PdfWriter writer = PdfWriter.getInstance(document, out);
            // 第五步，打开生成的pdf文件
            document.open();
            // 第六步,设置内容
            String title = "导出pdf测试的情况";
            document.add(new Paragraph(new Chunk(title, fontZH).setLocalDestination(title)));
            document.add(new Paragraph("\n"));
            // 创建table,注意这里的2是两列的意思,下面通过table.addCell添加的时候必须添加整行内容的所有列
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100.0F);
            //第一列是列表名
            table.setHeaderRows(1);

            table.getDefaultCell().setHorizontalAlignment(1);
            table.addCell(new Paragraph("序号", fontZH));
            table.addCell(new Paragraph("性别", fontZH));
            table.addCell(new Paragraph("姓名", fontZH));
            table.addCell(new Paragraph("年龄", fontZH));
            table.addCell(new Paragraph("1", fontZH));
            table.addCell(new Paragraph("男", fontZH));
            table.addCell(new Paragraph("测试名字1", fontZH));
            table.addCell(new Paragraph("20", fontZH));
            table.addCell(new Paragraph("2", fontZH));
            table.addCell(new Paragraph("女", fontZH));
            table.addCell(new Paragraph("测试名字2", fontZH));
            table.addCell(new Paragraph("21", fontZH));

            document.add(table);
            document.add(new Paragraph("\n"));

            // 第七步，关闭document
            document.close();
            System.out.println("导出pdf成功~");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }*/

    }
}
