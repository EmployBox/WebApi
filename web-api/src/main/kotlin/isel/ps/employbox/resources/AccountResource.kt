package isel.ps.employbox.resources

import com.google.code.siren4j.annotations.Siren4JAction
import com.google.code.siren4j.annotations.Siren4JEntity
import com.google.code.siren4j.annotations.Siren4JSubEntity
import com.google.code.siren4j.resource.BaseResource
import com.google.code.siren4j.resource.CollectionResource
import pt.isel.ps.employbox.common.AccountTypeEnum
import pt.isel.ps.employbox.model.ChatModel
import pt.isel.ps.employbox.model.CommentModel
import pt.isel.ps.employbox.model.JobModel

/**
 * @author tiago.ribeiro
 */

@Siren4JEntity(name = "account", uri = "/accounts/{id}")
class AccountResource: BaseResource() {
    var id: Long? = null
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var accountType: AccountTypeEnum? = null
    var rating: Double? = null

    @Siren4JSubEntity(uri = "/accounts/{parent.id}/jobs/offered", embeddedLink = true)
    var offeredJobs: CollectionResource<JobModel> = CollectionResource()
    @Siren4JSubEntity(uri = "/accounts/{parent.id}/comments", embeddedLink = true)
    var comments: CollectionResource<CommentModel> = CollectionResource()
    @Siren4JSubEntity(uri = "/accounts/{parent.id}/following", embeddedLink = true)
    var following: CollectionResource<AccountResource> =CollectionResource()
    @Siren4JSubEntity(uri = "/accounts/{parent.id}/followed", embeddedLink = true)
    var followers: CollectionResource<AccountResource> = CollectionResource()
    @Siren4JSubEntity(uri = "/accounts/{parent.id}/chats", embeddedLink = true)
    var chats: CollectionResource<ChatModel> = CollectionResource()

}