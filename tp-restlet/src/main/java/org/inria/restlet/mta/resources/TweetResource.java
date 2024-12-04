    package org.inria.restlet.mta.resources;

    import java.util.ArrayList;
    import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.inria.restlet.mta.database.InMemoryDatabase;
    import org.inria.restlet.mta.internals.Tweet;
    import org.inria.restlet.mta.internals.User;
    import org.restlet.resource.ResourceException;
    import org.restlet.resource.ServerResource;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.ext.json.JsonRepresentation;
    import org.restlet.representation.Representation;
    import org.restlet.resource.Delete;
    import org.restlet.resource.Get;
    import org.restlet.resource.Post;
    import org.restlet.resource.Put;


    public class TweetResource extends ServerResource
    {

        /** database. */
        private InMemoryDatabase db_;
        private Tweet tweet;

    
        /**
         * Constructor.
         * Call for every single user request.
         */
        public TweetResource()
        {
            db_ = (InMemoryDatabase) getApplication().getContext().getAttributes()
                    .get("database");
        }
        
        @Post("json")
        public Representation createTweet(JsonRepresentation representation)
            throws Exception
        {
            
            //récupérer l'utilisateur depuis l'url
            String userIdString = (String) getRequest().getAttributes().get("userId");
            int userId ;
            try {
                userId = Integer.valueOf(userIdString);
            } catch (NumberFormatException e) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "ID utilisateur invalide");
            }

            // Récupérer l'utilisateur de la base de données
            User user = db_.getUser(userId);
          if (user == null) {
           // Retourne un message explicite si l'utilisateur n'existe pas
        JSONObject errorObject = new JSONObject();
        errorObject.put("message", "Utilisateur introuvable avec ID : " + userId);
        return new JsonRepresentation(errorObject);
}
        //extraire le contenu du tweet depuis la requete json
            JSONObject object = representation.getJsonObject();
            String contenu = object.getString("contenu");
                
            // créer the tweet
            Tweet tweet = db_.createTweet(contenu,user);

            // generate result
            JSONObject resultObject = new JSONObject();
            resultObject.put("contenu", tweet.getContenu());
            resultObject.put("id", tweet.getIdTweet());
            JsonRepresentation result = new JsonRepresentation(resultObject);
            return result;
       
        
    }

        @Get("json")
        public Representation getTweet() throws JSONException {

            String userIdString = (String) getRequest().getAttributes().get("userId");
            int userId = Integer.valueOf(userIdString);
            User user= db_.getUser(userId);
            // Récupérer les tweets de cet utilisateur
            Collection<Tweet> tweets = db_.getUserTweet();
            List<Tweet> userTweets = tweets.stream()
                                    .filter(tweet -> tweet.getUser() != null && tweet.getUser().getId() == userId)
                                    .collect(Collectors.toList());

            JSONObject userObject=new JSONObject();
            userObject.put("user", tweet.getUser());
            userObject.put(("contenu"), tweet.getContenu());
            return new JsonRepresentation(userObject);
            
    }
        }
        
           
        
        
    
