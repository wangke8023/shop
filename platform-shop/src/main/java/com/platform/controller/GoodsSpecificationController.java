package com.platform.controller;

import java.util.List;
import java.util.Map;

import com.platform.entity.SysUserEntity;
import com.platform.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.entity.GoodsSpecificationEntity;
import com.platform.service.GoodsSpecificationService;

/**
 * 商品对应规格表值表Controller
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-08-31 11:15:55
 */
@RestController
@RequestMapping("goodsspecification")
public class GoodsSpecificationController {
    @Autowired
    private GoodsSpecificationService goodsSpecificationService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goodsspecification:list")
    public R list(@RequestParam Map<String, Object> params) {
	    SysUserEntity sysUserEntity= ShiroUtils.getUserEntity();

	    //查询列表数据
	    if(!(sysUserEntity.getRoleIdList().contains(Constant.SUPER_ROLE))){
		    params.put("merchantId",sysUserEntity.getMerchantId());
	    }
        Query query = new Query(params);

        List<GoodsSpecificationEntity> goodsSpecificationList = goodsSpecificationService.queryList(query);
        int total = goodsSpecificationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(goodsSpecificationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 查看信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goodsspecification:info")
    public R info(@PathVariable("id") Integer id) {
        GoodsSpecificationEntity goodsSpecification = goodsSpecificationService.queryObject(id);

        return R.ok().put("goodsSpecification", goodsSpecification);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goodsspecification:save")
    public R save(@RequestBody GoodsSpecificationEntity goodsSpecification) {
    	goodsSpecification.setMerchantId(ShiroUtils.getUserEntity().getMerchantId().intValue());
        goodsSpecificationService.save(goodsSpecification);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goodsspecification:update")
    public R update(@RequestBody GoodsSpecificationEntity goodsSpecification) {
        goodsSpecificationService.update(goodsSpecification);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goodsspecification:delete")
    public R delete(@RequestBody Integer[]ids) {
        goodsSpecificationService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/queryAll")
    public R queryAll(@RequestParam Map<String, Object> params) {
    	params.put("merchantId", ShiroUtils.getUserEntity().getMerchantId());
        List<GoodsSpecificationEntity> list = goodsSpecificationService.queryList(params);

        return R.ok().put("list", list);
    }
}
