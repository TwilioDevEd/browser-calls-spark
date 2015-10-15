package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.lib.CapabilityTokenGenerator;
import org.json.simple.JSONObject;
import spark.Route;

public class TokenController {
  public Route getToken = (request, response) -> {
    String page = request.queryParams("page");
    String role = page.equals("/dashboard") ? "support_agent" : "customer";
    CapabilityTokenGenerator generator = new CapabilityTokenGenerator(role);
    String token = generator.generate();

    JSONObject obj = new JSONObject();
    obj.put("token", token);
    response.type("application/json");
    return obj.toJSONString();
  };
}
