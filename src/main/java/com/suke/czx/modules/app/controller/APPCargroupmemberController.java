package com.suke.czx.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suke.czx.common.annotation.SysLog;
import com.suke.czx.common.base.AbstractController;
import com.suke.czx.common.utils.R;
import com.suke.czx.modules.aicar.entity.Cargroupmember;
import com.suke.czx.modules.aicar.service.CargroupmemberService;
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
@RequestMapping("/app/aicar/cargroupmember")
public class APPCargroupmemberController extends AbstractController {
    private final  CargroupmemberService cargroupmemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:cargroupmember:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Cargroupmember> queryWrapper = new QueryWrapper<>();
        IPage<Cargroupmember> sysConfigList = cargroupmemberService.page(mpPageConvert.<Cargroupmember>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{cargroupmemberid}")
    @PreAuthorize("hasRole('aicar:cargroupmember:info')")
    public R info(@PathVariable("cargroupmemberid") Integer cargroupmemberid){
        return R.ok().put("cargroupmember", cargroupmemberService.getById(cargroupmemberid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:cargroupmember:save')")
    public R save(@RequestBody Cargroupmember cargroupmember){
        cargroupmemberService.save(cargroupmember);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:cargroupmember:update')")
    public R update(@RequestBody Cargroupmember cargroupmember){
		cargroupmemberService.updateById(cargroupmember);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:cargroupmember:delete')")
    public R delete(@RequestBody Integer[] cargroupmemberids){
		cargroupmemberService.removeByIds(Arrays.asList(cargroupmemberids));
        return R.ok();
    }
	
}
