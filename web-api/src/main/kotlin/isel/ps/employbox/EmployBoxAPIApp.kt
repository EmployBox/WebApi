package isel.ps.employbox

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import pt.isel.ps.employbox.BusinessModuleConfiguration


@SpringBootApplication
@Import(BusinessModuleConfiguration::class)
class EmployBoxAPIApp

fun main(args: Array<String>) {
    SpringApplication.run(EmployBoxAPIApp::class.java, *args)
}
