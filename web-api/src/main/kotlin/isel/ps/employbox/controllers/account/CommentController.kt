package isel.ps.employbox.controllers.account

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.CommentModel
import pt.isel.ps.employbox.service.CommentService

@RestController
@RequestMapping("/accounts/{accountId}/comments")
class CommentController(
        private val service: CommentService
) {

    @GetMapping
    fun getAllComments(
            @PathVariable accountId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) = service.findByAccountId(accountId, page, pageSize, orderColumn, orderClause)

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable accountId: Long, @PathVariable commentId: Long) =
            service.retrieve(commentId)

    @GetMapping("/{commentId}/replies")
    fun getCommentReplies(
            @PathVariable accountId: Long,
            @PathVariable commentId: Long,
            @RequestParam(required = false, defaultValue = "0") page: Int,
            @RequestParam(required = false, defaultValue = "10") pageSize: Int
    ) = service.getReplies(commentId, page, pageSize)

    @PutMapping("/{commentId}")
    fun updateComment(
            @PathVariable accountId: Long,
            @PathVariable commentId: Long,
            @RequestBody inComment: CommentModel,
            authentication: Authentication
    ) =
            if(commentId != inComment.id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inComment)

    @PostMapping
    fun createComment(
            @PathVariable accountId: Long,
            @RequestBody inComment: CommentModel,
            authentication: Authentication
    ) = service.save(inComment)


    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long, authentication: Authentication) = service.remove(commentId)
}
