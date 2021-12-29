package ru.shurick.enterprise.short.link.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfiguration {

    private companion object {
        const val CONTROLLERS_PACKAGE_PATH = "ru.shurick.enterprise.short.link.controller"
        const val API_TITLE = "SHORT LINKS SERVICE"
        const val API_DESCRIPTION = "Service for generating short links and redirects"

        const val AUTHOR = "Alexander Kamanin"
        const val URL = "https://github.com/shurick7"
        const val EMAIL = "alexkamanin@mail.ru"

        const val LICENSE = "Apache License Version 2.0"
        const val LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0.txt"
    }

    @Bean
    fun bean(): Docket = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage(CONTROLLERS_PACKAGE_PATH))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(info)

    private val info: ApiInfo
        get() = ApiInfoBuilder()
            .title(API_TITLE)
            .description(API_DESCRIPTION)
            .contact(Contact(AUTHOR, URL, EMAIL))
            .license(LICENSE)
            .licenseUrl(LICENSE_URL)
            .build()
}