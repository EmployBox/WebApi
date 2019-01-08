/**
 *
 */
package pt.isel.ps.base.entity

import javax.persistence.MappedSuperclass

/**
 * <pre>
 * The identifiable base entity
 *
 * To change the generated
 *
</pre> *
 *
 */
@MappedSuperclass
abstract class IdBaseEntity<ID> : BaseEntity() {


    /**
     * @return the id
     */
    /**
     * @param id the id to set
     */
    abstract var id: ID

    companion object {
        private val serialVersionUID = -1557970941212862200L
    }
}
