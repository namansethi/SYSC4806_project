package SpringJPA;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class UserPageTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    @WithMockUser(username="User1")
    public void testUsernameAppearsWhenLoggedIn() throws Exception{
        this.mockMvc.perform(get("/pricing")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User1")));
    }

    @Test
    @WithMockUser(username="User1")
    public void testSignOutAppearsWhenLoggedIn() throws Exception{
        this.mockMvc.perform(get("/pricing")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Sign Out")));
    }

}
