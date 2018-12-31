package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.VersionedBaseEntity
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Rating(
        @EmbeddedId
        override var id: RatingKey?,
        var workLoad: Double?,
        var wage: Double?,
        var workEnviroment: Double?,
        var competence: Double?,
        var pontuality: Double?,
        var assiduity: Double?,
        var demeanor: Double?
) : VersionedBaseEntity<Rating.RatingKey?>() {
    @Embeddable
    class RatingKey(
            var accountIdFrom: Long,
            var accountIdTo: Long
    )
}

