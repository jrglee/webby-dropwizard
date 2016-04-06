package com.revinate.webby;

import com.revinate.webby.resources.LocationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebbyDropwizardApplication extends Application<AppConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebbyDropwizardApplication.class);

    public static void main(final String[] args) throws Exception {
        new WebbyDropwizardApplication().run(args);
    }

    @Override
    public void run(final AppConfiguration configuration, final Environment environment)
            throws Exception {
        LOGGER.info("Starting application with name: {}", configuration.getAppName());

        final LocationResource locationResource = new LocationResource();

        environment.jersey().register(locationResource);
    }

}
