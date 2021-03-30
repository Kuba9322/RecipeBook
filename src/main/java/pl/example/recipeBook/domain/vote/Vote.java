package pl.example.recipeBook.domain.vote;

import java.time.LocalDateTime;

public class Vote {
    private Integer userId;
    private Integer RecipeId;
    private Type type;
    private LocalDateTime dateAdded;

    public Vote(Integer userId, Integer recipeId, Type type, LocalDateTime dateAdded) {
        this.userId = userId;
        RecipeId = recipeId;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRecipeId() {
        return RecipeId;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public enum Type {
        UP, DOWN;
    }
}
