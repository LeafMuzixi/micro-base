# micro-base
A base project for micro.

## 初始化
### micro-authorize-serer 项目
1. 配置好数据源
2. 创建 resources/schema/spring-security-oauth2.sql 中 oauth_client_details、oauth_code、oauth_approvals表 (非必须，可修改配置使用内存存储)
3. 首次运行，修改 config/DBConfig.kt 中服务配置，取消以下注释以生成用户相关表
    ```kotlin
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
    ```
4. 启动项目

### micro-resource-server 项目
资源服务 demo

# rsa branch
使用 RSA 键值对配置的授权服务及资源服务 demo，未添加权限校验