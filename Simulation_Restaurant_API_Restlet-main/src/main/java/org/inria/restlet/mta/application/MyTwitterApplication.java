package org.inria.restlet.mta.application;

import org.inria.restlet.mta.resources.BuffetResource;
import org.inria.restlet.mta.resources.TweetResource;
import org.inria.restlet.mta.resources.UserResource;
import org.inria.restlet.mta.resources.ClientsResource;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**
 *
 * Application.
 *
 * @author msimonin
 *
 */
public class MyTwitterApplication extends Application {

    public MyTwitterApplication(Context context) {
        super(context);
    }

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/clients", ClientsResource.class);
        router.attach("/buffet", BuffetResource.class);

        return router;
    }
}
