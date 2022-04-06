package SpringJPA;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ErrorPageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username="User1", roles ="TRIAL")
    public void testAccessForbidden() throws Exception{
        this.mockMvc.perform(get("/user/admin")).andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="User1", roles ="TRIAL")
    public void testInvalidUrl() throws Exception{
        this.mockMvc.perform(get("/invalidurl")).andDo(print()).andExpect(status().isNotFound());
    }
}
