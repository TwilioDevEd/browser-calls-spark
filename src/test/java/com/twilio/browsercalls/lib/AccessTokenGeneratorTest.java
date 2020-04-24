package com.twilio.browsercalls.lib;

import com.google.common.collect.Lists;
import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.jwt.accesstoken.AccessToken;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class AccessTokenGeneratorTest {
  @Test
  public void testGenerate()
      throws UndefinedEnvironmentVariableException {
    AccessToken.Builder mockAccessTokenBuilder = mock(AccessToken.Builder.class);
    AccessToken mockCapability = mock(AccessToken.class);
    AppSetup mockAppSetup = mock(AppSetup.class);
    AccessTokenGenerator generator =
        new AccessTokenGenerator("some role", mockAccessTokenBuilder, mockAppSetup);

    when(mockAppSetup.getApplicationSid()).thenReturn("App ID");
    when(mockAccessTokenBuilder.grant(any())).thenReturn(mockAccessTokenBuilder);
    when(mockAccessTokenBuilder.build()).thenReturn(mockCapability);

    generator.generate();

    verify(mockAccessTokenBuilder).grant(any());
    verify(mockAccessTokenBuilder).build();
    verify(mockCapability).toJwt();
  }
}
