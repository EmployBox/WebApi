package isel.ps.employbox

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.web.reactive.config.EnableWebFlux
import pt.isel.ps.employbox.BusinessModuleConfiguration
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux


@SpringBootApplication
@EnableWebFlux
@Import(BusinessModuleConfiguration::class)
@EnableSwagger2WebFlux
class EmployBoxAPIApp

fun main(args: Array<String>) {
    SpringApplication.run(EmployBoxAPIApp::class.java, *args)
}
