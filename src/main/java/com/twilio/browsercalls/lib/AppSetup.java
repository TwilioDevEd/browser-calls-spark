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

    return port != null ? Integer.parseInt(port) : 8080;
  }

  public String getDatabasePassword() {
    String password = env.get("DB_PASSWORD");
    return password != null ? password : "";
  }

  public String getDatabaseURL() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("DB_URL", "DB_URL is not defined");
  }

  public String getDatabaseUsername() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("DB_USERNAME", "DB_USERNAME is not defined");
  }

  public String getAccountSid() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("TWILIO_ACCOUNT_SID", "TWILIO_ACCOUNT_SID is not defined");
  }
  
  public String getTwilioPhoneNumber() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("TWILIO_PHONE_NUMBER", "TWILIO_PHONE_NUMBER is not set");
  }
  
  public String getApplicationSid() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("TWILIO_APPLICATION_SID",
    "TWILIO_APPLICATION_SID is not set");
  }
  
  public String getApiKey() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("API_KEY", "API_KEY is not set");
  }
  
  public String getApiSecret() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("API_SECRET", "API_SECRET is not set");
  }

  private String getEnvironmentVariable(String twilio_account_sid, String message) throws UndefinedEnvironmentVariableException {
    String sid = env.get(twilio_account_sid);
    if (sid == null) {
      throw new UndefinedEnvironmentVariableException(message);
    } else {
      return sid;
    }
  }
}
