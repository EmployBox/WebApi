package isel.ps.employbox.repository

import isel.ps.base.repository.BaseRepository
import isel.ps.employbox.domain.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * @author tiago.ribeiro
 */
@Repository
interface CommentRepository : BaseRepository<Comment, Long> {
    fun findByMainComment(mainComment: Comment, pageable: Pageable) : Page<Comment>
}