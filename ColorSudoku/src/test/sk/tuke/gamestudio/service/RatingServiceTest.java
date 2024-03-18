package test.sk.tuke.gamestudio.service;

import main.java.sk.tuke.gamestudio.entity.Rating;
import main.java.sk.tuke.gamestudio.service.RatingService;
import main.java.sk.tuke.gamestudio.service.RatingServiceJDBC;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class RatingServiceTest {
    private RatingService ratingService = new RatingServiceJDBC();

    @Test
    public void reset(){
        ratingService.reset();
        //assertNotEquals(1, ratingService.getAverageRating("ColorSudoku"));
    }

    @Test
    public void setRating(){
        ratingService.reset();
        var rating = new Rating("Kargo","ColorSudoku",3,new Timestamp(System.currentTimeMillis()));
        ratingService.setRating(rating);
        var ratings = ratingService.getRating("ColorSudoku", "Kargo");
        assertSame(3, ratings);
    }

    @Test
    public void getAverageRating(){
        ratingService.reset();
        var rating1 = new Rating("Judo","ColorSudoku", 4, new Timestamp(System.currentTimeMillis()));
        var rating2 = new Rating("Pippin","ColorSudoku", 5, new Timestamp(System.currentTimeMillis()));
        var rating3 = new Rating("Zuzanna","ColorSudoku", 3, new Timestamp(System.currentTimeMillis()));
        ratingService.setRating(rating1);
        ratingService.setRating(rating2);
        ratingService.setRating(rating3);
        var average = ratingService.getAverageRating("ColorSudoku");
        assertSame(4, average);
    }
}