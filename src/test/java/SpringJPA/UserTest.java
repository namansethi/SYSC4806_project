package SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import SpringJPA.Model.User;
import SpringJPA.Model.UserType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class UserTest {
    @Autowired
    UserController controller;
    PageController controller2;

    User user, user1, user2, user3, user4, user5;

    @BeforeAll
    void initializeUser(){
        user = new User("Bob", "MyPass123", "123 Road");
        user1 = new User("Joe", "MyPass1234", "1234 Road");
        user2 = new User("Jill", "MyPass1235", "1235 Road");
        user3 = new User("Joy", "MyPass12355", "12354 Road");
        user4 = new User("Reed", "MyPass123567", "123Email.com", UserType.ROLE_TRIAL);
        user5 = new User("Lather", "MyPass1235224", "124Email.com", UserType.ROLE_PREMIUM);
    }

    @Test
    void addUser(){
        int initial = controller.getAll().size();
        controller.create(user);
        assertEquals(initial+1, controller.getAll().size());
    }
    @Test
    void DeleteUser(){
        System.out.println(controller.getAll());
        int initial = controller.getAll().size();
        controller.create(user1);
        System.out.println(controller.getAll());

        assertEquals(initial+1, controller.getAll().size());
        controller.delete(5L);
        assertNotEquals(initial+1, controller.getAll().size());
    }
//User user1 = new User("User1", passwordEncoder.encode("Password123"), "USER");
    @Test
    void getUserByID(){
        User idUser = controller.get(1L).get(0);
        assertEquals("User1", idUser.getUsername());

        User idUser2 = controller.get(2L).get(0);
        assertEquals("User2", idUser2.getUsername());
    }

    @Test
    void getUsers(){

        List<User> users = controller.getAll();
        assertEquals(users.size(), users.size());
        controller.create(user3);
        List<User> users2 = controller.getAll();
        assertEquals(users.size()+1, users2.size());
        assertEquals(user3.toString(), users2.get(users2.size()-1).toString());
        assertNotEquals(user2.toString(), users2.get(users2.size()-1).toString());


    }

    @Test
    void getUserByName(){
        controller.create(user2);
        User fetched = controller.getByUser("Jill");
        assertEquals(user2.toString(), fetched.toString());
        assertNotEquals(user3.toString(), fetched.toString());
    }

   /* @Test
    void changeUserType(){
        controller.create(user4);
        User fetched = controller.getByUser("Reed");
        assertEquals(UserType.TRIAL, fetched.getRole());

        controller2.changeStatus(fetched.getUserId());
        User updated = controller.getByUser("Reed");
        assertEquals(UserType.PREMIUM, updated.getRole());
    }

    @Test
    void editAPICalls(){
        controller.create(user5);
        User fetched = controller.getByUser("Lather");
        assertEquals(1000, fetched.getApiCallLimit());
        controller2.editRequests(fetched.getUserId(), 1500);
        User updated = controller.getByUser("Lather");
        assertEquals(1500, updated.getApiCallLimit());
    }*/

}