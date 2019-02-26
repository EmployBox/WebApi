package isel.ps.employbox.controllers.account

import isel.ps.employbox.ErrorMessages
import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.binders.ChatBinder
import isel.ps.employbox.model.binders.MessageBinder
import isel.ps.employbox.model.entities.Chat
import isel.ps.employbox.model.entities.Message
import isel.ps.employbox.model.input.InChat
import isel.ps.employbox.model.input.InMessage
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutMessage
import isel.ps.employbox.services.ChatService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH

@RestController
@RequestMapping("/accounts/{id}/chats")
class ChatController(private val chatBinder: ChatBinder, private val chatService: ChatService, private val messageBinder: MessageBinder) {


    @GetMapping("/{cid}/messages")
    fun getChatsMessages(
            @PathVariable id: Long,
            @PathVariable cid: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            authentication: Authentication
    ): Mono<HalCollectionPage<Message>> {
        val future = chatService.getAccountChatsMessages(id, authentication.name, page, pageSize)
                .thenCompose({ messageCollectionPage -> messageBinder.bindOutput(messageCollectionPage, this.javaClass, id, cid) })
        return Mono.fromFuture<HalCollectionPage<Message>>(future)
    }

    @PostMapping
    fun createChat(@PathVariable id: Long, @RequestBody inChat: InChat, authentication: Authentication): Mono<Chat> {
        if (id != inChat.accountIdFirst) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        return chatService.createNewChat(
                id,
                chatBinder.bindInput(inChat),
                authentication.name
        )
    }

    @PostMapping("/{cid}/messages")
    fun createMessage(@PathVariable id: Long, @PathVariable cid: Long, @RequestBody msg: InMessage, authentication: Authentication): Mono<OutMessage> {
        if (cid != msg.chatId)
            throw BadRequestException(ErrorMessages.BAD_REQUEST_IDS_MISMATCH)
        val future = chatService.createNewChatMessage(id, cid, messageBinder.bindInput(msg), authentication.name)
                .thenCompose(???({ messageBinder.bindOutput() }))
        return Mono.fromFuture<OutMessage>(future)
    }

    @GetMapping("/{cid}/messages/{mid}")
    fun getChatMessage(@PathVariable cid: Long, @PathVariable mid: Long, authentication: Authentication): Mono<OutMessage> {
        val future = chatService.getAccountChatsMessage(cid, mid, authentication.name)
                .thenCompose(???({ messageBinder.bindOutput() }))
        return Mono.fromFuture<OutMessage>(future)
    }
}
