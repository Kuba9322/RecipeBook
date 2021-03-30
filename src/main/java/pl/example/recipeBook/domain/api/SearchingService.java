package pl.example.recipeBook.domain.api;

import java.util.List;
import java.util.stream.Collectors;

public class SearchingService {


    public List<RecipeBasicInfo> search(String searchingText, List<RecipeBasicInfo> recipes) {
        return recipes.stream()
                .filter(recipeBasicInfo -> isContainText(searchingText, recipeBasicInfo))
                .collect(Collectors.toList());
    }

    private boolean isContainText(String searchingText, RecipeBasicInfo recipeBasicInfo) {
        String s = searchingText.toLowerCase();
        return recipeBasicInfo.getTitle().toLowerCase().contains(s) ||
                recipeBasicInfo.getDescription().toLowerCase().contains(s) ||
                recipeBasicInfo.getIngredients().toLowerCase().contains(s);
    }
}
