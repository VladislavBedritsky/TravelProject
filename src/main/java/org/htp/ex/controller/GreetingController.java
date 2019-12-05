package org.htp.ex.controller;

import org.htp.ex.model.User;
import org.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="Guest") String name, Model model,
                           @AuthenticationPrincipal User user, Map<String, Object> model1) {

        model.addAttribute("name", name);
        model.addAttribute("authUser",user);

        if (user != null) {
            userService.userRole(user,model1);
        }

        return "greeting";
    }
}