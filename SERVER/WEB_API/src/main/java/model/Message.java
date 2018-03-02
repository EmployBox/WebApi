package model;

import java.sql.Date;

public class Message extends DomainObject<Long> {
    private final long chadId;
    private final String text;
    private final Date date;

    private Message(long chatId, String text, Date date, long version) {
        super(chatId, (long) -1, version);
        this.chadId = chatId;
        this.date = date;
        this.text = text;
    }

    public static Message create(long chadId, String text, Date date){
        Message message = new Message( chadId , text, date, 0);
        message.markNew();
        return message;
    }

    public static Message load( long chatId, String text, Date date, long version){
        Message message = new Message( chatId, text, date, version);
        message.markClean();
        return message;
    }

    public long getChadId() {
        return chadId;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
