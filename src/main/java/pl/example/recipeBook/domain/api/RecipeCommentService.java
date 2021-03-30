package pl.example.recipeBook.domain.api;

import pl.example.recipeBook.domain.comment.Comment;
import pl.example.recipeBook.domain.comment.CommentDao;
import pl.example.recipeBook.domain.user.User;
import pl.example.recipeBook.domain.user.UserDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecipeCommentService {
    private CommentDao commentDao = new CommentDao();
    private RecipeCommentMapper recipeCommentMapper = new RecipeCommentMapper();

    public void addComment(RecipeComment recipeComment){
        Comment commentToSave = recipeCommentMapper.map(recipeComment);
        commentDao.save(commentToSave);
    }

    public List<RecipeComment> findCommentsByRecipeId(int recipeId){
        return commentDao.findByRecipeId(recipeId)
                .stream()
                .map(recipeCommentMapper::map)
                .collect(Collectors.toList());
    }

    private static class RecipeCommentMapper{
        private final UserDao userDao = new UserDao();

        public RecipeComment map(Comment comment){
            return new RecipeComment(
                    comment.getRecipeId(),
                    userDao.findById(comment.getUserId()).orElseThrow().getUsername(),
                    comment.getContent(),
                    comment.getDateAdded()
            );
        }

        public Comment map(RecipeComment recipeComment) {
            Optional<User> user = userDao.findByUsername(recipeComment.getUsername());
            return new Comment(
                    user.orElseThrow().getId(),
                    recipeComment.getRecipeId(),
                    recipeComment.getContent(),
                    LocalDateTime.now()
            );
        }
    }
}
