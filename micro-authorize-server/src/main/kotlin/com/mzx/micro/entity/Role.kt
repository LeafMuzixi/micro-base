package com.mzx.micro.entity

import io.ebean.annotation.Length
import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * 角色表
 */
@Entity
@Table(name = "t_role")
class Role(
        /**
         * 名称
         */
        @Length(32) var name: String
) : BaseDomain() {
    /**
     * 描述
     */
    @Length(512)
    var description: String? = null

    /**
     * 是否可用
     */
    var enable: Boolean = true

    /**
     * 关联用户
     */
    @ManyToMany
    lateinit var users: List<User>

    /**
     * 关联权限
     */
    @ManyToMany(mappedBy = "roles")
    @JoinTable(name = "t_role_permission")
    lateinit var permissions: List<Permission>
}