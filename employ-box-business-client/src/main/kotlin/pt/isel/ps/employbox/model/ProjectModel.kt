package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class ProjectModel(
        id: Long? = null,
        var name: String? = null,
        var description: String? = null
) : IdBaseModel<Long>(id)