package pt.isel.ps.employbox.business

import org.modelmapper.ModelMapper
import pt.isel.ps.base.BaseBean
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractBaseBO<T, U>(@Autowired modelMapper: ModelMapper ) : BaseBean(modelMapper){

}
