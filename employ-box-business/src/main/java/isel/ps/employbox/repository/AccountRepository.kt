package isel.ps.employbox.repository

import isel.ps.base.repository.BaseRepository
import isel.ps.employbox.domain.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : BaseRepository<Account, Long> {
    fun findByEmail(email: String) : Optional<Account>

    @Query("select f from Account a inner join a.followers where a.id = :id")
    fun findFollowers(@Param("id") id: Long, pageable: Pageable) : Page<Account>

    @Query("select f from Account a inner join a.following where a.id = :id")
    fun findFolloweds(@Param("id") id: Long, pageable: Pageable) : Page<Account>
}