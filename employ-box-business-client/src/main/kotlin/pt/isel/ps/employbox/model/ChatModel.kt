package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

/**
 * @author tiago.ribeiro
 */
class ChatModel(
        id: Long? = null,
        var accountFirst: AccountModel? = null,
        var accountSecond: AccountModel? = null
) : IdBaseModel<Long>(id)