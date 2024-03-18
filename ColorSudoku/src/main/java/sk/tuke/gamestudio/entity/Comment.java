package main.java.sk.tuke.gamestudio.entity;


import java.sql.Date;

public class Comment {
    private String player;
    private String game;
    private String comment;
    private Date commentedOn;

    public Comment(String player, String game, String comment, Date comentedOn) {
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

    public Date getCommentedOn() {
        return commentedOn;
    }

    public void setCommentedOn(Date comentedOn) {
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
