package isel.ps.employbox.controllers.account

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.ChatModel
import pt.isel.ps.employbox.model.MessageModel
import pt.isel.ps.employbox.service.ChatService
import pt.isel.ps.employbox.service.MessageService

@RestController
@RequestMapping("/accounts/{id}/chats")
class ChatController(
        private val service: ChatService,
        private val messageService: MessageService
) {


    @GetMapping("/{cid}/messages")
    fun getChatsMessages(
            @PathVariable id: Long,
            @PathVariable cid: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            authentication: Authentication
    ) = service.getChatMessages(cid, page, pageSize)

    @PostMapping
    fun createChat(@PathVariable id: Long, @RequestBody inChat: ChatModel, authentication: Authentication) =
        if(id != inChat.accountFirst!!.id)
            throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        else service.save(inChat)

    @PostMapping("/{cid}/messages")
    fun createMessage(@PathVariable id: Long, @PathVariable cid: Long, @RequestBody msg: MessageModel, authentication: Authentication)=
        if(cid != msg.chat!!.id)
            throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        else messageService.save(msg)

    @GetMapping("/{cid}/messages/{mid}")
    fun getChatMessage(@PathVariable cid: Long, @PathVariable mid: Long, authentication: Authentication)=
            messageService.retrieve(mid)
}
