package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel

class ProjectModel(
        id: Long,
        var name: String?,
        var description: String?
) : IdBaseModel<Long>(id)