package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

/**
 * @author tiago.ribeiro
 */
class CommentModel(
        id: Long? = null,
        var text: String? = null,
        var status: Boolean? = null,
        var accountFrom: AccountModel? = null,
        var accountDest: AccountModel? = null
) : IdBaseModel<Long>(id)