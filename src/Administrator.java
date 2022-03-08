public class Administrator extends User{

    public Administrator() {
        super();
    }

    public Administrator(String username, String password) {
        super(username, password);
    }

    public void setConstraints(Customer customer, Constraint constraint) {
        customer.setConstraint(constraint);
    }

    public void flipUserToPaid(Customer customer) {
        customer.setPaidUser(true);
    }
    public void flipUserToFreeTrial(Customer customer) {
        customer.setOnFreeTrial(true);
    }

}
