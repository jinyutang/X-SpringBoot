package com.suke.czx.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suke.czx.common.annotation.SysLog;
import com.suke.czx.common.base.AbstractController;
import com.suke.czx.common.utils.R;
import com.suke.czx.modules.aicar.entity.Carshare;
import com.suke.czx.modules.aicar.service.CarshareService;
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
@RequestMapping("/app/aicar/carshare")
public class APPCarshareController extends AbstractController {
    private final  CarshareService carshareService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:carshare:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Carshare> queryWrapper = new QueryWrapper<>();
        IPage<Carshare> sysConfigList = carshareService.page(mpPageConvert.<Carshare>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carCarid}")
    @PreAuthorize("hasRole('aicar:carshare:info')")
    public R info(@PathVariable("carCarid") Integer carCarid){
        return R.ok().put("carshare", carshareService.getById(carCarid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:carshare:save')")
    public R save(@RequestBody Carshare carshare){
        carshareService.save(carshare);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:carshare:update')")
    public R update(@RequestBody Carshare carshare){
		carshareService.updateById(carshare);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:carshare:delete')")
    public R delete(@RequestBody Integer[] carCarids){
		carshareService.removeByIds(Arrays.asList(carCarids));
        return R.ok();
    }
	
}
