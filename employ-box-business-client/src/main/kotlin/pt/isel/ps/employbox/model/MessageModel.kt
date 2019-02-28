package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

/**
 * @author tiago.ribeiro
 */
class MessageModel(
        id: Long? = null,
        var text: String? = null,
        var account: AccountModel? = null,
        var chat: ChatModel? = null
) : IdBaseModel<Long>(id) {
}