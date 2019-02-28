package isel.ps.employbox.resources

import com.google.code.siren4j.annotations.Siren4JEntity
import com.google.code.siren4j.annotations.Siren4JSubEntity
import com.google.code.siren4j.resource.CollectionResource
import pt.isel.ps.employbox.model.ApplicationModel
import pt.isel.ps.employbox.model.CurriculumModel

/**
 * @author tiago.ribeiro
 */
@Siren4JEntity(name = "user_account", uri = "/accounts/users/{id}")
class UserAccountResource : AccountResource() {
    var summary: String? = null
    var photoUrl: String? = null

    @Siren4JSubEntity(uri = "/accounts/users/{parent.id}/curricula", embeddedLink = true)
    var curricula: CollectionResource<CurriculumModel> = CollectionResource()
    @Siren4JSubEntity(uri = "/accounts/users/{parent.id}/applications", embeddedLink = true)
    var applications: CollectionResource<ApplicationModel> = CollectionResource()
}