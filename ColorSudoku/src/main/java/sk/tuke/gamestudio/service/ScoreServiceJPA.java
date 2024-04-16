package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException {
        if(score.getPoints() < 0)
            score.setPoints(0);
        List<Score> scoresList = getAllScores("ColorSudoku");
        for(Score checkedScore: scoresList){
            if(checkedScore.getPlayer().equals(score.getPlayer()))
                score.setIdent(checkedScore.getIdent());
        }
        entityManager.merge(score);
        //entityManager.persist(score);
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        var scores =  entityManager.createNamedQuery("Score.getTopScores")
                .setParameter("game", game).setMaxResults(10).getResultList();
        return scores;
    }

    @Override
    public List<Score> getAllScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getAllScores")
                .setParameter("game", game).getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScores").executeUpdate();
    }
}