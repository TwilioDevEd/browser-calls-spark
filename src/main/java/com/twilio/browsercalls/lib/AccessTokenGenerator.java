package com.twilio.browsercalls.lib;

import java.util.List;

import com.google.common.collect.Lists;
import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;

// Token generation imports
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VoiceGrant;

/**
 * Class that generates a Twilio capability token based on the page that is requesting it.
 */
public class AccessTokenGenerator {
  private AccessToken.Builder accessTokenBuilder;
  private AppSetup appSetup;
  private String role;

  public AccessTokenGenerator(String role, AccessToken.Builder accessTokenBuilder,
      AppSetup setup) {
    this.role = role;
    appSetup = setup;
    this.accessTokenBuilder = accessTokenBuilder;
  }

  public AccessTokenGenerator(String role) {
    this.role = role;
    appSetup = new AppSetup();
    try {
      /**
       * To find TWILIO_ACCOUNT_SID visit https://www.twilio.com/console
       */
      this.accessTokenBuilder =
          new AccessToken.Builder(appSetup.getAccountSid(), appSetup.getApiKey(), appSetup.getApiSecret()).identity(this.role);
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

    // Create Voice grant
    VoiceGrant grant = new VoiceGrant();
    grant.setOutgoingApplicationSid(appSid);
    
    // Optional: add to allow incoming calls
    grant.setIncomingAllow(true);
    
    String token = this.accessTokenBuilder.grant(grant).build().toJwt();
    
    return token;
  }
}
