package com.twilio.browsercalls.lib;

import org.junit.Test;
import spark.Request;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldsValidatorTest {
  @Test
  public void testValid() {
    Request mockRequest = mock(Request.class);
    FieldsValidator validator = new FieldsValidator(new String[] {"Existing field", "Missing field"});

    when(mockRequest.queryParams("Existing field")).thenReturn("Existing value");
    when(mockRequest.queryParams("Missing field")).thenReturn("");

    assertThat(validator.valid(mockRequest), is(false));
  }

  @Test
  public void testErrors() {
    Request mockRequest = mock(Request.class);
    FieldsValidator validator = new FieldsValidator(new String[] {"Existing field", "Missing field"});

    when(mockRequest.queryParams("Existing field")).thenReturn("Existing value");
    when(mockRequest.queryParams("Missing field")).thenReturn("");

    validator.valid(mockRequest);


  }
}
