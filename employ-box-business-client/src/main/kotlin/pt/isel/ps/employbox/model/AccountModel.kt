package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import pt.isel.ps.employbox.common.AccountTypeEnum
import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.domain.Chat
import pt.isel.ps.employbox.domain.Comment
import pt.isel.ps.employbox.domain.Rating
import pt.isel.ps.employbox.domain.jobs.Job

open class AccountModel(
        id: Long,
        var name: String?,
        var email: String?,
        var password: String?,
        var accountType: AccountTypeEnum,
        var rating: Double?
) : IdBaseModel<Long>(id) {
    lateinit var offeredJobs: List<JobModel>
    lateinit var comments: List<CommentModel>
    lateinit var following: List<AccountModel>
    lateinit var followers: List<AccountModel>
    lateinit var chats: List<ChatModel>
}