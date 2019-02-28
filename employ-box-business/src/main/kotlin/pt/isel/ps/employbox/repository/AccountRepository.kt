package pt.isel.ps.employbox.repository

import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : BaseRepository<Account, Long> {
    fun findByEmail(email: String) : Optional<Account>

    @Query("select f from Account a inner join a.followers f where a.id = :id")
    fun findFollowers(@Param("id") id: Long, pageable: Pageable) : Page<Account>

    @Query("select f from Account a inner join a.following f where a.id = :id")
    fun findFolloweds(@Param("id") id: Long, pageable: Pageable) : Page<Account>

    @Query("select f from Account a inner join a.followers f where a.id = :id and f.id = :accountToCheck")
    fun checkIfFollowing(@Param("id")accountId: Long,@Param("accountToCheck") accountToCheck: Long, pageable: Pageable) : Page<Account>
}