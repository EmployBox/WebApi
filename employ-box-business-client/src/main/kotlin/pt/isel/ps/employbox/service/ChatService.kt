package pt.isel.ps.employbox.service

import pt.isel.ps.base.service.BaseEntityService
import pt.isel.ps.employbox.model.ChatModel
import pt.isel.ps.employbox.model.MessageModel
import pt.isel.ps.base.model.PageModel

/**
 * @author tiago.ribeiro
 */
interface ChatService : BaseEntityService<ChatModel, Long> {
    fun getAccountChats(accountId : Long, page: Int, pageSize: Int) : PageModel<ChatModel>
    fun getChatMessages(chatId: Long, page: Int, pageSize: Int) : PageModel<MessageModel>
}