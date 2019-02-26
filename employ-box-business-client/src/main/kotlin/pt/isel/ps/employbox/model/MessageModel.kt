package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

/**
 * @author tiago.ribeiro
 */
class MessageModel(
        id: Long,
        var text: String?,
        var account: AccountModel,
        var chat: ChatModel
) : IdBaseModel<Long>(id) {
}