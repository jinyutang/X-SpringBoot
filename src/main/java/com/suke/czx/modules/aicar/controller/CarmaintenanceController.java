package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.Carmaintenance;
import com.suke.czx.modules.aicar.service.CarmaintenanceService;
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
@RequestMapping("/aicar/carmaintenance")
public class CarmaintenanceController  extends AbstractController {
    private final  CarmaintenanceService carmaintenanceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:carmaintenance:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Carmaintenance> queryWrapper = new QueryWrapper<>();
        IPage<Carmaintenance> sysConfigList = carmaintenanceService.page(mpPageConvert.<Carmaintenance>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carmaintenanceid}")
    @PreAuthorize("hasRole('aicar:carmaintenance:info')")
    public R info(@PathVariable("carmaintenanceid") Integer carmaintenanceid){
        return R.ok().put("carmaintenance", carmaintenanceService.getById(carmaintenanceid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:carmaintenance:save')")
    public R save(@RequestBody Carmaintenance carmaintenance){
        carmaintenanceService.save(carmaintenance);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:carmaintenance:update')")
    public R update(@RequestBody Carmaintenance carmaintenance){
		carmaintenanceService.updateById(carmaintenance);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:carmaintenance:delete')")
    public R delete(@RequestBody Integer[] carmaintenanceids){
		carmaintenanceService.removeByIds(Arrays.asList(carmaintenanceids));
        return R.ok();
    }
	
}
