package com.vino.test.controllers.MasterData;

import com.vino.test.Prefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class EmployeeController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping(Prefix.PATH_MASTER_DATA+Prefix.PATH_EMPLOYEE)
    public String index(Model model, HttpServletRequest request,
                        @RequestParam(value = "name", required = false,
                                defaultValue = "Guest") String name){
        String context = servletContext.getContextPath();
        model.addAttribute("name", name);
        model.addAttribute("title", Prefix.appName);
        try {
            model.addAttribute("employee_path_api", Prefix.getURLBase(request) + Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_EMPLOYEE);
        }catch (Exception ex){
            model.addAttribute("employee_path_api", Prefix.PATH_API + Prefix.PATH_MASTER_DATA + Prefix.PATH_EMPLOYEE);
        }
        return "master/employee";
    }
}
