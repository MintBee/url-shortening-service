package groonvail.example.urlshorteningserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
@RequiredArgsConstructor
public class ShortenedPathRepository {
    private final ConcurrentMap<String, String> shortenedSubdirectoryToOriginalUrls = new ConcurrentHashMap<>();

    public void save(String url, String shortenedUrl) {
        if (url.isBlank() || shortenedUrl.isBlank()) {
            throw new IllegalArgumentException("url and shortenedUrl must not be blank");
        }
        if (!url.startsWith("http") || shortenedUrl.startsWith("http")) {
            throw new IllegalArgumentException("It doesn't look like url and shortenedUrl are valid input.\n"
                    + "url: " + url + "\n" + "shortenedUrl: " + shortenedUrl);
        }

        shortenedSubdirectoryToOriginalUrls.put(shortenedUrl, url);
    }


    public String find(String shortenedPath) {
        if (!shortenedPath.startsWith("/")) {
            shortenedPath = "/" + shortenedPath;
        }

        String originalUrl = shortenedSubdirectoryToOriginalUrls.get(shortenedPath);
        if (originalUrl == null) {
            throw new NoSuchShortenedUrl("No such shortened subdirectory: " + shortenedPath);
        }
        return originalUrl;
    }

    public boolean exists(String shortenedSubdirectory) {
        return shortenedSubdirectoryToOriginalUrls.containsKey(shortenedSubdirectory);
    }
}
