package SpringJPA.Model;

public enum UserType {
    ROLE_TRIAL("Trial"),
    ROLE_NONTRIAL("Non-Trial"),
    ROLE_PREMIUM("Premium"),
    ROLE_ADMIN("Admin");
    private final String name;

    UserType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
