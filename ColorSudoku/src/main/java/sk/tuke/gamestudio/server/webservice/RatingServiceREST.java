package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.service.RatingService;

import java.util.List;

@RestController
@RequestMapping("api/rating")
public class RatingServiceREST {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public void setRating(@RequestBody Rating rating){
        ratingService.setRating(rating);
    }

    @GetMapping("/average/{game}")
    public int getAverageRating(@PathVariable String game){
        return ratingService.getAverageRating(game);
    }

    @GetMapping("/onerating/{game}/{player}")
    public int getRating(@PathVariable String game, @PathVariable String player){
        return ratingService.getRating(game, player);
    }

    @GetMapping("/getratingclass/{game}/{player}")
    public Rating getRatingClass(@PathVariable String game, @PathVariable String player){
        return ratingService.getRatingClass(game, player);
    }

    @GetMapping("/ratinglist/{game}")
    public List<Rating> getRatingPlayer(@PathVariable String game){
        return ratingService.getRatingPlayer(game);
    }
}
