package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.Car;
import com.suke.czx.modules.aicar.service.CarService;
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
@RequestMapping("/aicar/car")
public class CarController  extends AbstractController {
    private final  CarService carService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:car:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
        IPage<Car> sysConfigList = carService.page(mpPageConvert.<Car>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carid}")
    @PreAuthorize("hasRole('aicar:car:info')")
    public R info(@PathVariable("carid") Integer carid){
        return R.ok().put("car", carService.getById(carid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:car:save')")
    public R save(@RequestBody Car car){
        carService.save(car);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:car:update')")
    public R update(@RequestBody Car car){
		carService.updateById(car);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:car:delete')")
    public R delete(@RequestBody Integer[] carids){
		carService.removeByIds(Arrays.asList(carids));
        return R.ok();
    }
	
}
