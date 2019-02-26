package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

/**
 * @author tiago.ribeiro
 */
class ChatModel(
        id: Long,
        var accountFirst: AccountModel,
        var accountSecond: AccountModel
) : IdBaseModel<Long>(id) {
    lateinit var messages: List<MessageModel>
}
