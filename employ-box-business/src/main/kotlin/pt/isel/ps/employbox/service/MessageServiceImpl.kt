package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.business.AbstractBaseEntityBO
import pt.isel.ps.employbox.domain.Message
import pt.isel.ps.employbox.model.MessageModel

/**
 * @author tiago.ribeiro
 */
@Component
class MessageServiceImpl(
        modelMapper: ModelMapper,
        override val business: AbstractBaseEntityBO<Message, Long>
) : AbstractBaseEntityService<Message, MessageModel, Long>(modelMapper), MessageService {
    override val modelClass = MessageModel::class.java
    override val entityClass = Message::class.java

    override fun convertBeforeSave(model: MessageModel) = convert(model, entityClass)
}