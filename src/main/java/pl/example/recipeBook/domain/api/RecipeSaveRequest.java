package pl.example.recipeBook.domain.api;

import java.time.LocalDateTime;

public class RecipeSaveRequest {

    private String title;
    private String photo_url;
    private String description;
    private String ingredients;
    private Integer prepTimeMin;
    private Integer categoryId;
    private String author;

    public RecipeSaveRequest(String title, String photo_url,
                             String description, String ingredients,
                             Integer prepTimeMin, Integer categoryId,
                             String author) {
        this.title = title;
        this.photo_url = photo_url;
        this.description = description;
        this.ingredients = ingredients;
        this.prepTimeMin = prepTimeMin;
        this.categoryId = categoryId;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Integer getPrepTimeMin() {
        return prepTimeMin;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getAuthor() {
        return author;
    }
}
