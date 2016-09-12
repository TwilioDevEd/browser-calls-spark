package com.twilio.browsercalls.lib;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that holds methods to obtain configuration parameters from the environment.
 */
public class AppSetup {
  private Map<String, String> env;

  public AppSetup() {
    this.env = System.getenv();
  }

  /**
   * Returns an entity manager factory using the defined environment variables that this class
   * has access to.
   *
   * @return EntityManagerFactory
   */
  public EntityManagerFactory getEntityManagerFactory() {
    AppSetup appSetup = new AppSetup();
    Map<String, String> configOverrides = new HashMap<>();

    try {
      configOverrides.put("javax.persistence.jdbc.url", getDatabaseURL());
      configOverrides.put("javax.persistence.jdbc.user", getDatabaseUsername());
      configOverrides.put("javax.persistence.jdbc.password", getDatabasePassword());
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }

    return Persistence.createEntityManagerFactory("Browser-Calls-Persistence", configOverrides);
  }

  public int getPortNumber() {
    String port = env.get("PORT");

    if (port != null) {
      return Integer.parseInt(port);
    } else {
      return 4567;
    }
  }

  public String getDatabaseURL() throws UndefinedEnvironmentVariableException {
    String url = env.get("DB_URL");
    if (url == null) {
      throw new UndefinedEnvironmentVariableException("DB_URL is not defined");
    } else {
      return url;
    }
  }

  public String getDatabaseUsername() throws UndefinedEnvironmentVariableException {
    String username = env.get("DB_USERNAME");
    if (username == null) {
      throw new UndefinedEnvironmentVariableException("DB_USERNAME is not defined");
    } else {
      return username;
    }
  }

  public String getDatabasePassword() {
    String password = env.get("DB_PASSWORD");
    return password != null ? password : "";
  }

  public String getAccountSid() throws UndefinedEnvironmentVariableException {
    String sid = env.get("TWILIO_ACCOUNT_SID");
    if (sid == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_ACCOUNT_SID is not defined");
    } else {
      return sid;
    }
  }

  public String getAuthToken() throws UndefinedEnvironmentVariableException {
    String token = env.get("TWILIO_AUTH_TOKEN");
    if (token == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_AUTH_TOKEN is not set");
    } else {
      return token;
    }
  }

  public String getTwilioPhoneNumber() throws UndefinedEnvironmentVariableException {
    String phoneNumber = env.get("TWILIO_PHONE_NUMBER");
    if (phoneNumber == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_PHONE_NUMBER is not set");
    } else {
      return phoneNumber;
    }
  }

  public String getApplicationSid() throws UndefinedEnvironmentVariableException {
    String sid = env.get("TWILIO_APPLICATION_SID");
    if (sid == null) {
      throw new UndefinedEnvironmentVariableException("TWILIO_APPLICATION_SID is not set");
    } else {
      return sid;
    }
  }
}
