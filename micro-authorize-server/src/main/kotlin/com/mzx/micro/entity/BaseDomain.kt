package com.mzx.micro.entity

import io.ebean.Model
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * 基础对象
 */
@MappedSuperclass
open class BaseDomain : Model() {
    /**
     * Id
     */
    @Id
    var id: Long = 0
}