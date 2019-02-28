/**
 *
 */
package pt.isel.ps.base.entity

import org.springframework.data.util.ProxyUtils
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
    abstract var id: ID?

    companion object {
        private val serialVersionUID = -1557970941212862200L
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if(this === other) return true

        if(javaClass != ProxyUtils.getUserClass(other)) return false

        other as IdBaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode()= 31

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"
}
