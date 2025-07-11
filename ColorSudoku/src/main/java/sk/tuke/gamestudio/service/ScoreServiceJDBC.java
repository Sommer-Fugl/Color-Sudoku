package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ScoreServiceJDBC implements ScoreService {
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT ident,game, player, points, played_On FROM score WHERE game = ? ORDER BY points DESC LIMIT 10";
    public static final String DELETE = "DELETE FROM score";
    public static final String INSERT = "INSERT INTO score (ident,game, player, points, played_On) VALUES (?, ?, ?, ?, ?)";

    @Override
    public void addScore(Score score) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setInt(1, score.getIdent());
            statement.setString(2, score.getGame());
            statement.setString(3, score.getPlayer());
            statement.setInt(4, score.getPoints());
            statement.setTimestamp(5, new Timestamp(score.getPlayedOn().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new ScoreException("Problem inserting score", e);
        }
    }

    @Override
    public List<Score> getTopScores(String game) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1, game);
            try (ResultSet rs = statement.executeQuery()) {
                List<Score> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(new Score(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5)));
                    scores.getLast().setIdent(rs.getInt(1));
                }
                return scores;
            }
        } catch (SQLException e) {
            throw new ScoreException("Problem selecting score", e);
        }
    }

    @Override
    public List<Score> getAllScores(String game) throws ScoreException {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT);
        ){
            statement.setString(1, game);
            try (ResultSet rs = statement.executeQuery()) {
                List<Score> scores = new ArrayList<>();
                while (rs.next()) {
                    scores.add(new Score(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getTimestamp(5)));
                    scores.getLast().setIdent(rs.getInt(1));
                }
                return scores;
            }
        }
        catch (SQLException e){
            throw new ScoreException("Problem connecting to database" + e);
        }
    }

    @Override
    public void reset() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new ScoreException("Problem deleting score", e);
        }
    }
}