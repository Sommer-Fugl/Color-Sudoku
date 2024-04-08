package sk.tuke.gamestudio.Service;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.CommentServiceJDBC;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        comment.setIdent(1);
        commentService.addComment(comment);
        assertEquals("Pitty", commentService.getComments("ColorSudoku").get(0).getComment());
    }

    @Test
    public void getComment(){
        commentService.reset();
        var comment1 = new Comment("Karo", "ColorSudoku", "Putty", new Timestamp(System.currentTimeMillis()));
        comment1.setIdent(2);
        var comment2 = new Comment("Fikar", "ColorSudoku", "Werty", new Timestamp(System.currentTimeMillis()));
        comment2.setIdent(3);
        var comment3 = new Comment("Judo", "ColorSudoku", "Pitty", new Timestamp(System.currentTimeMillis()));
        comment3.setIdent(4);
        commentService.addComment(comment1);
        commentService.addComment(comment2);
        commentService.addComment(comment3);
        var commentList = commentService.getComments("ColorSudoku");

        assertEquals(comment1.getComment(), commentList.get(0).getComment());
        assertEquals(comment1.getComment(), commentList.get(0).getComment());
        assertEquals(comment1.getComment(), commentList.get(0).getComment());

        assertEquals(comment1.getPlayer(), commentList.get(0).getPlayer());
        assertEquals(comment1.getPlayer(), commentList.get(0).getPlayer());
        assertEquals(comment1.getPlayer(), commentList.get(0).getPlayer());

    }
}