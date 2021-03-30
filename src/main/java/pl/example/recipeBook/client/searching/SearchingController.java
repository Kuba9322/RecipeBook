package pl.example.recipeBook.client.searching;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.example.recipeBook.domain.api.*;
import pl.example.recipeBook.domain.category.Category;

import java.io.IOException;
import java.util.*;

@WebServlet("/search")
public class SearchingController extends HttpServlet {
    private RecipeService recipeService = new RecipeService();
    private SearchingService searchingService = new SearchingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        String searchingText = getSearchingText(parameterMap);
        Set<String> strings = parameterMap.keySet();
        List<RecipeBasicInfo> recipesByCategories = getRecipes(strings);
        List<RecipeBasicInfo> recipes = searchingService.search(searchingText, recipesByCategories);
        request.setAttribute("recipes" , recipes);
        request.getRequestDispatcher("/WEB-INF/views/selected-recipes.jsp").forward(request, response);
    }

    private List<RecipeBasicInfo> getRecipes(Set<String> strings) {
        List<RecipeBasicInfo> recipes = new ArrayList<>();
        Iterator<String> iterator = strings.iterator();
        iterator.next();
        while (iterator.hasNext()){
            int id = Integer.parseInt(iterator.next());
            List<RecipeBasicInfo> recipesById = recipeService.findByCategory(id);
            recipes.addAll(recipesById);
        }
        return recipes;
    }

    private String getSearchingText(Map<String, String[]> parameterMap) {
        String[] searches = parameterMap.get("search");
        return searches[0];
    }
}
