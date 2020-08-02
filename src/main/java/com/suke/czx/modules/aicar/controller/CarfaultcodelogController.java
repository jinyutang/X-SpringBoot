package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.Carfaultcodelog;
import com.suke.czx.modules.aicar.service.CarfaultcodelogService;
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
@RequestMapping("/aicar/carfaultcodelog")
public class CarfaultcodelogController  extends AbstractController {
    private final  CarfaultcodelogService carfaultcodelogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:carfaultcodelog:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Carfaultcodelog> queryWrapper = new QueryWrapper<>();
        IPage<Carfaultcodelog> sysConfigList = carfaultcodelogService.page(mpPageConvert.<Carfaultcodelog>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{carfaultcodelog}")
    @PreAuthorize("hasRole('aicar:carfaultcodelog:info')")
    public R info(@PathVariable("carfaultcodelog") Integer carfaultcodelog){
        return R.ok().put("carfaultcodelog", carfaultcodelogService.getById(carfaultcodelog));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:carfaultcodelog:save')")
    public R save(@RequestBody Carfaultcodelog carfaultcodelog){
        carfaultcodelogService.save(carfaultcodelog);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:carfaultcodelog:update')")
    public R update(@RequestBody Carfaultcodelog carfaultcodelog){
		carfaultcodelogService.updateById(carfaultcodelog);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:carfaultcodelog:delete')")
    public R delete(@RequestBody Integer[] carfaultcodelogs){
		carfaultcodelogService.removeByIds(Arrays.asList(carfaultcodelogs));
        return R.ok();
    }
	
}
