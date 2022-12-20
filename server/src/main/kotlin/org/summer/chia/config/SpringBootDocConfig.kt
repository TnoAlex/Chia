package org.summer.chia.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringBootDocConfig {

    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI().info(
            Info().title("Chia Apis").description("系统开放开放API").version("1.0.1")
        )
    }

    @Bean
    fun captchaAPI(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("验证模块")
            .packagesToScan("org.summer.chia.controller")
            .pathsToMatch("/verify/**", "/forget/**")
            .build()
    }

    @Bean
    fun userAPI(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("用户模块")
            .packagesToScan("org.summer.chia.controller")
            .pathsToMatch("/student/**", "/teacher/**", "/user/**")
            .build()
    }

    @Bean
    fun cspInfoAPI(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("CSP信息模块")
            .packagesToScan("org.summer.chia.controller")
            .pathsToMatch("/csp_info/**")
            .build()
    }


    @Bean
    fun cspPreInfoAPI(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("CSP预报名模块")
            .packagesToScan("org.summer.chia.controller")
            .pathsToMatch("/pre/**")
            .build()
    }

    @Bean
    fun messageAPI(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("消息模块")
            .packagesToScan("org.summer.chia.controller")
            .pathsToMatch("/message/**")
            .build()
    }
}