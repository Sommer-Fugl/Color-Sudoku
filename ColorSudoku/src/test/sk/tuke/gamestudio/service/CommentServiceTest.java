package test.sk.tuke.gamestudio.service;

import main.java.sk.tuke.gamestudio.entity.Comment;
import main.java.sk.tuke.gamestudio.service.CommentService;
import main.java.sk.tuke.gamestudio.service.CommentServiceJDBC;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {
    private CommentService commentService = new CommentServiceJDBC();

    @Test
    public void reset() {
        commentService.reset();
        var comment = commentService.getComments("ColorSudoku");
        assertEquals(0, comment.size());
    }

    @Test
    public void addComment() {
        commentService.reset();
        var comment = new Comment("Judo", "ColorSudoku", "Pitty", new Timestamp(System.currentTimeMillis()));
        commentService.addComment(comment);
        assertEquals("Pitty", commentService.getComments("ColorSudoku").get(0).getComment());
    }
}