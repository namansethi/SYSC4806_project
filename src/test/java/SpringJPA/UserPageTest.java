package SpringJPA;

import SpringJPA.Model.User;
import SpringJPA.Model.UserRepository;
import SpringJPA.Model.UserType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest
public class UserPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Test
    @DirtiesContext
    @WithMockUser(username="User1", roles="TRIAL")
    public void UserAPICallsAppears() throws Exception{
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("998")));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username="User1", roles="TRIAL")
    public void UserIDAppears() throws Exception{
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User1")));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username="User1", roles="TRIAL")
    public void testAPICall() throws Exception{
        this.mockMvc.perform(post("/user/makeAPICall").with(csrf()));
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("999")));
    }

    @Test
    @DirtiesContext
    @WithMockUser(username="User1", roles ="TRIAL")
    public void testAPILimit() throws Exception{
        this.mockMvc.perform(post("/user/makeAPICall").with(csrf()));
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("999")));
        this.mockMvc.perform(post("/user/makeAPICall").with(csrf()));
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("1000")));
        this.mockMvc.perform(post("/user/makeAPICall").with(csrf()));
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("1000")));
    }

    @Test
    @WithMockUser(username="User1", roles ="TRIAL")
    @DirtiesContext
    public void testUsernameAppearsWhenLoggedIn() throws Exception{
        this.mockMvc.perform(get("/pricing")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User1")));
    }

    @Test
    @WithMockUser(username="User1", roles ="TRIAL")
    @DirtiesContext
    public void testSignOutAppearsWhenLoggedIn() throws Exception{
        this.mockMvc.perform(get("/pricing")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign Out")));
    }

    @Test
    @WithMockUser(username="admin", roles ="ADMIN")
    @DirtiesContext
    public void testAdminPageAppearsWhenLoggedInAsAdmin() throws Exception{
        this.mockMvc.perform(get("/pricing")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Admin Page")));
    }

    @Test
    @WithMockUser(username="User2", roles ="NON_TRIAL")
    public void testNonTrialUser() throws Exception{
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="User3", roles ="PREMIUM")
    public void testPremiumUser() throws Exception{
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin", roles ="ADMIN")
    public void testAdminUser() throws Exception{
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="User1")
    @DirtiesContext
    public void testAfterTrial() throws Exception{
        User loadedUser = userRepository.findByUsername("User1");
        loadedUser.setStartTime(System.currentTimeMillis() - 2592000001l);
        this.userRepository.save(loadedUser);
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @DirtiesContext
    @WithMockUser(username="User1", roles ="TRIAL")
    public void testTrialDisplay() throws Exception{
        User loadedUser = userRepository.findByUsername("User1");
        int daysFromTrialStart = 5;
        int trialDuration = 30;
        loadedUser.setStartTime(Instant.now().plus(daysFromTrialStart, ChronoUnit.DAYS).toEpochMilli());
        this.userRepository.save(loadedUser);
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(String.valueOf(trialDuration-daysFromTrialStart-1))));
    }


    @Test
    @WithMockUser(username="User3")
    @DirtiesContext
    public void testPageForbiddenAfterEndButton() throws Exception{
        this.mockMvc.perform(post("/user/endSub").with(csrf()));
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="User3")
    @DirtiesContext
    public void resetAPICallsAfterEndButton() throws Exception{
        User user = userRepository.findByUsername("User3");
        this.mockMvc.perform(post("/user/endSub").with(csrf()));
        userRepository.save(user);
        assertEquals(user.getApiCallCount(), 0);
    }


    @Test
    @WithMockUser(username="User3")
    @DirtiesContext
    public void nonTrialUserAfterEndButton() throws Exception{
        this.mockMvc.perform(post("/user/endSub").with(csrf()));
        User user = userRepository.findByUsername("User3");
        userRepository.save(user);
        assertEquals(UserType.ROLE_NONTRIAL, user.getRole());
    }
}
