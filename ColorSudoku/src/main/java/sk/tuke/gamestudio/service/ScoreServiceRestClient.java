package sk.tuke.gamestudio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Score;

import java.util.Arrays;
import java.util.List;

public class ScoreServiceRestClient implements ScoreService{
    private final String url = "http://localhost:8080/api/score";

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void addScore(Score score) {
        restTemplate.postForEntity(url, score, Score.class);
    }

    @Override
    public List<Score> getTopScores(String game) {
        return Arrays.asList(restTemplate
                .getForEntity(url + "/topscores/" + game, Score[].class).getBody());
    }

    @Override
    public List<Score> getAllScores(String game) throws ScoreException {
        return Arrays.asList(restTemplate.getForEntity(url + "/allscores/" + game, Score[].class).getBody());
    }

    @Override
    public void reset() {
        throw new UnsupportedOperationException("Not supported via web service");
    }
}
