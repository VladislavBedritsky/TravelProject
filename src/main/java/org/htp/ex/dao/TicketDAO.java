package org.htp.ex.dao;

import org.htp.ex.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TicketDAO extends CrudRepository<Ticket,Long> {

    @Query("select t from Ticket t where t.userU.id = :userId and t.tripT.id = :tripId")
   Ticket findTicketByUserIdAndTripId (@Param("userId") Integer userId,@Param("tripId") Integer tripId);
}
