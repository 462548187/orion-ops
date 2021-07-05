package com.orion.ops.controller;

import com.orion.lang.wrapper.DataGrid;
import com.orion.ops.annotation.RestWrapper;
import com.orion.ops.consts.Const;
import com.orion.ops.entity.request.ApplicationInfoRequest;
import com.orion.ops.entity.vo.ApplicationInfoVO;
import com.orion.ops.service.api.ApplicationInfoService;
import com.orion.ops.utils.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 应用 api
 *
 * @author Jiahang Li
 * @version 1.0.0
 * @since 2021/7/2 17:54
 */
@RestController
@RestWrapper
@RequestMapping("/orion/api/app-info")
public class ApplicationInfoController {

    @Resource
    private ApplicationInfoService applicationService;

    /**
     * 添加应用
     */
    @RequestMapping("/add")
    public Long insertApp(@RequestBody ApplicationInfoRequest request) {
        Valid.notNull(request.getName());
        Valid.notNull(request.getTag());
        Valid.notNull(request.getSort());
        return applicationService.insertApp(request);
    }

    /**
     * 更新应用
     */
    @RequestMapping("/update")
    public Integer updateApp(@RequestBody ApplicationInfoRequest request) {
        Valid.notNull(request.getId());
        return applicationService.updateApp(request);
    }

    /**
     * 更新排序
     */
    @RequestMapping("/sort")
    public Integer updateAppSort(@RequestBody ApplicationInfoRequest request) {
        Long id = Valid.notNull(request.getId());
        Integer adjust = Valid.in(request.getSortAdjust(), Const.INCR, Const.DECR);
        return applicationService.updateAppSort(id, Const.INCR.equals(adjust));
    }

    /**
     * 删除应用
     */
    @RequestMapping("/delete")
    public Integer deleteApp(@RequestBody ApplicationInfoRequest request) {
        Long id = Valid.notNull(request.getId());
        return applicationService.deleteApp(id);
    }

    /**
     * 应用列表
     */
    @RequestMapping("/list")
    public DataGrid<ApplicationInfoVO> listApp(@RequestBody ApplicationInfoRequest request) {
        return applicationService.listApp(request);
    }

}
