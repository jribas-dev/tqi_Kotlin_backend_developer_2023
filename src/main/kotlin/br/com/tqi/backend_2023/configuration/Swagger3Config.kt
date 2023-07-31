package br.com.tqi.backend_2023.configuration

import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Swagger3Config {
    @Bean
    fun publicApi(): GroupedOpenApi? {
        return GroupedOpenApi.builder()
            .group("backendTQI")
            .addOpenApiCustomizer { openApi -> openApi.info(Info().title("TQI Desafio Backend | Kotlin | Spring").version("0.0.1")) }
            .pathsToMatch("/api/**")
            .build()
    }
}