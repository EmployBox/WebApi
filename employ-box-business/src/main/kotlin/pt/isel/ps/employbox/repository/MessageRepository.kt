package pt.isel.ps.employbox.repository

import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.Chat
import pt.isel.ps.employbox.domain.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * @author tiago.ribeiro
 */
@Repository
interface MessageRepository : BaseRepository<Message, Long> {
    fun findByChat(chat: Chat, pageable: Pageable) : Page<Message>
}