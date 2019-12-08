package org.htp.ex.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ticket_info")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip tripT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userU;

    public Ticket() {}

    public Ticket (String type, Integer price, Trip tripT, User userU) {
        this.type = type;
        this.price = price;
        this.tripT = tripT;
        this.userU = userU;
    }

    public Ticket (Trip tripT, User userU) {
        this.tripT = tripT;
        this.userU = userU;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Trip getTripT() {
        return tripT;
    }

    public void setTripT(Trip tripT) {
        this.tripT = tripT;
    }

    public User getUserU() {
        return userU;
    }

    public void setUserU(User userU) {
        this.userU = userU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
