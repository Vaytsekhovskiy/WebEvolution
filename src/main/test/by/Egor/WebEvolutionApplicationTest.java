package by.Egor;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.egor.inmemorybroker.Phrase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebEvolutionApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DummyService dummyService;

    @Test
    void should_return_hello_message() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("hello!")));
    }
    @Test
    void should_return_cheering_message() throws Exception {
        this.mockMvc.perform(get("/support")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("You are nice!")));
    }
    @Test
    void should_add_support_message() throws Exception {
        String json = new ObjectMapper().writeValueAsString(new Phrase("Dream big. Pray bigger!"));
        this.mockMvc.perform(post("/support")
                        .contentType("application/json")
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Dream big. Pray bigger!")));
        Thread.sleep(2000);
        boolean messageAdded = dummyService.getPhrases().contains(new Phrase("Dream big. Pray bigger!"));
        assertTrue(messageAdded, "Support message should be added to the system");
    }
}
