package org.htp.ex.service;

import org.htp.ex.dao.TicketDAO;
import org.htp.ex.model.Ticket;
import org.htp.ex.model.Trip;
import org.htp.ex.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private TripService tripService;
    @Autowired
    private UserService userService;

    public void save (Ticket ticket) {
        ticketDAO.save(ticket);
    }

    public void delete (Ticket ticket) {
        ticketDAO.delete(ticket);
    }

    public Ticket findTicketByUserIdAndTripId (Integer userId, Integer tripId) {
        return ticketDAO.findTicketByUserIdAndTripId(userId, tripId);
    }

    public void addTicket (String type, Trip trip, User user) {

        Ticket ticket;
        Integer q;

        if("economy".equals(type)) {
            q = trip.getTripInfo().getAmountEconomyPlace()-1;
            trip.getTripInfo().setAmountEconomyPlace(q);
            ticket = new Ticket(type,trip.getTripInfo().getPriceEconomyPlace(),trip,user);
        }else {
            q = trip.getTripInfo().getAmountComfortPlace() - 1;
            trip.getTripInfo().setAmountComfortPlace(q);
            ticket = new Ticket(type, trip.getTripInfo().getPriceComfortPlace(), trip, user);
        }

        trip.getUserTickets().add(user);
        user.getTripTickets().add(trip);
        trip.getTickets().add(ticket);
        user.getTickets().add(ticket);
        tripService.addTrip(trip);
        userService.addUser(user);
        save(ticket);
    }
}
