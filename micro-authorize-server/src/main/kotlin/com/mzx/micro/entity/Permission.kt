package com.mzx.micro.entity

import com.mzx.micro.enums.MethodEnum
import io.ebean.annotation.Length
import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * 权限表
 */
@Entity
@Table(name = "t_permission")
class Permission(
        /**
         * 名称
         */
        @Length(32) var name: String
) : BaseDomain() {
    /**
     * 父权限 Id
     */
    var parentId: Long? = null

    /**
     * URL
     */
    @Length(512)
    var url: String? = null

    /**
     * 请求方式
     */
    var method: MethodEnum = MethodEnum.GET

    /**
     * 菜单路径
     */
    @Length(512)
    var menuPath: String? = null

    /**
     * 关联角色
     */
    @ManyToMany
    lateinit var roles: List<Role>
}
