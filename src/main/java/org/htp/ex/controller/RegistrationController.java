package org.htp.ex.controller;


import org.htp.ex.model.User;
import org.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getRegForm () {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser (
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String password1,
            User user,
            Map<String, Object> model
    ) {

        if (username.isEmpty() || password.isEmpty()) {
            model.put("emptyFields","Fields can not be empty.");
            return "registration";
        }

        if (!password.equals(password1)) {
            model.put("diffPassw","Different passwords.");
            return "registration";
        }

        if(!userService.addUser(user)) {
            model.put("isExists","Such login is already exists!");
            return "registration";
        }

        return "redirect:/login";
    }

}
