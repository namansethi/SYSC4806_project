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
    private String role;

    public User() {
        this.username = "DEFAULT";
        this.password = "DEFAULT";
    }

    public User(String user, String pass, String role) {
        this.username = user;
        this.password = pass;
        this.role = role;
    }

    public User(String user, String pass, String role, String mail) {
        this.username = user;
        this.password = pass;
        this.role = role;
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
        return "Id: " + getUserId() + " User: " + getUsername() + " Password: " + getPassword();
    }

    public String getRole() {
        return role;
    }
}
