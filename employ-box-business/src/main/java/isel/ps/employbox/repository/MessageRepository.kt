package isel.ps.employbox.repository

import isel.ps.base.repository.BaseRepository
import isel.ps.employbox.domain.Chat
import isel.ps.employbox.domain.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

/**
 * @author tiago.ribeiro
 */
@Repository
interface MessageRepository : BaseRepository<Message, Long> {
    fun findByChat(chat: Chat, pageable: Pageable) : Page<Message>
}