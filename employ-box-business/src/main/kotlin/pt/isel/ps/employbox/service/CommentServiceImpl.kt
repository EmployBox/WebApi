package pt.isel.ps.employbox.service

import pt.isel.ps.base.MapperFactoryBean
import pt.isel.ps.employbox.business.CommentBO
import pt.isel.ps.employbox.domain.Comment
import pt.isel.ps.employbox.model.CommentModel
import pt.isel.ps.employbox.model.PageModel
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CommentServiceImpl(
        mapperFactoryBean: MapperFactoryBean,
        override val business: CommentBO
) : AbstractBaseEntityService<Comment, CommentModel, Long>(mapperFactoryBean), CommentService {

    override val modelClass: Class<CommentModel> = CommentModel::class.java

    override val entityClass: Class<Comment> = Comment::class.java

    override fun getReplies(commentId: Long, page: Int, pageSize: Int): PageModel<CommentModel> =
            convert(business.getReplies(commentId, page, pageSize), PageModel::class.java)
                    .map { convert(it, modelClass) }

    override fun convertBeforeSave(model: CommentModel) = convert(model, entityClass)
}