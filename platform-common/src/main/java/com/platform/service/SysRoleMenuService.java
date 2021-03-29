package com.platform.service;

import com.platform.entity.SysMenuEntity;
import com.platform.entity.SysRoleMenuEntity;

import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuService {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

	/**
	 * 修改菜单权限
	 * @param sysMenuEntity
	 */
	void update(SysRoleMenuEntity sysMenuEntity);

	/**
	 * 新增菜单权限
	 * @param sysMenuEntity
	 */
	void save(SysRoleMenuEntity sysMenuEntity);

}
