package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.IdBaseEntity
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.ManyToOne

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
) : IdBaseEntity<Rating.RatingKey>() {
    @Embeddable
    data class RatingKey (
            @ManyToOne
            var accountFrom: Account?,
            @ManyToOne
            var accountTo: Account?
    ) : Serializable
}

