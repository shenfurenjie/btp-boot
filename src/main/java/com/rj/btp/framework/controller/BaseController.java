
package com.rj.btp.framework.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rj.btp.framework.common.context.ApplicationContextRegister;
import com.rj.btp.framework.common.enums.ErrorCodeEnum;
import com.rj.btp.framework.common.utils.BeanConvertUtil;
import com.rj.btp.framework.common.utils.WebUtil;
import com.rj.btp.framework.common.wrapper.RestResponse;
import com.rj.btp.framework.model.condition.*;
import com.rj.btp.framework.service.BaseService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.rj.btp.framework.common.wrapper.RestResponse.success;

/**
 * BaseController
 * 提供单表操作的基础接口
 */
@Slf4j
public abstract class BaseController<T, S extends BaseService<T>> extends AbstractController implements InitializingBean {


    protected Class<T> entityClass;

    protected S service;

    @Autowired
    ApplicationContextRegister applicationContextRegister;

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() {
        if (service == null || entityClass == null) {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            Class<?> cls0 = (Class<?>) type.getActualTypeArguments()[0];
            Class<?> cls1 = (Class<?>) type.getActualTypeArguments()[1];
            try {
                entityClass = (Class<T>) cls0;
                service = (S) ApplicationContextRegister.getBean(cls1);
            } catch (Exception e) {
                logger.info("初始化controller失败,请查看配置启动扫描mapper路径", e);
            }
        }
    }


    @RequestMapping("/index")
    public RestResponse index() {
        return success("hello world");
    }

    /**
     * 查询数据集合
     */
    @ApiOperation(value = "查询数据集合")
    @PostMapping(value = "/queryList")
    public RestResponse queryList(HttpServletRequest request, HttpServletResponse response) {
        String queryCndJson = buildQueryCndJson(request);
        List<T> list = new ArrayList<>();
        if (StringUtils.isEmpty(queryCndJson)) {
            list = service.list(MyBaitsConditionUtil.buildQueryWrapper(new QueryCondition()));
        } else {
            list = service.list(MyBaitsConditionUtil.buildQueryWrapper(queryCndJson));
        }
        return success(list);
    }

    /**
     * 查询单条数据
     */
    @ApiOperation(value = "查询单条数据")
    @PostMapping(value = "/queryOne")
    public RestResponse queryOne(HttpServletRequest request, HttpServletResponse response) {
        String queryCndJson = buildQueryCndJson(request);
        if (StringUtils.isEmpty(queryCndJson)) {
            RestResponse.failure(ErrorCodeEnum.BAD_REQUEST);
        }
        List<?> list = service.list(MyBaitsConditionUtil.buildQueryWrapper(queryCndJson));
        if (CollectionUtils.isEmpty(list)) {
            return RestResponse.failure(ErrorCodeEnum.DATA_NOT_FOUND);
        }
        if (list.size() > 1) {
            return RestResponse.failure(ErrorCodeEnum.DATA_RANGE_NOT_SATISFIABLE);
        }
        return success(list.get(0));
    }

    /**
     * 分页查询数据
     */
    @ApiOperation(value = "分页查询数据")
    @PostMapping(value = "/queryByPaged")
    public RestResponse queryListByPage(HttpServletRequest request, HttpServletResponse response) {
        QueryCondition queryCondition = ConditionUtil.buildQueryCondition(buildQueryCndJson(request));
        PageCnd pageCnd = queryCondition.getPageCnd();
        if (pageCnd == null) {
            return RestResponse.failure(ErrorCodeEnum.BAD_REQUEST);
        }
        Page result = (Page) service.pageObject((IPage<T>) pageCnd, MyBaitsConditionUtil.buildQueryWrapper(queryCondition));
        return success();
    }

    /**
     * 查询数据总数
     */
    @ApiOperation(value = "查询数据总数")
    @PostMapping(value = "/queryCount")
    public RestResponse queryCount(HttpServletRequest request, HttpServletResponse response) {
        Integer count = service.count(MyBaitsConditionUtil.buildQueryWrapper(buildQueryCndJson(request)));
        return success(count);
    }

    /**
     * 新增数据，支持批量
     */
    @ApiOperation(value = "新增数据")
    @PostMapping(value = "/save")
    public RestResponse save(HttpServletRequest request, HttpServletResponse response) {
        UpdateCondition updateCondition = ConditionUtil.buildUpdateCondition(buildUpdateCndJson(request));
        //新增时，条件非空场景
        if (CollectionUtils.isNotEmpty(updateCondition.getRelCndArray())) {
            return RestResponse.failure(ErrorCodeEnum.BAD_REQUEST);
        }
        List<Map<String, Object>> updateMapList = updateCondition.getSetCndArray();
        List<T> saveObjects = null;
        try {
            saveObjects = BeanConvertUtil.mapToBean(updateMapList, (Class<T>) entityClass.newInstance().getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean flag = service.saveOrUpdateBatch(saveObjects);
        return success(flag);
    }


    /**
     * 修改数据,支持批量，单条，条件
     */
    @ApiOperation(value = "修改数据")
    @PostMapping(value = "/update")
    public RestResponse update(HttpServletRequest request, HttpServletResponse response) {
        UpdateCondition updateCondition = ConditionUtil.buildUpdateCondition(buildUpdateCndJson(request));
        List<Map<String, Object>> updateMapList = updateCondition.getSetCndArray();
        List<T> saveObjects = null;
        try {
            saveObjects = BeanConvertUtil.mapToBean(updateMapList, (Class<T>) entityClass.newInstance().getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //set为空场景
        if (CollectionUtils.isEmpty(saveObjects)) {
            return RestResponse.failure(ErrorCodeEnum.BAD_REQUEST);
        }
        //条件非空场景
        if (CollectionUtils.isNotEmpty(updateCondition.getRelCndArray())) {
            //批量
            if (saveObjects.size() > 1) {
                return RestResponse.failure(ErrorCodeEnum.BAD_REQUEST);
            }
            //条件更新
            UpdateWrapper updateWrapper = MyBaitsConditionUtil.buildUpdateWrapper(updateCondition);
            boolean flag = service.update(saveObjects.get(0), updateWrapper);
            return success(flag);
        }
        //批量或者单条更新
        Boolean flag = service.saveOrUpdateBatch(saveObjects);
        return success(flag);
    }


    /**
     * 删除数据
     */
    @ApiOperation(value = "删除数据")
    @DeleteMapping(value = "/delete")
    public RestResponse delete(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = service.remove(MyBaitsConditionUtil.buildQueryWrapper(buildQueryCndJson(request)));
        return success(flag);
    }

    /**
     * 统计查询
     */
//    @ApiOperation(value = "统计查询")
//    @RequestMapping(value = "/queryStatistics")
//    public RestResponse queryStatistics(@RequestParam(name = "queryCondition") QueryCondition queryCondition){
//        //Integer count = service.(ConditionUtil.buildQueryWrapper(queryCondition));
//        return RestResponse.success();
//    }
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }


    public String buildQueryCndJson(HttpServletRequest request) {
        String requestBody = WebUtil.getRequestBody(request);
        Map reqBodyMap = (Map) JSON.parse(requestBody);
        if (reqBodyMap == null) {
            return null;
        }
        String queryCndJson = JSON.toJSONString(reqBodyMap.get(CndEnum.queryCondition.name()));
        return queryCndJson;
    }

    public String buildUpdateCndJson(HttpServletRequest request) {
        String requestBody = WebUtil.getRequestBody(request);
        Map reqBodyMap = (Map) JSON.parse(requestBody);
        if (reqBodyMap == null) {
            return null;
        }
        String queryCndJson = JSON.toJSONString(reqBodyMap.get(CndEnum.updateCondition.name()));
        return queryCndJson;
    }
}
