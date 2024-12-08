package org.inria.restlet.mta.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.inria.restlet.mta.database.Restaurant;
import org.inria.restlet.mta.internals.Client;
import org.inria.restlet.mta.internals.StatusClient;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing the users
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class ClientsResource extends ServerResource {

    /** Database. */
    private Restaurant db_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public ClientsResource() {
        super();
        db_ = (Restaurant) getApplication().getContext().getAttributes()
                .get("database");
    }

    /**
     *
     * Returns the list of all the users
     *
     * @return JSON representation of the users
     * @throws JSONException
     */
    @Get("json")
    public Representation getClients() throws JSONException {
        List<Client> clients = db_.getClients();

        Collection<JSONObject> jsonClients = new ArrayList<JSONObject>();

        for (Client client : clients) {
            JSONObject current = new JSONObject();
            current.put("id", client.getIdentifiant());
            current.put("name", client.getNom());
            current.put("state", client.getStatus());
            current.put("url", getReference() + "/" + client.getIdentifiant());
            jsonClients.add(current);

        }
        JSONArray jsonArray = new JSONArray(jsonClients);
        return new JsonRepresentation(jsonArray);
    }

    @Post("json")
    public Representation createClient(JsonRepresentation representation)
            throws Exception {

        JSONObject object = representation.getJsonObject();
        String name = object.getString("name");
        // create the user
        Client client = db_.createClient(name);
        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("name", client.getNom());
        resultObject.put("id", client.getIdentifiant());
        resultObject.put("status", client.getStatus());
        JsonRepresentation result = new JsonRepresentation(resultObject);
        return result;
    }

}
