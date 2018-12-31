package isel.ps.employbox.service

import isel.ps.base.service.BaseEntityService
import isel.ps.employbox.model.CommentModel
import isel.ps.employbox.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface CommentService : BaseEntityService<CommentModel, Long> {
    fun getReplies(commentId: Long, page: Int, pageSize: Int) : PageModel<CommentModel>
}