# URL shortening service

Url shortening service to shortening long url
into short url.

You can register the url and this service returns the shortened url.
Even if you register same url multiple times, prior ones won't get expired.

## Requirements

1. Input url of any website and this service will return shortened url that redirects to the original url.
2. Shortened url will have subdirectory that is 8-10 characters long.
3. As redirection from shortened url to original url happens, the counter to record its occurrences needs to be triggered, which able to check latter.
4. Test codes to test the functions needed.

## Design

1. When input url for shortening comes, generate shortened url with `ShortenedUrlGenerator`.
Store original url and shortened url into `ShortenedUrlRegistry` and return shortened url to the user.
2. When `GET` request to the 

