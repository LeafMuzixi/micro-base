dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // security oauth2
    implementation("org.springframework.security:spring-security-oauth2-resource-server")
    implementation("org.springframework.security:spring-security-oauth2-jose")
    implementation("org.springframework.cloud:spring-cloud-starter-oauth2")

    // 使用相应数据库驱动
    runtimeOnly("org.postgresql:postgresql")
    // jdbc: 不添加则无法通过 Springboot 配置自动加载 DataSource
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    // ebean
    implementation("io.ebean:ebean:${Version.ebean}")
    implementation("io.ebean:ebean-querybean:${Version.ebean}")
    kapt("io.ebean:kotlin-querybean-generator:${Version.ebean}")
    testImplementation("io.ebean:ebean-test:${Version.ebean}")
}