package com.suke.czx.modules.aicar.controller;

import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.suke.czx.modules.aicar.entity.AicarSysUser;
import com.suke.czx.modules.aicar.service.AicarSysUserService;
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
 * @email 15251524420@163.com
 * @date 2020-08-02 21:05:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aicar/sysuser")
public class AiCarSysUserController extends AbstractController {
    private final AicarSysUserService sysUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @PreAuthorize("hasRole('aicar:sysuser:list')")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        QueryWrapper<AicarSysUser> queryWrapper = new QueryWrapper<>();
        IPage<AicarSysUser> sysConfigList = sysUserService.page(mpPageConvert.<AicarSysUser>pageParamConvert(params),queryWrapper);
        return R.ok().put("page", mpPageConvert.pageValueConvert(sysConfigList));
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @PreAuthorize("hasRole('aicar:sysuser:info')")
    public R info(@PathVariable("userId") Long userId){
        return R.ok().put("sysUser", sysUserService.getById(userId));
    }


    /**
     * 新增
     */
    @SysLog("新增数据")
    @RequestMapping("/save")
    @PreAuthorize("hasRole('aicar:sysuser:save')")
    public R save(@RequestBody AicarSysUser sysUser){
        sysUserService.save(sysUser);
        return R.ok();
    }


    /**
     * 修改
     */
    @SysLog("修改数据")
    @RequestMapping("/update")
    @PreAuthorize("hasRole('aicar:sysuser:update')")
    public R update(@RequestBody AicarSysUser sysUser){
		sysUserService.updateById(sysUser);
        return R.ok();
    }


    /**
     * 删除
     */
    @SysLog("删除数据")
    @RequestMapping("/delete")
    @PreAuthorize("hasRole('aicar:sysuser:delete')")
    public R delete(@RequestBody Long[] userIds){
		sysUserService.removeByIds(Arrays.asList(userIds));
        return R.ok();
    }
	
}
