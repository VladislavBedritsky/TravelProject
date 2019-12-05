package org.htp.ex.dao;

import org.htp.ex.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityDAO extends CrudRepository<City, Long> {

    City findById(Integer id);

    City findByName(String name);
}
