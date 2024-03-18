package test.sk.tuke.gamestudio.service;

import main.java.sk.tuke.gamestudio.service.ScoreService;
import main.java.sk.tuke.gamestudio.service.ScoreServiceJDBC;
import org.junit.jupiter.api.Test;
import main.java.sk.tuke.gamestudio.entity.Score;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreServiceTest {
    private ScoreService scoreService = new ScoreServiceJDBC();

    @Test
    public void reset() {
        scoreService.reset();
        assertEquals(0, scoreService.getTopScores("ColorSudoku").size());
    }

    @Test
    public void addScore() {
        var date = new Timestamp(System.currentTimeMillis());
        var score = new Score("ColorSudoku", "Jaro", 120, date);
        scoreService.reset();
        scoreService.addScore(score);
        var scores = scoreService.getTopScores("ColorSudoku");
        assertEquals(1, scores.size());
        assertEquals("Jaro", scores.get(0).getPlayer());
        assertEquals("ColorSudoku", scores.get(0).getGame());
        assertEquals(120, scores.get(0).getPoints());
        assertEquals(date, scores.get(0).getPlayedOn());
        //assertSame(score, scores.get(0));//equal but asserts that does not match?
    }

    @Test
    public void getTopScores() {
        var date = new Timestamp(System.currentTimeMillis());
        scoreService.reset();
        scoreService.addScore(new Score("ColorSudoku", "Jaro", 200, date));
        scoreService.addScore(new Score("ColorSudoku", "Jozo", 250, date));
        scoreService.addScore(new Score("ColorSudoku", "Anca", 150, date));

        var scores = scoreService.getTopScores("ColorSudoku");
        assertEquals(3, scores.size());
        assertEquals("Jozo", scores.get(0).getPlayer());
        assertEquals(250, scores.get(0).getPoints());
        assertEquals("Jaro", scores.get(1).getPlayer());
        assertEquals(200, scores.get(1).getPoints());
        assertEquals("Anca", scores.get(2).getPlayer());
        assertEquals(150, scores.get(2).getPoints());
    }
}