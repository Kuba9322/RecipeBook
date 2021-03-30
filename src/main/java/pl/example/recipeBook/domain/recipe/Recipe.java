package pl.example.recipeBook.domain.recipe;

import java.time.LocalDateTime;

public class Recipe {
    private Integer id;
    private String title;
    private String photo_url;
    private String description;
    private String ingredients;
    private Integer prepTimeInMinutes;
    private LocalDateTime dateAdded;
    private Integer categoryId;
    private Integer userId;

    public Recipe(Integer id, String title, String photo_url,
                  String description, String ingredients,
                  Integer prepTimeMin, LocalDateTime dateAdded,
                  Integer categoryId, Integer  userId) {
        this.id = id;
        this.title = title;
        this.photo_url = photo_url;
        this.description = description;
        this.ingredients = ingredients;
        this.prepTimeInMinutes = prepTimeMin;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Recipe(String title, String photo_url,
                  String description, String ingredients,
                  Integer prepTimeMin, LocalDateTime dateAdded,
                  Integer categoryId, Integer userId) {
        this.title = title;
        this.photo_url = photo_url;
        this.description = description;
        this.ingredients = ingredients;
        this.prepTimeInMinutes = prepTimeMin;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public Integer getPrepTimeInMinutes() {
        return prepTimeInMinutes;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }
}