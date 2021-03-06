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
    private long startTime;
    private long endTime;

    private final long MILLIS_IN_DAY = 86400000;
    private boolean hasUsedTrial;

    private long apiCallCount;
    private long apiCallLimit;


    public User() {
        this.username = "";
        this.password = "";
        this.role = UserType.ROLE_NONTRIAL;
        this.apiCallCount = 0;
        this.apiCallLimit = 1000;
        this.hasUsedTrial = false;
    }

    public User(String user, String pass, UserType role) {
        this(user, pass, "DEFAULT", role, 0, 1000);
    }

    public User(String user, String pass, String mail){
        this(user, pass, mail, UserType.ROLE_NONTRIAL, 0, 1000);
    }
    public User(String user, String pass,String mail, UserType role) {
        this(user, pass, mail, role, 0, 1000);
    }

    public User(String user, String pass, long apiCallCount, long apiCallLimit){
        this(user, pass, "DEFAULT", UserType.ROLE_NONTRIAL, apiCallCount, apiCallLimit);
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
        this.hasUsedTrial = false;
    }

    /**
     *
     * @return false if trial did not end, true if trial has ended
     */
    public boolean checkTrialEnd() {
        // check if trial user and if current day is 30 days ahead of startTime
        if(this.role == UserType.ROLE_TRIAL && this.endTime <= System.currentTimeMillis()) {
            this.setRole(UserType.ROLE_NONTRIAL);
            return true;
        }
        return false;
    }

    public void startTrial() {
        this.startTime = System.currentTimeMillis();
        this.endTime = this.startTime + 30 * MILLIS_IN_DAY;
        this.hasUsedTrial = true;
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

    public void changeTrialPeriod(int period){
        this.endTime = System.currentTimeMillis() + period * MILLIS_IN_DAY;

    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getEndTimeInDays(){
        if(this.endTime != 0L){
            return (int)((this.endTime - startTime)/MILLIS_IN_DAY);
        }
        return 999;//Number that will never happen in order to check for errors
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
        return "Id: " + getUserId() + " User: " + getUsername() + " Password: " + getPassword() + " Role: " + getRole();
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

    public boolean getHasUsedTrial() {
        return hasUsedTrial;
    }

    public void setHasUsedTrial(boolean hasUsedTrial) {
        this.hasUsedTrial = hasUsedTrial;
    }
}
