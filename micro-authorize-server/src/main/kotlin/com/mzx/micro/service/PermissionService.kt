package com.mzx.micro.service

import com.mzx.micro.entity.Permission

/**
 * 权限服务接口
 */
interface PermissionService {
    /**
     * 根据用户 Id 获取权限
     */
    fun getByUserId(userId: Long): List<Permission>
}