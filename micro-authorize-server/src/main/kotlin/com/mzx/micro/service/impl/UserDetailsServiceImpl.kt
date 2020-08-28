package com.mzx.micro.service.impl

import com.mzx.micro.entity.Permission
import com.mzx.micro.entity.Role
import com.mzx.micro.service.PermissionService
import com.mzx.micro.service.RoleService
import com.mzx.micro.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.lang.NullPointerException

typealias SysUser = org.springframework.security.core.userdetails.User

/**
 * 用户详细信息服务实现
 * Security 与 OAuth2 使用
 */
@Service
class UserDetailsServiceImpl(val userService: UserService,
                             val roleService: RoleService,
                             val permissionService: PermissionService) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val findOne = userService.getByUsername(username!!) ?: throw UsernameNotFoundException("账户不存在")

        return findOne.run {
            SysUser.withUsername(username)
                    .password(password)

                    /*
                     这两个方法配置的是相同的内容，即 authorities，位于之后的配置会覆盖之前的配置
                     TODO 这里暂时使用权限表的内容，之后可以调整
                     */
//                    .roles(*roleService.getByUserId(id).map(Role::name).toTypedArray())
                    .authorities(*permissionService.getByUserId(id).map(Permission::name).toTypedArray())

                    .disabled(locked)
                    .build()
        }
    }
}