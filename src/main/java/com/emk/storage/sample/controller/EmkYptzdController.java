package com.emk.storage.sample.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.storage.accessories.entity.EmkAccessoriesEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.material.entity.EmkMaterialEntity;
import com.emk.storage.pack.entity.EmkPackEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sample.entity.EmkSampleContentEntity;
import com.emk.storage.sample.entity.EmkSampleEntity;
import com.emk.storage.sample.service.EmkSampleServiceI;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tools.ant.util.DateUtils;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Api(value = "EmkSample", description = "样品通知单", tags = "emkSampleController")
@Controller
@RequestMapping("/emkYptzdController")
public class EmkYptzdController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkYptzdController.class);
    @Autowired
    private EmkSampleServiceI emkSampleService;

    @Autowired
    private Validator validator;

    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sample/emkSampleList");
    }

    @RequestMapping(params = "detailMxList")
    public ModelAndView detailMxList(HttpServletRequest request) {
        List<Map<String, Object>> list = systemService.findForJdbc("select CONCAT(remark,',',typecode) typecode,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        String color = JSONHelper.collection2json(list);
        request.setAttribute("color", "'"+color+ "'");
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("proOrderId"))){
            List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId = ?",map.get("proOrderId"));
            request.setAttribute("emkProOrderDetailEntities", emkEnquiryDetailEntityList);
            EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",map.get("proOrderId"));
            request.setAttribute("emkSizePage", emkSizeEntity);
        }
        return new ModelAndView("com/emk/storage/sample/detailMxList");
    }

    @RequestMapping(params = "contentList")
    public ModelAndView contentList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if(Utils.notEmpty(map.get("sampleId"))){
            EmkSampleEntity emkSample = emkSampleService.getEntity(EmkSampleEntity.class, map.get("sampleId").toString());
            request.setAttribute("emkSamplePage", emkSample);
            List<EmkSampleContentEntity> sampleContentEntities = systemService.findHql("from EmkSampleContentEntity where sampleId=? and type=?",map.get("sampleId"),map.get("type"));
            request.setAttribute("sampleContentEntities", sampleContentEntities);
        }
        if (map.get("type").equals("0")) {
            return new ModelAndView("com/emk/storage/sample/contentList");
        }
        if (map.get("type").equals("1")) {
            return new ModelAndView("com/emk/storage/sample/contentList2");
        }
        if (map.get("type").equals("2")) {
            return new ModelAndView("com/emk/storage/sample/contentList3");
        }
        return new ModelAndView("com/emk/storage/sample/contentList");
    }

    @RequestMapping(params = "gxList")
    public ModelAndView gxList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleGxEntity> emkSampleGxEntities = systemService.findHql("from EmkSampleGxEntity where sampleId=?", map.get("sampleId"));
            request.setAttribute("emkSampleGxEntities", emkSampleGxEntities);
        }
        return new ModelAndView("com/emk/storage/sample/gxList");
    }
    @RequestMapping(params = "ranList")
    public ModelAndView ranList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='bmzl'");
        request.setAttribute("bmzlList", list);
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleRanEntity> emkSampleRanEntities = systemService.findHql("from EmkSampleRanEntity where sampleId=? and type=0 ", map.get("sampleId"));
            request.setAttribute("emkSampleRanEntities", emkSampleRanEntities);
        }
        return new ModelAndView("com/emk/storage/sample/ranList");
    }
    @RequestMapping(params = "yinList")
    public ModelAndView yinList(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='yhgyzl'");
        request.setAttribute("yhgyzlList", list);
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleYinEntity> emkSampleYinEntities = systemService.findHql("from EmkSampleYinEntity where sampleId=?", map.get("sampleId"));
            request.setAttribute("emkSampleYinEntities", emkSampleYinEntities);
        }
        return new ModelAndView("com/emk/storage/sample/yinList");
    }

    /*@RequestMapping(params = "list0")
    public ModelAndView list0(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/sample/emkSampleList0");
    }*/

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleEntity.class, dataGrid);
       /* TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("ywy") || roleMap.get("rolecode").toString().contains("ywgdy")){
                cq.eq("createBy",user.getUserName());
            }
        }*/
        HqlGenerateUtil.installHql(cq, emkSample, request.getParameterMap());

        cq.add();
        emkSampleService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSample = systemService.getEntity(EmkSampleEntity.class, emkSample.getId());
        message = "样品通知单删除成功";
        try {
            if (!emkSample.getState().equals("0")) {
                message = "样品通知单已经提交处理，无法删除";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            emkSampleService.delete(emkSample);
            systemService.executeSql("delete from emk_sample_detail where sample_id=?", new Object[]{emkSample.getId()});
            systemService.executeSql("delete from emk_sample_total where sample_id=?", new Object[]{emkSample.getId()});

            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品通知单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkSampleEntity emkSample = systemService.getEntity(EmkSampleEntity.class, id);
               /* if (!emkSample.getState().equals("0")) {
                    message = "样品通知单单已经提交处理，无法删除";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }*/
//                WebFileUtils.delete( request.getRealPath("/")+emkSample.get());
                if("-1".equals(emkSample.getState())){
                    systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=0",id);
                    systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_sample_detail where sample_id=?))",id);
                    systemService.executeSql("delete from emk_size where form_id=?", id);
                    systemService.executeSql("delete from emk_sample_content where sample_id=?", emkSample.getId());
                    systemService.executeSql("delete from emk_sample_gx where sample_id=?", emkSample.getId());
                    systemService.executeSql("delete from emk_pb where price_id = ?",emkSample.getId());
                    emkSampleService.delete(emkSample);
                    systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
                }else{
                    message = "样品通知单已处理，无法删除";
                    j.setMsg(message);
                    j.setSuccess(false);
                    return j;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdateState")
    @ResponseBody
    public AjaxJson doUpdateState(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        EmkSampleEntity t = systemService.getEntity(EmkSampleEntity.class, emkSample.getId());
        message = "样品状态修改成功";
        try {
            if(t.getSampleState().equals("9")){
                message = "样品已完成";
                j.setMsg(message);
                j.setSuccess(false);
                return j;
            }
            MyBeanUtils.copyBeanNotNull2Bean(emkSample,t);
            systemService.saveOrUpdate(t);

            if(emkSample.getSampleState().equals("9")){
                EmkSampleRequiredEntity emkSampleRequiredEntity = systemService.findUniqueByProperty(EmkSampleRequiredEntity.class,"requiredNo",t.getXqdh());
                EmkEnquiryEntity emkEnquiryEntity = systemService.findUniqueByProperty(EmkEnquiryEntity.class,"enquiryNo",emkSampleRequiredEntity.getEnquiryNo());
                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"workNum",emkSampleRequiredEntity.getEnquiryNo());
                Map<String, Object> variables = new HashMap();
                TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");

                List<Task> task = taskService.createTaskQuery().taskAssignee(approvalEntity.getFormId()).list();
                variables.put("optUser", approvalEntity.getFormId());

                Task task1 = task.get(task.size() - 1);
                taskService.complete(task1.getId(), variables);
                EmkPriceEntity emkPrice = new EmkPriceEntity();

                String priceNo = savePrice2(emkEnquiryEntity,emkPrice,emkEnquiryEntity.getBusinesser(),emkEnquiryEntity.getBusinesserName(),t.getId());

                String title = "您有【"+approvalEntity.getCreateName()+"】提交的意向询盘单需要报价，报价单号："+priceNo;
                TSUser bpmUser = systemService.get(TSUser.class,approvalEntity.getCommitId());
                saveSmsAndEmailForOne("【生产跟单员】打样通知单",title+"，请及时处理。",bpmUser,user.getUserName());
                EmkApprovalDetailEntity papprovalDetail = ApprovalUtil.saveApprovalDetail(approvalEntity.getId(),user,approvalEntity,"打样完成");
                papprovalDetail.setBpmName("打样完成");
                papprovalDetail.setApproveStatus(0);
                approvalEntity.setStatus(30);

                systemService.save(papprovalDetail);

                systemService.executeSql("update emk_enquiry set state=30 where id=?",approvalEntity.getFormId());
                saveSmsAndEmailForOne("打样通知单【生产跟单员】","您有【"+user.getRealName()+"】完成的打样单，单号："+approvalEntity.getWorkNum()+"，请及时处理询盘单："+approvalEntity.getWorkNum()+",价格确认",bpmUser,user.getUserName());

            }


            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品状态修改失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doCreateKfd")
    @ResponseBody
    public AjaxJson doCreateKfd(EmkSampleEntity emkSampleEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkSampleEntity = systemService.getEntity(EmkSampleEntity.class, emkSampleEntity.getId());
        message = "样品需求开发单创建成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");

        try {
            if(Utils.notEmpty(emkSampleEntity.getState())){
                if(Integer.parseInt(emkSampleEntity.getState())>=1){
                    j.setSuccess(false);
                    j.setMsg("该样品单已经生开发单了");
                    return j;
                }
            }
            emkSampleEntity.setState("1");
            systemService.saveOrUpdate(emkSampleEntity);

            EmkMaterialEntity emkMaterialEntity = new EmkMaterialEntity();
            EmkAccessoriesEntity emkAccessories = new EmkAccessoriesEntity();
            EmkPackEntity emkPack = new EmkPackEntity();

            MyBeanUtils.copyBeanNotNull2Bean(emkSampleEntity,emkMaterialEntity);
            MyBeanUtils.copyBeanNotNull2Bean(emkSampleEntity,emkAccessories);

            emkMaterialEntity.setCreateBy(user.getUserName());
            emkMaterialEntity.setCreateName(user.getRealName());
            emkAccessories.setCreateBy(user.getUserName());
            emkAccessories.setCreateName(user.getRealName());

            EmkSampleRequiredEntity emkSampleRequiredEntity = systemService.findUniqueByProperty(EmkSampleRequiredEntity.class,"requiredNo",emkSampleEntity.getXqdh());

            emkMaterialEntity.setCustomSampleUrl(emkSampleRequiredEntity.getCustomSampleUrl());
            emkMaterialEntity.setCustomSample(emkSampleRequiredEntity.getCustomSample());
            emkMaterialEntity.setSampleNum(emkSampleEntity.getSampleNum());
            emkMaterialEntity.setSampleDate(emkSampleEntity.getKdTime());
            emkMaterialEntity.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
            emkMaterialEntity.setXqdh(emkSampleEntity.getXqdh());
            emkMaterialEntity.setDyxqdDate(emkSampleEntity.getKdTime());
            emkMaterialEntity.setYpjqDate(emkSampleRequiredEntity.getYsDate());
            if(Utils.notEmpty(emkSampleRequiredEntity.getLevelDays())){
                emkMaterialEntity.setLeaveYpjqDays(Integer.parseInt(emkSampleRequiredEntity.getLevelDays()));
            }
            emkMaterialEntity.setDhjqDate(emkSampleRequiredEntity.getDhjq());
            if(Utils.notEmpty(emkSampleRequiredEntity.getLeaveldhjqDays())){
                emkMaterialEntity.setLeaveDhjqDays(Integer.parseInt(emkSampleRequiredEntity.getLeaveldhjqDays()));
            }

            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(MATERIAL_NO, 2)),0)+1 AS signed) orderNum from emk_accessories");
            emkMaterialEntity.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));

            emkMaterialEntity.setFormType("1");
            emkMaterialEntity.setState("0");
            systemService.save(emkMaterialEntity);

            orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(MATERIAL_NO, 2)),0)+1 AS signed) orderNum from emk_accessories");
            emkAccessories.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            emkAccessories.setCustomSampleUrl(emkSampleRequiredEntity.getCustomSampleUrl());
            emkAccessories.setCustomSample(emkSampleRequiredEntity.getCustomSample());
            emkAccessories.setSampleNum(emkSampleEntity.getSampleNum());
            emkAccessories.setSampleDate(emkSampleEntity.getKdTime());
            emkAccessories.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
            emkAccessories.setXqdh(emkSampleEntity.getXqdh());
            emkAccessories.setYpjqDate(emkSampleRequiredEntity.getYsDate());
            if(Utils.notEmpty(emkSampleRequiredEntity.getLevelDays())){
                emkAccessories.setLeaveYpjqDays(Integer.parseInt(emkSampleRequiredEntity.getLevelDays()));
            }
            emkAccessories.setDhjqDate(emkSampleRequiredEntity.getDhjq());
            if(Utils.notEmpty(emkSampleRequiredEntity.getLeaveldhjqDays())){
                emkAccessories.setLeaveDhjqDays(Integer.parseInt(emkSampleRequiredEntity.getLeaveldhjqDays()));
            }
            emkAccessories.setFormType("1");
            emkAccessories.setState("0");
            systemService.save(emkAccessories);


            MyBeanUtils.copyBeanNotNull2Bean(emkSampleEntity,emkPack);
            emkPack.setState("0");
            emkPack.setParkNo(emkSampleEntity.getSampleNo() + DateUtils.format(new Date(), "yyMMdd"));
            emkPack.setFormType("1");
            emkPack.setId(null);
            emkPack.setKdDate(DateUtils.format(new Date(), "yyyy-MM-dd"));
            systemService.save(emkPack);

            //保存原料数据
            List<EmkSampleDetailEntity> emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ? and type=0",emkSampleEntity.getId());
            EmkSampleDetailEntity emkSampleDetailEntity = null;
            for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
                emkSampleDetailEntity = new EmkSampleDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
                emkSampleDetailEntity.setId(null);
                emkSampleDetailEntity.setSampleId(emkMaterialEntity.getId());
                systemService.save(emkSampleDetailEntity);
            }

            //保存缝制辅料数据
            emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ? and type=1",emkSampleEntity.getId());
            emkSampleDetailEntity = null;
            for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
                emkSampleDetailEntity = new EmkSampleDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
                emkSampleDetailEntity.setId(null);
                emkSampleDetailEntity.setSampleId(emkAccessories.getId());
                systemService.save(emkSampleDetailEntity);
            }

            //保存包装辅料数据
            emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ? and type=2",emkSampleEntity.getId());
            emkSampleDetailEntity = null;
            for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
                emkSampleDetailEntity = new EmkSampleDetailEntity();
                MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
                emkSampleDetailEntity.setId(null);
                emkSampleDetailEntity.setSampleId(emkPack.getId());
                systemService.save(emkSampleDetailEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单创建失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品通知单添加成功";
        try {
            emkSample.setState("0");
            if(emkSample.getType().equals("ss") || emkSample.getType().equals("cq")){
                /*if(emkSample.getOrderNo() == null || emkSample.getOrderNo().equals("")){
                    j.setSuccess(false);
                    j.setMsg("订单号不能为空");
                    return j;
                }*/
            }
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());

            emkSample.setKdTime(DateUtils.format(new Date(), "yyyy-MM-dd"));
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(SAMPLE_NUM, 2)),0)+1 AS signed) orderNum from emk_sample");
            emkSample.setSampleNum("YPTZD" + DateUtils.format(new Date(), "yyMMdd") + "A" + String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkSample.setXqdh("YPXQ" +emkSample.getCusNum()+ DateUtils.format(new Date(), "yyMMdd")+ String.format("%02d", Integer.parseInt(orderNum.get("orderNum").toString())));
            emkSampleService.save(emkSample);

           String  dataRows = (String)map.get("orderMxListIDContent");
            //保存说明数据一
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].content00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].content00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("0");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent2");
            //保存说明数据二
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bcontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].bcontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("1");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent3");
            //保存说明数据三
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].ccontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].ccontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("2");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            saveSampleDetail(null,null,map,"1",emkSample.getId());

            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkSampleEntity emkSample, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "样品通知单更新成功";
       /* if(emkSample.getType().equals("ss") || emkSample.getType().equals("cq")){
            if(emkSample.getOrderNo() == null || emkSample.getOrderNo().equals("")){
                j.setSuccess(false);
                j.setMsg("订单号不能为空");
                return j;
            }
        }*/
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());

        EmkSampleEntity t = emkSampleService.get(EmkSampleEntity.class, emkSample.getId());
        try {
            if (!t.getState().equals("0")) {
                message = "该样品通知已经生成样品需求单，请确认！";
                j.setMsg(message);
                j.setSuccess(false);
                return  j;
            }

            MyBeanUtils.copyBeanNotNull2Bean(emkSample, t);
            emkSampleService.saveOrUpdate(t);

            String dataRows = (String)map.get("orderMxListIDContent");
            //保存说明数据一
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_content where sample_id=? and type=0",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].content00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].content00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("0");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent2");
            //保存说明数据二
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_content where sample_id=? and type=1",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bcontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].bcontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("1");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            dataRows = (String)map.get("orderMxListIDContent3");
            //保存说明数据三
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_content where sample_id=? and type=2",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleContentEntity emkSampleContentEntity = new EmkSampleContentEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].ccontent00"))) {
                        emkSampleContentEntity.setContent((String)map.get("orderMxList["+i+"].ccontent00"));
                        emkSampleContentEntity.setSampleId((String)emkSample.getId());
                        emkSampleContentEntity.setType("2");
                        systemService.save(emkSampleContentEntity);
                    }
                }
            }

            saveSampleDetail(null,null,map,"1",t.getId());


            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "样品通知单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doPrintPDF")
    public String doPrintPDF(String ids,EmkSampleEntity emkSample, HttpServletRequest request,HttpServletResponse response) {
        String message = null;
        try {
            for (String id : ids.split(",")) {
                String fileName = "c:\\PDF\\"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                File file = new File(fileName);
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                emkSample = emkSampleService.getEntity(EmkSampleEntity.class, id);

                StringBuilder sql = new StringBuilder();
                sql.append("select t.enquiry_id enquiryId,(select typename from t_s_type a2 left join t_s_typegroup a1 on a1.ID=a2.typegroupid where a1.typegroupcode='color' and a2.typecode=t.color) colorName,t.color_value colorVal,t.size,t1.* \n" +
                        " from emk_enquiry_detail t left join emk_size_total t1  on t1.p_id=t.id \n" +
                        " where t.enquiry_id=? order by t.sort_desc asc \n");

                List<Map<String, Object>> emkProOrderDetailEntities = systemService.findForJdbc(sql.toString(),emkSample.getId());
                EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",emkSample.getId());
                Map type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='gylx' and typecode=?",emkSample.getGyzl());
                String gyzl = "";
                if(Utils.notEmpty(type)){
                    gyzl = type.get("typename").toString();
                }
                type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='sampletype' and typecode=?",emkSample.getType());
                String yplx = "";
                if(Utils.notEmpty(type)){
                    yplx = type.get("typename").toString();
                }

                List<EmkSampleContentEntity> sampleContentEntities0 = systemService.findHql("from EmkSampleContentEntity where sampleId=? and type=?",emkSample.getId(),"0");
                List<EmkSampleContentEntity> sampleContentEntities1 = systemService.findHql("from EmkSampleContentEntity where sampleId=? and type=?",emkSample.getId(),"1");
                List<EmkSampleContentEntity> sampleContentEntities2 = systemService.findHql("from EmkSampleContentEntity where sampleId=? and type=?",emkSample.getId(),"2");

                EmkPbEntity emkPbEntity = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",emkSample.getId());

                List<EmkSampleDetailEntity> emkSampleDetailEntities0 = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=0", emkSample.getId());
                List<EmkSampleDetailEntity> emkSampleDetailEntities1 = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", emkSample.getId());
                List<EmkSampleDetailEntity> emkSampleDetailEntities2 = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=2", emkSample.getId());

                List<EmkSampleGxEntity> emkSampleGxEntities = systemService.findHql("from EmkSampleGxEntity where sampleId=?", emkSample.getId());

                /*sql = new StringBuilder();
                sql.append("select t.*,(select typename from t_s_type a2 left join t_s_typegroup a1 on a1.ID=a2.typegroupid where a1.typegroupcode='bmzl' and a2.typecode=t.bu_type) colorName \n" +
                        " from emk_sample_ran t  where t.sample_id=? and type=0 \n");
                List<Map<String, Object>> emkSampleRanEntities = systemService.findForJdbc(sql.toString(),emkSample.getId());

                sql = new StringBuilder();
                sql.append("select t.*,(select typename from t_s_type a2 left join t_s_typegroup a1 on a1.ID=a2.typegroupid where a1.typegroupcode='yhgyzl' and a2.typecode=t.gyzy) colorName \n" +
                        " from emk_sample_yin t  where t.sample_id=? \n");
                List<Map<String, Object>> emkSampleYinEntities = systemService.findForJdbc(sql.toString(),emkSample.getId());*/

                sql = new StringBuilder();
                sql.append("select DATE_FORMAT(IFNULL(t1.START_TIME_,a.commit_time),'%Y-%m-%d %H:%i:%s') startTime,DATE_FORMAT(t1.END_TIME_,'%Y-%m-%d %H:%i:%s') endTime,b.* from emk_approval a \n" +
                        " left join emk_approval_detail b on b.approval_id = a.id \n" +
                        " left join act_hi_taskinst t1 on t1.assignee_= a.form_id and t1.task_def_key_=b.bpm_node ");

                sql.append(" where a.form_id=? ");
                sql.append(" order by b.approve_date asc ");
                List<Map<String, Object>> processList = systemService.findForJdbc(sql.toString(),id);

                new createPdf(file).generateSamplePDF(emkSample,null,null,gyzl,yplx,emkProOrderDetailEntities,emkSizeEntity,sampleContentEntities0,
                        sampleContentEntities1,sampleContentEntities2,emkPbEntity,emkSampleDetailEntities0,emkSampleDetailEntities1,
                        emkSampleDetailEntities2,emkSampleGxEntities,null,null,null,null,processList);
                String fFileName = "c:\\PDF\\F"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                WaterMark.waterMark(fileName,fFileName, "样品通知单");
                file.delete();
                WebFileUtils.downLoad(fFileName,response,false);
                file = new File(fFileName);
                file.delete();
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "导出PDF失败";
            throw new BusinessException(e.getMessage());
        }
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }


    @RequestMapping(params = "goAdd")
    public ModelAndView goAdd(EmkSampleEntity emkSample, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        TSUser user = (TSUser) req.getSession().getAttribute("LOCAL_CLINET_USER");

        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
        }

        return new ModelAndView("com/emk/storage/sample/emkSample-add");
    }

    @RequestMapping(params = "goSelectUser")
    public ModelAndView goSelectUser(HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/sample/emkSample-selectUser");
    }

    @RequestMapping(params = "goTab")
    public ModelAndView goBtn(EmkSampleEntity emkSample, HttpServletRequest req) {
        return new ModelAndView("com/emk/storage/sample/emkSample-tab");
    }
    @RequestMapping(params = "goBase")
    public ModelAndView goBase(EmkSampleEntity emkSample, HttpServletRequest req) {
        req.setAttribute("kdDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSample);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString());
                b = Double.parseDouble(countMap.get("Colums").toString());
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/sample/emkSample-base");
    }

    @RequestMapping(params = "goPb")
    public ModelAndView goPb(EmkPbEntity emkPbEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkPbEntity.getPriceId())) {
            emkPbEntity = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",emkPbEntity.getPriceId());
            req.setAttribute("emkPbPage", emkPbEntity);
            try {
                if(Utils.notEmpty(emkPbEntity)){
                    Map countMap = MyBeanUtils.culBeanCounts(emkPbEntity);
                    req.setAttribute("countMap", countMap);
                    double a=0,b=0;
                    a = Double.parseDouble(countMap.get("finishColums").toString())-4;
                    b = Double.parseDouble(countMap.get("Colums").toString())-4;
                    DecimalFormat df = new DecimalFormat("#.00");
                    req.setAttribute("recent", df.format(a*100/b));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/price/emkPrice-pb");
    }


    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkSampleEntity emkSample, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkSample);
                req.setAttribute("countMap", countMap);
                double a=0,b=0;
                a = Double.parseDouble(countMap.get("finishColums").toString());
                b = Double.parseDouble(countMap.get("Colums").toString());
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/sample/emkSample-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkSampleEntity emkSample, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
        }
        return new ModelAndView("com/emk/storage/sample/emkSample-update2");
    }

    @RequestMapping(params = "goState")
    public ModelAndView goState(EmkSampleEntity emkSample, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSample.getId())) {
            emkSample = emkSampleService.getEntity(EmkSampleEntity.class, emkSample.getId());
            req.setAttribute("emkSamplePage", emkSample);
        }
        return new ModelAndView("com/emk/storage/sample/emkSampleState-update");
    }


    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkSampleController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkSampleEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkSample, request.getParameterMap());
        List<EmkSampleEntity> emkSamples = emkSampleService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "样品通知单");
        modelMap.put("entity", EmkSampleEntity.class);
        modelMap.put("params", new ExportParams("样品通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkSamples);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkSampleEntity emkSample, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "样品通知单");
        modelMap.put("entity", EmkSampleEntity.class);
        modelMap.put("params", new ExportParams("样品通知单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "样品通知单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkSampleEntity>> list() {
        List<EmkSampleEntity> listEmkSamples = emkSampleService.getList(EmkSampleEntity.class);
        return Result.success(listEmkSamples);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取样品通知单信息", notes = "根据ID获取样品通知单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkSampleEntity task = emkSampleService.get(EmkSampleEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取样品通知单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建样品通知单")
    public ResponseMessage<?> create(@ApiParam(name = "样品通知单对象") @RequestBody EmkSampleEntity emkSample, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkSampleEntity>> failures = validator.validate(emkSample, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleService.save(emkSample);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品通知单信息保存失败");
        }
        return Result.success(emkSample);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新样品通知单", notes = "更新样品通知单")
    public ResponseMessage<?> update(@ApiParam(name = "样品通知单对象") @RequestBody EmkSampleEntity emkSample) {
        Set<ConstraintViolation<EmkSampleEntity>> failures = validator.validate(emkSample, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkSampleService.saveOrUpdate(emkSample);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新样品通知单信息失败");
        }
        return Result.success("更新样品通知单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除样品通知单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkSampleService.deleteEntityById(EmkSampleEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("样品通知单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkSampleEntity emkSampleEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "打样单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkSampleEntity.getId() == null) || (emkSampleEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkSampleEntity sampleEntity = systemService.getEntity(EmkSampleEntity.class, id);
                    if (!sampleEntity.getState().equals("0")) {
                        message = "存在已提交的打样单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkSampleEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkSampleEntity t = emkSampleService.get(EmkSampleEntity.class, id);
                    t.setState("1");
                    variables.put("inputUser", t.getId());

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("sampleTask")) {
                            taskService.complete(task1.getId(), variables);
                        }
                        if (task1.getTaskDefinitionKey().equals("leadTask")) {
                          /*  t.setLeader(user.getRealName());
                            t.setLeadUserId(user.getId());
                            t.setLeadAdvice(emkSampleEntity.getLeadAdvice());
                            if (emkSampleEntity.getIsPass().equals("0")) {
                                variables.put("isPass", emkSampleEntity.getIsPass());
                                taskService.complete(task1.getId(), variables);
                                t.setState("2");

                                EmkPriceEntity priceEntity = systemService.findUniqueByProperty(EmkPriceEntity.class,"pirceNo",t.getPirceNo());

                                EmkWorkOrderEntity workOrderEntity = systemService.findUniqueByProperty(EmkWorkOrderEntity.class,"askNo",priceEntity.getXpNo());
                                workOrderEntity.setSampleNum(t.getSampleNum());
                                workOrderEntity.setSampleUserId(user.getUserName());
                                workOrderEntity.setSampleUser(user.getRealName());
                                workOrderEntity.setSampleDate(DateUtil.getCurrentTimeString(null));
                                workOrderEntity.setSampleAdvice(emkSampleEntity.getLeadAdvice());
                                systemService.saveOrUpdate(workOrderEntity);

                                variables.put("inputUser", workOrderEntity.getId());
                                task = taskService.createTaskQuery().taskAssignee(workOrderEntity.getId()).list();
                                task1 = task.get(task.size() - 1);
                                taskService.complete(task1.getId(), variables);


                            } else {
                                List<HistoricTaskInstance> hisTasks = historyService.createHistoricTaskInstanceQuery().taskAssignee(t.getId()).list();

                                List<Task> taskList = taskService.createTaskQuery().taskAssignee(t.getId()).list();
                                if (taskList.size() > 0) {
                                    Task taskH = (Task)taskList.get(taskList.size() - 1);
                                    HistoricTaskInstance historicTaskInstance = hisTasks.get(hisTasks.size() - 2);
                                    FlowUtil.turnTransition(taskH.getId(), historicTaskInstance.getTaskDefinitionKey(), variables);
                                    Map activityMap = systemService.findOneForJdbc("SELECT GROUP_CONCAT(t0.ID_) ids,GROUP_CONCAT(t0.TASK_ID_) taskids FROM act_hi_actinst t0 WHERE t0.ASSIGNEE_=? AND t0.ACT_ID_=? ORDER BY ID_ ASC", t.getId(), historicTaskInstance.getTaskDefinitionKey());
                                    String[] activitIdArr = activityMap.get("ids").toString().split(",");
                                    String[] taskIdArr = activityMap.get("taskids").toString().split(",");
                                    systemService.executeSql("UPDATE act_hi_taskinst SET  NAME_=CONCAT('【驳回后】','',NAME_) WHERE ASSIGNEE_>=? AND ID_=?",t.getId(), taskIdArr[1]);
                                    systemService.executeSql("delete from act_hi_actinst where ID_>=? and ID_<?", activitIdArr[0], activitIdArr[1] );
                                }
                                t.setState("0");
                            }*/
                        }
                        systemService.saveOrUpdate(t);

                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("sample", "emkSampleEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);
                    }

                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "打样单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkSampleEntity emkSampleEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkSampleEntity.getId())) {
            emkSampleEntity = emkSampleService.getEntity(EmkSampleEntity.class, emkSampleEntity.getId());
            req.setAttribute("emkSample", emkSampleEntity);
        }
        return new ModelAndView("com/emk/storage/sample/emkSample-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkSampleEntity emkSampleEntity, HttpServletRequest req, DataGrid dataGrid) {
        String sql = "";String countsql = "";
        Map map = ParameterUtil.getParamMaps(req.getParameterMap());

        sql = "SELECT DATE_FORMAT(t1.START_TIME_, '%Y-%m-%d %H:%i:%s') startTime,t1.*,CASE \n" +
                " WHEN t1.TASK_DEF_KEY_='sampleTask' THEN t2.create_name \n" +
                " WHEN t1.TASK_DEF_KEY_='checkTask' THEN t2.leader \n" +
                " END workname FROM act_hi_taskinst t1 \n" +
                " LEFT JOIN emk_sample t2 ON t1.ASSIGNEE_ = t2.id where ASSIGNEE_='" + map.get("id") + "' ";

        countsql = " SELECT COUNT(1) FROM act_hi_taskinst t1 where ASSIGNEE_='" + map.get("id") + "' ";
        if (dataGrid.getPage() == 1) {
            sql = sql + " limit 0, " + dataGrid.getRows();
        } else {
            sql = sql + "limit " + (dataGrid.getPage() - 1) * dataGrid.getRows() + "," + dataGrid.getRows();
        }
        systemService.listAllByJdbc(dataGrid, sql, countsql);
        req.setAttribute("taskList", dataGrid.getResults());
        if (dataGrid.getResults().size() > 0) {
            req.setAttribute("stepProcess", Integer.valueOf(dataGrid.getResults().size() - 1));
        } else {
            req.setAttribute("stepProcess", Integer.valueOf(0));
        }
        emkSampleEntity = emkSampleService.getEntity(EmkSampleEntity.class, emkSampleEntity.getId());
        req.setAttribute("emkSample", emkSampleEntity);
        return new ModelAndView("com/emk/storage/sample/time");


    }
}
