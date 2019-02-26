package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import pt.isel.ps.employbox.business.CommentBO
import pt.isel.ps.employbox.domain.Comment
import pt.isel.ps.employbox.model.CommentModel
import pt.isel.ps.base.model.PageModel
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CommentServiceImpl(
        override val business: CommentBO, modelMapper: ModelMapper
) : AbstractBaseEntityService<Comment, CommentModel, Long>(modelMapper), CommentService {

    override val modelClass: Class<CommentModel> = CommentModel::class.java

    override val entityClass: Class<Comment> = Comment::class.java

    override fun getReplies(commentId: Long, page: Int, pageSize: Int): PageModel<CommentModel> =
            convert(business.getReplies(commentId, page, pageSize), PageModel::class.java)
                    .map { convert(it, modelClass) }

    override fun convertBeforeSave(model: CommentModel) = convert(model, entityClass)

    override fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            convert(business.findByAccountId(accountId, page, pageSize, orderColumn, orderClause), PageModel::class.java)
                    .map { convert(it, modelClass) }
}