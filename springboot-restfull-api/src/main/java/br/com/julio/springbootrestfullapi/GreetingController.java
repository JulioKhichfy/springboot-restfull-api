package br.com.julio.springbootrestfullapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

//@Controller e @ResponseBody = @RestController
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    //responsável por mockar um id
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "word") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
