package groonvail.example.urlshorteningserver.controller;

import groonvail.example.urlshorteningserver.ShortenedSubdirectoryCallCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuditController {
    private final ShortenedSubdirectoryCallCounter counter;

    @GetMapping("/audit/{shortenedSubdirectory}")
    public long audit(String shortenedSubdirectory) {
        return counter.getCount(shortenedSubdirectory);
    }
}
