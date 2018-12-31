package isel.ps.employbox.business

import isel.ps.employbox.domain.Chat
import isel.ps.employbox.domain.Message
import isel.ps.employbox.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class MessageBO(
        @Autowired override val repository: MessageRepository
) : AbstractBaseEntityBO<Message, Long>() {
    fun getChatMessages(chat: Chat, page: Int, pageSize: Int): Page<Message> =
            repository.findByChat(chat, PageRequest.of(page, pageSize))

}