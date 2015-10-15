package com.twilio.browsercalls.lib;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.sdk.CapabilityToken;
import com.twilio.sdk.client.TwilioCapability;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class CapabilityTokenGeneratorTest {
  @Test
  public void testGenerate()
      throws UndefinedEnvironmentVariableException, CapabilityToken.DomainException {
    TwilioCapability mockCapability = mock(TwilioCapability.class);
    AppSetup mockAppSetup = mock(AppSetup.class);
    CapabilityTokenGenerator generator =
        new CapabilityTokenGenerator("some role", mockCapability, mockAppSetup);

    when(mockAppSetup.getApplicationSid()).thenReturn("App ID");

    generator.generate();

    verify(mockCapability).allowClientIncoming("some role");
    verify(mockCapability).allowClientOutgoing("App ID");
    verify(mockCapability).generateToken();
  }
}
