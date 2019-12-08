package org.htp.ex.controller;

import org.htp.ex.model.Ticket;
import org.htp.ex.model.Trip;
import org.htp.ex.model.User;
import org.htp.ex.service.MainService;
import org.htp.ex.service.TicketService;
import org.htp.ex.service.TripService;
import org.htp.ex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;
    @Autowired
    private MainService mainService;
    @Autowired
    private TicketService ticketService;

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

    @GetMapping("/favorite_trips")
    public String getUserFavoritesTrips (
            @AuthenticationPrincipal User user,
            Model model) {

        model.addAttribute("q",true);
        model.addAttribute("authUser",user);
        model.addAttribute("user_fav_trips",tripService.findUserFavoriteTrips(user.getId()));

        return "favorites";
    }

    @GetMapping("like/{id}")
    public String addAndRemoveFavoriteTrip (
            @AuthenticationPrincipal User user,
            @PathVariable Integer id,
            Map <String, Object> model,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ) {

        Trip trip = tripService.findById(id);

        if (trip.getUserFavorites().contains(user)) {
            userService.dislike(user,trip);
            tripService.dislike(user,trip);
        }else {
            userService.like(user,trip);
            tripService.like(user,trip);
        }

        return "redirect:" + mainService.uriComponents(redirectAttributes,referer).getPath();
    }

    @GetMapping("/ticket")
    public String getUserTickets (
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("q",true);
        model.addAttribute("user_tickets",user.getTickets());
        model.addAttribute("authUser",user);

        return "userTickets";
    }

    @GetMapping("/ticket/{type}/{id}")
    public String addAndRemoveTicket (
            @AuthenticationPrincipal User user,
            @PathVariable String type,
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer
    ){

        Trip trip = tripService.findById(id);
        ticketService.addTicket(type,trip,user);

        return "redirect:"+mainService.uriComponents(redirectAttributes,referer).getPath();
    }

}

