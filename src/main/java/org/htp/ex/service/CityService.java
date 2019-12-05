package org.htp.ex.service;

import org.htp.ex.dao.CityDAO;
import org.htp.ex.model.City;
import org.htp.ex.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    private CityDAO cityDAO;

    public Iterable<City> findAll() {
        return cityDAO.findAll();
    }

    public void save(City city) {
        cityDAO.save(city);
    }

    public void delete(City city) {
        cityDAO.delete(city);
    }

    public City findById(Integer id) {
        return cityDAO.findById(id);
    }

    public City findByName (String name) {
        return cityDAO.findByName(name);
    }
}
