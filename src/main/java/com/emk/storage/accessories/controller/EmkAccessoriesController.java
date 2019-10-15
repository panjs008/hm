package com.emk.storage.accessories.controller;

import com.alibaba.fastjson.JSONArray;
import com.emk.approval.approval.entity.EmkApprovalEntity;
import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.bound.minstorage.entity.EmkMInStorageEntity;
import com.emk.bound.minstoragedetail.entity.EmkMInStorageDetailEntity;
import com.emk.storage.accessories.entity.EmkAccessoriesEntity;
import com.emk.storage.accessories.service.EmkAccessoriesServiceI;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity2;
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

@Api(value = "EmkAccessories", description = "缝制辅料需求开发单", tags = "emkAccessoriesController")
@Controller
@RequestMapping("/emkAccessoriesController")
public class EmkAccessoriesController extends BaseController {
    private static final Logger logger = Logger.getLogger(EmkAccessoriesController.class);
    @Autowired
    private EmkAccessoriesServiceI emkAccessoriesService;

    @Autowired
    private Validator validator;


    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/emk/storage/accessories/emkAccessoriesList");
    }

    @RequestMapping(params = "orderMxList2")
    public ModelAndView orderMxList2(HttpServletRequest request) {
        Map map = ParameterUtil.getParamMaps(request.getParameterMap());
        List<Map<String, Object>> list = systemService.findForJdbc("select typecode,typecode code,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='color'");
        String color = JSONHelper.collection2json(list);
        request.setAttribute("color", "'"+color+ "'");
        list = systemService.findForJdbc("select company_code gys from emk_factory_archives t2 where state=2 order by company_code asc ");
        request.setAttribute("gysList", list);
        if (Utils.notEmpty(map.get("sampleId"))) {
            List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", map.get("sampleId"));
            request.setAttribute("emkSampleDetailEntities", emkSampleDetailEntities);
        }
        return new ModelAndView("com/emk/storage/accessories/orderMxList2");
    }

    @RequestMapping(params = "datagrid")
    public void datagrid(EmkAccessoriesEntity emkAccessories, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(EmkAccessoriesEntity.class, dataGrid);
        /*TSUser user = (TSUser) request.getSession().getAttribute(ResourceUtil.LOCAL_CLINET_USER);
        Map roleMap = (Map) request.getSession().getAttribute("ROLE");
        if(roleMap != null){
            if(roleMap.get("rolecode").toString().contains("jsy")){
                cq.eq("createBy",user.getUserName());
            }
        }*/
        HqlGenerateUtil.installHql(cq, emkAccessories, request.getParameterMap());


        cq.add();
        emkAccessoriesService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(EmkAccessoriesEntity emkAccessories, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        emkAccessories = systemService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
        message = "缝制辅料需求开发单删除成功";
        try {
            emkAccessoriesService.delete(emkAccessories);
            systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单删除失败";
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
        message = "缝制辅料需求开发单删除成功";
        try {
            for (String id : ids.split(",")) {
                EmkAccessoriesEntity emkAccessories = systemService.getEntity(EmkAccessoriesEntity.class, id);
                //WebFileUtils.delete( request.getRealPath("/")+emkAccessories.getCustomSampleUrl());
                systemService.executeSql("delete from emk_sample_detail where sample_id = ?",id);

                EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",id);
                systemService.executeSql("delete from emk_approval where form_id=?",id);
                if(Utils.notEmpty(approvalEntity)){
                    systemService.executeSql("delete from emk_approval_detail where approval_id=?",approvalEntity.getId());
                }
                emkAccessoriesService.delete(emkAccessories);
                systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单删除失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(EmkAccessoriesEntity emkAccessories, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "缝制辅料需求开发单添加成功";
        try {
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            emkAccessories.setState("0");
            Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(MATERIAL_NO, 2)),0)+1 AS signed) orderNum from emk_accessories");
            emkAccessories.setMaterialNo("SY" + DateUtils.format(new Date(), "yyMMdd") + "B" + String.format("%02d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
            emkAccessoriesService.save(emkAccessories);

            String dataRows = map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bproZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].bproZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].bproNum"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].bbrand"));
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].bdirection"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].bbetchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].bwidth"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].bcolor"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].bweight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].bchengf"));
                        emkSampleDetailEntity.setSignTotal(map.get("orderMxList["+i+"].bsignTotal"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].bunit"));
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].bremark"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].bgysCode"));
                        emkSampleDetailEntity.setSampleId(emkAccessories.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单添加失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(EmkAccessoriesEntity emkAccessories, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "缝制辅料需求开发单更新成功";
        Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
        emkAccessories.setProTypeName(emkAccessories.getProTypeName().replaceAll(",",""));
        emkAccessories.setProType(emkAccessories.getProType().replaceAll(",",""));
        EmkAccessoriesEntity t = emkAccessoriesService.get(EmkAccessoriesEntity.class, map.get("materailId").toString());
        try {
            emkAccessories.setId(null);
            MyBeanUtils.copyBeanNotNull2Bean(emkAccessories, t);
            emkAccessoriesService.saveOrUpdate(t);

            String dataRows = map.get("orderMxListID2");
            //保存缝制辅料数据
            if (Utils.notEmpty(dataRows)) {
                systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=1",t.getId());
                int rows = Integer.parseInt(dataRows);
                for (int i = 0; i < rows; i++) {
                    EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
                    if (Utils.notEmpty(map.get("orderMxList["+i+"].bproZnName"))) {
                        emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].bproZnName"));
                        emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].bproNum"));
                        emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].bbrand"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].byongliang"))){
                            emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].byongliang")));
                        }
                        emkSampleDetailEntity.setDirection(map.get("orderMxList["+i+"].bdirection"));
                        emkSampleDetailEntity.setBetchNum(map.get("orderMxList["+i+"].bbetchNum"));
                        emkSampleDetailEntity.setWidth(map.get("orderMxList["+i+"].bwidth"));
                        emkSampleDetailEntity.setColor(map.get("orderMxList["+i+"].bcolor"));
                        emkSampleDetailEntity.setWeight(map.get("orderMxList["+i+"].bweight"));
                        emkSampleDetailEntity.setChengf(map.get("orderMxList["+i+"].bchengf"));
                        emkSampleDetailEntity.setSignTotal(map.get("orderMxList["+i+"].bsignTotal"));
                        emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].bsignPrice"));
                        emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].bunit"));
                        if(Utils.notEmpty(map.get("orderMxList["+i+"].bsunhaoPrecent"))){
                            emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].bsunhaoPrecent")));
                        }
                        emkSampleDetailEntity.setRemark(map.get("orderMxList["+i+"].bremark"));
                        emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].bgysCode"));
                        emkSampleDetailEntity.setSampleId(t.getId());
                        emkSampleDetailEntity.setType("1");
                        systemService.save(emkSampleDetailEntity);
                    }
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单更新失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params = "doPrintPDF")
    public String doPrintPDF(String ids,EmkAccessoriesEntity emkAccessories, HttpServletRequest request,HttpServletResponse response) {
        String message = null;
        try {
            for (String id : ids.split(",")) {
                String fileName = "c:\\PDF\\"+ DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
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
                emkAccessories =  systemService.get(EmkAccessoriesEntity.class, id);

                Map type = systemService.findOneForJdbc("select typecode,typename from t_s_type t2 left join t_s_typegroup t1 on t1.ID=t2.typegroupid where typegroupcode='gylx' and typecode=?",emkAccessories.getGyzl());
                String gyzl = "";
                if(Utils.notEmpty(type)){
                    gyzl = type.get("typename").toString();
                }

                List<EmkSampleDetailEntity2> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", id);
                new createPdf(file).generateXqkfdPDF(gyzl,null,emkAccessories,null, emkSampleDetailEntities,findProcessList(id));
                String fFileName = "c:\\PDF\\F"+DateUtil.format(new Date(),"yyyyMMddHHmmss")+".pdf";
                WaterMark.waterMark(fileName,fFileName, "缝制辅料需求开发单");
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
    public ModelAndView goAdd(EmkAccessoriesEntity emkAccessories, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessories.getId())) {
            emkAccessories = emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
            req.setAttribute("emkAccessoriesPage", emkAccessories);
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-add");
    }

    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(EmkAccessoriesEntity emkAccessories, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessories.getId())) {
            emkAccessories = emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
            req.setAttribute("emkAccessoriesPage", emkAccessories);
            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkAccessories);
                int a=0,b=0;
                a = Integer.parseInt(countMap.get("finishColums").toString());
                b = Integer.parseInt(countMap.get("Colums").toString())-4;

                countMap.put("finishColums",a);
                countMap.put("Colums",b);
                req.setAttribute("countMap", countMap);
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-update");
    }

    @RequestMapping(params = "goUpdate2")
    public ModelAndView goUpdate2(EmkAccessoriesEntity emkAccessories, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessories.getId())) {
            emkAccessories = emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessories.getId());
            req.setAttribute("emkAccessoriesPage", emkAccessories);

            try {
                Map countMap = MyBeanUtils.culBeanCounts(emkAccessories);
                int a=0,b=0;
                a = Integer.parseInt(countMap.get("finishColums").toString());
                b = Integer.parseInt(countMap.get("Colums").toString())-4;

                countMap.put("finishColums",a);
                countMap.put("Colums",b);
                req.setAttribute("countMap", countMap);
                DecimalFormat df = new DecimalFormat("#.00");
                req.setAttribute("recent", df.format(a*100/b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-update2");
    }

    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "emkAccessoriesController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(params = "exportXls")
    public String exportXls(EmkAccessoriesEntity emkAccessories, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        CriteriaQuery cq = new CriteriaQuery(EmkAccessoriesEntity.class, dataGrid);
        HqlGenerateUtil.installHql(cq, emkAccessories, request.getParameterMap());
        List<EmkAccessoriesEntity> emkAccessoriess = emkAccessoriesService.getListByCriteriaQuery(cq, Boolean.valueOf(false));
        modelMap.put("fileName", "缝制辅料需求开发单");
        modelMap.put("entity", EmkAccessoriesEntity.class);
        modelMap.put("params", new ExportParams("缝制辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", emkAccessoriess);
        return "jeecgExcelView";
    }

    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(EmkAccessoriesEntity emkAccessories, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
        modelMap.put("fileName", "缝制辅料需求开发单");
        modelMap.put("entity", EmkAccessoriesEntity.class);
        modelMap.put("params", new ExportParams("缝制辅料需求开发单列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));

        modelMap.put("data", new ArrayList());
        return "jeecgExcelView";
    }


    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "缝制辅料需求开发单列表信息", produces = "application/json", httpMethod = "GET")
    public ResponseMessage<List<EmkAccessoriesEntity>> list() {
        List<EmkAccessoriesEntity> listEmkAccessoriess = emkAccessoriesService.getList(EmkAccessoriesEntity.class);
        return Result.success(listEmkAccessoriess);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据ID获取缝制辅料需求开发单信息", notes = "根据ID获取缝制辅料需求开发单信息", httpMethod = "GET", produces = "application/json")
    public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
        EmkAccessoriesEntity task = emkAccessoriesService.get(EmkAccessoriesEntity.class, id);
        if (task == null) {
            return Result.error("根据ID获取缝制辅料需求开发单信息为空");
        }
        return Result.success(task);
    }

    @RequestMapping(method = {org.springframework.web.bind.annotation.RequestMethod.POST}, consumes = "application/json")
    @ResponseBody
    @ApiOperation("创建缝制辅料需求开发单")
    public ResponseMessage<?> create(@ApiParam(name = "缝制辅料需求开发单对象") @RequestBody EmkAccessoriesEntity emkAccessories, UriComponentsBuilder uriBuilder) {
        Set<ConstraintViolation<EmkAccessoriesEntity>> failures = validator.validate(emkAccessories, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkAccessoriesService.save(emkAccessories);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("缝制辅料需求开发单信息保存失败");
        }
        return Result.success(emkAccessories);
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.PUT}, consumes = "application/json")
    @ResponseBody
    @ApiOperation(value = "更新缝制辅料需求开发单", notes = "更新缝制辅料需求开发单")
    public ResponseMessage<?> update(@ApiParam(name = "缝制辅料需求开发单对象") @RequestBody EmkAccessoriesEntity emkAccessories) {
        Set<ConstraintViolation<EmkAccessoriesEntity>> failures = validator.validate(emkAccessories, new Class[0]);
        if (!failures.isEmpty()) {
            return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
        }
        try {
            emkAccessoriesService.saveOrUpdate(emkAccessories);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("更新缝制辅料需求开发单信息失败");
        }
        return Result.success("更新缝制辅料需求开发单信息成功");
    }

    @RequestMapping(value = "/{id}", method = {org.springframework.web.bind.annotation.RequestMethod.DELETE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("删除缝制辅料需求开发单")
    public ResponseMessage<?> delete(@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
        logger.info("delete[{}]" + id);
        if (StringUtils.isEmpty(id)) {
            return Result.error("ID不能为空");
        }
        try {
            emkAccessoriesService.deleteEntityById(EmkAccessoriesEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("缝制辅料需求开发单删除失败");
        }
        return Result.success();
    }

    @RequestMapping(params="doSubmit")
    @ResponseBody
    public AjaxJson doSubmit(EmkAccessoriesEntity emkAccessoriesEntity, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "缝制辅料需求开发单提交成功";
        try {
            int flag = 0;
            TSUser user = (TSUser)request.getSession().getAttribute("LOCAL_CLINET_USER");
            Map<String, String> map = ParameterUtil.getParamMaps(request.getParameterMap());
            if ((emkAccessoriesEntity.getId() == null) || (emkAccessoriesEntity.getId().isEmpty())) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkAccessoriesEntity accessoriesEntity = systemService.getEntity(EmkAccessoriesEntity.class, id);
                    if (!accessoriesEntity.getState().equals("0")) {
                        message = "存在已提交的缝制辅料需求开发单，请重新选择在提交！";
                        j.setSuccess(false);
                        flag = 1;
                        break;
                    }
                }
            }else{
                map.put("ids", emkAccessoriesEntity.getId());
            }
            Map<String, Object> variables = new HashMap();
            if (flag == 0) {
                for (String id : map.get("ids").toString().split(",")) {
                    EmkAccessoriesEntity t = emkAccessoriesService.get(EmkAccessoriesEntity.class, id);
                    EmkApprovalEntity b = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",t.getId());
                    if(Utils.isEmpty(b)){
                        b = new EmkApprovalEntity();
                        EmkApprovalDetailEntity approvalDetailEntity = new EmkApprovalDetailEntity();
                        ApprovalUtil.saveApproval(b,7,t.getMaterialNo(),t.getId(),user);
                        systemService.save(b);
                        ApprovalUtil.saveApprovalDetail(approvalDetailEntity,b.getId(),"缝制辅料需求开发申请单","materialTask","提交",user);
                        systemService.save(approvalDetailEntity);
                    }

                    List<Task> task = taskService.createTaskQuery().taskAssignee(id).list();
                    TSUser makerUser = systemService.findUniqueByProperty(TSUser.class,"userName",t.getCreateBy());
                    TSUser bpmUser = null;
                    if (task.size() > 0) {
                        bpmUser = systemService.get(TSUser.class,b.getBpmSherId());
                    }else{
                        bpmUser = systemService.get(TSUser.class,b.getCommitId());
                    }
                    //保存审批意见
                    EmkApprovalDetailEntity approvalDetail = ApprovalUtil.saveApprovalDetail(b.getId(),user,b,map.get("advice"));
                    variables.put("optUser", t.getId());

                    if (task.size() > 0) {
                        Task task1 = (Task)task.get(task.size() - 1);
                        if (task1.getTaskDefinitionKey().equals("materialTask")) {
                            taskService.complete(task1.getId(), variables);
                            t.setState("1");
                            b.setStatus(1);

                            b.setNextBpmSher(t.getBusinesserName());
                            b.setNextBpmSherId(t.getBusinesser());
                            bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());

                            saveApprvoalDetail(approvalDetail,"重新提交的缝制辅料需求开发申请单","materialTask",0,"重新提交缝制辅料需求开发申请单");
                            saveSmsAndEmailForOne("业务员审核","您有【"+user.getRealName()+"】重新提交的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",bpmUser,user.getUserName());

                            //saveSmsAndEmailForMany("业务员","业务员审核","您有【"+b.getCreateName()+"】重新提交的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if (task1.getTaskDefinitionKey().equals("ywyTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(35);
                                approvalDetail.setBpmName("业务员审核");
                                t.setState("35");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("业务经理","业务员审核","您有【"+user.getRealName()+"】审核过的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务员审核","ywyTask",1,map.get("advice"));
                                backProcess(task1.getProcessInstanceId(),"ywyTask","materialTask","缝制辅料需求开发申请单");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("checkTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(3);
                                approvalDetail.setBpmName("业务经理审核");
                                t.setState("3");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购员","业务经理审核","您有【"+user.getRealName()+"】审核过的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"业务经理审核","checkTask",1,map.get("advice"));
                                backProcess(task1.getProcessInstanceId(),"checkTask","materialTask","缝制辅料需求开发申请单");
                                systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"ywyTask");
                                systemService.executeSql("delete from act_hi_taskinst where proc_inst_id_=? and task_def_key_=? ",task1.getProcessInstanceId(),"ywyTask");
                                t.setState("0");
                                b.setStatus(0);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("业务经理审核","您有【"+user.getRealName()+"】回退的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(24);
                                approvalDetail.setBpmName("采购员审核");
                                t.setState("24");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("采购经理","采购员审核","您有【"+user.getRealName()+"】审核过的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购员审核","cgyTask",1,map.get("advice"));
                                backProcess(task1.getProcessInstanceId(),"cgyTask","checkTask","业务经理审核");
                                systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",task1.getProcessInstanceId(),"isPass");

                                t.setState("4");
                                b.setStatus(4);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购员审核","您有【"+user.getRealName()+"】回退的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgjlTask")) {
                            b.setNextBpmSherId(bpmUser.getUserName());
                            b.setNextBpmSher(bpmUser.getRealName());

                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(15);
                                approvalDetail.setBpmName("采购部经理复核");
                                t.setState("15");
                                approvalDetail.setApproveStatus(0);
                                saveSmsAndEmailForMany("业务经理","采购部经理复核","您有【"+user.getRealName()+"】审核过的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购部经理复核","cgjlTask",1,map.get("advice"));
                                backProcess(task1.getProcessInstanceId(),"cgjlTask","cgyTask","采购员审核");
                                t.setState("36");
                                b.setStatus(36);
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购部经理复核","您有【"+user.getRealName()+"】回退的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyzxTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(37);
                                approvalDetail.setBpmName("采购员执行");
                                t.setState("37");
                                approvalDetail.setApproveStatus(0);
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgyjdTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(38);
                                approvalDetail.setBpmName("采购员进度");
                                t.setState("38");
                                approvalDetail.setApproveStatus(0);
                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("rksqTask")) {
                            if(map.get("isPass").equals("0")) {
                                variables.put("isPass","0");
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(39);
                                approvalDetail.setBpmName("入库申请单【采购员】");
                                t.setState("39");

                                EmkMInStorageEntity emkMInStorageEntity = new EmkMInStorageEntity();
                                MyBeanUtils.copyBeanNotNull2Bean(t,emkMInStorageEntity);
                                emkMInStorageEntity.setId(null);
                                emkMInStorageEntity.setRkNo("RK"+DateUtil.format(new Date(),"yyyyMMddHHmmss"));
                                emkMInStorageEntity.setType("1");
                                emkMInStorageEntity.setCreateBy(user.getUserName());
                                emkMInStorageEntity.setCreateName(user.getRealName());
                                emkMInStorageEntity.setState("0");
                                emkMInStorageEntity.setFormType("1");
                                emkMInStorageEntity.setAppler(user.getRealName());
                                emkMInStorageEntity.setApplerId(user.getId());
                                emkMInStorageEntity.setRker(user.getRealName());
                                emkMInStorageEntity.setRkerId(user.getUserName());
                                emkMInStorageEntity.setCaigouer(bpmUser.getUserName());
                                emkMInStorageEntity.setCaigouerName(bpmUser.getRealName());
                                emkMInStorageEntity.setMaterialNo(t.getMaterialNo());
                                systemService.save(emkMInStorageEntity);

                                List<EmkSampleDetailEntity> emkSampleDetailEntities = systemService.findHql("from EmkSampleDetailEntity where sampleId=? and type=1", t.getId());
                                EmkMInStorageDetailEntity emkMInStorageDetailEntity = null;
                                for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntities){
                                    emkMInStorageDetailEntity = new EmkMInStorageDetailEntity();
                                    MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkMInStorageDetailEntity);
                                    emkMInStorageDetailEntity.setId(null);
                                    emkMInStorageDetailEntity.setTotal(sampleDetailEntity.getSignTotal());
                                    emkMInStorageDetailEntity.setInStorageId(emkMInStorageEntity.getId());
                                    systemService.save(emkMInStorageDetailEntity);
                                }

                            }
                        }
                        if (task1.getTaskDefinitionKey().equals("cgjlshTask")) {
                            String title = "入库申请单";
                            if(map.get("isPass").equals("0")) {
                                if(b.getStatus().equals(39)){
                                    variables.put("isType","0");
                                }else if(b.getStatus().equals(43)){
                                    variables.put("isType","1");
                                    title = "出库申请单";
                                }
                                taskService.complete(task1.getId(), variables);
                                b.setStatus(41);
                                approvalDetail.setBpmName("采购部经理审核");
                                t.setState("41");
                                approvalDetail.setApproveStatus(0);

//                                EmkMInStorageEntity emkMInStorageEntity = systemService.findUniqueByProperty(EmkMInStorageEntity.class,"materialNo",t.getMaterialNo());
                                saveSmsAndEmailForOne("采购部经理审核","您有【"+user.getRealName()+"】提交的"+title+"，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }else{
                                saveApprvoalDetail(approvalDetail,"采购部经理审核","cgjlshTask",1,map.get("advice"));
                                if(b.getStatus().equals(39)){
                                    backProcess(task1.getProcessInstanceId(),"cgjlshTask","rksqTask","采购部经理审核");
                                    t.setState("36");
                                    b.setStatus(36);
                                }else if(b.getStatus().equals(43)){
                                    backProcess(task1.getProcessInstanceId(),"cgjlshTask","cksqdTask","采购部经理审核");
                                    t.setState("23");
                                    b.setStatus(23);
                                    title = "出库申请单";
                                }
                                b.setBpmStatus("1");
                                saveSmsAndEmailForOne("采购部经理审核","您有【"+user.getRealName()+"】回退的"+title+"，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                            }
                        }
                        if(task1.getTaskDefinitionKey().equals("ckyrkTask")) {
                            taskService.complete(task1.getId(), variables);
                            b.setStatus(58);
                            approvalDetail.setBpmName("仓库员入库");
                            t.setState("58");
                            approvalDetail.setApproveStatus(0);

                            bpmUser = systemService.get(TSUser.class,b.getCommitId());
                            saveSmsAndEmailForOne("仓库员入库","您有【"+user.getRealName()+"】完成的入库申请单，单号："+b.getWorkNum()+"，请及时处理。",bpmUser,user.getUserName());
                        }
                        if(task1.getTaskDefinitionKey().equals("cksqdTask")) {
                            taskService.complete(task1.getId(), variables);
                            b.setStatus(43);
                            approvalDetail.setBpmName("出库申请单【技术员】");
                            t.setState("43");
                            approvalDetail.setApproveStatus(0);

                            saveSmsAndEmailForMany("采购经理","出库申请单【技术员】","您有【"+user.getRealName()+"】出库申请单【技术员】，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if (task1.getTaskDefinitionKey().equals("cksqdTask")) {
                            taskService.complete(task1.getId(), variables);
                            b.setStatus(43);
                            approvalDetail.setBpmName("出库申请单【技术员】");
                            t.setState("43");
                            approvalDetail.setApproveStatus(0);

                            saveSmsAndEmailForMany("采购经理","出库申请单【技术员】","您有【"+user.getRealName()+"】出库申请单【技术员】，单号："+b.getWorkNum()+"，请及时审核。",user.getUserName());
                        }
                        if (task1.getTaskDefinitionKey().equals("jsylqTask")) {
                            taskService.complete(task1.getId(), variables);
                            b.setStatus(2);
                            approvalDetail.setBpmName("技术员领取");
                            t.setState("2");
                            approvalDetail.setApproveStatus(0);
                        }
                        systemService.save(approvalDetail);
                    }else {
                        ProcessInstance pi = processEngine.getRuntimeService().startProcessInstanceByKey("accessories", "emkAccessoriesEntity", variables);
                        task = taskService.createTaskQuery().taskAssignee(id).list();
                        Task task1 = task.get(task.size() - 1);
                        taskService.complete(task1.getId(), variables);

                        t.setState("1");
                        b.setStatus(1);
                        b.setBpmStatus("0");
                        b.setProcessName("业务员审核");

                        b.setNextBpmSher(t.getBusinesserName());
                        b.setNextBpmSherId(t.getBusinesser());
                        bpmUser = systemService.findUniqueByProperty(TSUser.class,"userName",b.getNextBpmSherId());

                        saveApprvoalDetail(approvalDetail,"提交的缝制辅料需求开发申请单","materialTask",0,"提交缝制辅料需求开发申请单");
                        saveSmsAndEmailForOne("业务员审核","您有【"+user.getRealName()+"】提交的缝制辅料需求开发申请单，单号："+b.getWorkNum()+"，请及时审核。",bpmUser,user.getUserName());

                    }

                    systemService.saveOrUpdate(t);
                    systemService.saveOrUpdate(b);
                }
            }
            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        }
        catch (Exception e) {
            e.printStackTrace();
            message = "缝制辅料需求开发单提交失败";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

    @RequestMapping(params="goWork")
    public ModelAndView goWork(EmkAccessoriesEntity emkAccessoriesEntity, HttpServletRequest req) {
        if (StringUtil.isNotEmpty(emkAccessoriesEntity.getId())) {
            emkAccessoriesEntity = emkAccessoriesService.getEntity(EmkAccessoriesEntity.class, emkAccessoriesEntity.getId());
            req.setAttribute("emkAccessories", emkAccessoriesEntity);
        }
        return new ModelAndView("com/emk/storage/accessories/emkAccessories-work");

    }

    @RequestMapping(params="goTime")
    public ModelAndView goTime(EmkAccessoriesEntity emkAccessoriesEntity, HttpServletRequest req, DataGrid dataGrid) {
        EmkApprovalEntity approvalEntity = systemService.findUniqueByProperty(EmkApprovalEntity.class,"formId",emkAccessoriesEntity.getId());
        if(Utils.notEmpty(approvalEntity)){
            List<EmkApprovalDetailEntity> approvalDetailEntityList = systemService.findHql("from EmkApprovalDetailEntity where approvalId=? order by approveDate asc",approvalEntity.getId());
            req.setAttribute("approvalDetailEntityList", approvalDetailEntityList);
            req.setAttribute("approvalEntity", approvalEntity);
            req.setAttribute("createDate", org.jeecgframework.core.util.DateUtils.date2Str(approvalEntity.getCreateDate(), org.jeecgframework.core.util.DateUtils.datetimeFormat));
        }
        return new ModelAndView("com/emk/storage/accessories/time");

    }
}
