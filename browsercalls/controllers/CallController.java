package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.browsercalls.lib.AppSetup;
import com.twilio.sdk.verbs.*;
import com.twilio.sdk.verbs.Number;
import spark.Request;
import spark.Route;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CallController {
  AppSetup appSetup;

  public CallController() {
    this.appSetup = new AppSetup();
  }

  public CallController(AppSetup appSetup) {
    this.appSetup = appSetup;
  }

  public Route connect = (request, response) -> {
    response.type("application/xml");

    return getXMLResponse(request);
  };

  public String getXMLResponse(Request request) {
    String twilioPhoneNumber = null;
    try {
      twilioPhoneNumber = appSetup.getTwilioPhoneNumber();
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }
    String phoneNumber = request.queryParams("phoneNumber");

    TwiMLResponse twimlResponse = new TwiMLResponse();
    Dial dial = new Dial();

    /**
     * If the phoneNumber parameter is sent on the request, it means you are calling a customer.
     * If not, you will make a call to the support agent
     */
    try {
      if (phoneNumber != null) {
        dial.append(new Number(phoneNumber));
        dial.setCallerId(twilioPhoneNumber);
      } else {
        dial.append(new Client("support_agent"));
      }
      twimlResponse.append(dial);
    } catch (TwiMLException e) {
      e.printStackTrace();
    }

    return twimlResponse.toXML();
  }
}
