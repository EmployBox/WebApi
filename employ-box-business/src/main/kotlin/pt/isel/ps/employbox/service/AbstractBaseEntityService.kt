package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import pt.isel.ps.employbox.business.AbstractBaseEntityBO
import pt.isel.ps.base.entity.IdBaseEntity
import pt.isel.ps.base.model.IdBaseModel
import pt.isel.ps.base.model.PageModel
import org.springframework.stereotype.Component
import pt.isel.ps.base.service.BaseEntityService
import java.io.Serializable

@Component
abstract class AbstractBaseEntityService<E : IdBaseEntity<ID>, M : IdBaseModel<ID>, ID : Serializable>(modelMapper: ModelMapper) : AbstractBaseService(modelMapper), BaseEntityService<M, ID> {
    abstract val business: AbstractBaseEntityBO<E, ID>
    abstract val modelClass: Class<M>
    abstract val entityClass: Class<E>
    abstract fun convertBeforeSave(model: M) : E

    override fun save(model: M) = convert(business.save(convertBeforeSave(model)), modelClass)

    override fun remove(id: ID) = business.remove(id)

    override fun retrieveAll() = convertList(business.getAll(), modelClass)

    override fun retrieveAll(offset: Int, pageSize: Int) = convertList(business.getAll(offset, pageSize), modelClass)

    override fun retrieve(id: ID) = convert(business.get(id), modelClass)

    override fun getByParams(page: Int, pageSize: Int, orderColumn: String, orderClause: String): PageModel<M> =
            convert(business.getByParams(page, pageSize, orderClause, orderColumn), PageModel::class.java)
                    .map { convert(it, modelClass) }
}