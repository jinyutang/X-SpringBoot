package com.suke.czx.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.suke.czx.common.annotation.SysLog;
import com.suke.czx.common.base.AbstractController;
import com.suke.czx.common.utils.R;
import com.suke.czx.modules.aicar.entity.Recharge;
import com.suke.czx.modules.aicar.service.RechargeService;
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
@RequestMapping("/app/aicar/recharge")
public class APPRechargeController extends AbstractController {
    private final  RechargeService rechargeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:recharge:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<Recharge> queryWrapper = new QueryWrapper<>();
        IPage<Recharge> sysConfigList = rechargeService.page(mpPageConvert.<Recharge>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{rechargeid}")
    @PreAuthorize("hasRole('aicar:recharge:info')")
    public R info(@PathVariable("rechargeid") Integer rechargeid){
        return R.ok().put("recharge", rechargeService.getById(rechargeid));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:recharge:save')")
    public R save(@RequestBody Recharge recharge){
        rechargeService.save(recharge);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:recharge:update')")
    public R update(@RequestBody Recharge recharge){
		rechargeService.updateById(recharge);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:recharge:delete')")
    public R delete(@RequestBody Integer[] rechargeids){
		rechargeService.removeByIds(Arrays.asList(rechargeids));
        return R.ok();
    }
	
}
