package com.twilio.browsercalls.lib;

import spark.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of a simple for validation that simply validates that a request holds all
 * necessary parameters.
 */
public class FieldValidator {
  private List<String> fields;
  private List<String> errors;

  public FieldValidator(String[] fields) {
    this.fields = Arrays.asList(fields);
  }

  public boolean valid(Request request) {
    Stream<String> validations = fields.stream().map(p -> {
      if (request.queryParams(p).length() == 0) {
        return p + " can't be empty";
      } else {
        return "";
      }
    });
    List<String> mappedValues = validations.collect(Collectors.toList());
    List<String> messages = new ArrayList<>();

    for (String value : mappedValues) {
      if (!value.equals("")) {
        messages.add(value);
      }
    }

    this.errors = messages;

    if (messages.size() > 0) {
      return false;
    } else {
      return true;
    }
  }

  public List<String> errors() {
    return errors;
  }
}
