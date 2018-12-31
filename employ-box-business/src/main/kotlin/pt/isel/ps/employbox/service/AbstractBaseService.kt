package pt.isel.ps.employbox.service

import pt.isel.ps.base.BaseBean
import pt.isel.ps.base.MapperFactoryBean
import org.springframework.stereotype.Component

@Component
abstract class AbstractBaseService(mapperFactoryBean: MapperFactoryBean) : BaseBean(mapperFactoryBean)