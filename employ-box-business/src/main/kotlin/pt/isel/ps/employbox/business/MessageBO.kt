package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Chat
import pt.isel.ps.employbox.domain.Message
import pt.isel.ps.employbox.repository.MessageRepository
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
    fun getChatMessages(chatId: Long, page: Int, pageSize: Int): Page<Message> =
            repository.findByChat_Id(chatId, PageRequest.of(page, pageSize))

}