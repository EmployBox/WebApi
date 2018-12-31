package isel.ps.employbox.domain

import isel.ps.base.entity.VersionedBaseEntity
import javax.persistence.*

@Entity
class Comment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long,
        var text: String?,
        var status: Boolean?,
        @ManyToOne
        @JoinColumn(name = "accountIdFrom")
        var accountFrom: Account,
        @ManyToOne
        @JoinColumn(name = "accountIdDest")
        var accountDest: Account
) : VersionedBaseEntity<Long>() {
    @OneToMany(mappedBy = "mainComment")
    lateinit var replies: List<Comment>
    @ManyToOne
    @JoinColumn(name = "mainCommentId")
    lateinit var mainComment: Comment
}