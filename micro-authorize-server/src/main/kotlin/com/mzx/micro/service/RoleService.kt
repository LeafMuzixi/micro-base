package com.mzx.micro.service

import com.mzx.micro.entity.Role

/**
 * 角色服务接口
 */
interface RoleService {
    /**
     * 根据用户 Id 获取角色
     */
    fun getByUserId(userId: Long): List<Role>
}