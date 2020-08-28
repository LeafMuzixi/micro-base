package com.mzx.micro.config

import cn.hutool.core.lang.Snowflake
import io.ebean.Database
import io.ebean.DatabaseFactory
import io.ebean.config.DatabaseConfig
import io.ebean.config.IdGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * 数据库配置
 */
@Configuration
class DBConfig {
    /**
     * 注册雪花算法
     */
    @Bean
    fun snowflake(): Snowflake {
        return Snowflake(0, 0)
    }

    /**
     * 注册雪花算法 Id 生成器
     */
    @Bean
    fun idGenerator(): IdGenerator {
        return object : IdGenerator {
            override fun nextValue(): Any {
                return snowflake().nextId()
            }

            override fun getName(): String {
                return "snowflakeIdGenerator"
            }
        }
    }

    /**
     * 配置数据源
     */
    @Bean
    fun database(dataSource: DataSource): Database {
        val databaseConfig = DatabaseConfig()
        databaseConfig.apply {
            this.dataSource = dataSource
            // 开启以下两个选项，ebean 会根据 bean 自动生成表
//            this.isDdlGenerate = true
//            this.isDdlRun = true
        }
        return DatabaseFactory.create(databaseConfig)
    }
}