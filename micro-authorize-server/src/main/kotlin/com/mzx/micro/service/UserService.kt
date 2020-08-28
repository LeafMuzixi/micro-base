package com.mzx.micro.service

import com.mzx.micro.entity.User

/**
 * 用户服务接口
 */
interface UserService {
    /**
     * 根据用户名获取用户
     */
    fun getByUsername(username: String): User?
}