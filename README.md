<a  href="https://www.twilio.com">
<img  src="https://static0.twilio.com/marketing/bundles/marketing/img/logos/wordmark-red.svg"  alt="Twilio"  width="250"  />
</a>

# Browser Calls with Spark

![](https://github.com/TwilioDevEd/browser-calls-spark/workflows/Java-Gradle/badge.svg)

> We are currently in the process of updating this sample template. If you are encountering any issues with the sample, please open an issue at [github.com/twilio-labs/code-exchange/issues](https://github.com/twilio-labs/code-exchange/issues) and we'll try to help you.

## About

Learn how to use [Twilio Client](https://www.twilio.com/client) to make browser-to-phone and browser-to-browser calls with ease. The unsatisfied customers of the Birchwood Bicycle Polo Co. need your help.

[Read the full tutorial here](https://www.twilio.com/docs/tutorials/walkthrough/browser-calls/java/spark)!

Implementations in other languages:

| .NET | Python | Node | PHP | Ruby |
| :--- | :--- | :----- | :-- | :--- |
| [Done](https://github.com/TwilioDevEd/browser-calls-csharp) | [Done](https://github.com/TwilioDevEd/browser-calls-django)  | [Done](https://github.com/TwilioDevEd/browser-calls-node)  | [Done](https://github.com/TwilioDevEd/browser-calls-laravel) | [Done](https://github.com/TwilioDevEd/browser-calls-rails)  |

<!--
### How it works

**TODO: Describe how it works**
-->

## Set up

### Requirements

- [Java Development Kit](https://adoptopenjdk.net/) version 11 or later.
- [PostgreSQL](https://www.postgresql.org/)
- [ngrok](https://ngrok.com)
- A Twilio account - [sign up](https://www.twilio.com/try-twilio)

### Twilio Account Settings

This application should give you a ready-made starting point for writing your
own appointment reminder application. Before we begin, we need to collect
all the config values we need to run the application:

| Config Value  | Description |
| :-------------  |:------------- |
`TWILIO_ACCOUNT_SID` | Your primary Twilio account identifier - find this [in the console here](https://www.twilio.com/console).
`TWILIO_TWIML_APP_SID` | The TwiML application with a voice URL configured to access your server running this app - create one [in the console here](https://www.twilio.com/console/voice/twiml/apps). Also, you will need to configure the Voice "REQUEST URL" on the TwiML app once you've got your server up and running.
`TWILIO_CALLER_ID` | A Twilio phone number in [E.164 format](https://en.wikipedia.org/wiki/E.164) - you can [get one here](https://www.twilio.com/console/phone-numbers/incoming)
`API_KEY` / `API_SECRET` | Your REST API Key information needed to create an [Access Token](https://www.twilio.com/docs/iam/access-tokens) - create [one here](https://www.twilio.com/console/project/api-keys).

### Local development

After the above requirements have been met:

1. Clone this repository and `cd` into it

    ```bash
    git clone git@github.com:TwilioDevEd/browser-calls-spark.git
    cd browser-calls-spark
    ```

2. Set your environment variables

    ```bash
    cp .env.example .env
    ```
    See [Twilio Account Settings](#twilio-account-settings) to locate the necessary environment variables.

    If you are using a UNIX operating system, when the application starts the environment variables will be loaded. _If you are using a different operating system, make sure that all the
    variables from the `.env` file are loaded into your environment._

3. Create a database.

   ```bash
   createdb browser_calls
   ```

4. Run the migrations:

   ```bash
   $ ./gradlew flywayMigrate
   ```

5. Run the application

    ```bash
    make serve
    ```
    **NOTE:** If you are using a dedicated Java IDE like Eclipse or IntelliJ, you can start the application within the IDE and it will start in development mode, which means any changes on a source file will be automatically reloaded.

7. Expose the application to the wider Internet. [We recommend using ngrok to solve this problem](https://www.twilio.com/blog/2015/09/6-awesome-reasons-to-use-ngrok-when-testing-webhooks.html).

   ```bash
   ngrok http 4567 -host-header="localhost:4567"
   ```

   Once you have started ngrok, update your TwiML app's voice URL setting to use your ngrok hostname, so it will look something like this:

   ```
   http://<your-ngrok-subdomain>.ngrok.io/voice
   ```

That's it!

### Create a TwiML App

This project is configured to use a **TwiML App**, which allows us to easily set the voice URLs for all Twilio phone numbers we purchase in this app.

Create a new TwiML app at https://www.twilio.com/user/account/apps/add and use its `Sid` as the `TWIML_APPLICATION_SID` environment variable wherever you run this app.

Once you have created your TwiML app, configure your Twilio phone number to use it ([instructions here](https://www.twilio.com/help/faq/twilio-client/how-do-i-create-a-twiml-app)).

If you don't have a Twilio phone number yet, you can purchase a new number in your [Twilio Account Dashboard](https://www.twilio.com/user/account/phone-numbers/incoming).

### Try it out

1. To create a support ticket go to the home page.
   On this page you could fill some fields and create a ticket or you can call to support.

   ```
   https://<your-ngrok-subdomain>.ngrok.io
   ```

   __Note:__ Make sure you use the `https` version of your ngrok URL as some
   browsers won't allow access to the microphone unless you are using a secure
   SSL connection.

1. To respond to support tickets go to the `dashboard` page (you should open two windows or tabs).
   On this page you could call customers and answers phone calls.

   ```
   https://<your-ngrok-subdomain>.ngrok.io/dashboard
   ```

### Tests

You can run the tests locally by typing:

```bash
./gradlew test
```

### Cloud deployment

Additionally to trying out this application locally, you can deploy it to a variety of host services. Here is a small selection of them.

Please be aware that some of these might charge you for the usage or might make the source code for this application visible to the public. When in doubt research the respective hosting service first.

| Service                           |                                                                                                                                                                                                                           |
| :-------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| [Heroku](https://www.heroku.com/) | [![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/TwilioDevEd/browser-calls-spark/tree/master)                                                                                                                                       |

**Some notes:** 
- For Heroku, please [check this](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku) to properly configure the project for deployment.
- You can also follow [this guide](https://vaadin.com/blog/how-to-deploy-your-java-app-to-the-cloud) to deploy the application to several other cloud services including Google Cloud, Oracle Cloud, etc.

## Resources

- The CodeExchange repository can be found [here](https://github.com/twilio-labs/code-exchange/).

## Contributing

This template is open source and welcomes contributions. All contributions are subject to our [Code of Conduct](https://github.com/twilio-labs/.github/blob/master/CODE_OF_CONDUCT.md).

## License

[MIT](http://www.opensource.org/licenses/mit-license.html)

## Disclaimer

No warranty expressed or implied. Software is as is.

[twilio]: https://www.twilio.com
