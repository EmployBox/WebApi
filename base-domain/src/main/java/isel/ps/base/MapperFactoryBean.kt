package isel.ps.base

import org.springframework.stereotype.Component
import java.io.Serializable
import org.modelmapper.ModelMapper

@Component
class MapperFactoryBean : Serializable {
    val modelMapper = ModelMapper()


}