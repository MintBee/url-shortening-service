package groonvail.example.urlshorteningserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class ShortenedPathGenerator {
    private static final int MIN_SHORTENING_LENGTH = 8;
    private static final int MAX_SHORTENING_LENGTH = 10;

    private final ShortenedPathRepository repository;
    private final Random random = ThreadLocalRandom.current();

    /**
     * @param url original url for shortening
     * @return shortened subdirectory. e.g. "abc" for "http://example.com/abc"
     */
    public String generate(URI url) {
        String shortenedPath;
        do {
            shortenedPath = generateRandomBase64(MIN_SHORTENING_LENGTH, MAX_SHORTENING_LENGTH+1);
        } while (repository.exists(shortenedPath));
        return "/" + shortenedPath;
    }

    /**
     *
     * @param origin inclusive minimum length of base64 string
     * @param round exclusive maximum length of base64 string
     * @return random base64 string
     */
    private String generateRandomBase64(int origin, int round) {
        int length = random.nextInt(origin, round);
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return Base64.getUrlEncoder().encodeToString(randomBytes).substring(0, length);
    }
}
