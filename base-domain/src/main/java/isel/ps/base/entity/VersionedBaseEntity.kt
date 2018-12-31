/**
 *
 */
package isel.ps.base.entity

import javax.persistence.MappedSuperclass
import javax.persistence.Version

/**
 * The versioned base entity.
 *
 * @author davis.dun
 */
@MappedSuperclass
abstract class VersionedBaseEntity<ID> : IdBaseEntity<ID>() {

    /**
     * @return the regVersion
     */
    /**
     * @param regVersion the regVersion to set
     */
    @Version
    var regVersion: Long? = null

    companion object {
        private val serialVersionUID = 6769406037775715396L
    }
}
