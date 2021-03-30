package pl.example.recipeBook.domain.api;

import java.time.LocalDateTime;

public class RecipeBasicInfo {
    private Integer id;
    private String title;
    private String url;
    private String description;
    private String ingredients;
    private Integer prepTimeInMinutes;
    private LocalDateTime dateAdded;
    private int voteCount;
    private String author;

    public RecipeBasicInfo(Integer id, String title, String url,
                           String description, String ingredients,
                           Integer prepTimeInMinutes, LocalDateTime dateAdded,
                           int voteCount, String author) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.ingredients = ingredients;
        this.prepTimeInMinutes = prepTimeInMinutes;
        this.dateAdded = dateAdded;
        this.voteCount = voteCount;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getIngredients() {
        return ingredients;
    }

    public Integer getPrepTimeInMinutes() {
        return prepTimeInMinutes;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
}