package isel.ps.employbox

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.web.reactive.config.EnableWebFlux
import pt.isel.ps.employbox.BusinessModuleConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux


@SpringBootApplication
@EnableWebFlux
@Import(BusinessModuleConfiguration::class)
@EnableSwagger2WebFlux
class EmployBoxAPIApp {

    @Bean
    fun getObjectMapper() = ObjectMapper().registerKotlinModule()

}

fun main(args: Array<String>) {
    SpringApplication.run(EmployBoxAPIApp::class.java, *args)
}
