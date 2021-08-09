package sevryugin.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FirstController.
 *
 * @author Mihail_Sevryugin
 */
@Controller
//@RequestMapping("/first")
public class FirstController {
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        System.out.println("First : " + name + "; Second : " + surname);
        model.addAttribute("message", "First : " + name + "; Second : " + surname);
        return "/first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "/first/goodbye";
    }
}
