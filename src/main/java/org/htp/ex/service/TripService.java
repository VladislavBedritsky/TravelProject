package org.htp.ex.service;

import org.htp.ex.dao.TripDAO;
import org.htp.ex.model.Trip;
import org.htp.ex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripDAO tripDAO;
    @Autowired
    private CityService cityService;

    public void addTrip(Trip trip) {
        tripDAO.save(trip);
    }

    public Iterable<Trip> findAll() {
        return tripDAO.findAll();
    }

    public void delete(Trip trip) {
        tripDAO.delete(trip);
    }

    public Trip findById(Integer id) {
        return tripDAO.findById(id);
    }

    public List<Trip> findByDate (String date) {
        return tripDAO.findByDate(date);
    }

    public Iterable<Trip> findByCityFromAndCityWhere (String from,String where) {
        return tripDAO.findByCityFromAndCityWhere(from,where);
    }

    public Iterable<Trip> findByDateAndTime (String date, String time) {
        return tripDAO.findByDateAndTime(date,time);
    }

    public Iterable<Trip> findByCityFromAndTime (String from, String time) {
        return tripDAO.findByCityFromAndTime(from, time);
    }

    public Iterable<Trip> findByCityWhereAndTime (String where, String time) {
        return tripDAO.findByCityWhereAndTime(where, time);
    }
    public Iterable<Trip> findByCityWhereAndDate (String where, String date) {
        return tripDAO.findByCityWhereAndDate(where, date);
    }
    public Iterable<Trip> findByCityFromAndDate (String from, String date) {
        return tripDAO.findByCityFromAndDate(from,date);
    }

    public Iterable<Trip> findByCityWhereAndDateAndTime (String where, String date, String time) {
        return tripDAO.findByCityWhereAndDateAndTime(where,date,time);
    }

    public Iterable<Trip> findByCityFromAndDateAndTime (String from, String date, String time) {
        return tripDAO.findByCityFromAndDateAndTime(from,date,time);
    }

    public Iterable<Trip> findByCityWhereAndCityFromAndTime (String from, String where, String time) {
        return tripDAO.findByCityWhereAndCityFromAndTime(from,where,time);
    }

    public Iterable<Trip> findByCityWhereAndCityFromAndDate (String from, String where, String date) {
        return tripDAO.findByCityWhereAndCityFromAndDate(from,where,date);
    }

    public boolean searchError (String from, String where, String date, String time) {
        if (where.isEmpty() && date.isEmpty() && time.isEmpty() && from.isEmpty()) {
            return false;
        }else if ((from.isEmpty() && where.isEmpty() && date.isEmpty())) {
            return false;
        }
        return true;
    }

    public Iterable<Trip> findTrips (String from, String where, String date, String time) {

        if (where.isEmpty() && date.isEmpty() && time.isEmpty()) {
            if (cityService.findByName(from) == null) {
                return null;
            }
            return cityService.findByName(from).getDepartures();
        }
        else if (from.isEmpty() && date.isEmpty() && time.isEmpty()) {
            if (cityService.findByName(where) == null) {
                return null;
            }
            return cityService.findByName(where).getArrivals();
        }
        else if (from.isEmpty() && where.isEmpty() && time.isEmpty()) {
            return findByDate(date);
        }
        else if (date.isEmpty() && time.isEmpty()) {
            if (cityService.findByName(from) == null && cityService.findByName(where) != null) {
                return null;
            }
            return findByCityFromAndCityWhere(from,where);
        }
        else if (from.isEmpty() && where.isEmpty()) {
            return findByDateAndTime(date,time) ;
        }
        else if (date.isEmpty() && from.isEmpty()) {
            if (cityService.findByName(where) == null) {
                return null;
            }
            return findByCityWhereAndTime(where, time);
        }
        else if (date.isEmpty() && where.isEmpty()) {
            if (cityService.findByName(from) == null) {
                return null;
            }
            return findByCityFromAndTime(from, time);
        }
        else if (from.isEmpty() && time.isEmpty()) {
            if (cityService.findByName(where) == null) {
                return null;
            }
            return findByCityWhereAndDate(where, date);
        }
        else if (where.isEmpty() && time.isEmpty()) {
            if (cityService.findByName(from) == null) {
                return null;
            }
            return findByCityFromAndDate(from, date);
        }
        else if (from.isEmpty()) {
            if (cityService.findByName(where) == null) {
                return null;
            }
            return findByCityWhereAndDateAndTime(where,date,time);
        }
        else if (where.isEmpty()) {
            if (cityService.findByName(from) == null) {
                return null;
            }
            return findByCityFromAndDateAndTime(from, date, time);
        }
        else if (date.isEmpty()) {
            if (cityService.findByName(from) == null && cityService.findByName(where) != null) {
                return null;
            }
            return findByCityWhereAndCityFromAndTime(from, where, time);
        }
        else if (time.isEmpty()) {
            if (cityService.findByName(from) == null && cityService.findByName(where) != null) {
                return null;
            }
            return findByCityWhereAndCityFromAndDate(from, where, date);
        }


        return null;
    }

    public List<Trip> findTripsAndCompareByDateAndTime (Iterable<Trip> trips) {
        List<Trip> t = new ArrayList<>();
        for (Trip temp : trips) {
            t.add(temp);
        }

        Comparator<Trip> comparator = Comparator.comparing(Trip::getDate).thenComparing(Trip::getTime);
        Collections.sort(t,comparator);

        return t;
    }

    public void like (User user, Trip trip) {
        trip.getUserFavorites().add(user);
        addTrip(trip);
    }
    public void dislike (User user, Trip trip) {
        trip.getUserFavorites().remove(user);
        addTrip(trip);
    }

    public Iterable<Trip> findUserFavoriteTrips (Integer id) {
        return tripDAO.findUserFavoriteTrips(id);
    }

}
