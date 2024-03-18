package main.java.sk.tuke.gamestudio.entity;


import java.sql.Date;
import java.sql.Timestamp;

public class Rating {
    private String player;
    private String game;
    private int rating;
    private Timestamp ratedOn;

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