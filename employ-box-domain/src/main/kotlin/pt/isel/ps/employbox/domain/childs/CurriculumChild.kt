package pt.isel.ps.employbox.domain.childs

import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.domain.Curriculum
import org.springframework.stereotype.Component

@Component
interface CurriculumChild {
    var curriculum: Curriculum
    var account: Account
}
