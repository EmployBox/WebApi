package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.VersionedBaseEntity
import javax.persistence.*

@Entity
class Message(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long,
        var text: String?,
        @ManyToOne
        @JoinColumn(name = "accountId")
        var account: Account,
        @ManyToOne
        @JoinColumn(name = "chatId")
        var chat: Chat
) : VersionedBaseEntity<Long>()