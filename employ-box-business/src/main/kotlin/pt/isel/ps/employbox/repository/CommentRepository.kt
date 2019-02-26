package pt.isel.ps.employbox.repository

import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * @author tiago.ribeiro
 */
@Repository
interface CommentRepository : BaseRepository<Comment, Long> {
    fun findByMainComment_Id(mainCommentId: Long, pageable: Pageable) : Page<Comment>
    fun findByAccountFrom_Id(accountFrom_id: Long, pageable: Pageable) : Page<Comment>
}