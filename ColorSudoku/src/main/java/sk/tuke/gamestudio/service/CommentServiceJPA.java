package sk.tuke.gamestudio.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;
@Transactional
public class CommentServiceJPA implements CommentService{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addComment(Comment comment) throws CommentException {
        List<Comment> comments = getComments("ColorSudoku");
        for(Comment checkedComment: comments){
            if(checkedComment.getPlayer().equals(comment.getPlayer()))
                comment.setIdent(checkedComment.getIdent());
        }
        entityManager.merge(comment);
        //entityManager.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return entityManager.createNamedQuery("Comment.getComments")
                .setParameter("game",game).getResultList();
    }

    @Override
    public void reset() throws CommentException {
        entityManager.createNamedQuery("Comment.reset").executeUpdate();
    }
}