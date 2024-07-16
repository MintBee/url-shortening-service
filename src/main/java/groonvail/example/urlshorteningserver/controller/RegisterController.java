package groonvail.example.urlshorteningserver.controller;

import groonvail.example.urlshorteningserver.ShortenedPathGenerator;
import groonvail.example.urlshorteningserver.ShortenedPathRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private final ShortenedPathGenerator shortenedPathGenerator;
    private final ShortenedPathRepository shortenedPathRepository;

    @Value("${protocol.http}")
    private String httpProtocol;

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody String urlString, @RequestHeader(HttpHeaders.HOST) String host) throws URISyntaxException {
        URI url = new URI(urlString);
        String shortenedPath = shortenedPathGenerator.generate(url);
        shortenedPathRepository.save(urlString, shortenedPath);
        String shortenedUrl = httpProtocol + "://" + host + shortenedPath;
        log.info("Registered: {} -> {}", urlString, shortenedUrl);
        return new URI(shortenedUrl).toString();
    }

    @ExceptionHandler(URISyntaxException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleURISyntaxException() {
        return "Invalid URL format";
    }
}
