package sk.tuke.gamestudio.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sk.tuke.gamestudio.server.Controller.GameBoardController;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration
@EntityScan("sk.tuke.gamestudio.entity")
public class GameStudioServer {
    public static void main(String[] args){
        SpringApplication.run(GameStudioServer.class, args);
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceJPA();
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceJPA();
    }

    @Bean
    public PlayerAccountService accountService(){
        return new PlayerAccountServiceJPA();
    }

    @Bean
    public RatingService ratingService(){
        return new RatingServiceJPA();
    }
}