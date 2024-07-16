package groonvail.example.urlshorteningserver.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(TestController.class)
class TestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void getInt() throws Exception {
        mockMvc.perform(get("/1342"))
                .andDo(print());
    }

    @Test
    void getString() throws Exception {
        mockMvc.perform(get("/omgomgomg"))
                .andDo(print());
    }

    @Test
    void getHello() throws Exception {
        mockMvc.perform(get("/hello/omgomgomg"))
                .andDo(print());
    }

}
