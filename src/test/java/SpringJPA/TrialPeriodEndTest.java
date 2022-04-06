package SpringJPA;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import SpringJPA.Model.User;
import SpringJPA.Model.UserType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


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
        fetchUser2.checkTrialEnd();
        assertEquals(UserType.ROLE_TRIAL, fetchUser2.getRole());
    }
}
