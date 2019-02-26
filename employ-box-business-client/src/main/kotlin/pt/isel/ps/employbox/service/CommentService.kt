package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.CommentModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface CommentService : BaseEntityService<CommentModel, Long> {
    fun getReplies(commentId: Long, page: Int, pageSize: Int) : PageModel<CommentModel>
    fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String): PageModel<CommentModel>
}