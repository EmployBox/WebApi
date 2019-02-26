package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.VersionedBaseEntity
import javax.persistence.*

@Entity
class Chat(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long,
        @ManyToOne
        @JoinColumn(name = "accountIdFirst")
        var accountFirst: Account,
        @ManyToOne
        @JoinColumn(name = "accountIdSecond")
        var accountSecond: Account
        ): VersionedBaseEntity<Long>() {
    @OneToMany(mappedBy = "chat")
    lateinit var messages: MutableList<Message>
}