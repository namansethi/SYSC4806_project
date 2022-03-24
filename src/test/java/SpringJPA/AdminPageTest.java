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
public class AdminPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="admin")
    public void UserAPICallsAppears() throws Exception{
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("0/1000")));
    }

    @Test
    @WithMockUser(username="admin")
    public void UserIDAppears() throws Exception{
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("User1")));
    }


}
