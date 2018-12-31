/**
 *
 */
package pt.isel.ps.base.entity

import javax.persistence.*
import java.io.Serializable
import java.util.Date

/**
 * The base entity for all project entities.
 *
 * @author davis.dun
 */
@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    var created: Date? = null
        private set

    @Temporal(TemporalType.TIMESTAMP)
    var updated: Date? = null
        private set

    val lastUpdateDate: Date?
        get() = if (updated != null) updated else created

    @PrePersist
    protected fun onCreate() {
        created = Date()
    }

    @PreUpdate
    protected fun onUpdate() {
        updated = Date()
    }

    /**
     * <pre>
     * Called before save
     *
     * Most used in cases an entity have a ManyToOne relationship
     * and need to reference the new childs to him for a persist or merge
     * operation.
    </pre> *
     */
    fun doBeforeSave() {

    }

    /**
     * <pre>
     * Called before delete
     *
     * Most used in cases an entity need to check or force to remove some reference.
    </pre> *
     */
    fun doBeforeDelete() {

    }


//    override fun equals(obj: Any?): Boolean {
//        return EqualsBuilder.reflectionEquals(this, obj, false)
//    }
//
//    override fun hashCode(): Int {
//        return HashCodeBuilder.reflectionHashCode(this, false)
//    }

    companion object {

        private const val serialVersionUID = -2901388249152097036L
    }

}
