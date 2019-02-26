package pt.isel.ps.employbox.service

import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component
import pt.isel.ps.base.model.PageModel
import pt.isel.ps.employbox.business.CurriculumBO
import pt.isel.ps.employbox.domain.Curriculum
import pt.isel.ps.employbox.model.CurriculumModel

/**
 * @author tiago.ribeiro
 */
@Component
class CurriculumServiceImpl(
        modelMapper: ModelMapper,
        override val business: CurriculumBO
) : AbstractBaseEntityService<Curriculum, CurriculumModel, Long>(modelMapper), CurriculumService {
    override val modelClass = CurriculumModel::class.java
    override val entityClass = Curriculum::class.java

    override fun convertBeforeSave(model: CurriculumModel) = convert(model, entityClass)

    override fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            convert(business.findByAccountId(accountId, page, pageSize, orderColumn, orderClause), PageModel::class.java)
                    .map { convert(it, modelClass) }
}