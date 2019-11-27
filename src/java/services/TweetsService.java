package services;


import java.util.ArrayList;
import models.Respuesta;
import models.Tweet;
import models.UsuarioTwitter;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;
import twitter4j.conf.ConfigurationBuilder;

public class TweetsService {
    
    Twitter tw;
    
    public TweetsService (){
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("69CNRZbl1THq3lGy0mz6dTGUD")
          .setOAuthConsumerSecret("PVb0GaUy2ljrpYZ8C8QuQNgaCWYGOJ6ATVAzs4Ah2aQrtJLhSO")
          .setOAuthAccessToken("1199370058537062400-QxHaOBqPi5XdyxMQ3oAjlQ3oRw9erm")
          .setOAuthAccessTokenSecret("k3aSqu3qi4NKitVNdMT2h56hP42lMTknO22tCej1uwnbp");
        TwitterFactory tf = new TwitterFactory(cb.build());
        tw = tf.getInstance();
    }
    
    public Respuesta<ArrayList> getweets(String busqueda, int count) {
        
        Respuesta resp = new Respuesta(200, true, "OK!");
        ArrayList<Tweet> listaTweets = new ArrayList();
        
        Query query = new Query(busqueda);
        query.setCount(count);
        
        try {
            QueryResult qr =  tw.search(query);
            
            if (qr.getTweets().size() == 100) {
                query = qr.nextQuery();
                count -= 100;
                query.setCount(count);
                qr = tw.search(qr.nextQuery());
            }
            
            int size = qr.getTweets().size() > count ?10:qr.getTweets().size() ;
            
            count -= 10;
            
            
            for (int i = 0; i < size; i++) {
                
                Status status = qr.getTweets().get(count + i);
                Tweet tweet = new Tweet();
                UsuarioTwitter user = new UsuarioTwitter();
                
                tweet.setId(status.getId());
                tweet.setTexto(status.getText());
                tweet.setFecha(status.getCreatedAt().toString());
                tweet.setCantidadFavoritos(status.getFavoriteCount());
                tweet.setCantidadRetweet(status.getRetweetCount());
                tweet.setUrl( "https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId());
                
                user.setId(status.getUser().getId());
                user.setName(status.getUser().getName());
                user.setScreenName(status.getUser().getScreenName());
                user.setImage(status.getUser().get400x400ProfileImageURL());
                user.setUrl("https://twitter.com/" + user.getScreenName());
                tweet.setUser(user);
                
                listaTweets.add(tweet);
                
            }
            
            resp.setContent(listaTweets);
            
        }catch(TwitterException e) {
            resp = new Respuesta(500, false, e.getMessage());
        }catch(IndexOutOfBoundsException e) {
            resp.setContent(listaTweets);
        }

        return resp;
        
    }
    
    
}
