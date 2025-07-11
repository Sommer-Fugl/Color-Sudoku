package sk.tuke.gamestudio.server.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("api/comment")
public class CommentServiceREST {
    @Autowired
    private CommentService commentService;

    @GetMapping("/getcomments/{game}")
    public List<Comment> getComments(@PathVariable String game){
        return commentService.getComments(game);
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment){
        commentService.addComment(comment);
    }
}