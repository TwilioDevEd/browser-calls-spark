package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.lib.AccessTokenGenerator;
import org.json.simple.JSONObject;
import spark.Route;
import spark.Request;

public class TokenController {
  public Route getToken = (request, response) -> {
    response.type("application/json");

    return getTokenAsJSON(request);
  };

  public String getTokenAsJSON(Request request) {
    /**
     * Generates a token with specific capabilities depending on the page that is requesting
     * the page.
     */
    String page = request.queryParams("page");
    String role = page.equals("/dashboard") ? "support_agent" : "customer";
    AccessTokenGenerator generator = new AccessTokenGenerator(role);
    String token = generator.generate();

    JSONObject obj = new JSONObject();
    obj.put("token", token);

    return obj.toJSONString();
  }
}
