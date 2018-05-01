package isel.ps.employbox.model.entities;

import com.github.jayield.rapper.ColumnName;
import com.github.jayield.rapper.DomainObject;
import com.github.jayield.rapper.Id;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Chat implements DomainObject<Long> {
    @Id(isIdentity = true)
    private long chatId;
    private final long accountIdFirst;
    private final long accountIdSecond;
    private long version;

    @ColumnName(foreignName = "chatId")
    private CompletableFuture<List<Message>> messages;

    public Chat(){
        accountIdFirst = 0;
        accountIdSecond = 0;
        version = 0;
        messages = null;
    }

    public Chat(long chatId, long accountIdFirst, long accountIdSecond, long version, CompletableFuture<List<Message>> messages) {
        this.chatId = chatId;
        this.accountIdFirst = accountIdFirst;
        this.accountIdSecond = accountIdSecond;
        this.version = version;
        this.messages = messages;
    }

    public Chat(long accountIdFirst, long accountIdSecond){
        this.accountIdFirst = accountIdFirst;
        this.accountIdSecond = accountIdSecond;
    }

    @Override
    public Long getIdentityKey() {
        return chatId;
    }

    public long getVersion() {
        return version;
    }

    public long getAccountIdFirst() {
        return accountIdFirst;
    }

    public long getAccountIdSecond() {
        return accountIdSecond;
    }

    public CompletableFuture<List<Message>> getMessages() {
        return messages;
    }

}
