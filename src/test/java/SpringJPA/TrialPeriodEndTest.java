package SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import SpringJPA.Model.User;
import SpringJPA.Model.UserType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class TrialPeriodEndTest {
    @Autowired
    UserController controller;

    User user, user2;


    @BeforeAll
    void initializeUser() {
        user = new User("Reed", "MyPass123567", "123Email.com", UserType.ROLE_TRIAL);
        user2 = new User("Carl", "Password343", "343Email.com", UserType.ROLE_TRIAL);
    }

    @Test
    public void shouldBeNonTrialUserAfterEnd() {
        controller.create(user);
        User fetchUser = controller.getByUser("Reed");
        fetchUser.setStartTime(System.currentTimeMillis() - 2592000001l); // 30 days have passed since start
        fetchUser.checkTrialEnd();
        assertEquals(UserType.ROLE_NONTRIAL, fetchUser.getRole());
    }

    @Test
    public void shouldBeTrialUserBeforeEnd() {
        controller.create(user2);
        User fetchUser2 = controller.getByUser("Carl");
        fetchUser2.startTrial();
        fetchUser2.checkTrialEnd();
        assertEquals(UserType.ROLE_TRIAL, fetchUser2.getRole());
    }
}
