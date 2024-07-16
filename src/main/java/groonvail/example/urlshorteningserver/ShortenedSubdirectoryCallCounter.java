package groonvail.example.urlshorteningserver;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class ShortenedSubdirectoryCallCounter {
    private final ConcurrentMap<String, Long> counter = new ConcurrentHashMap<>();

    public void increment(String shortenedSubdirectory) {
        counter.put(shortenedSubdirectory, counter.getOrDefault(shortenedSubdirectory, 0L) + 1);
    }

    public long getCount(String shortenedSubdirectory) {
        return counter.getOrDefault(shortenedSubdirectory, 0L);
    }
}
