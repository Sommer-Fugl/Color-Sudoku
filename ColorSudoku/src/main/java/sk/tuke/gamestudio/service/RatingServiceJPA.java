package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Rating;

import java.util.List;
@Transactional
public class RatingServiceJPA implements RatingService{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void setRating(Rating rating) throws RatingException {
        entityManager.persist(rating);
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        var rating =  entityManager.createNamedQuery("Rating.getAverageRating")
                .setParameter("game", game).getSingleResult();
        if(rating == null)
            return 0;
        return (int)Math.round((double)rating);
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        return (int) entityManager.createNamedQuery("Rating.getRating")
                .setParameter("game", game).setParameter("player", player)
                .getSingleResult();
    }

    @Override
    public List<Rating> getRatingPlayer(String game) throws RatingException {
        return entityManager.createNamedQuery("Rating.getRatingPlayer")
                .setParameter("game", game).getResultList();
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();
    }
}
