package groonvail.example.urlshorteningserver;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest()
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IntegrationTest {
    static final String URL = "http://example.com";

    @Autowired
    MockMvc mockMvc;

    static String shortenedUrl;

    @Test
    @Order(1)
    void createShortenedUrl() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(URL)
                        .header("Host", "localhost:8080"))
                .andDo(result -> shortenedUrl = result.getResponse().getContentAsString());
    }

    @Test
    @Order(2)
    void redirectToOriginalUrl() throws Exception {
        Assertions.assertThat(shortenedUrl)
                .isNotNull();
        mockMvc.perform(get(shortenedUrl))
                .andExpect(redirectedUrl(URL));
    }
}
