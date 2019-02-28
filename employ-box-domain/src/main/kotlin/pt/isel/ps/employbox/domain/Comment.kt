package pt.isel.ps.employbox.domain

import pt.isel.ps.base.entity.IdBaseEntity
import javax.persistence.*

@Entity
class Comment(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        override var id: Long?,
        var text: String?,
        var status: Boolean?,
        @ManyToOne
        @JoinColumn(name = "accountIdFrom")
        var accountFrom: Account,
        @ManyToOne
        @JoinColumn(name = "accountIdDest")
        var accountDest: Account
) : IdBaseEntity<Long>() {
    @OneToMany(mappedBy = "mainComment")
    lateinit var replies: MutableList<Comment>
    @ManyToOne
    @JoinColumn(name = "mainCommentId")
    lateinit var mainComment: Comment
}