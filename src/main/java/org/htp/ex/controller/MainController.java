package org.htp.ex.controller;

import org.htp.ex.model.User;
import org.htp.ex.service.CityService;
import org.htp.ex.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private TripService tripService;
    @Autowired
    private CityService cityService;

    @GetMapping
    public String getMain (
            @AuthenticationPrincipal User user,
            @RequestParam String from,
            @RequestParam String where,
            @RequestParam String date,
            @RequestParam String time,
            Map<String,Object> model) {

        if (!tripService.searchError(from, where, date, time) || tripService.findTrips(from, where, date, time) == null) {
            model.put("searchError", "Sorry, nothing was found according to your information.");
            return "main";
        }

        model.put("trips",tripService.findTripsAndCompareByDateAndTime(
                tripService.findTrips(from,where,date,time)));

        model.put("currentUser",user);

        return "main";
    }

}
