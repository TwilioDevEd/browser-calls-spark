package com.twilio.browsercalls.controllers;

import com.twilio.browsercalls.lib.CapabilityTokenGenerator;
import com.twilio.browsercalls.lib.CapabilityTokenGeneratorFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import spark.Request;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenControllerTest {
  @Test
  public void getTokenAsJSONTest() {
    // Given
    Request mockRequest = mock(Request.class);
    CapabilityTokenGeneratorFactory generatorFactory = mock(CapabilityTokenGeneratorFactory.class);
    CapabilityTokenGenerator tokenGenerator = mock(CapabilityTokenGenerator.class);
    TokenController controller = new TokenController(generatorFactory);

    when(generatorFactory.create("support_agent")).thenReturn(tokenGenerator);
    when(tokenGenerator.generate()).thenReturn("token");
    when(mockRequest.queryParams("page")).thenReturn("/dashboard");

    String json = controller.getTokenAsJSON(mockRequest);
    JSONObject obj = null;

    try {
      obj = (JSONObject) new JSONParser().parse(json);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    assert (obj.containsKey("token"));
    assertNotNull(obj.get("token"));
    assertThat(obj.get("token"), not(""));
  }
}
