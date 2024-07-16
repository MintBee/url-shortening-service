package groonvail.example.urlshorteningserver.learning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@RestController
public class TestController {

    @GetMapping("/{param}")
    public String get1(@PathVariable("param") String param) {
        return "this is " + param;
    }

    @GetMapping("/hello/{param}")
    public String get2(@PathVariable("param") String param) {
        return "Hello! " + param;
    }
}
