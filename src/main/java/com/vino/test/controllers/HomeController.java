package com.vino.test.controllers;

import com.vino.test.Prefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    Environment env;

    @GetMapping("/")
    public String home(Model model, HttpServletRequest request,
                       @RequestParam(value = "name", required = false,
                               defaultValue = "Guest") String name) {

        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        model.addAttribute("gateway_path_api", env.getProperty("api.address") + env.getProperty("api.servlet.context-path"));
        try {
            model.addAttribute("dashboard_path_api", Prefix.getURLBase(request) + Prefix.PATH_API + "/dashboard");
        }catch (Exception ex){
            model.addAttribute("dashboard_path_api", Prefix.PATH_API + "/dashboard");
        }
        return "index";

    }
}
