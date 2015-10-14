package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.models.TicketService;
import spark.ModelAndView;
import spark.TemplateViewRoute;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class HomeController {
  public TemplateViewRoute index = (request, response) -> {
    Map map = new HashMap();

    return new ModelAndView(map, "home.mustache");
  };
}
