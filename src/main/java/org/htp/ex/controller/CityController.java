package org.htp.ex.controller;

import org.htp.ex.model.City;
import org.htp.ex.model.Trip;
import org.htp.ex.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/city_settings")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public String getCityForm (Map<String, Object> model) {
        Iterable<City> cities = cityService.findAll();
        model.put("cities",cities);

        return "citySettings";
    }

    @PostMapping
    public String addCity (
            @RequestParam String name,
            Map <String,Object> model) {

        City city = new City(name);
        cityService.save(city);

        Iterable<City> cities = cityService.findAll();
        model.put("cities",cities);

        return "citySettings";
    }

    @GetMapping("/delete_city/{id}")
    public String deleteCity (
            @PathVariable Integer id
    ){
        City city = cityService.findById(id);
        cityService.delete(city);
        return "redirect:/city_settings";
    }
}
