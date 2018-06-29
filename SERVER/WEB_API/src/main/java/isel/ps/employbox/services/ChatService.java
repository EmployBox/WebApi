package isel.ps.employbox.services;

import com.github.jayield.rapper.DataRepository;
import com.github.jayield.rapper.utils.Pair;
import com.github.jayield.rapper.utils.UnitOfWork;
import isel.ps.employbox.ErrorMessages;
import isel.ps.employbox.exceptions.BadRequestException;
import isel.ps.employbox.exceptions.ResourceNotFoundException;
import isel.ps.employbox.exceptions.UnauthorizedException;
import isel.ps.employbox.model.binders.CollectionPage;
import isel.ps.employbox.model.entities.Chat;
import isel.ps.employbox.model.entities.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import static isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH;
import static isel.ps.employbox.ErrorMessages.RESOURCE_NOT_FOUND_MESSAGE;

@Service
public class ChatService {
    private final DataRepository<Chat, Long> chatRepo;
    private final DataRepository<Message, Long> msgRepo;
    private final AccountService accountService;

    public ChatService(DataRepository<Chat, Long> chatRepo, DataRepository<Message, Long> msgRepo, AccountService accountService) {
        this.chatRepo = chatRepo;
        this.msgRepo = msgRepo;
        this.accountService = accountService;
    }

    //todo endpoint
    public CompletableFuture<CollectionPage<Chat>> getAccountChats(long accountId, int page, int pageSize) {
        return accountService.getAccount(accountId)
                .thenCompose(__ -> ServiceUtils.getCollectionPageFuture( chatRepo, page, pageSize, new Pair<>("accountId", accountId)));
    }

    public CompletableFuture<CollectionPage<Message>> getAccountChatsMessages(long accountId, String email, int page, int pageSize) {
        return accountService.getAccount(accountId, email)
                .thenCompose(__ -> ServiceUtils.getCollectionPageFuture( msgRepo, page, pageSize, new Pair<>("accountId", accountId)));
    }


    public CompletableFuture<Message> getAccountChatsMessage(long cid, long mid, String email) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return msgRepo.findById(unitOfWork, mid)
                .thenApply(omsg -> omsg.orElseThrow(() -> new ResourceNotFoundException(RESOURCE_NOT_FOUND_MESSAGE)))
                .thenCompose(msg -> {
                    if (msg.getChatId() != cid)
                        throw new BadRequestException(BAD_REQUEST_IDS_MISMATCH);
                    return accountService.getAccount(msg.getAccountId(), email)//throws exceptions
                            .thenApply(account -> msg);
                }).thenCompose( res -> unitOfWork.commit().thenApply( aVoid -> res));
    }

    public CompletableFuture<Message> createNewChatMessage(long accountId, long chatId, Message msg, String email) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return accountService.getAccount(accountId, email)
                .thenCompose(account -> getChat(chatId))
                .thenCompose(chat -> {
                    if (chat.getAccountIdFirst() != accountId)
                        throw new UnauthorizedException(ErrorMessages.UN_AUTHORIZED_MESSAGE);
                    return msgRepo.create(unitOfWork, msg);
                }).thenCompose( res -> unitOfWork.commit().thenApply( aVoid -> msg));
    }

    private CompletableFuture<Chat> getChat(long chatId){
        UnitOfWork unitOfWork = new UnitOfWork();
        return chatRepo.findById(unitOfWork, chatId)
                .thenCompose(res -> unitOfWork.commit().thenApply( aVoid -> res))
                .thenApply(ochat -> ochat.orElseThrow( () -> new ResourceNotFoundException(ErrorMessages.RESOURCE_NOT_FOUND_CHAT)));
    }

    public Mono<Chat> createNewChat(long accountIdFrom, Chat inChat, String username) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return Mono.fromFuture(
                CompletableFuture.allOf(
                        accountService.getAccount(accountIdFrom, username),
                        accountService.getAccount(inChat.getAccountIdSecond())
                )
                        .thenCompose(aVoid -> chatRepo.create(unitOfWork, inChat))
                        .thenCompose( res -> unitOfWork.commit().thenApply( aVoid -> inChat))
        );
    }
}
