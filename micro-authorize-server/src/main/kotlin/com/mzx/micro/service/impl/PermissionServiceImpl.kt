package com.mzx.micro.service.impl

import com.mzx.micro.entity.Permission
import com.mzx.micro.entity.query.QPermission
import com.mzx.micro.service.PermissionService
import org.springframework.stereotype.Service

@Service
class PermissionServiceImpl : PermissionService {
    override fun getByUserId(userId: Long): List<Permission> {
        return QPermission().roles.users.id.eq(userId).findList()
    }
}