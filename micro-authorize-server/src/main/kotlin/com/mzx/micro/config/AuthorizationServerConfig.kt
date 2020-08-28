package com.mzx.micro.config

import com.mzx.micro.provider.JwtTokenEnhancer
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import javax.sql.DataSource

/**
 * 授权服务器配置
 */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
        val dataSource: DataSource,
        val authorizationManager: AuthenticationManager,
        val authorizationCodeServices: AuthorizationCodeServices,
        val approvalStore: ApprovalStore,
        val jwtAccessTokenConverter: JwtAccessTokenConverter,
        val jwtTokenEnhancer: JwtTokenEnhancer
) : AuthorizationServerConfigurerAdapter() {

    /**
     * 授权服务器安全配置
     */
    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security?.run {
            // 开启 /oauth/toke_key 验证端口无权限访问
            tokenKeyAccess("permitAll()")
            // 开启 /oauth/check_token 验证端口无权限访问
            checkTokenAccess("permitAll()")
            // 开启表单认证，允许 /oauth/token 支持 client_id 以及 client_secret 做登录认证
            allowFormAuthenticationForClients()
        }

    }

    /**
     * 客户详细信息服务配置
     */
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        // 从数据库中获取配置信息
        clients?.jdbc(dataSource)
    }

    /**
     * 授权服务器端点配置
     */
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints?.let { point ->
//            // 配置 Jwt 访问令牌转换器
//            point.accessTokenConverter(jwtAccessTokenConverter)
            // 配置认证管理器 (密码模式需要)
            point.authenticationManager(authorizationManager)
            // 配置授权码服务
            point.authorizationCodeServices(authorizationCodeServices)
            // 配置批准存储库
            point.approvalStore(approvalStore)
            /*
             需要增强时，在此配置增强器，同时访问令牌转换器也在此配置
             增强在转换之前，因此将转换器最后填入
             */
            val tokenEnhancerChain = TokenEnhancerChain()
            tokenEnhancerChain.setTokenEnhancers(listOf(jwtTokenEnhancer, jwtAccessTokenConverter))
            point.tokenEnhancer(tokenEnhancerChain)
        }
    }
}