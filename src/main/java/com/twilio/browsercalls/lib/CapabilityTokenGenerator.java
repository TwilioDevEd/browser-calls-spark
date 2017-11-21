package com.twilio.browsercalls.lib;

import java.util.Arrays;
import java.util.List;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.jwt.client.ClientCapability;
import com.twilio.jwt.client.IncomingClientScope;
import com.twilio.jwt.client.OutgoingClientScope;
import com.twilio.jwt.client.Scope;

/**
 * Class that generates a Twilio capability token based on the page that is requesting it.
 */
public class CapabilityTokenGenerator {
  private ClientCapability.Builder capabilityBuilder;
  private AppSetup appSetup;
  private String role;

  public CapabilityTokenGenerator(String role, ClientCapability.Builder capabilityBuilder,
      AppSetup setup) {
    this.role = role;
    appSetup = setup;
    this.capabilityBuilder = capabilityBuilder;
  }

  public CapabilityTokenGenerator(String role) {
    this.role = role;
    appSetup = new AppSetup();
    try {
      /**
       * To find TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN visit https://www.twilio.com/console
       */
      this.capabilityBuilder =
          new ClientCapability.Builder(appSetup.getAccountSid(), appSetup.getAuthToken());
    } catch (UndefinedEnvironmentVariableException e) {
      e.getLocalizedMessage();
    }
  }

  public String generate() {
    /**
     * Sets the role depending on the page that requests que token. If the token is requested from
     * the /dashboard page, the role will be support_agent.
     */
    String appSid = null;
    try {
      appSid = appSetup.getApplicationSid();
    } catch (UndefinedEnvironmentVariableException e) {
      System.out.println(e.getLocalizedMessage());
    }
    OutgoingClientScope outgoingScope = new OutgoingClientScope.Builder(appSid).build();
    IncomingClientScope incomingScope = new IncomingClientScope(role);

    List<Scope> scopes = Arrays.asList(outgoingScope, incomingScope);

    String token = capabilityBuilder.scopes(scopes).build().toJwt();

    return token;
  }
}
