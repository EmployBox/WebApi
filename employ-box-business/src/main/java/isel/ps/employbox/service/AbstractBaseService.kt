package isel.ps.employbox.service

import isel.ps.base.BaseBean
import isel.ps.base.MapperFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
abstract class AbstractBaseService(mapperFactoryBean: MapperFactoryBean) : BaseBean(mapperFactoryBean)