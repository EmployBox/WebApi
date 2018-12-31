package isel.ps.employbox.service

import isel.ps.base.service.BaseEntityService
import isel.ps.employbox.model.ChatModel
import isel.ps.employbox.model.MessageModel
import isel.ps.employbox.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface ChatService : BaseEntityService<ChatModel, Long> {
    fun getAccountChats(accountId : Long, page: Int, pageSize: Int) : PageModel<ChatModel>
    fun getChatMessages(chatId: Long, page: Int, pageSize: Int) : PageModel<MessageModel>
}