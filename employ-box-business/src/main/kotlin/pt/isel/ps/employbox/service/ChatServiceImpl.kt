package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import pt.isel.ps.employbox.business.ChatBO
import pt.isel.ps.employbox.domain.Chat
import pt.isel.ps.employbox.model.ChatModel
import pt.isel.ps.employbox.model.MessageModel
import pt.isel.ps.base.model.PageModel
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class ChatServiceImpl(
        override val business: ChatBO, modelMapper: ModelMapper
) : AbstractBaseEntityService<Chat, ChatModel, Long>(modelMapper), ChatService {

    override val modelClass: Class<ChatModel> = ChatModel::class.java

    override val entityClass: Class<Chat> = Chat::class.java

    override fun convertBeforeSave(model: ChatModel) = convert(model, Chat::class.java)

    override fun getAccountChats(accountId: Long, page: Int, pageSize: Int) =
        convert(business.getAccountChats(accountId, page, pageSize), PageModel::class.java)
                .map { convert(it, modelClass) }

    override fun getChatMessages(chatId: Long, page: Int, pageSize: Int) =
            convert(business.getChatMessages(chatId, page, pageSize), PageModel::class.java)
                    .map { convert(it, MessageModel::class.java) }
}