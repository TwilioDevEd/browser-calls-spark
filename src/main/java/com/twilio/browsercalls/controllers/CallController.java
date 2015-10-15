package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.lib.AppSetup;
import com.twilio.sdk.verbs.Client;
import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.Number;
import com.twilio.sdk.verbs.TwiMLResponse;
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
    String twilioPhoneNumber = appSetup.getTwilioPhoneNumber();
    String phoneNumber = request.queryParams("phoneNumber");

    TwiMLResponse twimlResponse = new TwiMLResponse();
    Dial dial = new Dial();

    if (phoneNumber != null) {
      dial.append(new Number(phoneNumber));
      dial.setCallerId(twilioPhoneNumber);
    }
    else {
      dial.append(new Client("support_agent"));
    }
    twimlResponse.append(dial);

    response.type("application/xml");
    return twimlResponse.toXML();
  };
}
