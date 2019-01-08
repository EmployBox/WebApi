package pt.isel.ps.employbox.business

import pt.isel.ps.base.BaseBean
import pt.isel.ps.base.MapperFactoryBean
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractBaseBO<T, U>(@Autowired mapperFactoryBean: MapperFactoryBean) : BaseBean(mapperFactoryBean){

}
