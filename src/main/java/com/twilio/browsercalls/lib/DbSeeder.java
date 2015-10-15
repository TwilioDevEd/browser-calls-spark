package com.twilio.browsercalls.lib;

import com.twilio.browsercalls.models.Ticket;
import com.twilio.browsercalls.models.TicketService;

import java.util.Date;

/**
 * Seeder class called upon application start to fill the database
 * with example data if it's empty.
 */
public class DbSeeder {
  private TicketService ticketService;

  public DbSeeder(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  public void seedIfDbEmpty() {
    Long recordCount = ticketService.count();

    if (recordCount == 0) {
      Ticket ticket1 =
          new Ticket("Marsellus Wallace", "+5551234567", "I have a problem, call me right away.",
              new Date());
      Ticket ticket2 =
          new Ticket("Vincent Vega", "+5551234567", "I have a problem, call me right away...Please",
              new Date());

      ticketService.create(ticket1);
      ticketService.create(ticket2);
    }
  }
}
