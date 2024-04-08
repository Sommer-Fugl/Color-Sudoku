package sk.tuke.gamestudio.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NamedQuery( name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")
@NamedQuery(name = "Score.getAllScores",
        query = "SELECT s FROM Score s WHERE s.game=:game")
@NamedQuery( name = "Score.resetScores",
        query = "DELETE FROM Score")
public class Score implements Serializable {
    @Id
    @GeneratedValue
    private int ident;
    public Score(){}
    public int getIdent() { return ident; }
    public void setIdent(int ident) { this.ident = ident; }

    private String game;
    private String player;

    private int points;

    private Timestamp playedOn;

    public Score(String game, String player, int points, Timestamp playedOn) {
        this.game = game;
        this.player = player;
        this.points = points;
        this.playedOn = playedOn;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Timestamp getPlayedOn() {
        return playedOn;
    }

    public void setPlayedOn(Timestamp playedOn) {
        this.playedOn = playedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", points=" + points +
                ", playedOn=" + playedOn +
                '}';
    }

}