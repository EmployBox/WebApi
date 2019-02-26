package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Comment
import pt.isel.ps.employbox.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CommentBO(
        @Autowired override val repository: CommentRepository
) : AbstractBaseEntityBO<Comment, Long>() {
    fun getReplies(commentId: Long, page: Int, pageSize: Int) =
            repository.findByMainComment_Id(commentId, PageRequest.of(page, pageSize))

    fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            repository.findByAccountFrom_Id(accountId, PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))
}