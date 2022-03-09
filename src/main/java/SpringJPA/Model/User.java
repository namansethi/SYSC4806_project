package SpringJPA.Model;
import javax.persistence.*;

@Entity
@Table(name = "user_login")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;
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
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String toString(){
        return "Id: " + getUserId() + "User: " + getUsername() + "Password: " + getPassword();
    }
}
