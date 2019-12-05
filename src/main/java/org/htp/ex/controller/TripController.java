package org.htp.ex.controller;

import org.htp.ex.model.City;
import org.htp.ex.model.Trip;
import org.htp.ex.model.TripInfo;
import org.htp.ex.service.CityService;
import org.htp.ex.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/trip_settings")
public class TripController {

    @Autowired
    private TripService tripService;
    @Autowired
    private CityService cityService;

    @GetMapping
    public String getCityForm (Map<String, Object> model) {
        Iterable<Trip> trips = tripService.findAll();

        model.put("trips",tripService.findTripsAndCompareByDateAndTime(trips));

        return "tripSettings";
    }

    @PostMapping
    public String addTrip (
            @RequestParam String from,
            @RequestParam String where,
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam Integer cprice,
            @RequestParam Integer eprice,
            @RequestParam Integer amountc,
            @RequestParam Integer amounte,
            @RequestParam String dname,
            @RequestParam(name = "model") String carmodel,
            Map <String, Object> model
    ){

        TripInfo tripInfo = new TripInfo(amounte,eprice,amountc,cprice,carmodel,dname);

        City cityFrom = cityService.findByName(from);
        City cityWhere = cityService.findByName(where);
        Trip trip = new Trip(cityFrom,cityWhere,date,time,tripInfo);

        if (cityFrom == null || cityWhere == null) {
            model.put("noCity","City is not found. Try again or add a new City");
            return "tripSettings";
        }
        if (where.equalsIgnoreCase(from)) {
            model.put("sameCity","You are not available to add same cities");
            return "tripSettings";
        }

        tripService.addTrip(trip);

        return "redirect:/trip_settings";
    }

    @GetMapping("/delete_trip/{id}")
    public String deleteTrip (
            @PathVariable Integer id
    ){
        Trip trip = tripService.findById(id);
        tripService.delete(trip);

        return "redirect:/trip_settings";
    }


}
