package groonvail.example.urlshorteningserver;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(code = NOT_FOUND, reason = "No such shortened URL")
public class NoSuchShortenedUrl extends RuntimeException {
    public NoSuchShortenedUrl(String message) {
        super(message);
    }
}
