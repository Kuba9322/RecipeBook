package pl.example.recipeBook.domain.comment;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

public class Comment {
    private Integer commentId;
    private Integer userId;
    private Integer recipeId;
    private String content;
    private LocalDateTime dateAdded;

    public Comment(Integer userId, Integer recipeId, String content, LocalDateTime dateAdded) {
        this.userId = userId;
        this.recipeId = recipeId;
        this.content = content;
        this.dateAdded = dateAdded;
    }

    public Comment(Integer commentId, Integer userId, Integer recipeId, String content, LocalDateTime dateAdded) {
        this.commentId = commentId;
        this.userId = userId;
        this.recipeId = recipeId;
        this.content = content;
        this.dateAdded = dateAdded;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
}
