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

    private long apiCallCount;
    private long apiCallLimit;


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

    public User(String user, String pass, String role, long apiCallCount, long apiCallLimit) {
        this.username = user;
        this.password = pass;
        this.role = role;
        this.apiCallCount = apiCallCount;
        this.apiCallLimit = apiCallLimit;
    }

    public void incrementApICallCount(){
        this.apiCallCount++;
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


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getApiCallCount() {
        return apiCallCount;
    }

    public void setApiCallCount(long apiCallCount) {
        this.apiCallCount = apiCallCount;
    }

    public long getApiCallLimit() {
        return apiCallLimit;
    }

    public void setApiCallLimit(long apiCallLimit) {
        this.apiCallLimit = apiCallLimit;
    }

    public String toString(){
        return "Id: " + getUserId() + " User: " + getUsername() + " Password: " + getPassword() + " ApiCallCount: " + getApiCallCount() + " ApiCallLimit: " + getApiCallLimit();
    }


}
