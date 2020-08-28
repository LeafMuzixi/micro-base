package com.mzx.micro.config

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.config.Customizer.withDefaults

/**
 * 资源服务配置
 */
@EnableWebFluxSecurity
class ResourceServerConfig(val oAuth2ResourceServerProperties: OAuth2ResourceServerProperties) {
    /**
     * Web 安全过滤器链配置
     */
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                // 授权交换器
                .authorizeExchange { exchanges ->
                    exchanges
                            // 配置接口所需授权
                            .pathMatchers(HttpMethod.GET, "/test").hasAuthority("SCOPE_message:read")
                            .anyExchange().authenticated()
                }
                // 配置资源服务，指定 jwt 公钥获取地址
                .oauth2ResourceServer { oauth2ResourceServer ->
                    oauth2ResourceServer
                            .jwt { jwt ->
                                jwt.jwkSetUri(oAuth2ResourceServerProperties.jwt.jwkSetUri)
                            }
                }
        return http.build()
    }
}