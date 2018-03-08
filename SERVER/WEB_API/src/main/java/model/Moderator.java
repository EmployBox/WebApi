package model;

import util.Streamable;

import java.util.stream.Stream;

public class Moderator extends Account {
    private final Streamable<Rating> ratingsModerated;

    protected Moderator(long accountID,
                        String email,
                        String password,
                        double rating,
                        long version,
                        Streamable<Comment> comments,
                        Streamable<Chat> chats,
                        Streamable<Rating> ratings,
                        Streamable<Rating> ratingsModerated
    ) {
        super(accountID, email, password, rating, version, null, comments, chats, ratings, null);
        this.ratingsModerated = ratingsModerated;
    }

    public static Moderator create(
            String email,
            String password,
            double rating,
            long version,
            Streamable<Comment> comments,
            Streamable<Chat>chats,
            Streamable<Rating> ratings,
            Streamable<Rating> ratingsModerated
    ){
        Moderator moderator = new Moderator(
                defaultKey,
                email,
                password,
                rating,
                version,
                comments,
                chats,
                ratings,
                ratingsModerated
        );
        moderator.markNew();
        return moderator;
    }

    public static Moderator load(
            String email,
            String password,
            double rating,
            long version,
            Streamable<Comment> comments,
            Streamable<Chat>chats,
            Streamable<Rating> ratings,
            Streamable<Rating> ratingsModerated
    ){
        Moderator moderator = new Moderator(
                defaultKey,
                email,
                password,
                rating,
                version,
                comments,
                chats,
                ratings,
                ratingsModerated
        );
        moderator.markClean();
        return moderator;
    }

    public Stream<Rating> getRatingsModerated() {
        return ratingsModerated.get();
    }
}