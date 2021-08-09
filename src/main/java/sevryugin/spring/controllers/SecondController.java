package sevryugin.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * SecondContoller.
 *
 * @author Mihail_Sevryugin
 */
@Controller
public class SecondController {
    @GetMapping("/exit")
    public String exit(){
        return "second/exit";
    }
}
