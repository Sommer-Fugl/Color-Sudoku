package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Arrays;
import java.util.List;

public class RatingServiceRestClient implements RatingService{
    private final String url = "http://localhost:8080/api/rating";

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void setRating(Rating rating) throws RatingException {
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        int rate = restTemplate.getForEntity(url + "/average/" + game, Integer.class).getBody();
        return rate;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        int rate = restTemplate.getForEntity(url + "/onerating/" + game + "/" + player, Integer.class).getBody();
        return rate;
    }

    @Override
    public List<Rating> getRatingPlayer(String game) throws RatingException {
        return Arrays.asList(restTemplate.getForEntity(url + "/ratinglist/" + game, Rating[].class).getBody());
    }

    @Override
    public void reset() throws RatingException {
        throw new UnsupportedOperationException();
    }
}
