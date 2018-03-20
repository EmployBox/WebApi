package isel.ps.employbox.api.model.input;

public class User {
    private String email;
    private String password;
    private String name;
    private String photo_url;
    private String summary;

    /*public User(String email, String password, String name, String photo_url, String summary) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.photo_url = photo_url;
        this.summary = summary;
    }*/

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
}