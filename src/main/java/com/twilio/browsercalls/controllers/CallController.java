package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.browsercalls.lib.AppSetup;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.voice.Client;
import com.twilio.twiml.voice.Dial;
import com.twilio.twiml.voice.Number;

import spark.Request;
import spark.Route;

@SuppressWarnings({"rawtypes", "unchecked"})
public class CallController {
  AppSetup appSetup;
  public Route connect = (request, response) -> {
    response.type("application/xml");

    return getXMLResponse(request);
  };

  public CallController() {
    this.appSetup = new AppSetup();
  }

  public CallController(AppSetup appSetup) {
    this.appSetup = appSetup;
  }

  public String getXMLResponse(Request request) {
    String twilioPhoneNumber = null;
    try {
      twilioPhoneNumber = appSetup.getTwilioPhoneNumber();
    } catch (UndefinedEnvironmentVariableException e) {
      return e.getLocalizedMessage();
    }
    String phoneNumber = request.queryParams("phoneNumber");

    Dial.Builder dialBuilder = new Dial.Builder();

    /**
     * If the phoneNumber parameter is sent on the request, it means you are calling a customer. If
     * not, you will make a call to the support agent
     */
    if (phoneNumber != null) {
      Number number = new Number.Builder(phoneNumber).build();
      dialBuilder = dialBuilder.number(number).callerId(twilioPhoneNumber);
    } else {
      Client client = new Client.Builder("support_agent").build();
      dialBuilder = dialBuilder.client(client);
    }

    Dial dial = dialBuilder.build();
    VoiceResponse twimlResponse = new VoiceResponse.Builder().dial(dial).build();

    try {
      return twimlResponse.toXml();
    } catch (TwiMLException e) {
      return e.getLocalizedMessage();
    }
  }
}
