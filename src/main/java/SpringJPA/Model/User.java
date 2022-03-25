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
    private UserType role;

    private long apiCallCount;
    private long apiCallLimit;


    public User() {
        this.username = "";
        this.password = "";
        this.role = "USER";
        this.apiCallCount = 0;
        this.apiCallLimit = 1000;
    }

    public User(String user, String pass, UserType role) {
        this(user, pass, "DEFAULT", role, 0, 1000);
    }

    public User(String user, String pass, String mail){
        this(user, pass, mail, UserType.TRIAL, 0, 1000);
    }
    public User(String user, String pass,String mail, UserType role) {
        this(user, pass, mail, role, 0, 1000);
    }

    public User(String user, String pass, long apiCallCount, long apiCallLimit){
        this(user, pass, "DEFAULT", UserType.TRIAL, apiCallCount, apiCallLimit);
    }

    public User(String user, String pass, UserType role, long apiCallCount, long apiCallLimit){
        this(user, pass, "DEFAULT", role, apiCallCount, apiCallLimit);
    }

    public User(String user, String pass, String mail, UserType role, long apiCallCount, long apiCallLimit) {
        this.username = user;
        this.password = pass;
        this.email = mail;
        this.role = role;
        this.apiCallCount = apiCallCount;
        this.apiCallLimit = apiCallLimit;
    }

    public boolean incrementApICallCount(){
        if(this.apiCallCount < this.apiCallLimit){
            this.apiCallCount++;
            return true;
        }
        else {
            return false;
        }
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

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
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


}
