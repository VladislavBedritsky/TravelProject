package org.htp.ex.controller;

import org.htp.ex.model.Trip;
import org.htp.ex.model.User;
import org.htp.ex.service.TripService;
import org.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;

    @GetMapping
    public String getUserList (Model model) {
        model.addAttribute("users",userService.findAll());
        return "userList";
    }

    @PostMapping
    public String saveUserByHisRole(
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user) {

        userService.saveUserByHisRole(user,form);

        return "redirect:/user";
    }

    @GetMapping("user/favorites")
    public String getUserFavoritesTrips (Model model) {



        return "favorites";
    }

    @GetMapping("like/{id}")
    public String addFavoriteTrip (
            @AuthenticationPrincipal User user,
            @PathVariable Integer id,
            Map <String, Object> model,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ) {

        Trip trip = tripService.findById(id);

        if (trip.getUserFavorites().contains(user)) {
            tripService.dislike(user,trip);
        }else {
            tripService.like(user,trip);
        }

        UriComponents components = UriComponentsBuilder.fromHttpUrl(referer).build();

        components.getQueryParams().entrySet().forEach(pair ->
                redirectAttributes.addAttribute(pair.getKey(),pair.getValue()));

        return "redirect:"+components.getPath();
    }
}

