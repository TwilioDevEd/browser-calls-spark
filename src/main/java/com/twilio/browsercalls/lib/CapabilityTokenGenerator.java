package com.twilio.browsercalls.lib;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;
import com.twilio.sdk.CapabilityToken;
import com.twilio.sdk.client.TwilioCapability;

public class CapabilityTokenGenerator {
  private String role;
  TwilioCapability capability;
  AppSetup appSetup;

  public CapabilityTokenGenerator(String role) {
    this.role = role;
    appSetup = new AppSetup();
    try {
      /**
       * To find TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN visit
       * https://www.twilio.com/user/account
       */
      this.capability = new TwilioCapability(appSetup.getAccountSid(), appSetup.getAuthToken());
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }
  }

  public CapabilityTokenGenerator(String role, TwilioCapability capability, AppSetup setup) {
    this.role = role;
    appSetup = setup;
    this.capability = capability;
  }

  public String generate() {
    String token = null;
    try {
      capability.allowClientIncoming(role);
      capability.allowClientOutgoing(appSetup.getApplicationSid());
      token = capability.generateToken();
    } catch (CapabilityToken.DomainException e) {
      e.printStackTrace();
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }
    return token;
  }
}
