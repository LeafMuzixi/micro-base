package com.mzx.micro.config

import cn.hutool.crypto.SecureUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair

/**
 * Jwt 令牌配置
 */
@Configuration
class JwtTokenConfig {
    /**
     * 注册 Jwt 访问令牌转换器
     */
    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setKeyPair(keyPair())
        return jwtAccessTokenConverter
    }

    /**
     * 获取密钥对
     */
    @Bean
    fun keyPair(): KeyPair {
        // TODO 当前每次启动生成，应该从其它途径获取 (如数据库)
//        val rsa = SecureUtil.rsa()
//        return KeyPair(rsa.publicKey, rsa.privateKey)
        val keyStoreKeyFactory = KeyStoreKeyFactory(ClassPathResource("jwt.jks"), "123456".toCharArray())
        return keyStoreKeyFactory.getKeyPair("jwt")
    }
}