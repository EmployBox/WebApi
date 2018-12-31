package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Comment
import pt.isel.ps.employbox.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class CommentBO(
        @Autowired override val repository: CommentRepository
) : AbstractBaseEntityBO<Comment, Long>() {
    fun getReplies(commentId: Long, page: Int, pageSize: Int) =
            repository.findByMainComment(this.get(commentId), PageRequest.of(page, pageSize))
}