package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.Carshare;
import com.suke.czx.modules.aicar.service.CarshareService;
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
@RequestMapping("/aicar/carshare")
public class CarshareController  extends AbstractController {
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
