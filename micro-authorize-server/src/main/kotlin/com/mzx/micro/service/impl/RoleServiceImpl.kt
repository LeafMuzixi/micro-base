package com.mzx.micro.service.impl

import com.mzx.micro.entity.Role
import com.mzx.micro.entity.query.QRole
import com.mzx.micro.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl : RoleService {
    override fun getByUserId(userId: Long): List<Role> {
        return QRole().users.id.eq(userId).findList()
    }
}