package pt.isel.ps.employbox.repository

import pt.isel.ps.base.repository.BaseRepository
import pt.isel.ps.employbox.domain.Account
import pt.isel.ps.employbox.domain.Chat
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * @author tiago.ribeiro
 */
@Repository
interface ChatRepository : BaseRepository<Chat, Long> {
    fun findByAccountFirst(account: Account, pageable: Pageable) : Page<Chat>
}