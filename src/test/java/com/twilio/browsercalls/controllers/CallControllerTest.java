package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.browsercalls.lib.AppSetup;
import org.jdom2.JDOMException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import spark.Request;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;

import java.io.IOException;
import java.io.StringReader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallControllerTest {
  @Test
  public void getXMLResponseTest() throws UndefinedEnvironmentVariableException {
    AppSetup mockAppSetup = mock(AppSetup.class);
    Request mockRequest = mock(Request.class);
    CallController controller = new CallController(mockAppSetup);

    when(mockAppSetup.getTwilioPhoneNumber()).thenReturn("+twilio_number");
    when(mockRequest.queryParams("phoneNumber")).thenReturn("+client_number");

    String xml = controller.getXMLResponse(mockRequest);

    SAXBuilder builder = new SAXBuilder();
    Document document = null;
    String callerId = null;
    String number = null;
    try {
      document = builder.build(new StringReader(xml));
      callerId = document.getRootElement().getChild("Dial").getAttribute("callerId").getValue();
      number = document.getRootElement().getChild("Dial").getChild("Number").getValue();
    } catch (JDOMException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    assertThat(callerId, is("+twilio_number"));
    assertThat(number, is("+client_number"));
  }
}
