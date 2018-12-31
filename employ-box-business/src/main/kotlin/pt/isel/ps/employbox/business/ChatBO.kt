package pt.isel.ps.employbox.business

import pt.isel.ps.employbox.domain.Chat
import pt.isel.ps.employbox.domain.Message
import pt.isel.ps.employbox.repository.ChatRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class ChatBO(
        @Autowired override val repository: ChatRepository,
        @Autowired val accountBO: AccountBO,
        @Autowired val messageBO: MessageBO
) : AbstractBaseEntityBO<Chat, Long>() {

    fun getAccountChats(accountId: Long, page: Int, pageSize: Int) =
        repository.findByAccountFirst(accountBO.get(accountId), PageRequest.of(page, pageSize))

    fun getChatMessages(chatId: Long, page: Int, pageSize: Int): Page<Message> =
        messageBO.getChatMessages(this.get(chatId), page, pageSize)

}