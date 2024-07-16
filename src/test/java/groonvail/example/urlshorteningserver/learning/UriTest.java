package groonvail.example.urlshorteningserver.learning;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UriTest {
    @Test
    void uriTest() throws Exception {
        URI.create("https://localhost:8080/abc");
        new URI("https://localhost:8080/abc");

        new URI("https", "www.youtube.com", "/watch?v=TjpP1udN58k", null);

        new URI("https", "::1", "/abc", null);
        new URI("https", "[::1]", "/abc", null);
        assertThrows(URISyntaxException.class, () ->
                new URI("https", "localhost:8080", "/abc", null));
        new URI("https", null, "localhost", 8080, "/abc", null, null);
    }
}
