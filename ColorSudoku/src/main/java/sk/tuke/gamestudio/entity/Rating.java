package sk.tuke.gamestudio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NamedQuery( name = "Rating.resetRating",
        query = "DELETE FROM Rating")
@NamedQuery( name = "Rating.getAverageRating",
        query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game=:game")
@NamedQuery( name = "Rating.getRating",
        query = "SELECT r.rating FROM Rating r WHERE r.game=:game AND r.player=:player")
@NamedQuery( name = "Rating.getRatingPlayer",
        query = "SELECT r.rating FROM Rating r WHERE r.game=:game")
public class Rating implements Serializable {
    @Id
    @GeneratedValue
    private int ident;
    private String player;
    private String game;
    private int rating;
    private Timestamp ratedOn;
    public Rating(){}

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Rating(String player, String game, int rating, Timestamp ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getRatedOn() {
        return ratedOn;
    }

    public void setRatedOn(Timestamp ratedOn) {
        this.ratedOn = ratedOn;
    }
}