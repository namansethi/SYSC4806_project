package main.models;

import java.util.ArrayList;
import java.util.List;

public class Constraint {
    private List<Boolean> apiAccessList;
    //Needs to be set to the number of APIs offered.
    private final int DEFAULT_SIZE = 3;

    public Constraint() {
        apiAccessList = new ArrayList<>(DEFAULT_SIZE);
    }

    public Constraint(List<Boolean> list) {
        apiAccessList = list;
    }


    public List<Boolean> getApiAccessList() {
        return apiAccessList;
    }

    public void setApiAccessList(List<Boolean> apiAccessList) {
        this.apiAccessList = apiAccessList;
    }
}
