/**
 * Twilio Client configuration for the browser-calls-rails
 * example application.
 */

// Store some selectors for elements we'll reuse
var callStatus;
var answerButton;
var callSupportButton;
var hangUpButton;
var callCustomerButtons;

var device = null;

/* Helper function to update the call status bar */
function updateCallStatus(status) {
  callStatus.text(status);
}

/* Get a Twilio Client token with an AJAX request */
$(document).ready(function() {
  callStatus = $("#call-status");
  answerButton = $(".answer-button");
  callSupportButton = $(".call-support-button");
  hangUpButton = $(".hangup-button");
  callCustomerButtons = $(".call-customer-button");

  setupClient();
});

function setupHandlers(device) {
  device.on('ready', function (_device) {
    updateCallStatus("Ready");
  });

  /* Report any errors to the call status display */
  device.on('error', function (error) {
    updateCallStatus("ERROR: " + error.message);
  });

  /* Callback for when Twilio Client initiates a new connection */
  device.on('connect', function (connection) {
    // Enable the hang up button and disable the call buttons
    hangUpButton.prop("disabled", false);
    callCustomerButtons.prop("disabled", true);
    callSupportButton.prop("disabled", true);
    answerButton.prop("disabled", true);

    // If phoneNumber is part of the connection, this is a call from a
    // support agent to a customer's phone
    if ("phoneNumber" in connection.message) {
      updateCallStatus("In call with " + connection.message.phoneNumber);
    } else {
      // This is a call from a website user to a support agent
      updateCallStatus("In call with support");
    }
  });

  /* Callback for when a call ends */
  device.on('disconnect', function (connection) {
    // Disable the hangup button and enable the call buttons
    hangUpButton.prop("disabled", true);
    callCustomerButtons.prop("disabled", false);
    callSupportButton.prop("disabled", false);

    updateCallStatus("Ready");
  });

  /* Callback for when Twilio Client receives a new incoming call */
  device.on('incoming', function (connection) {
    updateCallStatus("Incoming support call");

    // Set a callback to be executed when the connection is accepted
    connection.accept(function () {
      updateCallStatus("In call with customer");
    });

    // Set a callback on the answer button and enable it
    answerButton.click(function () {
      connection.accept();
    });
    answerButton.prop("disabled", false);
  });
};

function setupClient() {
  $.post("/token/generate", {
    page: window.location.pathname
  }).done(function (data) {
    // Set up the Twilio Client device with the token
    device = new Twilio.Device(data.token);

    setupHandlers(device);
  }).fail(function () {
    updateCallStatus("Could not get a token from server!");
  });

};

/* Call a customer from a support ticket */
window.callCustomer = function (phoneNumber) {
  updateCallStatus("Calling " + phoneNumber + "...");

  var params = { "phoneNumber": phoneNumber };
  device.connect(params);
};

/* Call the support_agent from the home page */
window.callSupport = function () {
  updateCallStatus("Calling support...");

  // Our backend will assume that no params means a call to support_agent
  device.connect();
};

/* End a call */
window.hangUp = function () {
  device.disconnectAll();
};
