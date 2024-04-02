package sk.tuke.gamestudio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NamedQuery( name = "Comment.reset",
        query = "DELETE FROM Comment")
@NamedQuery( name = "Comment.getComments",
        query = "SELECT c FROM Comment c WHERE c.game=:game ORDER BY c.commentedOn DESC")
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private int ident;
    private String player;
    private String game;
    private String comment;
    private Timestamp commentedOn;
    public Comment(){};

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }

    public Comment(String player, String game, String comment, Timestamp comentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = comentedOn;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Timestamp comentedOn) {
        this.commentedOn = comentedOn;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "game='" + game + '\'' +
                player + '\'' +
                "commented: " + comment +
                ", commentedOn=" + commentedOn +
                '}';
    }
}