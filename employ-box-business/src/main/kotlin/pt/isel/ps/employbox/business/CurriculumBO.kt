package pt.isel.ps.employbox.business

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component
import pt.isel.ps.employbox.domain.Curriculum
import pt.isel.ps.employbox.repository.CurriculumRepository

@Component
class CurriculumBO(
        override val repository: CurriculumRepository
) : AbstractBaseEntityBO<Curriculum, Long>() {

    fun findByAccountId(accountId: Long, page: Int, pageSize: Int, orderColumn: String, orderClause: String) =
            repository.findByAccount_Id(accountId, PageRequest.of(page, pageSize, Sort.Direction.fromString(orderClause), orderColumn))

}
