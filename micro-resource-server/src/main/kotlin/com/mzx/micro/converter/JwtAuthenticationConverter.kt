package com.mzx.micro.converter

import net.minidev.json.JSONArray
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

/**
 * Jwt身份验证转换器
 * 目的是取出 claims 中 authorities 字段作为权限判断
 */
@Component
class JwtAuthenticationConverter : Converter<Jwt, Mono<AbstractAuthenticationToken>> {
    override fun convert(source: Jwt): Mono<AbstractAuthenticationToken> {
        return Mono.just(source).map { jwt ->
            val authorities = jwt.claims.getOrDefault("authorities", JSONArray()) as List<*>
            JwtAuthenticationToken(jwt, authorities
                    .filterNotNull()
                    .map { obj: Any -> obj.toString() }
                    .map { role: String? ->
                        SimpleGrantedAuthority(role)
                    })
        }
    }
}