package SpringJPA.Model;

public class User {

    private String username;
    private String password;
    private String email;

    public User() {
        this.username = "DEFAULT";
        this.password = "DEFAULT";
    }

    public User(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public User(String user, String pass, String mail) {
        this.username = user;
        this.password = pass;
        this.email = mail;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
