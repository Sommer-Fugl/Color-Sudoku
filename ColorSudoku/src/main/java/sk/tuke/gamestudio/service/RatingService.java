package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.util.List;

public interface RatingService {
    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, String player) throws RatingException;
    List<Rating> getRatingPlayer(String game) throws RatingException;
    Rating getRatingClass(String game, String player) throws RatingException;
    void reset() throws RatingException;
}