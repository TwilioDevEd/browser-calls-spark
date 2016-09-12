package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.lib.FieldsValidator;
import com.twilio.browsercalls.models.Ticket;
import com.twilio.browsercalls.models.TicketService;
import spark.ModelAndView;
import spark.Request;
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
    return new ModelAndView(createTicket(request), "home.mustache");
  };

  public Map createTicket(Request request) {
    FieldsValidator validator =
        new FieldsValidator(new String[] {"name", "phone_number", "description"});

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

      return map;
    } else {
      map.put("error", true);
      map.put("errors", validator.errors());
      return map;
    }
  }
}
