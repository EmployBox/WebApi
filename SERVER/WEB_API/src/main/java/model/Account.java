package model;

import util.Streamable;

import java.util.stream.Stream;

public abstract class Account extends AutoGeneratedIdentity<Long> {
    @ID(isIdentity = true)
    protected long accountID;
    protected final String email;
    protected final String password;
    protected final double rating;
       
    protected final Streamable<Job> offeredJobs;
    protected final Streamable<Comment> comments;
    protected final Streamable<User> following;
    protected final Streamable<Chat> chats;
    protected final Streamable<Rating> ratings;

    protected Account(
            long accountID,
            String email,
            String password,
            double rating,
            long version,
            Streamable<Job> offeredJobs,
            Streamable<Comment> comments,
            Streamable<Chat> chats,
            Streamable<Rating> ratings,
            Streamable<User> following
    ){
        super(accountID, version);
        this.accountID = accountID;
        this.email = email;
        this.password = password;
        this.rating = rating;
        this.offeredJobs = offeredJobs;
        this.chats = chats;
        this.ratings = ratings;
        this.comments = comments;
        this.following = following;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getRating() {
        return rating;
    }

    public Stream<Job> getOfferedJobs() {
        return offeredJobs.get();
    }

    public Stream<User> getFollowing() {
        return following.get();
    }

    public Stream<Comment> getComments() {
        return comments.get();
    }

    public Stream<Chat> getChats() {
        return chats.get();
    }

    public Stream<Rating> getRatings() {
        return ratings.get();
    }

    @Override
    protected void newdentityKey(Long key) {
        identityKey = key;
        accountID = key;
    }
}
