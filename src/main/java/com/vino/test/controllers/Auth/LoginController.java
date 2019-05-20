package com.vino.test.controllers.Auth;

import com.vino.test.Prefix;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(Model model,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        model.addAttribute("title", Prefix.appName);
        return "auth/login";
    }
}
