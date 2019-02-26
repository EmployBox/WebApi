package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import pt.isel.ps.base.BaseBean
import org.springframework.stereotype.Component

@Component
abstract class AbstractBaseService(modelMapper: ModelMapper) : BaseBean(modelMapper)