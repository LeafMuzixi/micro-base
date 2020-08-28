package com.mzx.micro.enums

import io.ebean.annotation.DbEnumValue

/**
 * 方法请求类型枚举
 */
enum class MethodEnum(@get:DbEnumValue var value: String) {
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE")
}