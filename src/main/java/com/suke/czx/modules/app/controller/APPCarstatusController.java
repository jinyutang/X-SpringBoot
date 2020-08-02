package com.suke.czx.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suke.czx.common.annotation.SysLog;
import com.suke.czx.common.base.AbstractController;
import com.suke.czx.common.utils.R;
import com.suke.czx.modules.aicar.entity.Carstatus;
import com.suke.czx.modules.aicar.service.CarstatusService;
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
@RequestMapping("/app/aicar/carstatus")
public class APPCarstatusController extends AbstractController {
    private final  CarstatusService carstatusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:carstatus:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Carstatus> queryWrapper = new QueryWrapper<>();
        IPage<Carstatus> sysConfigList = carstatusService.page(mpPageConvert.<Carstatus>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carstatusid}")
    @PreAuthorize("hasRole('aicar:carstatus:info')")
    public R info(@PathVariable("carstatusid") Integer carstatusid){
        return R.ok().put("carstatus", carstatusService.getById(carstatusid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:carstatus:save')")
    public R save(@RequestBody Carstatus carstatus){
        carstatusService.save(carstatus);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:carstatus:update')")
    public R update(@RequestBody Carstatus carstatus){
		carstatusService.updateById(carstatus);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:carstatus:delete')")
    public R delete(@RequestBody Integer[] carstatusids){
		carstatusService.removeByIds(Arrays.asList(carstatusids));
        return R.ok();
    }
	
}
