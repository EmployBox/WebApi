package isel.ps.employbox.controllers.account

import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.binders.CommentBinder
import isel.ps.employbox.model.entities.Comment
import isel.ps.employbox.model.input.InComment
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutComment
import isel.ps.employbox.services.CommentService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH

@RestController
@RequestMapping("/accounts/{accountId}/comments")
class CommentController(private val commentService: CommentService, private val commentBinder: CommentBinder) {

    @GetMapping
    fun getAllComments(
            @PathVariable accountId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ): Mono<HalCollectionPage<Comment>> {
        val future = commentService.getComments(accountId, page, pageSize, orderColumn, orderClause)
                .thenCompose({ commentCollectionPage -> commentBinder.bindOutput(commentCollectionPage, this.javaClass, accountId) })
        return Mono.fromFuture<HalCollectionPage<Comment>>(future)
    }

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable accountId: Long, @PathVariable commentId: Long): Mono<OutComment> {
        val future = commentService.getComment(accountId, commentId)
                .thenCompose(???({ commentBinder.bindOutput() }))
        return Mono.fromFuture<OutComment>(future)
    }

    @GetMapping("/{commentId}/replies")
    fun getCommentReplies(@PathVariable accountId: Long, @PathVariable commentId: Long): Mono<HalCollectionPage<Comment>> {
        val future = commentService.getCommentReplies(accountId, commentId)
                .thenCompose({ commentCollectionPage -> commentBinder.bindOutput(commentCollectionPage, this.javaClass, accountId) })
        return Mono.fromFuture<HalCollectionPage<Comment>>(future)
    }

    @PutMapping("/{commentId}")
    fun updateComment(
            @PathVariable accountId: Long,
            @PathVariable commentId: Long,
            @RequestBody inComment: InComment,
            authentication: Authentication
    ): Mono<Void> {
        if (accountId != inComment.accountIdTo || commentId != inComment.commmentId) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        return commentService.updateComment(commentBinder.bindInput(inComment), authentication.name)
    }

    @PostMapping
    fun createComment(
            @PathVariable accountId: Long,
            @RequestBody inComment: InComment,
            authentication: Authentication
    ): Mono<OutComment> {
        if (accountId != inComment.accountIdTo) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        val comment = commentBinder.bindInput(inComment)
        val future = commentService.createComment(comment, authentication.name)
                .thenCompose(???({ commentBinder.bindOutput() }))
        return Mono.fromFuture<OutComment>(future)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long, authentication: Authentication): Mono<Void> {
        return commentService.deleteComment(commentId, authentication.name)
    }
}
