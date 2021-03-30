package pl.example.recipeBook.domain.api;

public class RecipeVote {
    private String username;
    private Integer RecipeId;
    private String type;

    public RecipeVote(String username, Integer recipeId, String type) {
        this.username = username;
        RecipeId = recipeId;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public Integer getRecipeId() {
        return RecipeId;
    }

    public String getType() {
        return type;
    }
}
