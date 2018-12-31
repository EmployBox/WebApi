package isel.ps.employbox.repository

import isel.ps.base.repository.BaseRepository
import isel.ps.employbox.domain.Account
import isel.ps.employbox.domain.Chat
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * @author tiago.ribeiro
 */
@Repository
interface ChatRepository : BaseRepository<Chat, Long> {
    fun findByAccountFirst(account: Account, pageable: Pageable) : Page<Chat>
}