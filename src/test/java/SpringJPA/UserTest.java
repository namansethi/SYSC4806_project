package SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import SpringJPA.Model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class UserTest {
    @Autowired
    UserController controller;

    User user;

    @BeforeAll
    void initializeUser(){
        user = new User("Bob", "MyPass123", "123 Road");
    }

    @Test
    void addAndDeleteUser(){
        int initial = controller.getAll().size();
        controller.create(user);


        assertEquals(initial+1, controller.getAll().size());
        controller.delete((long) initial);
    }

    @Test
    void userDetailsTest() {

        controller.create(user);
        assertEquals(user.getUsername(), "Bob");

        assertEquals(controller.getByUser("Bob").toString(), user.toString());
        assertNotEquals(controller.get(0L).toString(), user.toString());

        controller.delete((long) controller.getAll().size()-1);

    }

}