package pt.isel.ps.employbox

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import kotlin.reflect.jvm.internal.impl.types.checker.StrictEqualityTypeChecker

/**
 * @author tiago.ribeiro
 */
@Configuration
@ComponentScan
@EnableJpaRepositories
@EntityScan
@EnableAutoConfiguration
class BusinessModuleConfiguration{
    @Bean
    fun getModelMapper() = ModelMapper()
}
