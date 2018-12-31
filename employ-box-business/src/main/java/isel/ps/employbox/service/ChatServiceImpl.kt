package isel.ps.employbox.service

import isel.ps.base.MapperFactoryBean
import isel.ps.employbox.business.ChatBO
import isel.ps.employbox.domain.Chat
import isel.ps.employbox.model.ChatModel
import isel.ps.employbox.model.MessageModel
import isel.ps.employbox.model.PageModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author tiago.ribeiro
 */
@Component
class ChatServiceImpl(
        mapperFactoryBean: MapperFactoryBean,
        override val business: ChatBO
) : AbstractBaseEntityService<Chat, ChatModel, Long>(mapperFactoryBean), ChatService {

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