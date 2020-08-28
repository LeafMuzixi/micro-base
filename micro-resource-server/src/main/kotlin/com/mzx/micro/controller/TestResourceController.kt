package com.mzx.micro.controller

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 测试资源控制器
 */
@RestController
@RequestMapping("/test")
class TestResourceController {
    /**
     * 测试接口，返回认证信息
     */
    @GetMapping
    fun hello(authentication: Authentication): Authentication {
        return authentication
    }
}