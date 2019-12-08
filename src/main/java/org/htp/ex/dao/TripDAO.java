package org.htp.ex.dao;

import org.htp.ex.model.Trip;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripDAO extends CrudRepository<Trip,Long> {

    Trip findById (Integer id);

    List<Trip> findByDate (String date);

    @Query("select t from Trip t join t.userFavorites u where u.id = :id")
    Iterable<Trip> findUserFavoriteTrips (@Param("id") Integer id);

    @Query("select t from Trip t where t.cityFrom.name = :name1 and t.cityWhere.name = :name2")
    Iterable<Trip> findByCityFromAndCityWhere (@Param("name1") String from, @Param("name2") String where);

    Iterable<Trip> findByDateAndTime (String date, String time);

    @Query("select t from Trip t where t.cityFrom.name = :name and t.time = :time")
    Iterable<Trip> findByCityFromAndTime (@Param("name") String from,@Param("time") String time);

    @Query("select t from Trip t where t.cityWhere.name = :name and t.time = :time")
    Iterable<Trip> findByCityWhereAndTime (@Param("name") String where,@Param("time") String time);

    @Query("select t from Trip t where t.cityWhere.name = :name and t.date = :date")
    Iterable<Trip> findByCityWhereAndDate (@Param("name") String where,@Param("date") String date);

    @Query("select t from Trip t where t.cityFrom.name = :name and t.date = :date")
    Iterable<Trip> findByCityFromAndDate (@Param("name") String from, @Param("date") String date);

    @Query ("select t from Trip t where t.cityWhere.name = :name and t.date = :date and t.time = :time")
    Iterable<Trip> findByCityWhereAndDateAndTime (@Param("name") String where, @Param("date") String date, @Param("time") String time);

    @Query ("select t from Trip t where t.cityFrom.name = :name and t.date = :date and t.time = :time")
    Iterable<Trip> findByCityFromAndDateAndTime (@Param("name") String from, @Param("date") String date, @Param("time") String time);

    @Query ("select t from Trip t where t.cityFrom.name = :from and t.cityWhere.name = :where and t.time = :time")
    Iterable<Trip> findByCityWhereAndCityFromAndTime (@Param("from") String from, @Param("where") String where, @Param("time") String time);

    @Query ("select t from Trip t where t.cityFrom.name = :from and t.cityWhere.name = :where and t.date = :date")
    Iterable<Trip> findByCityWhereAndCityFromAndDate (@Param("from") String from, @Param("where") String where, @Param("date") String date);
}
