package com.suke.czx.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suke.czx.common.annotation.SysLog;
import com.suke.czx.common.base.AbstractController;
import com.suke.czx.common.utils.R;
import com.suke.czx.modules.aicar.entity.Cargroup;
import com.suke.czx.modules.aicar.service.CargroupService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 
 * 
 * @author yutang.jin
 * @email 619366438@qq.com
 * @date 2020-08-01 16:07:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/app/aicar/cargroup")
public class APPCargroupController extends AbstractController {
    private final  CargroupService cargroupService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:cargroup:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Cargroup> queryWrapper = new QueryWrapper<>();
        IPage<Cargroup> sysConfigList = cargroupService.page(mpPageConvert.<Cargroup>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cargoupid}")
    @PreAuthorize("hasRole('aicar:cargroup:info')")
    public R info(@PathVariable("cargoupid") Integer cargoupid){
        return R.ok().put("cargroup", cargroupService.getById(cargoupid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:cargroup:save')")
    public R save(@RequestBody Cargroup cargroup){
        cargroupService.save(cargroup);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:cargroup:update')")
    public R update(@RequestBody Cargroup cargroup){
		cargroupService.updateById(cargroup);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:cargroup:delete')")
    public R delete(@RequestBody Integer[] cargoupids){
		cargroupService.removeByIds(Arrays.asList(cargoupids));
        return R.ok();
    }
	
}
