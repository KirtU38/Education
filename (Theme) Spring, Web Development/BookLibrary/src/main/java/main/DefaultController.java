package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
public class DefaultController {

    @RequestMapping("/")
    public int index() {

        return new Random().nextInt(100);
    }
}
