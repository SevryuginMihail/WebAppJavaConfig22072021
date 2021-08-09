package sevryugin.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HelloController.
 *
 * @author Mihail_Sevryugin
 */
@Controller
public class HelloController {
    @GetMapping("/hello_world")
    public String sayHello(){
        return "hello_world.html";
    }
}
