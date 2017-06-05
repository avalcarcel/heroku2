package com.organization.naturecitytours.user;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class WelcomeController {

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

}
