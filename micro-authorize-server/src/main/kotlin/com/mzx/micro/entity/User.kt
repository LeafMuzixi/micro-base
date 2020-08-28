package com.mzx.micro.entity

import io.ebean.annotation.Length
import javax.persistence.Entity
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * 用户表
 */
@Entity
@Table(name = "t_user")
class User(
        /**
         * 账号
         */
        @Length(32) var username: String,
        /**
         * 密码
         */
        @Length(255) var password: String
) : BaseDomain() {
    /**
     * 是否锁定
     */
    var locked: Boolean = false

    /**
     * 关联权限
     */
    @ManyToMany(mappedBy = "users")
    @JoinTable(name = "t_user_role")
    lateinit var roles: List<Role>
}