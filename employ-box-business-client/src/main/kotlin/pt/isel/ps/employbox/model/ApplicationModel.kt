package pt.isel.ps.employbox.model

import pt.isel.ps.base.model.IdBaseModel
import java.util.*

class ApplicationModel(
        id: Long? = null,
        var datetime: Date? = null,
        var job: JobModel? = null,
        var account: AccountModel? = null,
        var curriculum: CurriculumModel? = null
) : IdBaseModel<Long>(id) {

}
