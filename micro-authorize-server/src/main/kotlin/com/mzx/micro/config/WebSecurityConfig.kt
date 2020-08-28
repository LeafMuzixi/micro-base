package com.mzx.micro.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices
import javax.sql.DataSource

/**
 * Web 安全配置
 */
@EnableWebSecurity
class WebSecurityConfig(
        val dataSource: DataSource,
        @Qualifier("userDetailsServiceImpl") val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {
    /**
     * 配置资源安全
     */
    override fun configure(http: HttpSecurity?) {
        http?.run {
            // 配置授权请求
            this.authorizeRequests()
                    .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                    .antMatchers("/rsa/publicKey").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    // 表单登录
                    .formLogin()
        }
    }

    /**
     * 重写认证管理器构建，注入用户信息服务
     */
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.run {
            userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
        }
    }

    /**
     * 注册认证管理器
     */
    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    /**
     * 注册密码编码器
     */
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /**
     * 注册 JDBC 授权码服务
     */
    @Bean
    fun authorizationCodeServices(): AuthorizationCodeServices {
        return JdbcAuthorizationCodeServices(dataSource)
    }

    /**
     * 注册 JDBC 批准存储库
     */
    @Bean
    fun approvalStore(): ApprovalStore {
        return JdbcApprovalStore(dataSource)
    }
}