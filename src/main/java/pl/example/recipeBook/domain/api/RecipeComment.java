package pl.example.recipeBook.domain.api;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

public class RecipeComment {
    private Integer recipeId;
    private String username;
    private String content;
    private LocalDateTime dateAdded;

    public RecipeComment(Integer recipeId, String username, String content, LocalDateTime dateAdded) {
        this.recipeId = recipeId;
        this.username = username;
        this.content = content;
        this.dateAdded = dateAdded;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
}
