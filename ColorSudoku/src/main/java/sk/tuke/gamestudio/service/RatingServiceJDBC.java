package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.Rating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingServiceJDBC implements RatingService{
    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String SELECT = "SELECT ident,game, player, rating, rated_On FROM rating WHERE game = ?";
    public static final String DELETE = "DELETE FROM rating";
    public static final String INSERT = "INSERT INTO rating (ident,game, player, rating, rated_On) VALUES (?,?, ?, ?, ?)";
    @Override
    public void setRating(Rating rating) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(INSERT)
        ){
            statement.setInt(1,rating.getIdent());
            statement.setString(2, rating.getGame());
            statement.setString(3,rating.getPlayer());
            statement.setInt(4, rating.getRating());
            statement.setTimestamp(5, new Timestamp(rating.getRatedOn().getTime()));
            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new RatingException("Problem setting rating", e);
        }
    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT))
        {
            statement.setString(1, game);
            try(ResultSet rs = statement.executeQuery()){
                List<Rating> ratings = new ArrayList<>();
                while(rs.next()){
                    ratings.add(new Rating(rs.getString(3), rs.getString(2), rs.getInt(4), rs.getTimestamp(5)));
                    ratings.getLast().setIdent(rs.getInt(1));
                }
                int counter = 0;
                for (Rating rating : ratings) {
                    counter += rating.getRating();
                }
                return Math.round((float)(counter / ratings.size()));
            }
        }
        catch (SQLException e){
            throw new RatingException("Problem with rating", e);
        }
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT))
        {
            statement.setString(1, game);
            try(ResultSet rs = statement.executeQuery()){
                List<Rating> ratings = new ArrayList<>();
                while(rs.next()){
                    ratings.add(new Rating(rs.getString(3), rs.getString(2), rs.getInt(4), rs.getTimestamp(5)));
                    ratings.getLast().setIdent(rs.getInt(1));
                }
                for(Rating oneRating: ratings){
                    if(oneRating.getPlayer().equals(player))
                        return oneRating.getRating();
                }
                return -1;
            }
        } catch(SQLException e){
            throw new RatingException("Problem with rating", e);
        }
    }

    @Override
    public List<Rating> getRatingPlayer(String game) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT))
        {
            statement.setString(1, game);
            try(ResultSet rs = statement.executeQuery()){
                List<Rating> ratings = new ArrayList<>();
                while(rs.next()){
                    ratings.add(new Rating(rs.getString(3), rs.getString(2), rs.getInt(4), rs.getTimestamp(5)));
                    ratings.getLast().setIdent(rs.getInt(1));
                }
                return ratings;
            }
        } catch(SQLException e){
            throw new RatingException("Problem with rating", e);
        }
    }

    @Override
    public Rating getRatingClass(String game, String player) throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT))
        {
            statement.setString(1, game);
            try(ResultSet rs = statement.executeQuery()){
                List<Rating> ratings = new ArrayList<>();
                while(rs.next()){
                    ratings.add(new Rating(rs.getString(3), rs.getString(2), rs.getInt(4), rs.getTimestamp(5)));
                    ratings.getLast().setIdent(rs.getInt(1));
                }
                for(Rating oneRating: ratings){
                    if(oneRating.getPlayer().equals(player))
                        return oneRating;
                }
                return null;
            }
        } catch(SQLException e){
            throw new RatingException("Problem with rating", e);
        }
    }

    @Override
    public void reset() throws RatingException {
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement = connection.createStatement())
        {
            statement.executeUpdate(DELETE);
        }catch (SQLException e){
            throw new RatingException("Problem with rating", e);
        }
    }
}