package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.Carfaultcodes;
import com.suke.czx.modules.aicar.service.CarfaultcodesService;
import com.suke.czx.common.utils.R;
import lombok.AllArgsConstructor;
import com.suke.czx.common.annotation.SysLog;
import com.suke.czx.common.base.AbstractController;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.Arrays;



/**
 * 
 * 
 * @author yutang.jin
 * @email 619366438@qq.com
 * @date 2020-08-01 16:07:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aicar/carfaultcodes")
public class CarfaultcodesController  extends AbstractController {
    private final  CarfaultcodesService carfaultcodesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:carfaultcodes:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Carfaultcodes> queryWrapper = new QueryWrapper<>();
        IPage<Carfaultcodes> sysConfigList = carfaultcodesService.page(mpPageConvert.<Carfaultcodes>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carfaultcodesid}")
    @PreAuthorize("hasRole('aicar:carfaultcodes:info')")
    public R info(@PathVariable("carfaultcodesid") Integer carfaultcodesid){
        return R.ok().put("carfaultcodes", carfaultcodesService.getById(carfaultcodesid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:carfaultcodes:save')")
    public R save(@RequestBody Carfaultcodes carfaultcodes){
        carfaultcodesService.save(carfaultcodes);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:carfaultcodes:update')")
    public R update(@RequestBody Carfaultcodes carfaultcodes){
		carfaultcodesService.updateById(carfaultcodes);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:carfaultcodes:delete')")
    public R delete(@RequestBody Integer[] carfaultcodesids){
		carfaultcodesService.removeByIds(Arrays.asList(carfaultcodesids));
        return R.ok();
    }
	
}
