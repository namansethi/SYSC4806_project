package SpringJPA.Model;

import javax.persistence.*;
import java.time.LocalDate;

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
    private long startTime;

    private long apiCallCount;
    private long apiCallLimit;


    public User() {
        this.username = "";
        this.password = "";
        this.role = UserType.ROLE_TRIAL;
        this.apiCallCount = 0;
        this.apiCallLimit = 1000;

    }

    public User(String user, String pass, UserType role) {
        this(user, pass, "DEFAULT", role, 0, 1000);
    }

    public User(String user, String pass, String mail){
        this(user, pass, mail, UserType.ROLE_TRIAL, 0, 1000);
    }
    public User(String user, String pass,String mail, UserType role) {
        this(user, pass, mail, role, 0, 1000);
    }

    public User(String user, String pass, long apiCallCount, long apiCallLimit){
        this(user, pass, "DEFAULT", UserType.ROLE_TRIAL, apiCallCount, apiCallLimit);
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

    /**
     *
     * @return false if trial did not end, true if trial has ended
     */
    public boolean checkTrialEnd() {
        // check if trial user and if current day is 30 days ahead of startTime
        long length = System.currentTimeMillis() - this.startTime;
        if(this.role == UserType.ROLE_TRIAL && length >= 2592000000l) { // 30 days = 2592000000 milliseconds
            this.setRole(UserType.ROLE_NONTRIAL);
            return true;
        }
        return false;
    }

    public void startTrial() {
        this.startTime = System.currentTimeMillis();
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


    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStartTime() {
        return this.startTime;
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
