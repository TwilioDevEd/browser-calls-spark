package com.twilio.browsercalls.lib;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.jwt.client.ClientCapability;
import com.twilio.jwt.client.IncomingClientScope;
import com.twilio.jwt.client.OutgoingClientScope;
import com.twilio.jwt.client.Scope;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CapabilityTokenGeneratorTest {
  @Test
  public void testGenerate()
      throws UndefinedEnvironmentVariableException {
    ClientCapability.Builder mockCapabilityBuilder = mock(ClientCapability.Builder.class);
    ClientCapability mockCapability = mock(ClientCapability.class);
    AppSetup mockAppSetup = mock(AppSetup.class);
    CapabilityTokenGenerator generator =
        new CapabilityTokenGenerator("some role", mockCapabilityBuilder, mockAppSetup);

    OutgoingClientScope outgoingScope = new OutgoingClientScope.Builder("App ID").build();
    IncomingClientScope incomingScope = new IncomingClientScope("some role");
    List<Scope> scopes = Arrays.asList(outgoingScope, incomingScope);

    when(mockAppSetup.getApplicationSid()).thenReturn("App ID");
    when(mockCapabilityBuilder.scopes(any())).thenReturn(mockCapabilityBuilder);
    when(mockCapabilityBuilder.build()).thenReturn(mockCapability);

    generator.generate();

    verify(mockCapabilityBuilder).scopes(any());
    verify(mockCapabilityBuilder).build();
    verify(mockCapability).toJwt();
  }
}
