import java.time.LocalDateTime;

public class Customer extends User {

    private boolean isOnFreeTrial;
    private boolean isPaidUser;
    private boolean isBelowCallLimit;

    private LocalDateTime signUpDate;
    private LocalDateTime FreeTrialDeadline;

    private long FreeTrialDuration;
    private long apiCallCount;

    private long apiCallLimit;
    private LocalDateTime FreeTrialLimit;

    // Contains a list of booleans setting what APIs are accessible.
    private Constraint constraint;

    public Customer() {
        super();
        init();
    }

    public Customer(String username, String password) {
        super(username, password);
        init();
    }

    private void init(){
        FreeTrialDuration = 30;
        apiCallCount = 0;
        apiCallLimit = 1000;
        signUpDate = LocalDateTime.now();
        FreeTrialDeadline = LocalDateTime.now().plusDays(30);
        constraint = new Constraint();
        isPaidUser = false;
    }

    private void makeApiCall(int apiToCall){
        if(apiCallCount>apiCallLimit){
            System.out.println("EXCEEDED LIMIT.");
            return;
        }

        if(!constraint.getApiAccessList().get(apiToCall)){
            System.out.println("YOU DO NOT HAVE ACCESS");
            return;
        }

        call();

    }

    private void call(){
        apiCallCount++;
    }


    public boolean isOnFreeTrial() {
        if(isPaidUser){
            return false;
        }else if(LocalDateTime.now().isAfter(FreeTrialDeadline)){
            isOnFreeTrial = false;
            return false;
        }else{
            return isOnFreeTrial;
        }
    }

    public void setOnFreeTrial(boolean onFreeTrial) {
        if(onFreeTrial){
            isPaidUser = false;
            FreeTrialDeadline = LocalDateTime.now().plusDays(30);
        }
        isOnFreeTrial = onFreeTrial;
    }

    public boolean isPaidUser() {
        return isPaidUser;
    }

    public void setPaidUser(boolean paidUser) {
        if(paidUser){
            isOnFreeTrial = false;
        }
        isPaidUser = paidUser;
    }

    public LocalDateTime getSignUpDate() {
        return signUpDate;
    }



    public void setSignUpDate(LocalDateTime signUpDate) {
        this.signUpDate = signUpDate;
    }




    public boolean isBelowCallLimit() {
        return isBelowCallLimit;
    }

    public void setBelowCallLimit(boolean belowCallLimit) {
        isBelowCallLimit = belowCallLimit;
    }

    public long getFreeTrialDuration() {
        return FreeTrialDuration;
    }

    public void setFreeTrialDuration(long freeTrialDuration) {
        FreeTrialDuration = freeTrialDuration;
    }

    public long getApiCallCount() {
        return apiCallCount;
    }

    public void setApiCallCount(long apiCallCount) {
        this.apiCallCount = apiCallCount;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }
}
