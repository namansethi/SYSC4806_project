package SpringJPA.Model;
import javax.persistence.*;

enum UserType {
    TRIAL,
    PREMIUM
}

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
    private int apiCallsAlloted;
    private UserType userType;

    public User() {
        this("DEFAULT", "DEFAULT", "USER", "DEFAULT", false);
    }

    public User(String user, String pass, String role) {
        this(user, pass, role, "DEFAULT", false);
    }

    public User(String user, String pass, String role, String mail) {
        this(user, pass, role, mail, false);

    }

    public User(String user, String pass, String role, String mail, boolean paid) {
        this.apiCallsAlloted = 1000;
        this.username = user;
        this.password = pass;
        this.role = role;
        this.email = mail;
        this.userType = paid ? UserType.PREMIUM : UserType.TRIAL;
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

    public void setRole(String role) {
        this.role = role;
    }

    public int getApiCallsAlloted() {
        return apiCallsAlloted;
    }

    public void setApiCallsAlloted(int apiCallsAlloted) {
        this.apiCallsAlloted = apiCallsAlloted;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getRole() {
        return role;
    }
}
