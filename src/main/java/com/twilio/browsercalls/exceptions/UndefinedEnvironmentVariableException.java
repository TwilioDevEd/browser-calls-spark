package com.twilio.browsercalls.exceptions;

/**
 * Exception raised when an environment variable is not defined.
 */
public class UndefinedEnvironmentVariableException extends Exception {
  public UndefinedEnvironmentVariableException(String message) {
    super(message);
  }
}
