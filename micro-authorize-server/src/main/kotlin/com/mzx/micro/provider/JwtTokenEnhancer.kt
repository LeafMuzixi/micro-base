package com.mzx.micro.provider

import org.springframework.security.core.userdetails.User
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.stereotype.Component

/**
 * Jwt 令牌增强
 */
@Component
class JwtTokenEnhancer : TokenEnhancer {
    // TODO 这里添加 Jwt 额外的附加信息
    override fun enhance(accessToken: OAuth2AccessToken?, authentication: OAuth2Authentication?): OAuth2AccessToken {
        val user = authentication?.principal as User
        val map = mutableMapOf<String, Any>()

        (accessToken as DefaultOAuth2AccessToken).additionalInformation = map
        return accessToken
    }
}