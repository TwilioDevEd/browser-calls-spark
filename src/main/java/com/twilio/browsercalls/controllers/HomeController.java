package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.models.TicketService;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HomeController {

  public HomeController() {
    this(new TicketService(null));
  }

  public HomeController(TicketService service) {

  }

}
