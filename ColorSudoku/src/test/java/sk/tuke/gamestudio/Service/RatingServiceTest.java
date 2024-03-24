package sk.tuke.gamestudio.Service;

import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.RatingServiceJDBC;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class RatingServiceTest {
    private RatingService ratingService = new RatingServiceJDBC();

    @Test
    public void reset(){
        var rating = new Rating("Karo", "ColorSudoku", 4,new Timestamp(System.currentTimeMillis()));
        ratingService.setRating(rating);
        ratingService.reset();
        assertEquals(0, ratingService.getRatingPlayer("ColorSudoku").size());
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

    @Test
    public void getRating(){
        ratingService.reset();
        var rating = new Rating("Waster", "ColorSudoku", 3, new Timestamp(System.currentTimeMillis()));
        ratingService.setRating(rating);
        var gottenRating = ratingService.getRating("ColorSudoku", "Waster");
        assertEquals(rating.getRating(), gottenRating);
    }

    @Test
    public void getRatingPlayer(){
        ratingService.reset();
        var rating1 = new Rating("Judo","ColorSudoku", 4, new Timestamp(System.currentTimeMillis()));
        var rating2 = new Rating("Pippin","ColorSudoku", 5, new Timestamp(System.currentTimeMillis()));
        var rating3 = new Rating("Zuzanna","ColorSudoku", 3, new Timestamp(System.currentTimeMillis()));
        ratingService.setRating(rating1);
        ratingService.setRating(rating2);
        ratingService.setRating(rating3);

        var ratings = ratingService.getRatingPlayer("ColorSudoku");

        assertEquals(rating1.getPlayer(), ratings.get(0).getPlayer());
        assertEquals(rating2.getPlayer(), ratings.get(1).getPlayer());
        assertEquals(rating3.getPlayer(), ratings.get(2).getPlayer());

        assertEquals(rating1.getRating(), ratings.get(0).getRating());
        assertEquals(rating2.getRating(), ratings.get(1).getRating());
        assertEquals(rating3.getRating(), ratings.get(2).getRating());

    }
}