package groonvail.example.urlshorteningserver;

import org.junit.jupiter.api.RepeatedTest;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

class ShortenedPathGeneratorTest {
    ShortenedPathRepository repository = new ShortenedPathRepository();
    ShortenedPathGenerator generator = new ShortenedPathGenerator(repository);

    @RepeatedTest(10)
    void generate() throws Exception {
        String[] exampleUrls = {"http://example.com",
                "http://example1.com",
                "http://example2.com"};
        String[] results = new String[exampleUrls.length];
        for (int i = 0; i < exampleUrls.length; i++) {
            results[i] = generator.generate(new URI(exampleUrls[i]));
        }
        assertThat(results).allSatisfy(URI::new);
    }
}