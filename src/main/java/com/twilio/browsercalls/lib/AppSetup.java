package com.twilio.browsercalls.lib;

import com.twilio.browsercalls.exceptions.UndefinedEnvironmentVariableException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class that holds methods to obtain configuration parameters from the environment.
 */
public class AppSetup {
  private Map<String, String> env;

  private Optional<String> dbUser;
  private Optional<String> dbPass;
  private Optional<String> dbConnectionUrl;

  public AppSetup(String dbUser, String dbPass, String dbConnectionUrl) {
    this.dbUser = Optional.of(dbUser);
    this.dbPass = Optional.of(dbPass);
    this.dbConnectionUrl = Optional.of(dbConnectionUrl);
  }

  public AppSetup() {

    dbUser = Optional.empty();
    dbPass = Optional.empty();
    dbConnectionUrl = Optional.empty();
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
      String url = dbConnectionUrl.isPresent() ? dbConnectionUrl.get() : getDatabaseURL();
      String user = dbUser.isPresent() ? dbUser.get() : getDatabaseUsername();
      String password = dbPass.isPresent() ? dbPass.get() : getDatabasePassword();
      configOverrides.put("javax.persistence.jdbc.url", url);
      configOverrides.put("javax.persistence.jdbc.user", user);
      configOverrides.put("javax.persistence.jdbc.password", password);
    } catch (UndefinedEnvironmentVariableException e) {
      e.printStackTrace();
    }

    return Persistence.createEntityManagerFactory("Browser-Calls-Persistence", configOverrides);
  }

  public int getPortNumber() {
    String port = env.get("PORT");

    return port != null ? Integer.parseInt(port) : 4567;
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

  public String getAuthToken() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("TWILIO_AUTH_TOKEN", "TWILIO_AUTH_TOKEN is not set");
  }

  public String getTwilioPhoneNumber() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("TWILIO_PHONE_NUMBER", "TWILIO_PHONE_NUMBER is not set");
  }

  public String getApplicationSid() throws UndefinedEnvironmentVariableException {
    return getEnvironmentVariable("TWILIO_APPLICATION_SID",
                          "TWILIO_APPLICATION_SID is not set");
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
