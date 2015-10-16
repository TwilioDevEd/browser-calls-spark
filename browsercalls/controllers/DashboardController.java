package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.models.Ticket;
import com.twilio.browsercalls.models.TicketService;
import spark.ModelAndView;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class DashboardController {
  private TicketService ticketService;

  public DashboardController(TicketService ticketService) {
    this.ticketService = ticketService;
  }

  public TemplateViewRoute index = (request, response) -> {
    Map map = new HashMap();

    List<Ticket> tickets = ticketService.findAll();
    map.put("tickets", tickets);

    return new ModelAndView(map, "dashboard.mustache");
  };
}
