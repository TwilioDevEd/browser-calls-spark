<a href="https://www.twilio.com">
  <img src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg" alt="Twilio" width="250" />
</a>

# Browser Calls with Spark

Learn how to use [Twilio Client](https://www.twilio.com/client) to make browser-to-phone and browser-to-browser calls with ease. The unsatisfied customers of the Birchwood Bicycle Polo Co. need your help.

[Read the full tutorial here](https://www.twilio.com/docs/tutorials/walkthrough/browser-calls/java/spark)!

[![Build
Status](https://travis-ci.org/TwilioDevEd/browser-calls-spark.svg?branch=master)](https://travis-ci.org/TwilioDevEd/browser-calls-spark)

### Create a TwiML App

This project is configured to use a **TwiML App**, which allows us to easily set the voice URLs for all Twilio phone numbers we purchase in this app.

Create a new TwiML app at https://www.twilio.com/user/account/apps/add and use its `Sid` as the `TWIML_APPLICATION_SID` environment variable wherever you run this app.

![Creating a TwiML App](http://howtodocs.s3.amazonaws.com/call-tracking-twiml-app.gif)

Once you have created your TwiML app, configure your Twilio phone number to use it ([instructions here](https://www.twilio.com/help/faq/twilio-client/how-do-i-create-a-twiml-app)).

If you don't have a Twilio phone number yet, you can purchase a new number in your [Twilio Account Dashboard](https://www.twilio.com/user/account/phone-numbers/incoming).

### Run the application

1. Clone the repository and `cd` into it.
1. The application uses Gradle to manage dependencies. A wrapper is included in the 
   repository so, if you don't have Gradle installed on the system, you can use `./gradlew` to
   execute any gradle task.
1. The application uses PostgreSQL as the persistence layer. If you
   don't have it already, you should install it. The easiest way is by
   using [Postgres.app](http://postgresapp.com/).
1. Create a database.

   ```bash
   $ createdb browser_calls
   ```
1. Copy the sample configuration file and edit it to match your configuration.

    ```bash
    $ cp .env.example .env
    ```

   You'll need to set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`.

   You can find your `TWILIO_ACCOUNT_SID` and `TWILIO_AUTH_TOKEN` under
   your
   [Twilio Console](https://www.twilio.com/Console). Set
   `TWILIO_APPLICATION_SID` to the app SID you created
   before. `TWILIO_PHONE_NUMBER` should be set to the phone number you
   purchased above.
   
   Once you have populated all the values, load the variables with `source`.
   
    ```bash
    $ source .env
    ```

1. Run the migrations:

   ```bash
   $ ./gradlew flywayMigrate
   ```
1. Run the application using Gradle.

   ```bash
   $ ./gradlew run
   ```
   This will run the embedded spark application server that uses port 4567 by default.
   If you want to run the application using a different port, you must set the environment
   variable `PORT` to the port number you want to use.
1. Expose the application to the wider Internet using [ngrok](https://ngrok.com/)

   ```bash
   $ ngrok http 4567
   ```
   Once you have started ngrok, update your TwiML app's voice URL
   setting to use your ngrok hostname, so it will look something like
   this:

   ```
   http://<your-ngrok-subdomain>.ngrok.io/call/connect
   ```

### Dependencies

This application uses this Twilio helper library:
* [twilio-java](https://github.com/twilio/twilio-java)

### Run the tests

1. Run at the top-level directory:

   ```bash
   $ ./gradlew check
   ```

## Meta

* No warranty expressed or implied. Software is as is. Diggity.
* [MIT License](http://www.opensource.org/licenses/mit-license.html)
* Lovingly crafted by Twilio Developer Education.
