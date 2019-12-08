package org.htp.ex.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_city")
    private City cityFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "where_city")
    private City cityWhere;

    @Embedded
    private TripInfo tripInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="favorites_trips",
            joinColumns = {@JoinColumn(name="trip_id")},
            inverseJoinColumns = {@JoinColumn(name="user_id")}
    )
    private Set<User> userFavorites = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="tickets",
            joinColumns = {@JoinColumn(name="trip_id")},
            inverseJoinColumns = {@JoinColumn(name="user_id")}
    )
    private Set<User> userTickets = new HashSet<>();

    @OneToMany(mappedBy = "tripT",fetch = FetchType.EAGER)
    private Set<Ticket> tickets;

    public Trip() { }

    public Trip (City cityFrom, City cityWhere,String date, String time, TripInfo tripInfo) {
        this.date = date;
        this.cityFrom = cityFrom;
        this.cityWhere = cityWhere;
        this.time = time;
        this.tripInfo = tripInfo;
    }

    public boolean userPurchase (User user) {
        return userTickets.contains(user);
    }

    public boolean isQ (User user) {
        return userFavorites.contains(user);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCityFrom() {
        return cityFrom;
    }

    public void setCityFrom(City cityFrom) {
        this.cityFrom = cityFrom;
    }

    public City getCityWhere() {
        return cityWhere;
    }

    public void setCityWhere(City cityWhere) {
        this.cityWhere = cityWhere;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TripInfo getTripInfo() {
        return tripInfo;
    }

    public void setTripInfo(TripInfo tripInfo) {
        this.tripInfo = tripInfo;
    }

    public Set<User> getUserFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(Set<User> userFavorites) {
        this.userFavorites = userFavorites;
    }

    public Set<User> getUserTickets() {
        return userTickets;
    }

    public void setUserTickets(Set<User> userTickets) {
        this.userTickets = userTickets;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return id.equals(trip.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
