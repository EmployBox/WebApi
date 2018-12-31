package isel.ps.employbox.business

import isel.ps.base.BaseBean
import isel.ps.base.MapperFactoryBean
import org.springframework.beans.factory.annotation.Autowired

abstract class AbstractBaseBO<T, U>(@Autowired mapperFactoryBean: MapperFactoryBean) : BaseBean(mapperFactoryBean){

}
