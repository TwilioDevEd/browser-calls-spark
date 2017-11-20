package com.twilio.browsercalls.lib;

public class CapabilityTokenGeneratorFactory {

    public CapabilityTokenGenerator create(String role) {
        return new CapabilityTokenGenerator(role);
    }
}
