package groonvail.example.urlshorteningserver.controller;

import groonvail.example.urlshorteningserver.ShortenedSubdirectoryCallCounter;
import groonvail.example.urlshorteningserver.ShortenedPathRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class RedirectionController {
    private final ShortenedPathRepository shortenedPathRepository;
    private final ShortenedSubdirectoryCallCounter callCounter;

     @GetMapping("/{shortenedPath}")
     public void redirect(@PathVariable String shortenedPath,
                          HttpServletResponse response) throws IOException {
         String originalUrl = shortenedPathRepository.find(shortenedPath);
         callCounter.increment(shortenedPath);
         response.sendRedirect(originalUrl);
     }
}
