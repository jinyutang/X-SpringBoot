package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.Device;
import com.suke.czx.modules.aicar.service.DeviceService;
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
@RequestMapping("/aicar/device")
public class DeviceController  extends AbstractController {
    private final  DeviceService deviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:device:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        IPage<Device> sysConfigList = deviceService.page(mpPageConvert.<Device>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{deviceid}")
    @PreAuthorize("hasRole('aicar:device:info')")
    public R info(@PathVariable("deviceid") Integer deviceid){
        return R.ok().put("device", deviceService.getById(deviceid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:device:save')")
    public R save(@RequestBody Device device){
        deviceService.save(device);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:device:update')")
    public R update(@RequestBody Device device){
		deviceService.updateById(device);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:device:delete')")
    public R delete(@RequestBody Integer[] deviceids){
		deviceService.removeByIds(Arrays.asList(deviceids));
        return R.ok();
    }
	
}
