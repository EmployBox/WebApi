package isel.ps.employbox.model.input;

public class InUserAccount {
    private long id;
    private String name;
    private String email;
    private String password;
    private String photo_url;
    private long rating;
    private String summary;
    private long userVersion;
    private long accountVersion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoto_url() {
        return this.photo_url;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public long getUserVersion() { return userVersion; }

    public void setUserVersion(long userVersion) {
        this.userVersion = userVersion;
    }

    public long getAccountVersion() {
        return accountVersion;
    }

    public void setAccountVersion(long accountVersion) {
        this.accountVersion = accountVersion;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }
}