package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.List;

@RestController
@RequestMapping("api/score")
public class ScoreServiceREST {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/topscores/{game}")
    public List<Score> getTopScores(@PathVariable String game){
        return scoreService.getTopScores(game);
    }

    @GetMapping("/allscores/{game}")
    public List<Score> getAllScores(@PathVariable String game){
        return scoreService.getAllScores(game);
    }

    @PostMapping
    public void addScore(@RequestBody Score score){
        scoreService.addScore(score);
    }
}
