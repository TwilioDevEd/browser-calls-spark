package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.lib.FieldValidator;
import com.twilio.browsercalls.models.Ticket;
import com.twilio.browsercalls.models.TicketService;
import spark.ModelAndView;
import spark.TemplateViewRoute;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TicketController {
  TicketService ticketService;

  public TicketController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  public TemplateViewRoute create = (request, response) -> {
    FieldValidator validator =
        new FieldValidator(new String[] {"name", "phone_number", "description"});

    Map map = new HashMap();

    /** Validates that the required parameters are sent on the request. */
    if (validator.valid(request)) {
      String name = request.queryParams("name");
      String phoneNumber = request.queryParams("phone_number");
      String description = request.queryParams("description");
      Date date = new Date();

      Ticket ticket = new Ticket(name, phoneNumber, description, date);
      ticketService.create(ticket);
      map.put("message", true);
      map.put("notice", "Ticket created successfully");

      return new ModelAndView(map, "home.mustache");
    } else {
      map.put("error", true);
      map.put("errors", validator.errors());
      return new ModelAndView(map, "home.mustache");
    }
  };
}
