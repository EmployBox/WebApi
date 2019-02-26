package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class ApplicationModel(
        id: Long,
        var datetime: Date?,
        var job: JobModel?,
        var account: AccountModel?,
        var curriculum: CurriculumModel?
) : IdBaseModel<Long>(id) {

}
