package SpringJPA;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@AutoConfigureMockMvc
@SpringBootTest
public class AdminPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void UserAPICallsAppears() throws Exception{
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("0/1000")));
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void UserIDAppears() throws Exception{
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User1")));
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testUsernameAppearsWhenLoggedIn() throws Exception{
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("admin")));
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testSignOutAppearsWhenLoggedIn() throws Exception{
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign Out")));
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testChangeUser() throws Exception{
        this.mockMvc.perform(post("/user/admin/changeStatus").param("id", "1").with(csrf()));
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("PREMIUM")));
    }
    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testEditApi() throws Exception{
        this.mockMvc.perform(post("/user/admin/editRequests").param("id", "1").param("apiCallLimit", "9175").with(csrf()));
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("9175")));
    }
    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testEditEmail() throws Exception{
        this.mockMvc.perform(post("/user/admin/editEmail").param("id", "1").param("email", "testcase@gmail.com").with(csrf()));
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testcase@gmail.com")));
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    public void testResetCalls() throws Exception{
        this.mockMvc.perform(post("/user/admin/resetCalls").param("id", "1").with(csrf()));
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("0/1000")));
    }

    @Test
    @WithMockUser(username="User1", roles ="TRIAL")
    public void testNonAdminUser() throws Exception{
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isForbidden());
    }
}
