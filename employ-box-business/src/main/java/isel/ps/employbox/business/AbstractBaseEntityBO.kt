package isel.ps.employbox.business

import isel.ps.base.entity.IdBaseEntity
import isel.ps.base.entity.VersionedBaseEntity
import isel.ps.base.repository.BaseRepository
import isel.ps.exception.BaseRuntimeException
import isel.ps.exception.BusinessException
import kotlinx.coroutines.future.await
import org.hibernate.exception.ConstraintViolationException
import org.springframework.data.domain.PageRequest
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.io.Serializable
import javax.persistence.PersistenceException

abstract class AbstractBaseEntityBO<E: IdBaseEntity<ID>, ID : Serializable> {
    abstract val repository: BaseRepository<E, ID>

    private fun cast(entity: E) = entity as VersionedBaseEntity<ID>

    fun save(example: E) : E {
        try {

            example!!.doBeforeSave()

            if (example.id == null)
                return repository.save(example)
            else {
                /*
				 * The version control is made here because the approuch of convert model to entity.
				 * The most of the cases the model have just some atributes to be updated and in that scenario the
				 * entity are loaded and the changed attributes are updated, but if you try change the regversion
				 * by your self from the model will not have effect in the same transaction and the hibernate will
				 * not throw an execption.
				 * */
                if (example is VersionedBaseEntity<*>) {

                    val regVersion = cast(repository.getOne(example!!.id)).regVersion

                    if (regVersion != cast(example).regVersion){
                        //throw buildBusinessException(BusinessMessageEnum.ENTITY_OUT_OF_DATE);
                    }

                }

                return repository.save(example)

            }

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

    fun get(id: ID) = repository.findById(id)

    fun remove(id: ID) = repository.deleteById(id)

    fun getAll() = repository.findAll()


    fun getAll(offset: Int, pageSize: Int) = repository.findAll(PageRequest.of(offset, pageSize)).content

    fun getByParams(page: Int, pageSize: Int, orderClause: String, orderColumn: String) =
            repository.getByParams(page, pageSize, orderClause, orderColumn)
}