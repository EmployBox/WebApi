package pt.isel.ps.employbox.business

import pt.isel.ps.base.entity.IdBaseEntity
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.base.exception.BaseRuntimeException
import pt.isel.ps.base.exception.BusinessException
import org.hibernate.exception.ConstraintViolationException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.io.Serializable
import javax.persistence.PersistenceException

abstract class AbstractBaseEntityBO<E: IdBaseEntity<ID>, ID : Serializable> {
    abstract val repository: BaseRepository<E, ID>

    fun save(example: E) : E {
        try {

            example.doBeforeSave()
            return repository.save(example)


        } catch (e: PersistenceException) {

            if (e.cause is ConstraintViolationException)
                throw e.cause as ConstraintViolationException
            else
                throw BaseRuntimeException(e)

        } catch (e: Exception) {
            if (e !is BusinessException)
                throw BaseRuntimeException(e)
            else
                throw e
        }
    }

    fun get(id: ID) = repository.findById(id).orElseThrow { RuntimeException("entity not found") }

    fun remove(id: ID) = repository.deleteById(id)

    fun getAll() = repository.findAll()


    fun getAll(offset: Int, pageSize: Int) = repository.findAll(PageRequest.of(offset, pageSize)).content

    fun getByParams(page: Int, pageSize: Int, orderClause: String, orderColumn: String) =
            repository.findAll(PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))
}