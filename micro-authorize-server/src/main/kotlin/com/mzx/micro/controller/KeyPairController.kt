package com.mzx.micro.controller

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import net.minidev.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.KeyPair
import java.security.interfaces.RSAPublicKey

/**
 * 密钥对控制器
 */
@RestController
class KeyPairController(val keyPair: KeyPair) {
    /**
     * 获取 RSA 公钥
     */
    @GetMapping("/rsa/publicKey")
    fun publicKey(): JSONObject {
        val publicKey = RSAKey.Builder(keyPair.public as RSAPublicKey).build()
        return JWKSet(publicKey).toJSONObject()
    }
}