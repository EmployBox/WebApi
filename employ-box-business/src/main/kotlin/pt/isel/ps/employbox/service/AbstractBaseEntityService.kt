package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import pt.isel.ps.employbox.business.AbstractBaseEntityBO
import pt.isel.ps.base.entity.IdBaseEntity
import pt.isel.ps.base.model.IdBaseModel
import pt.isel.ps.base.model.PageModel
import org.springframework.stereotype.Component
import java.io.Serializable

@Component
abstract class AbstractBaseEntityService<E : IdBaseEntity<ID>, M : IdBaseModel<ID>, ID : Serializable>(modelMapper: ModelMapper) : AbstractBaseService(modelMapper) {
    abstract val business: AbstractBaseEntityBO<E, ID>
    abstract val modelClass: Class<M>
    abstract val entityClass: Class<E>
    abstract fun convertBeforeSave(model: M) : E

    fun save(model: M) = convert(business.save(convertBeforeSave(model)), modelClass)

    fun remove(id: ID) = business.remove(id)

    fun retrieveAll() = convertList(business.getAll(), modelClass)

    fun retrieveAll(offset: Int, pageSize: Int) = convertList(business.getAll(offset, pageSize), modelClass)

    fun retrieve(id: ID) = convert(business.get(id), modelClass)

    fun getByParams(page: Int, pageSize: Int, orderClause: String, orderColumn: String): PageModel<M> =
            convert(business.getByParams(page, pageSize, orderClause, orderColumn), PageModel::class.java)
                    .map { convert(it, modelClass) }
}