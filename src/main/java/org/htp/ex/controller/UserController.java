package org.htp.ex.controller;

import org.htp.ex.model.User;
import org.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getUserList (Model model) {
        model.addAttribute("users",userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String saveUserByHisRole(
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user) {

        userService.saveUserByHisRole(user,form);

        return "redirect:/user";
    }
}

