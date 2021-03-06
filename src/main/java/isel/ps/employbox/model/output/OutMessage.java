package isel.ps.employbox.model.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import isel.ps.employbox.controllers.account.ChatController;

import java.time.Instant;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class OutMessage implements OutputDto {

    @JsonProperty
    private final long accountId;

    @JsonProperty
    private final long messageId;

    @JsonProperty
    private final long chatId;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final Instant date;

    @JsonProperty
    private final String text;

    @JsonProperty
    private final _Links _links;

    public OutMessage(long accountId, long messageId, long chadId, Instant date, String text) {
        this.messageId = messageId;
        this.chatId = chadId;
        this.date = date;
        this.text = text;
        this.accountId = accountId;
        this._links = new _Links();
    }

    @JsonIgnore
    @Override
    public Object getCollectionItemOutput() {
        return new MessageItemOutput(messageId);
    }

    class MessageItemOutput {
        @JsonProperty
        private final long messageId;

        MessageItemOutput(long messageId) {
            this.messageId = messageId;
        }

        private class _Links {
            @JsonProperty
            private _Links.Self self = new _Links.Self();

            private class Self {
                @JsonProperty
                final String href = HOSTNAME + linkTo( methodOn(ChatController.class, accountId).getChatMessage(chatId, messageId, null)).withSelfRel().getHref();
            }
        }
    }

    private class _Links {
        @JsonProperty
        private _Links.Self self = new _Links.Self();

        private class Self {
            @JsonProperty
            final String href = HOSTNAME + linkTo( methodOn(ChatController.class, accountId).getChatMessage(chatId, messageId, null)).withSelfRel().getHref();
        }
    }
}
