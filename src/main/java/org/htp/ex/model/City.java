package org.htp.ex.model;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "cityFrom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Trip> departures;

    @OneToMany(mappedBy = "cityWhere", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Trip> arrivals;

    public City() {}

    public City (String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Trip> getDepartures() {
        return departures;
    }

    public void setDepartures(Set<Trip> departures) {
        this.departures = departures;
    }

    public Set<Trip> getArrivals() {
        return arrivals;
    }

    public void setArrivals(Set<Trip> arrivals) {
        this.arrivals = arrivals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id.equals(city.id) &&
                name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
