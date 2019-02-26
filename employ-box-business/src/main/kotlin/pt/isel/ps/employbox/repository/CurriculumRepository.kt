package pt.isel.ps.employbox.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.Curriculum

@Repository
interface CurriculumRepository : BaseRepository<Curriculum, Long> {
    fun findByAccount_Id(account_id: Long, pageable: Pageable) : Page<Curriculum>
}
