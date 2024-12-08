package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.database.Restaurant;

import java.util.ArrayList;
import org.inria.restlet.mta.database.Restaurant;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;

public class BuffetResource extends ServerResource {

    /** database. */
    private Restaurant db_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    // public BuffetResource()
    // {
    // super();
    // db_ = (Restaurant) getApplication().getContext().getAttributes()
    // .get("database");
    // }
    // // @Get("json")
    // // public Representation getBuffet() throws JSONException {

    // // Collection<Tweet> tweets = db_.getUserTweet();
    // // Collection<JSONObject> jsonTweets = new ArrayList<>();

    // // for (Tweet tweet : tweets) {

    // // JSONObject current = new JSONObject();
    // // current.put("id", tweet.getIdTweet());
    // // current.put("contenu", tweet.getContenu());
    // // // Vérification si l'utilisateur associé existe
    // // if (tweet.getUser() != null) {
    // // current.put("name", tweet.getUser().getName());
    // // } else {
    // // current.put("name", "Utilisateur inconnu");
    // // }
    // // jsonTweets.add(current);

    // // }
    // // JSONArray jsonArray = new JSONArray(jsonTweets);
    // // return new JsonRepresentation(jsonArray);
    // // }
}
